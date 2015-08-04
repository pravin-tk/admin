package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.AreaUnit;
import org.school.admin.util.HibernateUtil;

public class AreaUnitDAOImpl {

	
	public List<AreaUnit> getAreaUnit()
	{
		String hql = "from AreaUnit";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<AreaUnit> result = query.list();
		session.close();
		return result;
	}
	
	public String getAreaUnitById(int id)
	{
		String  areaUnit = "";
		String hql = "from AreaUnit where id =:id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
	   
		List<AreaUnit> result = query.list();
		if(result.size()>0)
		{
			areaUnit = result.get(0).getName();
		}
		session.close();
		return areaUnit;
	}
}
