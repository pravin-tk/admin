package org.school.admin.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.AreaUnit;
import org.school.admin.service.AreaUnitService;

@Path("/areaunit")
public class AreaUnitController {
	
	@GET
	@Path("/selectunit")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AreaUnit> getAreaUnit()
	{
		AreaUnitService araeUnitService = new AreaUnitService();
		return araeUnitService.getAreaUnit();
	}
	

}
