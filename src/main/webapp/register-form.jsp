<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Register Form</title>
</head>
<body>
	<div align="center">
		<form action="${pageContext.request.contextPath}/login-servlet?action=register" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>Register</h2>
				</caption>
				<tr>
					<th>Username:</th>
					<td><input type="text" name="username" size="45" required /></td>
				</tr>
				<tr>
					<th>Password:</th>
					<td><input type="password" name="password" size="45" required /></td>
				</tr>
				<tr>
					<th>Level:</th>
					<td>
						<select name="level">
							<option value="1">Level 1</option>
							<option value="2">Level 2</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Register" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>