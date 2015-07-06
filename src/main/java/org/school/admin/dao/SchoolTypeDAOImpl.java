package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.AreaUnit;
import org.school.admin.model.SchoolType;
import org.school.admin.util.HibernateUtil;

public class SchoolTypeDAOImpl {
	
	public List<SchoolType> getSchoolTypeList()
	{
		String hql = "from SchoolType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SchoolType> result = query.list();
		session.close();
		return result;
	}
}


