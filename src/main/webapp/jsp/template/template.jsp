<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<spring:url var="cssUrl" value="/resources/css" />
<spring:url var="jsUrl" value="/resources/js" />

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CSP Trading</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/libs/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/libs/datetimepicker.css" rel="stylesheet">
    <link href="resources/css/libs/select2/select2.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/cspTrading.css" rel="stylesheet">
    <link href="resources/css/positions.css" rel="stylesheet">
    <link href="resources/css/quotes.css" rel="stylesheet">
    <link href="resources/css/tools.css" rel="stylesheet">
    
 	<script src="resources/js/libs/jquery.js"></script>
	<script src="resources/js/libs/underscore-min.js"></script>
	<script src="resources/js/libs/underscore.string.js"></script>
    <script src="resources/js/libs/backbone-min.js"></script>
    <script src="resources/js/libs/bootstrap.min.js"></script>
    <script src="resources/js/libs/bootstrap-datetimepicker.min.js"></script>
    <script src="resources/js/libs/dygraph-combined.js"></script>
    <script src="resources/js/libs/select2.min.js"></script>
    <script src="resources/js/libs/moment.js"></script>
  </head>

  <body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="menu" />

    <div class="container">
		<div class="body-content">
	      	<div class="div-content">
				<tiles:insertAttribute name="content" />
			</div>
			<tiles:insertAttribute name="footer" />
			<div class="genericError-container modal hide fade">
			<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal">×</button>
    				<h3 id="myModalLabel">Error</h3>
  				</div>
  				<div class="modal-body">
					<div class="message "></div>
				</div>
			</div>
		</div>
    </div>
    
    <script src="resources/js/tools.js"></script>
	<script type="text/javascript">
       	var ctx = "${pageContext.servletContext.contextPath}";
       	
    	_.templateSettings = {
    	    interpolate: /\<\@\=(.+?)\@\>/gim,
    	    evaluate: /\<\@([\s\S]+?)\@\>/gim,
    	    escape: /\<\@\-(.+?)\@\>/gim
    	};
   	</script>
   	
    <script src="resources/js/positions/position.js"></script>
    <script src="resources/js/positions/positionView.js"></script>

    <script src="resources/js/quotes/quote.js"></script>
    <script src="resources/js/quotes/quoteView.js"></script>
    
    <script src="resources/js/quotes/quoteGap.js"></script>
    <script src="resources/js/quotes/quoteGapView.js"></script>
    
    <script src="resources/js/quotes/quoteProcessorStats.js"></script>
    
    <script src="resources/js/stocks/stock.js"></script>
    <script src="resources/js/stocks/stockView.js"></script>
    
    <script src="resources/js/wallets/wallet.js"></script>
    <script src="resources/js/wallets/walletView.js"></script>
    
    <script src="resources/js/app.js"></script>
  </body>
</html>