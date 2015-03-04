
var recordFormForm={};

/*初始化项目人员表单*/
recordFormForm.initProjMemeberForm = function(){
	var select = document.getElementById("projMemeberTypeSelected");
	for(optionJ = 0; optionJ < select.options.length; optionJ++){
		op = select.options[optionJ];
		var projMember = {"projMemeberType":op.value};
		recordFormForm.addProjMember(projMember);
	}
}

recordFormForm.projMemberCount = 0;//人员总数量，用于动态添加表格行

recordFormForm.removeProjMember = function(thisObj, projMemberObjId){
	if(window.confirm('确定删除吗?')){
		if(projMemberObjId == ""){
			$(thisObj).parent().parent().remove();
		}else{
			$.getJSON($('#initPath').val()+'/ProjMemberController.do?method=remove',{objId:projMemberObjId},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(thisObj).parent().parent().parent().remove();
			});
		}
	}
}



recordFormForm.addProjMember = function(projMember){
	if(projMember != null){
		var select = document.getElementById("projMemeberTypeSelected");
		for(j = 0; j < select.options.length; j++){
			op = select.options[j];
			if(op.value == String(projMember.projMemeberType)){
				op.selected = true;
			}
		}
	}
	
	var html = '';
		html += '<tr>';
		html += '<td>';
		html += '<input type="hidden" name="sets['+recordFormForm.projMemberCount+'&com.gpcsoft.epp.taskplan.domain.ProjMember].objId" value="'+(projMember != null && projMember.objId != undefined? projMember.objId : "")+'"/>';
		html += '<select style="width:90px" name="sets['+recordFormForm.projMemberCount+'&com.gpcsoft.epp.taskplan.domain.ProjMember].projMemeberType">'+$("#projMemeberTypeSelected").html()+'</select></td>';
		html += '<td><input style="width:70px;" maxlength="25" type="text" title="该项是必填项!" name="sets['+recordFormForm.projMemberCount+'&com.gpcsoft.epp.taskplan.domain.ProjMember].projMemberName" class="required" value="'+(projMember != null  && projMember.projMemberName != undefined? projMember.projMemberName : "")+'"/></td>';
		html += '<td><input style="width:140px;" maxlength="50" type="text" title="该项是必填项!" name="sets['+recordFormForm.projMemberCount+'&com.gpcsoft.epp.taskplan.domain.ProjMember].projMemberCompany" class="required"   value="'+(projMember != null  && projMember.projMemberCompany != undefined? projMember.projMemberCompany : "")+'"/></td>';
		html += '<td><input style="width:60px;" maxlength="25" type="text" title="该项是必填项!" name="sets['+recordFormForm.projMemberCount+'&com.gpcsoft.epp.taskplan.domain.ProjMember].projMemberDudy" class="required"   value="'+(projMember != null  && projMember.projMemberDudy != undefined? projMember.projMemberDudy : "")+'"/></td>';
		html += '<td><textarea  style="height:40px;width:150px" max="250" name="sets['+recordFormForm.projMemberCount+'&com.gpcsoft.epp.taskplan.domain.ProjMember].projMemberResults" class="required">'+(projMember != null  && projMember.projMemberResults != undefined? projMember.projMemberResults : "")+'</textarea></td>';
		html += '<td><textarea style="height:40px;width:150px"  max="1000" name="sets['+recordFormForm.projMemberCount+'&com.gpcsoft.epp.taskplan.domain.ProjMember].projMemberRemark" >'+ (projMember != null  && projMember.projMemberRemark != undefined? projMember.projMemberRemark : "")+'</textarea></td>';
		html += '<td><a type="button" onclick="recordFormForm.removeProjMember(this,\''+(projMember != null  && projMember.objId != undefined? projMember.objId : "")+'\');">删除</a></td>';
		html += '</tr>';
	$("#recordFormProjMemberTbody").append(html); 
	
	recordFormForm.projMemberCount ++;
}



$(document).ready(function(){
//	$('#recordFormForm').validate();
//	$("#ecpFormBulletinDate").epsDatepicker();  //发布公告时间
//	$("#ecpFormTenderDocDate").epsDatepicker(); //招标文件发放时间
//	$("#ecpFormBiddingSDate").epsDatepicker();  
//	$("#ecpFormBiddingEDate").epsDatepicker();  //投标截止、开标时间
//	$("#ecpFormNoticeDate").epsDatepicker();    //中标通知书发出
//	$("#ecpFormNoticDate").epsDatepicker();     //合同签订时间
//	$("#createTime").epsDatepicker();
//	
//	recordFormForm.checkTime=function(){
//		if($("#ecpFormBulletinDate").val()>=$("#ecpFormTenderDocDate").val()){alert('招标文件发放时间应大于发布公告时间！');return false;}
//		if($("#ecpFormTenderDocDate").val()>=$("#ecpFormBiddingEDate").val()){alert('投标截止、开标时间应大于招标文件发放时间！');return false;}
//		if($("#ecpFormBiddingEDate").val()>=$("#ecpFormNoticeDate").val()){alert('中标通知书发出应大于等于投标截止、开标时间！！');return false;}
//		if($("#ecpFormNoticeDate").val()>=$("#ecpFormNoticDate").val()){alert('合同签订时间应大于等于中标通知书发出时间！');return false;}
//		return true;
//	}
	
	
//    if($('#objId').val()!=''){
//    	$.getJSON($('#initPath').val()+'/RecordFormController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:'projMembers'},function(json){
//			for(var j = 0; j < json.recordForm.projMembers.length ; j ++){
//				recordFormForm.addProjMember(json.recordForm.projMembers[j]);
//			}
//    		if(json.result)alert(json.result);if(json.failure)return;
//    		jsonObjectToForm('recordFormForm', json.recordForm);
//    		
//    		//加载附件
//    		$('#viewTendererProve').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
//    			defineSelf:'attachRelaId',//存放关联id的属性名
//    			attachRelaId:json.recordForm.tendererProve,
//    			readOnly:'yes',
//    			isShowDelFileBtn:'yes'
//    		});
//    		$('#viewProjApproval').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
//    			defineSelf:'attachRelaId',//存放关联id的属性名
//    			attachRelaId:json.recordForm.projApproval,
//    			readOnly:'yes',
//    			isShowDelFileBtn:'yes'
//    		});
//    		$('#viewFundsProof').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
//    			defineSelf:'attachRelaId',//存放关联id的属性名
//    			attachRelaId:json.recordForm.fundsProof,
//    			readOnly:'yes',
//    			isShowDelFileBtn:'yes'
//    		});
//    		$('#viewApproveUnit').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
//    			defineSelf:'attachRelaId',//存放关联id的属性名
//    			attachRelaId:json.recordForm.approveUnit,
//    			readOnly:'yes',
//    			isShowDelFileBtn:'yes'
//    		});
//    		$('#viewDesignUnit').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
//    			defineSelf:'attachRelaId',//存放关联id的属性名
//    			attachRelaId:json.recordForm.designUnit,
//    			readOnly:'yes',
//    			isShowDelFileBtn:'yes'
//    		});
//    		$('#viewPerformProcedure').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
//    			defineSelf:'attachRelaId',//存放关联id的属性名
//    			attachRelaId:json.recordForm.performProcedure,
//    			readOnly:'yes',
//    			isShowDelFileBtn:'yes'
//    		});
//    	});
//    }else{
//    	recordFormForm.initProjMemeberForm();
//    	
//    	$("#recReserveUserName").val(PlatForm.user.emp.name);
//    	$("#recReserveUserIdObjId").val(PlatForm.user.objId);
//    	$("#recFormOrgName").val(PlatForm.user.orgInfo.orgName);
//    	$("#recFormOrgIdObjId").val(PlatForm.user.orgInfo.objId);
//    }
    
	//返回
	$('#recordFormReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/RecordFormController.do");
	});
	
	//保存
	$('#recordFormSave').click(function(){
		if(!$('#recordFormForm').valid()){alert('请正确填写表单!');return;}
		if($("#recordFormForm").find("input[type=radio][name=ebuyMethod][checked]").length == 0){
			alert("请选择招标方式!");
			return ;
		}
		if($("#recordFormForm").find("input[type=radio][name=recFormBuyMethod][checked]").length == 0){
			alert("请选择招标组织形式!");
			return ;
		}
		if($("#recordFormForm").find("input[type=radio][name=projProperty][checked]").length == 0){
			alert("请选择项目属性!");
			return ;
		}
		if($("#recordFormForm").find("input[type=radio][name=projNature][checked]").length == 0){
			alert("请选择项目性质!");
			return ;
		}
		
		if(window.confirm('确定保存吗?')){
			$.getJSON($('#initPath').val()+'/RecordFormController.do?method=saveRecordForm',formToJsonObject('recordFormForm'),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/RecordFormController.do');
			});
		}
	});
	
	
	
	//提交
	$('#recordFormSubmit').click(function(){
		if(!$('#recordFormForm').valid()){alert('请正确填写表单!');return;}
		if($("#recordFormForm").find("input[type=radio][name=ebuyMethod][checked]").length == 0){
			alert("请选择招标方式!");
			return ;
		}
		if($("#recordFormForm").find("input[type=radio][name=recFormBuyMethod][checked]").length == 0){
			alert("请选择招标组织形式!");
			return ;
		}
		if($("#recordFormForm").find("input[type=radio][name=projProperty][checked]").length == 0){
			alert("请选择项目属性!");
			return ;
		}
		if($("#recordFormForm").find("input[type=radio][name=projNature][checked]").length == 0){
			alert("请选择项目性质!");
			return ;
		}
		
		if(window.confirm('确定提交吗?')){
			var recordFormForm = formToJsonObject('recordFormForm');
			recordFormForm.useStatus = '01';
			$.getJSON($('#initPath').val()+'/RecordFormController.do?method=saveRecordForm',recordFormForm,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/RecordFormController.do');
			});
		}
		
//		if(recordFormForm.checkTime()){
//			if(window.confirm('确定提交吗?')){
//				$('#recordFormForm').ajaxSubmit({
//					url:$('#initPath').val()+"/RecordFormController.do?method=addRecordForm",
//					dataType:'json',
//					success:function(json){
//						if(json.failure){alert(json.result);return;}
//						$('#conBody').loadPage($('#initPath').val()+'/RecordFormController.do');
//					},
//					error:function(msg){
//						alert(msg);
//					}
//				});
//			}
//		}	
	
	});
	
	//选择招标中心
//	$("#agencyName").autocomplete(
//		$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
//		{
//			matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
//			extraParams:{"agencyId":null,"agencyId_op":"!="},
//			mustMatch: true,
//			max:50,
//			formatItem: function(data, i, total)
//			{
//				return data.orgName;
//			},
//			formatMatch: function(data, i, total) {
//				return data.orgName;
//			},
//			formatResult: function(data) {
//				return data.orgName;
//			}
//		}
//	).result(function(event,data,formatted){
//		if(data){
//			$("#agencyName").val(data.orgName);//回填id
//			$("#agencyId").val(data.objId);//回填id
//		}
//	});

});
