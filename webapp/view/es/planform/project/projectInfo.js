var ProjectInfo = {};
var result;//全局变量，存放工作计划数据

/**
 * 获取工作计划数据
 * Created at 2011-4-19 09:13 by zhouzhanghe
 */
ProjectInfo.getProjectPlanData = function(){
	var url = $('#initPath').val()+'/ProjectPlanController.do?method=getSecondStatusLightData';
	$.ajax({
		  url: url,
		  dataType: "json",       
		  data:{projectId:$("#projectId").val()},
		  async:false,
		  success: function(json){
			  result = json.result;
		  }
	});
}

/**
 * 获取最新的工作计划数据，刷新工作计划的样式
 * Created at 2011-4-19 09:13 by zhouzhanghe
 */
ProjectInfo.refreshProjectPlanStyle = function(){
	ProjectInfo.getProjectPlanData();//重新获取工作计划数据
	
	/*刷新二级工作计划样式*/
	var secondLevelA = $('#secondLevelUl>li');//存放界面上二级工作计划的集合
	/*将从后台获取的最新工作计划数据与前台的工作计划数据的状态进行对比，若不匹配则更新前台工作计划的样式*/
	if(secondLevelA.length > 0){
		$.each(result,function(i,n){
			$.each(secondLevelA, function(j,o){
				if(n.objId == $(o).find("a").attr("secondTaskId")){
					var statusMappingStyle = "";//存放从后台获取的状态值转换为样式的结果
					if(n.status == "00")//如果未开始
						statusMappingStyle = 'notstart';
					else if(n.status == "01")//如果进行中
						statusMappingStyle = 'ongoing';
					else if(n.status == "02")//如果完成
						statusMappingStyle = 'finish';
					else{
						alert("数据异常，请与管理员联系。");
						return true;//continue;
					}
					
					/*如果当前计划与最新从数据获取的状态不一致，则替换*/
					if(! $(o).hasClass(statusMappingStyle)){
						if(! $(o).hasClass("selected"))//如果当前工作计划不处于选中状态，则替换样式
							$(o).removeClass().addClass(statusMappingStyle);
						$(o).attr("backUpClass", statusMappingStyle);//备份的样式，以便在切换工作计划后还原为原来的样式
					}
					return true;//continue;
				}
			})
		});
	}
	
	/*刷新三级工作计划样式*/
	var threeLevelA = $('#projectMain .BtnDiv>a');//存放界面上的三级工作计划的集合
	/*将从后台获取的最新工作计划数据与前台的工作计划数据的状态进行对比，若不匹配则更新前台工作计划的样式*/
	if(threeLevelA.length > 0){
		var parentTaskId = $(threeLevelA[0]).attr("parentTaskId");//存放三级工作计划上的二级工作计划
		$.each(result,function(i,n){
			if(parentTaskId == n.objId){
				$.each(n.children,function(j,o){
					$.each(threeLevelA, function(k, aObj){
						if(o.objId == $(aObj).attr("projectTaskId")){
							var statusMappingStyle = "";//存放从后台获取的状态值转换为样式的结果
							if(o.status == "00")//如果未开始
								statusMappingStyle = 'siAccept notstart';
							else if(o.status == "01")//如果进行中
								statusMappingStyle = 'siAccept ongoing';
							else if(o.status == "02")//如果完成
								statusMappingStyle = 'siAccept finish';
							else{
								alert("数据异常，请与管理员联系。");;
								return true;//continue;
							}
							
							/*如果当前计划与最新从数据获取的状态不一致，则替换*/
							if(! $(aObj).hasClass(statusMappingStyle)){
								if(! $(aObj).hasClass("selected"))//如果当前工作计划不处于选中状态，则替换样式
									$(aObj).removeClass().addClass(statusMappingStyle);
								$(aObj).attr("backUpClass", statusMappingStyle);//备份的样式，以便在切换工作计划后还原为原来的样式
							}
							
							return true;//continue;
						}
					});
				});
				return false;//continue;
			}
		});
	}
}

/**
 * 根据工作计划数据，添加工作计划并绑定操作事件
 * Created at 2011-4-19 09:13 by zhouzhanghe
 */
ProjectInfo.addProjectPlan = function(){
	ProjectInfo.getProjectPlanData();//重新获取工作计划数据
	
	/*添加二级工作计划 */
	$.each(result,function(i,n){
		var li = document.createElement('li');
		li.innerHTML ='<a href="#" secondTaskId='+n.objId+'>'+n.name+'</a>';
		li.id=n.url;
		if(n.status == "00"){//如果未开始
			$(li).addClass('notstart');
		}else if(n.status == "01"){//如果进行中
			$(li).addClass('ongoing');
		}else if(n.status == "02"){//如果完成
			$(li).addClass('finish');
		}else{
			alert("数据异常，请与管理员联系。");;
			return false;
		}
		$(li).attr("backUpClass", $(li).attr("class"));//保存最初的样式，以便在切换工作计划后还原为原来的样式
		$('#secondLevelUl').append(li);
	});
	
	/*
	if($('#projImplStatus').val()!='00'){
		if($('#projImplStatus').val()=='01') alert("项目当前处于暂停状态，不能进行相应操作！");
		if($('#projImplStatus').val()=='02') alert("项目当前处于终止状态，不能进行相应操作！");
		$("#projectBtnDiv").hide();
		$("#projectDoDiv").loadPage($('#initPath').val()+"/ProjectViewController.do?method=toProjectView&projectId="+$("#projectId").val());
		return false;
	}*/
	
	
	 /*绑定二级工作计划的操作事件*/
	$('#secondLevelUl>li').click(function(){
		if($(this).hasClass('notstart'))//如果当前工作计划有未开始样式，表示当前工作计划不可用，则返回
			return false;
		
		/*还原选中的上一个工作计划样式*/
		var currentSeltecteSecondMenu = $('#secondLevelUl>li').siblings('.selected');
		if(currentSeltecteSecondMenu.length != 0){
			currentSeltecteSecondMenu.removeClass().addClass($(currentSeltecteSecondMenu).attr("backUpClass"));
		}
		$(this).removeClass().addClass('selected');//标识当前工作计划为选中状态
		
		/*如果是点击概览，则加载概览页面*/
		if($(this).find("a").attr("id")=="projectView"){
			$("#projectBtnDiv").hide();
			$("#projectDoDiv").loadPage($('#initPath').val()+"/ProjectViewController.do?method=toProjectView&projectId="+$("#projectId").val());
			return false;
		}
		var index = $('#secondLevelUl>li').index($(this));
		index -= 1;//因为加了一个默认的节点，这里需要减少一个节点
		
		/*添加二级工作计划下的工作计划*/
		$("#projectBtnDiv").show();
		$('#projectMain .BtnDiv').empty();
		var currentSencondPlan = result[index];
		$.each(currentSencondPlan.children,function(j,m){
			var classStyle = "";
			if(m.status == "00"){//如果未开始
				classStyle = "siAccept notstart";
			}else if(m.status == "01"){//如果正在进行中
				classStyle = "siAccept ongoing";
			}else if(m.status == "02"){//如果已完成
				classStyle = "siAccept finish";
			}else{
				alert("数据异常，请与管理员联系。");
				return false;
			}
//			alert("resourceView："+m.resourceView+" URL："+m.resourceView.resViewUrl);
			var tmpURL='';
			if (m.resViewUrl!=null&&m.resViewUrl!=undefined&&m.resViewUrl!=''&&m.resViewUrl!='null') {
				tmpURL = m.resViewUrl;
			}else{
				tmpURL = m.url;
			}
			$('#projectMain .BtnDiv').append('<a id='+tmpURL+' backUpClass=\"'+classStyle+'\" class=\"' + classStyle +'\" parentTaskId='+currentSencondPlan.objId+' projectTaskId='+m.objId+' taskCode='+m.taskCode+' title='+m.name+'><span>'+m.name+'</span></a>');
		})
		
		/*绑定三级工作计划的点击事件*/
		$('#projectMain .BtnDiv>a').click(function(){
			if($(this).hasClass('notstart'))//如果当前工作计划有未开始样式，表示当前工作计划不可用，则返回
				return false;
			
			/*还原选中的上一个工作计划样式*/
			var currentSeltecteThreeMenu = $('#projectMain .BtnDiv>a').siblings('.selected');
			if(currentSeltecteThreeMenu.length != 0){
				currentSeltecteThreeMenu.removeClass().addClass($(currentSeltecteThreeMenu).attr("backUpClass"));
			}
			$(this).addClass('selected');//标识当前工作计划为选中状态
			// 点击工作计划
			planTemplateTask.clickMethod($(this).attr('projectTaskId')+"");
		});
		
		var taskPlanId_ = $("#taskPlanId_lk").val();//存放待处理的计划
		//如果存在需加载的工作计划，则加载
		if($('#projectMain .BtnDiv>a[projectTaskId='+taskPlanId_+']').length != 0){
			$('#projectMain .BtnDiv>a[projectTaskId='+taskPlanId_+']').click();// 选中三级工作计划
		}//如果存在待处理的工作计划，则加载
		else if($('#projectMain .BtnDiv>a').siblings('.ongoing').length != 0){
			$('#projectMain .BtnDiv>a').siblings('.ongoing:first').click();
		}else{
			$('#projectMain .BtnDiv>a:first').click();
		}
	});	
}

$(document).ready(function(){
	var FirstFlag = true;     //判断是否第一次进入标识
	$('#modify_monitor_span2').hide();
	//收取左边菜单栏
	
	sysTools_hideSub();
	//初始化状态灯
	planTemplateTask.refStatusLight($("#ebuyMethodCN").val()+"");
	$('.spaceused').progressBar({showText:false,width:90});
		
	ProjectInfo.addProjectPlan();//添加工作计划
	
	//如果存在正在进行的工作计划，则加载
	if($('#secondLevelUl>li[class=ongoing]:first').length>0){
		$('#secondLevelUl>li[class=ongoing]:first').click();   
	}//加载"概览"工作计划
	else{
		$("#projectBtnDiv").hide();
		$("#projectDoDiv").loadPage($('#initPath').val()+"/ProjectViewController.do?method=toProjectView&projectId="+$("#projectId").val());
		$('#secondLevelUl>li:first').removeClass().addClass("selected");
		FirstFlag = false;
	}
	
	
	//获得监管人数据
	$.getJSON($('#initPath').val()+'/UserApiController.do?method=getEmpListByCurUser&queryColumns=objId,name', {}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$.each(json.empList,function(i,monitor){
			$("[name=monitor.objId]").append("<option value='"+monitor[0]+"'>"+monitor[1]+"</option>");
		});
	});
	$('#secondLevelUl>li:last').after("<li  class=\"warning\"><a href=\"#\" id=\"modifyProj\" onclick='ProjectInfo.exceptionUpdate();'>异常处理</a></li><li  class=\"package\"><a href=\"#\" id=\"recordProj\" onclick='ProjectInfo.projectKeepOnRecord();'>项目备案</a></li>");
	$("#returnDesk").click(function(){
		$("#myDesktop").click();
	})
	planTemplateTask.moreWorktaskPlan($("#projectId").val());
})
ProjectInfo.showPlan=function(projectId){
	$.epsDialog({
        title:"项目进度",
        width:"900",
        url:$('#initPath').val()+'/view/es/planform/projectplan/projectPlanDetailInner.jsp?projectId='+projectId
	});
}
ProjectInfo.plan=function(projectId){
	$('#projectDoDiv').loadPage($('#initPath').val()+'/ProjectPlanController.do?method=createProjectPlan&projectId='+projectId);
}
ProjectInfo.answer=function(projectId){
	var url = 'view/es/planform/oppugnrequisition/oppugnReqAgencyList.jsp';
	planTemplateTask.toDoIsUrl("projectDoDiv",url,$("#projectId").val());
	//$('#projectDoDiv').loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqAgencyList.jsp&projectId='+projectId);
}
ProjectInfo.checkProjectMenuForDialog=function(id,isReLoad){
	if(id == 'xmjbr_fp'){//分配项目负责人
		dialogPublicMethod("/ProjectController.do?method=toProjectLinkGovMan&projectId="+$("#projectId").val(),isReLoad,"分配项目负责人",800,300);
	}
}
// 更新监管人
function saveMonitorId(){
	if($("[name=monitor.objId]").val() != ""){//若没有选择监管人员，需要提示
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectMonitor', {projectId:$("#projectId").val(),monitorId:$("[name=monitor.objId]").val()}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#modify_monitor_span1').show();
			$('#modify_monitor_span2').hide();
			$('#monitor_objId').val($("[name=monitor.objId]").val())
			$('#monitor_name').empty().append($('[id=monitor.objId] option[selected=true]').text());
		});
	}else{
		alert("请选择监管人！");
	}
}

//异常处理页面修改
ProjectInfo.exceptionUpdate = function(){
	var projectId= $("#projectId").val();
	$("#projectDoDiv").loadPage($('#initPath').val()+'/ProjectController.do?method=toPuaseProjectList&isComplete=true&fromType=fromProj&projectId='+projectId);
}


//跳转到项目备案
ProjectInfo.projectKeepOnRecord = function(){
	var url = 'ProjectViewController.do?method=toProjectHistoryRecord';
	planTemplateTask.toDoIsUrl("projectDoDiv",url,$("#projectId").val());
}


// 选中项目计划
ProjectInfo.selectedProjectPlan = function(projectTaskId,prentProjectTaskId){
	$("#taskPlanId_lk").val(projectTaskId);
	$('#secondLevelUl>li>a[secondTaskId='+prentProjectTaskId+']').parent().click();// 选中二级工作计划
	
}

// 修改监管人点击事件
$('#modify_monitor').click(function(){
	$('#modify_monitor_span1').hide();
	$('#modify_monitor_span2').show();
	$('[id=monitor.objId]').val($('#monitor_objId').val());
})

// 更多工作计划点击事件
$('#more_worktask').click(function(){
	planTemplateTask.moreWorktaskPlan($("#projectId").val());
})
//用以隐藏或查看项目进度
$('#lookPace').click(function(){
	if($("#projectNav").attr("class")=== "hidden"){
		$("#projectNav").attr("class","partContainers");
		$("#pace").text("隐藏进度");
		$("#lookPace").attr("title","隐藏进度");
		$("#lookPace").attr("class","sysicon siUpBtn");
	}else{
		$("#projectNav").attr("class","hidden");
		$("#pace").text("查看进度");
		$("#lookPace").attr("title","查看进度");
		$("#hides").hide();
		$("#lookPace").attr("class","sysicon siDownBtn");
	}
})