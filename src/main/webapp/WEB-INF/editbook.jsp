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
<meta charset="UTF-8">
<title>Edit Book</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div>
	<h1>Edit Book</h1>

	<form:form action="/books/${book.id}/method" method="post" modelAttribute="book">	
		<input type="hidden" name="_method" value="put">
		<form:input type="hidden" value="${book.user.id }" path="user"/>
		<p>
			<form:label path="title">Title</form:label>
			<form:errors path="title" />
			<form:input path="title" placeholder="Title" />
		</p>
		<p>
			<form:label path="author">Author</form:label>
			<form:errors path="author" />
			<form:input path="author" placeholder="Author"/>
		</p>
		<p>
			<form:label path="description">My Thoughts</form:label>
			<form:errors path="description" />
			<form:input path="description" placeholder="My Thoughts"/>
		</p>
			<input type="submit" value="Submit" />
	</form:form>
</div>
</body>
</html>
