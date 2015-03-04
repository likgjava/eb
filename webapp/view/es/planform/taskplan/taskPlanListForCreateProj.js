
var taskPlanSubForCreateProj={};
var recordFormList={};

taskPlanSubForCreateProj.success = function(){//加载成功后
	$("#taskPlanSubForCreateProjGrid").flexGetColByName({//点击一行触发事件
		 "taskPlanSub.budgetName" : function(id,t){
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
	if ($('#taskPlanMSubId').val()!=null&&$('#taskPlanMSubId').val()!=undefined&&$('#taskPlanMSubId').val()!="") {
		$.each($('#taskPlanMSubId').val().split(","),function(i,n){//勾选上已选择的
			$('#'+n).find(":checkbox").attr("checked",true);
		});
	}
	
	$(".hDivBox").find(':checkbox').click(function(){//全选
		if ($(this).attr("checked")) {//全选
			
			if(!taskPlanSubForCreateProj.checkAllBuyer()){//采购人
				alert('预算单位名称不同不能全选！');
				$("#consignListView").find(':checkbox').attr('checked',false);
			}else if(!taskPlanSubForCreateProj.checkAllEbuyMethod()){//采购方式
				alert('采购方式不同不能全选！');
				$("#consignListView").find(':checkbox').attr('checked',false);
			} else if (!taskPlanSubForCreateProj.checkAllTaskType()) {//类型
				alert('申报书类型不同不能全选！');
				$("#consignListView").find(':checkbox').attr('checked',false);
			} else {
				taskPlanSubForCreateProj.checkAllBox();
			}
		} else {//反选
			taskPlanSubForCreateProj.noCheckAllBox();
		}
	});
	
	taskPlanSubForCreateProj.checkAllEbuyMethod = function(){//判断采购方式是否相同
		var ebuyMethod = "";
		var flag = true;
		$("#taskPlanSubForCreateProjGrid").flexGetColByName({
			 "taskPlan.ebuyMethod" : function(id,t){
			if (ebuyMethod!=null&&""!=ebuyMethod&&undefined!=ebuyMethod) {
				if (ebuyMethod!=$(t).html()) {
					flag = false;
				}
			}else {
				ebuyMethod = $(t).html();
			}	
			}
		});
		return flag;
	}
	
	taskPlanSubForCreateProj.checkAllBuyer = function(){  //判断采购人是否相同
		var buyerName="";
		var flag = true;
		$("#taskPlanSubForCreateProjGrid").flexGetColByName({
			 "taskPlanSub.budgetName" : function(id,t){
			if (buyerName!=null&&""!=buyerName&&undefined!=buyerName) {
				if (buyerName!=$(t).html()) {
					flag = false;
				}
			}else {
				buyerName = $(t).html();
			}	
			}
		});
		return flag;
	}
	
	
	
	
	taskPlanSubForCreateProj.checkAllTaskType = function(){//判断类型是否相同
		var taskType = "";
		var flag = true;
		$("#taskPlanSubForCreateProjGrid").flexGetColByName({
			 "taskPlan.taskType" : function(id,t){
			if (taskType!=null&&""!=taskType&&undefined!=taskType) {
				if (taskType!=$(t).html()) {
					flag = false;
				}
			}else {
				taskType = $(t).html();
			}	
			}
		});
		return flag;
	}
	
	taskPlanSubForCreateProj.checkAllBox = function(){//全选所有checkbox
		$('#chooseId').empty();
		$('#chooseId').append('<span style="color: red;">已选择的申报书明细：</span>');
		$("#taskPlanSubId").val("");//申报书条目Id
		$("#taskPlanMSubId").val("");//中间表Id
		$("#ebuyMethodId").val("");//采购方式
		$("#ebuyMethodCNId").val("");//采购方式别名
		$("#taskTypeId").val("");//类型
		$("#taskTypeCNId").val("");//类型别名
		$("#taskPlanSubForCreateProjGrid").flexGetColByName({
			 "taskPlanSub.budgetName" : function(id,t){
				var id = $(t).parent().parent().find(":checkbox").val();				
				taskPlanSubForCreateProj.checkbxClick(id);
			}
		});
	}
	taskPlanSubForCreateProj.noCheckAllBox = function(){//反选所有checkbox
		$("#taskPlanSubForCreateProjGrid").flexGetColByName({
			"taskPlanSub.budgetName" : function(id,t){
			var id = $(t).parent().parent().find(":checkbox").val();
			taskPlanSubForCreateProj.noChooseClick(id);
		}
		});
	}
}

taskPlanSubForCreateProj.noChooseClick = function(id){//不选中
	var json = $("#taskPlanSubForCreateProjGrid").flexGetRowJsonById(id);
	var jsonTaskPlanSubId = json["taskPlanSub"]["objId"];
	var taskPlanSubId = new Array;//条目Id
	var taskPlanMSubId = new Array;//中间表对象Id
	var taskPlanSubIds = $("#taskPlanSubId").val().split(",");
	var taskPlanMSubIds = $("#taskPlanMSubId").val().split(",");
	$.each(taskPlanSubIds,function(i,n){//条目
		if (jsonTaskPlanSubId!=n) {//判断是否相同
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

taskPlanSubForCreateProj.checkbxClick = function(id){//选中
	var json = $("#taskPlanSubForCreateProjGrid").flexGetRowJsonById(id);
	var taskPlanMSubId = $("#taskPlanSubId").val();
	if (taskPlanMSubId!=null&&""!=taskPlanMSubId&&taskPlanMSubId!=undefined) {//判断是否是第一次选择
		var jsonEbuyMethod = json["taskPlan"]["ebuyMethod"];
		var ebuyMethod = $("#ebuyMethodId").val();
		var jsonTaskType = json["taskPlan"]["taskType"];
		var taskType = $("#taskTypeId").val();
		var buyerName = $("#buyerName").val();
		var jsonBuyerName = json["taskPlanSub"]["budgetName"];
		if(buyerName != jsonBuyerName){  //判断是否同一预算单位
			alert("请选择同一预算单位，已选择"+ $("#buyerName").val()+"!");
			$("#taskPlanSubForCreateProjGrid :checked[value="+id+"]").attr("checked",false);
		}else{
			if(ebuyMethod==jsonEbuyMethod) {//判断是否为统一采购方式
				if (taskType==jsonTaskType) {//判断是否为同一类型
					$("#taskPlanSubId").val($("#taskPlanSubId").val()+","+json["taskPlanSub"]["objId"]);//申报书条目
					$("#taskPlanMSubId").val($("#taskPlanMSubId").val()+","+json["objId"]);//中间表Id
					var html = "<span id=s"+json["objId"]+"> <span onclick='taskPlanSubForCreateProj.viewInfo(\""+json["objId"]+"\")' style='cursor:hand;color: blue'>"+json["taskPlanSub"]["purchaseName"]+"("+json["taskPlan"]["ebuyMethodCN"]+")</span><span onclick='taskPlanSubForCreateProj.delSpan(\""+json["objId"]+"\");' class='sysicon siDelete' style='cursor:hand'>&nbsp;</span></span>";
					$('#chooseId').append(html);
				}else {
					alert("请选择同一类型,已选择"+$('#taskTypeCNId').val()+"!");
					$("#taskPlanSubForCreateProjGrid :checked[value="+id+"]").attr("checked",false);
				}
			}else{
				alert("请选择同一采购方式,已选择"+$('#ebuyMethodCNId').val()+"!");
				$("#taskPlanSubForCreateProjGrid :checked[value="+id+"]").attr("checked",false);
			}
		}
		
	}else{
		var html = "<span id=s"+json["objId"]+"><span onclick='taskPlanSubForCreateProj.viewInfo(\""+json["objId"]+"\")' style='cursor:hand;color: blue'>"+json["taskPlanSub"]["purchaseName"]+"("+json["taskPlan"]["ebuyMethodCN"]+")</span><span onclick='taskPlanSubForCreateProj.delSpan(\""+json["objId"]+"\");' class='sysicon siDelete' style='cursor:hand'>&nbsp;</span></span>";
		$('#chooseId').append(html);
		$("#taskPlanSubId").val(json["taskPlanSub"]["objId"]);//申报书条目Id
		$("#taskPlanMSubId").val(json["objId"]);//中间表Id
		$("#ebuyMethodId").val(json["taskPlan"]["ebuyMethod"]);//采购方式
		$("#ebuyMethodCNId").val(json["taskPlan"]["ebuyMethodCN"]);//采购方式别名
		$("#taskTypeId").val(json["taskPlan"]["taskType"]);//类型
		$("#taskTypeCNId").val(json["taskPlan"]["taskTypeCN"]);//类型别名
		$("#buyerName").val(json["taskPlanSub"]["budgetName"]);//采购人名称
	}
}

taskPlanSubForCreateProj.delSpan = function(id){//删除
	$('#s'+id).remove();
	$('#'+id).find(":checkbox").attr("checked",false);
	taskPlanSubForCreateProj.noChooseClick(id);
}

taskPlanSubForCreateProj.viewInfo = function(id){//查看详细信息

	$.epsDialog({
    	title:'申报书信息',
    	url:$('#initPath').val()+'/TaskPlanMSubController.do?method=viewTaskPlanMSubByObjId&TaskPlanMSubId='+id,
    	width: '400',
    	height: '140',
		dragAndResize:true,
		hasBg:false,
		dragAndResize:true,
    	isReload: false,
		left:event.clientX,
		top:event.clientY-70
	});
}


//选择品目 
$("input[id=purchase.name]").click(function(e){
	$.epsDialog({
		id:"selectPurCategory",
        title:'选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=purchase&dialogId=selectPurCategory&className=PurCategory',
        width: '500',
        height: '400',
        onOpen: function(){ },
        afterLoad: function(){ },
        onClose: function(){ }
    }); 
	})

		
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
		'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
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
			return false;//避免触发行单击事件
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
			$("#buyerName").val("");//采购人姓名
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
		var buyerName = $("#buyerName").val();
		var jsonBuyerName = json["recFormBuyMethodCn"];
		if(buyerName != jsonBuyerName){  //判断是否同一预算单位
			alert("请选择同一预算单位，已选择"+ $("#buyerName").val()+"!");
			$("#taskPlanSubForCreateProjGrid :checked[value="+id+"]").attr("checked",false);
		}else{
			if(ebuyMethod != jsonEbuyMethod) {//判断是否为统一采购方式
				alert("请选择同一采购方式,已选择"+$('#ebuyMethodCNId').val()+"!");
				$("#recordFormGrid :checked[value="+id+"]").attr("checked",false);
			}else{
				$("#taskPlanSubId").val($("#taskPlanSubId").val()+","+json["objId"]);//申报书条目
				$("#taskPlanMSubId").val($("#taskPlanMSubId").val()+","+json["objId"]);//中间表Id
				var html = "<span id=s"+json["objId"]+"> <span onclick='recordFormList.viewInfo(\""+json["objId"]+"\")' style='cursor:hand;color: blue'>"+json["engineeringName"]+"("+json["ebuyMethodCn"]+")</span><span onclick='recordFormList.delSpan(\""+json["objId"]+"\");' class='sysicon siDelete' style='cursor:hand'>&nbsp;</span></span>";
				$('#chooseId').append(html);
			}	
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
		$("#buyerName").val(json["recFormBuyMethodCn"]); //采购人名称
	}
}


recordFormList.delSpan = function(id){//删除
	alert(id);
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
	taskPlanSubForCreateProj.currentTabID = "project01";
	taskPlanSubForCreateProj.contentOnchange = function (){
		if ($('#purchaseName').val()==null||$('#purchaseName').val()==''||$('#purchaseName').val()==undefined) {
			$('#purchase.objId').val('');
		}
	}
	$("#nextId").click(function(){//下一步
		if ($("#taskPlanSubId").val()==null||$("#taskPlanSubId").val()==""||$("#taskPlanSubId").val()==undefined) {
			alert('申报书明细不能为空,请选择!');
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
		}else if(taskPlanSubForCreateProj.currentTabID=="project05"){
			var taskPlanSub = $("#taskPlanSubId").val();//申报书条目Id
			var ebuyMethod = $("#ebuyMethodId").val();//采购方式
			var ebuyMethodCN = $("#ebuyMethodCNId").val();//采购方式别名
			var taskType = $("#taskTypeId").val();//类型
			var taskTypeCN = $("#taskTypeCNId").val();//类型别名
			var url = $("#initPath").val();
			url +="/ProjectController.do?method=toCreateProjectByConsignId&taskPlanSubIds="+taskPlanSub+"&ebuyMethod="+ebuyMethod;
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
			$("#recordFormSearchZone").hide();
			$("#taskPlanSubForCreateProjZone").show();
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=01";
			$('#taskPlanSubForCreateProjGrid').reload();
		}else if(taskPlanSubForCreateProj.currentTabID == "project02"){
			$("#recordFormSearchZone").hide();
			$("#taskPlanSubForCreateProjZone").show();			
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=02";
			$('#taskPlanSubForCreateProjGrid').reload();
		}else if(taskPlanSubForCreateProj.currentTabID == "project03"){
			$("#recordFormSearchZone").hide();
			$("#taskPlanSubForCreateProjZone").show();			
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=03";
			$('#taskPlanSubForCreateProjGrid').reload();
		}else if(taskPlanSubForCreateProj.currentTabID == "project05"){
			$("#recordFormSearchZone").hide();
			$("#taskPlanSubForCreateProjZone").show();			
			$('#taskPlanSubForCreateProjGrid').attr("p").url = "TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&order=createTime&order_flag=true&taskType=05";
			$('#taskPlanSubForCreateProjGrid').reload();
		}
		else{
			$("#recordFormSearchZone").show();
			$("#taskPlanSubForCreateProjZone").hide();
		}
	})
})