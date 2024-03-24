<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="assets/css/kph/taskAddForm.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script defer src="./assets/js/kph/taskAddForm.js"></script>
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
					<li class="breadcrumb-item active">과업 추가</li>
				</ol>
			</nav>
		</div><!-- End Page Title -->

        <section class="section dashboard">
			<div class="card">
				<p>과업 정보 입력</p>
				<form action="taskAdd" method="post">
					<input type="hidden" id="project_no" name="project_no" value="${project_no }" />
					<div>
						<p>과업 제목</p>
						<input name="task_title" type="text" class="form-control">
						<p class="task-alert task-title-alert">과업 제목을 입력하세요</p>
					</div>
					<div>
						<div class="task-member-head">
							<p>참여자</p>
							<input class="form-check-input" type="checkbox" id="all" />
						</div>
						<div class="task-member">
							<c:forEach var="member" items="${projectMemberList}">
								<div class="form-check">
									<input name="user_no" class="form-check-input" type="checkbox" id="${member.user_no}" value="${member.user_no}" />
									<label class="form-check-label" for="${member.user_no}">
										<c:if test="${empty member.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
												alt="Profile"
												class="rounded-circle">${member.user_name }
										</c:if>
										<c:if test="${not empty member.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/${member.user_profile}"
												alt="Profile"
												class="rounded-circle">${member.user_name }
										</c:if>
									</label>
								</div>
							</c:forEach>
						</div>
						<p class="task-alert task-member-alert">과업 참여자를 한 명 이상 선택하세요</p>
					</div>
					<div>
						<p>시작일</p>
						<input name="task_start" type="date" class="form-control" />
						<p class="task-alert task-start-alert">과업 시작일을 입력하세요</p>
					</div>
					<div>
						<p>종료일</p>
						<div class="task-end-day-box">
							<input name="task_end_day" type="date"	class="task-end-day form-control" /> 
							<input name="task_end_time" type="time" class="form-control">
						</div>
						<p class="task-alert task-end-alert">과업 종료일을 입력하세요</p>
						<p class="task-alert task-chk-alert">잘못된 날짜선택입니다</p>
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