<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>문의사항</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-logo.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
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
<link href="assets/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<style>
@import
	url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css')
	;


.section {
	margin-right:100px;
}

.col-lg-6 {
    width: 100%;
   
}

.floatright{
	float: right;
}

.btn-primary {
    --bs-btn-color: #0d6efd;
    --bs-btn-bg: transparent;
    --bs-btn-border: 1px solid #0D6DFD;
    --bs-btn-hover-color: #ffffff;
    --bs-btn-hover-bg: #B52BFC;
    --bs-btn-hover-border: 1px solid #B52BFC;
    --bs-btn-border-radius: 7px;
}
</style>
<script type="text/javascript">
function chk(){
	title_value = document.getElementById("admin_title").value;
	if(title_value.trim() !== "" || title_value === null){
		return true;
	}else{
		alert("제목을 입력해주세요");
		return false;
	}
}
</script>
</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<main id="main" class="main">
	 <div class="pagetitle">
      <h1>글작성</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="main">Home</a></li>
		  <li class="breadcrumb-item"><a href="askList">문의하기</a></li>
	      <li class="breadcrumb-item"><a href="askForm">글작성</a></li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
    
        <div class="col-lg-6">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">문의사항 작성하기</h5>
             <%--  <c:if test="${msg!=null}">${msg}</c:if> --%>
              <!-- General Form Elements -->
						<form action="askWrite" method="post" name="frm" onsubmit="return chk()">

							<div class="row mb-3">
								<label for="inputText" class="col-sm-2 col-form-label">제목</label>
								<div class="col-sm-10">
									<!-- <input type="text" class="form-control"> -->
									<input type="text" class="form-control" name="admin_title"
										value="${BOARD_ADMIN.admin_title}" required="required" id="admin_title">
								</div>
							</div>
							<div class="row mb-3">
                  				<label for="inputDate" class="col-sm-2 col-form-label">문의내용 발생일</label>
                  				<div class="col-sm-10">
                    				<input type="date" name="admin_start" value="${BOARD_ADMIN.admin_start}" class="form-control">
                  				</div>
                			</div> 
							<div class="row mb-3">
								<label for="inputPassword" class="col-sm-2 col-form-label">내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" name="admin_content"
										value="${BOARD_ADMIN.admin_content}" style="height: 453px"></textarea>
								</div>
							</div>
							<div class="floatright">
								<button type="submit" class="btn btn-primary">글작성</button>
								<!-- 	<a href="ask">글작성 kkk</a></button>     -->
							</div>

						</form>
						<!-- End General Form Elements -->

					</div>
          </div>

        </div>
    </section>
	</main>
    
	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

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
