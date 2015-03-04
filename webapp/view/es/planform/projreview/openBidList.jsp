<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/openBidList.js"></script>
   <div id="bidFormView">
	<input type="hidden" id="projectId" name="projectId" value="${param.projectId}">
	<flex:flexgrid checkbox="true"
		id="bidGrid" url="OpenBidController.do?method=getOpenBidRecord&projectId=${param.projectId}" queryColumns="bidId"  
			 rp="10" title="开标" width="800"  
			onSubmit="bidList.before" onSuccess="bidList.success">
					<flex:flexCol name="sellerName" display="投标单位" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="openBRStatus" display="开标状态" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="查看详细" bclass="look" onpress="bidList.showDetail"></flex:flexBtn>	
		<flex:flexBtn name="开标" bclass="add" onpress="bidList.openBid"></flex:flexBtn>	
	</flex:flexgrid>
</div>
