<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.areaClass{width:70%;margin-left:1px;height:60px;}
.showAgency{display: none;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/resproject/resProjectInfoForm.js"></script>
<div class="paper"> 
<p class="headInfo">&nbsp;</p>
<form id="resProjectInfoForm" method="post" >
<input type="hidden" id="resProjectId" value="${resProject.objId}">
	<div>
		<h5 align="center"><span>项目基本情况</span></h5>
		<table class="tableListP">
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">招标人性质：</th>
			 	<td height="25" >
			 		<span>${resProject.unitPropertyCN}</span>
					<input type="hidden" name="unitProperty" id="unitProperty" value="${resProject.unitProperty}"/>
			   	</td>
			   	<th width="10%" height="25" align="center" valign="middle">招标项目属性：</th>
			 	<td height="25" colspan="3">
			 		<c:choose>
						<c:when test="${resProject.projectType==00}">基础设施</c:when>
						<c:otherwise>
						公用事业
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="projectType" id="projectType" value="${resProject.projectType}"/>
			   	</td>
			</tr>
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">计划批文：</th>
			 	<td height="25" >
			 		<span>${resProject.projApproval}</span>
					<input type="hidden" name="projApproval" id="projApproval" value="${resProject.projApproval}"/>
			   	</td>
			   	<th width="10%" height="25" align="center" valign="middle">规划许可证号：</th>
			 	<td height="25" colspan="3">
			 		<span>${resProject.planPermit}</span>
					<input type="hidden" name="planPermit" id="planPermit" value="${resProject.planPermit}"/>
			   	</td>
			</tr>
			<tr>
				<th width="10%" height="25" align="center" valign="middle">招标项目性质：</th>
			 		<td height="25" >
			 		<span><c:choose>
						<c:when test="${resProject.projNature==00}">新建</c:when>
						<c:when test="${resProject.projNature==01}">扩建</c:when>
						<c:otherwise>
						改建
						</c:otherwise>
					</c:choose></span>
					<input type="hidden" name="projNature" id="projNature" value="${resProject.projNature}"/>
			   	</td>
			 	<th width="10%" height="25" align="center" valign="middle">项目总投资额（元）：</th>
			 	<td height="25" >
			 		<span><fmt:formatNumber type="currency" value="${resProject.amt}"/></span>
			   	</td>
			</tr>
			<tr>
			   	<th width="10%" height="25" align="center" valign="middle">土地证号：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
					<input type="text" name="landNo" id="landNo" value="${resProject.landNo}" size="30" class="required"/>
			   	</td>
			</tr>
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">建设工程分类：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
					<input type="radio" name="engProjType" class="required" value="00" <c:if test="${resProject.engProjType=='00'}">checked="checked"</c:if>/>房屋建筑工程&nbsp;&nbsp;
					<input type="radio" name="engProjType" class="required" value="01" <c:if test="${resProject.engProjType=='01'}">checked="checked"</c:if>/>市政公用设施
			   	</td>
			</tr>
			<tr >
			 	<th width="10%" height="25" align="center" valign="middle">是否比选代理机构：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
					<input type="radio" name="isVoting" class="required" value="00" onclick="resProjectInfo.showAgency('YES');" <c:if test="${resProject.isVoting=='00'}">checked="checked"</c:if>/>否&nbsp;&nbsp;
					<input type="radio" name="isVoting" class="required" value="01" onclick="resProjectInfo.showAgency('NO');" <c:if test="${resProject.isVoting=='01'}">checked="checked"</c:if>/>是
			   		<input type="hidden" id="isVotingId" value="${resProject.isVoting}"/>
			   	</td>
			</tr>
			<tr id="showAgency" class="showAgency">
		   		<th width="10%" height="25" align="center" valign="middle">代理机构：<span class="eleRequired">(*必填项)</span></th>
		 		<td height="25" colspan="3">
				<input type="text" name="agenty.orgName" id="agencyName" size="25"/>
	    		<input type="hidden" name="agency.objId" id="agencyId"/>
		   		</td>
			</tr>
		</table>
	</div>
</form>
<div class="conOperationBtnDiv">
	<input type="hidden" id="resProjectId" value="${resProject.objId}"/>
	<button id="save" class="subBtn" type="button" tabindex="18"><span>提交</span></button>
	<button id="back" class="subBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
</div> 
<div id="resProjectInfoItem"></div>
