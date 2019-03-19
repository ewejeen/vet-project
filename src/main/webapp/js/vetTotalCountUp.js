jQuery(document).ready(function($) {
	$('.counter').counterUp({
		delay : 10, // 숫자당 돌아가는 속도를 지연시켜주는 요소, 숫자가 높을 수록 돌아가는 속도가 느려짐.
		time : 1500
	// 카운트업 해주는 애니메이션의 전체 지속시간 (1000==1초).
	});
});