<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch hidden">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="MemberGoodsSupplierListForm">
		<ul >
               <li>
                 <label for="supplier"> 供应商名称：</label>
                   <input type="text" name="supplier.orgName" id="supplierName">
                   <input type="hidden" name="supplier.orgName_op" value="like">
               </li>
               <li class="operationBtnDiv">
      				 <button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
    		</li>
        </ul>
</form>
</div>

<input type="hidden" name="goodsId" id="goodsId" value="${param.goodsId}"/>

<div class="formTips attention">
	<ul>
		<li>
			<em>提示：</em>点击列表中的"行情"链接,可查看该供应商的报价列表。
		</li>
	</ul>
</div>

<table class="frontTableList" id="memberGoodsSupplierList">
		<thead>
			<tr>
				<th class="left">商品名称</th>
				<th class="left">供应商名称</th>
				<th class="left">是否商圈会员</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>

<div id="supplierGoodsPriceDiv"></div>

<div class="conOperation ">
	<button class="largeBtn" id="returnBtn" type="button"><span><spring:message code="globe.return" /></span></button>
</div>

<script>
var MemberGoodsSupplierList = {};
MemberGoodsSupplierList.getOperatorStr=function(objId){
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<a  title="行情" href="javascript:void(0);" onclick="MemberGoodsSupplierList.toPriceList(\''+objId+'\');return false;">维护会员价</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}
//供应商已报价格
MemberGoodsSupplierList.toPriceList=function(id){
	$("#supplierGoodsPriceDiv").loadPage('view/goods/goodsprice/member_supplier_price_list.jsp?goodsPriceSupplierId='+id);
}
$(document).ready(function(){
	//加载行情供应商(会员)列表
	MemberGoodsSupplierList.oTable = $('#memberGoodsSupplierList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'goods.productName,supplier.orgName',
		'alias' : 'goods.productName,supplier.orgName',
		'hiddenColumns':'objId,supplier.objId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			MemberGoodsSupplierList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var operatorHtml = "";
			//过滤商圈会员供应商
			$.getJSON($('#initPath').val()+"/GoodsPriceSupplierController.do?method=isMember", {"orgInfoId":aData["supplier.objId"]} , function(json){
				if(json.isMember=="true") {
					operatorHtml += '<td align="center">是</td>'
					operatorHtml += '<td align="center">';
					operatorHtml += '<a  title="维护会员价" href="javascript:void(0);" onclick="MemberGoodsSupplierList.toPriceList(\''+aData.objId+'\');return false;">维护会员价</a>';
					operatorHtml += '</td>';
				}else {
					operatorHtml += '<td align="center">否</td>'
					operatorHtml += '<td align="center">';
					operatorHtml += '<span style="color:gray">维护会员价</span>';
					operatorHtml += '</td>';
				}
				
				$(nRow).append(operatorHtml)
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsPriceSupplierController.do?method=list",
		"params":{"goods.objId":$('#goodsId').val()},//显示的行情供应商
		'searchZone':'MemberGoodsSupplierListForm'
	});
	
	// 查询
	$("#query").click(function() {
		MemberGoodsSupplierList.oTable.fnDraw();
	})
	
	// 返回
	$("#returnBtn").click(function() {
		$('#conBody').loadPage('view/goods/goodsprice/member_goods_price_list.jsp');
	})
})
</script>
