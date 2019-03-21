package com.database.querybuilders.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.querybuilders.beans.BookingDetailsSummary;
import com.database.querybuilders.constants.DatabaseConstants;

/**
 * Servlet implementation class BookingDetailsSummaryServlet
 */
@WebServlet("/BookingDetailsSummaryServlet")
public class BookingDetailsSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOOKING_DETAILS_SUMMARY_OBJ = "BOOKING_DETAILS_SUMMARY_OBJ";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingDetailsSummaryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		Connection con = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		PreparedStatement preparedStatement = null;
		String bookingId= request.getParameter("BookingId");
		ResultSet resultSet = null;

		StringBuffer query = new StringBuffer("select bd.BookingId, bd.Price, bd.BookingDate, bd. distance, concat(p.FirstName,' ', p. LastName) as customername,");
					query.append("a1.AddressLine as sourceaddressline ,a1. City as sourcecity, a1.state as sourcestate,a1.Zip as source_Zipcode,  ")
					.append("a2.AddressLine as destinationaddressline, a2. city as destinationcity ,a2.State as destinationstate,a2.Zip as destination_Zipcode,  ")
					.append("t.TruckID, t.Type, concat(p1.firstname,' ', p1.lastname) as drivername ")
					.append("from bookingdetails bd join person p on bd.User_Person_PersonId= p.PersonId ")
					.append("join address a1 on bd.SourceAddress_AddressId= a1.AddressId ")
					.append("join address a2 on bd.DestinationAddress_AddressId1= a2. AddressId ")
					.append("join truck t on bd. Truck_TruckID=t.TruckID ")
					.append("join driver dr on dr.Truck_TruckID = t.TruckID ")
					.append("join person p1 on dr.Person_PersonId=p1.PersonId where  bd.bookingId= ? ");
		try {
			preparedStatement = con.prepareStatement(query.toString());
			preparedStatement.setInt(1,Integer.parseInt(bookingId));// to send dynamic inputs for "?" in query
					
			resultSet = preparedStatement.executeQuery();
			BookingDetailsSummary BookingDetailsSummary=null;

			while (resultSet != null && resultSet.next()) {

				        BookingDetailsSummary = new BookingDetailsSummary(
						resultSet.getString("BookingId"), resultSet.getString("price"),
						resultSet.getString("bookingDate"), resultSet.getString("distance"),
						resultSet.getString("customerName"), resultSet.getString("sourceAddressLine"),
						resultSet.getString("sourceCity"),resultSet.getString("sourceState"),
						resultSet.getString("source_Zipcode"), resultSet.getString("destinationAddressLine"),
						resultSet.getString("destinationCity"),resultSet.getString("destinationState"),
						resultSet.getString("destination_Zipcode"), resultSet.getString("truckID"),
						resultSet.getString("type"), resultSet.getString("driverName"));
				
			}

			request.setAttribute(BOOKING_DETAILS_SUMMARY_OBJ, BookingDetailsSummary);
		} catch (SQLException e) {
			e.printStackTrace();

			throw new ServletException("DB Connection problem.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
