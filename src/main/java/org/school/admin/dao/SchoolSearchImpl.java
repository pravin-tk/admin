package org.school.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.persistence.sessions.serializers.JSONSerializer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.school.admin.data.ClassFeeInfo;
import org.school.admin.data.FeeDetail;
import org.school.admin.data.GalleryData;
import org.school.admin.data.InfraCategory;
import org.school.admin.data.InfraItem;
import org.school.admin.data.NameList;
import org.school.admin.data.NearbySchoolList;
import org.school.admin.data.RatingData;
import org.school.admin.data.SchoolAnalyticsData;
import org.school.admin.data.SchoolFee;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolListingRequest;
import org.school.admin.data.Rating;
import org.school.admin.data.SchoolSearchResult;
import org.school.admin.data.SearchRequest;
import org.school.admin.data.TotalRating;
import org.school.admin.data.UriData;
import org.school.admin.data.VacantSeats;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.ClassInfo;
import org.school.admin.model.PrevStudentProfile;
import org.school.admin.model.RatingCategoryType;
import org.school.admin.model.School;
import org.school.admin.model.SchoolActivityCatItem;
import org.school.admin.model.SchoolAnalytics;
import org.school.admin.model.SchoolImageGallery;
import org.school.admin.model.SchoolInfrastructureCatItem;
import org.school.admin.model.SchoolPanoramicImage;
import org.school.admin.model.SchoolRating;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolSafetyCatItem;
import org.school.admin.model.UserRating;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.util.HibernateUtil;

import com.google.gson.Gson;


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
				     + "s.schoolType as schoolType,s.rating as rating,ta.name as teachingApproach,"
				     + "s.galeryImages as galeryImages,s.reviews as reviews, "
					 +distance+" as distance,ci.totalFee as totalFee,ci.vacantSeat as seats,"
					 + "ci.standardType.id as standardId FROM SchoolSearch s, ClassInfo ci JOIN ci.teachingApproachType ta"
					 + " where s.schoolId = ci.school.id AND ci.standardType.id = :standard_id";
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
	
	public List<SchoolList> fetchSchoolListByLattitudeByLongitude( SearchRequest searchRequest) {
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
			orderBy += " "+distance+" "+searchRequest.getDistance();
		}
		
		if (searchRequest.getSeats().equalsIgnoreCase("ASC") || searchRequest.getSeats().equalsIgnoreCase("DESC")) {
			if(orderBy != "")
				orderBy += ",";
			orderBy += " ci.vacantSeat "+searchRequest.getSeats();
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
				+ " s.schoolType as schoolType,s.rating as rating,s.galeryImages as galeryImages,s.reviews as reviews, "
				+ distance + " as distance,ci.totalFee as totalFee,ci.vacantSeat as seats,"
				+ " ci.standardType.id as standardId, ta.name as teachingApproach"
				+ " FROM SchoolSearch s, School ss JOIN ss.classInfos ci"
				+ " JOIN ss.schoolMediums sm JOIN ci.teachingApproachType ta" + queryJoin
				+ " WHERE s.schoolId = ss.id"
				+ queryCondition+ " Group By ss.id"+finalOrder
		).setResultTransformer(Transformers.aliasToBean(SchoolList.class));

		
		List<SchoolList> resultRaw = query.list();
		session.close();
		return resultRaw;
	}
	
	public List<SchoolList> fetchSchoolListById(int schoolId,int standardId) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(
				  "SELECT s.schoolId as schoolId, s.name as name,s.alias as alias, s.latitude as latitude,"
				+ " s.longitude as longitude, s.tagLine as tagLine, s.aboutSchool as aboutSchool,"
				+ " s.homeImage as homeImage,s.logo as logo, s.establishmentType as establishmentType,"
				+ " s.streetName as streetName, s.pincode as pincode, s.localityName as localityName,"
				+ " s.cityName as cityName,s.boardName as boardName,s.mediums as mediums,"
				+ " s.schoolCategory as schoolCategory,s.schoolClassification as schoolClassification,"
				+ " s.rating as rating,s.galeryImages as galeryImages,s.reviews as reviews, "
				+ " 0.0 as distance,ci.totalFee as totalFee,ci.vacantSeat as seats,ci.standardType.id as standardId"
				+ " FROM SchoolSearch s, School ss JOIN ss.classInfos ci"
				+ " WHERE s.schoolId = ss.id AND ci.standardType.id = "+standardId
				+ " AND ss.id = "+schoolId
		).setResultTransformer(Transformers.aliasToBean(SchoolList.class));
		List<SchoolList> resultRaw = query.list();
		session.close();
		return resultRaw;
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
			UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
			userRegistrationInfo.setId(reviews.get(i).getUserRegistrationInfo().getId());
			userRegistrationInfo.setFirstName(reviews.get(i).getUserRegistrationInfo().getFirstName());
			userRegistrationInfo.setLastName(reviews.get(i).getUserRegistrationInfo().getLastName());
			userRegistrationInfo.setImage(reviews.get(i).getUserRegistrationInfo().getImage());
			SchoolReview schoolReview = new SchoolReview();
			schoolReview.setId(reviews.get(i).getId());
			schoolReview.setReview(reviews.get(i).getReview());
			schoolReview.setDate(reviews.get(i).getDate());
			schoolReview.setTime(reviews.get(i).getTime());
			schoolReview.setTitle(reviews.get(i).getTitle());
			schoolReview.setUserRegistrationInfo(userRegistrationInfo);
			schoolReviews.add(schoolReview);
		}
		return schoolReviews;
	}
	
	public List<GalleryData> getImageGallary(Integer schoolId)
	{
		String HQL = "from SchoolImageGallery where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(HQL);
		query.setParameter("schoolId", schoolId);
		List<SchoolImageGallery> schoolImageGalleryList = query.list();
		List<GalleryData> gallaryDataList = new ArrayList<GalleryData>();
		if(schoolImageGalleryList.size() > 0)
		{
			for(int i=0;i<schoolImageGalleryList.size();i++){
				GalleryData gallaryData = new GalleryData();
				gallaryData.setImageTitle(schoolImageGalleryList.get(i).getTitle());
				gallaryData.setImageUrl(schoolImageGalleryList.get(i).getImage());
				gallaryDataList.add(gallaryData);
     		}
		}
		return gallaryDataList;
	}
	
	public List<SchoolPanoramicImage> getSchoolPanorama(int schoolId) {
		String HQL = "SELECT id as id,title as title, panoImage as panoImage from SchoolPanoramicImage where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(HQL).setResultTransformer(Transformers.aliasToBean(SchoolPanoramicImage.class));
		query.setParameter("schoolId", schoolId);
		List<SchoolPanoramicImage> schoolImageGalleryList = query.list();
		return schoolImageGalleryList;
	}
	
	public List<FeeDetail> getClassFeeDetails(int schoolId,Short std_id)
	{
		Short standardId;
		try{
			if(std_id > 0){
				standardId = std_id; 
			}else{
				standardId = 0;
			}
		} catch(NullPointerException e) {
			standardId = 0;
		}
		
		String hql = "SELECT c.id as id,s.name as name, f.id as feeId, f.feeDesc as feeName,"
					+"c.totalFee as totalFee,c.eligibilityCriteria as eligibilityCriteria,"
					+"c.admissionProcess as admissionProcess,c.howToApply as howToApply,"
					+"c.feesPaymentTerm as feesPaymentTerm,c.admissionFrom as admissionFrom,"
					+"c.admissionDeadline as admissionDeadline,"
					+"f.amount as amount from ClassFee f join f.classInfo c join c.standardType s "
					+"where c.school.id = :schoolId";
		if(standardId > 0){
			hql += " AND s.id = "+standardId;
		}
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(ClassFeeInfo.class));
		query.setParameter("schoolId", schoolId);
		
		List<ClassFeeInfo> classFeeInfos = query.list();
		List<FeeDetail> classFeeDataList = new ArrayList<FeeDetail>();
		if(classFeeInfos.size() > 0)
		{
			int class_id = 0;
			String class_name = "";
			Date admissionFrom = null;
			Date admissionDeadline = null;
			String eligibilityCriteria = "";
			String admissionProcess = "";
			String howToApply = "";
			String feesPaymentTerm = "";
			Double totalFee = 0.0;
			FeeDetail feeDetail = new FeeDetail();
			List<SchoolFee> schoolFees = new ArrayList<SchoolFee>();
			for(int i=0;i<classFeeInfos.size();i++)
			{
				if(class_id != 0 && class_id != classFeeInfos.get(i).getId()){
					feeDetail.setId(class_id);
					feeDetail.setClassName(class_name);
					feeDetail.setFees(schoolFees);
					feeDetail.setAdmissionFrom(admissionFrom);
					feeDetail.setAdmissionDeadline(admissionDeadline);
					feeDetail.setEligibilityCriteria(eligibilityCriteria);
					feeDetail.setAdmissionProcess(admissionProcess);
					feeDetail.setHowToApply(howToApply);
					feeDetail.setFeesPaymentTerm(feesPaymentTerm);
					feeDetail.setTotalFee(totalFee);
					classFeeDataList.add(feeDetail);
					schoolFees = new ArrayList<SchoolFee>();
					feeDetail = new FeeDetail();
				}
				SchoolFee schoolFee = new SchoolFee();
				schoolFee.setFeeDesc(classFeeInfos.get(i).getFeeName());
				schoolFee.setAmount(classFeeInfos.get(i).getAmount());
				schoolFees.add(schoolFee);
				class_id = classFeeInfos.get(i).getId();
				class_name = classFeeInfos.get(i).getName();
				admissionFrom = classFeeInfos.get(i).getAdmissionFrom();
				admissionDeadline = classFeeInfos.get(i).getAdmissionDeadline();
				eligibilityCriteria = classFeeInfos.get(i).getEligibilityCriteria();
				admissionProcess = classFeeInfos.get(i).getAdmissionProcess();
				howToApply = classFeeInfos.get(i).getHowToApply();
				feesPaymentTerm = classFeeInfos.get(i).getFeesPaymentTerm();
				totalFee = classFeeInfos.get(i).getTotalFee();
			}
			feeDetail.setId(class_id);
			feeDetail.setClassName(class_name);
			feeDetail.setFees(schoolFees);
			feeDetail.setAdmissionFrom(admissionFrom);
			feeDetail.setAdmissionDeadline(admissionDeadline);
			feeDetail.setEligibilityCriteria(eligibilityCriteria);
			feeDetail.setAdmissionProcess(admissionProcess);
			feeDetail.setHowToApply(howToApply);
			feeDetail.setFeesPaymentTerm(feesPaymentTerm);
			feeDetail.setTotalFee(totalFee);
			classFeeDataList.add(feeDetail);
		}
		return classFeeDataList;
	}
	
	public List<InfraCategory> getSchoolActivity(int id)
	{
		String hql = "from SchoolActivityCatItem where school.id = :schoolId";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", id);
		List<SchoolActivityCatItem> items = query.list();
		session.close();
		List<InfraCategory> infraCategories = new ArrayList<InfraCategory>();
		int cat_id = 0;
		String cat_name = "";
		String cat_desc = "";
		InfraCategory infraCategory = new InfraCategory();
		List<InfraItem> infraItems = new ArrayList<InfraItem>();
		for(int i=0; i<items.size(); i++){
			if(cat_id != 0 && cat_id != items.get(i).getActivityCategoryItem().getActivityCategory().getId()){
				infraCategory.setId(cat_id);
				infraCategory.setName(cat_name);
				infraCategory.setDescription(cat_desc);
				infraCategory.setItems(infraItems);
				infraCategories.add(infraCategory);
				infraItems = new ArrayList<InfraItem>();
				infraCategory = new InfraCategory();
			}
			InfraItem infraItem = new InfraItem();
			infraItem.setId(items.get(i).getActivityCategoryItem().getId());
			infraItem.setName(items.get(i).getActivityCategoryItem().getName());
			infraItem.setDescription(items.get(i).getActivityCategoryItem().getDescription());
			infraItems.add(infraItem);
			cat_id = items.get(i).getActivityCategoryItem().getActivityCategory().getId();
			cat_name = items.get(i).getActivityCategoryItem().getActivityCategory().getName();
			cat_desc = items.get(i).getActivityCategoryItem().getActivityCategory().getDescription();
		}
		infraCategory.setId(cat_id);
		infraCategory.setName(cat_name);
		infraCategory.setDescription(cat_desc);
		infraCategory.setItems(infraItems);
		infraCategories.add(infraCategory);
		return infraCategories;
	}
	
	public List<InfraCategory> getSchoolSafety(int id)
	{
		String hql = "from SchoolSafetyCatItem where school.id = :schoolId";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", id);
		List<SchoolSafetyCatItem> items = query.list();
		session.close();
		List<InfraCategory> infraCategories = new ArrayList<InfraCategory>();
		int cat_id = 0;
		String cat_name = "";
		String cat_desc = "";
		InfraCategory infraCategory = new InfraCategory();
		List<InfraItem> infraItems = new ArrayList<InfraItem>();
		for(int i=0; i<items.size(); i++){
			if(cat_id != 0 && cat_id != items.get(i).getSafetyCategoryItem().getSafetyCategory().getId()){
				infraCategory.setId(cat_id);
				infraCategory.setName(cat_name);
				infraCategory.setDescription(cat_desc);
				infraCategory.setItems(infraItems);
				infraCategories.add(infraCategory);
				infraItems = new ArrayList<InfraItem>();
				infraCategory = new InfraCategory();
			}
			InfraItem infraItem = new InfraItem();
			infraItem.setId(items.get(i).getSafetyCategoryItem().getId());
			infraItem.setName(items.get(i).getSafetyCategoryItem().getName());
			infraItem.setDescription(items.get(i).getSafetyCategoryItem().getDescription());
			infraItems.add(infraItem);
			
			cat_id = items.get(i).getSafetyCategoryItem().getSafetyCategory().getId();
			cat_name = items.get(i).getSafetyCategoryItem().getSafetyCategory().getName();
			cat_desc = items.get(i).getSafetyCategoryItem().getSafetyCategory().getDesc();
		}
		infraCategory.setId(cat_id);
		infraCategory.setName(cat_name);
		infraCategory.setDescription(cat_desc);
		infraCategory.setItems(infraItems);
		infraCategories.add(infraCategory);
		return infraCategories;
	}
	
	public List<InfraCategory> getSchoolInfra(int id)
	{
		String hql = "from SchoolInfrastructureCatItem where school.id = :schoolId";
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("schoolId", id);
		List<SchoolInfrastructureCatItem> items = query.list();
		session.close();
		List<InfraCategory> infraCategories = new ArrayList<InfraCategory>();
		int cat_id = 0;
		String cat_name = "";
		InfraCategory infraCategory = new InfraCategory();
		List<InfraItem> infraItems = new ArrayList<InfraItem>();
		for(int i=0; i<items.size(); i++){
			if(cat_id != 0 && cat_id != items.get(i).getInfrastructureCategoryItem().getInfrastructureCategory().getId()){
				infraCategory.setId(cat_id);
				infraCategory.setName(cat_name);
				infraCategory.setItems(infraItems);
				infraCategories.add(infraCategory);
				infraItems = new ArrayList<InfraItem>();
				infraCategory = new InfraCategory();
			}
			InfraItem infraItem = new InfraItem();
			infraItem.setId(items.get(i).getInfrastructureCategoryItem().getId());
			infraItem.setName(items.get(i).getInfrastructureCategoryItem().getName());
			infraItem.setDescription(items.get(i).getDescription());
			infraItem.setItemCount(items.get(i).getCountItemValue());
			infraItem.setCharges(items.get(i).getCharges());
			infraItems.add(infraItem);
			cat_id = items.get(i).getInfrastructureCategoryItem().getInfrastructureCategory().getId();
			cat_name = items.get(i).getInfrastructureCategoryItem().getInfrastructureCategory().getName();
		}
		infraCategory.setId(cat_id);
		infraCategory.setName(cat_name);
		infraCategory.setItems(infraItems);
		infraCategories.add(infraCategory);
		return infraCategories;
	}
	
	public List<Rating> getSchoolRating(Integer schoolId)
	{
		String HQL = "SELECT ratingCategoryType.id as catid, ratingCategoryType.categoryName as name, avg(rating) as rating, count(ratingCategoryType.id) as ratingCount, COALESCE(ratingCategoryType.image,'') as image from UserRating where school.id = :schoolId GROUP BY ratingCategoryType.id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		
		Query query = session.createQuery(HQL).setResultTransformer(Transformers.aliasToBean(Rating.class));
		query.setParameter("schoolId", schoolId);
		session.flush();
		List<Rating> schoolRatings = query.list();
		if(schoolRatings.size() <= 0){
			String newhql = "SELECT id as catid, categoryName as name, 0.0 as rating, 0L as ratingCount, COALESCE(image,'') as image from RatingCategoryType";
			Session newsession = hibernateUtil.openSession();
			Query newquery = newsession.createQuery(newhql).setResultTransformer(Transformers.aliasToBean(Rating.class));
			newsession.flush();
			schoolRatings = newquery.list();
		}
		
		return schoolRatings;
	}
	
	public ResponseMessage addSchoolRating(RatingData ratingData)
	{
		ResponseMessage msg = new ResponseMessage();
		ArrayList<String> errors = new ArrayList<String>();
		if (ratingData.getSchoolId() > 0 && ratingData.getUserId() > 0 && ratingData.getRatings().size() > 0) {
			try{
				School school = new School();
				school.setId(ratingData.getSchoolId());
				UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
				userRegistrationInfo.setId(ratingData.getUserId());
				SchoolRating schoolRating = new SchoolRating();
				schoolRating.setSchool(school);
				HibernateUtil hibernateUtil = new HibernateUtil();
				Session session = hibernateUtil.openSession();
				session.beginTransaction();
				for(int i=0; i<ratingData.getRatings().size();i++){
					UserRating userRating = new UserRating();
					userRating.setSchool(school);
					userRating.setUserRegistrationInfo(userRegistrationInfo);
					userRating.setRating((float)ratingData.getRatings().get(i).getRating());
					RatingCategoryType ratingCategoryType = new RatingCategoryType();
					ratingCategoryType.setId(ratingData.getRatings().get(i).getCatid());
					userRating.setRatingCategoryType(ratingCategoryType);
					session.save(userRating);
				}
				session.getTransaction().commit();
				session.flush();
				updateSchoolFinalRating(schoolRating);
				msg.setStatus(1);
				msg.setMessage("Rating added successfully.");
			} catch(Exception e) {
				errors.add(e.getMessage());
				msg.setStatus(0);
				msg.setErrors(errors);
			}
		} else {
			if(ratingData.getSchoolId() <= 0){
				errors.add("School Id Can not be empty");
			}
			if(ratingData.getUserId() <= 0){
				errors.add("User Id Can not be empty");
			}
			if(ratingData.getRatings().size() <= 0){
				errors.add("Rating data can not be empty");
			}
			msg.setStatus(0);
			msg.setErrors(errors);
		}
		return msg;
	}
	
	public void updateSchoolFinalRating(SchoolRating schoolRating){
		String HQL = "SELECT COUNT(id) as userCount, SUM(rating) as totalRating from UserRating where school.id = :schoolId";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(HQL).setResultTransformer(Transformers.aliasToBean(TotalRating.class));
		query.setParameter("schoolId", schoolRating.getSchool().getId());
		List<TotalRating> totalRatings = query.list();
		session.flush();
		System.out.println("total rating:"+totalRatings.get(0).getTotalRating()+" count"+totalRatings.get(0).getUserCount());
		if(totalRatings.size() > 0){
			String sql = "from SchoolRating where school.id ="+schoolRating.getSchool().getId();
			Session sqlseSession = hibernateUtil.openSession();
			Query sqlQuery = sqlseSession.createQuery(sql);
			boolean newRating = sqlQuery.list().size() > 0? true: false; 
			sqlseSession.flush();
			/*String newhql = "SELECT id from RatingCategoryType";
			Session newsession1 = hibernateUtil.openSession();
			Query newquery1 = newsession1.createQuery(newhql);
			int catSize = newquery1.list().size() > 0 ?newquery1.list().size():0;
			newsession1.flush();*/
			if(newRating){
				long calculated_rating = Math.round(totalRatings.get(0).getTotalRating()/totalRatings.get(0).getUserCount()*100);
				float final_rating = (float) calculated_rating/100;
				System.out.println("Rating:"+final_rating);
				String updatehql = "UPDATE SchoolRating set rating="+final_rating+" where school.id="+schoolRating.getSchool().getId();
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				Query newquery = newsession.createQuery(updatehql);
				newquery.executeUpdate();
				newsession.getTransaction().commit();
				newsession.flush();
			} else {
				long calculated_rating = Math.round(totalRatings.get(0).getTotalRating()/totalRatings.get(0).getUserCount()*100);
				float final_rating = (float) calculated_rating/100;
				System.out.println("Rating:"+final_rating);
				schoolRating.setRating(final_rating);
				Session newsession = hibernateUtil.openSession();
				newsession.beginTransaction();
				newsession.save(schoolRating);
				newsession.getTransaction().commit();
				newsession.flush();
			}
		}
	}
	
	public List<NameList> getBloodGroupList(){
		String hql = "select id as id, name as name from BloodGroup";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(NameList.class));
		List<NameList> result = query.list();
		session.close();
		return result;
	}
	
	public List<NameList> getCastList(){
		String hql = "select id as id, name as name from Cast";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(NameList.class));
		List<NameList> result = query.list();
		session.close();
		return result;
	}
	
	public List<VacantSeats> getVacantSeatsBySchoolIdByStandardId( Integer schoolId, Short standardId ) {
		String hql = "FROM ClassInfo WHERE school.id = :school_id AND standardType.id = :standard_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql)
				.setParameter("school_id", schoolId)
				.setParameter("standard_id", standardId);
		List<ClassInfo> result = query.list();
		
		VacantSeats vacantSeat = new VacantSeats();
		List<VacantSeats> vacantSeats = new ArrayList<VacantSeats>();
		for(int i=0; i< result.size(); i++){
			vacantSeat.setId(result.get(i).getId());
			vacantSeat.setSchoolId(schoolId);
			vacantSeat.setStandardId(standardId);
			vacantSeat.setVacantSeat(result.get(i).getVacantSeat());
			vacantSeats.add(vacantSeat);
		}
		
		session.close();
		return vacantSeats;
	}
	
	public List<SchoolAnalyticsData> getContactClicksBySchoolId( Integer schoolId ) {
		String hql = "FROM SchoolAnalytics WHERE school.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql)
				.setParameter("school_id", schoolId);
		List<SchoolAnalytics> result = query.list();
		
		SchoolAnalyticsData schoolAnalyticsData = new SchoolAnalyticsData();
		List<SchoolAnalyticsData> schoolAnalyticsDataList = new ArrayList<SchoolAnalyticsData>();
		for(int i=0; i< result.size(); i++){
			schoolAnalyticsData.setSchoolId(schoolId);
			schoolAnalyticsData.setContactClicks(result.get(i).getContactClicks());
			schoolAnalyticsDataList.add(schoolAnalyticsData);
		}
		
		session.close();
		return schoolAnalyticsDataList;
	}
	
	public void updateContactClicksBySchoolId( Integer schoolId ) {
		String hql = "FROM SchoolAnalytics WHERE school.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql)
				.setParameter("school_id", schoolId);
		List<SchoolAnalytics> result = query.list();
		
		if(result.isEmpty() != true) {
			Integer contactClicks=0;
			for(int i=0; i< result.size(); i++) {
				contactClicks = result.get(i).getContactClicks();
			}
			++contactClicks;
			hql = "UPDATE SchoolAnalytics SET contact_clicks = :contact_clicks WHERE school.id = :school_id";
			query = session.createQuery(hql).setParameter("contact_clicks", contactClicks).setParameter("school_id",schoolId);
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			session.flush();
		} else {
			SchoolAnalytics schoolAnalytics = new SchoolAnalytics();
			schoolAnalytics.setContactClicks(1);
			hql = "FROM School where id = :school_id";
			query = session.createQuery(hql).setParameter("school_id",schoolId);
			schoolAnalytics.setSchool((School)query.uniqueResult());
			
			session.beginTransaction();
			session.save(schoolAnalytics);
			session.getTransaction().commit();
			session.flush();
		}
		
		session.close();
	}
	
	public List<NearbySchoolList> getNearbySchoolByLatitudeByLogitude(SearchRequest searchRequest) {
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();
		String distance = "ROUND(6371 *  "
			+ " ACOS(COS( RADIANS(" + searchRequest.getLatitude() + ") ) * COS( RADIANS( s.latitude ) ) * " 
			+ " COS(RADIANS( s.longitude ) - RADIANS(" + searchRequest.getLongitude() + ") ) "
			+ " + SIN(RADIANS("+ searchRequest.getLatitude() +")) * SIN(RADIANS(s.latitude)) ),6)";
		
		String hql = "SELECT s.schoolId as schoolId, "
			+ " s.name as name, "
			+ " s.localityName as localityName, "
			+ " s.rating as rating, "
			+ " s.homeImage as homeImage, "
			+ " s.mediums as mediums, "
			+ " ci.vacantSeat as vacantSeat, "
			+ " ci.totalFee as totalFee, "
			+ " ci.standardType.id as standardId, "
			+ distance+" as distance"
			+ " FROM SchoolSearch s, ClassInfo ci"
			+ " WHERE ci.school.id = s.schoolId";
		if(searchRequest.getStandardId() != 0) {
			hql = hql + " AND ci.standardType.id = :standard_id "; 
		}
		if(searchRequest.getLatitude() != null && searchRequest.getLongitude() != null){
			hql = hql + " AND "+distance+" < 6";
		}
		hql = hql + " GROUP BY s.schoolId " ;
		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(NearbySchoolList.class));
		if(searchRequest.getStandardId() != 0) {
			query.setParameter("standard_id", searchRequest.getStandardId()); 
		}
		List<NearbySchoolList> nearbySchools = query.list();
		session.close();
		
		return nearbySchools;
	}
	
	public List<PrevStudentProfile> getSchoolAchievmentsBySchoolId( Integer schoolId ) {
		String hql = "SELECT psp.id as id, psp.name as name, psp.batch as batch, psp.achievements as achievements "
				+ " FROM PrevStudentProfile psp "
				+ " WHERE school.id = :school_id";
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.openSession();
		Query query = session.createQuery(hql)
				.setParameter("school_id", schoolId)
				.setResultTransformer(Transformers.aliasToBean(PrevStudentProfile.class));
		List<PrevStudentProfile> result = query.list();
		session.close();
		return result;
	}
	
	public List<SchoolList> getTopSchools() {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();

		String hql = "SELECT s.schoolId as schoolId, s.name as name,"
					 + " s.homeImage as homeImage, s.logo as logo,"
				     + " s.localityName as localityName,"
				     + " s.cityName as cityName, s.rating as rating"
					 + " FROM SchoolSearch s, School schl"
					 + " WHERE s.schoolId = schl.id AND schl.promote = 1";

		Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(SchoolList.class));
		List<SchoolList> result = query.list();
		session.close();
		return result;
	}
	
	public ResponseMessage getUriByLatitudeLongitudeByStandard(String latitude, String longitude, Short standardId) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		Session session = hibernateUtil.getSessionFactory().openSession();

		String sql = "SELECT "
				+ " CONCAT(REPLACE(c.name, ' ', '-') , '/', REPLACE(l.name,' ', '-'), '/', REPLACE(st.name, ' ', '-')) as Uri "
				+ " FROM locality l JOIN city c ON l.city_id = c.id, standard_type st "
				+ " WHERE st.id = :standardId "
				+ " AND (ROUND(6371 *   ACOS(COS( RADIANS( :latitude ) ) * COS( RADIANS( l.latitude ) ) *  COS(RADIANS( l.longitude ) - RADIANS(:longitude) )  + SIN(RADIANS( :latitude)) * SIN(RADIANS(l.latitude)) ),3 )) < 3 "
				+ " ORDER BY (ROUND(6371 * ACOS(COS( RADIANS( :latitude ) ) * COS( RADIANS( l.latitude ) ) * COS(RADIANS( l.longitude ) - RADIANS(:longitude) ) + SIN(RADIANS( :latitude)) * SIN(RADIANS(l.latitude)) ),3 )) ASC "
				+ " LIMIT 1";
		Query query = session.createSQLQuery(sql)
				.setParameter("standardId", standardId)
				.setParameter("latitude", latitude)
				.setParameter("longitude", longitude)
				.setResultTransformer(Transformers.aliasToBean(UriData.class));
		
		UriData result = (UriData)query.uniqueResult();
		ResponseMessage responseMessage = new ResponseMessage();
		if( result == null ) { 
			responseMessage.setMessage("No school found.");
			responseMessage.setStatus(0);
		} else {
			Gson gson = new Gson();
			responseMessage.setMessage("School found.");
			responseMessage.setData(result);
			responseMessage.setStatus(1);
		}
		return responseMessage;
	}
}
