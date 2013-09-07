var handleErrors = function(model, response, options){
	$(".genericError-container .message").html(response.responseText);
	$(".genericError-container").modal();
};