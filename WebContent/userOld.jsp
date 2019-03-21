<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Add new user</title>
</head>
<body>
<jsp:include page="Header_2.jsp"/>
    <script>
        $(function() {
            
        });
    </script>
    
     
    <form method="POST" action='AdminController' name="frmAddUser">
        Truck ID  <input type="text" readonly="readonly" name="truckId"
            value="<c:out value="${user.truckId}" />" /> <br><br>
        Availability  <input
            type="text" name="availability"
            value="<c:out value="${user.availability}" />" /> <br><br>
        <%-- Type : <input
            type="text" name="type"
            value="<c:out value="${user.type}" />" /> <br />  --%>
        RatePerMile  <input
            type="text" name="hourlyRate"
            value="<c:out value="${user.hourlyRate}" />" /> 
    <br><br>
        Location  <select class="target"  id="locationId" name="location" placeholder="location">

<c:forEach items="${user.city}" var="cit">
   <option value ="<c:out value="${cit}"/>">${cit}</option>
</c:forEach>
</select>

Size  
    <select name="type">
        <option value="Small">Small</option>
        <option value="Medium">Medium</option>
        <option value="Large">Large</option>
    </select>
    
    <br><br>
    Address  <input type="text" name="address" value="<c:out value="${user.address}" />" /> &nbsp;
    FirstName  <input type="text" name="firstName"value="<c:out value="${user.firstName}" />" /> &nbsp;
    LastName  <input type="text" name="lastName"value="<c:out value="${user.lastName}" />" /> &nbsp;<br><br>
    Email  <input type="text" name="email"value="<c:out value="${user.email}" />" /> &nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Phone  <input type="text" name="phone"value="<c:out value="${user.phone}" />" /> &nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Salary  <input type="text" name="salary"value="<c:out value="${user.salary}" />" />&nbsp;<br><br>
    <input type="submit" value="Submit">
    </form>
</body>
</html>