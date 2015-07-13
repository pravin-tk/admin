package org.school.admin.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.school.admin.model.ActivityCategory;
import org.school.admin.model.InfrastructureCategory;
import org.school.admin.data.InfrastructureDetail;
import org.school.admin.data.NameList;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.model.SafetyCategory;
import org.school.admin.model.SafetyCategoryItem;
import org.school.admin.model.SchoolActivityCatItem;
import org.school.admin.model.SchoolInfrastructureCatItem;
import org.school.admin.model.SchoolSafetyCatItem;
import org.school.admin.data.ClassDetail;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.ActivityCategoryItem;
import org.school.admin.model.AdminUser;
import org.school.admin.model.AdminUserRole;
import org.school.admin.model.ClassAccessories;
import org.school.admin.model.ClassFee;
import org.school.admin.model.ClassInfo;
import org.school.admin.model.ClassSection;
import org.school.admin.model.ClassSubjects;
import org.school.admin.model.FeeType;
import org.school.admin.model.InfrastructureCategoryItem;
import org.school.admin.model.Locality;
import org.school.admin.model.School;
import org.school.admin.model.SchoolHighlight;
import org.school.admin.model.SchoolImageGallery;
import org.school.admin.model.SchoolNameList;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolTimeline;
import org.school.admin.model.SchoolTimelineMilestone;
import org.school.admin.model.TabControl;
import org.school.admin.util.HibernateUtil;

public class SchoolDAOImp {

	/*-------------Pankaj Naik -------------*/
	public List<AdminUserRole> getAdminUserRole()
	{
		String hql = "from AdminUserRole";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<AdminUserRole> result = query.list();
		session.close();
		return result;

	}
	public List<AdminUser> getAdminUser()
	{
		String hql = "from AdminUser";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<AdminUser> result = query.list();
		List<AdminUser> newList = new ArrayList<AdminUser>();
		if(result.size() > 0)
		{
			for(int i=0;i<result.size();i++){
				AdminUser adminUser = new AdminUser();
			AdminUserRole adminUserRole = new AdminUserRole();
			adminUserRole.setId(result.get(i).getAdminUserRole().getId());
			adminUserRole.setRoleName(result.get(i).getAdminUserRole().getRoleName());
			adminUser.setAdminUserRole(adminUserRole);
			adminUser.setId(result.get(i).getId());
			adminUser.setEmail(result.get(i).getEmail());
			adminUser.setName(result.get(i).getName());
			adminUser.setMobile(result.get(i).getMobile());
			newList.add(adminUser);
			}
		}
		session.close();
		return newList;
	}
	public List<AdminUser> getAdminUserById(int id)
	{
		String hql = "from AdminUser";
		if(id > 1){
			hql = hql+" where adminUserRole.id = :id";
		}
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		if(id > 1)
		query.setParameter("id", id);
		List<AdminUser> adminUser = query.list();
		List<AdminUser> newAdminUserList = new ArrayList<AdminUser>();
		if(adminUser.size() > 0)
		{
			for(int i=0; i< adminUser.size();i++){
			AdminUser newAdminUser = new AdminUser();
			AdminUserRole adminUserRole = new AdminUserRole();
			newAdminUser.setId(adminUser.get(i).getId());
			newAdminUser.setName(adminUser.get(i).getName());
			newAdminUser.setMobile(adminUser.get(i).getMobile());
			newAdminUser.setEmail(adminUser.get(i).getEmail());
			adminUserRole.setId(adminUser.get(i).getAdminUserRole().getId());
			adminUserRole.setRoleName(adminUser.get(i).getAdminUserRole().getRoleName());
			newAdminUser.setAdminUserRole(adminUserRole);
			newAdminUserList.add(newAdminUser);
			}
		}
		
		return newAdminUserList;
		
	}
	public ResponseMessage saveAdminUser(AdminUser adminUser)
	{
		ResponseMessage message = new ResponseMessage();
		if (adminUser.getName() == null || adminUser.getName().trim().length() == 0) {
			message.setMessage("Registration Name Required");
			message.setStatus(0);
		}else if(adminUser.getPassword() == null || adminUser.getPassword().trim().length() == 0){
			message.setMessage("Pasword Required");
			message.setStatus(0);
		}else if(adminUser.getMobile() == null || adminUser.getMobile().trim().length() == 0)
		{
			message.setMessage("Mobile Number Required");
			message.setStatus(0);
		}else if(adminUser.getEmail() == null || adminUser.getEmail().trim().length() == 0)
		{
			message.setMessage("Email Id Required");
			message.setStatus(0);
		}else
		{
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			session.save(adminUser);
			session.getTransaction().commit();
			session.close();
			message.setMessage("Registered successfuly..");
			message.setStatus(1);
		}
		
		return message;
	}
	
	public ResponseMessage saveSchoolReview(SchoolReview schoolReview)
	{
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		  Transaction tx;
	        tx = session.beginTransaction();
		ResponseMessage responseMessage = new ResponseMessage();
		try
		{
			
		      
			session.save(schoolReview);
			tx.commit();
        	session.flush();
			responseMessage.setStatus(1);
			responseMessage.setMessage("Saved successfuly");
			
		}
		catch(javax.validation.ConstraintViolationException e) {
	    	ArrayList<String> errors = new ArrayList<String>();
	    	Set<ConstraintViolation<?>> s = e.getConstraintViolations();
	    	Iterator<ConstraintViolation<?>> i = s.iterator();
	    	while (i.hasNext()) {
	    		ConstraintViolation<?> cv = i.next();
	    		errors.add(cv.getMessage());
	    	}
	    	responseMessage.setStatus(0);
        	responseMessage.setMessage("School Review failed to save.");
	    	responseMessage.setErrors(errors);
	    }    
		return responseMessage;
	}
/*------------------------------------------------------*/
	
	
	public ResponseMessage save(School school)
	{
		int school_id = 0;
		ResponseMessage message = new ResponseMessage();
		if (school.getName() == null || school.getName().trim().length() == 0) {
			message.setMessage("School Name Required");
			message.setStatus(0);
		} else if(school.getLocality().getId() == 0 || school.getLocality().getId() <= 0) {
			message.setMessage("Locality Name Required");
			message.setStatus(0);
		} else if(school.getStreetName() == null || school.getStreetName().trim().length() == 0) {
			message.setMessage("Street Name Required");
			message.setStatus(0);
		}else if(school.getLatitude() == null || school.getLatitude().trim().length() == 0) {
			message.setMessage("Latitude Required");
			message.setStatus(0);
		}else if(school.getLongitude() == null || school.getLongitude().trim().length() == 0) {
			message.setMessage("Longitude Required");
			message.setStatus(0);
		} else {
			try{
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.save(school);
				session.getTransaction().commit();
				school_id = school.getId();
				session.close();
				saveTab(school);
				message.setMessage("Added Successfully");
				message.setStatus(school_id);
				message.setData(school);
			} catch(Exception e) {
				message.setMessage("Failed to add school");
				message.setStatus(0);
			}
		}
		return message;
	}
	
	public List<School> getSchoolList(int school_id, int city_id, int locality_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		Query query = null;
		
		if (school_id > 0) {
			hql = "from School where id = :school_id";
			query = session.createQuery(hql);
			query.setParameter("school_id",school_id);
		}else if(locality_id > 0) {
			hql = "from School where locality.id = :locality_id";
			query = session.createQuery(hql);
			query.setParameter("locality_id",locality_id);
		} else if (city_id > 0) {
			hql = "from School where locality.city.id = :city_id";
			query = session.createQuery(hql);
			query.setParameter("city_id",city_id);
		} 
		List<School> result = query.list();
		List<School> schools = new ArrayList<School>();
		for(int i=0; i<result.size(); i++){
			School school = new School();
			Locality locality = new Locality();
			locality.setId(result.get(i).getLocality().getId());
			locality.setName(result.get(i).getLocality().getName());
			school.setId(result.get(i).getId());
			school.setName(result.get(i).getName());
			school.setAboutSchool(result.get(i).getAboutSchool());
			school.setEstablishmentType(result.get(i).isEstablishmentType());
			school.setAlias(result.get(i).getAlias());
			school.setCreatedBy(result.get(i).getCreatedBy());
			school.setLandmark(result.get(i).getLandmark());
			school.setLatitude(result.get(i).getLatitude());
			school.setLongitude(result.get(i).getLongitude());
			school.setLiveDate(result.get(i).getLiveDate());
			school.setLocality(locality);
			school.setPincode(result.get(i).getPincode());
			school.setPlotNo(result.get(i).getPlotNo());
			school.setStatus(result.get(i).getStatus());
			school.setStreetName(result.get(i).getStreetName());
			school.setTagLine(result.get(i).getTagLine());
			school.setPromote(result.get(i).getPromote());
			schools.add(school);
		}
		return schools;
	}
	/**
	 * 
	 * @param school
	 */
	
	private void saveTab(School school)
	{
		Byte basic =1;
		Byte defaultVal = 0;
		TabControl tabControl = new TabControl();
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		tabControl.setSchool(school);
		tabControl.setBasic(basic);
		tabControl.setAchievements(defaultVal);
		tabControl.setCampusDetail(defaultVal);
		tabControl.setClassDetail(defaultVal);
		tabControl.setContact(defaultVal);
		tabControl.setGallery(defaultVal);
		tabControl.setHighlights(defaultVal);
		tabControl.setInfrastructure(defaultVal);
		tabControl.setOldStudentProfile(defaultVal);
		tabControl.setSalesDetails(defaultVal);
		tabControl.setSchoolDetail(defaultVal);
		tabControl.setTimeLine(defaultVal);
		session.save(tabControl);
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateTabs(int school_id,String tabName)
	{
		Byte one = 1;
		String hql = "update TabControl set "+tabName+" = :tabName where school.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("tabName", one);
		query.setParameter("id", school_id);
		query.executeUpdate();
//		School school = new School();
//		school.setId(school_id);
//		TabControl tabControl = new TabControl();
//		switch(tabName)
//		{
//			case "schoolDetail":
//								Byte schoolDetail = 1;
//								tabControl.setSchoolDetail(schoolDetail);
//								break;
//			case "campusDetail":
//								Byte campusDetail = 1;
//								tabControl.setCampusDetail(campusDetail);
//								break;
//			case "classDetail":
//								Byte classDetail = 1;
//								tabControl.setClassDetail(classDetail);
//								break;
//			case "contact":
//								Byte contact = 1;
//								tabControl.setContact(contact);
//								break;
//			case "infrastructure":
//								Byte infrastructure = 1;
//								tabControl.setInfrastructure(infrastructure);
//								break;
//			case "achievements":
//								Byte achievements = 1;
//								tabControl.setAchievements(achievements);
//								break;
//			case "salesDetails":
//								Byte salesDetails = 1;
//								tabControl.setSalesDetails(salesDetails);
//								break;
//			case "oldStudentProfile":
//								Byte oldStudentProfile = 1;
//								tabControl.setOldStudentProfile(oldStudentProfile);
//								break;
//			case "timeLine":
//								Byte timeLine = 1;
//								tabControl.setTimeLine(timeLine);
//								break;
//			case "highlights":
//								Byte highlights = 1;
//								tabControl.setHighlights(highlights);
//								break;
//			case "gallery":
//								Byte gallery = 1;
//								tabControl.setGallery(gallery);
//								break;
//				
//		}
		session.getTransaction().commit();
		session.close();
	}
	
	public double getPer(int school_id)
	{
		double per= getTabs(school_id);
		return Math.round((per*100)/11);

	}
	public double getTabs(int school_id)
	{
		String hql = "from TabControl where school.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", school_id);
		List<TabControl> tabControls = query.list();
		double sum =0.0;
		//List<TabControl> tabControls2 = new ArrayList<TabControl>();
		for(int i=0;i<tabControls.size();i++)
		{
//			TabControl tabControl = new TabControl();
//			
//			tabControl.setId(tabControls.get(i).getId());
//			School school = new School();
//			school.setId(tabControls.get(i).getSchool().getId());
//			tabControl.setSchool(school);
//			tabControl.setAchievements(tabControls.get(i).getAchievements());
//			tabControl.setBasic(tabControls.get(i).getBasic());
//			tabControl.setCampusDetail(tabControls.get(i).getCampusDetail());
//			tabControl.setClassDetail(tabControls.get(i).getClassDetail());
//			tabControl.setContact(tabControls.get(i).getContact());
//			tabControl.setGallery(tabControls.get(i).getGallery());
//			tabControl.setHighlights(tabControls.get(i).getHighlights());
//			tabControl.setInfrastructure(tabControls.get(i).getInfrastructure());
//			tabControl.setOldStudentProfile(tabControls.get(i).getOldStudentProfile());
//			tabControl.setSalesDetails(tabControls.get(i).getSalesDetails());
//			tabControl.setSchoolDetail(tabControls.get(i).getSchoolDetail());
//			tabControl.setTimeLine(tabControls.get(i).getTimeLine());
//			
//			tabControls2.add(tabControl);
//			
			sum = tabControls.get(i).getAchievements()
					+tabControls.get(i).getBasic()
					+tabControls.get(i).getCampusDetail()
					+tabControls.get(i).getClassDetail()
					+tabControls.get(i).getContact()
					+tabControls.get(i).getGallery()
					+tabControls.get(i).getHighlights()
					+tabControls.get(i).getInfrastructure()
					+tabControls.get(i).getOldStudentProfile()
					+tabControls.get(i).getSalesDetails()
					+tabControls.get(i).getSchoolDetail()
					+tabControls.get(i).getTimeLine();
			
		}
		return sum;
	}
	public List<SchoolNameList> getSchoolNameList(int school_id, int city_id, int locality_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		Query query = null;
		
		if (school_id > 0) {
			hql = "select id, name from School where id = :school_id";
			query = session.createQuery(hql);
			query.setParameter("school_id",school_id);
		} else if (city_id > 0) {
			hql = "select id, name from School where locality.city.id = :city_id";
			query = session.createQuery(hql);
			query.setParameter("city_id",city_id);
		} else if(locality_id > 0) {
			hql = "select id, name from School where locality.id = :locality_id";
			query = session.createQuery(hql);
			query.setParameter("locality_id",locality_id);
		}
		List<School> result = query.list();
		System.out.println("Result of school : "+result.size());
		List<SchoolNameList> namelist = new ArrayList<SchoolNameList>();
		for(Object item : result)
		{
			Object[] element = (Object[]) item;
			SchoolNameList school = new SchoolNameList();
			school.setSchool_id((int)element[0]);
			school.setSchool_name((String)element[1]);
			namelist.add(school);
		}
		return namelist;
	}
	
	
	public List<School> getSchoolDetails(int school_id)
	{
		String hql = "from School where id = :school_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("school_id", school_id);
		
		List<School> schoolList = query.list();
		
		return schoolList;
		
	}
	public ResponseMessage updateSchool(School School)
	{
		ResponseMessage message = new ResponseMessage();
		if (School.getName() == null || School.getName().trim().length() == 0) {
			message.setMessage("School Name Required");
			message.setStatus(0);
		} else if(School.getLocality().getId() == 0 || School.getLocality().getId() <= 0) {
			message.setMessage("Locality Name Required");
			message.setStatus(0);
		} else if(School.getStreetName() == null || School.getStreetName().trim().length() == 0) {
			message.setMessage("Street Name Required");
			message.setStatus(0);
		}else if(School.getLatitude() == null || School.getLatitude().trim().length() == 0) {
			message.setMessage("Latitude Required");
			message.setStatus(0);
		}else if(School.getLongitude() == null || School.getLongitude().trim().length() == 0) {
			message.setMessage("Longitude Required");
			message.setStatus(0);
		} else {
			try{
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				
				School SchoolNew = (School) session.get(School.class, School.getId());
				SchoolNew.setAboutSchool(School.getAboutSchool());
				SchoolNew.setAlias(School.getAlias());
				SchoolNew.setEstablishmentType(School.isEstablishmentType());
				SchoolNew.setLandmark(School.getLandmark());
				SchoolNew.setLastUpdatedBy(School.getLastUpdatedBy());
				SchoolNew.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				SchoolNew.setLatitude(School.getLatitude());
				SchoolNew.setLongitude(School.getLongitude());
				SchoolNew.setLocality(School.getLocality());
				SchoolNew.setPincode(School.getPincode());
				SchoolNew.setPlotNo(School.getPlotNo());
				SchoolNew.setId(School.getId());
				SchoolNew.setStreetName(School.getStreetName());
				SchoolNew.setTagLine(School.getTagLine());
				SchoolNew.setIsFreelisting(School.getIsFreelisting());
				SchoolNew.setTrialStartDate(School.getTrialStartDate());
				SchoolNew.setTrialEndDate(School.getTrialEndDate());
				session.beginTransaction();
				session.update(SchoolNew);
				session.getTransaction().commit();
				session.close();
				message.setMessage("Updated Successfully");
				message.setStatus(School.getId());
				message.setData(SchoolNew);
			} catch(Exception e) {
				message.setMessage("Failed to update school");
				message.setStatus(0);
			}
		}
		return message;
	}
	public List<ClassInfo> getClassInfoBySchStdTaId(int school_id, Short standard_id, Short ta_id, Short stream_id)
	{
		String hql = "from ClassInfo where school.id = :school_id and "
				+ "standardType.id = :standard_id and teachingApproachType.id = :ta_id and streamType.id = :stream_id" ;
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session classsession = hibernateUtil.openSession();
		
		Query query = classsession.createQuery(hql);
		query.setParameter("school_id", school_id);
		query.setParameter("standard_id", standard_id);
		query.setParameter("ta_id", ta_id);
		query.setParameter("stream_id", stream_id);
		
		List<ClassInfo> schoolList = query.list();
		classsession.close();
		return schoolList;
		
	}
	public ResponseMessage saveClassDetail(ClassDetail classDetail)
	{
		int class_info_id = 0;
		ResponseMessage msg = new ResponseMessage();
		ClassInfo classInfo = classDetail.getClassInfo();
		School school = new School();
		school.setId(classDetail.getClassInfo().getSchool().getId());
		if(classDetail.getClassInfo().getStandardType().getId() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Class");
		} else if(classDetail.getClassSubjects().size() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Subjects");
		} else if(classDetail.getClassInfo().getTeachingApproachType().getId() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Teaching Aproach Type");
		} else if(classDetail.getClassInfo().getStreamType().getId() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Stream Type");	
		} else {
			List<ClassInfo> oldClassInfo = getClassInfoBySchStdTaId(
				classDetail.getClassInfo().getSchool().getId(),
				classDetail.getClassInfo().getStandardType().getId(),
				classDetail.getClassInfo().getTeachingApproachType().getId(),
				classDetail.getClassInfo().getStreamType().getId()
			);
			if(oldClassInfo.size() > 0){
				msg.setStatus(0);
				msg.setMessage("Class already added");
				classInfo.setId(oldClassInfo.get(0).getId());
			}else{
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.save(classInfo);
				class_info_id = classInfo.getId();
				session.getTransaction().commit();
				session.close();
			}
			
			Set<ClassAccessories> classAccessories = classDetail.getClassAccessories();
			HibernateUtil hibernateUtil = new HibernateUtil();
		
			if(classAccessories.size()>0)
			{		
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				
				Iterator<ClassAccessories> sIterator = classAccessories.iterator();
				while(sIterator.hasNext())
				{
					ClassAccessories classAccessoryItem = sIterator.next();
					classAccessoryItem.setClassInfo(classInfo);
					session.save(classAccessoryItem);
				}
				session.getTransaction().commit();
				session.close();
				
			}
			
			Set<ClassSubjects> classSubjects = classDetail.getClassSubjects();
			Session session3 = hibernateUtil.openSession();
			session3.beginTransaction();
			if(classSubjects.size()>0)
			{
				for(int i=0;i< classSubjects.size();i++)
				{
					if(!classSubjects.isEmpty())
					{
						Iterator<ClassSubjects> classSubjects3 = classSubjects.iterator();
						while(classSubjects3.hasNext())
						{
							ClassSubjects classSubjects4 = classSubjects3.next();
							classSubjects4.setClassInfo(classInfo);
							classSubjects4.setSchool(school);
							session3.save(classSubjects4);
						}
					}
				}
			}
			session3.getTransaction().commit();
			session3.close();
			
			Set<ClassFee> classFees = classDetail.getClassFee();
			Session session4 = hibernateUtil.openSession();
			session4.beginTransaction();
			if(classFees.size()>0)
			{
				for(int i=0;i< classFees.size();i++)
				{
					if(!classFees.isEmpty())
					{
						Iterator<ClassFee> classFees3 = classFees.iterator();
						while(classFees3.hasNext())
						{
							ClassFee classFees4 = classFees3.next();
							classFees4.setClassInfo(classInfo);
							session4.save(classFees4);
						}
					}
				}
			}
			session4.getTransaction().commit();
			session4.close();
			msg.setStatus(1);
			msg.setMessage("Saved Successfully");
			updateTabs(school.getId(), "classDetail");
		}	
		
		return msg;
	}
	
	public ResponseMessage updateClassDetail(ClassDetail classDetail){
		//int class_info_id = 0;
		ResponseMessage msg = new ResponseMessage();
		ClassInfo classInfo = classDetail.getClassInfo();
		School school = new School();
		school.setId(classDetail.getClassInfo().getSchool().getId());
		if(classDetail.getClassInfo().getStandardType().getId() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Class");
		} else if(classDetail.getClassSubjects().size() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Subjects");
		} else if(classDetail.getClassInfo().getTeachingApproachType().getId() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Teaching Aproach Type");
		} else if(classDetail.getClassInfo().getStreamType().getId() <= 0) {
			msg.setStatus(0);
			msg.setMessage("Select Stream Type");	
		} else {
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				//ClassStream classStream = classDetail.getClassSection();
				Set<ClassAccessories> classAccessories = classDetail.getClassAccessories();
				session.beginTransaction();
				classInfo.setId(classInfo.getId());
				session.update(classInfo);
				
				session.getTransaction().commit();
				session.close();
				String deleteClassAccessories = "DELETE from ClassAccessories where classInfo.id = :class_id";
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				Query smdelete = newsession.createQuery(deleteClassAccessories);
				smdelete.setParameter("class_id", classInfo.getId());
				smdelete.executeUpdate();
				newsession.getTransaction().commit();
				newsession.close();
				if(classDetail.getClassAccessories().size() > 0){
					Session session2 = hibernateUtil.openSession();
					session2.beginTransaction();
					if(classAccessories.size()>0)
					{
						for(int i=0;i< classAccessories.size();i++)
						{
							if(!classAccessories.isEmpty())
							{
								Iterator<ClassAccessories> classAccessories3 = classAccessories.iterator();
								while(classAccessories3.hasNext())
								{
									ClassAccessories classAccessories4 = classAccessories3.next();
									session2.save(classAccessories4);
								}
							}
						}
					}
					session2.getTransaction().commit();
					session2.close();
				}
			   /* Subjects */
				String deleteClassSubjects = "DELETE from ClassSubjects where classInfo.id = :class_id";
				Session newsession1 = hibernateUtil.openSession();
				newsession1.beginTransaction();
				Query smdelete1 = newsession1.createQuery(deleteClassSubjects);
				smdelete1.setParameter("class_id", classInfo.getId());
				smdelete1.executeUpdate();
				newsession1.getTransaction().commit();
				newsession1.close();
				
				Set<ClassSubjects> classSubjects = classDetail.getClassSubjects();
				Session session3 = hibernateUtil.openSession();
				session3.beginTransaction();
				if(classSubjects.size()>0)
				{
					for(int i=0;i< classSubjects.size();i++)
					{
						if(!classSubjects.isEmpty())
						{
							Iterator<ClassSubjects> classSubjects3 = classSubjects.iterator();
							while(classSubjects3.hasNext())
							{
								ClassSubjects classSubjects4 = classSubjects3.next();
								session3.save(classSubjects4);
							}
						}
					}
				}
				session3.getTransaction().commit();
				session3.close();
				msg.setStatus(1);
				msg.setMessage("Updated Successfully.");
				
				/*class fee */
				String deleteClassFees = "DELETE from ClassFee where classInfo.id = :class_id";
				Session newsession4 = hibernateUtil.openSession();
				newsession4.beginTransaction();
				Query smdelete4 = newsession4.createQuery(deleteClassFees);
				smdelete4.setParameter("class_id", classInfo.getId());
				smdelete4.executeUpdate();
				newsession4.getTransaction().commit();
				newsession4.close();
				
				Set<ClassFee> classFee = classDetail.getClassFee();
				Session session4 = hibernateUtil.openSession();
				session4.beginTransaction();
				if(classFee.size()>0)
				{
					for(int i=0;i< classFee.size();i++)
					{
						if(!classFee.isEmpty())
						{
							Iterator<ClassFee> classFee4 = classFee.iterator();
							while(classFee4.hasNext())
							{
								ClassFee classSubjects4 = classFee4.next();
								session4.save(classSubjects4);
							}
						}
					}
				}
				session4.getTransaction().commit();
				session4.close();
				msg.setStatus(1);
				msg.setMessage("Updated Successfully.");
				updateTabs(school.getId(), "classDetail");
		}
		
		return msg;
	}
	public void updatePromote(School school)
	{
		String hql = "update School set promote = :promote where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("promote", school.getPromote());
		query.setParameter("id", school.getId());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	public List<ClassSection> getClassSectionInfo(int school_id)
	{
		String hql = "from ClassSection where classInfo.school.id = :school_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("school_id", school_id);
		
		List<ClassSection> schoolList = query.list();
		session.close();
		return schoolList;
		
	}

	 public List<ClassInfo> getClassInfoBySchId(int school_id)
		{
			String hql = "from ClassInfo where school.id = :school_id  ";
					
			
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session classsession = hibernateUtil.openSession();
			
			Query query = classsession.createQuery(hql);
			query.setParameter("school_id", school_id);
			
			List<ClassInfo> schoolList = query.list();
			classsession.close();
			return schoolList;
			
		}
	 
	 
	 public List<ClassInfo> getClassInfoById(int class_id)
		{
			String hql = "from ClassInfo where id = :id   ";
					
			
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session classsession = hibernateUtil.openSession();
			
			Query query = classsession.createQuery(hql);
			query.setParameter("id", class_id);
			
			List<ClassInfo> schoolList = query.list();
			classsession.close();
			return schoolList;
			
		}
	 
	 public List<ClassFee> getClassFeeByClassId( int class_id)
		{
			String hql = "from ClassFee where  classInfo.id = :class_id";
			
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			
			Query query = session.createQuery(hql);
			query.setParameter("class_id", class_id);
			List<ClassFee> classList = query.list();
			session.close();
			return classList;
			
		}
	 
	 public List<FeeType> getFeeTypes()
		{
			String hql = "from FeeType";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			List<FeeType> result = query.list();
			session.close();
			return result;
		}
	public List<ClassInfo> getClassInfoBySchStdTaId(int school_id, Short standard_id, Short ta_id)
	{
		String hql = "from ClassInfo where school.id = :school_id and standardType.id = :standard_id and teachingApproachType.id = :ta_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session classsession = hibernateUtil.openSession();
		
		Query query = classsession.createQuery(hql);
		query.setParameter("school_id", school_id);
		query.setParameter("standard_id", standard_id);
		query.setParameter("ta_id", ta_id);
		
		List<ClassInfo> schoolList = query.list();
		classsession.close();
		return schoolList;
		
	}
	public List<ClassSection> getClassSectionByIdClassNSectionId(int class_id, int section_type_id, int id)
	{
		String hql = "from ClassSection where classInfo.id = :class_id and sectionType.id = :section_type_id and id != :id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session selectsession = hibernateUtil.openSession();
		
		Query query = selectsession.createQuery(hql);
		query.setParameter("class_id", class_id);
		query.setParameter("section_type_id", section_type_id);
		query.setParameter("id", id);
		
		List<ClassSection> schoolList = query.list();
		selectsession.close();
		return schoolList;
		
	}
	
	public List<ClassSection> getClassSectionByClassNSectionId(int class_id, int section_type_id)
	{
		String hql = "from ClassSection where classInfo.id = :class_id and sectionType.id = :section_type_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session selectsession = hibernateUtil.openSession();
		
		Query query = selectsession.createQuery(hql);
		query.setParameter("class_id", class_id);
		query.setParameter("section_type_id", section_type_id);
		
		List<ClassSection> schoolList = query.list();
		selectsession.close();
		return schoolList;
		
	}
	
	public List<ClassSection> getClassSectionInfoById(int id)
	{
		String hql = "from ClassSection where id = :id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<ClassSection> schoolList = query.list();
		session.close();
		return schoolList;
		
	}
	
	public List<ClassSection> getClassSectionListBySchoolId(int school_id)
	{
		String hql = "SELECT id, sectionType.name, classInfo.standardType.name, classInfo.teachingApproachType.name, totalSeat, vacantSeat from ClassSection where classInfo.school.id = :school_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("school_id", school_id);
		
		List<ClassSection> schoolList = query.list();
		session.close();
		return schoolList;
		
	}
	
	public List<ClassSubjects> getClassSubjectsByClassId(int school_id, int class_id)
	{
		String hql = "from ClassSubjects where school.id = :school_id and classInfo.id = :class_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("school_id", school_id);
		query.setParameter("class_id", class_id);
		List<ClassSubjects> schoolList = query.list();
		session.close();
		return schoolList;
		
	}
	
	public List<ClassAccessories> getClassAccessoriesByClassId(int class_id)
	{
		String hql = "from ClassAccessories where classInfo.id = :class_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("class_id", class_id);
		List<ClassAccessories> schoolList = query.list();
		session.close();
		return schoolList;
		
	}
	
	public String saveInfrastructure(InfrastructureDetail infrastructureDetail)
	{
		Set<SchoolActivityCatItem> schoolActivityCatItems = infrastructureDetail.getSchoolActivityCatItems();
		HibernateUtil hibernateUtil = new HibernateUtil();
		
			String hqlDeleteAcitivity = "Delete from SchoolActivityCatItem where school.id = :school_id";
			Session deleteSession = hibernateUtil.openSession();
			deleteSession.beginTransaction();
			Query query = deleteSession.createQuery(hqlDeleteAcitivity);
			System.out.println("SCHOOLID : "+infrastructureDetail.getSchool().getId());
			query.setParameter("school_id", infrastructureDetail.getSchool().getId());
			query.executeUpdate();
			deleteSession.getTransaction().commit();
			deleteSession.close();
			
		if(schoolActivityCatItems.size()>0)
		{		
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			
			Iterator<SchoolActivityCatItem> sIterator = schoolActivityCatItems.iterator();
			while(sIterator.hasNext())
			{
				SchoolActivityCatItem schoolActivityCatItem = sIterator.next();
				session.save(schoolActivityCatItem);
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		Set<SchoolSafetyCatItem> schoolSafetyCatItems = infrastructureDetail.getSafetyCatItems();
		
		
			
			String hqlDeleteSafety = "Delete from SchoolSafetyCatItem where school.id = :school_id";
			Session deleteSessionSafety = hibernateUtil.openSession();
			deleteSessionSafety.beginTransaction();
			Query querySafety = deleteSessionSafety.createQuery(hqlDeleteSafety);
			querySafety.setParameter("school_id", infrastructureDetail.getSchool().getId());
			querySafety.executeUpdate();
			deleteSessionSafety.getTransaction().commit();
			deleteSessionSafety.close();
		if(schoolSafetyCatItems.size()>0)
		{
			Session sessionsafety = hibernateUtil.openSession();
			sessionsafety.beginTransaction();
			Iterator<SchoolSafetyCatItem> sIterator = schoolSafetyCatItems.iterator();
			while(sIterator.hasNext())
			{
				SchoolSafetyCatItem schoolSafetyCatItem = sIterator.next();
				sessionsafety.save(schoolSafetyCatItem);
			}
			sessionsafety.getTransaction().commit();
			sessionsafety.close();
			
		}
		
		
		Set<SchoolInfrastructureCatItem> schoolInfrastructureCatItems = infrastructureDetail.getSchoolInfrastructureCatItems();
		
			
			String hqlDeleteInfra = "Delete from SchoolInfrastructureCatItem where school.id = :school_id";
			Session deleteSessionInfra = hibernateUtil.openSession();
			deleteSessionInfra.beginTransaction();
			Query queryInfra = deleteSessionInfra.createQuery(hqlDeleteInfra);
			queryInfra.setParameter("school_id", infrastructureDetail.getSchool().getId());
			queryInfra.executeUpdate();
			deleteSessionInfra.getTransaction().commit();
			deleteSessionInfra.close();
			
		if(schoolInfrastructureCatItems.size()>0)
		{	
			Session sessionInfra = hibernateUtil.openSession();
			sessionInfra.beginTransaction();
			Iterator<SchoolInfrastructureCatItem> sIterator = schoolInfrastructureCatItems.iterator();
			while(sIterator.hasNext())
			{
				SchoolInfrastructureCatItem schoolInfrastructureCatItem = sIterator.next();
				sessionInfra.save(schoolInfrastructureCatItem);
			}
			sessionInfra.getTransaction().commit();
			sessionInfra.close();
		}
		
		updateTabs(infrastructureDetail.getSchool().getId(), "infrastructure");
		return "Infrastructure detail saved successfuly";
	}
	public List<ActivityCategoryItem> getActivityCategoryItem()
	{
		String hql = "from ActivityCategoryItem";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<ActivityCategoryItem> result = query.list();
		session.close();
		return result;
	}
	
	public List<ActivityCategoryItem> getActivityCategoryItem(int id)
	{
		String hql = "from ActivityCategoryItem where activityCategory.id = :id and activityCategory.status = 1";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ActivityCategoryItem> result = query.list();
		session.close();
		return result;
	}
	 public List<ActivityCategory> getActivityCategory()
	 {
		 String hql = "from ActivityCategory";
		 HibernateUtil hibernateUtil = new HibernateUtil();
		 Session session = hibernateUtil.openSession();
		 Query query = session.createQuery(hql);
		 List<ActivityCategory> result = query.list();
		 session.close();
		 return result;
	 }
	 
	public List<SafetyCategoryItem> getSafetyCategoryItem(int id)
	{
		String hql = "from SafetyCategoryItem where safetyCategory.id = :id and safetyCategory.status = 1";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SafetyCategoryItem> result = query.list();
		session.close();
		return result;
	}
	public List<SafetyCategory> getSafetyCategory()
	{
		 String hql = "from SafetyCategory";
		 HibernateUtil hibernateUtil = new HibernateUtil();
		 Session session = hibernateUtil.openSession();
		 Query query = session.createQuery(hql);
		 List<SafetyCategory> result = query.list();
		 session.close();
		 return result;
	}
	public List<InfrastructureCategory> getInfrastructureCategory(int id)
	{
		String hql = "from InfrastructureCategory where id = :id and status = 1";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<InfrastructureCategory> result = query.list();
		session.close();
		return result;
	}
	public List<InfrastructureCategoryItem> getInfrastructureCategoryItem()
	{
		 String hql = "from InfrastructureCategoryItem";
		 HibernateUtil hibernateUtil = new HibernateUtil();
		 Session session = hibernateUtil.openSession();
		 Query query = session.createQuery(hql);
		 List<InfrastructureCategoryItem> result = query.list();
		 session.close();
		 return result;
	}
	 
	public List<SchoolActivityCatItem> getSchoolActivityCatItem(int schoolId)
	{
		String hql = "from SchoolActivityCatItem where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", schoolId);
		List<SchoolActivityCatItem> result = query.list();
		session.close();
		return result;
	}
	 
	 public List<SchoolSafetyCatItem> getSchoolSafetyCatItem(int schoolId)
	 {
		 String hql = "from SchoolSafetyCatItem where school.id = :schoolId";
		 HibernateUtil hibernateUtil = new HibernateUtil();
		 Session session = hibernateUtil.openSession();
		 Query query = session.createQuery(hql);
		 query.setParameter("schoolId", schoolId);
		 List<SchoolSafetyCatItem> result = query.list();
		 session.close();
		 return result;
	 }
	 
	 public List<SchoolInfrastructureCatItem> getSchoolInfrastructureCatItem(int schoolId)
	 {
		 	String hql = "from SchoolInfrastructureCatItem where school.id = :schoolId";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("schoolId", schoolId);
			List<SchoolInfrastructureCatItem> result = query.list();
			session.close();
			return result;
	 }
	 
	public ResponseMessage saveSchoolTimeline(SchoolTimeline schoolTimeline)
	{
		int timeline_id = 0;
		ResponseMessage message = new ResponseMessage();
		if (schoolTimeline.getTitle() == null || schoolTimeline.getTitle().trim().length() == 0) {
			message.setMessage("Image title Required");
			message.setStatus(0);
		} else if(schoolTimeline.getClassesUpto() == null || schoolTimeline.getClassesUpto().trim().length() <= 0) {
			message.setMessage("Classes detail Required");
			message.setStatus(0);
		} else if(schoolTimeline.getYear() == null || schoolTimeline.getYear() <= 0) {
			message.setMessage("Year of time line required");
			message.setStatus(0);
		} else {
			try{
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.save(schoolTimeline);
				session.getTransaction().commit();
				timeline_id = schoolTimeline.getId();
				session.close();
				message.setMessage("Added Successfully");
				message.setStatus(timeline_id);
				message.setData(schoolTimeline);
				updateTabs(schoolTimeline.getSchool().getId(), "timeLine");
			} catch(Exception e) {
				message.setMessage("Failed to add school time line");
				message.setStatus(0);
			}
		}
		
		return message;
	}
	
	public Boolean updateSchoolTimelineImage(int id, String image)
	{
		try{
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			String hql = "update SchoolTimeline set image = :image where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("image", image);
			query.setParameter("id", id);
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public ResponseMessage saveSchoolTimelineMilestone(List<SchoolTimelineMilestone> milestones)
	{
		ResponseMessage message = new ResponseMessage();
		try{
			String hqldelete = "DELETE from SchoolTimelineMilestone where schoolTimeline.id = :schoolId";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session1 = hibernateUtil.openSession();
			Query query1 = session1.createQuery(hqldelete);
			query1.setParameter("schoolId", milestones.get(0).getSchoolTimeline().getId());
			query1.executeUpdate();
			session1.close();
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			for(int i = 0; i < milestones.size(); i++){
				session.save(milestones.get(i));
			}
			session.getTransaction().commit();
			session.close();
			message.setMessage("Added Successfully");
			message.setStatus(1);
		} catch(Exception e) {
			message.setMessage("Failed to add school time line milestone");
			message.setStatus(0);
		}
		return message;
	}
	
	public List<SchoolTimelineMilestone> getSchoolTimelineMilestones(int schoolId)
	 {
		 	String hql = "from SchoolTimelineMilestone where schoolTimeline.school.id = :schoolId";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("schoolId", schoolId);
			List<SchoolTimelineMilestone> result = query.list();
			session.close();
			return result;
	 }
	
	public List<SchoolTimelineMilestone> getSchoolTimelineMilestonesByTimelineId(int id)
	 {
		 	String hql = "from SchoolTimelineMilestone where schoolTimeline.id = :id";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			List<SchoolTimelineMilestone> result = query.list();
			session.close();
			List<SchoolTimelineMilestone> schoolTimelineMilestones = new ArrayList<SchoolTimelineMilestone>();
			for(int i=0; i<result.size(); i++){
				SchoolTimelineMilestone schoolTimelineMilestone = new SchoolTimelineMilestone();
				schoolTimelineMilestone.setId(result.get(i).getId());
				schoolTimelineMilestone.setMilestoneDesc(result.get(i).getMilestoneDesc());
				schoolTimelineMilestone.setTitle(result.get(i).getTitle());
				SchoolTimeline schoolTimeline = new SchoolTimeline();
				schoolTimeline.setId(result.get(i).getSchoolTimeline().getId());
				schoolTimeline.setImage(result.get(i).getSchoolTimeline().getImage());
				System.out.println("IMAGEURL : "+result.get(i).getSchoolTimeline().getImage());
				schoolTimeline.setTitle(result.get(i).getSchoolTimeline().getTitle());
				schoolTimeline.setYear(result.get(i).getSchoolTimeline().getYear());
				schoolTimeline.setClassesUpto(result.get(i).getSchoolTimeline().getClassesUpto());
				schoolTimelineMilestone.setSchoolTimeline(schoolTimeline);
				schoolTimelineMilestones.add(schoolTimelineMilestone);
			}
			
			return schoolTimelineMilestones;
	 }
	
	public ResponseMessage updateSchoolTimeline(SchoolTimeline schoolTimeline)
	{
		int timeline_id = 0;
		ResponseMessage message = new ResponseMessage();
		if (schoolTimeline.getTitle() == null || schoolTimeline.getTitle().trim().length() == 0) {
			message.setMessage("Image title Required");
			message.setStatus(0);
		} else if(schoolTimeline.getClassesUpto() == null || schoolTimeline.getClassesUpto().trim().length() <= 0) {
			message.setMessage("Classes detail Required");
			message.setStatus(0);
		} else if(schoolTimeline.getYear() == null || schoolTimeline.getYear() <= 0) {
			message.setMessage("Year of time line required");
			message.setStatus(0);
		} else if(schoolTimeline.getImage() == null || schoolTimeline.getImage().trim().length() <= 0) {
			message.setMessage("Image required");
			message.setStatus(0);
		} else {
			try{
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.update("id",schoolTimeline);
				session.getTransaction().commit();
				timeline_id = schoolTimeline.getId();
				session.close();
				message.setMessage("Updated Successfully");
				message.setStatus(timeline_id);
				message.setData(schoolTimeline);
				updateTabs(schoolTimeline.getSchool().getId(), "timeLine");
			} catch(Exception e) {
				message.setMessage("Failed to update school time line");
				message.setStatus(0);
			}
		}
		return message;
	}
	
	public ResponseMessage saveHighlight(SchoolHighlight schoolHighlight){
		ResponseMessage message = new ResponseMessage();
		if (schoolHighlight.getName() == null || schoolHighlight.getName().trim().length() == 0) {
			message.setMessage("Highlight title Required");
			message.setStatus(0);
		} else {
			try{
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.save(schoolHighlight);
				session.getTransaction().commit();
				session.close();
				message.setMessage("Added successfully.");
				message.setStatus(1);
				updateTabs(schoolHighlight.getSchool().getId(), "highlights");
			} catch(Exception e) {
				message.setMessage("Error adding Highlight.");
				message.setStatus(0);
			}
		}
		return message;
	}
	
	public ResponseMessage updateHighlight(SchoolHighlight schoolHighlight){
		ResponseMessage message = new ResponseMessage();
		if (schoolHighlight.getName() == null || schoolHighlight.getName().trim().length() == 0) {
			message.setMessage("Highlight title Required");
			message.setStatus(0);
		} else {
			try{
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				session.update("id",schoolHighlight);
				session.getTransaction().commit();
				session.close();
				message.setMessage("Updated successfully.");
				message.setStatus(1);
			} catch(Exception e) {
				message.setMessage("Error adding Highlight.");
				message.setStatus(0);
			}
		}
		return message;
	}
	
	public List<SchoolHighlight> getSchoolHighlights(int schoolId){
		String hql = "from SchoolHighlight where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", schoolId);
		List<SchoolHighlight> result = query.list();
		session.close();
		List<SchoolHighlight> highlights = new ArrayList<SchoolHighlight>();
		for(int i = 0; i < result.size(); i++){
			SchoolHighlight schoolHighlight = new SchoolHighlight();
			schoolHighlight.setId(result.get(i).getId());
			schoolHighlight.setName(result.get(i).getName());
			School school = new School();
			school.setId(result.get(i).getSchool().getId());
			schoolHighlight.setSchool(school);
			highlights.add(schoolHighlight);
		}
		return highlights;
	}
	
	public List<NameList> getSchoolHighlightList(int schoolId){
		String hql = "SELECT id as id, name as name FROM SchoolHighlight where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(NameList.class));
		query.setParameter("schoolId", schoolId);
		List<NameList> result = query.list();
		session.close();
		return result;
	}
	
	public List<SchoolHighlight> getSchoolHighlightById(int id){
		String hql = "from SchoolHighlight where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SchoolHighlight> result = query.list();
		session.close();
		List<SchoolHighlight> highlights = new ArrayList<SchoolHighlight>();
		for(int i = 0; i < result.size(); i++){
			SchoolHighlight schoolHighlight = new SchoolHighlight();
			schoolHighlight.setId(result.get(i).getId());
			schoolHighlight.setName(result.get(i).getName());
			School school = new School();
			school.setId(result.get(i).getSchool().getId());
			schoolHighlight.setSchool(school);
			highlights.add(schoolHighlight);
		}
		return highlights;
	}
	public ResponseMessage saveSchoolImageGallery(School school,
			List<SchoolImageGallery> schoolImageGalleryList) {
		ResponseMessage msg = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		
		Session imageGallery = hibernateUtil.openSession();
		imageGallery.beginTransaction();
		for(int i=0;i<schoolImageGalleryList.size();i++)
		{
			SchoolImageGallery schoolImageGallery = schoolImageGalleryList.get(i);
			imageGallery.save(schoolImageGallery);
		}
		imageGallery.getTransaction().commit();
		imageGallery.close();
		
		msg.setStatus(1);
		msg.setMessage("Images saved Successfuly..");
		updateTabs(school.getId(), "gallery");
		return msg;
	}
	
	public ResponseMessage updateSchoolImages(School school){
		ResponseMessage msg = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if(school.getLogo().trim().length() > 0 || school.getHomeImage().trim().length() > 0) {
			String logo_update = "";
			String home_image_update = "";
			if (school.getLogo().trim().length() > 0) {
				if (school.getHomeImage().trim().length() > 0) {
					logo_update = " logo = :logo,";
				} else {
					logo_update = " logo = :logo";
				}
			}
			if (school.getHomeImage().trim().length() > 0) {
				home_image_update = " homeImage = :homeImage";
			}
			String hql = "update School set"+logo_update+home_image_update+" where id = :id";
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("id", school.getId());
			if (school.getLogo().trim().length() > 0) {
				query.setParameter("logo", school.getLogo());
			}
			if (school.getHomeImage().trim().length() > 0) {
				query.setParameter("homeImage", school.getHomeImage());
			}
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			msg.setStatus(1);
			msg.setMessage("School images updated.");
		} else {
			msg.setStatus(0);
			msg.setMessage("Unable to update School Image");
		}
		return msg;
	}
	
	public List<SchoolImageGallery> getSchoolImageGallery(int schoolId){
		String hql = "from SchoolImageGallery where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", schoolId);
		List<SchoolImageGallery> result = query.list();
		session.close();
		List<SchoolImageGallery> highlights = new ArrayList<SchoolImageGallery>();
		for(int i = 0; i < result.size(); i++){
			SchoolImageGallery schoolImageGallery = new SchoolImageGallery();
			schoolImageGallery.setId(result.get(i).getId());
			schoolImageGallery.setTitle(result.get(i).getTitle());
			School school = new School();
			school.setId(result.get(i).getSchool().getId());
			schoolImageGallery.setSchool(school);
			schoolImageGallery.setImage(result.get(i).getImage());
			highlights.add(schoolImageGallery);
		}
		return highlights;
	}
	
	public ResponseMessage deleteGalleryImage(int id){
		ResponseMessage responseMessage = new ResponseMessage();
		String hql = "DELETE from SchoolImageGallery where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		responseMessage.setStatus(1);
		responseMessage.setMessage("Image deleted successfully.");
		return responseMessage;
	}
	
	public ResponseMessage updateImageTitle(int id, String title){
		ResponseMessage responseMessage = new ResponseMessage();
		if (title.trim().length() > 0) {
			String hql = "UPDATE SchoolImageGallery set title = :title where id = :id";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			query.setParameter("title", title);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			responseMessage.setStatus(1);
			responseMessage.setMessage("Image title updated successfully.");
		} else {
			responseMessage.setStatus(1);
			responseMessage.setMessage("Please enter Image title.");
		}
		return responseMessage;
	}
	
	public List<SchoolTimelineData> getSchoolTimelineDetails(int school_id)
	{
		String sql = "from SchoolTimeline st where st.school.id = :school_id ";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(sql).setParameter("school_id", school_id);
		List<SchoolTimeline> schoolTimelines = query.list();
		List<SchoolTimelineData> result = new ArrayList<SchoolTimelineData>();
		for(int i=0; i < schoolTimelines.size(); i++) {
			SchoolTimelineData schoolTimelineData = new SchoolTimelineData();
			schoolTimelineData.setId(schoolTimelines.get(i).getId());
			schoolTimelineData.setImage(schoolTimelines.get(i).getImage());
			schoolTimelineData.setTitle(schoolTimelines.get(i).getTitle());
			schoolTimelineData.setYear(schoolTimelines.get(i).getYear());
			
			List<SchoolTimelineMilestone> schoolTimelineMilestones = new ArrayList<SchoolTimelineMilestone>();
			for ( SchoolTimelineMilestone schoolTimelineMilestone : schoolTimelines.get(i).getSchoolTimelineMilestones()) {
				System.out.println(" Title : " + schoolTimelineMilestone.getTitle());
				SchoolTimelineMilestone newSchoolTimelineMilestone = new SchoolTimelineMilestone();
				newSchoolTimelineMilestone.setId(schoolTimelineMilestone.getId());
				newSchoolTimelineMilestone.setMilestoneDesc((schoolTimelineMilestone.getMilestoneDesc()));
				newSchoolTimelineMilestone.setTitle( (schoolTimelineMilestone.getTitle()));
				schoolTimelineMilestones.add( newSchoolTimelineMilestone );
			}
			
		    schoolTimelineData.setMilestones(schoolTimelineMilestones);
		    result.add(schoolTimelineData);
		}
		return result;
	}
}
