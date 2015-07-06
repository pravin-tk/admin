package org.school.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolListingRequest;
import org.school.admin.data.SchoolSearchResult;
import org.school.admin.util.HibernateUtil;


public class SchoolSearchImpl {
	
	@SuppressWarnings("unchecked")
	public SchoolSearchResult getSearchList(SchoolListingRequest request) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		String distance = "ROUND(6371 *  "
				+ " ACOS(COS( RADIANS(" + request.getLatitude() + ") ) * COS( RADIANS( s.latitude ) ) * " 
				+ " COS(RADIANS( s.longitude ) - RADIANS(" + request.getLongitude() + ") ) "
				+ " + SIN(RADIANS("+ request.getLatitude() +")) * SIN(RADIANS(s.latitude)) ),3)";
		String hql = "SELECT s.schoolId as schoolId, s.name as name,s.alias as alias, s.latitude as latitude,"
					 + " s.longitude as longitude, s.tagLine as tagLine, s.aboutSchool as aboutSchool,"
					 + "s.homeImage as homeImage,s.logo as logo, s.establishmentType as establishmentType,"
				     + "s.streetName as streetName, s.pincode as pincode, s.localityName as localityName,"
				     + "s.cityName as cityName,s.boardName as boardName,s.mediums as mediums,"
				     + "s.schoolCategory as schoolCategory,s.schoolClassification as schoolClassification,"
				     + "s.rating as rating,s.galeryImages as galeryImages,s.reviews as reviews, "
					 +distance+" as distance FROM SchoolSearch s, ClassInfo ci where s.schoolId = ci.school.id AND ci.standardType.id = :standard_id";
		if(request.getLatitude() != null && request.getLongitude() != null){
		hql = hql + " AND "+distance+" < 3";
		}
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(SchoolList.class));
		query.setParameter("standard_id", request.getStandardId());
		List<SchoolList> resultRaw = query.list();
		session.close();
		SchoolSearchResult filterMap = new SchoolSearchResult();
		filterMap.setSchoolList(resultRaw);
		return filterMap;
	}
	
	public Map<String, List> fetchSchoolListByLattitudeByLongitude( SchoolListingRequest schoolListRequest) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		
		String queryJoin = "";
		String queryCondition = "";
		if(schoolListRequest.getStandardId() != null) {
			queryCondition += " AND ci.standardType = " + schoolListRequest.getStandardId();
			queryJoin += " JOIN s.classInfos ci ";
		}
		
//		if(schoolListRequest.getBoardTypeId() != null) {
//			queryCondition += " AND bt.id = " + schoolListRequest.getBoardTypeId();
//		}
//		
//		if(schoolListRequest.getSchoolTypeId() != null) {
//			queryCondition += " AND si.schoolType.id = " + schoolListRequest.getSchoolTypeId();
//		}
//		
//		if(schoolListRequest.getSchoolMediumId() != null) {
//			queryCondition += " AND sm.mediumType.id = " + schoolListRequest.getSchoolMediumId();
//		}
//		
//		if(schoolListRequest.getGenderId() != null) {
//			queryCondition += " AND sct.id = " + schoolListRequest.getGenderId();
//		}
		
//		if(schoolListRequest.getSafetyFacilities() != null) {
//			String safetyFacilities = String.join(",", schoolListRequest.getSafetyFacilities());
//			queryCondition = " AND sc.id in( " + safetyFacilities + ")";
//			queryJoin += " JOIN s.schoolSafetyCatItems ssci JOIN ssci.safetyCategoryItem sci JOIN sci.safetyCategory sc";
//		}
//		
//		if(schoolListRequest.getActivityFacilities() != null) {
//			String activityFacilities = String.join(",", schoolListRequest.getActivityFacilities());
//			queryCondition = " AND ac.id in( " + activityFacilities + ")";
//			queryJoin += " JOIN s.schoolActivityCatItems saci JOIN saci.activityCategoryItem aci JOIN aci.activityCategory ac";
//		}
//		
//		if(schoolListRequest.getInfraFacilities() != null) {
//			String infraFacilities = String.join(",", schoolListRequest.getInfraFacilities());
//			queryCondition = " AND ic.id in( " + infraFacilities + ")";
//			queryJoin += " JOIN s.schoolInfrastructureCatItems sici JOIN sici.infrastructureCategoryItem ici JOIN ici.infrastructureCategory ic";
//		}
		
		Query query = session.createQuery("SELECT s.id as id, s.name as name, l.name as locality, c.name as city, "
				+ " mt.title as medium_type, bt.boardName as board, sct.name as school_category, sr.rating as school_rating "
				+ " FROM School s join s.locality l"
				+ " JOIN l.city c"
				+ " JOIN s.schoolMediums sm"
				+ " JOIN sm.mediumType mt "
				+ " JOIN s.schoolBoards sb "
				+ " JOIN sb.boardType bt "
				+ " JOIN s.schoolInfos si "
				+ " JOIN si.schoolCategoryType sct "
				+ " JOIN s.schoolRatings sr " + queryJoin
				+ " WHERE 8250 *  "
				+ " ACOS(COS( RADIANS(" + schoolListRequest.getLatitude() + ") ) * COS( RADIANS( s.latitude ) ) * " 
				+ " COS(RADIANS( s.longitude ) - RADIANS(" + schoolListRequest.getLongitude() + ") ) "
				+ " + SIN(RADIANS("+ schoolListRequest.getLatitude() +")) * SIN(RADIANS(s.latitude)) ) < 3 " + queryCondition )
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		Map<String, List> filterMap = new HashMap<String, List>();
		filterMap.put("schools", query.list());
//		filterMap.put("selectedSafetyFacilities", schoolListRequest.getSafetyFacilities());
//		filterMap.put("selectedActivityFacilities", schoolListRequest.getActivityFacilities());
//		filterMap.put("selectedInfraFacilities", schoolListRequest.getInfraFacilities());
		return filterMap;
	}
}
