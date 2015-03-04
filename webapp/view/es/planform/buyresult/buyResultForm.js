
var buyResultForm={};

$(document).ready(function(){
	
	if($('#buyResultId').val()=='')
	{
		$("#buyWinnerListView").hide();
	}
	else
	{
		var buyResultId =  $('#buyResultId').val();
		$("#buyWinnerListView").empty().loadPage($('#initPath').val()+'/view/es/planform/buyresult/buyWinnerList.jsp?buyResultId='+buyResultId);
	}

	$('#buyResultForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#buyResultId').val()!=''){
    	$.getJSON($('#initPath').val()+'/BuyResultController.do?method=createOrUpdate',{objId:$('#buyResultId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('buyResultForm', json.buyResult);
    	});
    }
	//返回
	$('#buyResultReturn').click(function(){
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/buyresult/buyResultList.jsp?projectId='+$("#projectId").val());	
	});
	//提交
	$('#buyResultSave').click(function(){
		if(!$('#buyResultForm').valid()){alert('请正确填写表单!');return;}
		//alert(obj2str(formToJsonObject('buyResultForm')));
		$.getJSON($('#initPath').val()+'/BuyResultController.do?method=saveOrUpdateBuyResult', formToJsonObject('buyResultForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#buyResultId').val(json.buyResultId);
			$("#buyWinnerListView").empty().loadPage($('#initPath').val()+'/view/es/planform/buyresult/buyWinnerList.jsp?buyResultId='+json.buyResultId);
			$("#buyWinnerListView").show();
		});
	});

});
