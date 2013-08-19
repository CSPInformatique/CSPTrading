<script type="text/javascript">
	$(document).ready(function(){
		$("li.tools").addClass("active");

		$(".investmentCalculator input").keyup(calculateInvestment);
		$(".buyableQuantityCalculator input").keyup(calculateBuyableQuantity);
	});
</script>
<div class="row">
	<div class="investmentCalculator col-lg-4">
		<h2>Investment calculator</h2>
		<div>
			<div class="buyPrice">
				<input type="text" class="form-control" placeholder="Buy price">
			</div>
			<div class="sellPrice">
				<input type="text" class="form-control" placeholder="Sell price">
			</div>
			<div class="quantity">
				<input type="text" class="form-control" placeholder="Quantity">
			</div>
			<div class="brokerFees">
				<input type="text" class="form-control" placeholder="Broker fees">
			</div>
			<div class="investmentResult">
				<input type="text" class="form-control" placeholder="Investment result" disabled="disabled">
			</div>
		</div>
	</div>
         
	<div class="buyableQuantityCalculator col-lg-4">
		<h2>Buyable quantity calculator</h2>
		<div>
       		<div class="investmentAmount">
       			<input type="text" class="form-control" placeholder="Investment amount">
       		</div>
        	<div class="price">
        		<input type="text" class="form-control" placeholder="Price" >
        	</div>
        	<div class="quantity">
        		<input type="text" class="form-control" placeholder="Quantity" disabled="disabled">
        	</div>
        </div>
     </div>
</div>