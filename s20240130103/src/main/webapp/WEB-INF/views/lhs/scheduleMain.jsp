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
    <link href="assets/css/lhs/scheduleMain.css"  rel="stylesheet"  type="text/css"> 
     
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/lhs/lhsScheduleMain.js"></script>
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
            <li class="breadcrumb-item"><a href="index.html">프로젝트</a></li>
            <li class="breadcrumb-item active">프로젝트 일정</li>
          </ol>
        </nav>
      </div>
      <!-- End Page Title -->

      <section class="section dashboard">
        <input
          class="projectLeader_no"
          type="hidden"
          name="projectLeader_no"
          value="41"
        />
        <div class="top">
          <div class="top-btn">
            <form action="detailProject" method="get">
				<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
				<button type="submit" class="project-board-btn btn btn-secondary">프로젝트 홈</button>
			</form>
			<form action="boardProject" method="get">
				<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
				<button type="submit" class="project-board-btn btn btn-secondary">공유 게시판</button>			
			</form>
            <form action="proejctSchedule" method="get">
              <input
                class="project_no"
                type="hidden"
                name="project_no"
                value="${project_no }"
              />
              <button type="submit" class="btn btn-primary">프로젝트 일정</button>
            </form>
          </div>
        </div>
        <div class="project-home">
          <div class="card schedule-card">
            <div class="schedule-card-head d-flex justify-content-between">
              <h5 class="card-title">일정 목록</h5>
              <form action="scheduleAddForm">
                <input type="hidden" name="project_no" value="${project_no }" />
                <input type="submit" class="btn btn-primary btn-sm" value="+" />
              </form>
            </div>
	         <div class="schedule-list">
	            <c:forEach items="${scheduleList}" var="schedule">
	              <div class="schedule-content">
	                <div
	                  class="schedule-content-title d-flex justify-content-between"
	                >
	                  <p>${schedule.sch_title }</p>
	                  <div>
		                  <a href="scheduleUpdateForm?project_no=${schedule.project_no }&sch_no=${schedule.sch_no}">
		                    <button>
		                      <i class="bi bi-pencil-fill"></i>
		                    </button>
	                      </a>
	                    <button onclick="removeChk(${schedule.project_no},${schedule.sch_no})">
	                    	<i class="bi bi-trash"></i>
	                    </button>
	                  </div>
	                </div>
	                <div class="schedule-content-body">
	                
	                
	                  <div class="schedule-contents">
	                    <i class="bi bi-calendar3"></i>
	                    <fmt:formatDate value="${schedule.sch_start }" type="date"/> ~ 
	                    <fmt:formatDate value="${schedule.sch_end }" type="date"/>
	                  </div>
	                  <div class="schedule-contents">
	                    <i class="bi bi-person-fill"></i>
	                    ${schedule.user_name }
	                  </div>
	                </div>
	              </div>
	            </c:forEach>
	         </div>
          </div>
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