<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/news/news_manager_list.js"></script>

<div class="frameMainSub frameMs12 fullScreen">
<input type="hidden" id="channelId" value="${param.channelId}" />
	<form id="cmsNewsSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
			<li><label><spring:message code="cmsNewsForm.title" />:</label>
						<input type="text" name="title" value="" class="sysicon">
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

<div id="epsTabs">
  <ul>
    <li>
      <a href="#newsListPage" class="refreshData"><span>待审核</span></a>
    </li>
    <li>
      <a href="#newsListPage"  class="refreshData"><span>已通过</span></a>
    </li>
    <li>
      <a href="#newsListPage"  class="refreshData"><span>被退回</span></a>
    </li>
  </ul>
  
  <div id="newsListPage">
  
  </div>
 </div>
	
</div>