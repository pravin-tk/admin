package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.SchoolTypeDAOImpl;
import org.school.admin.model.SchoolType;

public class SchoolTypeService {
	
	public List<SchoolType> getSchoolTypeList()
	{
		SchoolTypeDAOImpl  schoolTypeDAOImpl = new SchoolTypeDAOImpl();
		return schoolTypeDAOImpl.getSchoolTypeList();
	}

}
