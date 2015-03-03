<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_model_form.js"></script>
	<input type="hidden" name="isCopy" id="isCopy" value="<c:out value="${param.isCopy}"/>"/>
	<form id="channelModelForm" method="post">
<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="channelModelId" value="<c:out value="${param.objId}"/>"/>
     	<h5><span id="modelH5Title">栏目模型</span></h5>
     	<ul>
	     	<li>
	     		<label for="channelModelForm.name"><spring:message code="channelModelForm.name"/></label>
					<input type="text" name="name" id="name" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelModelForm.shortName"><spring:message code="channelModelForm.shortName"/></label>
					<input type="text" name="shortName" id="shortName" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelModelForm.channelTplPrefix"><spring:message code="channelModelForm.channelTplPrefix"/></label>
					<input type="text" name="channelTplPrefix" id="channelTplPrefix" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelModelForm.contentTplPrefix"><spring:message code="channelModelForm.contentTplPrefix"/></label>
					<input type="text" name="contentTplPrefix" id="contentTplPrefix" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelModelForm.sort"><spring:message code="channelModelForm.sort"/></label>
					<input type="text" name="sort" id="sort" class="required" 
						    class="number"  />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="channelModelForm.display"><spring:message code="channelModelForm.display"/></label>
					<input type="checkbox" name="display" id="display" value="true"  />
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="channelModelSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="channelModelReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="channelModelReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
</div>
	<div class="formLayout form3Pa detail">
		<div class="treeEditNav">
			<ul>
				<li id="addChannelModelItemRow" class="add"><a href="javascript:void(0)"><span>新增</span></a></li>
			</ul>
		</div>
     	<h5><span id="modelItemH5Title">模型表单数据</span></h5>
     	<ul id="ChannelDataList">
		</ul>
	</div>
</form>