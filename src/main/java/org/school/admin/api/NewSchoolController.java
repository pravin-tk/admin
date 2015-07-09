package org.school.admin.api;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.school.admin.dao.ClassDetailDAO;
import org.school.admin.dao.ContactDetaillDAO;
import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.dao.SchoolSearchImpl;
import org.school.admin.data.Facility;
import org.school.admin.data.FeeDetail;
import org.school.admin.data.InfraCategory;
import org.school.admin.data.NameList;
import org.school.admin.data.SchoolContact;
import org.school.admin.data.SchoolFee;
import org.school.admin.data.ClassInfoData;
import org.school.admin.data.GalleryData;
import org.school.admin.data.SchoolAddress;
import org.school.admin.data.SchoolHighlightInfo;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.model.SchoolMedium;
import org.school.admin.model.SchoolReview;

@Path("api1.0/school")
public class NewSchoolController {
	@Context ServletContext context;
	String img_path;
	
	@GET
	@Path("classInfo.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClassInfoData> getClassInfoDetails(@Context UriInfo uriInfo)
	{
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String schoolId = params.getFirst("schoolId");
		String stdId = params.getFirst("stdId");
		try
		{
					if(schoolId !=null && stdId !=null){
				return new ClassDetailDAO().getClassDetail(Integer.parseInt(schoolId),Short.parseShort(stdId));
				}else if(schoolId !=null)
					return new ClassDetailDAO().getClassDetail(Integer.parseInt(schoolId));
				else
					return null;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	
	@GET
	@Path("basic.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolAddress getBasicInfo(@PathParam("id") int id)
	{
		List<SchoolAddress> basic = new ClassDetailDAO().getSchoolBasicInfo(id);
		if(basic.size() > 0){
			return new ClassDetailDAO().getSchoolBasicInfo(id).get(0);
		}else{
			return null;
		}
	}
	
	@GET
	@Path("/contact.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolContact getSchoolContact(@PathParam("id") int id){
		ContactDetaillDAO contactDetaillDAO = new ContactDetaillDAO();
		return contactDetaillDAO.getExternalConatctDetail(id);
	}
	
	@GET
	@Path("gallery.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GalleryData> getImageGallary(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<GalleryData> galleryDatas = schoolSearchImpl.getImageGallary(id);
		img_path = this.context.getInitParameter("s3_base_url");
		for(int i = 0; i < galleryDatas.size(); i++){
			galleryDatas.get(i).setImageUrl(img_path+galleryDatas.get(i).getImageUrl());
		}
		return galleryDatas;
	}
	
	@GET
	@Path("highlight.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameList> getSchoolHighlightInfo(@PathParam("id") int id)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolHighlightList(id);
	}
	
	@GET
	@Path("fee.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FeeDetail> getClassFeeDetails(
			@PathParam("id") int id,
			@DefaultValue("0") @QueryParam("standardId") Short standardId
	){
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getClassFeeDetails(id,standardId);
	}
	
	@GET
	@Path("timeline.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolTimelineData> getSchoolTimeLine(@PathParam("id") int id){
		img_path = this.context.getInitParameter("s3_base_url");
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		List<SchoolTimelineData> timelines = schoolDAOImp.getSchoolTimelineDetails(id);
		for(int i = 0; i < timelines.size(); i++){
			timelines.get(i).setImage(img_path+timelines.get(i).getImage());
		}
		return timelines;
	}
	
	@GET
	@Path("reviews.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolReview> getSchoolReviews(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getSchoolReviews(id);
	}
	
	@GET
	@Path("facility.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Facility getSchoolFacility(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<InfraCategory> activity = schoolSearchImpl.getSchoolActivity(id);
		List<InfraCategory> safety = schoolSearchImpl.getSchoolSafety(id);
		List<InfraCategory> infra = schoolSearchImpl.getSchoolActivity(id);
		Facility facility = new Facility();
		facility.setActivity(activity);
		facility.setSafety(safety);
		facility.setInfra(infra);
		return facility;
	}
	
}
