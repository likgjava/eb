
var signUprecordForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		var signType=json.signUprecord.signType;
    		if(signType=='00'){
    			signType="招标中心录入";
    		}else if(signType='01'){
    			signType="网上报名";
    		}else if(signType='02'){
    			signType="网下报名";
    		}
    		json.signUprecord.signType=signType;
    		json2Object('signUprecordDetailForm',json.signUprecord);
    	});
    }
	//返回
	$('#signUprecordReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/SignUprecordController.do');
	});
});