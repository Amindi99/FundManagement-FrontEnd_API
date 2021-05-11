//------insert crud------------------------------------------------------------------------------------
$(document).on("click", "#btnSave", function(event){ 
	
	
// Clear alerts---------------------
	 $("#alertSuccess").text("Successfully Inserted"); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
 
// Form validation-------------------
	var status = validatefundForm(); 
	if (status != true) 
 	{ 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
 	} 
 	
// If valid------------------------
	var type = ($("#hidFundIdSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
 	{ 
		 url : "fundAPI", 
		 type : type, 
		 data : $("#formFund").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
 	{ 
 onfundSaveComplete(response.responseText, status); 
 } 
 });
});



// user input fields validation ===================================
	function validatefundForm(){

	// funder code
		if ($("#code").val().trim() == ""){
			return "Please enter funder ID.";
		}
	
	// funder name
		if ($("#name").val().trim() == ""){
			return "Please enter funder name.";
		}
	
	// funder price------------------------------------------
		if ($("#price").val().trim() == ""){
			return "Please enter funding price";
		}
		// check is it numerical value
		var tmpPrice = $("#price").val().trim();
		if (!$.isNumeric(tmpPrice)){
					
			return "Insert a numerical value for fund.";
		}
	// convert to decimal price
		$("#price").val(parseFloat(tmpPrice).toFixed(2));

	// funder location
		if ($("#location").val().trim() == ""){
			return "Please enter funder location";
		}
		
	return true;
}



function onfundSaveComplete(response, status)
	{ 
		if (status == "success") 
 		{ 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
 			{ 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
			} 
			else if (resultSet.status.trim() == "error") 
 			{ 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
			 } 
 				} else if (status == "error") 
					{ 
					 $("#alertError").text("Error while saving."); 
					 $("#alertError").show(); 
					 } else
 						{ 
						 $("#alertError").text("Unknown error while saving.."); 
						 $("#alertError").show(); 
						 } 
							$("#hidFundIdSave").val(""); 
 
						}



//------delete crud------------------------------------------------------------------------------------
		$(document).on("click", ".btnRemove", function(event) { 
		 $.ajax( 
		 	{ 
			 	url : "fundAPI", 
			 	type : "DELETE", 
			 	data : "code=" + $(this).data("code"),
			 	dataType : "text", 
			 	complete : function(response, status) { 
		 		onfundDeleteComplete(response.responseText, status); 
		 	} 
		}); 
})
		
		function onfundDeleteComplete(response, status){ 
		if (status == "success") { 
	 		var resultSet = JSON.parse(response); 
	 		if (resultSet.status.trim() == "success") { 
	 			$("#alertSuccess").text("Successfully deleted."); 
	 			$("#alertSuccess").show(); 
	 			$("#divItemsGrid").html(resultSet.data); 
	 		} else if (resultSet.status.trim() == "error") { 
	 			$("#alertError").text(resultSet.data); 
	 			$("#alertError").show(); 
	 		} 
	 	} else if (status == "error") { 
	 		$("#alertError").text("Error while deleting."); 
	 		$("#alertError").show(); 
	 	} else { 
	 		$("#alertError").text("Unknown error while deleting.."); 
	 		$("#alertError").show(); 
	 	} 
}



//------update crud------------------------------------------------------------------------------------
		$(document).on("click", ".btnUpdate", function(event)
		{ 
		 $("#hidFundIdSave").val($(this).data("code")); 
			 $("#code").val($(this).closest("tr").find('td:eq(0)').text()); 
			 $("#name").val($(this).closest("tr").find('td:eq(1)').text()); 
			 $("#price").val($(this).closest("tr").find('td:eq(2)').text()); 
			 $("#location").val($(this).closest("tr").find('td:eq(3)').text()); 
			 
		});