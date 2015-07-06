package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.CountryDAOImp;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.Country;

public class CountryService {

	public ResponseMessage save(Country country)
	{
		CountryDAOImp countryDAOImp = new CountryDAOImp();
		return countryDAOImp.save(country);
		
	}
	public List<Country> getCountryList()
	{
		CountryDAOImp countryDAOImp = new CountryDAOImp();
		
		return countryDAOImp.getCountryList();
	}
}
