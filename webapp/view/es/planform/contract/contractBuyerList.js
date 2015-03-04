//定义文件全局变量 处理方法名重复问题
var ContractSupplierList={};
var cupplierId= $("#cupplierId").val();
var option={"contractStatus":"01","cuyerId":cupplierId};
//确认合同
ContractSupplierList.sure=function(name,grid){
	if(!ContractSupplierList.validation(name,grid))return;
	ContractSupplierList.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+$(grid).getSelect()+"&type=supplierToSure");
}

//查看详情-已签订
ContractSupplierList.showDetail_signed=function(name,grid){
	if(!ContractSupplierList.validation(name,grid))return;
	ContractSupplierList.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+$(grid).getSelect()+"&type=supplierViewSigned");
}

//查看详情-待提交
ContractSupplierList.showDetail_toSubmit=function(name,grid){
	if(!ContractSupplierList.validation(name,grid))return;
	ContractSupplierList.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+$(grid).getSelect()+"&type=supplierToSubmitView");
}

//查看详情-作废合同
ContractSupplierList.showDetail_toViewCancel=function(name,grid){
	if(!ContractSupplierList.validation(name,grid))return;
	ContractSupplierList.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+$(grid).getSelect()+"&type=supplierViewCancel");
}

//查看详情-待确认作废
ContractSupplierList.showDetail_toSureCancel=function(name,grid){
	if(!ContractSupplierList.validation(name,grid))return;
	ContractSupplierList.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+$(grid).getSelect()+"&type=supplierToSureCancel");
}

//新增
ContractSupplierList.supplierAdd=function(name,grid){
	//跳转到修改页面
	ContractSupplierList.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toSaveOrUpdate"+"&supplierId="+PlatForm.userInfo.orgInfo.objId);
} 

//修改
ContractSupplierList.supplierModify=function(name,grid){
	if(!ContractSupplierList.validation(name,grid))return;
	//跳转到修改页面
	ContractSupplierList.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toSaveOrUpdate&returnType=fromDesk&objId="+$(grid).getSelect());
} 

//列表操作验证
ContractSupplierList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个合同');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个合同');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
ContractSupplierList.before=function(){
	$('#ContractSupplierGrid').flexOptions({params:option});
	return true;
}

//设定返回时的路径
ContractSupplierList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/contract/contractSupplierList.jsp");
}

ContractSupplierList.success = function(){
	if(ContractSupplierList.currentTabID == "tabs_submit" || ContractSupplierList.currentTabID == "tabs_notpass"){
		$("#ContractSupplierGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					ContractSupplierList.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&fromType=fromLeft&objId="+rowId+"&type=supplierToSubmitView");
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					ContractSupplierList.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toUpdateContract&fromType=fromLeft&objId="+rowId);
				}).appendTo(obj);
			}
		});
	}
	if(ContractSupplierList.currentTabID == "tabs_todo"){
		$("#ContractSupplierGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">确认</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				ContractSupplierList.setRetrun();
				$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toConfirmContract&fromType=fromLeft&objId="+rowId+"&type=supplierToSubmitView");
			 }).appendTo(obj);
			}
		});
	}
	if(ContractSupplierList.currentTabID == "tabs_done"){
		$("#ContractSupplierGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				ContractSupplierList.setRetrun();
				$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetailForBuyer&fromType=fromLeft&objId="+rowId+"&type=supplierToSubmitView");
			 }).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	  ContractSupplierList.currentTabID = "tabs_todo";
	//加载tabs
	$('#epsTabs').tabs();
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		ContractSupplierList.currentTabID = $(this).attr("id");
		//参数值
		if(ContractSupplierList.currentTabID == "tabs_submit"){//待提交
			$('#ContractSupplierGrid').attr("p").newp = 1;
			option={"contractStatus":"00","cuyerId":cupplierId};
		}else if(ContractSupplierList.currentTabID == "tabs_todo"){//待确认
			$('#ContractSupplierGrid').attr("p").newp = 1;
			option={"contractStatus":"01","cuyerId":cupplierId};
		}else if(ContractSupplierList.currentTabID == "tabs_notpass"){//被退回
			$('#ContractSupplierGrid').attr("p").newp = 1;
			option={"contractStatus":"02","cuyerId":cupplierId};
		}else if(ContractSupplierList.currentTabID == "tabs_done"){//已签订
			$('#ContractSupplierGrid').attr("p").newp = 1;
			option={"contractStatus":"03","cuyerId":cupplierId};
		}
		$('#ContractSupplierGrid').reload();
	})
});
	
