<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.database.querybuilders.beans.UserX"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="mystyle.css"/>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header_2.jsp"/>
<c:import url="/ZipCodeServlet"></c:import>
<c:set var="zipCodeList" value="${requestScope.Location_Obj_Key}" />

<%-- <%
	UserX user = (UserX) session.getAttribute("User");
%>
<div class="userinfodiv">
<p><h3> Welcome  <%= user.getUserName() %> </h3></p>
</div> --%>

<!-- <div class="logoutdiv">
<form action="Logout" method="post">
<input type="submit" value="Logout" >
</form>
</div>
 -->
<form action="ConfirmBookingServlet" method="POST">

<div class="sourceaddressdiv">
<h3> Enter your source address</h3>
<input type="text" id="addrressLine1" name="addrressLine1" value="" placeholder="Address Line 1" required/>
<input type="text" id="addrressLine2" name="addrressLine2" value="" placeholder="Address Line 2" required/> <br/><br/>
<input type="text" id="city" name="city" value="" placeholder="City" required/>
<input type="text" id="state" name="state" value="" placeholder="State" required/> <br/><br/>

ZipCodes : <select class="target"  id="zipcode" name="zipcode" placeholder="zipcode">

<option value="-1">Select Zipcode</option>
<c:forEach items="${zipCodeList}" var="zipCode">
   <option value ="<c:out value="${zipCode.zipCode}"/>">${zipCode.zipCode}</option>
</c:forEach>
</select><br/><br/>
</div>

<div class="destinationaddressdiv">
<h3> Enter your destination address</h3>
<input type="text" id= "addrressLine1des" name="addrressLine1des" value="" placeholder="Address Line 1" required/>
<input type="text" id="addrressLine2des" name="addrressLine2des" value="" placeholder="Address Line 2" required/> <br/><br/>
<input type="text" id="citydes" name="citydes" value="" placeholder="City" required/>
<input type="text" id="statedes" name="statedes" value="" placeholder="State" required/> <br/><br/>

ZipCodes : <select id="zipcodedes" name="zipcodedes" placeholder="zipcode">
<option value="-1">Select Zipcode</option>
<c:forEach items="${zipCodeList}" var="zipCode">
   <option value ="<c:out value="${zipCode.zipCode}"/>">${zipCode.zipCode}</option>
</c:forEach>
</select><br/><br/>

</div>
<div class="truckdatadiv" id="truckdatadiv">

<table cellspacing="0" id="trucktable" border="5">
<tr>
<th scope="col">TruckId</th>
<th scope="col">Type</th>
<th scope="col">Rate Per Mile</th>
<th scope="col">Drive Name</th>
<th scope="col">Contact Number</th>
<th scope="col">Zipcode</th>
<th scope="col">Select</th>
</tr>
</table>
</div>

<div id="submitbuttondiv" class="submitbuttondiv">
<input type="button" id="submitbutton" name="submitbutton" value="EstimatedPrice"/>
</div>

<div id="dynamicpricediv" class="dynamicpricediv">
</div>

<div id="confirmbookingdiv" class="confirmbookingdiv">
<input type="submit" id="bookingconfirm" name="bookingconfirm" value="Confirm Booking"/>
</div>
</form>

</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$("#truckdatadiv").hide();
$("#submitbuttondiv").hide();
$("#confirmbookingdiv").hide();
$("#dynamicpricediv").hide();

$( ".target" ).change(function() {	  
	  $.ajax({
		  type: 'post',
		  url: 'TruxxServlet',
		  data: {
			  zipcode:$("#zipcode").val()
	          },
		  success: function (data) {
		  var result=data;
		  console.log(data);
		  $("#trucktable").find("tr:gt(0)").remove()
		  var table1 = $("#trucktable");

		  $.each(data, function(key,value) { 
		  	
		  	var radioHtml = '<input type="radio" id="truckradio" name="truckradio" value="'+value['truckId']+'"/>';
		  	
		  	 var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>"+radioHtml+"</td></tr>");
		  	     rowNew.children().eq(0).text(value['truckId']);
		  	     rowNew.children().eq(1).text(value['type']); 
		  	     rowNew.children().eq(2).text('$ '+value['hourlyPrice']); 
		  	     rowNew.children().eq(3).text(value['driverName']); 
		  	     rowNew.children().eq(4).text(value['driverPhoneNuber']); 
		  	     rowNew.children().eq(5).text(value['zipcode']); 
		  	    // rowNew.children().eq(6).html(radioHtml); 

		  	     rowNew.appendTo(table1);
		  	  });
		   $("#truckdatadiv").show();
		   $("#submitbuttondiv").show();
		  }

		  });

});
	
var form = $('#form1');
form.submit(function () {

$.ajax({
type: 'post',
url: 'TruxxServlet',
data: form.serialize(),
success: function (data) {
var result=data;
console.log(data);
$("#trucktable").find("tr:gt(0)").remove()
var table1 = $("#trucktable");

$.each(data, function(key,value) { 
	
	var radioHtml = '<input type="radio" id="truckradio" name="truckradio" value="'+value['truckId']+'"/>';
	
	 var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>"+radioHtml+"</td></tr>");
	     rowNew.children().eq(0).text(value['truckId']);
	     rowNew.children().eq(1).text(value['type']); 
	     rowNew.children().eq(2).text(value['hourlyPrice']); 
	     rowNew.children().eq(3).text(value['driverName']); 
	     rowNew.children().eq(4).text(value['driverPhoneNuber']); 
	     rowNew.children().eq(5).text(value['zipcode']); 
	    // rowNew.children().eq(6).html(radioHtml); 

	     rowNew.appendTo(table1);
	  });
 $("#truckdatadiv").show();
 $("#submitbuttondiv").show();
}

});
return false;
});

$("#submitbutton").click(function(e) {
		
	  $.ajax({
		  type: 'post',
		  url: 'DistancePriceCalculation',
		  data: {
			  addressline1:$('input[name=addrressLine1]').val(),
			  addrressLine2:$('input[name=addrressLine2]').val(),
			  city:$('input[name=city]').val(),
			  state:$('input[name=state]').val(),
			  zipcode:$("#zipcode").val(),

			  addressLine1des:$('input[name=addressLine1des]').val(),
			  addrressLine2des:$('input[name=addrressLine2des]').val(),
			  citydes:$('input[name=citydes]').val(),
			  statedes:$('input[name=statedes]').val(),
			  zipcodedes:$("#zipcodedes").val(),
			  truckId: $('input[name=truckradio]:checked').val(),
			  distance: $('input[name=distance]').val(),
			  price: $('input[name=dprice]').val()
	          },
		  success: function (data) {
		  var result=data;
		  var dynamicpricingdiv = $("#dynamicpricediv");
		  var dataDistance = '<p> distance : '+data.distance+' miles</p>';
		  var dataPrice = '<p> estimated price : '+data.price+'$  </p><br/>';
		  dataDistance = dataDistance + dataPrice ;
		  dataDistance = dataDistance + '<input type="hidden" id="distance" name="distance" value='+data.distance+'>';
		  dataDistance = dataDistance + '<input type="hidden" id="dprice" name="dprice" value='+data.price+'>';
		 
		  $( ".dynamicpricediv" ).html( dataDistance);
		  $("#dynamicpricediv").show();
		  $("#confirmbookingdiv").show();  
		  }
		  });
	
});
</script>
