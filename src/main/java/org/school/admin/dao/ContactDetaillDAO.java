package org.school.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.SchoolContact;
import org.school.admin.model.ContactInfo;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
import org.school.admin.model.SchoolSearch;
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
	
	public SchoolContact getExternalConatctDetail(Integer school_id)
	{
		SchoolContact schoolContact = new SchoolContact();
		HibernateUtil hibernateUtil = new HibernateUtil();
		String shql = "from SchoolSearch where schoolId = :school_id";
		Session sessionnew = hibernateUtil.openSession();
		Query query1 = sessionnew.createQuery(shql);
		query1.setParameter("school_id", school_id);
		List<SchoolSearch> schoolSearchs = query1.list();
		sessionnew.close();
		if(schoolSearchs.size() > 0) {
			Byte type = 1;
			String hql = "from ContactInfo where school.id = :school_id AND type = :type";
			Session session = hibernateUtil.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("school_id", school_id);
			query.setParameter("type", type);
			List<ContactInfo> contactInfoList = query.list();
			session.close();
			schoolContact.setId(schoolSearchs.get(0).getSchoolId());
			schoolContact.setName(schoolSearchs.get(0).getName());
			schoolContact.setLatitude(schoolSearchs.get(0).getLatitude());
			schoolContact.setLongitude(schoolSearchs.get(0).getLongitude());
			String address = schoolSearchs.get(0).getStreetName()+", "
							 +schoolSearchs.get(0).getLocalityName()+", "
							 +schoolSearchs.get(0).getCityName()+", "
							 +schoolSearchs.get(0).getPincode();
			List<ContactInfo> newcontactInfoList = new ArrayList<ContactInfo>();
			schoolContact.setAddress(address);
			for(int i =0 ;i < contactInfoList.size(); i++)
			{
				ContactInfo contactInfoInternal = new ContactInfo();
				contactInfoInternal.setName(contactInfoList.get(i).getName());
				contactInfoInternal.setMobileNo(contactInfoList.get(i).getMobileNo());
				contactInfoInternal.setEmail(contactInfoList.get(i).getEmail());
				newcontactInfoList.add(contactInfoInternal);
				
			}
			schoolContact.setContacts(newcontactInfoList);
		} else {
			schoolContact = null;
		}
		return schoolContact;
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
