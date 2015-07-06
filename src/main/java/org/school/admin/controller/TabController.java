package org.school.admin.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.Query;
import org.hibernate.Session;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.School;
import org.school.admin.model.SchoolImageGallery;
import org.school.admin.model.TabControl;
import org.school.admin.util.HibernateUtil;

@Path("tab")
public class TabController {
	
	public List<TabControl> updateTabs() {
		return null;
}
}