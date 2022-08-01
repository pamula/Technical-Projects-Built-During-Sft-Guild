$(document).ready(function(){

//when ready for new purchase 
//cleares all the values choosen during purchase and 
//gets the page ready 
clearAllValues();	
loadItems();

purchase();



});
var totalUserAmountInput =0;

function loadItems(){
	clearItemsTable();

	var contentRows = $("#itemRowsWithCards");
	var quantityLeft = "Quantity Left : ";
	$.ajax({
		type:'GET',
		url:'http://vending.us-east-1.elasticbeanstalk.com/items',
		success: function(itemsArray){
			$.each(itemsArray, function(index,items){

				var itemId = items.id;
				var itemName = items.name;
				var itemPrice = items.price;
				var itemQuantity = items.quantity;
				var dollar = "$";


    var row = ' <div class="column"';
   
    row +=  '<br /> <a onclick ="itemNum('+ itemId +')" > ' + '<br />'+itemId +'<br />'+ itemName + ' <br />' +dollar + itemPrice + '<br />' +quantityLeft + itemQuantity +'</a>';

    row += '</div>';

    contentRows.append(row);
})
		},
		error: function(){

			$('#errorMessages').text('Error calling web service .Please try again');
			
		}
	});


}
	function clearItemsTable(){
		$('#itemRowsWithCards').empty();
	}
	function clearAllValues(){
		$('#message').val('');
		$('#itemsName').val('');
		$('#messageForBoughtOrNot').val('');
		$('#editInputMoney').val('');
	}

	


	$('#dollarButton').click(function (event) {
		totalUserAmountInput = +$('#editInputMoney').val();
		
		totalUserAmountInput += 1.00;
		$('#editInputMoney').val(totalUserAmountInput.toFixed(2));
});
console.log(totalUserAmountInput);

	$('#quarterButton').click(function (event) {
		totalUserAmountInput = +$('#editInputMoney').val();
		
		totalUserAmountInput += 0.25;
		$('#editInputMoney').val(totalUserAmountInput.toFixed(2));

	});



	$('#dimeButton').click(function (event) {
		totalUserAmountInput = +$('#editInputMoney').val();
		
		totalUserAmountInput += 0.10;
		$('#editInputMoney').val(totalUserAmountInput.toFixed(2));

	});


	$('#nickelButton').click(function (event) {
		totalUserAmountInput = +$('#editInputMoney').val();
		
		totalUserAmountInput += 0.05;
		$('#editInputMoney').val(totalUserAmountInput.toFixed(2));

	});

//here this method prints like 10quarters,4 nickles,2 dimes,60pennies


	$('#changeReturnButton').click(function(event){

		if($('#editInputMoney').val()==''){
			$('#message').val('NO Change');

			$('#itemsName').val('');
			$('#messageForBoughtOrNot').val('');
			return false;
		}

		totalUserAmountInput = + $('#editInputMoney').val();
		totalUserAmountInput = totalUserAmountInput *100;

//math.floor rounds down to nearest integer here
var quarters = Math.floor(totalUserAmountInput/25);
totalUserAmountInput = totalUserAmountInput-(quarters*25);

var dimes =Math.floor(totalUserAmountInput/10);
totalUserAmountInput = totalUserAmountInput-(dimes*10);

var nickles =Math.floor(totalUserAmountInput/5);

var pennies = totalUserAmountInput-(nickles*5);

messageToDisplay(quarters,dimes,nickles,pennies);
$('#messageForBoughtOrNot').val('');


});



//selects item id and displays on items box
function itemNum(id){
	$('#itemsBox').val(id);
}


//message about different coins and displays this msg on msg box

function messageToDisplay(quarters,dimes,nickles,pennies){
	var balance = '';

	if(quarters >0){
		if(quarters==1){
			balance += quarters + 'Quarter,';
		}else{
			balance += quarters + 'Quarters,';
		}
	}

	if(dimes >0){
		if(dimes==1){
			balance += dimes + 'Dime,';
		}else{
			balance += dimes + 'Dimes,';
		}
	}

	if(nickles >0){
		if(nickles==1){
			balance += nickles + 'Nickle,';
		}else{
			balance += nickles + 'Nickles,';
		}
	}

	if(pennies >0){
		if(pennies==1){
			balance += pennies + 'Penny,';
		}else{
			balance += pennies + 'Pennies,';
		}
	}

	$('#message').val(balance);
	$('#itemsBox').val('');
	$('#editInputMoney').val('');

}



function purchase(){

	$('#purchaseButton').click(function(){

		$('#messageForBoughtOrNot').val('');

		if($('#itemsBox').val()==''){
			$('#messageForBoughtOrNot').val('Select an Item Please');
			$('#message').val('');
			return false;
		}

//get current amount from total i/p box and item id
var id = $('#itemsBox').val();
totalUserAmountInput = +$('#editInputMoney').val();


$.ajax({

	type:'POST',
	url:'http://vending.us-east-1.elasticbeanstalk.com/money/'+ totalUserAmountInput +'/item/' + id,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json'
	},
	'dataType': 'json',
	success: function(data){

		messageToDisplay(data.quarters,data.dimes,data.nickles,data.pennies);
		$('#messageForBoughtOrNot').val('Thank You!!');
		loadItems();

	},
	error: function(message){
		var errorMessage = JSON.parse(message.responseText);
		$('#messageForBoughtOrNot').val(errorMessage.message);
	}

});
});
}




