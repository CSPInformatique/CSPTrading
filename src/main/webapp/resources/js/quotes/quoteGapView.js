window.QuoteGapMatrixView = Backbone.View.extend({	
	el : $("#quoteGapMatrix-container"),
    
    initialize : function() {
    	_.bindAll(this, "render"); // Make all methods in this class have `this` bound to this class
    	var view = this;
    	this.model.fetch({
    		success: function(){
    			view.render();
    		}
    	});
    },
    	
    render : function(){;
    	var stockTitleGenerated = false;
    	$(".quoteGapMatrix-container").append("<div class=\"stockColumn\"><div>&nbsp;</div></div>");
    	for(var dateKey in this.model.attributes){
    		// span the date columns.
    		$(".quoteGapMatrix-container").append("<div class=\"dateColumn\"><div class=\"header\">" + dateKey + "</div></div>");
    		for(var stockKey in this.model.attributes[dateKey]){
    			// Handling the first stock column.
    			if(!stockTitleGenerated){
    				$(".stockColumn").append("<div class\"stockName\">" + stockKey + "</div>");
    			}
    			var gap = "N/A";
    			var quoteGap = this.model.attributes[dateKey][stockKey];
    			if(quoteGap != null){
    				gap = quoteGap.gap.toFixed(2);
    			}
    			
    			var levelClass = "";
    			if(gap != "N/A"){
	    			if(parseFloat(gap) > 4){
	    				levelClass = "lvl1";
	    			}else if(parseFloat(gap) > 3){
	    				levelClass = "lvl2";
	    			}else if(parseFloat(gap) > 2){
	    				levelClass = "lvl3";
	    			}else if(parseFloat(gap) > 1){
	    				levelClass = "lvl4";
	    			}
    			}
    			$(".quoteGapMatrix-container > div").last().append(
    				"<div " +
    					"class=\"stockGap " + levelClass + " " + dateKey + " " + stockKey + "\" " +
    					"data-date=\"" + dateKey + "\" " +
    					"data-symbol=\"" + stockKey + "\"" +
    				">" + 
    					gap + 
    				"</div"
    			);
    			
    			console.log($(".quoteGapMatrix-container > div > div").last().html());
    			
    			$(".quoteGapMatrix-container > div > div").last().click(bindQuotePopover);
    		}
    		
    		stockTitleGenerated = true;
    	}
    	$(".quoteGapMatrix-container").append("<div class=\"spacer\"><\div>");

    	return this;
    }
});

var quotePopoverView;
bindQuotePopover = function(){
	var el=$(this);
	
	var quote = new Quote({
		symbol: el.attr("data-symbol"),
		date: el.attr("data-date")
	});
	
	quote.url = ctx + "/quote?symbol=" + quote.get("symbol") + "?date=" + moment(quote.get("date").format('L'));
	
	console.log("symbol : " + quote.symbol);
	console.log("date : " + moment(quote.date).format('L'));
	
	if(quotePopoverView == null){
		quotePopoverView = new QuotePopoverView({model : quote});
	}else{
		quotePopoverView.model = quote;
	}
	
	quotePopoverView.model.fetch({
		success: function(){
			el.popover(
				{ 
					content: $(".quotePopover-container").html(), 
					placement: "bottom", 
					html: true, 
					trigger: "click"
				}
			).popover("show");
		}
	});
};
