<%@page import="com.database.querybuilders.beans.BookingDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css" href="mystyle.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Booking History</title>
</head>
<body>
<jsp:include page="Header_2.jsp"/>

<% List<BookingDetails> eList = (ArrayList<BookingDetails>) request.getAttribute("BOOKING_DETAILS_OBJ");
	pageContext.setAttribute("eList2", eList);
%>

<table border="1" cellpadding="5" cellspacing="3">
<tr>
    <th>BookingId</th>
	<th>Booking Date</th>
	<th>Source Address</th>
	<th>Destination Address</th>
	<th>TruckId</th>
	<th>TruckType</th>
	<th>Price</th>
</tr>
<c:forEach var="bookingDetails" items="${eList2}">
        <tr>
        	<td><c:out value="${bookingDetails.bookingId}"/></td>
            <td><c:out value="${bookingDetails.bookingDate}"/></td>
            <td><c:out value="${bookingDetails.sourceAddressLine}, ${bookingDetails.sourceCity}, ${bookingDetails.sourceState}, ${bookingDetails.sourceZip}"/></td>  
            <td><c:out value="${bookingDetails.destinationAddressLine}, ${bookingDetails.destinationCity}, ${bookingDetails.destinationState}, ${bookingDetails.destinationZip}"></c:out></td>
            <td><c:out value="${bookingDetails.truck_TruckID}"/></td>
            <td><c:out value="${bookingDetails.type}"/></td>
        	<td>$<c:out value="${bookingDetails.price}"></c:out>
        </tr>
    </c:forEach>
</table>

</body>
</html>