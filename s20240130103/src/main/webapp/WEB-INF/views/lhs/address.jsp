<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  	<script type="text/javascript" src="assets/js/lhs/lhsaddress.js"></script>
  	
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
    <link href="assets/css/lhs/address.css" rel="stylesheet"  type="text/css">
     
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
</head>

<body>

    <!-- ======= Header ======= -->
    <%@ include file="../header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>
    
    <!-- End Sidebar-->

	<main id="main" class="main">
	<section>
		<div class="pagetitle">
            <h1>주소록</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">주소록</li>
                </ol>
            </nav>
        </div>
		<div class="maindiv card-body">
			<div class="d-flex align-items-center address-top">
	          <div class="d-flex align-items-center ">
	            <ul class="nav nav-tabs nav-tabs-bordered">
	              <li class="nav-item flex-fill" role="presentation">
	                  <button class="nav-link w-100 active" id = "addressList" data-bs-toggle="tab" type="button" aria-selected="true">주소록 목록</button>
	              </li>
	              <li class="nav-item flex-fill" role="presentation">
	                  <button class="nav-link w-100" id="addressrequest" type="button" data-bs-toggle="tab"  aria-selected="false" >보낸 요청</button>
	              </li>
	              <li class="nav-item flex-fill" role="presentation">
	                  <button class="nav-link w-100" id="addressresponse" type="button" data-bs-toggle="tab"  aria-selected="false" >받은 요청</button>
	              </li>
	            </ul>
	          </div>
	          <div>
	          	<a href="addressaddForm">
		            <button class="btn btn-info">
		              <i class="bi bi-plus"></i>
		            </button>
	            </a>
	          </div>
			</div>
	        <div class="address-main">
	        
			<%-- <c:forEach items="${addressUserList }" var="user">
	          <div class="address-main-inner">
	            <div class="address-list-card-detail">
	              <div class="profile-img-user-name">
	                <img src="upload/userImg/${user.user_profile }" alt="Profile" class="rounded-circle address-list-profile-img">
	                <div>
	                  <p class="user-name">${user.user_name }</p>
	                  <p class="user-id">#${user.user_id }</p>
	                </div>
	              </div>
	              <div class="score-message">
	                <div class="user-score rounded-circle">${user.user_score }</div> 
	                <form action="" method="get"> 
	                  <input type="hidden" name="user_no" value="${addressUser.user_no }">
	                  <button type="submit" class="rounded-circle message">
	                    <i class="bi bi-envelope-fill"></i>
	                  </button>
	                </form>
	              </div>
	            </div>
	          </div>
	         </c:forEach> --%>
	          
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
    <script src="assets/vendor/quill/quill.min.js"></script>
 	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
  	<script src="assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

</body>

</html>