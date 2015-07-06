package org.school.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.PrevStudentProfileList;
import org.school.admin.model.PrevStudentProfile;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
import org.school.admin.model.SchoolInfo;
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
		
	}
	
	public void updatePreStudentProfile(PrevStudentProfile prevStudentProfile)
	{
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		session.update("id", prevStudentProfile);
		session.getTransaction().commit();
		session.close();
		
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
	



}
