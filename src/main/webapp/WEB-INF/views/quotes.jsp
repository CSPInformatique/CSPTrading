<!-- Example row of columns -->
<script type="text/javascript">
	$(document).ready(function(){
		$("li.quotes").addClass("active");
		
		
		new QuoteGapMatrixView({model: new QuoteGapsMatrix()});
	});
</script>
<style>
	.dateColumn, .stockColumn{ 
		float: left;
		padding: 10px;
		text-align: center;
	}
	
	.spacer{
		clear: left;
	}
</style>

<div class="quoteGapMatrix-container">

</div>

