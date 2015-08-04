package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.Country;
import org.school.admin.model.MediumType;
import org.school.admin.util.HibernateUtil;

public class MediumDAOImpl {

	public List<MediumType> getMediumList()
	{
	 	String hql = "from MediumType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<MediumType> result = query.list();
		session.close();
		return result;
	}
	public List<MediumType> getMediumListNameById(Short id)
	{
		String hql = "from MediumType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<MediumType> result = query.list();
		session.close();
		return result;
	}
}
