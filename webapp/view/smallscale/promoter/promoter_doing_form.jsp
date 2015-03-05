<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
<form id="messageForm" method="post">
	<input type="hidden" name="fromWeb" id="fromWeb" value="${fromWeb}" />
	
	<h4><span>填写要推荐的采购人信息</span></h4>
	<ul>
		<li class="fullLine">
			<label for="">采购单位名称：</label>
			<input name="promoterUnitName" id="promoterUnitName" class="required" />
            <span class="eleRequired">*</span>
            <input type="hidden" name="recordType" id="recordType" value="01" />
		</li>          
		<li class="fullLine">
			<label for="promoterUnitCode">采购意向：</label>
			<input name="promoterUnitCode" id="promoterUnitCode" size="40" />
		</li> 
		<li class="fullLine">
			<label for="promotedLinkName">采购单位联系人：</label>
			<input name="promotedLinkName" id="promotedLinkName" class="required" />
            <span class="eleRequired">*</span>
		</li>
		<li class="fullLine">
			<label for="promotedLinkTel">联系电话：</label>
			<input name="promotedLinkTel" id="promotedLinkTel"  maxlength="30" class="cnPhone eleRight required" />
            <span class="eleRequired">*</span>	
		</li> 	
		<li class="fullLine">			
			<label for="email">联系人邮箱：</label>
			<input name="email" id="email" class="required email" />
			<span class="eleRequired">*</span>
		</li>						
	</ul>
	<div class="conOperation">
		<button id="submitBtn" type="button"  class="largeBtn"><span>提交</span></button>
	</div>
</form>
</div>

<script>
var messageAdd = {};

//提交
messageAdd.saveMessage = function(messageJson){
	$.getJSON($('#initPath').val()+'/PromoterController.do?method=savePromoter', formToJsonObject('messageForm'), function(json){
		if(json.failure){alert(json.result); return};
		if(json.isNew=='false'){
			alert("该企业你已经推荐过！");
		}		
		if($("#fromWeb").val()=="yes"){//由我要推广直接录入
			$('#sysContent').loadPage($('#initPath').val()+'/PromoterController.do?method=listbyUser&userType=user&fromWeb=yes');
		}else{				
			$('#conBody').loadPage($('#initPath').val()+'/PromoterController.do?method=listbyUser&userType=user');
			$('.epsDialogClose').trigger('click');
		}		
	});	
}

$(document).ready(function(){
	//唯一性验证
	$.validator.addMethod("promoterUnitNameUnique",function(value,element,param){return uniqueHandler("Promoter",param,value,"");},'该该企业已被推广！');
	$("#messageForm").validate({
		rules:{		  
			promoterUnitName:{promoterUnitNameUnique:"promoterUnitName"}
		}
	});	
	
    //提交
    $('#submitBtn').click(function(){
		if(!$('#messageForm').valid()){alert('请正确填写表单!');return;}
		if(confirm('确定提交？')){
			var messageJson = formToJsonObject("messageForm");
			messageAdd.saveMessage(messageJson);
		}
    });  
})
</script>