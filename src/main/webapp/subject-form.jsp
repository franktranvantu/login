<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Subject Form</title>
</head>
<body>
	<center>
		<h4>
			<a href="${pageContext.request.contextPath}/subject-servlet?action=list">List All Subjects</a>
		</h4>
	</center>
	<div align="center">
		<c:if test="${subject != null}">
			<form action="${pageContext.request.contextPath}/subject-servlet" method="post">
				<input type="hidden" name="action" value="update">
		</c:if>
		<c:if test="${subject == null}">
			<form action="${pageContext.request.contextPath}/subject-servlet?action=insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${subject != null}">Edit Subject</c:if>
					<c:if test="${subject == null}">Add New Subject</c:if>
				</h2>
			</caption>
			<c:if test="${subject != null}">
				<input type="hidden" name="id" value="<c:out value='${subject.getId()}' />" />
			</c:if>
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name" size="45" required value="<c:out value='${subject.getName()}' />" /></td>
			</tr>
			<tr>
				<th>Prerequisite:</th>
				<td><input type="text" name="prerequisite" size="45" required value="<c:out value='${subject.getPrerequisite()}' />" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>