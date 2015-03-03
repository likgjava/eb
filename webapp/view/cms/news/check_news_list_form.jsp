<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/news/check_news_list_form.js"></script>

<div class="frameMainSub frameMs12 fullScreen ">
<input type="hidden" id="channelId" value="${param.channelId}" />
	<form id="cmsNewsSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
			<li><label><spring:message code="cmsNewsForm.title" />:</label>
						<input type="text" name="title" value="" class="">
						<input type="hidden" name="title_op" value="like">
					</li>
					<li><label><spring:message code="cmsNewsForm.channel" />:</label>
						  <input type="hidden" name="channel.objId" value="" class="sysicon autoSearch">
						  <input type="text" id="channel.name" value="" class="sysicon autoSearch">
						  <input type="hidden" name="channel.objId_op" value="like">
					</li>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="cmsNewsGrid" url="CmsNewsController.do?method=list&roleType=02" queryColumns="channel.objId,channel.url,url,channel.checkCount"  
			searchZone="cmsNewsSearchZone" rp="10" title="公告"
			onSubmit="cmsNewsList.before" onSuccess="cmsNewsList.success" >
					<flex:flexCol name="title" display="cmsNewsForm.title" sortable="true" width="200"align="left"></flex:flexCol>
					<flex:flexCol name="channel.name" display="cmsNewsForm.channel" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="checkStatus" alias="checkStatusCn" display="状态" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="createUser.usName" display="cmsNewsForm.createUser" sortable="true" width="80"align="left"></flex:flexCol>
					<flex:flexCol name="createTime" display="cmsNewsForm.createTime" sortable="true" width="140"align="left"></flex:flexCol>
		<flex:flexBtn name="通过" bclass="enable" onpress="cmsNewsList.pass"></flex:flexBtn>	
		<flex:flexBtn name="退回" bclass="warn" onpress="cmsNewsList.back"></flex:flexBtn>	
		<flex:flexBtn name="直接退回" bclass="disable" onpress="cmsNewsList.backAll"></flex:flexBtn>	
	</flex:flexgrid>
	
</div>