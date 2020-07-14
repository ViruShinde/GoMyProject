
 //to load nav bar
$(function(){
	  $("#nav-placeholder").load("Nav");
});

jQuery(document).ready(function($) {

	// to get the client list in drop down.
	jQuery("#client").autocomplete({
		source : "\clientList",
		minLength : 0,
		scroll : true,
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#client").val(ui.item.label);
			//console.log(this.value);
			//console.log(ui.item);
			$("#clientId").val(ui.item.value);
			//alert("INSID");
			jQuery.getJSON("getFundsFromClient", {
				p1 : ui.item.label
			}, function(responseData) {
				console.log(responseData);
				//var clientFunds = jQuery('#clientFunds');
				jQuery('#funds').empty();
				var clientFunds = jQuery('#funds');
				jQuery.each(responseData, function(key, value) {
					console.log(value.label + " >> "+value.value);										
					jQuery('<option id =' +value.value+'>').val(value.value)
            	    .text(value.label).appendTo(clientFunds);			
				});

			});
			return false;
		}
	});
	
	//label	value
			
	$("#funds").select2({
		  maximumSelectionLength: 2
	});

	$("#funds").select2({
		  placeholder: 'Select Funds',
		  allowClear: true
	});
	
	
	// its check if clien field is blank then blank clientId as well
	jQuery('#client').change(function(event) {		
		//let clientLength = $('#client').val().length;
		//console.log(clientLength);
		if( $('#client').val() == "" ){
			$("#clientId").val("");
		}	
	}); 
	
	
	jQuery.getJSON("riskAggregatorList", function(responseData) {
		//console.log(responseData);
		//var clientFunds = jQuery('#clientFunds');
		jQuery('#riskAggregator').empty();
		let ra =jQuery('#riskAggregator');
		jQuery('<option>').val(-1).text("Select RiskAggregator").appendTo(ra);
		jQuery.each(responseData, function(key, value) {
			//console.log(value.label + " >> "+value.value);				
			jQuery('<option id =' +value.value+'>').val(value.value)
    	    .text(value.label).appendTo(ra);			
		});

	});
});