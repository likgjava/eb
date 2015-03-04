<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/service_charge_money_confirm.js"></script>
<div class="formLayout" >         
  <div class="partContainers operationLog"><h5 id="beginAffirm"><span class="switch left11">投标单位确认</span></h5></div>
	<div id="affirmProcess">             
<flex:flexgrid usepager="false" checkbox="false" id="serviceChargeMoneyConfirmGrid" url="view/esdemo/data/projectmanager/service_charge_comfirm.txt" queryColumns=""  
			searchZone="serviceChargeMoneyConfirmSearchZone" rp="10" title="" width="1000" height="100" onSubmit="serviceChargeMoneyConfirm.before" onSuccess="serviceChargeMoneyConfirm.success">
		 <flex:flexCol name="packNo" display="包号" sortable="true" width="100"align="left"></flex:flexCol> 
	 <flex:flexCol name="packName" display="包名" sortable="true" width="100"align="left"></flex:flexCol> 
	  <flex:flexCol name="supplier" display="投标单位名称" sortable="true" width="200"align="left"></flex:flexCol>
	 <flex:flexCol name="count" display="中标金额" sortable="true" width="100"align="left"></flex:flexCol>
	 <flex:flexCol name="actualStartTime" display="中标服务费" sortable="true" width="100"align="left"></flex:flexCol>
	 <flex:flexCol name="status" display="签订状态" sortable="true" width="100"align="left"></flex:flexCol>
    </flex:flexgrid> 
 </div> 
 </div>


