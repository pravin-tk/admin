package org.school.admin.service;

import org.school.admin.dao.SchoolSuggestionDao;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolSuggestion;

public class SchoolSuggestionService {
	
	public ResponseMessage addSchoolSuggestion(SchoolSuggestion schoolSuggestion)
	{
		SchoolSuggestionDao schoolSuggestionRepo = new SchoolSuggestionDao();
		return schoolSuggestionRepo.addSchoolSuggestion(schoolSuggestion);
	}

}
