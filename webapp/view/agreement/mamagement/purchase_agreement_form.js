/*
 * 平台开发  协议管理  form页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var AgreementForm={};

//保存方法(设置是否提示 并判断是否需要返回值)
AgreementForm.save = function(){
	if(!$('#AgreementForm').valid()){alert('请正确填写表单!');return;}
	$.getJSON($('#initPath').val()+'/AgreementController.do?method=saveAgreement',
		formToJsonObject("AgreementForm"),
		function(json){
			if(json.success)
				alert("保存成功!");
				$("#objId").val(json.objId);
		}
	);
}

//选择甲方objId
AgreementForm.selectOrg = function(){
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭时用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=org.objId,org.orgName' 
				+'&returnColums=objId,orgName'
				+'&DialogId=orgSelect'
				+'&isCheckBox=false'
				+'&colums=orgName,orgCode'
				+'&columCns=甲方名称,机构代码'
				+'&domain=OrgInfo'
				;
	$.epsDialog({
		id:'orgSelect',
        title:'请选择甲方',
        url:$('#initPath').val()+'/view/agreement/mamagement/agentSelectList.jsp?'+params,
        width: '700',
        height: '400'
	}); 
}


//选择乙方
AgreementForm.selectSupplier = function(){
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭时用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=supplier.objId,supplier.orgName' 
				+'&returnColums=objId,orgName'
				+'&DialogId=supplierSelect'
				+'&isCheckBox=false'
				+'&colums=orgName,orgCode'
				+'&columCns=乙方名称,机构代码'
				+'&domain=OrgInfo'
				;
	$.epsDialog({
	   id:'supplierSelect',
       title:'请选择乙方',
       url:$('#initPath').val()+'/view/agreement/mamagement/supplierSelectList.jsp?'+params,
       width: '700',
       height: '400'
	}); 	
}


//选择期间
AgreementForm.selectPeriod = function(){
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭时用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=period.objId,period.periodName' 
				+'&returnColums=objId,periodName'
				+'&DialogId=periodSelect'
				+'&isCheckBox=false'
				+'&colums=periodName,memo'
				+'&columCns=期间名称,期间备注'
				+'&domain=Period';
	$.epsDialog({
		id:'periodSelect',
        title:'选择关联期间',
        url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
        width: '700',
        height: '400'
	}); 
}

//选择区间
AgreementForm.selectArea = function(){
	$.epsDialog({
		id:"selectArea",
		title:'选择关联区间',
        url:$('#initPath').val()+'/AreaController.do?method=toAreaTreeChoose&isValid=1',
        width: '600',
        height: '400'
    }); 
}

$(document).ready(function(){
	
	//签订时间
	$('#creTime').epsDatepicker({ timeShow:true});
	$('#creTime').val((new Date()).Format("yyyy-MM-dd hh:mm:ss"));	
	
	
	//甲方选择弹框
	$("input[name=org.orgName]").click(function(){
		AgreementForm.selectOrg();
	});
	
	//期间选择弹框
	$("input[name=period.periodName]").click(function(){
		AgreementForm.selectPeriod();
	});
	
	//选择乙方(经销商)
	$("input[name=supplier.orgName]").click(function(){
		AgreementForm.selectSupplier();
	});
	
	//加载附件
	var resId = $("#agreementFile").attr("value");
	if(resId){
		$('#agreementFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=agreementFile&attachRelaId='+resId);
	}else{
		$('#agreementFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=agreementFile');
	}
	
	//返回
	$('#purchaseAgreementReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/purchase_agreement_list.jsp");
	});
	
	//下一步(挑选品牌和分类)
	$('#purchaseAgreementNext').click(function(){
		if(null==$("#objId").val()||""==$("#objId").val()){alert("请先保存协议信息!");return;}
		$('#conBody').loadPage($('#initPath').val()+'/AgreementController.do?method=toChooseClassBrand&toView=toChooseClassBrandView&objId='+$("#objId").val());
	});
	
	//保存
	$('#purchaseAgreementSave').click(function(){
		AgreementForm.save(null);
	});
	
});
