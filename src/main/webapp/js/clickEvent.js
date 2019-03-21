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
});

$('#rateName1').click(function() {
	$('#myChart').hide();
	$('.mapDiv').show();
	$('.rateName').css('font-weight', 'normal');
	$('#rateName1').css('font-weight', 'bold');
});
$('#rateName2').click(function() {
	$('#myChart').hide();
	$('.mapDiv').show();
	$('.rateName').css('font-weight', 'normal');
	$('#rateName2').css('font-weight', 'bold');
});
$('#rateName3').click(function() {
	$('#myChart').hide();
	$('.mapDiv').show();
	$('.rateName').css('font-weight', 'normal');
	$('#rateName3').css('font-weight', 'bold');
});
$('#rateName4').click(function() {
	$('#myChart').hide();
	$('.mapDiv').show();
	$('.rateName').css('font-weight', 'normal');
	$('#rateName4').css('font-weight', 'bold');
});
$('#rateName5').click(function() {
	$('#myChart').hide();
	$('.mapDiv').show();
	$('.rateName').css('font-weight', 'normal');
	$('#rateName5').css('font-weight', 'bold');
});

$('#retName1').click(function() {
	$('.chartLine').hide();
	$('.mapDiv2').show();
	$('.retName').css('font-weight', 'normal');
	$('#retName1').css('font-weight', 'bold');
});
$('#retName2').click(function() {
	$('.chartLine').hide();
	$('.mapDiv2').show();
	$('.retName').css('font-weight', 'normal');
	$('#retName2').css('font-weight', 'bold');
});
$('#retName3').click(function() {
	$('.chartLine').hide();
	$('.mapDiv2').show();
	$('.retName').css('font-weight', 'normal');
	$('#retName3').css('font-weight', 'bold');
});
$('#retName4').click(function() {
	$('.chartLine').hide();
	$('.mapDiv2').show();
	$('.retName').css('font-weight', 'normal');
	$('#retName4').css('font-weight', 'bold');
});
$('#retName5').click(function() {
	$('.chartLine').hide();
	$('.mapDiv2').show();
	$('.retName').css('font-weight', 'normal');
	$('#retName5').css('font-weight', 'bold');
});