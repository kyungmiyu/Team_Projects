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
<style type="text/css">
.main-div {
  width: 700px;
  height: 800px;
  border-radius: 10px;
  padding: 15px;
}
.bg-white {
  background-color: white;
}
.btns {
  width: 70px;
  margin-left: 15px;
}
.enableId{
	color: blue;
	display: none;
}
.disableId{
	color: red;
	display: none;
}
</style>
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

	<main id="main"
		class="main d-flex justify-content-center align-items-center">
		<section>
			<div class="main-div bg-white">
				<div class="card" style="top: 200px">
					<div class="card-body">
						<h5 class="card-title">관리자 계정 등록</h5>

						<!-- General Form Elements -->
						<form action="adminRegister" method="post" accept-charset="utf-8">
							<div class="row mb-3 d-flex">
								<label for="inputText" class="col-sm-2 col-form-label">아이디</label>
								<div class="col-sm-10 d-flex">
									<input id="user_id" type="text" name="user_id" class="form-control" style="width: 300px" />
									<button id="idChkBtn" type="button" class="btn btn-primary ms-2">중복확인</button>
								</div>
								<div class="enableId">사용가능한 아이디입니다.</div>
                      			<div class="disableId">사용불가능한 아이디입니다.</div>
							</div>

							<div class="row mb-3">
								<label for="inputPassword" class="col-sm-2 col-form-label">비밀번호</label>
								<div class="col-sm-10">
									<input type="password" name="user_pw" class="form-control" />
								</div>
							</div>

							<div class="row mb-3">
								<label for="inputEmail" class="col-sm-2 col-form-label">이메일</label>
								<div class="col-sm-10">
									<input name="user_email" type="email" class="form-control" />
								</div>
							</div>

							<div class="row mb-3">
								<label for="inputNumber" class="col-sm-2 col-form-label">이름</label>
								<div class="col-sm-10">
									<input name="user_name" type="text" class="form-control" />
								</div>
							</div>

							<div class="row mb-3">
								<label for="inputNumber" class="col-sm-2 col-form-label">핸드폰번호</label>
								<div class="col-sm-10">
									<input name="user_phone" type="text" class="form-control" />
								</div>
							</div>
							<input type="hidden" name="user_rank_big" value="500" /> <input
								type="hidden" name="user_rank_mid" value="20" /> <input
								type="hidden" name="user_nic" value="관리자" />
							<div class="row mb-3 d-flex justify-content-center">
								<button type="submit" class="btn btn-outline-info btns">
									확인</button>
								<button id="canclebtn" type="button" class="btn btn-outline-secondary btns"
									style="margin-right: 20px">취소</button>
							</div>
						</form>
						<!-- End General Form Elements -->
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->
<script type="text/javascript" >
document.getElementById("idChkBtn").addEventListener('click',function(){
	document.querySelector('.disableId').style.display='none';
	document.querySelector('.enableId').style.display='none';
	let idval = document.getElementById('user_id').value;
	if(idval && idval !==""){
		$.ajax({
			type:'post',
			url:'/joinIdChk',
			data:{'id':idval},
			dataType:'json',
			success:function(result){
				if(result=='0'){
					document.querySelector('.enableId').style.display='block';
					document.getElementById('user_id').readOnly=true;
				}else{
					document.querySelector('.disableId').style.display='block';
				}
			}
		})
	}else{
		alert("id 입력하세요");
	}
});
document.getElementById("canclebtn").addEventListener('click',function(){
	location.href="adminMain";
})
</script>
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