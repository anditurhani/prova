<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List</title>
</head>
<body>
    <div style="text-align: center">${message}</div>
	<ul>
		<c:forEach var="city" items="${cityList}">
			<li>${city.name} ${city.population} 
			     <br><a href="http://localhost:8080/world_list/updateForm?id=${city.id}">modify</a>
			</li>
		</c:forEach>
	</ul>
	<div style="text-align: center">
		<a href="http://localhost:8080/world_list/countries?continent=${continent}">indietro</a>
	</div>
</body>
</html>