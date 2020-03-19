
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
	// var ctxPath = window.location.pathname.substring(0,
	// window.location.pathname.indexOf("/",2));
	// return ctxPath;
}

    $(document).ready(function () {
        /*======================================== 댓글 CRUD 관련 ========================================*/
        // ---------------------------------------- 댓글 목록, 페이징 ----------------------------------------
        
        // 댓글 내용 줄바꿈, 공백 처리를 위한 문자열 처리
        Handlebars.registerHelper("escape", function(replytext) {
            var text = Handlebars.Utils.escapeExpression(replytext);
            text = text.replace(/(\r\n|\n|\r)/gm, "<br/>");
            text = text.replace(/( )/gm, "&nbsp;");
            return new Handlebars.SafeString(text);
        });
        // 댓글 등록일자 출력을 위한 날짜/시간 문자열 처리
        Handlebars.registerHelper("prettifyDate", function (timeVale) {
            var dateObj = new Date(timeVale);
            var year = dateObj.getFullYear();
            var month = dateObj.getMonth() + 1;
            var date = dateObj.getDate();
            var hours = dateObj.getHours();
            var minutes = dateObj.getMinutes();
            // 2자리 숫자 맞추기
            month < 10 ? month = '0' + month : month;
            date < 10 ? date = '0' + date : date;
            hours < 10 ? hours = '0' + hours : hours;
            minutes < 10 ? minutes = '0' + minutes : minutes;
            return year + "-" + month + "-" + date + " " + hours + ":" + minutes;
        });
        
        getReplies(getContextPath()+"/reply/" + movieNo + "/" + replyPage);
        
        // 댓글 갯수, 목록, 하단페이징 출력 호출 함수
        function getReplies(repliesUri) {
            // 댓글 목록 가져오기
            $.getJSON(repliesUri, function (data) {
                // 1. 댓글 갯수 출력 함수 호출
                printReplyCount(data.pageMaker.totalCount);
                // 2. 댓글 목록 출력 함수 호출
                printReplies(data.replies, $(".repliesDiv"), $("#replyTemplate"));
                // 3. 댓글 하단 페이징 출력 함수 호출
                printReplyPaging(data.pageMaker, $(".pagination"));
            });
        };
        
        // 1. 댓글 갯수 출력, 댓글 보기 버튼 활성/비활성 함수
        function printReplyCount(totalCount) {
        	
        	var replyCount = $("#replyCount");
        	var replybox = $(".replybox");
        	
        	if (totalCount == 0) {
                replyCount.html(" 댓글이 없습니다. 의견을 남겨주세요.");
                return;
            }
        	
        	replyCount.html(" 댓글 ("+totalCount+")");
        }
        
        // 2. 댓글 목록 출력 함수
        function printReplies(replyArr, targetArea, templateObject) { //data.replies , $(".repliesDiv"), $("#replyTemplate")
            var replyTemplate = Handlebars.compile(templateObject.html());
            var html = replyTemplate(replyArr);
            $(".replyDiv").remove();
            targetArea.html(html);
        };
        
        
        // 3. 하단 페이징 출력 함수
         function printReplyPaging(pageMaker, targetArea) {
            var str = "";
            if (pageMaker.prev) {
                str += "<li class=\"page-item\"><a class=\"page-link\" href='" + (pageMaker.startPage - 1) + "'>이전</a></li>";
            }
            for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
                var strClass = pageMaker.criteria.page == i ? "active" : "";
                str += "<li class=\"page-item\" " + strClass + "><a class=\"page-link\" href='" + i + "'>" + i + "</a></li>";
            }
            if (pageMaker.next) {
                str += "<li class=\"page-item\"><a href='" + (pageMaker.endPage + 1) + "'></a></li>"
            }
            targetArea.html(str);
        }; 
        
        // 댓글 페이지 번호 클릭 이벤트
        $(".pagination").on("click", "li a", function (event) {
            event.preventDefault();
            replyPage = $(this).attr("href");
            getReplies(getContextPath()+"/reply/"+movieNo+"/"+replyPage);
        });
        
        // ---------------------------------------- 댓글 입력 ----------------------------------------
        // 댓글 저장 버튼 클릭 이벤트
        $(".replyAddBtn").on("click", function () {
            // 입력 form 선택자
            var replyTextObj = $("#comment");
            var replyWriter = movieWriter;
            var replyText = replyTextObj.val();
            console.log("뜨나?")
			var comment = $("#comment").val();
			
			if(comment == ""){
				alert("댓글 내용을 입력하세요");
				document.commentForm.comment.focus();
				return false;
			}
            
            // 댓글 입력처리 수행
            $.ajax({
                type: "post",
                url: getContextPath()+"/reply/",
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "POST"
                },
                dataType: "text",
                data: JSON.stringify({
                	movieNo:movieNo,
                    replyText:replyText,
                    replyWriter:replyWriter
                }),
                success: function (result) {
                    console.log("result : " + result);
                    if (result == "regSuccess") {
                        alert("댓글이 등록되었습니다.");
                        replyPage = 1;  // 페이지 1로 초기화
                        getReplies(getContextPath()+"/reply/" + movieNo + "/" + replyPage); // 댓글 목록 호출
                        replyTextObj.val("");   // 댓글 입력창 공백처리
                    }
                }
            });
        });
        // ---------------------------------------- 댓글 수정 ----------------------------------------
        // 댓글 수정을 위해 modal창에 선택한 댓글의 값들을 세팅
        $(".repliesDiv").on("click", ".replyDiv", function (event) {
            var reply = $(this);
            console.log(reply);
            $(".replyNo").val(reply.attr("data-replyNo"));
            $("#replytext").val(reply.find(".oldReplytext").text());
        });
        // modal 창의 댓글 수정버튼 클릭 이벤트
        $(".modalModBtn").on("click", function () {
            var replyNo = $(".replyNo").val();
            var replyText = $("#replyText").val();
            $.ajax({
                type: "put",
                url: getContextPath()+"/reply/" + replyNo,
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "PUT"
                },
                dataType: "text",
                data: JSON.stringify({
                    replyText:replyText
                }),
                success: function (result) {
                    console.log("result : " + result);
                    if (result == "modSuccess") {
                        alert("댓글이 수정되었습니다.");
                        getReplies(getContextPath()+"/reply/" + movieNo + "/" + replyPage); // 댓글 목록 호출
                        $("#modModal").modal('hide'); // modal 창 닫기
                    }
                }
            })
        });
        // ---------------------------------------- 댓글 삭제 ----------------------------------------
        $(".modalDelBtn").on("click", function () {
            var replyNo = $(".replyNo").val();
            $.ajax({
                type: "delete",
                url: getContextPath()+"/reply/" + replyNo,
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "DELETE"
                },
                dataType: "text",
                success: function (result) {
                    console.log("result : " + result);
                    if (result == "delSuccess") {
                        alert("댓글이 삭제되었습니다.");
                        getReplies(getContextPath()+"/reply/" + movieNo + "/" + replyPage); // 댓글 목록 호출
                        $("#delModal").modal("hide"); // modal 창 닫기
                    }
                }
            });
        });
    });
