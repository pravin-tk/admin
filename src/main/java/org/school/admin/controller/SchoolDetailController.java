package org.school.admin.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.school.admin.dao.SchoolDetailDAOImpl;
import org.school.admin.data.SchoolDetail;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.BoardType;
import org.school.admin.model.MediumType;
import org.school.admin.model.School;
import org.school.admin.model.SchoolBoard;
import org.school.admin.model.SchoolCategoryType;
import org.school.admin.model.SchoolClassificationType;
import org.school.admin.model.SchoolInfo;
import org.school.admin.model.SchoolMedium;
import org.school.admin.model.SchoolType;


@Path("test")
public class SchoolDetailController {
	

	@Context
	
	HttpServletRequest req ;
	HttpServletResponse response;
	HttpSession session;
	
	
	@POST
	@Path("/testschool")
	public ResponseMessage addSchoolDetail(@FormParam("school_id") int school_id,
			@FormParam("updated_by") int updated_by,
			@FormParam("school_website") String school_website,
			@FormParam("classification") int classificationId,
			@FormParam("school_type") Short school_type,
			@FormParam("school_category") int school_category,
			@FormParam("medium") String medium,
			@FormParam("board") Short  board,
			@FormParam("residential") Short residential,
			@FormParam("display_fee") Short display_fee
	){
		
		ResponseMessage response = new ResponseMessage();
		if(school_website.equals(""))
		{
			
		}
		
		if(medium == null || medium == ""){
			response.setStatus(0);
			response.setMessage("select medium of instruction");
				return response;
		}else if(board == 0){
			response.setStatus(0);
			response.setMessage("select board");
				return response;		}else
		if(classificationId > 0 && school_type > 0 && school_category > 0 && (medium != null || !medium.equals(""))){
         School school = new School();
         school.setId(school_id);
         SchoolClassificationType classification = new SchoolClassificationType();
         classification.setId(classificationId);
         SchoolType schoolType = new SchoolType();
         schoolType.setId(school_type);
         SchoolCategoryType schoolCategory = new SchoolCategoryType();
         schoolCategory.setId(school_category);
         SchoolInfo schoolInfo = new SchoolInfo();
         schoolInfo.setLastUpdatedBy(updated_by);
         schoolInfo.setSchool(school);
         schoolInfo.setSchoolWebsite(school_website);
         schoolInfo.setSchoolClassificationType(classification);
         schoolInfo.setSchoolType(schoolType);
         schoolInfo.setSchoolCategoryType(schoolCategory);
         schoolInfo.setLastUpdatedBy(updated_by);
         schoolInfo.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		 schoolInfo.setIsResidential(residential);
		 schoolInfo.setDisplayFee(display_fee);
		 schoolInfo.setApprovalDesc("");
		 schoolInfo.setAwardDesc("");
		 schoolInfo.setTieUpDesc("");
		 //Short boardId = Short.parseShort(board);
         BoardType boardType = new BoardType();
         boardType.setId(board);
       
        
         SchoolBoard schoolBoard = new SchoolBoard();
         schoolBoard.setSchool(school);
         schoolBoard.setBoardType(boardType);
        
         Set<SchoolBoard> schoolBoards = new HashSet<>();
         schoolBoards.add(schoolBoard);
         boardType.setSchoolBoards(schoolBoards);
		         
         String[] strMedium;
         strMedium = medium.split(",");

          Set<SchoolMedium> schoolMediums = new HashSet<SchoolMedium>();
          for(int i=0; i < strMedium.length;i++)
          {
	          MediumType mediumType = new MediumType();
	          mediumType.setId(Short.parseShort(strMedium[i]));
	          
	          SchoolMedium schoolMedium = new SchoolMedium();
	          schoolMedium.setSchool(school);
	          schoolMedium.setMediumType(mediumType);
	          schoolMedium.setLastUpdatedBy(updated_by);
	          schoolMedium.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	          
	          Set<SchoolMedium> schoolMediums1 = new HashSet<>();
	          schoolMediums1.add(schoolMedium);
	          mediumType.setSchoolMeduims(schoolMediums1);
	          schoolMediums.add(schoolMedium);
          
         }
		      
		 SchoolDetail schoolDetail = new SchoolDetail();
		 schoolDetail.setSchoolInfo(schoolInfo);
		 schoolDetail.setSchoolBoard(schoolBoard);
		 schoolDetail.setSchoolMedium(schoolMediums);
		        
		 SchoolDetailDAOImpl schoolDetailDAOImpl = new SchoolDetailDAOImpl();
		         
		 	return schoolDetailDAOImpl.saveSchoolDetail(schoolDetail);
		 }else{
			 if(classificationId == 0){
				 response.setStatus(0);
				 response.setMessage("Please select school classification");
				 return response;
			 }
			 if(school_type == 0){
				 response.setStatus(0);
				 response.setMessage("Please select school Management");
				 return response;
			 }
			 if(school_category == 0){
				 response.setStatus(0);
				 response.setMessage("Please select school Type");
				 return response;
			 }else{
				 response.setStatus(0);
				 response.setMessage("Please select proper data");
				 return response;
			 }
		 }
		
	}
	@POST
	@Path("/schooldetail")
	public String addNewSchoolDetail(@FormParam("school_id") int school_id,@FormParam("updated_by") int updated_by,
			@FormParam("school_website") String school_website,
			@FormParam("classification") int classification,@FormParam("school_type") int school_type,
			@FormParam("school_category") int school_category,@FormParam("medium") Short medium,@FormParam("board") Short  board)
	{
		School school = new School();
		school.setId(school_id);
		BoardType boardtype = new BoardType();
		boardtype.setId(board);
		
		SchoolDetailDAOImpl schooldetail = new SchoolDetailDAOImpl();
		schooldetail.saveSchoolBaord(boardtype, school);
		return "success";
	}

}
