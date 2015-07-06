package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.AreaUnitDAOImpl;
import org.school.admin.model.AreaUnit;

public class AreaUnitService {

	public List<AreaUnit> getAreaUnit()
	{
		AreaUnitDAOImpl areaUnitDAOImpl = new AreaUnitDAOImpl();
		return areaUnitDAOImpl.getAreaUnit();
	}
}
