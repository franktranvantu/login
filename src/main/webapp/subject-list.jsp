<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Subject List</title>
</head>
<body style="font-family: arial, serif;">
	<div align="center" cellpadding=10>
		<table>
			<h2>List of Subjects</h2>
			<center>
				<h4>
					<a href="${pageContext.request.contextPath}/subject-servlet?action=new">Add New Subject</a>
					<br>
					<a href="${pageContext.request.contextPath}">Home</a>
				</h4>
			</center>
			<tr align=center>
				<th>ID</th>
				<th>Name</th>
				<th>Prerequisite</th>
				<th>Actions</th>

			</tr>
			<c:forEach var="subject" items="${subjects}">
				<tr align=center>
					<td><c:out value="${subject.getId()}" /></td>
					<td><c:out value="${subject.getName()}" /></td>
					<td><c:out value="${subject.getPrerequisite()}" /></td>
					<td>
						|<a href="${pageContext.request.contextPath}/subject-servlet?action=edit&id=<c:out value='${subject.getId()}' />">Edit</a>|
						|<a	href="${pageContext.request.contextPath}/subject-servlet?action=delete&id=<c:out value='${subject.getId()}' />">Delete</a>|
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>