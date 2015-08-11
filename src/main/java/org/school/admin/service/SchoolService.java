package org.school.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.school.admin.data.RatingData;
import org.school.admin.data.RatingReviewData;
import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.dao.SchoolSearchImpl;
import org.school.admin.data.SchoolCompleteDetail;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.School;
import org.school.admin.model.SchoolBoard;
import org.school.admin.model.SchoolNameList;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.UserRegistrationInfo;

public class SchoolService {

	public ResponseMessage addSchool(School School, Short boardId)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		
		return schoolDAOImp.save(School,boardId);
	}
	
	public List<School> getSchoolList(int school_id, int city_id, int locality_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolList(school_id, city_id, locality_id);
	}
	public List<School> getSchoolList(String contact_id,int cityId){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolList(contact_id,cityId);
	}
	
	public List<SchoolNameList> getSchoolNameList(int school_id, int city_id, int locality_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolNameList(school_id, city_id, locality_id);
	}
	
	public List<School> getSchoolDetails(int school_id)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolDetails(school_id);
	}
	public ResponseMessage updateSchool(School School,SchoolBoard schoolBoard)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.updateSchool(School,schoolBoard);
	}
	
	public SchoolCompleteDetail getSchoolCompleteDetails(int schoolId)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		SchoolCompleteDetail schoolCompleteDetail = new SchoolCompleteDetail();
		schoolCompleteDetail.setSchoolTimelineData(schoolDAOImp.getSchoolTimelineDetails(schoolId));
		return schoolCompleteDetail;
	}
	
	public ResponseMessage addOrUpdateSchoolRatingAndReview(RatingReviewData ratingReviewData) {

		RatingData ratingData = new RatingData();
		ratingData.setRatings(ratingReviewData.getRatings());
		ratingData.setSchoolId(ratingReviewData.getSchoolId());
		ratingData.setUserId(ratingReviewData.getUserId());
		ResponseMessage ratingResponse = new SchoolSearchImpl().addSchoolRating(ratingData);
		if(ratingResponse.getStatus() == 1) {
			SchoolReview schoolReview = new SchoolReview();
		    Byte reviewStatus =0;
		    schoolReview.setId(ratingReviewData.getReviewId());
			schoolReview.setDate(new Date());
			schoolReview.setTime(new Date());
			schoolReview.setStatus(reviewStatus);
			schoolReview.setReview(ratingReviewData.getReview());
			schoolReview.setTitle(ratingReviewData.getTitle());
			
			School school = new School();
			school.setId(ratingReviewData.getSchoolId());
			schoolReview.setSchool(school);
			
			UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
			userRegistrationInfo.setId(ratingReviewData.getUserId());
			schoolReview.setUserRegistrationInfo(userRegistrationInfo);
			
			ResponseMessage reviewResponse = new SchoolDAOImp().saveSchoolReview(schoolReview);
			if(reviewResponse.getStatus() == 1 ) {
				ratingResponse.setMessage("Rating and Review saved successfully.");
			} else {
				ratingResponse.setMessage(reviewResponse.getMessage());
				ratingResponse.setErrors(reviewResponse.getErrors());
			}
		}

		return ratingResponse;
	}
}
