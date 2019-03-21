package com.database.querybuilders.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.querybuilders.beans.UserX;
import com.database.querybuilders.constants.DatabaseConstants;

/**
 * Servlet implementation class ConfirmBookingServlet
 */
@WebServlet("/ConfirmBookingServlet")
public class ConfirmBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		
		String addressLine1 = httpServletRequest.getParameter("addrressLine1");
		String addressLine2 = httpServletRequest.getParameter("addrressLine2");
		String city = httpServletRequest.getParameter("city");
		String state = httpServletRequest.getParameter("state");
		String zipcode = httpServletRequest.getParameter("zipcode");
		String addressLine1Des = httpServletRequest.getParameter("addrressLine1des");
		String addressLine2Des = httpServletRequest.getParameter("addrressLine2des");
		String cityDes = httpServletRequest.getParameter("citydes");
		String stateDes = httpServletRequest.getParameter("statedes");
		String zipcodeDes = httpServletRequest.getParameter("zipcodedes");
		String truckId = httpServletRequest.getParameter("truckradio");
		String distance = httpServletRequest.getParameter("distance");
		String dprice = httpServletRequest.getParameter("dprice");

		
		UserX userObj = (UserX) httpServletRequest.getSession().getAttribute("User");
		
		Integer sourceAddressId = createAddress(addressLine1, city, state, zipcode);
		Integer destinationAddressId = createAddress(addressLine1Des, cityDes, stateDes, zipcodeDes);
		
		Integer bookingId = createBookingDetails(Integer.valueOf(distance),userObj.getUserId(),sourceAddressId, destinationAddressId,Double.valueOf(dprice), Integer.valueOf(truckId));
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Booking_details_Summary.jsp");
		httpServletRequest.setAttribute("bookingId",bookingId);
		requestDispatcher.include(httpServletRequest, httpServletResponse);
	}
	
	public Integer createAddress(String line1, String city,String state,String zipcode) throws ServletException{
		
		Connection con = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		PreparedStatement preparedStatement = null;
		Integer addressId = 0;
		String insertQuery = "insert into Address(AddressLine,City,State,Zip) values(?,?,?,?)";
		
		try {
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1,line1);
			preparedStatement.setString(2,city);
			preparedStatement.setString(3,state);
			preparedStatement.setString(4,zipcode);
			
			preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				addressId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return addressId;
	}
	
	public Integer createBookingDetails(Integer distance, Integer userId,Integer sAddressId,Integer dAddressId,Double price,Integer truckId) throws ServletException{
		
		Connection con = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		PreparedStatement preparedStatement = null;
		Integer bookingId = 0;
		String insertQuery = "insert into bookingdetails(price,BookingDate,Distance,User_Person_PersonId,SourceAddress_AddressId,DestinationAddress_AddressId1,Truck_TruckID)\n" + 
				" values(?,?,?,?,?,?,?) ";
		
		try {
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setDouble(1,price);
			preparedStatement.setDate(2,new Date(new java.util.Date().getTime()));
			preparedStatement.setDouble(3,Double.valueOf(distance));
			preparedStatement.setInt(4,userId);
			preparedStatement.setInt(5,sAddressId);
			preparedStatement.setInt(6,dAddressId);
			preparedStatement.setInt(7,truckId);

			
			preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				bookingId = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return bookingId;
	}

}
