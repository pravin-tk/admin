package org.school.admin.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
import org.school.admin.data.UserInfo;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.ApplicantBasicDetail;
import org.school.admin.model.ApplicantParentDetail;
import org.school.admin.model.ApplicantPreSchoolDetail;
import org.school.admin.model.PostRequirement;
import org.school.admin.model.School;
import org.school.admin.model.StandardType;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.service.ImageUploader;
@Path("api1.0/post/")
public class GeneralController extends ResourceConfig {
	@Context ServletContext context;
	ImageUploader imageUploader;
	public GeneralController() {
		register(MultiPartFeature.class);
		imageUploader = new ImageUploader();
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
		@FormDataParam("geoCode") String geoCode,
		@FormDataParam("city") String city,
		@FormDataParam("pincode") String pincode,
		@FormDataParam("schoolName[]") List<FormDataBodyPart> schoolName,
		@FormDataParam("fromStd[]") List<FormDataBodyPart> fromStd,
		@FormDataParam("toStd[]") List<FormDataBodyPart> toStd,
		@FormDataParam("fromDate[]") List<FormDataBodyPart> fromDate,
		@FormDataParam("toDate[]") List<FormDataBodyPart> toDate,
		@FormDataParam("grade[]") List<FormDataBodyPart> grade,
		@FormDataParam("parentName[]") List<FormDataBodyPart> parentName,
		@FormDataParam("parentMobile[]") List<FormDataBodyPart> parentMobile,
		@FormDataParam("parentEmail[]") List<FormDataBodyPart> parentEmail,
		@FormDataParam("parentQualification[]") List<FormDataBodyPart> parentQualification,
		@FormDataParam("parentProfession[]") List<FormDataBodyPart> parentProfession,
		@FormDataParam("parentIncome[]") List<FormDataBodyPart> parentIncome,
		@FormDataParam("relationType[]") List<FormDataBodyPart> relationType,
		@FormDataParam("whomSignature") String whomSignature,
		@FormDataParam("image") InputStream is, 
		@FormDataParam("image") FormDataContentDisposition header,
		@FormDataParam("signature") InputStream isSignature, 
		@FormDataParam("signature") FormDataContentDisposition headerSignature
	) {
		UserImpl userImpl = new UserImpl();
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		String pattern = "yyyy-MM-dd";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    format.setLenient(false);
		try{
			ApplicantBasicDetail applicantBasicDetail = new ApplicantBasicDetail();
			School school = new School();
			school.setId(schoolId);
			UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
			userRegistrationInfo.setId(userId);
			StandardType standardType = new StandardType();
			standardType.setId(standardId);
			applicantBasicDetail.setSchool(school);
			applicantBasicDetail.setUserRegistrationInfo(userRegistrationInfo);
			applicantBasicDetail.setStandardType(standardType);
			applicantBasicDetail.setFirstName(firstName);
			applicantBasicDetail.setMiddleName(middleName);
			applicantBasicDetail.setLastName(lastName);
			String image_name = firstName.replaceAll("([^a-zA-Z]|\\s)+", " ");
			image_name = image_name+header.getFileName();
			image_name = userId+"_"+image_name.replaceAll(" ", "_").toLowerCase();
			image_name = "avatar/"+image_name;
			String signatureImage = firstName.replaceAll("([^a-zA-Z]|\\s)+", " ")+"-"+whomSignature.replaceAll("([^a-zA-Z]|\\s)+", " ");
			signatureImage = signatureImage+headerSignature.getFileName();
			signatureImage = userId+"_"+signatureImage.replaceAll(" ", "_").toLowerCase();
			signatureImage = "signature/"+signatureImage;
			applicantBasicDetail.setImage(image_name);
			applicantBasicDetail.setGender(gender);
			applicantBasicDetail.setDob(format.parse(dob));
			applicantBasicDetail.setBloodGroup(bloodGroup);
			applicantBasicDetail.setPlaceOfBirth(placeOfBirth);
			applicantBasicDetail.setNationality(nationality);
			applicantBasicDetail.setReligion(religion);
			applicantBasicDetail.setCatId(catId);
			applicantBasicDetail.setFlatNo(flatNo);
			applicantBasicDetail.setBuildingName(buildingName);
			applicantBasicDetail.setLocality(locality);
			applicantBasicDetail.setGeoCode(geoCode);
			applicantBasicDetail.setCity(city);
			applicantBasicDetail.setPincode(pincode);
			applicantBasicDetail.setWhomSignature(whomSignature);
			applicantBasicDetail.setSignature(signatureImage);
			applicantBasicDetail.setStatus((byte)0);
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			String uploadedSignatureLocation = this.context.getInitParameter("logo_url") + image_name;
			this.imageUploader.writeToFile(isSignature, uploadedSignatureLocation);
			this.imageUploader.writeToFile(is, uploadedFileLocation);
			
			Set<ApplicantParentDetail> applicantParentDetails = new HashSet<ApplicantParentDetail>();
			List<String> parentNameList = new ArrayList<String>();
			List<String> parentMobileList = new ArrayList<String>();
			List<String> parentEmailList = new ArrayList<String>();
			List<String> parentQualificationList = new ArrayList<String>();
			List<String> parentProfessionList = new ArrayList<String>();
			List<String> parentIncomeList = new ArrayList<String>();
			List<String> relationTypeList = new ArrayList<String>();
			if(parentName.size() > 0){
				for(FormDataBodyPart pname : parentName){
					if(pname.getValueAs(String.class).toString() != null) {
						parentNameList.add(pname.getValueAs(String.class).toString());
					} else {
						errors.add("Parent Name Required.");
					}
				}
			}
			if(parentMobile.size() > 0){
				for(FormDataBodyPart pmobile : parentMobile){
					if(pmobile.getValueAs(String.class).toString() != null) {
						String mobile = pmobile.getValueAs(String.class).toString();
						if (mobile.matches("\\d{10}") || mobile.matches("\\d{0}")) {
							parentMobileList.add(pmobile.getValueAs(String.class).toString());
						} else {
							errors.add("Invalid Parent Mobile("+mobile+").");
						}
					} else {
						errors.add("Parent Mobile Required.");
					}
				}
			}
			if(parentEmail.size() > 0){
				for(FormDataBodyPart pemail : parentEmail){
					if(pemail.getValueAs(String.class).toString() != null) {
						Boolean isvalidEmail = true;
						try {
					       InternetAddress emailAddr = new InternetAddress(pemail.getValueAs(String.class).toString());
					       emailAddr.validate();
					    } catch (AddressException ex) {
					    	isvalidEmail = false;
					    }
						if (isvalidEmail) {
							parentEmailList.add(pemail.getValueAs(String.class).toString());
						} else {
							errors.add("Invalid Parent Email("+pemail.getValueAs(String.class).toString()+").");
						}
					} else {
						errors.add("Parent Email Required.");
					}
				}
			}
			if(parentQualification.size() > 0){
				for(FormDataBodyPart pqualification : parentQualification){
					if(pqualification.getValueAs(String.class).toString() != null) {
						parentQualificationList.add(pqualification.getValueAs(String.class).toString());
					} else {
						errors.add("Parent Qualification Required.");
					}
				}
			}
			if(parentProfession.size() > 0){
				for(FormDataBodyPart pprofession : parentProfession){
					if(pprofession.getValueAs(String.class).toString() != null) {
						parentProfessionList.add(pprofession.getValueAs(String.class).toString());
					} else {
						errors.add("Parent Profession Required.");
					}
				}
			}
			if(parentIncome.size() > 0){
				for(FormDataBodyPart pincome : parentIncome){
					if(pincome.getValueAs(String.class).toString() != null) {
						String income = pincome.getValueAs(String.class).toString();
						if (income.matches("\\d{4,10}")) {
							parentIncomeList.add(pincome.getValueAs(String.class).toString());
						} else {
							errors.add("Invalid Annual Income("+income+").");
						}
					} else {
						errors.add("Parent Annual Income Required.");
					}
				}
			}
			
			if(relationType.size() > 0){
				for(FormDataBodyPart prelation : relationType){
					String relation = prelation.getValueAs(String.class).toString();
					if(prelation.getValueAs(String.class).toString() != null) {
						if(
							relation.equalsIgnoreCase("father") || 
							relation.equalsIgnoreCase("mother") || 
							relation.equalsIgnoreCase("guardian")
						){
							relationTypeList.add(prelation.getValueAs(String.class).toString());
						} else {
							errors.add("Invalid Parent Relation("+relation+").");
						}
					} else {
						errors.add("Parent Relation Required.");
					}
				}
			}
			for(int i=0; i<relationType.size();i++){
				ApplicantParentDetail applicantParentDetail = new ApplicantParentDetail();
				applicantParentDetail.setName(parentNameList.get(i));
				applicantParentDetail.setMobile(parentMobileList.get(i));
				applicantParentDetail.setEmail(parentEmailList.get(i));
				applicantParentDetail.setQualification(parentQualificationList.get(i));
				applicantParentDetail.setProfession(parentProfessionList.get(i));
				applicantParentDetail.setIncome(Double.parseDouble(parentIncomeList.get(i)));
				applicantParentDetail.setRelationType(Byte.parseByte(relationTypeList.get(i)));
				applicantParentDetails.add(applicantParentDetail);
			}
			
			Set<ApplicantPreSchoolDetail> applicantPreSchoolDetails = new HashSet<ApplicantPreSchoolDetail>();
			List<String> schoolNameList = new ArrayList<String>();
			List<String> fromStdList = new ArrayList<String>();
			List<String> toStdList = new ArrayList<String>();
			List<String> fromDateList = new ArrayList<String>();
			List<String> toDateList = new ArrayList<String>();
			List<String> gradeList = new ArrayList<String>();
			
			if(schoolName.size() > 0){
				for(FormDataBodyPart pschool : schoolName){
					if(pschool.getValueAs(String.class).toString() != null) {
						schoolNameList.add(pschool.getValueAs(String.class).toString());
					} else {
						errors.add("Previous School Name Required.");
					}
				}
			}
			
			if(fromStd.size() > 0){
				for(FormDataBodyPart pfromstd : fromStd){
					if(pfromstd.getValueAs(String.class).toString() != null) {
						fromStdList.add(pfromstd.getValueAs(String.class).toString());
					} else {
						errors.add("From Standard Required.");
					}
				}
			}
			
			if(toStd.size() > 0){
				for(FormDataBodyPart ptostd : toStd){
					if(ptostd.getValueAs(String.class).toString() != null) {
						toStdList.add(ptostd.getValueAs(String.class).toString());
					} else {
						errors.add("To standard Required.");
					}
				}
			}
			
			if(fromDate.size() > 0){
				for(FormDataBodyPart pfromdate : fromDate){
					String newfromdate = pfromdate.getValueAs(String.class).toString();
					if(pfromdate.getValueAs(String.class).toString() != null) {
						try {
							format.parse(newfromdate);
							fromDateList.add(pfromdate.getValueAs(String.class).toString());
						} catch(ParseException fe) {
							errors.add("Invalid From Date("+newfromdate+")");
						}
					} else {
						errors.add("From Date Required.");
					}
				}
			}
			
			if(toDate.size() > 0){
				for(FormDataBodyPart ptodate : toDate){
					if(ptodate.getValueAs(String.class).toString() != null) {
						String newtodate = ptodate.getValueAs(String.class).toString();
						try {
							format.parse(newtodate);
							toDateList.add(ptodate.getValueAs(String.class).toString());
						} catch(ParseException fe) {
							errors.add("Invalid From Date("+newtodate+")");
						}
					} else {
						errors.add("To date Required.");
					}
				}
			}
			
			if(grade.size() > 0){
				for(FormDataBodyPart pgrade : grade){
					if(pgrade.getValueAs(String.class).toString() != null) {
						gradeList.add(pgrade.getValueAs(String.class).toString());
					} else {
						errors.add("Grade Required.");
					}
				}
			}
			
			for(int i=0; i<schoolNameList.size();i++){
				ApplicantPreSchoolDetail applicantPreSchoolDetail = new ApplicantPreSchoolDetail();
				applicantPreSchoolDetail.setSchoolName(schoolNameList.get(i));
				applicantPreSchoolDetail.setFromStd(fromStdList.get(i));
				applicantPreSchoolDetail.setToStd(toStdList.get(i));
				applicantPreSchoolDetail.setFromDate(format.parse(fromDateList.get(i)));
				applicantPreSchoolDetail.setToDate(format.parse(toDateList.get(i)));
				applicantPreSchoolDetail.setGrade(gradeList.get(i));
				applicantPreSchoolDetails.add(applicantPreSchoolDetail);
			}
			
			applicantBasicDetail.setApplicantParentDetails(applicantParentDetails);
			applicantBasicDetail.setApplicantPreSchoolDetails(applicantPreSchoolDetails);
			if (errors.size() > 0){
				responseMessage.setStatus(0);
				responseMessage.setMessage("Fields not set properly.");
				responseMessage.setErrors(errors);
			} else{
				responseMessage = userImpl.addApplicantDetails(applicantBasicDetail);
			}
		} catch (ParseException e) {
	    	errors.add("Invalid date of birth format.");
	    	responseMessage.setErrors(errors);
	    	responseMessage.setStatus(0);
	    	responseMessage.setMessage("Invalid date format.");
		} catch(Exception e) {
			errors.add("Unable to add Info.");
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
			responseMessage.setMessage("Unable to add Info.");
		}
		return responseMessage;
	}
	
	
	/*private void writeToFile(InputStream is, String uploadedFileLocation) {
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
		
	}*/
}
