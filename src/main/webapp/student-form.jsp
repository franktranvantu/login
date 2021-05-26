<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Form</title>
</head>
<body>
	<center>
		<h4>
			<a href="${pageContext.request.contextPath}/student-servlet?action=list">List All Students</a>
			<br>
			<a href="${pageContext.request.contextPath}">Home</a>
		</h4>
	</center>
	<div align="center">
		<c:if test="${student != null}">
			<form action="${pageContext.request.contextPath}/student-servlet" method="post">
				<input type="hidden" name="action" value="update">
		</c:if>
		<c:if test="${student == null}">
			<form action="${pageContext.request.contextPath}/student-servlet?action=insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${student != null}">Edit Student</c:if>
					<c:if test="${student == null}">Add New Student</c:if>
				</h2>
			</caption>
			<c:if test="${student != null}">
				<input type="hidden" name="id" value="<c:out value='${student.getId()}' />" />
			</c:if>
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name" size="45" required value="<c:out value='${student.getName()}' />" /></td>
			</tr>
			<tr>
				<th>Status:</th>
				<td>
					<select name="status">
						<option value="Part time" ${student.getStatus() == 'Part time' ? 'selected' : ''}>Part time</option>
						<option value="Full time" ${student.getStatus() == 'Full time' ? 'selected' : ''}>Full time</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>Major:</th>
				<td>
					<select name="major">
						<option value="B" ${student.getMajor() == 'B' ? 'selected' : ''}>B</option>
						<option value="N" ${student.getMajor() == 'N' ? 'selected' : ''}>N</option>
						<option value="D" ${student.getMajor() == 'D' ? 'selected' : ''}>D</option>
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