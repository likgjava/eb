var BargainProjectCreateForm = {};

BargainProjectCreateForm.save = function(){
	var fileType = $("input[name=chooseFile]:checked").val();
	$('#BargainProjectCreateForm').ajaxSubmit({
		url:$("#initPath").val()+"/BargainProjectController.do?method=createSignPayLinkerInfo&projId="+$('#projId').val()+"&fileType="+fileType,
		dataType:'json',
		success:function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$('#project_info').loadPage($('#initPath').val()+"/BargainProjectController.do?method=toUpdateSignPayLinkerInfo&objId="+$('#projId').val());
		},
		error:function(msg){
			alert("操作失败！");
		}
	});
}

//选择联系人 
BargainProjectCreateForm.chooseLinker = function(){
    $.epsDialog({
        id:"linkerSelect",
        title:'请选择联系人',
        url:$('#initPath').val()+'/view/agreement/bargin/signup/a_linker_list.jsp',
        width:700,
        height:400
    }); 
}

//选择文件方式的切换
BargainProjectCreateForm.chooseFileChange = function(e){
	if( $("input[name=chooseFile]:checked").attr("id") != 'chooseUpload' ){
		$("#assessment_File").attr("disabled",true);
	}else{
		$("#assessment_File").attr("disabled",false);
	}
}


//选择已有的文件
BargainProjectCreateForm.chooseRuleFile = function(){
	$.epsDialog({
		id:"chooseRuleFileDiv",
		title:"选择已有的文件",
		url:$("#initPath").val()+"/AssessmentRuleController.do?method=toChooseAssessmentRuleList&projId="+$('#projId').val()
	})
}

$(document).ready(function(){
	//表单验证
	$("#BargainProjectCreateForm").validate();
	
	//保存
	$("#save").click(function(){
		if(!$('#BargainProjectCreateForm').valid()){alert('请正确填写表单!');return;}
		var fileType = $("input[name=chooseFile]:checked").val();
//		if("choose"==fileType && $("input[id=assessmentFile.objId]").val() == "" ){
//			alert("请选择或上传规则文件！");return;
//		}
		if(window.confirm('确认保存吗?')) {
			BargainProjectCreateForm.save();
		}
	});
});

