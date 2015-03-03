var BuyerChargeDepartmentList={};

$(document).ready(function(){
	
	var params = {};
	if(null!=$("#orgType").val()&&$("#orgType").val()=="s"){
		params = {"supplierId":null,"supplierId_op":"!="};
	}else if(null!=$("#orgType").val()&&$("#orgType").val()=="b"){
		params = {"buyerId":null,"buyerId_op":"!="};
	}else if(null!=$("#orgType").val()&&$("#orgType").val()=="a"){
		params = {"agencyId":null,"agencyId_op":"!="};
	}else{
		params = {};
	}
	
	BuyerChargeDepartmentList.oTable = $('#buyerSuperList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,user.emp.name,validTime',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			BuyerChargeDepartmentList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append("<td align='center'><a name='selectOrg' href='javascript:void(0);'>选择</a></td>").find('a[name=selectOrg]').click(function(){
				$("#"+$("#Hname").val()).val(aData.orgName);
				$("#"+$("#Hid").val()).val(aData.objId);
				$('.epsDialogClose').trigger('click');
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoController.do?method=list",
		"params":{"buyerId":null,"buyerId_op":"!="},
		'searchZone':'BuyerChargeDepartmentForm'
	});
	
	//查询
	$("#query").click(function(){
		BuyerChargeDepartmentList.oTable.fnDraw();
	})
	
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
