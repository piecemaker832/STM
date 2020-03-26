function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
	// var ctxPath = window.location.pathname.substring(0,
	// window.location.pathname.indexOf("/",2));
	// return ctxPath;
}

$(document).ready(function() {
	
		var formObj = $("form[role='form']");
		console.log(formObj);
		
		$(".listBtn").on("click",function() {
			formObj.attr("action","/movie/listall");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		var maxSize = 10485760
		
		function checkExtension(fileName, fileSize){
			
			if(fileSize > maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			
			if (fileName != "") {
			    var ext = fileName.slice(fileName.lastIndexOf(".") + 1).toLowerCase();
			 
			    if (!(ext == "gif" || ext == "jpg" || ext == "png")) {
			        alert("이미지파일 (.jpg, .png, .gif ) 만 업로드 가능합니다.");
			        return false;
			    }
			}

			return true;
		}
		
		$("input[type='file']").change(function(e){
				e.preventDefault();
			var formData = new FormData();
			var inputFile = $("input[name=uploadFile]");
			var files = inputFile[0].files;
			
			for(var i = 0; i < files.length; i++){
				if(!checkExtension(files[i].name,files[i].size) ){
					return false;
				}
				formData.append("file",files[i]);
			}
				uploadFile(formData)
				console.log(formData);
				});
			
				function uploadFile(formData){
				$.ajax({
					url : getContextPath()+"/movie/file/upload",
					data: formData,
					dataType : "text",
					processData: false,
					contentType: false,
					type: "POST",
					success : function(data){
						console.log(data);
						printFiles(data); // 첨부파일 출력 메서드 호출
						$(".noAttach").remove();
					}
				});
				}
				
				//첨부파일 출력
				function printFiles(data) {
					//파일 정보 처리
					var fileInfo = getFileInfo(data);
					// Handlebars 파일 템플릿에 파일 정보들을 바인딩하고 HTML 생성
					var html = fileTemplate(fileInfo);
					// Handlebars 파일 템플릿 컴파일을 통해 생성된 HTML을 DOM에 주입
					$(".uploadedFileList").append(html);
					// 이미지 파일인 경우 파일 템플릿에 lightbox 속성 추가
					if(fileInfo.fullName.substr(12,2) === "s_"){
						// 마지막에 추가된 첨부파일 템플릿 선택자
						var that = $(".uploadedFileList li").last();
						// lightbox 속성 추가
						that.find(".mailbox-attachment-name").attr("data-lightbox","uploadImages");
						// 파일 아이콘에서 이미지 아이콘으로 변경
						that.find(".fa-paperclip").attr("class","fa fa-camera");
					}
				}
				
				//게시글 입력/수정 submit 처리시에 첨부파일 정보도 함께 처리
 				function  filesSubmit(that) {
 					var str = "";
 					$(".uploadedFileList .delBtn").each(function(index){
 						if($(".mailbox-attachment-name").attr("href")==null) {
 							alert("이미지를 업로드 해주세요");
 							return false;
 						}
 						str += "<input type='hidden' name='files[" + index + "]' value ='" + $(this).attr("href") + "'>"
 					});
 					that.append(str);
 					that.get(0).submit();
 				}
 				
 				// 파일 삭제(입력페이지) : 첨부파일만 삭제처리
 				function deleteFileWrtPage(that) {
 					var url = getContextPath()+"/movie/file/delete";
 					deleteFile(url, that);
 				}
 				
 				// 파일 삭제 AJAX 통신
 				function deleteFile(url,that) {
 					$.ajax({
 						url : url,
 						type : "post",
 						data : {fileName: that.attr("href")},
 						dataType : "text",
 						success : function (result) {
 							if(result === "DELETED") {
 								alert("삭제되었습니다.");
 								that.parents("li").remove();
 							}
 						}
 					});
 				}
 				
 				//파일 정보 처리
 				function getFileInfo(fullName){
 					var originalFileName; //화면에 출력할 파일명
 					var imgSrc; //썸네일 or 파일아이콘 이미지 파일 출력 요청 URL
 					var originalFileUrl; //원본파일 요청 URL
 					var uuidFileName; // 날짜경로를 제외한 나머지 파일명(UUID_파일명.확장자)
 					
 					//이미지 파일이면
 					if(checkImageType(fullName)) {
 						imgSrc = getContextPath()+"/movie/file/display?fileName="+fullName; // 썸네일 이미지 링크
 						uuidFileName = fullName.substr(14);
 						var originalImg = fullName.substr(0,12) + fullName.substr(14);
 						// 원본 이미지 요청 링크
 						originalFileUrl = getContextPath()+"/movie/file/display?fileName=" + originalImg;
 					} else {
 						imgSrc = getContextPath()+"/resources/files/noImage.png" //파일 아이콘 이미지 링크
 						uuidFileName = fullName.substr(12);
 						//파일 다운로드 요청 링크
 						originalFileUrl = getContextPath()+"/movie/file/display?fileName=" + fullName;
 					}
 					
 					originalFileName = uuidFileName.substr(uuidFileName.indexOf("_") + 1);
 					
 					return {originalFileName: originalFileName, imgSrc: imgSrc, originalFileUrl: originalFileUrl, fullName:fullName};
 				}
 				
 				function checkImageType(fullName){
 					var pattern = /jpg$|gif$|png$|jpeg$/i;
 					return fullName.match(pattern);
 				}
				
 				$(document).on("click",".delBtn",function(event){
 					event.preventDefault();
 					var that = $(this);
 					deleteFileWrtPage(that);
 				});
 				
 				$("#writeForm").submit(function(event){
 					event.preventDefault();
 					var that = $(this);
 					filesSubmit(that);
 				})
		
	});