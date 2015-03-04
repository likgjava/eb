/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var craeteProjForTaskPlanSub={};
craeteProjForTaskPlanSub.add = function(){
	var taskTypes = $("#tenderType").val();
	var budgetName = '';
	$("#consignGrid").flexGetColByName({//点击一行触发事件
		 "budgetName" : function(id,t){
		budgetName =$(t).html();
		}
	});
	
	
	
	var str = '{"budgetName":"'+budgetName+'"}';
	var json =  JSON.parse(str);
	//弹出对应的申报书明细信息
	$.epsDialog({
        title:"申报书明细",
        url:$("#initPath").val()+"/TaskPlanController.do?method=toTaskPlanSubListPageForSuperaddition&taskType="+taskTypes+"&ebuyMethod="+$("#ebuyMethod").val()+"&rp=6&taskPlanSubIds_not="+$("#taskPlanSubIds").val(), 
        params:json,
        width: 600,
        height: 330,
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
		$.each(ids_remove,function(k,o){//循环要删除申报书条目
			$.each($("#taskPlanSubIds").val().split(','),function(i,n){//循环已经存在的申报书条目
				if (n=o) {//判断是否是要删除的申报书条目
					taskPlanSubIds = taskPlanSubIds.replace(n,'');
				}
			});
		});
		var new_taskPlanSubIds = new Array();
		$.each(taskPlanSubIds.split(','),function(j,f){
			if (f!=null&&f!=''&&f!=undefined) {
				new_taskPlanSubIds.push(f);
			}
		});
	}
	if(new_taskPlanSubIds.toString() == null || new_taskPlanSubIds.toString() == ""){
		alert("委托至少要有一条申报书明细!");
		return false;
	}
	//回选最终的数据
	$("#taskPlanSubIds").val(new_taskPlanSubIds.toString());
	//刷新表单
	$("#consignGrid").reload();
}


craeteProjForTaskPlanSub.saveOrSumbit=function(){
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectByTaskPlanSubs&isComplete=true', formToJsonObject('craeteProjForTaskPlanSubForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		/*$("#conBody").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toCreateProjectByConsignId');*/
		//跳到项目显示页面
//		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+json.projectId);
		//跳到申报书条目列表页《立项第一步》
		$('#conBody').empty().loadPage($('#initPath').val()+'/TaskPlanController.do?method=toTaskPlanListForCreateProj');
	});
}
craeteProjForTaskPlanSub.viewTaskPlanSub=function(taskPlanSubId){
	//弹出对应的申报书明细信息
	$.epsDialog({
        title:"申报书明细",
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
    
    //加载数据，判断id是否为空
    if($('#objId').val()!= null && $('#objId').val()!=''){
    	//编号置为不可用
    	$("#projCode").attr("disabled","disabled");
    };
    
    //加载上传组件
    $('#consContentsFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=consContentsAtt&attachRelaId='+$("#consContentsFile").text());
    
    //加载申报书页面
   // $("#taskPlanInfo").empty().loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=div&objId='+$("#taskPlanId").val())
    
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
		if ($('#managerId').val()==PlatForm.user.emp.objId) {
			if (window.confirm('确认要分配给自己？')){
				if($("#taskPlanSubIds").val() == "" || $("#taskPlanSubIds").val() == "-1"){
					alert("请选择申报书明细！");
					return;
				}
				if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
					craeteProjForTaskPlanSub.saveOrSumbit();
			}
		}else{
			if($("#taskPlanSubIds").val() == "" || $("#taskPlanSubIds").val() == "-1"){
				alert("请选择申报书明细！");
				return;
			}
			if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
			if(window.confirm('是否确认？')){
				craeteProjForTaskPlanSub.saveOrSumbit();
			}
		}
	});

});
