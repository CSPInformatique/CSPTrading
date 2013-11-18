<script type="text/javascript">
	$(document).ready(function(){
		$.getJSON(ctx + "/stock/${symbol}/chart.json", function(data) {
			// Create the chart
			$('.stockChart-container').highcharts('StockChart', {
				

				rangeSelector : {
					selected : 1
				},

				title : {
					text : '${symbol} Stock Price'
				},
				
				series : [{
					name : '${symbol}',
					data : data,
					tooltip: {
						valueDecimals: 2
					}
				}]
			});
		});
	});
	

</script>

<div class="stockChart-container">

</div>