package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.school.admin.data.MainFilter;
import org.school.admin.data.NameList;
import org.school.admin.model.StandardType;
import org.school.admin.util.HibernateUtil;

public class StandardTypeDAO {

	public List<StandardType> getStandardType()
	{
		String hql = "from StandardType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<StandardType> result = query.list();
		session.close();
		return result;
	}
	
	public List<NameList> getStandardList()
	{
		String hql = "SELECT s.id as id, s.name as name FROM StandardType s";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(NameList.class));
		List<NameList> result = query.list();
		session.close();
		return result;
	}
}
