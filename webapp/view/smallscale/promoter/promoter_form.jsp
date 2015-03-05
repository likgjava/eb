<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">         
	<form id="promoterForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		 <ul>         
          <li class="fullLine">
			<span><label for="">已推广单位名称：</label></span>
            <span><input name="promoterUnitName" id="promoterUnitName" class="required" />
            <input type="hidden" name="recordType" id="recordType"  value="01" /></span>
            <span class="eleRequired">*</span>
          </li>          
          <li class="fullLine">
			<span><label for="">机构代码：</label></span>
            <span><input name="promoterUnitCode" id="promoterUnitCode" /></span>
          </li> 
          <li class="fullLine">
			<span><label for="">联系人：</label></span>
            <span><input name="promotedLinkName" id="promotedLinkName" class="required" /></span>
            <span class="eleRequired">*</span>
          </li>  
          <li class="fullLine">
			<span><label for="">联系电话：</label></span>
            <span><input name="promotedLinkTel" id="promotedLinkTel"  maxlength="16" class="digits"/></span>
          </li> 
          
         
          
                   
  		</ul> 		
		<div class="conOperation">	   
         <button  id="promoterSave" type="button" ><span><spring:message code="globe.save"/></span></button>
         <button  id="promoterClose" type="button" ><span><spring:message code="globe.close"/></span></button>	 
         
	    </div>	
	   
	</form>
</div>

<script>

var promoterForm={};

$(document).ready(function(){
	$('#promoterForm').validate();

    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/PromoterController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('promoterForm', json.advise);    		
    	});
    }    
	
	
	//提交
	$('#promoterSave').click(function(){	
		if(!$('#promoterForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/PromoterController.do?method=promoterSave', formToJsonObject('promoterForm'), function(json){
			if(json.failure)return;
			if(json.result=='faile'){
				alert('提交失败,请重新提交!');
				return;
			}
			
			alert("生成对方确认的验证码为：" + json.result);
			$('#conBody').loadPage($('#initPath').val()+'/PromoterController.do?method=listbyUser');

			$('.epsDialogClose').trigger('click');
		});
	});

	 //关闭弹出层
	$('#promoterClose').click(function(){
		$('.epsDialogClose').trigger('click');
		
	});

});



</script>