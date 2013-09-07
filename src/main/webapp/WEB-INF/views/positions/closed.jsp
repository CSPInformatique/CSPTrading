<script type="text/javascript">
	$(document).ready(function(){
		$("li.positions").addClass("active");

		var walletComboBoxView = new WalletComboBoxView({collection : new WalletList()});
		
		walletComboBoxView.collection.fetch({success: function(){
			var walletClosedPositionList = new WalletClosedPositionList();
			walletClosedPositionList.walletId = $("select.wallet").select2("val");
			
			var walletClosedPositionListView = new WalletClosedPositionListView({collection: walletClosedPositionList});
			walletClosedPositionListView.collection.fetch();
			
			$("select.wallet").on("change", function(e){
				walletClosedPositionListView.collection.walletId = e.val;
				walletClosedPositionListView.collection.fetch();
			});
		}});
	});
</script>
<script type="text/template" id="closedPositionList-template">
	<table>
		<tr>
			<th>#</th>
			<th>Stock</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Broker Fees</th>
			<th>Open Value</th>
			<th>Close Value</th>
			<th>ROI</th>
			<th>Performance</th>
			<th>Open Date</th>
			<th>Close Date</th>
		</tr>
		<@ _.each(positionList, function(position) { @>
			<tr>
				<td class="id"><div><@= position.id @></div></td>
				<td class="stock"><div><@= position.stock.symbol @></div></td>
				<td class="price"><div><@= position.buyOrder.price @></div></td>
				<td class="quantity"><div><@= position.buyOrder.quantity @></div></td>
				<td class="brokerFees"><div><@= position.buyOrder.brokerFees @></div></td>
				<td class="closeValue"><div><@= position.openValue @></div></td>
				<td class="currentValue"><div><@= position.currentValue @></div></td>
				<td class="performance"><div><@= position.returnOnInvestment @></div></td>
				<td class="performance"><div><@= position.performance @></div></td>
				<td class="openDate"><div><@= position.openDate @></div></td>
				<td class="closeDate"><div><@= position.closeDate @></div></td>
			</tr>
		<@}); @>
	</table>
</script>

<script type="text/template" id="walletComboBox-template">
	<select class="wallet">
		<optgroup label="Real">
			<@ _.each(walletList, function(wallet) { @>
				<@ if(!wallet.simulated){ @>
					<option value="<@= wallet.id @>"><@= wallet.name @></option>
				<@ } @>	
			<@ }); @>
		</optgroup>
		<optgroup label="Simulated">
			<@ _.each(walletList, function(wallet) { @>
				<@ if(wallet.simulated){ @>
					<option value="<@= wallet.id @>"><@= wallet.name @></option>
				<@ } @>	
			<@ }); @>
		</optgroup>
	</select>
</script>
        
<div class="row closedPositions">
	<h2>Positions</h2>
	<div class="row walletComboBox-container"></div>
	<div id="closedPositionList-container" class="row"></div>
</div>