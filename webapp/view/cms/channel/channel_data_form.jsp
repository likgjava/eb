<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_data_form.js"></script>
<div class="formLayout form2Pa">
	<form id="channelDataForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span>栏目数据</span></h4>
     	<ul>
	     	<li>
	     		<label for="channelDataForm.name"><spring:message code="channelDataForm.name"/></label>
					<input type="text" name="name" id="name" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.sortNo"><spring:message code="channelDataForm.sortNo"/></label>
					<input type="text" name="sortNo" id="sortNo" class="required" 
						    class="number"  />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.display"><spring:message code="channelDataForm.display"/></label>
					<input type="text" name="display" id="display" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.channel"><spring:message code="channelDataForm.channel"/></label>
					<input type="text" name="channel" id="channel" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.dataType"><spring:message code="channelDataForm.dataType"/></label>
					<input type="text" name="dataType" id="dataType" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.num"><spring:message code="channelDataForm.num"/></label>
					<input type="text" name="num" id="num" class="required" 
						    class="number"  />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.needLoad"><spring:message code="channelDataForm.needLoad"/></label>
					<input type="text" name="needLoad" id="needLoad" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.dataKey"><spring:message code="channelDataForm.dataKey"/></label>
					<input type="text" name="dataKey" id="dataKey" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.dataVal"><spring:message code="channelDataForm.dataVal"/></label>
					<input type="text" name="dataVal" id="dataVal" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.tmpType"><spring:message code="channelDataForm.tmpType"/></label>
					<input type="text" name="tmpType" id="tmpType" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelDataForm.sortType"><spring:message code="channelDataForm.sortType"/></label>
					<input type="text" name="sortType" id="sortType" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="channelDataSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="channelDataReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form>
</div>