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
<%@ include file="../include/head.jsp"%>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<div class="container">
	<div class="row" >
		<div id="carouselExampleIndicators" class="carousel slide col-md-4"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<c:forEach items="${image}" var="image" varStatus="status">
					<c:if test="${status.count eq 1 }">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
					</c:if>
					<c:if test="${status.count ne 1 }">
					<li data-target="#carouselExampleIndicators" data-slide-to="${status	.count}"></li>
					</c:if>
				</c:forEach>
			</ol>
			<div class="carousel-inner">
				<c:forEach items="${image}" var="image" varStatus="status">
					<c:if test="${status.count eq 1 }">
						<div class="carousel-item active">
							<img src="${path}/movie/file/display?fileName=${image.mainImage}"
								class="d-block w-100" alt="...">
						</div>
					</c:if>
					<c:if test="${status.count ne 1 }">
					<div class="carousel-item">
							<img src="${path}/movie/file/display?fileName=${image.mainImage}"
								class="d-block w-100" alt="...">
					</div>
					</c:if>
				</c:forEach>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<div>
		<div class="bg-light rounded shadow-sm">
			<div class="board_title">${movies.movieTitle}</div>
			<div class="board_content">${movies.movieSummary}</div>
			<div class="board_info_box">
				<span class="board_author">${movies.movieWriter},
				<fmt:formatDate
						pattern="yyyy-MM-dd a HH:mm" value="${movies.movieDate}" />, Hit:${movies.movieHit}</span>
				<span align="right">
					<P id="rating">
					Rating
					<a id="1">★</a> 
					<a id="2">★</a> 
					<a id="3">★</a> 
					<a id="4">★</a> 
					<a id="5">★</a>(${movies.movieRating} / 5)</P> 
				</span>
			</div>
		</div>
		<div>
		<div class="container">
				<form id="commentForm" name="commentForm" method="post">
					<br> <br>
					<div>
						<div>
							<span><strong>Comments</strong></span> <span id="replyCount"></span>
						</div>
						<div>
							<c:if test="${not empty login.userId}">
							<table class="table">
								<tr>
									<input type="hidden" id="newReplyWriter" value=${login.userId }> 
									<td><textarea style="width: 400px" rows="3" cols="30"
											id="comment" name="comment" placeholder="댓글을 입력하세요"></textarea>
										<br>
										<div>
											<button type="button" id="addBtn" class="btn pull-right btn-success replyAddBtn">등록</button>
										</div></td>
								</tr>
							</table>
							</c:if>
							<c:if test="${empty login.userId}">
								<a href="${path}/user/login" style="margin-top: 10px; margin-bottom:20px" class="btn btn-secondary" role="button">
									로그인 한 사용자만 댓글 등록이 가능합니다.
								</a>
							</c:if>
						</div>
					</div>
				</form>
			</div>
				<div class="box box-success replybox">
					<div class="box-body repliesDiv"></div>
						<div class="box-footer box-tool">
							<div class="text-center">
								<ul class="pagination justify-content-center">

								</ul>
							</div>
						</div>
					</div>
				</div>
	</div>

	</div>
		<div class="box-footer">
			<form role="form" method="post">
				<input type="hidden" name="movieNo" value="${movies.movieNo}">
				<input type="hidden" name="page" value="${searchCriteria.page}">
				<input type="hidden" name="perPageNum" value="${searchCriteria.perPageNum}">
				<input type="hidden" name="searchType" value="${searchCriteria.searchType}">
				<input type="hidden" name="keyword" value="${searchCriteria.keyword}">
			</form>
			<div align="right">
				<button type="submit" class="btn btn-sm btn-primary listBtn">목록</button>
				<c:if test="${login.userId eq movies.movieWriter}">
				<button type="submit" class="btn btn-sm btn-warning modBtn">수정</button>
				<button type="submit" class="btn btn-sm btn-danger delBtn">삭제</button>
				</c:if>
			</div>
		</div>
	</div>
	<!-- 댓글 수정,삭제 모달 -->
	<%@ include file="../include/modal.jsp"%>
	
	<%@ include file="../include/footer.jsp"%>
	<%@ include file="../include/plugin_js.jsp"%>
	
	
	
	<script id="replyTemplate" type="text/x-handlebars-template">

    {{#each.}}
    <div class="post replyDiv" style="border-bottom : 10px solid white; background-color: #f3f2e18f;" data-replyNo={{replyNo}}>
        <div class="user-block">
			<span>
                <%--작성자 이름--%>
                <a href="#" style="font-weight:bold;"><font size="4" color="#0B610B">{{replyWriter}}</a></font>
			</span>
			<c:if test="${login.userId eq movies.movieWriter}">
			<span style="text-align:right;">
                <%--댓글 삭제 버튼--%>
                <a href="#" class="replyDelBtn" data-toggle="modal" data-target="#delModal"><font color="#da3e3e">
                    <i class="fa fa-times"> 삭제</i>
                </a></font>
                <%--댓글 수정 버튼--%>
                <a href="#" class="replyModBtn" data-toggle="modal" data-target="#modModal"><font color="#5d93d6">
                    <i class="fa fa-edit"> 수정</i>
                </a></font>
			</span>
			</c:if>
			<br/>
            <%--댓글 작성일자--%>
            <font size="2">{{prettifyDate regDate}}</font>
        </div>
        <%--댓글 내용--%>
        <div class="oldReplyText">{{{escape replyText}}}</div>
        <br/>
    </div> 
    {{/each}} <%-- c:forEach 와 같은 배열의 루프처리 --%>
	</script>
	
	
	<script type="text/javascript">
			
	        // 전역변수 선언
	        // 게시글 번호
	        var movieNo = ${movies.movieNo};
	        var movieWriter = "${movies.movieWriter}";
	        console.log(movieNo)
	        
	        // 댓글 페이지 초기화
	        var replyPage = 1;
	        
	</script>
	
	<script type="text/javascript" src="${path}/resources/reply.js"></script>
	
	<script>
	        
			var ra = ${movies.movieRating};
			$('#rating a').val(ra);
			console.log($('#${movies.movieRating}').val());
			$('#${movies.movieRating}').addClass("on").prevAll("a").addClass("on");
			
			var formObj = $("form[role='form']");
			console.log(formObj);
			
			$(".modBtn").on("click",function() {
				formObj.attr("action","${path}/movie/update");
				formObj.attr("method","get");
				formObj.submit();
			});
			
			$(".delBtn").on("click",function() {
				formObj.attr("action","${path}/movie/delete");
				formObj.submit();
			});
			
			$(".listBtn").on("click",function() {
				formObj.attr("action","${path}/movie/listall");
				formObj.attr("method","get");
				formObj.submit();
			});
		
	</script>
</body>
</html>