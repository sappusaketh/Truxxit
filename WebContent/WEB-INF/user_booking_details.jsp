<%@page import="com.database.querybuilders.beans.BookingDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Booking details page</title>
</head>
<body>
<h1> Booking details page</h1>

<% List<BookingDetails> eList = (ArrayList<BookingDetails>) request.getAttribute("BOOKING_DETAILS_OBJ");
	pageContext.setAttribute("eList2", eList);
%>

<table>

<c:forEach var="bookingDeatils" items="${eList2}">
        <tr>
            <td>Booking ID: <c:out value="${bookingDeatils.bookingId}"/></td>
            <td>Address Id : <c:out value="${bookingDeatils.addressId}"/></td>  
        </tr>
    </c:forEach>
</table>

</body>
</html>