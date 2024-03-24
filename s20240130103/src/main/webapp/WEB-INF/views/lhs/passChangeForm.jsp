<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <script type="text/javascript" src="assets/js/lhs/lhsidSearchResult.js" defer="defer"></script>
  <title>비밀번호 찾기</title>
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
<c:if test="${result eq 1 }">
	<script type="text/javascript">
		alert("비밀번호 변경 완료");
		location.href="/";
	</script>
</c:if>

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
                <div class="card-body">
                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">비밀번호 입력</h5>
                  </div>
                  <form class="row g-3 needs-validation" action="passwordChange" method="post">
                  <input type="hidden" name="user_no" value="${user_no }">
                    <div class="col-12">
                      <label for="yourUserpassword" class="form-label">새 비밀번호</label>
                      <div class="input-group has-validation">
                        <input type="password" name="user_pw" class="form-control" id="user_pw1" required>
                      </div>
                    </div>
                    
                    <div class="col-12">
                      <label for="yourUserpassword" class="form-label">비밀번호 확인</label>
                      <div class="input-group has-validation">
                        <input type="password" name="user_pw2" class="form-control" id="user_pw2" required>
                      </div>
                    </div>
                    
                    <div class="col-12 inlinedivwid100 justify-content-center align-items-center">
                      <button class="btn btn-outline-primary" type="submit">확인</button>
                      <button type="button" class="btn btn-outline-dark" onclick="history.back();">취소</button>
                    </div>
                  </form>
                </div>
              </div>
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