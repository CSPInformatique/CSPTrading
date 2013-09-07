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
    <a class="brand" href="#">CSP Trading</a>
    <div class="nav-collapse collapse">
      <ul class="nav">
      	<li class="wallets"><a href="wallet">Wallets</a></li>
      	<li class="positions dropdown">
      		<a href="position" class="dropdown-toggle" data-toggle="dropdown">Positions<b class="caret"></b></a>
      		<ul class="dropdown-menu">
				<li><a href="position">Open positions</a></li>
				<li><a href="position?closed">Closed positions</a></li>
      		</ul>
		<li class="quotes"><a href="quote">Quotes</a></li>
		<li class="tools"><a href="tools">Tools</a></li>
      </ul>
    </div><!--/.nav-collapse -->
    </div>
  </div>
</div>