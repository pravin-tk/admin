package org.school.admin.controller;

import java.util.Calendar;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.TransientObjectException;
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
//	public ResponseMessage saveCampus(
//			@FormParam("school_id") int school_id,
//			@FormParam("updated_by") int updated_by,
//			@FormParam("campus_size") String campus_size,
//			@FormParam("area_unit") String area_unit,
//			@FormParam("no_of_building") String no_of_building,
//			@FormParam("no_of_playground") String no_of_playground,
//			@FormParam("no_of_total_student") String no_of_total_student,
//			@FormParam("no_of_student_boys") String no_of_student_boys,
//			@FormParam("no_of_student_girls") String no_of_student_girls,
//			@FormParam("no_of_male_teaching_staff") String no_of_male_teaching_staff,
//			@FormParam("no_of_female_teaching_staff") String no_of_female_teaching_staff,
//			@FormParam("no_of_female_supporting_staff") String no_of_female_supporting_staff,
//			@FormParam("no_of_male_supporting_staff") String no_of_male_supporting_staff)
//			{
	
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
//		if(no_of_building == "" 
//					|| no_of_playground == "" 
//					||campus_size == "" 
//					|| no_of_total_student == "" 
//					|| no_of_student_boys == ""
//					|| no_of_student_girls == ""
//					|| no_of_male_teaching_staff == ""
//					|| no_of_female_teaching_staff == ""
//					|| no_of_male_supporting_staff == ""
//					|| no_of_female_supporting_staff == "")
//				{
//			        response.setStatus(0);
//			        response.setMessage("All Fields are mendatory");
//			   
//					return response;
//				}
//				else
//				{
		    
			CampusInfo campusInfo = new CampusInfo();
			AreaUnit areaUnit = new AreaUnit();
		    		areaUnit.setId(0);
					School school = new School();
					school.setId(0);
					campusInfo.setAreaUnit(areaUnit);
					campusInfo.setSchool(school);
					campusInfo.setTotalBoys(0);
					campusInfo.setTotalBuildings(0);
					campusInfo.setTotalFemaleTeacher(0);
					campusInfo.setTotalGirls(0);
					campusInfo.setTotalMaleTeacher(0);
					campusInfo.setTotalPlaygrounds(0);
					campusInfo.setMaleSupportingStaff(0);
					campusInfo.setFemaleSupportingStaff(0);
					campusInfo.setCampusSize(0);
					campusInfo.setName("");
					
					try{
						
						school.setId(school_id);
						if(area_unit.trim().length() !=0 || area_unit != null)
						areaUnit.setId(Integer.parseInt(area_unit));
					campusInfo.setSchool(school);
					campusInfo.setAreaUnit(areaUnit);
					if(campus_size.trim().length() != 0)
					campusInfo.setCampusSize(Double.parseDouble(campus_size));
					if(no_of_building.trim().length() != 0)
					campusInfo.setTotalBuildings(Integer.parseInt(no_of_building));
					if(no_of_playground.trim().length() != 0)
					campusInfo.setTotalPlaygrounds(Integer.parseInt(no_of_playground));
					if(no_of_total_student.trim().length() != 0)
					campusInfo.setTotalStudents(Integer.parseInt(no_of_total_student));
					if(no_of_student_boys.trim().length() != 0)
					campusInfo.setTotalBoys(Integer.parseInt(no_of_student_boys));
					if(no_of_student_girls.trim().length() != 0)
					campusInfo.setTotalGirls(Integer.parseInt(no_of_student_girls));
					if(no_of_male_teaching_staff.trim().length() != 0)
					campusInfo.setTotalMaleTeacher(Integer.parseInt(no_of_male_teaching_staff));
					if(no_of_female_teaching_staff.trim().length() != 0)
					campusInfo.setTotalFemaleTeacher(Integer.parseInt(no_of_female_teaching_staff));
					if(no_of_female_supporting_staff.trim().length() != 0)
					campusInfo.setFemaleSupportingStaff(Integer.parseInt(no_of_female_supporting_staff));
					if(no_of_male_supporting_staff.trim().length() != 0)
					campusInfo.setMaleSupportingStaff(Integer.parseInt(no_of_male_supporting_staff));
						areaUnit.setId(Integer.parseInt(area_unit));
						campusInfo.setSchool(school);
						campusInfo.setAreaUnit(areaUnit);
//						
//						campusInfo.setCampusSize(campus_size);
//						campusInfo.setTotalBuildings(no_of_building);
//						campusInfo.setTotalPlaygrounds(no_of_playground);
//						campusInfo.setTotalStudents(no_of_total_student);
//						campusInfo.setTotalBoys(no_of_student_boys);
//						campusInfo.setTotalGirls(no_of_student_girls);
//						campusInfo.setTotalMaleTeacher(no_of_male_teaching_staff);
//						campusInfo.setTotalFemaleTeacher(no_of_female_teaching_staff);
//						campusInfo.setFemaleSupportingStaff(no_of_female_supporting_staff);
//						campusInfo.setMaleSupportingStaff(no_of_male_supporting_staff);
					campusInfo.setLastUpdatedBy(updated_by);
					campusInfo.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
					
					CampusService campusService =new CampusService();
					//campusService.saveCampus(campusInfo) ;
					
					return campusService.saveCampus(campusInfo);
					}
					catch(NumberFormatException e)
					{
						e.printStackTrace();
						System.out.println(e);
						response.setStatus(0);
						response.setMessage("Faile to save campus details");
						return response;
					}
					catch(TransientObjectException e)
					{
					//	e.printStackTrace();
						System.out.println(e);
						response.setStatus(0);
						response.setMessage("Please select campus unit");
						return response;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println(e);
						response.setStatus(0);
						response.setMessage("Faile to save campus details");
						return response;
					}
				}
	 
			}