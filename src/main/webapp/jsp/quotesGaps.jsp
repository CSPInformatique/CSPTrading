<script type="text/javascript">
	$(document).ready(function(){
		$("li.quotes").addClass("active");
		
		new QuoteGapMatrixView({model: new QuoteGapsMatrix()});
	});
</script>
<div class="quoteGapMatrix-container">

</div>
<div id="quotePopover-container">

</div>

<div class="quotePopover-container">

</div>

<script type="text/template" id="quotePopover-template">
	<div>
		<div>last : <@=last @></div>
		<div>min : <@=min @></div>
		<div>max : <@=max @></div>
		<div>volume : <@=volume @></div>
	</div
</script>