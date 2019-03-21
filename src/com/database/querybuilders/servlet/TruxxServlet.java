package com.database.querybuilders.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.log4j.Logger;

import com.database.querybuilders.beans.BookingDetails;
import com.database.querybuilders.beans.Location;
import com.database.querybuilders.beans.TruckData;
import com.database.querybuilders.constants.DatabaseConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class TruxxServlet
 */
@WebServlet("/TruxxServlet")
public class TruxxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADDRESS_LINE1_KEY = "addrressLine1";
	private static final String ADDRESS_LINE2_KEY = "addrressLine2";
	private static final String CITY = "city";
	private static final String STATE = "state";
	private static final String ZIPCODE = "zipCode";
	static Logger logger = Logger.getLogger(TruxxServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TruxxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException {
		
		String addressLine1 = (String) httpServletRequest.getAttribute(ADDRESS_LINE1_KEY);
		String addressLine2 = (String) httpServletRequest.getAttribute(ADDRESS_LINE2_KEY);
		String city = (String) httpServletRequest.getAttribute(ADDRESS_LINE1_KEY);
		String state = (String) httpServletRequest.getAttribute(ADDRESS_LINE2_KEY);
		String zipcode = (String) httpServletRequest.getAttribute(ADDRESS_LINE1_KEY);
		
		logger.info("addressLine1"+addressLine1);
		logger.info("addressLine1"+addressLine2);
		logger.info("city"+city);
		logger.info("state"+state);
		logger.info("zipcode"+zipcode);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String addressLine1 = (String) httpServletRequest.getParameter("addrressLine1");
		String addressLine2 = (String) httpServletRequest.getParameter("addrressLine2");
		String city = (String) httpServletRequest.getParameter("city");
		String state = (String) httpServletRequest.getParameter("state");
		String zipcode = (String) httpServletRequest.getParameter("zipcode");
		
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();

        List<TruckData> truckDataList =  getAvailableTrucks(Integer.valueOf(zipcode));
        JsonElement truckDataListJsonObj = gson.toJsonTree(truckDataList,new TypeToken<List<TruckData>>() {}.getType());     
        myObj.add("truckDataList", truckDataListJsonObj);
        
        JsonArray jsonArray = truckDataListJsonObj.getAsJsonArray();
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().print(jsonArray);
	}
	
	public List<TruckData> getAvailableTrucks(int locationId) throws ServletException,IOException{
		
		Connection con = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String trucksQuery ="select tr.TruckID TruckId,tr.Type truckType,tr.HourlyRate hrcharge,pr.PersonId pid, pr.FirstName fname,pr.LastName lname,pr.Phone phoneNum,lo.ZipCode zipcode  from truck tr join location lo on tr.Location_LocationID=lo.LocationID join driver dr on dr.Truck_TruckID=tr.TruckID and tr.Availibility=1 join person pr on pr.PersonId = dr.Person_PersonId  where lo.zipcode=?";
		
		List<TruckData> trucksListData = null; 
	
		try {
			preparedStatement = con.prepareStatement(trucksQuery.toString());
			preparedStatement.setInt(1, locationId);
			resultSet = preparedStatement.executeQuery();
			
			logger.info("resultSet executed");
			trucksListData =  new ArrayList<TruckData>();
			
			while(resultSet != null && resultSet.next()){		
				logger.info("TruckId "+resultSet.getString("TruckId"));
				
				TruckData  truckData = new TruckData(resultSet.getInt("TruckId"), resultSet.getString("truckType"), 
						resultSet.getDouble("hrcharge"), resultSet.getInt("zipcode"),resultSet.getString("fname"),
						resultSet.getString("phoneNum"));
				trucksListData.add(truckData);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");;
			}			
		}
		
		return trucksListData;
	}

}
