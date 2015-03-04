<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/buyerListGrid.js"></script>
<form id="agentSearchZone" >
			<input type="hidden" name="orgId">
			<div class="conSearch">
				<ul>
					<li class="fullLine operationBtnDiv">
						<label style="width: auto;">名称:</label>
					 	<input type="text" name="orgName" value=""><input type="hidden" name="orgName_op" value="like">
				      	<button type="submit" class=""><span><spring:message code="globe.search"/></span></button>
				    </li>
				</ul>
			</div>
			<input type="hidden" name="num" id="num" value="${param.num}"/>
		</form>
<div id="agentListInfo">
   <flex:flexgrid checkbox="false"
		id="agentListGrid" url="OrgInfoController.do?method=list" queryColumns="objId"  
			searchZone="agentSearchZone" rp="10"  title="招标单位 列表" onSuccess="agentList.success"
			onSubmit="agentList.before" >
					<flex:flexCol name="orgName" display="招标单位 名称" sortable="true" width="150" align="center"></flex:flexCol>
					<flex:flexCol name="orgCode" display="招标单位编码" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="company.contact" display="联系人" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="company.tel" display="联系电话 " sortable="true" width="120" align="center"></flex:flexCol>
	</flex:flexgrid>
</div>


