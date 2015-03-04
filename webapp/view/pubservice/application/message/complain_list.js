
var complainList={};

complainList.oTable1;
complainList.oTable2;

complainList.currentTabID="tabs_tell"; //当前Tab的ID

//生成列表中每条数据的操作：根据登录用户不同，生成“处理”和“查看”超链接
complainList.getOperatorStr=function(objId){
	if ("tabs_tell"==complainList.currentTabID) {
	return '<td class="operation"><a href="javascript:void(0);" onclick="complainList.openOperatorPage(\'tabs_tell\',\''+objId+'\');return false;"><span>处理</span></a></td>';
	}
	else if("tabs_complain"==complainList.currentTabID){
		return '<td class="operation"><a href="javascript:void(0);" onclick="complainList.openOperatorPage(\'tabs_complain\',\''+objId+'\');return false;"><span>处理</span></a></td>';
	}

}

//根据不同的操作，导向不同的页面
complainList.openOperatorPage=function(currentTabID,objId){	
	$('#conBody').loadPage($('#initPath').val()+'/ComplainController.do?method=toDeal&objId='+ objId + '&currentTabID='+currentTabID);		
}


$(document).ready(function(){	
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		complainList.currentTabID = $(this).attr("id");	
		
		if(complainList.currentTabID == "tabs_tell"){//投诉
			$(complainList.oTable1.dataTableSettings).attr('params', {"type":"00"});
			$('#currentTabID').attr("value","tabs_tell");
			complainList.oTable1.fnDraw();
		}else if(complainList.currentTabID == "tabs_complain"){//举报			
			$(complainList.oTable1.dataTableSettings).attr('params', {"type":"01"});
			$('#currentTabID').attr("value","tabs_complain");
			complainList.oTable2.fnDraw();
		}
	});
	
	
	//投诉
	complainList.oTable1=$('#tellList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'title,complainantName,complainTime,beCompanyName,isDispose',//指定要查询的列
		//'alias':'goodsPriceSupplier.goods.productName,goodsPriceSupplier.supplier.name,useStatus,goodsPriceSupplier.protocolCN,marktUnitPrice,dscuRate,prtcPrice,efctDate,endDate,town.parent.parent.name',
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
		"sAjaxSource": $('#initPath').val()+"/ComplainController.do?method=list",
		'searchZone':'complainSearchZone'
	});
	
	//举报
	complainList.oTable2=$('#complainList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'title,complainantName,complainTime,beCompanyName,isDispose',//指定要查询的列
		//'alias':'goodsPriceSupplier.goods.productName,goodsPriceSupplier.supplier.name,useStatus,goodsPriceSupplier.protocolCN,marktUnitPrice,dscuRate,prtcPrice,efctDate,endDate,town.parent.parent.name',
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
		params:{"type":"01"},
		"sAjaxSource": $('#initPath').val()+"/ComplainController.do?method=list",
		'searchZone':'complainSearchZone'
	});
	
	if($('#currentTabID').val()=='tabs_tell'){	
		$('#tabs_tell').click();
	}
	else if ($('#currentTabID').val()=='tabs_complain'){
		$('#tabs_complain').click();		
	}
	
	//查询
	$("#query").click(function(){		
		if($('#currentTabID').val()=='tabs_tell' || $('#currentTabID').val()==''){	
			complainList.oTable1.fnDraw();
		}
		else if ($('#currentTabID').val()=='tabs_complain'){
			complainList.oTable2.fnDraw();	
		}		
	});

});

