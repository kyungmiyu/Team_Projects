// 페이징 << >> 버튼 더이상 쪽지 없을시 비활성화
document.addEventListener('DOMContentLoaded', function() {
	// 'disabled' 클래스를 가진 모든 페이지 링크에 대해 클릭 이벤트를 방지합니다.
	document.querySelectorAll('.pagination .disabled a').forEach(function(link) {
		link.addEventListener('click', function(event) {
			event.preventDefault();
		});
	});
	// 검색 기능
	function changeDropdownItem(value) {
		var dropdown = document.getElementById('dropdownSelect');
		dropdown.value = value;
	}

	var dropdown = document.getElementById('dropdownSelect');

	dropdown.addEventListener('click', function(event) {
		event.stopPropagation();
		dropdown.classList.toggle('active');
	});

	document.addEventListener('click', function(event) {
		if (!dropdown.contains(event.target)) {
				dropdown.classList.remove('active');
		}
	});
	
	
}); // 건들지마