package org.school.admin.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.BusInfo;
import org.school.admin.model.Route;
import org.school.admin.model.BusStop;
import org.school.admin.model.BusRoute;
import org.school.admin.model.BusRouteStop;
import org.school.admin.model.SafetyCategoryItem;
import org.school.admin.util.HibernateUtil;

public class TransportImpl {
	
	/**
	 * Save Transport
	 * 
	 * @param Transport
	 * @return String
	 */
	public ResponseMessage saveRoute(Route route){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (route.getName() == null || route.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter route name");
		} else {
			String hql = "from Route where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", route.getName());
			List<Route> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Route name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(route);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update Route
	 * 
	 * @param route
	 * @return String
	 */
	public ResponseMessage updateRoute(Route route){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from Route where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);		
		query.setParameter("name", route.getName());
		query.setParameter("id", route.getId());
		List<Route> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Route name already exists");
		} else {
			route.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(route);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Routes
	 * @author 
	 * @return List<Route>
	 */
	public List<Route> getRoute()
	{
		String hql = "from Route";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Route> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get Route by ID
	 * @author 
	 * @return List<Route>
	 */
	public List<Route> getRouteById(int id)
	{
		String hql = "from Route where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Route> result = query.list();
		session.close();
		return result;
	}
	
	/***************** Route Stops *****************************/
	
	/**
     * Save Route Stops
     * 
     * @param routeStop
     * @return String
     */
    public ResponseMessage saveRouteStop(BusRouteStop routeStop){
        ResponseMessage response = new ResponseMessage();
        HibernateUtil hibernateUtil = new HibernateUtil();
        System.out.println("pick="+routeStop.getBusPickTime());
        if (routeStop.getBusPickTime() == null || routeStop.getBusPickTime().toString().length()== 0) {
            response.setStatus(0);
            response.setMessage("Please enter pick up time");
        
        }else if (routeStop.getBusDropTime() == null || routeStop.getBusDropTime().toString().length()== 0) {
                response.setStatus(0);
                response.setMessage("Please enter drop time");    
        } else {
            String hql = "from BusRouteStop where busPickTime = :busPickTime and busDropTime = :busDropTime ";
            Session session = hibernateUtil.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("busPickTime", routeStop.getBusPickTime());
            query.setParameter("busDropTime", routeStop.getBusDropTime());
            List<BusRouteStop> result = query.list();
            session.close();
            if (result.size() > 0) {
                response.setStatus(0);
                response.setMessage("Route Stop info already exists");
            } else {
                Session newsession = hibernateUtil.openSession();
                newsession.beginTransaction();
                newsession.save(routeStop);
                newsession.getTransaction().commit();
                newsession.close();
                response.setStatus(1);
                response.setMessage("Success");
            }
        }
        return response;
    }
    /**
     * update
     * @param routeStop
     * @return json string
     */
    public ResponseMessage updateRouteStop(BusRouteStop routeStop){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from BusRouteStop where busPickTime = :busPickTime and busDropTime = :busDropTime and route.id = :route_id and busStop.id = :stop_id and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
	    query.setParameter("busPickTime", routeStop.getBusPickTime());
        query.setParameter("busDropTime", routeStop.getBusDropTime());
		query.setParameter("route_id", routeStop.getRoute().getId());
		query.setParameter("stop_id", routeStop.getBusStop().getId());
		query.setParameter("id", routeStop.getId());
		List<SafetyCategoryItem> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Route Stop info already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(routeStop);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
    
    /**
     * Get All Stop    
     * @return List<BusStop>
     */
    public List<BusStop> getStopList()
    {
        String hql = "from BusStop";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        List<BusStop> result = query.list();
        session.close();
        return result;
    }
    
    /**
     * Get all stops by route id
     * @param int route
     * @return List<busRouteStop>
     */
    public List<BusRouteStop> getStopByRouteId(int routeId)
    {   System.out.println("hey");
        String hql = "from BusRouteStop where route.id = :routeId";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("routeId", routeId);
        List<BusRouteStop> result = query.list();
        session.close();
        //System.out.println("Size1="+result.size());
        return result;
    }
    
    public List<BusRouteStop> getRouteStopById(int routeStopId)
    {
        String hql = "from BusRouteStop where id = :routestop_id";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("routestop_id", routeStopId);
        List<BusRouteStop> result = query.list();
        session.close();
        return result;
    }
   
   
}// end class
