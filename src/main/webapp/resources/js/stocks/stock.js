window.Stock = Backbone.Model.extend({
	url : function() {
		var base = ctx + '/stock.json';
		if (this.isNew()) return base;
		return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id;
	},
});

window.MarketStocksList = Backbone.Collection.extend({
	url: ctx + '/stock.json'
});