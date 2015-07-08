package org.school.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolListingRequest;
import org.school.admin.data.SchoolSearchResult;
import org.school.admin.data.SearchRequest;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolTimeline;
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
	
	public SchoolSearchResult fetchSchoolListByLattitudeByLongitude( SearchRequest searchRequest) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		String distance = "0";
		String queryJoin = "";
		String queryCondition = "";
		String orderBy = "";
		if (searchRequest.getLatitude().trim().length() > 0 && searchRequest.getLongitude().trim().length() > 0) {
			distance = "ROUND(6371 *  "
				+ " ACOS(COS( RADIANS(" + searchRequest.getLatitude() + ") ) * COS( RADIANS( s.latitude ) ) * " 
				+ " COS(RADIANS( s.longitude ) - RADIANS(" + searchRequest.getLongitude() + ") ) "
				+ " + SIN(RADIANS("+ searchRequest.getLatitude() +")) * SIN(RADIANS(s.latitude)) ),3)";
			queryCondition += " AND "+distance+" < 3";
		}
		
		if (searchRequest.getStandardId() > 0) {
			queryCondition += " AND ci.standardType.id = " + searchRequest.getStandardId();
		}
		
		if (searchRequest.getBoardId() != "0") {
			queryCondition += " AND s.boardId IN(" + searchRequest.getBoardId() + ")";
		}
		
		if (searchRequest.getTypeId() != "0") {
			queryCondition += " AND s.schoolTypeId IN(" + searchRequest.getTypeId() + ")";
		}
		
		if (searchRequest.getMediumId() != "0") {
			queryCondition += " AND sm.mediumType.id IN(" + searchRequest.getMediumId() + ")";
		}
		
		if (searchRequest.getCategoryId() != "0") {
			queryCondition += " AND s.categoryId IN(" + searchRequest.getCategoryId() + ")";
		}
		
		if (searchRequest.getClassificationId() != "0") {
			queryCondition += " AND s.classificationId IN(" + searchRequest.getClassificationId() + ")";
		}
		
		if (searchRequest.getSafetyId() != "0") {
			queryCondition += " AND sc.safetyCategoryItem.id IN(" + searchRequest.getSafetyId() + ")";
			queryJoin += " JOIN ss.schoolSafetyCatItems sc";
		}
		
		if (searchRequest.getActivityId() != "0") {
			queryCondition += " AND ac.activityCategoryItem.id IN(" + searchRequest.getActivityId() + ")";
			queryJoin += " JOIN ss.schoolActivityCatItems ac";
		}
		
		if (searchRequest.getInfraId() != "0") {
			queryCondition += " AND ic.infrastructureCategoryItem.id IN(" + searchRequest.getInfraId() + ")";
			queryJoin += " JOIN ss.schoolInfrastructureCatItems ic";
		}
		
		if (searchRequest.getFee().equalsIgnoreCase("ASC") || searchRequest.getFee().equalsIgnoreCase("DESC")) {
			orderBy += " ci.totalFee "+searchRequest.getFee();
		}
		
		if (searchRequest.getRating().equalsIgnoreCase("ASC") || searchRequest.getRating().equalsIgnoreCase("DESC")) {
			if(orderBy != "")
				orderBy += ",";
			orderBy += " s.rating "+searchRequest.getRating();
		}
		
		if (searchRequest.getDistance().equalsIgnoreCase("ASC") || searchRequest.getDistance().equalsIgnoreCase("DESC")) {
			if(orderBy != "")
				orderBy += ",";
			orderBy += " s.distance "+searchRequest.getDistance();
		}
		String finalOrder = "";
		if(orderBy != ""){
			finalOrder = " ORDER BY"+orderBy;
		}
		
		Query query = session.createQuery(
				  "SELECT s.schoolId as schoolId, s.name as name,s.alias as alias, s.latitude as latitude,"
				+ " s.longitude as longitude, s.tagLine as tagLine, s.aboutSchool as aboutSchool,"
				+ " s.homeImage as homeImage,s.logo as logo, s.establishmentType as establishmentType,"
				+ " s.streetName as streetName, s.pincode as pincode, s.localityName as localityName,"
				+ " s.cityName as cityName,s.boardName as boardName,s.mediums as mediums,"
				+ " s.schoolCategory as schoolCategory,s.schoolClassification as schoolClassification,"
				+ " s.rating as rating,s.galeryImages as galeryImages,s.reviews as reviews, "
				+ distance + " as distance,ci.totalFee as totalFee"
				+ " FROM SchoolSearch s, School ss JOIN ss.classInfos ci"
				+ " JOIN ss.schoolMediums sm" + queryJoin
				+ " WHERE s.schoolId = ss.id"
				+ queryCondition+ " Group By ss.id"+finalOrder
		).setResultTransformer(Transformers.aliasToBean(SchoolList.class));

		List<SchoolList> resultRaw = query.list();
		session.close();
		SchoolSearchResult filterMap = new SchoolSearchResult();
		filterMap.setSchoolList(resultRaw);
		return filterMap;
	}
	
	public List<SchoolReview> getSchoolReviews(int id){
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		String hql = "FROM SchoolReview where school.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<SchoolReview> reviews = query.list();
		session.close();
		List<SchoolReview> schoolReviews = new ArrayList<SchoolReview>();
		for(int i=0; i < reviews.size(); i++){
			SchoolReview schoolReview = new SchoolReview();
			schoolReview.setId(reviews.get(i).getId());
			schoolReview.setReview(reviews.get(i).getReview());
			schoolReview.setDate(reviews.get(i).getDate());
			schoolReview.setTime(reviews.get(i).getTime());
			schoolReview.setTitle(reviews.get(i).getTitle());
			schoolReviews.add(schoolReview);
		}
		return schoolReviews;
	}
	
}
