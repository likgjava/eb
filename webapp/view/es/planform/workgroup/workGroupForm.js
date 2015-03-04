
var workGroupForm={};

$(document).ready(function(){
	
	if($("#objId").val()=='')
	{
		$("#workgMemberList").hide();
	}
	else
	{
		$("#workgMemberList").empty().loadPage($('#initPath').val()+"/view/es/planform/workgroup/workgMemberList.jsp?workGroupId="+$("#objId").val());
	}
	
	
	
	
	$('#workGroupForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/WorkGroupController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('workGroupForm', json.workGroup);
    	});
    }
	//返回
	$('#workGroupReturn').click(function(){
		$('#workGroupListView').empty().loadPage($('#initPath').val()+"/view/es/planform/workgroup/workGroupList.jsp");
	});
	//提交
	$('#workGroupSave').click(function(){
		if(!$('#workGroupForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/WorkGroupController.do?method=saveOrUpdateWorkGroup', formToJsonObject('workGroupForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#objId").val(json.workGroupId);
			$("#workgMemberList").empty().loadPage($('#initPath').val()+'/view/es/planform/workgroup/workgMemberList.jsp?workGroupId='+json.workGroupId);
			$("#workgMemberList").show();
		});
	});

});
