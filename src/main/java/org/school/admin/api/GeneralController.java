package org.school.admin.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.school.admin.dao.UserImpl;
import org.school.admin.data.ApplicantInfo;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.PostRequirement;
@Path("api1.0/post/")
public class GeneralController extends ResourceConfig {
	@Context ServletContext context;
	public GeneralController() {
		register(MultiPartFeature.class);
    }
	
	@POST
	@Path("requirement.json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addRequirement(PostRequirement postRequirement) {
		UserImpl userImpl = new UserImpl();
		ResponseMessage responseMessage = userImpl.postRequirement(postRequirement);
		return responseMessage;
	}
	
	@POST
	@Path("studentinfo.json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addRequirement(
		@FormDataParam("schoolId") Integer schoolId,
		@FormDataParam("userId") Integer userId,
		@FormDataParam("standardId") Short standardId,
		@FormDataParam("firstName") String firstName,
		@FormDataParam("middleName") String middleName,
		@FormDataParam("lastName") String lastName,
		@FormDataParam("gender") String gender,
		@FormDataParam("dob") String dob,
		@FormDataParam("bloodGroup") String bloodGroup,
		@FormDataParam("placeOfBirth") String placeOfBirth,
		@FormDataParam("nationality") String nationality,
		@FormDataParam("religion") String religion,
		@FormDataParam("catId") Short catId,
		@FormDataParam("flatNo") String flatNo,
		@FormDataParam("buildingName") String buildingName,
		@FormDataParam("locality") String locality,
		@FormDataParam("geocode") String geocode,
		@FormDataParam("city") String city,
		@FormDataParam("pincode") String pincode,
		@FormDataParam("whomSignature") String whomSignature,
		@FormDataParam("parentName[]") List<FormDataBodyPart> parentName,
		@FormDataParam("parentMobile[]") List<FormDataBodyPart> parentMobile,
		@FormDataParam("parentEmail[]") List<FormDataBodyPart> parentEmail,
		@FormDataParam("parentQualification[]") List<FormDataBodyPart> parentQualification,
		@FormDataParam("parentProfession[]") List<FormDataBodyPart> parentProfession,
		@FormDataParam("parentIncome[]") List<FormDataBodyPart> parentIncome,
		@FormDataParam("image") InputStream is, 
		@FormDataParam("image") FormDataContentDisposition header,
		@FormDataParam("signature") InputStream isSignature, 
		@FormDataParam("signature") FormDataContentDisposition headerSignature
	) {
		UserImpl userImpl = new UserImpl();
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		try{
			if(schoolId <= 0){
	    		errors.add("School Id required.");
	    	}
	    	if(userId <= 0){
	    		errors.add("User Id required.");
	    	}
	    	if(firstName.trim().length() <= 0){
	    		errors.add("First Name required.");
	    	}
	    	if(dob.trim().length() <= 0){
	    		errors.add("Date of Birth required.");
	    	} else {
	    		if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
	    			errors.add("Invalid date format.");
	    		}
	    	}
	    	if(gender.trim().length() <= 0){
	    		errors.add("Gender required.");
	    	}
	    	if(placeOfBirth.trim().length() <= 0){
	    		errors.add("Place of birth required.");
	    	}
	    	if(bloodGroup.trim().length() <= 0){
	    		errors.add("Blood group required.");
	    	}
			ApplicantInfo applicantInfo = new ApplicantInfo();
			applicantInfo.setSchoolId(schoolId);
			applicantInfo.setUserId(userId);
			applicantInfo.setFirstName(firstName);
			applicantInfo.setMiddleName(middleName);
			applicantInfo.setLastName(lastName);
			applicantInfo.setGender(gender);
			applicantInfo.setDob(dob);
			applicantInfo.setBloodGroup(bloodGroup);
			applicantInfo.setPlaceOfBirth(placeOfBirth);
			String image_name = firstName.replaceAll("([^a-zA-Z]|\\s)+", " ");
			image_name = image_name+header.getFileName();
			image_name = userId+"_"+image_name.replaceAll(" ", "_").toLowerCase();
			image_name = "avatar/"+image_name;
			applicantInfo.setImage(image_name);
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			writeToFile(is, uploadedFileLocation);
			responseMessage = userImpl.addApplicantDetails(applicantInfo);
		} catch(Exception e) {
			errors.add("Unable to add Info.");
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Unable to add Info.");
		}
		return responseMessage;
	}
	
	
	private void writeToFile(InputStream is, String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[102400];
 
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
 
			e.printStackTrace();
		}
		
	}
}
