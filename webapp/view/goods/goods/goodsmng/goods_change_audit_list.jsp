<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="GoodsChangeAuditListForm">
			  <ul >
                <li>
                  <label for="productName"> 商品名称：</label>
                    <input type="text" name="productName" id="productName">
                </li>
                
                <li class="operationBtnDiv">
       				 <button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
     			 </li>
              </ul>
</form>
</div>

<table class="frontTableList" id="GoodsChangeAuditList">
		<thead>
			<tr>
				<th>图片</th>
				<th class="omission" omiLength="10">商品名称</th>
				<th class="omission" omiLength="15">规格型号</th>
				<th class="center">审核状态</th>
				<th class="center">售卖状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>	

<script>
var GoodsChangeAuditListForm={};

//跳转到审核页面
GoodsChangeAuditListForm.toAuditGoodsChange=function(goodsId){
	$('#conBody').loadPage($('#initPath').val()+'/GoodsChangeController.do?method=toAuditChangeGoods&goodsId='+goodsId);
}

$(document).ready(function(){
	//加载商品列表
	GoodsChangeAuditListForm.oTable = $('#GoodsChangeAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'picture,productName,productCode,auditStatus,sellStatus',
		'alias' : 'picture,productName,productCode,auditStatusCN,sellStatusCN',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GoodsChangeAuditListForm.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
			//添加操作按钮
			var operatorHtml = "";
			operatorHtml += '<td align="center">';
			operatorHtml += '<a  title="审核" href="javascript:void(0);" onclick="GoodsChangeAuditListForm.toAuditGoodsChange(\''+aData.objId+'\');return false;">审核</a>';
			operatorHtml += '</td>';
			$(nRow).append(operatorHtml);
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsChangeController.do?method=toGoodsChangeAuditList",
		'searchZone':'GoodsChangeAuditListForm'
	});

	// 查询
	$("#query").click(function() {
		GoodsChangeAuditListForm.oTable.fnDraw();
	})
})
</script>