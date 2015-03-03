/*
 * 协议管理，协议管理list页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */

//定义文件全局变量 处理方法名重复问题
var PurchaseAgreementList={};
PurchaseAgreementList.oTable;	

//新增
PurchaseAgreementList.add=function(){
	$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toNewAgreement");
}

//删除
PurchaseAgreementList.del=function(){
	//获取选中的复选框的ID
	var selectIds=[];
	$('#PurchaseAgreementList tbody td').each(function(){
		var input=$(this).find('input:first');
		if(input.attr('checked'))
			selectIds.push(input.attr('value'));
	});
	
	if(selectIds.length<1){
		alert("请至少选择一行删除");
	}else {
		if(confirm("确定删除吗？")){
			PurchaseAgreementList.delAgreement(selectIds.toString());
		}
	}
}

//删除
PurchaseAgreementList.delAgreement = function(agreementIds){
	$.getJSON($('#initPath').val()+'/AgreementController.do?method=removeAgreement',{"agreementIds":agreementIds},function(json){
			alert(json.result);
			PurchaseAgreementList.reload();
	})
}

//维护协议商品
PurchaseAgreementList.updateAgreementGoods=function(){
	var length = $('#PurchaseAgreementList').dtSelects().length;
	if(null!=length&&length>0){
		var objIds = $('#PurchaseAgreementList').dtSelects().split(',');
		objIds.length>1?alert(
		"请选择单一协议维护协议商品!"):$('#conBody').loadPage($('#initPath').val()+'/AgreementController.do?method=toChooseClassBrand&toView=toChooseClassBrandView&objId='+objIds);
	}else{
		alert("请选一行数据进行维护!");
		return;
	}
}

//维护供货资格
PurchaseAgreementList.updateVenderQualify=function(){
	var length = $('#PurchaseAgreementList').dtSelects().length;
	if(null!=length&&length>0){
		var objIds = $('#PurchaseAgreementList').dtSelects().split(',');
		
		
		objIds.length>1?alert("请选择单行数据维护供货商!"):PurchaseAgreementList.getVenderQualify(objIds);
		//$('#conBody').loadPage($('#initPath').val()+"/view/mamagement/authorized_vender.jsp?resource=maintainVender&agreementId="+objIds);
	}else{
		alert("请选一行数据进行维护!");
		return;
	}
}

//查询是否有供货商
PurchaseAgreementList.getVenderQualify = function(agreementId){
	$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=getObjectQuery',
			{
				"agreement.objId":agreementId,
				queryColumns:"objId"
			},
			function(json){
				//alert(json.result.length);
				if(json.result.length>0){
					$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&toView=toAuthorizedVenderView&objId="+agreementId);
				}else{
					alert("该协议下暂无供货商!");
				}
		});
}

//查询
PurchaseAgreementList.query=function(){
	PurchaseAgreementList.reload();
}

//刷新
PurchaseAgreementList.reload=function(){
	PurchaseAgreementList.oTable.fnDraw();
}


$(document).ready(function(){
	
	//orgInfoId
//	var orgInfoId = ((null!=PlatForm.userInfo&&null!=PlatForm.userInfo.orgInfo)?PlatForm.userInfo.orgInfo.objId:"-1");
	
//	//分权限操作
//	if(PlatForm.userInfo.orgInfo.supplierId!=null){
		$("#updateVenderQualify").show();
//	}
	
	//初始化 tab
	var $tabs = $('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){
			$(PurchaseAgreementList.oTable.dataTableSettings).attr("params",
				{
					"useStatus":"02",
					"useStatus_op":"<"
//					,"org.objId":orgInfoId,
//					"supplier.objId":orgInfoId,
//					"org.objId_relative":"[and:or]",
//					"supplier.objId_relative":"[and:or]"
				}
			);
			
		}else if(ui.index==1){
			$(PurchaseAgreementList.oTable.dataTableSettings).attr("params",
				{
					"useStatus":"02"
//					,"org.objId":orgInfoId,
//					"supplier.objId":orgInfoId,
//					"org.objId_relative":"[and:or]",
//					"supplier.objId_relative":"[and:or]"
				}
			);
		}
		PurchaseAgreementList.oTable.fnDraw();
	});
	
	//点击搜索
	$("#agreementSearch").click(function(){
		PurchaseAgreementList.reload();
	})
	
	//加载列表
	PurchaseAgreementList.oTable=$('#PurchaseAgreementList').dataTable( {
		'params':{
			"useStatus":"02",
			"useStatus_op":"<"
			//以下是区分查看权限的参数
//			,"org.objId":orgInfoId,
//			"supplier.objId":orgInfoId,
//			"org.objId_relative":"[and:or]",
//			"supplier.objId_relative":"[and:or]"
		},
		'searchZone':'agreementSearchForm',
		'singleSelect':false,	
		'checkbox':true,		
		'queryColumns':'name,supplier.orgName,period.periodName,areaNames',
		'hiddenColumns':'objId',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" name="modify" type="alink"><span>修改</span></a></td>')//添加修改按钮
			$(nRow).find('a[name=modify]').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toNewAgreement&objId="+aData.objId);
			});
			
			//添加预览按钮
			$(nRow).find('td:last').append('<a href="javascript:void(0);" name="preview" type="alink"><span>预览</span></a>').find("a[name=preview]").click(function(){
				$.epsDialog({
					id:"agreementPreview",
					title:"协议预览",
					url:$("#initPath").val()+"/AgreementController.do?method=toAgreementPreView&objId="+aData.objId
				})
			})
			
			return nRow;
		},
		"sPaginationType":"full_numbers",
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": $('#initPath').val()+"/AgreementController.do?method=list"
	});
	
});
	
