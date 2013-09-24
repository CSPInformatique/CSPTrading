window.Quote = Backbone.Model.extend();

window.QuoteGapsMatrix = Backbone.Model.extend({
	url : ctx + '/quoteGap',
	contentType : "application/json"
});

window.QuoteLowsMatrix = Backbone.Model.extend({
	url : ctx + '/quotes/lows'
});