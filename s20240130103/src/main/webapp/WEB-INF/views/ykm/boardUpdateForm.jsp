<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="assets/css/style.css" rel="stylesheet" type="text/css">

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


<link href="assets/css/ykm/boardUpdateForm.css" rel="stylesheet">

</head>
<body>
<script>
window.onload = function() {
	

	function updateFileView() {
		$('input[type=\'file\']').on(
				"change",
				function(e) {
					$('.checkboxContainer').empty();
					console.log();
					const files = e.target.files;
					const arr = Array.prototype.slice.call(files);
					arr.reverse();

					$.each(arr, function(index, file) {
						let fileName = file.name;
						var checkboxDiv = $('<div>').addClass(
								'form-check');
						var checkboxInput = $('<input>').addClass(
								'form-check-input').attr('type',
								'checkbox').val(file.name);
						var checkboxLabel = $('<label>').addClass(
								'form-check-label').text(file.name);
						checkboxDiv.append(checkboxInput);
						checkboxDiv.append(checkboxLabel);
						$('#checkboxContainer').append(checkboxDiv);
					});
				});
	}
	updateFileView();

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

	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

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
							<li class="breadcrumb-item">글수정</li>
						</c:when>
						<c:when test="${comm_mid eq 20}">
							<li class="breadcrumb-item"><a href="boardStudy">스터디</a></li>
							<li class="breadcrumb-item">글수정</li>
						</c:when>
					</c:choose>
				</ol>
			</nav>
		</div>
		<!-- 게시판 페이지 본문 -->
		<section class="section">
			<div class="row card card-body">
				<div class="community-header">
					<p>글 수정</p>
				</div>
				<form action="updatePost" method="post" enctype="multipart/form-data">
					<input type="hidden" name="cboard_no" value="${getPost.cboard_no}">
					<input type="hidden" name="comm_big" value="${getPost.comm_big}">
					<input type="hidden" name="comm_mid" value="${getPost.comm_mid}">
					<input type="hidden" name="deleteFiles" id="deleteFiles">
					<div class="title-input">
						<label for="boradTitle" class="form-label"></label> 
						<input type="text" name="cboard_title" id="inputTitle" class="form-control" value="${getPost.cboard_title}" />
					</div>

					<!-- 파일 첨부 -->
					<div class="upload-files">
						<label for="inputNumber" class="form-label"> 
							<span class="upload-file-title">파일 첨부</span>
						</label> 
						<div class="d-flex" style="margin: 3px 0;">
							<c:forEach items="${getFileList}" var="boardFile">
								<div class="fileBox d-flex" id="importFile${boardFile.cboard_file_cnt }">
									<div class="fileName">${boardFile.cboard_file_user_name}</div>
									<button class="fileXbtn" data-index="${boardFile.cboard_file_cnt }" type="button">X</button>
								</div>
							</c:forEach>
						</div>
							<input class="form-control" name="cboard_file_name" type="file" id="formFile" multiple />
						<div class="upload-title checkboxContainer" id="fileList">
							
						</div>
					</div>

					<!-- 글 내용 작성 -->
					<div class="content-input">
						<label for="boardContent" class="form-label"></label>
						<textarea class="form-control" id="boardContent" name="cboard_content" rows="15">${getPost.cboard_content}</textarea>
					</div>

					<div class="btn-container">
						<input type="submit" class="btn btn-primary" value="확인" id="blankchk">
						<button type="button" class="btn btn-secondary" onclick="window.history.back()">취소</button>
					</div>
				</form>
			</div>
		</section>
	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>
</html>