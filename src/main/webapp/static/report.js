
function getBrandReportUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content");
	return baseUrl + "/api/brand-report";
}

function getInventoryReportUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content");
	return baseUrl + "/api/inventory-report";
}

function getSalesReportUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content");
	return baseUrl + "/api/sales-report";
}

function downloadBrandReport(){
	//Set the values to add
//	var $tbody = $('#brandreport-table').find('tbody');
//	$tbody.empty();
	var $form = $("#brandreport-form");
	var json = toJson($form);
	var url = getBrandReportUrl();
	// call api
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},
		success: function(response) {
	   		writeFileData(response);
	   	},
	   	error: handleAjaxError
	   });

	return false;
}

function downloadInventoryReport(){
	//Set the values to add
//	var $tbody = $('#brandreport-table').find('tbody');
//	$tbody.empty();
	var $form = $("#inventoryreport-form");
	var json = toJson($form);
	var url = getInventoryReportUrl();
	// call api
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},
		success: function(response) {
	   		writeFileData(response);
	   	},
	   	error: handleAjaxError
	   });

	return false;
}
function downloadSalesReport(){
    var startdate = $('#inputStartDate').val().trim();
	var enddate = $('#inputEndDate').val().trim();
	// validate dates
	if(startdate=="" && enddate!=""){
		$.notify("Enter both dates or none !!","error");
		return false;
	}

	if(startdate!="" && enddate==""){
		$.notify("Enter both dates or none !!","error");
		return false;
	}

	var $form = $("#salesreport-form");
	// form to json
	var json = toJson($form);
	var url = getSalesReportUrl();
	// call api
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},
		success: function(response) {
			// display report
			writeFileData(response);
		},
		error: function(response){
			handleAjaxError(response);
	   	}
	   });

	return false;
}


//INITIALIZATION CODE
function init(){
	$('#download-brandreport').click(downloadBrandReport);
	$('#download-inventoryreport').click(downloadInventoryReport);
	$('#inputStartDate').datepicker({
    		uiLibrary: 'bootstrap4',
    		showOnFocus: true,
    		showRightIcon: false,
    		format: 'dd-mm-yyyy',
    		maxDate: function () {
    			return $('#inputEndDate').val();
    		}
    });
    $('#inputEndDate').datepicker({
    		uiLibrary: 'bootstrap4',
    		showOnFocus: true,
    		showRightIcon: false,
    		format: 'dd-mm-yyyy',
    		minDate: function () {
    			return $('#inputStartDate').val();
    		}
    });
	$('#download-salesreport').click(downloadInventoryReport);
	$('#download-brandreport').click(downloadBrandReport);
	$('#download-inventoryreport').click(downloadInventoryReport);
}
$(document).ready(init);