window.Position = Backbone.Model.extend({
	idAttribute: "id",
	
	url : function() {
		var base = ctx + '/position.json';
		if (this.isNew()) return base;
		return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id;
	}
});

window.WalletOpenPositionList = Backbone.Collection.extend({
	url: function(){
		return ctx + '/wallet/' + this.walletId + '/position.json?open';
	}
});

window.WalletClosedPositionList = Backbone.Collection.extend({
	url: function(){
		return ctx + '/wallet/' + this.walletId + '/position.json?closed';
	}
});