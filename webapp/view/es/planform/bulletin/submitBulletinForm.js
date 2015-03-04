
var bulletinForm={};

bulletinForm.checkTime=function(){
	if($("#signUpSTime").val()>=$("#signUpETime").val()){alert('报名结束时间应大于报名开始时间！');return false;}
//	if(better_time($("#signUpSTime").val(),$("#signUpETime").val())<20){alert('报名开始时间与报名结束时间最少要相差20天！');return false;}
	
	if($("#tenderStartTime").val()>=$("#signUpETime").val()){alert('投标开始时间应大于报名结束时间！');return false;}
	
	if($("#tenderStartTime").val()>=$("#tenderEndTime").val()){alert('投标结束时间应大于投标开始时间！');return false;}
//	if(better_time($("#tenderStartTime").val(),$("#tenderEndTime").val())<15){alert('投标开始时间与投标结束时间最少要相差15天！');return false;}
	return true;
}

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


$(document).ready(function(){
	/**
	 * 初始化关联项目Id
	 */
	$("*[name=project.objId]").val($("#projectId").val());
	
	 if($('#objId').val()!=''){
	    	$.getJSON($('#initPath').val()+'/BulletinController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
	    		jsonObjectToForm('bulletinForm', json.bulletin);
	    	});
	    }
	 
	$('#bulletinForm').validate();
	
	$("#signUpSTime").epsDatepicker({ timeShow:true});
	$("#signUpETime").epsDatepicker({ timeShow:true});
	$("#tenderStartTime").epsDatepicker({ timeShow:true});
	$("#openBidTime").epsDatepicker({ timeShow:true});
	//返回
	//关闭
	$('#closeButton').click(function(){
		$('#epsDialogClose').click();
	});
	
	bulletinForm.oFCKeditor = new FCKeditor('bullContents','100%','240','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	bulletinForm.oFCKeditor.ReplaceTextarea();
	//提交
	$('#bulletinSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		//同步时间
		$("#tenderEndTime").val($("#openBidTime").val());
		if(bulletinForm.checkTime()){
			if(window.confirm("确认提交?"))
			{
			$.getJSON($('#initPath').val()+'/PurBulletinController.do?method=saveSubmitPurBulletin', formToJsonObject('bulletinForm'), function(json){
				if(json.failure)return;
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				if($("#bullType").val() == '01'){
					if($("#fromDiv").val() == 'yes'){//若是来源于弹出层，则关闭弹出层
						$('#epsDialogClose').click();
					}else{//若不是来源于弹出层，则跳到指定页面
						//project_agent.jsp 公有方法 
						planTemplateTask.refresh($("#projectTaskId").val()+"");
						alert("提交成功！");
					}
				}else if($("#bullType").val() == '06'){
					checkProjectMenu("menu_resultBulletin");
				}else if($("#bullType").val() == '05'){
					checkProjectMenu('menu_resultPublicity');
				}
			});
			}
		}
	});
//	codeHighlight.init();
});
