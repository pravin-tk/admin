package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.LocalityNamesImp;
import org.school.admin.model.Locality;

public class LocalityService  {

	
	public List<Locality> getLocalityName(int city) {
		LocalityNamesImp localityNamesImp = new LocalityNamesImp();
		return localityNamesImp.getLocalityNames(city);
	}

}
