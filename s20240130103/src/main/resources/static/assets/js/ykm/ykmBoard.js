/* 댓글 REST API */

// 세션에 저장된 유저 정보 불러오기
let seesion_user_no, seesion_user_nic;

function getUserInfo() {
	$.ajax({
		url: "/sessionData",
		type: "GET",
		success: function(data) {
		 seesion_user_no = data.seesion_user_no;
         seesion_user_nic = data.seesion_user_nic;
		},
		error: function(error) {
			console.log('세션 데이터를 가져오는 중 오류 발생!', error);
		}
	});
}

// 댓글 리스트
let cboardNo = null;
function getCommentList(cboard_no) {
	if (cboardNo === null) {
		cboardNo = cboard_no;
	}
	console.log('cboard_no -> ' + cboard_no);

	$.ajax({
		url: "/comment?cboard_no=" + cboard_no,
		type: "GET",
		success: function(data) {
			let listFilter = data.filter(comment => comment.creply_delete_chk === 0);
			updateCommentView(listFilter);
		
		},
		error: function(error) {
			console.log('리스트 오류 발생!', error);
		}
	});
}

let currentTime;
let commentWriter;

// 댓글리스트 갱신
function updateCommentView(data) {
	let replyList = '';
	data.forEach(function(comment) {
		
		const paddingValue = comment.creply_indent * 30;
		
		console.log(comment);

		const originalDate = new Date(comment.creply_date);

		let hour = originalDate.getHours();
		const ampm = hour >= 12 ? '오후' : '오전';
		hour = hour % 12 || 12; // 0시일 때 12시로 변경
		const formatted = `${originalDate.getFullYear()}.${(originalDate.getMonth() + 1).toString().padStart(2, '0')}.${originalDate.getDate().toString().padStart(2, '0')} ${ampm} ${hour.toString().padStart(2, '0')}:${originalDate.getMinutes().toString().padStart(2, '0')}`;
		currentTime = formatted;
		
		// 댓글 작성자 비교
		// commentWriter: 댓글 작성자, seesion_user_no: 로그인 한 유저정보
		commentWriter = comment.user_no 
		console.log('commentWriter'+commentWriter);      

		replyList += `
				<div class="groupBy" style="padding-left: ${paddingValue}px;"> 
				<div class="comment-card" data-creply-no="${comment.creply_no}">
				<div class="comment-header">
				<img class="rounded-circle" src="/upload/userImg/${comment.user_profile== null ? '987654321487321564defaultImg.jpg' : comment.user_profile}" alt="유저 프로필"></img>
				<div class="comment-user-container">
					<p class="card-title comment-user-name">${comment.user_nic}</p>
					<p class="card-subtitle comment-updated-at">작성일 ${formatted}</p>
				</div>	
				<div class="btnContainer">
					<button type="button" id="check" class="checkButton_${comment.creply_no}" onclick="updateComment('${comment.creply_no}', document.querySelector('#inputField_${comment.creply_no}').value), buttonStatus('${comment.creply_no}')" style="display : none">
						<i class="bi bi-check2-circle"></i> 확인
					</button>
					<button type="button" class="modifyComment_${comment.creply_no} badge bg-light text-dark" 
							value="${comment.creply_no}" onclick="buttonStatus('${comment.creply_no}')" 
							style="display: ${seesion_user_no && commentWriter && seesion_user_no === commentWriter ? 'inline' : 'none'}">
							<i class="bi bi-pencil-fill"></i> 수정
					</button>
					<button type="button" class="deleteComment badge bg-light text-dark" 
							onclick="deleteComment('${comment.creply_no}','${comment.creply_group}')" 
							style="display: ${seesion_user_no && commentWriter && seesion_user_no === commentWriter ? 'inline' : 'none'}">
							<i class="bi bi-trash"></i> 삭제
					</button>
					<button type="button" class="replyBtn_${comment.creply_no} badge bg-light text-dark" 
							onclick="createReplyBox('${comment.creply_no}')">
							<i class="bi bi-reply-fill"></i> 답글
					</button>
				</div>
			</div>
			<div class="comment-body" style="padding-top: 0px; padding-bottom: 0px;">
				<input type="text" id="inputField_${comment.creply_no}" value ="${comment.creply_content}" 
						class="form-control" required="required" disabled >
	 			<button type="button" class="check" id="checkButton_${comment.creply_no}" 
	 					onclick="updateComment('${comment.creply_no}', document.querySelector('#inputField_${comment.creply_no}').value)" 
	 					style="display : none"><i class="bi bi-check2-circle"></i> 확인
	 			</button>
			</div>
				<div id="replyContainer_${comment.creply_no}" class="replyContainer_${comment.creply_no} replyBox"></div>
			</div>
			</div>
	`;
	
	});
	

	$('#commentContainer').html(replyList);
}

// 댓글 등록 버튼 이벤트
const commentEditor = $('.comment-editor');
const commentSubmitBtn = $('#commentSubmitBtn');

commentEditor.on("keydown", function(e) {
	if (e.keyCode === 13) {
		submitComment();
	}
})

commentSubmitBtn.on("click", function() {
	submitComment();
})


// 댓글 등록 버튼 정보 담기
function submitComment() {
	const creply_content = $('#creply_content').val();
	const cboardNo = $('input[name="cboard_no"]').val();
	const userNo = $('input[name="user_no"]').val();

	const commentData = {
		cboard_no: cboardNo,
		user_no: userNo,
		creply_content: creply_content
	};

	writeComment(commentData);

	$('#creply_content').val(''); // 입력 필드 비우기
}

// 댓글 등록
function writeComment(commentData) {
	const cboard_no = commentData.cboard_no;
	console.log(cboard_no);

	$.ajax({
		url: "/comment",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(commentData),
		success: function(data) {
			console.log(data);
			getCommentList(cboard_no);
			$('#answer-count').text(Number($('#answer-count').text()) + 1);
		},
		error: function(error) {
			console.log('댓글 등록 오류 발생!', error);
		}
	});
}

// 댓글 수정 버튼 이벤트
function buttonStatus(creply_no) {

	console.log('buttonStatus creply_no : ' + creply_no);

	let inputField = $(`#inputField_` + creply_no);
	let checkButton = $(`.checkButton_` + creply_no);
	let modifyButton = $(`.modifyComment_` + creply_no);

	if (modifyButton.html() === '<i class="bi bi-x-lg"></i> 취소') {
		// 취소 버튼을 클릭한 경우
		inputField.prop('disabled', 'disabled');
		checkButton.hide();
		modifyButton.html('<i class="bi bi-pencil-fill"></i> 수정');
	} else {
		// 수정 버튼을 클릭한 경우
		inputField.prop('disabled', false);
		checkButton.show();
		modifyButton.html('<i class="bi bi-x-lg"></i> 취소');
	}

}

// 댓글 수정
function updateComment(creply_no, creply_content) {

	var commentData = {
		creply_no: creply_no,			// 댓글 번호
		creply_content: creply_content 	// 수정 된 댓글 내용
	};

	console.log("commentData.creply_content ===> " + commentData.creply_content);

	$.ajax({
		url: "/comment",
		type: "PUT",
		contentType: "application/json",
		data: JSON.stringify(commentData),
		success: function(data) {
			console.log(data);
			$(`#inputField_${creply_no}`).value = commentData.creply_content;
		},
		error: function(error) {
			console.log('댓글 수정 오류!', error);
		}
	});
	// 확인 누르면 화면에서 갱신됨
	$(`#inputField_${creply_no}`).disabled = true;
}

// 댓글 삭제
// 댓글 삭제 버튼 클릭하면 삭제상태 값을 0 > 1 변경 (관리자 페이지에서 최종 삭제)
function deleteComment(creply_no) {
	$.ajax({
		url: "/comment/" + creply_no,
		type: "DELETE",
		success: function(response) {
			// 삭제된 댓글을 화면에서 제거
			const deleteComment = $(`.comment-card[data-creply-no="${creply_no}"]`);
			deleteComment.remove();
			$('#answer-count').text(Number($('#answer-count').text()) - 1);
		},
		error: function(error) {
			console.log('댓글 삭제 오류!', error);
		}
	});
}

$('.recruitBtn').on("click", function() {
	let cboard_no = $('.recruitBtn').closest('.sider_right').find('input[name=cboard_no]').val();
	let comm_mid2 = $('.recruitBtn').closest('.sider_right').find('input[name=comm_mid2]').val();
	
	
	
	updateRecruitment(cboard_no, comm_mid2);
});

// 모집 버튼 이벤트, 모집완료가 되면 값 변경
function updateRecruitment(cboard_no, comm_mid2) {
	console.log('updateRecruitment 실행!');
	$.ajax({
		url: "/recruitment",
		type: "PUT",
		contentType: "application/json",
		data: JSON.stringify({
			cboard_no: cboard_no,
			comm_mid2: comm_mid2 == 10 ? 20 : 10
		}),
		success: function(data) {
			console.log('success', data);
			$('.recruitBtn').closest('.sider_right').find('input[name=comm_mid2]').val(data.comm_mid2);
			if (data.comm_mid2 === 20) {
				$('.recruitBtn').text("모집 완료");
				$('.tooltip').hide();
				console.log('모집완료 상태로 변경되었습니다. : ' + data);
			} else if (data.comm_mid2 === 10) {
				$('.recruitBtn').text("모집 중");
				console.log('모집중 상태로 변경되었습니다.');
			}
		},
		error: function(error) {
			console.log('모집완료 변경 오류!', error);
		}
	});
}

// 대댓글 이벤트
function createReplyBox(creply_no) {
	const replyBox = `<div class="reply-editor">
				        <i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
				        <input type="text" id="creply_content_${creply_no}" placeholder="댓글 추가..." class="form-control" required="required">
				    </div>
				    <div class="replyBtnContainer">
				        <button id="replyResetBtn_${creply_no}" type="button" onclick="hideReplyBox('${creply_no}')">취소</button>
				        <button id="replySubmitBtn_${creply_no}" type="button" onclick="writeReply('${creply_no}')">답글</button>
				    </div>
				    `;

	$('.replyContainer_'+creply_no).html(replyBox);
}

// 대댓글 등록
function writeReply(creply_no) {
	const creply_content = $(`#creply_content_${creply_no}`).val();
	const replyData = {
		cboard_no: cboardNo,
		creply_no : creply_no,
		creply_content : creply_content
	}
	console.log('writeReply Data : ' +replyData);
	
	$.ajax({
		url: "/replys",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(replyData),
		success: function(data) {
			console.log('댓글이 성공적으로 등록되었습니다', data);
			getCommentList(cboardNo);
			$('#answer-count').text(Number($('#answer-count').text()) + 1);
		},
		error: function(error) {
			console.log('댓글 등록 중 오류 발생!', error);
		}
	});
	
	$(`.replyContainer_${creply_no} > *`).empty();
		
}


function hideReplyBox(creply_no) {
	$(`.replyContainer_${creply_no}`).remove();
}


/* 삭제 확인 팝업 */

// 삭제 확인 버튼을 누르면 글 삭제
var postDeleteBtn = $('#postDeleteBtn');

if (postDeleteBtn!==null) {
	postDeleteBtn.on("click", function(e) {
		e.preventDefault();
		$('.confirmPopup').css("display", "flex");
	});
}

// 취소 버튼 클릭 시 팝업을 숨김
$('#cancelButton').on('click', function() {
	$('.confirmPopup').css("display", "none");
});
