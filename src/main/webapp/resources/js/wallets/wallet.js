window.Wallet = Backbone.Model.extend({
	url : function() {
		var base = ctx + '/wallet.json';
		if (this.isNew()) return base;
		return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id;
	},
});

window.WalletList = Backbone.Collection.extend({
	url: ctx + '/wallet'
});