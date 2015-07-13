package org.school.admin.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintViolation;

import org.glassfish.jersey.message.internal.MsgTraceEvent;
import org.hibernate.Session;
import org.omg.CORBA.REBIND;
import org.school.admin.data.ApplicantInfo;
import org.school.admin.data.NameList;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.ApplicantBasicDetail;
import org.school.admin.model.PostRequirement;
import org.school.admin.model.School;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.util.HibernateUtil;

public class UserImpl {
	
	public ResponseMessage postRequirement(PostRequirement postRequirement){
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		try {
			boolean result = true;
			if(postRequirement.getEmail() == null){
				if(postRequirement.getMobile() == null){
					errors.add("Please enter your mobile or email");
				}
				responseMessage.setStatus(0);
	        	responseMessage.setMessage("Failed to post requirement.");
		    	responseMessage.setErrors(errors);
			} else if (postRequirement.getMobile() == null) {
				if(postRequirement.getEmail() == null){
					errors.add("Please enter your mobile or email");
					responseMessage.setErrors(errors);
					responseMessage.setMessage("Failed to post requirement.");
					responseMessage.setStatus(0);
				} else {
					if(postRequirement.getEmail() != null) {
					    try {
					       InternetAddress emailAddr = new InternetAddress(postRequirement.getEmail());
					       emailAddr.validate();
					    } catch (AddressException ex) {
					       result = false;
					    }
					}
					if(!result) {
				    	errors.add("Please enter valid email.");
				    	responseMessage.setMessage("Failed to post requirement.");
						responseMessage.setErrors(errors);
						responseMessage.setStatus(0);
				    }
				}
		    } else {
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.save(postRequirement);
				session.getTransaction().commit();
				session.flush();
				responseMessage.setId(postRequirement.getId());
				responseMessage.setStatus(1);
				responseMessage.setMessage("Requirement post successfully.");
		    }
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> s = e.getConstraintViolations();
	    	Iterator<ConstraintViolation<?>> i = s.iterator();
	    	while (i.hasNext()) {
	    		ConstraintViolation<?> cv = i.next();
	    		errors.add(cv.getMessage());
	    	}
	    	responseMessage.setStatus(0);
        	responseMessage.setMessage("Failed to post requirement.");
	    	responseMessage.setErrors(errors);
		} catch(Exception e) {
			errors.add("Fields not set properly");
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
		}
		return responseMessage;
	}
	
	public ResponseMessage addApplicantDetails(ApplicantInfo applicantInfo){
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		String pattern = "yyyy-MM-dd";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    try {
	    	if(applicantInfo.getSchoolId() <= 0){
	    		errors.add("School Id required.");
	    	}
	    	if(applicantInfo.getUserId() <= 0){
	    		errors.add("User Id required.");
	    	}
	    	if(applicantInfo.getFirstName().trim().length() <= 0){
	    		errors.add("First Name required.");
	    	}
	    	if(applicantInfo.getDob().trim().length() <= 0){
	    		errors.add("Date of Birth required.");
	    	} else {
	    		if (!applicantInfo.getDob().matches("\\d{4}-\\d{2}-\\d{2}")) {
	    			errors.add("Invalid date format.");
	    		}
	    	}
	    	if(applicantInfo.getGender().trim().length() <= 0){
	    		errors.add("Gender required.");
	    	}
	    	if(applicantInfo.getPlaceOfBirth().trim().length() <= 0){
	    		errors.add("Place of birth required.");
	    	}
	    	if(applicantInfo.getBloodGroup().trim().length() <= 0){
	    		errors.add("Blood group required.");
	    	}
	    	if(errors.size() <= 0){
		    	Date date = format.parse(applicantInfo.getDob());
		      	ApplicantBasicDetail applicantBasicDetail = new ApplicantBasicDetail();
				School school = new School();
				UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
				school.setId(applicantInfo.getSchoolId());
				userRegistrationInfo.setId(applicantInfo.getUserId());
				applicantBasicDetail.setSchool(school);
				applicantBasicDetail.setUserRegistrationInfo(userRegistrationInfo);
				applicantBasicDetail.setFirstName(applicantInfo.getFirstName());
				applicantBasicDetail.setMiddleName(applicantInfo.getMiddleName());
				applicantBasicDetail.setLastName(applicantInfo.getLastName());
				applicantBasicDetail.setGender(applicantInfo.getGender());
				applicantBasicDetail.setDob(date);
				applicantBasicDetail.setBloodGroup(applicantInfo.getBloodGroup());
				applicantBasicDetail.setPlaceOfBirth(applicantInfo.getPlaceOfBirth());
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.save(applicantBasicDetail);
				session.getTransaction().commit();
				int id = applicantBasicDetail.getId();
				session.close();
				responseMessage.setStatus(1);
				responseMessage.setId(id);
				responseMessage.setMessage("Applicant Info Added Successfully.");
	    	} else {
	    		responseMessage.setStatus(1);
	    		responseMessage.setErrors(errors);
				responseMessage.setMessage("Unable to add info.");
	    	}
	    } catch (ParseException e) {
	    	errors.add("Invalid date format.");
	    	responseMessage.setErrors(errors);
	    	responseMessage.setStatus(0);
	    	responseMessage.setMessage("Invalid date format.");
	    } catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> s = e.getConstraintViolations();
	    	Iterator<ConstraintViolation<?>> i = s.iterator();
	    	while (i.hasNext()) {
	    		ConstraintViolation<?> cv = i.next();
	    		errors.add(cv.getMessage());
	    	}
	    	responseMessage.setStatus(0);
        	responseMessage.setMessage("Failed to post application");
	    	responseMessage.setErrors(errors);
	    } catch (Exception e) {
	    	errors.add("Invalid post fields.");
	    	responseMessage.setErrors(errors);
	    	responseMessage.setStatus(0);
        	responseMessage.setMessage("Failed to post application");
		}
		return responseMessage;
	}
	
	public ResponseMessage addApplicantAddress(
		int applicantId,
		String nationality,
		String religion,
		int catId,
		String flatNo,
		String buildingName,
		String locality,
		String city,
		String pincode
	){
		ResponseMessage responseMessage = new ResponseMessage();
		
		return responseMessage;
	}

}
