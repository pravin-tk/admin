package org.school.admin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;

import org.school.admin.Emailer;
import org.school.admin.dao.SchoolSearchUserDao;
import org.school.admin.data.SchoolList;
import org.school.admin.data.UserInfo;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.model.UserRegistrationInfo;

public class SchoolSearchUserService {
	private ServletContext context;
	private CommonUtility commonUtility;
	public SchoolSearchUserService(ServletContext servletContext) {
		this.context = servletContext;
		this.commonUtility = new CommonUtility();
	}
	String img_path;
	
	public ResponseMessage addSchoolSearchUser(SchoolSearchUser schoolSearchUser, InputStream inputStream, String uploadFileLocation)
	{
		SchoolSearchUserDao schoolSearchUserRepo = new SchoolSearchUserDao();
		return schoolSearchUserRepo.addSchoolSearchUser(schoolSearchUser, inputStream, uploadFileLocation );
	}
	
	public ResponseMessage userLogin(Integer socialType, SchoolSearchUser schoolSearchUser, InputStream inputStream, String uploadFileLocation)
	{
		SchoolSearchUserDao schoolSearchUserRepo = new SchoolSearchUserDao();
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(socialType == 0) {
			SchoolSearchUser fetchedUser = schoolSearchUserRepo.checkUserCredentials(schoolSearchUser);
			if(fetchedUser == null) {
				responseMessage.setMessage("Invalid credentials.");
				responseMessage.setStatus(0);
			} else {
				responseMessage.setMessage("Success");
				responseMessage.setStatus(1);
				responseMessage.setData(fetchedUser);
			}
		} else if(socialType == 1 || socialType == 2) {
			SchoolSearchUser fetchedUser = schoolSearchUserRepo.fetchUserByEmailId(schoolSearchUser);
			if(fetchedUser == null) {
				schoolSearchUserRepo.addSchoolSearchUser(schoolSearchUser, inputStream, uploadFileLocation );
				responseMessage.setMessage("Success");
				responseMessage.setStatus(1);
				schoolSearchUser.setPassword("");;
				responseMessage.setData(schoolSearchUser);
			} else {
				responseMessage.setData(fetchedUser);
			}
			responseMessage.setMessage("Success");
			responseMessage.setStatus(1);
		}
		return responseMessage;
	}
	
	public ResponseMessage checkLogin(Integer socialType, UserRegistrationInfo userRegistrationInfo, InputStream inputStream, String uploadFileLocation)
	{
		img_path = this.context.getInitParameter("s3_base_url");
		SchoolSearchUserDao schoolSearchUserRepo = new SchoolSearchUserDao();
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		UserRegistrationInfo fetchedUser = schoolSearchUserRepo.getUserByEmailId(userRegistrationInfo.getEmail());
		if(socialType == 0) {
			if(fetchedUser == null) {
				errors.add("Invalid credentials.");
				responseMessage.setErrors(errors);
				responseMessage.setMessage("Invalid credentials.");
				responseMessage.setStatus(0);
			} else {
				if (userRegistrationInfo.getPassword().equals(fetchedUser.getPassword())) {
					if(fetchedUser.getImage().trim().length() <= 0){
						fetchedUser.setImage("avatar/avatar.jpg");
					} else {
						fetchedUser.setImage(img_path+fetchedUser.getImage());
					}
					responseMessage.setId(fetchedUser.getId());
					responseMessage.setMessage("Success");
					responseMessage.setStatus(1);
					responseMessage.setData(fetchedUser);
				} else {
					errors.add("Invalid credentials.");
					responseMessage.setErrors(errors);
					responseMessage.setMessage("Invalid credentials.");
					responseMessage.setStatus(0);
				}
			}
		} else if(socialType == 1 || socialType == 2) {
			if(fetchedUser == null) {
				String autoPass = this.commonUtility.randomString(8);
				userRegistrationInfo.setPassword(autoPass);
				responseMessage = schoolSearchUserRepo.signUpUser(userRegistrationInfo, inputStream, uploadFileLocation );
				userRegistrationInfo.setId(responseMessage.getId());
				if(userRegistrationInfo.getImage().trim().length() <= 0){
					userRegistrationInfo.setImage("avatar/avatar.jpg");
				} else {
					userRegistrationInfo.setImage(img_path+userRegistrationInfo.getImage());
				}
				responseMessage.setData(userRegistrationInfo);
			} else {
				if(fetchedUser.getImage().trim().length() <= 0){
					fetchedUser.setImage("avatar/avatar.jpg");
				} else {
					fetchedUser.setImage(img_path+fetchedUser.getImage());
				}
				responseMessage.setId(fetchedUser.getId());
				responseMessage.setData(fetchedUser);
				responseMessage.setMessage("Success");
				responseMessage.setStatus(1);
			}
		}
		return responseMessage;
	}
	
	public UserInfo getUserInfo(int id){
		img_path = this.context.getInitParameter("s3_base_url");
		UserInfo userInfo = new UserInfo();
		SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
		userInfo = schoolSearchUserDao.getProfile(id);
		if(userInfo.getImage() != null && userInfo.getImage().trim().length() > 0){
			userInfo.setImage(img_path+userInfo.getImage());
		}
		return userInfo;
	}

	public ResponseMessage activateUser(String email, String password) {
		SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
		return schoolSearchUserDao.updatePassword(email,password);
	}
	
	public Boolean resetUserPassword(String email) {
		SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
		String password = this.commonUtility.randomString(8);
		boolean status = schoolSearchUserDao.resetPassword(email,password);
		if(status){
			//Sending newly generated password to user.
			Emailer emailer = new Emailer();
			emailer.setTo(email);
			emailer.setSubject("Resetting password with edbuddy.com is successful.");
			emailer.setBody("Your password is : " + password);
			emailer.sendEmail();
		}
		return status;
	}

	public Boolean changeUserPassword(String email, String password) {
		SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
		return schoolSearchUserDao.changePassword(email,password);
	}

	public List<SchoolList> getShortlistedSchoolsByUserId(Integer userId) {
		SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
		return schoolSearchUserDao.fetchShortlistedSchoolsByUserId(userId);
	}
	
	public List<SchoolList> getAppliedSchoolsByUserId(Integer userId) {
		SchoolSearchUserDao schoolSearchUserDao = new SchoolSearchUserDao();
		return schoolSearchUserDao.fetchAppliedSchoolsByUserId(userId);
	}

}
