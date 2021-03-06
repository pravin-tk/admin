package org.school.admin.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Context;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolSearchResult;
import org.school.admin.data.UserInfo;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.service.ImageUploader;
import org.school.admin.util.HibernateUtil;

public class SchoolSearchUserDao {
	@Context ServletContext context;
	ImageUploader imageUploader = new ImageUploader();
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
	        	file_name = file_name.replaceAll("([^a-zA-Z0-9.]|\\s)+", " ").replaceAll(" ", "_").toLowerCase();
	        	file_name = "avatar/"+userId+"-"+file_name;
	        	String uploadFileLocation = img_path+file_name;
	        	schoolSearchUser.setImage(file_name);
	        	Session newsession = hibernateUtil.openSession();
	        	newsession.beginTransaction();
	        	newsession.update("id", schoolSearchUser);
	        	newsession.getTransaction().commit();
	        	newsession.close();
	        	this.imageUploader.writeToFile( inputStream, uploadFileLocation);
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
	
	@SuppressWarnings("null")
	public ResponseMessage signUpUser( UserRegistrationInfo userRegistrationInfo, InputStream inputStream, String img_path, Integer socialType ) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        ResponseMessage responseMessage = new ResponseMessage();
        String hql = "FROM UserRegistrationInfo ur WHERE ur.email = :email";
        Query query = session.createQuery(hql).setParameter("email", userRegistrationInfo.getEmail());
        UserRegistrationInfo fetchedUser = (UserRegistrationInfo)query.uniqueResult();
        session.close();
        if(fetchedUser != null ) {
        	ArrayList<String> errors = new ArrayList<String>();
        	errors.add("Email Id is already registered.");
        	responseMessage.setStatus(0);
        	responseMessage.setMessage("User registration failed.");
        	responseMessage.setErrors(errors);
        } else {
	        try {
	        	Session session1 = hibernateUtil.openSession();
	        	Transaction tx;
	            tx = session1.beginTransaction();
	            if(socialType == 1 || socialType == 2) {
	            	userRegistrationInfo.setStatus((byte)1);
	            } else {
	            	userRegistrationInfo.setStatus((byte)0);
	            }
	        	
	        	session1.save("UserRegistrationInfo",userRegistrationInfo );
	        	tx.commit();
	        	session1.close();
	        	int userId = userRegistrationInfo.getId();
	        	if(userRegistrationInfo.getImage() != null && userRegistrationInfo.getImage().trim().length() > 0) {
		        	String file_name = userRegistrationInfo.getImage();
		        	file_name = file_name.replaceAll("([^a-zA-Z0-9.]|\\s)+", " ").replaceAll(" ", "_").toLowerCase();
		        	file_name = "avatar/"+userId+"-"+file_name;
		        	String uploadFileLocation = img_path+file_name;
		        	userRegistrationInfo.setImage(file_name);
		        	Session newsession = hibernateUtil.openSession();
		        	newsession.beginTransaction();
		        	newsession.update("id", userRegistrationInfo);
		        	newsession.getTransaction().commit();
		        	newsession.close();
		        	this.imageUploader.writeToFile( inputStream, uploadFileLocation);
	        	}
//	        	userRegistrationInfo.setStatus((byte)0);
	        	responseMessage.setId(userId);
	        	responseMessage.setStatus(1);
	        	responseMessage.setData(userRegistrationInfo);
	        	responseMessage.setMessage("User registered successfully. Password sent to registered email id.");
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
	    return responseMessage;
	}
	
	public ResponseMessage updateUser( UserRegistrationInfo userRegistrationInfo, InputStream inputStream, String img_path ) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        ResponseMessage responseMessage = new ResponseMessage();
        String hql = "FROM UserRegistrationInfo ur WHERE ur.id = :id";
        Query query = session.createQuery(hql).setParameter("id", userRegistrationInfo.getId());
        UserRegistrationInfo fetchedUser = (UserRegistrationInfo)query.uniqueResult();
        session.close();
        if(fetchedUser == null ) {
        	ArrayList<String> errors = new ArrayList<String>();
        	errors.add("User does not exists.");
        	responseMessage.setStatus(0);
        	responseMessage.setMessage("Profile update failed.");
        	responseMessage.setErrors(errors);
        } else {
	        try {
	        	int userId = userRegistrationInfo.getId();
	        	String setImageQuery = "";
	        	if(userRegistrationInfo.getImage() != null && userRegistrationInfo.getImage().trim().length() > 0) {
		        	String file_name = userRegistrationInfo.getImage();
		        	file_name = file_name.replaceAll("([^a-zA-Z0-9.]|\\s)+", " ").replaceAll(" ", "_").toLowerCase();
		        	file_name = "avatar/"+userId+"-"+file_name;
		        	String uploadFileLocation = img_path+file_name;
		        	userRegistrationInfo.setImage(file_name);
		        	System.out.println("User Image:"+uploadFileLocation);
		        	this.imageUploader.writeToFile( inputStream, uploadFileLocation);
		        	setImageQuery = ", image='"+userRegistrationInfo.getImage()+"'";
	        	}
	        	String updateQuery = "UPDATE UserRegistrationInfo set firstName='"+userRegistrationInfo.getFirstName()+"'"
	        						+", lastName='"+userRegistrationInfo.getLastName()+"'"
	        						+", mobile='"+userRegistrationInfo.getMobile()+"'"
	        						+ setImageQuery 
	        						+" WHERE id="+userRegistrationInfo.getId();
	        	Session newsession = hibernateUtil.openSession();
	        	Query newQuery = newsession.createQuery(updateQuery);
	        	newsession.beginTransaction();
	        	newQuery.executeUpdate();
	        	newsession.getTransaction().commit();
	        	newsession.close();
	        	responseMessage.setId(userId);
	        	responseMessage.setStatus(1);
	        	responseMessage.setMessage("Profile updated successfully.");
		    } catch(Exception e) {
		    	ArrayList<String> errors = new ArrayList<String>();
		    	errors.add("Fields not set properly.");
		    	responseMessage.setStatus(0);
	        	responseMessage.setMessage("Failed to update profile.");
		    	responseMessage.setErrors(errors);
		    }
        }
	    return responseMessage;
	}
	
	public UserInfo getProfile(int id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        String hql = "SELECT id as id, firstName as firstName, COALESCE(lastName,'') as lastName,"
        			+" COALESCE(mobile,'') as mobile, email as email, status as status, image as image"
        			+" FROM UserRegistrationInfo ur WHERE ur.id = :id";
        Query query = session.createQuery(hql).setParameter("id", id).setResultTransformer(Transformers.aliasToBean(UserInfo.class));
        UserInfo fetchedUser = (UserInfo)query.uniqueResult();
        session.close();
        return fetchedUser;
	}

	// save uploaded file to new location
	/*private void writeToFile(InputStream uploadedInputStream,
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
 
	}*/
	
	public SchoolSearchUser checkUserCredentials(SchoolSearchUser schoolSearchUser) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
        String hql = "Select ssu.id as id , ssu.firstName as firstName, ssu.lastName as lastName, ssu.status as status,"
        		+ " ssu.mobile as mobile, ssu.image as image FROM SchoolSearchUser ssu WHERE ssu.email = :email AND ssu.password = :password";
        Query query = session.createQuery(hql).setParameter("email", schoolSearchUser.getEmail())
        		.setParameter("password", schoolSearchUser.getPassword())
        		.setResultTransformer(Transformers.aliasToBean(SchoolSearchUser.class));
        	session.close();	
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
        session.close();
        return (SchoolSearchUser)query.uniqueResult();
	}
	
	public UserRegistrationInfo getUserByEmailId(String email) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		List<UserRegistrationInfo> userRegistrationInfos = new ArrayList<UserRegistrationInfo>();
		Session session = hibernateUtil.openSession();
        String hql = "FROM UserRegistrationInfo ssu WHERE ssu.email = '"+email+"'";
        Query query = session.createQuery(hql);
        userRegistrationInfos = query.list();
        session.close();
        if(userRegistrationInfos.size() > 0) {
        	return userRegistrationInfos.get(0);
        } else {
        	return null;
        }
	}

	public String updatePassword(String email, String password) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		String updateQuery = "UPDATE UserRegistrationInfo set"
				+" password='"+password+"'"
				+", status=1"
				+" WHERE email='"+email+"'";
		Session newsession = hibernateUtil.openSession();
		Query newQuery = newsession.createQuery(updateQuery);
		newsession.beginTransaction();
		newQuery.executeUpdate();
		newsession.getTransaction().commit();
		newsession.close();
		
		return "Updated successfully";
	}
	
	public Boolean resetPassword(String email, String password) {
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			String updateQuery = "UPDATE UserRegistrationInfo set"
					+" password='"+password+"'"
					+", status=0"
					+" WHERE email='"+email+"'";
			Session newsession = hibernateUtil.openSession();
			Query newQuery = newsession.createQuery(updateQuery);
			newsession.beginTransaction();
			newQuery.executeUpdate();
			newsession.getTransaction().commit();
			newsession.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public Boolean changePassword(String email, String password) {
		try {
			HibernateUtil hibernateUtil = new HibernateUtil();
			String updateQuery = "UPDATE UserRegistrationInfo set"
					+" password='"+password+"'"
					+" WHERE email='"+email+"'";
			Session newsession = hibernateUtil.openSession();
			Query newQuery = newsession.createQuery(updateQuery);
			newsession.beginTransaction();
			newQuery.executeUpdate();
			newsession.getTransaction().commit();
			newsession.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<SchoolList> fetchShortlistedSchoolsByUserId(Integer userId) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		String hql = "SELECT s.schoolId as schoolId, s.name as name,s.alias as alias, s.latitude as latitude,"
					 + " s.longitude as longitude, s.tagLine as tagLine, s.aboutSchool as aboutSchool,"
					 + " s.homeImage as homeImage,s.logo as logo, s.establishmentType as establishmentType,"
				     + " s.streetName as streetName, s.pincode as pincode, s.localityName as localityName,"
				     + " s.cityName as cityName,s.boardName as boardName,s.mediums as mediums,"
				     + " s.schoolCategory as schoolCategory,s.schoolClassification as schoolClassification,"
				     + " s.rating as rating,s.galeryImages as galeryImages,s.reviews as reviews, "
				     + " ci.totalFee as totalFee,  "
				     + " ci.vacantSeat as seats,"
					 + " ci.standardType.id as standardId "
					 + " FROM SchoolSearch s, ClassInfo ci"
					 + " JOIN ci.school schl JOIN schl.shortListedSchools sls"
					 + " WHERE sls.userRegistrationInfo.id = :user_id AND s.schoolId = ci.school.id "
					 + " GROUP BY s.schoolId";

		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(SchoolList.class));
		query.setParameter("user_id", userId);
		List<SchoolList> resultRaw = query.list();
		session.close();
		return resultRaw;
	}

	public List<SchoolList> fetchAppliedSchoolsByUserId(Integer userId) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		String hql = "SELECT s.schoolId as schoolId, s.name as name,s.alias as alias, s.latitude as latitude,"
					 + " s.longitude as longitude, s.tagLine as tagLine, s.aboutSchool as aboutSchool,"
					 + " s.homeImage as homeImage,s.logo as logo, s.establishmentType as establishmentType,"
				     + " s.streetName as streetName, s.pincode as pincode, s.localityName as localityName,"
				     + " s.cityName as cityName,s.boardName as boardName,s.mediums as mediums,"
				     + " s.schoolCategory as schoolCategory,s.schoolClassification as schoolClassification,"
				     + " s.rating as rating,s.galeryImages as galeryImages,s.reviews as reviews, "
				     + " ci.totalFee as totalFee,  "
				     + " ci.vacantSeat as seats,"
					 + " abd.standardType.id as standardId "
					 + " FROM SchoolSearch s, ClassInfo ci "
					 + " JOIN ci.school schl "
					 + " JOIN schl.appliedSchools aschl "
					 + " JOIN aschl.applicantBasicDetail abd"
					 + " WHERE abd.userRegistrationInfo.id = :user_id AND s.schoolId = ci.school.id AND ci.standardType = abd.standardType "
					 + " GROUP BY s.schoolId, abd.standardType";
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(SchoolList.class));
		query.setParameter("user_id", userId);
		List<SchoolList> resultRaw = query.list();
		session.close();
		return resultRaw;
	}
}
