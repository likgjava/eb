	var projectPackageForm={};

	$(document).ready(function(){
		$('#projectPackageForm').validate();
	     			$("#createTime").epsDatepicker();
	    if($('#objId').val()!=''){
	    	$.getJSON($('#initPath').val()+'/ProjectPackageController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
	    		jsonObjectToForm('projectPackageForm', json.projectPackage);
	    	});
	    }
		//返回
		$('#projectPackageReturn').click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/ProjectPackageController.do");
		});
		//提交
		$('#projectPackageSave').click(function(){
			if(!$('#projectPackageForm').valid()){alert('请正确填写表单!');return;}
			$.getJSON($('#initPath').val()+'/ProjectPackageController.do?method=save', formToJsonObject('projectPackageForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/ProjectPackageController.do');
			});
		});
	});
