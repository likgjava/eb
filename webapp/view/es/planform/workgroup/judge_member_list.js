var openbid_member_list ={};
openbid_member_list.add = function(workGroupId,subProjectId){
	 $.epsDialog({
	        title:'新增评标小组成员',
	        url:$('#initPath').val()+'/WorkGroupController.do?method=toSaveJudgeMember&subProjectId='+subProjectId+'&workGroupId='+workGroupId,
	        width: '800',
	        height: '250',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        isReload:false,
	        onClose: function(){
	        	if($("#projectTaskId") && $("#projectTaskId").val() != ""){
		        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			    }
	        }
	    }); 
}
openbid_member_list.del= function(memberId){
	$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=removeProofMember',{objId:memberId},function(json){
		alert("删除成功！");
		if($("#projectTaskId") && $("#projectTaskId").val() != ""){
        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
	   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	    }
	});
}
$(document).ready(function(){
});

