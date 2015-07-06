package org.school.admin.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.InfrastructureCategoryItem;
import org.school.admin.model.SafetyCategory;
import org.school.admin.model.SafetyCategoryItem;
import org.school.admin.model.ActivityCategory;
import org.school.admin.model.ActivityCategoryItem;
import org.school.admin.model.InfrastructureCategory;
import org.school.admin.util.HibernateUtil;

public class FacilityImpl {
	
	/**
	 * Save SafetyCategory
	 * 
	 * @param safetyCategory
	 * @return String
	 */
	public ResponseMessage saveSafetyCategory(SafetyCategory safetyCategory){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (safetyCategory.getName() == null || safetyCategory.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter category name");
		} else {
			String hql = "from SafetyCategory where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", safetyCategory.getName());
			List<SafetyCategory> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Safety category  name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(safetyCategory);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update SafetyCategory
	 * 
	 * @param safetyCategory
	 * @return String
	 */
	public ResponseMessage updateSafetyCategory(SafetyCategory safetyCategory){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from SafetyCategory where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);		
		query.setParameter("name", safetyCategory.getName());
		query.setParameter("id", safetyCategory.getId());
		List<SafetyCategory> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Safety Category name already exists");
		} else {
			safetyCategory.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(safetyCategory);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Safety Category
	 * @author 
	 * @return List<SafetyCategory>
	 */
	public List<SafetyCategory> getSafetyCategory()
	{
		String hql = "from SafetyCategory";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SafetyCategory> result = query.list();
		System.out.println("SAFCAT=="+result.size());
		session.close();
		return result;
	}
	
	/**
	 * Get SafetyCategory by ID
	 * @author 
	 * @return List<SafetyCategory>
	 */
	public List<SafetyCategory> getSafetyCategoryById(int id)
	{
		String hql = "from SafetyCategory where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SafetyCategory> result = query.list();
		session.close();
		return result;
	}
	
	
    /**
     * Save Safety Category Item
     * 
     * @param safetyCategoryItem
     * @return String
     */
    public ResponseMessage saveSafetyCategoryItem(SafetyCategoryItem safetyCategoryItem){
        ResponseMessage response = new ResponseMessage();
        HibernateUtil hibernateUtil = new HibernateUtil();
        if (safetyCategoryItem.getName() == null || safetyCategoryItem.getName().trim().length() == 0) {
            response.setStatus(0);
            response.setMessage("Please enter item name");
        } else {
            String hql = "from SafetyCategoryItem where name = :name and safetyCategory.id = :cat_id";
            Session session = hibernateUtil.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("name", safetyCategoryItem.getName());
            query.setParameter("cat_id", safetyCategoryItem.getSafetyCategory().getId());
            List<SafetyCategoryItem> result = query.list();
            session.close();
            if (result.size() > 0) {
                response.setStatus(0);
                response.setMessage("SafetyCategoryItem name already exists");
            } else {
                Session newsession = hibernateUtil.openSession();
                newsession.beginTransaction();
                newsession.save(safetyCategoryItem);
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
     * @param safetyCategoryItem
     * @return json string
     */
    public ResponseMessage updateSafetyCategoryItem(SafetyCategoryItem safetyCategoryItem){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from SafetyCategoryItem where name = :name and safetyCategory.id = :safetycategory_id and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", safetyCategoryItem.getName());
		query.setParameter("safetycategory_id", safetyCategoryItem.getSafetyCategory().getId());
		query.setParameter("id", safetyCategoryItem.getId());
		List<SafetyCategoryItem> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Safety Category Item name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(safetyCategoryItem);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
    
    /**
     * Get All SafetyCategoryItem    
     * @return List<State>
     */
    public List<SafetyCategoryItem> getSafetyCategoryItemList()
    {
        String hql = "from SafetyCategoryItem";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        List<SafetyCategoryItem> result = query.list();
        session.close();
        return result;
    }
    
    /**
     * Get item by category id
     * @param int category
     * @return List<safetyCategoryItem>
     */
    public List<SafetyCategoryItem> getItemByCategoryId(int categoryId)
    {
        
        String hql = "from SafetyCategoryItem where safetyCategory.id = :categoryId";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("categoryId", categoryId);
        List<SafetyCategoryItem> result = query.list();
        session.close();
        System.out.println("Size1="+result.size());
        return result;
    }
    
    public List<SafetyCategoryItem> getSafetyCategoryItemById(int item_id)
    {
        String hql = "from SafetyCategoryItem where id = :item_id";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("item_id", item_id);
        List<SafetyCategoryItem> result = query.list();
        session.close();
        return result;
    }
	
	/***************** Activity Category ****************************/
    /**
	 * Save ActivityCategory
	 * 
	 * @param activityCategory
	 * @return String
	 */
	public ResponseMessage saveActivityCategory(ActivityCategory activityCategory){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (activityCategory.getName() == null || activityCategory.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter category name");
		} else {
			String hql = "from ActivityCategory where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", activityCategory.getName());
			List<ActivityCategory> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Activity category  name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(activityCategory);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update ActivityCategory
	 * 
	 * @param activityCategory
	 * @return String
	 */
	public ResponseMessage updateActivityCategory(ActivityCategory activityCategory){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from ActivityCategory where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);		
		query.setParameter("name", activityCategory.getName());
		query.setParameter("id", activityCategory.getId());
		List<ActivityCategory> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Activity Category name already exists");
		} else {
			activityCategory.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(activityCategory);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Activity Category
	 * @author 
	 * @return List<ActivityCategory>
	 */
	public List<ActivityCategory> getActivityCategory()
	{
		String hql = "from ActivityCategory";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<ActivityCategory> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get ActivityCategory by ID
	 * @author 
	 * @return List<ActivityCategory>
	 */
	public List<ActivityCategory> getActivityCategoryById(int id)
	{
		String hql = "from ActivityCategory where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ActivityCategory> result = query.list();
		session.close();
		return result;
	}
	
	
    /**
     * Save Activity Category Item
     * 
     * @param activityCategoryItem
     * @return String
     */
    public ResponseMessage saveActivityCategoryItem(ActivityCategoryItem activityCategoryItem){
        ResponseMessage response = new ResponseMessage();
        HibernateUtil hibernateUtil = new HibernateUtil();
        if (activityCategoryItem.getName() == null || activityCategoryItem.getName().trim().length() == 0) {
            response.setStatus(0);
            response.setMessage("Please enter item name");
        } else {
            String hql = "from ActivityCategoryItem where name = :name and activityCategory.id = :cat_id";
            Session session = hibernateUtil.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("name", activityCategoryItem.getName());
            query.setParameter("cat_id", activityCategoryItem.getActivityCategory().getId());
            List<ActivityCategoryItem> result = query.list();
            session.close();
            if (result.size() > 0) {
                response.setStatus(0);
                response.setMessage("ActivityCategoryItem name already exists");
            } else {
                Session newsession = hibernateUtil.openSession();
                newsession.beginTransaction();
                newsession.save(activityCategoryItem);
                newsession.getTransaction().commit();
                newsession.close();
                response.setStatus(1);
                response.setMessage("Success");
            }
        }
        return response;
    }
    /**
     * updateActivityCategoryItem
     * @param ActivityCategoryItem
     * @return json string
     */
    public ResponseMessage updateActivityCategoryItem(ActivityCategoryItem activityCategoryItem){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = " from ActivityCategoryItem where name = :name and activityCategory.id = :activitycategory_id and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", activityCategoryItem.getName());
		query.setParameter("activitycategory_id", activityCategoryItem.getActivityCategory().getId());
		query.setParameter("id", activityCategoryItem.getId());
		List<ActivityCategoryItem> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Activity category name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(activityCategoryItem);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
    
    /**
     * Get All ActivityCategoryItem    
     * @return List<ActivityCategoryItem>
     */
    public List<ActivityCategoryItem> getactivityCategoryItemList()
    {
        String hql = "from ActivityCategoryItem";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        List<ActivityCategoryItem> result = query.list();
        session.close();
        return result;
    }
    
    /**
     * Get item by category id
     * @param int category
     * @return List<safetyCategoryItem>
     */
    public List<ActivityCategoryItem> getActivityItemByCategoryId(int categoryId)
    {
        
        String hql = "from ActivityCategoryItem where activityCategory.id = :categoryId";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("categoryId", categoryId);
        List<ActivityCategoryItem> result = query.list();
        session.close();
        System.out.println("Size1="+result.size());
        return result;
    }
    
    public List<ActivityCategoryItem> getActivityCategoryItemById(int item_id)
    {
        String hql = "from ActivityCategoryItem where id = :item_id";
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("item_id", item_id);
        List<ActivityCategoryItem> result = query.list();
        session.close();
        return result;
    }
    /****************Activity***********************/
    
	/*************** Infrastructure *******************/
    
    /**
	 * Save Infrastructure Category
	 * 
	 * @param infrastructureCategory
	 * @return String
	 */
	public ResponseMessage saveInfrastructureCategory(InfrastructureCategory infrastructureCategory){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (infrastructureCategory.getName() == null || infrastructureCategory.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter category name");
		} else {
			String hql = "from InfrastructureCategory where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", infrastructureCategory.getName());
			List<InfrastructureCategory> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Infrastrutcure category name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(infrastructureCategory);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update InfrastructureCategory
	 * 
	 * @param infrastructureCategory
	 * @return String
	 */
	public ResponseMessage updateInfrastructureCategory(InfrastructureCategory infrastructureCategory){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from InfrastructureCategory where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);		
		query.setParameter("name", infrastructureCategory.getName());
		query.setParameter("id", infrastructureCategory.getId());
		List<InfrastructureCategory> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Infrastructure Category already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(infrastructureCategory);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Infrastructure Category
	 * @author 
	 * @return List<InfrastructureCategory>
	 */
	public List<InfrastructureCategory> getInfrastructureCategory()
	{
		String hql = "from InfrastructureCategory";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<InfrastructureCategory> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get InfrastructureCategory by ID
	 * @author 
	 * @return List<InfrastructureCategory>
	 */
	public List<InfrastructureCategory> getInfrastructureCategoryById(int id)
	{
		String hql = "from InfrastructureCategory where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<InfrastructureCategory> result = query.list();
		session.close();
		return result;
	}
    
    
    /**
	 * Save Infrastructure CategoryItem
	 * 
	 * @param infrastructureCategoryItem
	 * @return String
	 */
	public ResponseMessage saveInfrastructureCategoryItem(InfrastructureCategoryItem infrastructureCategoryItem){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (infrastructureCategoryItem.getName() == null || infrastructureCategoryItem.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter category item name");
		} else {
			String hql = "from InfrastructureCategoryItem where name = :name and infrastructureCategory.id = :cat_id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", infrastructureCategoryItem.getName());
			query.setParameter("cat_id", infrastructureCategoryItem.getInfrastructureCategory().getId());
			List<InfrastructureCategoryItem> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Infrastrutcure category item name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(infrastructureCategoryItem);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update InfrastructureCategoryItem
	 * 
	 * @param infrastructureCategoryItem
	 * @return String
	 */
	public ResponseMessage updateInfrastructureCategoryItem(InfrastructureCategoryItem infrastructureCategoryItem){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from InfrastructureCategoryItem where name = :name and infrastructureCategory.id = :cat_id and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);		
		query.setParameter("name", infrastructureCategoryItem.getName());
		query.setParameter("cat_id", infrastructureCategoryItem.getInfrastructureCategory().getId());
		query.setParameter("id", infrastructureCategoryItem.getId());
		List<InfrastructureCategoryItem> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Infrastructure Category Item already exists");
		} else {
			infrastructureCategoryItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(infrastructureCategoryItem);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Infrastructure Category Item by act id
	 * @author 
	 * @return List<InfrastructureCategoryItem>
	 */
	public List<InfrastructureCategoryItem> getInfrastructureCategoryItem(int cat_id)
	{
		String hql = "from InfrastructureCategoryItem where infrastructureCategory.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", cat_id);
		List<InfrastructureCategoryItem> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get InfrastructureCategoryItem by ID
	 * @author 
	 * @return List<InfrastructureCategoryItem>
	 */
	public List<InfrastructureCategoryItem> getInfrastructureCategoryItemById(int id)
	{
		String hql = "from InfrastructureCategoryItem where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<InfrastructureCategoryItem> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get InfrastructureCategoryItem List
	 * @author 
	 * @return List<InfrastructureCategoryItem>
	 */
	public List<InfrastructureCategoryItem> getInfrastructureCategoryItemList()
	{
		String hql = "from InfrastructureCategoryItem";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<InfrastructureCategoryItem> result = query.list();
		session.close();
		return result;
	}
	
	
    /***************Infrastructure *******************/
   
}
