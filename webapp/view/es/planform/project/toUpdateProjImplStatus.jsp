<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
.btn_2k3 {
BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #002D96 1px solid
}
.btn {
BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid
}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/toUpdateProjImplStatus.js"></script>       
		<input type="hidden" name="objId" id="objId" value="${project.objId }"/>
		<input type="hidden" name="projImplStatus" id="projImplStatus" value="${project.projImplStatus }"/>
		<h5><span id="s">
		当前项目状态：
		<c:choose>
		<c:when test="${project.projImplStatus==00}">正常</c:when>
		<c:when test="${project.projImplStatus==01}">暂停中</c:when>
		<c:otherwise>已终止</c:otherwise>
		</c:choose>
		</span></h5>
		<ul>
				<li>
				<c:choose>
				<c:when test="${project.projImplStatus==00}">
				<button id="projectSave1" type="button" tabindex="15" class="btn_2k3"><span>暂停项目</span></button>
				<button id="projectSave0" type="button" tabindex="15" disabled="disabled" class="btn"><span>恢复项目</span></button>
				<button id="projectSave2" type="button" tabindex="15" class="btn_2k3"><span>终止项目</span></button>
				</c:when>
				<c:when test="${project.projImplStatus==01}">
				<button id="projectSave1" type="button" tabindex="15" disabled="disabled" class="btn"><span>暂停项目</span></button>
				<button id="projectSave0" type="button" tabindex="15" class="btn_2k3"><span>恢复项目</span></button>
				<button id="projectSave2" type="button" tabindex="15" class="btn_2k3"><span>终止项目</span></button>
				</c:when>
				<c:otherwise>
				<button id="projectSave1" type="button" tabindex="15" disabled="disabled" class="btn"><span>暂停项目</span></button>
				<button id="projectSave0" type="button" tabindex="15" disabled="disabled" class="btn"><span>恢复项目</span></button>
				<button id="projectSave2" type="button" tabindex="15" disabled="disabled" class="btn"><span>终止项目</span></button></c:otherwise>
				</c:choose>
				</li>
				<li>
				<div id="updateProjectTime"></div>
				</li>
				<li>
				 <div id="updateProjectEvalTime"></div>
				</li>
		</ul>
          
         
        
     
