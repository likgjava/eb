<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_role_list.js"></script>
   
    
   
   <div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
	  	<div id="channelTree" class="treeContentDiv"></div>
		<div class="treeResize" ></div>
	</div>
	
	<div class="treeRight frameSub" id="channelRoleTabs">
	 <ul id="">
	 	<li><a href="#channelInfoPage" >栏目简介</a></li>
	 	<li class="hidden"><a href="#channelRolePage" id="channelRoleType_00" >栏目管理员</a></li>
	 	<li class="hidden"><a href="#channelRolePage" id="channelRoleType_01" >栏目发布员</a></li>
	 	<li class="hidden"><a href="#channelRolePage" id="channelRoleType_02" >栏目审核员</a></li>
    </ul>
	<div id="channelInfoPage" class="formLayout form2Pa detail">
	<form id="channelInfoForm">
	<ul>
		<li>
				<label for="channelForm.name"><spring:message code="channelForm.name"/>：</label>
			<span id="name"></span>
		</li>
		<li>
				<label for="channelForm.chnlCode"><spring:message code="channelForm.chnlCode"/>：</label>
			<span id="chnlCode"></span>
		</li>
		<li>
				<label for="channelForm.chnlTemplate"><spring:message code="channelForm.chnlTemplate"/>：</label>
			<span id="chnlTemplate"></span>
		</li>
		<li>
				<label for="channelForm.contentTemplate"><spring:message code="channelForm.contentTemplate"/>：</label>
			<span id="contentTemplate"></span>
		</li>
		<li>
				<label for="channelForm.isAlone"><spring:message code="channelForm.isAlone"/>：</label>
			<span id="isAloneCn"></span>
		</li>
		
		<li>
				<label for="channelForm.isQuery"><spring:message code="channelForm.isQuery"/>：</label>
			<span id="isQueryCn"></span>
		</li>
		<li>
				<label for="channelForm.display"><spring:message code="channelForm.display"/>：</label>
			<span id="displayCn"></span>
		</li>
		<li>
				<label for="channelForm.filePage"><spring:message code="channelForm.filePage"/>：</label>
			<span id="filePageCn"></span>
		</li>
		<li>
				<label for="channelForm.pageSize"><spring:message code="channelForm.pageSize"/>：</label>
			<span id="pageSize"></span>
		</li>
		<li class="fullLine">
				<label for="channelForm.outUrl"><spring:message code="channelForm.outUrl"/>：</label>
			<span id="outUrl"></span>
		</li>
		<li class="fullLine">
				<label for="channelForm.specifiedPath"><spring:message code="channelForm.specifiedPath"/>：</label>
			<span id="specifiedPath"></span>
		</li>
		<li class="formTextarea eleDisable" style="height:100px">
   		<label for="channelForm.chnlMemo"><spring:message code="channelForm.chnlMemo"/>：</label>
		<textarea name="chnlMemo" id="chnlMemo" class="" ></textarea><span class="eleRequired"></span>
 	    </li>
	</ul>
	</form>
	</div>
	<div id="channelRolePage"></div>
	
    <input id="channelRoleType" type="hidden" value=""/>
    
	</div>
	</div>
	

