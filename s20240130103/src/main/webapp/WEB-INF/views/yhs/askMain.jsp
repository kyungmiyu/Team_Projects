<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<link href="assets/css/lhs/askMain.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

    <main id="main" class="main">
      <div class="pagetitle d-flex justify-content-between">
        <h1>문의사항</h1>
        <a href="askList">
	        <button type="button" class="btn btn-outline-secondary">
	          내 문의내역
	        </button>
        </a>
      </div>
      <section class="section contact">
        <div class="row gy-4">
          <div class="col-lg-6 d-flex justify-content-center">
            <div class="card wid-80">
              <div class="card-body">
                <h5 class="card-title">자주하는 질문</h5>

                <!-- Default Accordion -->
                <div class="accordion" id="accordionExample">
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                      <button
                        class="accordion-button"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#questionOne"
                        aria-expanded="true"
                        aria-controls="questionOne"
                      >
                        id를 분실했어요
                      </button>
                    </h2>
                    <div
                      id="questionOne"
                      class="accordion-collapse collapse show"
                      aria-labelledby="headingOne"
                      data-bs-parent="#accordionExample"
                      style=""
                    >
                      <div class="accordion-body">
                        ID를 분실하였을 경우, 'ID 찾기'를 통해 만들었던 ID를
                        찾을 수 있습니다.<br />
                        ① 홈페이지 로그인 창 아래있는 ID 찾기를 클릭합니다.<br />
                        ② ‘아이디찾기’를 선택하여 가입하신 이메일을 통해 인증을
                        완료하면 고객님의 ID를 찾을 수 있습니다.<br />
                        ③ 언제든 불편사항이 발생하면 1:1문의를 통해 관리자에게
                        문의를 남겨주세요
                      </div>
                    </div>
                  </div>
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                      <button
                        class="accordion-button collapsed"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#questionTwo"
                        aria-expanded="false"
                        aria-controls="questionTwo"
                      >
                        비밀번호가 기억이 안나요
                      </button>
                    </h2>
                    <div
                      id="questionTwo"
                      class="accordion-collapse collapse"
                      aria-labelledby="headingTwo"
                      data-bs-parent="#accordionExample"
                    >
                      <div class="accordion-body">
                        비밀번호를 분실하였을 경우, '비밀번호 찾기'를 통해
                        만들었던 비밀번호를 찾을 수 있습니다.<br />
                        ① 홈페이지 로그인 창 아래있는 비밀번호 찾기를
                        클릭합니다.<br />
                        ② ‘비밀번호찾기’를 선택하여 가입하신 이메일,계정 통해
                        인증을 완료하면 고객님의 비밀번호를 변경할 주소를
                        보내드립니다.<br />
                        ③ 언제든 불편사항이 발생하면 1:1문의를 통해 관리자에게
                        문의를 남겨주세요
                      </div>
                    </div>
                  </div>
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                      <button
                        class="accordion-button collapsed"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#questionThree"
                        aria-expanded="false"
                        aria-controls="questionThree"
                      >
                        탈퇴한 계정을 다시 복구하고 싶어요
                      </button>
                    </h2>
                    <div
                      id="questionThree"
                      class="accordion-collapse collapse"
                      aria-labelledby="headingThree"
                      data-bs-parent="#accordionExample"
                    >
                      <div class="accordion-body">
                        탈퇴 신청후 3개월이 지나면 관련 기록은 모두 삭제가
                        됩니다.<br />
                        3개월이 지나지 않은 계정을 복구하기 위해선
                        dlgkstnrn@naver.com 이 메일주소로 계정에 관한 정보 및
                        문의를 보내주시면 도와드리겠습니다<br />
                        ① 계정을 모르시는 경우 위에 있는 메일을 통해 문의를
                        주시면 친절하게 답변해드리겠습니다.<br />
                        ② 언제든 불편사항이 발생하면 1:1문의를 통해 관리자에게
                        문의를 남겨주세요
                      </div>
                    </div>
                  </div>
                </div>
                <!-- End Default Accordion Example -->
              </div>
            </div>
          </div>

          <div class="col-xl-6">
            <div class="row wid-80">
              <div class="col-lg-6">
                <div class="info-box card">
                  <i class="bi bi-geo-alt"></i>
                  <h3>주소</h3>
                  <p>서울특별시<br />마포구 신촌로 176</p>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="info-box card">
                  <i class="bi bi-telephone"></i>
                  <h3>전화번호</h3>
                  <p>010-0000-0000<br />02-000-0000</p>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="info-box card">
                  <i class="bi bi-envelope"></i>
                  <h3>이메일</h3>
                  <p>choongang@example.com<br />choongang@example.com</p>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="info-box card">
                  <i class="bi bi-clock"></i>
                  <h3>상담가능시간</h3>
                  <p>월 - 금<br />오전 9:00 - 오후 05:00</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
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