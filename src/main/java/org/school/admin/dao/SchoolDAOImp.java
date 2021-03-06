package org.school.admin.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.school.admin.data.Rating;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.data.SchoolTimelineMilestoneData;
import org.school.admin.data.ViewContactData;
import org.school.admin.data.ViewSchoolData;
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
import org.school.admin.model.BoardType;
import org.school.admin.model.City;
import org.school.admin.model.ClassAccessories;
import org.school.admin.model.ClassBatchTime;
import org.school.admin.model.ClassFee;
import org.school.admin.model.ClassInfo;
import org.school.admin.model.ClassSection;
import org.school.admin.model.ClassSubjects;
import org.school.admin.model.ContactInfo;
import org.school.admin.model.FeeType;
import org.school.admin.model.InfrastructureCategoryItem;
import org.school.admin.model.Locality;
import org.school.admin.model.School;
import org.school.admin.model.SchoolBoard;
import org.school.admin.model.SchoolHighlight;
import org.school.admin.model.SchoolImageGallery;
import org.school.admin.model.SchoolLog;
import org.school.admin.model.SchoolNameList;
import org.school.admin.model.SchoolPanoramicImage;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolTimeline;
import org.school.admin.model.SchoolTimelineMilestone;
import org.school.admin.model.TabControl;
import org.school.admin.model.ViewContact;
import org.school.admin.model.ViewSchool;
import org.school.admin.util.HibernateUtil;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

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
	public List<SchoolLog> getSchoolLog()
	{
		String hql = "from SchoolLog";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SchoolLog> result = query.list();
		List<SchoolLog> schoolLogList = new ArrayList<SchoolLog>();
		if(result.size()>0){
			for(int i=0;i<result.size();i++){
				School school = new School();
				school.setId(result.get(i).getSchool().getId());
				school.setName(result.get(i).getSchool().getName());
				Locality locality = new Locality();
				locality.setId(result.get(i).getSchool().getLocality().getId());
				locality.setName(result.get(i).getSchool().getLocality().getName());
				City city = new City();
				city.setId(result.get(i).getSchool().getLocality().getCity().getId());
				city.setName(result.get(i).getSchool().getLocality().getCity().getName());
				locality.setCity(city);
				school.setLocality(locality);
				
				AdminUser adminUser = new AdminUser();
				adminUser.setId(result.get(i).getAdminUser().getId());
				adminUser.setName(result.get(i).getAdminUser().getName());
				
				schoolLogList.add(new SchoolLog(adminUser, school, result.get(i).getReason(), result.get(i).getLog(), result.get(i).getLogDate(), result.get(i).getLogTime()));
				
			}
		}
		session.close();
		return schoolLogList;
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
	
	public String getAdminUserNameById(int id)
	{
		String name = "";
		String hql = "from AdminUser where id=:id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<AdminUser> adminUser = query.list();
		if(adminUser.size()>0)
		{
			name = adminUser.get(0).getName();
		}
		session.close();
		return name;
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
		session.close();
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
		
		ResponseMessage responseMessage = new ResponseMessage();
		try
		{
			String HQL = "SELECT sr.id as id from SchoolReview sr where sr.school = :school AND sr.userRegistrationInfo = :user ";
			Session sess = hibernateUtil.openSession();
			Query query = sess.createQuery(HQL).setResultTransformer(Transformers.aliasToBean(SchoolReview.class))
				.setParameter("school", schoolReview.getSchool())
				.setParameter("user", schoolReview.getUserRegistrationInfo());
			SchoolReview fetchedSchoolReview = (SchoolReview)query.uniqueResult();
			sess.close();
			
			if(fetchedSchoolReview != null) {
				schoolReview.setId(fetchedSchoolReview.getId());
			}
			
			Session session = hibernateUtil.openSession();
			Transaction tx;
			tx = session.beginTransaction();
			session.saveOrUpdate(schoolReview);
			tx.commit();
        	session.flush();
        	session.close();
			responseMessage.setStatus(1);
			responseMessage.setMessage("Saved successfuly");
		} catch(javax.validation.ConstraintViolationException e) {
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
	    } catch(Exception e) {
	    	ArrayList<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			responseMessage.setErrors(errors);
			responseMessage.setStatus(0);
        	responseMessage.setMessage("School Review failed to save.");
	    	responseMessage.setErrors(errors);
		}
		return responseMessage;
	}
/*------------------------------------------------------*/
	
	
	public ResponseMessage save(School school,Short boardId)
	{
		int school_id = 0;
		ResponseMessage message = new ResponseMessage();
		if (school.getName() == null || school.getName().trim().length() == 0) {
			message.setMessage("School Name Required");
			message.setStatus(0);
		}
//		} else if(school.getLocality().getId() == 0 || school.getLocality().getId() <= 0) {
//			message.setMessage("Locality Name Required");
//			message.setStatus(0);
//		} else if(school.getStreetName() == null || school.getStreetName().trim().length() == 0) {
//			message.setMessage("Street Name Required");
//			message.setStatus(0);
//		}else if(school.getLatitude() == null || school.getLatitude().trim().length() == 0) {
//			message.setMessage("Latitude Required");
//			message.setStatus(0);
//		}else if(school.getLongitude() == null || school.getLongitude().trim().length() == 0) {
//			message.setMessage("Longitude Required");
//			message.setStatus(0);
//		} 
			else {
				HibernateUtil oldHibernateUtil = new HibernateUtil();
				Session oldSession = oldHibernateUtil.openSession();
				String schoolHql = "from School where name=:name and locality.id = :localityId";
				Query schoolQuery = oldSession.createQuery(schoolHql);
				schoolQuery.setParameter("name", school.getName());
				schoolQuery.setParameter("localityId", school.getLocality().getId());
				List<School> checkSchool = schoolQuery.list();
				oldSession.close();
			try{
				if(checkSchool.size()>0){
					message.setStatus(0);
					message.setMessage("School name "+checkSchool.get(0).getName()+" with same locality and city already exists.");
				}else{
					HibernateUtil hibernateUtil = new HibernateUtil();
					Session session = hibernateUtil.openSession();
					session.beginTransaction();
					session.save(school);
					session.getTransaction().commit();
					school_id = school.getId();
					saveTab(school);
					session.close();
					
					Session boardSchool = hibernateUtil.openSession();
					SchoolBoard schoolBoard = new SchoolBoard();
					BoardType boardType = new BoardType();
					boardType.setId(boardId);
					schoolBoard.setBoardType(boardType);
					School newSchool = new School();
					newSchool.setId(school_id);
					schoolBoard.setSchool(newSchool);
					boardSchool.beginTransaction();
					boardSchool.save(schoolBoard);
					boardSchool.getTransaction().commit();
					boardSchool.close();
					
					message.setMessage("Added Successfully");
					message.setStatus(school_id);
					message.setData(school);
				}
			} catch(Exception e) {
				message.setMessage("Failed to add school");
				message.setStatus(0);
			}
		}
		return message;
	}
	/**
	 * @param no-parameter
	 * @return List<school> with school id and with school name,alias name,locality and city
	 */
	public List<ViewSchoolData> getViewSchoolList()
	{
		String hql = "from ViewSchool";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<ViewSchool> result = query.list();
		List<ViewSchoolData> viewSchoolDatas = new ArrayList<ViewSchoolData>();
		if(result.size()>0)
		{
			for(int i=0;i<result.size();i++)
			{
				ViewSchoolData viewSchoolData = new  ViewSchoolData();
				viewSchoolData.setSchoolId(result.get(i).getSchoolId());
				viewSchoolData.setSchoolFullName(result.get(i).getSchoolFullName());
				viewSchoolDatas.add(viewSchoolData);
			}
		}
		session.close();
		return viewSchoolDatas;
	}
	/**
	 * 
	 * @param cityId
	 * @return
	 */
	public List<ViewSchoolData> getViewSchoolList(int cityId)
	{
		String hql = "from ViewSchool where cityId = :cityId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("cityId", cityId);
		List<ViewSchool> result = query.list();
		List<ViewSchoolData> viewSchoolDatas = new ArrayList<ViewSchoolData>();
		if(result.size()>0)
		{
			for(int i=0;i<result.size();i++)
			{
				ViewSchoolData viewSchoolData = new  ViewSchoolData();
				viewSchoolData.setSchoolId(result.get(i).getSchoolId());
				viewSchoolData.setSchoolFullName(result.get(i).getSchoolFullName());
				viewSchoolDatas.add(viewSchoolData);
			}
		}
		session.close();
		return viewSchoolDatas;
	}
//	public List<ViewContact> getViewContactList()
//	{
//		String hql = "from ViewContact";
//		HibernateUtil hibernateUtil = new HibernateUtil();
//		Session session = hibernateUtil.openSession();
//		Query query = session.createQuery(hql);
//		List<ViewContact> result = query.list();
//		session.close();
//		return result;
//	}
	
	public List<ViewContactData> getViewContactList(int cityId)
	{
		System.out.println("TTTT");
		String hql = "SELECT DISTINCT contactDetail as contactDetail,contactNumber as contactNumber from ViewContact where cityId =:cityId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(ViewContactData.class));
		
		query.setParameter("cityId", cityId);
		List<ViewContactData> result = query.list();
		session.close();
//		List<ViewContactData> viewContactDatas = new ArrayList<ViewContactData>();
//		if(result.size()>0){
//			for(int i=0;i<result.size();i++)
//			{
//				String contactNumber = result.get(i).getContactNumber();
//				String conatctDetail = result.get(i).getContactDetail();
//				ViewContactData viewContactData = new ViewContactData();
//				viewContactData.setContactNumber(contactNumber);
//				viewContactData.setContactDetail(conatctDetail);
//				viewContactDatas.add(viewContactData);
//				System.out.println("AAA : "+result.get(i).getContactDetail());
//			}
//		}
//		else{
//			System.out.println("ZZZZZZZZZZZ..");
//		}
//		return viewContactDatas;
		return result;
	}
	
	public List<School> getSchoolList(String contact_id,int cityId){
		String hql = "select distinct v from ViewContact v where v.contactNumber = :contactNumber and cityId =:cityId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("contactNumber", contact_id);
		query.setParameter("cityId",cityId);
		List<ViewContact> viewContact = query.list();
		List<School> viewSchoolList = new ArrayList<School>();
		if(viewContact.size()>0)
		{
			for(int j =0 ;j<viewContact.size();j++)
			{
				viewSchoolList.addAll(getSchoolDetail(viewContact.get(j).getSchoolId()));
			}
		}
		session.close();
		return viewSchoolList;
	}
	
	
	public List<School> getSchoolDetail(int school_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		int count =0;
		List<School> schools = new ArrayList<School>();
		Query query = null;
		if (school_id > 0) {
			hql = "select distinct s from School s where s.id = :school_id";
			query = session.createQuery(hql);
			query.setParameter("school_id",school_id);
				List<School> result = query.list();
	
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
		}
		session.close();
		return schools;
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
		session.close();
		return schools;
	}
	
	public List<School> getSchoolById(int school_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		Query query = null;
		
		if (school_id > 0) {
			hql = "from School where id = :school_id";
			query = session.createQuery(hql);
			query.setParameter("school_id",school_id);
		}
		List<School> result = query.list();
		List<School> schools = new ArrayList<School>();
		for(int i=0; i<result.size(); i++){
			School school = new School();
			Locality locality = new Locality();
			City city = new City();
			locality.setId(result.get(i).getLocality().getId());
			locality.setName(result.get(i).getLocality().getName());
			
			city.setId(result.get(i).getLocality().getCity().getId());
			city.setName(result.get(i).getLocality().getCity().getName());
			locality.setCity(city);
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
		session.close();
		return schools;
	}
	
	public List<School> getSchoolActiveList(int city_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		Query query = null;
		 if (city_id > 0) {
			hql = "from School where locality.city.id = :city_id and status = 1";
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
			City city = new City();
			city.setId(result.get(i).getLocality().getCity().getId());
			city.setName(result.get(i).getLocality().getCity().getName());
			locality.setCity(city);
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
		session.close();
		return schools;
	}
	
	
	public List<School> getSchoolPendingList(int city_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		Query query = null;
		 if (city_id > 0) {
			hql = "from School where locality.city.id = :city_id";
			query = session.createQuery(hql);
			query.setParameter("city_id",city_id);
		} 
		List<School> result = query.list();
		List<School> schools = new ArrayList<School>();
		for(int i=0; i<result.size(); i++){
			System.out.println("SCHOOLID : "+result.get(i).getId());
			if(getPer(result.get(i).getId()) == 100 && result.get(i).getStatus() == 0){
			School school = new School();
			Locality locality = new Locality();
			locality.setId(result.get(i).getLocality().getId());
			locality.setName(result.get(i).getLocality().getName());
			City city = new City();
			city.setId(result.get(i).getLocality().getCity().getId());
			city.setName(result.get(i).getLocality().getCity().getName());
			locality.setCity(city);
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
		}
		session.close();
		return schools;
	}
	
	public List<School> getSchoolIncompleteList(int city_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		Query query = null;
		 if (city_id > 0) {
			hql = "from School where locality.city.id = :city_id";
			query = session.createQuery(hql);
			query.setParameter("city_id",city_id);
		} 
		List<School> result = query.list();
		List<School> schools = new ArrayList<School>();
		for(int i=0; i<result.size(); i++){
			System.out.println("SCHOOLID : "+result.get(i).getId());
			if((getPer(result.get(i).getId()) >0 && getPer(result.get(i).getId()) < 100) && result.get(i).getStatus() == 0){
			School school = new School();
			Locality locality = new Locality();
			locality.setId(result.get(i).getLocality().getId());
			locality.setName(result.get(i).getLocality().getName());
			City city = new City();
			city.setId(result.get(i).getLocality().getCity().getId());
			city.setName(result.get(i).getLocality().getCity().getName());
			locality.setCity(city);
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
		}
		session.close();
		return schools;
	}
	
	public List<School> getSchoolRejectedList(int city_id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		String hql = "";
		Query query = null;
		 if (city_id > 0) {
			hql = "from School where locality.city.id = :city_id and status = 2";
			query = session.createQuery(hql);
			query.setParameter("city_id",city_id);
		} 
		List<School> result = query.list();
		session.close();
		List<School> schools = new ArrayList<School>();
		for(int i=0; i<result.size(); i++){
			if((result.get(i).getStatus() == 2)){
			School school = new School();
			Locality locality = new Locality();
			locality.setId(result.get(i).getLocality().getId());
			locality.setName(result.get(i).getLocality().getName());
			City city = new City();
			city.setId(result.get(i).getLocality().getCity().getId());
			city.setName(result.get(i).getLocality().getCity().getName());
			locality.setCity(city);
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
		tabControl.setPanoImage(defaultVal);
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
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteTabs(int school_id,String tabName)
	{
		Byte zero = 0;
		String hql = "update TabControl set "+tabName+" = :tabName where school.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("tabName", zero);
		query.setParameter("id", school_id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public double getPer(int school_id)
	{
		double per= getTabs(school_id);
		return Math.round((per*100)/13);

	}
	public double getTabs(int school_id)
	{
		System.out.println("Test:");
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
					+tabControls.get(i).getTimeLine()
					+tabControls.get(i).getPanoImage();
			
		}
		session.close();
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
		session.close();
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
		session.close();
		return schoolList;
		
	}
	public ResponseMessage updateSchool(School school,SchoolBoard schoolBoard)
	{
		ResponseMessage message = new ResponseMessage();
		if (school.getName() == null || school.getName().trim().length() == 0) {
			message.setMessage("School Name Required");
			message.setStatus(0);
//		} else if(School.getLocality().getId() == 0 || School.getLocality().getId() <= 0) {
//			message.setMessage("Locality Name Required");
//			message.setStatus(0);
//		} else if(School.getStreetName() == null || School.getStreetName().trim().length() == 0) {
//			message.setMessage("Street Name Required");
//			message.setStatus(0);
//		}else if(School.getLatitude() == null || School.getLatitude().trim().length() == 0) {
//			message.setMessage("Latitude Required");
//			message.setStatus(0);
//		}else if(School.getLongitude() == null || School.getLongitude().trim().length() == 0) {
//			message.setMessage("Longitude Required");
//			message.setStatus(0);
		} else {
			
			   HibernateUtil oldHibernateUtil = new HibernateUtil();
			   Session oldSession = oldHibernateUtil.openSession();
			   String oldHql = "from School where name = :name and locality.id=:localityId and id!=:id";
			   Query oldQuery = oldSession.createQuery(oldHql);
			   oldQuery.setParameter("name", school.getName());
			   oldQuery.setParameter("localityId", school.getLocality().getId());
			   oldQuery.setParameter("id", school.getId());
			   List<School> checkUpdateSchool = oldQuery.list();
			   oldSession.close();
			   if(checkUpdateSchool.size()>0){
				   message.setStatus(0);
				   message.setMessage("School name "+school.getName()+" with same city and locality already exists");
			   }
			   else{
					try{
						HibernateUtil hibernateUtil = new HibernateUtil();
						Session session = hibernateUtil.openSession();
						
						School schoolNew = (School) session.get(School.class, school.getId());
						schoolNew.setAboutSchool(school.getAboutSchool());
						schoolNew.setName(school.getName());
						schoolNew.setAlias(school.getAlias());
						schoolNew.setEstablishmentType(school.isEstablishmentType());
						schoolNew.setLandmark(school.getLandmark());
						schoolNew.setLastUpdatedBy(school.getLastUpdatedBy());
						schoolNew.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
						schoolNew.setLatitude(school.getLatitude());
						schoolNew.setLongitude(school.getLongitude());
						schoolNew.setLocality(school.getLocality());
						schoolNew.setPincode(school.getPincode());
						schoolNew.setPlotNo(school.getPlotNo());
						schoolNew.setId(school.getId());
						schoolNew.setStreetName(school.getStreetName());
						schoolNew.setTagLine(school.getTagLine());
						schoolNew.setIsFreelisting(school.getIsFreelisting());
						schoolNew.setTrialStartDate(school.getTrialStartDate());
						schoolNew.setTrialEndDate(school.getTrialEndDate());
						session.beginTransaction();
						session.update(schoolNew);
						session.getTransaction().commit();
						session.close();
						
						String hqlSchoolBoard = "from SchoolBoard where school.id = :school_id";
						Session oldSchoolBoard = hibernateUtil.openSession();
							Query query1 = oldSchoolBoard.createQuery(hqlSchoolBoard);
							query1.setInteger("school_id",school.getId());
							List<SchoolBoard> resultSchoolBoard = query1.list();
							oldSchoolBoard.close();
							
							Session newSession = hibernateUtil.openSession();
							newSession.beginTransaction();
							if (resultSchoolBoard.size() > 0) {
									schoolBoard.setId(resultSchoolBoard.get(0).getId());
									newSession.update(schoolBoard);
							}else{
								newSession.save(schoolBoard);
							}
							newSession.getTransaction().commit();
							newSession.close();
						message.setMessage("Updated Successfully");
						message.setStatus(school.getId());
						message.setData(schoolNew);
					} catch(Exception e) {
						e.printStackTrace();
						message.setMessage("Failed to update school");
						message.setStatus(0);
					}
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
		String log = "|New entry in class detail |";
		int class_info_id = 0;
		ResponseMessage msg = new ResponseMessage();
		ClassInfo classInfo = classDetail.getClassInfo();
		String strReason = classDetail.getStrReason();
		AdminUser adminUser = new AdminUser();
		adminUser.setId(classDetail.getClassInfo().getLastUpdatedBy());
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
				try{
					HibernateUtil hibernateUtil = new HibernateUtil();
					Session session = hibernateUtil.openSession();
					session.beginTransaction();
					session.save(classInfo);
					class_info_id = classInfo.getId();
					session.getTransaction().commit();
					session.close();
					
					log += " Standard : "+new StandardTypeDAO().getStandardTypeNameById(classInfo.getStandardType().getId());
					log += "| stream : "+new SettingsImpl().getStreamTypeNameById(classInfo.getStreamType().getId());
					log += "| Teaching approach : "+new SettingsImpl().getTeachingApproachTypeNameById(classInfo.getTeachingApproachType().getId());
					log += "| Total seats : "+classInfo.getTotalSeat();
					log += "| Vacant seats : "+classInfo.getVacantSeat();
					log += "| Admission deadline : "+classInfo.getAdmissionDeadline();
					log += "| Admission from : "+classInfo.getAdmissionFrom();
					log += "| Admission to : "+classInfo.getAdmissionTo();
					log += "| Eligibility Criteria :"+classInfo.getEligibilityCriteria();
					log += "| Specialization : "+classInfo.getSpecialization();
					log += "| Admission Procedure : "+classInfo.getAdmissionProcess();
					log += "| How To Apply : "+classInfo.getHowToApply();
					log += "| Fee Payment Term : "+classInfo.getFeesPaymentTerm();
				}
				catch(org.hibernate.exception.ConstraintViolationException e){
					ResponseMessage response = new ResponseMessage();
					response.setStatus(0);
					response.setMessage("Class with this name alredy exist");
					return response;
				}
				catch(org.hibernate.exception.LockAcquisitionException e){
					ResponseMessage response = new ResponseMessage();
					response.setStatus(0);
					response.setMessage("Save failed Please try again");
					return response;
				}
			}
			
			Set<ClassAccessories> classAccessories = classDetail.getClassAccessories();
			HibernateUtil hibernateUtil = new HibernateUtil();
		
			if(classAccessories.size()>0)
			{		
				log +="| Accessories Provided : ";
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				
				Iterator<ClassAccessories> sIterator = classAccessories.iterator();
				while(sIterator.hasNext())
				{
					ClassAccessories classAccessoryItem = sIterator.next();
					classAccessoryItem.setClassInfo(classInfo);
					session.save(classAccessoryItem);
					log +=", "+new SettingsImpl().getAccessoryNameById(classAccessoryItem.getAccessories().getId());
				}
				session.getTransaction().commit();
				session.close();
				
			}
			
			Set<ClassSubjects> classSubjects = classDetail.getClassSubjects();
			Session session3 = hibernateUtil.openSession();
			session3.beginTransaction();
			if(classSubjects.size()>0)
			{
				log += "| subject taughts : ";
				
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
							log +=", "+new SettingsImpl().getSubjectNameById(classSubjects4.getSubject().getId());
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
				log +="| Fee Type : ";
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
							log += " Fee Description : "+classFees4.getFeeDesc();
							log += " | Amount : "+classFees4.getAmount();
						}
					}
				}
			}
			session4.getTransaction().commit();
			session4.close();
			
			Set<ClassBatchTime> classBatchTimes = classDetail.getClassBatchTime();
			Session session5 = hibernateUtil.openSession();
			session5.beginTransaction();
			if(classBatchTimes.size()>0)
			{
				log +="| Batch Time : ";
				for(int i=0;i<classBatchTimes.size();i++)
				{
					if(!classBatchTimes.isEmpty())
					{
						Iterator<ClassBatchTime> classBatchTimeIterator = classBatchTimes.iterator();
						while(classBatchTimeIterator.hasNext())
						{
							ClassBatchTime classBatchTime = classBatchTimeIterator.next();
							classBatchTime.setClassInfo(classInfo);
							session5.save(classBatchTime);
							log += "  | from : "+classBatchTime.getBatchTimeFrom();
							log += " | to : "+classBatchTime.getBatchTimeTo();
						}
					}
				}
			}
			session5.getTransaction().commit();
			session5.close();
			
			
			msg.setStatus(1);
			msg.setMessage("Saved Successfully");
			updateTabs(school.getId(), "classDetail");
			System.out.println("LogEntry : "+log);
			saveSchoolLog(new SchoolLog(adminUser, school, "", log, new Date(), new Date()));
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
			    
			    Set<ClassAccessories> classAccessories = classDetail.getClassAccessories();
			    HibernateUtil hibernateUtil = new HibernateUtil();
			    try{
				
				Session session = hibernateUtil.openSession();
				//ClassStream classStream = classDetail.getClassSection();
			
				session.beginTransaction();
				classInfo.setId(classInfo.getId());
				session.update(classInfo);
				
				session.getTransaction().commit();
				session.close();
			    }
			    catch(org.hibernate.exception.ConstraintViolationException e){
			    	ResponseMessage response = new ResponseMessage();
			    	response.setStatus(0);
			    	response.setMessage("Trying to update duplicate entry with same combination of standard, stream, subjects and teaching approach");
			    	return response;
			    }
			    catch(org.hibernate.exception.LockTimeoutException e){
			    	ResponseMessage response = new ResponseMessage();
			    	response.setStatus(0);
			    	response.setMessage("Update fail due to time out");
			    	return response;
			    }
			    
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
				/*Batch time*/
				String deleteClassBatchTime = "DELETE from ClassBatchTime where classInfo.id = :class_id";
				Session newsessionBatchTime = hibernateUtil.openSession();
				newsessionBatchTime.beginTransaction();
				Query deleteBatchTime = newsessionBatchTime.createQuery(deleteClassBatchTime);
				deleteBatchTime.setParameter("class_id", classInfo.getId());
				deleteBatchTime.executeUpdate();
				newsessionBatchTime.getTransaction().commit();
				newsessionBatchTime.close();
				
				Set<ClassBatchTime> classBatchTimeSet = classDetail.getClassBatchTime();
				
					Session session2 = hibernateUtil.openSession();
					session2.beginTransaction();
					if(classBatchTimeSet.size()>0)
					{
						for(int i=0;i< classBatchTimeSet.size();i++)
						{
							if(!classBatchTimeSet.isEmpty())
							{
								Iterator<ClassBatchTime> classAccessories3 = classBatchTimeSet.iterator();
								while(classAccessories3.hasNext())
								{
									ClassBatchTime classAccessories4 = classAccessories3.next();
									session2.save(classAccessories4);
								}
							}
						}
					}
					session2.getTransaction().commit();
					session2.close();
				
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
			for(int i=0; i<schoolList.size(); i++){
				if(schoolList.get(i).getAdmissionDeadline() == null){
					schoolList.get(i).setAdmissionDeadline(null);
				}
				if(schoolList.get(i).getAdmissionFrom() == null){
					schoolList.get(i).setAdmissionFrom(null);
				}
				if(schoolList.get(i).getAdmissionTo() == null){
					schoolList.get(i).setAdmissionTo(null);
				}
			}
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
			for(int i=0; i<schoolList.size(); i++){
				if(schoolList.get(i).getAdmissionDeadline() == null){
					schoolList.get(i).setAdmissionDeadline(null);
				}
				if(schoolList.get(i).getAdmissionFrom() == null){
					schoolList.get(i).setAdmissionFrom(null);
				}
				if(schoolList.get(i).getAdmissionTo() == null){
					schoolList.get(i).setAdmissionTo(null);
				}
			}
		
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
//		if (schoolTimeline.getTitle() == null || schoolTimeline.getTitle().trim().length() == 0) {
//			message.setMessage("Image title Required");
//			message.setStatus(0);
//		} else if(schoolTimeline.getClassesUpto() == null || schoolTimeline.getClassesUpto().trim().length() <= 0) {
//			message.setMessage("Classes detail Required");
//			message.setStatus(0);
//		} else if(schoolTimeline.getYear() == null || schoolTimeline.getYear() <= 0) {
//			message.setMessage("Year of time line required");
//			message.setStatus(0);
		//}else {
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
		//}
		
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
	
	public List<SchoolTimelineMilestoneData> getSchoolTimelineMilestones(int schoolId)
	 {
		 	String hql = "from SchoolTimelineMilestone where schoolTimeline.school.id = :schoolId";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("schoolId", schoolId);
			List<SchoolTimelineMilestone> result = query.list();
			List<SchoolTimelineMilestoneData> schoolTimelineMilestones = new ArrayList<SchoolTimelineMilestoneData>();
			 
			if(result.size() > 0)
			{
			   Short defaultValue =0;
				for(int i = 0; i < result.size();i++)
				{
					SchoolTimelineMilestoneData schoolTimelineMilestoneData = new SchoolTimelineMilestoneData();
					schoolTimelineMilestoneData.setTitle(result.get(i).getTitle());
					schoolTimelineMilestoneData.setId(result.get(i).getId());
					SchoolTimeline schoolTimeline = new SchoolTimeline();
					schoolTimeline.setId(result.get(i).getSchoolTimeline().getId());
					schoolTimeline.setTitle(result.get(i).getSchoolTimeline().getTitle());
					if(result.get(i).getSchoolTimeline().getYear() != null)
						schoolTimeline.setYear(result.get(i).getSchoolTimeline().getYear());
					else
						schoolTimeline.setYear(defaultValue);
					schoolTimelineMilestoneData.setSchoolTimeline(schoolTimeline);
					schoolTimelineMilestones.add(schoolTimelineMilestoneData);
				}
				
			}
			session.close();
			return schoolTimelineMilestones;
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
				schoolTimelineMilestone.setSchoolTimeline(schoolTimeline);
				schoolTimelineMilestones.add(schoolTimelineMilestone);
			}
			
			return schoolTimelineMilestones;
	 }
	
	public ResponseMessage updateSchoolTimeline(SchoolTimeline schoolTimeline)
	{
		int timeline_id = 0;
		ResponseMessage message = new ResponseMessage();
//		if (schoolTimeline.getTitle() == null || schoolTimeline.getTitle().trim().length() == 0) {
//			message.setMessage("Image title Required");
//			message.setStatus(0);
//		} else if(schoolTimeline.getYear() == null || schoolTimeline.getYear() <= 0) {
//			message.setMessage("Year of time line required");
//			message.setStatus(0);
//		} else if(schoolTimeline.getImage() == null || schoolTimeline.getImage().trim().length() <= 0) {
//			message.setMessage("Image required");
//			message.setStatus(0);
//		} else {
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
	//	}
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
			schoolHighlight.setDescription(result.get(i).getDescription());
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
			schoolHighlight.setDescription(result.get(i).getDescription());
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
	
	
	public ResponseMessage saveSchoolPanoImageGallery(School school,
			List<SchoolPanoramicImage> schoolImageGalleryList) {
		ResponseMessage msg = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		System.out.println("SchoolIDIMP : "+school.getId());
		Session imageGallery = hibernateUtil.openSession();
		imageGallery.beginTransaction();
		for(int i=0;i<schoolImageGalleryList.size();i++)
		{
			SchoolPanoramicImage schoolImageGallery = schoolImageGalleryList.get(i);
			imageGallery.save(schoolImageGallery);
		}
		imageGallery.getTransaction().commit();
		imageGallery.close();
		
		msg.setStatus(1);
		msg.setMessage("Images saved Successfuly..");
		updateTabs(school.getId(), "panoImage");
		return msg;
	}
	
	public List<SchoolPanoramicImage> getSchoolPanoImageGallery(int schoolId){
		String hql = "from SchoolPanoramicImage where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", schoolId);
		List<SchoolPanoramicImage> result = query.list();
		session.close();
		List<SchoolPanoramicImage> highlights = new ArrayList<SchoolPanoramicImage>();
		for(int i = 0; i < result.size(); i++){
			SchoolPanoramicImage schoolImageGallery = new SchoolPanoramicImage();
			schoolImageGallery.setId(result.get(i).getId());
			schoolImageGallery.setTitle(result.get(i).getTitle());
			School school = new School();
			school.setId(result.get(i).getSchool().getId());
			schoolImageGallery.setSchool(school);
			schoolImageGallery.setPanoImage(result.get(i).getPanoImage());
			highlights.add(schoolImageGallery);
		}
		return highlights;
	}	
	
	public ResponseMessage deletePanoGalleryImage(int id){
		ResponseMessage responseMessage = new ResponseMessage();
		String hql = "DELETE from SchoolPanoramicImage where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		responseMessage.setStatus(1);
		responseMessage.setMessage("Pano Image deleted successfully.");
		return responseMessage;
	}

	public ResponseMessage updatePanoImageTitle(int id, String title){
		ResponseMessage responseMessage = new ResponseMessage();
		if (title.trim().length() > 0) {
			String hql = "UPDATE SchoolPanoramicImage set title = :title where id = :id";
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
		session.close();
		List<SchoolTimelineData> result = new ArrayList<SchoolTimelineData>();
		for(int i=0; i < schoolTimelines.size(); i++) {
			String milestoneTitle = "";
			SchoolTimelineData schoolTimelineData = new SchoolTimelineData();
			schoolTimelineData.setId(schoolTimelines.get(i).getId());
			schoolTimelineData.setImage(schoolTimelines.get(i).getImage());
			schoolTimelineData.setYear(schoolTimelines.get(i).getYear());
			
			List<SchoolTimelineMilestone> schoolTimelineMilestones = new ArrayList<SchoolTimelineMilestone>();
			for ( SchoolTimelineMilestone schoolTimelineMilestone : schoolTimelines.get(i).getSchoolTimelineMilestones()) {
				milestoneTitle = schoolTimelineMilestone.getTitle();
			}
			schoolTimelineData.setTitle(milestoneTitle);
		    result.add(schoolTimelineData);
		}
		return result;
	}
	public ResponseMessage updateAcivateStatus(int schoolId){
		ResponseMessage response = new ResponseMessage();
		String hql = "update School set status=1 where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("id", schoolId);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		response.setStatus(1);
		response.setMessage("data verified successfuly");
		
		return response;
	}
	public ResponseMessage deleteContact(int contactId,String strReason,int schoolId,int userId)
	{
		ResponseMessage response = new ResponseMessage();
		try
		{
			List<ContactInfo> contactInfos =new ContactDetaillDAO().getConatctDetailById(contactId);
			if(contactInfos.size()>0)
			{
				String log = "Deleted data : ";
				log +="| Name : "+contactInfos.get(0).getName();
				log +="| Email : "+contactInfos.get(0).getEmail();
				log +="| Mobile no : "+contactInfos.get(0).getMobileNo();
				log +="| Contact no : "+contactInfos.get(0).getContactNo();
				
				School school = new School();
				school.setId(schoolId);
				
				AdminUser adminUser = new AdminUser();
				adminUser.setId(userId);
				
				saveSchoolLog(new SchoolLog(adminUser, school, strReason, log, new Date(), new Date()));
			}
			
			String hql = "delete ContactInfo  where id = :id";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("id", contactId);
			
			query.executeUpdate();
			session.getTransaction().commit();
			session.clear();
			session.close();
			
			response.setStatus(1);
			response.setMessage("Contact Detail deleted successfully");
			return response;
		}catch(Exception e){
			System.out.println(e);
			response.setStatus(0);
			response.setMessage("Fail to delete contact detail");
			return response;
		}
		
		
	}
	
	public void deleteHighlight(int highlightId)
	{
			String hql = "delete SchoolHighlight  where id = :id";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("id", highlightId);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
	}
	public void saveSchoolLog(SchoolLog schoolLog)
	{
		HibernateUtil hibernateUtilSchoolLog = new HibernateUtil();
		Session sessionSchoolLog = hibernateUtilSchoolLog.openSession();
		sessionSchoolLog.beginTransaction();
		sessionSchoolLog.save(schoolLog);
		sessionSchoolLog.getTransaction().commit();
		sessionSchoolLog.close();
	}
	public ResponseMessage deleteClass(int classId, String reasonDelete,
			int schoolId, int userId) {
		ResponseMessage responseMessage = new ResponseMessage();
		String log = "| Deleted data : ";
		String hql = "from ClassInfo where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", classId);
		List<ClassInfo> classInfos = query.list();
		session.close();
		log += "| standard : "+new StandardTypeDAO().getStandardTypeNameById(classInfos.get(0).getStandardType().getId());
		log += "| stream : "+new SettingsImpl().getStreamTypeNameById(classInfos.get(0).getStreamType().getId());
		log += "| teaching approach : "+new SettingsImpl().getTeachingApproachTypeNameById(classInfos.get(0).getTeachingApproachType().getId());
		log += "| total seats : "+classInfos.get(0).getTotalSeat();
		log += "| vacent seats : "+classInfos.get(0).getVacantSeat();
		log += "| admission deadline : "+classInfos.get(0).getAdmissionDeadline();
		log += "| admission from : "+classInfos.get(0).getAdmissionFrom();
		log += "| admission to : "+classInfos.get(0).getAdmissionTo();
		log += "| admission process : "+classInfos.get(0).getAdmissionProcess();
		log += "| eligibility criteria : "+classInfos.get(0).getEligibilityCriteria();
		log += "| specialization : "+classInfos.get(0).getSpecialization();
		log += "| how_to_apply : "+classInfos.get(0).getHowToApply();
		
		String classSubjectQuery = "from ClassSubjects where classInfo.id = :id";
		HibernateUtil hibernateUtil2 = new HibernateUtil();
		Session sessionClassSubject = hibernateUtil2.openSession();
		sessionClassSubject.beginTransaction();
		Query queryClassSubject = sessionClassSubject.createQuery(classSubjectQuery);
		queryClassSubject.setParameter("id", classId);
		List<ClassSubjects> classSubjects = queryClassSubject.list();
		sessionClassSubject.close();
		
		log +="| subject taught :";
		for(int i=0;i<classSubjects.size();i++){
			log +=","+new SettingsImpl().getSubjectNameById(classSubjects.get(i).getSubject().getId());
		}
		
		String classAccessoryQuery = "from ClassAccessories where classInfo.id = :id";
		HibernateUtil hibernateUtil3 = new HibernateUtil();
		Session sessionClassAccessory = hibernateUtil3.openSession();
		sessionClassAccessory.beginTransaction();
		Query queryClassAccessory = sessionClassAccessory.createQuery(classAccessoryQuery);
		queryClassAccessory.setParameter("id", classId);
		List<ClassAccessories> classAccessories = queryClassAccessory.list();
		sessionClassAccessory.close();
		
		log +="| Accessories Provided : ";
		for(int i=0;i<classAccessories.size();i++){
			log +=","+new SettingsImpl().getAccessoryNameById(classAccessories.get(0).getAccessories().getId());
		}
		
		
		
		String feeTypeQuery = "from ClassFee where classInfo.id = :id";
		HibernateUtil hibernateUtil4 = new HibernateUtil();
		Session sesionFeeType = hibernateUtil4.openSession();
		Query queryFeeType = sesionFeeType.createQuery(feeTypeQuery);
		queryFeeType.setParameter("id", classId);
		List<ClassFee> classFeeList = queryFeeType.list();
		sesionFeeType.close();
		
		
        log +="| Fee type : ";
        for(int f =0;f<classFeeList.size();f++){
        	log +=" | Fee decription : "+classFeeList.get(f).getFeeDesc();
        	log +=" | Amount : "+classFeeList.get(f).getAmount();
        }
        
        String batchTimeQuery = "from ClassBatchTime where classInfo.id = :id";
		HibernateUtil hibernateUtil5 = new HibernateUtil();
		Session sesionBatchTime = hibernateUtil5.openSession();
		Query querybatchTime = sesionBatchTime.createQuery(batchTimeQuery);
		querybatchTime.setParameter("id", classId);
		List<ClassBatchTime> classBatchTimeList = querybatchTime.list();
		sesionBatchTime.close();
		
		
        log +="| Batch Time : ";
        for(int f =0;f<classBatchTimeList.size();f++){
        	log +=" | from  : "+classBatchTimeList.get(f).getBatchTimeFrom();
        	log +=" | to : "+classBatchTimeList.get(f).getBatchTimeTo();
        }
        
        try{
        	 String deleteClassFee = "delete ClassFee where classInfo.id = :id";
             HibernateUtil hibernateUtildeleteClassFee = new HibernateUtil();
             Session sessionDeleteClassFee = hibernateUtildeleteClassFee.openSession();
             sessionDeleteClassFee.beginTransaction();
             Query queryDeleteClassFee = sessionDeleteClassFee.createQuery(deleteClassFee);
             queryDeleteClassFee.setParameter("id", classId);
             queryDeleteClassFee.executeUpdate();
             sessionDeleteClassFee.getTransaction().commit();
             sessionDeleteClassFee.close();
             
             String deleteClassBatchTime = "delete ClassBatchTime where classInfo.id = :id";
             HibernateUtil hibernateUtildeleteClassBatchTime = new HibernateUtil();
             Session sessionDeleteClassBatchTime = hibernateUtildeleteClassBatchTime.openSession();
             sessionDeleteClassBatchTime.beginTransaction();
             Query queryDeleteClassBatchTime = sessionDeleteClassBatchTime.createQuery(deleteClassBatchTime);
             queryDeleteClassBatchTime.setParameter("id", classId);
             queryDeleteClassBatchTime.executeUpdate();
             sessionDeleteClassBatchTime.getTransaction().commit();
             sessionDeleteClassBatchTime.close();
             
             String deleteClassSubjects = "delete ClassSubjects where classInfo.id = :id";
             HibernateUtil hibernateUtildeleteClassSubjects = new HibernateUtil();
             Session sessionDeleteClassSubjects = hibernateUtildeleteClassSubjects.openSession();
             sessionDeleteClassSubjects.beginTransaction();
             Query queryDeleteClassSubjects = sessionDeleteClassSubjects.createQuery(deleteClassSubjects);
             queryDeleteClassSubjects.setParameter("id", classId);
             queryDeleteClassSubjects.executeUpdate();
             sessionDeleteClassSubjects.getTransaction().commit();
             sessionDeleteClassSubjects.close();
             
             String deleteClassAccessories = "delete ClassAccessories where classInfo.id = :id";
             HibernateUtil hibernateUtildeleteClassAccessories = new HibernateUtil();
             Session sessionDeleteClassAccessories = hibernateUtildeleteClassAccessories.openSession();
             sessionDeleteClassAccessories.beginTransaction();
             Query queryDeleteClassAccessories = sessionDeleteClassAccessories.createQuery(deleteClassAccessories);
             queryDeleteClassAccessories.setParameter("id", classId);
             queryDeleteClassAccessories.executeUpdate();
             sessionDeleteClassAccessories.getTransaction().commit();
             sessionDeleteClassAccessories.close();
             
             
     		String deleteClassQuery = "delete ClassInfo  where id = :id";
     		HibernateUtil deleteClassUtil = new HibernateUtil();
     		Session sessiondeleteClass = deleteClassUtil.openSession();
     		sessiondeleteClass.beginTransaction();
     		Query querydeleteClass = sessiondeleteClass.createQuery(deleteClassQuery);
     		querydeleteClass.setParameter("id", classId);
     		
     		querydeleteClass.executeUpdate();
     		sessiondeleteClass.getTransaction().commit();
     		sessiondeleteClass.close();
     		School school = new School();
     		school.setId(schoolId);
     		
     		AdminUser adminUser = new AdminUser();
     		adminUser.setId(userId);
     		saveSchoolLog(new SchoolLog(adminUser, school, reasonDelete, log, new Date(), new Date()));
     		responseMessage.setStatus(1);
     		responseMessage.setMessage("class deleted successfully");
     		return responseMessage;

        }catch(Exception e){
        	responseMessage.setStatus(0);
        	responseMessage.setMessage("Fail to delte class");
        	return responseMessage;
        }
       	}
	public List<School> getSchoolLatLong(int cityId) {
		String hql = "from School where locality.city.id = :city_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("city_id", cityId);
		List<School> schoolList = query.list();
		List<School> schoolLatlong = new ArrayList<School>();
		if(schoolList.size()>0)
		{
			for(int s =0 ;s<schoolList.size();s++){
				School school = new School();
				school.setId(schoolList.get(s).getId());
				school.setName(schoolList.get(s).getName());
				school.setLatitude(schoolList.get(s).getLatitude());
				school.setLongitude(schoolList.get(s).getLongitude());
				schoolLatlong.add(school);
				
			}
			
		}
		session.close();
		return schoolLatlong;
	}
	public List<ClassBatchTime> getClassBatchTimeByClassId(int class_id) {
		String hql = "from ClassBatchTime where  classInfo.id = :class_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
	
		Query query = session.createQuery(hql).setCacheable(false);
		query.setParameter("class_id", class_id);
		List<ClassBatchTime> classBatchTimeList = query.list();
		session.close();
		return classBatchTimeList;
	}
}
