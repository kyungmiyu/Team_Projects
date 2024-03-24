<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Blueberry</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="assets/img/blueberry-logo.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/kph/projectAddForm.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script defer src="./assets/js/kph/projectAddForm.js"></script>
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
    <%@ include file="../header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>
    
    <!-- End Sidebar-->

    <main id="main" class="main">

        <div class="pagetitle">
            <h1>워크 스페이스</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="main">워크 스페이스</a></li>
                    <li class="breadcrumb-item active">프로젝트 추가</li>
                </ol>
            </nav>
        </div><!-- End Page Title -->

        <section class="section dashboard">
			<div class="card">
				<p>프로젝트 정보 입력</p>
				<form action="projectAdd" method="post">
					<div>
						<p>프로젝트 제목</p>
						<input name="project_title" type="text" class="form-control">
						<p class="project-alert project-title-alert">프로젝트 제목을 입력하세요</p>
					</div>
					<div>
						<p>프로젝트 내용</p>
						<input name="project_content" type="text" class="form-control">
						<p class="project-alert project-content-alert">프로젝트 내용을 입력하세요</p>
					</div>
					<div>
						<p>시작일</p>
						<input name="project_start" type="date" class="form-control" />
						<p class="project-alert project-start-alert">프로젝트 시작일을 입력하세요</p>
					</div>
					<div>
						<p>종료일</p>
						<input name="project_end" type="date" class="form-control" />
						<p class="project-alert project-end-alert">프로젝트 종료일을 입력하세요</p>
						<p class="project-alert project-chk-alert">잘못된 날짜선택입니다</p>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">저장</button>
						<button type="button" class="btn btn-secondary">취소</button>
					</div>
				</form>
			</div>
        </section>

    </main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
    <!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
    <!-- <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script> -->

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

</body>

</html>