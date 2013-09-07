<script type="text/javascript">
	$(document).ready(function(){
		$("li.wallets").addClass("active");
		
		new WalletListView({collection : new WalletList()});
	});
</script>
<script type="text/template" id="walletList-template">
	<table>
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Simulated</th>
			<th>Open positions</th>
			<th>Initial amount</th>
			<th>Current amount</th>
			<th>Current value</th>
			<th>Performance</th>
			<th>&nbsp;</th>
		</tr>
		<@ _.each(walletList, function(wallet) { @>
			<tr>
				<td class="id"><div><@= wallet.id @></div></td>
				<td class="name"><div><@= wallet.name @></div></td>
				<td class="simulated"> 
					<@ if(wallet.simulated){ @>
						<input type="checkbox" disabled="disabled" checked="checked" />
					<@ }else{ @>
						<input type="checkbox" disabled="disabled"/>
					<@ } @>
				</td>
				<td class="openPositions"><div><@= wallet.openPositions @></div></td>
				<td class="initialAmount"><div><@= wallet.initialAmount @></div></td>
				<td class="initialAmount"><div><@= wallet.currentAmount @></div></td>
				<td class="currentValue"><div><@= wallet.currentValue @></div></td>
				<td class="currentValue"><div><@= wallet.performance @></div></td>
				<td>&nbsp;</td>
			</tr>
		<@}); @>
	</table>
</script>

<script type="text/template" id="newWallet-template">
	<tr class="newWallet">
		<td>&nbsp;</td>
		<td class="name"><input type="text" /></td>
		<td class="simulated"><div><input type="checkbox" class="checkbox" /><div></td>
		<td class="openPositions"><div>0</div></td>
		<td class="initialAmount"><input type="text" /></td>
		<td class="currentAmount"><div>0</div></td>
		<td class="currentValue"><div>0</div></td>
		<td class="save"><button class="btn btn-primary">Save</button></td>
	</tr>
</script>
        
<div class="row wallets">
	<h2>Wallets</h2>
	<form class="addWallet">
		<div id="walletList-container"></div>
	</form>
	<button class="btn btn-primary">Add</button>
</div>