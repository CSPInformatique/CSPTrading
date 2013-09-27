<script type="text/javascript">
	$(document).ready(function(){
		$("li.positions").addClass("active");

		var walletComboBoxView = new WalletComboBoxView({collection : new WalletList()});
		
		walletComboBoxView.collection.fetch({success: function(){
			var walletOpenPositionList = new WalletOpenPositionList();
			walletOpenPositionList.walletId = $("select.wallet").select2("val");
			
			var walletOpenPositionListView = new WalletOpenPositionListView({collection: walletOpenPositionList});
			walletOpenPositionListView.collection.fetch();
			
			$("select.wallet").on("change", function(e){
				walletOpenPositionListView.collection.walletId = e.val;
				walletOpenPositionListView.collection.fetch();
			});
		}});
	});
</script>
<script type="text/template" id="openPositionList-template">
	<table>
		<tr>
			<th>#</th>
			<th>Stock</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Broker Fees</th>
			<th>Last Quote</th>
			<th>Current Value</th>
			<th>Performance</th>
			<th>Open Date</th>
			<th>&nbsp;</th>
		</tr>
		<@ _.each(positionList, function(position) { @>
			<tr>
				<td class="id"><div><@= position.id @></div></td>
				<td class="stock"><div><@= position.stock.symbol @></div></td>
				<td class="price"><div><@= position.buyOrder.price @></div></td>
				<td class="quantity"><div><@= position.buyOrder.quantity @></div></td>
				<td class="brokerFees"><div><@= position.buyOrder.brokerFees @></div></td>
				<td class="lastQuote"><div><@= position.lastQuote.last @></div></td>
				<td class="currentValue"><div><@= position.currentValue @></div></td>
				<td class="performance"><div><@= position.performance @></div></td>
				<td class="openDate"><div><@= position.openDate @></div></td>
				<td><button class="closePosition btn btn-primary" data-position="<@= position.id @>">Close</button></td>
			</tr>
		<@}); @>
	</table>
</script>

<script type="text/template" id="stocksComboBox-template">
	<select class="stock">
		<@ _.each(marketsStocksList, function(marketStocks) { @>
			<optgroup label="<@= marketStocks.market.name @>">
				<@ _.each(marketStocks.stocks, function(stock) { @>
					<option value="<@= stock.id @>"><@= stock.symbol @></option>
				<@}); @>
			</optgroup
		<@}); @>
	</select>
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

<script type="text/template" id="closePositionModal-template">
	<div class="closePosition">
		<div>
			<@= position.stock.name @>
		</div>
		<div class="price">
			<div>Price</div>
			<div>
				<input type="text" />
			</div>
		</div>
		<div class="closeDate">
			<div>Close Date</div>
			<div><input size="16" type="text"></div>
		</div>
		<div>
			<button class="btn btn-primary closeButton">Close position</button>
			<button class="btn btn-primary cancelButton">Cancel</button>
		</div>
	</div>
</script>

<script type="text/template" id="newPosition-template">
	<tr class="newPosition">
		<td>&nbsp;</td>
		<td class="stock stocksComboBox-container"></td>
		<td class="price"><input type="text" /></td>
		<td class="quantity"><input type="text" /></td>
		<td class="brokerFees"><input type="text" /></td>
		<td class="lastQuote"><div>0</div></td>
		<td class="currentValue"><div>0</div></td>
		<td class="performance"><div>0</div></td>
		<td class="openDate">
			<input size="16" type="text" readonly>
		</td>
		<td class="save"><button class="btn btn-primary">Save</button></td>
	</tr>
</script>
        
<div class="row openPositions">
	<h2>Positions</h2>
	<div class="row walletComboBox-container"></div>
	<div id="openPositionList-container" class="row"></div>
	<div class="row"><button class="add btn btn-primary">Add</button></div>
	<div class="modal hide fade closePositionModal-container"></div>
</div>