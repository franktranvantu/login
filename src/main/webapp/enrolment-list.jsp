<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Enrolment Management</title>
</head>
<body style="font-family: arial, serif;">
	<div align="center" cellpadding=10>
		<table>
			<h2>List of Enrolments</h2>
			<center>
				<h4>
					<a href="${pageContext.request.contextPath}/enrolment-servlet?action=new">Add New Enrolment</a>
				</h4>
				<div align="center">
					<a href="student-servlet">Student Management</a> | <a href="subject-servlet">Subject Management</a>
				</div>
				<br>
			</center>
			<tr align=center>
				<th>ID</th>
				<th>Student Name</th>
				<th>Subject Name</th>
				<th>Actions</th>

			</tr>
			<c:forEach var="enrolment" items="${enrolments}">
				<tr align=center>
					<td><c:out value="${enrolment.getId()}" /></td>
					<td><c:out value="${enrolment.getStudentName()}" /></td>
					<td><c:out value="${enrolment.getSubjectName()}" /></td>
					<td>
						|<a href="${pageContext.request.contextPath}/enrolment-servlet?action=edit&id=<c:out value='${enrolment.getId()}' />">Edit</a>|
						|<a	href="${pageContext.request.contextPath}/enrolment-servlet?action=delete&id=<c:out value='${enrolment.getId()}' />">Delete</a>|
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>