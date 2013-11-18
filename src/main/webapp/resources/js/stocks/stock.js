window.Stock = Backbone.Model.extend({
	url : function() {
		var base = ctx + '/stock.json';
		if (this.isNew()) return base;
		return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id;
	},
});

window.StockChart = Backbone.Model.extend({
	url : function() {
		return  ctx + "/stock/" + this.symbol +  "/chart.json";
	},
});

window.MarketStocksList = Backbone.Collection.extend({
	url: ctx + '/stock.json?market'
});

window.StockSearchResult = Backbone.Model.extend({
	url : function() {
		if(!this.pageIndex || this.pageIndex < 0){
			this.pageIndex = 0;
		}
		return ctx + '/stock.json?pageIndex=' + this.pageIndex + "&resultsPerPage=20";
	},
});