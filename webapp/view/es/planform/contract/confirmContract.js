
var contractForm={};
$(document).ready(function(){
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text(),
		readOnly:'yes'
	});
	$("input[name='subProj']").each(
			function(i,n){
				if($(this).val()==$("#subProjectId").val()){
					$(this).attr("checked","checked");
				}
		});
	$("input[name='subProj']").attr("disabled","disabled");
	
	//通过
	$('#contractSave').click(function(){
			var obj ={};
			obj.objId = $("#objId").val();
			obj.opinion= $("#opinion").val();
			obj.contractStatus = '03';
			$.getJSON($('#initPath').val()+'/ContractController.do?method=saveContractForConfirm',obj,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#tabValue").val("9");
				if($("#fromType").val()=='fromLeft'){
					$("#conBody").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractSupplierListByRole&projectId='+$("#projectId").val());
				}else{
					$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractListForBuyer&projectId='+$("#projectId").val());
				}
	    	});
	});
	//不通过
	$('#contractReback').click(function(){
		var obj ={};
		obj.objId = $("#objId").val();
		obj.opinion= $("#opinion").val();
		obj.contractStatus = '02';
		$.getJSON($('#initPath').val()+'/ContractController.do?method=saveContractForConfirm',obj,function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#tabValue").val("9");
			if($("#fromType").val()=='fromLeft'){
				$("#conBody").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractSupplierListByRole&projectId='+$("#projectId").val());
			}else{
				$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractListForBuyer&projectId='+$("#projectId").val());
			}
    	});
	});

	
	//操作历史
//	$('#history').click(function(){
//		$.epsDialog({
//	    	title:'查看操作历史',
//	    	url:$('#initPath').val()+"/ContractController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#projectId").val()+'&taskType=04',
//	    	width: '700',
//	    	height: '150',
//	    	onClose: function(){ 
//	       }
//		});
//		
//	});
	//返回
	$('#contractReturn').click(function(){
		$("#tabValue").val("9");
		if($("#fromType").val()=='fromLeft'){
			$("#conBody").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractSupplierListByRole&projectId='+$("#projectId").val());
		}else{
			$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractListForBuyer&projectId='+$("#projectId").val());
		}
	});

});
