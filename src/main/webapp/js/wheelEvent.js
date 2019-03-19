var win_h = $(window).height();

var $section = $('.main .section');
var $secSize = $section.length;
var $item = $('.item li');

window.onload = function() {
	$section.each(function(index) {
		$(this).attr('data-index', win_h * index);
	});

	$section.on('mousewheel', function(e) {
		var sectionPos = parseInt($(this).attr('data-index'));

		if (e.originalEvent.wheelDelta >= 0) {
			$('html,body').stop().animate({
				scrollTop : sectionPos - win_h
			});
			return false;
		} else if (e.originalEvent.wheelDelta < 0) {
			$('html,body').stop().animate({
				scrollTop : sectionPos + win_h
			});
			return false;
		}
	});

}

function fnMove(i) {
	var offset = $('#sec' + i).offset();
	$('html, body').stop().animate({
		scrollTop : offset.top
	});
}

var $idx = $(this).index();

$item.on('click', function() {
	var $idx = $(this).index();
	$item.eq($idx).addClass('on');
	$item.eq($idx).siblings().removeClass('on');
});

$(window).scroll(function(e) {
	var windowTop = $(window).scrollTop();
	for (var i = 0; i < $secSize; i++) {
		var secTop = $section.eq(i).offset().top;
		if (windowTop >= secTop) {
			// 마우스 휠로 section이동 했을 때 오른쪽 버튼에 on 클래스 추가 / 삭제
			$item.eq(i).addClass('on');
			$item.eq(i).siblings().removeClass('on');
		}
	}
});