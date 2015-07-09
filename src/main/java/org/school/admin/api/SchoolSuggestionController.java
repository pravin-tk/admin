package org.school.admin.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSuggestion;
import org.school.admin.service.SchoolSuggestionService;

@Path("school/")
public class SchoolSuggestionController {
	@POST
	@Path("suggest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addUser(SchoolSuggestion schoolSuggestion) {
		SchoolSuggestionService schoolSuggestionService = new SchoolSuggestionService();
		ResponseMessage responseMessage = schoolSuggestionService.addSchoolSuggestion(schoolSuggestion);
		return responseMessage;
	}
}

