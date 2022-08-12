<#macro masterTemplate title="Elevator" selected="">

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="/assets/css/bootstrap.css" rel="stylesheet">

    <!-- Add custom CSS here -->
    <link href="/assets/css/blog-home.css" rel="stylesheet">
    
  </head>

  <body>
  <#--
  			<#assign navElements = ["Home", "Modelli", "Ascensori", "Tecnici", "Interventi"] />

			<div class="row">
				<div class="span3 bs-docs-sidebar">
					<ul class="nav nav-list bs-docs-sidenav">
						<#list navElements as navElement>
							<li <#if selected != "" && selected?lower_case?starts_with(navElement?lower_case) || (request.uri?lower_case?starts_with("/"+navElement?lower_case))>class="active"</#if>>
								<a href="/${navElement}">
									<i class="icon-chevron-right"></i>
									${navElement}
								</a>
							</li>
						</#list>
					</ul>
				</div>
				<div class="span9">
					<#nested />
				</div>
			</div> -->

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">Blog</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li <#if selected != "" && selected?lower_case?starts_with("home")>class="active"</#if>>
            	<a href="/">Home</a>
            </li>
            <#if sessionUser??>
            	<li <#if selected != "" && selected?lower_case?starts_with("newpost")>class="active"</#if>><a href="/newpost">Nuovo post</a></li>
            </#if>
          </ul>
          <#if sessionUser??>
          	<ul class="nav navbar-nav navbar-right">
		      <li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ${sessionUser} <b class="caret"></b></a>
		        <ul class="dropdown-menu">
		          <li><a href="#" style="color:#ccc">Profilo</a></li>
		          <li class="divider"></li>            
		          <li><a href="/logout">Logout</a> </li>
		        </ul>
		      </li>
		    </ul>
          <#else>
          <form class="navbar-form navbar-right" style="padding:0;margin-right: -15px;">
          	<a href="/signup" class="btn btn-default">Registrati</a>
          	<a href="/login" class="btn btn-primary">Login</a>
	      </form>
	      </#if>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav>

    <div class="container">
	  <#nested />
      
      <div class="col-lg-4">
          
          <div class="well">
            <h4>Blog Search</h4>
            <div class="input-group">
              <input type="text" class="form-control" disabled>
              <span class="input-group-btn">
                <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
              </span>
            </div><!-- /input-group -->
          </div><!-- /well -->
          <div class="well">
            <h4>Blog</h4>
            <p>Questo è un blog. Ed usa <a href="http://www.mongodb.com/">mongoDB</a>.</p>
          </div><!-- /well -->
        </div>
      </div>
      
            
      <hr>
      
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <p>Copyright &copy; Blog 2019</p>
          </div>
        </div>
      </footer>

    </div><!-- /.container -->

    <!-- JavaScript -->
    <script src="/assets/js/jquery-1.10.2.js"></script>
    <script src="/assets/js/bootstrap.js"></script>

  </body>
</html>
</#macro>