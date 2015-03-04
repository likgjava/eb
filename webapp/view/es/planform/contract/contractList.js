
var contractList={};
contractList.rows=null;//列表查询的结果集
	//新增
	contractList.add=function(name,grid){
		//跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/ContractController.do?method=toCreateOrUpdate");
	}   
	//修改
	contractList.update=function(name,grid){
		if(!contractList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/ContractController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	contractList.remove=function(name,grid){
		if(!contractList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/ContractController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	contractList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	contractList.before=function(){
		var projectId = $("#projectId").val();
		var cuyerId = $("#cuyerId").val();
		var option={
			'projectId':projectId,
			'cuyerId':cuyerId,
			'contractStatus':'01'
		}
		$('#contractGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	contractList.success=function(){
		if(contractList.currentTabID == "tabs_toConfirm"||contractList.currentTabID == undefined){
			$("#contractGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					var objId = $('#contractGrid').getRowById(rowId)["objId"];
					$('#tabform').loadPage($('#initPath').val()+'/ContractController.do?method=toConfirmContract&objId='+rowId);
				}).appendTo(obj);
			}
			});
		}else 	if(contractList.currentTabID == "tabs_confirmed"){
			$("#contractGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">详情</a>' : function(btn,rowId,obj){
				btn.click(function(){
					var objId = $('#contractGrid').getRowById(rowId)["objId"];
					$('#tabform').loadPage($('#initPath').val()+'/ContractController.do?method=toContractDetailForBuyer&objId='+rowId);
				}).appendTo(obj);
			}
			});
		}
	}
$(document).ready(function(){
	$('#epsTabs3').tabs();
	contractList.currentTabID == "tabs_toConfirm";
	$("li a.refreshData").click(function(){
		$("#contractGrid").find("tDiv").empty();
		contractList.currentTabID = $(this).attr("id");
		//参数值
		if(contractList.currentTabID == "tabs_toConfirm"){//待确认
			$('#contractGrid').attr("p").url = "ContractController.do?method=list&contractStatus=01";
		}else if(contractList.currentTabID == "tabs_confirmed"){//已确认
			$('#contractGrid').attr("p").url = "ContractController.do?method=list&contractStatus=03";
		}
		$('#contractGrid').reload();
	})
});

