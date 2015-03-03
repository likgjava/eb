<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_form.js"></script>
<div class="formLayout form2Pa">
	<form id="channelForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span>栏目</span></h4>
     	<ul>
	     	<li>
	     		<label for="channelForm.parent"><spring:message code="channelForm.parent"/></label>
					<input type="text" name="parent" id="parent" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.name"><spring:message code="channelForm.name"/></label>
					<input type="text" name="name" id="name" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.chnlCode"><spring:message code="channelForm.chnlCode"/></label>
					<input type="text" name="chnlCode" id="chnlCode" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.chnlMemo"><spring:message code="channelForm.chnlMemo"/></label>
					<input type="text" name="chnlMemo" id="chnlMemo" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.sort"><spring:message code="channelForm.sort"/></label>
					<input type="text" name="sort" id="sort" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.createTime"><spring:message code="channelForm.createTime"/></label>
					<input type="text" name="createTime" id="createTime" class="required" 
									readonly="readonly"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.createUser"><spring:message code="channelForm.createUser"/></label>
					<input type="text" name="createUser" id="createUser" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.modifyTime"><spring:message code="channelForm.modifyTime"/></label>
					<input type="text" name="modifyTime" id="modifyTime" class="required" 
									readonly="readonly"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.modifyUser"><spring:message code="channelForm.modifyUser"/></label>
					<input type="text" name="modifyUser" id="modifyUser" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.totalCount"><spring:message code="channelForm.totalCount"/></label>
					<input type="text" name="totalCount" id="totalCount" class="required" 
						    class="number"  />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.visitCount"><spring:message code="channelForm.visitCount"/></label>
					<input type="text" name="visitCount" id="visitCount" class="required" 
						    class="number"  />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.chnlTemplate"><spring:message code="channelForm.chnlTemplate"/></label>
					<input type="text" name="chnlTemplate" id="chnlTemplate" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.contentTemplate"><spring:message code="channelForm.contentTemplate"/></label>
					<input type="text" name="contentTemplate" id="contentTemplate" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.channelModel"><spring:message code="channelForm.channelModel"/></label>
					<input type="text" name="channelModel" id="channelModel" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.filePage"><spring:message code="channelForm.filePage"/></label>
					<input type="text" name="filePage" id="filePage" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.url"><spring:message code="channelForm.url"/></label>
					<input type="text" name="url" id="url" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.outUrl"><spring:message code="channelForm.outUrl"/></label>
					<input type="text" name="outUrl" id="outUrl" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.pageSize"><spring:message code="channelForm.pageSize"/></label>
					<input type="text" name="pageSize" id="pageSize" class="required" 
						    class="number"  />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelForm.sortType"><spring:message code="channelForm.sortType"/></label>
					<input type="text" name="sortType" id="sortType" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="channelSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="channelReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form>
</div>