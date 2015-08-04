package org.school.admin.dao;



import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.SchoolDetail;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.AdminUser;
import org.school.admin.model.BoardType;
import org.school.admin.model.MediumType;
import org.school.admin.model.School;
import org.school.admin.model.SchoolBoard;
import org.school.admin.model.SchoolLog;
import org.school.admin.model.SchoolMedium;
import org.school.admin.model.SchoolInfo;
import org.school.admin.util.HibernateUtil;

public class SchoolDetailDAOImpl {

	public ResponseMessage saveSchoolDetail(SchoolDetail schoolDetail,String reason)
	{
		String beforeUpdate = "Data before update in school detail :";
		String afterUpdate = "Data after update in school detail";
		String log = "| New data entry for school detail";
		String mainLog = "";
		String strReason = "";
		String afterUpdatemediumLog = "| medium type : ";
		String beforeUpdateMedium = "| medium type :";
		ResponseMessage response = new ResponseMessage();
		String hqlSchoolInfo = "from SchoolInfo where school.id = :school_id";
		String deleteSchoolMedium = "DELETE from SchoolMedium where school.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session oldSchoolInfo = hibernateUtil.openSession();
		Query query = oldSchoolInfo.createQuery(hqlSchoolInfo);
		query.setParameter("school_id",schoolDetail.getSchoolInfo().getSchool().getId());
		List<SchoolInfo> resultSchoolInfo = query.list();
		oldSchoolInfo.close();

		SchoolInfo schoolInfo = schoolDetail.getSchoolInfo();
		String msg = "";
		if(	schoolInfo.getSchoolClassificationType().getId() == 0 && schoolInfo.getSchoolType().getId()==0 && 
				schoolInfo.getSchoolCategoryType().getId() == 0
		){
			response.setStatus(0);
			msg = "Please select classification, school management  and type of school";
			response.setMessage(msg);
		}  else {
			Session newsession = hibernateUtil.openSession();
			newsession.beginTransaction();
			Query smdelete = newsession.createQuery(deleteSchoolMedium);
			smdelete.setParameter("school_id", schoolDetail.getSchoolInfo().getSchool().getId());
			smdelete.executeUpdate();
			newsession.getTransaction().commit();
			newsession.close();
			Session session = hibernateUtil.openSession();
			session.beginTransaction();
			SchoolInfo schoolInfoBeforeUpdate = null;
			if (resultSchoolInfo.size() > 0) {
				schoolInfoBeforeUpdate = resultSchoolInfo.get(0);
				beforeUpdate +="| school website : "+schoolInfoBeforeUpdate.getSchoolWebsite();
				beforeUpdate += "| classification : "+schoolInfoBeforeUpdate.getSchoolClassificationType().getName();
				beforeUpdate += " | type of school : "+schoolInfoBeforeUpdate.getSchoolCategoryType().getName();
				beforeUpdate += "| School Management : "+schoolInfoBeforeUpdate.getSchoolType().getName();
				System.out.println("BeforeUpdate : "+schoolInfoBeforeUpdate.getDisplayFee());
				if(schoolInfoBeforeUpdate.getDisplayFee() ==0)
					beforeUpdate += "|  display fee : no";
				else if(schoolInfoBeforeUpdate.getDisplayFee() ==1)
					beforeUpdate +="| display fee : yes";
				System.out.println("BeforeUpdateRe : "+schoolInfoBeforeUpdate.getIsResidential());
				if(schoolInfoBeforeUpdate.getIsResidential() == 0)
					beforeUpdate +="| is residential : no";
				else if(schoolInfoBeforeUpdate.getIsResidential() == 1)
					beforeUpdate +="| is residential : yes";
					
				schoolInfo.setId(resultSchoolInfo.get(0).getId());
				Session sessionUpdate = hibernateUtil.openSession();
				sessionUpdate.beginTransaction();
				sessionUpdate.update(schoolInfo);
				sessionUpdate.getTransaction().commit();
				sessionUpdate.close();
				List<SchoolInfo> schoolInfoAfterUpdate = getSchoolInfo(schoolInfo.getSchool().getId());
				if(schoolInfoAfterUpdate.size()>0)
				{
					afterUpdate +="| school website : "+schoolInfoAfterUpdate.get(0).getSchoolWebsite();
					afterUpdate += "| classification : "+schoolInfoAfterUpdate.get(0).getSchoolClassificationType().getName();
					afterUpdate += " | type of school : "+schoolInfoAfterUpdate.get(0).getSchoolCategoryType().getName();
					afterUpdate += "| School Management : "+schoolInfoAfterUpdate.get(0).getSchoolType().getName();
					System.out.println("AfterUpdate : "+schoolInfoAfterUpdate.get(0).getDisplayFee());
					if(schoolInfoAfterUpdate.get(0).getDisplayFee() ==0)
						afterUpdate += "|  display fee : no";
					else if(schoolInfoAfterUpdate.get(0).getDisplayFee() ==1)
						afterUpdate +="| display fee : yes";
					System.out.println("AfterUpdateRe : "+schoolInfoAfterUpdate.get(0).getIsResidential());
					if(schoolInfoAfterUpdate.get(0).getIsResidential() == 0)
						afterUpdate +="| is residential : no";
					else if(schoolInfoAfterUpdate.get(0).getIsResidential() == 1)
						afterUpdate +="| is residential : yes";
				}
				
				response.setStatus(1);
				msg = "School detail updated successfully";
				response.setMessage(msg);
			} else {
				response.setStatus(1);
				session.save(schoolInfo);
				log +="| school website : "+schoolInfo.getSchoolWebsite();
				log += "| classification : "+new ClassificationDAOImpl().getClassificationNameById(schoolInfo.getSchoolClassificationType().getId());
				log += " | type of school : "+new CategoryDAOImpl().getCategoryList(schoolInfo.getSchoolCategoryType().getId());
				log += "| School Management : "+new SchoolTypeDAOImpl().getSchoolTypeList(schoolInfo.getSchoolType().getId());
				if(schoolInfo.getDisplayFee() ==0)
					log += "|  display fee : no";
				else if(schoolInfo.getDisplayFee() ==1)
					log +="| display fee : yes";
				if(schoolInfo.getIsResidential() == 0)
					log +="| is residential : no";
				else if(schoolInfo.getIsResidential() == 1)
					log +="| is residential : yes";
				msg="School detail save successfully";
				response.setMessage(msg);
			}
			
			//System.out.println("School ID:"+schoolBoard.getSchool().getId());
			session.getTransaction().commit();
			session.close();
			Set<SchoolMedium> schoolMediums = schoolDetail.getSchoolMedium();

			if(schoolMediums.size() > 0)
			{
				Iterator<SchoolMedium> sIterator = schoolMediums.iterator();
				while(sIterator.hasNext())
				{
					SchoolMedium schoolMedium1 = sIterator.next();
					SchoolMedium schoolMedium2 = new SchoolMedium();
					schoolMedium2 = schoolMedium1;
					List<MediumType> mediumTypesBeforeUpdate = new MediumDAOImpl().getMediumListNameById(schoolMedium2.getMediumType().getId());
					if(mediumTypesBeforeUpdate.size()>0)
					{
						beforeUpdateMedium +=","+mediumTypesBeforeUpdate.get(0).getTitle();
					}
					Session session2 = hibernateUtil.openSession();
					session2.beginTransaction();
					session2.save(schoolMedium2);
					session2.getTransaction().commit();
					session2.close();
					List<MediumType> mediumTypesAfterUpdate = new MediumDAOImpl().getMediumListNameById(schoolMedium2.getMediumType().getId());
					if(mediumTypesAfterUpdate.size()>0)
					{
					
						afterUpdatemediumLog +=","+mediumTypesAfterUpdate.get(0).getTitle();
					}
				}
			}
			if(resultSchoolInfo.size()>0)
			{
				beforeUpdate = beforeUpdate.concat(beforeUpdateMedium);
				afterUpdate = afterUpdate.concat(afterUpdatemediumLog);
				mainLog = beforeUpdate.concat(afterUpdate);
				strReason = reason;
				AdminUser adminUser = new AdminUser();
				adminUser.setId(schoolInfo.getLastUpdatedBy());
				School school = new School();
				school.setId(schoolInfo.getSchool().getId());
				new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, strReason, mainLog, new Date(), new Date()));
						
			}else{
				mainLog = log.concat(afterUpdatemediumLog);
				AdminUser adminUser = new AdminUser();
				adminUser.setId(schoolInfo.getLastUpdatedBy());
				School school = new School();
				school.setId(schoolInfo.getSchool().getId());
				new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, strReason, mainLog, new Date(), new Date()));
			}
			
			SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
			schoolDAOImp.updateTabs(schoolDetail.getSchoolInfo().getSchool().getId(), "schoolDetail");
			//msg = "Record Added successfuly..";
		}
		
		return response;
	}

	public String saveSchoolBaord(BoardType boardType, School school)
	{
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		session.beginTransaction();
		BoardType newBoardType = new BoardType();
		newBoardType.setId(boardType.getId());
		School newSchool = new School();
		newSchool.setId(school.getId());
		SchoolBoard newSchoolBoard = new SchoolBoard();
		newSchoolBoard.setBoardType(newBoardType);
		newSchoolBoard.setSchool(school);
		session.save(newSchoolBoard);
		session.getTransaction().commit();
		
		session.close();
		return "success";
	}
	
	public List<SchoolInfo> getSchoolInfo(int school_id){
		String hql = "from SchoolInfo where school.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", school_id);
		List<SchoolInfo> result = query.list();
		session.close();
		return result;
	}
	
	public List<SchoolBoard> getSchoolBoard(int school_id){
		String hql = "from SchoolBoard where school.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", school_id);
		List<SchoolBoard> result = query.list();
		session.close();
		return result;
	}
	
	public List<SchoolMedium> getSchoolMedium(int school_id){
		String hql = "from SchoolMedium where school.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", school_id);
		List<SchoolMedium> result = query.list();
		session.close();
		return result;
	}
	
}