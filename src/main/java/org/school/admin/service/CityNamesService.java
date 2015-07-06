package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.CityNamesImp;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.City;

public class CityNamesService  {

	public ResponseMessage addCity(City city)
	{
		CityNamesImp cityNamesImp = new CityNamesImp();
		
		return cityNamesImp.save(city);
	}
	
	public String updateCity(City city)
	{
		CityNamesImp cityNamesImp = new CityNamesImp();
		
		return cityNamesImp.update(city);
	}
	
	public List<City> getAllCityNames() {
		CityNamesImp cityNamesImp = new CityNamesImp();
		return cityNamesImp.getCityNames();
	}
	
	public List<City> getAllCityNamesByTehsilId(int tehsilId) {
		CityNamesImp cityNamesImp = new CityNamesImp();
		return cityNamesImp.getCityNamesByTehsilId(tehsilId);
	}
	
	public List<City> getCityDetailById(int id) {
		CityNamesImp cityNamesImp = new CityNamesImp();
		return cityNamesImp.getCityById(id);
	}

}
