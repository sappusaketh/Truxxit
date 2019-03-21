package com.database.querybuilders.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.database.querybuilders.beans.User;
import com.database.querybuilders.constants.DatabaseConstants;

@WebServlet(urlPatterns= { "/AdminController" })
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/userOld.jsp";
    private static String LIST_USER = "/listUser.jsp";
    //private AdminDao dao;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//dao = new AdminDao();
    	String forward="";
        String action = request.getParameter("action");
        
        if(null== action || action.length()==0) {
        	action = "listUser";
        }

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println(userId);
            //dao.deleteUser(userId);
            deleteUser(userId);
            forward = LIST_USER;
            //request.setAttribute("users", dao.getAllUsers());  
            request.setAttribute("users", getAllUsers());  
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            //User user = dao.getUserById(userId);
            //User user = new User();
            User user = getUserById(userId);
            List<String> city = getCities();
            user.setCity(city);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            //request.setAttribute("users", dao.getAllUsers());
            request.setAttribute("users", getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
            User user = new User();
            List<String> city = getCities();
            user.setCity(city);
            request.setAttribute("user", user);
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setAvailability(Integer.parseInt(request.getParameter("availability")));
        user.setHourlyRate(Double.parseDouble(request.getParameter("hourlyRate")));
        user.setType(request.getParameter("type"));
        //user.setLocationId(Integer.parseInt(request.getParameter("location")));  
        user.setLocation(request.getParameter("location"));  

        String userid = request.getParameter("truckId");
        //add new driver
        if(userid == null || userid.isEmpty() || userid.equals("0"))
        {
            //dao.addUser(user);
        	//Integer userID= addTruck(user);
        	addTruckDriver(request);
        	//write your code
        }
        //update driver
        else
        {        	
        	//user.setTruckId((Integer.parseInt(userid)));
            //dao.updateUser(user);        	
        	int truckID= Integer.parseInt(userid);
        	updateUser(request,truckID);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        //request.setAttribute("users", dao.getAllUsers());
        request.setAttribute("users", getAllUsers());
        view.forward(request, response);
    }
    
    private List<String> getCities()
    {
    	List<String> city = new ArrayList<>();
    	Connection connection = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
    	Statement stmt=null;
        ResultSet res=null;
    	try {
    		stmt=connection.createStatement();
            res=stmt.executeQuery("select City from location");
            while(res.next()){
            	city.add(res.getString(1));            	
                }      
            
    	}
    	catch(Exception e) {
    		
    	}
    	finally
    	{
    		
    	}
    	return city;
    	
    }
    
    public Integer addTruck(User user) {
    	Connection connection = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
    	Integer userID=0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into truck(Availibility,Type,HourlyRate,Location_LocationID) values (?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setInt(1, user.getAvailability());
            preparedStatement.setString(2, user.getType());
            preparedStatement.setDouble(3, user.getHourlyRate());
            preparedStatement.setInt(4, user.getLocationId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				userID = resultSet.getInt(1);
			}
            System.out.println("added "+userID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }
    
    public void addTruckDriver(HttpServletRequest request)
    {
    	Connection connection = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
    	 Statement stmt=null;
	     PreparedStatement pstmt=null;
	     CallableStatement cstmt=null;
	     ResultSet res=null;
    	int ans=0;
    	    try{
    	        String type=request.getParameter("type");
    	        String rate=request.getParameter("hourlyRate");
    	       
    	        String query="select LocationID from location where location.City=?";
    	        pstmt=connection.prepareStatement(query);

    	        String param=request.getParameter("location");
    	        pstmt.setString(1,param);

    	        res=pstmt.executeQuery();
    	        res.next();
    	        ans=res.getInt(1);
    	        String insertQuery="insert into truck(`Availibility`,`Type`,`HourlyRate`,`Location_LocationID`)  values(1,?,?,?)";
    	        String latestId="select TruckId from truck order by TruckId desc limit 1";
    	        stmt=connection.createStatement();

    	        pstmt=connection.prepareStatement(insertQuery);
    	        pstmt.setString(1,type);
    	        pstmt.setDouble(2,Double.parseDouble(rate));
    	        pstmt.setInt(3,ans);
    	        pstmt.executeUpdate();
    	        ResultSet resultsetNew=stmt.executeQuery(latestId);
    	        resultsetNew.next();
    	        int truckId=resultsetNew.getInt(1);
    	        String address=request.getParameter("address");
    	        String firstName=request.getParameter("firstName");
    	        String lastName=request.getParameter("lastName");
    	        String email=request.getParameter("email");

    	        String phone=request.getParameter("phone");
    	        Double salary=Double.parseDouble(request.getParameter("salary"));
    	        cstmt=connection.prepareCall("call addTruckAndDriver(?,?,?,?,?,?,?)");
    	        cstmt.setString(1,address);
    	        cstmt.setString(2,firstName);
    	        cstmt.setString(3,lastName);
    	        cstmt.setString(4,email);
    	        cstmt.setString(5,phone);
    	        cstmt.setDouble(6,salary);
    	        cstmt.setInt(7,truckId);
    	        cstmt.executeQuery();
    	        
    	    }catch (Exception e){
    	            e.printStackTrace();
    	    }
    	
    	
    }
    
    
    public void updateUser(HttpServletRequest request,int truckID) {
    	Connection connection = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
    	CallableStatement cstmt=null;
        try {
         /*   PreparedStatement preparedStatement = connection
                    .prepareStatement("update truck set Availibility=?, Type=?, HourlyRate=?, Location_LocationID=?" +
                            " where TruckID=?");
            // Parameters start with 1
            preparedStatement.setInt(1, user.getAvailability());
            preparedStatement.setString(2, user.getType());
            preparedStatement.setDouble(3, user.getHourlyRate());
            preparedStatement.setInt(4, user.getLocationId());
            preparedStatement.setInt(5, user.getTruckId());
            preparedStatement.executeUpdate();*/

        	String type=request.getParameter("type");
        	int avail = Integer.parseInt(request.getParameter("availability"));
        	Double rate=Double.parseDouble(request.getParameter("hourlyRate"));
	        String location=request.getParameter("location");
        	String address=request.getParameter("address");
	        String firstName=request.getParameter("firstName");
	        String lastName=request.getParameter("lastName");
	        String email=request.getParameter("email");
	        String phone=request.getParameter("phone");
	        Double salary=Double.parseDouble(request.getParameter("salary"));
        	
        	
        	cstmt=connection.prepareCall("call updateTruckAndDriver(?,?,?,?,?,?,?,?,?,?,?)");
	        cstmt.setInt(1,truckID);
	        cstmt.setInt(2,avail);
	        cstmt.setDouble(3,rate);
	        cstmt.setString(4,type);
	        cstmt.setString(5,location);
	        cstmt.setString(6,address);
	        cstmt.setString(7,firstName);
	        cstmt.setString(8,lastName);
	        cstmt.setString(9,email);
	        cstmt.setString(10,phone);
	        cstmt.setDouble(11,salary);
	        cstmt.executeQuery();
            System.out.println("updated "+truckID);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(int userId) {
    	Connection connection = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		try {
        	
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from truck where truckid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("deleted "+userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
    	Connection connection = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);
		List<User> users = new ArrayList<User>();
        try {
        	PreparedStatement statement = connection.prepareStatement("select * from TruckDetails");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                User user = new User();
                user.setTruckId(rs.getInt("TruckID"));
                user.setAvailability(rs.getInt("Availibility"));
                user.setType(rs.getString("Type"));
                user.setHourlyRate(rs.getDouble("HourlyRate"));
                //user.setLocationId(rs.getInt("Location_LocationID"));;
                
                user.setLocation(rs.getString("City"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setSalary(rs.getString("Salary"));
                
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int truckId) {
    	Connection connection = (Connection) getServletContext().getAttribute(DatabaseConstants.DATABASECONNECTION);		
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from TruckDetails where TruckID=?");
            preparedStatement.setInt(1, truckId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs != null && rs.next()) {
            	user.setTruckId(rs.getInt("TruckID"));
                user.setAvailability(rs.getInt("Availibility"));
                user.setType(rs.getString("Type"));
                user.setHourlyRate(rs.getDouble("HourlyRate"));                
                user.setLocation(rs.getString("City"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setSalary(rs.getString("Salary"));
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    
    
/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }*/
}