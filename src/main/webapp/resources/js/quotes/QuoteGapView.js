window.QuoteGapMatrixView = Backbone.View.extend({	
	el : $("#quoteGapMatrix-container"),
    
    initialize : function() {
    	var view = this;
    	this.model.fetch(
    		{
    			success: function(){
    				view.render();
    			}
    		}
    	);
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
    			if(this.model.attributes[dateKey][stockKey] != null){
    				gap = this.model.attributes[dateKey][stockKey].gap.toFixed(2);
    			}
    			$(".quoteGapMatrix-container > div").last().append("<div class=\"stockGap\">" + gap + "</div");
    		}
    		
    		stockTitleGenerated = true;
    	}
    	$(".quoteGapMatrix-container").append("<div class=\"spacer\"><\div>");
    	
    	return this;
    }
});