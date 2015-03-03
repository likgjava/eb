/*
 * 协议供货，供应商合同列表页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ContractSupplierList={};
ContractSupplierList.oTable;	

ContractSupplierList.currentTabID="tabs_submit"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看、作废”超链接
ContractSupplierList.getOperatorStr=function(objId){
	if ("tabs_submit"==ContractSupplierList.currentTabID) {
		return '<td class="operation"><a href="javascript:void(0);" onclick="ContractSupplierList.openOperatorPage(\'edit\',\''+objId+'\');return false;"><span>编辑</span></a></td>';
	} else if ("tabs_todo"==ContractSupplierList.currentTabID) {
		return '<td class="operation"><a href="javascript:void(0);" onclick="ContractSupplierList.openOperatorPage(\'sure\',\''+objId+'\');return false;"><span>确认</span></a></td>';
	} else if("tabs_notpass"==ContractSupplierList.currentTabID){
		return '<td class="operation"><a class="aLink" href="javascript:void(0);" onclick="ContractSupplierList.openOperatorPage(\'edit\',\''+objId+'\');return false;"><span>编辑</span></a></td>';
	} else if("tabs_done"==ContractSupplierList.currentTabID){
		return '<td class="operation"><a class="aLink" href="javascript:void(0);" onclick="ContractSupplierList.openOperatorPage(\'view1\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	} else if("tabs_tocancel"==ContractSupplierList.currentTabID){
		return '<td class="operation"><a class="aLink" href="javascript:void(0);" onclick="ContractSupplierList.openOperatorPage(\'view2\',\''+objId+'\');return false;"><span>确认</span></a></td>';
	} else if("tabs_cancel"==ContractSupplierList.currentTabID){
		return '<td class="operation"><a class="aLink" href="javascript:void(0);" onclick="ContractSupplierList.openOperatorPage(\'detail\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}
}

//根据不同的操作，导向不同的页面
ContractSupplierList.openOperatorPage=function(type,objId){
	
	//设置返回路径
	$("#returnUrl").val($("#initPath").val()+"/AgContractController.do?method=toContractSupplierList");
	
	if("edit" == type){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_supplier_confirm.jsp?type=edit&objId="+objId);
	} else if("submit" == type){
		if(confirm('确定要提交该合同吗？')){
			alert("已将该合同提交给甲方，请等待甲方确认！")
			ContractSupplierList.oTable.fnDraw();
		}
	}else if("sure" == type){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_supplier_view.jsp?type=sure&objId="+objId);
	} else if("cancel" == type){
		if(confirm('确定要取消该合同吗？')){
			ContractSupplierList.oTable.fnDraw();
		}
	} else if("view1" == type){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_supplier_view.jsp?type=view&objId="+objId);
	} else if("view2" == type){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_supplier_view.jsp?type=agree&objId="+objId);
	} else if("detail" == type){
		$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDetail&objId="+objId);
	} else if("invalid" == type){
		if(confirm('确定要申请作废该合同吗？')){
			alert("已将该申请提交给甲方，请等待甲方确认！")
			ContractSupplierList.oTable.fnDraw();
		}
	} else if("downFile" == type){
		//下载合同
		$.epsDialog({
	        title:'合同文件',
			url:$('#initPath').val()+'/view/agreement/contract/contract_file_div.jsp?attachRelaId='+objId
	  });
	}
}

//查询
ContractSupplierList.reload=function(){
	if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		$(ContractSupplierList.oTable.dataTableSettings).attr("params",
				$.extend(ContractSupplierList.oTable.dataTableSettings[0].params,{"contractSignedTime":$("#startDate").val(),"contractSignedTime_op":"ge"}));
	}
	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		$(ContractSupplierList.oTable.dataTableSettings).attr("params",
				$.extend(ContractSupplierList.oTable.dataTableSettings[0].params,{"contractSignedTime":$("#startDate").val(),"contractSignedTime_op":"le"}));
	}
	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		$(ContractSupplierList.oTable.dataTableSettings).attr("params",
				$.extend(ContractSupplierList.oTable.dataTableSettings[0].params,{"contractSignedTime":$("#startDate").val()+","+$("#endDate").val(),"contractSignedTime_op":"bt"}));
	}
	ContractSupplierList.oTable.fnDraw();
}

//创建或刷新列表数据
ContractSupplierList.getTableList = function() {
	if(!ContractSupplierList.oTable) {
		//项目列表
		ContractSupplierList.oTable=$('#ContractSupplierList').dataTable( {
    		'singleSelect':true,//(false表示可以多选)
    		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
    		'queryColumns':'contractNo,contractName,buyer.orgName,total,contractSignedTime,contractFile',//指定要查询的列
    		'hiddenColumns':'contractFile',//隐藏查询不显示的列属性
    		'fnInitComplete':function(oSettings) {
    			//表格初始化完毕、未开始查询之前的方法
    		},
    		'fnDrawCallback':function(oSettings) {	
    			ContractSupplierList.oTable.oSettings=oSettings;
    		},
    		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
    			$(nRow).find('td[name=contractFile]').html('<a href="javascript:void(0);" onclick="ContractSupplierList.openOperatorPage(\'downFile\',\''+aData.contractFile+'\');return false;">下载合同文件</a>'); // 合同文件作为超链
    			$(nRow).append(ContractSupplierList.getOperatorStr(aData.objId))//添加修改按钮
    			return nRow;
    		},
    		"sAjaxSource": $('#initPath').val()+"/AgContractController.do?method=list&contractType=02&orgType=supplier",
    		'params':{"tabsType":ContractSupplierList.currentTabID.replace("tabs_","")},
    		'searchZone':'contractForm'
    	});
	}else {
		$(ContractSupplierList.oTable.dataTableSettings).attr("params",{'tabsType':ContractSupplierList.currentTabID.replace("tabs_","")});
		ContractSupplierList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			ContractSupplierList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			ContractSupplierList.getTableList();
		}
	});
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	
	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		ContractSupplierList.getTableList();
	}

	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
	
	//查询
	$("#query").click(function(){
		ContractSupplierList.reload();
	})
});
	
