<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractList.js"></script>
<div id="epsTabs3">
	  <ul>
	  	<li>
	      <a href="#taskPlanListInfo" id = "tabs_toConfirm" class="refreshData"><span>待确认</span></a>
	    </li>
	    <li>
	      <a href="#taskPlanListInfo" id = "tabs_confirmed" class="refreshData"><span>已确认</span></a>
	    </li>
	  </ul>
   <div id="taskPlanListInfo">
		<flex:flexgrid checkbox="false"
			id="contractGrid" url="ContractController.do?method=list" queryColumns="objId"  
				searchZone="contractSearchZone" rp="10" title="合同列表" height="200"  
				onSubmit="contractList.before" onSuccess="contractList.success">
						<flex:flexCol name="contractNo" display="合同编号" sortable="true" width="100"align="left"></flex:flexCol>
						<flex:flexCol name="contractName" display="合同名称" sortable="true" width="100"align="left"></flex:flexCol>
						<flex:flexCol name="partyA.acquirer" display="买方名称" sortable="true" width="135"align="left"></flex:flexCol>
						<flex:flexCol name="secondParty.supplierName" display="卖方名称" sortable="true" width="135"align="left"></flex:flexCol>
						<flex:flexCol name="witenessParty.agentName" display="见证方名称" sortable="true" width="135"align="left"></flex:flexCol>
		</flex:flexgrid>
	</div>
</div>