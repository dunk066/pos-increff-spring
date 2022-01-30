
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

			var config = {
            		quoteChar: '',
            		escapeChar: '',
            		delimiter: "\t"
            	};

            	var data = Papa.unparse(response, config);
                var blob = new Blob([data], {type: 'text/tsv;charset=utf-8;'});
                var fileUrl =  null;
                var currentdate = new Date();
                var brandreportname = "brand-report_"+ currentdate.getDate() + "/"
                                 	+ (currentdate.getMonth()+1)  + "/"
                                 	+ currentdate.getFullYear() + "@"
                                 	+ currentdate.getHours() + "h_"
                                 	+ currentdate.getMinutes() + "m_"
                                 	+ currentdate.getSeconds()+"s.tsv";
                if (navigator.msSaveBlob) {
                    fileUrl = navigator.msSaveBlob(blob, brandreportname);
                } else {
                    fileUrl = window.URL.createObjectURL(blob);
                }
                var tempLink = document.createElement('a');
                tempLink.href = fileUrl;
                tempLink.setAttribute('download', brandreportname);
                tempLink.click();
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

			var config = {
            		quoteChar: '',
            		escapeChar: '',
            		delimiter: "\t"
            	};

            	var data = Papa.unparse(response, config);
                var blob = new Blob([data], {type: 'text/tsv;charset=utf-8;'});
                var fileUrl =  null;
                var currentdate = new Date();
                var inventoryreportname = "inventory-report_"+ currentdate.getDate() + "/"
                                 	+ (currentdate.getMonth()+1)  + "/"
                                 	+ currentdate.getFullYear() + "@"
                                 	+ currentdate.getHours() + "h_"
                                 	+ currentdate.getMinutes() + "m_"
                                 	+ currentdate.getSeconds()+"s.tsv";
                if (navigator.msSaveBlob) {
                    fileUrl = navigator.msSaveBlob(blob, inventoryreportname);
                } else {
                    fileUrl = window.URL.createObjectURL(blob);
                }
                var tempLink = document.createElement('a');
                tempLink.href = fileUrl;
                tempLink.setAttribute('download', inventoryreportname);
                tempLink.click();
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
			var config = {
            		quoteChar: '',
            		escapeChar: '',
            		delimiter: "\t"
            	};

            	var data = Papa.unparse(response, config);
                var blob = new Blob([data], {type: 'text/tsv;charset=utf-8;'});
                var fileUrl =  null;
                var currentdate = new Date();
                var salesreportname = "sales-report_"+ currentdate.getDate() + "/"
                                 	+ (currentdate.getMonth()+1)  + "/"
                                 	+ currentdate.getFullYear() + "@"
                                 	+ currentdate.getHours() + "h_"
                                 	+ currentdate.getMinutes() + "m_"
                                 	+ currentdate.getSeconds()+"s.tsv";
                if (navigator.msSaveBlob) {
                    fileUrl = navigator.msSaveBlob(blob, salesreportname);
                } else {
                    fileUrl = window.URL.createObjectURL(blob);
                }
                var tempLink = document.createElement('a');
                tempLink.href = fileUrl;
                tempLink.setAttribute('download', salesreportname);
                tempLink.click();
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
	$('#download-salesreport').click(downloadSalesReport);
	$('#download-brandreport').click(downloadBrandReport);
	$('#download-inventoryreport').click(downloadInventoryReport);
}
$(document).ready(init);