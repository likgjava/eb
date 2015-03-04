var dosBuyRecordForm = {};

$(document).ready( function() {
			
	var tenderId = $('#tenderId').val();//标书费对应的项目或包组ID
	var alreadSupplierIds = new Array();
	$('input[name='+tenderId+']').each(function(){
		alreadSupplierIds.push($(this).val());
	});
	$("#supplierName").autocomplete(
			$('#initPath').val() + '/UserApiController.do?method=getOrginfoByQueryObject&alreadSupplierIds='+alreadSupplierIds.toString()+'&projectId='+$("#tenderId").val(), 
			{
				matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
				extraParams:{"supplierId":null,"supplierId_op":"!="},
				mustMatch: true,
				formatItem: function(data, i, total) {
					return data.orgName;
				},
				formatMatch: function(data, i, total) {
					return data.orgName;
				},
				formatResult: function(data) {
					return data.orgName;
				}
			}
		).result(function(event,data,formatted){
			if(data){
				$("#supplierName").val(data.orgName);//回填id
				$("#supplierId").val(data.objId);//回填id
			}
	});
	
	$('#dosBuyRecordReturn').click(function(){//关闭
		$("#epsDialogCloseReload").click();
	});
	
	$('#dosBuyRecordSave').click(function(){//保存
		if(!$('#dosBuyRecordForm').valid()){alert('请正确填写表单!');return;}
		var flagPayType = false;
		$('input[name="payType"]:checked').each(function(){//判断是否有评审室
			flagPayType = true;
		 });
		if (!flagPayType) {
			alert('请选择支付方式！');
			return false;
		}
		if(null == $('#attachRelaIdBak').val() || "" == $('#attachRelaIdBak').val()){
			$("#attachRelaId1").val("false");
		}else{
			$("#attachRelaId1").val("true");
		}
		if(null == $('#invoiceFileBak').val() || "" == $('#invoiceFileBak').val()){
			$("#invoiceFile1").val("false");
		}else{
			$("#invoiceFile1").val("true");
		}
		$('#dosBuyRecordForm').ajaxSubmit({
			url:$('#initPath').val()+"/DosBuyRecordController.do?method=saveDosBuyRecord",
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);return;}
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				$("#epsDialogCloseReload").click();return;
			},
			error:function(msg){
				alert(msg);
			}
		});
		
	});

	//附件 缴纳证明
	$('#attachRelaId3').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId2").val(),
		readOnly:'yes'
	});
	//附件 发票附件
	$('#invoiceFile3').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#invoiceFile2").val(),
		readOnly:'yes'
	});
	
});
