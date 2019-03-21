package com.database.querybuilders.beans;

import java.io.Serializable;

public class TruckData implements Serializable {
	
	private int truckId;
	private String type;
	private double hourlyPrice;
	private int zipcode;
	private String driverName;
	private String driverPhoneNuber;
	
	
	public TruckData(int truckId, String type, double hourlyPrice, int zipcode, String driverName,
			String driverPhoneNuber) {
		super();
		this.truckId = truckId;
		this.type = type;
		this.hourlyPrice = hourlyPrice;
		this.zipcode = zipcode;
		this.driverName = driverName;
		this.driverPhoneNuber = driverPhoneNuber;
	}
	
	public int getTruckId() {
		return truckId;
	}
	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getHourlyPrice() {
		return hourlyPrice;
	}
	public void setHourlyPrice(double hourlyPrice) {
		this.hourlyPrice = hourlyPrice;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverPhoneNuber() {
		return driverPhoneNuber;
	}
	public void setDriverPhoneNuber(String driverPhoneNuber) {
		this.driverPhoneNuber = driverPhoneNuber;
	}
	
	
}
