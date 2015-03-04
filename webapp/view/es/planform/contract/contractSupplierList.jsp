<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractSupplierList.js"></script>
 <style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="partContainers" style="width: 100%">
<!-- 查询条件 -->
<input type="hidden" id="role" value="${role}">
<form id="contractSearchZone">
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
	    <ul>
	      <li>
	        <label>合同编号：</label>
	        <input type="hidden" value="${orgInfoId }" id="cupplierId">
			<input name="contractNo" type="text" >	
			<input type="hidden" name="contractNo_op" value="like">
	      </li>
	      <li>
	        <label for="contractName">合同名称：</label>
		  	<input name="contractName" type="text" >	
		  	<input name="contractName_op" type="hidden" value="like">		
	      </li>
	      <li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
		  </li>
	    </ul>
</div>
</form>
<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#contractInfo" id = "tabs_submit" class="refreshData"><span>待提交合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_todo" class="refreshData"><span>待确认合同</span></a>
    </li>
      <li>
      <a href="#contractInfo" id = "tabs_notpass" class="refreshData"><span>被退回合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_done" class="refreshData"><span>已签订合同</span></a>
    </li>
  </ul>
  <div id="contractInfo">
    <flex:flexgrid checkbox="true"
		id="ContractSupplierGrid" url="ContractController.do?method=list&order=secondParty.supplierSignedTime&order_flag=true" queryColumns="cupplierId"  
			searchZone="contractSearchZone" rp="10"  title="合同列表" height="258" minGridHeight="250"
			onSubmit="ContractSupplierList.before" onSuccess="ContractSupplierList.success">
			<flex:flexCol name="contractNo" display="合同编号" sortable="true" width="100" align="center"></flex:flexCol>
			<flex:flexCol name="contractName" display="合同名称" sortable="true" width="100" align="left"></flex:flexCol>
			<flex:flexCol name="secondParty.supplierName" display="投标单位" sortable="true" width="100" align="center"></flex:flexCol>
			<flex:flexCol name="partyA.acquirer" display="招标单位" sortable="true" width="100" align="center"></flex:flexCol>
			<flex:flexCol name="secondParty.supplierSignedTime" format="date" display="签订时间" sortable="true" width="100" align="center"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>
</div>