window.Position = Backbone.Model.extend({
	url : function() {
		var base = ctx + '/position.json';
		if (this.isNew()) return base;
		return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id;
	},
});

window.PositionList = Backbone.Collection.extend({
	url: ctx + '/position.json'
});