<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<style>
h1{
	text-shadow:-1px 0 orange,0 1px orange,1px 0 orange,0 -1px orange;
}
</style>
</head>
<body>
	<div class="jumbotron jumbotron-fluid" style=" background-color: #0e253d;">
		<h1 class="display-4"OnClick="location.href ='${path}/movie/listall'" 
			style=" cursor:pointer; text-align:center; color:red;">
		<strong>See The Movie</strong></h1>
		<div align="right">
		<c:choose>
	        <c:when test="${empty login.userId }">
				<button class="btn btn-primary btn-sm btnLogin"
				onclick="javascript:location.href='${path}/user/login'">Login</button>
				<button class="btn btn-primary btn-sm btnJoin"
				onclick="javascript:location.href='${path}/user/register'">Register</button>
	        </c:when>
	        <c:otherwise>
	       		<button class="btn btn-primary btn-sm btnLogout" 
	       		onclick="javascript:location.href='${path}/user/logout'">Logout</button>
				<button class="btn btn-primary btn-sm btnModify"
				onclick="javascript:location.href='${path}/user/update'">Modify</button>
	        </c:otherwise>
	        </c:choose>
		</div>
		<div align="right">
			<c:if test="${not empty login.userId}">
			<p>${login.userId}님 안녕하세요!</p>
			</c:if>
		</div>
	</div>
	
	<script>
		
		$("#btnLogin").on("click", function() {
			self.location = "${path}/user/login"
		});
		$("#btnJoin").on("click", function() {
			self.location = "${path}/user/register"
		});
		$("#btnLogout").on("click", function() {
			self.location = "${path}/user/logout"
		});
		$("#btnModify").on("click", function() {
			self.location = "${path}/user/update"
		});

	</script>
</body>
</html>