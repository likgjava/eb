/*
 * 执行平台，采购申报书列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanListForSum={};

//设定返回时的路径
taskPlanListForSum.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/taskplan/taskPlanListForSum.jsp");
}
  
taskPlanListForSum.batchAudit = function(){
	var json = {};
	json['idsP']='objIds';
	json['opinionP']='opinion';
	json['ids'] =$('#taskPlanSubGrid').getSelects();
	json['passAction'] = 'TaskPlanController.do?method=submitTwainTaskPlan';
	json['noPassAction'] = 'TaskPlanController.do?method=rejectTwainTaskPlan';
	$.epsDialog({
				title:'批量审核',
				url:$('#initPath').val()+'/view/srplatform/batch/audit.jsp?json='+obj2str(json), 
				width: 500,
				height: 200,
				afterLoad: function(){}, //加载完url后调用
				onClose: function(){$('#taskPlanSubGrid').reload()} //关闭后调用
			});	
}
//新增
taskPlanListForSum.add=function(name,grid){
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+"/TaskPlanController.do?method=toCreateTaskPlanStair");
}  

//修改
taskPlanListForSum.update=function(name,grid){
	if(!taskPlanListForSum.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toUpdateTaskPlanStair&objId='+$(grid).getSelect());
} 

//删除
taskPlanListForSum.remove=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(window.confirm('确定'+name+'?')){
		var ids = $(grid).getSelects().split(",");
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=removeTaskPlan',{objId:ids},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//发起委托
taskPlanListForSum.consign=function(name,grid){
	if(!taskPlanListForSum.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toDraft&taskPlanId='+$(grid).getSelect());
} 

//查看详情
taskPlanListForSum.showDetail=function(name,grid)
{
	if(!taskPlanListForSum.validation(name,grid))return;
	taskPlanListForSum.setRetrun();
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+$(grid).getSelect());
}

//查看下级详情
taskPlanListForSum.showDetailSub=function(name,grid)
{
	if(!taskPlanListForSum.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=sub&objId='+$(grid).getSelect());
}

//提交审核
taskPlanListForSum.auditAgian = function(name,grid)
{
	if(!taskPlanListForSum.validation(name,grid))return;
	if(window.confirm('确定要提交审核吗？')){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitTaskPlan', 
				{objIds:$(grid).getSelect(),"auditDetail":"01"}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//列表操作验证
taskPlanListForSum.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanListForSum.before=function(){
	var option={"createUser.objId":PlatForm.user.objId,
				"auditDetail":"00",
				"useStatus":"00",
				"order":"createTime",
				"order_flag":"true"
				}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}

//查询下级条件过滤
taskPlanListForSum.beforeSub=function(){
	var option={"orgId":PlatForm.user.emp.company.objId}
	$('#taskPlanSubGrid').flexOptions({params:option});
	return true;
}
//查询下级条件过滤
taskPlanListForSum.beforeSub2=function(){
	var option={"orgId":PlatForm.user.emp.company.objId}
	$('#taskPlanSubGrid2').flexOptions({params:option});
	return true;
}
//加载数据成功之后调用的函数
taskPlanListForSum.success=function(){
	//添加查看招标中心信息超链接
	   $("#taskPlanGrid").flexGetColByName({
			'taskAgentName':function(id,t){
		   var agentId = $("#taskPlanGrid").getRowById(id)["taskAgent.objId"];
		   var agentName = $("#taskPlanGrid").getRowById(id)["taskAgentName"];
		   $(t).html('<a href="#" onClick="getAgentMessage(\''+agentId+'\',\''+agentName+'\');">'+$(t).html()+'</a>');
		   }
		});
	
	
	if(taskPlanListForSum.currentTabID == "tabs_toSubmit"){// 本级待提交
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toUpdateTaskPlanStair&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=removeTaskPlan',{objId:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#taskPlanGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}
	if(taskPlanListForSum.currentTabID == "tabs_toSure"){// 本级待采购办审核
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					taskPlanListForSum.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
	if(taskPlanListForSum.currentTabID == "tabs_toAdjust"){// 本级被退回
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+rowId);
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toUpdateTaskPlanStair&objId='+rowId);
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				if(window.confirm('确定删除吗?')){
					$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=removeTaskPlan',{objId:rowId},function(json){
						if(json.result)alert(json.result);if(json.failure)return;
						$("#taskPlanGrid").reload();
					});
				}
			}).appendTo(obj);
		}
		});
	}
	if(taskPlanListForSum.currentTabID =="tabs_forSelectAgent"){//待抽取招标中心
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">抽取招标中心</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSelectAgentPage&objId='+rowId);
			}).appendTo(obj);
		   },
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					taskPlanListForSum.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
	if(taskPlanListForSum.currentTabID =="tabs_SelectAgentAgain"){//重新抽取招标中心
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">抽取招标中心</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				  $('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSelectAgentPage&objId='+rowId);
			}).appendTo(obj);
		   },
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					taskPlanListForSum.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
	if(taskPlanListForSum.currentTabID =="tabs_Audit"){//待审核抽取招标中心
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					taskPlanListForSum.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
	if(taskPlanListForSum.currentTabID =="tabs_aleradyCreateHistory"){//查看历史
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					taskPlanListForSum.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&canConsign=no&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}	
	if(taskPlanListForSum.currentTabID =="tabs_aleradySelectAgent"){//已抽取招标中心
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					taskPlanListForSum.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&canConsign=no&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
	$("#taskPlanGrid").flexGetColByName({
		'confirmStatus':function(id,t){
			var json = $("#taskPlanGrid").flexGetRowJsonById(id);
			var confirmStatus = json['confirmStatus'];
			var useStatus = json['useStatus'];
			var auditDetail = json['auditDetail'];
			var leaderId = json['leader.objId'];
			$(t).html(taskPlanListForSum.getStatus(confirmStatus,useStatus,auditDetail,leaderId));
		}
	});
}
taskPlanListForSum.success1=function(){
	if(taskPlanListForSum.currentTabID == "tabs_toSubmit_sub"){// 下级待提交
		$("#taskPlanSubGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$("#allTask").hide();
				$('#taskPlanDetailForSum').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=sub&objId='+rowId);
			}).appendTo(obj);
		}
		});
	}
	$("#taskPlanSubGrid").flexGetColByName({
		'confirmStatus':function(id,t){
			var json = $("#taskPlanSubGrid").flexGetRowJsonById(id);
			var confirmStatus = json['confirmStatus'];
			var useStatus = json['useStatus'];
			var auditDetail = json['auditDetail'];
			var leaderId = json['leader.objId'];
			$(t).html(taskPlanListForSum.getStatus(confirmStatus,useStatus,auditDetail,leaderId));
		}
	});
}
taskPlanListForSum.success2=function(){
	if(taskPlanListForSum.currentTabID == "tabs_aleradyCreateSubHistory"){// 下级审核历史记录
		$("#taskPlanSubGrid2").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&canConsign=no&objId='+rowId);
			}).appendTo(obj);
		}
		});
	}
	$("#taskPlanSubGrid2").flexGetColByName({
		'confirmStatus':function(id,t){
			var json = $("#taskPlanSubGrid2").flexGetRowJsonById(id);
			var confirmStatus = json['confirmStatus'];
			var useStatus = json['useStatus'];
			var auditDetail = json['auditDetail'];
			var leaderId = json['leader.objId'];
			$(t).html(taskPlanListForSum.getStatus(confirmStatus,useStatus,auditDetail,leaderId));
		}
	});
}
function getAgentMessage(objId,name)//获得招标中心信息
{
	  $.epsDialog({
	        title:'招标中心信息',
	        url:$('#initPath').val()+'/UserApiController.do?method=getAgentMessageByObjId&objId='+objId,
	        width: '600',
	        height: '400',
	 	    isReload:true,
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
				});
}


taskPlanListForSum.getStatus = function(confirmStatus,useStatus,auditDetail,leaderId){
	var confirm = confirmStatus;
	var status = useStatus;
	var audit = auditDetail;
	var leader = leaderId;
//	alert('confirm：'+confirm+' status:'+status+' audit:'+audit+' leader:'+leader);
	if ("00"==confirm&&"01"==status&&"00"==audit) {//待上级采购单位审核申报书
		return "待上级采购单位审核";
	} else if ("00"==confirm&&"01"==audit) {//待业务处室审核采购资金
		return "待业务处室审核资金";
	} else if ("01"==confirm&&"02"==audit) {//待采购办管理员审核采购方式
		return "待采购办审核采购方式";
	} else if ("01"==confirm&&"02"==audit) {//待采购办审核申报书
		return "待采购办审核申报书";
	} else if ("02"==confirm&&"02"==audit) {//待招标单位抽取招标中心
		return "待招标单位抽取招标中心";
	} else if ("05"==confirm&&"02"==audit) {//招标单位指定招标中心不通过
		return "招标单位指定招标中心不通过";
	} else if ("07"==confirm&&"02"==audit) {//待采购办审核招标中心
		return "待采购办审核招标中心";
	} else if ("06"==confirm&&"02"==audit) {//审核通过
		return "采购办审核通过";
	} else {//待提交申报书
		return "待招标单位提交";
	}
}
$(document).ready(function(){
	//日历控件
	$('input[name=applyDate]').epsDatepicker();
	
	//设定返回时的路径
	taskPlanListForSum.setRetrun();
	
	//加载tabs
	$('#epsTabs').tabs();
	taskPlanListForSum.currentTabID = "tabs_toSubmit";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		taskPlanListForSum.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(taskPlanListForSum.currentTabID == "tabs_toSubmit"){//待提交	
			buttons = [{name:"新增",bclass:"add",onpress:taskPlanListForSum.add},
			           {name:"批量删除",bclass:"delete",onpress:taskPlanListForSum.remove}
			];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=00&auditDetail=00&order=createTime&order_flag=true";
			$('#taskPlanListInfo').find('input[type=checkbox]').show();
			$('#taskPlanGrid').attr("p").checkbox=true;
		}else if(taskPlanListForSum.currentTabID == "tabs_toSure"){//待审核
			buttons = [];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&confirmStatus=01&confirmStatus_relative=[and:or]&auditDetail=01&auditDetail_relative=[and:or]";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}else if(taskPlanListForSum.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [{name:"批量删除",bclass:"delete",onpress:taskPlanListForSum.remove}
			];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&confirmStatus=04&confirmStatus_relative=[and:or]&auditDetail=04&auditDetail_relative=[and:or]";
			$('#taskPlanListInfo').find('input[type=checkbox]').show();
			$('#taskPlanGrid').attr("p").checkbox=true;
		}else if(taskPlanListForSum.currentTabID == "tabs_done"){//已通过
			buttons = [{name:"详情",bclass:"look",onpress:taskPlanListForSum.showDetail},
			           {name:"发起委托",bclass:"audit",onpress:taskPlanListForSum.consign}
			];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&auditDetail=02&confirmStatus=02";
		}else if(taskPlanListForSum.currentTabID == "tabs_forSelectAgent"){//待抽取招标中心
			buttons = [];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&auditDetail=02&confirmStatus=02&taskType=00";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}else if(taskPlanListForSum.currentTabID == "tabs_Audit"){ //待审核抽取招标中心
			buttons = [];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&auditDetail=02&confirmStatus=07";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}else if(taskPlanListForSum.currentTabID == "tabs_SelectAgentAgain"){//重新抽取招标中心
			buttons = [];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&auditDetail=02&confirmStatus=05";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}else if(taskPlanListForSum.currentTabID == "tabs_aleradySelectAgent"){//已抽取招标中心
			buttons = [];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=02&auditDetail=02&confirmStatus=06&taskType=00";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}else if(taskPlanListForSum.currentTabID == "tabs_aleradyCreateHistory"){//查看历史
			buttons = [];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&auditDetail=&useStatus=&order=applyDate&order_flag=true";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}
		if("tabs_toSubmit_sub" == $(this).attr("id")){
			$('#taskPlanSubGrid').reload();
			$('#taskPlanListInfo').find('input[type=checkbox]').show();
			$('#taskPlanGrid').attr("p").checkbox=true;
			return false;
		}else if("tabs_aleradyCreateSubHistory" == $(this).attr("id")){
			$('#taskPlanSubGrid2').reload();
			$('#taskPlanListInfo').find('input[type=checkbox]').show();
			$('#taskPlanGrid').attr("p").checkbox=true;
			return false;
		}
		$('#taskPlanGrid').flexReDrawButtons(buttons);
		$('#taskPlanGrid').reload();
	})
});

