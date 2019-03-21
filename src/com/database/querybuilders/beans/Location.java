/**
 * 
 */
package com.database.querybuilders.beans;

import java.io.Serializable;

/**
 * @author vamsiveginati
 *
 */
public class Location implements Serializable {
	

	private Integer locationId;
	private Integer zipCode;
	private String city;

	public Location(Integer locationId, Integer zipCode, String city) {
		super();
		this.locationId = locationId;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", zipCode=" + zipCode + ", city=" + city + "]";
	}
}
