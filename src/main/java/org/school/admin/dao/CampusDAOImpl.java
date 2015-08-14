package org.school.admin.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.AdminUser;
import org.school.admin.model.AreaUnit;
import org.school.admin.model.BoardType;
import org.school.admin.model.CampusInfo;
import org.school.admin.model.School;
import org.school.admin.model.SchoolLog;
import org.school.admin.util.HibernateUtil;

public class CampusDAOImpl {
	
	public ResponseMessage saveCampus(CampusInfo campusInfo,String strReason)
	{
		 ResponseMessage response = new ResponseMessage();
		 String beforeUpdate="Data before update campus detail : ";
		 String hql = "from CampusInfo where school.id = :school_id";
		 HibernateUtil hibernateUtil = new HibernateUtil();
		 Session oldsession = hibernateUtil.openSession();
		 Query query = oldsession.createQuery(hql);
		 query.setParameter("school_id",campusInfo.getSchool().getId());
		 List<CampusInfo> result = query.list();
		 oldsession.close();
		 Session session = hibernateUtil.openSession();
		 session.beginTransaction();
		 if(result.size() > 0){
			 beforeUpdate +="campusSize : "+result.get(0).getCampusSize();
			 AreaUnit areaUnitBeforeUpdate = result.get(0).getAreaUnit();
			 areaUnitBeforeUpdate.setId(result.get(0).getAreaUnit().getId());
			 areaUnitBeforeUpdate.setName(result.get(0).getAreaUnit().getName());
			 beforeUpdate +="| campusUnit : "+areaUnitBeforeUpdate.getName();
			 beforeUpdate +="| Total buildings : "+result.get(0).getTotalBuildings();
			 beforeUpdate +="| Total playground : "+result.get(0).getTotalPlaygrounds();
			 beforeUpdate +="| total students : "+result.get(0).getTotalStudents();
			 beforeUpdate  +="| total boys : "+result.get(0).getTotalBoys();
			 beforeUpdate +="| total girls : "+result.get(0).getTotalGirls();
			 beforeUpdate +="| total teaching(Male) : "+result.get(0).getTotalMaleTeacher();
			 campusInfo.setId(result.get(0).getId());
			 session.update("id", campusInfo);
	
			 List<CampusInfo> campusInfos = 	getCampusInfo(result.get(0).getSchool().getId());
			 AdminUser adminUser = new AdminUser();
			 adminUser.setId(campusInfo.getLastUpdatedBy());
			 School school = new School();
			 school.setId(campusInfos.get(0).getSchool().getId());
			 String afterUpdate = " | Data after update in campus detail : ";
			 afterUpdate +="campusSize : "+campusInfos.get(0).getCampusSize();
			 AreaUnit areaUnit = campusInfos.get(0).getAreaUnit();
			 areaUnit.setId(campusInfos.get(0).getAreaUnit().getId());
			 areaUnit.setName(campusInfos.get(0).getAreaUnit().getName());
			 afterUpdate +="| campusUnit : "+areaUnit.getName();
			 afterUpdate +="| Total buildings : "+campusInfos.get(0).getTotalBuildings();
			 afterUpdate +="| Total playground : "+campusInfos.get(0).getTotalPlaygrounds();
			 afterUpdate +="| total students : "+campusInfos.get(0).getTotalStudents();
			 afterUpdate  +="| total boys : "+campusInfos.get(0).getTotalBoys();
			 afterUpdate +="| total girls : "+campusInfos.get(0).getTotalGirls();
			 afterUpdate +="| total teaching(Male) : "+campusInfos.get(0).getTotalMaleTeacher();
			 String log = beforeUpdate.concat(afterUpdate);
			
		new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, strReason, log, new Date(), new Date()));
			response.setStatus(1);
			response.setMessage("Campus detail updated successfully");
		} else {
			
			session.save(campusInfo);
			AdminUser adminUser = new AdminUser();
			adminUser.setId(campusInfo.getLastUpdatedBy());
			School school = new School();
			school.setId(campusInfo.getSchool().getId());
			String log = "New Entry  in campus detail : ";
			log +="campusSize : "+campusInfo.getCampusSize();
		    String areaUnit = new AreaUnitDAOImpl().getAreaUnitById(campusInfo.getAreaUnit().getId()); 
			log +="| campusUnit : "+areaUnit;
			log +="| Total buildings : "+campusInfo.getTotalBuildings();
			log +="| Total playground : "+campusInfo.getTotalPlaygrounds();
			log +="| total students : "+campusInfo.getTotalStudents();
			log  +="| total boys : "+campusInfo.getTotalBoys();
			log +="| total girls : "+campusInfo.getTotalGirls();
			log +="| total teacher(Male) : "+campusInfo.getTotalMaleTeacher();
			log +="| total teacher(Female) : "+campusInfo.getTotalFemaleTeacher();
			log +="| total supporting staff(Male) : "+campusInfo.getMaleSupportingStaff();
			log +="| total supporting staff(Female) : "+campusInfo.getFemaleSupportingStaff();

			new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, "", log, new Date(), new Date()));
			response.setStatus(1);
			response.setMessage("Campus detail saved successfully");
		}
		session.getTransaction().commit();
		session.close();
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(campusInfo.getSchool().getId(), "campusDetail");
		return response;
	}
	
	public List<CampusInfo> getCampusInfo(int school_id){
		String hql = "from CampusInfo where school.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session oldsession = hibernateUtil.openSession();
		Query query = oldsession.createQuery(hql);
		query.setParameter("school_id",school_id);
		List<CampusInfo> result = query.list();
		oldsession.close();
		return result;
	}
	
}
