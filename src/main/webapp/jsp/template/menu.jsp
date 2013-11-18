<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="navbar navbar-inverse navbar-fixed-top">
<div class="navbar-inner">
  <div class="container">
    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="brand">CSP Trading</a>
    <div class="nav-collapse collapse">
      <ul class="nav">
      	<li class="wallets"><a href="<c:url value="/wallet" />">Wallets</a></li>
      	<li class="positions dropdown">
      		<a href="<c:url value="/position" />" class="dropdown-toggle" data-toggle="dropdown">Positions<b class="caret"></b></a>
      		<ul class="dropdown-menu">
				<li><a href="<c:url value="/position" />">Open positions</a></li>
				<li><a href="<c:url value="/position?closed" />">Closed positions</a></li>
      		</ul>
      	</li>
      	<li class="stocks"><a href="<c:url value="/stock" />">Stocks</a></li>
		<li class="tools"><a href="<c:url value="/tools" />">Tools</a></li>
      </ul>
    </div><!--/.nav-collapse -->
    </div>
  </div>
</div>