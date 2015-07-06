package org.school.admin.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolInfo;
import org.school.admin.util.HibernateUtil;

public class SchoolAchievementDAO {
	public ResponseMessage saveSchoolAchievement(SchoolInfo schoolInfo)
	{
		 ResponseMessage response = new ResponseMessage();
		String hql = "from SchoolInfo where school.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session oldsession = hibernateUtil.openSession();
		Query query = oldsession.createQuery(hql);
		query.setParameter("school_id",schoolInfo.getSchool().getId());
		List<SchoolInfo> result = query.list();
		oldsession.close();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		if(result.size() > 0){
			String hqlupdate = "Update SchoolInfo set approvalDesc = :approval , awardDesc = :award , tieUpDesc = :tieup where school.id = :school_id";
			Query query1 = session.createQuery(hqlupdate);
			query1.setString("approval", schoolInfo.getApprovalDesc());
			query1.setString("award", schoolInfo.getAwardDesc());
			query1.setString("tieup", schoolInfo.getTieUpDesc());
			query1.setParameter("school_id",schoolInfo.getSchool().getId());
			query1.executeUpdate();
			response.setStatus(1);
			response.setMessage("Updated successfully");
			
		} else {
			session.save(schoolInfo);
			response.setStatus(1);
			response.setMessage("Saved successfully");
		}
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(schoolInfo.getSchool().getId(), "achievements");
		session.getTransaction().commit();
		session.close();
		return response;
	}

}
