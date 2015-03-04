
var buyResultForm={};
$(document).ready(function(){
	if($('#buyResultId').val()=='')
	{
		$("#buyWinnerListView").hide();
	}

	if($('#buyResultId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/BuyResultController.do?method=createOrUpdate',{objId:$('#buyResultId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('buyResultDetailForm',json.buyResult);
    	});
    }
	//返回
	$('#buyResultReturn').click(function(){
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/buyresult/buyResultList.jsp?projectId='+$("#projectId").val());	
	});
});