jQuery(document).ready(function($) {
	
	var request = $("#request").val();
	
	var col=[""];
	createDatatTable(request,col);
	var selectedFields = [""];
	
	$("#add").click(function(){
		request = $("#request").val();
		selectedFields = getSelectedFields();         
        createDatatTable(request, selectedFields );
    });
	
	
	$("#select").on('click',function(){	    
	    
	    if (this.innerHTML === "Select All") {
	    	$('input:checkbox').each(function(){
	  	      var checked = $(this).is(':checked');
	  	       $(this).prop("checked", true)
	  	       //.val($(this).is(':checked'));
	  	    });
	    	this.innerHTML = "UnSelect All";
	      } else {
	    	  $('input:checkbox').each(function(){
	    	      var checked = $(this).is(':checked');
	    	       $(this).prop("checked", false)
	    	       //.val($(this).is(':checked'));
	    	    });
	    	  this.innerHTML = "Select All";
	      }
	    
	})
	
});

function getSelectedFields(){
	var selectedFields = [""];
	$.each($("input[name='sport']:checked"), function(){
		selectedFields.push($(this).val());
    });
	return selectedFields;
}

function createDatatTable(request, column ){
	
	console.log(column);
	$.ajax({
	    //"url": '/api/Test/', //for testing
		"url": '/GoMyProject/api/allBillingDetails/',
	    "data" : { req : request, columns : column },
	    "success": function (result) {
	    	//console.log("result >> " +result.columns);
	        var tableHeaders="";
	        $.each(result.columns, function (i, val) {
	            tableHeaders += "<th>" + val + "</th>";
	        });
       
	         $("#tableDiv").empty();
	         $("#tableDiv").append('<table id="displayTable" class="display"     cellspacing="0" width="100%"><thead><tr>' + tableHeaders + '</tr></thead></table>');

	         var columns = [];
	        jQuery.each(result.columns, function (i, value) {
	        	var obj = { data: value };
	            if(value.includes("Date")){
	            	obj = { data: value,
	            			type: 'datetime',	                       
	            			"render" : function (data) {	            				
	            				if(data!=null){
	            					var date = new Date(data);
		            		        var month = date.getMonth() + 1;
		            		        //return (month.toString().length > 1 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
		            		        return  date.getFullYear() + "-" + (month.toString().length > 1 ? month : "0" + month) +"-" + date.getDate();
	            				}else{
	            					return"";
	            				}
	            		        
	            		    }
	                        
	                       };
	            	//console.log("colm "+JSON.stringify(obj));
	            }else{
	            	obj = { data: value };
	            }
	            columns.push(obj);
	            //console.log(obj.data);
	            
	        });
	        
	        console.log(result.data);
	        
	        var table = $('#displayTable').DataTable({	 
	        	"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],	        	
	            data: result.data,
	            columns: columns,	            
	            dom: 'Blfrtip',	
	           // responsive: true, // to display additional columns in below row
	            scrollY : 400 ,
	            scrollX : 400 ,	            
	            buttons: [
	            	{
	                    text: 'Refresh',
	                    action: function ( e, dt, node, config ) {
	                        //alert( 'Button activated' );
	                        //$('#displayTable').DataTable().ajax.reload();
	                    	//firstRequest >> to refresh hole table
	                        createDatatTable("firstRequest", getSelectedFields() );
	                    }
	                },
	                {
	                    extend: 'excelHtml5',
	                    title: 'Data export'
	                },
	                {
	                    extend: 'pdfHtml5',
	                    title: 'Data export'
	                },
	                {
	                    extend: 'csvHtml5',
	                    title: 'Data export'
	                },
	                {
	                    extend: 'print',
	                    title: 'Data export'
	                }	                
	            ]	            
	        });
	        	        
	        // this is handelled in controller.
	        $("#request").val("secondRequest");
	        /* var table2 = $('#displayTable');
	        addColumn(table2, 'automationProcess'); */
	            
	        
	       
	        
	    },
	    "dataType": "json"
	});
	
	
	
	
}


//var favorite = [""];
//var favorite2 = [];


/*$.each($("input[name='sport']:checked"), function(){
    favorite.push($(this).val());
});*/

//console.log(favorite);
/* $.each($("input[name='sport']:not(:checked)"), function(){
    favorite2.push($(this).val());   
    let removed = $(this).val();
    favorite = $.grep(favorite, function(value) {
  	  return value != removed;
    });
}); */

     
// alert(favorite+ "  My favourite sports are: " + favorite.join(", "));
	//alert(">>"+ favorite2);


/*  var obj = { data : "automationProcess"};
columns.push(obj);

console.log(columns); 
data :	Blfrtip >> showing length menu 
data : Bfrtip  

"lengthMenu": [ [ 2, 5, 50, -1 ], [ '2 rows', '5 rows', '50 rows', 'Show all' ] ], 
*/
