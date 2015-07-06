package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.CategoryDAOImpl;
import org.school.admin.model.SchoolCategoryType;

public class CategoryService {

	public List<SchoolCategoryType> getCategoryList()
	{
		CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
		return categoryDAOImpl.getCategoryList();
	}
}
