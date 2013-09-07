window.WalletComboBoxView = Backbone.View.extend({
	el : ".walletComboBox-container",
    
    initialize : function() {
        this.template = _.template($("#walletComboBox-template").html());
        
        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
    },
    	
    render : function(){;
    	var renderedContent = this.template({walletList : this.collection.toJSON()});
        $(this.el).html(renderedContent);
        
        $(this.el).find("select").select2();
        
        return this;
    }
});

window.WalletListView = Backbone.View.extend({
	el : "#walletList-container",
	
    events : {
        "click .newWallet .save button" : "saveNewWallet"
    },
    
    initialize : function() {
    	var view = this;
    	this.collection.fetch({success: function(){
    		if(view.collection.toJSON().length == 0){
    			view.render();
    		}
    	}});
        this.template = _.template($("#walletList-template").html());

        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
        
        $(".wallets > button").click(this.newWallet);
    },
    
    newWallet: function(){
    	$(".wallets table").append($("#newWallet-template").html());
    	$(".wallets > button").hide();
    	
    },

	saveNewWallet: function(){
		var simulated = function(){
			if($(".newWallet .simulated input").is(':checked')){
				return true;
			}else{
				return false;
			}
		};
		
		var wallet = {
			name: $(".newWallet .name input").val(),
			simulated: simulated(),
			initialAmount: parseInt($(".newWallet .initialAmount input").val()),
			positions:[] 
		};
		
		this.collection.create(wallet, {	wait : true,
			success : function(resp){
		        collection.fetch();
		        $(".wallets > button").show();
		    }
		});
	},
    	
    render : function(){;
    	var renderedContent = this.template({walletList : this.collection.toJSON()});
        $(this.el).html(renderedContent);
        return this;
    }
});