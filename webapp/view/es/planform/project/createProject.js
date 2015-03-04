/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var craeteProjForTaskPlanSub={};

//删除
craeteProjForTaskPlanSub.remove=function(subid){
	if(confirm("确认移除需求明细吗？")){
		$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=remove',{"objId":subid},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#consignGrid").reload();//刷新
			//重新获取需求条目ids，参数：需求书ID
			getTaskPlanSubs($('#objId').val());
		});
	}
}
//新增
craeteProjForTaskPlanSub.add=function(name,grid){
	$.epsDialog({
        title:'需求条目',
        url:$('#initPath').val()+'/TaskPlanSubController.do?method=toCreateTaskPlanSubFormView&type=createProject',
        width: '700',
        height: '450',
        maxWin:false
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
craeteProjForTaskPlanSub.saveOrSumbit=function(){
	var jsonObj = formToJsonObject('craeteProjForTaskPlanSubForm');
	//设置招标类型
	jsonObj.purCategoryNames=$('#purCategoryIds option:selected').text();
	
	//获取需求明细列表
	 var ids = ""
		 $("#consignGrid").flexGetColByName({
		'budgetName':function(id,t){
		 ids += id+",";
		   }
		});
	//将此次立项的需求条目ID传入后台,以创建项目任务书条目中间表数据
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectByTaskPlan&isComplete=true&projSubIds='+ids,jsonObj , function(json){
		if(json.result)alert(json.result);if(json.failure)return;
//		$("#conBody").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toCreateProjectByConsignId');
		//跳到项目显示页面
//		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+json.projectId);
		//跳到需求条目列表页《立项第一步》
		$('#conBody').empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanListForCreateProject.jsp');
	});
}
craeteProjForTaskPlanSub.viewTaskPlanSub=function(taskPlanSubId){
	//弹出对应的需求明细信息
	$.epsDialog({
        title:"需求明细",
        url:$("#initPath").val()+"/view/es/planform/taskplan/taskPlanSubDetailForm.jsp?taskPlanSubId="+taskPlanSubId,
        width: 750,
        height: 450,
        isReload:false,
        onClose: function(){
        }
	});
}
//招标编号重构
craeteProjForTaskPlanSub.changePrefixCode = function(code,ebuyMethod){
	var pre ;
	switch(ebuyMethod){
	 case "00": pre="open"; break;
	 case "01": pre="invite"; break;
	 case "02": pre="negotiate"; break;
	 case "03":	 pre="inquiry";	 break;
	 case "04":	 pre="single";	 break;
	 default:pre=""; 
	};
	var v= pre+code.substring( code.indexOf('-'),code.length);
	$('#projCode').val(v);
}
$(document).ready(function(){
	$('#ebuyMethod').change(function(){
		craeteProjForTaskPlanSub.changePrefixCode($('#projCode').val(),$('#ebuyMethod').val());
	})
	//判断输入字符是否超过多少字符
	function wordsNumMoreThan(num,id)
	{
		var otext = $("#"+id).val();
	    var app = $("label[for='"+id+"']").html();
		if(otext.length>num)
		{
			alert(app+"的输入字数不能超过"+num+"字符！")
			return true;
		}
		return false;
	};
    
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
    		'<span><a href="#" class="abtn">查看明细</a></span>'  : function(btn,rowId,obj){
				 btn.click(function(){
					 craeteProjForTaskPlanSub.viewTaskPlanSub(rowId);
				 }).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a></span>'  : function(btn,rowId,obj){
				btn.click(function(){
					craeteProjForTaskPlanSub.remove(rowId);
				}).appendTo(obj);
			}
    	});
	}

  	//返回
	$('#returnButton').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanListForCreateProject.jsp');
	})
	
	//提交
	$('#projectSaveButton').click(function(){
		if ($('#managerId').val()==PlatForm.user.emp.objId) {
			if (window.confirm('确认要分配给自己？')){
				if($("#taskPlanSubIds").val() == "" || $("#taskPlanSubIds").val() == "-1"){
					alert("请选择需求明细！");
					return;
				}
				if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
					craeteProjForTaskPlanSub.saveOrSumbit();
			}
		}else{
			if($("#taskPlanSubIds").val() == "" || $("#taskPlanSubIds").val() == "-1"){
				alert("请选择需求明细！");
				return;
			}
			if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
			if(window.confirm('是否确认？')){
				craeteProjForTaskPlanSub.saveOrSumbit();
			}
		}
	});

});
