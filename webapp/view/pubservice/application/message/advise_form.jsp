<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<div class="formLayout">
	<input type="hidden" id="isDialog" value="<c:out value="${param.isDialog}"/>"/>      
	<form id="adviseForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		 <ul>          
           <li class="formTextarea">
                    <label>建议意见：</label>
                    <textarea name="content" class="required" id="content"  ></textarea>
                    <span class="eleRequired">*</span>
          </li>   
          
          <li class="fullLine">
			<span><label for="">联系电话：</label></span>
            <span><input name="contactTel" id="contactTel" value="${mobile}" /></span>
          </li>  
         
          <li class="fullLine">
			<span><label for="">信箱：</label></span>
            <span><input name="contactEmail" id="contactEmail" class="required email" value="${email}"/></span>
            <span class="eleRequired">*</span>
          </li>         
  		</ul> 		
		<div class="conOperation">	   
         <button  id="adviseSave" type="button" ><span><spring:message code="globe.save"/></span></button>
         <button  id="adviseReturn" type="button" ><span><spring:message code="globe.return"/></span></button>	 
         <button  id="adviseSubmit" type="button" class="hidden"><span><spring:message code="globe.submit"/></span></button>
         <button  id="dialogReturn" type="button" class="hidden"><span><spring:message code="globe.return"/></span></button>
	    </div>	
	   
	</form>
</div>

<script>

var adviseForm={};

$(document).ready(function(){
	$('#adviseForm').validate();

	//判断是否是弹出框
	var isDialog = $('#isDialog').val();
	if('true' == isDialog){
		$('#adviseSubmit').removeClass();
		$('#dialogReturn').removeClass();
		$('#adviseSave').addClass("hidden");
		$('#adviseReturn').addClass("hidden");
	}	

    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/AdviseController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('adviseForm', json.advise);    		
    	});
    }    
    
	//返回
	$('#adviseReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/AdviseController.do?method=myList");
	});
	
	//保存
	$('#adviseSave').click(function(){	
		if(!$('#adviseForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/AdviseController.do?method=save', formToJsonObject('adviseForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/AdviseController.do?method=myList');
		});
	});

	//dialog提交
	$('#adviseSubmit').click(function(){	
		if(!$('#adviseForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/AdviseController.do?method=save', formToJsonObject('adviseForm'), function(json){
			if(json.result)alert(json.result);
			if(json.failure)return;
			alert("建议提交成功！");
			$('.epsDialogClose').trigger('click');
		});
	});
	//dialog返回
	$('#dialogReturn').click(function(){
		$('.epsDialogClose').trigger('click');
	});

});

</script>