package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.BoardType;
import org.school.admin.util.HibernateUtil;

public class BoardDAOImpl {
	
	public List<BoardType> getBoardList()
	{
		String hql = "from BoardType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<BoardType> result = query.list();
		session.close();
		return result;
	}

}
