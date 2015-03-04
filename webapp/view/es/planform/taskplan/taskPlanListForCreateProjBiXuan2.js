
var taskPlanSubForCreateProj={};
var recordFormList={};
taskPlanSubForCreateProj.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个项目!');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择一个项目!');return false;}//是否只选中一个
	return true;
}
taskPlanSubForCreateProj.success = function(){//加载成功后
	$("#taskPlanSubForCreateProjGrid").flexGetColByName({
		'bidInviStyle':function(id,t){
			var json = $("#taskPlanSubForCreateProjGrid").flexGetRowJsonById(id);
			var confirmStatus = json['bidInviStyle'];
			$(t).html(taskPlanSubForCreateProj.getStatus(confirmStatus));
		}
	});
	$("#taskPlanSubForCreateProjGrid").flexGetColByName({
		'projectType':function(id,t){
			var json = $("#taskPlanSubForCreateProjGrid").flexGetRowJsonById(id);
			var projectType = json['projectType'];
			$(t).html(taskPlanSubForCreateProj.getStatus2(projectType));
		}
	});
	$("#taskPlanSubForCreateProjGrid").flexGetColByName({
		'ebuyMethod':function(id,t){
			var json = $("#taskPlanSubForCreateProjGrid").flexGetRowJsonById(id);
			$(t).html("公开招标");
		}
	});
	$("#taskPlanSubForCreateProjGrid").flexGetColByName({//点击一行触发事件
		 "budgetName" : function(id,t){
			$(t).parent().parent().click(function(){
				var id = $(this).find(":checkbox").val();
				if($(this).find(":checkbox").attr("checked")){//选中
					taskPlanSubForCreateProj.checkbxClick(id);
				}else{//没选中
					taskPlanSubForCreateProj.noChooseClick(id);
				}
			})
		}
	});
	$("#taskPlanSubForCreateProjGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn"><u>立项</u></a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toCreateProjectByReProjectId2&objId='+rowId);
			}).appendTo(obj);
		}
	});
}
taskPlanSubForCreateProj.noChooseClick = function(id){//不选中
	var json = $("#taskPlanSubForCreateProjGrid").flexGetRowJsonById(id);
	$("#taskPlanSubForCreateProjGrid").reload();
}

taskPlanSubForCreateProj.checkbxClick = function(id){//选中
	var json = $("#taskPlanSubForCreateProjGrid").flexGetRowJsonById(id);
	var jsonTaskPlanSubId = json["objId"];
	$("#objId").val(jsonTaskPlanSubId);
}
taskPlanSubForCreateProj.getStatus = function(confirmStatus){
	if ("00"==confirmStatus) {//待上级采购单位审核申报书
		return "自主招标";
	}else {//待提交申报书
		return "委托招标";
	}
}		
taskPlanSubForCreateProj.getStatus2 = function(projectType){
	if ("00"==projectType) {//待上级采购单位审核申报书
		return "中央投资";
	}else {//待提交申报书
		return "非中央投资";
	}
}	

taskPlanSubForCreateProj.before=function(){
	var option={};
	$('#taskPlanSubForCreateProjGrid').flexOptions({params:option});
	return true;
}
 

$(document).ready(function(){
	$('#epsTabs').tabs();
	$("#nextId").click(function(name,grid){//下一步
		if(!taskPlanSubForCreateProj.validation(name,grid))return;
		var url = $("#initPath").val();
		url +="/ProjectController.do?method=toCreateProjectByReProjectId2";
		alert($("#objId").val());
		url += "&objId="+$("#objId").val();
		url = encodeURI(url);
		$("#conBody").loadPage(url);
	});
	
})