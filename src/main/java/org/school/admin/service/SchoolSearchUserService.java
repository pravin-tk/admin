package org.school.admin.service;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.school.admin.dao.SchoolSearchUserDao;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;

public class SchoolSearchUserService {
	
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

}
