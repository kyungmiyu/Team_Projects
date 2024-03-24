<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>마이페이지</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="assets/js/lhs/lhsuserMypage.js" defer="defer"></script>
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
  <link href="assets/css/lhs/userMypage.css" rel="stylesheet">
  <link href="assets/css/style.css" rel="stylesheet"> <!-- 헤더, 푸터, 사이드바 css -->

</head>
<body>
    <!-- Header -->
    <%@ include file="../header.jsp" %>
    <!-- Sidebar -->
    <%@ include file="../asidebar.jsp" %>
    <!-- Main Content -->
    
    <main id="main" class="main">

    <div class="pagetitle">
      <h1>프로필</h1>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
            <c:choose>
				<c:when test="${user.user_profile!=null }">
					 <img src="${pageContext.request.contextPath}/upload/userImg/${user.user_profile}" alt="Profile" class="userProfileSize">
				</c:when>
				<c:otherwise>
					<img src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="userProfileSize">
				</c:otherwise>
             </c:choose>
             <h2 class="photoUsername">${user.user_name }</h2>
             <div class="social-links mt-2 fntsize25">
             <c:forEach var="i" begin="1" end="${user_score }">
             	<i class="bi bi-star-fill"></i>
             </c:forEach>
             <c:forEach var="i" begin="1" end="${5-user_score }">
             	<i class="bi bi-star"></i>
             </c:forEach>
             </div>
           </div>
         </div>

        </div>

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
            
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Overview</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">Edit Profile</button>
                </li>
                
                <li class="nav-item ms-auto">
                  <button class="btn btn-outline-danger btn-sm dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                  	<i class="bi bi-gear"></i>
                  </button>
                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
				      <li>
				      	<a class="dropdown-item" href="userChangePassword">비밀번호변경</a>
				      </li>
				      <li>
				      	<a class="dropdown-item" href="userLeaveForm">회원탈퇴</a>
				      </li>
				  </ul>
                </li>
                
                
                
              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview">

                  <h5 class="card-title">사용자 정보</h5>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">이름</div>
                    <div class="col-lg-9 col-md-8">${user.user_name }</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">닉네임</div>
                    <div class="col-lg-9 col-md-8">${user.user_nic }</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">휴대폰번호</div>
                    <div class="col-lg-9 col-md-8">${user.user_phone }</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">이메일</div>
                    <div class="col-lg-9 col-md-8">${user.user_email }</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">가입일</div>
                    <div class="col-lg-9 col-md-8">
                    	<fmt:formatDate value="${user_date }" dateStyle="full"/>
                    </div>
                  </div>

                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                  <!-- Profile Edit Form -->
                  <form method="post" enctype="multipart/form-data" action="userUpdate">
                    <div class="row mb-3">
                      <label for="profileImage" class="col-md-4 col-lg-3 col-form-label">유저 프로필사진</label>
                      <div class="col-md-8 col-lg-9">
                      <c:choose>
						<c:when test="${user.user_profile!=null }">
							<img class="userProfileSize" src="${pageContext.request.contextPath}/upload/userImg/${user.user_profile}" alt="Profile" id="userProfile">
						</c:when>
						<c:otherwise>
							<img class="userProfileSize" src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" id="userProfile" alt="Profile">
						</c:otherwise>
		             </c:choose>
                     
                        <div class="pt-2">
                        	<input type="file" name="user_profile" class="form-control" id="file">
                        </div>
                      </div>
                    </div>
                    
                    <div class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label"> 아이디</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="user_id" type="text" class="form-control" id="user_id" value="${user.user_id }" readonly="readonly">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">이름</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="user_name" type="text" class="form-control" id="user_name" value="${user.user_name }">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="닉네임" class="col-md-4 col-lg-3 col-form-label">닉네임</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="user_nic" type="text" class="form-control" id="user_nic" value="${user.user_nic }">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="휴대폰번호" class="col-md-4 col-lg-3 col-form-label">휴대폰번호</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="user_phone" type="text" class="form-control" id="user_phone" value="${user.user_phone }">
                      </div>
                    </div>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">저장하기</button>
                    </div>
                  </form><!-- End Profile Edit Form -->

                </div>

              </div><!-- End Bordered Tabs -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->
    <!-- Footer -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->
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
