package org.school.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.dao.SettingsImpl;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.Accessories;
import org.school.admin.model.AdminUser;
import org.school.admin.model.AdminUserRole;
import org.school.admin.model.AreaUnit;
import org.school.admin.model.BoardType;
import org.school.admin.model.BusInfo;
import org.school.admin.model.BusStop;
import org.school.admin.model.CertificateType;
import org.school.admin.model.EducationType;
import org.school.admin.model.ExamType;
import org.school.admin.model.FacultyType;
import org.school.admin.model.FeeType;
import org.school.admin.model.LeaveType;
import org.school.admin.model.MediumType;
import org.school.admin.model.NotificationType;
import org.school.admin.model.OccupationType;
import org.school.admin.model.PaymentMode;
import org.school.admin.model.RatingCategoryType;
import org.school.admin.model.Role;
import org.school.admin.model.SchoolCategoryType;
import org.school.admin.model.SchoolClassificationType;
import org.school.admin.model.SchoolType;
import org.school.admin.model.SecondaryRole;
import org.school.admin.model.StandardType;
import org.school.admin.model.StreamType;
import org.school.admin.model.Subject;
import org.school.admin.model.TeachingApproachType;
import org.school.admin.util.HibernateUtil;

@Path("settings")
public class SettingsController extends ResourceConfig {
	@Context ServletContext context;
	/*-------------------Pankaj Naik-------------------------*/
	@POST
	@Path("/adminuserrole_save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveAdminUserRole(
		@FormParam("adminuserrole") String adminuserrole
	){
		AdminUserRole adminUserRole2 = new AdminUserRole();
		adminUserRole2.setRoleName(adminuserrole);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveAdminUserRole(adminUserRole2);
	}
	
	@POST
	@Path("/adminuserrole/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateAdminUserRole(
			@FormParam("id") int id,
			@FormParam("adminuserrole") String adminuserrole
	){
	
		AdminUserRole adminUserRole2 = new AdminUserRole();
		adminUserRole2.setId(id);
		adminUserRole2.setRoleName(adminuserrole);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateAdminUserRole(adminUserRole2);
	}
	@POST
	@Path("/adminuser/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateAdminUser(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("mobile") String mobile,
			@FormParam("upassword") String password,
			@FormParam("email") String email,
			@FormParam("role_id") int roleId,
			@FormParam("user_status") int status
			
			
	){
	
		AdminUser adminUser = new AdminUser();
		AdminUserRole adminUserRole = new AdminUserRole();
		adminUserRole.setId(roleId);
		adminUser.setId(id);
		adminUser.setName(name);
		adminUser.setEmail(email);
		adminUser.setMobile(mobile);
		adminUser.setPassword(password);
		adminUser.setStatus(status);
		adminUser.setAdminUserRole(adminUserRole);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateAdminUser(adminUser);
	}
	/*-----------------------------------------------------*/
	
	@POST
	@Path("/accessory/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveAccessory(@FormParam("name") String name){
		Accessories accessory = new Accessories();
		accessory.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveAccessory(accessory);
	}
	
	@POST
	@Path("/accessory/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateAccessory(
			@FormParam("id") int id,
			@FormParam("name") String name
	){
	
		Accessories accessory = new Accessories();
		accessory.setId(id);
		accessory.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateAccessory(accessory);
	}
	
	
	@POST
	@Path("/areaunit/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveAreaUnit(@FormParam("name") String name){
		AreaUnit areaunit = new AreaUnit();
		areaunit.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveAreaUnit(areaunit);
	}
	
	@POST
	@Path("/areaunit/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateAreaUnit(
			@FormParam("id") int id,
			@FormParam("name") String name
	){
	
		AreaUnit areaunit = new AreaUnit();
		areaunit.setId(id);
		areaunit.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateAreaUnit(areaunit);
	}
	
	@POST
	@Path("/boardtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveBoardType(@FormParam("name") String name){
		BoardType boardtype = new BoardType();
		boardtype.setBoardName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveBoardType(boardtype);
	}
	
	@POST
	@Path("/boardtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateBoardType(
			@FormParam("id") Short id,
			@FormParam("name") String name
	){
	
		BoardType boardType = new BoardType();
		boardType.setId(id);
		boardType.setBoardName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateBoardType(boardType);
	}
	
	@POST
	@Path("/businfo/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveBusInfo(
		@FormParam("title") String title,
		@FormParam("busNumber") String busNumber,
		@FormParam("vehicleNo") String vehicleNo,
		@FormParam("busDriverName") String busDriverName,
		@FormParam("contactNo") String contactNo,
		@FormParam("description") String description
	){
		BusInfo businfo = new BusInfo();
		businfo.setTitle(title);
		businfo.setBusNumber(busNumber);
		businfo.setVehicleNo(vehicleNo);
		businfo.setBusDriverName(busDriverName);
		businfo.setContactNo(contactNo);
		businfo.setDescription(description);
		businfo.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveBusInfo(businfo);
	}
	
	@POST
	@Path("/businfo/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateBusInfo(
			@FormParam("id") int id,
			@FormParam("title") String title,
			@FormParam("busNumber") String busNumber,
			@FormParam("vehicleNo") String vehicleNo,
			@FormParam("busDriverName") String busDriverName,
			@FormParam("contactNo") String contactNo,
			@FormParam("description") String description
	){
	
		BusInfo businfo = new BusInfo();
		businfo.setId(id);
		businfo.setTitle(title);
		businfo.setBusNumber(busNumber);
		businfo.setVehicleNo(vehicleNo);
		businfo.setBusDriverName(busDriverName);
		businfo.setContactNo(contactNo);
		businfo.setDescription(description);
		businfo.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateBusInfo(businfo);
	}
	
	@POST
	@Path("/busstop/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveBusStop(
		@FormParam("name") String name,
		@FormParam("pickupLatitude") String pickupLatitude,
		@FormParam("pickupLongitude") String pickupLongitude,
		@FormParam("dropLatitude") String dropLatitude,
		@FormParam("dropLongitude") String dropLongitude,
		@FormParam("status") Boolean status
	){
		BusStop busstop = new BusStop();
		busstop.setName(name);
		busstop.setPickupLatitude(pickupLatitude);
		busstop.setPickupLongitude(pickupLongitude);
		busstop.setDropLatitude(dropLatitude);
		busstop.setDropLongitude(dropLongitude);
		busstop.setStatus(status);
		busstop.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveBusStop(busstop);
	}
	
	@POST
	@Path("/busstop/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateBusStop(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("pickupLatitude") String pickupLatitude,
			@FormParam("pickupLongitude") String pickupLongitude,
			@FormParam("dropLatitude") String dropLatitude,
			@FormParam("dropLongitude") String dropLongitude,
			@FormParam("status") Boolean status
	){
	
		BusStop busstop = new BusStop();
		busstop.setId(id);
		busstop.setName(name);
		busstop.setPickupLatitude(pickupLatitude);
		busstop.setPickupLongitude(pickupLongitude);
		busstop.setDropLatitude(dropLatitude);
		busstop.setDropLongitude(dropLongitude);
		busstop.setStatus(status);
		busstop.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateBusStop(busstop);
	}
	
	
	/* *************************************** certificate *********************************** */
	@POST
	@Path("/certificatetype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveCertificateType(
		@FormParam("title") String title,
		@FormParam("sortOrder") Byte sortOrder,
		@FormParam("status") Byte status
	){
		CertificateType certificateType = new CertificateType();
		certificateType.setTitle(title);
		certificateType.setSortOrder(sortOrder);
		certificateType.setStatus(status);
		certificateType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveCertificateType(certificateType);
	}
	
	@POST
	@Path("/certificatetype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateCertificateType(
			@FormParam("id") int id,
			@FormParam("title") String title,
			@FormParam("sortOrder") Byte sortOrder,
			@FormParam("status") Byte status
	){
		
		
		CertificateType certificateType = new CertificateType();
		certificateType.setId(id);
		certificateType.setTitle(title);
		certificateType.setSortOrder(sortOrder);
		certificateType.setStatus(status);
		certificateType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateCertificate(certificateType);
	}
	
	/* *************************** shinee code ****************************** */
	
	@POST
	@Path("/ta/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveTeachingApproachType(@FormParam("name") String name){
		TeachingApproachType teachingApproachType = new TeachingApproachType();	
		teachingApproachType.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveTeachingApproachType(teachingApproachType);
	}
	
	@POST
	@Path("/ta/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateTeachingApproachType(
			@FormParam("id") Short id,
			@FormParam("name") String name
	){
	
		TeachingApproachType teachingApproachType = new TeachingApproachType();
		teachingApproachType.setId(id);
		teachingApproachType.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateTeachingApproachType(teachingApproachType);
	}
	
	@POST
	@Path("/schtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveSchoolType(@FormParam("name") String name){
		SchoolType schoolType = new SchoolType();
		schoolType.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveSchoolType(schoolType);
	}
	
	@POST
	@Path("/schtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSchoolType(
			@FormParam("id") short id,
			@FormParam("name") String name
	){	
		SchoolType schoolType = new SchoolType();
		schoolType.setId(id);
		schoolType.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateSchoolType(schoolType);
	}
	@POST
	@Path("/stdtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveStandardType(@FormParam("name") String name){
		StandardType standardType = new StandardType();
		standardType.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveStandardType(standardType);
	}
	
	@POST
	@Path("/stdtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateStandardType(
			@FormParam("id") short id,
			@FormParam("name") String name
	){	
		StandardType standardType = new StandardType();
		standardType.setId(id);
		standardType.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateStandardType(standardType);
	}
	
	@POST
	@Path("/subject/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveSubject(@FormParam("name") String name){
		Subject subject = new Subject();
		subject.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.saveSubject(subject);
	}
	
	@POST
	@Path("/subject/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSubject(
			@FormParam("id") int id,
			@FormParam("name") String name
	){	
		Subject subject = new Subject();
		subject.setId(id);
		subject.setName(name);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateSubject(subject);
	}
	
	/* *************************************** education type *********************************** */
	@POST
	@Path("/educationtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveEducationType(
		@FormParam("title") String title,
		@FormParam("shortTitle") String shortTitle,
		@FormParam("description") String description,
		@FormParam("sortOrder") Byte sortOrder
	){
		EducationType educationType = new EducationType();
		educationType.setTitle(title);
		educationType.setShortTitle(shortTitle);
		educationType.setDescription(description);
		educationType.setSortOrder(sortOrder);
		educationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveEducationType(educationType);
	}
	
	@POST
	@Path("/educationtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateEducationType(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("shortTitle") String shortTitle,
			@FormParam("description") String description,
			@FormParam("sortOrder") Byte sortOrder
	){
		EducationType educationType = new EducationType();
		educationType.setId(id);
		educationType.setTitle(title);
		educationType.setShortTitle(shortTitle);
		educationType.setDescription(description);
		educationType.setSortOrder(sortOrder);
		educationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateEducationType(educationType);
	}
	
	/* *************************************** exam type *********************************** */
	@POST
	@Path("/examtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveExamType(
		@FormParam("title") String title,
		@FormParam("description") String description,
		@FormParam("sortOrder") Byte sortOrder
	){
		ExamType examType = new ExamType();
		examType.setTitle(title);
		examType.setDescription(description);
		examType.setSortOrder(sortOrder);
		examType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveExamType(examType);
	}
	
	@POST
	@Path("/examtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateExamType(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("description") String description,
			@FormParam("sortOrder") Byte sortOrder
	){
		ExamType examType = new ExamType();
		examType.setId(id);
		examType.setTitle(title);
		examType.setDescription(description);
		examType.setSortOrder(sortOrder);
		examType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateExamType(examType);
	}
	
	/* *************************************** faculty type *********************************** */
	@POST
	@Path("/facultytype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveFacultyType(
		@FormParam("name") String name
	){
		FacultyType facultyType = new FacultyType();
		facultyType.setName(name);
		facultyType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveFacultyType(facultyType);
	}
	
	@POST
	@Path("/facultytype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateFacultyType(
			@FormParam("id") Short id,
			@FormParam("name") String name
	){
		FacultyType facultyType = new FacultyType();
		facultyType.setId(id);
		facultyType.setName(name);
		facultyType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateFacultyType(facultyType);
	}
	
	/* *************************************** fee type *********************************** */
	@POST
	@Path("/feetype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveFeeType(
		@FormParam("title") String title,
		@FormParam("description") String description,
		@FormParam("isDeleted") Byte isDeleted
	){
		FeeType feeType = new FeeType();
		feeType.setTitle(title);
		feeType.setDescription(description);
		feeType.setIsDeleted(isDeleted);
		feeType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveFeeType(feeType);
	}
	
	@POST
	@Path("/feetype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateFeeType(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("description") String description,
			@FormParam("isDeleted") Byte isDeleted
	){
		FeeType feeType = new FeeType();
		feeType.setId(id);
		feeType.setTitle(title);
		feeType.setDescription(description);
		feeType.setIsDeleted(isDeleted);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateFeeType(feeType);
	}
	
	/* *************************************** Leave type *********************************** */
	@POST
	@Path("/leavetype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveLeaveType(
		@FormParam("name") String name,
		@FormParam("description") String description
	){
		LeaveType leaveType = new LeaveType();
		leaveType.setName(name);
		leaveType.setDescription(description);
		leaveType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveLeaveType(leaveType);
	}
	
	@POST
	@Path("/leavetype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateLeaveType(
			@FormParam("id") Short id,
			@FormParam("name") String name,
			@FormParam("description") String description
	){
		LeaveType leaveType = new LeaveType();
		leaveType.setId(id);
		leaveType.setName(name);
		leaveType.setDescription(description);
		SettingsImpl settings = new SettingsImpl();
		return settings.updateLeaveType(leaveType);
	}
	
	/* *************************************** Medium type *********************************** */
	@POST
	@Path("/mediumtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveMediumType(
		@FormParam("title") String title,
		@FormParam("description") String description
	){
		MediumType mediumType = new MediumType();
		mediumType.setTitle(title);
		mediumType.setDescription(description);
		mediumType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveMediumType(mediumType);
	}
	
	@POST
	@Path("/mediumtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateMediumType(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("description") String description
	){
		MediumType mediumType = new MediumType();
		mediumType.setId(id);
		mediumType.setTitle(title);
		mediumType.setDescription(description);
		mediumType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateMediumType(mediumType);
	}
	
	/* *************************************** Notification type *********************************** */
	@POST
	@Path("/notificationtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveNotificationType(
		@FormParam("name") String name,
		@FormParam("status") Byte status
	){
		NotificationType notificationType = new NotificationType();
		notificationType.setName(name);
		notificationType.setStatus(status);
		notificationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveNotificationType(notificationType);
	}
	
	@POST
	@Path("/notificationtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateNotificationType(
			@FormParam("id") Short id,
			@FormParam("name") String name,
			@FormParam("status") Byte status
	){
		NotificationType notificationType = new NotificationType();
		notificationType.setId(id);
		notificationType.setName(name);
		notificationType.setStatus(status);
		notificationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateNotificationType(notificationType);
	}
	
	/* *************************************** Occupation type *********************************** */
	@POST
	@Path("/occupationtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveOccupationType(
		@FormParam("title") String title,
		@FormParam("description") String description
	){
		OccupationType occupationType = new OccupationType();
		occupationType.setTitle(title);
		occupationType.setDescription(description);
		occupationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveOccupationType(occupationType);
	}
	
	@POST
	@Path("/occupationtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateOccupationType(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("description") String description
	){
		OccupationType occupationType = new OccupationType();
		occupationType.setId(id);
		occupationType.setTitle(title);
		occupationType.setDescription(description);
		occupationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateOccupationType(occupationType);
	}
	
	/* *************************************** Payment Mode *********************************** */
	@POST
	@Path("/paymentmode/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage savePaymentMode(
		@FormParam("title") String title,
		@FormParam("isOnline") Byte isOnline,
		@FormParam("isDeleted") Byte isDeleted,
		@FormParam("description") String description
	){
		PaymentMode paymentMode = new PaymentMode();
		paymentMode.setTitle(title);
		paymentMode.setDescription(description);
		paymentMode.setIsOnline(isOnline);
		paymentMode.setIsDeleted(isDeleted);
		paymentMode.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.savePaymentMode(paymentMode);
	}
	
	@POST
	@Path("/paymentmode/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updatePaymentMode(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("isOnline") Byte isOnline,
			@FormParam("isDeleted") Byte isDeleted,
			@FormParam("description") String description
	){
		PaymentMode paymentMode = new PaymentMode();
		paymentMode.setId(id);
		paymentMode.setTitle(title);
		paymentMode.setDescription(description);
		paymentMode.setIsOnline(isOnline);
		paymentMode.setIsDeleted(isDeleted);
		paymentMode.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updatePaymentMode(paymentMode);
	}
	
	/* *************************************** Rating Category type *********************************** */
	@POST
	@Path("/ratingcategorytype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveRatingCategoryType(
		@FormParam("categoryName") String categoryName,
		@FormParam("weightage") Float weightage
	){
		RatingCategoryType ratingCategoryType = new RatingCategoryType();
		ratingCategoryType.setCategoryName(categoryName);
		ratingCategoryType.setWeightage(weightage);
		ratingCategoryType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveRatingCategoryType(ratingCategoryType);
	}
	
	@POST
	@Path("/ratingcategorytype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateRatingCategoryType(
			@FormParam("id") Short id,
			@FormParam("categoryName") String categoryName,
			@FormParam("weightage") Float weightage
	){
		RatingCategoryType ratingCategoryType = new RatingCategoryType();
		ratingCategoryType.setId(id);
		ratingCategoryType.setCategoryName(categoryName);
		ratingCategoryType.setWeightage(weightage);
		ratingCategoryType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateRatingCategoryType(ratingCategoryType);
	}
	
	/* *************************************** Role *********************************** */
	@POST
	@Path("/role/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveRole(
		@FormParam("name") String name,
		@FormParam("status") Byte status
	){
		Role role1 = new Role();
		role1.setName(name);
		role1.setStatus(status);
		role1.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveRole(role1);
	}
	
	@POST
	@Path("/role/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateRole(
			@FormParam("id") Short id,
			@FormParam("name") String name,
			@FormParam("status") Byte status
	){
		Role role1 = new Role();
		role1.setId(id);
		role1.setName(name);
		role1.setStatus(status);
		role1.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateRole(role1);
	}
	
	/* *************************************** School Category type *********************************** */
	@POST
	@Path("/schoolcategorytype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveSchoolCategoryType(
		@FormParam("name") String name,
		@FormParam("status") Byte status,
		@FormParam("maxPoints") Double maxPoints
	){
		SchoolCategoryType schoolCategoryType = new SchoolCategoryType();
		schoolCategoryType.setName(name);
		schoolCategoryType.setStatus(status);
		schoolCategoryType.setMaxPoints(maxPoints);
		schoolCategoryType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveSchoolCategoryType(schoolCategoryType);
	}
	
	@POST
	@Path("/schoolcategorytype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSchoolCategoryType(
			@FormParam("id") Integer id,
			@FormParam("name") String name,
			@FormParam("status") Byte status,
			@FormParam("maxPoints") Double maxPoints
	){
		SchoolCategoryType schoolCategoryType = new SchoolCategoryType();
		schoolCategoryType.setId(id);
		schoolCategoryType.setName(name);
		schoolCategoryType.setStatus(status);
		schoolCategoryType.setMaxPoints(maxPoints);
		schoolCategoryType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateSchoolCategoryType(schoolCategoryType);
	}
	
	/* *************************************** School Classification type *********************************** */
	@POST
	@Path("/schoolclassificationtype/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveSchoolClassificationType(
		@FormDataParam("name") String name,
		@FormDataParam("image") InputStream is, 
		@FormDataParam("image") FormDataContentDisposition header
	){
		SchoolClassificationType schoolClassificationType = new SchoolClassificationType();
		schoolClassificationType.setName(name);
		if(header.getFileName() !=null){
		String image_name = header.getFileName();
		image_name = image_name.replaceAll(" ", "_").toLowerCase();
		image_name = "classification/"+image_name;
		String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
		
		
		writeToFile(is, uploadedFileLocation);
		
		schoolClassificationType.setImage(image_name);
		}
		else
			schoolClassificationType.setImage("");
		schoolClassificationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveSchoolClassificationType(schoolClassificationType);
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
	@POST
	@Path("/schoolclassificationtype/update")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSchoolClassificationType(
			@FormDataParam("id") Integer id,
			@FormDataParam("name") String name,
			@FormDataParam("image") InputStream is, 
			@FormDataParam("image") FormDataContentDisposition header
		){
		SchoolClassificationType schoolClassificationType = new SchoolClassificationType();
		schoolClassificationType.setId(id);
		schoolClassificationType.setName(name);
		if(header.getFileName() !=null){
			String image_name = header.getFileName();
			image_name = image_name.replaceAll(" ", "_").toLowerCase();
			image_name = "classification/"+image_name;
			String uploadedFileLocation = this.context.getInitParameter("logo_url") + image_name;
			
			
			writeToFile(is, uploadedFileLocation);
			
			schoolClassificationType.setImage(image_name);
			}
			else
				schoolClassificationType.setImage("");
		schoolClassificationType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateSchoolClassificationType(schoolClassificationType);
	}
	
	/* *************************************** Secondary role *********************************** */
	@POST
	@Path("/secondaryrole/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveSecondaryRole(
		@FormParam("title") String title,
		@FormParam("description") String description,
		@FormParam("status") Byte status,
		@FormParam("roleId") Short roleId
	){
		Role newrole = new Role();
		newrole.setId(roleId);
		SecondaryRole secondaryRole = new SecondaryRole();
		secondaryRole.setRole(newrole);
		secondaryRole.setTitle(title);
		secondaryRole.setDescription(description);
		secondaryRole.setStatus(status);
		secondaryRole.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveSecondaryRole(secondaryRole);
	}
	
	@POST
	@Path("/secondaryrole/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateSecondaryRole(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("description") String description,
			@FormParam("status") Byte status,
			@FormParam("roleId") Short roleId
	){
		Role newrole = new Role();
		newrole.setId(roleId);
		SecondaryRole secondaryRole = new SecondaryRole();
		secondaryRole.setId(id);
		secondaryRole.setRole(newrole);
		secondaryRole.setTitle(title);
		secondaryRole.setDescription(description);
		secondaryRole.setStatus(status);
		secondaryRole.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateSecondaryRole(secondaryRole);
	}
	
	/* *************************************** Stream type *********************************** */
	@POST
	@Path("/streamtype/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveStreamType(
		@FormParam("title") String title,
		@FormParam("description") String description
	){
		StreamType streamType = new StreamType();
		streamType.setTitle(title);
		streamType.setDescription(description);
		streamType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.saveStreamType(streamType);
	}
	
	@POST
	@Path("/streamtype/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateStreamType(
			@FormParam("id") Short id,
			@FormParam("title") String title,
			@FormParam("description") String description
	){
		StreamType streamType = new StreamType();
		streamType.setId(id);
		streamType.setTitle(title);
		streamType.setDescription(description);
		streamType.setLastUpdatedOn(new Date());
		SettingsImpl settings = new SettingsImpl();
		return settings.updateStreamType(streamType);
	}
	
}
