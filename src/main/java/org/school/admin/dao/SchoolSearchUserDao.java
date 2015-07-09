package org.school.admin.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Context;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.util.HibernateUtil;

public class SchoolSearchUserDao {
	@Context ServletContext context;
	
	public ResponseMessage addSchoolSearchUser( SchoolSearchUser schoolSearchUser, InputStream inputStream, String uploadFileLocation ) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
        	schoolSearchUser.setStatus((byte)0);
        	schoolSearchUser.setImage(new File(uploadFileLocation).getName());
        	session.save("SchoolSearchUser",schoolSearchUser );
        	tx.commit();
        	session.flush();
        	writeToFile( inputStream, uploadFileLocation);
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

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {
 
		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
 
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
	}

}
