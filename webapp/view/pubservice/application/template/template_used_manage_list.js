var TemplateUsedManageList = {};
TemplateUsedManageList.oTable;

//获取操作字符串
TemplateUsedManageList.getOperatorStr = function(templateId){
	var operatorHtml = '';
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="TemplateUsedManageList.showDotTemplate(\''+templateId+'\')"><span>查看</span></a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//查看范本信息
TemplateUsedManageList.showDotTemplate = function(objId){
	$.epsDialog({
		title:'范本信息',
		width: 600,
		height: 350,
		url:$('#initPath').val()+'/DotTemplateController.do?method=toDotTemplateDetailView&objId='+objId
	});
}

//加载范本使用记录列表
TemplateUsedManageList.getTemplateUsedList = function(){
	if(null==TemplateUsedManageList.oTable){
		TemplateUsedManageList.oTable = $('#templateUsedList').dataTable({
			'queryColumns' : 'dotTemplate.templateName,org.orgName,projectName,dotTemplate.templateType,createTime',
			'alias' : 'dotTemplate.templateName,org.orgName,projectName,dotTemplate.templateTypeCN,createTime',
			'hiddenColumns': 'dotTemplate.objId',
			'fnRowCallback': function(nRow, aData, iDisplayIndex) {
				$(nRow).append(TemplateUsedManageList.getOperatorStr(aData['dotTemplate.objId']));
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/DotTemplateUsedController.do?method=list",
			"params":{},
			'searchZone':'templateUsedSearchForm'
		});
	}else{
		TemplateUsedManageList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	$("#templateType").attr('name', 'dotTemplate.templateType');
	
	//加载列表
	TemplateUsedManageList.getTemplateUsedList();
});