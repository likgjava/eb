var craeteProjForTaskPlanSub={};
//新增
craeteProjForTaskPlanSub.addSub=function(name,grid){
	$.epsDialog({
        title:'招标类型/内容',
        url:$('#initPath').val()+'/TaskPlanSubController.do?method=toCreateTaskPlanSubFormView&type=createProject',
        width: '700',
        height: '450',
        maxWin:false
	});	
}  
craeteProjForTaskPlanSub.viewTaskPlanSub=function(taskPlanSubId){
	//弹出对应的招标类型/内容信息
	$.epsDialog({
        title:"招标类型/内容",
        url:$("#initPath").val()+"/view/es/planform/taskplan/taskPlanSubDetailForm.jsp?taskPlanSubId="+taskPlanSubId,
        width: 750,
        height: 450,
        isReload:false,
        onClose: function(){
        }
	});
}
craeteProjForTaskPlanSub.remove=function(taskPlanSubId){ 
	$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=remove',{"objId":taskPlanSubId},function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$("#consignGrid").reload();//刷新
	});
	
}

craeteProjForTaskPlanSub.before=function(){
	var option={"taskPlanSubIds":$("#taskPlanSubIds").val()}
	$('#consignGrid').flexOptions({params:option});
	return true;
}
//加载数据成功之后调用的函数
craeteProjForTaskPlanSub.success=function(){
	$("#consignGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">查看明细</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
			 btn.click(function(){
				 craeteProjForTaskPlanSub.viewTaskPlanSub(rowId);
			 }).appendTo(obj);
		},'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
			 btn.click(function(){
				 craeteProjForTaskPlanSub.remove(rowId);
			 }).appendTo(obj);
		}
	});
	 
	
	$("#consignGrid").flexGetColByName({
		 'purchaseName' : function(id,t){
		 	var json = $("#consignGrid").flexGetRowJsonById(id); 
			$(t).html('<a href="#" onclick="craeteProjForTaskPlanSub.viewTaskPlanSub(\''+id+'\')">'+$(t).html()+'</a>')
		}
	});
}
function getTaskPlanSubs(taskPlanId){
	//回填taskplanSubIds
	$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=getTaskPlanSubIdsByTaskPlanId',{"taskPlanId":taskPlanId},function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if(json.taskplansubIds=="") {
			$('#taskPlanSubIds').val("-1");
		} else {
			$('#taskPlanSubIds').val(json.taskplansubIds);
		}
		$("#consignGrid").reload();//刷新
	});
}
$(document).ready(function(){
	$('#save').click(function(){
		if(!$('#tenderProjectForm').valid()){alert('请正确填写表单!');return false;}
		if($("#consignGrid").isSelectEmpty()){alert('请选择项目明细');return false;}
		var selectedIds = $("#consignGrid").getSelects();
		
		var url = $('#initPath').val()+'/ResProjectController.do?method=saveTenderProjectInfo';
		var tenderProjectInfo = formToJsonObject('tenderProjectForm');
		tenderProjectInfo.manager = {objId:$('#managerId').val()};
		$.getJSON(url,{"projectStr":JSON.stringify(tenderProjectInfo),"projSubIds":selectedIds,"resProjectId":$('#resProjectId').val()},function(json){
			if(json.result)alert(json.result);
	        if(json.failure){
	        	alert(json.result);
	            return;
	        }
	        
	        //跳到招标项目信息页面
			$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+json.projectId);

	        //返回到项目列表页面
	        /*
	        if($('#resProjectParentId').val()==''){
				$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
			}else{
				 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+$('#resProjectParentId').val());
			}
	        */
	        
		});
	});
	
	$('#back').click(function(){
		if($('#resProjectParentId').val()==''){
			$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
		}else{
			 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+$('#resProjectParentId').val());
		}
	});
	
	
});

