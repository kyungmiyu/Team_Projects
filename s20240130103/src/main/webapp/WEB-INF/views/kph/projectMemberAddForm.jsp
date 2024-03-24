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
   	<link href="assets/css/style.css" rel="stylesheet" />
    <link href="assets/css/kph/projectMemberAddForm.css" rel="stylesheet" /> <!-- 이건 복사해서 사용하지 마세요 헤더 푸터가 아닙니다.-->
     
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script defer src="/assets/js/kph/projectMemberAddForm.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

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
	            <li class="breadcrumb-item">프로젝트 홈</li>
	            <li class="breadcrumb-item active">팀원 추가</li>
	          </ol>
        	</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="card">
				<p>팀원 추가</p>
				<form action="projectMemberAdd" method="post">
					<input class="project_no" type="hidden" name="project_no" value="${project_no }">
					<div class="address-list">
						<c:forEach var="addressUser" items="${addressUserList }">
							<div class="address-list-card">
								<div class="address-list-card-detail">
									<div class="profile-img-user-name">
										<c:if test="${empty addressUser.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
												alt="Profile"
												class="rounded-circle address-list-profile-img">
										</c:if>
										<c:if test="${not empty addressUser.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/${addressUser.user_profile}"
												alt="Profile"
												class="rounded-circle address-list-profile-img">
										</c:if>
										<div>
											<p class="user-name">${addressUser.user_name }</p>
											<p class="user-id">#${addressUser.user_id }</p>
										</div>
									</div>
									<div class="form-check">
										<input name="user_no" class="form-check-input" type="checkbox"
											value="${addressUser.user_no }" />
									</div>
								</div>
							</div>
						</c:forEach>
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
    <!-- 
    <script src="assets/vendor/quill/quill.min.js"></script>
 	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
  	<script src="assets/vendor/php-email-form/validate.js"></script> -->

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

</body>

</html>