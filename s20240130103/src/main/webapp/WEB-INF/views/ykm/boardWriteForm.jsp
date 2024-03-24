<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Blueberry</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="assets/img/blueberry-favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    
    <!-- Google Fonts -->
  	<link href="https://fonts.gstatic.com" rel="preconnect">
  	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">

    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script defer="defer" type="text/javascript" src="assets/js/ykm/ykmBoardFile.js"></script>
    <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<link href="assets/css/ykm/boardWriteForm.css" rel="stylesheet">
<script>
window.onload = function() {

	function updatePreview() {
	    $("input[type='file']").on("change", function (e) {
	    	
	        $('.preview').empty(); 
	        var files = e.target.files;
	        var arr = Array.prototype.slice.call(files);
	        
	        $.each(arr, function (index, file) {
	            let fileName = file.name; 
	            var div = $('<div>').text(fileName);
	            $('.preview').prepend(div); 
	        });
	    });
	} 
	
	updatePreview();
	
	$('#blankchk').on("click", function(e) {
		const inputTitle = $('#inputTitle').val();
		if (inputTitle.trim() === "") {
			e.preventDefault();
			alert('제목에 공백을 사용할 수 없습니다.');
		} else {
			inputTitle.focus();
		}
	});
}
</script>

</head>
<body>
	
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>
	
	<!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>

	<!-- 게시판 페이지 헤더 -->
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>정보게시판</h1>
			<nav style="--bs-breadcrumb-divider: '/';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">정보게시판</li>
					<c:choose>
				    <c:when test="${comm_mid eq 10}">
				        <li class="breadcrumb-item"><a href="boardContest">공모전</a></li>
				        <li class="breadcrumb-item">글쓰기</li>
				    </c:when>
				    <c:when test="${comm_mid eq 20}">
						<li class="breadcrumb-item"><a href="boardStudy">스터디</a></li>
						<li class="breadcrumb-item">글쓰기</li>
				    </c:when>
				</c:choose>
				</ol>
			</nav>
		</div>
		<!-- 게시판 페이지 본문 -->
		<section class="section">
			<div class="row card card-body">
				<div class="community-header">
					<p>글쓰기</p>
				</div>
				<form action="writePost" method="post" enctype="multipart/form-data">
					<input type="hidden" name="comm_mid" value="${comm_mid}">
					<input type="hidden" name="comm_big" value="${comm_big}">
					<div class="community-body">
						<!-- 제목 -->
						<div class="title-input">
							<label for="boradTitle" class="form-label"></label> 
							<input type="text" class="form-control" id="inputTitle" 
									name="cboard_title"	placeholder="제목에 핵심 내용을 요약해보세요." value="" required="required"/>
						</div>

						<!-- 파일 첨부 -->
						<div class="upload-files">
							<label for="inputNumber" class="form-label">
								<span class="upload-file-title">파일 첨부</span>
							</label> 
							<input class="form-control" name="cboard_file_name" type="file" id="formFile" multiple/>
							<div class="upload-title" id="fileList">
							
					        </div>
						</div>
						
						<!-- 글 내용 작성 -->
						<div class="content-input">
							<label for="boardContent" class="form-label"></label>
							<textarea class="form-control" id="boardContent" rows="15" 
										placeholder="내용을 입력하세요." name="cboard_content" required="required"></textarea>
						</div>
						
						<div class="btn-container">
							<input type="submit" class="btn btn-primary" value="확인" id="blankchk">
							<button type="button" class="btn btn-secondary" onclick="window.history.back()">취소</button>
						</div>
					</div>
				</form>
			</div>
		</section>
	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
	<i class="bi bi-arrow-up-short"></i></a>
	
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

</body>
</html>