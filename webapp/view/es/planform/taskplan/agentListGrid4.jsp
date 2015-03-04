<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/agentListGrid4.js"></script>
<form id="agentSearchZone" >
			<input type="hidden" name="orgId">
			<div class="conSearch">
				<ul>
					<li class="fullLine operationBtnDiv">
						<label style="width: auto;">名称:</label>
						<input type="text" name="orgInfo.orgName" value=""><input type="hidden" name="orgInfo.orgName_op" value="like">
				      	<button type="submit" class=""><span><spring:message code="globe.search"/></span></button>
				    </li>
				</ul>
			</div>
			<input type="hidden" name="num" id="num" value="${param.num}"/>
		</form>
<div id="agentListInfo">
   <flex:flexgrid checkbox="false"
		id="agentListGrid" url="AgencyController.do?method=list" queryColumns="orgInfo.objId"  
			searchZone="agentSearchZone" rp="10"  title="招标中心列表" onSuccess="agentList.success"
			onSubmit="agentList.before">
					<flex:flexCol name="orgInfo.orgName" display="名称" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="unitDesc" display="资质" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="regPrctsNmbr" display="从业人数" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="regCapital" display="注册资金" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="regCoporate" display="注册法人" sortable="true" width="120" align="center"></flex:flexCol>
	</flex:flexgrid>
</div>


