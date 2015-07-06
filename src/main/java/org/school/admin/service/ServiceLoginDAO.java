package org.school.admin.service;

import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.AdminUser;

public interface ServiceLoginDAO {
	public ResponseMessage isValidUser(AdminUser registration);
}
