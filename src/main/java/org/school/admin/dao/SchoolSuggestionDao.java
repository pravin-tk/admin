package org.school.admin.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSuggestion;
import org.school.admin.util.HibernateUtil;

public class SchoolSuggestionDao {
	
	public ResponseMessage addSchoolSuggestion(SchoolSuggestion schoolSuggestion) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
        	schoolSuggestion.setStatus(0);
        	session.save("SchoolSuggestion",schoolSuggestion );
        	tx.commit();
        	session.flush();
        	responseMessage.setStatus(1);
        	responseMessage.setMessage("Thank you for your suggestion.");
	    } catch(javax.validation.ConstraintViolationException e) {
	    	ArrayList<String> errors = new ArrayList<String>();
	    	Set<ConstraintViolation<?>> s = e.getConstraintViolations();
	    	Iterator<ConstraintViolation<?>> i = s.iterator();
	    	while (i.hasNext()) {
	    		ConstraintViolation<?> cv = i.next();
	    		errors.add(cv.getMessage());
	    	}
	    	responseMessage.setStatus(0);
        	responseMessage.setMessage("Suggestion Failed.");
	    	responseMessage.setErrors(errors);
	    }    
	    session.close();
	    return responseMessage;
	}

}
