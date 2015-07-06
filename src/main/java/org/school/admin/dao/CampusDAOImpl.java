package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.BoardType;
import org.school.admin.model.CampusInfo;
import org.school.admin.util.HibernateUtil;

public class CampusDAOImpl {
	
	public ResponseMessage saveCampus(CampusInfo campusInfo)
	{
		 ResponseMessage response = new ResponseMessage();
		String hql = "from CampusInfo where School.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session oldsession = hibernateUtil.openSession();
		Query query = oldsession.createQuery(hql);
		query.setParameter("school_id",campusInfo.getSchool().getId());
		List<CampusInfo> result = query.list();
		oldsession.close();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		if(result.size() > 0){
			campusInfo.setId(result.get(0).getId());
			session.update("id", campusInfo);
			response.setStatus(1);
			response.setMessage("Campus detail updated successfully");
		} else {
			
			session.save(campusInfo);
			response.setStatus(1);
			response.setMessage("Campus detail saved successfully");
		}
		session.getTransaction().commit();
		session.close();
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(campusInfo.getSchool().getId(), "campusDetail");
		return response;
	}
	
	public List<CampusInfo> getCampusInfo(int school_id){
		String hql = "from CampusInfo where School.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session oldsession = hibernateUtil.openSession();
		Query query = oldsession.createQuery(hql);
		query.setParameter("school_id",school_id);
		List<CampusInfo> result = query.list();
		oldsession.close();
		return result;
	}

}
