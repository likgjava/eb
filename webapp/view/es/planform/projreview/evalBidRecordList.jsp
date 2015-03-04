<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/evalBidRecordList.js"></script>
    <div id="evalBidRecordFormView">
	  <flex:flexgrid checkbox="true"
		id="evalBidRecordGrid" url="EvalBidRecordController.do?method=list&project.objId=${param.projectId}" queryColumns=""  
			rp="10" title="评标" width="800"  
			onSubmit="evalBidRecordList.before" onSuccess="evalBidRecordList.success">
					<flex:flexCol name="evalOpinion" display="evalBidRecordForm.evalOpinion" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="evalLinker" display="evalBidRecordForm.evalLinker" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="evalBidRecordList.add"></flex:flexBtn>
		<flex:flexBtn name="详情" bclass="look" onpress="evalBidRecordList.showDetail"></flex:flexBtn>		
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="evalBidRecordList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="evalBidRecordList.remove"></flex:flexBtn>	
	</flex:flexgrid>
	</div>

