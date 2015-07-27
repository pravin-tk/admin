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
import org.school.admin.data.UserInfo;
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
				if(postRequirement.getMobile() == null || postRequirement.getMobile().trim().length() <= 0){
					errors.add("Please enter your mobile or email.");
				} else if(!postRequirement.getMobile().matches("\\d{10}")){
					errors.add("Invalid mobile number.");
				}
			} else {
				try {
			       InternetAddress emailAddr = new InternetAddress(postRequirement.getEmail());
			       emailAddr.validate();
				} catch (AddressException ex) {
				   result = false;
				}
				if(!result) {
			    	errors.add("Please enter valid email.");
			    }
			}
				
			if (postRequirement.getMobile() == null) {
				if(postRequirement.getEmail() == null){
					errors.add("Please enter your mobile or email.");
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
				    }
				}
		    }
			if(errors.size() > 0){
				responseMessage.setErrors(errors);
				responseMessage.setMessage("Failed to post requirement.");
				responseMessage.setStatus(0);
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
	
	public ResponseMessage addApplicantDetails(ApplicantBasicDetail applicantBasicDetail){
		ResponseMessage responseMessage = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
	    try {
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

}
