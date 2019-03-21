package com.database.querybuilders.beans;

import java.io.Serializable;

public class BookingDetailsSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private String bookingId;
	private String price;
	private String bookingDate;
	private String distance;
	private String customerName;
	private String sourceAddressLine;
	private String sourceCity;
	private String sourceState;
	private String source_Zipcode;
	private String destinationAddressLine;
	private String destinationCity;
	private String destinationState;
	private String destination_Zipcode;
	private String truckID;
	private String type;
	private String driverName;
	public BookingDetailsSummary(String bookingId, String price, String bookingDate, String distance,
			String customerName, String sourceAddressLine, String sourceCity, String sourceState, String source_Zipcode,
			String destinationAddressLine, String destinationCity, String destinationState, String destination_Zipcode,
			String truckID, String type, String driverName) {
		super();
		this.bookingId = bookingId;
		this.price = price;
		this.bookingDate = bookingDate;
		this.distance = distance;
		this.customerName = customerName;
		this.sourceAddressLine = sourceAddressLine;
		this.sourceCity = sourceCity;
		this.sourceState = sourceState;
		this.source_Zipcode = source_Zipcode;
		this.destinationAddressLine = destinationAddressLine;
		this.destinationCity = destinationCity;
		this.destinationState = destinationState;
		this.destination_Zipcode = destination_Zipcode;
		this.truckID = truckID;
		this.type = type;
		this.driverName = driverName;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSourceAddressLine() {
		return sourceAddressLine;
	}
	public void setSourceAddressLine(String sourceAddressLine) {
		this.sourceAddressLine = sourceAddressLine;
	}
	public String getSourceCity() {
		return sourceCity;
	}
	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}
	public String getSourceState() {
		return sourceState;
	}
	public void setSourceState(String sourceState) {
		this.sourceState = sourceState;
	}
	public String getSource_Zipcode() {
		return source_Zipcode;
	}
	public void setSource_Zipcode(String source_Zipcode) {
		this.source_Zipcode = source_Zipcode;
	}
	public String getDestinationAddressLine() {
		return destinationAddressLine;
	}
	public void setDestinationAddressLine(String destinationAddressLine) {
		this.destinationAddressLine = destinationAddressLine;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public String getDestinationState() {
		return destinationState;
	}
	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}
	public String getDestination_Zipcode() {
		return destination_Zipcode;
	}
	public void setDestination_Zipcode(String destination_Zipcode) {
		this.destination_Zipcode = destination_Zipcode;
	}
	public String getTruckID() {
		return truckID;
	}
	public void setTruckID(String truckID) {
		this.truckID = truckID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	@Override
	public String toString() {
		return "BookingDetailsSummary [bookingId=" + bookingId + ", price=" + price + ", bookingDate=" + bookingDate
				+ ", distance=" + distance + ", customerName=" + customerName + ", sourceAddressLine="
				+ sourceAddressLine + ", sourceCity=" + sourceCity + ", sourceState=" + sourceState
				+ ", source_Zipcode=" + source_Zipcode + ", destinationAddressLine=" + destinationAddressLine
				+ ", destinationCity=" + destinationCity + ", destinationState=" + destinationState
				+ ", destination_Zipcode=" + destination_Zipcode + ", truckID=" + truckID + ", type=" + type
				+ ", driverName=" + driverName + "]";
	}
	
	
	
}
