<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/agentListGrid3.js"></script>
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
			<input type="hidden" name="supplierIds" id="supplierIds" />
		</form>
<div id="agentListInfo">
   <flex:flexgrid checkbox="false"
		id="agentListGrid" url="SupplierController.do?method=list&orgInfo.company.status=1" queryColumns="orgInfo.objId,objId"  
			searchZone="agentSearchZone" rp="10"  title="投标单位列表" onSuccess="agentList.success"
			onSubmit="agentList.before" >
					<flex:flexCol name="orgInfo.orgName" display="投标单位名称" sortable="true" width="100" align="center"></flex:flexCol>
					<flex:flexCol name="orgInfo.company.mobilePhone" display="联系方式" sortable="true" width="100" align="center"></flex:flexCol>
					<flex:flexCol name="unitAddress" display="经营地址" sortable="true" width="150" align="center"></flex:flexCol>
	</flex:flexgrid>
</div>