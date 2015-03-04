
var GoodsAccessoryForm={};

$(document).ready(function(){
	$('#GoodsAccessoryForm').validate();
	
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
	$.getJSON($('#initPath').val()+'/GoodsController.do?method=getObjectQuery',{objId:$('#mainGoodsId').val(), queryColumns:queryColumns.toString()}, function(json){
		json2Object("GoodsAccessoryForm",json.result[0]);
		jsonObjectToForm("GoodsAccessoryForm",json.result[0]);
		$('#img').attr('src','AttachmentController.do?method=showImg&objId='+json.result[0].picture);
	});
	
	//选择配件商品
	$("input[id=accessoryGoodsName]").click(function(){
		$.epsDialog({
		    title:'挑选配件商品',
		    url:$('#initPath').val()+'/view/goods/goods/goodsmng/accessory_goods_select_list.jsp?id=accessoryGoodsId&name=accessoryGoodsName',
		    width: 800,
			height: 400    
		})
	})
	
	//保存
	$('#save').click(function(){
		if(!$('#GoodsAccessoryForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/GoodsAccessoriesController.do?method=save', formToJsonObject('GoodsAccessoryForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert('操作成功')
			$('#conBody').loadPage($('#initPath').val()+"/view/goods/goods/goodsmng/goodsAccessories_list.jsp?goodsId="+$('#mainGoodsId').val());
		});
	});

	//返回
	$('#return').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/goods/goods/goodsmng/goodsAccessories_list.jsp?goodsId="+$('#mainGoodsId').val());
	});
});
