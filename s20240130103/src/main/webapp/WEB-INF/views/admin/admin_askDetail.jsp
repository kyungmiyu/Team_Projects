<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
<link href="assets/img/blueberry-logo.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

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
<link href="assets/css/style.css" rel="stylesheet" type="text/css">
<link href="assets/css/lhs/admin_board_detail.css" rel="stylesheet"
	type="text/css">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>

<body>
	<!-- ======= Header ======= -->
	<%@ include file="admin_header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="admin_aside.jsp"%>
	<!-- End Sidebar-->

	<main id="main" class="d-flex justify-content-center">
		<section>
			<div class="main-detail bg-white">
				<div class="card-body">
					<div class="card-top">
						<h5 class="fnt-nanum">문의 상세 정보</h5>
					</div>
					<div class="d-flex justify-content-center">
						<table class="table table-bordered table-style">
							<tbody>
								<tr>
									<td style="width: 10%">제목</td>
									<td>${ask.admin_title }</td>
								</tr>
								<tr>
									<td>작성자</td>
									<td>
										${ask.user_id } 
									</td>
								</tr>
								<tr style="height: 300px">
									<td>내용</td>
									<td>${ask.admin_content }</td>
								</tr>
								<tr>
									<td>작성일</td>
									<td>
										<fmt:formatDate value="${ask.admin_date }" type="both" />
									</td>
								</tr>
								<tr>
									<td>오류발생일</td>
									<td>
										<c:if test="${not empty ask.admin_start }">
											<fmt:formatDate value="${ask.admin_start }" type="both" />
										</c:if>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="card-bottom d-flex justify-content-end">
						<a href="admin_ask">
							<button class="btn btn-secondary">목록</button>
						</a> 
						<c:if test="${ask.admin_reply_chk eq 0 }">
							<a href="admin_ask_responseForm?user_no=${ask.user_no }&admin_no=${ask.admin_no}">
								<button class="btn btn-primary">답변작성</button>
							</a>
						</c:if>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<%@ include file="admin_footer.jsp"%>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
	<!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>

</html>