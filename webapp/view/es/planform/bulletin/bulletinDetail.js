
var bulletinForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/BulletinController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		//公告类型
    		var bullType=json.bulletin.bullType;
    		if(bullType=="00"){
    			bullType="采购预告";
	 		}else if(bullType=="01"){
	 			bullType="采购公告";
	 		}else if(bullType=="02"){
	 			bullType="变更公告";
	 		}else if(bullType=="03"){
	 			bullType="资格预审公告";
	 		}else if(bullType=="04"){
	 			bullType="资格预审结果公示";
	 		}else if(bullType=="05"){
	 			bullType="结果公示";
	 		}else if(bullType=="06"){
	 			bullType="结果公告";
	 		}else if(bullType=="07"){
	 			bullType="暂停公告";
	 		}else if(bullType=="08"){
	 			bullType="失败公告";
	 		}
    		
    		//采购方式
    		var buyMethod=json.bulletin.buyMethod;
    		if(buyMethod=="00"){
    			buyMethod="公开招标";
	 		}else if(buyMethod=="01"){
	 			buyMethod="邀请招标";
	 		}else if(buyMethod=="02"){
	 			buyMethod="竞争性谈判";
	 		}else if(buyMethod=="03"){
	 			buyMethod="询价";
	 		}else if(buyMethod=="04"){
	 			buyMethod="单一来源";
	 		}
    		json.bulletin.bullType=bullType;
    		json.bulletin.buyMethod=buyMethod;
    		json2Object('bulletinDetailForm',json.bulletin);
    	});
    }
	//返回
	$('#bulletinReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do');
	});
});