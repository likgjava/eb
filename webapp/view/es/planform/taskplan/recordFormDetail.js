
var recordFormForm={};

recordFormForm.addProjMember = function(projMember){
	var html = '';
		html += '<tr height="25">';
		html += '<td>' + (projMember != null ? projMember.projMemeberTypeCn : "") + '</td>';
		html += '<td>' + (projMember != null ? projMember.projMemberName : "") + '</td>';
		html += '<td>' + (projMember != null ? projMember.projMemberCompany : "") + '</td>';
		html += '<td>' + (projMember != null ? projMember.projMemberDudy : "") + '</td>';
		html += '<td><span>' + ((projMember!= null && projMember.projMemberResults!= undefined )? projMember.projMemberResults : "") + '</span></td>';
		html += '<td>' + ((projMember != null && projMember.projMemberRemark!= undefined) ? projMember.projMemberRemark : "") + '</td>';
		html += '</tr>';
	$("#recordFormProjMemberTbody").append(html);
	
	recordFormForm.projMemberCount ++;
}

$(document).ready(function(){
	if($('#recordFormDetailFormObjId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/RecordFormController.do?method=createOrUpdate',{objId:$('#recordFormDetailFormObjId').val(), includedProperties:'projMembers'},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('recordFormDetailForm',json.recordForm);
    		
			for(var j = 0; j < json.recordForm.projMembers.length ; j ++){
				recordFormForm.addProjMember(json.recordForm.projMembers[j]);
			}
			
    		//加载附件
    		$('#viewTendererProve').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
    			defineSelf:'attachRelaId',//存放关联id的属性名
    			attachRelaId:json.recordForm.tendererProve,
    			readOnly:'yes'
    		});
    		$('#viewProjApproval').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
    			defineSelf:'attachRelaId',//存放关联id的属性名
    			attachRelaId:json.recordForm.projApproval,
    			readOnly:'yes'
    		});
    		$('#viewFundsProof').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
    			defineSelf:'attachRelaId',//存放关联id的属性名
    			attachRelaId:json.recordForm.fundsProof,
    			readOnly:'yes'
    		});
    		$('#viewApproveUnit').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
    			defineSelf:'attachRelaId',//存放关联id的属性名
    			attachRelaId:json.recordForm.approveUnit,
    			readOnly:'yes'
    		});
    		$('#viewDesignUnit').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
    			defineSelf:'attachRelaId',//存放关联id的属性名
    			attachRelaId:json.recordForm.designUnit,
    			readOnly:'yes'
    		});
    		$('#viewPerformProcedure').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
    			defineSelf:'attachRelaId',//存放关联id的属性名
    			attachRelaId:json.recordForm.performProcedure,
    			readOnly:'yes'
    		});
    	});
    }
});