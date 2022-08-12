<#import "_master.ftl" as layout />

<@layout.masterTemplate title="Blog" selected="last">
<div class="row">
    <div class="col-lg-8">
        
     <#list posts as post>
     <#if post["comments"]??>
        <#assign numComments = post["comments"]?size>
        <#else>
        <#assign numComments = 0>
    </#if>
     <h1><a href="/post/${post["permalink"]}">${post["title"]}</a> <small>ultima ora: ${post["count"]} <#if post["count"]==1>visita <#else>visite</#if></small></h1>
          <p class="lead">by ${post["author"]}</p>
          <hr>
          <p><span class="glyphicon glyphicon-time"></span> Postato il ${post["date"]?datetime} | 
          <span class="glyphicon glyphicon-comment"></span> Commenti: <a href="/post/${post["permalink"]}">${numComments}</a>
           | <span class="glyphicon glyphicon-tag"></span> Tags:
        <#if post["tags"]??>
            <#list post["tags"] as tag>
                <a href="/tag/${tag}">${tag}</a>
            </#list>
        </#if> 
          </p>
          <hr>
          <p>${post["body"]!""}</p>
          <a class="btn btn-primary" href="/post/${post["permalink"]}">Leggi <span class="glyphicon glyphicon-chevron-right"></span></a>
	          <hr style="border-top:3px solid #eee">
	</#list>
    <#if posts?size==0>
        <h2>Nessun post letto nell'ultima ora.</h2>
    </#if>
</div>
        
</@layout.masterTemplate>