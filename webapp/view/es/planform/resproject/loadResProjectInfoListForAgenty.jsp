<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<%@page import="com.gpcsoft.srplatform.auth.domain.User"%>
<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%>
<style>a.abtn {text-decoration:underline}</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/resproject/loadResProjectInfoListForAgenty.js"></script>

<flex:flexgrid height="450" id="resProjectListGrid" url="ResProjectController.do?method=loadResProjectInfoListForAgenty" 
queryColumns="isLeaf" searchZone="resProjectSeachZone" rp="15"  title="委托建设项目列表 " onSuccess="resProjectList.success"
onSubmit="resProjectList.loadInit" >
					<flex:flexCol name="projectName" display="委托建设项目名称" sortable="true" width="250" align="center"></flex:flexCol>
					<flex:flexCol name="agentyLinker" display="项目经理" sortable="true" width="100" align="center"></flex:flexCol>
					<flex:flexCol name="budgetName" display="业主单位" sortable="true" width="140" align="center"></flex:flexCol>
					<flex:flexCol name="budgetLinker" display="业主单位负责人" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="objId" display="操作" sortable="true" width="400"align="center"></flex:flexCol>
					<c:if test="${param.projectStatus =='00'}">
					<flex:flexBtn name="globe.new" bclass="add" onpress="resProjectList.add"></flex:flexBtn>
					</c:if>
	</flex:flexgrid>
