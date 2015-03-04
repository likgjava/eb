var TemplateDownManageList = {};
TemplateDownManageList.oTable;

//获取操作字符串
TemplateDownManageList.getOperatorStr = function(objId,templateId,templateFileId){
	var operatorHtml = '';
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="TemplateDownManageList.showDotTemplate(\''+templateId+'\')"><span>查看</span></a>';
	operatorHtml += '<a title="删除" href="javascript:void(0);" onclick="TemplateDownManageList.deleteTemplateDown(\''+objId+'\')"><span>删除</span></a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//查看范本信息
TemplateDownManageList.showDotTemplate = function(objId){
	$.epsDialog({
		title:'范本信息',
		width: 600,
		height: 350,
		url:$('#initPath').val()+'/DotTemplateController.do?method=toDotTemplateDetailView&objId='+objId
	});
}

//删除范本下载记录信息
TemplateDownManageList.deleteTemplateDown = function(objId){
	$.getJSON($('#initPath').val()+"/DotTemplateDownController.do?method=remove",{'objId':objId},function(){
		if(json.failure){alert(json.result);return;}
		alert("删除成功!");
	});
}

//加载范本下载信息列表
TemplateDownManageList.getTemplateDownList = function(){
	if(null==TemplateDownManageList.oTable){
		TemplateDownManageList.oTable = $('#templateDwonList').dataTable({
			'queryColumns' : 'dotTemplate.templateName,dotTemplate.districtName,dotTemplate.categoryName,org.orgName,dotTemplate.templateType,createTime',
			'alias' : 'dotTemplate.templateName,dotTemplate.districtName,dotTemplate.categoryName,org.orgName,dotTemplate.templateTypeCN,createTime',
			'hiddenColumns': 'dotTemplate.objId,dotTemplate.templateFile',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append(TemplateDownManageList.getOperatorStr(aData.objId,aData['dotTemplate.objId'],aData['dotTemplate.templateFile']));
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/DotTemplateDownController.do?method=list",
			"params":{},
			'searchZone':'templateDownSearchForm'
		});
	}else{
		TemplateDownManageList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	$("#templateType").attr('name', 'dotTemplate.templateType');
	
	//加载列表
	TemplateDownManageList.getTemplateDownList();
});