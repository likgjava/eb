/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignForm={};
$(document).ready(function(){
	if ('YES'==$("#isSave").val()) {
		$("#noPassButton").hide();
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
		var attrNum = $("#atta_display_ul").find("li").length;
		if (!$('#consignForm').valid()) {
			alert('请正确填写表单!');
			return false;
		} else if($("#consFinishTime").val()<=$("#consTime").val()) {
			alert("拟完成时间应在委托时间之后！");
			return false;
		} else if(attrNum<=0){
			alert("请上传附件！");
			return false;
		}else if(window.confirm('确定要保存吗？')) {
			var consign = formToJsonObject('consignForm');
			consign.attachRelaId=$("input[name=attachRelaId]").val();
			consign.consOpinion=$("#opinion").val();
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=createConsign', consign, function(json){
				if(json.result)alert(json.result);
				//跳转到上一个页面
				$('button[name=historyBackBtn]').click();
			});
		}
	});
	
	//提交
	$('#consignSubmit').click(function(){
		var attrNum = $("#atta_display_ul").find("li").length;
		if (!$('#consignForm').valid()) {
			alert('请正确填写表单!');
			return false;
		} else if($("#consFinishTime").val()<=$("#consTime").val()) {
			alert("拟完成时间应在委托时间之后！");
			return false;
		}else if(attrNum<=0){
			alert("请上传附件！");
			return false;
		}else if(window.confirm('确定要提交招标单位确认吗？')) {
			var consign = formToJsonObject('consignForm');
			consign.attachRelaId=$("input[name=attachRelaId]").val();
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=submitConsign', consign, function(json){
				if(json.result)alert(json.result);
				//跳转到上一个页面
				$('button[name=historyBackBtn]').click();
			});
		}
	});

	//退回
	$('#noPassButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==""||$("#opinion").val()=="同意"){
			$("#opinion").val("");
			alert("请输入退回意见！");
			return false;
		}
		if(window.confirm("确认不受理申报书吗？")){
			$("#noPassButton").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=saveReturnTaskplanByAgent&consignId='+$("#consignId").val()+'&taskplanId='+$("#taskPlanId").val()+'&opinion='+$('#opinion').val(),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=historyBackBtn]').click();
			});
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
