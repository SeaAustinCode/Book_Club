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
<title>Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class ="d-flex justify-content-between"> 
	<h1> Welcome, ${loggedInUser.name} id: ${user_id }</h1> <!--loggedInUser comes from book controller line 38 variable name gives access to User object  -->
	<h6><a href= "/logout/method">Logout</a></h6>
	</div>
	<div class ="d-flex justify-content-between">
	<h2>Books from everyone's shelves:</h2>
	<h6><a href ="/books/new">+ Add a book to my shelf!</a></h6>
	</div>
	<div>
		<table class="table table-striped table-dark table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allBooks }" var="book"><!-- allBooks comes from the bookcontroller it is the List<Book>allBooks -->
					<tr>
						<td><c:out value="${book.id }"></c:out></td>
						<td><a href="/books/${book.id }"><c:out value="${book.title }"/></a></td>
						<%-- <td><a href="/books/${book.id }"></td><c:out value="${book.title }"></c:out></td> --%>
						<td><c:out value="${book.author }"></c:out></td>
						<td><c:out value="${book.user.name }"></c:out></td>
						<td><form action="/remove/book/${book.id}/method" method="post">
	    						<input type="hidden" name="_method" value="delete">
	    						<input type="submit" value="Delete"></td>
							</form>
						<td><c:if test="${loggedInUser.id == book.user.id}"></c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
				
	</div>
</body>
</html>