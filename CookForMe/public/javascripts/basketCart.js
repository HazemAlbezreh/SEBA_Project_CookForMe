function openBasketCart(){
	
	console.log('openbasket');
	
	basketCartOpenCloseCheck();
		
	
	
	
	return false;
}

function basketCartOpenCloseCheck(){
	var e = document.getElementById('basketCartDIV_12');
   	if(e.style.display == 'block')
   		e.style.display = 'none';
   	else{
   		
   		loadItemsInCart();
   		
   		e.style.display = 'block';
   		
   		
   	}
   		
   	
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



function loadItemsInCart()
{
	
	var totalPrice = 0;
	
	$.ajax({
	        url: "/items/getAll",
	        type: 'POST',
	        contentType: 'application/xml',
	        dataType: 'xml',
	        data: {},
	        async: false,
	        success: function (bItems)
	        {
	        	
	        	console.log("CHECK=="+new XMLSerializer().serializeToString(bItems.documentElement));
	        	
	        	//console.log('qty='+$(bItems).find('quantity').text());
	        	$("#basketCartDIV_14 ul").empty();
	        	
	        	$(bItems).find('item').each(function(){
	        		
	        		var name = $(this).find('name').text();
	        		var itemId = $(this).find('id').text();
	        		var qty = $(this).parent().find('quantity').text();
	        		console.log('qty='+qty);
	        		console.log('name='+name);
	        		var price = $(this).parent().find('basketItemPrice').text();
	        		console.log('price='+price);
	        		console.log('itemId='+itemId);
	        		totalPrice = parseInt(totalPrice) + parseInt(price);

	        		//var imageSrc = @{Meals.renderImage('+itemId+')};
	        		//<img src="@{Meals.renderImage('+itemId+')}"'+'>
	        		$("#basketCartDIV_14 ul").append('<li id="basketCartLI_22"> <div id="basketCartDIV_23"> <span id="basketCartSPAN_24">'+'$'+price+'</span><span id="basketCartSPAN_26">'+name+'</span> <span id="basketCartSPAN_27">QTY:'+ qty +' Plates</span> <span id="basketCartSPAN_28"> <a href="#" onClick="return removeItemFromCart('+itemId+');" id="basketCartA_29">remove</a></span> </div> </li>');
	        		

	            });
	        	
	        }
	   });
	totalPrice = parseInt(totalPrice);
	console.log('totalPrice='+parseInt(totalPrice));
	$("#basketCartSPAN_42").text("Subtotal"+ "  $"+totalPrice);
			
}





function addItemToBasketCart(){
	
	//var currentRowID = item;	
	
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
	
	
	loadItemsInCart();
	
	
	return false;
	
}

function addItem(itemid) {	
	console.log('additem was clicked'+itemid);

	
	$.ajax({
        type: "POST",
        url: "/items/add/"+itemid,
        //data: {newname: newname}, 
		     data: {},
		     contentType: "text/xml",
		     dataType: 'xml', 
		     async: false,
		     success: function(json) {
		    	console.log('result='+json);
		    	//tmpl('basketItems_tmpl',  also put bracket at end
//	   			$('#BasketItemsTemplateOutput').html({basketItems: json});
//  	   		$('#basketTbl #priceCell').each(function() {
//  	   			$(this).formatCurrency({region: 'en-GB'});
//        	 });
  	   			  	   		
  	   		//addItemToBasketCart(itemid);
  	   		
		     },
		     error: function(json) {
	   		     alert('fail: ' + json);
	   			
		     } 
		 });	 
		 
	 return false; 
	}	

function removeItem(basketItemId) {
	
	var totalPrice = 0;
	
	$.ajax({
        type: "POST",
        url: "/items/remove/"+basketItemId,
		     data: {},
		     contentType: "text/xml",
		     dataType: "xml",
		     async: false,
		    success: function(bItems) {
//	   			$('#BasketItemsTemplateOutput').html(tmpl('basketItems_tmpl', {basketItems: json}));
//	   			$('#basketTbl #priceCell').each(function() {
//  	   			$(this).formatCurrency({region: 'en-GB'});
//    	 	});
		    	
		    	
		    	
		    	$("#basketCartDIV_14 ul").empty();
	        	
	        	$(bItems).find('item').each(function(){
	        		
	        		var name = $(this).find('name').text();
	        		var itemId = $(this).find('id').text();
	        		var qty = $(this).parent().find('quantity').text();
	        		console.log('qty='+qty);
	        		console.log('name='+name);
	        		var price = $(this).parent().find('basketItemPrice').text();
	        		console.log('price='+price);
	        		console.log('itemId='+itemId);
	        		totalPrice = parseInt(totalPrice) + parseInt(price);

	        		//var imageSrc = @{Meals.renderImage('+itemId+')};
	        		//<img src="@{Meals.renderImage('+itemId+')}"'+'>
	        		$("#basketCartDIV_14 ul").append('<li id="basketCartLI_22"> <div id="basketCartDIV_23"> <span id="basketCartSPAN_24">'+'$'+price+'</span><span id="basketCartSPAN_26">'+name+'</span> <span id="basketCartSPAN_27">QTY:'+ qty +' Plates</span> <span id="basketCartSPAN_28"> <a href="#" onClick="return removeItemFromCart('+itemId+');" id="basketCartA_29">remove</a></span> </div> </li>');
	        		
	        		
	            });
	        	
	        	$("#basketCartSPAN_42").text("Subtotal"+ "  $"+totalPrice);
	        	
	        	getTotalItemCount();
		    	
	        	$("#basketCartSPAN_11").text(cartCount);
		    	
		    	
		     },
		     
		 });
	 return false; 
}

function removeItemFromCart(itemId){
	
	console.log('removeItemFromCart='+itemId);
	removeItem(itemId);
	return false;
	//loadItemsInCart();
}


function addItemsInReviewOrder(){
	
	console.log('addItemsInReviewOrder');
	
	
}



