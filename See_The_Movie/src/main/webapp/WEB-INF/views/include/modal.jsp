<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%--댓글 수정을 위한 modal 영역--%>
	<div class="modal fade" id="modModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">댓글수정</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" data-rno>
					<input type="hidden" class="replyNo" />
					<%--<input type="text" id="replytext" class="form-control"/>--%>
					<textarea class="form-control" id="replyText" rows="3"
						style="resize: none"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary modalModBtn">수정</button>
				</div>
			</div>
		</div>
	</div>

	<%--댓글 삭제를 위한 modal 영역--%>
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">댓글 삭제</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<input type="hidden" class="replyNo" />
				</div>
				<div class="modal-body" data-rno>
					<p>댓글을 삭제하시겠습니까?</p>
					<input type="hidden" class="replyNo" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">아니요.</button>
					<button type="button" class="btn btn-primary modalDelBtn">네.
						삭제합니다.</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>