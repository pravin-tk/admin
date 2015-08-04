package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.school.admin.data.NameImageList;
import org.school.admin.model.SchoolClassificationType;
import org.school.admin.util.HibernateUtil;

public class ClassificationDAOImpl {

	public List<SchoolClassificationType> getClassificationList()
	{
		String hql = "from SchoolClassificationType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SchoolClassificationType> result = query.list();
		session.close();
		return result;
	}
	public String getClassificationNameById(int id){
		String hql = "from SchoolClassificationType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SchoolClassificationType> result = query.list();
		session.close();
		return result.get(0).getName();
	}
	public List<NameImageList> getClassifications()
	{
		String hql = "SELECT id as id, name as name,COALESCE(image,'na') as image from SchoolClassificationType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(NameImageList.class));
		@SuppressWarnings("unchecked")
		List<NameImageList> result = query.list();
		session.close();
		return result;
	}
	
}
