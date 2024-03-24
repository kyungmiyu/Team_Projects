<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:if test="${sessionScope.admin ne true }">
 	<script>
 		location.href = "main";
 	</script>
 </c:if>
  <header id="header" class="header fixed-top d-flex align-items-center">
      <div class="d-flex align-items-center justify-content-between">
        <a href="adminMain" class="logo d-flex align-items-center">
          <img src="assets/img/blueberry-logo.png" alt="" />
          <span class="d-none d-lg-block">Blueberry</span>
        </a>
        <i class="bi bi-list toggle-sidebar-btn"></i>
      </div>
      <!-- End Logo -->

      <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">
          <li class="nav-item dropdown pe-3">
            <a
              class="nav-link nav-profile d-flex align-items-center pe-0"
              href="#"
              data-bs-toggle="dropdown"
            >
              <!-- 로그인 성공 시 세션으로 인해 화면이 변하는 부분 -->
              <img
                src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
                alt="Profile"
                class="rounded-circle"
              />
              <span class="d-none d-md-block dropdown-toggle ps-2">관리자</span>
              </a><!-- End Profile Iamge Icon -->

            <ul
              class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile"
            >
              <li class="dropdown-header">
                <h6>${sessionScope.user_name }</h6>
                <span>관리자</span>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>

              <li>
                <a
                  class="dropdown-item d-flex align-items-center"
                  href="adminRegisterForm"
                >
                  <i class="bi bi-person-plus"></i>
                  <span>신규 관리자 등록</span>
                </a>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>
              <li>
                <a
                  class="dropdown-item d-flex align-items-center"
                  href="userLogout"
                >
                  <i class="bi bi-box-arrow-right"></i>
                  <span>로그아웃</span>
                </a>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>
            </ul>
            <!-- End Profile Dropdown Items -->
          </li>
          <!-- End Profile Nav -->
        </ul>
      </nav>
      <!-- End Icons Navigation -->
    </header>