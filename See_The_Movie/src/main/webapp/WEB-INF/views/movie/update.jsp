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
<script>
	$(document).ready(function(){
		$("#btnSave").click(function(){
			var movieTitle = $("#movieTitle").val();
			var movieSummary = $("#movieSummary").val();
			
			if(movieTitle == ""){
				alert("제목을 입력하세요");
				document.writeForm.movieTitle.focus();
				return false;
			}
			
			if(movieSummary == ""){
				alert("내용을 입력하세요");
				document.writeForm.movieSummary.focus();
				return false;
			}
			
			document.writeForm.submit();
		});
	});
</script>
<%@ include file="../include/head.jsp" %>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<form name="writeForm" role="form" id="writeForm" method="post"
		action="${path}/movie/write">
		<div class="container">
			<div>
				<h2>게시글 수정</h2>
			</div>
			<div>
				<input type="hidden" name="movieNo" value="${movies.movieNo}">
				<input type="hidden" name="page" value="${searchCriteria.page}">
				<input type="hidden" name="perPageNum" value="${searchCriteria.perPageNum}"> 
				<input type="hidden" name="searchType" value="${searchCriteria.searchType}">
				<input type="hidden" name="keyword" value="${searchCriteria.keyword}">
					
				<div class="mb-3">
					<label for="movieTitle">Title</label> <input class="form-control"
						id="movieTitle" name="movieTitle" placeholder="제목을 입력해주세요">
				</div>
				<div class="mb-3">
					<label for="movieSummary">Summary</label>
					<textarea class="form-control" id="movieSummary"
						name="movieSummary" rows="7" placeholder="내용을 입력해주세요"
						style="resize: none;">${movies.movieSummary}</textarea>
				</div>
				<div class="mb-3">
					<label for="movieRating">Rating</label> <input type="hidden"
						name="movieRating" id="movieRating" />
					<P id="rating"><!-- 부모 -->
						<a href="#" value="1">★</a><!-- 자식들-->
						<a href="#" value="2">★</a> 
						<a href="#" value="3">★</a> 
						<a href="#" value="4">★</a> 
						<a href="#" value="5">★</a>
					</p>
				</div>
				<div class="mb-3">
					<label for="moviedWriter">Writer</label> <input
						class="form-control" id="movieWriter" name="movieWriter" value="${movies.movieWriter}">
				</div>
				<div class="mb-3">첨부파일(미구현)</div>

			</div>
			<div align="right">
				<button class="btn btn-primary listBtn" type="button">목록</button>
				<button class="btn btn-secondary cancelBtn" type="reset">취소</button>
				<button type="submit" class="btn btn-success modBtn">저장</button>
			</div>
		</div>
	</form>
	<%@ include file="../include/footer.jsp"%>
	<!-- js플러그인 -->
	<%@ include file="../include/plugin_js.jsp"%>
	<script>
	$(document)
	.ready(
			function() {
				var formObj = $("form[role='form']");
				console.log(formObj);

				$(".modBtn").on("click", function() {
					formObj.submit();
				});
				$(".cancelBtn").on("click", function() {
					history.go(-1);
				});
			});
	
	$('#rating a').click(function(){
		$(this).parent().children("a").removeClass("on");
		$(this).addClass("on").prevAll("a").addClass("on");
		console.log($(this).attr("value"));
		$('#movieRating').val($(this).attr("value"));
	});

	</script>
</body>
</html>