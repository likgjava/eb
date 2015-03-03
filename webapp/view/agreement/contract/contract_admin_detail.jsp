<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--任务书ID-->
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>

<div class="formLayout form2Pa">
  <form id="contractForm" method="post">
  <h4><span>合同信息</span></h4>
    <ul>
      <li><label>合同编号：</label>
      		<span id="contractNo">${contract.contractNo }</span>
      </li>
      <li><label>合同名称：</label>
      		<span id="contractName">${contract.contractName }</span>
      </li>
      <li><label>甲方（采购人）：</label>
      		<span id="buyer.orgName">${contract.buyer.orgName }</span>
      </li>
      <li><label>乙方（供应商）：</label>
      		<span id="supplier.orgName">${contract.supplier.orgName}</span>
      </li>
      <li><label>甲方确认状态：</label>
      		<span id="buyerConfirmUser">${contract.buyerConfirmStatusCN}</span>
      </li>
      <li><label>乙方确认状态：</label>
      		<span id="supplierConfirmStatus">${contract.supplierConfirmStatusCN}</span>
      </li>
      <li><label>生效日期：</label>
      		<span id="contractBeginTime" tabType="datesimple">${contract.contractBeginTime}</span>
      </li>
      <li><label>失效日期：</label>
      		<span id="contractEndTime" tabType="datesimple">${contract.contractEndTime}</span>
      </li>
      <li><label>签订日期：</label>
      		<span id="contractSignedTime" tabType="datesimple">${contract.contractSignedTime}</span>
      </li>
      <li><label>合同总额（元）：</label>
      		<span>￥</span><span id="total" tabType="money">${contract.total}</span>
      </li>
      <li><label>交货时间：</label>
      		<span id="deliveryTime">${contract.deliveryTime}</span>
      </li>
      <li><label>交货地点：</label>
      		<span id="deliveryPlace">${contract.deliveryPlace}</span>
      </li>
      <li class="fullLine"><label>备注：</label>
      		<span id="memo">${contract.memo}</span>
      </li>
      <li class="fullLine"><label>附件：</label>
      		<input type="hidden" value="${contract.contractFile }" name="contractFile" >
      		<div id="contractFile" class="uploadFile"></div>
      </li>
    </ul>
  </form>
	<br />   
<!--  订单商品列表-->
  <h4><span>订单信息</span></h4>
  <table class="frontTableList" id="orderList">
      <thead>
      	<tr>
          <th class="center">订单编号</th>
          <th >订购品目</th>
          <th class="amount">总数量</th>
          <th class="money">总金额</th>
          <th >创建人</th>
          <th class="date">创建时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
  </table>
</div>
<div class="conOperation">
	<input name="currentTabID" id="currentTabID"  type="hidden" value="<c:out value="${param.currentTabID}"/>" >	
	<c:choose>
		<c:when test="${param.isDialog == '1'}">
			<button id="contractDetailCloseBut" type="button" ><span>关闭</span></button>
		</c:when>
		<c:otherwise>
			<button name="historyBackBtn" id="historyBackBtn" type="button" ><span>返回</span></button>
		</c:otherwise>
	</c:choose>
</div>

<script>
//定义文件全局变量 处理方法名重复问题
var ContractAdminDetail={};
ContractAdminDetail.oTable;	

//查看订单详情
ContractAdminDetail.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	$.epsDialog({
		id: 'contractDetailDiv',
		title:'订单编号：'+orderNo,
		url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&dialogId=contractDetailDiv&navigate=divDetail&objId='+objId
	});
}

$(document).ready(function(){
	
	// 订单详细
	ContractAdminDetail.oTable=$('#orderList').dataTable( {
		//'bPaginate': false,//不分页
		'singleSelect':false,//(false表示可以多选)
		'checkbox':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			ContractAdminDetail.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			$("#total").text(goodsAPI.fnSumTotal(ContractAdminDetail.oTable.oSettings));
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class='center'><a href='javascript:void(0);' onclick=\"ContractAdminDetail.viewOrder('"+aData.objId+"','"+aData.orderNo+"');return false;\">查看</a></td>");//添加操作连接
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+'/OrderController.do?method=list&contract.objId='+$('#objId').val()
	});
	
//	// 展示合同主要信息：编号、总金额、采购人、供应商
//	if($('#objId').val()!=''){
//		var queryColumns=[];
//		$('#contractForm').find("ul").find("span,input").each(function(i,n){
//			queryColumns.push(n.id);     
//		});	
//		$.getJSON($('#initPath').val()+'/AgContractController.do?method=getObjectQuery',{objId:$('#objId').val(), queryColumns:queryColumns.toString()}, function(json){
//			json2Object("contractForm",json.result[0]);
//			$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId='+json.result[0].contractFile);
//		});
//	} by yucy  改成了同步
	
	//附件
	$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId='+$("input[name=contractFile]").val());

	//操作历史
	$('#operatorList').loadPage($('#initPath').val()+"/view/agreement/order/common/operator_history_list.jsp");

	//返回
	//$("#historyBackBtn").click(function(){
	//	var currentTabID = $("#currentTabID").val();
	//	$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractAdminList&currentTabID="+ currentTabID);
	//});

	//关闭
	$("#contractDetailCloseBut").click(function(){
		$('.epsDialogClose').trigger('click');
	});
});
</script>
