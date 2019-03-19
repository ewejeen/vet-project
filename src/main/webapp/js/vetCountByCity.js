setTimeout(function() {
	$.ajax({
		url : 'getCountByCity.do',
		type : 'POST',
		data : {
			'position' : $('#address').text()
		},
		success : function(data) {
			$('#cntByCity').text(data);
		}
	});
}, 1000);