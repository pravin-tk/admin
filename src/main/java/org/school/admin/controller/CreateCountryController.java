package org.school.admin.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.Country;
import org.school.admin.service.CountryService;

@Path("country")
public class CreateCountryController {
	
	@POST
	@Path("/createcountry")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> save(@FormParam("country") String addcountry,@FormParam("creation_id") int creation_id)
	{
	
		Country country = new Country();
		country.setName(addcountry);
		
		
		CountryService countryService = new CountryService();
		countryService.save(country);
		return countryService.getCountryList();
	}
	
	@GET
	@Path("/view")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Country> getCountryList()	
    {
		CountryService countryService = new CountryService();
		return countryService.getCountryList();

}

}
