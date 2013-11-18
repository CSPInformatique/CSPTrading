<script type="text/javascript">
	$(document).ready(function(){
		var AppRouter = Backbone.Router.extend({
	        routes: {
	            "pageIndex=:pageIndex&resultsPerPage=:resultsPerPage": "searchWithParams",
	            
	            "*actions": "defaultRoute" // Backbone will try match the route above first
	        }
	    });
		
		var app_router = new AppRouter();
		
		app_router.on('route:searchWithParams', function (pageIndex, resultsPerPage) {
			var model = new StockSearchResult();
			model.pageIndex = pageIndex;
			model.resultsPerPage = resultsPerPage;
			new StockSearchResultView({model: model});
	    });
		
		app_router.on('route:defaultRoute', function (actions) {
			new StockSearchResultView({model: new StockSearchResult()});
	    });
		
		$("li.stocks").addClass("active");
		
		Backbone.history.start();
	});
	

</script>

<script type="text/template" id="stocks-template">
	<div>
		<@= stockSearchResult.results @> stocks found.
	</div>
	
	<div class="pagination">
  		<ul>
		<@	if(stockSearchResult.pageIndex == 0){	@>
			<li class="disabled">
		<@	}else{	@>
			<li>
		<@	}@>
				<a href="#">«</a>
			</li>
		<@	for(var i = stockSearchResult.pageIndex -5; i <= stockSearchResult.pageIndex + 5;i ++){
				if(i >= 0 && i < stockSearchResult.totalPages){
					if(i == stockSearchResult.pageIndex){	@>
   			<li class="active">
		<@			}else{	@>
			<li>
		<@			}	@>
				<a href="#pageIndex=<@= i@>&resultsPerPage=<@=stockSearchResult.stocks.length@>"><@= i+1 @></a>
			</li>
		<@		}
			}
			if(stockSearchResult.pageIndex +1 ==  stockSearchResult.totalPages){ @>
			<li class="active">
		<@	}else{	@>
			<li>
		<@	}	@>
				<a href="#">»</a>
			</li>
  		</ul>
	</div>

	<table class="stocks table table-striped">
		<thead>
			<tr>
				<th>Symbol</th>
				<th>Stock</th>
				<th>Last</th>
			</tr>
		</thead>
		<tbody>
	<@ _.each(stockSearchResult.stocks, function(stock) { @>
			<tr data-symbol="<@= stock.symbol @>">
				<td><@= stock.symbol @></td>
				<td><@= stock.name @></td>
				<td><@= stock.lastQuote.low @></td>
			</tr>
	<@}); @>
		</tbody>
	</table>
</script>

<div class="stocks-container">

</div>