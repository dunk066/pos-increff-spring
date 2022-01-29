// get url
function getBrandUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/brand";
}


//BUTTON ACTIONS
function searchBrand(event){
	//Set the values to add
	var $tbody = $('#brand-table').find('tbody');
	$tbody.empty();
	var $form = $("#brand-form");
	var json = toJson($form);
	var url = getBrandUrl()+"/search";
	// call api
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
	   		displayBrandList(response);  
	   	},
	   	error: handleAjaxError
	   });

	return false;
}

//BUTTON ACTIONS
function addBrand(event){
	//Set the values to add
	
	var $form = $("#brand-add-form");
	var json = toJson($form);
	var url = getBrandUrl();
	// call api
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#add-brand-modal').modal('toggle');
			$('#brand-add-form').trigger("reset");
	   		$.notify("Brand added successfully !!","success");
	   		searchBrand();
	   		document.getElementById("download-errors-brand").disabled = true;
	   	},
	   	error: handleAjaxError
	   });

	return false;
}

function updateBrand(event){
	
	//Get the ID
	var id = $("#brand-edit-form input[name=id]").val();	
	var url = getBrandUrl() + "/" + id;

	//Set the values to update
	var $form = $("#brand-edit-form");
	var json = toJson($form);
	// call api
	$.ajax({
		url: url,
		type: 'PUT',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#edit-brand-modal').modal('toggle');
	   		$.notify("Brand updated successfully !!","success");
	   		searchBrand();
	   	},
	   	error: handleAjaxError
	   });

	return false;
}


function getBrandList(){
	var url = getBrandUrl();
	// call api
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
	   		// display data

	   		displayBrandList(data);  
	   	},
	   	error: handleAjaxError
	   });
}

// FILE UPLOAD METHODS
var fileData = [];
var errorData = [];
var processCount = 0;


function processDataBrand(){
	var file = $('#brandFile')[0].files[0];
	readFileData(file, readFileDataCallbackBrand);
}

function readFileDataCallbackBrand(results){
	fileData = results.data;
	// check no of rows
	if(fileData.length > 5000)
	{
		$.notify("File Contains more than 5000 rows !!","error");
		return;
	}
	uploadRowsBrand();
}

function uploadRowsBrand(){
	//Update progress
	updateUploadDialogBrand();
	//If everything processed then return
	if(processCount==fileData.length){
		$.notify("File processed successfully !!","success");
	   	searchBrand();
		return;
	}
	
	//Process next row
	var row = fileData[processCount];
	processCount++;
	
	var json = JSON.stringify(row);
	var url = getBrandUrl();

	//Make ajax call
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			uploadRowsBrand();  
		},
		error: function(response){
			row.error=response.responseText
			errorData.push(row);
			uploadRowsBrand();
		}
	});

}

//var $button = $('#download-errors-brand');
//$button.disabled = true;
document.getElementById("download-errors-brand").disabled = true;

function downloadErrorsBrand(){
	writeFileData(errorData);
    document.getElementById("download-errors-brand").disabled = true;
}

//UI DISPLAY METHODS

function displayBrandList(data){
	var $tbody = $('#brand-table').find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
		// dynamic buttons
		var buttonHtml = ' <button class="btn btn-outline-success" onclick="displayEditBrand(' + e.id + ')">Edit</button>'
		var row = '<tr>'
		+ '<td>' + e.brand + '</td>'
		+ '<td>'  + e.category + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function displayEditBrand(id){
	var url = getBrandUrl() + "/" + id;
	// call api
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
	   		// display brand for update
	   		displayBrand(data);   
	   	},
	   	error: handleAjaxError
	   });	
}

function resetUploadDialogBrand(){
	//Reset file name
	document.getElementById("download-errors-brand").disabled = true;
	var $file = $('#brandFile');
	$file.val('');
	$('#brandFileName').html("Choose File");
	//Reset various counts
	processCount = 0;
	fileData = [];
	errorData = [];
	//Update counts	
	updateUploadDialogBrand();

}

function updateUploadDialogBrand(){
	// update counts
	$('#rowCountBrand').html("" + fileData.length);
	$('#processCountBrand').html("" + processCount);
	$('#errorCountBrand').html("" + errorData.length);
	if(errorData.length != 0) document.getElementById("download-errors-brand").disabled = false;
}

function updateFileNameBrand(){
	var $file = $('#brandFile');
	var fileName = $file.val();
	$('#brandFileName').html(fileName);
}

function displayUploadDataBrand(){
	resetUploadDialogBrand(); 	
	$('#upload-brand-modal').modal('toggle');
}

function displayBrand(data){
	// fill entries
	$("#brand-edit-form input[name=brand]").val(data.brand);	
	$("#brand-edit-form input[name=category]").val(data.category);	
	$("#brand-edit-form input[name=id]").val(data.id);	
	$('#edit-brand-modal').modal('toggle');
}

function showAddBrandModal(){
	$('#add-brand-modal').modal('toggle');
	$('#brand-add-form').trigger("reset");
}
//INITIALIZATION CODE
function init(){
	$('#show-add-brand-modal').click(showAddBrandModal);
	$('#search-brand').click(searchBrand);
	$('#upload-brand-data').click(displayUploadDataBrand);
	$('#process-data-brand').click(processDataBrand);
	$('#download-errors-brand').click(downloadErrorsBrand);
	$('#brandFile').on('change', updateFileNameBrand);
}

$(document).ready(init);
$(document).ready(getBrandList);