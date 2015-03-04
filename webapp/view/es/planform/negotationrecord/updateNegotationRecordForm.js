/*
 * 执行平台，采购申报书明细表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var updateNegotationRecordForm={};
$(document).ready(function(){
	updateNegotationRecordForm.sumMoney = function(){
		var total = 0;
		  $("input[name$=negRecItemTotal]").each(function(){
			  if($(this).val()!=''){
		    	  total += parseInt($(this).val());
		      } 
	      })	
	   $("#recordTotal").val(total);
	} 
	//时间控件
	$("#recordTime").epsDatepicker({ timeShow:true});//报价时间
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	//展示需求明细信息
	updateNegotationRecordForm.showPreqEntryDetail = function(objId){
		$.epsDialog({
			id:"showPreqEntryDetail",
	    	title:'需求条目信息',
	    	url:$('#initPath').val()+'/view/es/planform/negotationrecord/lookPreqEntryInfo.jsp?objId='+objId,
	    	width: '800',
	    	height: '420',
	    	isReload:false,
	    	onClose: function(){ 	
	       }
		});	
	}
	
	
	//提交
	$('#taskPlanSubSave').click(function(){
		var j = 0;
		$("input[nam='nam']").each(
				function(){
					if(!isNaN($(this).val())){
						j=parseInt($(this).val())+parseInt(j);
					}
				}
			)
		$("#recordTotal").val(j);
	
		var negotationRecord = formToJsonObject('negotationRecordForm','json');
		negotationRecord.supplierName = $("select[id=supplierId] option:selected").html();
		negotationRecord.negRecordFile = $("input[name=attachRelaId]").val();
		var jsonObj = formToJsonObject('negotationRecordItemForm','jsonUtils');
		if(!$('#negotationRecordForm').valid()){
			alert('请正确填写表单!');
			return false;
		}
		if(!$('#negotationRecordItemForm').valid()){
			alert('请正确填写表单!');
			return false;
		}
		$("#taskPlanSubSave").attr("disabled","disabled");
		$.getJSON($('#initPath').val()+'/NegotationRecordController.do?method=updateNegotationRecord',{"negotationRecordString":JSON.stringify(negotationRecord),"negotationRecordItemString":JSON.stringify(jsonObj.negotationRecordItem)}, function(json){
			if(json.result)alert(json.result);if(json.failure)return false;
			$('#closeBtn').click();
			$('#negotationRecordGrid').reload();
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    } else {
		    	$("#myDesktop").click();
		    }
		});
	});
	
	
	
	//返回
	$('#closeBtn').click(function(){
		$('#epsDialogCloseReload').click();
	})
});
