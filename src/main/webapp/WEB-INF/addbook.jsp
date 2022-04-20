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
<title>Add book</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class ="d-flex justify-content-between">
	<h1>Add a Book to Your Shelf! </h1>
	<h4><a href="/books">back to the shelves</a></h4>
	</div>
	<form:form action="/books/new/method" method="post" modelAttribute="book">
	<form:input type="hidden" path="user" value="${user_id }"/>
		<form:label path="title">Title:</form:label>
		<form:input path="title" />
		<form:errors path="title" />
		<form:label path="author">Author:</form:label>
		<form:input path="author" />
		<form:errors path="author" />
		<form:label path="description">Description:</form:label>
		<form:input path="description" />
		<form:errors path="description" />
		<button>Create</button>
	</form:form>
</body>
</html>