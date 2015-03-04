//定义文件全局变量 处理方法名重复问题
var TaskPlanList={};
//查询条件过滤
TaskPlanList.before=function(){
	var option={"useStatus":$('#useStatus').val(),
			"createUser":PlatForm.user.objId,
			"leader":$('#leader').val(),
			"governmentId":$('#governmentId').val(),
			"confirmStatus":$('#confirmStatus').val(),
			"confirmStatus_relative":"[and:or]",
			"auditDetail":$('#auditDetail').val(),
			"auditDetail_relative":"[and:or]",
			"isSubmit":$('#isSubmit').val(),
			"taskAgentId":$('#taskAgentId').val(),
			"taskType":$('#taskType').val()
	};
	$('#TaskPlanGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
TaskPlanList.success=function(){
	var auditDetail = $('#auditDetail').val();
	var confirmStatus = $('#confirmStatus').val();
		if(auditDetail=='01'){
			$("#TaskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核资金</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				TaskPlanList.auditFund(rowId);
			}).appendTo(obj);
		}
			});
		}else if(confirmStatus=='04'){
			$("#TaskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">修改申报书</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#TaskPlanGrid").flexGetRowJsonById(rowId); 
				if(undefined != json.department.objId && null != json.department.objId && '' != json.department.objId 
				&& json.department.objId == json.budget.objId){// 一级单位
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdateForSum&objId='+rowId+'&paramType=desktopList');
				}else{// 二级单位
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdate&fromDesk=yes&objId='+rowId+'&paramType=desktopList');
				}
			}).appendTo(obj);
		}
			});
		}else if(confirmStatus=='01'){
			$("#TaskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核申报书</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				TaskPlanList.audit(rowId);
			}).appendTo(obj);
		}
			});
		}else if(auditDetail=='00'){
		$("#TaskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">提交申报书</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#TaskPlanGrid").flexGetRowJsonById(rowId); 
				if(undefined != json.department.objId && null != json.department.objId && '' != json.department.objId 
				&& json.department.objId == json.budget.objId){// 一级单位
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdateForSum&objId='+rowId+'&paramType=desktopList');
				}else{// 二级单位
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdate&fromDesk=yes&objId='+rowId+'&paramType=desktopList');
				}
			}).appendTo(obj);
		}
			});	
		}else if(confirmStatus=='02'||confirmStatus=='05'){//抽取招标中心
			$("#TaskPlanGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">抽取招标中心</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSelectAgentPage&fromType=fromDesk&objId='+rowId);	
				}).appendTo(obj);
			}
				});
		}else if(confirmStatus=='07'){//审核抽取招标中心
			$("#TaskPlanGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toAuditTaskPlanSelectForAgentPage&fromType=fromDesk&objId='+rowId);	
				}).appendTo(obj);
			}
				});
		}else if(confirmStatus=='06'){//待委托的申报书
			$("#TaskPlanGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">起草委托</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toCreateConsign&taskPlanId='+rowId);	
				}).appendTo(obj);
			}
				});
		}
		else if(confirmStatus=='03'){//待审核大宗申报书
			$("#TaskPlanGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanForExerciseAudit.jsp?fromType=fromDesk&objId='+rowId);	
				}).appendTo(obj);
			}
				});
		}
}
//待审核资金的申报书
TaskPlanList.auditFund=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanFormForAuditDe.jsp?objId='+rowId);
}
//待修改的申报书
TaskPlanList.modify=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdate&objId='+rowId+'&paramType=desktopList');
}
//待审核的申报书
TaskPlanList.audit=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toAuditTaskPlanPage&objId='+rowId);
}
