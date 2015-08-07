package org.school.admin.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.hibernate.criterion.Distinct;
import org.school.admin.dao.CountryDAOImp;
import org.school.admin.dao.DistrictImpl;
import org.school.admin.dao.LocalityNamesImp;
import org.school.admin.dao.StateImp;
import org.school.admin.dao.TehsilImpl;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.City;
import org.school.admin.model.Country;
import org.school.admin.model.District;
import org.school.admin.model.Locality;
import org.school.admin.model.School;
import org.school.admin.model.State;
import org.school.admin.model.Tehsil;
import org.school.admin.service.CityNamesService;
import org.school.admin.service.CountryService;
import org.school.admin.service.LocalityService;
import org.school.admin.service.SchoolService;


@Path("general")
public class GeneralController {
	
	@POST
	@Path("/country/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage save(
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("sortOrder") int sortOrder)
	{
	
		Country country = new Country();
		country.setName(name);
		country.setStatus(status);
		country.setSortOrder(sortOrder);
		CountryDAOImp countryService = new CountryDAOImp();
		return countryService.save(country);
		
	}
	
	@POST
	@Path("/country/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateCountry(
			@FormParam("id") int id,
			@FormParam("name") String name,			
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") byte status
	){
	
		Country country = new Country();
		country.setId(id);
		country.setName(name);		
		country.setSortOrder(sortOrder);
		country.setStatus(status);
		CountryDAOImp countryService = new CountryDAOImp();
		return countryService.update(country);
	}

	@POST
	@Path("/state/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addState(
			@FormParam("name") String name,
			@FormParam("countryId") int countryId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") byte status
	){
		Country country = new Country();
		country.setId(countryId);
		State state = new State();
		state.setName(name);
		state.setCountry(country);
		state.setSortOrder(sortOrder);
		state.setStatus(status);
		StateImp stateImp = new StateImp();
		return stateImp.save(state);
	}
	
	@POST
	@Path("/state/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateState(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("countryId") int countryId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") byte status
	){
		Country country = new Country();
		country.setId(countryId);
		State state = new State();
		state.setId(id);
		state.setName(name);
		state.setCountry(country);
		state.setSortOrder(sortOrder);
		state.setStatus(status);
		StateImp stateImp = new StateImp();
		return stateImp.update(state);
	}
	
	@POST
	@Path("/district/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addDistrict(
			@FormParam("name") String name,
			@FormParam("stateId") int stateId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") byte status
	){
		State state = new State();
		state.setId(stateId);
		District district = new District();
		district.setName(name);
		district.setState(state);
		district.setSortOrder(sortOrder);
		district.setStatus(status);
		DistrictImpl districtImpl = new DistrictImpl();
		return districtImpl.save(district);
	}
	
	@POST
	@Path("/district/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateDistrict(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("stateId") int stateId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") byte status
	){
		State state = new State();
		state.setId(stateId);
		District district = new District();
		district.setId(id);
		district.setName(name);
		district.setState(state);
		district.setSortOrder(sortOrder);
		district.setStatus(status);
		DistrictImpl districtImpl = new DistrictImpl();
		return districtImpl.update(district);
	}
	
	@POST
	@Path("/tehsil/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addTehsil(
			@FormParam("name") String name,
			@FormParam("districtId") int districtId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") boolean status
	){
		District district = new District();
		district.setId(districtId);
		Tehsil tehsil = new Tehsil();
		tehsil.setName(name);
		tehsil.setDistrict(district);
		tehsil.setSortOrder(sortOrder);
		tehsil.setStatus(status);
		TehsilImpl tehsilImpl = new TehsilImpl();
		return tehsilImpl.save(tehsil);
	}
	
	@POST
	@Path("/tehsil/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateTehsil(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("districtId") int districtId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") boolean status
	){
		District district = new District();
		district.setId(districtId);
		Tehsil tehsil = new Tehsil();
		tehsil.setId(id);
		tehsil.setName(name);
		tehsil.setDistrict(district);
		tehsil.setSortOrder(sortOrder);
		tehsil.setStatus(status);
		TehsilImpl tehsilImpl = new TehsilImpl();
		return tehsilImpl.update(tehsil);
	}
	
	@POST
	@Path("/city/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addCity(
			@FormParam("name") String name,
			@FormParam("isCity") int isCity,
			@FormParam("tehsilId") int tehsilId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") byte status
	){
		Tehsil tehsil = new Tehsil();
		tehsil.setId(tehsilId);
		City city = new City();
		city.setName(name);
		city.setTehsil(tehsil);
		city.setIsCity(isCity);
		city.setSortOrder(sortOrder);
		city.setStatus(status);
		CityNamesService cityNamesService = new CityNamesService();
		return cityNamesService.addCity(city);
	}
	
	@POST
	@Path("/city/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateCity(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("isCity") int isCity,
			@FormParam("tehsilId") int tehsilId,
			@FormParam("sortOrder") int sortOrder,
			@FormParam("status") byte status
	){
		Tehsil tehsil = new Tehsil();
		tehsil.setId(tehsilId);
		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setTehsil(tehsil);
		city.setIsCity(isCity);
		city.setSortOrder(sortOrder);
		city.setStatus(status);
		CityNamesService cityNamesService = new CityNamesService();
		return cityNamesService.updateCity(city);
	}
	
	@POST
	@Path("/locality/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addLocality(
			@FormParam("name") String name,
			@FormParam("cityId") int cityId,
			@FormParam("sortOrder") byte sortOrder,
			@FormParam("status") boolean status,
			@FormParam("latitude") String latitude,
			@FormParam("longitude") String longitude
	){
		City city = new City();
		city.setId(cityId);
		Locality locality = new Locality();
		locality.setName(name);
		locality.setCity(city);
		locality.setSortOrder(sortOrder);
		locality.setStatus(status);
		locality.setLongitude(longitude);
		locality.setLatitude(latitude);
		LocalityNamesImp localityNamesImp = new LocalityNamesImp();
		return localityNamesImp.save(locality);
	}
	
	@POST
	@Path("/locality/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateLocality(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("cityId") int cityId,
			@FormParam("sortOrder") byte sortOrder,
			@FormParam("status") boolean status,
			@FormParam("latitude") String latitude,
			@FormParam("longitude") String longitude
	){
		City city = new City();
		city.setId(cityId);
		Locality locality = new Locality();
		locality.setId(id);
		locality.setName(name);
		locality.setCity(city);
		locality.setSortOrder(sortOrder);
		locality.setStatus(status);
		locality.setLongitude(longitude);
		locality.setLatitude(latitude);
		LocalityNamesImp localityNamesImp = new LocalityNamesImp();
		return localityNamesImp.update(locality);
	}
	
	@GET
	@Path("/cities")
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> getAllCity() {
		CityNamesService cityNamesService = new CityNamesService();
		System.out.print("Test : ,," + cityNamesService.getAllCityNames());
		return cityNamesService.getAllCityNames();
	}

	@GET
	@Path("/locality/{city_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Locality> getLocalityByCity(@PathParam("city_id") int city_id) {
		LocalityService localityService = new LocalityService();
		return localityService.getLocalityName(city_id);
	}


}
