package org.school.admin.service;

import org.school.admin.dao.MediumDAOImpl;
import org.school.admin.model.MediumType;

import java.util.List;

public class MediumService {

	
	public List<MediumType> getMediumList()
	{
		MediumDAOImpl mediumDAOImpl = new MediumDAOImpl();
		return mediumDAOImpl.getMediumList();
	}
}
