<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<h2>${message}</h2>
	<form action="showMessage" id="loginForm" method="post">
		<input type="text" id="id" name="id" value="${id}" />
		<input type="text" id="password" name="password" value="${password}" />
		<input type="submit" />
	</form>
</body>
</html>
