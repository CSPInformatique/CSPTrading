<script type="text/javascript">
	$(document).ready(function(){
		$("li.stocks").addClass("active");
		
		new StocksView({collection: new StocksList()});
	});
</script>

<script type="text/template" id="stocks-template">
	<div class="row">
		<div>Stock</div>
		<div>Low Average</div>
		<@ _.each(stocks, function(stock) { @>
			<div class="row">
				<div><@= stock.name @></div>
				<div><@= stock.quoteStats.lowAverageVariation @></div>
			</div>
		<@}); @>
	<div>
</script>

<div class="stocks-container">

</div>