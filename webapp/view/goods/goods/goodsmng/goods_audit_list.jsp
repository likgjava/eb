<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span><span class="detailsSwitch" id="hightSearchSwitch">高级搜索</span></h4>	
<form id="GoodsAuditListForm">
	<ul >
                 <li>
                  <label for="goodsClass.goodsClassName">商品分类：</label>
		      		<input type="text" name="goodsClass.goodsClassName" id="goodsClassName" value="" class="sysicon siSearch">
		      		<input type="hidden" name="goodsClass.goodsClassName_op" value="like">
                </li>
                 <li>
                  <label for="goodsClass.goodsclassName">商品品牌：</label>
                    <input type="text" name="goodsBrand.brandName" id="brandName">
                    <input type="hidden" name="goodsBrand.brandName_op" value="like">
                </li>
               
                <li>
                  <label for="productName"> 商品名称：</label>
                    <input type="text" name="productName" id="productName">
                    <input type="hidden" name="productName_op" value="like">
                </li>
                
                <li class="hightSearch">
                  <label for="productCode">规格型号：</label>
                    <input type="text" name="productCode" id="productCode">
                    <input type="hidden" name="productCode_op" value="like">
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

<table class="frontTableList" id="goodsAuditList">
		<thead>
			<tr>
				<th class="omission" omiLength="10">商品名称</th>
				<th class="omission" omiLength="15">规格型号</th>
				<th class="omission" omiLength="10">商品品牌</th>
				<th class="center">售卖状态</th>
				<th class="center">有效状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>

<script>
var GoodsAuditList = {};
GoodsAuditList.getOperatorStr=function(objId){
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<a  title="审核" href="javascript:void(0);" onclick="GoodsAuditList.toAudit(\''+objId+'\');return false;">审核</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}
//审核
GoodsAuditList.toAudit=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/GoodsController.do?method=toAuditGoods&objId="+id);
}
$(document).ready(function(){
	//开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
    
    /*选择商品分类*/
	$('#goodsClassName').click(function(){
		 $.epsDialog({
		        title:'选择分类',
		        url:'view/goods/goodsclass/goodsclass_select.jsp',
		        width: 380,
				height: 400    
		    })
	});
	//加载商品列表
	GoodsAuditList.oTable = $('#goodsAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'productName,productCode,goodsBrand.brandName,sellStatus,useStatus',
		'alias' : 'productName,productCode,goodsBrand.brandName,sellStatusCN,useStatusCN',
		'hiddenColumns':'auditStatus',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GoodsAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).append(GoodsAuditList.getOperatorStr(aData.objId))
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=list",
		"params":{"auditStatus":"01"},
		'searchZone':'GoodsAuditListForm'
	});
	
	// 查询
	$("#query").click(function() {
		if($("#startDate").val() != "" && $("#endDate").val() != ""){
			if($("#endDate").val() < $("#startDate").val()){
				alert("请输入正确的创建时间范围!");return;
			}
		}
		if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
			$(GoodsAuditList.oTable.dataTableSettings).attr("params",
					$.extend(GoodsAuditList.oTable.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		}
		else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
			$(GoodsAuditList.oTable.dataTableSettings).attr("params",
					$.extend(GoodsAuditList.oTable.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"le"}));
		}
		else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
			$(GoodsAuditList.oTable.dataTableSettings).attr("params",
					$.extend(GoodsAuditList.oTable.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		}
		
		GoodsAuditList.oTable.fnDraw();
	})
	
	$("#hightSearchSwitch").click(function(){
		$(".conSearch .hightSearch").toggle("slow");
		$(this).toggleClass("collapsable");
		$(".operationBtnDiv").toggleClass("hightSearchBtn");
	});
})
</script>
