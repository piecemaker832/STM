<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<head>
<%@ include file="../include/head.jsp" %>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<div class="container">
		<form action="${path}/user/loginPost" method="post">
			<div class="container bg-light rounded shadow-sm" style = "padding-top : 150px; padding-bottom : 200px; margin-bottom : 50px">
				<h1 class="text-center" style = "padding-bottom : 30px;">로그인</h1>
				<div align="center">
				<div class="col-6 col-sm-3">
					<input type="text" name="userId" class="form-control"
						placeholder="아이디">
				</div>
				<div class="col-6 col-sm-3">
					<input type="password" name="userPass" class="form-control"
						placeholder="비밀번호">
				</div>
				<br>
				<div>
					<button type="button" class="btn btn-sm btn-primary" id="join">회원가입</button>
					<button class="btn btn-sm btn-success" type="submit">로그인</button>
					<label><input type="checkbox" name="useCookie">자동로그인</label>
				</div>
				</div>
			</div>
		</form>
		
	</div>
	
	<%@ include file="../include/footer.jsp" %>
	<!-- js플러그인 -->
	<%@ include file="../include/plugin_js.jsp" %>
	<!-- js스크립트 -->
	<script>
			$("#join").on("click", function() {
			self.location = "${path}/user/register"
		});
	</script>
</body>
</html>