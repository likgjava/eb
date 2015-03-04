
var bidPackageForm={};

$(document).ready(function(){
	$('#bidPackageForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#modifyTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/BidPackageController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('bidPackageForm', json.bidPackage);
    	});
    }
	//返回
	$('#bidPackageReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/project/projectList.jsp");
	});
	//提交
	$('#bidPackageSave').click(function(){
		if(!$('#bidPackageForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/BidPackageController.do?method=saveOrUpdateBidPackage&bidId='+$("#bidId").val(), formToJsonObject('bidPackageForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/project/projectList.jsp');
		});
	});

});
