<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<div id="sysContent">
   
	<div class="" id="contentSub">
	<ul>
		<li class="selected">
			<a href="#"  class="icon1"><span></span>模板管理</a>
	
			<ul class="subnav">
			<li><a href="TemplateFileController.do?method=toResourceFileList" target="#conBody" ">资源管理</a></li>
			<li><a href="TemplateFileController.do?method=toResourceFileList" target="#conBody" ">资源管理</a></li>
			<li><a href="TemplateFileController.do?method=toResourceFileList" target="#conBody" ">资源管理</a></li>
			</ul>
	</ul>
	</div>

    <div id="contentMain" class="management width">
    <div id="conBody">
    
      <div class="navCurrent">
       <span class="highLight">当前位置：</span><@f.position/>
      </div>
      
       <div class="frameNews">
	       <h1>${news.title}</h1>
	      <p class="subtitle">发布时间： 　发布人：${news.origin!}-${news.author!}  　访问次数：${news.visitCount!}</p>
	      ${content!}
       </div>
       
     </div>
    
	</div>
</div>
