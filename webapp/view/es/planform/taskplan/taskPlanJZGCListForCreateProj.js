
var taskPlanSubForCreateProj={};
var recordFormList = {};

/*------------建筑工程tab start----------------------*/
//查询条件过滤
recordFormList.before=function(){
	var option={"project.objId":"null","project.objId_op":"is"}
	$('#recordFormGrid').flexOptions({params:option});
	return true;
}
//加载数据成功之后调用的函数
recordFormList.success=function(){
	$("#recordFormGrid").flexGetColByName({//点击一行触发事件
		 "engineeringName" : function(id,t){
			$(t).parent().parent().click(function(){
				var id = $(this).find(":checkbox").val();
				if($(this).find(":checkbox").attr("checked")){//选中
					recordFormList.checkbxClick(id);
				}else{//没选中
					recordFormList.noChooseClick(id);
				}
			})
		}
	});
	$("#recordFormGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'   : function(btn,rowId,obj){
		btn.click(function(){
			$.epsDialog({
		    	title:'备案书',
		    	url:$('#initPath').val()+"/RecordFormController.do?method=toShowView&objId="+rowId,
		    	width: '800',
		    	height: '500',
				dragAndResize:true,
				hasBg:false,
		    	isReload: false
			});
		}).appendTo(obj);
	}});
}
recordFormList.noChooseClick = function(id){//不选中
	var json = $("recordFormGrid").flexGetRowJsonById(id);
	var jsonTaskPlanSubId = json["objId"];
	var taskPlanSubId = new Array;//条目Id
	var taskPlanMSubId = new Array;//中间表对象Id
	var taskPlanSubIds = $("#taskPlanSubId").val().split(",");
	var taskPlanMSubIds = $("#taskPlanMSubId").val().split(",");
	$.each(taskPlanSubIds,function(i,n){//条目
		if (id!=n) {//判断是否相同
			taskPlanSubId.push(n);
		}else if (taskPlanSubIds.length==1) {
			$("#ebuyMethodId").val("");//采购方式
			$("#ebuyMethodCNId").val("");//采购方式别名
			$("#taskTypeId").val("");//类型
			$("#taskTypeCNId").val("");//类型别名
			$(".hDivBox").find(':checkbox').attr('checked',false);
		}
	})
	
	$.each(taskPlanMSubIds,function(i,n){//中间表对象
		if (id!=n) {
			taskPlanMSubId.push(n);
		}
	})
	$('#taskPlanSubId').val(taskPlanSubId.toString());
	$('#taskPlanMSubId').val(taskPlanMSubId.toString());
	$('#s'+id).remove();
}

recordFormList.checkbxClick = function(id){//选中
	var json = $("#recordFormGrid").flexGetRowJsonById(id);
	var taskPlanMSubId = $("#taskPlanSubId").val();
	if (taskPlanMSubId!=null&&""!=taskPlanMSubId&&taskPlanMSubId!=undefined) {//判断是否是第一次选择
		var jsonEbuyMethod = json["ebuyMethod"];
		var ebuyMethod = $("#ebuyMethodId").val();
		var taskType = $("#taskTypeId").val();
		if(ebuyMethod != jsonEbuyMethod) {//判断是否为统一采购方式
			alert("请选择同一采购方式,已选择"+$('#ebuyMethodCNId').val()+"!");
			$("#recordFormGrid :checked[value="+id+"]").attr("checked",false);
		}else{
			$("#taskPlanSubId").val($("#taskPlanSubId").val()+","+json["objId"]);//申报书条目
			$("#taskPlanMSubId").val($("#taskPlanMSubId").val()+","+json["objId"]);//中间表Id
			var html = "<span id=s"+json["objId"]+"> <span onclick='recordFormList.viewInfo(\""+json["objId"]+"\")' style='cursor:hand;color: blue'>"+json["engineeringName"]+"("+json["ebuyMethodCn"]+")</span><span onclick='recordFormList.delSpan(\""+json["objId"]+"\");' class='sysicon siDelete' style='cursor:hand'>&nbsp;</span></span>";
			$('#chooseId').append(html);
		}
		
	}else{
		var html = "<span id=s"+json["objId"]+"><span onclick='recordFormList.viewInfo(\""+json["objId"]+"\")' style='cursor:hand;color: blue'>"+json["engineeringName"]+"("+json["ebuyMethodCn"]+")</span><span onclick='recordFormList.delSpan(\""+json["objId"]+"\");' class='sysicon siDelete' style='cursor:hand'>&nbsp;</span></span>";
		$('#chooseId').append(html);
		$("#taskPlanSubId").val(json["objId"]);//申报书条目Id
		$("#taskPlanMSubId").val(json["objId"]);//中间表Id
		$("#ebuyMethodId").val(json["ebuyMethod"]);//采购方式
		$("#ebuyMethodCNId").val(json["ebuyMethodCn"]);//采购方式别名
		$("#taskTypeId").val(json["recFormBuyMethod"]);//类型
		$("#taskTypeCNId").val(json["recFormBuyMethodCn"]);//类型别名
	}
}


recordFormList.delSpan = function(id){//删除
	$('#s'+id).remove();
	$('#'+id).find(":checkbox").attr("checked",false);
	recordFormList.noChooseClick(id);
}

recordFormList.viewInfo = function(id){//查看详细信息
	$.epsDialog({
  	title:'备案书',
  	url:$('#initPath').val()+"/RecordFormController.do?method=toShowView&objId="+id,
  	width: '800',
  	height: '500',
		dragAndResize:true,
		hasBg:false,
  	isReload: false
	});
}
/*------------建筑工程tab end----------------------*/


$(document).ready(function(){
	$('#epsTabs').tabs();
	
	/*如果只有一个tabPanel，则隐藏*/
	if($("#epsTabs >ul>li>a").length == 1){
		$("#epsTabs >ul>li:first").hide();
	}
	
	taskPlanSubForCreateProj.currentTabID = $("#epsTabs >ul>li>a:first").attr("id");//得到初始加载的第一个TabPanel
	taskPlanSubForCreateProj.contentOnchange = function (){
		if ($('#purchaseName').val()==null||$('#purchaseName').val()==''||$('#purchaseName').val()==undefined) {
			$('#purchase.objId').val('');
		}
	}
	
	
	$("#nextId").click(function(){//下一步
		if ($("#taskPlanSubId").val()==null||$("#taskPlanSubId").val()==""||$("#taskPlanSubId").val()==undefined) {
			alert('任务书明细不能为空,请选择!');
			return false;
		}
		if(taskPlanSubForCreateProj.currentTabID=="project04"){
			var taskPlanSub = $("#taskPlanSubId").val();//申报书条目Id
			var ebuyMethod = $("#ebuyMethodId").val();//采购方式
			var ebuyMethodCN = $("#ebuyMethodCNId").val();//采购方式别名
			var taskType = $("#taskTypeId").val();//类型
			var taskTypeCN = $("#taskTypeCNId").val();//类型别名
			var url = $("#initPath").val();
			url +="/RecordFormController.do?method=toCreateRecordFormJz&taskPlanSubIds="+taskPlanSub+"&ebuyMethod="+ebuyMethod;
			url += "&taskType="+taskType;
			url += "&taskTypeCN=";
			url += encodeURI(taskTypeCN);
			url += "&ebuyMethodCN=";
			url+= encodeURI(ebuyMethodCN);
			url = encodeURI(url);
			$("#conBody").loadPage(url);
		}else if(taskPlanSubForCreateProj.currentTabID=="project03"){
			var taskPlanSub = $("#taskPlanSubId").val();//申报书条目Id
			var ebuyMethod = $("#ebuyMethodId").val();//采购方式
			var ebuyMethodCN = $("#ebuyMethodCNId").val();//采购方式别名
			var taskType = $("#taskTypeId").val();//类型
			var taskTypeCN = $("#taskTypeCNId").val();//类型别名
			var url = $("#initPath").val();
			url +="/RecordFormController.do?method=toCreateRecordFormCq&taskPlanSubIds="+taskPlanSub+"&ebuyMethod="+ebuyMethod;
			url += "&taskType="+taskType;
			url += "&taskTypeCN=";
			url += encodeURI(taskTypeCN);
			url += "&ebuyMethodCN=";
			url+= encodeURI(ebuyMethodCN);
			url = encodeURI(url);
			$("#conBody").loadPage(url);
		}else if(taskPlanSubForCreateProj.currentTabID=="project02"){
			var taskPlanSub = $("#taskPlanSubId").val();//申报书条目Id
			var ebuyMethod = $("#ebuyMethodId").val();//采购方式
			var ebuyMethodCN = $("#ebuyMethodCNId").val();//采购方式别名
			var taskType = $("#taskTypeId").val();//类型
			var taskTypeCN = $("#taskTypeCNId").val();//类型别名
			var url = $("#initPath").val();
			url +="/RecordFormController.do?method=toCreateRecordFormTd&taskPlanSubIds="+taskPlanSub+"&ebuyMethod="+ebuyMethod;
			url += "&taskType="+taskType;
			url += "&taskTypeCN=";
			url += encodeURI(taskTypeCN);
			url += "&ebuyMethodCN=";
			url+= encodeURI(ebuyMethodCN);
			url = encodeURI(url);
			$("#conBody").loadPage(url);
		}else{
		var taskPlanSub = $("#taskPlanSubId").val();//申报书条目Id
		var ebuyMethod = $("#ebuyMethodId").val();//采购方式
		var ebuyMethodCN = $("#ebuyMethodCNId").val();//采购方式别名
		var taskType = $("#taskTypeId").val();//类型
		var taskTypeCN = $("#taskTypeCNId").val();//类型别名
		var url = $("#initPath").val();
		url += "/ProjectController.do?method=toCreateProjectByConsignId&taskPlanSubIds="+taskPlanSub+"&ebuyMethod="+ebuyMethod;
		url += "&taskType="+taskType;
		url += "&taskTypeCN=";
		url += encodeURI(taskTypeCN);
		url += "&ebuyMethodCN=";
		url+= encodeURI(ebuyMethodCN);
		url = encodeURI(url);
		$("#conBody").loadPage(url);
		}
	});
	$("li a.refreshData").click(function(){
		taskPlanSubForCreateProj.currentTabID = $(this).attr("id");
		if(taskPlanSubForCreateProj.currentTabID == "project01"){//临时	
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=01";
		}else if(taskPlanSubForCreateProj.currentTabID == "project02"){
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=02";
		}else if(taskPlanSubForCreateProj.currentTabID == "project03"){
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=03";
		}else{
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=04";
		}
		$('#taskPlanSubForCreateProjGrid').reload();
	})
	
})