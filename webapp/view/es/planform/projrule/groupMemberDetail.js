	var groupMemberForm={};

	$(document).ready(function(){
		$('#groupMemberForm').validate();
	     			$("#createTime").epsDatepicker();
	    if($('#objId').val()!=''){
	    	$.getJSON($('#initPath').val()+'/GroupMemberController.do?method=beforeUpdateView&objId='+$('#objId').val(),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
	    		jsonObjectToForm('groupMemberForm', json.groupMember);
	    	});
	    }
		//返回
		$('#groupMemberReturn').click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/project/projectList.jsp");
		});
		//提交
		$('#groupMemberSave').click(function(){
			if(!$('#groupMemberForm').valid()){alert('请正确填写表单!');return;}
			$.getJSON($('#initPath').val()+'/GroupMemberController.do?method=saveOrUpdateGroupMember&projectId='+$("#projectId").val(), formToJsonObject('groupMemberForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/project/projectList.jsp');
			});
		});
	});
