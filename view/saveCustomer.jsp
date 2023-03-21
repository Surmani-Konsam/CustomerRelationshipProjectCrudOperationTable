
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>

<html>

<head>
<title>Customer Registration Form</title>
	
	

		<script src="<c:url value="/Resources/MoreFunctionality.js" />"></script>

		<link href="<c:url value="/Resources/styling.css"/>" rel="stylesheet">

		<style>
			.error{
				color:red;
				font-style: italic
				}
		</style>

	
	
</head>

<body>

<div style="height:45px; width:55%;margin-top:10px;background-color: lightblue">
	<span style="font-size:35px;font-family:Audiowide, sans-serif;font-weight:bold; color:Red;margin-left:10px; margin-top:10px">Save Customer Details</span>
</div>


<div style="margin-left:100px">

	<form:form action="fetchedDetails" modelAttribute="customerObject" method="POST"><br>
	
	<form:hidden path="id"/>
	
		<i>(*) means Required</i><br><br>
	
		First name(*) : <form:input id="first" path="firstName" name="firstName" style="margin-left:50px; width:200px" placeholder="Please enter your FirstName"/>
						<form:errors path="firstName" cssClass="error"/>
		
		<br><br>
	
		Last name(*) : <form:input id="last" path="lastName" style="margin-left:50px; width:200px" placeholder="Please enter your lastName"/>
						<form:errors path="lastName" cssClass="error"/>
		<br><br>
		
		Email Id(*) : <form:input id="email" path="emailId" style="margin-left:60px; width:200px" placeholder="Please enter your emailId"/>
					  <form:errors path="emailId" cssClass="error"/>
		<br><br>
	
		<button  type="submit" style="font-weight:bold">Submit</button>
	
	</form:form>

</div>

	


</body>

</html>












