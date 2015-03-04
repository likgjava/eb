<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="GoodsChangeListForm">
			  <ul >
                <li>
                  <label for="goodsClass.goodsclassName">商品品牌：</label>
                    <input type="text" name="brandName" id="brandName">
                </li>
                <li>
                  <label for="productName"> 商品名称：</label>
                    <input type="text" name="productName" id="productName">
                </li>
                <li class="hightSearch">
                  <label for="productCode">规格型号：</label>
                    <input type="text" name="productCode" id="productCode">
                </li>
                
                <li class="hightSearch">
                	<label>创建时间：</label>
		  			<input type="text" name="startDate" id="startDate">&nbsp;到
	            	<input type="text" name="endDate" id="endDate">
                </li>
                <li class="operationBtnDiv">
       				 <button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
     			 </li>
              </ul>
</form>
</div>

<table class="frontTableList" id="GoodsChangeList">
		<thead>
			<tr>
				<th class="hidden">图片</th>
				<th class="omission" omiLength="10">商品名称</th>
				<th class="omission" omiLength="15">规格型号</th>
				<th class="omission" omiLength="10">品牌</th>
				<th class="center">审核状态</th>
				<th class="center">售卖状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>

<script>
var GoodsChangeList = {};
GoodsChangeList.getOperatorStr=function(aData) {
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<a  title="变更" href="javascript:void(0);" onclick="GoodsChangeList.change(\''+aData.objId+'\');return false;">变更</a>';
	operatorHtml += '<a  title="查看历史" href="javascript:void(0);" onclick="GoodsChangeList.history(\''+aData.objId+'\');return false;">查看变更历史</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//变更
GoodsChangeList.change=function(id){
	var url = $('#initPath').val()+"/GoodsChangeController.do?method=toGoodsChange&objId="+id;
	$.epsDialog({
		id:'goodsChangeDiv',
        title:'商品变更',
        url:url
    })
}
//查看变更历史
GoodsChangeList.history=function(id){
	var url = $('#initPath').val()+"/GoodsController.do?method=getGoodsHistory&id="+id;
	$.epsDialog({
		id:'goodsChangeHistoryDiv',
        title:'商品变更历史',
        url:url
    }); 
}

$(document).ready(function(){
	//开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
    
	//加载商品列表
	GoodsChangeList.oTable = $('#GoodsChangeList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'picture,productName,productCode,goodsBrand.brandName,auditStatus,sellStatus',
		'alias' : 'picture,productName,productCode,goodsBrand.brandName,auditStatusCN,sellStatusCN',
		'hiddenColumns':'useStatus,currentId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GoodsChangeList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
			//添加操作按钮
			$(nRow).append(GoodsChangeList.getOperatorStr(aData));
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=listGoods",
		"params":{"type":"validGoods"},
		'searchZone':'GoodsChangeListForm'
	});
	
	// 查询
	$("#query").click(function() {
		GoodsChangeList.oTable.fnDraw();
	})
})
</script>
