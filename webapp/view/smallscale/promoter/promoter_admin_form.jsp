<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">         
<form id="promoterForm" method="post">
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	<ul>  
		<li class="fullLine">
			<label for="promoterName">采购大使姓名：</label>
			<input type="text" name="promoterName" id="promoterName" class="required" />
            <span class="eleRequired">*</span> 
			<input type="hidden" name="recordType" id="recordType" value="02"/>
			<input type="hidden" name="dealStatus" id="dealStatus" value="01"/>
		</li>    	
        <li class="fullLine">
			<label for="promoterCID">采购大使身份证号：</label>
			<input type="text" name="promoterCID" id="promoterCID" class="cnIdCard required" maxlength="18" />
            <span class="eleRequired">*</span> 
		</li>
		<li class="fullLine">
			<label>已推广单位名称：</label>
			<input type="text"  name="promoterUnitName" id="promoterUnitName" class="required" />
            <span class="eleRequired">*</span>      
		</li>          
		<li class="fullLine">
			<label>已推广单位联系人：</label>
			<input type="text"  name="promotedLinkName" id="promotedLinkName" class="required" />
			<span class="eleRequired">*</span>
		</li>  
		<li class="fullLine">
			<label>已推广单位电话：</label>
			<input type="text"  name="promotedLinkTel" id="promotedLinkTel" maxlength="16" class="cnPhone" />
		</li>
	</ul>
	<div class="conOperation">	   
		<button  id="promoterSave" type="button" ><span><spring:message code="globe.save"/></span></button>
		<button  id="promoterClose" type="button"><span><spring:message code="globe.close"/></span></button>
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
		if(confirm('确认提交？')){
			$.getJSON($('#initPath').val()+'/PromoterController.do?method=promoterSave', formToJsonObject('promoterForm'), function(json){
				if(json.failure)return;
				if(json.result=='faile'){
					alert('提交失败,请重新提交!');
					return;
				}
				$('#conBody').loadPage($('#initPath').val()+'/PromoterController.do?method=listbyAdmin');
	
				$('.epsDialogClose').trigger('click');
			});
		}
	});

	 //关闭弹出层
	$('#promoterClose').click(function(){
		$('.epsDialogClose').trigger('click');
	});

});
</script>