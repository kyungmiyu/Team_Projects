<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="kr">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>아이디 찾기</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
  <link href="assets/img/blueberry-favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">


  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
  <link href="assets/css/lhs/lhs.css" rel="stylesheet">

</head>

<body>
<header id="header" class="header fixed-top d-flex align-items-center">
        <div class="d-flex align-items-center justify-content-between">
            <a href="/" class="logo d-flex align-items-center">
                <img src="/assets/img/blueberry-logo.png" alt="">
                <span class="d-none d-lg-block">Blueberry</span>
            </a>
        </div><!-- End Logo -->
</header>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
	    <div class="container">
	        <div class="row justify-content-center">
	            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
	                <div class="login-banner">
	                    <a href="/">
	                        <img src="assets/img/blueberry-logo.png" alt="">
	                    </a>
	                </div>
	                <div class="card mb-3">
	                    <div class="card-body w600_h400">
	                        <div class="pt-4 pb-2">
	                            <h5 class="card-title text-center pb-0 fs-4">아이디 찾기 결과</h5>
	                        </div>
	                        <div>
	                        	<c:if test="${not empty user }">
		                            <table class="table">
		                                <thead>
		                                    <tr>
		                                        <th scope="col">아이디</th>
		                                        <th scope="col">가입일</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                	<c:forEach items="${user }" var="us">
			                                	<tr>
													<td>${us.user_id }</td>
													<td>
														<fmt:parseDate value="${us.user_date }" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate" type="both" />
														<fmt:formatDate value="${parseDate }" dateStyle="full"/>
													</td>
			                                	</tr>
			                                </c:forEach>
		                                </tbody>
		                            </table>
	                            </c:if>
	                            <c:if test="${empty user }">
	                            	<p>해당 이메일로 가입하신 아이디가 없습니다.</p>
	                            </c:if>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="container">
	        <div class="row justify-content-center">
	            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
	            	<a href="loginForm">
	            		<button class="btn btn-outline-primary">로그인</button>
	            	</a>
	            </div>
	        </div>
	    </div>
	</section>


    </div>
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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