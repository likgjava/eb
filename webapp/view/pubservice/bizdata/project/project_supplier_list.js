var ProjectList = {};
ProjectList.currentTabID = "tabs_doing";

//根据不同的操作，导向不同的页面
ProjectList.openOperatorPage=function(type,objId){
	if("detail" == type){
		$("#returnUrl").val($('#initPath').val()+"/view/pubservice/bizdata/project/project_supplier_list.jsp");
		$('#conBody').loadPage($('#initPath').val()+"/ProjectController.do?method=toProjectVeiw&objId="+objId);
	}
}

//评价
ProjectList.openEvaluateDiv = function(projectId,projectName){
	$.getJSON($("#initPath").val()+"/ProjectController.do?method=getEvaluateObject",{projectId:projectId},function(json){
		if(json.success){
			var ahref = '';
			$.each(json.evaluateOrg,function(index,obj){
				var orgType = '';
				if(null!=obj[2]&&obj[2]=='a'){
					orgType = '代理机构';
				}else if(null!=obj[2]&&obj[2]=='b'){
					orgType = '采购人';
				}else{
					orgType = '供应商';
				}
				ahref += '&nbsp;<a id="'+obj[0]+'" href="javascript:void(0);" onclick="ProjectList.openEvaluateClick(\''+obj[0]+'\',\''+projectId+'\',\''+projectName+'\');return false;" title="'+orgType+'">'+obj[1]+'</a>&nbsp;';
			})
			
			var evaluateDivHtml = '<div class="formTips attention"><ul><li><em>待评价机构：</em></li></ul></div>'+
			'<div class="formLayout form2Pa"><div class="conOperation"><ul><li>'+ahref+'</li></ul><button id="evlautaeDivBtn" type="button" class="largeBtn"><span>关闭</span></button></div></div>';
			
			$.epsDialog({id:"evlauateDiv", title:"对参与项目的机构评价",content:evaluateDivHtml,width: 300,height: 200});
			//关闭
			$("#evlautaeDivBtn").click(function(){$('.epsDialogClose').trigger('click');})
		}
	})
}

//评价
ProjectList.openEvaluateClick = function(objId,projectId,projectName){
	$.epsDialog({
		id:'evaluateDailog',
        title:'评价',
        url:$('#initPath').val()+'/EvaluateController.do?method=toEvaluate&orgId='+objId+'&projectId='+projectId+'&projectName='+projectName,
        width: '700',
        height: '500',
        onClose: function(){ 
			
        }
    }); 
}

$(document).ready(function(){
	//高级搜索
	$("#hightSearchSwitch").click(function(){
		$(".conSearch .hightSearch").toggle("slow");
		$(this).toggleClass("collapsable");
		$(".operationBtnDiv").toggleClass("hightSearchBtn");
	});
	//加载tab页
	var $tabs = $('#epsTabs').tabs({}); 
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		ProjectList.currentTabID = $(this).attr("id");
		if ("tabs_doing"==ProjectList.currentTabID) {
			$(ProjectList.oTable.dataTableSettings).attr("params", {"flag":"doing"});
		} else if ("tabs_down"==ProjectList.currentTabID) {
			$(ProjectList.oTable.dataTableSettings).attr("params", {"flag":"down"});
		} 
		ProjectList.oTable.fnDraw();
	})
	//加载项目列表
	ProjectList.oTable = $('#projectManageList').dataTable({
		'singleSelect' : true,//(false表示可以多选)
		'checkbox' : false,
		'queryColumns' : 'projCode,projName,ebuyMethod,purCategoryNames,projProcessStatus,purchAmout',
		'alias' : 'projCode,projName,ebuyMethodCN,purCategoryNames,projProcessStatusCN,purchAmout',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {//表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback' : function(oSettings) {
			ProjectList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮 
			if($(ProjectList.oTable.dataTableSettings).attr("params")['flag']=='down'){//查看、评价
				$(nRow).append('<td  align="center"><a href="javascript:void(0);" onclick="ProjectList.openOperatorPage(\'detail\',\''+aData.objId+'\');return false;">查看</a><a href="javascript:void(0);" onclick="ProjectList.openEvaluateDiv(\''+aData.objId+'\',\''+aData.projName+'\');return false;">评价</a></td>')
			}else{//查看
				$(nRow).append('<td  align="center"><a href="javascript:void(0);" onclick="ProjectList.openOperatorPage(\'detail\',\''+aData.objId+'\');return false;">查看</a></td>')
			}
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/SignUprecordController.do?method=toSupplierList",
		"params":{"flag":"doing"},
		'searchZone':'ProjectManageSearchForm'		
	});
	// 查询
	$("#query").click(function() {		
		ProjectList.oTable.fnDraw();
	})
})