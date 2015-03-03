/*
 * 协议管理，协议管理list页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */

//定义文件全局变量 处理方法名重复问题
var AgreementGoodsList={};
AgreementGoodsList.oTable;	

$(document).ready(function(){
	
	//加载列表
	AgreementGoodsList.oTable=$('#AgreementGoodsList').dataTable( {
		'params':{
			"useStatus":"02",
			"useStatus_op":"<"
			//区分权限的参数
			//,"supplier.objId":PlatForm.userInfo.orgInfo.objId
		},
		'singleSelect':false,	
		'queryColumns':'name,supplier.orgName,unAuthorizeClass,unAuthorizeGoods',
		'hiddenColumns':'objId',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			$(nRow).append('<td><a href="javascript:void(0);" type="alink"><span>维护</span></a></td>')//添加修改按钮
			$(nRow).find('a').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/agreement_category_goods.jsp?agreementId="+aData.objId);
			});
			return nRow;
		},
		"sPaginationType":"full_numbers",
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": $('#initPath').val()+"/AgreementController.do?method=getVenderlist"
	});
	
});
	
