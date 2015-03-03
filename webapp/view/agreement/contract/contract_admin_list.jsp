<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>


<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="contractForm">
	    <ul>
	      <li>
	        <label>合同编号：</label>
			<input name="contractNo" type="text" >	
			<input name="contractNo_op" type="hidden" value="like">	
	      </li>
	      <li>
	        <label for="contractName">合同名称：</label>
		  	<input name="contractName" type="text" >	
		  	<input name="contractName_op" type="hidden" value="like">		
	      </li>
	      <li>
		  		<label for="supplier.orgName">供应商：</label>
		  		<input name="supplier.orgName" type="text" >
		  		<input name="supplier.orgName_op" type="hidden" value="like">
		  </li>
		  <li>
		  		<label for="contractSignedTime">签订时间：</label>
		  		<input name="startDate" id="startDate">&nbsp;&nbsp;到
	            <input name="endDate" id="endDate">
		  	</li>
	      <li class="operationBtnDiv right">
	       	<button type="button" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
<input name="currentTabID" id="currentTabID"  type="hidden" value="<c:out value="${param.currentTabID}"/>" >
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
    <li>
      <a href="#contractInfo" id = "tabs_tocancel" class="refreshData"><span>待确认作废合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_cancel" class="refreshData"><span>作废合同</span></a>
    </li>
  </ul>
  <div id="contractInfo">
    <!-- 合同列表 -->
    <table class="frontTableList" id="ContractAdminList">
      <thead>
      	<tr>
          <th class="center">合同编号</th>
          <th class="left">合同名称</th>
          <th class="left omission" >采购商</th>
          <th class="left omission" >供应商</th>
          <th class="amount">合同总额</th>
          <th class="date">签订时间</th>
          <th class="center">合同文件</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
 
 <script>
//定义文件全局变量 处理方法名重复问题
 var ContractAdminList={};
 ContractAdminList.oTable;	

 ContractAdminList.currentTabID = "tabs_submit";

 // 生成列表“查看”超链接
 ContractAdminList.getOperatorStr=function(objId){
 		return '<td class="operation"><a class="aLink" href="javascript:void(0);" onclick="ContractAdminList.openOperatorPage(\'view\',\''+objId+'\');return false;"><span>查看</span></a></td>';
 }

 //根据不同的操作，导向不同的页面
 ContractAdminList.openOperatorPage=function(type,objId){
	
 	if("view" == type){
 		$("#returnUrl").val($("#initPath").val()+"/AgContractController.do?method=toContractAdminList&currentTabID="+ ContractAdminList.currentTabID );
 		$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDetail&forType=admin&objId="+objId + "&currentTabID="+ContractAdminList.currentTabID);
 	} else if("downFile" == type){
 		//下载合同
 		$.epsDialog({
 	        title:'合同文件',
 			url:$('#initPath').val()+'/view/agreement/contract/contract_file_div.jsp?attachRelaId='+objId
 	  });
 	}
 }

 //查询
 ContractAdminList.reload=function(){
 	if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
 		$(ContractAdminList.oTable.dataTableSettings).attr("params",
 				$.extend(ContractAdminList.oTable.dataTableSettings[0].params,{"contractSignedTime":$("#startDate").val(),"contractSignedTime_op":"ge"}));
 	}
 	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
 		$(ContractAdminList.oTable.dataTableSettings).attr("params",
 				$.extend(ContractAdminList.oTable.dataTableSettings[0].params,{"contractSignedTime":$("#endDate").val(),"contractSignedTime_op":"le"}));
 	}
 	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
 		$(ContractAdminList.oTable.dataTableSettings).attr("params",
 				$.extend(ContractAdminList.oTable.dataTableSettings[0].params,{"contractSignedTime":$("#startDate").val()+","+$("#endDate").val(),"contractSignedTime_op":"bt"}));
 	}
 	ContractAdminList.oTable.fnDraw();
 }



 $(document).ready(function(){	
 	//加载tabs
 	var $tabs = $('#epsTabs').tabs({}); 
 	
 	//tabs的点击事件
 	$("li a.refreshData").click(function(){
 		ContractAdminList.currentTabID = $(this).attr("id");
 		if ("tabs_submit"==ContractAdminList.currentTabID) {
 			$(ContractAdminList.oTable.dataTableSettings).attr("params", {useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"00","tabsType":"submit","orgType":"admin"});
 		} else if ("tabs_todo"==ContractAdminList.currentTabID) {
 			//$(ContractAdminList.oTable.dataTableSettings).attr("params", {useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"01","tabsType":"todo","orgType":"admin"});
 			$(ContractAdminList.oTable.dataTableSettings).attr("params", {useStatus:"00","tabsType":"todo","orgType":"admin"});
 		} else if("tabs_notpass"==ContractAdminList.currentTabID){
 			$(ContractAdminList.oTable.dataTableSettings).attr("params", {useStatus:"00","tabsType":"notpass","orgType":"admin"});
 		}else if("tabs_done"==ContractAdminList.currentTabID){
 			$(ContractAdminList.oTable.dataTableSettings).attr("params", {useStatus:"01","tabsType":"done","orgType":"admin"});
 		} else if("tabs_tocancel"==ContractAdminList.currentTabID){
 			$(ContractAdminList.oTable.dataTableSettings).attr("params", {useStatus:"01","tabsType":"tocancel","orgType":"admin"});
 		} else if("tabs_cancel"==ContractAdminList.currentTabID){
 			$(ContractAdminList.oTable.dataTableSettings).attr("params", {useStatus:"02","tabsType":"cancel","orgType":"admin"});
 		}
 		ContractAdminList.oTable.fnDraw();
 	})
 	
 	//开始时间
     $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
     //结束时间
     $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
 	
 	ContractAdminList.oTable=$('#ContractAdminList').dataTable({
 		'singleSelect':true,//(false表示可以多选)
 		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
 		'queryColumns':'contractNo,contractName,buyer.orgName,supplier.orgName,total,contractSignedTime,contractFile',//指定要查询的列
 		'hiddenColumns':'contractFile',//隐藏查询不显示的列属性
 		'fnInitComplete':function(oSettings) {
 			 //表格初始化完毕、未开始查询之前的方法
 		},
 		'fnDrawCallback':function(oSettings) {
 			ContractAdminList.oTable.oSettings=oSettings;
 		},
 		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
 			$(nRow).find('td[name=contractFile]').html('<a href="javascript:void(0);" onclick="ContractAdminList.openOperatorPage(\'downFile\',\''+aData.contractFile+'\');return false;">下载</a>'); // 合同文件作为超链
 			$(nRow).append(ContractAdminList.getOperatorStr(aData.objId))//添加修改按钮
 			return nRow;
 		},
 		"sAjaxSource": $('#initPath').val()+"/AgContractController.do?method=list&contractType=02",
 		'params':{useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"00","tabsType":"submit","orgType":"admin"},
 		'searchZone':'contractForm'
 	});

 	ContractAdminList.currentTabID = $("#currentTabID").val();
	
	if(ContractAdminList.currentTabID==""){
		ContractAdminList.currentTabID = "tabs_submit";
	}

 	if ("tabs_todo"==ContractAdminList.currentTabID) {
 		$('#tabs_todo').click();
	} else if("tabs_notpass"==ContractAdminList.currentTabID){
		$('#tabs_notpass').click();
	}else if("tabs_done"==ContractAdminList.currentTabID){
		$('#tabs_done').click();
	} else if("tabs_tocancel"==ContractAdminList.currentTabID){
		$('#tabs_tocancel').click();
	} else if("tabs_cancel"==ContractAdminList.currentTabID){
		$('#tabs_cancel').click();
	}

 	
 	
 	//查询
 	$("#query").click(function(){
 		ContractAdminList.reload();
 	})
 });
 </script>       
        

 
