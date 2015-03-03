var CreateProjectForm3 = {};

//选择联系人 
CreateProjectForm3.chooseLinker = function(){
    $.epsDialog({
        id:"linkerSelect",
        title:'请选择联系人',
        url:$('#initPath').val()+'/view/agreement/bargin/signup/a_linker_list.jsp',
        width:700,
        height:400
    }); 
}

//选择文件方式的切换
CreateProjectForm3.chooseFileChange = function(e){
	if( $("input[name=chooseFile]:checked").attr("id") != 'chooseUpload' ){
		$("#assessment_File").attr("disabled",true);
	}else{
		$("#assessment_File").attr("disabled",false);
	}
}


//选择已有的文件
CreateProjectForm3.chooseRuleFile = function(){
	$.epsDialog({
		id:"chooseRuleFileDiv",
		title:"选择已有的文件",
		url:$("#initPath").val()+"/AssessmentRuleController.do?method=toChooseAssessmentRuleList&projId="+$('#projId').val()
	})
}

$(document).ready(function(){
	$("#CreateProjectForm3").validate();

	//保存
	$('#bd_post_save_btn').click(function(){
		if(!$('#CreateProjectForm3').valid()){alert('请正确填写表单!');return;}
		
		var fileType = $("input[name=chooseFile]:checked").val();
//		if("choose"==fileType && $("input[id=assessmentFile.objId]").val() == "" ){
//			alert("请选择或上传规则文件！");return;
//		}
		
		if(window.confirm('确定保存?')) {
			CreateProjectForm3.createSignPayLinkerInfo('00');//保存联系人、付款方式、供应商资质等信息
		}
	});
	
	//提交
	$('#bd_post_submit_btn').click(function(){
		if(!$('#CreateProjectForm3').valid()){alert('请正确填写表单!');return;}
		
		var fileType = $("input[name=chooseFile]:checked").val();
//		if("choose"==fileType && $("input[id=assessmentFile.objId]").val() == "" ){
//			alert("请选择或上传规则文件！");return;
//		}
		
		if(window.confirm('确定提交项目信息?')) {
			CreateProjectForm3.createSignPayLinkerInfo('01');//保存联系人、付款方式、供应商资质等信息
		}
	});
})

//保存联系人、付款方式、供应商资质等信息
CreateProjectForm3.createSignPayLinkerInfo = function(saveStatus){
	
	//所有条件验证完毕更换按钮样式
	$('#submitDiv').addClass('hidden')
	$('#submittingDiv').removeClass('hidden').text().replace('保存中','提交中');

	//供应商资质信息
	var signInfoJsonArray = [];
	var signInfoJson={
			project:{objId:$('#projId').val()},
			companyQualification:$('#companyQualification').val()
	}
	signInfoJsonArray.push(signInfoJson);
	
	//联系方式信息
	var contactInfoJsonArray = [];
	var contactInfoJson={
			project:{objId:$('#projId').val()},
			linker:$('#linker').val(),
			mobilePhone:$('#mobilePhone').val(),
			fixedTelephone:$('#fixedTelephone').val(),
			fax:$('#fax').val(),
			address:$('#address').val(),
			postCode:$('#postCode').val()
	}
	contactInfoJsonArray.push(contactInfoJson);
	
	//支付方式信息
	var payInfoJsonArray = [];
	var payInfoJson={
			project:{objId:$('#projId').val()},
			deliveryDate:$('#deliveryDate').val(),
			deliveryAddress:$('#deliveryAddress').val(),
			deliveryType:$('#deliveryType').val(),
			payType:$('#payType').val(),
			supplement:$('#supplement').val()
	}
	payInfoJsonArray.push(payInfoJson);
	
	var params = {};
	params.projId = $('#projId').val();//项目ID
	params.signInfoStr = JSON.stringify(signInfoJsonArray);//供应商资质信息
	params.payInfoStr = JSON.stringify(payInfoJsonArray);//付款方式信息
	params.contactInfoStr = JSON.stringify(contactInfoJsonArray);//联系人信息
	params.status = saveStatus;//项目状态
	
	var fileType = $("input[name=chooseFile]:checked").val();
	
	$('#CreateProjectForm3').ajaxSubmit({
		url:$("#initPath").val()+"/BargainProjectController.do?method=createSignPayLinkerInfo&projId="+$('#projId').val()+"&status="+saveStatus+"&fileType="+fileType,
		dataType:'json',
		success:function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			window.location.href=$('#initPath').val()+'/BargainProjectController.do?method=toProjectSubmitResultView&projectStatus='+saveStatus+"&projId="+$('#projId').val();
		},
		error:function(msg){
			alert("操作失败！");
		}
	});
	
//	$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=createSignPayLinkerInfo',params, function(json){
//		if(json.failure){if(json.result)alert(json.result);return;}
//		window.location.href=$('#initPath').val()+'/BargainProjectController.do?method=toProjectSubmitResultView&projectStatus='+saveStatus+"&projId="+$('#projId').val();
//	})
}