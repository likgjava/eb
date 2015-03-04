
var recordFormForm={};


$(document).ready(function(){
	
	//返回
	$('#recordFormReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/taskplan/recordFormForTradeCentList.jsp");
	});
	
	//通过
	$('#passButton').click(function(){
		if(window.confirm("确认审核通过？")){
			$.getJSON($('#initPath').val()+'/RecordFormController.do?method=auditRecordForm', {'objId':$("#recordFormId").val(),'auditStatus':'01','opinion':$("#opinion").val()}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/taskplan/recordFormForTradeCentList.jsp");
			});
		}		
	});
	//不通过
	$('#noPassButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==""||$("#opinion").val()=="同意"){
			$("#opinion").val("");
			alert("请输入退回意见！");
			return false;
		}
		if(!$('#recordFormAudit').valid()){
			alert("请正确填写表单！");return;
		}
		if(window.confirm("确认审核不通过？")){
			$.getJSON($('#initPath').val()+'/RecordFormController.do?method=auditRecordForm', {'objId':$("#recordFormId").val(),'auditStatus':'02','opinion':$("#opinion").val()}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/taskplan/recordFormForTradeCentList.jsp");
			});
		}	
	});
});