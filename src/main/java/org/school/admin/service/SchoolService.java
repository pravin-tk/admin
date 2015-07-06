package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.School;
import org.school.admin.model.SchoolNameList;

public class SchoolService {

	public ResponseMessage addSchool(School School)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		
		return schoolDAOImp.save(School);
	}
	
	public List<School> getSchoolList(int school_id, int city_id, int locality_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolList(school_id, city_id, locality_id);
	}
	
	public List<SchoolNameList> getSchoolNameList(int school_id, int city_id, int locality_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolNameList(school_id, city_id, locality_id);
	}
	
	public List<School> getSchoolDetails(int school_id)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolDetails(school_id);
	}
	public ResponseMessage updateSchool(School School)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.updateSchool(School);
	}
}