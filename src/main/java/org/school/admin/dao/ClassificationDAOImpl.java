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
		System.out.println("Result : "+result.size());
		for( int i=0;i<result.size();i++)
		{
			SchoolClassificationType classification = result.get(i);
			System.out.println(classification.getName());
		}
		session.close();
		return result;
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
