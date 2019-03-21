<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BookingDetailsSummary</title>
</head>

<body bgcolor="LightGray">

<jsp:include page="./../Header_2.jsp"/>
<c:import var="test" url="/BookingDetailsSummaryServlet">
   <c:param name="BookingId">${requestScope.bookingId}</c:param>
</c:import>

<c:set var="bds" value="${requestScope.BOOKING_DETAILS_SUMMARY_OBJ}"/>
<style>
  .modal-content {
	 align:"center";
    background-color: #fefefe;
   margin: 7% 25% auto;
	/* 5% from the top, 15% from the bottom and centered */
    border: 10px solid #888;
	border-radius: 5px;
	
    width:50%;/* Could be more or less, depending on screen size */
}
.img1{
padding-top:2%;
	padding-left: 45%;
	padding-right:25%;
	}
</style>

<div class="modal-content" >
<img src="images/greentck.png" class="img1"/>
<H2 align='center'>Booking Confirmed</H2>
<div style="padding-left: 7%;" >
<p> Booking Id:<c:out value="${bds.bookingId}"/></p>
<p> CustomerName: <c:out value="${bds.customerName}"/></p></div>
<table border='0'width=100% style="padding-left: 10%;">
<col width=25% />
    <col width=25% />
    <col width=25% />
	<col width=25% />

<tr><td colspan='2'><b>Source address</b></td><td colspan='2'><b>Destination address<b></td></tr><tr colspan='4'></tr>
<tr><td> AddressLine</td><td><c:out value="${bds.sourceAddressLine}"/></td><td>AddressLine</td><td><c:out value="${bds.destinationAddressLine}"/></td></tr>
<tr><td>Source city</td><td><c:out value="${bds.sourceCity}"/></td><td>destination city</td><td> <c:out value="${bds.destinationCity}"/></td></tr>
<tr><td> Source state</td><td><c:out value="${bds.sourceState}"/></td><td>destination state</td><td> <c:out value="${bds.destinationState}"/></td></tr>
<tr><td> source zip</td><td><c:out value="${bds.source_Zipcode}"/></td><td>destination zip</td><td><c:out value="${bds.destination_Zipcode}"/></td></tr>
</table>
<br><br>
<table  align='center'border='0'align='center' style="padding-right: 5%;">
<col width=50% />
    <col width=50% />

<td colspan='2'><b>Truck Details</b></td><tr colspan='2'></tr>
<tr><td>Truck Id</td><td><c:out value="${bds.truckID}"/></td></tr>
<tr><td>Truck Driver</td><td><c:out value="${bds.driverName}"/></td></tr>
<tr><td>Truck Type </td><td> <c:out value="${bds.type}"/></td></tr>
<tr><td> </td><td> </td></tr>
</table>

<table border='0' style="padding-left: 5%;"width=100%>
<col width=50%/>
<col width=25%/><tr><td><b>Price: $<c:out value="${bds.price}"/></b></td><td></td><td><H4>Distance=<c:out value="${bds.distance}"/> miles</h4></td></tr></table>



</div>




</body>
</html>