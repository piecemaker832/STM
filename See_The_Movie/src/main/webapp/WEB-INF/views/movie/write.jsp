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
			var movieRating = $("#movieRating").val();
			
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
			
			if(movieRating == ""){
				alert("별점을 표시해주세요");
				return false;
			}
			
			if($(".mailbox-attachment-name").attr("href")==null) {
				alert("이미지를 업로드 해주세요");
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
	<form name="writeForm" role="form" id="writeForm" method="post" enctype="multipart/form-data"
		action="${path}/movie/write">
	<div class="container">
			<div>
				<h2>게시글 작성</h2>
			</div>
			<div>
				<div class="mb-3">
					<label for="movieTitle">Title</label> <input class="form-control"
						id="movieTitle" name="movieTitle" placeholder="제목을 입력해주세요">
				</div>
				<div class="mb-3">
					<label for="movieSummary">Summary</label>
					<textarea class="form-control" id="movieSummary"
						name="movieSummary" rows="7" placeholder="내용을 입력해주세요"
						style="resize: none;"></textarea>
				</div>
				<div class="mb-3">
					<label for="movieRating">Rating</label>
					<input type="hidden" name="movieRating" id="movieRating" />
					<P id="rating"> <!-- 부모 --> 
					<a href="#" value="1">★</a> <!-- 자식들--> 
					<a href="#" value="2">★</a> 
					<a href="#" value="3">★</a> 
					<a href="#" value="4">★</a> 
					<a href="#" value="5">★</a> </p>
				</div>
				<div class="mb-3">
					<label for="moviedWriter">Writer</label> <input class="form-control"
						id="movieWriter" name="movieWriter" value="${login.userId}"
						readonly>
				</div>
				<div class="mb-3 form-group uploadDiv">
					<label for="movieImage">이미지 업로드</label> <input type="file"
						name="uploadFile" multiple><br>
					<div>
						<ul class="mailbox-attachments clearfix uploadedFileList"></ul>
					</div>
				</div>
			</div>
			<div align="right">
				<button class="btn btn-primary listBtn" type="button">목록</button>
				<button class="btn btn-secondary" type="reset">초기화</button>
				<button type="submit" class="btn btn-success btnSave" id="btnSave">저장</button>
			</div>
	</div>
	</form>
	<%@ include file="../include/footer.jsp"%>
	<!-- js플러그인 -->
	<%@ include file="../include/plugin_js.jsp"%>
	
	<script id="fileTemplate" type="text/x-handlebars-template">
		<li>
			<span class="mailbox-attachment-icon has-img">
				<i class="fas fa-image"></i>
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{originalFileUrl}}" class="mailbox-attachment-name">
					<i class="fa fa-paperclip"></i>{{originalFileName}}
				</a>
				<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
					<i class="fa fa-fw fa-remove"></i>
				</a>
			</div>
		</li>
	</script>
	
	<script>
	var fileTemplate = Handlebars.compile($("#fileTemplate").html());
	
	$('#rating a').click(function(){
		$(this).parent().children("a").removeClass("on");
		$(this).addClass("on").prevAll("a").addClass("on");
		console.log($(this).attr("value"));
		$('#movieRating').val($(this).attr("value"));
	});
	
	</script>
	<script type="text/javascript" src="${path}/resources/upload.js"></script>
</body>
</html>