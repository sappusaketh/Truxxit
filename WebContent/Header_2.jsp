<%@page import="com.database.querybuilders.beans.UserX"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css"/>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not (.active ) {
	background-color: #111;
}

.active {
	background-color: #4CAF50;
}
</style>
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<c:choose>
<c:when test="${sessionScope.User.userName == 'admin@test.org' }">
	<ul>
		<li><a href="/Truxx/AdminController">Trucks Information</a></li>
		<li><a href="/Truxx/AdminController?action=insert">Add Truck</a></li>
		<li><a href="/Truxx/bookingdetailsAdmin">Bookings</a></li>
		<li style="float: right">
			<form action="Logout" method="post">
				<a href="#" onclick="$(this).closest('form').submit()">Sign Off</a>
			</form>
		</li>
	</ul>
	
</c:when>
<c:otherwise>
	<ul>
		<li><a href="TrucksList_temp2.jsp">Book a Truck</a></li>
		<li><a href="/Truxx/bookingdetailsAdmin">User History</a></li>
		<li style="float: right">
			<form action="Logout" method="post">
				<a href="#" onclick="$(this).closest('form').submit()">Sign Off</a>
			</form>
		</li>
	</ul>
</c:otherwise>
</c:choose>
	
<div class="userinfodiv">
<p><h3> ${sessionScope.User.userName} </h3></p>
</div>

</body>
</html>