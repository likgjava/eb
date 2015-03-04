var openbid_member_list ={};
openbid_member_list.add = function(workGroupId){
	 $.epsDialog({
	        title:'选择开标小组成员',
	        url:$('#initPath').val()+'/WorkGroupController.do?method=toSaveBidMember&projectId='+$("#projectId").val()+'&workGroupId='+workGroupId,
	        width: '600',
	        height: '250',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        isReload:false,
	        onClose: function(){

	        }
	    }); 
}
openbid_member_list.del= function(memberId){
	if(window.confirm("确定要删除吗?")){
	 $.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=removeBidMember',{objId:memberId},function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
	 });
	}
}
$(document).ready(function(){
});

