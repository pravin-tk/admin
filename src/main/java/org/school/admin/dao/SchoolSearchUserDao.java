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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.util.HibernateUtil;

public class SchoolSearchUserDao {
	@Context ServletContext context;
	
	public ResponseMessage addSchoolSearchUser( SchoolSearchUser schoolSearchUser, InputStream inputStream, String img_path ) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        ResponseMessage responseMessage = new ResponseMessage();
        String hql = "FROM SchoolSearchUser ssu WHERE ssu.email = :email";
        Query query = session.createQuery(hql).setParameter("email", schoolSearchUser.getEmail());
        SchoolSearchUser fetchedUser = (SchoolSearchUser)query.uniqueResult();
        if(fetchedUser != null ) {
        	ArrayList<String> errors = new ArrayList<String>();
        	errors.add("Email Id is already registered.");
        	responseMessage.setStatus(0);
        	responseMessage.setMessage("User registration failed.");
        	responseMessage.setErrors(errors);
        } else {
	        try {
	        	schoolSearchUser.setStatus((byte)0);
	        	session.save("SchoolSearchUser",schoolSearchUser );
	        	tx.commit();
	        	int userId = schoolSearchUser.getId();
	        	session.flush();
	        	String file_name = schoolSearchUser.getImage();
	        	file_name = "avatar/"+userId+"-"+file_name;
	        	String uploadFileLocation = img_path+file_name;
	        	schoolSearchUser.setImage(file_name);
	        	Session newsession = hibernateUtil.openSession();
	        	newsession.beginTransaction();
	        	newsession.update("id", schoolSearchUser);
	        	newsession.getTransaction().commit();
	        	newsession.close();
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
        }
	    session.close();
	    return responseMessage;
	}
	
	public ResponseMessage signUpUser( UserRegistrationInfo userRegistrationInfo, InputStream inputStream, String img_path ) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        ResponseMessage responseMessage = new ResponseMessage();
        String hql = "FROM UserRegistrationInfo ur WHERE ur.email = :email";
        Query query = session.createQuery(hql).setParameter("email", userRegistrationInfo.getEmail());
        UserRegistrationInfo fetchedUser = (UserRegistrationInfo)query.uniqueResult();
        if(fetchedUser != null ) {
        	ArrayList<String> errors = new ArrayList<String>();
        	errors.add("Email Id is already registered.");
        	responseMessage.setStatus(0);
        	responseMessage.setMessage("User registration failed.");
        	responseMessage.setErrors(errors);
        } else {
	        try {
	        	userRegistrationInfo.setStatus((byte)0);
	        	session.save("UserRegistrationInfo",userRegistrationInfo );
	        	tx.commit();
	        	int userId = userRegistrationInfo.getId();
	        	session.flush();
	        	String file_name = userRegistrationInfo.getImage();
	        	file_name = "avatar/"+userId+"-"+file_name;
	        	String uploadFileLocation = img_path+file_name;
	        	userRegistrationInfo.setImage(file_name);
	        	Session newsession = hibernateUtil.openSession();
	        	newsession.beginTransaction();
	        	newsession.update("id", userRegistrationInfo);
	        	newsession.getTransaction().commit();
	        	newsession.close();
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
	
	public SchoolSearchUser checkUserCredentials(SchoolSearchUser schoolSearchUser) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        String hql = "Select ssu.id as id , ssu.firstName as firstName, ssu.lastName as lastName, ssu.status as status,"
        		+ " ssu.mobile as mobile, ssu.image as image FROM SchoolSearchUser ssu WHERE ssu.email = :email AND ssu.password = :password";
        Query query = session.createQuery(hql).setParameter("email", schoolSearchUser.getEmail())
        		.setParameter("password", schoolSearchUser.getPassword())
        		.setResultTransformer(Transformers.aliasToBean(SchoolSearchUser.class));
        		
        return (SchoolSearchUser)query.uniqueResult();
	}
	
	public SchoolSearchUser fetchUserByEmailId(SchoolSearchUser schoolSearchUser) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        String hql = "Select ssu.id as id , ssu.firstName as firstName, ssu.lastName as lastName, ssu.status as status,"
        		+ " ssu.mobile as mobile, ssu.image as image FROM SchoolSearchUser ssu WHERE ssu.email = :email";
        Query query = session.createQuery(hql).setParameter("email", schoolSearchUser.getEmail())
        		.setResultTransformer(Transformers.aliasToBean(SchoolSearchUser.class));
        System.out.println("email : " + schoolSearchUser.getEmail());		
        return (SchoolSearchUser)query.uniqueResult();
	}
	
	public SchoolSearchUser getUserByEmailId(SchoolSearchUser schoolSearchUser) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        String hql = "Select ssu.id as id , ssu.firstName as firstName, ssu.lastName as lastName, ssu.status as status,"
        		+ " ssu.mobile as mobile, ssu.image as image FROM SchoolSearchUser ssu WHERE ssu.email = :email";
        Query query = session.createQuery(hql).setParameter("email", schoolSearchUser.getEmail())
        		.setResultTransformer(Transformers.aliasToBean(SchoolSearchUser.class));
        System.out.println("email : " + schoolSearchUser.getEmail());		
        return (SchoolSearchUser)query.uniqueResult();
	}

}
