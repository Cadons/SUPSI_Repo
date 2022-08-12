<#import "_master.ftl" as layout />

<@layout.masterTemplate title="Blog | "+post["title"] selected="home">

<div class="row">
        <div class="col-lg-8">
        
          <h1>${post["title"]}</h1>
          <p class="lead">by ${post["author"]}</p>
          <hr>
          <p><span class="glyphicon glyphicon-time"></span> Postato il ${post["date"]?datetime} 
           | <span class="glyphicon glyphicon-tag"></span> Tags:
	        <#if post["tags"]??>
	            <#list post["tags"] as tag>
	                <a href="/tag/${tag}">${tag}</a>
	            </#list>
	        </#if> 
          </p>
          <hr>
          <p> ${post["body"]}</p>

          <hr>

          <!-- the comment box -->
          <div class="well">
            <h4>Lascia un commento:</h4>
            <br/>
            <form role="form" class="form-horizontal" action="/newcomment" method="POST">
              <input type="hidden" name="permalink", value="${post["permalink"]}">
           	  <div class="form-group">
			    <label for="commentName" class="col-sm-2 control-label">* Nome</label>
			    <div class="col-sm-6" style="margin-bottom: -30px;">
			    <input type="text" class="form-control" id="commentName" placeholder="Nome" name="commentName" value="<#if comment??>${comment["name"]}</#if>"><br>
			    </div>
			    </div>
			    <div class="form-group">
			  	<label for="commentEmail" class="col-sm-2 control-label">Email</label>
			  	<div class="col-sm-6" style="margin-bottom: -30px;">
			    <input type="text" class="form-control"  id="commentEmail" name="commentEmail" placeholder="Email" value="<#if comment??>${comment["email"]}</#if>"><br>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="commentBody" class="col-sm-2 control-label">* Commento</label>
			    <div class="col-sm-10">
			    <textarea name="commentBody" rows="3" class="form-control"><#if comment??>${comment["body"]}</#if></textarea>
			    </div>
              </div>
              <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Submit</button> <span style="color:red">${errors!""}</span>
    </div>
  </div>
        
            </form>
           
          </div>
          
          <hr>

          <!-- the comments -->
          <#if post["comments"]??>
		        <#assign numComments = post["comments"]?size>
		            <#else>
		                <#assign numComments = 0>
		    </#if>
          <#if (numComments > 0)>
	        <#list 0 .. (numComments -1) as i>
	            <h3>${post["comments"][i]["author"]} <#if post["comments"][i]["email"]??><small>${post["comments"][i]["email"]}</small></#if></h3>
          		<p>${post["comments"][i]["body"]}</p>
          		<hr />
	        </#list>
	    </#if>
          
        </div>


        
</@layout.masterTemplate>
