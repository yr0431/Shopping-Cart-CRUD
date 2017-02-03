$(document).ready(function(){

	$('.new-wishlist-btn').on('click', function(){
		$('.create-form').show();
		$('.new-wishlist-btn').hide();
	});
	$('.cancel-btn').on('click',function(){
		console.log('test');
		$('.create-form').hide();
		$('.new-wishlist-btn').show();
	})
});