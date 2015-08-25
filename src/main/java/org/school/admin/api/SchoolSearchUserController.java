package org.school.admin.api;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.school.admin.Emailer;
import org.school.admin.dao.SchoolSearchImpl;
import org.school.admin.dao.SchoolSearchUserDao;
import org.school.admin.data.SchoolList;
import org.school.admin.data.UserInfo;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.service.CommonUtility;
import org.school.admin.service.SchoolSearchUserService;

@Path("api1.0/user/")
public class SchoolSearchUserController {
	@Context ServletContext context;
	
	@POST
	@Path("adduser.json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addUser( @FormDataParam("firstName") String firstName,
			@FormDataParam("lastName") String lastName,
			@FormDataParam("email") String email,
			@FormDataParam("password") String password,
			@FormDataParam("mobile") String mobile,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail
	) {
		SchoolSearchUser schoolSearchUser = new  SchoolSearchUser();
		schoolSearchUser.setFirstName(firstName);
		schoolSearchUser.setLastName(lastName);
		schoolSearchUser.setEmail(email);
		schoolSearchUser.setPassword(password);
		schoolSearchUser.setMobile(mobile);
		try{
			schoolSearchUser.setImage(fileDetail.getFileName());
		}catch(NullPointerException e){
			schoolSearchUser.setImage("na");
		}
		
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		ResponseMessage responseMessage = schoolSearchUserService.addSchoolSearchUser(schoolSearchUser, uploadedInputStream, context.getInitParameter("home_url"));
		return responseMessage;
	}
	
	
	@POST
	@Path("login")
//	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage login(@FormDataParam("socialType") Integer socialType,
			@FormDataParam("firstName") String firstName,
			@FormDataParam("lastName") String lastName,
			@FormDataParam("email") String email,
			@FormDataParam("password") String password,
			@FormDataParam("mobile") String mobile,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail
			) {
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		SchoolSearchUser schoolSearchUser = new  SchoolSearchUser();
		schoolSearchUser.setFirstName(firstName);
		schoolSearchUser.setLastName(lastName);
		schoolSearchUser.setEmail(email);
		schoolSearchUser.setPassword(password);
		schoolSearchUser.setMobile(mobile);
		try{
			schoolSearchUser.setImage(fileDetail.getFileName());
		}catch(NullPointerException e){
			schoolSearchUser.setImage("na");
		}
		ResponseMessage responseMessage = schoolSearchUserService.userLogin(socialType, schoolSearchUser, uploadedInputStream, context.getInitParameter("home_url"));
		return responseMessage;
	}
	
	/* ********************** New SignUp ************************* */
	@POST
	@Path("signup.json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage signUp( 
			@FormDataParam("firstName") String firstName,
			@FormDataParam("lastName") String lastName,
			@FormDataParam("email") String email,
			@FormDataParam("mobile") String mobile,
			@FormDataParam("image") InputStream uploadedInputStream,
			@FormDataParam("image") FormDataContentDisposition fileDetail
	) {
		String img_path = this.context.getInitParameter("s3_base_url");
		CommonUtility commonUtility = new CommonUtility();
		ResponseMessage responseMessage = new ResponseMessage();
		UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
		userRegistrationInfo.setFirstName(firstName);
		userRegistrationInfo.setLastName(lastName);
		userRegistrationInfo.setEmail(email);
		userRegistrationInfo.setMobile(mobile);
		String password = commonUtility.randomString(8);
		userRegistrationInfo.setPassword(password);
		try{
			userRegistrationInfo.setImage(fileDetail.getFileName());
		}catch(NullPointerException e){
			userRegistrationInfo.setImage("");
		}
		Boolean isValidEmail = true;

		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
	    } catch (AddressException ex) {
	    	isValidEmail = false;
	    }
		if (isValidEmail) {
			SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
			responseMessage = schoolSearchUserDao.signUpUser(userRegistrationInfo, uploadedInputStream, context.getInitParameter("home_url"), 0);
			userRegistrationInfo.setId(responseMessage.getId());
			if(userRegistrationInfo.getImage().trim().length() <= 0){
				userRegistrationInfo.setImage("avatar/avatar.jpg");
			} else {
				userRegistrationInfo.setImage(img_path+userRegistrationInfo.getImage());
			}
			userRegistrationInfo.setStatus((byte)0);
			responseMessage.setData(userRegistrationInfo);
			
			//Sending newly generated password to user.
			if( responseMessage.getStatus() == 1) {
				Emailer emailer = new Emailer();
				emailer.setTo(email);
				emailer.setSubject("Registration with edbuddy.com is successful.");
				emailer.setBody("Thank you for registering with edbuddy.com. Your password is : " + password);
				emailer.sendEmail();
			}
		} else {
			ArrayList<String> errors = new ArrayList<String>();
			errors.add("Invalid email.");
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Failed to signup.");
		}
		return responseMessage;
	}
	
	@POST
	@Path("update.json")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateProfile( 
			@FormDataParam("id") Integer id,
			@FormDataParam("firstName") String firstName,
			@FormDataParam("lastName") String lastName,
			@FormDataParam("mobile") String mobile,
			@FormDataParam("image") InputStream uploadedInputStream,
			@FormDataParam("image") FormDataContentDisposition fileDetail
	) {
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
		userRegistrationInfo.setId(id);
		userRegistrationInfo.setFirstName(firstName);
		userRegistrationInfo.setLastName(lastName);
		userRegistrationInfo.setMobile(mobile);
		
		try{
			if(fileDetail != null && uploadedInputStream != null) {
				userRegistrationInfo.setImage(fileDetail.getFileName());
			}
		}catch(NullPointerException e){
			//userRegistrationInfo.setImage("");
		}
		
		if (id > 0) {
			if(firstName.trim().length() <= 0){
				errors.add("First Name required.");
			}
			if(!mobile.matches("\\d{10}") && !mobile.matches("\\d{0}")){
				errors.add("Invalid mobile number.");
			}
			if(errors.size() > 0){
				responseMessage.setErrors(errors);
				responseMessage.setStatus(0);
				responseMessage.setMessage("Failed to update profile.");
			} else {
				SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
				responseMessage = schoolSearchUserDao.updateUser(userRegistrationInfo, uploadedInputStream, context.getInitParameter("home_url"));
			}
		} else {
			errors.add("User Id required.");
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Failed to update profile.");
		}
		return responseMessage;
	}
	
	@POST
	@Path("login.json")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage loginUser(
			@FormDataParam("socialType") Integer socialType,
			@FormDataParam("firstName") String firstName,
			@FormDataParam("lastName") String lastName,
			@FormDataParam("email") String email,
			@FormDataParam("password") String password,
			@FormDataParam("mobile") String mobile,
			@FormDataParam("image") InputStream uploadedInputStream,
			@FormDataParam("image") FormDataContentDisposition fileDetail
	) {
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		UserRegistrationInfo userRegistrationInfo = new  UserRegistrationInfo();
		userRegistrationInfo.setFirstName(firstName);
		userRegistrationInfo.setLastName(lastName);
		userRegistrationInfo.setEmail(email);
		userRegistrationInfo.setPassword(password);
		userRegistrationInfo.setMobile(mobile);
		try{
			userRegistrationInfo.setImage(fileDetail.getFileName());
		}catch(NullPointerException e){
			userRegistrationInfo.setImage("");
		}
		Boolean isValidEmail = true;
		try {
		       InternetAddress emailAddr = new InternetAddress(email);
		       emailAddr.validate();
	    } catch (AddressException ex) {
	    	isValidEmail = false;
	    }
		if (isValidEmail) {
			responseMessage = schoolSearchUserService.checkLogin(socialType, userRegistrationInfo, uploadedInputStream, context.getInitParameter("home_url"));
		} else {
			errors.add("Invalid Email.");
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Invalid email.");
		}
		return responseMessage;
	}
	
	@GET
	@Path("profile.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserInfo getUserProfile(@PathParam("id") int id){
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		return schoolSearchUserService.getUserInfo(id);
	}
	
	@POST
	@Path("activate.json")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage activateAccount(
		@FormParam("email") String email,
		@FormParam("password") String password
	){
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		try {
		       InternetAddress emailAddr = new InternetAddress(email);
		       emailAddr.validate();
	    } catch (AddressException ex) {
	    	errors.add("Invalid Email.");
	    }
		if (password.trim().length() <= 0) {
			errors.add("Password required.");
		} else if (password.trim().length() < 8) {
			errors.add("Minimum 8 character password required.");
		}
		if (errors.size() == 0) {
			responseMessage = schoolSearchUserService.activateUser(email,password);
		} else {
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Invalid email or password.");
		}
		return responseMessage;
	}
	
	@POST
	@Path("forgot.json")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage forgotPassword(
		@FormParam("email") String email
	){
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(this.context);
		try {
		       InternetAddress emailAddr = new InternetAddress(email);
		       emailAddr.validate();
	    } catch (AddressException ex) {
	    	errors.add("Invalid Email.");
	    }
		if (errors.size() == 0) {
			if(schoolSearchUserDao.getUserByEmailId(email) != null){
				if(schoolSearchUserService.resetUserPassword(email)){
					responseMessage.setStatus(1);
					responseMessage.setMessage("Passowrd sent to your registered email.");
				} else {
					errors.add("Failed to reset password.");
					responseMessage.setErrors(errors);
					responseMessage.setStatus(0);
					responseMessage.setMessage("Failed to reset password.");
				}
			} else {
				errors.add("This email id is not registered with us.");
				responseMessage.setErrors(errors);
				responseMessage.setStatus(0);
				responseMessage.setMessage("Failed to reset password.");
			}
		} else {
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Invalid email.");
		}
		return responseMessage;
	}
	
	@POST
	@Path("changepassword.json")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage changePassword(
		@FormParam("email") String email,
		@FormParam("password") String password
	){
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		try {
		       InternetAddress emailAddr = new InternetAddress(email);
		       emailAddr.validate();
	    } catch (AddressException ex) {
	    	errors.add("Invalid Email.");
	    }
		if (password.trim().length() <= 0) {
			errors.add("Password required.");
		} else if (password.trim().length() < 8) {
			errors.add("Minimum 8 character password required.");
		}
		if (errors.size() == 0) {
			if(schoolSearchUserService.changeUserPassword(email,password)){
				responseMessage.setStatus(1);
				responseMessage.setMessage("Password set successfully.");
			} else {
				errors.add("Failed to change password.");
				responseMessage.setErrors(errors);
				responseMessage.setStatus(0);
				responseMessage.setMessage("Failed to change password.");
			}
		} else {
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Invalid email.");
		}
		return responseMessage;
	}
	
	@GET
	@Path("shortlistedschools.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolList> getShortlistedSchools(@PathParam("id") int userId) {
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		return schoolSearchUserService.getShortlistedSchoolsByUserId(userId);
	}
	
	@GET
	@Path("appliedschools.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolList> getAppliedSchools(@PathParam("id") int userId) {
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService(context);
		return schoolSearchUserService.getAppliedSchoolsByUserId(userId);
	}
	
	@GET
	@Path("shortlist.json/{schoolId}/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage getShortlistedSchools(@PathParam("schoolId") Integer schoolId, @PathParam("userId") Integer userId) {
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.shortlistSchool(schoolId, userId);
	}
}
