<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script type="text/javascript" src="assets/js/lhs/lhsjoinform.js" defer="defer"></script>
  <title>회원가입</title>
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

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
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
          <div class="row justify-content-center" style="margin-top: 90px;">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center wid50">

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">회원가입</h5>
                  </div>

                  <form action="/userJoin" class="row g-3" method="post" enctype="multipart/form-data" onsubmit="return chk()">
                  
                    <div class="col-12">
                      <label for="id" class="form-label">아이디</label>
                      <div class="inlinediv">
                      	<input type="text" name="user_id" class="form-control" id="id" required>
                    	<button type="button" class="btn btn-secondary" id="idChkBtn">중복확인</button>
                      </div>
                      <div class="enableId">사용가능한 아이디입니다.</div>
                      <div class="disableId">사용불가능한 아이디입니다.</div>
                    </div>
                    
                    <div class="col-12">
                      <label for="pw1" class="form-label">비밀번호</label>
                      <input type="password" name="user_pw" class="form-control" id="pw1" required>
                    </div>
                    
                    <div class="col-12">
                      <label for="pw2" class="form-label">비밀번호확인</label>
                      <input type="password" name="user_pw" class="form-control" id="pw2" required>
                      <div class="enablePass">사용가능한 비밀번호입니다.</div>
                      <div class="disablePass">사용불가능한 비밀번호이거나 비밀번호가 서로 다릅니다.</div>
                    </div>
                    
                    <div class="col-12">
                      <label for="name" class="form-label">이름</label>
                      <input type="text" name="user_name" class="form-control" required>
                    </div>

                    <div class="col-12">
                      <label for="email" class="form-label">이메일</label>
                       <input type="email" name="user_email" class="form-control" value="${email}" readonly="readonly"> 
                    </div>
                    
                    <div class="col-12">
                      <label for="nicname" class="form-label">닉네임</label>
                      <input type="text" name="user_nic" class="form-control" maxlength="10" placeholder="닉네임은 최대 한글10글자입니다" required>
                    </div>
                    
                    <div class="col-12">
                      <label for="phone" class="form-label">휴대폰 번호</label>
                      <input type="text" name="user_phone" class="form-control" required>
                    </div>
                    
                    <div class="col-12">
                      <label for="picture" class="form-label">프로필 사진</label>
                      <div class="inlinediv">
	                      <input type="file" name="user_profile" class="form-control" id="file">
	                      <img id="joinPicture">
                      </div>
                    </div>

                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">회원가입</button>
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