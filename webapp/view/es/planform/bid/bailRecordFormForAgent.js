var dosBuyRecordForm = {};

$(document).ready( function() {
	var subProjectId = $('#projectIds').val();
	var alreadSupplierIds = new Array();
	$('input[name='+subProjectId+']').each(function(){
		alreadSupplierIds.push($(this).val());
	});
	$("#supplyerName").autocomplete(
			$('#initPath').val() + '/UserApiController.do?method=getOrginfoByQueryObject&alreadSupplierIds='+alreadSupplierIds.toString()+'&projectId='+$("#projectIds").val()+'', 
			{
				matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
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
				$("#supplyerName").val(data.orgName);//回填id
				$("#supplyerId").val(data.objId);//回填id
			}
	});
	
	$('#bailRecordSave').click(function(){//保存
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
			url:$('#initPath').val()+"/BailRecordController.do?method=saveBailRecordForAgent&projectId="+$('#projectIds').val()+"&isAJAX=true",
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);return;}
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				$("#epsDialogCloseReload").click();
			},
			error:function(msg){
				alert(msg);
			}
		});
		
	});

	
	$('#bailRecordClose').click(function(){
		$("#epsDialogCloseReload").click();
	});
	
});
