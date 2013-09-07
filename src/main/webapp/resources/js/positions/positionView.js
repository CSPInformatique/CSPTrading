window.WalletClosedPositionListView = Backbone.View.extend({
	el : "#closedPositionList-container",
    
    initialize : function() {
    	var view = this;
    	this.collection.fetch({success: function(){
    		if(view.collection.toJSON().length == 0){
    			view.render();
    		}
    	}});
        this.template = _.template($("#closedPositionList-template").html());

        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
    },
    	
    render : function(){;
    	var renderedContent = this.template({positionList : this.collection.toJSON()});
        $(this.el).html(renderedContent);
        $(".closedPositions").find(".openDate div, .closeDate div").each(function(index, value) {
        	$(value).html(moment(parseInt($(value).html())).format("YYYY-MM-DD HH:mm"));
        });
        
        return this;
    }
});

window.WalletOpenPositionListView = Backbone.View.extend({
	el : "#openPositionList-container",
	
    events : {
        "click .newPosition button" : "saveNewPosition"
    },
    
    initialize : function() {
    	var view = this;
    	this.collection.fetch({success: function(){
    		if(view.collection.toJSON().length == 0){
    			view.render();
    		}
    	}});
        this.template = _.template($("#openPositionList-template").html());

        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
        
        $(".openPositions button.add").click(this.newPosition);
    },
    
    newPosition: function(){
    	$(".openPositions table").append($("#newPosition-template").html());
    	new MarketsStocksComboBoxView({collection : new MarketStocksList()});
    	$(".newPosition .openDate input").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
    	$(".openPositions button.add").hide();
    },

	saveNewPosition: function(){
		var collection = this.collection;
		this.collection.create(
			{	stock: {
					id: parseInt($(".newPosition .stock").select2("val"))
				},
				wallet: {
					id: parseInt($("select.wallet").select2("val"))
				},
				buyOrder: {
					stock: {
						id: parseInt($(".newPosition .stock").select2("val"))
					},
					price: parseFloat($(".newPosition .price input").val()),
					brokerFees: parseFloat($(".newPosition .brokerFees input").val()),
					quantity: parseInt($(".newPosition .quantity input").val())
				},
				openDate: moment($(".newPosition .openDate input").val(), "YYYY-MM-DD HH:mm").toDate()
			},
			{	wait : true,
				success : function(resp){
			        collection.fetch();
			        $(".openPositions button.add").show();
			    },
			    error : handleErrors
			}	
		);		
	},
    	
    render : function(){;
    	var renderedContent = this.template({positionList : this.collection.toJSON()});
        $(this.el).html(renderedContent);
        $(".openPositions").find(".openDate div, .closeDate div").each(function(index, value) {
        	$(value).html(moment(parseInt($(value).html())).format("YYYY-MM-DD HH:mm"));
        });
        
        var view = this;
        $("button.closePosition").click(function(){
        	var positionId = $(this).attr("data-position");
        		 
        	// Load HTML from Template.
        	var closePositionTemplate = _.template($("#closePositionModal-template").html());
        	
        	$(".closePositionModal-container").html(
        		closePositionTemplate({
        			position: view.collection.get(positionId).toJSON()
        		})
        	).modal({
        		backdrop: "static",
        		keyboard: "false",
        	});
        	
        	$(".closeDate input").datetimepicker({
        		format: "yyyy-mm-dd hh:ii"
        	});
        	
        	$(".closePosition button.closeButton").click(function(){
        		var url = view.collection.get(positionId).url().toString() + 
					"?close&date=" + moment($(".closePosition .closeDate input").val(), "YYYY-MM-DD HH:mm") + 
					"&price=" + parseFloat($(".closePosition .price input").val());
        		
        		$.ajax({
					type: "POST",
					url: url,
					data: "",
					success: function(){
        				view.collection.fetch();
    					$(".closePositionModal-container").modal("hide");
        			}
    			});
        	});
        });
        
        return this;
    }
});