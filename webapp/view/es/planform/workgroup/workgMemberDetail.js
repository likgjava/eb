
var workgMemberForm={};

$(document).ready(function(){

	$('#workgMemberForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#workgMemberId').val()!=''){
  
    	$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=createOrUpdate',{objId:$('#workgMemberId').val(), includedProperties:'workGroup'},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			json2Object('workgMemberForm', json.workgMember);
    		
    		 //修改是否为小组长的显示内容
    	    if($("#workgmIsLeader").val()==0)
    		{
    			$("#workgmIsLeader").val('否');
    		}
    		 if($("#workgmIsLeader").val()==1)
    		{
    			$("#workgmIsLeader").val('是');
    		}
    		
    	});
    }
   
	//返回
	$('#workgMemberReturn').click(function(){
		if($("#workGroupType").val()=='1')//开标小组为1
		{
			$("#projectContent").empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=saveOrUpdateOpenBidGroup&projectId='+$("#projectId").val());
		}
		else if($("#workGroupType").val()=='2') //项目小组为2
		{
			$("#projectContent").empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=saveOrUpdateWorkGroup&projectId='+$("#projectId").val());
		}
	});
	
});