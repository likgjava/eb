<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" name="supplierId" id="supplierId" value="${supplierId }">

<input type="hidden" name="projectId" id="projectId" value="${param.projectId }">

<input type="hidden" name="appType" id="appType" value="${param.appType }">

<input id="singlePrice" name="singlePrice" type="hidden" value="<c:choose><c:when test="${buyResult.project.ebuyMethod=='05'||singlePrice=='1'}">1</c:when><c:otherwise>0</c:otherwise></c:choose>"/>




<div class="formLayout form2Pa">
	<h4>成交结果</h4>
	<input type="hidden" id="projectId" value="${buyResult.project.objId }">
	<ul>
	<li class="fullLine">
		<label>项目名称：</label>
		<span>${buyResult.projName }</span>
	</li>
	<li class="fullLine">
		<label for="">项目编号：</label>
		<span>${buyResult.projCode }</span>
	</li>
	<li class="fullLine">
		<label for="">确认者：</label>
		<span>${buyResult.createUser.emp.name }</span>
	</li>
	<li>
		<label>确认时间：</label>	
		<span class="date"><fmt:formatDate value="${buyResult.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
	</li>
	<li>
		<label>结果类型：</label>
		<span>${buyResult.buyrResultCN }</span>
	</li>
	<c:if test="${buyResult.buyrResult=='03'}">
	<li class="fullLine">
		<label>放弃理由：</label>
		<span>${buyResult.buyrOpinion }</span>
	</li>	
	</c:if>
	</ul>
</div>

<div class="formLayout">
	<table class="tableList">
		<caption>结果供应商</caption>
		<thead>
			<tr>
				<th>供应商名称</th>
				<th>成交结果</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="buyWinner" items="${buyWinnerList}" >
				<tr>
					<td>${buyWinner.sellerName }</td>
					<td align="center">
						<span style="<c:if test="${buyWinner.resultType=='00'}">color:red;</c:if>">${buyWinner.resultTypeCn }</span>
						
						<c:if test="${buyWinner.resultType=='00' && param.userType!='supplier'}">
							<a href="javascript:void(0);" onclick="buyresultsupplier.createOrder('${buyWinner.selllerId}');return false;">生成订单</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot></tfoot>
	</table>
</div>

<!--  订单商品列表-->
<div class="formLayout form2Pa">
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
	<button type="button" name="historyBackBtn"><span>返回</span></button>
</div>
<script type="text/javascript">

var buyresultsupplier = {};

//弹出生成订单页面
buyresultsupplier.createOrder = function(supplierId){
	$.epsDialog({
		id:"resultCreateOrderDiv",
		title:"生成订单",
		url:$("#initPath").val()+"/BuyResultXyghController.do?method=toChooseGoodsToOrderViewTalkProject&appType="+$("#appType").val()+"&supplierId="+supplierId+"&projectId="+$("#projectId").val()+"&singlePrice="+$("#singlePrice").val()
	})
}

//查看订单详情
buyresultsupplier.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}

//取得订单列表
buyresultsupplier.getOrderList = function(params){
	// 订单详细
	buyresultsupplier.oTable=$('#orderList').dataTable( {
		'params':params,
		'bPaginate': false,
		'singleSelect':false,
		'checkbox':false,
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',
		'hiddenColumns':'',
		'fnInitComplete':function(oSettings) {},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class='center'><a href=\"javascript:buyresultsupplier.viewOrder('"+aData.objId+"','"+aData.orderNo+"');\">查看</a></td>");//添加操作连接
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+'/OrderController.do?method=list'
	});
}

$(document).ready(function(){
	var params = {};
	params["project.objId"] = $("#projectId").val();

	//按供应商过滤
	if($("input[name=supplierId]").val()!=""){
		params["supplier.objId"] = $("input[name=supplierId]").val();
		params["project.objId"] = $("input[id=projectId]").val();
	}
	buyresultsupplier.getOrderList(params);
})
</script>