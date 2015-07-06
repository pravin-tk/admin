//package org.school.admin.dao;
//
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.school.admin.model.Country;
//import org.school.admin.util.HibernateUtil;
//
//public class ClassTypeDAO {
//	public List<ClassType> getCountryList()
//	{
//		String hql = "from ClassType";
//		HibernateUtil hibernateUtil = new HibernateUtil();
//		Session session = hibernateUtil.openSession();
//		Query query = session.createQuery(hql);
//		List<ClassType> result = query.list();
//		System.out.println("Result : "+result.size());
//		session.close();
//		return result;
//	}
//
//}
