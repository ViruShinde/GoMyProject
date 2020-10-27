
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
	
	//let riskAggregatorId = jQuery("#riskAggregator").val();
	/*if(typeof(riskAggregatorId)  === "undefined") 
		riskAggregatorId =-1;*/
	
	
	jQuery('#client').empty();
	//getClientList(riskAggregatorId);
		
	
	jQuery('#riskAggregator').change(
			function(){	
				jQuery('#client').val("");
				$('#info').hide();
				let riskAggregatorId = jQuery("#riskAggregator").val();
				//console.log(riskAggregatorId);
				// to get the client list in drop down.								
				getClientList(riskAggregatorId);							
			});

	
	$("#funds").select2({
		  maximumSelectionLength: 2
	});

	$("#funds").select2({
		  placeholder: 'Select Funds',
		  allowClear: true
	});
	
	
	/*// its check if clien field is blank then blank clientId as well
	jQuery('#client').change(function(event) {		
		//let clientLength = $('#client').val().length;
		//console.log(clientLength);
		if( $('#client').val() == "" ){
			$("#clientId").val("");
		}	
	}); */
	
	
	jQuery.getJSON("/GoMyProject/riskAggregatorList", function(responseData) {
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


function getClientList(riskAggregatorId) {
	
	// to get the client list in drop down.
	//console.log(riskAggregatorId);
	jQuery("#client").autocomplete({
		source : "\clientList\?riskAggregatorId="+riskAggregatorId,
		minLength : 0,
		scroll : true,
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#client").val(ui.item.label);
			//console.log(this.value);
			//console.log(ui.item.label);
			//console.log(ui.item.value);
			$("#clientId").val(ui.item.value);
			jQuery.getJSON("getFundsFromClient", {
				p1 : ui.item.label
			}, function(responseData) {
				//console.log(responseData);
				//var clientFunds = jQuery('#clientFunds');
				jQuery('#funds').empty();
				var clientFunds = jQuery('#funds');
				jQuery.each(responseData, function(key, value) {
					//console.log(value.label + " >> "+value.value);										
					jQuery('<option id =' +value.value+'>').val(value.value)
            	    .text(value.label).appendTo(clientFunds);			
				});

			});
			return false;
		}
	});
}