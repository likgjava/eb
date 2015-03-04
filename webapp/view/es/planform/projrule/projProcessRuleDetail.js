
var projProcessRuleForm={};

$(document).ready(function(){
	
	$('#projProcessRuleForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ProjProcessRuleController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('projProcessRuleForm', json.projProcessRule);
    	});
    }
    //显示项目规则
    if($("#docDemonStrate").text()=='0')
    {
    	$("#docDemonStrate").text('不进行论证');
    }
    else if($("#docDemonStrate").text()=='1')
    {
    	$("#docDemonStrate").text('进行论证');
    }
    
    if($("#localEsurvey").text()=='0')
    {
    	$("#localEsurvey").text('否');
    }
    else if($("#localEsurvey").text()=='1')
    {
    	$("#localEsurvey").text('是');
    }
    
    if($("#qualification").text()=='0')
    {
    	$("#qualification").text('否');
    }
    else if($("#qualification").text()=='1')
    {
    	$("#qualification").text('是');
    }
    
    if($("#multiphaseBid").text()=='0')
    {
    	$("#multiphaseBid").text('否');
    }
    else if($("#multiphaseBid").text()=='1')
    {
    	$("#multiphaseBid").text('是');
    }
    
    if($("#openBudget").text()=='0')
    {
    	$("#openBudget").text('否');
    }
    else if($("#openBudget").text()=='1')
    {
    	$("#openBudget").text('是');
    }
    
    if($("#bidSinglePack").text()=='0')
    {
    	$("#bidSinglePack").text('否');
    }
    else if($("#bidSinglePack").text()=='1')
    {
    	$("#bidSinglePack").text('是');
    }
    
    if($("#needEvaluateGroup").text()=='0')
    {
    	$("#needEvaluateGroup").text('否');
    }
    else if($("#needEvaluateGroup").text()=='1')
    {
    	$("#needEvaluateGroup").text('是');
    }
    
    if($("#needBuyErackPurchaseDoc").text()=='0')
    {
    	$("#needBuyErackPurchaseDoc").text('否');
    }
    else if($("#needBuyErackPurchaseDoc").text()=='1')
    {
    	$("#needBuyErackPurchaseDoc").text('是');
    }
    
    if($("#evaluateOnline").text()=='0')
    {
    	$("#evaluateOnline").text('否');
    }
    else if($("#evaluateOnline").text()=='1')
    {
    	$("#evaluateOnline").text('是');
    }
    
    if($("#publishPurchaseBulletin").text()=='0')
    {
    	$("#publishPurchaseBulletin").text('否');
    }
    else if($("#publishPurchaseBulletin").text()=='1')
    {
    	$("#publishPurchaseBulletin").text('是');
    }
    
    if($("#publishResultReview").text()=='0')
    {
    	$("#publishResultReview").text('否');
    }
    else if($("#publishResultReview").text()=='1')
    {
    	$("#publishResultReview").text('是');
    }
    
    if($("#publishResultBulletin").text()=='0')
    {
    	$("#publishResultBulletin").text('否');
    }
    else if($("#publishResultBulletin").text()=='1')
    {
    	$("#publishResultBulletin").text('是');
    }
    
    if($("#upLoadAfterSignUp").text()=='0')
    {
    	$("#upLoadAfterSignUp").text('否');
    }
    else if($("#upLoadAfterSignUp").text()=='1')
    {
    	$("#upLoadAfterSignUp").text('是');
    }
    
    if($("#evalFillScoreType").text()=='0')
    {
    	$("#evalFillScoreType").text('按百分');
    }
    else if($("#evalFillScoreType").text()=='1')
    {
    	$("#evalFillScoreType").text('按比重');
    }
    
    if($("#openPackType").text()=='0')
    {
    	$("#openPackType").text('按整体');
    }
    else if($("#openPackType").text()=='1')
    {
    	$("#openPackType").text('按包组');
    }
    
    if($("#openProcessType").text()=='0')
    {
    	$("#openProcessType").text('分阶段开标');
    }
    else if($("#openProcessType").text()=='1')
    {
    	$("#openProcessType").text('整体开标');
    }
    
    
    
    
    
    
    
    
    
	//杩斿洖
	$('#projProcessRuleReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ProjProcessRuleController.do");
	});
	//鎻愪氦
	

});
