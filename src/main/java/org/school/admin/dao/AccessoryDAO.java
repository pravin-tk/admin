package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.Accessories;
import org.school.admin.util.HibernateUtil;

public class AccessoryDAO {
	
	public List<Accessories> getAccessory()
	{
		String hql = "from Accessories";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Accessories> result = query.list();
		session.close();
		return result;
	}

}
