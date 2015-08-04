package org.school.admin.controller;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.school.admin.dao.SalesDetailDAOImpl;
import org.school.admin.model.AdminUser;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;

@Path("salesdetail")
public class SalesDetailController {
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalesInfo> saveSalesDetail(@FormParam("school_id") int school_id,@FormParam("user_id") int user_id,@FormParam("sales_executive") int sales_executive,
											@FormParam("datacollector") int datacollector,@FormParam("contact_person_name") String contact_person,
											@FormParam("contact_person_no") String contact_person_no,@FormParam("contact_person_email") String contact_person_email, @FormParam("designation") String designation)
	{
		School school = new School();
		school.setId(school_id);
		
		AdminUser dataCollectorId = new AdminUser();
		dataCollectorId.setId(datacollector);
		
		AdminUser salesExecutiveId = new AdminUser();
		salesExecutiveId.setId(sales_executive);
		
		SalesInfo salesInfo = new SalesInfo();
		salesInfo.setSchool(school);
		salesInfo.setAdminUserByDataCollectorId(dataCollectorId);
		salesInfo.setAdminUserBySalesExecutiveId(salesExecutiveId);
		salesInfo.setInfoProviderContactNo(contact_person_no);
		salesInfo.setInfoProviderName(contact_person);
		salesInfo.setInfoProviderDesignation(designation);
		salesInfo.setInfoProviderEmail(contact_person_email);
		salesInfo.setLastUpdatedBy(user_id);
		salesInfo.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		
		SalesDetailDAOImpl salesDetailDAOImpl = new SalesDetailDAOImpl();
		return salesDetailDAOImpl.saveSalesDetail(salesInfo);
	}

	@POST
	@Path("update")
	
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalesInfo> updateSalesDetail(@FormParam("id") int id,@FormParam("school_id") int school_id,
			@FormParam("user_id") int user_id,
			@FormParam("sales_executive") int sales_executive,
			@FormParam("datacollector") int datacollector,
			@FormParam("contact_person_name") String contact_person,
			@FormParam("contact_person_no") String contact_person_no,
			@FormParam("contact_person_email") String contact_person_email, 
			@FormParam("designation") String designation,
			@FormParam("strReason") String reason)
	{
		School school = new School();
		school.setId(school_id);
		
		AdminUser dataCollectorId = new AdminUser();
		dataCollectorId.setId(datacollector);
		
		AdminUser salesExecutiveId = new AdminUser();
		salesExecutiveId.setId(sales_executive);
		
		SalesInfo salesInfo = new SalesInfo();
		salesInfo.setId(id);
		salesInfo.setSchool(school);
		salesInfo.setAdminUserByDataCollectorId(dataCollectorId);
		salesInfo.setAdminUserBySalesExecutiveId(salesExecutiveId);
		salesInfo.setInfoProviderContactNo(contact_person_no);
		salesInfo.setInfoProviderName(contact_person);
		salesInfo.setInfoProviderDesignation(designation);
		salesInfo.setInfoProviderEmail(contact_person_email);
		salesInfo.setLastUpdatedBy(user_id);
		salesInfo.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		
		SalesDetailDAOImpl salesDetailDAOImpl = new SalesDetailDAOImpl();
		return salesDetailDAOImpl.updateSalesDetail(salesInfo,reason);
	}

}
