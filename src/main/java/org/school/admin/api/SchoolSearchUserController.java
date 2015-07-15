package org.school.admin.api;

import java.io.InputStream;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.school.admin.dao.SchoolSearchUserDao;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.model.UserRegistrationInfo;
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
		
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService();
		ResponseMessage responseMessage = schoolSearchUserService.addSchoolSearchUser(schoolSearchUser, uploadedInputStream, context.getInitParameter("home_url"));
		return responseMessage;
	}
	
	@POST
	@Path("signup.json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage signUp( 
			@FormDataParam("firstName") String firstName,
			@FormDataParam("lastName") String lastName,
			@FormDataParam("email") String email,
			@FormDataParam("password") String password,
			@FormDataParam("mobile") String mobile,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail
	) {
		ResponseMessage responseMessage = new ResponseMessage();
		UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
		userRegistrationInfo.setFirstName(firstName);
		userRegistrationInfo.setLastName(lastName);
		userRegistrationInfo.setEmail(email);
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
			SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
			responseMessage = schoolSearchUserDao.signUpUser(userRegistrationInfo, uploadedInputStream, context.getInitParameter("home_url"));
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
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService();
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
	
}
