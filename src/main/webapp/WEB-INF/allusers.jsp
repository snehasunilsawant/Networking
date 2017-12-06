<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>	

 <a href="/dashboard">My Profile</a>   |   <a href="/logout">Logout</a>    
<h3>Users you may want to connect with</h3>
	<table>
			<tr>
				<th>Name</th>
				<th>Action</th>				
			</tr>
			<c:forEach items="${allNotMyFriendsUsers}" var="user" >
    		<tr>
    					<td><a href="/user/${user.id }"><c:out value="${user.name}"/></a></td>	
    					<td><a href="/connect/${user.id}">Connect</a></td>			
			</tr>
			</c:forEach>
			
			</table>
</body>
</html>