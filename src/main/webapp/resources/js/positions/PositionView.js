window.PositionListView = Backbone.View.extend({
	el : "#positionList-container",
	
    events : {
        "click #newPosition button" : "saveNewPosition"
    },
    
    initialize : function() {
    	this.collection.fetch();
        this.template = _.template($("#positionList-template").html());

        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
        
        $(".positions > button").click(this.newPosition);
    },
    
    newPosition: function(){
    	$(".positions table").append($("#newPosition-template").html());
    },

	saveNewPosition: function(){
		var collection = this.collection;
		this.collection.create(
			{	id: null,
				stock: $(".newPosition .stock input").val(),
				buyOrder: {
					id: 0,
					stock: $(".newPosition .stock input").val(),
					price: $(".newPosition .price input").val(),
					brokerFees: $(".newPosition .brokerFees input").val(),
					quantity: $(".newPosition .quantity input").val()
				},
				openDate: $(".newPosition .date input").val(),
				closeDate: null
			},
			{	wait : true,
				success : function(resp){
			        collection.fetch();
			    }
			}	
		);		
	},
    	
    render : function(){;
    	var renderedContent = this.template({vehiculeList : this.collection.toJSON()});
        $(this.el).html(renderedContent);
        return this;
    }
});