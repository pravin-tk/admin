package org.school.admin.controller;

import java.io.InputStream;
import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.ServletContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.school.admin.dao.FacilityImpl;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.InfrastructureCategoryItem;
import org.school.admin.model.SafetyCategory;
import org.school.admin.model.SafetyCategoryItem;
import org.school.admin.model.ActivityCategory;
import org.school.admin.model.ActivityCategoryItem;
import org.school.admin.model.InfrastructureCategory;
import org.school.admin.service.ImageUploader;

@Path("facility")
public class FacilityController extends ResourceConfig {
	@Context ServletContext context;
	ImageUploader imageUploader = new ImageUploader();
	public FacilityController() {
		register(MultiPartFeature.class);
    }
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
			@FormDataParam("safetycatitemname") String name,
			@FormDataParam("catId") int categoryId,
			@FormDataParam("status") byte status,
			@FormDataParam("safety-image")  InputStream is, 
			@FormDataParam("safety-image") FormDataContentDisposition header
	){
		SafetyCategory safetyCategory = new SafetyCategory();
		safetyCategory.setId(categoryId);
		SafetyCategoryItem safetyCategoryItem = new SafetyCategoryItem();
		safetyCategoryItem.setName(name);
		System.out.println("HeaderSafety: "+header.getFileName());
		try{
			if(header.getFileName() != null || header.getFileName().trim().length() != 0){
				String image_name = name.replaceAll("([^a-zA-Z]|\\s)+", " ").replaceAll(" ", "-").toLowerCase();
				image_name = image_name+"-"+header.getFileName().replaceAll(" ", "-").toLowerCase();
				image_name = "milestones/"+image_name;
				String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
				this.imageUploader.writeToFile(is, uploadedFileLocation);
				safetyCategoryItem.setImage(image_name);
			}
			else{
				safetyCategoryItem.setImage("");
			}
		} catch(Exception e) {
			safetyCategoryItem.setImage("");
		}
		safetyCategoryItem.setSafetyCategory(safetyCategory);
	//	safetyCategoryItem.setDescription(desc);
		safetyCategoryItem.setStatus(status);
		FacilityImpl facilityImpl = new FacilityImpl();
		return facilityImpl.saveSafetyCategoryItem(safetyCategoryItem);
	}
	
	@POST
	@Path("/safetycatitem/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSafetyCatItem(
			@FormDataParam("id") int id,
			@FormDataParam("txtname") String name,
			@FormDataParam("categoryId") int categoryId,
			@FormDataParam("status") byte status,
			@FormDataParam("safety-image")  InputStream is, 
			@FormDataParam("safety-image") FormDataContentDisposition header
	){
		SafetyCategory safetyCategory = new SafetyCategory();
		safetyCategory.setId(categoryId);
		SafetyCategoryItem safetyCategoryItem = new SafetyCategoryItem();
		safetyCategoryItem.setId(id);
		safetyCategoryItem.setName(name);
		try{
		if(header.getFileName() != null || header.getFileName().trim().length() != 0){
			String image_name = name.replaceAll("([^a-zA-Z]|\\s)+", " ").replaceAll(" ", "-").toLowerCase();
			image_name = image_name+"-"+header.getFileName().replaceAll(" ", "-").toLowerCase();
			image_name = "milestones/"+image_name;
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			this.imageUploader.writeToFile(is, uploadedFileLocation);
			safetyCategoryItem.setImage(image_name);
		}
		else{
			safetyCategoryItem.setImage("");
		}
		}
		catch(Exception e){
			safetyCategoryItem.setImage("");
		}
		safetyCategoryItem.setSafetyCategory(safetyCategory);
	//	safetyCategoryItem.setDescription(desc);
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
			@FormDataParam("tname") String name,
			@FormDataParam("catId") int categoryId,
			@FormDataParam("status") byte status,
			@FormDataParam("activity-image")  InputStream is, 
			@FormDataParam("activity-image") FormDataContentDisposition header
			
	){
		System.out.println("ActivityItemName : "+name);
		ActivityCategory activityCategory = new ActivityCategory();
		activityCategory.setId(categoryId);
		ActivityCategoryItem activityCategoryItem = new ActivityCategoryItem();
		activityCategoryItem.setName(name);
		System.out.println("Header: "+header.getFileName());
		try{
		if(header.getFileName() != null || header.getFileName().trim().length() != 0){
			String image_name = name.replaceAll("([^a-zA-Z]|\\s)+", " ").replaceAll(" ", "-").toLowerCase();
			image_name = image_name+"-"+header.getFileName().replaceAll(" ", "-").toLowerCase();
			image_name = "milestones/"+image_name;
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			this.imageUploader.writeToFile(is, uploadedFileLocation);
			activityCategoryItem.setImage(image_name);
		}
		else{
			activityCategoryItem.setImage("");
		}
		}
		catch(Exception e){
			activityCategoryItem.setImage("");
		}
		activityCategoryItem.setActivityCategory(activityCategory);
		
		activityCategoryItem.setStatus(status);
		FacilityImpl facilityImpl = new FacilityImpl();
		return facilityImpl.saveActivityCategoryItem(activityCategoryItem);
	}
	
	@POST
	@Path("/activitycatitem/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateActivityCatItem(
			@FormDataParam("id") int id,
			@FormDataParam("tname") String name,
			@FormDataParam("categoryId") int categoryId,
			@FormDataParam("status") byte status,
			@FormDataParam("activity-image")  InputStream is, 
			@FormDataParam("activity-image") FormDataContentDisposition header
			
	){
		ActivityCategory activityCategory = new ActivityCategory();
		activityCategory.setId(categoryId);
		ActivityCategoryItem activityCategoryItem = new ActivityCategoryItem();
		activityCategoryItem.setId(id);
		activityCategoryItem.setName(name);
		try{
		if(header.getFileName() != null || header.getFileName().trim().length() != 0){
			String image_name = name.replaceAll("([^a-zA-Z]|\\s)+", " ").replaceAll(" ", "-").toLowerCase();
			image_name = image_name+"-"+header.getFileName().replaceAll(" ", "-").toLowerCase();
			image_name = "milestones/"+image_name;
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			this.imageUploader.writeToFile(is, uploadedFileLocation);
			activityCategoryItem.setImage(image_name);
		}
		else
		{
			activityCategoryItem.setImage("");
		}
		}
		catch(Exception e){
			activityCategoryItem.setImage("");
		}
		activityCategoryItem.setActivityCategory(activityCategory);
		//activityCategoryItem.setDescription(desc);
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
			@FormDataParam("infracatitemname") String name,
			@FormDataParam("status") byte status,
			@FormDataParam("isOptional") byte isOptional,
			@FormDataParam("countItemName") String countItemName,
			@FormDataParam("infracatid") Integer infra_cat_id, //infra-image
			@FormDataParam("infra-image")  InputStream is, 
			@FormDataParam("infra-image") FormDataContentDisposition header
			){
		InfrastructureCategoryItem infrastructureCategory = new InfrastructureCategoryItem();
		infrastructureCategory.setName(name);
		infrastructureCategory.setStatus(status);
		infrastructureCategory.setIsOptional(isOptional);
		infrastructureCategory.setCountItemName(countItemName);
		System.out.println("Header: "+header.getFileName());
		try{
		if(header.getFileName() != null || header.getFileName().trim().length() != 0){
			String image_name = name.replaceAll("([^a-zA-Z]|\\s)+", " ").replaceAll(" ", "-").toLowerCase();
			image_name = image_name+"-"+header.getFileName().replaceAll(" ", "-").toLowerCase();
			image_name = "milestones/"+image_name;
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			this.imageUploader.writeToFile(is, uploadedFileLocation);
			infrastructureCategory.setImage(image_name);
		}
		else{
			infrastructureCategory.setImage("");
		}
		}
		catch(Exception e){
			infrastructureCategory.setImage("");
		}
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
			@FormDataParam("id") int id,
			@FormDataParam("infracatitemname") String name,
			@FormDataParam("status") byte status,
			@FormDataParam("isOptional") byte isOptional,
			@FormDataParam("updatecountItemName") String countItemName,
			@FormDataParam("infrcatid") Integer infra_cat_id,
			@FormDataParam("infra-image")  InputStream is, 
			@FormDataParam("infra-image") FormDataContentDisposition header
	){
	
		InfrastructureCategoryItem infrastructureCategory = new InfrastructureCategoryItem();
		infrastructureCategory.setId(id);
		infrastructureCategory.setName(name);
		infrastructureCategory.setStatus(status);
		InfrastructureCategory infraCat = new InfrastructureCategory();
		infraCat.setId(infra_cat_id);
		infrastructureCategory.setInfrastructureCategory(infraCat);
		infrastructureCategory.setIsOptional(isOptional);
		infrastructureCategory.setCountItemName(countItemName);
		try{
			if(header.getFileName() != null || header.getFileName().trim().length() != 0){
				String image_name = name.replaceAll("([^a-zA-Z]|\\s)+", " ").replaceAll(" ", "-").toLowerCase();
				image_name = image_name+"-"+header.getFileName().replaceAll(" ", "-").toLowerCase();
				image_name = "milestones/"+image_name;
				String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
				this.imageUploader.writeToFile(is, uploadedFileLocation);
				infrastructureCategory.setImage(image_name);
			}
			else{
				infrastructureCategory.setImage("");
			}
			}
			catch(Exception e){
				infrastructureCategory.setImage("");
			}
		FacilityImpl facility = new FacilityImpl();
		return facility.updateInfrastructureCategoryItem(infrastructureCategory);
	}
	
	/********************************/
}// end class


