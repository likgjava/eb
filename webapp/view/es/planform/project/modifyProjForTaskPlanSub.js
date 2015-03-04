
var craeteProjForTaskPlanSub={};

craeteProjForTaskPlanSub.add = function(){
	//弹出对应的申报书明细信息
	$.epsDialog({
        title:"申报书明细",
        url:$("#initPath").val()+"/TaskPlanController.do?method=toModifyTaskPlanSubListPageForSuperaddition&projectId="+$('#projectId').val()+"&taskType="+$('#tenderType').val()+"&ebuyMethod="+$("#ebuyMethod").val()+"&rp=6&taskPlanSubIds_not="+$("#taskPlanSubIds").val(),
        width: 800,
        height: 400,
        isReload:false,
        onClose: function(){
        }
	});
}
//移除
craeteProjForTaskPlanSub.remove = function(){
	if($("#consignGrid").isSelectEmpty()){alert('请选择申报书明细');return false;}//是否选中
	if(confirm("确认移除申报书明细吗？")){
		var ids_remove = $("#consignGrid").getSelects().split(',');
		var taskPlanSubIds = $("#taskPlanSubIds").val();
		for(var j = 0 ; j < ids_remove.length ; j ++){
			var temp = ids_remove[j];
			var old_taskPlanSubIds = taskPlanSubIds.split(',');
			if(old_taskPlanSubIds.length>0){
				for(var i = 0 ; i < old_taskPlanSubIds.length ; i ++){
					if(temp == old_taskPlanSubIds[i]){//若相等，则从原有数据中删除
						//两种情况 的删除[代码顺序不能颠倒]
						taskPlanSubIds = taskPlanSubIds.replace(","+temp,'');//第一次替换
						taskPlanSubIds = taskPlanSubIds.replace(temp,'');//第二次替换
					}
				}
			}
		}
	}
	//回选最终的数据
	$("#taskPlanSubIds").val(taskPlanSubIds);
	//刷新表单
	$("#consignGrid").reload();
}


craeteProjForTaskPlanSub.saveOrSumbit=function(){
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectMamanger&projectId='+$("#projectId").val()+'&managerId='+$("input[name=manager.objId]").val(), {}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$("#epsDialogClose").click();
		$("#project04").click();
	});
}
craeteProjForTaskPlanSub.viewTaskPlanSub=function(taskPlanSubId){
	//弹出对应的申报书明细信息
	$.epsDialog({
        title:"申报书明细",
        url:$("#initPath").val()+"/view/es/planform/taskplan/taskPlanSubDetailForm.jsp?taskPlanSubId="+taskPlanSubId,
        width: 800,
        height: 600,
        isReload:false,
        onClose: function(){
        }
	});
}
$(document).ready(function(){
	$("input[name=manager.name]").autocomplete(
			$('#initPath').val() + '/EmployeeController.do?method=getObjectQuery&queryColumns=name,objId', 
			{
				matchColumn:'name',//作为查询显示, 被选中之后匹配的列
				extraParams:{"company.code":PlatForm.user.orgInfo.orgCode},
				mustMatch: true,
				formatItem: function(data, i, total) {
					return data.name;
				},
				formatMatch: function(data, i, total) {
					return data.name;
				},
				formatResult: function(data) {
					return data.name;
				}
			}
		).result(function(event,data,formatted){
			if(data){
				$("input[name=manager.name]").val(data.name);//回填id
				$("input[id=manager.objId]").val(data.objId);//回填id
			}
		});
    //加载上传组件
    $('#consContentsFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=consContentsAtt&attachRelaId='+$("#consContentsFile").text());
    
    //查询条件过滤
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
			}
    	});
		$("#consignGrid").flexGetColByName({
			 'purchaseName' : function(id,t){
			 	var json = $("#consignGrid").flexGetRowJsonById(id); 
				$(t).html('<a href="#" onclick="craeteProjForTaskPlanSub.viewTaskPlanSub(\''+id+'\')">'+$(t).html()+'</a>')
			}
		});
	}

	
	
	//提交
	$('#projectSaveButton').click(function(){
		if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm('是否确认？')){
			craeteProjForTaskPlanSub.saveOrSumbit();
		}
	});

});
