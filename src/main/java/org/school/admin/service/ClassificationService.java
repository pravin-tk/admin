package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.ClassificationDAOImpl;
import org.school.admin.model.SchoolClassificationType;

public class ClassificationService {

	
	public List<SchoolClassificationType> getClassificationList()
	{
		ClassificationDAOImpl classificationDAOImpl = new ClassificationDAOImpl();
		return classificationDAOImpl.getClassificationList();
	}
	
}
