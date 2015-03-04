<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">         
	<form id="complainForm" method="post">
		<input type="hidden" name="currentTabID" id="currentTabID" value="<c:out value="${param.currentTabID}"/>"/>
		<input type="hidden" name="complainType" id="complainType" value="<c:out value="${param.type}"/>"/>	
     	 <ul>     	 
     	  <li class="fullLine">
           <c:choose>
				      <c:when test='${param.type eq "tell"}'>
				       <span><label for="">被投诉机构：</label></span> 
				       <input type="hidden" name="type" id="type" value="00"/>
				      </c:when>
				      <c:otherwise>
				        <span><label for="">被举报机构：</label></span>
				        <input type="hidden" name="type" id="type" value="01"/>
				      </c:otherwise>
			</c:choose>         
            <span><input name="beCompanyName" id="beCompanyName" readonly="readonly"  value='<%=(-1!= request.getHeader("USER-AGENT").indexOf("MSIE"))?new String(request.getParameter("beCompanyName").getBytes("ISO-8859-1"),"gbk"):new String(request.getParameter("beCompanyName").getBytes("ISO-8859-1"),"UTF-8")%>' /></span>
            <span><input name="beCompanyId" type="hidden"  id="beCompanyId"  value="<c:out value="${param.beCompanyId}"/>"/></span>
            <span><input name="projectId" type="hidden"  id="projectId"  value="<c:out value="${param.projectId}"/>"/></span>
         
          </li>
          
           <li class="fullLine">
           <c:choose>
				      <c:when test='${param.type eq "tell"}'>
				       <span><label for="">被投诉人姓名：</label></span> 				  
				      </c:when>
				      <c:otherwise>
				        <span><label for="">被举报人姓名：</label></span>				        
				      </c:otherwise>
			</c:choose> 
			
			<c:choose>
				 <c:when test='${beComplain != null}'>
				        <span><input name="beComplainName" id="beComplainName" readonly="readonly"  value='${beComplainName}'/></span>
            			<span><input type="hidden" name="beComplain" id="beComplain"  value="${beComplain}" /></span> 				  
				  </c:when>
				  <c:otherwise>
				        <span><input name="beComplainName" id="beComplainName" readonly="readonly"  value='<%=(-1!= request.getHeader("USER-AGENT").indexOf("MSIE"))?new String(request.getParameter("beComplainName").getBytes("ISO-8859-1"),"gbk"):new String(request.getParameter("beComplainName").getBytes("ISO-8859-1"),"UTF-8")%>'/></span>
              			<span><input type="hidden" name="beComplain" id="beComplain"  value="<c:out value="${param.beComplain}"/>" /></span>			        
				  </c:otherwise>
			</c:choose>          
          </li>
     	 
     	 
     	 <li class="fullLine">
			<span><label for="">标题：</label></span>
            <span><input name="title" id="title" class="required" size='50' /></span>
            <span class="eleRequired">*</span>
          </li>   
          
 
          
           <li class="formTextarea">
                    <label>内容：</label>
                    <textarea name="content" class="required" id="content"  ></textarea>
                    <span class="eleRequired">*</span>
          </li>     
         
          <li class="fullLine">
			<span><label for="">回复信箱：</label></span>
            <span><input name="replyEmail" id="replyEmail" class='email' value="<c:out value="${replyEmail}"/>" /></span>
          </li>
          
           <li class="fullLine">
			<span><label for="">附件：</label></span>
			<div id="attFileDiv" class="uploadFile"></div>
          </li>
  		</ul>
     	
	
		
		<div class="conOperation">
	   
         <button  id="complainSave" type="button" ><span><spring:message code="globe.save"/></span></button>
         <button id="complainReturn" type="button" ><span><spring:message code="globe.close"/></span></button>	 
            
   
	    </div>
	
	   
	</form>
</div>
<script>
var complainForm={};

$(document).ready(function(){
	$('#complainForm').validate();
   
	//加载附件
	$("#attFileDiv").loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attFile');

   
	//关闭
	$('#complainReturn').click(function(){
		//$('#conBody').loadPage($('#initPath').val()+"/ComplainController.do?currentTabID=" + $('#currentTabID').val());
		$('.epsDialogClose').trigger('click');
	});
	//提交
	$('#complainSave').click(function(){
		//if(!$('#complainForm').validate()) return false;
		if(!$('#complainForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ComplainController.do?method=mySave', formToJsonObject('complainForm'), function(json){
			
			if(json.result)alert(json.result);if(json.failure)return;
			alert("操作成功!");
			if($("#complainType").val()=="tell"){//投诉
				$('#conBody').loadPage($('#initPath').val()+'/ComplainController.do?currentTabID=tabs_tell' );
			}
			else {//举报
				$('#conBody').loadPage($('#initPath').val()+'/ComplainController.do?currentTabID=tabs_complain' );
			}
			$('.epsDialogClose').trigger('click');
			
		});
	});

});
</script>