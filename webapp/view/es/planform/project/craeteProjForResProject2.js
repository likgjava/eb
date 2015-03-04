/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var craeteProjForTaskPlanSub={};
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
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectByResProject&isComplete=true', formToJsonObject('craeteProjForTaskPlanSubForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('#conBody').empty().loadPage($('#initPath').val()+'/TaskPlanController.do?method=toTaskPlanListForCreateProjBiXuan');
	});
}
$(document).ready(function(){
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
    	var option={"objId":$("#resProjectId").val()}
    	$('#consignGrid').flexOptions({params:option});
    	return true;
    }
    
  //加载数据成功之后调用的函数
    craeteProjForTaskPlanSub.success=function(){
	  return true;
	}

	
	
	//提交
	$('#projectSaveButton').click(function(){
			if (window.confirm('确认提交吗？')){
				if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
					craeteProjForTaskPlanSub.saveOrSumbit();
			}
	});

});
