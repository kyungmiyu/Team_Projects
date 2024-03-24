<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header id="header" class="header fixed-top d-flex align-items-center">

        <div class="d-flex align-items-center justify-content-between">
            <a href="main" class="logo d-flex align-items-center">
                <img src="assets/img/blueberry-logo.png" alt="">
                <span class="d-none d-lg-block">Blueberry</span>
            </a>
            <i class="bi bi-list toggle-sidebar-btn"></i>
        </div><!-- End Logo -->

        <nav class="header-nav ms-auto">
            <ul class="d-flex align-items-center">

                <li class="nav-item d-block d-lg-none">
                    <a class="nav-link nav-icon search-bar-toggle " href="#">
                        <i class="bi bi-search"></i>
                    </a>
                </li><!-- End Search Icon-->

                <li class="nav-item dropdown">

                <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
		            <i class="bi bi-bell"></i>
		            <span class="badge bg-primary badge-number" id="badge-number1">${sessionScope.addressRequestCnt }</span>
		        </a>

                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
                        <li class="dropdown-header">
                            주소록 받은 요청 최대 4개까지 표시됩니다.
                            <a href="address"><span class="badge rounded-pill bg-primary p-2 ms-2">전체보기</span></a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

						<c:forEach items="${sessionScope.addressRequestUserName }" var="name">
							<a href="address">
		                        <li class="notification-item">
		                            <i class="bi bi-exclamation-circle text-warning"></i>
		                            <div>
		                                <h4>${name }</h4>
		                                <p>님의 요청이 있습니다</p>
		                            </div>
		                        </li>
		
		                        <li>
		                            <hr class="dropdown-divider">
		                        </li>
	                        </a>
						</c:forEach>
	
	                        <li class="dropdown-footer">
	                        </li>

                    </ul><!-- End Notification Dropdown Items -->

                </li><!-- End Notification Nav -->

                <li class="nav-item dropdown">

                    <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
                        <i class="bi bi-chat-left-text"></i>
                        <span class="badge bg-success badge-number" id="badge-number2">${sessionScope.messageCnt}</span>
                    </a><!-- End Messages Icon -->

                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow messages">
                        <li class="dropdown-header">
                            최대 3개까지만 표시됩니다.
                            <a href="msgReceivebox"><span class="badge rounded-pill bg-primary p-2 ms-2">전체보기</span></a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

						<c:forEach items="${sessionScope.headerList }" var="message">
	                        <li class="message-item">
	                            <a href="/msgReadReceived?msg_no=${message.msg_no}">
	                            <c:choose>
                        			<c:when test="${message.user_profile !=null}">
                        				<img src="${pageContext.request.contextPath}/upload/userImg/${message.user_profile}" alt="" class="rounded-circle">
                        			</c:when>
                        			<c:otherwise>
                        				<img src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" alt="" class="rounded-circle">
                        			</c:otherwise>
                        		</c:choose>
	                                <div>
	                                    <h4>${message.msg_title }</h4>
	                                    <p>${message.senderNic }</p>
	                                    <p>${message.msg_createdate }</p>
	                                </div>
	                            </a>
	                        </li>
	                        <li>
	                            <hr class="dropdown-divider">
	                        </li>
						</c:forEach>
                    </ul><!-- End Messages Dropdown Items -->

                </li><!-- End Messages Nav -->

                <li class="nav-item dropdown pe-3">

                    <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    
                    <!-- 로그인 성공 시 세션으로 인해 화면이 변하는 부분 -->
                        <c:choose>
                        	<c:when test="${sessionScope.user_no eq null}">
                        		<img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle">
                        		<span class="d-none d-md-block dropdown-toggle ps-2">로그인안됨</span>
                        		<script type="text/javascript">
                        		 	location.href="loginForm";
                        		</script>
                        	</c:when>
                        	<c:otherwise>
                        		<c:choose>
                        			<c:when test="${user_profile !=null}">
                        				<img src="${pageContext.request.contextPath}/upload/userImg/${user_profile}" alt="Profile" class="rounded-circle">
                        			</c:when>
                        			<c:otherwise>
                        				<img src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="rounded-circle">
                        			</c:otherwise>
                        		</c:choose>
                        			<span class="d-none d-md-block dropdown-toggle ps-2">${sessionScope.user_name }</span>
                        	</c:otherwise>
                        </c:choose>
                    <!-- 로그인 성공 시 세션으로 인해 화면이 변하는 부분 -->
                        
                    </a><!-- End Profile Iamge Icon -->

                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                        <li class="dropdown-header">
                            <h6>${sessionScope.user_name }</h6>
                            <span>회원</span>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li>
			              <a class="dropdown-item d-flex align-items-center" href="myPage">
			                <i class="bi bi-person"></i>
			                <span>마이페이지</span>
			              </a>
			            </li>
			            <li>
			              <hr class="dropdown-divider">
			            </li>
			            
			            <li>
			              <a class="dropdown-item d-flex align-items-center" href="askMain">
			                <i class="bi bi-question-circle"></i>
			                <span>문의하기</span>
			              </a>
			            </li>
			            <li>
			              <hr class="dropdown-divider">
			            </li>
			
			            <li>
			              <a class="dropdown-item d-flex align-items-center" href="userLogout">
			                <i class="bi bi-box-arrow-right"></i>
			                <span>로그아웃</span>
			              </a>
			            </li>
			            <li>
			              <hr class="dropdown-divider">
			            </li>

                    </ul><!-- End Profile Dropdown Items -->
                </li><!-- End Profile Nav -->

            </ul>
        </nav><!-- End Icons Navigation -->

    </header><!-- End Header -->
    <script type="text/javascript">
	    $(document).ready(function() {
	    	/* 쪽지알림 99개 이상이면 +로 표시 */
	        var messageCount = parseInt($('#badge-number1').text(), 10); // 현재 메시지 수를 정수로 변환
	        if (messageCount >= 100) {
	            $('#badge-number1').text('99+');
	        }
	    	/* 쪽지알림 99개 이상이면 +로 표시 */
	        var messageCount = parseInt($('#badge-number2').text(), 10); // 현재 메시지 수를 정수로 변환
	        if (messageCount >= 100) {
	            $('#badge-number2').text('99+');
	        }
	    });
    </script>