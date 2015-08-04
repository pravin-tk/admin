package org.school.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.PrevStudentProfileList;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.AdminUser;
import org.school.admin.model.City;
import org.school.admin.model.Locality;
import org.school.admin.model.PrevStudentProfile;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
import org.school.admin.model.SchoolInfo;
import org.school.admin.model.SchoolLog;
import org.school.admin.util.HibernateUtil;

public class PrevStudentProfileDAO {
	
	public void savePreStudentProfile(PrevStudentProfile prevStudentProfile)
	{
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		session.save(prevStudentProfile);
		session.getTransaction().commit();
		session.close();
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(prevStudentProfile.getSchool().getId(), "oldStudentProfile");
		
		String log ="New entry : ";
		log +="| Name : "+prevStudentProfile.getName();
		log +="| Email : "+prevStudentProfile.getEmail();
    	log +="| Mobile : "+prevStudentProfile.getMobile();
    	log +="| Batch : "+prevStudentProfile.getBatch();
    	log +="| Achivements : "+prevStudentProfile.getAchievements();
    	
    	School school = new School();
    	school.setId(prevStudentProfile.getSchool().getId());
    	
    	AdminUser adminUser = new AdminUser();
    	adminUser.setId(prevStudentProfile.getLastUpdatedBy());
    	
    	SchoolLog schoolLog = new SchoolLog(adminUser, school, "", log, new Date(), new Date());
    	saveSchoolLog(schoolLog);
    	
		
		
	}
	
	public void updatePreStudentProfile(PrevStudentProfile prevStudentProfile,String strReason)
	{
		String beforeUpdate = "Before change data in old student profile ";
		String afterUpdate = " | After change data in old student profile ";
		List<PrevStudentProfileList> prevStudentProfileLists =  getPrevStudentProfileById(prevStudentProfile.getId());
		if(prevStudentProfileLists.size()>0)
		{
			
			beforeUpdate += "| Name : "+prevStudentProfileLists.get(0).getName();
			beforeUpdate += "| Mobile : "+prevStudentProfileLists.get(0).getMobile();
			beforeUpdate += "| Email : "+prevStudentProfileLists.get(0).getEmail();
			beforeUpdate += "| Batch : "+prevStudentProfileLists.get(0).getBatch();
			beforeUpdate +="| Achievements : "+prevStudentProfileLists.get(0).getAchievements();
			
			School school = new School();
			school.setId(prevStudentProfile.getSchool().getId());
			
			AdminUser adminUser = new AdminUser();
			adminUser.setId(prevStudentProfile.getLastUpdatedBy());
			
			//new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, "", beforeUpdate, new Date(), new Date()));
		}
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		session.update("id", prevStudentProfile);
		session.getTransaction().commit();
		session.close();
		
		List<PrevStudentProfileList> afterUpdatePreStudent =  getPrevStudentProfileById(prevStudentProfile.getId());
		if(afterUpdatePreStudent.size()>0)
		{
		
			afterUpdate += "| Name : "+afterUpdatePreStudent.get(0).getName();
			afterUpdate += "| Mobile : "+afterUpdatePreStudent.get(0).getMobile();
			afterUpdate += "| Email : "+afterUpdatePreStudent.get(0).getEmail();
			afterUpdate += "| Batch : "+afterUpdatePreStudent.get(0).getBatch();
			afterUpdate +="| Achievements : "+afterUpdatePreStudent.get(0).getAchievements();
			
			School school = new School();
			school.setId(prevStudentProfile.getSchool().getId());
			
			AdminUser adminUser = new AdminUser();
			adminUser.setId(prevStudentProfile.getLastUpdatedBy());
			String log = beforeUpdate.concat(afterUpdate);
			new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, strReason, log, new Date(), new Date()));
		}
		
	}
	
	public List<PrevStudentProfileList> getPrevStudentProfile(Integer schoolId) {
		String hql = "from PrevStudentProfile where school.id = :school_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("school_id", schoolId);
		
		List<PrevStudentProfile> prevStudentProfileList = query.list();
		List<PrevStudentProfileList> newListofStudentProfile = new ArrayList<PrevStudentProfileList>();
		for(int i =0 ;i < prevStudentProfileList.size(); i++)
		{
			PrevStudentProfileList prevStudentProfile = new PrevStudentProfileList();
			prevStudentProfile.setId(prevStudentProfileList.get(i).getId());
			prevStudentProfile.setMobile(prevStudentProfileList.get(i).getMobile());
			prevStudentProfile.setEmail(prevStudentProfileList.get(i).getEmail());
			prevStudentProfile.setAchievements(prevStudentProfileList.get(i).getAchievements());
			prevStudentProfile.setBatch(prevStudentProfileList.get(i).getBatch());
			prevStudentProfile.setName(prevStudentProfileList.get(i).getName());
			newListofStudentProfile.add(prevStudentProfile);
		}
		session.close();
		return newListofStudentProfile;
	}
	
	public List<PrevStudentProfileList> getPrevStudentProfileById(Integer id) {
		String hql = "from PrevStudentProfile where id = :id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<PrevStudentProfile> prevStudentProfileList = query.list();
		List<PrevStudentProfileList> newListofStudentProfile = new ArrayList<PrevStudentProfileList>();
		for(int i =0 ;i < prevStudentProfileList.size(); i++)
		{
			PrevStudentProfileList prevStudentProfile = new PrevStudentProfileList();
			prevStudentProfile.setId(prevStudentProfileList.get(i).getId());
			prevStudentProfile.setMobile(prevStudentProfileList.get(i).getMobile());
			prevStudentProfile.setEmail(prevStudentProfileList.get(i).getEmail());
			prevStudentProfile.setAchievements(prevStudentProfileList.get(i).getAchievements());
			prevStudentProfile.setBatch(prevStudentProfileList.get(i).getBatch());
			prevStudentProfile.setName(prevStudentProfileList.get(i).getName());
			newListofStudentProfile.add(prevStudentProfile);
		}
		session.close();
		return newListofStudentProfile;
	}
	
		
	public void deletePrevStudentProfile(int contactId,int schoolId,String reasonDelete,int userId)
	{
		    AdminUser adminUser=new AdminUser();
		    adminUser.setId(userId);
		    School school = null;
		    System.out.println("AdminUserID.. "+userId);
		    List<PrevStudentProfileList> prevStudentProfiles = getPrevStudentProfileById(contactId);
		    List<School> schoolList = new SchoolDAOImp().getSchoolById(schoolId);
		    String log = "Deleted Data : ";
		    for( int s=0;s<schoolList.size();s++){
		    	school = schoolList.get(s);
		    	school.setName(schoolList.get(s).getName());
		    	Locality locality= schoolList.get(s).getLocality();
		    	locality.setName(schoolList.get(s).getLocality().getName());
		    	City city = schoolList.get(s).getLocality().getCity();
		    	city.setName(schoolList.get(s).getLocality().getCity().getName());
		    	locality.setCity(city);
		    	school.setLocality(locality);
		    	log +="School Address "+school.getName()+","+school.getLocality().getName()+","
		             +school.getLocality().getCity().getName();
		    	
		    }
		    for(int i =0 ;i <prevStudentProfiles.size();i++){
		    	log +="| Student Record : Name :"+prevStudentProfiles.get(i).getName();
		    	log +="| Email : "+prevStudentProfiles.get(i).getEmail();
		    	log +="| Mobile : "+prevStudentProfiles.get(i).getMobile();
		    	log +="| Batch : "+prevStudentProfiles.get(i).getBatch();
		    	log +="| Achivements : "+prevStudentProfiles.get(i).getAchievements();
		    }
		  
		    SchoolLog schoolLog = new SchoolLog(adminUser, school, reasonDelete, log, new Date(), new Date());
			String hql = "delete PrevStudentProfile  where id = :id";
			HibernateUtil hibernateUtil = new HibernateUtil();
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("id", contactId);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			saveSchoolLog(schoolLog);
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
}