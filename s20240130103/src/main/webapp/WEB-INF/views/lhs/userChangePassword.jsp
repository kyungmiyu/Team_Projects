<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 변경</title>
<script src="assets/js/lhs/lhsuserChangePassword.js" defer="defer"></script>
<meta content="" name="description">
<meta content="" name="keywords">
<!-- Favicons -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<link href="assets/img/blueberry-favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
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
<link href="assets/css/lhs/userChangePassword.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<!-- 헤더, 푸터, 사이드바 css -->

</head>
<body>
	<!-- Header -->
	<%@ include file="../header.jsp"%>
	<!-- Sidebar -->
	<%@ include file="../asidebar.jsp"%>
	<!-- Main Content -->

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>비밀번호변경</h1>
		</div>
		<!-- End Page Title -->

		<section class="section profile">
		    <div class="bigwhiteback d-flex align-items-center justify-content-center">
		        <div class="container">
		            <div class="row justify-content-center">
		                <div class="col-md-8">
		                    <div class="card midcard">
		                        <div class="card-body">
		                            <form action="changePassword" onsubmit="return chk()" method="post">
		                                <div class="row mb-3">
		                                    <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">현재 비밀번호</label>
		                                    <div class="col-md-8 col-lg-9">
		                                        <input name="password" type="password" class="form-control" id="currentPassword">
		                                    </div>
		                                </div>
		                                <c:if test="${result==0 }">
		                                	<div style="color: red;">현재 비밀번호가 틀렸습니다</div>
		                                </c:if>
		
		                                <div class="row mb-3">
		                                    <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호</label>
		                                    <div class="col-md-8 col-lg-9">
		                                        <input name="newpassword" type="password" class="form-control" id="newPassword">
		                                    </div>
		                                </div>
		
		                                <div class="row mb-3">
		                                    <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호 확인</label>
		                                    <div class="col-md-8 col-lg-9">
		                                        <input name="renewpassword" type="password" class="form-control" id="renewPassword">
		                                    </div>
		                                </div>
		
		                                <div class="text-center">
		                                    <button type="submit" class="btn btn-primary">비밀번호 변경</button>
		                                    <a href="myPage">
		                                    	<button type="button" class="btn btn-outline-secondary">취소</button>
		                                    </a>
		                                </div>
		                            </form>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</section>


	</main>
	<!-- End #main -->
	<!-- Footer -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>
	<!-- Vendor JS Files -->
	<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/chart.js/chart.umd.js"></script>
	<script src="assets/vendor/echarts/echarts.min.js"></script>
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>
	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>
</html>
