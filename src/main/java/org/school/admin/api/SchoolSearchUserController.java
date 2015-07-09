package org.school.admin.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		schoolSearchUser.setImage(fileDetail.getFileName());
		
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService();
		ResponseMessage responseMessage = schoolSearchUserService.addSchoolSearchUser(schoolSearchUser, uploadedInputStream, context.getInitParameter("home_url"));
		return responseMessage;
	}
	
}
