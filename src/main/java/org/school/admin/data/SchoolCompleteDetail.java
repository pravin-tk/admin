package org.school.admin.data;

import java.util.List;
import java.util.Set;

import org.school.admin.model.PrevStudentProfile;
import org.school.admin.model.SchoolHighlight;
import org.school.admin.model.SchoolImageGallery;
import org.school.admin.model.SchoolPanoramicImage;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolTimeline;

public class SchoolCompleteDetail {
	List<NameList> highlights;
	List<SchoolReview> reviews;
	SchoolContact contacts;
	private List<SchoolTimelineData> schoolTimelineData;
	private List<SchoolFacilityData> schoolFacilityData;
	private List<GalleryData> images;
	private List<SchoolPanoramicImage> panorama;
	private List<FeeDetail> fees;
	private Facility facility;
	private List<Rating> rating;
	private List<PrevStudentProfile> schoolAchievements;

	public List<SchoolTimelineData> getSchoolTimelineData() {
		return schoolTimelineData;
	}

	public void setSchoolTimelineData(List<SchoolTimelineData> schoolTimelineData) {
		this.schoolTimelineData = schoolTimelineData;
	}

	public List<NameList> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<NameList> highlights) {
		this.highlights = highlights;
	}

	public List<SchoolReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<SchoolReview> reviews) {
		this.reviews = reviews;
	}

	public SchoolContact getContacts() {
		return contacts;
	}

	public void setContacts(SchoolContact contacts) {
		this.contacts = contacts;
	}

	public List<SchoolFacilityData> getSchoolFacilityData() {
		return schoolFacilityData;
	}

	public void setSchoolFacilityData(List<SchoolFacilityData> schoolFacilityData) {
		this.schoolFacilityData = schoolFacilityData;
	}

	public List<GalleryData> getImages() {
		return images;
	}

	public void setImages(List<GalleryData> images) {
		this.images = images;
	}

	public List<SchoolPanoramicImage> getPanorama() {
		return panorama;
	}

	public void setPanorama(List<SchoolPanoramicImage> panorama) {
		this.panorama = panorama;
	}

	public List<FeeDetail> getFees() {
		return fees;
	}

	public void setFees(List<FeeDetail> fees) {
		this.fees = fees;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	public List<PrevStudentProfile> getSchoolAchievements() {
		return schoolAchievements;
	}

	public void setSchoolAchievements(List<PrevStudentProfile> schoolAchievements) {
		this.schoolAchievements = schoolAchievements;
	}
	
}
