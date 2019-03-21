/**
 * 
 */
package com.database.querybuilders.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.database.querybuilders.beans.BookingDetails;
import com.database.querybuilders.beans.UserX;
import com.database.querybuilders.constants.DatabaseConstants;

import java.sql.ResultSet;

/**
 * @author AKSHAY`S
 *
 */
@WebServlet(urlPatterns = { "/bookingdetailsAdmin" }, name = "BookingDetailsServlet")
public class BookingDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String BOOKING_DETAILS_OBJ = "BOOKING_DETAILS_OBJ";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);

		PreparedStatement preparedStatement = null;
		//String userId = request.getParameter("userId");
		String userId="";
		String userName ="";
		HttpSession session = request.getSession();

		if (null != session) {
			UserX user = (UserX) session.getAttribute("User");

			if (null != user) {
				userId = user.getUserId().toString();
				userName=user.getUserName();
			}
		}

		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer(
				"SELECT bd.BookingId, bd.User_Person_PersonId as userId, bd.Price, bd.BookingDate, Bd.Distance, concat(p.FirstName,' ',p.LastName)as customerName,");
		query.append("a.AddressLine as sourceAddressLine,a.City as sourcecity, a.state as sourcestate,a.Zip as sourceZip,  ")
				.append("ab.AddressLine as destinationAddressLine,ab.city as destinationcity,ab.state as destinationstate, ab.zip as destinationzip,  ")
				.append("bd.Truck_TruckID, t.type from bookingdetails bd  join person p on bd.User_Person_PersonId=p.PersonId join address a on bd.SourceAddress_AddressId=a.AddressId ")
				.append("join address ab on bd.DestinationAddress_AddressId1 = ab.AddressId join truck t on bd.Truck_TruckID=t.truckid ");
				
				if(!userName.equals("admin@test.org"))
				{
					query.append(" where User_Person_PersonId="+userId);	
				}	
				
				query.append(" order by bd.BookingId desc ");

		try {

			preparedStatement = con.prepareStatement(query.toString());
			//preparedStatement.setInt(1, Integer.parseInt("100"));
			resultSet = preparedStatement.executeQuery();

			List<BookingDetails> bookingDetailsList = new ArrayList<BookingDetails>();

			while (resultSet != null && resultSet.next()) {
				BookingDetails bookingDetails = new BookingDetails(resultSet.getString("bookingId"),
						resultSet.getString("userId"), resultSet.getString("price"), resultSet.getString("bookingDate"),
						resultSet.getString("distance"), resultSet.getString("customerName"),
						resultSet.getString("sourceAddressLine"), resultSet.getString("sourceCity"),
						resultSet.getString("sourceState"), resultSet.getString("sourceZip"),
						resultSet.getString("destinationAddressLine"), resultSet.getString("destinationCity"),
						resultSet.getString("destinationState"), resultSet.getString("destinationZip"),
						resultSet.getString("truck_TruckID"), resultSet.getString("type"));
				bookingDetailsList.add(bookingDetails);

			}

			request.setAttribute(BOOKING_DETAILS_OBJ, bookingDetailsList);
		} catch (SQLException e) {
			e.printStackTrace();

			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {

			}
		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Bookingdetails.jsp");
		requestDispatcher.include(request, response);
	}
}
