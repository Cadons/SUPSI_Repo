<#import "_master.ftl" as layout />

<@layout.masterTemplate title="Blog | Login" selected="home">


<div class="row">
    <div class="col-lg-8">
    
        <h3 style="padding-bottom:30px">Login</h3>
        <form class="form-horizontal" role="form" method="post">
            <div class="form-group">
				<label for="username" class="col-sm-2 control-label">Utente</label>
                <div class="col-sm-6">
                	<input type="text" name="username" value="<#if username??>${username}</#if>" class="form-control" placeholder="Nome utente" id="username">
                </div>
            </div>
            <div class="form-group">
                
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-6">
                	<input type="password" name="password" value="" class="form-control" placeholder="Password" id="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Entra</button> <#if login_error??><span style="color:red">${login_error}</span></#if>
                </div>
            </div>
        	<p style="margin-top: 40px;" class="col-sm-offset-2">
	            Devi registrarti? <a href="/signup">Registrati</a><p>
	        </p> 
    </div>
        
</@layout.masterTemplate>
