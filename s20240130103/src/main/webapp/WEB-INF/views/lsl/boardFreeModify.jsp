<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>Blueberry</title>
	<meta content="" name="description">
	<meta content="" name="keywords">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery CDN -->

	<!-- Favicons -->
 <link href="assets/img/blueberry-favicon.png" rel="icon">
 <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
  	<link href="https://fonts.gstatic.com" rel="preconnect">
  	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">


<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">


<!-- Template Main CSS File -->
<link href="assets/css/lsl/lslboardFreeModify.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<!-- 헤더, 푸터, 사이드바 css -->

<!-- File Js -->
<script defer src="assets/js/lsl/boardFile.js"></script>

    <!-- Write Form Js  -->
  <script defer src="assets/js/lsl/writeForm.js"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>


	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<!-- ======= Main ======= -->
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>글 수정하기</h1>
			<nav style="--bs-breadcrumb-divider: '/';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">정보 게시판</li>
					<li class="breadcrumb-item">글 수정</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section">
			<div class="row justify-content-center">
				<div class="col-lg-13">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">글 수정</h5>

							<!-- General Form Elements -->
							<form id="boardFreeUpdate" method="post" action="/boardFreeAskUpdate"  enctype="multipart/form-data">
								<input type="hidden" name="cboard_no"value="${boardModifyContents.cboard_no}" /> 
								<input type="hidden" name="boardType" id="boardType" value="${boardType}" />
								<input type="hidden" name="deleteFiles" id="deleteFiles">
								<div class="mb-3">
									<input id="ModifyTitle" type="text" class="form-control" name="cboard_title" placeholder="제목을 입력하세요." value="${boardModifyContents.cboard_title}">
								</div>
									<!-- 파일 첨부 -->
									<div class="upload-files">
										<div>
											<label for="files" class="form-label">파일 첨부</label>
										</div>
										<div class="d-flex" style="margin: 3px 0;">
											<c:forEach items="${boardFiles}" var="boardFile">
												<div class="fileBox" id="importFile${boardFile.cboard_file_cnt }">
													<div class="fileName">${boardFile.cboard_file_user_name}</div>
													<button class="fileXbtn" data-index="${boardFile.cboard_file_cnt }" type="button">X</button>
												</div>
											</c:forEach>
										</div>
										<input class="form-control" name="files" type="file" id="formFile" multiple/>
										<div class="upload-title" id="fileList" ></div>
									</div>
								<div class="mb-3">
									<textarea class="form-control" style="height: 550px;"
										name="cboard_content" >${boardModifyContents.cboard_content}</textarea>

								</div>
								<div class="mb-3">
									<button type="button" class="btn bfmCancle" onclick="goBack()">취소</button>
									<button type="submit" class="btn bfmModify">저장</button>
								</div>
							</form>
							<!-- End General Form Elements -->
							</div>
						</div>
					</div>
				</div>
		</section>
	</main>

	<!-- ======= End Main ======= -->


	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->


	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>


	<!-- Vendor JS Files -->
	<script defer src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script defer src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script defer src="assets/vendor/chart.js/chart.umd.js"></script>
	<script defer src="assets/vendor/echarts/echarts.min.js"></script>
	<script defer src="assets/vendor/quill/quill.min.js"></script>
	<script defer
		src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script defer src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script defer src="assets/vendor/php-email-form/validate.js"></script>


	<!-- Template Main JS File -->
	 <script src="assets/js/main.js"></script>
</body>
</html>
