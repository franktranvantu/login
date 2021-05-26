<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student List</title>
</head>
<body style="font-family: arial, serif;">
	<div align="center" cellpadding=10>
		<table>
			<h2>List of Students</h2>
			<center>
				<h4>
					<a href="${pageContext.request.contextPath}/student-servlet?action=new">Add New Student</a>
					<br>
					<a href="${pageContext.request.contextPath}">Home</a>
				</h4>
			</center>
			<tr align=center>
				<th>ID</th>
				<th>Name</th>
				<th>Status</th>
				<th>Major</th>
				<th>Actions</th>

			</tr>
			<c:forEach var="student" items="${students}">
				<tr align=center>
					<td><c:out value="${student.getId()}" /></td>
					<td><c:out value="${student.getName()}" /></td>
					<td><c:out value="${student.getStatus()}" /></td>
					<td><c:out value="${student.getMajor()}" /></td>
					<td>
						|<a href="${pageContext.request.contextPath}/student-servlet?action=edit&id=<c:out value='${student.getId()}' />">Edit</a>|
						|<a	href="${pageContext.request.contextPath}/student-servlet?action=delete&id=<c:out value='${student.getId()}' />">Delete</a>|
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>