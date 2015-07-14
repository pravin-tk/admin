package org.school.admin.controller;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.school.admin.dao.ContactDetaillDAO;
import org.school.admin.dao.PrevStudentProfileDAO;
import org.school.admin.dao.SalesDetailDAOImpl;
import org.school.admin.dao.SchoolAchievementDAO;
import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.data.ClassDetail;
import org.school.admin.data.InfrastructureDetail;
import org.school.admin.data.PrevStudentProfileList;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.Accessories;
import org.school.admin.model.AdminUser;
import org.school.admin.model.AdminUserRole;
import org.school.admin.model.ClassAccessories;
import org.school.admin.model.ClassFee;
import org.school.admin.model.ClassInfo;
import org.school.admin.model.ClassSection;
import org.school.admin.model.ClassSubjects;
import org.school.admin.model.ContactInfo;
import org.school.admin.model.FeeType;
import org.school.admin.model.Locality;
import org.school.admin.model.PrevStudentProfile;
import org.school.admin.model.SalesInfo;
import org.school.admin.model.School;
import org.school.admin.model.SchoolActivityCatItem;
import org.school.admin.model.SchoolHighlight;
import org.school.admin.model.SchoolImageGallery;
import org.school.admin.model.SchoolInfo;
import org.school.admin.model.SchoolInfrastructureCatItem;
import org.school.admin.model.SchoolNameList;
import org.school.admin.model.SchoolTimeline;
import org.school.admin.model.SchoolTimelineMilestone;
import org.school.admin.model.SectionType;
import org.school.admin.model.StandardType;
import org.school.admin.model.StreamType;
import org.school.admin.model.Subject;
import org.school.admin.model.TeachingApproachType;
import org.school.admin.service.SchoolService;

@Path("school")
public class SchoolController extends ResourceConfig {
	@Context ServletContext context;
	public SchoolController() {
		register(MultiPartFeature.class);
    }
	
	/*---------PANKAJ------*/
	/*--------Registration --------*/
	/**
	 * @param name,email,password,mobile_no
	 * @return ResponseMessage
	 */
	@POST
	@Path("/saveuser")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveRegistration(
	@FormParam("registartion_name") String name,
	@FormParam("registration_email") String email,
	@FormParam("registration_password") String password,
	@FormParam("registration_mobleno") String mobileNo,
	@FormParam("user_type") int user_type,
	@FormParam("status") int status
	)
	{
		System.out.println("registartion_name : "+name);
		AdminUser adminUser = new AdminUser();
		AdminUserRole adminUserRole = new AdminUserRole();
		
		adminUserRole.setId(user_type);
		adminUser.setAdminUserRole(adminUserRole);
		adminUser.setName(name);
		adminUser.setEmail(email);
		adminUser.setMobile(mobileNo);
		adminUser.setPassword(password);
		adminUser.setStatus(status);
		
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.saveAdminUser(adminUser);
	}
/*------------------------------------------------------*/

	@GET
	@Path("/list/{school_id}/{city_id}/{locality_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<School> schoollist(@PathParam("school_id") int school_id, 
									 @PathParam("city_id") int city_id,
									 @PathParam("locality_id") int locality_id) {
		SchoolService schoolService = new SchoolService();
		List<School> schoollist = schoolService.getSchoolList(school_id, city_id, locality_id);
		
		return schoollist;
	}
	
	@GET
	@Path("/htmllist/{school_id}/{city_id}/{locality_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<School> schoolhtmllist(@PathParam("school_id") int school_id, 
									 @PathParam("city_id") int city_id,
									 @PathParam("locality_id") int locality_id) {
		SchoolService schoolService = new SchoolService();
		List<School> schoollist = schoolService.getSchoolList(school_id, city_id, locality_id);
		return schoollist;
	}
	
	@GET
	@Path("/namelist/{school_id}/{city_id}/{locality_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolNameList> schoolnamelist(@PathParam("school_id") int school_id, 
									 @PathParam("city_id") int city_id,
									 @PathParam("locality_id") int locality_id) {
		SchoolService schoolService = new SchoolService();
		return schoolService.getSchoolNameList(school_id, city_id, locality_id);
	}
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSchool(
		@FormParam("school_id") int school_id,
		@FormParam("school_name") String school_name,
		@FormParam("plot_no") String plot_no,
		@FormParam("locality_id") int locality_id,
		@FormParam("street_name") String street_name,
		@FormParam("landmark") String landmark,
		@FormParam("pincode") String pincode, 
		@FormParam("latitude") String latitude,
		@FormParam("longitude") String longitude,
		@FormParam("alias") String alias,
		@FormParam("about_school") String about_school,
		@FormParam("tag_line") String tag_line,
		@FormParam("establishment_type") byte establishment_type,
		@FormParam("registration_id") int registration_id,
		@FormParam("isFreeListing") Byte isFreeListing,
		@FormParam("trialStartDate") String trial_StartDate,
		@FormParam("trialEndDate") String trial_EndDate
	){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date trialStartDate = null,trialEndDate = null;
		try {
			trialStartDate = format.parse(trial_StartDate);
			trialEndDate = format.parse(trial_EndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		School School = new School();
		
		Locality locality = new Locality();
		locality.setId(locality_id);
		System.out.println("User id : "+registration_id);
		School.setId(school_id);
		School.setName(school_name);
		School.setAboutSchool(about_school);
		School.setAlias(alias);
		School.setLastUpdatedBy(registration_id);
		if(establishment_type == 1)
		School.setEstablishmentType(true);
		else
			School.setEstablishmentType(false);
		School.setLandmark(landmark);
		School.setLatitude(latitude);
		School.setLocality(locality);
		School.setLongitude(longitude);
		School.setPincode(pincode);
		School.setPlotNo(plot_no);
		School.setStreetName(street_name);
		School.setTagLine(tag_line);
		School.setIsFreelisting(isFreeListing);
		School.setTrialStartDate(trialStartDate);
		School.setTrialEndDate(trialEndDate);
	    
		SchoolService schoolService = new SchoolService();
		return schoolService.updateSchool(School);
	}
	
	@POST
	@Path("/addschool")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addSchool(
		@FormParam("school_name") String school_name,
		@FormParam("plot_no") String plot_no,
		@FormParam("locality_id") int locality_id,
		@FormParam("street_name") String street_name,
		@FormParam("landmark") String landmark,
		@FormParam("pincode") String pincode, 
		@FormParam("latitude") String latitude,
		@FormParam("longitude") String longitude,
		@FormParam("alias") String alias,
		@FormParam("about_school") String about_school,
		@FormParam("tag_line") String tag_line,
		@FormParam("establishment_type") byte establishment_type,
		@FormParam("registration_id") int registration_id,
		@FormParam("isFreeListing") Byte isFreeListing,
		@FormParam("trialStartDate") String trial_StartDate,
		@FormParam("trialEndDate") String trial_EndDate
	){
		Byte status = 0,promote =0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date trialStartDate = null,trialEndDate = null;
		try {
			trialStartDate = format.parse(trial_StartDate);
			trialEndDate = format.parse(trial_EndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		School School = new School();
		
		Locality locality = new Locality();
		locality.setId(locality_id);
		School.setName(school_name);
		School.setAboutSchool(about_school);
		School.setAlias(alias);
		School.setCreatedBy(registration_id);
		if(establishment_type == 1)
		School.setEstablishmentType(true);
		else
			School.setEstablishmentType(false);
		School.setLandmark(landmark);
		School.setLatitude(latitude);
		School.setLocality(locality);
		School.setLongitude(longitude);
		School.setPincode(pincode);
		School.setPlotNo(plot_no);
		School.setStreetName(street_name);
		School.setStatus(status);
		School.setTagLine(tag_line);
		School.setPromote(promote);
		School.setIsFreelisting(isFreeListing);
		School.setTrialStartDate(trialStartDate);
		School.setTrialEndDate(trialEndDate);
		SchoolService schoolService = new SchoolService();
		
 
		return schoolService.addSchool(School);
	}
	
	@POST
	@Path("updatepromote")
	public void updatePromote(@FormParam("schoolId")int schoolId,@FormParam("promote") Byte promote)
	{
		Byte zero = 0;
		Byte one = 1;
		School school = new School();
		school.setId(schoolId);
		if(promote == 1)
		school.setPromote(zero);
		else
		school.setPromote(one);
		new SchoolDAOImp().updatePromote(school);
	}
	
	
	@POST
	@Path("/schoolachievement")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveSchoolAchievement(
		@FormParam("school_id") int school_id,
		@FormParam("user_id") int user_id,
		@FormParam("award_name") String award_name,
		@FormParam("school_tieup") String school_tieup,
		@FormParam("approval") String approval
	){
		ResponseMessage response = new ResponseMessage();
		School school = new School();
		school.setId(school_id);
		SchoolInfo schoolInfo = new SchoolInfo();
		schoolInfo.setApprovalDesc("");
		schoolInfo.setAwardDesc("");
		schoolInfo.setTieUpDesc("");
		try {
			schoolInfo.setApprovalDesc(approval);
			schoolInfo.setAwardDesc(award_name);
			schoolInfo.setTieUpDesc(school_tieup);
		} catch(NullPointerException e) {
			//null values
		}
		schoolInfo.setLastUpdatedBy(user_id);
		schoolInfo.setSchool(school);
		SchoolAchievementDAO schoolAchievementDAO = new SchoolAchievementDAO();
		response = schoolAchievementDAO.saveSchoolAchievement(schoolInfo);
		return response;
	}

	@POST
	@Path("savecontact")
	public List<ContactInfo> saveContactInfo(
			@FormParam("school_id") int school_id,
			@FormParam("user_id") int user_id,
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("mobile") String mobile,
			@FormParam("type") Byte type
	){
		School school = new School();
		school.setId(school_id);
		
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setSchool(school);
		contactInfo.setEmail(email);
		contactInfo.setName(name);
		contactInfo.setMobileNo(mobile);
		contactInfo.setLastUpdatedBy(user_id);
		contactInfo.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		contactInfo.setType(type);
		ContactDetaillDAO contactDetaillDAO = new ContactDetaillDAO();
		
		return contactDetaillDAO.saveContactInfoInternal(contactInfo);
	}
	
	@POST
	@Path("updatecontact")
	public List<ContactInfo> updateContactInfo(
			@FormParam("id") int id,
			@FormParam("school_id") int school_id,
			@FormParam("user_id") int user_id,
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("mobile") String mobile,
			@FormParam("type") Byte type
	){
		School school = new School();
		school.setId(school_id);
		
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setId(id);
		contactInfo.setSchool(school);
		contactInfo.setEmail(email);
		contactInfo.setName(name);
		contactInfo.setMobileNo(mobile);
		contactInfo.setLastUpdatedBy(user_id);
		contactInfo.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		contactInfo.setType(type);
		ContactDetaillDAO contactDetaillDAO = new ContactDetaillDAO();
		
		return contactDetaillDAO.updateContactInfoInternal(contactInfo);
	}
	
	@GET
	@Path("/contact_detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContactInfo getContactDetail(@PathParam("id") int id)
	{
		ContactDetaillDAO contactDetaillDAO = new ContactDetaillDAO();
        return contactDetaillDAO.getConatctDetailById(id).get(0);
	}
	
	@GET
	@Path("/sales_detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SalesInfo getSalesInfo(@PathParam("id") int id)
	{
		SalesDetailDAOImpl salesDetail = new SalesDetailDAOImpl();
		return salesDetail.getSalesDetailById(id);
	}
	
	@POST
	@Path("prestudent")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PrevStudentProfileList> savePreStudentProfile(
		@FormParam("school_id") int school_id,
		@FormParam("user_id") int user_id,
		@FormParam("name") String name,
		@FormParam("email") String email,
		@FormParam("mobile") String mobile,
		@FormParam("batch") String batch,
		@FormParam("achievements") String achievements
	){
		School school = new School();
		school.setId(school_id);
		PrevStudentProfile prevStudentProfile = new PrevStudentProfile();
		prevStudentProfile.setSchool(school);
		prevStudentProfile.setLastUpdatedBy(user_id);
		prevStudentProfile.setName(name);
		prevStudentProfile.setEmail(email);
		prevStudentProfile.setMobile(mobile);
		prevStudentProfile.setBatch(batch);
		prevStudentProfile.setAchievements(achievements);
		prevStudentProfile.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));;
		
		System.out.println(prevStudentProfile);
		
		PrevStudentProfileDAO prevStudentProfileDAO = new PrevStudentProfileDAO();
		prevStudentProfileDAO.savePreStudentProfile(prevStudentProfile);
        return prevStudentProfileDAO.getPrevStudentProfile(school_id);

	}
	
	@POST
	@Path("prestudent_update")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PrevStudentProfileList> updatePreStudentProfile(
		@FormParam("id") int id,
		@FormParam("school_id") int school_id,
		@FormParam("user_id") int user_id,
		@FormParam("name") String name,
		@FormParam("email") String email,
		@FormParam("mobile") String mobile,
		@FormParam("batch") String batch,
		@FormParam("achievements") String achievements
	){
		School school = new School();
		school.setId(school_id);
		PrevStudentProfile prevStudentProfile = new PrevStudentProfile();
		prevStudentProfile.setSchool(school);
		prevStudentProfile.setId(id);
		prevStudentProfile.setLastUpdatedBy(user_id);
		prevStudentProfile.setName(name);
		prevStudentProfile.setEmail(email);
		prevStudentProfile.setMobile(mobile);
		prevStudentProfile.setBatch(batch);
		prevStudentProfile.setAchievements(achievements);
		prevStudentProfile.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));;
		
		System.out.println(prevStudentProfile);
		
		PrevStudentProfileDAO prevStudentProfileDAO = new PrevStudentProfileDAO();
		prevStudentProfileDAO.updatePreStudentProfile(prevStudentProfile);
        return prevStudentProfileDAO.getPrevStudentProfile(school_id);

	}
			
	@GET
	@Path("/prestudent_detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PrevStudentProfileList getPreStudentDetail(@PathParam("id") int id)
	{
		PrevStudentProfileDAO prevStudentProfileDAO = new PrevStudentProfileDAO();
        return prevStudentProfileDAO.getPrevStudentProfileById(id).get(0);
	}
								
	
	@POST
	@Path("/saveclassdetail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMessage saveClassDetail(ClassDetail classDetail )
	{
		System.out.println(classDetail);
		/* save class accessory*/
		Double totalFee = 0.0;
		ClassInfo classInfo = classDetail.getClassInfo();
		Set<ClassAccessories> classAccessories = classDetail.getClassAccessories();
		if(classAccessories.size()>0)
		{
			Iterator<ClassAccessories> sIterator = classAccessories.iterator();
			
			while(sIterator.hasNext())
			{
				ClassAccessories classAccessoryItem = sIterator.next();
				classAccessoryItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));						
			}
		}
		Set<ClassSubjects> classSubjects = classDetail.getClassSubjects();
		if(classSubjects.size()>0)
		{
			Iterator<ClassSubjects> sIterator = classSubjects.iterator();
			
			while(sIterator.hasNext())
			{
				ClassSubjects classSubjItem = sIterator.next();
				classSubjItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));						
			}
		}
		Set<ClassFee> classFee = classDetail.getClassFee();
		if(classFee.size()>0)
		{
			Iterator<ClassFee> sIterator = classFee.iterator();
			
			while(sIterator.hasNext())
			{
				ClassFee classFeeItem = sIterator.next();
				classFeeItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				totalFee = totalFee + classFeeItem.getAmount();
			}
		}
		classInfo.setTotalFee(totalFee);
		classDetail.setClassInfo(classInfo);
		classDetail.setClassAccessories(classAccessories);
		classDetail.setClassSubjects(classSubjects);
		classDetail.setClassFee(classFee);
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.saveClassDetail(classDetail);
	}
	
	@POST
	@Path("/updateclassdetail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMessage updateClassDetail(ClassDetail classDetail)
	{
		System.out.println("deadline date: "+classDetail.getClassInfo().getAdmissionDeadline().toString());
		System.out.println("Class Id  : "+classDetail.getClassInfo().getId());
		/* save class accessory*/
		
		Double totalFee = 0.0;
		ClassInfo classInfo = classDetail.getClassInfo();
		Set<ClassAccessories> classAccessories = classDetail.getClassAccessories();
		if(classAccessories.size()>0)
		{
			Iterator<ClassAccessories> sIterator = classAccessories.iterator();
			
			while(sIterator.hasNext())
			{
				ClassAccessories classAccessoryItem = sIterator.next();
				classAccessoryItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));						
			}
		}
		Set<ClassSubjects> classSubjects = classDetail.getClassSubjects();
		if(classSubjects.size()>0)
		{
			Iterator<ClassSubjects> sIterator = classSubjects.iterator();
			
			while(sIterator.hasNext())
			{
				ClassSubjects classSubjItem = sIterator.next();
				classSubjItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));						
			}
		}
		Set<ClassFee> classFee = classDetail.getClassFee();
		if(classFee.size()>0)
		{
			Iterator<ClassFee> sIterator = classFee.iterator();
			
			while(sIterator.hasNext())
			{
				ClassFee classFeeItem = sIterator.next();
				classFeeItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				totalFee = totalFee + classFeeItem.getAmount();
			}
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date admissionDeadlineDate = null,admissionFromDate = null,admissionToDate = null;
//		try {
////			admissionDeadlineDate = format.parse(admission_deadline);
////			admissionFromDate = format.parse(admission_form);
////			admissionToDate = format.parse(admission_to);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Time time = null;
		try
		{
			 time = classInfo.getMorningTimeFrom();
			if(time == null)
				classInfo.setMorningTimeFrom(new Time(Calendar.getInstance().getTime().getTime()));
		}
		catch(NullPointerException e)
		{
			classInfo.setMorningTimeFrom(new Time(Calendar.getInstance().getTime().getTime()));
		}
		try
		{
			 time = classInfo.getMorningTimeTo();
			if(time ==null)
				classInfo.setMorningTimeTo(new Time(Calendar.getInstance().getTime().getTime()));
		}
		catch(NullPointerException e)
		{
			classInfo.setMorningTimeTo(new Time(Calendar.getInstance().getTime().getTime()));
		}
		try
		{
			 time = classInfo.getAfternoonTimeFrom();
			if(time == null)
				classInfo.setAfternoonTimeFrom(null);
		}
		catch(NullPointerException e)
		{
			classInfo.setAfternoonTimeFrom(null);
		}
		try
		{
			 time = classInfo.getAfternoonTimeTo();
			if(time == null)
				classInfo.setAfternoonTimeTo(null);
		}
		catch(NullPointerException e)
		{
			classInfo.setAfternoonTimeTo(null);
		}
		classInfo.setTotalFee(totalFee);
		classDetail.setClassInfo(classInfo);
		classDetail.setClassAccessories(classAccessories);
		classDetail.setClassSubjects(classSubjects);
		classDetail.setClassFee(classFee);
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		
		return schoolDAOImp.updateClassDetail(classDetail);
	}	
	@GET
	@Path("/section_info/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClassDetail getSectionInfo(@PathParam("id") int id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		List<ClassSection> classSections = schoolDAOImp.getClassSectionInfoById(id);
		
		List<ClassSection> newClassSection = new ArrayList<ClassSection>();
		for(int i=0; i < classSections.size(); i++){
			ClassSection classSection = new ClassSection();
			classSection.setAdmissionDeadline(classSections.get(i).getAdmissionDeadline());
			classSection.setAdmissionFrom(classSections.get(i).getAdmissionFrom());
			classSection.setAdmissionTo(classSections.get(i).getAdmissionTo());
			School school = new School();
			school.setId(classSections.get(i).getClassInfo().getSchool().getId());
			school.setName(classSections.get(i).getClassInfo().getSchool().getName());
			ClassInfo classInfo = new ClassInfo();
			classInfo.setId(classSections.get(i).getClassInfo().getId());
			classInfo.setSchool(school);
		//	classInfo.setAdmissionFee(classSections.get(i).getClassInfo().getAdmissionFee());
			classInfo.setAdmissionProcess(classSections.get(i).getClassInfo().getAdmissionProcess());
			classInfo.setEligibilityCriteria(classSections.get(i).getClassInfo().getEligibilityCriteria());
			classInfo.setFeesPaymentTerm(classSections.get(i).getClassInfo().getFeesPaymentTerm());
			classInfo.setHowToApply(classSections.get(i).getClassInfo().getHowToApply());
			//classInfo.setRegistrationFee(classSections.get(i).getClassInfo().getRegistrationFee());
			classInfo.setSpecialization(classSections.get(i).getClassInfo().getSpecialization());
			StandardType standardType = new StandardType();
			standardType.setId(classSections.get(i).getClassInfo().getStandardType().getId());
			standardType.setName(classSections.get(i).getClassInfo().getStandardType().getName());
			classInfo.setStandardType(standardType);
			TeachingApproachType teachingApproachType = new TeachingApproachType();
			teachingApproachType.setId(classSections.get(i).getClassInfo().getTeachingApproachType().getId());
			teachingApproachType.setName(classSections.get(i).getClassInfo().getTeachingApproachType().getName());
			classInfo.setTeachingApproachType(teachingApproachType);
			classSection.setClassInfo(classInfo);
			classSection.setId(classSections.get(i).getId());
			SectionType sectionType = new SectionType();
			sectionType.setId(classSections.get(i).getSectionType().getId());
			sectionType.setName(classSections.get(i).getSectionType().getName());
			classSection.setSectionType(sectionType);
			classSection.setTotalSeat(classSections.get(i).getTotalSeat());
			classSection.setVacantSeat(classSections.get(i).getVacantSeat());
			classSection.setClassInfo(classInfo);
			newClassSection.add(classSection);
			
		}
		int school_id = newClassSection.get(0).getClassInfo().getSchool().getId();
		int class_id = newClassSection.get(0).getClassInfo().getId();
		List<ClassSubjects> classSubjectList = schoolDAOImp.getClassSubjectsByClassId(school_id, class_id);
		Set<ClassSubjects> cs = new HashSet<ClassSubjects>();
		for(int i=0; i<classSubjectList.size(); i++){
			Subject subject2 = new Subject();
			subject2.setId(classSubjectList.get(i).getSubject().getId());
			
			ClassSubjects classSubjects2 = new ClassSubjects();
			classSubjects2.setSubject(subject2);
			cs.add(classSubjects2);
		}
		List<ClassAccessories> classAccessoryList = schoolDAOImp.getClassAccessoriesByClassId(class_id);
		Set<ClassAccessories> cass = new HashSet<ClassAccessories>();
		for(int i=0; i<classAccessoryList.size(); i++){
			Accessories accessory = new Accessories();
			accessory.setId(classAccessoryList.get(i).getAccessories().getId());
			
			ClassAccessories classAccess = new ClassAccessories();
			classAccess.setAccessories(accessory);
			cass.add(classAccess);
		}
		ClassDetail list = new ClassDetail();
		list.setClassSection(newClassSection.get(0));
		list.setClassSubjects(cs);
		list.setClassAccessories(cass);
		return list;
	}
	
	@GET
	@Path("/getclasslist/{school_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClassInfo> getClassList(@PathParam("school_id") int school_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		//List<ClassSection> classSections = schoolDAOImp.getClassSectionInfo(school_id);
		List<ClassInfo> classes= schoolDAOImp.getClassInfoBySchId(school_id);
		//List<ClassSection> newClassSection = new ArrayList<ClassSection>();
		List<ClassInfo> newClassInfo = new ArrayList<ClassInfo>();
		for(int i=0; i < classes.size(); i++){
			
			ClassInfo classInfo = new ClassInfo();
			
			StandardType standardType = new StandardType();
			standardType.setName(classes.get(i).getStandardType().getName());
			classInfo.setStandardType(standardType);
			
			TeachingApproachType teachingApproachType = new TeachingApproachType();
			teachingApproachType.setName(classes.get(i).getTeachingApproachType().getName());
			classInfo.setTeachingApproachType(teachingApproachType);
			
			StreamType streamType = new StreamType();
			streamType.setTitle(classes.get(i).getStreamType().getTitle());
			classInfo.setStreamType(streamType);
			classInfo.setId(classes.get(i).getId());
			
			classInfo.setTotalSeat(classes.get(i).getTotalSeat());
			classInfo.setVacantSeat(classes.get(i).getVacantSeat());
			newClassInfo.add(classInfo);
			
		}
		return newClassInfo;
	}
	
	@GET
	@Path("/getclasssection/{school_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClassSection> getClassSectionList(@PathParam("school_id") int school_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		List<ClassSection> classSections = schoolDAOImp.getClassSectionInfo(school_id);
		
		List<ClassSection> newClassSection = new ArrayList<ClassSection>();
		for(int i=0; i < classSections.size(); i++){
			ClassSection classSection = new ClassSection();
			ClassInfo classInfo = new ClassInfo();
			StandardType standardType = new StandardType();
			standardType.setName(classSections.get(i).getClassInfo().getStandardType().getName());
			classInfo.setStandardType(standardType);
			TeachingApproachType teachingApproachType = new TeachingApproachType();
			teachingApproachType.setName(classSections.get(i).getClassInfo().getTeachingApproachType().getName());
			classInfo.setTeachingApproachType(teachingApproachType);
			classSection.setClassInfo(classInfo);
			classSection.setId(classSections.get(i).getId());
			SectionType sectionType = new SectionType();
			sectionType.setName(classSections.get(i).getSectionType().getName());
			classSection.setSectionType(sectionType);
			classSection.setTotalSeat(classSections.get(i).getTotalSeat());
			classSection.setVacantSeat(classSections.get(i).getVacantSeat());
			classSection.setClassInfo(classInfo);
			newClassSection.add(classSection);
			
		}
		return newClassSection;
	}
	@GET
	@Path("/class_info/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClassDetail getClassInfo(@PathParam("id") int id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		int school_id = 0;
		int class_id = 0;
		//List<ClassSection> classSections = schoolDAOImp.getClassSectionInfoById(id);
		List<ClassInfo> classes= schoolDAOImp.getClassInfoById(id);
		//List<ClassSection> newClassSection = new ArrayList<ClassSection>();
		List<ClassInfo> newClassInfo = new ArrayList<ClassInfo>();
		for(int i=0; i < classes.size(); i++){
			//ClassSection classSection = new ClassSection();
			
			ClassInfo classInfo = new ClassInfo();
			classInfo.setId(classes.get(i).getId());
			classInfo.setAdmissionDeadline(classes.get(i).getAdmissionDeadline());
			classInfo.setAdmissionFrom(classes.get(i).getAdmissionFrom());
			classInfo.setAdmissionTo(classes.get(i).getAdmissionTo());
			
			classInfo.setAdmissionProcess(classes.get(i).getAdmissionProcess());
			classInfo.setEligibilityCriteria(classes.get(i).getEligibilityCriteria());
			classInfo.setFeesPaymentTerm(classes.get(i).getFeesPaymentTerm());
			classInfo.setHowToApply(classes.get(i).getHowToApply());
			classInfo.setSpecialization(classes.get(i).getSpecialization());
			classInfo.setTotalSeat(classes.get(i).getTotalSeat());
			classInfo.setVacantSeat(classes.get(i).getVacantSeat());
			classInfo.setMorningTimeFrom(classes.get(i).getMorningTimeFrom());
			classInfo.setMorningTimeTo(classes.get(i).getMorningTimeTo());
			classInfo.setAfternoonTimeFrom(classes.get(i).getAfternoonTimeFrom());
			classInfo.setAfternoonTimeTo(classes.get(i).getAfternoonTimeTo());
			School school = new School();
			school.setId(classes.get(i).getSchool().getId());
			school.setName(classes.get(i).getSchool().getName());
			classInfo.setSchool(school);
			
			StandardType standardType = new StandardType();
			standardType.setId(classes.get(i).getStandardType().getId());
			standardType.setName(classes.get(i).getStandardType().getName());
			classInfo.setStandardType(standardType);
			
			TeachingApproachType teachingApproachType = new TeachingApproachType();
			teachingApproachType.setId(classes.get(i).getTeachingApproachType().getId());
			teachingApproachType.setName(classes.get(i).getTeachingApproachType().getName());
			classInfo.setTeachingApproachType(teachingApproachType);
			
			StreamType streamType = new StreamType();
			streamType.setId(classes.get(i).getStreamType().getId());
			streamType.setTitle(classes.get(i).getStreamType().getTitle());
			classInfo.setStreamType(streamType);
			
			newClassInfo.add(classInfo);
			school_id = classes.get(i).getSchool().getId();
			class_id = classes.get(i).getId();
			
		}
		
		List<ClassSubjects> classSubjectList = schoolDAOImp.getClassSubjectsByClassId(school_id, class_id);
		Set<ClassSubjects> cs = new HashSet<ClassSubjects>();
		for(int i=0; i<classSubjectList.size(); i++){
			Subject subject2 = new Subject();
			subject2.setId(classSubjectList.get(i).getSubject().getId());
			
			ClassSubjects classSubjects2 = new ClassSubjects();
			classSubjects2.setSubject(subject2);
			cs.add(classSubjects2);
		}
		List<ClassAccessories> classAccessoryList = schoolDAOImp.getClassAccessoriesByClassId(class_id);
		Set<ClassAccessories> cass = new HashSet<ClassAccessories>();
		for(int i=0; i<classAccessoryList.size(); i++){
			Accessories accessory = new Accessories();
			accessory.setId(classAccessoryList.get(i).getAccessories().getId());
			
			ClassAccessories classAccess = new ClassAccessories();
			classAccess.setAccessories(accessory);
			cass.add(classAccess);
		}
		
		List<ClassFee> classFeeList = schoolDAOImp.getClassFeeByClassId(class_id);
		Set<ClassFee> classfee= new HashSet<ClassFee>();
		for(int i=0; i<classFeeList.size(); i++){
			ClassFee fee = new ClassFee();
			fee.setId(classFeeList.get(i).getId());
			fee.setFeeDesc(classFeeList.get(i).getFeeDesc());
			fee.setAmount(classFeeList.get(i).getAmount());
			classfee.add(fee);
		}
		ClassDetail classDetaiL = new ClassDetail();
		classDetaiL.setClassInfo(newClassInfo.get(0));
		classDetaiL.setClassSubjects(cs);
		classDetaiL.setClassAccessories(cass);
		classDetaiL.setClassFee(classfee);
		return classDetaiL;
	}
	@POST
	@Path("saveinfrastructure")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMessage saveInfrastructure(InfrastructureDetail infrastructureDetail) {
		ResponseMessage msg = new ResponseMessage();
		try{
			Set<SchoolActivityCatItem> activitycateitems = infrastructureDetail.getSchoolActivityCatItems();
			if(activitycateitems.size()>0)
			{
				Iterator<SchoolActivityCatItem> sIterator = activitycateitems.iterator();
				
				while(sIterator.hasNext())
				{
					SchoolActivityCatItem schoolActivityCatItem = sIterator.next();
					schoolActivityCatItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));						
				}
			}
			infrastructureDetail.setSchoolActivityCatItems(activitycateitems);
			
			Set<SchoolInfrastructureCatItem> schoolInfrastructureCatItems = infrastructureDetail.getSchoolInfrastructureCatItems();
			if(schoolInfrastructureCatItems.size()>0)
			{
				Iterator<SchoolInfrastructureCatItem> sIterator = schoolInfrastructureCatItems.iterator();
				
				while(sIterator.hasNext())
				{
					SchoolInfrastructureCatItem schoolInfrastructureCatItem = sIterator.next();
					schoolInfrastructureCatItem.setLastUpdatedOn(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				}
			}
			
			infrastructureDetail.setSchoolInfrastructureCatItems(schoolInfrastructureCatItems);
			
			SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
			String message = schoolDAOImp.saveInfrastructure(infrastructureDetail);
			msg.setStatus(1);
			msg.setMessage(message);
		} catch(Exception e){
			msg.setStatus(0);
			msg.setMessage("Unable to update infra");
		}
		return msg;
	}
	
	@SuppressWarnings("null")
	@POST
	@Path("/savetimeline")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseMessage saveSchoolTimeline(
			@FormDataParam("school_id") Integer school_id,
			@FormDataParam("user_id") Integer user_id,
			@FormDataParam("year") Short year, 
			@FormDataParam("title") String title, 
			@FormDataParam("classes_upto") String classes_upto, 
			@FormDataParam("milestoneTitle[]") List<FormDataBodyPart> milestones,
			@FormDataParam("milestoneDesc[]") List<FormDataBodyPart> milestonesDescription,
			@FormDataParam("image") InputStream is, 
			@FormDataParam("image") FormDataContentDisposition header
	){
		ResponseMessage msg = new ResponseMessage();
		List<String> milestoneTitle = new ArrayList<String>();
		List<String> milestoneDesc = new ArrayList<String>();
		if(milestones.size() > 0){
			for(FormDataBodyPart milestone : milestones){
				String mtitle = milestone.getValueAs(String.class).toString();
				if(mtitle != null)
				milestoneTitle.add(mtitle);
			}
		}
		
		if(milestonesDescription.size() > 0){
			for(FormDataBodyPart desc : milestonesDescription){
				String mdesc = desc.getValueAs(String.class);
				if(mdesc != null)
				milestoneDesc.add(mdesc);
			}
		}
		School school = new School();
		school.setId(school_id);
		SchoolTimeline schoolTimeline = new SchoolTimeline();
		schoolTimeline.setSchool(school);
		schoolTimeline.setTitle(title);
		schoolTimeline.setYear(year);
		schoolTimeline.setClassesUpto(classes_upto);
		schoolTimeline.setLastUpdatedBy(user_id);
		schoolTimeline.setLastUpdatedOn(new Date());
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		msg = schoolDAOImp.saveSchoolTimeline(schoolTimeline);
		if (msg.getStatus() == 0) {
			return msg;
		} else {
			schoolTimeline.setId(msg.getStatus());
			String image_name = schoolTimeline.getTitle().replaceAll("([^a-zA-Z]|\\s)+", " ");
			image_name = image_name+header.getFileName();
			image_name = msg.getStatus()+"_"+image_name.replaceAll(" ", "_").toLowerCase();
			image_name = "milestones/"+image_name;
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			writeToFile(is, uploadedFileLocation);
			schoolTimeline.setImage(image_name);
			List<SchoolTimelineMilestone> timelineMilestones = new ArrayList<SchoolTimelineMilestone>();
			if(schoolDAOImp.updateSchoolTimelineImage(msg.getStatus(),image_name)){
				for(int k = 0; k < milestoneTitle.size(); k++){
					SchoolTimelineMilestone schoolTimelineMilestone = new SchoolTimelineMilestone();
					schoolTimelineMilestone.setSchoolTimeline(schoolTimeline);
					schoolTimelineMilestone.setMilestoneDesc(milestoneDesc.get(k));
					schoolTimelineMilestone.setTitle(milestoneTitle.get(k));
					schoolTimelineMilestone.setLastUpdatedBy(user_id);
					schoolTimelineMilestone.setLastUpdatedOn(new Date());
					timelineMilestones.add(schoolTimelineMilestone);
				}
				msg = schoolDAOImp.saveSchoolTimelineMilestone(timelineMilestones);
				
			} else {
				msg.setStatus(0);
				msg.setMessage("Failed to upload image");
			}
		}
 
		return msg;
	}
	
	@SuppressWarnings("null")
	@POST
	@Path("/updatetimeline")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseMessage updateSchoolTimeline(
			@FormDataParam("school_id") Integer school_id,
			@FormDataParam("user_id") Integer user_id,
			@FormDataParam("id") Integer id,
			@FormDataParam("year") Short year, 
			@FormDataParam("title") String title, 
			@FormDataParam("classes_upto") String classes_upto, 
			@FormDataParam("milestoneTitle[]") List<FormDataBodyPart> milestones,
			@FormDataParam("milestoneDesc[]") List<FormDataBodyPart> milestonesDescription,
			@FormDataParam("image") InputStream is, 
			@FormDataParam("image") FormDataContentDisposition header
	){
		ResponseMessage msg = new ResponseMessage();
		List<String> milestoneTitle = new ArrayList<String>();
		List<String> milestoneDesc = new ArrayList<String>();
		if(milestones.size() > 0){
			for(FormDataBodyPart milestone : milestones){
				String mtitle = milestone.getValueAs(String.class).toString();
				if(mtitle != null)
				milestoneTitle.add(mtitle);
			}
		}
		
		if(milestonesDescription.size() > 0){
			for(FormDataBodyPart desc : milestonesDescription){
				String mdesc = desc.getValueAs(String.class);
				if(mdesc != null)
				milestoneDesc.add(mdesc);
			}
		}
		School school = new School();
		school.setId(school_id);
		SchoolTimeline schoolTimeline = new SchoolTimeline();
		schoolTimeline.setId(id);
		schoolTimeline.setSchool(school);
		schoolTimeline.setTitle(title);
		schoolTimeline.setYear(year);
		schoolTimeline.setClassesUpto(classes_upto);
		schoolTimeline.setLastUpdatedBy(user_id);
		schoolTimeline.setLastUpdatedOn(new Date());
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		String image_name = schoolTimeline.getTitle().replaceAll("([^a-zA-Z]|\\s)+", " ");
		image_name = image_name+header.getFileName();
		image_name = id+"_"+image_name.replaceAll(" ", "_").toLowerCase();
		image_name = "milestones/"+image_name;
		String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
		writeToFile(is, uploadedFileLocation);
		schoolTimeline.setImage(image_name);
		msg = schoolDAOImp.updateSchoolTimeline(schoolTimeline);
		if (msg.getStatus() == 0) {
			return msg;
		} else {
			List<SchoolTimelineMilestone> timelineMilestones = new ArrayList<SchoolTimelineMilestone>();
			for(int k = 0; k < milestoneTitle.size(); k++){
				SchoolTimelineMilestone schoolTimelineMilestone = new SchoolTimelineMilestone();
				schoolTimelineMilestone.setSchoolTimeline(schoolTimeline);
				schoolTimelineMilestone.setMilestoneDesc(milestoneDesc.get(k));
				schoolTimelineMilestone.setTitle(milestoneTitle.get(k));
				schoolTimelineMilestone.setLastUpdatedBy(user_id);
				schoolTimelineMilestone.setLastUpdatedOn(new Date());
				timelineMilestones.add(schoolTimelineMilestone);
			}
			msg = schoolDAOImp.saveSchoolTimelineMilestone(timelineMilestones);
		}
 
		return msg;
	}
	
	@GET
	@Path("/timeline_detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolTimelineMilestone> getSchoolTimeline(@PathParam("id") int id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolTimelineMilestonesByTimelineId(id);
	}
	
	@POST
	@Path("/savehighlight")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolHighlight> saveSchoolHighlight(
		@FormParam("school_id") Integer school_id,
		@FormParam("name") String name
	){
		List<SchoolHighlight> highlights = new ArrayList<SchoolHighlight>();
		try{
			School school = new School();
			school.setId(school_id);
			SchoolHighlight schoolHighlight = new SchoolHighlight();
			schoolHighlight.setName(name);
			schoolHighlight.setSchool(school);
			SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
			schoolDAOImp.saveHighlight(schoolHighlight);
			highlights =  schoolDAOImp.getSchoolHighlights(school_id);
		} catch(Exception e) {
			//
		}
		return highlights;
	}
	
	@POST
	@Path("/updatehighlight")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolHighlight> updateSchoolHighlight(
		@FormParam("school_id") Integer school_id,
		@FormParam("id") Integer id,
		@FormParam("name") String name
	){
		List<SchoolHighlight> highlights = new ArrayList<SchoolHighlight>();
		try{
			School school = new School();
			school.setId(school_id);
			SchoolHighlight schoolHighlight = new SchoolHighlight();
			schoolHighlight.setId(id);
			schoolHighlight.setName(name);
			schoolHighlight.setSchool(school);
			SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
			schoolDAOImp.updateHighlight(schoolHighlight);
			highlights = schoolDAOImp.getSchoolHighlights(school_id);
		} catch(Exception e) {
			//
		}
		return highlights;
	}
	
	@GET
	@Path("/highlight_detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolHighlight getSchoolHighlight(@PathParam("id") int id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolHighlightById(id).get(0);
	}

	private void writeToFile(InputStream is, String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[102400];
 
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
 
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/feetype")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FeeType> getFeeTypeList()
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		List<FeeType> feetypelist= schoolDAOImp.getFeeTypes();
		return feetypelist;
		
	}
		
	@SuppressWarnings("null")
	@POST
	@Path("/saveimagegallery")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseMessage saveSchoolImageGallery(
			@FormDataParam("school_id") Integer school_id,
			@FormDataParam("logo_image") InputStream is_logo_img, 
			@FormDataParam("logo_image") FormDataContentDisposition header_logo_img,
			@FormDataParam("home_image") InputStream is_home_img, 
			@FormDataParam("home_image") FormDataContentDisposition header_home_img,
			@FormDataParam("imageTitle[]") List<FormDataBodyPart> imageTitle,
			@FormDataParam("ga_image[]") List<FormDataBodyPart> imageslist
	){
		ResponseMessage msg = new ResponseMessage();
		msg.setStatus(0);
		msg.setMessage("Failed to save images.");
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		School school = new School();
		school.setId(school_id);
		school.setLogo("");
		school.setHomeImage("");
		try{
			if(!header_logo_img.getFileName().isEmpty()) {
				String logo_name = header_logo_img.getFileName();
				logo_name = logo_name.replaceAll(" ", "_").toLowerCase();
				logo_name = "logo/"+logo_name;
				String uploadLogoLocation = this.context.getInitParameter("logo_url")+logo_name;
				writeToFile(is_logo_img, uploadLogoLocation);
				school.setLogo(logo_name);
				System.out.println("LOGO URL : "+logo_name);
			}
			
			if(!header_home_img.getFileName().isEmpty()) {
				String home_name = header_home_img.getFileName();
				home_name = home_name.replaceAll(" ","_").toLowerCase();
				home_name = "home/"+home_name;
				String uploadHomeLocation = this.context.getInitParameter("logo_url")+home_name;
				writeToFile(is_home_img, uploadHomeLocation);
				school.setHomeImage(home_name);
				System.out.println("HOME-URL : "+home_name);
			}
			msg = schoolDAOImp.updateSchoolImages(school);
			if(msg.getStatus() == 0){
				return msg;
			}
		} catch(NullPointerException e) {
			//null objects
		}
		try {	
			List<SchoolImageGallery> schoolImageGalleryList = new ArrayList<SchoolImageGallery>();
			//for multiple inserting images.
			if (imageslist.size() > 0) {
				for(int i=0 ;i < imageslist.size();i++)
				{
					SchoolImageGallery schoolImageGallery = new SchoolImageGallery();
					String gallery_name = imageslist.get(i).getFormDataContentDisposition().getFileName();
					gallery_name = gallery_name.replaceAll(" ", "_").toLowerCase();
					gallery_name = "gallery/"+gallery_name;
					String uploadGalleryLocation = this.context.getInitParameter("logo_url")+gallery_name;
					System.out.println("for loop image path: "+uploadGalleryLocation);
					writeToFile(imageslist.get(i).getValueAs(InputStream.class), uploadGalleryLocation);
					schoolImageGallery.setImage(gallery_name);
					schoolImageGallery.setTitle(imageTitle.get(i).getValueAs(String.class).toString());
					schoolImageGallery.setSchool(school);
					schoolImageGalleryList.add(schoolImageGallery);
				}
				msg =  schoolDAOImp.saveSchoolImageGallery(school,schoolImageGalleryList);
			}
		} catch(Exception e) {
//			msg.setStatus(0);
//			msg.setMessage("Unable to save image");
		}
       	return msg;
	}
	
	@GET
	@Path("/deletegalleryimage/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage deleteGalleryImage(@PathParam("id") int id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.deleteGalleryImage(id);
	}
	@POST
	@Path("/updateimagetitle")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateImageTitle(@FormParam("id") int id, @FormParam("title") String title){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.updateImageTitle(id, title);
	}
	
	@GET
	@Path("/get_school_progress/{school_id}")
	public Double getSchoolProgress(@PathParam("school_id") int school_id){
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getPer(school_id);
	}

}
