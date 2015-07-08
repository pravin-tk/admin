package org.school.admin.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSearchUser;
import org.school.admin.service.SchoolSearchUserService;

@Path("/user/")
public class SchoolSearchUserController {
	@POST
	@Path("add.json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addUser( SchoolSearchUser schoolSearchUser ) {
		SchoolSearchUserService schoolSearchUserService = new SchoolSearchUserService();
		ResponseMessage responseMessage = schoolSearchUserService.addSchoolSearchUser(schoolSearchUser);
		return responseMessage;
	}
	
}
