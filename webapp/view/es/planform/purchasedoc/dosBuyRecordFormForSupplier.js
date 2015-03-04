var dosBuyRecordForm = {};

$(document).ready( function() {
			
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
			url:$('#initPath').val()+"/DosBuyRecordController.do?method=saveDosBuyRecordForSupplier&isAJAX=true&tenderId="+$('#projectIds').val(),
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);return;}
				$('#tabform15').click();
			},
			error:function(msg){
				alert(msg);
			}
		});
		
	});
	
	
	//清空缴纳证明
	$('#cleanEvidence').click(function(){//
		//alert("123");
		$("#attachRelaId1").val("false");
		$("#attachRelaIdBak").val(""); 
		
	});
	//清空发票附件
	$('#cleanAttachment').click(function(){//
		$("#invoiceFile1").val("false");
		$("#invoiceFileBak").val("");
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
