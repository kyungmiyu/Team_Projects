let currentuser_no;

// 유저 정보 가져오기 
function getUserNo() {
	$.ajax({
		url: "/userno",
		method: 'GET',
		success: function(data) {
			currentuser_no = data
			console.log("currerntuser_no ->" + currentuser_no);
		},
		error: function(error) {
			console.log('사용자 정보를 가져오는 중 오류 발생:', error);
		}
	})
}

// getUserNic 함수 에러 콜백 수정
function getUserNic() {
    $.ajax({
        url: '/getUserInfo',
        method: 'GET',
        success: function(data) {
            currentuser_nic = data;
            console.log(currentuser_nic);
            $('#freeBoardRe').text(currentuser_nic + '님, 댓글을 작성해보세요.');
        },
        error: function(error) {
            console.log('사용자 닉네임을 가져오는 중 오류 발생:', error.responseText); // 수정: 오류 내용 출력
        }
    });
}

  

// 게시판 댓글 리스트
let cboardNo = null;

function replyBoardFreeAskList(cboard_no) {
	if (cboard_no === null) {
		cboard_no = cboardNo;
		console.log('cboard_no :' +cboard_no);
	}
	$.ajax({
		url: "/reply?cboard_no=" + cboard_no,
		method: "GET",
		success: function(data) {
			data.forEach(function(comment) {
				console.log("댓글리스트 comemnt : " + comment)
			});
			updateReplyList(data);
		},
		error: function(error) {
			console.log('리스트 업데이트 오류', error);
		}
	});
}

// 게시판 댓글 리스트 갱신 
function updateReplyList(data) {
    let boardReplyList = '';
    $.each(data, function(index, lslCommReply) {
        console.log(lslCommReply);
        const originalDate = new Date(lslCommReply.creply_date);
        let hour = originalDate.getHours();
        const ampm = hour >= 12 ? '오후' : '오전';
        hour = hour % 12 || 12; 
        const formatDate = `${originalDate.getFullYear()}.${(originalDate.getMonth() + 1).toString().padStart(2, '0')}.${originalDate.getDate().toString().padStart(2, '0')} ${ampm} ${hour.toString().padStart(2, '0')}:${originalDate.getMinutes().toString().padStart(2, '0')}`;

        let ReplyUser = lslCommReply.user_no;

        console.log(currentuser_no);
        console.log(ReplyUser);

        // 현재 세션 사용자와 댓글 작성자가 동일한 경우에만 수정 및 삭제 버튼 표시
        const showButtons = currentuser_no === ReplyUser;

      
            indentation = lslCommReply.creply_indent * 25;
            
     
        
        var profileImageUrl = ''; 

		// 프로필 이미지가 없는 경우 기본 이미지 
		if (lslCommReply.user_profile !=null) {
		    profileImageUrl = 'http://localhost:8989/upload/userImg/'+lslCommReply.user_profile;
		} else {
		    profileImageUrl = 'http://localhost:8989/upload/userImg/987654321487321564defaultImg.jpg'; // 기본 이미지 URL
		}
		
		console.log(profileImageUrl);
		
        let imageUrl = profileImageUrl;


        boardReplyList += `<div class="re-comment-body" style="margin-left: ${indentation}px;"> 
            <div id="replyBoardFreeList" class="comment-card">
                <div class="comment-header">
                    <img class="user-profile" src="${imageUrl}" alt="User Profile" ></img>
                    <div class="comment-user-container">
                        <p id="replyuser_name" class="card-title comment-user-name">${lslCommReply.user_nic}</p>
                        <p class="card-subtitle comment-updated-at">작성일 ${formatDate}</p>
                    </div>
                    <div class="re-btn-container">
                        <button type="button" class="brModify" onclick="toggleEdit('${lslCommReply.creply_no}');" style="display: ${showButtons ? 'block' : 'none'};"><i class="bi bi-pencil-fill"></i>수정</button>
                        <button type="button" class="brDelete" onclick="deleteComment('${lslCommReply.cboard_no}', '${lslCommReply.creply_no}');" style="display: ${showButtons ? 'block' : 'none'};"><i class="bi bi-trash"></i>삭제</button>
                        <button type="button" class="btn brBtn" data-creply-group="${lslCommReply.creply_group}" data-cboard-no="${lslCommReply.cboard_no}" data-creply-level="${lslCommReply.creply_level}" data-creply-indent="${lslCommReply.creply_indent}" data-creply-no="${lslCommReply.creply_no}" onclick="toggleReply('${lslCommReply.creply_no}');"><i class="bi bi-reply-fill"></i> 답글</button>
                    </div>
                </div>
                
                <div id="replyBox">
                    <div id="editComment_${lslCommReply.creply_no}" class="card-body comment-body" style="display: none;">
                        <textarea id="editCommentText_${lslCommReply.creply_no}" class="form-control">${lslCommReply.creply_content}</textarea>
                        <button type="button" class="btn brSave" onclick="modifyComment('${lslCommReply.creply_no}');">저장</button>
                        <button type="button" class="btn brCancle" onclick="toggleEdit('${lslCommReply.creply_no}');">취소</button>
                    </div>
                </div>

                <div id="viewComment_${lslCommReply.creply_no}" class="card-body comment-body">
                    <p class="markdown-body" id="creply_content${lslCommReply.creply_no}">${lslCommReply.creply_content}</p>
                </div>

                <div id="addComment_${lslCommReply.creply_no}" class="card-body comment-body" style="display: none;">
                    <textarea id="addCommentText_${lslCommReply.creply_no}"  class="form-control"></textarea>
                    <button type="button" class="btn addReply" id="addReply" data-cboard-no="${lslCommReply.cboard_no}" data-creply-no="${lslCommReply.creply_no}" data-creply-indent="${lslCommReply.creply_indent}" data-user-profile="${lslCommReply.user_profile}" data-creply-level="${lslCommReply.creply_level}"  onclick="addReply(${lslCommReply.creply_no},${lslCommReply.creply_group});">저장</button>
                    <button type="button" class="btn replyCancle" onclick="toggleReply('${lslCommReply.creply_no}');">취소</button>
                </div>
            </div>
        </div>`;
    });

    $('#boardReplyForm').html(boardReplyList);
}




// 대댓글 입력창 토글 함수
function toggleReply(creply_no) {
    const addComment = $(`#addComment_${creply_no}`);
    addComment.css('display', addComment.css('display') === 'none' ? 'block' : 'none');

}

 

const replyData = null;

// 댓글 등록 이벤트 처리 
$(document).on("click", "#submitBtn", function() {
    const creply_content = $('#creply_content').val();
    const cboard_no = $('input[name="cboard_no"]').val();
    const user_No = $('input[name="user_no"]').val();
    const creply_no = $('input[name="creply_no"]').val();
    const user_profile = $('input[name="user_profile"]').val();

    const replyData = {
        cboard_no: cboard_no,
        user_no: user_No,
        creply_content: creply_content,
        creply_no: creply_no,
        user_profile:user_profile
        
    };

    console.log(replyData);

    addComment(replyData);
});

// 댓글 등록 
function addComment(replyData) {
    $.ajax({
        url: "/replys/insert",
        method: "POST",
        contentType: 'application/json',
        data: JSON.stringify(replyData),
        success: function(data) {
            console.log(data);
            if (data > 0) {
                $('#creply_content').val('');
                // 댓글 등록 후 댓글 리스트 업데이트
                $('#replyCnt').text(Number($('#replyCnt').text()) + 1);
                replyBoardFreeAskList(replyData.cboard_no);
            } else {
                console.log('댓글 등록에 실패했습니다!');
            }
        },
        error: function(error) {
            console.log('댓글 등록 오류 발생!', error);
        }
    });
}





// 대댓글 
let cboard_no;
let pcreply_group;
let pcreply_level;
let pcreply_indent;

$(document).on("click", ".brBtn", function() {
	//pcreply_no = $(this).data("creply-no");
	pcreply_group = $(this).data("creply-group");
	cboard_no = $(this).data("cboard-no");
	pcreply_level = $(this).data("creply-level");
	pcreply_indent = $(this).data("creply-indent");
	
	
	console.log("pcreply_group :" +pcreply_group);
	console.log('cboard_no : ' + cboard_no);
	console.log('preply_level : ' + pcreply_level);	
	console.log('pcreply_indent : ' + pcreply_indent);	
	
});


// 대댓글 추가 시 creply_group 추가
$(document).on('click', ".addReply", function(e) {
	console.log(e.target.dataset);
	console.log("test" , e.target.dataset['creplyNo']);
	console.log("test" , e.target.dataset['creplyLevel']);
    
    // 대댓글 데이터에 creply_group 추가
    // 넘겨주는 값의 이름은 모델의 이름과 같게 해서 넘겨야 한다.
    //creply_no = 모델 이름과 동일하게 
    const reReplyData = {
        creply_content : $('#addCommentText_'+e.target.dataset['creplyNo']).val(),
        creply_group : pcreply_group,
        creply_indent : e.target.dataset['creplyIndent'],
        creply_level : e.target.dataset['creplyLevel'],
        creply_no : e.target.dataset['creplyNo'],
        user_profile : e.target.dataset['userProfile'],
        cboard_no : cboard_no
        
		
    };

    addReply(reReplyData);
    console.log(reReplyData);
});


// 대댓글 등록
function addReply(reReplyData) {
    $.ajax({
        url: "/rereplys/reinsert",
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(reReplyData),
        success: function(data) {
            console.log(data);
            if (data > 0) {
                console.log('대댓글 데이터 넘어옴');
                replyBoardFreeAskList(reReplyData.cboard_no);
                $('#replyCnt').text(Number($('#replyCnt').text()) + 1);
            }
        },
        error: function(error) {
            console.log('대댓글 데이터 오류' + error);
            console.log(reReplyData);
        }
    });
}






// 수정 창을 토글하는 함수
function toggleEdit(creply_no) {
	// 수정 창의 display 속성을 토글
	const editComment = $(`#editComment_${creply_no}`);
	const brModify = $('.brModify');
	const brDelete = $('.brDelete');

	if (editComment.css('display') === "none") {
		editComment.css('display', 'block');
		brModify.css('display', 'none');
		brDelete.css('display', 'none');
	} else {
		editComment.css('display', 'none');
		brModify.css('display', 'block');
		brDelete.css('display', 'block');
	}

	// 뷰 창의 display 속성도 토글
	const viewComment = $(`#viewComment_${creply_no}`);
	if (viewComment.css('display') === "none") {
		viewComment.css('display', 'block');
	} else {
		viewComment.css('display', 'none');
	}
}



// 댓글 삭제
function deleteComment(cboard_no, creply_no) {
	// AJAX 요청을 통해 댓글 삭제 API 호출
	$.ajax({
		url: `/replys/delete?cboard_no=${cboard_no}&creply_no=${creply_no}`,
		method: "DELETE",
		success: function(data) {
			// 댓글 삭제 성공 여부에 따라 처리
			if (data > 0) {
				// 삭제 성공 시, 다시 댓글 리스트를 업데이트
				replyBoardFreeAskList(cboard_no);
				$('#replyCnt').text(Number($('#replyCnt').text()) - 1);
				 
			} else {
				console.log('댓글 삭제에 실패했습니다!');
			}
		},
		error: function(error) {
			console.log('댓글 삭제 오류 발생!', error);
		}
	});
}





// 댓글 수정
function modifyComment(creply_no) {
	const creply_content = $(`#editCommentText_${creply_no}`).val();
	const modifyData = {
		creply_no: creply_no,
		creply_content: creply_content
	};
	console.log('modifyData -> ' + JSON.stringify(modifyData));

	$.ajax({
		url: "/replys/modify",
		method: "PUT",
		contentType: 'application/json',
		data: JSON.stringify(modifyData),
		success: function(data) {
			if (data > 0) {
				// 수정 성공 시, 다시 댓글 리스트를 업데이트
				$(`#creply_content${creply_no}`).html(modifyData.creply_content);
				toggleEdit(modifyData.creply_no);
			} else {
				console.log('댓글 수정에 실패했습니다!');
			}
		},
		error: function(error) {
			console.log('댓글 수정 오류 발생!', error);
		}
	});
} 