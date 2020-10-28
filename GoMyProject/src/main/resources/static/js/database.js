var map = new Object();
var selectedDb="";
jQuery(document).ready(function($){
	
	$(".nav-tabs a").click(function(){
		$(this).tab('show');
	});
	
	jQuery('#server').change(
			function(){	
				showLoading();
				jQuery('#databaseId').empty();
				let serverId = jQuery('#server').val();				
				if(serverId != -1){							
				jQuery.getJSON("databaseList", {
					p1 : serverId					
				}, function(responseData) {
					console.log(responseData);
					let databselist = document.getElementById("databaseId");
					let parentUl=document.createElement("ul");
					databselist.appendChild(parentUl);
					parentUl.classList.add("parent_list");
					parentUl.setAttribute("style","list-style-type:none");
					//style="list-style-type:none;
					//parentUl.classList.add("collapse");
					jQuery.each(responseData, function(index, value) {
						console.log( " >> "+value);
						let t ='fa';
						let classValue='clickable';
						jQuery("<li id =" +value+" class=active value=" +value+" > <a class="+classValue+" value=" +value+" >"+value+" <span class="+t+"></span> </a>" +
								//"<span class="+t+" />" +
								"</li>").appendTo(parentUl);   
						$('.'+classValue).addClass("panel-collapsed");
						$('.fa').addClass("fa-caret-down");
					});					
					createTablesList();	
					hideLoading();				
					});
				}									
			});
 	
	
$(document).on("click", ".tables", function() {
//$(.database).unbind().click(function(){
	showLoading();
    let selectedTable = $(this).attr('value');
    jQuery.getJSON("tabledesc", {
    	p1 : selectedTable
    }, function(responseData) {
    	console.log(responseData);
    	let databselist = document.getElementById("tableDesc");
    	let parentUl=document.createElement("ul");
    	databselist.appendChild(parentUl);
    	parentUl.classList.add("parent_list");
    	jQuery.each(responseData, function(index, value) {
    		console.log( " >> "+value);
    		jQuery('<li id =' +value+' class=database value =' +value+ ' >').text(value).appendTo(parentUl);                
    	});
    });
    hideLoading();
});

$("#submit").click(function(){	
	jQuery('#myGrid').empty();	
	let database = jQuery('#database').val();
	let serverId = jQuery('#server').val();	
	let env = jQuery('#env').val();
	let query=$("textarea#query").val();
	
	console.log(" query :  "+query);
	confirm(selectedDb+ " Are you sure ? "+query);
	showLoading();
    jQuery.getJSON("getresult", {
    	p1 : database,
    	p2 : serverId,
    	p3 : env,
    	p4 : query,
    	p5 : selectedDb
    }, function(responseData) {
    	console.log("********** "+responseData);
    	createGrid(responseData)
    	/*let databselist = document.getElementById("display");
    	let parentUl=document.createElement("ul");
    	databselist.appendChild(parentUl);
    	parentUl.classList.add("parent_list");*/
    	jQuery.each(responseData, function(index, value) {
    		console.log( " >> "+value);
    		//jQuery('<li id =' +value+' class=database value =' +value+ ' >').text(value).appendTo(parentUl);                
    	});
    	hideLoading();
    });
    
});
 
});

$(".confirmLink").click(function(e) {
    e.preventDefault();
    var targetUrl = $(this).attr("href");

    $("#dialog").dialog({
      buttons : {
        "Confirm" : function() {
          window.location.href = targetUrl;
        },
        "Cancel" : function() {
          $(this).dialog("close");
        }
      }
    });
    $("#dialog").dialog("open");          
});

function createTablesList(){	
	$('.active a.clickable').each( function(){
		$(this).click(function(){			
			selectedDb = $(this).attr('value');
			//console.log(">>>clicked on >>>"+selectedDb) ;  
			let val=document.getElementById(selectedDb).getAttribute('value');    
			console.log(val+">>>clicked on >>>"+selectedDb) ;  	    
			$('.clickable').css('font-weight','normal');
				   	   	  	    
			if(map[selectedDb] == selectedDb){
				console.log("true 2nd timwe clicked");      	
			}else{	  
				let database = jQuery('#database').val();
				let serverId = jQuery('#server').val();	
				let env = jQuery('#env').val();
				showLoading();
				jQuery.getJSON("tablelist", {
					p1 : serverId,
			    	p2 : selectedDb
				}, function(responseData) {
					console.log(responseData);
					let parent = document.getElementById(selectedDb);
					let id="#"+selectedDb+" li";
					let parentUl=document.createElement("ul");
					parentUl.setAttribute("id",id);
					parentUl.classList.add("child_list");				
					parent.appendChild(parentUl);
					
					jQuery.each(responseData, function(index, value) {
						console.log( " >> "+value);
	    				jQuery('<li id =' +value+'> <span class=font-weight-normal value=' +value+ ' </span> </li>').text(value).appendTo(parentUl); 
					});
					map[selectedDb]=selectedDb;
					hideLoading();
				});							
 			}
			

			if ($(this).hasClass('panel-collapsed')) {
				$(this).css('font-weight','bold');
		        console.log("expand the panel");
		        $(this).parents('.active').find('.child_list').slideDown();
		        $(this).removeClass('panel-collapsed');
		        $(this).parents('.active').find('span').removeClass('fa-caret-down').addClass('fa-caret-up');
		    }
		    else {
		    	$(this).css('font-weight','normal');
		    	console.log("collapse the panel");
		        $(this).parents('.active').find('.child_list').slideUp();
		        $(this).addClass('panel-collapsed');
		        $(this).parents('.active').find('span').removeClass('fa-caret-up').addClass('fa-caret-down');
		    }
			
			
		});
	});
	
}

function createGrid(responseData){
/*	
	console.log("Inside create grid");
	let header = JSON.stringify(responseData.header);
	let data = JSON.stringify(responseData.data)
	
	console.log(header);
	console.log("-----");
	console.log("error @@@@@@@@@@ "+responseData.error +">>"+responseData.error!="");
		
*/	
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