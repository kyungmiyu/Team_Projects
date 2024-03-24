<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
  <link href="assets/img/blueberry-logo.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link
    href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
    rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet" type="text/css">
  <link href="assets/css/lhs/admin.css" rel="stylesheet" type="text/css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script type="text/javascript" src="assets/js/lhs/lhsadmin.js"></script>

</head>

<body>
   <!-- ======= Header ======= -->
    <%@ include file="admin_header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="admin_aside.jsp" %>
  <!-- End Sidebar-->

  <main id="main" class="main">
      <section>
        <div class="main-div">
          <div class="main-top">
            <div class="pagetitle">
              <h1>관리자전용 페이지</h1>
            </div>

            <div class="d-flex justify-content-around">
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20">
                  회원<span class="small-title">(신규 가입자)</span>
                </div>
                <div class="content-detail">
                  ${cntMap.ALL_USER_CNT }&nbsp;<span class="small-title cl-pupple">
                    (${cntMap.TODAY_USER_CNT })<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20 cl-gray">
                  문의글<span class="small-title">(신규 문의글)</span>
                </div>
                <div class="content-detail">
                  ${cntMap.ALL_BOARDADMIN_CNT }&nbsp;<span class="small-title cl-pupple">
                    (${cntMap.TODAY_BOARDADMIN_CNT })<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20">
                  댓글<span class="small-title">(신규 댓글)</span>
                </div>
                <div class="content-detail">
                  ${cntMap.ALL_COMENT_CNT }&nbsp;<span class="small-title cl-pupple">
                    (${cntMap.TODAY_COMENT_CNT })<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20">
                  게시글<span class="small-title">(신규 게시글)</span>
                </div>
                <div class="content-detail">
                  ${cntMap.ALL_BOARD_CNT }&nbsp;<span class="small-title cl-pupple">
                    (${cntMap.TODAY_BOARD_CNT })<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div class="main-mid d-flex justify-content-between">
            <div class="mid-left border-radius10 bg-white">
              <!-- 그래프 영역 시작-->
              <div class="card-body">
                <div class="d-flex justify-content-between">
                  <div class="fnt-nanum fnt-20">신규 회원</div>
                  <div class="fnt-nanum">(Day)</div>
                </div>
                <!-- Bar Chart -->
                <canvas
                  id="barChart"
                  style="
                    max-height: 400px;
                    display: block;
                    box-sizing: border-box;
                    height: 223px;
                    width: 446px;
                  "
                  width="446"
                  height="223"
                ></canvas>

                <!-- End Bar CHart -->
              </div>
              <!-- 그래프 영역 끝-->
            </div>
            <div class="mid-right">
              <div class="mid-right-top bg-white">
                <div class="right-title fnt-nanum">신규 문의글</div>
                <div>
                  <div class="card border-none">
                    <div class="card-body padding-none">
                      <!-- Default Table -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">아이디</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성일</th>
                          </tr>
                        </thead>
                        <tbody>
	                       <c:forEach items="${boardAdminList }" var="boardAdmin">
		                        <tr>
		                          <th>${boardAdmin.user_id }</th>
		                          <td>
		                          	<a href="/admin_ask_detail?user_no=${boardAdmin.user_no }&admin_no=${boardAdmin.admin_no}">${boardAdmin.admin_title }</a>
		                          </td>
		                          <td>
		                          	<fmt:formatDate value="${boardAdmin.admin_date }" type="both"/>
		                          </td>
		                        </tr>
	                        </c:forEach> 
                        </tbody>
                      </table>
                      <!-- End Default Table Example -->
                    </div>
                  </div>
                </div>
              </div>
              <div class="mid-right-bottom bg-white">
                <div class="right-title fnt-nanum">회원 탈퇴 신청</div>
                <div>
                  <div class="card border-none">
                    <div class="card-body padding-none">
                      <!-- Table with hoverable rows -->
                      <table class="table table-hover">
                        <thead>
                          <tr>
                            <th scope="col">회원번호</th>
                            <th scope="col">이름</th>
                            <th scope="col">신청일</th>
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${deleteUserList }" var="user">
                          <tr>
                            <th>${user.user_no }</th>
                            <td>
                            	<a href="/admin_user_detail?user_no=${user.user_no }">${user.user_name }</a>
                            </td>
                            <td>
                            	<fmt:formatDate value="${user.user_update_date }" type="both"/> 
                            </td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                      <!-- End Table with hoverable rows -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  <!-- End #main -->

  <!-- ======= Footer ======= -->
    <%@ include file="admin_footer.jsp" %>
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