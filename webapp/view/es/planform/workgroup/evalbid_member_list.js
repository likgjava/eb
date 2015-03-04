var evalbid_member_list ={};
evalbid_member_list.add = function(workGroupId,projectId){
	 $.epsDialog({
	        title:'添加评标小组成员',
	        url:$('#initPath').val()+'/view/es/planform/workgroup/float/expert_info.jsp?workGroupId='+workGroupId+'&projectId='+projectId,
	        width: '700',
	        height: '400',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
}
evalbid_member_list.del= function(memberId){
	$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=remove',{objId:memberId},function(json){
		if($('#formTab').val()=='yes'){
			$('#agentTabDiv').loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupForProject&groupType=02&fromTab=yes&projectId='+$('#projectId').val());
		}else{
			$('#projectDoDiv').loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupForProject&groupType=02&projectId='+$('#projectId').val());
		}
	});
}
$(document).ready(function(){
});

