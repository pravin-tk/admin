package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.CampusDAOImpl;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.CampusInfo;

public class CampusService {
	
	public ResponseMessage saveCampus(CampusInfo campusiInfo)
	{
		CampusDAOImpl campusDAOImpl = new CampusDAOImpl();
		return campusDAOImpl.saveCampus(campusiInfo);
	}

}
