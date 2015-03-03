<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/votetopic/vote_topic_list.js"></script>

<flex:flexgrid checkbox="true"
	id="voteTopicGrid" url="VoteTopicController.do?method=list" queryColumns=""  
	 rp="10" title="投票主题"  height="200" 
		onSubmit="voteTopicList.before" onSuccess="voteTopicList.success">
				<flex:flexCol name="title" display="voteTopicForm.title" sortable="true" width="200"align="left"></flex:flexCol>
				<flex:flexCol name="totalCount" display="voteTopicForm.totalCount" sortable="true" width="80"align="right"></flex:flexCol>
				<flex:flexCol name="startTime" display="voteTopicForm.startTime" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="endTime" display="voteTopicForm.endTime" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="multSelect" alias="multSelectCn" display="voteTopicForm.multSelect" sortable="true" width="80"align="center"></flex:flexCol>
				<flex:flexCol name="close" alias="closeCn" display="voteTopicForm.close" sortable="true" width="100"align="center"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="voteTopicList.add"></flex:flexBtn>	
	<flex:flexBtn name="globe.modify" bclass="modify" onpress="voteTopicList.update"></flex:flexBtn>	
	<flex:flexBtn name="globe.delete" bclass="delete" onpress="voteTopicList.remove"></flex:flexBtn>	
</flex:flexgrid>
