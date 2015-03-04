var TemplateList = {};
TemplateList.oTableMy;
TemplateList.oTableFavorite;
TemplateList.oTableDown;
TemplateList.oTableUsed;
TemplateList.currentTableId = 'tabs_my';

//获取操作字符串
TemplateList.getOperatorStr=function(templateId,objId,type){
	var operatorHtml = '';
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="TemplateList.showDotTemplate(\''+templateId+'\')"><span>查看</span></a>';
	if(type == 'my'){
		operatorHtml += '<a title="修改" href="javascript:void(0);" onclick="TemplateList.updateDotTemplate(\''+templateId+'\')"><span>修改</span></a>';
	}else if(type == 'down' || type == 'favorite'){
		operatorHtml += '<a title="删除" href="javascript:void(0);" onclick="TemplateList.deleteTemplate(\''+objId+'\',\''+type+'\')"><span>删除</span></a>';
	}
	operatorHtml += '</td>';
	return operatorHtml;
}

//查看范本信息
TemplateList.showDotTemplate = function(objId){
	$.epsDialog({
		title:'范本信息',
		width: 600,
		height: 350,
		url:$('#initPath').val()+'/DotTemplateController.do?method=toDotTemplateDetailView&objId='+objId
	});
}

//修改范本信息
TemplateList.updateDotTemplate = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/DotTemplateController.do?method=toCreateOrUpdateView&objId="+objId);
}

//当点击不同的tab页时，改变搜索表单的name属性值
TemplateList.changeInputName = function(tabId){
	if(TemplateList.currentTableId != tabId){
		if(tabId == 'tabs_my'){
			$('#templateName').attr('name', 'templateName').next().attr('name', 'templateName_op');
			$('#districtName').attr('name', 'districtName').next().attr('name', 'districtName_op');
			$('#categoryName').attr('name', 'categoryName').next().attr('name', 'categoryName_op');
			$('#templateType').attr('name', 'templateType');
		}else{
			$('#templateName').attr('name', 'dotTemplate.templateName').next().attr('name', 'dotTemplate.templateName_op');
			$('#districtName').attr('name', 'dotTemplate.districtName').next().attr('name', 'dotTemplate.districtName_op');
			$('#categoryName').attr('name', 'dotTemplate.categoryName').next().attr('name', 'dotTemplate.categoryName_op');
			$('#templateType').attr('name', 'dotTemplate.templateType');
		}
		
		if(tabId == 'tabs_used'){$('#projectNameInput').show();}else{$('#projectNameInput').hide();}
		if(tabId == 'tabs_favorite'){$('#favoriteNameInput').show();}else{$('#favoriteNameInput').hide();}
	}
}

//删除范本下载、收藏记录信息
TemplateList.deleteTemplate = function(objId,type){
	if(window.confirm('确认删除吗？')){
		var domainName = '';
		if(type == 'down'){domainName = 'DotTemplateDown';}else if(type == 'favorite'){domainName = 'DotTemplateFavorite';}
		$.getJSON($('#initPath').val()+'/'+domainName+'Controller.do?method=remove',{'objId':objId},function(json){
			if(json.failure){alert(json.result);return;}
			alert("删除成功!");
			$('#'+TemplateList.currentTableId).click();
		});
	}
}

//加载我的范本列表
TemplateList.getMyTemplateList = function(){
	if(null==TemplateList.oTableMy){
		TemplateList.oTableMy = $('#myTemplateList').dataTable({
			'queryColumns' : 'templateName,districtName,categoryName,templateType,isShare,favoriteNum',
			'alias' : 'templateName,districtName,categoryName,templateTypeCN,isShare,favoriteNum',
			'hiddenColumns': 'downNum',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).find('td[name=isShare]').html((aData.isShare == 'true' ? '是' : '否'));
				$(nRow).find('td[name=favoriteNum]').html(aData.favoriteNum+'|'+aData.downNum);
				$(nRow).append(TemplateList.getOperatorStr(aData.objId,null,'my'));
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/DotTemplateController.do?method=list",
			"params":{},
			'searchZone':'templateSearchForm'
		});
	}else{
		TemplateList.oTableMy.fnDraw();
	}
}

//加载收藏的范本列表
TemplateList.getFavoriteTemplateList = function(){
	if(null==TemplateList.oTableFavorite){
		TemplateList.oTableFavorite = $('#favoriteTemplateList').dataTable({
			'queryColumns' : 'favoriteName,dotTemplate.templateName,dotTemplate.districtName,dotTemplate.categoryName,dotTemplate.templateType',
			'alias' : 'favoriteName,dotTemplate.templateName,dotTemplate.districtName,dotTemplate.categoryName,dotTemplate.templateTypeCN',
			'hiddenColumns': 'dotTemplate.objId',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append(TemplateList.getOperatorStr(aData['dotTemplate.objId'],aData.objId,'favorite'));
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+"/DotTemplateFavoriteController.do?method=list",
		"params":{},
		'searchZone':'templateSearchForm'
		});
	}else{
		TemplateList.oTableFavorite.fnDraw();
	}
}

//加载下载的范本列表
TemplateList.getDownTemplateList = function(){
	if(null==TemplateList.oTableDown){
		TemplateList.oTableDown = $('#downTemplateList').dataTable({
			'queryColumns' : 'dotTemplate.templateName,dotTemplate.districtName,dotTemplate.categoryName,dotTemplate.templateType,createTime',
			'alias' : 'dotTemplate.templateName,dotTemplate.districtName,dotTemplate.categoryName,dotTemplate.templateTypeCN,createTime',
			'hiddenColumns': 'dotTemplate.objId',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append(TemplateList.getOperatorStr(aData['dotTemplate.objId'],aData.objId,'down'));
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+"/DotTemplateDownController.do?method=list",
		"params":{},
		'searchZone':'templateSearchForm'
		});
	}else{
		TemplateList.oTableDown.fnDraw();
	}
}

//加载使用过的范本列表
TemplateList.getUsedTemplateList = function(){
	if(null==TemplateList.oTableUsed){
		TemplateList.oTableUsed = $('#usedTemplateList').dataTable({
			'queryColumns' : 'dotTemplate.templateName,dotTemplate.categoryName,projectName,dotTemplate.templateType',
			'alias' : 'dotTemplate.templateName,dotTemplate.categoryName,projectName,dotTemplate.templateTypeCN',
			'hiddenColumns': 'dotTemplate.objId',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append(TemplateList.getOperatorStr(aData['dotTemplate.objId'],aData.objId,'used'));
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+"/DotTemplateUsedController.do?method=list",
		"params":{},
		'searchZone':'templateSearchForm'
		});
	}else{
		TemplateList.oTableUsed.fnDraw();
	}
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val("DotTemplateController.do?method=toDotTemplateListView");
	
	//加载tabs,绑定选中事件
	$('#epsTabs').tabs({});
	$('#tabs_my').click(function(){
		TemplateList.getMyTemplateList();
		TemplateList.changeInputName('tabs_my');
		TemplateList.currentTableId = 'tabs_my';
	});
	$('#tabs_favorite').click(function(){
		TemplateList.getFavoriteTemplateList();
		TemplateList.changeInputName('tabs_favorite');
		TemplateList.currentTableId = 'tabs_favorite';
	});
	$('#tabs_down').click(function(){
		TemplateList.getDownTemplateList();
		TemplateList.changeInputName('tabs_down');
		TemplateList.currentTableId = 'tabs_down';
	});
	$('#tabs_used').click(function(){
		TemplateList.getUsedTemplateList();
		TemplateList.changeInputName('tabs_used');
		TemplateList.currentTableId = 'tabs_used';
	});
	
	//加载我的范本列表
	TemplateList.getMyTemplateList();
	
	//新增范本
	$('#addTemplate').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/DotTemplateController.do?method=toCreateOrUpdateView");
	});
	
	//查询
	$('#queryTemplateList').click(function(){
		$('#'+TemplateList.currentTableId).click();
	});
})