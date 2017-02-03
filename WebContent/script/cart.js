$(document).ready(function(){

	var qty_update_show = function(){

		$(this).parents('tr').find('.qty-update-btn').show();
	}

	var update = function(){
		var id = $(this).parents('tr').attr('id');
		var qty = $('#'+id).find('.qty-input').val();
		location.assign("UpdateQty?id="+id+"&qty="+qty);

	}

	// var test = function(){
	// 	window.open("login.jsp",null,"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
	// }

	$(document).on('focus','.qty-input', qty_update_show);
	$(document).on('click','.qty-update-btn', update);
	// $(document).on('click','.wishlist-btn', test);
})