<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="GoodsEvaluateListForm">
	<ul >
         <li>
          	<label for="goodsClass.goodsclassName">商品品牌：</label>
            <input type="text" name="goods.goodsBrand.brandName" id="brandName">
            <input type="hidden" name="goods.goodsBrand.brandName_op" value="like">
        </li>
        <li>
          	<label for="productName"> 商品名称：</label>
            <input type="text" name="goods.productName" id="productName">
            <input type="hidden" name="goods.productName_op" value="like">
        </li>
        <li class="operationBtnDiv">
			 <button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
	 	</li>
	 </ul>
</form>
</div>
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em><span id="notice">点击下面列表选择一个商品对其评价</span>
		</li>
		<li>
			新增商品请点击
			<a class="sysicon siAdd" id="addEvaluate"><span><strong>新增商品评价</strong></span></a>.
		</li>
	</ul>
</div>
<div>
	<div>
		<table class="frontTableList" id="GoodsEvaluateList">
			<thead>
				<tr>
					<th class="omission" omiLength="18">商品名称</th>
					<th class="omission"  omiLength="20">评价标题</th>
					<th class="center">评价级别</th>
					<th class="center">评价人</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
			


<script>
var GoodsEvaluateList = {};
GoodsEvaluateList.getOperatorStr=function(objId){
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<a title="删除" href="javascript:void(0);" onclick="GoodsEvaluateList.toDelete(\''+objId+'\');return false;">删除</a>';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="GoodsEvaluateList.toDetail(\''+objId+'\');return false;">查看</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}
//增加
GoodsEvaluateList.toAdd=function(){
	if(GoodsEvaluateList.oTable.dtSelects().length<=0){alert('请至少选择一行数据！');return}
	var id=GoodsEvaluateList.oTable.dtSelects();
	//alert(id);
	$.epsDialog({id:"brandInfo",title:"新增商品评价信息",width: 600,height: 400,url:$('#initPath').val()+'/GoodsEvaluateController.do?method=toGoodsEvaluateAdd&objId='+id });
}
//查看
GoodsEvaluateList.toDetail=function(id){
	$.epsDialog({id:"brandInfo",title:"查看商品评价信息",width: 600,height: 400,url:$('#initPath').val()+'/GoodsEvaluateController.do?method=toGoodsEvaluateDetail&objId='+id });
}
//删除
GoodsEvaluateList.toDelete = function(id){
	if(confirm('确定删除？')){
		$.getJSON($("#initPath").val()+"/GoodsEvaluateController.do?method=remove",{"objId":id},function(json){
			if(json.success){
				alert(json.result);
				GoodsEvaluateList.getGoodsEvaluateList();//刷新列表
			}else{
				alert("操作失败！");
			}
		})
	}
}
//取得商品评价列表
GoodsEvaluateList.getGoodsEvaluateList=function(){
	if(null==GoodsEvaluateList.oTable){
		GoodsEvaluateList.oTable = $('#GoodsEvaluateList').dataTable({
			'singleSelect' : true,
			'checkbox' : false,
			'queryColumns' : 'goods.productName,title,level,createUser.usName',
			'alias' : 'goods.productName,title,level,createUser.usName',
			'hiddenColumns':'goods.objId',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				GoodsEvaluateList.oTable.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				//添加操作按钮alert(aData['goods.productName']);
				$(nRow).append(GoodsEvaluateList.getOperatorStr(aData.objId))
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsEvaluateController.do?method=list",
			"params":{},
			'searchZone':'GoodsEvaluateListForm'
		});
	}else{
		GoodsEvaluateList.oTable.fnDraw();
	}
}

$(document).ready(function(){
    
	//加载商品评价列表
	GoodsEvaluateList.getGoodsEvaluateList();
	//添加新商品评价
	$('#addEvaluate').click(function(){
		GoodsEvaluateList.toAdd();
	});
	
	// 查询
	$("#query").click(function() {		
		GoodsEvaluateList.oTable.fnDraw();
	})
})
</script>
