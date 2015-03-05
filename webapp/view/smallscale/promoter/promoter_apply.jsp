<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<h1>申请成为采购大使</h1>
<div>         
	<form id="promoterForm" method="post">
	<ul class="liuchen">
		<li><h4 class="firstH4">申请成为采购大使</h4><p>可以获得推广积分</p></li>
	</ul>
	<ul>           
		<li style="margin-left: 100px;">
			<label for="idCard" style="width: 80px;">身份证号码：</label>
			<input name="idCard" id="idCard" value="${user.emp.idCard}" class="required cnIdCard" />
			<span class="eleRequired">*</span>
			<br/>
			<span class="eleRequired">身份证号码是您推广积分的重要依据</span>
		</li>
	</ul>
  			
	<div class="conOperation">	   
		<button  id="promoterSave" type="button" ><span><spring:message code="globe.submit"/></span></button>         
	</div>		   
	</form>
</div>

<script>

var promoterForm={};

$(document).ready(function(){
	//$.validator.addMethod("idCardUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该身份证已注册');
	
	//$("#promoterForm").validate({
		//rules:{		  
		//	idCard:{idCardUnique:"idCard"}
		//}
	//});	
	
	//提交
	$('#promoterSave').click(function(){	
		if(!$('#promoterForm').valid()){alert('请填写有效身份证!');return;}
		$.getJSON($('#initPath').val()+'/PromoterController.do?method=toRegistrationPromoter', formToJsonObject('promoterForm'), function(json){
		
			if(json.failure)return;
			if(json.result=='faile'){
				alert('提交失败!');
				return;
			}
					
			$('#sysContent').loadPage($('#initPath').val()+'/PromoterController.do?method=listbyUser');			
		});
	});
});
</script>