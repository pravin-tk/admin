package org.school.admin.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.util.HibernateUtil;

public class SchoolSearchUserDao {
	
	public ResponseMessage addSchoolSearchUser( SchoolSearchUser schoolSearchUser) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
        	schoolSearchUser.setStatus((byte)0);
        	session.save("SchoolSearchUser",schoolSearchUser );
        	tx.commit();
        	session.flush();
        	responseMessage.setStatus(1);
        	responseMessage.setMessage("User registered successfully.");
	    } catch(javax.validation.ConstraintViolationException e) {
	    	ArrayList<String> errors = new ArrayList<String>();
	    	Set<ConstraintViolation<?>> s = e.getConstraintViolations();
	    	Iterator<ConstraintViolation<?>> i = s.iterator();
	    	while (i.hasNext()) {
	    		ConstraintViolation<?> cv = i.next();
	    		errors.add(cv.getMessage());
	    	}
	    	responseMessage.setStatus(0);
        	responseMessage.setMessage("User registration failed.");
	    	responseMessage.setErrors(errors);
	    }    
	    session.close();
	    return responseMessage;
	}

}
