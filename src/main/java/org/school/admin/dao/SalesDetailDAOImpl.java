package org.school.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.PrevStudentProfileList;
import org.school.admin.model.AdminUser;
import org.school.admin.model.City;
import org.school.admin.model.Locality;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
import org.school.admin.model.SchoolLog;
import org.school.admin.util.HibernateUtil;



public class SalesDetailDAOImpl {
	
	public List<SalesInfo> saveSalesDetail(SalesInfo salesInfo)
	{
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		session.save(salesInfo);
		session.getTransaction().commit();
		session.close();
		
		String log = "New Entry of sales detail :";
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		String sales = schoolDAOImp.getAdminUserNameById(salesInfo.getAdminUserBySalesExecutiveId().getId());
		String dataCollector = schoolDAOImp.getAdminUserNameById(salesInfo.getAdminUserByDataCollectorId().getId());
		log +="| Sales executive name : "+sales;
		log +="| Data collector name : "+dataCollector;
		log +="| Contact Person Name : "+salesInfo.getInfoProviderName();
		log +="| Contact Person Number : "+salesInfo.getInfoProviderContactNo();
		log +="| Contact Person email : "+salesInfo.getInfoProviderEmail();
		log +="| designation : "+salesInfo.getInfoProviderDesignation();
 
		School school = new School();
		school.setId(salesInfo.getSchool().getId());
		
		AdminUser adminUser = new AdminUser();
		adminUser.setId(salesInfo.getLastUpdatedBy());
		
		List<SalesInfo> salesInfos = new ArrayList<SalesInfo>();
		salesInfos = getSalesDetail(salesInfo.getSchool().getId());
		
		schoolDAOImp.updateTabs(salesInfo.getSchool().getId(), "salesDetails");
		schoolDAOImp.saveSchoolLog(new SchoolLog(adminUser, school, "", log, new Date(), new Date()));
		//return "Sales Detsil Save successfully";
		return salesInfos;
	
	}

	public List<SalesInfo> getSalesDetail(Integer schoolId) {
		String hql = "from SalesInfo where school.id = :school_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("school_id", schoolId);
		
		List<SalesInfo> salesInfoList = query.list();
		List<SalesInfo> newsalesInfoList = new ArrayList<SalesInfo>();
		for(int i =0 ;i < salesInfoList.size(); i++)
		{
			SalesInfo salesInfo = new SalesInfo();
			salesInfo.setId(salesInfoList.get(i).getId());
			salesInfo.setInfoProviderName(salesInfoList.get(i).getInfoProviderName());
			salesInfo.setInfoProviderContactNo(salesInfoList.get(i).getInfoProviderContactNo());
			salesInfo.setInfoProviderEmail(salesInfoList.get(i).getInfoProviderEmail());
			salesInfo.setInfoProviderDesignation(salesInfoList.get(i).getInfoProviderDesignation());
			newsalesInfoList.add(salesInfo);
		}
		session.close();
		return newsalesInfoList;
	}
	
	public SalesInfo getSalesDetailById(Integer id) {
		String hql = "from SalesInfo where id = :id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<SalesInfo> salesInfoList = query.list();
		List<SalesInfo> newsalesInfoList = new ArrayList<SalesInfo>();
		
		for(int i =0 ;i < salesInfoList.size(); i++)
		{
			SalesInfo salesInfo = new SalesInfo();
		    AdminUser dataCollector = new AdminUser();
		    dataCollector.setId(salesInfoList.get(i).getAdminUserByDataCollectorId().getId());
		    dataCollector.setName(salesInfoList.get(i).getAdminUserByDataCollectorId().getName());
			salesInfo.setAdminUserByDataCollectorId(dataCollector);
			
			AdminUser salesExecutive = new AdminUser();
			salesExecutive.setId(salesInfoList.get(i).getAdminUserBySalesExecutiveId().getId());
			salesExecutive.setName(salesInfoList.get(i).getAdminUserBySalesExecutiveId().getName());
			salesInfo.setAdminUserBySalesExecutiveId(salesExecutive);
			salesInfo.setId(salesInfoList.get(i).getId());
			salesInfo.setInfoProviderName(salesInfoList.get(i).getInfoProviderName());
			salesInfo.setInfoProviderContactNo(salesInfoList.get(i).getInfoProviderContactNo());
			salesInfo.setInfoProviderEmail(salesInfoList.get(i).getInfoProviderEmail());
			salesInfo.setInfoProviderDesignation(salesInfoList.get(i).getInfoProviderDesignation());
			newsalesInfoList.add(salesInfo);
			
		}
		session.close();
		return newsalesInfoList.get(0);
	}

     public List<SalesInfo> updateSalesDetail(SalesInfo salesInfo,String reason)
     {
    	 
    	 SalesInfo salesBeforeUpdate = getSalesDetailById(salesInfo.getId());
    	 String beforeUpdate = "Data before update in sales detail : ";
    	 beforeUpdate += "| sales executive name : "+salesBeforeUpdate.getAdminUserBySalesExecutiveId().getName();
    	 beforeUpdate += " | data collector name : "+salesBeforeUpdate.getAdminUserByDataCollectorId().getName();
    	 beforeUpdate += "| contact person name : "+salesBeforeUpdate.getInfoProviderName();
    	 beforeUpdate += " | contact person number : "+salesBeforeUpdate.getInfoProviderContactNo();
    	 beforeUpdate += " | contact person email : "+salesBeforeUpdate.getInfoProviderEmail();
    	 
    	 
    	 HibernateUtil hibernateUtil = new HibernateUtil();
 		Session session = hibernateUtil.openSession();
 		session.beginTransaction();
 		session.update("id", salesInfo);
 		session.getTransaction().commit();
 		session.close();
 		
 		SalesInfo salesAfterUpdate = getSalesDetailById(salesInfo.getId());
 		String afterUpdate ="Data after update in sales detail : ";
 		afterUpdate += "| sales executive name : "+salesAfterUpdate.getAdminUserBySalesExecutiveId().getName();
 		afterUpdate += " | data collector name : "+salesAfterUpdate.getAdminUserByDataCollectorId().getName();
 		afterUpdate += "| contact person name : "+salesAfterUpdate.getInfoProviderName();
 		afterUpdate += " | contact person number : "+salesAfterUpdate.getInfoProviderContactNo();
 		afterUpdate += " | contact person email : "+salesAfterUpdate.getInfoProviderEmail();
 		
 		AdminUser adminUser = new AdminUser();
 		adminUser.setId(salesInfo.getLastUpdatedBy());
 		
 		School school = new School();
 		school.setId(salesInfo.getSchool().getId());
 		String log = beforeUpdate.concat(afterUpdate);
 		new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, reason, log, new Date(), new Date()));
 		List<SalesInfo> salesInfos = new ArrayList<SalesInfo>();
		salesInfos = getSalesDetail(salesInfo.getSchool().getId());
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(salesInfo.getSchool().getId(), "salesDetails");
		return salesInfos;
     }
     public void deleteSalesDetaile(int contactId,int schoolId,String reasonDelete,int userId)
 	{
 		    AdminUser adminUser=new AdminUser();
 		    adminUser.setId(userId);
 		    School school = null;
 		    SalesInfo salesInfo = getSalesDetailById(contactId);
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
 		    
 		    	String sales = new SchoolDAOImp().getAdminUserNameById(salesInfo.getAdminUserBySalesExecutiveId().getId());
 				String dataCollector =new  SchoolDAOImp().getAdminUserNameById(salesInfo.getAdminUserByDataCollectorId().getId());
 				log +="| Sales executive name : "+sales;
 				log +="| Data collector name : "+dataCollector;
 				log +="| Contact Person Name : "+salesInfo.getInfoProviderName();
 				log +="| Contact Person Number : "+salesInfo.getInfoProviderContactNo();
 				log +="| Contact Person email : "+salesInfo.getInfoProviderEmail();
 				log +="| designation : "+salesInfo.getInfoProviderDesignation();
 		 
 				school.setId(schoolId);
 		    SchoolLog schoolLog = new SchoolLog(adminUser, school, reasonDelete, log, new Date(), new Date());
 			String hql = "delete SalesInfo  where id = :id";
 			HibernateUtil hibernateUtil = new HibernateUtil();
 			Session session = hibernateUtil.openSession();
 			session.beginTransaction();
 			Query query = session.createQuery(hql);
 			query.setParameter("id", contactId);
 			query.executeUpdate();
 			session.getTransaction().commit();
 			session.close();
 			new SchoolDAOImp().saveSchoolLog(schoolLog);
 	}
}
