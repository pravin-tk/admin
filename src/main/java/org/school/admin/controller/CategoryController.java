package org.school.admin.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.SchoolCategoryType;
import org.school.admin.service.CategoryService;


@Path("category")
public class CategoryController {
	
	@GET
	@Path("/selectcategory")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolCategoryType> getCategoryList()
	{
		CategoryService categoryService = new CategoryService();
		return categoryService.getCategoryList();
		
	}

}
