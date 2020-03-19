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
		<div class="row">
			<c:forEach items="${movies}" var="movies">
				<div class="col-xl-6 col-md-6">
					<div class="card" style="width: 18rem;">
						<img src="${path}/movie/file/display?fileName=${movies.mainImage}" class="card-img-top" alt="...">
						<div class="card-body">
							<a href="${path}/movie/read${pageMaker.makeSearch(pageMaker.criteria.page)}&movieNo=${movies.movieNo}">
							<h5 class="card-title">${movies.movieTitle}</h5></a>
							<p class="card-text">${movies.movieSummary}</p>
								<div class="pull-right board_author" align="right">
										${movies.movieWriter}, <fmt:formatDate value="${movies.movieDate}"
										pattern="yyyy-MM-dd a HH:mm" />
								</div>
								<br>
								<div align="center">
									<button class="btn btn-info btn-lg" id="viewBtn" 
									onclick="javascript:location.href='${path}/movie/read${pageMaker.makeSearch(pageMaker.criteria.page)}&movieNo=${movies.movieNo}'">
									<i class="fas fa-eye"></i>View</button>
								</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<br>
	<br>
		<!-- 페이징 번호 -->
	<div class="container">
		<ul class="pagination justify-content-center">
			<c:if test="${pageMaker.prev}">
				<li class="page-item"><a class="page-link"
					href="${path}/movie/listall${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<c:choose>
					<c:when test="${pageMaker.criteria.page == idx}">
						<li class="page-link"><span style="color: red">${idx}</span></li>
					</c:when>
					<c:otherwise>
						<li class="page-link"><a
							href="${path}/movie/listall${pageMaker.makeSearch(idx)}">${idx}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li class="page-item"><a class="page-link"
					href="${path}/movie/listall${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
			</c:if>
		</ul>
	</div>
	<!-- 검색 -->
		<div class="container">
			<div class="row" align="center">
				<div class="form-group col-sm-2" style="margin: 0px 0px 0px 200px;">
					<select class="form-control" name="searchType" id="searchType">
						<option value="n"
							<c:out value="${searchCriteria.searchType == null ? 'selected' : '' }"/>>:::::
							선택 :::::</option>
						<option value="t"
							<c:out value="${searchCriteria.searchType == t ? 'selected' : '' }"/>>제목</option>
						<option value="c"
							<c:out value="${searchCriteria.searchType == c ? 'selected' : '' }"/>>내용</option>
						<option value="w"
							<c:out value="${searchCriteria.searchType == w ? 'selected' : '' }"/>>작성자</option>
						<option value="tc"
							<c:out value="${searchCriteria.searchType == tc ? 'selected' : '' }"/>>제목+내용</option>
						<option value="cw"
							<c:out value="${searchCriteria.searchType == cw ? 'selected' : '' }"/>>내용+작성자</option>
						<option value="tcw"
							<c:out value="${searchCriteria.searchType == tcw ? 'selected' : '' }"/>>제목+내용+작성자</option>
					</select>
				</div>
				<div class="form-group col-sm-4">
					<input type="text" class="form-control" name="keyword"
						id="keywordInput" value="${searchCriteria.keyword}"
						placeholder="검색어">
				</div>
				<span>
					<button type="button" class="btn btn-primary" id="searchBtn">
						<i class="fas fa-search"></i>검색
					</button>
				</span>
			</div>
			<div align="right">
				<button type="button" class="btn btn-success" id="writeBtn">
					<i class="fas fa-pen"></i>글쓰기
				</button>
			</div>
		</div>
	<%@ include file="../include/footer.jsp" %>
	<!-- js플러그인 -->
	<%@ include file="../include/plugin_js.jsp" %>
	<!-- js스크립트 -->
	<script>
		
		$(document).on("click",function(event){
			$("#searchBtn").on("click",function(event) {
			self.location = 
				"${path}/movie/listall${pageMaker.makeQuery(1)}"
				+ "&searchType=" + $("select option:selected").val()
				+ "&keyword=" + encodeURIComponent($("#keywordInput").val());
			});
		});
		
		$("#writeBtn").on("click", function() {
			self.location = "${path}/movie/write"
		});
		
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