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