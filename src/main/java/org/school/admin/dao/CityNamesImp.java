package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.City;
import org.school.admin.model.Tehsil;
import org.school.admin.util.HibernateUtil;

public class CityNamesImp {
	/**
	 * Save City
	 * 
	 * @param city
	 * @return String
	 */
	public ResponseMessage save(City city){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (city.getName() == null || city.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter city name");
		} else {
			String hql = "from City where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", city.getName());
			List<City> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("City name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(city);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	public String update(City city){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		session.update(city);
		session.getTransaction().commit();
		session.close();
		return "Success";
	}
	
	/**
	 * Get All Cities
	 * @author pradeep
	 * @return List<City>
	 */
	public List<City> getCityNames()
	{
		String hql = "from City";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<City> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get cities by tehsil ID
	 * @author pradeep
	 * @param int tehsilId
	 * @return List<City>
	 */
	public List<City> getCityNamesByTehsilId(int tehsilId)
	{
		String hql = "from City where tehsil.id = :tehsilId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("tehsilId", tehsilId);
		List<City> result = query.list();
		session.close();
		return result;
	}
	
	public List<City> getCityById(int id)
	{
		String hql = "from City where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<City> result = query.list();
		session.close();
		return result;
	}
}
