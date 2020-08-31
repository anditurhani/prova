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
    <div style="text-align: right"><a href="http://localhost:8080/world_list/searchForm">search</a></div>
    <div style="text-align: right"><a href="http://localhost:8080/world_list/updateForm?id=">insert city</a></div>
	<ul>
		<c:forEach var="continent" items="${continentList}">
			<li>
			<a href="http://localhost:8080/world_list/countries?continent=${continent.name}">${continent.name}</a></li>
		</c:forEach>
	</ul>
</body>
</html>