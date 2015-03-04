<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style  type="text/css">
.spanFont{font: 0.9em/0.9em;font-size: 12;margin-left: 2px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/projectInfo.js"></script>
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="${project.ebuyMethodCN }" id="ebuyMethodCN"/>
<div id="project" class="partContainers">
	<div class="formLayout form2Pa">
		<h5><span>项目信息</span></h5>
		<ul>
			<li>
				<label class="short" ><spring:message code="projectForm.projCode"/>：</label>
				${project.projCode}
			</li>
		</ul>
		<ul>
			<li>
				<label class="short" ><spring:message code="projectForm.projName"/>：</label>
				${project.projName}
			</li>
		</ul>
	</div>		
	<div class="conOperation" style="text-align: left">
        <button class="button" type="button" tabindex="18" onclick="alert('暂未实现！')"><span>暂停项目</span></button>
        <button class="button" type="button" tabindex="19" onclick="alert('暂未实现！')"><span>终止项目</span></button>
        <button class="button" type="button" tabindex="20" onclick="alert('暂未实现！')"><span>重启项目</span></button>
        <button class="button" type="button" tabindex="21" onclick="alert('暂未实现！')"><span>删除项目</span></button>
    </div>	
	<table class="tableList" id="inrqDetailList">
		<thead>
			<tr>
				<th>功能点名称</th>
				<th>操作</th>
				<th>功能点名称</th>
				<th>操作	</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>分配组织处经办人</td>
				<td class="center">没有权限</td>
				<td>分配评审处经办人</td> 
				<td class="center">没有权限</td>
			</tr>
			<tr>
				<td>发布变更公告</td>
				<td class="center">
				<authz:authorize ifAnyGranted="xmba-fbbggg">
				   <c:set var="fbbggg" value="Y"/>
				</authz:authorize>
				<c:choose>
				<c:when test="${fbbggg == 'Y'}">
				<span><a href="#" onclick="ProjectInfoView.releaseChangeBulletin()"  class="abtn">进入</a></span>
				</c:when>
				<c:otherwise>没有权限</c:otherwise>
				</c:choose>
				</td>
				<td>设置开标小组</td> 
				<td class="center">
				<authz:authorize ifAnyGranted="xmba-szkbxz">
				   <c:set var="szkbxz" value="Y"/>
				</authz:authorize>
				<c:choose>
				<c:when test="${szkbxz == 'Y'}">
				<span><a href="#" onclick="ProjectInfoView.setOpenBidGroup()"  class="abtn">进入</a></span>
				</c:when>
				<c:otherwise>没有权限</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td>待办提示</td>
				<td class="center">没有权限</td>
				<td>发送公告邮件</td> 
				<td class="center">没有权限</td>
			</tr>
			<tr>
				<td>查看质疑</td>
				<td class="center">
				<authz:authorize ifAnyGranted="xmba-ckzy">
				   <c:set var="ckzy" value="Y"/>
				</authz:authorize>
				<c:choose>
				<c:when test="${ckzy == 'Y'}">
				<span><a href="#" onclick="ProjectInfoView.lookOppugnReqAgency()" class="abtn">进入</a></span>
				</c:when>
				<c:otherwise>没有权限</c:otherwise>
				</c:choose>
				</td>
				<td>查看开标一览表</td> 
				<td class="center">
				<authz:authorize ifAnyGranted="xmba-ckkbylb">
				   <c:set var="ckkbylb" value="Y"/>
				</authz:authorize>
				<c:choose>
				<c:when test="${ckkbylb == 'Y'}">
				<span><a href="#" onclick="ProjectInfoView.lookOpenbidGeneral()" class="abtn">进入</a></span>
				</c:when>
				<c:otherwise>没有权限</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td>查看历史</td>
				<td class="center">
				<authz:authorize ifAnyGranted="xmba-ckls">
				   <c:set var="ckls" value="Y"/>
				</authz:authorize>
				<c:choose>
				<c:when test="${ckls == 'Y'}">
				<span><a href="#" onclick="ProjectInfoView.lookHistory()" class="abtn">进入</a></span>
				</c:when>
				<c:otherwise>没有权限</c:otherwise>
				</c:choose>
				</td>
				<td>起草结果公示</td> 
				<td class="center">
				<authz:authorize ifAnyGranted="xmba-qcjggs">
				   <c:set var="qcjggs" value="Y"/>
				</authz:authorize>
				<c:choose>
				<c:when test="${qcjggs == 'Y'}">
				<span><a href="#" onclick="ProjectInfoView.draftResultPublicity()" class="abtn">进入</a></span>
				</c:when>
				<c:otherwise>没有权限</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td>网上投标、开标</td>
				<td class="center" colspan="3"><input type="radio" name="bid" checked="checked">是<input type="radio" name="bid">否</td>
			</tr>
			<tr>
				<td>删除待办</td>
				<td class="center">没有权限</td>
				<td>上传投标文件</td> 
				<td class="center">没有权限</td>
			</tr>			
			<tr>
				<td>调整项目时间</td>
				<td class="center">
				<authz:authorize ifAnyGranted="xmba-tzxmsj">
				   <c:set var="tzxmsj" value="Y"/>
				</authz:authorize>
				<c:choose>
				<c:when test="${tzxmsj == 'Y'}">
				<span><a href="#" onclick="ProjectInfoView.setProjectTime()" class="abtn">进入</a></span>
				</c:when>
				<c:otherwise>没有权限</c:otherwise>
				</c:choose>
				</td>
				<td>查看公告同步日志</td> 
				<td class="center">没有权限</td>
			</tr>			
		</tbody>
	</table>
</div>