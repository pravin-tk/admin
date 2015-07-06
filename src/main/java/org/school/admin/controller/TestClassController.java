package org.school.admin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.AdminUser;

@Path("test")
public class TestClassController {
	
	@GET
	@Path(value="/pankaj")
	@Produces(MediaType.APPLICATION_XHTML_XML)
	public String test()
	{
		return "Test";
	}

}
