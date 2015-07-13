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
	
	public ResponseMessage userLogin(SchoolSearchUser schoolSearchUser)
	{
		SchoolSearchUserDao schoolSearchUserRepo = new SchoolSearchUserDao();
		SchoolSearchUser fetchedUser = schoolSearchUserRepo.checkUserCredentials(schoolSearchUser);
		ResponseMessage responseMessage = new ResponseMessage();
		if(fetchedUser == null) {
			responseMessage.setMessage("Invalid credentials.");
			responseMessage.setStatus(0);
		} else {
			responseMessage.setMessage("Success");
			responseMessage.setStatus(1);
			responseMessage.setData(fetchedUser);
		}
		return responseMessage;
	}

}
