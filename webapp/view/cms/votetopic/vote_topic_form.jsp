<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/votetopic/vote_topic_form.js"></script>
<div class="formLayout form2Pa">
	<div class="treeEditNav">
		<ul>
			<li id="addVoteItem" class="add"><a href="javascript:void(0)"><span>添加投票项</span></a></li>
			<li id="deleteVoteItem" class="del"><a href="javascript:void(0)"><span>删除</span></a></li>
		</ul>
	</div>
	<form id="voteTopicForm" method="post">
		<input type="hidden" name="objId" id="voteTopicId" value="<c:out value="${param.objId}"/>"/>
     	<h5><span>投票主题</span></h5>
     	<ul id="voteItemListForm">
	     	<li class="fullLine">
	     		<label for="voteTopicForm.title"><spring:message code="voteTopicForm.title"/></label>
					<input type="text" name="title" id="title" class="required" size="50"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="voteTopicForm.multSelect"><spring:message code="voteTopicForm.multSelect"/></label>
					多选<input type="radio" name="multSelect" id="" value="true">
					单选<input type="radio" name="multSelect" id="" value="false">
    	   			<span class="eleRequired"></span>
    	    </li>
	     	<li>
	     		<label for="voteTopicForm.close"><spring:message code="voteTopicForm.close"/></label>
					关闭<input type="radio" name="close" id="" value="true">
					打开<input type="radio" name="close" id="" value="false">
    	   			<span class="eleRequired"></span>
    	    </li>
	     	<li>
	     		<label for="voteTopicForm.startTime"><spring:message code="voteTopicForm.startTime"/></label>
					<input type="text" name="startTime" id="startTime" class="required" 
									readonly="readonly"
						      />
    	   			<span class="eleRequired"></span>
    	    </li>
	     	<li>
	     		<label for="voteTopicForm.endTime"><spring:message code="voteTopicForm.endTime"/></label>
					<input type="text" name="endTime" id="endTime" class="required" 
									readonly="readonly"
						      />
    	   			<span class="eleRequired"></span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="voteTopicSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="voteTopicReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form>
</div>