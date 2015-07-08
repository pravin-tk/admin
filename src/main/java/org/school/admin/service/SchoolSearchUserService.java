package org.school.admin.service;

import org.school.admin.dao.SchoolSearchUserDao;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;

public class SchoolSearchUserService {
	
	public ResponseMessage addSchoolSearchUser(SchoolSearchUser schoolSearchUser)
	{
		SchoolSearchUserDao schoolSearchUserRepo = new SchoolSearchUserDao();
		return schoolSearchUserRepo.addSchoolSearchUser(schoolSearchUser);
	}

}
