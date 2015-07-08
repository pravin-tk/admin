package org.school.admin.api;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.data.SchoolCompleteDetail;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.model.School;
import org.school.admin.model.SchoolTimeline;
import org.school.admin.model.SchoolTimelineMilestone;
import org.school.admin.service.SchoolService;

@Path("schoolDetails/")
public class SchoolController {
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public  SchoolCompleteDetail fetchSchoolCompleteDetails(@PathParam("id") int id) {
		SchoolService schoolHome = new SchoolService();
		return schoolHome.getSchoolCompleteDetails(id);
	}
}
