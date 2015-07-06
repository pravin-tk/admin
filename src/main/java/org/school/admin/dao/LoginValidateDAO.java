package org.school.admin.dao;

import java.util.List;

import org.school.admin.model.AdminUser;

public interface LoginValidateDAO {

	public List<AdminUser> getAdminUserByEmail(String email);
//	public List<Registration> getAdminUserByEmailAndPassword(String eail, String password);
	public  boolean isValidEmailId(String email);
	public boolean isValidPassword(String password);
	
}
