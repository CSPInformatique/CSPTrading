
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Jumbotron Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/tools.css" rel="stylesheet">
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Project name</a>
        <div class="nav-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <form class="navbar-form form-inline pull-right">
            <input type="text" placeholder="Email" class="form-control">
            <input type="password" placeholder="Password" class="form-control">
            <button type="submit" class="btn">Sign in</button>
          </form>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">
      <div class="body-content">

        <!-- Example row of columns -->
        <div class="row">
          <div class="investmentCalculator">
            <h2>Investment calculator</h2>
            <div>
            	<div>
            		Quotes
            	</div>

            	<div>
            		
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
		
        <hr/>

        <footer>
          <p>&copy; CSPInformatique 2013</p>
        </footer>
      </div>

    </div> <!-- /container -->

    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    
    <script src="resources/js/tools.js"></script>
  </body>
</html>