package org.school.admin.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.Accessories;
import org.school.admin.model.AdminUser;
import org.school.admin.model.AdminUserRole;
import org.school.admin.model.AreaUnit;
import org.school.admin.model.BloodGroup;
import org.school.admin.model.BoardType;
import org.school.admin.model.BusInfo;
import org.school.admin.model.BusStop;
import org.school.admin.model.Cast;
import org.school.admin.model.CertificateType;
import org.school.admin.model.EducationType;
import org.school.admin.model.ExamType;
import org.school.admin.model.FacultyType;
import org.school.admin.model.FeeType;
import org.school.admin.model.LeaveType;
import org.school.admin.model.MediumType;
import org.school.admin.model.NotificationType;
import org.school.admin.model.OccupationType;
import org.school.admin.model.PaymentMode;
import org.school.admin.model.RatingCategoryType;
import org.school.admin.model.Role;
import org.school.admin.model.SchoolCategoryType;
import org.school.admin.model.SchoolClassificationType;
import org.school.admin.model.SchoolType;
import org.school.admin.model.SecondaryRole;
import org.school.admin.model.StandardAlias;
import org.school.admin.model.StandardType;
import org.school.admin.model.StreamType;
import org.school.admin.model.Subject;
import org.school.admin.model.TeachingApproachType;
import org.school.admin.util.HibernateUtil;

public class SettingsImpl {
	
	/*------------Pankaj Naik ----------------*/
	/**
	 * Save AdminUserRole
	 * 
	 * @param adminUserRole
	 * @return String
	 */
	public ResponseMessage saveAdminUserRole(AdminUserRole adminUserRole){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (adminUserRole.getRoleName() == null || adminUserRole.getRoleName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter admin user role name");
		} else {
			String hql = "from AdminUserRole where roleName = :roleName";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("roleName", adminUserRole.getRoleName());
			List<BusStop> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Admin User Role name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(adminUserRole);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Saved Success");
			}
		}
		return response;
	}
	
/*-------------------------------------------------------------------*//*-------------------Pankaj Naik------------*/
	/**
	 * Update AdminUserRole
	 * 
	 * @param adminUserRole
	 * @return String
	 */
	public ResponseMessage updateAdminUserRole(AdminUserRole adminUserRole){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (adminUserRole.getRoleName() == null || adminUserRole.getRoleName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter admin user role name");
		} else {
			String hql = "from AdminUserRole where roleName = :roleName and id != :id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("roleName", adminUserRole.getRoleName());
			query.setParameter("id", adminUserRole.getId());
			List<AdminUserRole> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Admin User role name already exists");
			} else {
		        Session newsession = hibernateUtil.openSession();
		        newsession.beginTransaction();
		        newsession.update(adminUserRole);
		        newsession.getTransaction().commit();
		        newsession.close();
		        response.setStatus(1);
				response.setMessage("Updated Success");
			}
		}
        return response;
    }
	
	/**
	 * Update AdminUserRole
	 * 
	 * @param adminUserRole
	 * @return String
	 */
	public ResponseMessage updateAdminUser(AdminUser adminUser){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (adminUser.getName() == null || adminUser.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter admin user name");
		} 
		else if (adminUser.getEmail() == null || adminUser.getEmail().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter admin user email");
		}
		else if (adminUser.getMobile() == null || adminUser.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter admin user mobile");
		}
		else if (adminUser.getName() == null || adminUser.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter admin user name");
		}
		else {
			String hql = "from AdminUser where name = :name and id != :id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", adminUser.getName());
			query.setParameter("id", adminUser.getId());
			List<AdminUser> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Admin User name already exists");
			} else {
		        Session newsession = hibernateUtil.openSession();
		        newsession.beginTransaction();
		        newsession.update(adminUser);
		        newsession.getTransaction().commit();
		        newsession.close();
		        response.setStatus(1);
				response.setMessage("Updated Success");
			}
		}
        return response;
    }
	/*------------------------Stream Type---------------*/
	/**
	 * Get All StreamType
	 * @author PANKAJ
	 * @return List<StreamType>
	 */
	public List<StreamType> getAllStreamType(){
		String hql = "from StreamType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<StreamType> result = query.list();
		session.close();
		return result;
	}
	
	public ResponseMessage saveStreamType(StreamType streamType)
	{
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (streamType.getTitle() == null || streamType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter stream type name");
		} else {
			String hql = "from StreamType where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", streamType.getTitle());
			List<StandardType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Stream type  already exists");
			} else {
				streamType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(streamType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Save Success");
			}
		}
		return response; 
		
	}
	/**
	 * Update StreamType
	 * 
	 * @param StreamType
	 * @return String
	 */
	public ResponseMessage updateStreamType(StreamType streamType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from StreamType where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", streamType.getTitle());
		query.setParameter("id", streamType.getId());
		List<StreamType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("stream Type name already exists");
		} else {
			streamType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(streamType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	/**
	 * Get StreamType by ID
	 * @author Pankaj Naik
	 * @return List<StreamType>
	 */
	public List<StreamType> getStreamTypeById(Short id)
	{
		String hql = "from StreamType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<StreamType> result = query.list();
		session.close();
		return result;
	}
	public String getStreamTypeNameById(Short id){
		String hql = "from StreamType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<StreamType> result = query.list();
		session.close();
		return result.get(0).getTitle();
	}
	/*------------------------------------------------------------------------*/
	/*------------------------Pankaj Naik--------------*/
	/**
	 * Get AdminUserRole by ID
	 * @author pankaj
	 * @return List<AdminUserRole>
	 */
	public List<AdminUserRole> getAdminUserRoleById(int id)
	{
		String hql = "from AdminUserRole where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<AdminUserRole> result = query.list();
		session.close();
		return result;
	}
	 
	/**
	 * Get AdminUserRole by ID
	 * @author pankaj
	 * @return List<AdminUserRole>
	 */
	public List<AdminUser> getAdminUserById(int id)
	{
		String hql = "from AdminUser where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<AdminUser> result = query.list();
		session.close();
		return result;
	}
	
	public ResponseMessage saveBloodGroup(BloodGroup bloodGroup){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (bloodGroup.getName() == null || bloodGroup.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter blood group name");
		} else {
			String hql = "from BloodGroup where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", bloodGroup.getName());
			List<BloodGroup> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Blood Group name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(bloodGroup);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update BloodGroup
	 * 
	 * @param bloodGroup
	 * @return String
	 */
	public ResponseMessage updateBloodGroup(BloodGroup bloodGroup){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from BloodGroup where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", bloodGroup.getName());
		query.setParameter("id", bloodGroup.getId());
		List<BloodGroup> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Blood Group name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(bloodGroup);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	
	public ResponseMessage saveCast(Cast cast){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (cast.getName() == null || cast.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter cast name");
		} else {
			String hql = "from Cast where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", cast.getName());
			List<Cast> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("cast name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(cast);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update Cast
	 * 
	 * @param cast
	 * @return String
	 */
	public ResponseMessage updateCast(Cast cast){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from Cast where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", cast.getName());
		query.setParameter("id", cast.getId());
		List<BloodGroup> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Cast name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(cast);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	/*--------------------------------------------------------*/
	
	
	/**
	 * Save Accessories
	 * 
	 * @param accessories
	 * @return String
	 */
	public ResponseMessage saveAccessory(Accessories accessories){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (accessories.getName() == null || accessories.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter accessory name");
		} else {
			String hql = "from Accessories where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", accessories.getName());
			List<Accessories> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Accessories name already exists");
			} else {
				accessories.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(accessories);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update Accessories
	 * 
	 * @param accessories
	 * @return String
	 */
	public ResponseMessage updateAccessory(Accessories accessories){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from Accessories where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", accessories.getName());
		query.setParameter("id", accessories.getId());
		List<Accessories> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Accessories name already exists");
		} else {
			accessories.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(accessories);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Accessories
	 * @author pradeep
	 * @return List<Accessories>
	 */
	public List<Accessories> getAccessories()
	{
		String hql = "from Accessories";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Accessories> result = query.list();
		session.close();
		return result;
	}
	/*---------------------PANKAJ NAIK ---------------------------*/
	/**
	 * Get Accessory Name by id
	 * @author PANKAJ 
	 * @return String
	 */
	public String getAccessoryNameById(int id)
	{
		String hql = "from Accessories where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Accessories> result = query.list();
		session.close();
		return result.get(0).getName();
	}
	/**
	 * Get all standard alias names
	 * @author PANKAJ
	 * @return List<StandardAilas>
	 */
	public List<StandardAlias> getStandardAlias()
	{
		String hql = "from StandardAlias";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<StandardAlias> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get All Blood group
	 * @author PANKAJ 
	 * @return List<BloodGroup>
	 */
	public List<BloodGroup> getBloodGroup()
	{
		String hql = "from BloodGroup";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<BloodGroup> bloodGroupList = query.list();
		session.close();
		return bloodGroupList;
		
	}
	
	/**
	 * Get BloodGroup by ID
	 * @author PANKAJ
	 * @return List<BloodGroup>
	 */
	public List<BloodGroup> getBloodGroupById(Short id)
	{
		String hql = "from BloodGroup where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<BloodGroup> result = query.list();
		session.close();
		return result;
	}
	
	
	/**
	 * Get All Cast
	 * @author PANKAJ 
	 * @return List<Cast>
	 */
	public List<Cast> getCast()
	{
		String hql = "from Cast";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Cast> castList = query.list();
		session.close();
		return castList;
		
	}
	
	/**
	 * Get Cast by ID
	 * @author PANKAJ
	 * @return List<Cast>
	 */
	public List<Cast> getCastById(Short id)
	{
		String hql = "from Cast where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Cast> result = query.list();
		session.close();
		return result;
	}
	
	/*-----------------------------------------------------------*/
	/**
	 * Get Accessory by ID
	 * @author pradeep
	 * @return List<Accessories>
	 */
	public List<Accessories> getAccessoryById(int id)
	{
		String hql = "from Accessories where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Accessories> result = query.list();
		session.close();
		return result;
	}
	
	
	/**
	 * Save AreaUnit
	 * 
	 * @param areaunit
	 * @return String
	 */
	public ResponseMessage saveAreaUnit(AreaUnit areaunit){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (areaunit.getName() == null || areaunit.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter AreaUnit name");
		} else {
			String hql = "from AreaUnit where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", areaunit.getName());
			List<AreaUnit> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("AreaUnit name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(areaunit);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update AreaUnit
	 * 
	 * @param areaunit
	 * @return String
	 */
	public ResponseMessage updateAreaUnit(AreaUnit areaunit){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from AreaUnit where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", areaunit.getName());
		query.setParameter("id", areaunit.getId());
		List<AreaUnit> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("AreaUnit name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(areaunit);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All AreaUnits
	 * @author pradeep
	 * @return List<AreaUnit>
	 */
	public List<AreaUnit> getAreaUnits()
	{
		String hql = "from AreaUnit";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<AreaUnit> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get AreaUnit by ID
	 * @author pradeep
	 * @return List<AreaUnit>
	 */
	public List<AreaUnit> getAreaUnitById(int id)
	{
		String hql = "from AreaUnit where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<AreaUnit> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Save Board Type
	 * 
	 * @param boardtype
	 * @return String
	 */
	public ResponseMessage saveBoardType(BoardType boardtype){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (boardtype.getBoardName() == null || boardtype.getBoardName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter board type name");
		} else {
			String hql = "from BoardType where boardName = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", boardtype.getBoardName());
			List<BoardType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Board Type name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(boardtype);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update BoardType
	 * 
	 * @param boardtype
	 * @return String
	 */
	public ResponseMessage updateBoardType(BoardType boardtype){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from BoardType where boardName = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", boardtype.getBoardName());
		query.setParameter("id", boardtype.getId());
		List<BoardType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("BoardType name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(boardtype);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All BoardTypes
	 * @author pradeep
	 * @return List<BoardType>
	 */
	public List<BoardType> getBoardTypes()
	{
		String hql = "from BoardType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<BoardType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get BoardType by ID
	 * @author pradeep
	 * @return List<BoardType>
	 */
	public List<BoardType> getBoardTypeById(Short id)
	{
		String hql = "from BoardType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<BoardType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Save Bus 
	 * 
	 * @param busInfo
	 * @return String
	 */
	public ResponseMessage saveBusInfo(BusInfo busInfo){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (busInfo.getVehicleNo() == null || busInfo.getVehicleNo().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter vehicle number");
		}else if (busInfo.getBusNumber() == null || busInfo.getBusNumber().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter bus number");
		}else if (busInfo.getBusDriverName() == null || busInfo.getBusDriverName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter bus driver name");
		}else if (busInfo.getContactNo() == null || busInfo.getContactNo().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter contact number");
		}else if (busInfo.getTitle() == null || busInfo.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter title");
		} else {
			String hql = "from BusInfo where vehicleNo = :vehicle_no";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("vehicle_no", busInfo.getVehicleNo());
			List<BusStop> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Vehicle number already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(busInfo);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update BusInfo
	 * 
	 * @param busInfo
	 * @return String
	 */
	public ResponseMessage updateBusInfo(BusInfo busInfo){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (busInfo.getVehicleNo() == null || busInfo.getVehicleNo().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter vehicle number");
		}else if (busInfo.getBusNumber() == null || busInfo.getBusNumber().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter bus number");
		}else if (busInfo.getBusDriverName() == null || busInfo.getBusDriverName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter bus driver name");
		}else if (busInfo.getContactNo() == null || busInfo.getContactNo().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter contact number");
		}else if (busInfo.getTitle() == null || busInfo.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter title");
		} else {
			String hql = "from BusInfo where vehicleNo = :vehicleNo and id != :id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("vehicleNo", busInfo.getVehicleNo());
			query.setParameter("id", busInfo.getId());
			List<BusStop> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Bus name already exists");
			} else {
		        Session newsession = hibernateUtil.openSession();
		        newsession.beginTransaction();
		        newsession.update(busInfo);
		        newsession.getTransaction().commit();
		        newsession.close();
		        response.setStatus(1);
				response.setMessage("Success");
			}
		}
        return response;
    }
	
	/**
	 * Get All BusInfo
	 * @author pradeep
	 * @return List<BusInfo>
	 */
	public List<BusInfo> getAllBusInfo()
	{
		String hql = "from BusInfo";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<BusInfo> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get BusInfo by ID
	 * @author pradeep
	 * @return List<BusInfo>
	 */
	public List<BusInfo> getBusInfoById(int id)
	{
		String hql = "from BusInfo where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<BusInfo> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Save Bus Stop
	 * 
	 * @param boardtype
	 * @return String
	 */
	public ResponseMessage saveBusStop(BusStop busStop){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (busStop.getName() == null || busStop.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter stop name");
		}else if (busStop.getPickupLatitude() == null || busStop.getPickupLatitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter pickup latitude");
		}else if (busStop.getPickupLongitude() == null || busStop.getPickupLongitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter pickup longitude");
		}else if (busStop.getDropLatitude() == null || busStop.getDropLatitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter drop latitude");
		}else if (busStop.getDropLongitude() == null || busStop.getDropLongitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter drop longitude");
		} else {
			String hql = "from BusStop where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", busStop.getName());
			List<BusStop> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Stop name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(busStop);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update BusStop
	 * 
	 * @param busStop
	 * @return String
	 */
	public ResponseMessage updateBusStop(BusStop busStop){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (busStop.getName() == null || busStop.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter stop name");
		}else if (busStop.getPickupLatitude() == null || busStop.getPickupLatitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter pickup latitude");
		}else if (busStop.getPickupLongitude() == null || busStop.getPickupLongitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter pickup longitude");
		}else if (busStop.getDropLatitude() == null || busStop.getDropLatitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter drop latitude");
		}else if (busStop.getDropLongitude() == null || busStop.getDropLongitude().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter drop longitude");
		} else {
			String hql = "from BusStop where name = :name and id != :id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", busStop.getName());
			query.setParameter("id", busStop.getId());
			List<BusStop> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Bus stop already exists");
			} else {
		        Session newsession = hibernateUtil.openSession();
		        newsession.beginTransaction();
		        newsession.update(busStop);
		        newsession.getTransaction().commit();
		        newsession.close();
		        response.setStatus(1);
				response.setMessage("Success");
			}
		}
        return response;
    }
	
	/**
	 * Get All BusStop
	 * @author pradeep
	 * @return List<BusStop>
	 */
	public List<BusStop> getAllBusStop()
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
	 * Get BusStop by ID
	 * @author pradeep
	 * @return List<BusStop>
	 */
	public List<BusStop> getBusStopById(int id)
	{
		String hql = "from BusStop where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<BusStop> result = query.list();
		session.close();
		return result;
	}
	
	
/* ******************************* Certificate Type ************************* */
	
	/**
	 * Save Certificate Type
	 * 
	 * @param certificateType
	 * @return String
	 */
	public ResponseMessage saveCertificateType(CertificateType certificateType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (certificateType.getTitle() == null || certificateType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter certificate title");
		} else {
			String hql = "from CertificateType where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", certificateType.getTitle());
			List<CertificateType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("certificate title already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(certificateType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update certificateType
	 * 
	 * @param certificateType
	 * @return String
	 */
	public ResponseMessage updateCertificate(CertificateType certificateType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (certificateType.getTitle() == null || certificateType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter certificate title");
		} else {
			String hql = "from CertificateType where title = :title and id != :id";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", certificateType.getTitle());
			query.setParameter("id", certificateType.getId());
			List<CertificateType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Certificate title already exists");
			} else {
		        Session newsession = hibernateUtil.openSession();
		        newsession.beginTransaction();
		        newsession.update(certificateType);
		        newsession.getTransaction().commit();
		        newsession.close();
		        response.setStatus(1);
				response.setMessage("Success");
			}
		}
        return response;
    }
	
	/**
	 * Get All certificateType
	 * @author pradeep
	 * @return List<CertificateType>
	 */
	public List<CertificateType> getAllCertificateType()
	{
		String hql = "from CertificateType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<CertificateType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get CertificateType by ID
	 * @author pradeep
	 * @return List<CertificateType>
	 */
	public List<CertificateType> getCertificateTypeById(int id)
	{
		String hql = "from CertificateType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<CertificateType> result = query.list();
		session.close();
		return result;
	}
	
	/* ************************** Shinee ************************ */
	
	/**
	 * Save Teaching Approach
	 * 
	 * @param teaching approach
	 * @return String
	 */
	public ResponseMessage saveTeachingApproachType(TeachingApproachType teachingApproachType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (teachingApproachType.getName() == null || teachingApproachType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter teaching approach name");
		} else {
			String hql = "from TeachingApproachType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", teachingApproachType.getName());
			List<TeachingApproachType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Teaching approach type already exists");
			} else {
				teachingApproachType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(teachingApproachType);
				newsession.getTransaction().commit();
				newsession.close();				
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update Teaching Approach Type
	 * 
	 * @param teachingapproachtype
	 * @return String
	 */
	public ResponseMessage updateTeachingApproachType(TeachingApproachType teachingApproachType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from TeachingApproachType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", teachingApproachType.getName());
		query.setParameter("id", teachingApproachType.getId());
		List<Accessories> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("TeachingApproachType name already exists");
		} else {
			teachingApproachType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(teachingApproachType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All TeachingApproachType
	 * @author 
	 * @return List<TeachingApproachType>
	 */
	public List<TeachingApproachType> getTeachingApproachType()
	{
		String hql = "from TeachingApproachType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<TeachingApproachType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get Teaching Approach by ID
	 * @author 
	 * @return List<TeachingApproachType>
	 */
	public List<TeachingApproachType> getTeachingApproachTypeById(short id)
	{
		String hql = "from TeachingApproachType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<TeachingApproachType> result = query.list();
		System.out.println("Output="+result.size());
		session.close();
		return result;
	}
	public String getTeachingApproachTypeNameById(short id)
	{
		String hql = "from TeachingApproachType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<TeachingApproachType> result = query.list();
		System.out.println("Output="+result.size());
		session.close();
		return result.get(0).getName();
	}
	/**
	 * Save SchoolType
	 * 
	 * @param schoolType
	 * @return String
	 */
	public ResponseMessage saveSchoolType(SchoolType schoolType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (schoolType.getName() == null || schoolType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter school type name");
		} else {
			String hql = "from SchoolType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", schoolType.getName());
			List<SchoolType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("School Type name already exists");
			} else {
				schoolType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(schoolType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update SchoolType
	 * 
	 * @param schoolType
	 * @return String
	 */
	public ResponseMessage updateSchoolType(SchoolType schoolType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from SchoolType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", schoolType.getName());
		query.setParameter("id", schoolType.getId());
		List<SchoolType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("SchoolType name already exists");
		} else {
			schoolType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(schoolType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All School Type
	 * @author 
	 * @return List<SchoolType>
	 */
	public List<SchoolType> getSchoolType()
	{
		String hql = "from SchoolType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SchoolType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get SchoolType by ID
	 * @author 
	 * @return List<SchoolType>
	 */
	public List<SchoolType> getSchoolTypeById(Short id)
	{
		String hql = "from SchoolType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SchoolType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Save Standard Type
	 * 
	 * @param standardtype
	 * @return String
	 */
	public ResponseMessage saveStandardType(StandardType standardType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (standardType.getName() == null || standardType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter standard type name");
		} else {
			String hql = "from SchoolType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", standardType.getName());
			List<StandardType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Standard type  already exists");
			} else {
				standardType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(standardType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update StandardType
	 * 
	 * @param standardType
	 * @return String
	 */
	public ResponseMessage updateStandardType(StandardType standardType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from StandardType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", standardType.getName());
		query.setParameter("id", standardType.getId());
		List<StandardType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Standard Type name already exists");
		} else {
			standardType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(standardType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All StandardType
	 * @author 
	 * @return List<StandardType>
	 */
	public List<StandardType> getStandardType()
	{
		String hql = "from StandardType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<StandardType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get Standard Type by ID
	 * @author 
	 * @return List<StandardType>
	 */
	public List<StandardType> getStandardTypeById(short id)
	{
		String hql = "from StandardType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<StandardType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Save Subject
	 * 
	 * @param subject
	 * @return String
	 */
	public ResponseMessage saveSubject(Subject subject){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (subject.getName() == null || subject.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter subject title");
		} else {
			String hql = "from Subject where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", subject.getName());
			List<Subject> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Subject already exists");
			} else {
				subject.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(subject);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update Subject
	 * 
	 * @param subject
	 * @return String
	 */
	public ResponseMessage updateSubject(Subject subject){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from Subject where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", subject.getName());
		query.setParameter("id", subject.getId());
		List<Subject> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Subject name already exists");
		} else {
			subject.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(subject);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Subject
	 * @author 
	 * @return List<Subject>
	 */
	public List<Subject> getSubject()
	{
		String hql = "from Subject";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Subject> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get Subject name by id
	 * @author Pankaj
	 * @return String
	 */
	public String getSubjectNameById(int id)
	{
		String hql = "from Subject where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Subject> result = query.list();
		session.close();
		return result.get(0).getName();
	}
	
	/**
	 * Get Subject by ID
	 * @author 
	 * @return List<Subject>
	 */
	public List<Subject> getSubjectById(int id)
	{
		String hql = "from Subject where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Subject> result = query.list();
		session.close();
		return result;
	}
	
	/* ******************************* Education Type **************************** */
	
	/**
	 * Save EducationType
	 * 
	 * @param educationType
	 * @return String
	 */
	public ResponseMessage saveEducationType(EducationType educationType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (educationType.getTitle() == null || educationType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter education type title");
		} else {
			String hql = "from EducationType where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", educationType.getTitle());
			List<EducationType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Title already exists");
			} else {
				educationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(educationType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update EducationType
	 * 
	 * @param educationType
	 * @return String
	 */
	public ResponseMessage updateEducationType(EducationType educationType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from EducationType where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", educationType.getTitle());
		query.setParameter("id", educationType.getId());
		List<EducationType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Title name already exists");
		} else {
			educationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(educationType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All EducationType
	 * @author 
	 * @return List<EducationType>
	 */
	public List<EducationType> getAllEducationType()
	{
		String hql = "from EducationType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<EducationType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get EducationType by ID
	 * @author 
	 * @return List<EducationType>
	 */
	public List<EducationType> getEducationTypeById(Short id)
	{
		String hql = "from EducationType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<EducationType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Exam Type **************************** */
	
	/**
	 * Save ExamType
	 * 
	 * @param examType
	 * @return String
	 */
	public ResponseMessage saveExamType(ExamType examType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (examType.getTitle() == null || examType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter exam type title");
		} else {
			String hql = "from ExamType where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", examType.getTitle());
			List<ExamType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Title already exists");
			} else {
				examType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(examType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update ExamType
	 * 
	 * @param examType
	 * @return String
	 */
	public ResponseMessage updateExamType(ExamType examType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from ExamType where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", examType.getTitle());
		query.setParameter("id", examType.getId());
		List<ExamType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Title name already exists");
		} else {
			examType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(examType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All ExamType
	 * @author 
	 * @return List<ExamType>
	 */
	public List<ExamType> getAllExamType()
	{
		String hql = "from ExamType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<ExamType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get ExamType by ID
	 * @author 
	 * @return List<ExamType>
	 */
	public List<ExamType> getExamTypeById(Short id)
	{
		String hql = "from ExamType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ExamType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* faculty Type **************************** */
	
	/**
	 * Save FacultyType
	 * 
	 * @param facultyType
	 * @return String
	 */
	public ResponseMessage saveFacultyType(FacultyType facultyType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (facultyType.getName() == null || facultyType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter faculty type name");
		} else {
			String hql = "from FacultyType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", facultyType.getName());
			List<FacultyType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Name already exists");
			} else {
				facultyType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(facultyType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update FacultyType
	 * 
	 * @param facultyType
	 * @return String
	 */
	public ResponseMessage updateFacultyType(FacultyType facultyType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from FacultyType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", facultyType.getName());
		query.setParameter("id", facultyType.getId());
		List<FacultyType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Faculty Type name already exists");
		} else {
			facultyType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(facultyType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All FacultyType
	 * @author 
	 * @return List<FacultyType>
	 */
	public List<FacultyType> getAllFacultyType()
	{
		String hql = "from FacultyType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<FacultyType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get FacultyType by ID
	 * @author 
	 * @return List<FacultyType>
	 */
	public List<FacultyType> getFacultyTypeById(Short id)
	{
		String hql = "from FacultyType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<FacultyType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Fee Type **************************** */
	
	/**
	 * Save FeeType
	 * 
	 * @param feeType
	 * @return String
	 */
	public ResponseMessage saveFeeType(FeeType feeType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (feeType.getTitle() == null || feeType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter fee type title");
		} else {
			String hql = "from FeeType where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", feeType.getTitle());
			List<FeeType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Name already exists");
			} else {
				feeType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(feeType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update FeeType
	 * 
	 * @param feeType
	 * @return String
	 */
	public ResponseMessage updateFeeType(FeeType feeType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from FeeType where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", feeType.getTitle());
		query.setParameter("id", feeType.getId());
		List<FeeType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Fee Type name already exists");
		} else {
			feeType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(feeType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All FeeType
	 * @author 
	 * @return List<FeeType>
	 */
	public List<FeeType> getAllFeeType()
	{
		String hql = "from FeeType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<FeeType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get FeeType by ID
	 * @author 
	 * @return List<FeeType>
	 */
	public List<FeeType> getFeeTypeById(Short id)
	{
		String hql = "from FeeType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<FeeType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Leave Type **************************** */
	
	/**
	 * Save LeaveType
	 * 
	 * @param leaveType
	 * @return String
	 */
	public ResponseMessage saveLeaveType(LeaveType leaveType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (leaveType.getName() == null || leaveType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter leave type name");
		} else {
			String hql = "from LeaveType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", leaveType.getName());
			List<LeaveType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Name already exists");
			} else {
				leaveType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(leaveType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update LeaveType
	 * 
	 * @param leaveType
	 * @return String
	 */
	public ResponseMessage updateLeaveType(LeaveType leaveType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from LeaveType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", leaveType.getName());
		query.setParameter("id", leaveType.getId());
		List<LeaveType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Leave Type name already exists");
		} else {
			leaveType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(leaveType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All LeaveType
	 * @author 
	 * @return List<LeaveType>
	 */
	public List<LeaveType> getAllLeaveType()
	{
		String hql = "from LeaveType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<LeaveType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get LeaveType by ID
	 * @author 
	 * @return List<LeaveType>
	 */
	public List<LeaveType> getLeaveTypeById(Short id)
	{
		String hql = "from LeaveType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<LeaveType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Medium Type **************************** */
	
	/**
	 * Save MediumType
	 * 
	 * @param mediumType
	 * @return String
	 */
	public ResponseMessage saveMediumType(MediumType mediumType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (mediumType.getTitle() == null || mediumType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter medium type name");
		} else {
			String hql = "from MediumType where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", mediumType.getTitle());
			List<MediumType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Name already exists");
			} else {
				mediumType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(mediumType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update MediumType
	 * 
	 * @param mediumType
	 * @return String
	 */
	public ResponseMessage updateMediumType(MediumType mediumType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from MediumType where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", mediumType.getTitle());
		query.setParameter("id", mediumType.getId());
		List<MediumType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Medium Type name already exists");
		} else {
			mediumType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(mediumType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All MediumType
	 * @author 
	 * @return List<MediumType>
	 */
	public List<MediumType> getAllMediumType()
	{
		String hql = "from MediumType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<MediumType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get MediumType by ID
	 * @author 
	 * @return List<MediumType>
	 */
	public List<MediumType> getMediumTypeById(Short id)
	{
		String hql = "from MediumType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<MediumType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Notification Type **************************** */
	
	/**
	 * Save NotificationType
	 * 
	 * @param notificationType
	 * @return String
	 */
	public ResponseMessage saveNotificationType(NotificationType notificationType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (notificationType.getName() == null || notificationType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter notification type name");
		} else {
			String hql = "from NotificationType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", notificationType.getName());
			List<NotificationType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Name already exists");
			} else {
				notificationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(notificationType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update NotificationType
	 * 
	 * @param notificationType
	 * @return String
	 */
	public ResponseMessage updateNotificationType(NotificationType notificationType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from NotificationType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", notificationType.getName());
		query.setParameter("id", notificationType.getId());
		List<NotificationType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Notification Type name already exists");
		} else {
			notificationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(notificationType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All NotificationType
	 * @author 
	 * @return List<NotificationType>
	 */
	public List<NotificationType> getAllNotificationType()
	{
		String hql = "from NotificationType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<NotificationType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get NotificationType by ID
	 * @author 
	 * @return List<NotificationType>
	 */
	public List<NotificationType> getNotificationTypeById(Short id)
	{
		String hql = "from NotificationType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<NotificationType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Occupation Type **************************** */
	
	/**
	 * Save OccupationType
	 * 
	 * @param occupationType
	 * @return String
	 */
	public ResponseMessage saveOccupationType(OccupationType occupationType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (occupationType.getTitle() == null || occupationType.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter occupation type name");
		} else {
			String hql = "from OccupationType where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", occupationType.getTitle());
			List<OccupationType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Name already exists");
			} else {
				occupationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(occupationType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update OccupationType
	 * 
	 * @param occupationType
	 * @return String
	 */
	public ResponseMessage updateOccupationType(OccupationType occupationType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from OccupationType where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", occupationType.getTitle());
		query.setParameter("id", occupationType.getId());
		List<OccupationType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Occupation Type name already exists");
		} else {
			occupationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(occupationType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All OccupationType
	 * @author 
	 * @return List<OccupationType>
	 */
	public List<OccupationType> getAllOccupationType()
	{
		String hql = "from OccupationType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<OccupationType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get OccupationType by ID
	 * @author 
	 * @return List<OccupationType>
	 */
	public List<OccupationType> getOccupationTypeById(Short id)
	{
		String hql = "from OccupationType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<OccupationType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Payment Mode **************************** */
	
	/**
	 * Save PaymentMode
	 * 
	 * @param paymentMode
	 * @return String
	 */
	public ResponseMessage savePaymentMode(PaymentMode paymentMode){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (paymentMode.getTitle() == null || paymentMode.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter payment mode");
		} else {
			String hql = "from PaymentMode where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", paymentMode.getTitle());
			List<PaymentMode> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Payment mode already exists");
			} else {
				paymentMode.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(paymentMode);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update PaymentMode
	 * 
	 * @param paymentMode
	 * @return String
	 */
	public ResponseMessage updatePaymentMode(PaymentMode paymentMode){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from PaymentMode where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", paymentMode.getTitle());
		query.setParameter("id", paymentMode.getId());
		List<PaymentMode> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Payment mode already exists");
		} else {
			paymentMode.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(paymentMode);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All PaymentMode
	 * @author 
	 * @return List<PaymentMode>
	 */
	public List<PaymentMode> getAllPaymentMode()
	{
		String hql = "from PaymentMode";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<PaymentMode> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get PaymentMode by ID
	 * @author 
	 * @return List<PaymentMode>
	 */
	public List<PaymentMode> getPaymentModeById(Short id)
	{
		String hql = "from PaymentMode where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<PaymentMode> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Rating Category Type **************************** */
	
	/**
	 * Save RatingCategoryType
	 * 
	 * @param ratingCategoryType
	 * @return String
	 */
	public ResponseMessage saveRatingCategoryType(RatingCategoryType ratingCategoryType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (ratingCategoryType.getCategoryName() == null || ratingCategoryType.getCategoryName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter rating category name");
		} else {
			String hql = "from RatingCategoryType where categoryName = :categoryName";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("categoryName", ratingCategoryType.getCategoryName());
			List<RatingCategoryType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Rating category already exists");
			} else {
				ratingCategoryType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(ratingCategoryType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update RatingCategoryType
	 * 
	 * @param ratingCategoryType
	 * @return String
	 */
	public ResponseMessage updateRatingCategoryType(RatingCategoryType ratingCategoryType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from RatingCategoryType where categoryName = :categoryName and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("categoryName", ratingCategoryType.getCategoryName());
		query.setParameter("id", ratingCategoryType.getId());
		List<RatingCategoryType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Rating category already exists");
		} else {
			if(ratingCategoryType.getImage() == ""){
				List<RatingCategoryType> image =getRatingCatImageById(ratingCategoryType);
				ratingCategoryType.setImage(image.get(0).getImage());
			}
			ratingCategoryType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(ratingCategoryType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	private List<RatingCategoryType> getRatingCatImageById(
			RatingCategoryType ratingCategoryType) {
		String hql = "from RatingCategoryType where id = :id";
		HibernateUtil hibernateUtil = new  HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", ratingCategoryType.getId());
		List<RatingCategoryType> image = query.list();
		session.close();
		
		return image;
	}

	/**
	 * Get All RatingCategoryType
	 * @author 
	 * @return List<RatingCategoryType>
	 */
	public List<RatingCategoryType> getAllRatingCategoryType()
	{
		String hql = "from RatingCategoryType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<RatingCategoryType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get RatingCategoryType by ID
	 * @author 
	 * @return List<RatingCategoryType>
	 */
	public List<RatingCategoryType> getRatingCategoryTypeById(Short id)
	{
		String hql = "from RatingCategoryType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<RatingCategoryType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Role **************************** */
	
	/**
	 * Save Role
	 * 
	 * @param role
	 * @return String
	 */
	public ResponseMessage saveRole(Role role){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (role.getName() == null || role.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter role name");
		} else {
			String hql = "from Role where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", role.getName());
			List<Role> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Role already exists");
			} else {
				role.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(role);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update Role
	 * 
	 * @param role
	 * @return String
	 */
	public ResponseMessage updateRole(Role role){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from Role where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", role.getName());
		query.setParameter("id", role.getId());
		List<Role> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Role already exists");
		} else {
			role.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(role);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All Role
	 * @author 
	 * @return List<Role>
	 */
	public List<Role> getAllRole()
	{
		String hql = "from Role";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<Role> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get Role by ID
	 * @author 
	 * @return List<Role>
	 */
	public List<Role> getRoleById(Short id)
	{
		String hql = "from Role where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Role> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* School Category Type **************************** */
	
	/**
	 * Save SchoolCategoryType
	 * 
	 * @param schoolCategoryType
	 * @return String
	 */
	public ResponseMessage saveSchoolCategoryType(SchoolCategoryType schoolCategoryType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (schoolCategoryType.getName() == null || schoolCategoryType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter school category name");
		} else {
			String hql = "from SchoolCategoryType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", schoolCategoryType.getName());
			List<SchoolCategoryType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("School category name already exists");
			} else {
				schoolCategoryType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(schoolCategoryType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update SchoolCategoryType
	 * 
	 * @param schoolCategoryType
	 * @return String
	 */
	public ResponseMessage updateSchoolCategoryType(SchoolCategoryType schoolCategoryType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from SchoolCategoryType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", schoolCategoryType.getName());
		query.setParameter("id", schoolCategoryType.getId());
		List<SchoolCategoryType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("School category name already exists");
		} else {
			schoolCategoryType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(schoolCategoryType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All SchoolCategoryType
	 * @author 
	 * @return List<SchoolCategoryType>
	 */
	public List<SchoolCategoryType> getAllSchoolCategoryType()
	{
		String hql = "from SchoolCategoryType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SchoolCategoryType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get SchoolCategoryType by ID
	 * @author 
	 * @return List<SchoolCategoryType>
	 */
	public List<SchoolCategoryType> getSchoolCategoryTypeById(Integer id)
	{
		String hql = "from SchoolCategoryType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SchoolCategoryType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* School Classification Type **************************** */
	
	/**
	 * Save SchoolClassificationType
	 * 
	 * @param schoolClassificationType
	 * @return String
	 */
	public ResponseMessage saveSchoolClassificationType(SchoolClassificationType schoolClassificationType){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (schoolClassificationType.getName() == null || schoolClassificationType.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter school classification name");
		} else {
			String hql = "from SchoolClassificationType where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", schoolClassificationType.getName());
			List<SchoolClassificationType> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("School classification type already exists");
			} else {
				schoolClassificationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(schoolClassificationType);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update SchoolClassificationType
	 * 
	 * @param schoolClassificationType
	 * @return String
	 */
	public ResponseMessage updateSchoolClassificationType(SchoolClassificationType schoolClassificationType){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from SchoolClassificationType where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", schoolClassificationType.getName());
		query.setParameter("id", schoolClassificationType.getId());
		List<SchoolClassificationType> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("School classification type already exists");
		} else {
			schoolClassificationType.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(schoolClassificationType);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All SchoolClassificationType
	 * @author 
	 * @return List<SchoolClassificationType>
	 */
	public List<SchoolClassificationType> getAllSchoolClassificationType()
	{
		String hql = "from SchoolClassificationType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SchoolClassificationType> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get SchoolClassificationType by ID
	 * @author 
	 * @return List<SchoolClassificationType>
	 */
	public List<SchoolClassificationType> getSchoolClassificationTypeById(int id)
	{
		String hql = "from SchoolClassificationType where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SchoolClassificationType> result = query.list();
		session.close();
		return result;
	}
	
/* ******************************* Secondary Role **************************** */
	
	/**
	 * Save SecondaryRole
	 * 
	 * @param secondaryRole
	 * @return String
	 */
	public ResponseMessage saveSecondaryRole(SecondaryRole secondaryRole){
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (secondaryRole.getTitle() == null || secondaryRole.getTitle().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter secondary role");
		} else {
			String hql = "from SecondaryRole where title = :title";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("title", secondaryRole.getTitle());
			List<SecondaryRole> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Secondary role already exists");
			} else {
				secondaryRole.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(secondaryRole);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	/**
	 * Update SecondaryRole
	 * 
	 * @param secondaryRole
	 * @return String
	 */
	public ResponseMessage updateSecondaryRole(SecondaryRole secondaryRole){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from SecondaryRole where title = :title and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", secondaryRole.getTitle());
		query.setParameter("id", secondaryRole.getId());
		List<SecondaryRole> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Secondary role already exists");
		} else {
			secondaryRole.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(secondaryRole);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
	
	/**
	 * Get All SecondaryRole
	 * @author 
	 * @return List<SecondaryRole>
	 */
	public List<SecondaryRole> getAllSecondaryRole()
	{
		String hql = "from SecondaryRole";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<SecondaryRole> result = query.list();
		session.close();
		return result;
	}
	
	/**
	 * Get SecondaryRole by ID
	 * @author 
	 * @return List<SecondaryRole>
	 */
	public List<SecondaryRole> getSecondaryRoleById(Short id)
	{
		String hql = "from SecondaryRole where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SecondaryRole> result = query.list();
		session.close();
		return result;
	}
	/**
	 * Get All StreamTypes
	 * @author pradeep
	 * @return List<StreamType>
	 */
	public List<StreamType> getStreamTypes()
	{
		String hql = "from StreamType";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		List<StreamType> result = query.list();
		session.close();
		return result;
	}

	public List<StandardAlias> getStandardAliasById(Short id)
	{
		String hql = "from StandardAlias where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<StandardAlias> result = query.list();
		session.close();
		return result;
	}
	
	public ResponseMessage saveStandardAlias(StandardAlias standardAlias) {
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		if (standardAlias.getName() == null || standardAlias.getName().trim().length() == 0) {
			response.setStatus(0);
			response.setMessage("Please enter standard alias name");
		} else {
			String hql = "from StandardAlias where name = :name";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("name", standardAlias.getName());
			List<StandardAlias> result = query.list();
			session.close();
			if (result.size() > 0) {
				response.setStatus(0);
				response.setMessage("Standard alias name already exists");
			} else {
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(standardAlias);
				newsession.getTransaction().commit();
				newsession.close();
				response.setStatus(1);
				response.setMessage("Success");
			}
		}
		return response;
	}
	
	public ResponseMessage updateStandardAlias(StandardAlias standardAlias){
    	ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String hql = "from StandardAlias where name = :name and id != :id";
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", standardAlias.getName());
		query.setParameter("id", standardAlias.getId());
		List<StandardAlias> result = query.list();
		session.close();
		if (result.size() > 0) {
			response.setStatus(0);
			response.setMessage("Standard alias name already exists");
		} else {
	        Session newsession = hibernateUtil.openSession();
	        newsession.beginTransaction();
	        newsession.update(standardAlias);
	        newsession.getTransaction().commit();
	        newsession.close();
	        response.setStatus(1);
			response.setMessage("Success");
		}
        return response;
    }
}
