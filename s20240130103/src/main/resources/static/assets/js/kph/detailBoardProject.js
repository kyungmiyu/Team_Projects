// 대댓글 더보기 버튼
$('.reply-list').on('click', '.reply-reply-btn', function () {
	const reply_list = $(this).closest('.reply-reply-box').find('.reply-reply-list');
	if ($(reply_list).css('display') == 'none') {
		$(reply_list).css('display', 'block');
	} else {
		$(reply_list).css('display', 'none');
	}
});

// 대댓글/ 대댓글 추가용 변수
let preply_group;

// 대댓글 달기 버튼
$('.reply-list').on('click', '.reply-reply-write', function () {
	const write_box = $(this).closest('.reply-detail').find('.reply-reply-write-box')
	console.log(write_box)
	preply_group = $(this).closest('.reply-detail').children('input[name=preply_group]').val();
	console.log(preply_group);
	if ($(write_box).css('display') == 'none') {
		$(write_box).css('display', 'flex');
	} else if ($(write_box).css('display') == 'flex' && $(write_box).children('textarea').val() != '') {
		$(write_box).children('textarea').val('');
	} else {
		$(write_box).css('display', 'none');
	}
});

// 대대댓글 달기 버튼
$('.reply-list').on('click', '.reply-reply-reply-write', function () {
	const write_box = $(this).closest('.reply-detail').find('.reply-reply-write-box');
	preply_group = $(this).closest('.reply-reply-detail').find('input[name=preply_group]').val();
	const user_name = $(this).closest('.reply-reply-detail').find('.reply-reply-user-name').text();
	if ($(write_box).css('display') == 'none') {
		$(write_box).children('textarea').val('@' + user_name + ' ');
		$(write_box).css('display', 'flex');
	} else if ($(write_box).css('display') == 'flex' && $(write_box).val() != '') {
		$(write_box).css('display', 'none');
	} else {
		$(write_box).children('textarea').val('@' + user_name + ' ');
	}
});


// reply-write-btn = 댓글 쓰기
// reply-reply-write = 대댓글 쓰기 / 대대댓글 쓰기

// 댓글 쓰기
$('.reply-write-btn').on('click', function () {
	const preply_content = $(this).siblings().val();
	const pboard_no = $('#pboard_no').val();
	$(this).siblings().val('');
	$.ajax({
		type: "post",
		url: "boardProjectReplyAdd",
		data: {
			preply_content: preply_content,
			pboard_no: pboard_no
		},
		dataType: "json",
		success: function (reply) {
			
			const viewCnt = Number($('.reply-count span').text());  
			$('.reply-count span').text(viewCnt + 1);

			const userImg = reply.user_profile == null ?
				`<img src="/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="rounded-circle">` :
				`<img src="/upload/userImg/${reply.user_profile}" alt="Profile" class="rounded-circle">`;

			const writeDay = reply.preply_update_date == null ?
				`<p class="reply-write-day">${reply.preply_date}</p>` :
				`<p class="reply-write-day">${reply.preply_update_date}</p>`;

			let preply_content = reply.preply_delete_chk == 0 ?
				`<div class="reply-content">
					<div class="reply-default-content">${reply.preply_content}</div>
					<div class="reply-content-box">
						<textarea class="form-control"></textarea>
						<button type="button" class="btn btn-primary reply-edit-btn" id="reply-edit-btn">수정</button>
						<button type="button" class="btn btn-secondary reply-edit-cancle-btn" id="reply-edit-cancle-btn">취소</button>
					</div>
				</div>` :
				`<div class="reply-content">
					<div class="reply-default-content">삭제된 댓글입니다.</div>
				</div>`;

			$('.reply-list').prepend(`
				<div class="reply-detail">
					<input type="hidden" name="preply_no" value="${reply.preply_no}" />
					<input type="hidden" name="preply_group" value="${reply.preply_group }">
					<input type="hidden" name="preply_indent" value="${reply.preply_indent}" />
					<input type="hidden" name="preply_level" value="${reply.preply_level}" />
					<div class="reply-detail-top">
						<div class="reply-writer">
							<input type="hidden" name="user_no" value="${reply.user_no}" />
							${userImg}
							<div class="reply-writer-detail">
								<p class="reply-user-name">${reply.user_name}</p>
								${writeDay}
								<i class="bi bi-trash-fill reply-delete-btn"></i>
								<i class="bi bi-pencil-square reply-edit-btn-icon"></i>
							</div>
						</div>
						<div class="reply-reply-write">
							<i class="bi bi-reply-fill"></i> 답글
						</div>
					</div>
					${preply_content}
					<div class="reply-reply-write-box">
						<textarea class="form-control reply-reply-write-area" placeholder="댓글을 입력하세요"></textarea>
						<button type="button"
							class="btn btn-primary reply-reply-write-btn">
							등록</button>
					</div>
				</div>
			`);


		}
	});
});

// 대댓글 / 대대댓글 쓰기
$('.reply-list').on('click', '.reply-reply-write-btn', function () {
	const preply_content = $(this).siblings().val();
	const pboard_no = $('#pboard_no').val();
	$(this).siblings().val('');
	const reply_reply_box = $(this).closest('.reply-detail').find('.reply-reply-box');
	if ($(reply_reply_box).length == 0) {
		$(this).closest('.reply-detail').append(`
			<div class="reply-reply-box">
				<p class="reply-reply-btn">답글 더보기</p>
				<div class="reply-reply-list">
				</div>
			</div>
		`);
	}
	const reply_reply_list = $(this).closest('.reply-detail').find('.reply-reply-list');

	$.ajax({
		type: "post",
		url: "boardProjectReplyReplyAdd",
		data: {
			preply_content: preply_content,
			preply_group: preply_group,
			pboard_no: pboard_no
		},
		dataType: "json",
		success: function (reply) {
			
			const viewCnt = Number($('.reply-count span').text());  
			$('.reply-count span').text(viewCnt + 1);

			const userImg = reply.user_profile == null ?
							`<img src="/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="rounded-circle">` :
							`<img src="/upload/userImg/${reply.user_profile}" alt="Profile" class="rounded-circle">`;

			const writeDay = reply.preply_update_date == null ?
							`<p class="reply-reply-write-day">${reply.preply_date}</p>` :
							`<p class="reply-reply-write-day">${reply.preply_update_date}</p>`;


			let preply_indent = reply.preply_indent == 0 ?
								`<div class="reply-reply-default-content">${reply.preply_content}</div>
								  <div class="reply-reply-content-box">
									<textarea class="form-control"></textarea>
									<button type="button" class="btn btn-primary reply-reply-edit-btn" id="reply-reply-edit-btn">수정</button>
									<button type="button" class="btn btn-secondary reply-reply-edit-cancle-btn" id="reply-reply-edit-cancle-btn">취소</button>
								  </div>` :
								`<span class="tag">${reply.preply_content.substring(0, reply.preply_content.indexOf(" "))}</span>
								<div class="reply-reply-default-content">${reply.preply_content.substring(reply.preply_content.indexOf(" "), reply.preply_content.length)}</div>
								<div class="reply-reply-content-box">
									<textarea class="form-control"></textarea>
									<button type="button" class="btn btn-primary reply-reply-edit-btn" id="reply-reply-edit-btn">수정</button>
									<button type="button" class="btn btn-secondary reply-reply-edit-cancle-btn" id="reply-reply-edit-cancle-btn">취소</button>
								</div>`;

			let preply_content = reply.preply_delete_chk == 0 ?
								preply_indent :
								`<div class="reply-reply-default-content">삭제된 댓글입니다.</div>`;

			$(reply_reply_list).append(`
				<div class="reply-reply-detail">
					<div class="reply-reply-detail-top">
						<input type="hidden" name="preply_no" value="${reply.preply_no}" />
						<input type="hidden" name="preply_group" value="${reply.preply_group}">
						<input type="hidden" name="preply_indent" value="${reply.preply_indent}" />
						<input type="hidden" name="preply_level" value="${reply.preply_level}" />
						<div class="reply-reply-writer">
							${userImg}
							<div class="reply-reply-writer-detail">
								<p class="reply-reply-user-name">${reply.user_name}</p>
								${writeDay}
								<i class="bi bi-trash-fill reply-reply-delete-btn"></i>
								<i class="bi bi-pencil-square reply-reply-edit-btn-icon"></i>
							</div>
						</div>
						<div class="reply-reply-reply-write">
							<i class="bi bi-reply-fill"></i> 답글
						</div>
					</div >
					<div class="reply-reply-content">
						${preply_content}
					</div>
				</div >
			`);
		}
	});
});

$('.reply-list').on('click', '.reply-delete-btn, .reply-reply-delete-btn', function() {
	let preply_no;
	let reply_detail;
	let reply_reply_detail;
	let preply_level;
	
	if($(this).hasClass('reply-delete-btn')) {
		reply_detail = $(this).closest('.reply-detail');
		preply_no = $(reply_detail).find('input[name=preply_no]').val();
		preply_level = $(reply_detail).find('input[name=preply_level]').val();
	} else {
		reply_detail = $(this).closest('.reply-detail');
		reply_reply_detail = $(this).closest('.reply-reply-detail');
		preply_no = $(reply_reply_detail).find('input[name=preply_no]').val();
		preply_level = $(reply_reply_detail).find('input[name=preply_level]').val();
	}
	
	$.ajax({
		type: "post",
		url: "boardProjectReplyDelete",
		data: {
			preply_no : preply_no,
			preply_level : preply_level
		},
		dataType: "text",
		success: function (response) {
			console.log(response);
			if(reply_reply_detail === undefined) {
				// 댓글 삭제인 경우
				if($(reply_detail).find('.reply-reply-box').length == 0) {
					$(reply_detail).remove();
				} else {
					$(reply_detail).find('.reply-content .reply-default-content').text('삭제된 댓글입니다.');
					$(reply_detail).find('.reply-delete-btn').remove();
					$(reply_detail).find('.reply-edit-btn-icon').remove();
				}
			} else {
				// 대댓글 삭제인 경우
				$(reply_reply_detail).remove();
				console.log($(reply_detail).find('.reply-default-content').text());
				if($(reply_detail).find('.reply-reply-detail').length == 0 && $(reply_detail).find('.reply-default-content').text() == '삭제된 댓글입니다.'){
					$(reply_detail).remove();
				} else if ($(reply_detail).find('.reply-reply-detail').length == 0) {
					$(reply_detail).find('.reply-reply-box').remove();
				}
			}
			
			const viewCnt = Number($('.reply-count span').text());  
			$('.reply-count span').text(viewCnt - 1);
		}
	});
});

$('#board-project-delete, #board-project-update').on('click', function () {  
	$(this).closest('form').submit();
})

$('.reply-list').on('click', '.reply-edit-btn-icon, .reply-reply-edit-btn-icon', function() {
	
	if($(this).hasClass('reply-edit-btn-icon')) {
		const reply_content_box = $(this).closest('.reply-detail').find('.reply-content-box');
		const reply_default_content = $(this).closest('.reply-detail').find('.reply-default-content');
		const new_reply_default_content = $(reply_default_content).html().replace(/<br\s*[\/]?>/gi, "\n");
		$(reply_content_box).find('textarea').val(new_reply_default_content);
		$(reply_content_box).css('display', 'flex');
		$(reply_default_content).css('display', 'none');
	} else {
		const reply_reply_content_box = $(this).closest('.reply-reply-detail').find('.reply-reply-content-box');
		const reply_reply_default_content = $(this).closest('.reply-reply-detail').find('.reply-reply-default-content');
		const new_reply_reply_default_content = $(reply_reply_default_content).html().replace(/<br\s*[\/]?>/gi, "\n");
		const tag = $(this).closest('.reply-reply-detail').find('.tag');
		if($(tag).length == 0) {
			$(reply_reply_content_box).find('textarea').val(new_reply_reply_default_content);
		} else {
			$(reply_reply_content_box).find('textarea').val(tag.text() + new_reply_reply_default_content);	
		}
		$(reply_reply_content_box).css('display', 'flex');
		$(reply_reply_default_content).css('display', 'none');
		$(tag).css('display', 'none');
	}
	
});

$(document).mousedown(function(event) {
	if($(event.target).closest('.reply-reply-content-box').length == 0 && $(event.target).closest('.reply-content-box').length == 0 && $(event.target).closest('.reply-edit-btn-icon').length == 0 && $(event.target).closest('.reply-reply-edit-btn-icon').length == 0) {
		$('.reply-reply-content-box, .reply-content-box').css('display', 'none');
		$('.reply-default-content, .tag, .reply-reply-default-content').css('display', 'block');
	}
})

$('.reply-list').on('click', '.reply-edit-cancle-btn', function() {
	$(this).closest('.reply-content-box').css('display', 'none');
	$(this).closest('.reply-content').find('.reply-default-content').css('display', 'block');
});

$('.reply-list').on('click', '.reply-edit-btn', function() {
	const reply_content = $(this).closest('.reply-content');
	const reply_content_box = $(this).closest('.reply-content-box');
	const preply_no = $(this).closest('.reply-detail').find('input[name=preply_no]').val();
	const preply_content = $(this).closest('.reply-content-box').find('textarea').val();
	
	$.ajax({
		type: "post",
		url: "boardProjectReplyEdit",
		data: {
			preply_no : preply_no,
			preply_content : preply_content
		},
		dataType: "json",
		success: function (reply) {
			$(reply_content).find('.reply-default-content').html(reply.preply_content);
			$(reply_content).find('.reply-default-content').css('display', 'block');
			$(reply_content_box).css('display', 'none');
		}
	});
	
 });
 
$('.reply-list').on('click', '.reply-reply-edit-cancle-btn', function() {
	$(this).closest('.reply-reply-content-box').css('display', 'none');
	$(this).closest('.reply-reply-content').find('.reply-reply-default-content').css('display', 'block');
	$(this).closest('.reply-reply-content').find('.tag').css('display', 'block');
});

$('.reply-list').on('click', '.reply-reply-edit-btn', function() {
	const reply_reply_detail = $(this).closest('.reply-reply-detail');
	const reply_reply_content = $(this).closest('.reply-reply-content');
	const reply_reply_content_box = $(this).closest('.reply-reply-content-box');
	const preply_no = $(this).closest('.reply-reply-detail').find('input[name=preply_no]').val();
	const preply_content = $(this).closest('.reply-reply-content-box').find('textarea').val();
	const preply_group = $(this).closest('.reply-reply-detail').find('input[name=preply_group]').val();
	const tag = $(this).closest('.reply-reply-content').find('.tag');
	if($(tag).length == 0) {
		$(reply_reply_content).prepend(`
			<span class="tag"></span>
		`);
	}
	
	$.ajax({
		type: "post",
		url: "boardProjectReplyReplyEdit",
		data: {
			preply_no : preply_no,
			preply_content : preply_content,
			preply_group: preply_group
		},
		dataType: "json",
		success: function (reply) {
			if(reply.preply_indent == 0) {
				$(reply_reply_detail).find('.tag').remove();
				$(reply_reply_detail).find('.reply-reply-default-content').html(reply.preply_content);
			} else {
				$(reply_reply_detail).find('.tag').text(reply.preply_content.substring(0, reply.preply_content.indexOf(" ")));
				$(reply_reply_detail).find('.reply-reply-default-content').html(reply.preply_content.substring(reply.preply_content.indexOf(" "), reply.preply_content.length));
			}
			
			$(reply_reply_content).find('.reply-reply-default-content').css('display', 'block');
			$(reply_reply_content).find('.tag').css('display', 'block');
			$(reply_reply_content_box).css('display', 'none');
		}
	});
	
 });