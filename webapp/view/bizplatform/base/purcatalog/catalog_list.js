/***
 * 
 * 
 * create by yucy 2010.08.09
 * modify by yucy
 * 
 */


var catalogList={};  //定义文件全局变量 处理方法名重复问题
catalogList.oTable;	

//发布
catalogList.publish = function(catalogId){
	var publishHtml = '<div class="formTips attention"><ul><li><em>注意：</em><strong>发布操作，谨慎！</strong></li></ul></div><div class="conOperation"><button id="publishButton" type="button"  class="largeBtn" ><span>发布</span></button></div>';
	$.epsDialog({title:"目录发布",content:publishHtml,width: 200,height: 100});
	$("#publishButton").click(function(){
		$.getJSON($("#initPath").val()+'/PurCatalogController.do?method=updatePubStatus',{"catalogId":catalogId},function(json){
			if(json.success){
				alert('发布成功！');
				catalogList.oTable.fnDraw();
			}else{
				alert('发布失败！');
			}
			$('.epsDialogClose').trigger('click');
		})
	})
}

//删除
catalogList.deleteCatalog = function(id){					
	if(confirm("确定删除!")){
		$.getJSON($("#initPath").val()+"/PurCatalogController.do?method=deleteCatalog",{"objId":id},function(json){
			if(json.success){
				alert(json.result);
				catalogList.oTable.fnDraw();
			}
		})
	}
}

//拷贝
catalogList.createCopyCatalog = function(id,title){
	var createCopyHtml = '<div class="formLayout form2Pa"><ul><li class="fullLine">提示：确定复制？（慎重）</li>'+
						 '<li class="fullLine"><label>目录标题</label><input id="copyTitle" name="copyTitle" value="'+title+'"/></li></ul>'+
					  	 '<div class="operationBtnDiv"><button id="sureCopy" class="largeBtn" ><span>确定</span></button>'+
					  	 '<button id="cancelCopy" class="largeBtn" ><span>取消</span></button></div></div>';
	$.epsDialog({title:"复制目录",content:createCopyHtml,width: 300,height: 150});
	$("#sureCopy").click(function(){
		$.getJSON($("#initPath").val()+'/PurCatalogController.do?method=createCopyCatalog',{"objId":id,"copyTitle":$("#copyTitle").val()},function(json){
			if(json.success){
				$('.epsDialogClose').trigger('click');
			}else{
				alert("操作失败!");
				$('.epsDialogClose').trigger('click');
			}
			catalogList.oTable.fnDraw();
		})
	})
	$("#cancelCopy").click(function(){
		$('.epsDialogClose').trigger('click');
	})
}

$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//新建
			$(catalogList.oTable.dataTableSettings).attr("params",{"useStatus":"00","auditStatus_op":"in","auditStatus":"00,03"});
		}else if(ui.index==1){			//通过
			$(catalogList.oTable.dataTableSettings).attr("params",{'useStatus':'01','auditStatus':'02'});
		}
		catalogList.oTable.fnDraw();
	});
	
	//新增目录
	$("#addCatalog").click(function(){				
		$('#conBody').loadPage($('#initPath').val()+"/PurCatalogController.do?method=toCreateOrModifyView");
	})
	
	//删除目录
	$("#delCatalog").click(function(){				
		catalogList.delCatalog();
	})
	
	//加载列表
	catalogList.oTable=$('#catalogList').dataTable( {   
		'params':{"useStatus":"00","auditStatus_op":"in","auditStatus":"00,03"},
		'searchZone':'catalogSearchForm',
		'singleSelect':false,	
		'checkbox':true,		
		'queryColumns':'title,areaCode,areaName,publishStatus,year',
		'alias':'title,areaCode,areaName,publishStatusCN,year',
		'hiddenColumns':'useStatus,auditStatus',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			//添加操作
			$(nRow).append('<td class="operation"></td>');
			
			//修改
			if(aData.auditStatus=='00'||aData.auditStatus=='03'){
				$(nRow).find('td:last').append('<a name="modify" title="修改" href="javascript:void(0);" ><span>修改</span></a>');
				$(nRow).find('a[name=modify]').click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/PurCatalogController.do?method=toCreateOrModifyView&objId="+aData.objId);
				})
			}
			
			//复制
			if(aData.auditStatus=='00'||aData.auditStatus=='03'){
				$(nRow).find('td:last').append('<a name="copy" title="复制" href="javascript:void(0);"><span>复制</span></a>');
				$(nRow).find('a[name=copy]').click(function(){
					catalogList.createCopyCatalog(aData.objId,aData.title+"（复制）");
				})
			}
			
			//删除
			if(aData.auditStatus=='00'||aData.auditStatus=='03'){
				$(nRow).find('td:last').append('<a name="delete" title="删除" href="javascript:void(0);"><span>删除</span></a>');
				$(nRow).find('a[name=delete]').click(function(){
					catalogList.deleteCatalog(aData.objId);
				})
			}
			
			//查看
			if(aData.useStatus=='01'||aData.auditStatus=='02'){
				$(nRow).find('td:last').append('<a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a>');
				$(nRow).find('a[name=view]').click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/PurCatalogController.do?method=toCatalogView&objId="+aData.objId)
				})
			}
			
			//发布
			if(aData.useStatus=='01'&&aData.auditStatus=='02'&&aData.publishStatus!='01'){
				$(nRow).find('td:last').append('<a name="publish" title="发布" href="javascript:void(0);"><span>发布</span></a>');
				$(nRow).find('a[name=publish]').click(function(){
					catalogList.publish(aData.objId);
				})
			}
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/PurCatalogController.do?method=list"
	});
	
	//搜
	$("#catalogSearch").click(function(){
		catalogList.oTable.fnDraw();
	})
});