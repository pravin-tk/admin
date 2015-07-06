package org.school.admin.dao;



import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.SchoolDetail;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.BoardType;
import org.school.admin.model.MediumType;
import org.school.admin.model.School;
import org.school.admin.model.SchoolBoard;
import org.school.admin.model.SchoolMedium;
import org.school.admin.model.SchoolInfo;
import org.school.admin.util.HibernateUtil;

public class SchoolDetailDAOImpl {

	public ResponseMessage saveSchoolDetail(SchoolDetail schoolDetail)
	{
		ResponseMessage response = new ResponseMessage();
		String hqlSchoolInfo = "from SchoolInfo where school.id = :school_id";
		String hqlSchoolBoard = "from SchoolBoard where school.id = :school_id";
		String hqlSchoolMedium = "from SchoolMedium where school.id = :school_id";
		String deleteSchoolMedium = "DELETE from SchoolMedium where school.id = :school_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();

		Session oldSchoolInfo = hibernateUtil.openSession();
		Query query = oldSchoolInfo.createQuery(hqlSchoolInfo);
		query.setParameter("school_id",schoolDetail.getSchoolInfo().getSchool().getId());
		List<SchoolInfo> resultSchoolInfo = query.list();
		oldSchoolInfo.close();

		Session oldSchoolBoard = hibernateUtil.openSession();
		Query query1 = oldSchoolBoard.createQuery(hqlSchoolBoard);
		query1.setInteger("school_id",schoolDetail.getSchoolBoard().getSchool().getId());
		List<SchoolBoard> resultSchoolBoard = query1.list();
		oldSchoolBoard.close();
		
		SchoolInfo schoolInfo = schoolDetail.getSchoolInfo();
		SchoolBoard schoolBoard = schoolDetail.getSchoolBoard();
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
			if (resultSchoolInfo.size() > 0) {
				schoolInfo.setId(resultSchoolInfo.get(0).getId());
				session.update(schoolInfo);
				response.setStatus(1);
				msg = "School detail updated successfully";
				response.setMessage(msg);
			} else {
				response.setStatus(1);
				session.save(schoolInfo);
				msg="School detail save successfully";
				response.setMessage(msg);
			}
			if (resultSchoolBoard.size() > 0) {
				schoolBoard.setId(resultSchoolBoard.get(0).getId());
				session.update(schoolBoard);
			} else {
				session.save(schoolBoard);
			}
			System.out.println("School ID:"+schoolBoard.getSchool().getId());
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
					Session session2 = hibernateUtil.openSession();
					session2.beginTransaction();
					session2.save(schoolMedium2);
					session2.getTransaction().commit();
					session2.close();
				}
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