<!-- Example row of columns -->
<script type="text/javascript">
	var stats = null;
	
	$(document).ready(function(){
		$("li.quoteProcessor").addClass("active");
		
		stats = new QuoteProcessorStats();
		
		loadTemplate();
	});
	
	var loadTemplate = function(){
		stats.fetch({success: function(){
			var template = _.template($("#quoteProcessor-template").html());
			$(".quoteProcessor-container").html(template({stats : stats.toJSON()}));
			
			$(".quoteProcessor-container button").click(function(){
				$.ajax({
					type: "GET",
					url: ctx + "/quoteProcessor?start",
					data: "",
					success: function(){
						loadTemplate();
        			}
    			});
			});
			
			if(stats.toJSON().running){
				setTimeout(refreshStats, 1000);
			}
		}});
	};
	
	var refreshStats = function(){
		stats.fetch({success: function(){
			if(!stats.toJSON().running){
				loadTemplate();
			}else{
				$(".bar").width(stats.toJSON().progress + "%");
				$(".stockProcessed").html(stats.toJSON().stocksProccessed);
				$(".stocksToProccess").html(stats.toJSON().stocksToProccess);
				
				setTimeout(refreshStats, 1000);
			}
		}});
	};
</script>
<div class="quoteProcessor-container">

</div>
<div id="quotePopover-container">

</div>

<div class="quotePopover-container">

</div>

<script type="text/template" id="quoteProcessor-template">
	<@	if(stats.running){	@>
		<div class="row">
			<div class="progress progress-info progress-striped">
  				<div class="bar" style="width: <@= stats.progress @>%">
					<div>
						<span class="stockProcessed"><@= stats.stocksProccessed @></span>
						/
						<span class="stocksToProccess"><@= stats.stocksToProccess @></span>
					</div>
				</div>
			</div>
		</div>
		<div class="row"
			<
		</div>
	<@	}else{	@>
		<div class="row">
			<span class="span3">Stock processed on last run :</span>
			<span class="span3"><@= stats.stocksProccessed @></span>
		</div>
		<div class="row">
			<button class="btn btn-primary">Launch Quote Processor</button>
		</div>
	<@	}	@>
</script>