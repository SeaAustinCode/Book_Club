<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Book Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class = "mx-3">
		<div class = "d-flex justify-content-between">
			<h1><c:out value = "${book.title }"/></h1>
			<h6><a href= "/books/">back to the shelves</a></h6>
		</div>
		<div>
			<h3><c:out value = "${book.user.getName()}"></c:out>
			read <c:out value = "${book.title }"></c:out> by 
			<c:out value = "${book.author }"></c:out></h3>
			<h3>Here are <c:out value = "${book.user.getName()}"></c:out>'s thoughts:</h3>
			<hr>
			<p><c:out value = "${book.description }"></c:out></p>
		</div>
		<c:if test="${loggedInUser.id == book.user.id}"><a href="/books/${book.id }/edit">Edit</a></c:if>
	</div>
</body>
</html>