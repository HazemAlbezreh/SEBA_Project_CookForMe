function openBasketCart(){
	
	console.log('openbasket');
	
	basketCartOpenCloseCheck();
		
	return false;
}

function basketCartOpenCloseCheck(){
	var e = document.getElementById('basketCartDIV_12');
   	if(e.style.display == 'block')
   		e.style.display = 'none';
   	else
   		e.style.display = 'block';
   	
   	return false;
	 		 
}

var cartCount;

function getTotalItemCount(){
	
	$.ajax({
		url: "/items/count",
		type: 'POST',
		contentType: 'application/json;',
		data: {},
		success: function (count)
		{	
			console.log('getTotalItemCount='+count);
			cartCount = count;
			showCart(cartCount);
			return count;
		}
	});
}

function showCart(cartCount){
	
	console.log('showCart='+cartCount);
	
	if(cartCount!=0){
		
		var e = document.getElementById('basketCartSPAN_11');
		
		if(e!=null && e.style.display == 'block'){
			
		}else if(e!=null){
			//console.log('yaha1 for cart counter');
			$("#basketCartSPAN_11").css('display','block');
		}
		
   		$("#basketCartSPAN_11").text(cartCount);

   		var e = document.getElementById('checkoutButtonDIV_13');
   		console.log('proceedtobasket='+e);
		
   		if(e!=null && e.style.display == 'block'){
			//console.log('yaha2 BLOCK for button proceed');
		}else if (e!=null){
			console.log('yaha2 for button proceed');
	   		$("#checkoutButtonDIV_13").css('display','block');
		}
   		
	}else{
		
		var e = document.getElementById('basketCartSPAN_11');
	   	
		if(e.style.display == 'block')
	   		$("#basketCartSPAN_11").css('display','none');
		
		var e = document.getElementById('checkoutButtonDIV_13');
	   	
		if(e.style.display == 'block'){
	   		$("#checkoutButtonDIV_13").css('display','none');
		}
	}	
	
}

function addItemToBasketCart(item){
	
	var currentRowID = item;	
	
	$.ajax({
        url: "/items/count",
        type: 'POST',
        contentType: 'application/json;',
        data: {},
        async: false,
        success: function (count)
        {
        	console.log('Count='+count);
            cartCount =  count;
            showCart(cartCount);
        }
   });
	
	var itemID = "#itemname" + item;
	
	console.log(itemID);
	console.log('here='+ $("#basketRow").find(itemID).html());
	
	return false;
	
}