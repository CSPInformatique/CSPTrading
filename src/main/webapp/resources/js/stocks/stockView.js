window.MarketsStocksComboBoxView = Backbone.View.extend({
	el : ".stocksComboBox-container",
    
    initialize : function() {
    	this.collection.fetch();
        this.template = _.template($("#stocksComboBox-template").html());
        
        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
    },
    	
    render : function(){;
    	var renderedContent = this.template({marketsStocksList : this.collection.toJSON()});
        $(this.el).html(renderedContent);
        
        $(this.el).find("select").select2();
        
        return this;
    }
});

window.StockChartView = Backbone.View.extend({
	el : ".stockChart-container",
    
    initialize : function() {
    	this.model.fetch();
        
        _.bindAll(this, "render");
        this.model.bind("change", this.render);
    },
    	
    render : function(){    	
        $(this.el).highcharts("StockChart", {
        	rangeSelector : {
				selected : 1
			},
			title : {
				text : this.model.symbol + " Stock Price"
			},
			
			series : [{
				name : this.model.symbol,
				data : this.model.toJSON(),
				tooltip: {
					valueDecimals: 2
				}
			}]
        });
        
        return this;
    }
});

window.StockSearchResultView = Backbone.View.extend({
	el : ".stocks-container",
    
    initialize : function() {
    	this.model.fetch();
        this.template = _.template($("#stocks-template").html());
        
        _.bindAll(this, "render");
        this.model.bind("change", this.render);
    },
    	
    render : function(){;
    	var renderedContent = this.template({stockSearchResult : this.model.toJSON()});
        $(this.el).html(renderedContent);
        
        $(this.el).find("tbody tr").click(function(){
        	location.href = ctx + "/stock/" + $(this).attr("data-symbol") + "/chart";
        });
        
        return this;
    }
});