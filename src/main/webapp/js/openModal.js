//Modal창 띄우기
$('#openModal').click(function() {
	$('#myModal').show();
});

$('#closeModal').click(function() {
	$('#myModal').hide();
});

// 모달 밖의 구역을 클릭하면 닫기
window.onclick = function(event) {
	var modal = document.getElementById('myModal');
	if (event.target == modal) {
		modal.style.display = 'none';
	}
}