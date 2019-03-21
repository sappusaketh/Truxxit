package com.database.querybuilders.beans;
import java.io.Serializable;

/**
 * @author AKSHAY`S
 *
 */

public class BookingDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String bookingId;
	private String userId;
	private String price;
	private String bookingDate;
	private String distance;
	private String customerName;
	private String sourceAddressLine;
	private String sourceCity;
	private String sourceState;
	private String sourceZip;
	private String destinationAddressLine;
	private String destinationCity;
	private String destinationState;
	private String destinationZip;
	private String truck_TruckID;
	private String type;
	
	public BookingDetails(String bookingId, String userId, String price, String bookingDate, String distance,
			String customerName, String sourceAddressLine, String sourceCity, String sourceState, String sourceZip,
			String destinationAddressLine, String destinationCity, String destinationState, String destinationZip,
			String truck_TruckID, String type) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.price = price;
		this.bookingDate = bookingDate;
		this.distance = distance;
		this.customerName = customerName;
		this.sourceAddressLine = sourceAddressLine;
		this.sourceCity = sourceCity;
		this.sourceState = sourceState;
		this.sourceZip = sourceZip;
		this.destinationAddressLine = destinationAddressLine;
		this.destinationCity = destinationCity;
		this.destinationState = destinationState;
		this.destinationZip = destinationZip;
		this.truck_TruckID = truck_TruckID;
		this.type = type;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSourceZip() {
		return sourceZip;
	}

	public void setSourceZip(String sourceZip) {
		this.sourceZip = sourceZip;
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

	public String getDestinationZip() {
		return destinationZip;
	}

	public void setDestinationZip(String destinationZip) {
		this.destinationZip = destinationZip;
	}

	public String getTruck_TruckID() {
		return truck_TruckID;
	}

	public void setTruck_TruckID(String truck_TruckID) {
		this.truck_TruckID = truck_TruckID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", userId=" + userId + ", price=" + price + ", bookingDate="
				+ bookingDate + ", distance=" + distance + ", customerName=" + customerName + ", sourceAddressLine="
				+ sourceAddressLine + ", sourceCity=" + sourceCity + ", sourceState=" + sourceState + ", sourceZip="
				+ sourceZip + ", destinationAddressLine=" + destinationAddressLine + ", destinationCity="
				+ destinationCity + ", destinationState=" + destinationState + ", destinationZip=" + destinationZip
				+ ", truck_TruckID=" + truck_TruckID + ", type=" + type + "]";
	}
	
	

}
