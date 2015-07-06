package org.school.admin.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.MediumType;
import org.school.admin.service.MediumService;

@Path("medium")
public class MediumController {
	
	@GET
	@Path("/viewmedium")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MediumType> getMediumList()
	{
		MediumService mediumService = new MediumService();
		return mediumService.getMediumList();
	}

}
