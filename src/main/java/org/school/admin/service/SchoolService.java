package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.data.SchoolCompleteDetail;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.School;
import org.school.admin.model.SchoolBoard;
import org.school.admin.model.SchoolNameList;

public class SchoolService {

	public ResponseMessage addSchool(School School, Short boardId)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		
		return schoolDAOImp.save(School,boardId);
	}
	
	public List<School> getSchoolList(int school_id, int city_id, int locality_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolList(school_id, city_id, locality_id);
	}
	public List<School> getSchoolList(String contact_id,int cityId){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolList(contact_id,cityId);
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
	public ResponseMessage updateSchool(School School,SchoolBoard schoolBoard)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.updateSchool(School,schoolBoard);
	}
	
	public SchoolCompleteDetail getSchoolCompleteDetails(int schoolId)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		SchoolCompleteDetail schoolCompleteDetail = new SchoolCompleteDetail();
		schoolCompleteDetail.setSchoolTimelineData(schoolDAOImp.getSchoolTimelineDetails(schoolId));
		return schoolCompleteDetail;
	}
}
