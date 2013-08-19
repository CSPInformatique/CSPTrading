<script type="text/javascript">
	$(document).ready(function(){
		$("li.positions").addClass("active");

		new PositionListView({collection : new PositionList()});
	});
</script>
<script type="text/template" id="positionList-template">
	<table>
		<tr>
			<th>#</th>
			<th>Stock</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Broker Fees</th>
			<th>Open Date</th>
			<th>&nbsp;</th>
		</tr>
		<@ _.each(positionList, function(position) { @>
			<tr>
				<td class="id"><@= position.id @></td>
				<td class="stock"><@= position.stock.symbol @></td>
				<td class="price"><@= position.buyOrder.price @></td>
				<td class="quantity"><@= position.buyOrder.quantity @></td>
				<td class="brokerFees"><@= position.buyOrder.brokerFees @></td>
				<td class="openDate"><@= position.openDate @></td>
				<td>&nbsp;</td>
			</tr>
		<@}); @>
	</table>
</script>

<script type="text/template" id="newPosition-template">
	<tr class="newPosition">
		<td>&nbsp;</td>
		<td class="stock"><input type="text" /></td>
		<td class="price"><input type="text" /></td>
		<td class="quantity"><input type="text" /></td>
		<td class="brokerFees"><input type="text" /></td>
		<td class="openDate"><input type="text" /></td>
		<td class="save"><button>Save</button></td>
	</tr>
</script>

        
<div class="row positions">
	<h2>Positions</h2>
	<div id="positionList-container"></div>
	<button>Add</button>
</div>