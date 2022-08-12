<#import "_master.ftl" as layout />

<@layout.masterTemplate title="Blog | nuovo post" selected="newpost">



<div class="row">
    <div class="col-lg-8">
    
        <h3 style="padding-bottom:30px">Nuovo post</h3>
        <form class="form-horizontal" role="form" action="/newpost" method="POST">
            <div class="form-group">
				<label for="subject" class="col-sm-2 control-label">* Titolo</label>
                <div class="col-sm-10">
                	<input type="text" name="subject" value="${subject!""}" class="form-control" placeholder="Titolo" id="subject">
                </div>
            </div>
            <div class="form-group">
			    <label for="body" class="col-sm-2 control-label">* Testo</label>
			    <div class="col-sm-10">
			    <textarea name="body" rows="15" class="form-control">${body!""}</textarea>
			    </div>
              </div>
            <div class="form-group">
                <label for="tags" class="col-sm-2 control-label">Tags</label>
                <div class="col-sm-10">
                	<input type="text"class="form-control" name="tags" size="120" value="${tags!""}" placeholder="tag1, tag2" id="tags"><br>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Pubblica</button> <#if errors??><span style="color:red">${errors}</span></#if>
                </div>
            </div>
    </div>
        
</@layout.masterTemplate>

