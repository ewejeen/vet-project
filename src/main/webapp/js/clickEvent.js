/* 통계 클릭 조건 설정 */
$('#st1').click(function() {
	$('.statistics.st1').show();
	$('.statistics.st2').hide();
	/*
	 * $('li#st2').hover(function(){ $('li#st2').css('font-weight','bold') },
	 * function(){ $('li#st2').css('font-weight','normal') });
	 */
	$('li#st1').css('font-weight', 'bold');
	$('li#st2').css('font-weight', 'normal');
	$('#backToChart2').css('display','none');
	
	$('.rateName').each(function(){
		$(this).css('font-weight', 'normal');
	});
});

$('#st2').click(function() {
	$('.statistics.st1').hide();
	$('.statistics.st2').show();
	/*
	 * $('li#st1').hover(function(){ $('li#st1').css('font-weight','bold') },
	 * function(){ $('li#st1').css('font-weight','normal') });
	 */
	$('li#st1').css('font-weight', 'normal');
	$('li#st2').css('font-weight', 'bold');
	$('#backToChart1').css('display','none');
	
});

// 반복문 클로저
for(var i=1;i<=5;i++){
	(function(j){
		$('#rateName'+j).click(function() {
			$('#myChart').hide();
			$('.mapDiv').show();
			$('#backToChart1').css('display','block');
			$('.rateName').css('font-weight', 'normal');
			$('#rateName'+j).css('font-weight', 'bold');
		});	
		
		$('#retName'+j).click(function() {
			$('.chartLine').hide();
			$('.mapDiv2').show();
			$('#backToChart2').css('display','block');
			$('.retName').css('font-weight', 'normal');
			$('#retName'+j).css('font-weight', 'bold');
		});	
	})(i);
}

$('#backToChart1').click(function(){
	$('.mapDiv').hide();
	$('#backToChart1').hide();
	$('#myChart').show();
});

$('#backToChart2').click(function(){
	$('.mapDiv2').hide();
	$('#backToChart2').hide();
	$('.chartLine').show();
});