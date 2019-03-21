package com.database.querybuilders.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.database.querybuilders.beans.TruckData;
import com.database.querybuilders.constants.DatabaseConstants;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DistancePriceCalculation
 */
@WebServlet("/DistancePriceCalculation")
public class DistancePriceCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistancePriceCalculation() {
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
		

		String sourceZipcode = httpServletRequest.getParameter("zipcode");
		String destinationZipcode = httpServletRequest.getParameter("zipcodedes");
		String truckId  = httpServletRequest.getParameter("truckId");
		
		//Integer distance = 100;
		Double hourlyRate = getHourlyRate(Integer.valueOf(truckId));
		Integer distance = getDistance(Integer.valueOf(sourceZipcode), Integer.valueOf(destinationZipcode));
		System.out.println(distance);
		Double distanceMiles=(distance/1000)/1.60934;
		Double price = distanceMiles * hourlyRate;
        
        JsonObject jsonObject = new JsonObject();
        //jsonObject.addProperty("distance", distance);
        jsonObject.addProperty("distance", distanceMiles.intValue());
        jsonObject.addProperty("price", price.intValue());
        
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().print(jsonObject);

	}
	
	
	public Integer getDistance(int sourceZip, int destinationZip)
	{
		
		Double distance=0.0;
		Integer distanceInt = 0;
		try
		{
			/*
			 * https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC&destinations=San+Francisco+CA&key=AIzaSyCKvlzQJ8S87FTH8YJbWT3H8OtxzlzAjbk
			 */
			URL url=new URL("http://maps.googleapis.com/maps/api/distancematrix/json?origins="+sourceZip+"&destinations="+destinationZip+"&mode=driving&language=en-EN&sensor=false");
			/*
			 	&mode=driving
				&mode=bicycling
				&mode=transit
				&mode=walking
			 */
			URLConnection con = url.openConnection();
	        InputStream is =con.getInputStream();
			
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	
	        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
	        String line;
	        while ((line = br.readLine()) != null) {
	          response.append(line);
	          response.append('\r');
	        }
	        
	        JSONObject root=new JSONObject(response.toString());
	        JSONObject jsonObject = (JSONObject) root.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance");
	        distance=jsonObject.getDouble("value");  
	        
	        distanceInt = distance.intValue();
	    }
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Invalid Zipcode");
		}
        return distanceInt;
		
	}
	
	
	
	
	
	
	
	public Double getHourlyRate(Integer truckId) throws ServletException,IOException{
		
		Connection con = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String trucksQuery = "select hourlyRate from truck where truckId=?";
		List<TruckData> trucksListData = null; 
		Double hourlyRate = new Double(0.0);
	
		try {
			preparedStatement = con.prepareStatement(trucksQuery.toString());
			preparedStatement.setInt(1, truckId);
			resultSet = preparedStatement.executeQuery();
						
			while(resultSet != null && resultSet.next()){	
			 hourlyRate = resultSet.getDouble("hourlyRate");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
					e.printStackTrace();
			}			
		}
		
		return hourlyRate;
	}


}
