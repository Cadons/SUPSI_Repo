<#import "_master.ftl" as layout />

<@layout.masterTemplate title="Blog | Registrazione" selected="home">


<div class="row">
    <div class="col-lg-8">
    
        <h3 style="padding-bottom:30px">Registrati</h3>
        <form class="form-horizontal" role="form" method="post">
            <div class="form-group">
				<label for="username" class="col-sm-2 control-label">* Utente</label>
                <div class="col-sm-6">
                	<input type="text" name="username" value="<#if username??>${username}</#if>" class="form-control" placeholder="Nome utente" id="username">
                	<#if username_error??><span style="color:red">${username_error!""}<span></#if>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">* Password</label>
                <div class="col-sm-6">
                	<input type="password" name="password" value="" class="form-control" placeholder="Password" id="password">
                	<#if password_error??><span style="color:red">${password_error!""}<span></#if>
                </div>
            </div>
            <div class="form-group">
                <label for="verify" class="col-sm-2 control-label">* Password</label>
                <div class="col-sm-6">
                	<input type="password" name="verify" value="" class="form-control" placeholder="Verifica password" id="verify">
                	<#if verify_error??><span style="color:red">${verify_error!""}<span></#if>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-6">
                	<input type="text" name="email" value="<#if email??>${email}</#if>" class="form-control" placeholder="Email" id="email">
                	<#if email_error??><span style="color:red">${email_error!""}<span></#if>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Registrati</button>
                </div>
            </div>
        	<p style="margin-top: 40px;" class="col-sm-offset-2">
	            Già registrato? <a href="/login">Login</a><p>
	        </p> 
    </div>
</@layout.masterTemplate>