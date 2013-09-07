window.QuotePopoverView = Backbone.View.extend({	
	el : $(".quotePopover-container"),
    initialize : function() {
    	this.template = _.template($("#quotePopover-template").html());
    },
	render : function(){
    	var renderedContent = this.template({quote : this.model.toJSON()});
        $(this.el).html(renderedContent);
        return this;
	}
});