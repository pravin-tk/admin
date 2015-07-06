package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.District;
import org.school.admin.model.Tehsil;
import org.school.admin.util.HibernateUtil;

public class DistrictImpl {
	
	/**
	 * Save District
	 * 
	 * @param tehsil
	 * @return ResponseMessage
	 */
	public ResponseMessage save(District district){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (district.getName() == null || district.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter district name");
		} else {
			String hql = "from District where name = :name and state.id = :state_id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", district.getName());
			query.setParameter("state_id", district.getState().getId());
			List<Tehsil> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("District name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(district);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	public ResponseMessage update(District district){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from District where name = :name and state.id = :state_id and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", district.getName());
		query.setParameter("state_id", district.getState().getId());
		query.setParameter("id", district.getId());
		List<Tehsil> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("District name already exists");
		} else {
			Session newsession = hibernateUtil.openSession();
			newsession.beginTransaction();
			newsession.update(district);
			newsession.getTransaction().commit();
			newsession.close();
			response.setStatus(1);
			response.setMessage("Success");
		}
		return response;
	}
	
	/**
	 * Get All Districts
	 * @author pradeep
	 * @return List<District>
	 */
	public List<District> getDistrictNames()
	{
		String hql = "from District";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<District> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get All Districts by state
	 * @author pradeep
	 * @return List<District>
	 */
	public List<District> getAllDistrictByStateId(int state_id)
	{
		String hql = "from District where state.id = :state_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("state_id", state_id);
		List<District> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get All Tehsils by district
	 * @author pradeep
	 * @return List<District>
	 */
	public List<District> getDistrictDetailById(int id)
	{
		String hql = "from District where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<District> result = query.list();
		session.close();
		return result;
	}
}
