package org.school.admin.controller;

import java.sql.Time;
import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.dao.TransportImpl;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.Route;
import org.school.admin.model.BusStop;
import org.school.admin.model.BusRouteStop;

@Path("transport")
public class TransportController {
	
	@POST
	@Path("/route/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage saveRoute(
			@FormParam("name") String name,
			@FormParam("status") byte status,
			@FormParam("desc") String desc
			){
		Route route = new Route();
		route.setName(name);
		route.setStatus(status);
		//route.setDesc(desc);
		TransportImpl transport = new TransportImpl();
		return transport.saveRoute(route);
	}
	
	@POST
	@Path("/route/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateRoute(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("status") byte status
			//@FormParam("desc") String desc
	){
	
		Route route = new Route();
		route.setId(id);
		route.setName(name);
		route.setStatus(status);
		//route.setDesc(desc);
		TransportImpl transport = new TransportImpl();
		return transport.updateRoute(route);
	}
	
	@POST
	@Path("/routestop/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addRouteStop(
			@FormParam("routeId") int routeId,
			@FormParam("stopId") int stopId,
			@FormParam("stopno") int stopNo,
			@FormParam("pickuptime") Time busPickupTime,
			@FormParam("droptime") Time busDropTime
	){
		Route route = new Route();
		route.setId(routeId);
		BusStop stop = new BusStop();
		stop.setId(stopId);
		BusRouteStop routeStop = new BusRouteStop();
		routeStop.setRoute(route);
		routeStop.setBusStop(stop);
		routeStop.setBusStopNo(stopNo);
		routeStop.setBusPickTime(busPickupTime);
		routeStop.setBusDropTime(busDropTime);
		TransportImpl transportImpl = new TransportImpl();
		return transportImpl.saveRouteStop(routeStop);
	}
	
	@POST
	@Path("/routestop/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateRouteStop(
			@FormParam("id") int id,
			@FormParam("routeId") int routeId,
			@FormParam("stopId") int stopId,
			@FormParam("stopno") int stopNo,
			@FormParam("pickuptime") Time busPickupTime,
			@FormParam("droptime") Time busDropTime
	){
		Route route = new Route();
		route.setId(routeId);
		BusStop stop = new BusStop();
		stop.setId(stopId);
		BusRouteStop routeStop = new BusRouteStop();
		routeStop.setId(id);
		routeStop.setBusStopNo(stopNo);
		routeStop.setBusPickTime(busPickupTime);
		routeStop.setBusDropTime(busDropTime);
		routeStop.setBusStop(stop);
		routeStop.setRoute(route);
		TransportImpl transportImpl = new TransportImpl();
		System.out.println("Saving item object");
		return transportImpl.updateRouteStop(routeStop);
	}
	
}// end class


