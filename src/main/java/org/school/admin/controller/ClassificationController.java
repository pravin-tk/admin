package org.school.admin.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.SchoolClassificationType;
import org.school.admin.service.ClassificationService;


@Path("classification")
public class ClassificationController {
	
	@GET
	@Path("/select")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolClassificationType> getClassificationList()
	{
		ClassificationService classificationService = new ClassificationService();
		return classificationService.getClassificationList();
	}

}
