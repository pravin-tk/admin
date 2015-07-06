package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.SectionType;
import org.school.admin.util.HibernateUtil;

public class SectionTypeDAO {

	public List<SectionType> getSectionType()
	{
		String hql = "from SectionType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SectionType> result = query.list();
		System.out.println("Result : "+result.size());
		session.close();
		return result;
	}
}
