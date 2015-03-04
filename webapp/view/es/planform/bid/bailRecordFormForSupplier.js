var dosBuyRecordForm = {};

$(document).ready( function() {
			
	$('#dosBuyRecordSave').click(function(){//保存
		if(!$('#bailRecordForm').valid()){alert('请正确填写表单!');return;}
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
		$('#bailRecordForm').ajaxSubmit({
			url:$('#initPath').val()+"/BailRecordController.do?method=saveBailRecordForSupplier&projectId="+$('#projectIds').val(),
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);return;}
				$('#tabform16').click();
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
	
});
