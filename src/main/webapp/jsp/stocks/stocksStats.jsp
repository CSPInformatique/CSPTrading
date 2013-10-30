<script type="text/javascript">
	$(document).ready(function(){
		$("li.stocks").addClass("active");
		
		new StocksStatsView({collection: new StocksStatsList()});
	});
</script>

<script type="text/template" id="stocksStats-template">
	<table class="table table-striped">
		<tr>
			<th>Symbol</th>
			<th>Stock</th>
			<th>Low Average</th>			
		</tr>
	<@ _.each(stocksStats, function(stockStats) { @>
		<tr>
			<td><@= stockStats.stock.symbol @></td>
			<td><@= stockStats.stock.name @></td>
			<td><@= stockStats.lowAverageVariation @></td>
		</tr>
	<@}); @>
	</table>
</script>

<div class="stocksStats-container">

</div>