
var contractList={};
	//新增
	contractList.add=function(subId,contractMethod){
		//跳转到新增页面
		$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toCreateContract&subId='+subId+'&contractMethod='+contractMethod+'&projectId='+$("#projectId").val());
	}   
	contractList.update=function(objId){
		//跳转到修改页面
		$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toUpdateContract&objId='+objId);
	}
	contractList.remove=function(id){
		//删除合同
		if(window.confirm('确定要删除吗?')){
			$.getJSON($('#initPath').val()+'/ContractController.do?method=remove',{objId:id},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
			});
		}
		$("#tabValue").val("9");
		$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractList&projectId='+$("#projectId").val());
	}
	contractList.detail=function(id,fromType){
		//查看合同明细
		$('#tabform').empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractDetailForBuyer&fromType='+fromType+'&objId='+id);
	}
	contractList.confirm=function(id){
		//确认合同
		$('#tabform').empty().loadPage($('#initPath').val()+'/ContractController.do?method=toConfirmContract&objId='+id);
	}
$(document).ready(function(){
	
});

