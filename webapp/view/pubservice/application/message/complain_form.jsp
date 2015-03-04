<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/message/complain_form.js"></script>
<div class="formLayout">         
	<form id="complainForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="currentTabID" id="currentTabID" value="<c:out value="${param.currentTabID}"/>"/>
		    	   	
  	    <ul>
  	    <li class="fullLine">
			<span><label for="">标题：</label></span>
            <span>${complain.title}</span>
          </li>
     	 <li class="fullLine">
           <c:choose>
				      <c:when test='${param.currentTabID eq "tabs_tell"}'>
				       <span><label for="">投诉人：</label></span> 
				      
				      </c:when>
				      <c:otherwise>
				        <span><label for="">举报人：</label></span>				        
				      </c:otherwise>
			</c:choose>          
          
            <span>${complain.complainantName}</span>
          </li>
          
          <li class="fullLine">
           <c:choose>
				      <c:when test='${param.currentTabID eq "tabs_tell"}'>
				       <span><label for="">被投诉人机构：</label></span> 
				     
				      </c:when>
				      <c:otherwise>
				        <span><label for="">被举报人机构：</label></span>
				       
				      </c:otherwise>
			</c:choose>         
            <span>${complain.beCompanyName}</span>
          </li>
          
           <li class="fullLine">
           <c:choose>
				      <c:when test='${param.currentTabID eq "tabs_tell"}'>
				       <span><label for="">被投诉人姓名：</label></span> 
				     
				      </c:when>
				      <c:otherwise>
				        <span><label for="">被举报人姓名：</label></span>
				       
				      </c:otherwise>
			</c:choose>         
            <span>${complain.beComplainName}</span>
          </li>
          
          <li class="fullLine">
			<span><label for="">回复信箱：</label></span>
            <span>${complain.replyEmail}</span>
          </li>
         
          
          <li class="fullLine">
			<span><label for="attFileDiv">附件：</label></span>
            <div id="attFileDiv" class="uploadFile">${complain.attFile}</div>            
          </li>
          
          <li class="fullLine">
                    <label>内容：</label>
                    <span>${complain.content}</span>                    
          </li>
          
          <li class="formTextarea">
                    <label>处理结果：</label>
                    <textarea name="result"  id="result"  class="required"></textarea>
                    <span class="eleRequired">*</span>
          </li>

		</ul>
	    <div class="conOperation">
	        <button id="complainSave" type="button" ><span>处理</span></button>
	      
	        <button  id="complainReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
	       
	    </div>
	</form>
</div>