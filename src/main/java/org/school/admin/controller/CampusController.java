package org.school.admin.controller;

import java.util.Calendar;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.AreaUnit;
import org.school.admin.model.CampusInfo;
import org.school.admin.model.School;
import org.school.admin.service.CampusService;

@Path("campus")
public class CampusController {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/savecampus")
	public ResponseMessage saveCampus(
			@FormParam("school_id") int school_id,
			@FormParam("updated_by") int updated_by,
			@FormParam("campus_size") String campus_size,
			@FormParam("area_unit") String area_unit,
			@FormParam("no_of_building") String no_of_building,
			@FormParam("no_of_playground") String no_of_playground,
			@FormParam("no_of_total_student") String no_of_total_student,
			@FormParam("no_of_student_boys") String no_of_student_boys,
			@FormParam("no_of_student_girls") String no_of_student_girls,
			@FormParam("no_of_male_teaching_staff") String no_of_male_teaching_staff,
			@FormParam("no_of_female_teaching_staff") String no_of_female_teaching_staff,
			@FormParam("no_of_female_supporting_staff") String no_of_female_supporting_staff,
			@FormParam("no_of_male_supporting_staff") String no_of_male_supporting_staff)
			{
		    ResponseMessage response = new ResponseMessage();
		if(no_of_building == "" 
					|| no_of_playground == "" 
					||campus_size == "" 
					|| no_of_total_student == "" 
					|| no_of_student_boys == ""
					|| no_of_student_girls == ""
					|| no_of_male_teaching_staff == ""
					|| no_of_female_teaching_staff == ""
					|| no_of_male_supporting_staff == ""
					|| no_of_female_supporting_staff == "")
				{
			        response.setStatus(0);
			        response.setMessage("All Fields are mendatory");
			   
					return response;
				}
				else
				{
					School School = new School();
					School.setId(school_id);
					
					AreaUnit areaUnit = new AreaUnit();
					areaUnit.setId(Integer.parseInt(area_unit));
					
					CampusInfo campusInfo = new CampusInfo();
					
					campusInfo.setSchool(School);
					campusInfo.setAreaUnit(areaUnit);
					
					campusInfo.setCampusSize(Double.parseDouble(campus_size));
					campusInfo.setTotalBuildings(Integer.parseInt(no_of_building));
					campusInfo.setTotalPlaygrounds(Integer.parseInt(no_of_playground));
					campusInfo.setTotalStudents(Integer.parseInt(no_of_total_student));
					campusInfo.setTotalBoys(Integer.parseInt(no_of_student_boys));
					campusInfo.setTotalGirls(Integer.parseInt(no_of_student_girls));
					campusInfo.setTotalMaleTeacher(Integer.parseInt(no_of_male_teaching_staff));
					campusInfo.setTotalFemaleTeacher(Integer.parseInt(no_of_female_teaching_staff));
					campusInfo.setFemaleSupportingStaff(Integer.parseInt(no_of_female_supporting_staff));
					campusInfo.setMaleSupportingStaff(Integer.parseInt(no_of_male_supporting_staff));
					campusInfo.setLastUpdatedBy(updated_by);
					campusInfo.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
					
					CampusService campusService =new CampusService();
					campusService.saveCampus(campusInfo) ;
					
					return campusService.saveCampus(campusInfo);
				}
			}

}

