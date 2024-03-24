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
<link href="assets/css/lhs/admin_userList.css" rel="stylesheet"
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

	<main id="main" class="main">
		<section>
			<div class="main-div ">
				<div class="main-top">
					<div class="pagetitle">
						<h1>회원 관리</h1>
					</div>
				</div>
				<div class="main-mid bg-white">
					<div class="card-body">
						<div class="card-top">
							<form action="admin_users" class="d-flex" method="get" accept-charset="UTF-8">
								<select class="form-select searchSelete"
									aria-label="Default select example" name="searchkind">
									<c:if test="${searchkind ne 'id' }">
										<option value="name" selected="selected">이름</option>
										<option value="id">아이디</option>
									</c:if>
									<c:if test="${searchkind eq 'id' }">
										<option value="name">이름</option>
										<option value="id" selected="selected">아이디</option>
									</c:if>
								</select> 
								<input type="text" class="form-control searchInput" name="searchValue" value=${searchValue }>
								<button class="btn btn-primary" type="submit">검색</button>
							</form>

						</div>
						<!-- Default Table -->
						<table class="table">
							<thead>
								<tr>
									<th scope="col" style="width: 5%;">번호</th>
									<th scope="col" style="width: 10%;">아이디</th>
									<th scope="col" style="width: 20%;">이메일</th>
									<th scope="col" style="width: 10%;">이름</th>
									<th scope="col" style="width: 15%;">휴대폰번호</th>
									<th scope="col" style="width: 20%;">가입일</th>
									<th scope="col" style="width: 10%;">권한</th>
									<th scope="col" style="width: 10%;">비고</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList }" var="user">
									<tr>
										<th>${user.user_no }</th>
										<td>
											<a href="admin_user_detail?user_no=${user.user_no }">${user.user_id }</a>
										</td>
										<td>${user.user_email }</td>
										<td>${user.user_name }</td>
										<td>${user.user_phone }</td>
										<td>
											<fmt:formatDate value="${user.user_date }" type="both"/>
										</td>
										<td>${user.comm_content }</td>
										<td>
											<c:if test="${user.user_delete_chk eq 1 }">삭제대기중</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- End Default Table Example -->
					</div>
					<div class="mid-bottom justify-content-center">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<c:if test="${paging.startPage>paging.pageBlock }">
									<li class="page-item"><a class="page-link"
										href="admin_users?currentPage=${paging.startPage-paging.pageBlock }&searchkind=${searchkind}&searchValue=${searchValue}"><</a>
									</li>
								</c:if>
								<c:forEach var="i" begin="${paging.startPage }"
									end="${paging.endPage }">
									<li class="page-item"><a class="page-link"
										href="admin_users?currentPage=${i }&searchkind=${searchkind}&searchValue=${searchValue}">${i }</a>
									</li>
								</c:forEach>
								<c:if test="${paging.endPage < paging.totalPage }">
									<li class="page-item"><a class="page-link"
										href="admin_users?currentPage=${paging.startPage+paging.pageBlock }&searchkind=${searchkind}&searchValue=${searchValue}">></a>
									</li>
								</c:if>
							</ul>
						</nav>
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