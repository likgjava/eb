var TemplateManageList = {};
TemplateManageList.oTable;

//获取操作字符串
TemplateManageList.getOperatorStr=function(objId,templateFileId,useStatus){
	var operatorHtml = '';
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="TemplateManageList.showDotTemplate(\''+objId+'\')"><span>查看</span></a>';
	operatorHtml += '<a title="修改" href="javascript:void(0);" onclick="TemplateManageList.updateDotTemplate(\''+objId+'\')"><span>修改</span></a>';
	if(useStatus == '02'){
		operatorHtml += '<a title="启用" href="javascript:void(0);" onclick="TemplateManageList.updateUseStatus(\''+objId+'\',\'01\')"><span>启用</span></a>';
	}else{
		operatorHtml += '<a title="禁用" href="javascript:void(0);" onclick="TemplateManageList.updateUseStatus(\''+objId+'\',\'02\')"><span>禁用</span></a>';
	}
	operatorHtml += '</td>';
	return operatorHtml;
}

//查看范本信息
TemplateManageList.showDotTemplate = function(objId){
	$.epsDialog({
		title:'范本信息',
		width: 600,
		height: 350,
		url:$('#initPath').val()+'/DotTemplateController.do?method=toDotTemplateDetailView&objId='+objId
	});
}

//修改范本信息
TemplateManageList.updateDotTemplate = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/DotTemplateController.do?method=toCreateOrUpdateView&objId="+objId);
}

//启用、禁用范本
TemplateManageList.updateUseStatus = function(objId, useStatus){
	var msg = (useStatus=='02' ? '禁用' : '启用');
	if(confirm("确定"+msg+"吗？")){
		$.getJSON($("#initPath").val()+"/DotTemplateController.do?method=updateUseStatus", {"objId":objId, 'useStatus':useStatus}, function(json){
			if(json.failure){alert(json.result);return;}
			TemplateManageList.oTable.fnDraw(); //刷新列表
		});
	}
}

//加载范本信息列表
TemplateManageList.getTemplateList = function(){
	if(null==TemplateManageList.oTable){
		TemplateManageList.oTable = $('#templateList').dataTable({
			'queryColumns' : 'templateName,districtName,categoryName,org.orgName,templateType,isShare,favoriteNum',
			'alias' : 'templateName,districtName,categoryName,org.orgName,templateTypeCN,isShare,favoriteNum',
			'hiddenColumns': 'templateFile,useStatus,downNum',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).find('td[name=isShare]').html((aData.isShare == 'true' ? '是' : '否'));
				$(nRow).find('td[name=favoriteNum]').html(aData.favoriteNum+'|'+aData.downNum);
				$(nRow).append(TemplateManageList.getOperatorStr(aData.objId,aData.templateFile,aData.useStatus));
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/DotTemplateController.do?method=list",
			"params":{},
			'searchZone':'templateSearchForm'
		});
	}else{
		TemplateManageList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val("view/pubservice/application/template/template_manage_list.jsp");
	
	//加载列表
	TemplateManageList.getTemplateList();
	
	//新增范本
	$('#addTemplate').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/DotTemplateController.do?method=toCreateOrUpdateView&userType=manager");
	});
})