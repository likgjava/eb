<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/publish_static_pages_form.js"></script>
<div id="fileTabs">
    <ul >
    <li><a href="#ftabs0" id="clickFinaFile"><span>发布系统页面</span></a></li>
    <li><a href="#ftabs1"><span>发布自定义页面</span></a></li>
    </ul>
    
   <div id="ftabs0">
   <div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
	  	<div id="channelTree" class="treeContentDiv"></div>
		<div class="treeResize" ></div>
	</div>
	<div class="treeRight frameSub">
		<div class="conOperation">
			<button type="button" name="createStaticPages" id="publicAllSelectedChannelPages"><span>发布选中的栏目静态页面</span></button>
			<button type="button" name="createStaticPages" id="publicAllSelectedChannelContentPages"><span>发布选中栏目的内容静态页面</span></button>
			<br/>
			<br/>
			<button type="button" name="createStaticPages" id="publicAllChannelPages"><span>发布所有的栏目静态页面</span></button>
			<button type="button" name="createStaticPages" id="publicAllChannelContentPages" ><span>发布所有栏目的内容静态页面</span></button>
			<br/>
			<br/>
			<button type="button" name="deleteStaticPages" id="sysPages" ><span>删除所有系统静态页面</span></button>
	   </div>
	</div>
	
    </div>
    
	</div>
	
    <div id="ftabs1">
   
<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
	  	<div id="includeTemplateTree" class="treeContentDiv"></div>
		<div class="treeResize" ></div>
	</div>
	<div class="treeRight frameSub">
		<div class="conOperation">
			<button type="button" name="createStaticPages" id="publicTemplatePages" isTemplate="true"><span>发布选中的引用静态页面</span></button>
			<br/>
			<br/>
			<button type="button" name="createStaticPages" id="publicTemplatePages" publistAll = "true" isTemplate="true"><span>发布所有的引用静态页面</span></button>
			<br/>
			<br/>
			<button type="button" name="deleteStaticPages" id="includePages" ><span>删除所有引用静态页面</span></button>
	   </div>
	</div>
	
    </div>

    </div>

</div>
