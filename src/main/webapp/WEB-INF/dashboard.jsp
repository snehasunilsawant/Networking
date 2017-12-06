<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<a href="/logout">Logout</a>    |   <a href="/allusers">All Users</a>
	<h1>Welcome , ${ name }</h1>
	
	<fieldset>
		<legend>Here is your profile description</legend>
		<h4>${ description }</h4>
	</fieldset>	
	
	<fieldset>
		<legend>Your Professional Network</legend>
				<c:forEach items="${user.friends}" var="myfriend" >      			
    				<p><a href="/user/${myfriend.id}"><c:out value="${myfriend.name}"/></a></p>
    			</c:forEach>				
	</fieldset>
	
	<h3>Invitations</h3>
	<p>The Following people asked you to be their network:</p>
	
	<table>
			<tr>
				<th>Name</th>
				<th>Action</th>				
			</tr>
			<c:forEach items="${myinvitations}" var="invitation" >
    		<tr>
    		 	<td><a href="/user/${ invitation.id }"><c:out value="${invitation.name}"/></a></td>	
    		 	<td><a href="/acceptinvite/${ invitation.id }">Accept Invite</a>  |  <a href="/ignoreinvite/${ invitation.id }">Ignore</a></td>			
			</tr>
			</c:forEach>
			
			</table>
	
	
</body>
</html>