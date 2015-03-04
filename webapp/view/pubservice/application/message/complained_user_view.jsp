<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script>
$(document).ready(function(){
	//加载附件
	$("#attFileDiv").loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attFile&isSelect=yes&attachRelaId='+$("#attFileDiv").text());

	//返回
	$('#complainReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ComplainController.do?currentTabID=" + $('#currentTabID').val());
	});
})
</script>
<div class="formZone">         
	<form id="complainForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="currentTabID" id="currentTabID" value="<c:out value="${param.currentTabID}"/>"/>
		
		
     	<div class="formLayout form2Pa">   
     	<ul>  	
     	 <li class="fullLine">
			<label for="">标题：</label>
            <span>${complain.title}</span>
          </li>     
          
           <li class="fullLine">
                    <label>内容：</label>
                    <span>${complain.content}</span>
                                      
          </li>
          <li class="fullLine">
                    <label>处理结果：</label>
                    <span>${complain.result}</span>
                                      
          </li>
          
          <li class="fullLine">
			<span><label for="">附件：</label></span>
			<div id="attFileDiv" class="uploadFile">${complain.attFile}</div>                    
          </li> 
     	</ul>
		</div>
		
		<div class="conOperation">        
         <button id="complainReturn" type="button" ><span><spring:message code="globe.return"/></span></button>   
	    </div>
	
	   
	</form>
</div>