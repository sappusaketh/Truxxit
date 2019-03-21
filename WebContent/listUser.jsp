<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>
<body>
<jsp:include page="Header_2.jsp"/>
<link rel="stylesheet" type="text/css" href="mystyle.css"/>
    <table border=1>
        <thead>
            <tr>
                <th>TruckId</th>
                <th>Availability</th>
                <th>Type</th>
                <th>HourlyRate</th>
                <th>Location</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Salary</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>                
                    <td><c:out value="${user.truckId}" /></td>
                    <td><c:out value="${user.availability}" /></td>
                    <td><c:out value="${user.type}" /></td>
                    <td><c:out value="${user.hourlyRate}" /></td>
                    <td><c:out value="${user.location}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.phone}" /></td>
                    <td><c:out value="${user.address}" /></td>
                    <td><c:out value="${user.salary}" /></td>
                    <td><a href="AdminController?action=edit&userId=<c:out value="${user.truckId}"/>">Update</a></td>
                    <td><a href="AdminController?action=delete&userId=<c:out value="${user.truckId}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
     
</body>
</html>