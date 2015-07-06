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
	System.out.println("Result : "+result.size());
	for( int i=0;i<result.size();i++)
	{
		AreaUnit city = result.get(i);
		System.out.println(city.getName());
	}
	session.close();
	return result;
	}
}
