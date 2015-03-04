/*
 * 维护品牌页面
 * created by yucy
 */
var brandList={};
brandList.oTable;

//批量操作前确认
brandList.batConfrim = function(){
	if(brandList.oTable.dtSelects().length<=0){alert('请至少选择一行数据！');return false}
	if(confirm("确认执行？")){return true}else{return false}
}


//启卖
brandList.startBrand = function(){
	if(!brandList.batConfrim()){return}
	$.getJSON($("#initPath").val()+'/GoodsBrandController.do?method=updateSellStatus',{"objId":brandList.oTable.dtSelects(),"sellStatus":"start"},function(json){
		if(json.success){
			alert("操作成功!");
			brandList.getBrandList();//刷新列表
		}else{
			alert("操作失败!");
		}
	})
}

//禁卖
brandList.stopBrand = function(){
	if(!brandList.batConfrim()){return}
	//if(brandList.oTable.dtSelects().length<=0){alert('请至少选择一行数据！');return}
	$.getJSON($("#initPath").val()+'/GoodsBrandController.do?method=updateSellStatus',{"objId":brandList.oTable.dtSelects(),"sellStatus":"stop"},function(json){
		if(json.success){
			alert("操作成功!");
			brandList.getBrandList();//刷新列表
		}else{
			alert("操作失败!");
		}
	})
}

//报废
brandList.destroyBrand = function(){
	if(!brandList.batConfrim()){return}
	$.getJSON($("#initPath").val()+"/GoodsBrandController.do?method=updateDestroy",{"objId":brandList.oTable.dtSelects()},function(json){
		if(json.success){
			alert(json.result);
		}else{
			alert("操作失败！");
		}
		brandList.oTable.fnDraw();
	})
}

//删除
brandList.delBrand = function(ids){
	if(confirm('确定删除？')){
		$.getJSON($("#initPath").val()+"/GoodsBrandController.do?method=deleteBrand",{"objId":ids},function(json){
			if(json.success){
				alert("操作成功！");
				brandList.getBrandList();//刷新列表
			}else{
				alert("操作失败！");
			}
		})
	}
}

//批量删
brandList.delBrands = function(){
	if(brandList.oTable.dtSelects().length<=0){alert('请至少选择一行数据！');return}
	brandList.delBrand(brandList.oTable.dtSelects());
}


//取得品牌列表
brandList.getBrandList = function(){
	if(null==brandList.oTable){
		brandList.oTable = $('#QualityList').dataTable({   
			'params':{'useStatus':'00'},
			'searchZone':'brandSearchForm',
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'brandCode,mainLogo,brandName,goodsClassNames,sellStatus,auditStatus',
			'hiddenColumns':'useStatus,currentId',
			'alias':'brandCode,mainLogo,brandName,goodsClassNames,sellStatusCN,auditStatusCN',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				//有效品牌列表中，如果品牌已被变更，不允许禁卖或报废
				if(aData["sellStatus"]=='01' && aData["useStatus"]=='01'){
					if(aData["currentId"]){
						$(nRow).find('input:checkbox').each(function(i,n){
							$(n).attr("disabled","disabled");
						})
					}
				}
				
				//报废品牌不允许启卖
				if(aData["useStatus"]=='02'){
					if(""==aData["currentId"]){
						$(nRow).find('input:checkbox').each(function(i,n){
							$(n).attr("disabled","disabled");
						})
					}
					$(nRow).find('td[name=sellStatus]').html('报废');
				}
				
				//待审品牌不许删除
				if(aData["auditStatus"]=='01'){
					$(nRow).find('input:checkbox').each(function(i,n){
						$(n).attr("disabled","disabled");
					})
				}
				
				//处理主标示
				$(nRow).find('td:eq(2)').html('<img width="80" height="50" src="'+$("#initPath").val()+"/AttachmentController.do?method=showImg&objId="+aData.mainLogo+'"></img>');
				
				$(nRow).append('<td class="operation"></td>');
				
				//修改
				if(aData.auditStatus!='01' && aData.useStatus!='02'){
					$(nRow).find('td:last').append('<a name="modify" title="修改" href="'+$("#initPath").val()+'/GoodsBrandController.do?method=toCreateOrUpdateView&objId='+aData.objId+'" target="_blank"><span>修改</span></a>');
				}
				
				//删除
				if(aData.useStatus=='00'&&aData.auditStatus!='01'){
					$(nRow).find('td:last').append('<a name="delete" href="javascript:void(0);"><span>删除</span></a>');
					$(nRow).find('a[name=delete]').click(function(){
						brandList.delBrand(aData.objId);
					})
				}
				
				//查看
				$(nRow).find('td:last').append('<a name="detail" href="javascript:void(0);"><span>查看</span></a>');
				$(nRow).find('a[name=detail]').click(function(){
					$.epsDialog({id:"brandInfo",title:"查看品牌信息",width: 900,height: 500,url:$('#initPath').val()+'/GoodsBrandController.do?method=toGoodsBrandDetailView&pageType=detail&objId='+aData.objId });
				})
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/GoodsBrandController.do?method=list"
		});
	}else{
		brandList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//新建
			$("#addBrand").show();$("#destroyBrand").hide();$("#startBrand").hide();$("#stopBrand").hide();$("#delBrand").show();
			$(brandList.oTable.dataTableSettings).attr("params",{'useStatus':'00'});
		}else if(ui.index==1){			//有效
			$("#destroyBrand").show();$("#startBrand").hide();$("#stopBrand").show();$("#delBrand").hide();
			$(brandList.oTable.dataTableSettings).attr("params",{'useStatus':'01','sellStatus':'01'});
		}else {							//禁卖
			$("#destroyBrand").hide();$("#startBrand").show();$("#stopBrand").hide();$("#delBrand").hide();
			$(brandList.oTable.dataTableSettings).attr("params",{"useStatus":"02","sellStatus":"02","useStatus_relative":"[and:or]","sellStatus_relative":"[and:or]"});
		}
		brandList.oTable.fnDraw();
	});

	//加载列表
	brandList.getBrandList();
});

