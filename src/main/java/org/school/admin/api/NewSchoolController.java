package org.school.admin.api;

import java.util.List;

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
import org.school.admin.data.SchoolFee;
import org.school.admin.data.ClassInfoData;
import org.school.admin.data.GallaryData;
import org.school.admin.data.SchoolAddress;
import org.school.admin.data.SchoolHighlightInfo;
import org.school.admin.model.SchoolMedium;

@Path("class")
public class NewSchoolController {
	
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
	@Path("classfee")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolFee> getClassFeeDetails(@Context UriInfo uriInfo)
	{
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String schoolId = params.getFirst("schoolId");
		String stdId = params.getFirst("stdId");
		try
		{
	
			if(schoolId !=null && stdId !=null)
				return new ClassDetailDAO().getClassFeeDetails(Integer.parseInt(schoolId),Short.parseShort(stdId));
			else
				return null;
		}
			catch(Exception e)
			{
				System.out.println("Error in classFee : "+e);
				e.printStackTrace();
				return null;
			}
				
	}
	
	@GET
	@Path("gallary")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GallaryData> getImageGallary(@Context UriInfo uriInfo)
	{
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		return new ClassDetailDAO().getImageGallary(Integer.parseInt(params.getFirst("schoolId")));
	}
	@GET
	@Path("basic")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolAddress> getBasicInfo(@Context UriInfo uriInfo)
	{
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		return new ClassDetailDAO().getSchoolBasicInfo(Integer.parseInt(params.getFirst("schoolId")));
	}
	
	@GET
	@Path("highlight")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolHighlightInfo> getSchoolHighlightInfo(@Context UriInfo uriInfo)
	{
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String school_id = params.getFirst("schoolId");
		if(school_id !=null)
		return new ClassDetailDAO().getSchoolHighlightInfo(Integer.parseInt(params.getFirst("schoolId")));
		else
			return null;
	}
	
}
