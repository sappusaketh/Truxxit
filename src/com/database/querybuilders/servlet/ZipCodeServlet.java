package com.database.querybuilders.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.database.querybuilders.beans.Location;
import com.database.querybuilders.constants.DatabaseConstants;

/**
 * Servlet implementation class ZipCodeServlet
 */
@WebServlet("/ZipCodeServlet")
public class ZipCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(ZipCodeServlet.class);
	private static final String LOCATION_LIST_OBJ_KEY = "Location_Obj_Key";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		
		Connection con = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("select * from location");
			resultSet = preparedStatement.executeQuery();
			
			List<Location> locationList =  new ArrayList<Location>();
			
			while(resultSet != null && resultSet.next()){
				
				logger.info("LocationID"+resultSet.getInt("LocationID"));
				
				Location  location = new Location(resultSet.getInt("LocationID"), resultSet.getInt("ZipCode"), resultSet.getString("City"));
				locationList.add(location);

			}
			 logger.info(locationList);			 
			 httpServletRequest.setAttribute(LOCATION_LIST_OBJ_KEY, locationList);
			 
		}catch(Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
