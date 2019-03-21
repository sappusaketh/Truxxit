<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:import url="/ZipCodeServlet"></c:import>
<c:set var="zipCodeList" value="${requestScope.Location_Obj_Key}" />

<%-- <c:out value="${zipCodeList}"/> --%>
<h3> Enter your address for pick up</h3>

<div id="sourceformdata">
<form name="form1" id="form1">

<input type="text" name="addrressLine1" value="" placeholder="Address Line 1" />
<input type="text" id="addrressLine2" name="addrressLine2" value="" placeholder="Address Line 2" /> <br/><br/>
<input type="text" id="city" name="city" value="" placeholder="City" />
<input type="text" id="state" name="state" value="" placeholder="State" /> <br/><br/>

ZipCodes : <select class="target"  id="zipcode" name="zipcode" placeholder="zipcode">

<c:forEach items="${zipCodeList}" var="zipCode">
   <option value ="<c:out value="${zipCode.locationId}"/>">${zipCode.zipCode}</option>
</c:forEach>
</select><br/><br/>

<!-- <input type="submit" value ="submit"/> -->
</form>
</div>

<h3> Enter your destination address</h3>
<div id="formdata">
<input type="text" name="addrressLine1des" value="" placeholder="Address Line 1" />
<input type="text" id="addrressLine2des" name="addrressLine2" value="" placeholder="Address Line 2" /> <br/><br/>
<input type="text" id="city" name="citydes" value="" placeholder="City" />
<input type="text" id="state" name="statedes" value="" placeholder="State" /> <br/><br/>

ZipCodes : <select id="zipcodedes" name="zipcodedes" placeholder="zipcode">

<c:forEach items="${zipCodeList}" var="zipCode">
   <option value ="<c:out value="${zipCode.locationId}"/>">${zipCode.zipCode}</option>
</c:forEach>
</select><br/><br/>

</div>
<div id="truckdatadiv">

<table cellspacing="0" id="trucktable" border="5">
<tr>
<th scope="col">TruckId</th>
<th scope="col">Type</th>
<th scope="col">Hourly Rate</th>
<th scope="col">Drive Name</th>
<th scope="col">Contact Number</th>
<th scope="col">zipcode</th>
<th scope="col">radiobutton</th>
</tr>
</table>
</div>
<br/><br/>
<div id="submitbuttondiv">
<input type="submit" id="submitbutton" name="submitbutton" value="submit"/>
</div>

<div id="radiobuttondiv">
</div>

</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$("#truckdatadiv").hide();
$("#submitbuttondiv").hide();

$( ".target" ).change(function() {	  
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
	 var selValue = $('input[name=truckradio]:checked').val(); 
	alert(selValue);
});
</script>
