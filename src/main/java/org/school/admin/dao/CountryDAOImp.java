package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.Country;
import org.school.admin.model.State;
import org.school.admin.model.Tehsil;
import org.school.admin.util.HibernateUtil;

public class CountryDAOImp {
	
	public ResponseMessage save(Country country)
	{
		ResponseMessage response = new ResponseMessage();
        HibernateUtil hibernateUtil = new HibernateUtil();
        if (country.getName() == null || country.getName().trim().length() == 0) {
            response.setStatus(0);
            response.setMessage("Please enter country name");
        } else {
            String hql = "from Country where name = :name";
            Session session = hibernateUtil.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("name", country.getName());
            List<State> result = query.list();
            session.close();
            if (result.size() > 0) {
                response.setStatus(0);
                response.setMessage("Country name already exists");
            } else {
                Session newsession = hibernateUtil.openSession();
                newsession.beginTransaction();
                newsession.save(country);
                newsession.getTransaction().commit();
                newsession.close();
                response.setStatus(1);
                response.setMessage("Success");
            }
        }
        return response;
	}
	
	public ResponseMessage update(Country country){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from Country where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", country.getName());
		query.setParameter("id", country.getId());
		List<Country> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Country name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(country);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	public List<Country> getCountryList()
	{
		String hql = "from Country";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Country> result = query.list();
		System.out.println("Result : "+result.size());
		for( int i=0;i<result.size();i++)
		{
			Country city = result.get(i);
			System.out.println(city.getName());
		}
		session.close();
		return result;
	}
	
	public List<Country> getCountryById(int id)
	{
		String hql = "from Country where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Country> result = query.list();
		session.close();
		return result;
	}
	
}
