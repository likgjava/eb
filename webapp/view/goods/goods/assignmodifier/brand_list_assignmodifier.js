var GoodsManageList = {};
GoodsManageList.currentTabID = "tabs_unspecified";

//加载已制定维护供应商的商品列表Unspecified
GoodsManageList.getSpecifiedGoodsTable = function(){
	// 加载指定维护供应商的商品列表specified
	GoodsManageList.specifiedTable = $('#specifiedGoodsTable').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'hiddenColumns' : 'goodsClass.objId,goodsBrand.objId,supplier.objId',
		'queryColumns' : 'goodsBrand.brandName,goodsClass.goodsClassName,supplier.orgName',
		'alias' : 'goodsBrand.brandName,goodsClass.goodsClassName,supplier.orgName',
		'fnInitComplete' : function(oSettings) {
	},
	'fnDrawCallback' : function(oSettings) {
		GoodsManageList.specifiedTable.oSettings = oSettings;
	},
	'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
		
		// 添加操作按钮
	$(nRow).append('<td align="center"><a href="javascript:void(0);" name="modefy" type="alink" title="修改供应商"><span>修改供应商</span></a>&nbsp;<a href="javascript:void(0);" name="delete" type="alink" title="删除"><span>删除</span></a></td>').find("a[name=modefy]").click(function() {
			$.epsDialog({
		        title:'修改商品的维护供应商',
		        url:'view/goods/goods/assignmodifier/edit_assign_supplier_for_goods.jsp?objId='+aData.objId + "&goodsClassId=" + aData["goodsClass.objId"] + "&goodsBrandId=" + aData["goodsBrand.objId"] +"&supplierId=" + aData["supplier.objId"],
		        width: 500,
				height: 450,
				onClose: function(){ 
				 GoodsManageList.specifiedTable.fnDraw();
			    } 
			})
			return false;
		}).end().find("a[name=delete]").click(function() {
			if(window.confirm("确定要删除该商品的指定维护供应商吗？")) {
				$.getJSON($('#initPath').val()+"/GoodsModifierController.do?method=remove",{"objId":aData.objId},function(json) {
					if(json.failure) {
						alert(json.result);
						return;
					}
					GoodsManageList.specifiedTable.fnDraw();
				})
				
			}
		})
		return nRow;
	},
	"sAjaxSource" : $('#initPath').val()+ "/GoodsModifierController.do?method=list",
	"params":{"goodsBrand.useStatus":"01","goodsBrand.sellStatus":"01","goodsBrand.belongsId":$('#_belongsId').val()},
	'searchZone':'GoodsManageSearchForm'
	});	
}
$(document).ready(function(){

	$('#epsTabs').tabs({}); 
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		GoodsManageList.currentTabID = $(this).attr("id");
		if ("tabs_unspecified"==GoodsManageList.currentTabID) {
			GoodsManageList.unSpecifiedTable.fnDraw();
		} else if ("tabs_specified"==GoodsManageList.currentTabID) {
			if(!GoodsManageList.specifiedTable) {
				GoodsManageList.getSpecifiedGoodsTable();
			}else {
				GoodsManageList.specifiedTable.fnDraw();
			}
		} 
	})
	GoodsManageList.unSpecifiedTable = $('#unspecifiedGoodsTable').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'hiddenColumns' : 'goodsClass.objId,goodsBrand.objId,supplier.objId',
		'queryColumns' : 'goodsBrand.brandName,goodsClass.goodsClassName',
		'alias' : 'goodsBrand.brandName,goodsClass.goodsClassName',
		'fnInitComplete' : function(oSettings) {
	},
	'fnDrawCallback' : function(oSettings) {
		GoodsManageList.unSpecifiedTable.oSettings = oSettings;
	},
	'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
		
		// 添加操作按钮
		$(nRow).append('<td align="center"><a href="javascript:void(0);" type="alink" title="指定供应商"><span>指定供应商</span></a></td>').find("a").click(function() {
			$.epsDialog({
		        title:'指定商品的维护供应商',
		        url:'view/goods/goods/assignmodifier/assign_supplier_for_goods.jsp?objId=' + "&goodsClassId=" + aData.goodsClass.objId + "&goodsBrandId=" + aData.goodsBrand.objId,
		        width: 680,
				height: 450,
				onClose: function(){ 
				  GoodsManageList.unSpecifiedTable.fnDraw();
			    } 
			})
			return false;
		})
		return nRow;
	},
	"sAjaxSource" : $('#initPath').val()+ "/GoodsModifierController.do?method=getUnSpecifiedGoods",
	"params":{},
	'searchZone':'GoodsManageSearchForm'
	});
	
})


// 查询
$("#query").click(function() {
	if("tabs_specified"==GoodsManageList.currentTabID) {
		GoodsManageList.specifiedTable.fnDraw();
	}else {
		GoodsManageList.unSpecifiedTable.fnDraw();
	}
})

/*选择商品分类*/
$('#goodsClassName').click(function(){
	 $.epsDialog({
	        title:'选择分类',
	        url:'view/goods/goodsclass/goodsclass_select.jsp',
	        width: 380,
			height: 400
	         
	    })
});
