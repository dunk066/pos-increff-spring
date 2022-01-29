// get url
function getProductUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/product";
}

//BUTTON ACTIONS
function searchProduct(event){
	var $tbody = $('#product-table').find('tbody');
	$tbody.empty();

	var $form = $("#product-form");
	var json = toJson($form);
	var url = getProductUrl()+"/search";
	// call api
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},
		success: function(response) {
	   		displayProductList(response);
	   	},
	   	error: handleAjaxError
	   });

	return false;
}

//BUTTON ACTIONS
function addProduct(){
	var mrp=$('#product-add-form input[name=mrp]').val();
	if(mrp<=0){
		$.notify("MRP cannot be negative or zero !!","error");
		return false;
	}
	var $form = $("#product-add-form");
	var json = toJson($form);
	var url = getProductUrl();
	// call api
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},
		success: function(response) {
			$('#add-product-modal').modal('toggle');
			$.notify("Product added successfully !!","success");
			searchProduct();
		},
		error: handleAjaxError
	});

	return false;
}

function updateProduct(){

	//Get the ID
	var id = $("#product-edit-form input[name=id]").val();
	var url = getProductUrl() + "/" + id;
	var mrp=$('#product-edit-form input[name=mrp]').val();
	if(mrp<=0){
		$.notify("MRP can not be negative or zero !!","error");
		return false;
	}
	//Set the values to update
	var $form = $("#product-edit-form");
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
			$('#edit-product-modal').modal('toggle');
			$.notify("Product updated successfully !!","success");
	   		searchProduct();
	   	},
	   	error: handleAjaxError
	   });

	return false;
}


function getProductList(){
	var url = getProductUrl();
	// call api
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
	   		// display data
	   		displayProductList(data);
	   	},
	   	error: handleAjaxError
	   });
}

// FILE UPLOAD METHODS
var fileData = [];
var errorData = [];
var processCount = 0;


function processDataProduct(){
	var file = $('#productFile')[0].files[0];
	readFileData(file, readFileDataCallbackProduct);
}

function readFileDataCallbackProduct(results){
	fileData = results.data;
	// check no of rows
	if(fileData.length > 5000)
	{
		$.notify("File Contains more than 5000 rows !!","error");
		return;
	}
	uploadRowsProduct();
}

function uploadRowsProduct(){
	//Update progress
	updateUploadDialogProduct();
	//If everything processed then return
	if(processCount==fileData.length){
		$.notify("File processed successfully !!","success");
	   	searchProduct();
		return;
	}

	//Process next row
	var row = fileData[processCount];
	processCount++;

	var json = JSON.stringify(row);
	var url = getProductUrl();

	//Make ajax call
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},
		success: function(response) {
			uploadRowsProduct();
		},
		error: function(response){
			row.error=response.responseText
			errorData.push(row);
			uploadRowsProduct();
		}
	});

}

function downloadErrorsProduct(){
	writeFileData(errorData);
}

//UI DISPLAY METHODS

function displayProductList(data){
	var $tbody = $('#product-table').find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
		var buttonHtml = ' <button class="btn btn-outline-success" onclick="displayEditProduct(' + e.id + ')">Edit</button>'
		var row = '<tr>'
		+ '<td>' + e.barcode + '</td>'
		+ '<td>' + e.brand + '</td>'
		+ '<td>'  + e.category + '</td>'
		+ '<td>' + e.name + '</td>'
		+ '<td>' + e.mrp + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function displayEditProduct(id){
	var url = getProductUrl() + "/" + id;
	// call api
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
	   		// display data
	   		displayProduct(data);
	   	},
	   	error: handleAjaxError
	   });
}

function resetUploadDialogProduct(){
	//Reset file name
	var $file = $('#productFile');
	$file.val('');
	$('#productFileName').html("Choose File");
	//Reset various counts
	processCount = 0;
	fileData = [];
	errorData = [];
	//Update counts
	updateUploadDialogProduct();
}

// update data
function updateUploadDialogProduct(){
	$('#rowCountProduct').html("" + fileData.length);
	$('#processCountProduct').html("" + processCount);
	$('#errorCountProduct').html("" + errorData.length);
}

function updateFileNameProduct(){
	var $file = $('#productFile');
	var fileName = $file.val();
	$('#productFileName').html(fileName);
}

function displayUploadDataProduct(){
	resetUploadDialogProduct();
	$('#upload-product-modal').modal('toggle');
}

// fill entries
function displayProduct(data){
	$("#product-edit-form input[name=brand]").val(data.brand);
	$("#product-edit-form input[name=category]").val(data.category);
	$("#product-edit-form input[name=name]").val(data.name);
	$("#product-edit-form input[name=mrp]").val(data.mrp);
	$("#product-edit-form input[name=id]").val(data.id);
	$('#edit-product-modal').modal('toggle');
}

function showAddProductModal(){
	$('#add-product-modal').modal('toggle');
}

//INITIALIZATION CODE
function init(){
	$('#show-add-product-modal').click(showAddProductModal);
	$('#search-product').click(searchProduct);
	$('#upload-product-data').click(displayUploadDataProduct);
	$('#process-data-product').click(processDataProduct);
	$('#download-errors-product').click(downloadErrorsProduct);
	$('#productFile').on('change', updateFileNameProduct);
}

$(document).ready(init);
$(document).ready(getProductList);