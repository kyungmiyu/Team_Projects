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
    <link href="assets/css/lhs/scheduleAddForm.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
function chk(){
	let start = new Date(document.getElementById("sch_start").value);
	let end = new Date(document.getElementById("sch_end").value);
	let title = document.getElementById("sch_title").value;
	if(title.trim() !== "" && title !== null){
		if(end.getTime()>=start.getTime()){
			return true;
		}else{
			alert("날짜 선택을 정확하게 해주세요");
			return false;
		}
	}else{
		alert("제목을 입력해주세요");
		return false;
	}
}


function goback(project_no){
	location.href="proejctSchedule?project_no="+project_no;
}
</script>
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
            <li class="breadcrumb-item active">일정 추가</li>
          </ol>
        </nav>
      </div>
      <!-- End Page Title -->

      <section class="section dashboard">
        <div class="card">
          <p>일정 정보 입력</p>
          <form action="scheduleAdd" method="post" onsubmit="return chk()">
            <input type="hidden" id="project_no" name="project_no" value="${project_no }" />
            <div>
              <p>일정 제목</p>
              <input name="sch_title" id="sch_title" type="text" class="form-control" required="required"/>
            </div>

            <div>
              <p>시작일</p>
              <input name="sch_start" id="sch_start" type="date" class="form-control" required="required"/>
            </div>
            <div>
              <p>종료일</p>
              <input name="sch_end" id="sch_end" type="date" class="form-control" required="required"/>
            </div>
            <div class="text-center">
              <button type="submit" class="btn btn-primary">저장</button>
              <button type="button" class="btn btn-secondary" onclick="goback(${project_no})">취소</button>
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
