
var projectInfoForm = {}



$(document).ready(function(){

	projectInfoForm.timeChange = function(){//投标结束时间改变
		if ($('#tenderEndTime').val()) {
			$('#openBidStartDate').val($('#tenderEndTime').val());
		}
	}
	$("#signUpSTime").epsDatepicker({ timeShow:true});//报名开始时间
	$("#signUpETime").epsDatepicker({ timeShow:true});//报名结束时间
	$("#tenderStartTime").epsDatepicker({ timeShow:true});//投标开始时间
	$("#tenderEndTime").epsDatepicker({ timeShow:true});//投标结束时间
	$("#openBidStartDate").epsDatepicker({ timeShow:true});//开标开始时间
	$("#evalStartTime").epsDatepicker({ timeShow:true});//评标开始时间
	
	//对时间进行校验
	projectInfoForm.checkTime=function(){
		if($("#signUpSTime").val()>=$("#signUpETime").val()){alert('报名结束时间应大于报名开始时间！');return false;}
		if($("#signUpETime").val()>=$("#tenderStartTime").val()){alert('投标开始时间应大于报名结束时间！');return false;}
		if($("#tenderStartTime").val()>=$("#tenderEndTime").val()){alert('投标结束时间应大于投标开始时间！');return false;}
		if($("#tenderEndTime").val()>$("#openBidStartDate").val()){alert('开标时间应大于等于投标结束时间！');return false;}
		return true;
	}
	if ($('#openBidStartDate').val()==null||$('#openBidStartDate').val()==undefined||$('#openBidStartDate').val()=="") {
		$('#openBidStartDate').val($('#tenderEndTime').val());
	}
	
	$('#projectSave').click(function(){
		if (projectInfoForm.checkTime()) {//验证时间
			if(!$('#tenderForm').valid()){alert('请正确填写表单!');return;}
			if(!$('#subProjectForm').valid()){alert('请正确填写表单!');return;}
			var jsonObj = formToJsonObject('subProjectForm','jsonUtils');
			var saveObj = new Array();
			if(jsonObj.subProject!=null&&jsonObj.subProject!=undefined){
				$.each(jsonObj.subProject,function(i,n){
					if(n != undefined && null != n){
						saveObj.push(n);
					}
				});
			}
			var tenderForm = formToJsonObject('tenderForm','jsonUtils');
			$.getJSON($('#initPath').val()+'/ChangebulletinController.do?method=saveInputTenderInfo',{"project":JSON.stringify(tenderForm),"subProjects":JSON.stringify(saveObj)}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/ChangebulletinController.do?method=toDraftChangeBulletin&projectId='+$('input[name=objId]').val());
			});
		}
	});
	
	$('#returnback').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/bulletin/allprojectList.jsp');
	})
	
});