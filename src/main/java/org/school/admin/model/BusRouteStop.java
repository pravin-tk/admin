package org.school.admin.model;

// Generated 16 Jun, 2015 2:20:26 PM by Hibernate Tools 4.3.1

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BusRouteStop generated by hbm2java
 */
@Entity
@Table(name = "bus_route_stop")
public class BusRouteStop implements java.io.Serializable {

	private Integer id;
	private BusStop busStop;
	private Route route;
	private Integer busStopNo;
	private Time busPickTime;
	private Time busDropTime;
	private Date lastUpdatedOn;
	private Integer lastUpdatedBy;

	public BusRouteStop() {
	}

	public BusRouteStop(BusStop busStop, Route route, Integer busStopNo,
			Time busPickTime, Time busDropTime, Date lastUpdatedOn,
			Integer lastUpdatedBy) {
		this.busStop = busStop;
		this.route = route;
		this.busStopNo = busStopNo;
		this.busPickTime = busPickTime;
		this.busDropTime = busDropTime;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stop_id")
	public BusStop getBusStop() {
		return this.busStop;
	}

	public void setBusStop(BusStop busStop) {
		this.busStop = busStop;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "route_id")
	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Column(name = "bus_stop_no")
	public Integer getBusStopNo() {
		return this.busStopNo;
	}

	public void setBusStopNo(Integer busStopNo) {
		this.busStopNo = busStopNo;
	}

	
	@Column(name = "bus_pick_time", length = 19)
	public Time getBusPickTime() {
		return this.busPickTime;
	}

	public void setBusPickTime(Time busPickTime) {
		this.busPickTime = busPickTime;
	}

	
	@Column(name = "bus_drop_time", length = 8)
	public Time getBusDropTime() {
		return this.busDropTime;
	}

	public void setBusDropTime(Time busDropTime) {
		this.busDropTime = busDropTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_on", length = 8)
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Column(name = "last_updated_by")
	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}