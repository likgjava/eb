
var goodsAccessoriesList={};

//修改零配件
goodsAccessoriesList.update = function(objId) {
	$('#conBody').loadPage($('#initPath').val()+"/GoodsAccessoriesController.do?method=toCreateOrUpdate&objId="+objId);
}

//删除零配件
goodsAccessoriesList.remove = function(objId) {
	if(window.confirm("确定删除所选零配件吗")){
		$.getJSON($('#initPath').val()+'/GoodsAccessoriesController.do?method=remove',{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			goodsAccessoriesList.oTable.fnDraw();
		});
	}
}

//启用
goodsAccessoriesList.start = function(objId) {
	if(window.confirm("确定启用所选零配件吗")){
		$.getJSON($('#initPath').val()+'/GoodsAccessoriesController.do?method=updateDisableOrEnable',{"objId":objId,"isOff":"1"},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			goodsAccessoriesList.oTable.fnDraw();
		});
	}
}

//禁用
goodsAccessoriesList.forbid = function(objId) {
	if(window.confirm("确定禁用所选零配件吗")){
		$.getJSON($('#initPath').val()+'/GoodsAccessoriesController.do?method=updateDisableOrEnable',{"objId":objId,"isOff":"2"},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			goodsAccessoriesList.oTable.fnDraw();
		});
	}
}

$(document).ready(function(){
	//加载商品
	var queryColumns=[];//指定列
	queryColumns.push("picture");     
	queryColumns.push("purCategory.categoryName");     
	queryColumns.push("goodsClass.goodsClassName");     
	queryColumns.push("goodsBrand.brandName");     
	queryColumns.push("measureUnit");     
	queryColumns.push("productName");     
	queryColumns.push("productCode");     
	queryColumns.push("referPrice");     
	queryColumns.push("factory");     
	queryColumns.push("madeIn");     
	queryColumns.push("externalInforLink");     
	queryColumns.push("spec");     
	queryColumns.push("functionIntro");     
	$.getJSON($('#initPath').val()+'/GoodsController.do?method=getObjectQuery',{objId:$('#goodsId').val(), queryColumns:queryColumns.toString()}, function(json){
		json2Object("GoodsAccessoriesListForm",json.result[0]);
		jsonObjectToForm("GoodsAccessoriesListForm",json.result[0]);
		$('#img').attr('src','AttachmentController.do?method=showImg&objId='+json.result[0].picture);
	});
	
	//加载零配件列表
	goodsAccessoriesList.oTable = $('#GoodsAccessoriesList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'accessoryGoods.productName,accessoryQty,isOff,createUser.emp.name',
		'alias':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			goodsAccessoriesList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var html = '<td align="center">';
			html += '<a title="修改" href="javascript:void(0);" onclick="goodsAccessoriesList.update(\''+aData["objId"]+'\');return false;">修改</a>';
			html += '<a title="删除" href="javascript:void(0);" onclick="goodsAccessoriesList.remove(\''+aData["objId"]+'\');return false;">删除</a>';
			
			if(aData["isOff"]=='1'){
				$(nRow).find('td[name=isOff]').html('启用');
				html += '<a title="禁用" href="javascript:void(0);" onclick="goodsAccessoriesList.forbid(\''+aData["objId"]+'\');return false;">禁用</a>';
			}else if(aData["isOff"]=='2') {
				$(nRow).find('td[name=isOff]').html('禁用');
				html += '<a title="启用" href="javascript:void(0);" onclick="goodsAccessoriesList.start(\''+aData["objId"]+'\');return false;">启用</a>';
			}
			html += '</td>';
			$(nRow).append(html);
			return nRow;
		},
		"params":{"goods.objId":$('#goodsId').val()},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsAccessoriesController.do?method=list"
	});
	
	//新增零配件跳转
	$('#addAccessory').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/GoodsAccessoriesController.do?method=toCreateOrUpdate&goodsId="+$('#goodsId').val());
	})
});

