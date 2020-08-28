<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form</title>
</head>
<body>
	<div class="topnav">
		<form action="/world_list/updateCities" method="GET">
		    <input type="hidden" name="id" value="${id}">
			<input type="text" name="name" required value="${name}">
			<input type="text" name="population" required value="${population}"> 
			<label for="country">Choose a country:</label> 
			<select name="countrycode" id="country" required>
			    <option disabled selected value="${null}"> -- select a country -- </option>
				<c:forEach var="country" items="${countryList}">
				    <c:choose>
				        <c:when test = "${country.code == countrycode}">
					        <option value="${country.code}" selected>${country.name}</option>
					    </c:when>
					    <c:otherwise>
					        <option value="${country.code}">${country.name}</option>
					    </c:otherwise>
					</c:choose>
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