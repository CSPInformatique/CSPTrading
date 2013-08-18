calculateBuyableQuantity = function(){
	if(validateInputs($(".buyableQuantityCalculator input"))){
		var investmentAmount = $(".buyableQuantityCalculator .investmentAmount input").val();
		var price = $(".buyableQuantityCalculator .price input").val();
		
		$.ajax({
			type: "GET",
			url:	"tools" +
					"?calculateBuyableQuantity" +
					"&investmentAmount=" + investmentAmount +
					"&price=" + price,
		}).done(function(data) {
			$(".buyableQuantityCalculator .quantity input").val(data);
		});
	}else{
		$(".buyableQuantityCalculator .quantity input").val("");
	}
};

calculateInvestment = function(){	
	if(validateInputs($(".buyableQuantityCalculator input"))){
		var brokerFees = $(".investmentCalculator .brokerFees input").val();
		var quantity = $(".investmentCalculator .quantity input").val();
		var buyPrice = $(".investmentCalculator .buyPrice input").val();
		var sellPrice = $(".investmentCalculator .sellPrice input").val();
		
		$.ajax({
			type: "GET",
			url:	"tools" +
					"?calculateInvestment" +
					"&brokerFees=" + brokerFees +
					"&quantity=" + quantity +
					"&buyPrice=" + buyPrice +
					"&sellPrice=" + sellPrice,
		}).done(function(data) {
			$(".investmentCalculator .investmentResult input").val(data);
		});
	}else{
		$(".investmentCalculator .investmentResult input").val("");
	}
};

validateInputs = function(inputs){
	var submit = true;
	inputs.each(function(){
		if($(this).attr("disabled") != "disabled"){
			if($(this).val() == ""){
				// Validate content.
				submit = false;
			}
		}
	});
	
	return submit;
};