/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignForm={};

consignForm.saveOrSumbit=function(){
	
	$.getJSON($('#initPath').val()+'/ConsignController.do?method=saveOrUpdateConsign', formToJsonObject('consignForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if($("#useStatus").val() == "00")
			alert("保存成功！");
		else{
			alert("已提交，请等待确认！");
		}
		//跳转到上一个页面
		$('button[name=historyBackBtn]').click();
	});
}





$(document).ready(function(){
	if('YES'==$("#isSave").val())//如果已经保存过了，保存按钮消失
	{
		$("#consignSave").hide();
	}
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
	
	//日历控件
    $("#consTime").val(new Date().Format('yyyy-MM-dd')).epsDatepicker();
    $("#consFinishTime").epsDatepicker();
    
    //加载数据，判断id是否为空
//    if($('#objId').val()==''){
//    	$("#consCodeSpan").empty().append("保存后编号自动生成.");
//    };
    
    //加载上传组件
    //$('#consContentsFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=consContentsAtt&attachRelaId='+$("#consContentsFile").text());
    
    //附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
    
    //加载申报书页面
    $("[name=viewTaskPlan]").click(function(){
    	//弹出对应的申报书明细信息
    	$.epsDialog({
            title:"申报书明细",
            url:$("#initPath").val()+"/TaskPlanController.do?method=showDetail&type=div&objId="+$("#taskPlanId").val(),
            width: 800,
            height: 400,
            isReload:false,
            onClose: function(){
            }
    	});
    });
  
	//保存
	$('#consignSave').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#consignForm').valid()){
			alert('请正确填写表单!');
			return;
		}else if($("#consFinishTime").val()<=$("#consTime").val())
		{
			alert("拟完成时间应在委托时间之后！");
		}else if(window.confirm('确定要保存吗？'))
		{
			//填写使用状态
			$("#useStatus").val("00");
			$("#confirmStatus").val("00");
			consignForm.saveOrSumbit();
		}
	});
	
	//提交
	$('#consignSubmit').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#consignForm').valid()){
			alert('请正确填写表单!');
			return;
		}else if($("#consFinishTime").val()<=$("#consTime").val())
		{
			alert("拟完成时间应在委托时间之后！");
		}else if(window.confirm('确定要提交招标单位确认吗？'))
		{
				$("#useStatus").val("01");
				$("#confirmStatus").val("01");
				consignForm.saveOrSumbit();
		}
	});

	var isShowOpinion = $("#isShowOpinion").val();
	if(isShowOpinion != "true"){
		$("#show_audit_view").remove();
	}
});

$('#show_audit_view').click(function(){
	$("#consign_audit_list").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#consignForm").find('input[id=objId]').val()+'&taskType=05');
});
