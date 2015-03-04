var ProjectInfo = {};
var result;

$(document).ready(function(){
	var FirstFlag = true;     //判断是否第一次进入标识
	$('#modify_monitor_span2').hide();
	//收取左边菜单栏
	
	sysTools_hideSub();
	//初始化状态灯
	planTemplateTask.refStatusLight($("#ebuyMethodCN").val()+"");
	$('.spaceused').progressBar({showText:false,width:90});
	var url = $('#initPath').val()+'/ProjectPlanController.do?method=getSecondStatusLightData';
	var data = {projectId:$("#projectId").val()};
	$.getJSON(url,data,function(json){
		result = json.result;
		$.each(result,function(i,n){
			var li = document.createElement('li');
			li.innerHTML ='<a href="#" secondTaskId='+n.objId+'>'+n.name+'</a>';
			li.id=n.url;
			if(n.status == "01")
				$(li).addClass('selected');
			$('#secondLevelUl').append(li);
		});
		$('#secondLevelUl>li').click(function(){
			$(this).siblings('li').removeClass('selected').end().addClass('selected');
			$("#projectBtnDiv").show();
			$('#projectMain .BtnDiv').html('');
			var index = $('#secondLevelUl>li').index($(this));
			index -= 1;//因为加了一个默认的节点，这里需要减少一个节点
			if($(this).find("a").attr("id")=="projectView"){
				$("#projectBtnDiv").hide();
				$("#projectDoDiv").loadPage($('#initPath').val()+"/ProjectViewController.do?method=toProjectView&projectId="+$("#projectId").val());
				return false;
			}
			$.each(result[index].children,function(j,m){
				if(m.status == "01")
					$('#projectMain .BtnDiv').append('<a isslct="selected" class="siNext siAccept" id='+m.url+' projectTaskId='+m.objId+' taskCode='+m.taskCode+' title='+m.name+'><span>'+m.name+'</span></a>');
				else
					$('#projectMain .BtnDiv').append('<a class="siNext siAccept" id='+m.url+' projectTaskId='+m.objId+' taskCode='+m.taskCode+' title='+m.name+'><span>'+m.name+'</span></a>');
					
			})
			$('#projectMain .BtnDiv>a').click(function(){
				$(this).parent().find('a').removeClass('siControl_fastforward_right').addClass('siNext');
				$(this).removeClass('siNext').addClass('siControl_fastforward_right');
				
				// 点击工作计划
				planTemplateTask.clickMethod($(this).attr('projectTaskId')+"");
			});
				var taskPlanId_ = $("#taskPlanId_lk").val();//存放待处理的计划
				//如果存在需加载的工作计划，则加载
				if($('#projectMain .BtnDiv>a[projectTaskId='+taskPlanId_+']').length != 0){
					$('#projectMain .BtnDiv>a[projectTaskId='+taskPlanId_+']').click();// 选中三级工作计划
				}//如果存在待处理的工作计划，则加载
				else if($('#projectMain .BtnDiv>a[isslct=selected]').length != 0){
					$('#projectMain .BtnDiv>a[isslct=selected]:first').click();
				}//如果不存在待处理的工作计划，则加载第一个
				else{
					$('#projectMain .BtnDiv>a:first').click();
				}
		});
		if($('#secondLevelUl>li[class=selected]:first').length>0){
			$('#secondLevelUl>li[class=selected]:first').click();   //一进页面就点击进行中的任务
		}else{
			$("#projectBtnDiv").hide();
			$("#projectDoDiv").loadPage($('#initPath').val()+"/ProjectViewController.do?method=toProjectView&projectId="+$("#projectId").val());
			$('#secondLevelUl>li:first').attr("class","selected");
			FirstFlag = false;
		}
		 
		
		
	})
	//获得监管人数据
	$.getJSON($('#initPath').val()+'/UserApiController.do?method=getEmpListByCurUser&queryColumns=objId,name', {}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$.each(json.empList,function(i,monitor){
			$("[name=monitor.objId]").append("<option value='"+monitor[0]+"'>"+monitor[1]+"</option>");
		});
	});
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