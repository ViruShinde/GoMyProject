jQuery(document).ready(function($){
	
	
	jQuery.getJSON("/GoMyProject/riskAggregatorList", function(responseData) {
		//console.log(responseData);
		//var clientFunds = jQuery('#clientFunds');
		jQuery('#riskAggregatorId').empty();
		let ra =jQuery('#riskAggregatorId');
		jQuery('<option>').val("").text("Select RiskAggregator").appendTo(ra);
		jQuery.each(responseData, function(key, value) {
			//console.log(value.label + " >> "+value.value);				
			jQuery('<option id =' +value.value+'>').val(value.value)
    	    .text(value.label).appendTo(ra);			
		});

	});
	
	jQuery("#client").autocomplete({
		source : "\clientList",
		minLength : 0,
		scroll : true,
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#client").val(ui.item.label);
			//console.log(this.value);
			//console.log(ui.item.label);
			//console.log(ui.item.value);
			//$("#clientId").val(ui.item.value);
			jQuery.getJSON("getFundsFromClient", {
				p1 : ui.item.label
			}, function(responseData) {
				//console.log(responseData);
				//var clientFunds = jQuery('#clientFunds');
				jQuery('#fund').empty();								
				var clientFunds = jQuery('#fund');
				jQuery('<option>').val("").text("Select Fund").appendTo(clientFunds);
				jQuery.each(responseData, function(key, value) {
					//console.log(value.label + " >> "+value.value);										
					jQuery('<option id =' +value.label+'>').val(value.value)
            	    .text(value.label).appendTo(clientFunds);			
				});

			});
			return false;
		}
	});
 
 jQuery('#riskAggregatorId').change(
			function(){	
				jQuery('#client').val("");					
				let riskAggregatorId = jQuery("#riskAggregatorId").val();					
				// to get the client list in drop down.	
				//console.log("@@@@@@@@@ "+$("#riskAggregatorId :selected").text());
				jQuery('#riskAggregator').empty();
				let r = jQuery("#riskAggregatorId :selected").text();
				if (r == "Select RiskAggregator"){
					r="";
				}
				jQuery('#riskAggregator').val(r);			
				getClientList(riskAggregatorId);							
			});
		 		

// its check if clien field is blank then blank clientId as well
jQuery('#client').change(function(event) {		
	//let clientLength = $('#client').val().length;
	//console.log(clientLength);
	if( $('#client').val() == "" ){
		$("#clientId").val("");
	}	
});
		
jQuery('#frequencyId').change(function(event) {
	jQuery('#frequency').empty();
	let f = jQuery("#frequencyId :selected").text();
	if (f == "Select Frequency"){
		f="";
	}
	$("#frequency").val(f);
});

	
	$("#submit").click(function(){	
		jQuery('#myGrid').empty();	
		let riskAggregator = jQuery('#riskAggregator').val();
		let client = jQuery('#client').val();	
		let fund = jQuery('#fund').val();
		let frequency = jQuery("#frequency").val();
		let fromDate = jQuery("#fromDate").val();
		let toDate = jQuery("#toDate").val();
		
		console.log(" param :  "+riskAggregator +" "+ client +" "+ fund +" "+ frequency +" "+ fromDate +" "+ toDate);		
		showLoading();
	    jQuery.getJSON("getMisLog", {	
	    	p1 : riskAggregator,
	    	p2 : client,
	    	p3 : fund,
	    	p4 : frequency,
	    	p5 : fromDate,
	    	p6 : toDate
	    }, function(responseData) {
	    	console.log("********** "+responseData);
	    	createGrid(responseData)
	    	jQuery.each(responseData, function(index, value) {
	    		console.log( " >> "+value);
	    		//jQuery('<li id =' +value+' class=database value =' +value+ ' >').text(value).appendTo(parentUl);                
	    	});
	    	hideLoading();
	    });
	    
	});
	
});

function createGrid(responseData){	
		if(responseData.error.length == 0){
			console.log("Inside if");
			// let the grid know which columns and what data to use
			var gridOptions = {
				columnDefs: responseData.header,
				rowData: responseData.data,
				rowSelection: 'multiple',
				cellRendererParams: {
					checkbox: true
				}
			};
		    var gridDiv = document.querySelector('#myGrid');
		    new agGrid.Grid(gridDiv, gridOptions);
		}else{		
			$("#myGrid").html("<p class='alert alert-danger mb-12 col-12' > Error : "+responseData.error+"</p>");
		}
	}

	function showLoading(){
	 	 document.getElementById("loading").style.display='block';
	}

	function hideLoading(){
		document.getElementById("loading").style.display='none';
	}
	
	
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
				//$("#clientId").val(ui.item.value);
				jQuery.getJSON("getFundsFromClient", {
					p1 : ui.item.label
				}, function(responseData) {
					//console.log(responseData);
					//var clientFunds = jQuery('#clientFunds');
					jQuery('#fund').empty();								
					var clientFunds = jQuery('#fund');
					jQuery('<option>').val("").text("Select Fund").appendTo(clientFunds);
					jQuery.each(responseData, function(key, value) {
						//console.log(value.label + " >> "+value.value);										
						jQuery('<option id =' +value.label+'>').val(value.value)
	            	    .text(value.label).appendTo(clientFunds);			
					});

				});
				return false;
			}
		});
	}	