package org.school.admin.api;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.service.SchoolSearchUserService;

@Path("api1.0/user/")
public class SchoolSearchUserController {
	@Context ServletContext context;
	
	@POST
	@Path("adduser.json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addUser( @FormDataParam("firstName") String firstName,
			@FormDataParam("lastName") String lastName,
			@FormDataParam("email") String email,
			@FormDataParam("password") String password,
			@FormDataParam("mobile") String mobile,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail
	) {
		SchoolSearchUser schoolSearchUser = new  SchoolSearchUser();
		schoolSearchUser.setFirstName(firstName);
		schoolSearchUser.setLastName(lastName);
		schoolSearchUser.setEmail(email);
		schoolSearchUser.setPassword(password);
		schoolSearchUser.setMobile(mobile);
		try{
			schoolSearchUser.setImage(fileDetail.getFileName());
		}catch(NullPointerException e){
			schoolSearchUser.setImage("na");
		}
		
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService();
		ResponseMessage responseMessage = schoolSearchUserService.addSchoolSearchUser(schoolSearchUser, uploadedInputStream, context.getInitParameter("home_url"));
		return responseMessage;
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public ResponseMessage login(SchoolSearchUser schoolSearchUser) {
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService();
		ResponseMessage responseMessage = schoolSearchUserService.userLogin(schoolSearchUser);
		return responseMessage;
	}
	
}
