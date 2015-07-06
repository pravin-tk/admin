package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.SchoolCategoryType;
import org.school.admin.util.HibernateUtil;

public class CategoryDAOImpl {

	public List<SchoolCategoryType> getCategoryList() {
		String hql = "from SchoolCategoryType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SchoolCategoryType> result = query.list();
		session.close();
		return result;
	}

}
