package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.Accessories;
import org.school.admin.model.Subject;
import org.school.admin.util.HibernateUtil;

public class SubjectDAO {

	public List<Subject> getSubjects()
	{
		String hql = "from Subject";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Subject> result = query.list();
		session.close();
		return result;
	}
}
