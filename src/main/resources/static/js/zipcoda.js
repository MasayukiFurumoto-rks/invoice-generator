'use strict'

$(function(){
//郵便番号から住所自動入力
	$(document).on('click','#getAddress',function(){
		let zipcode = $('#zipcode').val();
		console.log(zipcode);
		$.ajax({
		url: 'http://zipcoda.net/api',
		dataType: 'jsonp',
		data: {
			zipcode: zipcode
		},
		async: true
	}).done(function(data) {
		// 検索成功時にはページに結果を反映
		// コンソールに取得データを表示
		let displayAddress = data.items[0].pref + data.items[0].address  
		
		$('#address').val(displayAddress);
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		// 検索失敗時には、その旨をダイアログ表示
		console.log('XMLHttpRequest : ' + XMLHttpRequest.status);
		console.log('textStatus     : ' + textStatus);
		console.log('errorThrown    : ' + errorThrown.message);
		});
	});
});