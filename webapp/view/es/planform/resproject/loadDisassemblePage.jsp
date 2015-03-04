<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.areaClass{width:70%;margin-left:1px;height:60px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/resproject/loadDisassemblePage.js"></script>
<div class="formLayout form2Pa">
<h5 align="center"><span>委托建设项目基本信息</span></h5>
<input type="hidden" id="parentId" value="${parent.objId }" >
<table class="tableList">
<tr>
	<th><label class="short"  for="projectName">委托建设项目名称：&nbsp;<span class="eleRequired">*</span></label></th>
	<td colspan="3">${parent.projectName }&nbsp;</td>		
</tr>
</table>
</div>
<flex:flexgrid id="resProjectSubListGrid" height="450" url="ResProjectController.do?method=loadResProjectOfSubList" 
	queryColumns="" rp="15"  title="子项目列表 " onSuccess="resProjectSubList.success"
	onSubmit="resProjectSubList.loadInit" >
						<flex:flexCol name="projectName" display="委托建设项目名称" sortable="true" width="250" align="center"></flex:flexCol>
						<flex:flexCol name="agentyLinker" display="项目经理" sortable="true" width="100" align="center"></flex:flexCol>
						<flex:flexCol name="budgetName" display="业主单位" sortable="true" width="140" align="center"></flex:flexCol>
						<flex:flexCol name="budgetLinker" display="业主单位负责人" sortable="true" width="120" align="center"></flex:flexCol>
						<flex:flexCol name="objId" display="操作" sortable="true" width="400"align="center"></flex:flexCol>
						<c:if test="${parent.projectStatus =='00' or parent.projectStatus =='01'}">
						<flex:flexBtn name="globe.new" bclass="add" onpress="resProjectSubList.add"></flex:flexBtn>
						</c:if>
		</flex:flexgrid>
<div class="conOperationBtnDiv">
	<button id="back" class="subBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
