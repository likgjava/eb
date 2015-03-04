/*
 * 执行平台，采购申报书列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanList={};

//设定返回时的路径
taskPlanList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/taskplan/taskPlanList.jsp");
}

//新增
taskPlanList.add=function(name,grid){
	//跳转到修改页面
	$("#task_plan_list").hide();
	$("#task_plan_form").show();
	$("#task_plan_form").empty().loadPage($("#initPath").val()+"/TaskPlanController.do?method=toCreateTaskPlanForAgent");
}  

//修改
taskPlanList.update=function(name,grid){
	if(!taskPlanList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toUpdateTaskPlanForAgent&objId='+$(grid).getSelect());
} 

//发起委托
taskPlanList.consign=function(name,grid){
	if(!taskPlanList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toDraft&taskPlanId='+$(grid).getSelect());
} 

//删除
taskPlanList.remove=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(window.confirm('确定'+name+'?')){
		var ids = $(grid).getSelects().split(",");
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=removeTaskPlan',{objId:ids},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//查看详情
taskPlanList.showDetail=function(name,grid){
	if(!taskPlanList.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+$(grid).getSelect())+'&type=selectAgent';
}

//提交
taskPlanList.auditAgian = function(name,grid)
{
	if(!taskPlanList.validation(name,grid))return;
	if(window.confirm('确定要保存为正式申报书，并提交上级吗？')){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitTaskPlan', 
				{objIds:$(grid).getSelects(),"useStatus":"01","confirmStatus":"00","auditDetail":"00"}, function(json){
		
			if(json.isTaskPlanSub == 'YES')
			{
				alert("已保存为正式申报书！请等待上级汇总或审核")
			}
			else
			{
				alert("请录入申报书明细！")
			}
			$(grid).reload();//刷新
		});
	}
}

//列表操作验证
taskPlanList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanList.before=function(){
	var option={"useStatus":"00",
				"order":"applyDate",
				"order_flag":true}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}
//加载数据成功之后调用的函数
taskPlanList.success=function(){
	//添加查看招标中心信息超链接
	   $("#taskPlanGrid").flexGetColByName({
			'taskAgentName':function(id,t){
		   var agentId = $("#taskPlanGrid").getRowById(id)["taskAgent.objId"];
		   var agentName = $("#taskPlanGrid").getRowById(id)["taskAgentName"];
		   $(t).html('<a href="#" onClick="getAgentMessage(\''+agentId+'\',\''+agentName+'\');">'+$(t).html()+'</a>');
		   }
		});
	
	if(taskPlanList.currentTabID == "tabs_toTemp"){//临时 
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#task_plan_list").hide();
					$("#task_plan_form").empty().show().loadPage($("#initPath").val()+"/TaskPlanController.do?method=showDetail&objId="+rowId+"&type=selectAgent");
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#task_plan_list").hide();
					$("#task_plan_form").empty().show().loadPage($("#initPath").val()+"/TaskPlanController.do?method=toUpdateTaskPlanForAgent&objId="+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
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
	}else if(taskPlanList.currentTabID == "tabs_toFormal"){//正式
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#task_plan_list").hide();
					$("#task_plan_form").empty().show().loadPage($("#initPath").val()+"/TaskPlanController.do?method=showDetail&objId="+rowId);
				}).appendTo(obj);
			}
		});
		 
	}else if(taskPlanList.currentTabID == "tabs_aleradyCreateHistory"){//查看历史
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#task_plan_list").hide();
					$('#task_plan_form').empty().show().loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&canConsign=no&objId='+rowId);
				}).appendTo(obj);
			}
		});
	} else if(taskPlanList.currentTabID == "tabs_done"){//已通过
		 
	}
	
	$("#taskPlanGrid").flexGetColByName({
		'confirmStatus':function(id,t){
			var json = $("#taskPlanGrid").flexGetRowJsonById(id);
			var confirmStatus = json['confirmStatus'];
			var useStatus = json['useStatus'];
			var auditDetail = json['auditDetail'];
			var leaderId = json['leader.objId'];
			$(t).html(taskPlanList.getStatus(confirmStatus,useStatus,auditDetail,leaderId));
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



taskPlanList.getStatus = function(confirmStatus,useStatus,auditDetail,leaderId){
	var confirm = confirmStatus;
	var status = useStatus;
	var audit = auditDetail;
	var leader = leaderId;
	if ("00"==confirm&&"01"==status&&"00"==audit) {//待上级采购单位审核申报书
		return "已提交";
	}else {//待提交申报书
		return "待提交";
	}
}

//弹出层共有方法
function dialogPublicMethod(url,isReload,title,width,height){
	$.epsDialog({
		id:"1",
        title:title,
        url:$("#initPath").val()+url,
        width: width,
        height: height,
        isReload:isReload,
        onClose: function(){ 
       			if(isReload){//若需要刷新页面，则跳转
        		//跳转到招标中心项目首页
        		$('#conBody').empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanList.jsp');
        	}
        }
	});
}

$(document).ready(function(){
	taskPlanList.submit = function(){
		$('#budgetDetailForm').ajaxSubmit({
			url:$('#initPath').val()+'/TaskPlanController.do?method=inputTaskplanXML',
			dataType:'json',
			success:function(json){
				if(json.result){alert(json.result); $('#taskPlanGrid').reload();}if(json.failure)return;
				$("#xmlUrl").remove();
				$("#xmlUrlLi").append('<input type="file" name="xmlUrl" id="xmlUrl" class="long"  value="" class="required"/>');
			}
		});
	}
	
	$('#upload').click(function(){
		if (null == $('#xmlUrl').val() || null == $('#xmlUrl').val() || $('#xmlUrl').val().toString().length<1) {
			alert('请选择 预算XML文件');
			return false;
		}
		var filePath = $('#xmlUrl').val().replace(/.+\\([^\\]+)/,'$1');
		var i = filePath.lastIndexOf('.');        //从右边开始找第一个'.'
		var len = filePath.length;                //取得总长度
		var str = filePath.substring(len,i+1);    //取得后缀名
		var exName = "XML";       //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());//转成大写后判断
		if(k==-1) {
		  alert("上传文件错误！只能上传"+exName);
		  return false;
		}
		taskPlanList.submit();
	})
	
	//日历控件
	$('#_applyDate').epsDatepicker();
	
	//设定返回时的路径
	taskPlanList.setRetrun();
	
	//加载tabs
	$('#epsTabs').tabs();
	taskPlanList.currentTabID = "tabs_toTemp";//初始化时，默认当前TABID为tabs_toTemp
	taskPlanList.preTabID = "";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("input[name=taskCode]").val("");
		$("input[name=taskName]").val("");
		$("input[name=applyDate]").val("");
		
		$("#taskPlanGrid").find("tDiv").empty();
		taskPlanList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(taskPlanList.currentTabID == "tabs_toTemp"){//临时	
			buttons = [{name:"新增",bclass:"add",onpress:taskPlanList.add},
			           {name:"批量删除",bclass:"delete",onpress:taskPlanList.remove}
			];
			$('#taskPlanGrid').attr("p").newp = 1;
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&order=createTime&order_flag=true&useStatus=00";
			$('#taskPlanListInfo').find('input[type=checkbox]').show();
			$('#taskPlanGrid').attr("p").checkbox=true;
		}else if(taskPlanList.currentTabID == "tabs_toFormal"){//正式
			buttons = [];
			$('#taskPlanGrid').attr("p").newp = 1;
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&order=createTime&order_flag=true&useStatus=01&auditDetail=00&confirmStatus=00";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}else if(taskPlanList.currentTabID == "tabs_aleradyCreateHistory"){//查看采购历史
			buttons = [];
			$('#taskPlanGrid').attr("p").newp = 1;
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=";
			$('#taskPlanListInfo').find('input[type=checkbox]').hide();
			$('#taskPlanGrid').attr("p").checkbox=false;
		}
		$('#taskPlanGrid').flexReDrawButtons(buttons);
		$("button[type=submit]").click();
		$('#taskPlanGrid').reload();
	})
});

