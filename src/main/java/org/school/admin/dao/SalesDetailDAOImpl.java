package org.school.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.AdminUser;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
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
		List<SalesInfo> salesInfos = new ArrayList<SalesInfo>();
		salesInfos = getSalesDetail(salesInfo.getSchool().getId());
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(salesInfo.getSchool().getId(), "salesDetails");
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
			salesInfo.setAdminUserByDataCollectorId(dataCollector);
			
			AdminUser salesExecutive = new AdminUser();
			salesExecutive.setId(salesInfoList.get(i).getAdminUserBySalesExecutiveId().getId());
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

     public List<SalesInfo> updateSalesDetail(SalesInfo salesInfo)
     {
    	 HibernateUtil hibernateUtil = new HibernateUtil();
 		Session session = hibernateUtil.openSession();
 		session.beginTransaction();
 		session.update("id", salesInfo);
 		session.getTransaction().commit();
 		session.close();
 		
 		List<SalesInfo> salesInfos = new ArrayList<SalesInfo>();
		salesInfos = getSalesDetail(salesInfo.getSchool().getId());
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(salesInfo.getSchool().getId(), "salesDetails");
		return salesInfos;
     }
}
