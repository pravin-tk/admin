package org.school.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.model.ContactInfo;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
import org.school.admin.util.HibernateUtil;

public class ContactDetaillDAO {
	public List<ContactInfo> saveContactInfoInternal(ContactInfo contactDetail)
	{
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
	
		session.beginTransaction();
		session.save(contactDetail);
		session.getTransaction().commit();
		session.close();
		
		School school = contactDetail.getSchool();
		int school_id = school.getId();
		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
		contactInfos = getConatctDetail(school_id);
		
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(school_id, "contact");
		return contactInfos;
	}
	
	public List<ContactInfo> updateContactInfoInternal(ContactInfo contactDetail)
	{
		String hql ="from ContactInfo where id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
	
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("id", contactDetail.getId());
		List<ContactInfo> getConatctInfo = query.list();
		session.getTransaction().commit();
		session.close();
		
		Session updateContactInfo = hibernateUtil.openSession();
		updateContactInfo.beginTransaction();
		if(getConatctInfo.size()>0)
		{
			contactDetail.setId(getConatctInfo.get(0).getId());
			updateContactInfo.update("id",contactDetail);
		}
		updateContactInfo.getTransaction().commit();
		updateContactInfo.close();
		School school = contactDetail.getSchool();
		int school_id = school.getId();
		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
		contactInfos = getConatctDetail(school_id);
		return contactInfos;
	}
	
	public List<ContactInfo> getConatctDetail(Integer school_id)
	{
		
		String hql = "from ContactInfo where school.id = :school_id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("school_id", school_id);
		
		List<ContactInfo> contactInfoList = query.list();
		List<ContactInfo> newcontactInfoList = new ArrayList<ContactInfo>();
		for(int i =0 ;i < contactInfoList.size(); i++)
		{
			ContactInfo contactInfoInternal = new ContactInfo();
			contactInfoInternal.setId(contactInfoList.get(i).getId());
			School school = new School();
			school.setId(contactInfoList.get(i).getId());
			contactInfoInternal.setSchool(school);
			contactInfoInternal.setName(contactInfoList.get(i).getName());
			contactInfoInternal.setMobileNo(contactInfoList.get(i).getMobileNo());
			contactInfoInternal.setEmail(contactInfoList.get(i).getEmail());
			contactInfoInternal.setType(contactInfoList.get(i).getType());
			newcontactInfoList.add(contactInfoInternal);
			
		}
		session.close();
		return newcontactInfoList;
		
		
		
	}
	
	public List<ContactInfo> getConatctDetailById(Integer id)
	{
		
		String hql = "from ContactInfo where id = :id";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<ContactInfo> contactInfoList = query.list();
		List<ContactInfo> newcontactInfoList = new ArrayList<ContactInfo>();
		for(int i =0 ;i < contactInfoList.size(); i++)
		{
			ContactInfo contactInfoInternal = new ContactInfo();
			contactInfoInternal.setId(contactInfoList.get(i).getId());
			School school = new School();
			school.setId(contactInfoList.get(i).getId());
			contactInfoInternal.setSchool(school);
			contactInfoInternal.setName(contactInfoList.get(i).getName());
			contactInfoInternal.setMobileNo(contactInfoList.get(i).getMobileNo());
			contactInfoInternal.setEmail(contactInfoList.get(i).getEmail());
			contactInfoInternal.setType(contactInfoList.get(i).getType());
			newcontactInfoList.add(contactInfoInternal);
			
		}
		session.close();
		return newcontactInfoList;
		
		
		
	}
	
   
    
}
