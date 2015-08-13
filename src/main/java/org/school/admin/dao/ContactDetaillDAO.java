package org.school.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.SchoolContact;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.AdminUser;
import org.school.admin.model.ContactInfo;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
import org.school.admin.model.SchoolLog;
import org.school.admin.model.SchoolSearch;
import org.school.admin.util.HibernateUtil;

public class ContactDetaillDAO {
	public ResponseMessage saveContactInfoInternal(List<ContactInfo> contactDetail)
	{
		ResponseMessage response = new ResponseMessage();
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		School school = null;
		AdminUser adminUser = new AdminUser();
		String message = "";
		String log = "New entry for contact detail | ";
	try{
		session.beginTransaction();
		for(int i=0;i<contactDetail.size();i++){
			System.out.println("i=: "+i);
			//checking for external
			if(contactDetail.get(i).getType()==1){//external
				if(contactDetail.get(i).getIsPrimary() ==1){//primary is 1
					if(!isPrimaryExternal(contactDetail.get(i).getSchool().getId())){//primary 1 not exist DB
						session.save(contactDetail.get(i));
						log +=" Name : "+contactDetail.get(i).getName();
						log +="| Email : "+contactDetail.get(i).getEmail();
						log +="| Mobile : "+contactDetail.get(i).getMobileNo();
						log +="| Contact_no : "+contactDetail.get(i).getContactNo();
						log+="|type : external";
						log +="| is primary : yes";
						response.setStatus(1);
						response.setMessage("Saved successfuly");
					}else{// primary 1 exist in DB
						response.setStatus(0);
						response.setMessage("Primary contact number for external already exist");
					}
				}else{// if primary is 0.
						session.save(contactDetail.get(i));
						log +=" Name : "+contactDetail.get(i).getName();
						log +="| Email : "+contactDetail.get(i).getEmail();
						log +="| Mobile : "+contactDetail.get(i).getMobileNo();
						log +="| Contact_no : "+contactDetail.get(i).getContactNo();
						log+="|type : external";
						log +="| is primary : no";
						response.setStatus(1);
						response.setMessage("Saved successfuly");
				}
				
				
			}
			if(contactDetail.get(i).getType()==0){//internal
				if(contactDetail.get(i).getIsPrimary() == 1){//if primary is 1
					if(!isPrimaryInternal(contactDetail.get(i).getSchool().getId())){//primary 1 not exist DB
						session.save(contactDetail.get(i));
						log +=" Name : "+contactDetail.get(i).getName();
						log +="| Email : "+contactDetail.get(i).getEmail();
						log +="| Mobile : "+contactDetail.get(i).getMobileNo();
						log +="| Contact_no : "+contactDetail.get(i).getContactNo();
						log+="|type : internal";
						log +="| is primary : yes";
						response.setStatus(1);
						response.setMessage("Saved successfuly");
					}else{// primary 1 exist in DB
						response.setStatus(0);
						response.setMessage("Primary contact number for internal already exist");
					}
				}else{// if primary 0
						session.save(contactDetail.get(i));
						log +=" Name : "+contactDetail.get(i).getName();
						log +="| Email : "+contactDetail.get(i).getEmail();
						log +="| Mobile : "+contactDetail.get(i).getMobileNo();
						log +="| Contact_no : "+contactDetail.get(i).getContactNo();
						log+="|type : external";
						log +="| is primary : no";
						response.setStatus(1);
						response.setMessage("Saved successfuly");
						
					}
				}
				
			}
		
			adminUser.setId(contactDetail.get(0).getLastUpdatedBy());
			session.getTransaction().commit();
			session.close();
	}
	catch(Exception e)
	{ 
		response.setStatus(0);
		response.setMessage("Fail to save data");
		System.out.println("Error in db : "+e);
		e.printStackTrace();
	}
		school = contactDetail.get(0).getSchool();
		school.setId(contactDetail.get(0).getSchool().getId());
		int school_id = school.getId();
//		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
//		contactInfos = getConatctDetail(school_id);
		new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, school, message, log, new Date(), new Date()));
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		schoolDAOImp.updateTabs(school_id, "contact");
		return response;
	}
	
	private boolean isPrimaryExternal(Integer id) {
		String hql ="from ContactInfo where school.id = :id and type = 1 and isPrimary=1";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ContactInfo> contactInfoList = query.list();
		session.close();
		if(contactInfoList.size() > 0)
		{
			return true;
		}else{
			return false;
		}
	}

	private boolean isPrimaryInternal(Integer id) {
		String hql ="from ContactInfo where school.id = :id and type = 0 and isPrimary=1";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ContactInfo> contactInfoList = query.list();
		session.close();
		if(contactInfoList.size() > 0)
		{
			return true;
		}else{
			return false;
		}
	}

	public ResponseMessage updateContactInfoInternal(ContactInfo contactDetail,String strReason)
	{
		ResponseMessage response = new ResponseMessage();
		String hql1 ="from ContactInfo where school.id = :id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session1 = hibernateUtil.openSession();
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id", contactDetail.getSchool().getId());
		List<ContactInfo> contactInfoList = query1.list();
		session1.close();
		int internal_cont = 0;
		int external_cont = 0;
		int is_int_primary = 0;
		int is_ext_primary = 0;
		for(int i=0; i<contactInfoList.size(); i++){
			if(contactInfoList.get(i).getId() != contactDetail.getId()){
				if(contactInfoList.get(i).getType() == 1){
					if(contactInfoList.get(i).getIsPrimary() == 1){
						is_ext_primary++;
					}
					external_cont++;
				}else{
					if(contactInfoList.get(i).getIsPrimary() == 1){
						is_int_primary++;
					}
					internal_cont++;
				}
			}
		}
		if(contactDetail.getType() == 1){
			if(external_cont >= 2){
				response.setMessage("Can not add more than two external contacts.");
				response.setStatus(0);
				return response;
			}
		}else{
			if(internal_cont >= 2){
				response.setMessage("Can not add more than two internal contacts.");
				response.setStatus(0);
				return response;
			}
		}
		if(contactDetail.getIsPrimary() == 1){
			if(contactDetail.getType() == 1){
				if(is_ext_primary >= 1){
					response.setMessage("Primary external contact already exists.");
					response.setStatus(0);
					return response;
				}
			}else{
				if(is_int_primary >= 1){
					response.setMessage("Primary internal contact already exists.");
					response.setStatus(0);
					return response;
				}
			}
		}
		String afterChange ="| Data after update in contact detail |";
		String hql ="from ContactInfo where id = :id";
		Session session = hibernateUtil.openSession();
	
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("id", contactDetail.getId());
		List<ContactInfo> getContactInfo = query.list();
		session.getTransaction().commit();
		session.close();
		
		String beforeChange = "Data before update in contact detail |";
		beforeChange += " Name : "+getContactInfo.get(0).getName();
		beforeChange += "| Email : "+getContactInfo.get(0).getEmail();
		beforeChange += "| Mobile : "+getContactInfo.get(0).getMobileNo();
		beforeChange += "| Contact_no : "+getContactInfo.get(0).getContactNo();
		if(getContactInfo.get(0).getType() == 0)
			beforeChange+="| type : internal";
		else
			beforeChange+="|type : external";
		if(getContactInfo.get(0).getIsPrimary() == 0)
			beforeChange += "| is primary : no";
		else
			beforeChange += "| is primary : yes";
		
		//new SchoolDAOImp().saveSchoolLog(new SchoolLog(adminUser, beforeUpdateschool, "", beforeChange, new Date(), new Date()));
		
		Session updateContactInfo = hibernateUtil.openSession();
		updateContactInfo.beginTransaction();
		if(getContactInfo.size()>0)
		{
//			if(!isPrimaryExternal(getContactInfo.get(0).getSchool().getId()) && !isPrimaryInternal(getContactInfo.get(0).getSchool().getId())){
				contactDetail.setId(getContactInfo.get(0).getId());
				updateContactInfo.update("id",contactDetail);
				updateContactInfo.getTransaction().commit();
				updateContactInfo.close();
				response.setStatus(1);
				response.setMessage("Updated successfully");
//			}
//			else{
//				response.setStatus(0);
//				response.setMessage("More than one primary contact numbers not allow");
//			}
		}
		
		
		List<ContactInfo> contactAfterUpdate =  getConatctDetailById(contactDetail.getId());
		if(contactAfterUpdate.size()>0)
		{
			afterChange +="| Name : "+contactAfterUpdate.get(0).getName();
			afterChange +="| Email : "+contactAfterUpdate.get(0).getEmail();
			afterChange +="| Mobile : "+contactAfterUpdate.get(0).getMobileNo();
			beforeChange +="| Contact_no : "+contactAfterUpdate.get(0).getContactNo();
			if(contactAfterUpdate.get(0).getType() == 0)
				afterChange+="| type : internal";
			else
				afterChange+="|type : external";
			String log = beforeChange.concat(afterChange);
			School school = new School();
			AdminUser adminUser = new AdminUser();
			school.setId(contactDetail.getSchool().getId());
			adminUser.setId(contactDetail.getLastUpdatedBy());
			
			
		}
		
		School school = contactDetail.getSchool();
		int school_id = school.getId();
	//	List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
		//contactInfos = getConatctDetail(school_id);
		return response;
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
		System.out.println("ContactSIZEzz : "+contactInfoList.size());
		for(int i =0 ;i < contactInfoList.size(); i++)
		{
			ContactInfo contactInfoInternal = new ContactInfo();
			contactInfoInternal.setId(contactInfoList.get(i).getId());
			School school = new School();
			school.setId(contactInfoList.get(i).getId());
			System.out.println("ConatctId : "+contactInfoList.get(i).getId());
			contactInfoInternal.setSchool(school);
			contactInfoInternal.setName(contactInfoList.get(i).getName());
			System.out.println("Name : "+contactInfoList.get(i).getName());
			contactInfoInternal.setMobileNo(contactInfoList.get(i).getMobileNo());
			System.out.println("ContactNO: "+contactInfoList.get(i).getContactNo());
			contactInfoInternal.setContactNo(contactInfoList.get(i).getContactNo());
			contactInfoInternal.setEmail(contactInfoList.get(i).getEmail());
			System.out.println("ContactEmail : "+contactInfoList.get(i).getEmail());
			contactInfoInternal.setType(contactInfoList.get(i).getType());
			System.out.println("ConatctType : "+contactInfoList.get(i).getType());
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
			String hql = "from ContactInfo where school.id = :school_id AND type = :type order by isPrimary DESC";
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
				contactInfoInternal.setIsPrimary(contactInfoList.get(i).getIsPrimary());
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
		session.close();
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
			contactInfoInternal.setContactNo(contactInfoList.get(i).getContactNo());
			contactInfoInternal.setEmail(contactInfoList.get(i).getEmail());
			contactInfoInternal.setType(contactInfoList.get(i).getType());
			contactInfoInternal.setIsPrimary(contactInfoList.get(i).getIsPrimary());
			newcontactInfoList.add(contactInfoInternal);
			
		}
		
		return newcontactInfoList;
		
	}
}
