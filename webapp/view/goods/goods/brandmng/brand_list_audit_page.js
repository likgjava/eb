/*
 * 维护品牌页面
 * created by yucy
 */
var brandList={};
brandList.oTable;

//取得品牌列表
brandList.getBrandList = function(){
	if(null==brandList.oTable){
		brandList.oTable = $('#QualityList').dataTable({   
			'params':{"auditStatus":'01',"currentId":"null","currentId_op":"is"},
			'searchZone':'brandSearchForm',
			'singleSelect':true,	
			'checkbox':false,		
			'queryColumns':'brandCode,brandName,englishName,goodsClassNames',
			'alias':'brandCode,brandName,englishName,goodsClassNames',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				//加操作行
				$(nRow).append('<td class="operation"></td>');

				//审核
				$(nRow).find('td:last').append('<a name="audit" href="javascript:void(0);"><span>审核</span></a>');
				$(nRow).find('a[name=audit]').click(function(){
				    $('#conBody').loadPage($('#initPath').val()+'/GoodsBrandController.do?method=toAuditGoodsBrandView&objId='+aData.objId);
				})
				
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
	
	//返回页
	$("#returnUrl").val($('#initPath').val()+'/view/goods/goods/brandmng/brand_list_audit_page.jsp');
	
	//初始化tabs页
	$('#epsTabs').tabs({}); 
	
	//切换tab页
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				
			$(brandList.oTable.dataTableSettings).attr("sAjaxSource",$("#initPath").val()+"/GoodsBrandController.do?method=list");
			$(brandList.oTable.dataTableSettings).attr("params",{"auditStatus":'01',"currentId":"null","currentId_op":"is"});
		}else if(ui.index==1){			
			$("#addbtnDiv").hide();
			$(brandList.oTable.dataTableSettings).attr("sAjaxSource",$("#initPath").val()+"/GoodsBrandChangeController.do?method=getBrandListToAuditchange");
		}
		brandList.oTable.fnDraw();
	});
	
	//加载列表
	brandList.getBrandList();
	
	//展开与关闭
	$("#hightSearchSwitch").click(function(){
		$(".conSearch .hightSearch").toggle("slow");
		$(this).toggleClass("collapsable");
		$(".operationBtnDiv").toggleClass("hightSearchBtn");
	});
	
	//点击查询按钮
	$("#brandSearch").click(function(){
		brandList.getBrandList();
	})
	
	//新增品牌
	$("#addBrand").click(function(){
	    $('#conBody').loadPage($('#initPath').val()+'/GoodsBrandController.do?method=toCreateOrUpdateView');
	})
	
});


