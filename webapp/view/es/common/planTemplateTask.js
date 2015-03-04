var planTemplateTask = {};
var planTemplateTaskTree;
planTemplateTask.stairProjectPlan = new Array();
planTemplateTask.secondProjectPlan = new Array();
planTemplateTask.thirdProjectPlan = new Array();

// 获取一级项目计划
planTemplateTask.getStairProjectPlan = function(projectId){
	if (planTemplateTask.stairProjectPlan.length == 0) {
		$.ajax({
			url:"ProjectPlanController.do?method=getStairProjectPlan",
			data:{'projectId':projectId},
			type:"POST",
			async: false,
				success:function(msg){
				msg = eval('('+msg+')');
				planTemplateTask.stairProjectPlan = msg.result;
			},error:function(e){
				alert('获取一级工作计划异常！');
				return false;
			}
		})
	}
	return planTemplateTask.stairProjectPlan;
}

// 获取二级项目计划
planTemplateTask.getSecondProjectPlan = function(projectId){
	if (planTemplateTask.secondProjectPlan.length == 0) {
		$.ajax({
			url:"ProjectPlanController.do?method=getSecondStatusLightData",
			data:{'projectId':projectId},
			type:"POST",
			async: false,
				success:function(msg){
				msg = eval('('+msg+')');
				planTemplateTask.secondProjectPlan = msg.result;
			},error:function(e){
				alert('获取二级工作计划异常！');
				return false;
			}
		})
	}
	return planTemplateTask.secondProjectPlan;
}

// 获取三级项目计划
planTemplateTask.getThirdProjectPlan = function(projectId){
	if (planTemplateTask.thirdProjectPlan.length == 0) {
		$.each(planTemplateTask.getSecondProjectPlan(projectId),function(i,n){
			$.each(planTemplateTask.secondProjectPlan[i].children,function(k,o){
				planTemplateTask.thirdProjectPlan.push(o);
			})
		})
	}
	return planTemplateTask.thirdProjectPlan;
}

//更新项目进度条
planTemplateTask.updateProjectProcess = function(projectId){
	$.getJSON($("#initPath").val()+"/ProjectController.do?method=updateProjectProcess",{"objId":projectId},function(json){
       var process = json.process+"%";
		$("span[id='spaceused']").text(process);
		$('#spaceused').progressBar({showText:false});
	});
}
//更新项目下个阶段显示
planTemplateTask.updateProjectProcessCN = function(projectId){
	$.getJSON($("#initPath").val()+"/ProjectController.do?method=getWaitprocWorkTaskProjectPlan",{"objId":projectId},function(json){
       alert(JSON.stringify(json));
	});
}

//加载
planTemplateTask.loadPlanTree = function(treeId,projectId){
	planTemplateTaskTree = new dhtmlXTreeObject(treeId,"100%","100%",0);
	planTemplateTaskTree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	planTemplateTaskTree.enableThreeStateCheckboxes(false);

	//定义树鼠标左键点击事件调用的方法名
	planTemplateTaskTree.setOnClickHandler(planTemplateTask.treeLeftClick);
	 
	//定义从xml文件加载树
	planTemplateTaskTree.loadXML($("#initPath").val()+"/ProjectPlanController.do?method=getPlanTreeByProjectAndUser&projectId="+$("#projectId").val(), function(){
		if ($("#planId").val() != "") {
			planTemplateTaskTree.selectItem($("#planId").val());
			planTemplateTask.treeLeftClick($("#planId").val());
		} else {
			planTemplateTaskTree.selectItem(planTemplateTaskTree.getChildItemIdByIndex("-1",0));  //获得根节点下的第一个子节点
			planTemplateTask.treeLeftClick(planTemplateTaskTree.getChildItemIdByIndex("-1",0));
		}
	});
}
//工作计划树的鼠标左键点击事件
planTemplateTask.treeLeftClick = function(id) {
	$("#projectTaskId").val(id);//将当前的选择ID存入到隐藏数据中，方便数据回显
	var url = planTemplateTask.getProjectPlanUrl(id);
	planTemplateTask.toDoIsUrl("projectDoDiv",url,$("#projectId").val());
}

//叶子节点工作计划点击操作事件
planTemplateTask.clickMethod = function(id) {
	//获得计划的URL
	$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=toInterceptPlan', {'objId':id}, function(json){
		if(json.result)alert(json.result);
		if(json.failure){
			// 切换工作任务选中样式
			return false;
		}else{
			$("#projectTaskId").val(id);//将当前的选择ID存入到隐藏数据中，方便数据回显
			planTemplateTask.toDoIsUrl("projectDoDiv",json.url,$("#projectId").val());
			return true;
		}
	});
}
// 根据项目计划ID获取有效URL
planTemplateTask.getProjectPlanUrl = function(projectTaskId){
	var url = "";
	//获得计划的URL
	$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=toInterceptPlan', {'objId':projectTaskId}, function(json){
		if(json.result)alert(json.result);
		if(json.failure){
			return false;
		}else{
			return json.url;
		}
	});
	return url;
}

//指定页面跳转到工作计划对应的URL
planTemplateTask.toDoIsUrl = function(viewId,url,projectId){
	if(!projectId || projectId == "undefined") projectId="";
	if('' == url){
		$("#"+viewId).empty();
		return false;
	}
	if(url == "-1") return;
	if(url.indexOf('?')==-1){//若请求里没有"?",表示请求地址为jsp,则需要手动添加?projectId=
		url+= "?projectId="+projectId;
	}else if(url.indexOf('projectId=')>-1){//若请求里有"projectId=",表示请求需要项目ID，则将项目ID赋给它
		url+=projectId;
	}
	else{
		url+= "&projectId="+projectId;
	}
	$("#"+viewId).empty().loadPage($('#initPath').val()+'/'+url);
}

// 从新加载工作计划
planTemplateTask.refresh = function(projectTaskId){
	var url = planTemplateTask.getProjectPlanUrl(projectTaskId);
	if(url != ""){
		$.getJSON($("#initPath").val()+"/ProjectPlanController.do?method=getObjectQuery&queryColumns=project.objId,resource.url",{"objId":projectTaskId},function(json){
			if(null != json.result && "[]" != json.result && json.result.length >0 ){
				planTemplateTask.toDoIsUrl("projectDoDiv",url,json.result[0]["project.objId"]);
			}
		})
	}
}
planTemplateTask.refStatusLight = function(ebuyMethodCN){
	//状态灯
	var url2 = $('#initPath').val()+'/ProjectPlanController.do?method=getAllSecondStatusLightData';
	var data2 = {projectId:$("#projectId").val()};
	var setting2={ebuyMethodCN:ebuyMethodCN};
	$('#projectNav').empty().epsStatusLight(url2,data2,setting2);
}
// 完成业务操作后的统一入口
planTemplateTask.fulfillPlan=function(planId){//获取待完成的数据
	ProjectInfo.refreshProjectPlanStyle();//刷新工作计划样式
 
	planTemplateTask.updateProjectProcess($("#projectId").val());//更新项目进度条
	$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=getWaitprocWorkTaskProjectPlan',{"parentBizId":$("#projectId").val()}, function(json){
		$("#waitporcPlan").empty();
		if(json.projectPlanList != null){
			$.each(json.projectPlanList,function(i,n){
				if (i == 0) {
					//$("#waitporcPlan").append("<a href='#' class=\"linkButton\" id=\""+n.objId+"\" onClick=\"ProjectInfo.selectedProjectPlan('"+n.objId+"','"+n.parent.objId+"');\"><span class='highlight'><em>"+n.name+"</em></span></a>");
					if(json.projectPlanList.length>0){
						$("#waitporcPlan").append("&nbsp;&nbsp;<span><a class=\"myFont\" href=\"#\" id=\"more_worktask\" href=\"#\" onClick=\"planTemplateTask.moreWorktaskPlan('"+n.project.objId+"');\"><font color=\"red\" title=\"点击查看待办任务！\"><b>待办任务</b></a></span>");
					}
				}
			})
		}
	});
	planTemplateTask.refStatusLight($("#ebuyMethodCN").val());
	//弹出一个待办提示界面
	planTemplateTask.moreWorktaskPlan($("#projectId").val());
}

// 更多待办工作计划 
planTemplateTask.moreWorktaskPlan = function(projectId){
	$('#nextplanTaskPage').loadPage($('#initPath').val()+'/ProjectPlanController.do?method=toLookMoreWaitprocWorkTaskProjectPlanView&parentBizId='+projectId);
}