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

    <title>Jumbotron Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/libs/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/positions.css" rel="stylesheet">
    <link href="resources/css/quotes.css" rel="stylesheet">
    <link href="resources/css/tools.css" rel="stylesheet">
    
	<script src="resources/js/libs/jquery.js"></script>
	<script src="resources/js/libs/underscore-min.js"></script>
	<script src="resources/js/libs/underscore.string.js"></script>
    <script src="resources/js/libs/backbone-min.js"></script>
    <script src="resources/js/libs/bootstrap.min.js"></script>
    
    <script src="resources/js/tools.js"></script>
	<script type="text/javascript">
       	var ctx = "${pageContext.servletContext.contextPath}";
       	
    	_.templateSettings = {
    	    interpolate: /\<\@\=(.+?)\@\>/gim,
    	    evaluate: /\<\@([\s\S]+?)\@\>/gim,
    	    escape: /\<\@\-(.+?)\@\>/gim
    	};
   	</script>
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
		</div>
    </div>
    
    <script src="resources/js/positions/Position.js"></script>
    <script src="resources/js/positions/PositionView.js"></script>
    
    <script src="resources/js/quotes/QuoteGap.js"></script>
    <script src="resources/js/quotes/QuoteGapView.js"></script>
    
    <script src="resources/js/app.js"></script>
  </body>
</html>