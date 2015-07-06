package org.school.admin.controller;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.dao.FacilityImpl;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.InfrastructureCategoryItem;
import org.school.admin.model.SafetyCategory;
import org.school.admin.model.SafetyCategoryItem;
import org.school.admin.model.ActivityCategory;
import org.school.admin.model.ActivityCategoryItem;
import org.school.admin.model.InfrastructureCategory;

@Path("facility")
public class FacilityController {
	
	@POST
	@Path("/safetycat/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveSafetyCategory(
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("desc") String desc
			){
		SafetyCategory safetyCategory = new SafetyCategory();
		safetyCategory.setName(name);
		safetyCategory.setStatus(status);
		safetyCategory.setDesc(desc);
//		safetyCategory.setLastUpdatedOn(new Date());
		FacilityImpl facility = new FacilityImpl();
		return facility.saveSafetyCategory(safetyCategory);
	}
	
	@POST
	@Path("/safetycat/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSafetyCategory(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("desc") String desc
	){
	
		SafetyCategory safetyCategory = new SafetyCategory();
		safetyCategory.setId(id);
		safetyCategory.setName(name);
		safetyCategory.setStatus(status);
		safetyCategory.setDesc(desc);
		FacilityImpl facility = new FacilityImpl();
		return facility.updateSafetyCategory(safetyCategory);
	}
	
	@POST
	@Path("/safetycatitem/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addSafetyCatItem(
			@FormParam("name") String name,
			@FormParam("categoryId") int categoryId,
			@FormParam("desc") String desc,
			@FormParam("status") byte status
	){
		SafetyCategory safetyCategory = new SafetyCategory();
		safetyCategory.setId(categoryId);
		SafetyCategoryItem safetyCategoryItem = new SafetyCategoryItem();
		safetyCategoryItem.setName(name);
		safetyCategoryItem.setSafetyCategory(safetyCategory);
		safetyCategoryItem.setDescription(desc);
		safetyCategoryItem.setStatus(status);
		FacilityImpl facilityImpl = new FacilityImpl();
		return facilityImpl.saveSafetyCategoryItem(safetyCategoryItem);
	}
	
	@POST
	@Path("/safetycatitem/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSafetyCatItem(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("categoryId") int categoryId,
			@FormParam("desc") String desc,
			@FormParam("status") byte status
	){
		SafetyCategory safetyCategory = new SafetyCategory();
		safetyCategory.setId(categoryId);
		SafetyCategoryItem safetyCategoryItem = new SafetyCategoryItem();
		safetyCategoryItem.setId(id);
		safetyCategoryItem.setName(name);
		safetyCategoryItem.setSafetyCategory(safetyCategory);
		safetyCategoryItem.setDescription(desc);
		safetyCategoryItem.setStatus(status);
		FacilityImpl facilityImpl = new FacilityImpl();
		System.out.println("Saving item object");
		return facilityImpl.updateSafetyCategoryItem(safetyCategoryItem);
	}
	/*** Activity ***/
	
	
	@POST
	@Path("/activitycat/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveActivityCategory(
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("desc") String desc
			){
		ActivityCategory activityCategory = new ActivityCategory();
		activityCategory.setName(name);
		activityCategory.setStatus(status);
		activityCategory.setDescription(desc);
//		safetyCategory.setLastUpdatedOn(new Date());
		FacilityImpl facility = new FacilityImpl();
		return facility.saveActivityCategory(activityCategory);
	}
	
	@POST
	@Path("/activitycat/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateActivityCategory(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("desc") String desc
	){
	
		ActivityCategory activityCategory = new ActivityCategory();
		activityCategory.setId(id);
		activityCategory.setName(name);
		activityCategory.setStatus(status);
		activityCategory.setDescription(desc);
		FacilityImpl facility = new FacilityImpl();
		return facility.updateActivityCategory(activityCategory);
	}
	
	@POST
	@Path("/activitycatitem/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addActivityCatItem(
			@FormParam("name") String name,
			@FormParam("categoryId") int categoryId,
			@FormParam("desc") String desc,
			@FormParam("status") byte status
	){
		ActivityCategory activityCategory = new ActivityCategory();
		activityCategory.setId(categoryId);
		ActivityCategoryItem activityCategoryItem = new ActivityCategoryItem();
		activityCategoryItem.setName(name);
		activityCategoryItem.setActivityCategory(activityCategory);
		activityCategoryItem.setDescription(desc);
		activityCategoryItem.setStatus(status);
		FacilityImpl facilityImpl = new FacilityImpl();
		return facilityImpl.saveActivityCategoryItem(activityCategoryItem);
	}
	
	@POST
	@Path("/activitycatitem/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateActivityCatItem(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("categoryId") int categoryId,
			@FormParam("desc") String desc,
			@FormParam("status") byte status
	){
		ActivityCategory activityCategory = new ActivityCategory();
		activityCategory.setId(categoryId);
		ActivityCategoryItem activityCategoryItem = new ActivityCategoryItem();
		activityCategoryItem.setId(id);
		activityCategoryItem.setName(name);
		activityCategoryItem.setActivityCategory(activityCategory);
		activityCategoryItem.setDescription(desc);
		activityCategoryItem.setStatus(status);
		FacilityImpl facilityImpl = new FacilityImpl();
		System.out.println("Saving item object");
		return facilityImpl.updateActivityCategoryItem(activityCategoryItem);
	}
	
	/**************************/
	
	/**** Infrastructure ***/
	
	@POST
	@Path("/infracat/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveInfrastructureCategory(
			@FormParam("name") String name
	){
		InfrastructureCategory infrastructureCategory = new InfrastructureCategory();
		infrastructureCategory.setName(name);
		FacilityImpl facility = new FacilityImpl();
		return facility.saveInfrastructureCategory(infrastructureCategory);
	}
	
	@POST
	@Path("/infracat/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateInfrastructureCategory(
			@FormParam("id") int id,
			@FormParam("name") String name
	){
	
		InfrastructureCategory infrastructureCategory = new InfrastructureCategory();
		infrastructureCategory.setId(id);
		infrastructureCategory.setName(name);
		FacilityImpl facility = new FacilityImpl();
		return facility.updateInfrastructureCategory(infrastructureCategory);
	}
	
	/* ********************** Inftra cat item ********************* */
	
	@POST
	@Path("/infracatitem/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveInfrastructureCategoryItem(
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("isOptional") byte isOptional,
			@FormParam("countItemName") String countItemName,
			@FormParam("desc") String desc,
			@FormParam("infra_cat_id") Integer infra_cat_id
			){
		InfrastructureCategoryItem infrastructureCategory = new InfrastructureCategoryItem();
		infrastructureCategory.setName(name);
		infrastructureCategory.setStatus(status);
		infrastructureCategory.setDescription(desc);
		infrastructureCategory.setIsOptional(isOptional);
		infrastructureCategory.setCountItemName(countItemName);
		infrastructureCategory.setLastUpdatedOn(new Date());
		InfrastructureCategory infraCat = new InfrastructureCategory();
		infraCat.setId(infra_cat_id);
		infrastructureCategory.setInfrastructureCategory(infraCat);
		FacilityImpl facility = new FacilityImpl();
		return facility.saveInfrastructureCategoryItem(infrastructureCategory);
	}
	
	@POST
	@Path("/infracatitem/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateInfrastructureCategoryItem(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("isOptional") byte isOptional,
			@FormParam("countItemName") String countItemName,
			@FormParam("desc") String desc,
			@FormParam("infra_cat_id") Integer infra_cat_id
	){
	
		InfrastructureCategoryItem infrastructureCategory = new InfrastructureCategoryItem();
		infrastructureCategory.setId(id);
		infrastructureCategory.setName(name);
		infrastructureCategory.setStatus(status);
		infrastructureCategory.setDescription(desc);
		InfrastructureCategory infraCat = new InfrastructureCategory();
		infraCat.setId(infra_cat_id);
		infrastructureCategory.setInfrastructureCategory(infraCat);
		infrastructureCategory.setIsOptional(isOptional);
		infrastructureCategory.setCountItemName(countItemName);
		FacilityImpl facility = new FacilityImpl();
		return facility.updateInfrastructureCategoryItem(infrastructureCategory);
	}
	
	/********************************/
}// end class


