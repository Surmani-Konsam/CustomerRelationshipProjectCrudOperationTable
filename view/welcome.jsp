<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Customer</title>


<script src="<c:url value="/Resources/CustomerRealationFunctionality.js" />"></script>


<link href="<c:url value="/Resources/styling.css"/>" rel="stylesheet">


</head>
<body>


<div style="height:45px; width:55%;margin-top:10px;background-color: lightblue">
	<span style="font-size:35px;font-family:Audiowide, sans-serif;font-weight:bold; color:Red;margin-left:10px; margin-top:10px">CRM - Customer Relationship Manager</span>
</div>


<!-- this will add customer to the table now!!! -->
<!-- on-click="addRow('dataTable')" -->
<div style="margin-top:10px">

<a href="editCustomerDetails"><button style="font-weight: bold">Add Customer!!!</button></a>
<a href="clearCustomerData"><button style="font-weight: bold" onclick="if(!(confirm('Are you sure you want to delete all entry. Your previous entry will also get deleted???'))) return false">Clear All Customer Data!!!</button></a>
</div>


<!-- need to create a table now!!! -->
<table id="dataTable" border="1" style="margin-top:10px">

<!-- table header section!!! -->
	<thead>
		<tr style="background-color:lightblue">
			<th class="header" id="first" style="width:150px">First_Name</th>
			<th class="header" id="second" style="width:150px">Last_Name</th>
			<th class="header" id="email" style="width:200px">Customer_email_id</th>
			<th class="header" id="action">Action</th>
		</tr>
	</thead>
	<tbody>
            <c:forEach items="${customers}" var="customer">
            
            <c:url var="updateLink" value="showFormUpdate">
            	<c:param name="tableId" value="${customer.id}"/>
            </c:url>
            
  
            
            <!-- constructing delete link for the delete operation -->
            <c:url var="deleteLink" value="deleteFormUpdate">
            	<c:param name="tableId" value="${customer.id}"/>
            </c:url>
                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.emailId}</td>
                    <td>
                    <!--update link is the link that we created-->
                     	<a href="${updateLink}" style="text-decoration:none;font-weight:bold">
                     		<button style="font-weight:bold">Update</button>
                     	</a>
                     
                     <!-- creating button for deletion of customer object using the session by the customer id -->
                     	<a href="${deleteLink}" style="margin-left:20px;text-decoration:none">
                     		<button style="font-weight:bold" onclick="if(!(confirm('Are you sure you want to delete this customer????'))) return false">Delete</button>
                     	</a>
                     </td>
                    </tr>
            </c:forEach>
            </tbody>
	<tbody>
	</tbody>
	
	
</table>

</body>
</html>