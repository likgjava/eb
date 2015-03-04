
var bulletinForm={};

bulletinForm.checkTime=function(){
	if($("#signUpSTime").val()>=$("#signUpETime").val()){alert('报名结束时间应大于报名开始时间！');return false;}
//	if(better_time($("#signUpSTime").val(),$("#signUpETime").val())<20){alert('报名开始时间与报名结束时间最少要相差20天！');return false;}
	if($("#tenderStartTime").val()>=$("#tenderEndTime").val()){
		alert('投标开始时间应小于投标结束时间！');
		return false;
	}
	if($("#tenderStartTime").val()<=$("#signUpETime").val()){
		alert('投标开始时间应大于报名结束时间！');
		return false;
	}
	
	if($("#openBidTime").val()<$("#tenderEndTime").val()){
		alert('开标开始时间应大于投标结束时间！');
		return false;
	}

//	if(better_time($("#tenderStartTime").val(),$("#tenderEndTime").val())<15){alert('投标开始时间与开标开始时间最少要相差15天！');return false;}
	return true;
}

//打印预览
$('#toPrint').click(function(){
	FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
	$("#tenderEndTime").val($("#openBidTime").val());//同步时间
	var jsonObject = formToJsonObject('bulletinForm');
	delete jsonObject.tenderEndTime;
	delete jsonObject.openBidTime;
	delete jsonObject.signUpSTime;
	delete jsonObject.signUpETime;
	delete jsonObject.tenderStartTime;
	$.getJSON($('#initPath').val()+'/BulletinController.do?method=toBulletinPrintPageByType',jsonObject,function(json){
	    if(json.result)alert(json.result);if(json.failure)return;
	    window.open($('#initPath').val()+'/view/es/common/RequestContentPrint.jsp');
	});	
});

//比较日期相差天数
function better_time(strDateStart,strDateEnd){
	   var strSeparator = "-"; //日期分隔符
	   var strDateArrayStart;
	   var strDateArrayEnd;
	   var intDay;
	   strDateArrayStart = strDateStart.split(strSeparator);
	   strDateArrayEnd = strDateEnd.split(strSeparator);
	   var strDateS = new Date(strDateArrayStart[0] + "/" + strDateArrayStart[1] + "/" + strDateArrayStart[2]);
	   var strDateE = new Date(strDateArrayEnd[0] + "/" + strDateArrayEnd[1] + "/" + strDateArrayEnd[2]);
	   intDay = (strDateE-strDateS)/(1000*3600*24);
	   return intDay;
}

//对指标的check
bulletinForm.checkFactorView = function(obj){
	var flag=true;
	var flag2 = true;
	var factorNames = $(obj).parent().parent().find('input[name=factorName]')
	var remarks = $(obj).parent().parent().find('textarea[name=remark]');
	$(factorNames).each(function(){
		if($(this).val()==""){
			$(this).focus();
			$(this).parent().find('span').html('<font color="red">必填</font>');
			flag=false;
		}else{
			$(this).parent().find('span').html('<font color="red">*</font>');
			flag=true;
		}
	});
	$(remarks).each(function(){
		if($(this).val()==""){
			$(this).focus();
			$(this).parent().find('span').text('必填');
			flag2 = false;
		} else if($(this).val().length>=250){
			$(this).parent().find('span').text('指标说明需少于250个字');	
			flag2 = false;
		}else{
			$(this).parent().find('span').text('*');
			flag2=true;
		}
	});
	
	return flag&&flag2;
};


$(document).ready(function(){
	$('#epsTabs').tabs();
	/**
	 * 初始化关联项目Id
	 */
	$("*[name=project.objId]").val($("#projectId").val());
	 
	$('#bulletinForm').validate();
	/*
	$("#signUpSTime").epsDatepicker({ timeShow:true});
	$("#signUpETime").epsDatepicker({ timeShow:true});
	$("#tenderStartTime").epsDatepicker({ timeShow:true});
	$("#openBidTime").epsDatepicker({ timeShow:true});
	$("#tenderEndTime").epsDatepicker({ timeShow:true});
	$("#evalStartTime").epsDatepicker({ timeShow:true});
	*/
	//关闭
	$('#closeButton').click(function(){
		$('#epsDialogClose').click();
	});
	
	bulletinForm.oFCKeditor = new FCKeditor('bullContents','100%','600','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	bulletinForm.oFCKeditor.ReplaceTextarea();
	//保存
	$('#bulletinSave').click(function(){
		if(bulletinForm.checkFactorView($(this))){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
	
		//if(bulletinForm.checkTime()){
			if(window.confirm("确认保存吗?"))
			{
				var jsonObj = formToJsonObject('bulletinForm');
			    var jsonContracts = new Array();
				$("a[name='savesignUpCondFactor']").each(function(i,n){
					var obj = {};
					obj.objId =$(this).parent().parent().find('input[name=signupfacId]').val();
					obj.factorId =$(this).parent().parent().find('input[name=factorId]').val();
					obj.factorName=$(this).parent().parent().find('input[name=factorName]').val();
					obj.remark = $(this).parent().parent().find('textarea[name=remark]').val();
					jsonContracts.push(obj);
				})
			jsonObj.contractStr = JSON.stringify(jsonContracts);
			$.getJSON($('#initPath').val()+'/QualificationsController.do?method=saveQualification', jsonObj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
					planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				  } else {
					$("#myDesktop").click();
				}
			});
		 }
			
		//}
	}
	});
	//提交
	$('#bulletinSubmit').click(function(){
		if(bulletinForm.checkFactorView($(this))){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		
		if(bulletinForm.checkTime()){
			if(window.confirm("确认提交?"))
			{
			var jsonObj = formToJsonObject('bulletinForm');
			var workFlowTaskId = $('[id=auditTaskId]').val();
			if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
				workFlowTaskId = $('[id=auditTask_Id]').val();
			}
			jsonObj.workFlowTaskId = workFlowTaskId;
			jsonObj.auditStatus = 'Y';
			jsonObj.projectTaskId = $('#projectTaskId').val();
		    var jsonContracts = new Array();
			$("a[name='savesignUpCondFactor']").each(function(i,n){
				var obj = {};
				obj.objId =$(this).parent().parent().find('input[name=signupfacId]').val();
				obj.factorId =$(this).parent().parent().find('input[name=factorId]').val();
				obj.factorName=$(this).parent().parent().find('input[name=factorName]').val();
				obj.remark = $(this).parent().parent().find('textarea[name=remark]').val();
				if(obj.remark.length >= 250){
					alert(obj.factorName + "指标的说明需小于250个字!");
					return false;
				}
				jsonContracts.push(obj);
			})
			jsonObj.contractStr = JSON.stringify(jsonContracts);
			$.getJSON($('#initPath').val()+'/QualificationsController.do?method=saveUpdateSubmitQualificationBulletin',jsonObj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
					planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				  } else {
					$("#myDesktop").click();
				}
				});
			}
		}
	}
	});
	
	$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&projectId='+$("input[name=project.objId]").val());
});
//返回
function bullentinReturn(){
	var auditStatus = $('#auditStatus').val();
	var bullType = $('#bullType').val();
	$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getMoreForBulletinList&auditStatus='+auditStatus+'&bullType='+bullType);

}

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});
