<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">         
	<form id="adviseReplyForm" method="post">
	    <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="currentTabID" id="currentTabID" value="<c:out value="${param.currentTabID}"/>"/>
		 <ul>
           <li class="fullLine">
                    <label>建议意见：</label>
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
          
          <li class="fullLine">
                    <label>回复内容：</label>
                    <span>${advise.replyContent}</span>
          </li>         
       
  		</ul>
		
		<div class="conOperation">	   
       
         <button id="adviseReplyReturn" type="button" ><span>关闭</span></button>	      
   
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
    
     //关闭弹出层
	$('#adviseReplyReturn').click(function(){
		
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
		//$('#conBody').loadPage($('#initPath').val()+"/AdviseController.do?method=myList");
	});
	
	

});

</script>