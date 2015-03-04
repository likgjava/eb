<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">         
	<form id="adviseReplyForm" method="post">
	    <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="currentTabID" id="currentTabID" value="<c:out value="${param.currentTabID}"/>"/>
		 <ul>
           <li class="fullLine">
                    <label>建议意见内容：</label>
                    <span>${advise.content}</span>
          </li> 
          
          <li class="fullLine">
			<span><label for="">电话：</label></span>
            <span>${advise.contactTel}</span>
          </li>    
         
          <li class="fullLine">
			<span><label for="">信箱：</label></span>
            <span>${advise.contactEmail}</span>
          </li>  
          
          <li class="formTextarea">
                    <label>回复内容：</label>
                    <textarea name="replyContent" class="required" id="replyContent"  >${advise.replyContent}</textarea>
                    <input type="hidden" name="replyStatus" id="replyStatus" value="1"/>
                    <span class="eleRequired">*</span>
          </li>         
       
  		</ul>
		
		<div class="conOperation">
	   
         <button  id="adviseReplySave" type="button" ><span>回复</span></button>
         <button id="adviseReplyReturn" type="button" ><span><spring:message code="globe.return"/></span></button>	      
   
	    </div>
	
	   
	</form>
</div>

<script>

var adviseForm={};

$(document).ready(function(){
	

    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/AdviseController.do?method=toReply',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('adviseReplyForm', json.advise);    		
    	});
    }    
    
	//返回
	$('#adviseReplyReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/AdviseController.do?method=myList&isAdmin=true&currentTabID="+$('#currentTabID').val());
	});
	
	//回复提交
	$('#adviseReplySave').click(function(){
		if(!$('#adviseReplyForm').valid()){alert('请正确填写表单!');return;}			
		$.getJSON($('#initPath').val()+'/AdviseController.do?method=saveReply', formToJsonObject('adviseReplyForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/AdviseController.do?method=myList&isAdmin=true&currentTabID='+$('#currentTabID').val());
		});
	});

});

</script>