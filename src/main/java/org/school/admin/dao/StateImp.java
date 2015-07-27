package org.school.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.State;
import org.school.admin.model.Country;
import org.school.admin.model.Tehsil;
import org.school.admin.util.HibernateUtil;

public class StateImp {
    /**
     * Save State
     * 
     * @param state
     * @return String
     */
    public ResponseMessage save(State state){
        ResponseMessage response = new ResponseMessage();
        HibernateUtil hibernateUtil = new HibernateUtil();
        if (state.getName() == null || state.getName().trim().length() == 0) {
            response.setStatus(0);
            response.setMessage("Please enter state name");
        } else {
            String hql = "from State where name = :name";
            Session session = hibernateUtil.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("name", state.getName());
            List<State> result = query.list();
            session.close();
            if (result.size() > 0) {
                response.setStatus(0);
                response.setMessage("State name already exists");
            } else {
                Session newsession = hibernateUtil.openSession();
                newsession.beginTransaction();
                newsession.save(state);
                newsession.getTransaction().commit();
                newsession.close();
                response.setStatus(1);
                response.setMessage("Success");
            }
        }
        return response;
    }
    
    public ResponseMessage update(State state){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from State where name = :name and country.id = :country_id and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", state.getName());
		query.setParameter("country_id", state.getCountry().getId());
		query.setParameter("id", state.getId());
		List<State> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("State name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(state);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
    
    /**
     * Get All States    
     * @return List<State>
     */
    public List<State> getStateList()
    {
        String hql = "from State";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        List<State> result = query.list();
        session.close();
        return result;
    }
    
    /**
     * Get state by country id
     * @param int countryID
     * @return List<State>
     */
    public List<State> getStateByCountryId(int countryId)
    {
        System.out.println("country="+countryId);
        String hql = "from State where country.id = :countryId";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("countryId", countryId);
        List<State> result = query.list();
        session.close();
        System.out.println("Size1="+result.size());
        return result;
    }
    
    public List<State> getStateById(int id)
    {
        String hql = "from State where id = :id";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<State> result = query.list();
        session.close();
        return result;
    }
}