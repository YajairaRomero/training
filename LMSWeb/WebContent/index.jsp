<%@ include file="menu.jsp" %>
<body>
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Welcome to the GCIT Library Management System</h1>
        <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
        <p><a class="btn btn-primary btn-lg" href="javascript:aboutLMS();" role="button">Learn more »</a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2>Administrator</h2>
          <p>Admins can Create, Update and Delete Inventory in the Library System</p>
          <p><a class="btn btn-default" href="admin.jsp" role="button">Proceed »</a></p>
        </div>
        <div class="col-md-4">
          <h2>Librarian</h2>
          <p> Update library details, such as branch name, address, and number of book copies available. </p>
          <p><a href="listBranches.jsp" class="btn btn-default" role="button" data-target="#myModal" data-toggle="modal">View details »</a></p>
       </div>
        <div class="col-md-4">
          <h2>Borrower</h2>
          <p> Check out books available at library branches or return a book.</p>
          <p><a class="btn btn-default" href="http://getbootstrap.com/examples/jumbotron/#" role="button">View details »</a></p>
        </div>
      </div>

	<!-- Event Modal -->
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
	
      <hr>

      <footer>
        <p>© Company 2014</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./scripts/jquery.min.js"></script>
    <script src="./scripts/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./scripts/ie10-viewport-bug-workaround.js"></script>
  

</body>