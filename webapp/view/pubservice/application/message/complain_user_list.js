
var complainList={};

complainList.oTable1;


complainList.currentTabID="tabs_telled"; //当前Tab的ID

//生成列表中每条数据的操作：根据登录用户不同，生成“处理”和“查看”超链接
complainList.getOperatorStr=function(objId){
	if ("tabs_telled"==complainList.currentTabID) {
	return '<td><a href="javascript:void(0);" onclick="complainList.openOperatorPage(\'tabs_telled\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}
	else if("tabs_tell"==complainList.currentTabID){
		return '<td><a href="javascript:void(0);" onclick="complainList.openOperatorPage(\'tabs_tell\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}
	else if("tabs_complained"==complainList.currentTabID){
		return '<td><a href="javascript:void(0);" onclick="complainList.openOperatorPage(\'tabs_complained\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}
	else if("tabs_complain"==complainList.currentTabID){
		return '<td><a href="javascript:void(0);" onclick="complainList.openOperatorPage(\'tabs_complain\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}

}

//根据不同的操作，导向不同的页面
complainList.openOperatorPage=function(currentTabID,objId){		
		$('#conBody').loadPage($('#initPath').val()+'/ComplainController.do?method=toView&objId='+ objId + '&currentTabID='+currentTabID);	
} 

$(document).ready(function(){
	
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 
	
	if($("#currentTabID").val()!=null && $("#currentTabID").val()!=''){
		complainList.currentTabID = $("#currentTabID").val();
	}	
	
	//投诉举报
	complainList.oTable1=$('#tellList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'title,complainTime,isDispose,result',//指定要查询的列 content,		
		'hiddenColumns':'objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(complainList.getOperatorStr(aData.objId))//添加操作按钮			
			return nRow;
		},
		params:{"type":"00"},
		"sAjaxSource": $('#initPath').val()+"/ComplainController.do?method=list&tellType=telled",
		'searchZone':'complainSearchZone'
	});
	
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		complainList.currentTabID = $(this).attr("id");	
		
		if(complainList.currentTabID == "tabs_telled"){//我收到的投诉
			$(complainList.oTable1.dataTableSettings).attr('params', {"type":"00"});
			$(complainList.oTable1.dataTableSettings).attr('sAjaxSource', $('#initPath').val()+"/ComplainController.do?method=list&tellType=telled");
			complainList.oTable1.fnDraw();
		}else if(complainList.currentTabID == "tabs_tell"){//我做出的投诉			
			$(complainList.oTable1.dataTableSettings).attr('params', {"type":"00"});
		    $(complainList.oTable1.dataTableSettings).attr('sAjaxSource', $('#initPath').val()+"/ComplainController.do?method=list&tellType=tell");
			complainList.oTable1.fnDraw();
		}else if(complainList.currentTabID == "tabs_complained"){//我收到的举报		
			$(complainList.oTable1.dataTableSettings).attr('sAjaxSource', $('#initPath').val()+"/ComplainController.do?method=list&tellType=telled");
			$(complainList.oTable1.dataTableSettings).attr('params', {"type":"01"});
			complainList.oTable1.fnDraw();
		}else if(complainList.currentTabID == "tabs_complain"){//我做出的举报	
			$(complainList.oTable1.dataTableSettings).attr('sAjaxSource', $('#initPath').val()+"/ComplainController.do?method=list&tellType=tell");
			$(complainList.oTable1.dataTableSettings).attr('params', {"type":"01"});
			complainList.oTable1.fnDraw();
		}
	});
	
	
	if($('#currentTabID').val()=='tabs_telled'){	
		$('#tabs_telled').click();
	}
	else if ($('#currentTabID').val()=='tabs_tell'){
		$('#tabs_tell').click();		
	}
	else if ($('#currentTabID').val()=='tabs_complained'){
		$('#tabs_complained').click();		
	}
	else if ($('#currentTabID').val()=='tabs_complain'){
		$('#tabs_complain').click();		
	}
	
	//查询
	$("#query").click(function(){	
		complainList.oTable1.fnDraw();
	});

});

