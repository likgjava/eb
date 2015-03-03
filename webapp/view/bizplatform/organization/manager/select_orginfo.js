var BuyerChargeDepartmentList={};

$(document).ready(function(){
	var params = {};
	if(null!=$("#orgType").val()&&$("#orgType").val()=="s"){
		params = {"supplierId":null,"supplierId_op":"!=","useStatus":"01","isOff":"1"};
		if($("#isManufacturer").val()=="true"){
			params.isManufacturer = null;
			params.isManufacturer_op = "!=";
		}
		if($("#filterVelue").val()){
			params.objId = $("#filterVelue").val();
			params.objId_op="!in";
		}		
		
	}else if(null!=$("#orgType").val()&&$("#orgType").val()=="b"){
		params = {"buyerId":null,"buyerId_op":"!=","useStatus":"01","isOff":"1"};
	}else if(null!=$("#orgType").val()&&$("#orgType").val()=="a"){
		params = {"agencyId":null,"agencyId_op":"!=","useStatus":"01","isOff":"1"};
	}else{
		params = {"useStatus":"01","isOff":"1"};
	}
	BuyerChargeDepartmentList.oTable = $('#buyerSuperList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,user.emp.name,validTime',
		'hiddenColumns':'isOff,useStatus,agencyId,buyerId,supplierId',
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
		"params":params,
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
	
	//清空
	$("#clear").click(function(){
		$("#"+$("#Hname").val()).val(null);
		$("#"+$("#Hid").val()).val(null);
		$('.epsDialogClose').trigger('click');
	})
})
