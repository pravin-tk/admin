package org.school.admin.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.SchoolType;
import org.school.admin.service.SchoolTypeService;


@Path("schooltype")
public class SchoolTypeController {
	@GET
	@Path("/selectschooltype")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolType> getSchoolTypeList()
	{
		SchoolTypeService schoolTypeService = new SchoolTypeService();
		return schoolTypeService.getSchoolTypeList();
	}

}
