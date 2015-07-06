package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.TeachingApproachType;
import org.school.admin.util.HibernateUtil;

public class TeachingApproachTypeDAO {
	
	public List<TeachingApproachType> getTeachingApproachType()
	{
		String hql = "from TeachingApproachType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<TeachingApproachType> result = query.list();
		System.out.println("Result : "+result.size());
		session.close();
		return result;
	}


}
