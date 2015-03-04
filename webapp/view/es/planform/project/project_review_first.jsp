<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_review_first.js"></script>
<div class="formLayout">
<form>
<div>
 <div class="partContainers operationLog"><h5 id="candidate"><span class="switch left11">推荐成交候选人情况</span></h5></div>
 <div id="candidateContent">
 <flex:flexgrid usepager="false" checkbox="false" id="reviewReportGrid" url="view/esdemo/data/projectmanager/review_report.txt" queryColumns=""  
			searchZone="reviewReportSearchZone" rp="10" title="" height="120" onSubmit="reviewReportGrid.before" onSuccess="reviewReportGrid.success">
		<flex:flexCol name="number" display="名次" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="name" display="成交候选人名单" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="acount" display="报价(元)" sortable="true" width="100" align="left"></flex:flexCol>
	</flex:flexgrid>
</div>
</div>

<div class="partContainers operationLog"><h5 id="invalidSubmission"><span class="switch left11">无效<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>（报价）情况</span></h5></div>
<div id="invalidSubmissionContent">
<flex:flexgrid usepager="false" checkbox="false" id="invllReviewReportGrid" url="view/esdemo/data/projectmanager/review_report_ill.txt" queryColumns=""  
			searchZone="invllReviewReportSearchZone" rp="10" title="" height="120" onSubmit="reviewReportGrid.before" onKeyRight="reviewReportGrid.keyRight" onSuccess="reviewReportGrid.success">
		<flex:flexCol name="name" display="投标人名称" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="account" display="报价(元)" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="couse" display="不通过原因" sortable="true" width="100" align="left"></flex:flexCol>
	</flex:flexgrid>
</div>

<ul style="padding-top:0px">
	<li>
		<div style="text-align:center">
			<button type="button" id="sub"><span>提交</span></button>
		</div>	
	</li>
</ul>
</form>
</div>
