package org.school.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.TehsilData;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.Tehsil;
import org.school.admin.util.HibernateUtil;

public class TehsilImpl {
	
	/**
	 * Save Tehsil
	 * 
	 * @param tehsil
	 * @return ResponseMessage
	 */
	public ResponseMessage save(Tehsil tehsil){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (tehsil.getName() == null || tehsil.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter tehsil name");
		} else {
			String hql = "from Tehsil where name = :name and district.id = :district_id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", tehsil.getName());
			query.setParameter("district_id", tehsil.getDistrict().getId());
			List<Tehsil> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Tehsil name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(tehsil);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	public ResponseMessage update(Tehsil tehsil){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from Tehsil where name = :name and district.id = :district_id and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", tehsil.getName());
		query.setParameter("district_id", tehsil.getDistrict().getId());
		query.setParameter("id", tehsil.getId());
		List<Tehsil> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Tehsil name already exists");
		} else {
			Session newsession = hibernateUtil.openSession();
			newsession.beginTransaction();
			newsession.update(tehsil);
			newsession.getTransaction().commit();
			newsession.close();
			response.setStatus(1);
			response.setMessage("Success");
		}
		return response;
	}
	/**
	 * Get All Tehsils
	 * @author pradeep
	 * @return List<Tehsil>
	 */
	public List<Tehsil> getTehsilList()
	{
		String hql = "from Tehsil";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Tehsil> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get All Tehsils by district
	 * @author pradeep
	 * @return List<Tehsil>
	 */
	public List<TehsilData> getTehsilByDistrictId(int district_id)
	{
		String hql = "from Tehsil where district.id = :district_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("district_id", district_id);
		List<Tehsil> result = query.list();
		session.close();
		List<TehsilData> tehsilDataList = new ArrayList<TehsilData>();
		if(result.size()>0){
			for(int i=0;i<result.size();i++){
				TehsilData tehsilData = new TehsilData();
				tehsilData.setId(result.get(i).getId());
				tehsilData.setName(result.get(i).getName());
				tehsilDataList.add(tehsilData);
			}
		}
		return tehsilDataList;
	}
	public List<Tehsil> getAllTehsilByDistrictId(int district_id)
	{
		String hql = "from Tehsil where district.id = :district_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("district_id", district_id);
		List<Tehsil> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get All Tehsils by district
	 * @author pradeep
	 * @return List<Tehsil>
	 */
	public List<Tehsil> getTehsilDetailById(int id)
	{
		String hql = "from Tehsil where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Tehsil> result = query.list();
		session.close();
		return result;
	}
}
