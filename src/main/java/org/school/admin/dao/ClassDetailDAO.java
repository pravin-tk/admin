package org.school.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.data.SchoolFee;
import org.school.admin.data.ClassInfoData;
import org.school.admin.data.GalleryData;
import org.school.admin.data.SchoolAddress;
import org.school.admin.data.SchoolHighlightInfo;
import org.school.admin.data.StandardTypeData;
import org.school.admin.data.StreamTypeData;
import org.school.admin.data.TeachingApproachTypeData;
import org.school.admin.model.City;
import org.school.admin.model.ClassFee;
import org.school.admin.model.ClassInfo;
import org.school.admin.model.Locality;
import org.school.admin.model.School;
import org.school.admin.model.SchoolHighlight;
import org.school.admin.model.SchoolImageGallery;
import org.school.admin.util.HibernateUtil;

public class ClassDetailDAO {

	public List<ClassInfoData> getClassDetail(int school_id)
	{
		String hql = "from ClassInfo where school.id = :schoolId";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", school_id);
		List<ClassInfo> classDetailList = query.list();
		List<ClassInfoData> classInfoDataList = new ArrayList<ClassInfoData>();
		if(classDetailList.size() > 0)
		{
			for(int i =0; i< classDetailList.size();i++)
			{
				ClassInfoData classInfoData = new ClassInfoData();
				classInfoData.setAdmissionDeadline(classDetailList.get(i).getAdmissionDeadline());
				classInfoData.setAdmissionFrom(classDetailList.get(i).getAdmissionFrom());
				classInfoData.setAdmissionProcess(classDetailList.get(i).getAdmissionProcess());
				classInfoData.setAdmissionTo(classDetailList.get(i).getAdmissionTo());
				classInfoData.setEligibilityCriteria(classDetailList.get(i).getEligibilityCriteria());
				classInfoData.setFeesPaymentTerm(classDetailList.get(i).getFeesPaymentTerm());
				classInfoData.setHowToApply(classDetailList.get(i).getHowToApply());
				classInfoData.setTotalFee(classDetailList.get(i).getTotalFee());
				classInfoData.setTotalSeat(classDetailList.get(i).getTotalSeat());
				classInfoData.setVacantSeat(classDetailList.get(i).getVacantSeat());
				
				
				StreamTypeData streamTypeData = new StreamTypeData();
				streamTypeData.setId(classDetailList.get(i).getStreamType().getId());
				streamTypeData.setTitle(classDetailList.get(i).getStreamType().getTitle());
				
				TeachingApproachTypeData teachingApproachTypeData = new TeachingApproachTypeData();
				teachingApproachTypeData.setId(classDetailList.get(i).getTeachingApproachType().getId());
				teachingApproachTypeData.setName(classDetailList.get(i).getTeachingApproachType().getName());
				
				StandardTypeData standardTypeData = new StandardTypeData();
				standardTypeData.setId(classDetailList.get(i).getStandardType().getId());
				standardTypeData.setName(classDetailList.get(i).getStandardType().getName());
				
				classInfoData.setStreamTypeData(streamTypeData);
				classInfoData.setStandardTypeData(standardTypeData);
				classInfoData.setTeachingApproachTypeData(teachingApproachTypeData);
				classInfoDataList.add(classInfoData);
			}
		}
		session.close();
		return classInfoDataList;
	}
	
	public List<ClassInfoData> getClassDetail(int school_id,Short stdId)
	{
		String hql = "from ClassInfo where school.id = :schoolId and standardType.id = :stdId";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", school_id);
		query.setParameter("stdId", stdId);
		List<ClassInfo> classDetailList = query.list();
		session.close();
		List<ClassInfoData> classInfoDataList = new ArrayList<ClassInfoData>();
		if(classDetailList.size() > 0)
		{
			for(int i =0; i< classDetailList.size();i++)
			{
				ClassInfoData classInfoData = new ClassInfoData();
				classInfoData.setAdmissionDeadline(classDetailList.get(i).getAdmissionDeadline());
				classInfoData.setAdmissionFrom(classDetailList.get(i).getAdmissionFrom());
				classInfoData.setAdmissionProcess(classDetailList.get(i).getAdmissionProcess());
				classInfoData.setAdmissionTo(classDetailList.get(i).getAdmissionTo());
				classInfoData.setEligibilityCriteria(classDetailList.get(i).getEligibilityCriteria());
				classInfoData.setFeesPaymentTerm(classDetailList.get(i).getFeesPaymentTerm());
				classInfoData.setHowToApply(classDetailList.get(i).getHowToApply());
				classInfoData.setTotalFee(classDetailList.get(i).getTotalFee());
				classInfoData.setTotalSeat(classDetailList.get(i).getTotalSeat());
				classInfoData.setVacantSeat(classDetailList.get(i).getVacantSeat());
				
				
				StreamTypeData streamTypeData = new StreamTypeData();
				streamTypeData.setId(classDetailList.get(i).getStreamType().getId());
				streamTypeData.setTitle(classDetailList.get(i).getStreamType().getTitle());
				
				TeachingApproachTypeData teachingApproachTypeData = new TeachingApproachTypeData();
				teachingApproachTypeData.setId(classDetailList.get(i).getTeachingApproachType().getId());
				teachingApproachTypeData.setName(classDetailList.get(i).getTeachingApproachType().getName());
				
				StandardTypeData standardTypeData = new StandardTypeData();
				standardTypeData.setId(classDetailList.get(i).getStandardType().getId());
				standardTypeData.setName(classDetailList.get(i).getStandardType().getName());
				
				classInfoData.setStreamTypeData(streamTypeData);
				classInfoData.setStandardTypeData(standardTypeData);
				classInfoData.setTeachingApproachTypeData(teachingApproachTypeData);
				classInfoDataList.add(classInfoData);
			}
		}
		
		return classInfoDataList;
	}
	
	public List<SchoolFee> getClassFeeDetails(int schoolId,Short stdId)
	{
		String hql = "from ClassInfo c where c.school.id = :schoolId and c.standardType.id = :stdId";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", schoolId);
		query.setParameter("stdId", stdId);
		
		List<ClassInfo> idList = query.list();
		session.close();
		List<SchoolFee> classFeeDataList = new ArrayList<SchoolFee>();
		if(idList.size() > 0)
		{
			System.out.println("ClassInfoId : "+idList.get(0).getId());
			for(int j=0;j<idList.size();j++)
			{
				Session selectFee = hibernateUtil.openSession();
				ClassInfo classInfo = idList.get(j);
				Integer id = classInfo.getId();
				String selectFeeHQL = "from ClassFee cf where cf.classInfo.id = :id";
				Query selectFeeQuery = selectFee.createQuery(selectFeeHQL);
				selectFeeQuery.setParameter("id", id);
				List<ClassFee> classFeeList = selectFeeQuery.list();
				if(classFeeList.size() > 0)
				{
					 for(int h=0;h<classFeeList.size();h++)
					 {
						 SchoolFee schoolFee = new SchoolFee();
						 schoolFee.setFeeDesc(classFeeList.get(h).getFeeDesc());
						 schoolFee.setAmount(classFeeList.get(h).getAmount());
						 classFeeDataList.add(schoolFee);
					 }
				}
				
			}
		}
		return classFeeDataList;
	}
	
	public List<GalleryData> getImageGallary(Integer schoolId)
	{
		String HQL = "from SchoolImageGallery where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(HQL);
		query.setParameter("schoolId", schoolId);
		session.close();
		List<SchoolImageGallery> schoolImageGalleryList = query.list();
		List<GalleryData> gallaryDataList = new ArrayList<GalleryData>();
		String url ="http://localhost:8080/admin/images/";
		if(schoolImageGalleryList.size() > 0)
		{
			for(int i=0;i<schoolImageGalleryList.size();i++){
				GalleryData gallaryData = new GalleryData();
				gallaryData.setImageTitle(schoolImageGalleryList.get(i).getTitle());
				gallaryData.setImageUrl(url+schoolImageGalleryList.get(i).getImage());
				gallaryDataList.add(gallaryData);
     		}
		}
		return gallaryDataList;
	}
	public List<SchoolAddress> getSchoolBasicInfo(int schoolId)
	{
		String HQL = "from School where id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(HQL);
		query.setParameter("schoolId", schoolId);
	     
		List<School> schoolList = query.list();
		session.close();
		List<SchoolAddress> schoolAddressList = new ArrayList<SchoolAddress>();
		if(schoolList.size() > 0)
		{
			for(int i=0;i<schoolList.size();i++){
			SchoolAddress schoolAddress = new SchoolAddress();
			schoolAddress.setSchoolId(schoolList.get(i).getId());
			schoolAddress.setName(schoolList.get(i).getName());
			schoolAddress.setAboutSchool(schoolList.get(i).getAboutSchool());
			schoolAddress.setAlias(schoolList.get(i).getAlias());
			schoolAddress.setTagLine(schoolList.get(i).getTagLine());
			schoolAddress.setStreetName(schoolList.get(i).getStreetName());
			schoolAddress.setLongitude(schoolList.get(i).getLongitude());
			schoolAddress.setLatitude(schoolList.get(i).getLatitude());
			schoolAddress.setLandmark(schoolList.get(i).getLandmark());
			boolean establishmentType = schoolList.get(i).isEstablishmentType();
			if(establishmentType == false){
				String old = "old";
				schoolAddress.setEstablishmentType(old);
			}
			else
			{
				String newEstablishment = "new";
				schoolAddress.setEstablishmentType(newEstablishment);
			}
			schoolAddress.setPincode(schoolList.get(i).getPincode());
			schoolAddress.setHomeImage(schoolList.get(i).getHomeImage());
			schoolAddress.setLogo(schoolList.get(i).getLogo());
		
			Locality locality = new Locality();
			locality.setId(schoolList.get(i).getLocality().getId());
			locality.setName(schoolList.get(i).getLocality().getName());
			String localityName = locality.getName();
			schoolAddress.setLocalityName(localityName);
			
			City city = new City();
			city.setId(schoolList.get(i).getLocality().getCity().getId());
			city.setName(schoolList.get(i).getLocality().getCity().getName());
			String cityName = city.getName();
			schoolAddress.setCityName(cityName);
		
			
			schoolAddressList.add(schoolAddress);
		}
		}
		
		return schoolAddressList;
	}
	 
	public List<SchoolHighlightInfo> getSchoolHighlightInfo(int schoolId)
	{
		String HQL = "from SchoolHighlight where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(HQL);
		query.setParameter("schoolId", schoolId);
	    List<SchoolHighlight> schoolHighlightList = query.list();
	    session.close();
	    List<SchoolHighlightInfo> schoolHighlightInfos = new ArrayList<SchoolHighlightInfo>();
	    if(schoolHighlightList.size() > 0)
	    {
	    	for(int i=0;i<schoolHighlightList.size();i++)
	    	{
	    	SchoolHighlightInfo schoolHighlightInfo = new SchoolHighlightInfo();
	    	schoolHighlightInfo.setTitle(schoolHighlightList.get(i).getName());
	    	schoolHighlightInfos.add(schoolHighlightInfo);
	    	
	    	}
	    }
	    return schoolHighlightInfos;
	}
}
