
var evaSellerRecordForm={};
evaSellerRecordForm.changeFile = function(){
	var fileval = $("#attachRealFile").val();
	if(null != fileval && fileval != "" && "null" != fileval){
		$("#isUploadFile").val("true");
		var operate = "";
		operate += '<button style="height: 23px">';
		operate += '<span>清空</span>';
		operate += '</button>';
		$("#operate_div").empty().append(operate).find('button').click(function(){
			document.getElementById("attachRealFile").outerHTML = document.getElementById("attachRealFile").outerHTML;
			$("#operate_div").empty();
		})
	}else{
		$("#isUploadFile").val("false");
	}
}
$(document).ready(function(){
	//$("#download_div").empty();
	$('#openBidRecordForm').validate();
	//初始化页面数据 从evalBidRecordListView.jsp获取数据         start=========
	var count = $("#count").val();
	$("#sellerName").val($("#supplierName_"+count+"").html());
	$("#quoteSum").val($("#quoteSum").val());
	
	//初始化页面数据 从evalBidRecordListView.jsp获取数据         end=========
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/OpenBidRecordController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('openBidRecordForm', json.openBidRecord);
    	});
//		$.ajax({
//			url:$("#initPath").val()+"/OpenbidGeneralviewController.do?method=getOpenbidGeneralviewByOpenBidRecordId&openBidRecordId="+$('#objId').val(),
//			type:"POST",
//			data:{},
//			async:false,
//			success:function(json){
//				var json = eval('('+json+')');
//				$.each(json.openbidGeneralviewList,function(i,n){
//					$("#openbidGeneralviewObjId").val(n.objId);
//					if(undefined != n.attachment && '' != n.attachment && n.attachment.objId.length >0){
//						$("#attachRealFileId").val(n.attachment.objId);
//						var download = "";
//						download += '<a href="'+$("#initPath").val()+"/AttachmentController.do?method=downLoadFile&objId="+n.attachment.objId+'">';
//						download += n.attachment.viewName;
//						download += '</a>';
//						$("#download_div").empty().append(download);
//					}
//				})
//			}
//		})
    	
    }
	//对于页面初始化完毕后的数据，进行显示处理
	$("#sellerNameView").html($("#sellerName").val());
	$("#quoteSumView").html($("#quoteSum").val());
	
	//返回
	$('#openBidRecordReturn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	
	// 表单提交
	evaSellerRecordForm.submit = function(){
		$("#openBidRecordForm").ajaxSubmit({
			success:function(json){
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				$('#epsDialogCloseReload').click();
			},
			error:function(json){
				alert("录入开标记录失败,请稍候重试..");
				return false;
			}
		});
	}
	
	//提交事件
	$('#openBidRecordSave').click(function(){
//		var isUploadFile = $("#isUploadFile").val();
//		if("false" == isUploadFile){
//			if(null == $("#download_div").html() || '' == $("#download_div").html()){
//				alert("您还没有上传附件,请添加附件..")
//				return false;
//			}
//		}
		evaSellerRecordForm.submit();
	});
});
