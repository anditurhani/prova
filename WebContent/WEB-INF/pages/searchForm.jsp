<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<body>
    <div class="topnav">
		<form action="/world_list/search" method="GET">
			<input type="text" name="searchStr" placeholder="Search.."> <label
				for="country">Choose a country:</label> <select name="countrycode"
				id="country">
				<option value="${null}">Nessuna Nazione</option>
				<c:forEach var="country" items="${countryList}">
					<option value="${country.code}">${country.name}</option>
				</c:forEach>
			</select> <br>
			<input type="submit" value="submit">
		</form>
	</div>
	
	<div style="text-align: center">
		<a href="http://localhost:8080/world_list/continents">continents</a>
	</div>

</body>
</html>