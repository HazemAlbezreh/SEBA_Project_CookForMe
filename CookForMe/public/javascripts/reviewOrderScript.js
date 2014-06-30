//<script type="text/javascript">
//	$(document).ready(function () {
//		$("#review_INPUT76").click(function() {
//	
//			console.log('next clicked');
//		
//		});
//
//	});
//	
	function expirationMonthDropDown(){
		
		var month = $("#reviewSELECT_108 option:selected").text();
		
		console.log('month:'+month);
		
		$("#reviewSPAN_123").text(month);
		
		$("#reviewSPAN_122").css('display','block');

		
		$("#reviewSPAN_123").css('display','block');
	}
	
	
	function expirationYearDropDown(){
		
		var year = $("#reviewSELECT_125 option:selected").text();
		
		console.log('year:'+year);
		
		$("#reviewSPAN_141").text(year);
		
		$("#reviewSPAN_140").css('display','block');

		
		$("#reviewSPAN_141").css('display','block');
	}
		
	function addShippingDetails() {
		
		var hasErrors = false;
		$("#reviewDIV_72 ul").empty();
		
		var firstname = $("#reviewINPUT_35").val();
		
		if (firstname.trim()=="" || firstname==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>First name cant be blank</li>');
	    	$("#reviewINPUT_35").css('border-color','#ff1f1f');
	    }else{
	    	$("#reviewINPUT_35").css('border-color','#cfcfcf');
	    }
		
		var lastname = $("#reviewINPUT_37").val();
		
		if (lastname.trim()=="" || lastname==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>Last name cant be blank</li>');
	    	$("#reviewINPUT_37").css('border-color','#ff1f1f');
	    }else{
	    	$("#reviewINPUT_37").css('border-color','#cfcfcf');
	    }
		
		var add1 = $("#reviewINPUT_40").val();
		
		var add2 =  $("#reviewINPUT_43").val();
		
		if (add1.trim()=="" || add1==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>Address line 1 cant be blank</li>');
	    	$("#reviewINPUT_40").css('border-color','#ff1f1f');
	    }
		else if (add1.length<2){
			hasErrors = true;
			$("#reviewDIV_72 ul").append('<li>Address line 1 is too short (minimum is 2 characters)</li>');
			$("#reviewINPUT_40").css('border-color','#ff1f1f');
	    }
	    else{
	    	$("#reviewINPUT_40").css('border-color','#cfcfcf');
	    }
		
		var city =  $("#reviewINPUT_46").val();
		if (city.trim()=="" || city==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>City Name cant be blank</li>');
	    	$("#reviewINPUT_46").css('border-color','#ff1f1f');
	    }
		else if (city.toLowerCase()!="Munich".toLowerCase()){
			hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>City is not included in our List</li>');
	    	$("#reviewINPUT_46").css('border-color','#ff1f1f');
		}
	    else{
	    	$("#reviewINPUT_46").css('border-color','#cfcfcf');
	    }
				
		
		var zipcode =  $("#reviewINPUT_50").val();
		/* 80331-80999 - Munich*/
		
		if (zipcode.trim()=="" || zipcode==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>Zipcode cant be blank</li>');
	    	$("#reviewINPUT_50").css('border-color','#ff1f1f');
	    }
		else if (!(zipcode > 80331 && zipcode < 80999)){
			hasErrors = true;
			$("#reviewDIV_72 ul").append('<li>Zipcode not in area we serve {80331-80999}</li>');
			$("#reviewINPUT_50").css('border-color','#ff1f1f');
	    } 
		else{
	    	$("#reviewINPUT_50").css('border-color','#cfcfcf');
	    }
				
		var phone =  $("#reviewINPUT_53").val();
		
		var stripped = phone.replace(/[\(\)\.\-\ ]/g, ''); 
		
		if (phone.trim()=="" || phone==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>Phone number cant be blank</li>');
	    	$("#reviewINPUT_53").css('border-color','#ff1f1f');
	    }
		else if(isNaN(parseInt(stripped))) {
			hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>Phone number should only contain numbers</li>');
	    	$("#reviewINPUT_53").css('border-color','#ff1f1f');
			
		}
		else if (!(stripped.length == 10)) {
			hasErrors = true;
	    	$("#reviewDIV_72 ul").append('<li>Phone number should have 10 digits</li>');
	    	$("#reviewINPUT_53").css('border-color','#ff1f1f');
		}
		else{
	    	$("#reviewINPUT_53").css('border-color','#cfcfcf');
	    }
	
		if(hasErrors){
			$("#reviewDIV_72").css('display','block');
			return false;
		}
		
	
		//now summarizing shipping details in new layout
		$( "#reviewDIV_77" ).empty();
		
		var reviewShipping = '<div id="reviewShippingDIV_21"> <ol id="reviewShippingOL_22"> <li id="reviewShippingLI_23"> <div id="reviewShippingDIV_24"> <div id="reviewShippingDIV_1"> <div id="reviewShippingDIV_2"> <div id="reviewShippingDIV_3"> <div id="reviewShippingDIV_4"> <div id="reviewShippingDIV_5"> <i id="reviewShippingI_6"></i> <div id="reviewShippingDIV_7"> <strong id="reviewShippingSTRONG_8">'+firstname+' '+lastname+'</strong> </div> <div id="reviewShippingDIV_9">'+ add1 + '</div> <div id="reviewShippingDIV_10">'+ add2 +'</div> <div id="reviewShippingDIV_11">'+city+' '+ zipcode+' </div> </div> <div id="reviewShippingDIV_12"> <i id="reviewShippingI_13"></i> <strong id="reviewShippingSTRONG_14">'+phone+'</strong> </div> <div id="reviewShippingDIV_15"> <i id="reviewShippingI_16"></i> <div id="reviewShippingDIV_17"> <strong id="reviewShippingSTRONG_18">Delivery on Thursday, July 3</strong> </div> </div> </div> </div> <div id="reviewShippingDIV_19"> <a href="" onclick="return updateShipping();" id="reviewShippingA_20">Update Shipping Address</a> </div> </div> </div> </div> </li> </ol> </div>';			
  		    	
		
  	   	$('#reviewDIV_77').append(reviewShipping);
  	  	
  	   	$('#reviewDIV_77').css('display','block');   	   	
  	    $('#reviewFORM_25').css('display','none');

  	   
		//making billing visible
		$('#reviewSPAN_181').css('display','none');
		
  	    $('#reviewDIV_81').css({opacity:1.0});
  	 	$('#reviewH3_79').css({opacity:1.0}); 
  	 	
  	 	//serializing form data and sending async to controller	 	
  	 	
  	 	var shippingForm = $('#reviewFORM_25').serializeArray();
  	 	var shippingFormObject = {};
  	 	
  	 	$.each(shippingForm,
  	 	    function(i, v) {
  	 			shippingFormObject[v.name] = v.value;
  	 	});
  	 	
  	 	console.log(shippingFormObject);
	  	 
		$.ajax({
            type: 'POST',
            url: '/checkout/review/billing',
  		     data: JSON.stringify(shippingFormObject),//JSON.stringify($("#reviewFORM_25").serialize()),//JSON.stringify(arr),
  		     contentType: "application/json; charset=utf-8",
  		     dataType: 'json', 
  		     success: function(json) {
  		    	console.log('result='+json);
  		    	  		    	
  	   			//$('#reviewDIV_77').append(large);

  		     },
  		     error: function(json) {
  	   		     alert('fail: ' + json);
  	   			
  		     } 
  		 });
		
		 
		
		return false;
	}
	
	//function for credit card number restricting to inputting only numbers
	function isNumberKey(evt)
    {
		var charCode = (evt.which) ? evt.which : event.keyCode;
		if (charCode != 46 && charCode > 31 
				&& (charCode < 48 || charCode > 57) ){
			return false;
		}

		return true;
    }
	
	
	function isValidPhoneNumber(p) {
		  var phoneRe = /^[2-9]\d{2}[2-9]\d{2}\d{4}$/;
		  var digits = p.replace(/\D/g, "");
		  return (digits.match(phoneRe) !== null);
	}
	
	
	function addDeliveryInstructions(){
		var e = document.getElementById('reviewDIV_69');
       	if(e.style.display == 'block')
       		e.style.display = 'none';
       	else
       		e.style.display = 'block';
       	
       	return false;
		 		 
	}
	
	function updateShipping(){
		
		//making billing opaque again
  	    $('#reviewDIV_81').css({opacity:0.2});
  	 	$('#reviewH3_79').css({opacity:0.2}); 
		$('#reviewSPAN_181').css('display','block');

		
		$('#reviewFORM_25').css('display','block');
		$('#reviewDIV_77').css('display','none'); //hide summary of shipping
		
		//make review part invisible
		$('#reviewSPAN_213').css('display','block');
		
		$('#reviewDIV_185').css({opacity:0.2});
		$('#reviewH3_183').css({opacity:0.2});
		
		 
		return false;
	}
	
	
	function addBillingDetails() {
		
		console.log('in billing');
		var hasErrors = false;
		
		$("#reviewDIV_164 ul").empty();
		
		var firstname = $("#reviewINPUT_94").val();
				
		if (firstname.trim()=="" || firstname==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_164 ul").append('<li>First name cant be blank</li>');
	    }
				
		var lastname = $("#reviewINPUT_96").val();

		if (lastname.trim()=="" || lastname==null) { 
	    	hasErrors = true;
	    	$("#reviewDIV_164 ul").append('<li>Last name cant be blank</li>');
	    }
		
		console.log('in billing'+firstname+'-'+lastname);
		
		
		var ccnumber = $("#reviewINPUT_102").val();
		
		if(ccnumber=="" || ccnumber==null){
			hasErrors = true;
	    	$("#reviewDIV_164 ul").append('<li>Credit card number is required</li>');	    	
		}
		else if(!valid_credit_card(ccnumber)){
			hasErrors = true;
	    	$("#reviewDIV_164 ul").append('<li>Invalid credit card number</li>');
		}
		
		var ccv = $("#reviewINPUT_104").val();
		
		if(ccv=="" || ccv==null){
			hasErrors = true;
	    	$("#reviewDIV_164 ul").append('<li>Credit card cvv is required</li>');	    	
		}
		
		
		var year = $("#reviewSELECT_125 option:selected").text();
		
		if(year.trim()==""||year==null){
			hasErrors = true;
	    	$("#reviewDIV_164 ul").append('<li>Credit card exp year is required</li>')
		}
		
		var month = $("#reviewSELECT_108 option:selected").text();
		
		if(month.trim()==""||month==null){
			hasErrors = true;
	    	$("#reviewDIV_164 ul").append('<li>Credit card month year is required</li>')
		}
		
		if(hasErrors){
			$("#reviewDIV_164").css('display','block');
			return false;
		}
		
		
		//now summarizing shipping details in new layout
		$( "#reviewDIV_180" ).empty();
		
		var reviewBilling = '<div id="reviewBillingDIV_7"> <ol id="reviewBillingOL_8"> <li id="reviewBillingLI_9"> <div id="reviewBillingDIV_10"> <div id="reviewBillingDIV_1"> <div id="reviewBillingDIV_2"> <div id="reviewBillingDIV_3"> <div id="reviewBillingDIV_4"> Using card ending in:<br id="reviewBillingBR_5" />XXXX-XXXX-XXXX-'+ccnumber.substr(ccnumber.length - 4)+'</div> <a href="" onclick ="return updateBilling();" id="reviewBillingA_6">Update Card on File</a> </div> </div> </div> </div> </li> </ol> </div>';
  		    	
		
  	   	$('#reviewDIV_180').append(reviewBilling);
  	  	
  	   	$('#reviewDIV_180').css('display','block');   	   	
  	    $('#reviewFORM_85').css('display','none');

		
		
		//make review part visible
		$('#reviewSPAN_213').css('display','none');
		
		$('#reviewDIV_185').css({opacity:1.0});
		$('#reviewH3_183').css({opacity:1.0});
		
		return false;
	}
	
	function updateBilling(){

		//show billing form again
		$('#reviewFORM_85').css('display','block');
		$('#reviewDIV_180').css('display','none'); //hide summary of billing
		
		//make review part invisible
		$('#reviewSPAN_213').css('display','block');
		
		$('#reviewDIV_185').css({opacity:0.2});
		$('#reviewH3_183').css({opacity:0.2});
		
		
		return false;
	}
	
	
	function cancelEditBilling(){
		
		
		
		return false;
	}
	
	
	//luhn's algorithm to check for valid credit card numbers
	function valid_credit_card(value) {
		  // accept only digits, dashes or spaces
			if (/[^0-9-\s]+/.test(value)) return false;
		 
			// The Luhn Algorithm. It's so pretty.
			var nCheck = 0, nDigit = 0, bEven = false;
			value = value.replace(/\D/g, "");
		 
			for (var n = value.length - 1; n >= 0; n--) {
				var cDigit = value.charAt(n),
					  nDigit = parseInt(cDigit, 10);
		 
				if (bEven) {
					if ((nDigit *= 2) > 9) nDigit -= 9;
				}
		 
				nCheck += nDigit;
				bEven = !bEven;
			}
		 
			return (nCheck % 10) == 0;
	}
	
	
	
	function addItemsInReviewOrder(){
		
		console.log('addItemsInReviewOrder');
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
	        	
	        	//console.log("CHECK=="+new XMLSerializer().serializeToString(bItems.documentElement));
	        	
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
	        		$("#reviewUL_192 ul").append(
	 '<li id="reviewLI_193"> <div id="reviewDIV_194"> <span id="reviewSPAN_195">'+totalPrice+'</span><img src="//dusyefwqqyfwe.cloudfront.net/uploads/utility/image/file/3706/small_square__Spaghetti_with_Beet_Greens_hero.jpg" alt="Spaghetti with Beet Greens and Heirloom Cherry Tomatoes" id="reviewIMG_196" /> <span id="reviewSPAN_197">'+name+'</span> <span id="reviewSPAN_198">QTY: '+qty+'Plates</span> </div> </li>'); 
	        	
	        	
	        	});
	        	
	        }
	   });
		
		totalPrice = parseInt(totalPrice);
		console.log('totalPrice='+parseInt(totalPrice));
		$("#basketCartSPAN_42").text("Subtotal"+ "  $"+totalPrice);
		
		
		
	}
	
	
	