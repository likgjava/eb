/*
 * 协议管理，供货商资格
 * author: yucy
 * mail: yuchengyang@yeah.net
 */

//定义文件全局变量 处理方法名重复问题
var VenderQualificationList={};
VenderQualificationList.oTable;	

$(document).ready(function(){
	
	//加载列表
	VenderQualificationList.oTable=$('#VenderQualificationList').dataTable( {
		'params':{
			"useStatus":"02",
			"useStatus_op":"<"
			//区分权限的参数
			//,"supplier.objId":PlatForm.userInfo.orgInfo.objId
		},
		'searchZone':'agreementSearchForm',
		'singleSelect':false,	
		'checkbox':false,		
		'queryColumns':'name,org.orgName,authorizeSupplier,unAuthorizeClass,unAuthorizeGoods',
		'hiddenColumns':'objId,authorizeClass,authorizeGoods',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			
			//alert(aData['authorizeClass']+'/'+aData['unAuthorizeClass']);
			//alert(aData['authorizeGoods']+'/'+aData['unAuthorizeGoods']);
			
			//已授权的供货商
			var column=$(nRow).find('td:eq(2)');//获取第3列的对象
			column.html('<a id="authorizeSupplier" href="javascript:void(0);">'+aData['authorizeSupplier']+'</a>');//可以加ID或者onClick
			column.find('a[id=authorizeSupplier]').click(function(){
				if(aData['authorizeSupplier']<1){
					alert("无授权供货商!");
					return;
				}
				$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&toView=toAuthorizedVenderView&objId="+aData.objId);
			});
			
			//已授权的分类
			column = $(nRow).find('td:eq(3)');//获取第4列的对象
			column.html('<a id="authorizeClass" href="#">'+aData['authorizeClass']+"/"+aData['unAuthorizeClass']+'</a>');//可以加ID或者onClick
			column.find('a[id=authorizeClass]').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&resource=category&toView=toAuthorizedGoodsOrClassView&objId="+aData.objId);
			});
		
			//授权的单品
			column = $(nRow).find('td:eq(4)');//获取第5列的对象
			column.html('<a id="authorizeGoods" href="#">'+aData['authorizeGoods']+"/"+aData['unAuthorizeGoods']+'</a>');//可以加ID或者onClick
			column.find('a[id=authorizeGoods]').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&resource=goods&toView=toAuthorizedGoodsOrClassView&objId="+aData.objId);
			});
			
			//增加供货商按钮
			$(nRow).append('<td><a id="addSupplier" href="javascript:void(0);"><span>新增供货商</span></a></td>')//添加修改按钮
			$(nRow).find('a[id=addSupplier]').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&toView=toVenderFormView&objId="+aData.objId);
			});
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/AgreementController.do?method=getVenderlist"
	});
	
});
	
