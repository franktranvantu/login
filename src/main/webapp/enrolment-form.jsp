<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Enrolment Form</title>
</head>
<body>
	<center>
		<h4>
			<a href="${pageContext.request.contextPath}/enrolment-servlet?action=list">List All Enrolments</a>
			<br>
			<a href="${pageContext.request.contextPath}">Home</a>
		</h4>
	</center>
	<div align="center">
		<c:if test="${enrolment != null}">
			<form action="${pageContext.request.contextPath}/enrolment-servlet" method="post">
				<input type="hidden" name="action" value="update">
		</c:if>
		<c:if test="${enrolment == null}">
			<form action="${pageContext.request.contextPath}/enrolment-servlet?action=insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${enrolment != null}">Edit Enrolment</c:if>
					<c:if test="${enrolment == null}">Add New Enrolment</c:if>
				</h2>
			</caption>
			<c:if test="${enrolment != null}">
				<input type="hidden" name="id" value="<c:out value='${enrolment.getId()}' />" />
			</c:if>
			<tr>
				<th>Student Name:</th>
				<td>
					<select name="student_id">
						<c:forEach var="student" items="${students}">
							<option value="${student.getId()}">${student.getName()}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Subject Name:</th>
				<td>
					<select name="subject_id">
						<c:forEach var="subject" items="${subjects}">
							<option value="${subject.getId()}">${subject.getName()}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>