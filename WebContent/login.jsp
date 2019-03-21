<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor='lightgray'>
<style>
.loginform
{

display:inline-block;
padding-left:5%;
padding-top:5%;
padding-right:5%;
padding-bottom:5%;
background-color:white;
border-radius:10px;
margin-top:10%;
margin-left:37%;
margin-right:10%;
}
img{
margin-top:0%;
margin-bottom:90%;

}
input[type=text]{
	width: 100%;
    padding: 10px 10px ;
	padding-right:90px;
    margin: 5px 0;
    box-sizing: border-box;
    border: 1px solid black;
    border-radius: 4px;
	}
	input[type=password]{
	width: 100%;
    padding: 10px 10px;
    margin: 5px 0;
    box-sizing: border-box;
    border: 1px solid black;
    border-radius: 4px;
	}
	input[type=submit] {
    background-color: white; /* Green */
    border: 1px solid #008CBA;
    color: black;
    padding: 10px 32px;
    text-align: center;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
}


.button:hover {
    background-color: #008CBA;
    color: white;
}

</style>
<h1 align='center'>Truxx4U</h1>
<div class="loginform">

<form  action="LoginServlet" method="post">

<input type="text" name="email" placeholder="email" required><br>
<input type="password" name="password" placeholder="password" required><br><br>
<input type="submit" class="button"value="Login">
</form>
<br>

</div>
</body>
</html>