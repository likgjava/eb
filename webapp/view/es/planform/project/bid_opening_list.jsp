<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/bid_opening_list.js"></script>
<div class="formLayout">
	<div class="partContainers operationLog"><h5 id="projectDetail"><span class="switch left11">项目信息</span></h5></div>
	<div id="projectDetailContent">
		<form id="projMessageForm" class="form2Pa">
		<ul style="padding-top:0px">
			<li>
			   <label>招标名称:</label>
				<span id="number"></span>
			</li>
			<li>
			   <label><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>号:</label>
				<span id="number"></span>
			</li>
			<li>
				<label><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称:</label>
				<span id="packname"></span>
			</li>
			<li>
				<label><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>时间:</label>
				<span id="opentime"></span>
			</li>
		</ul>
		</form>
	</div>
	<div class="partContainers operationLog"><h5 id="group"><span class="switch left11"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>小组列表</span></h5></div>
	<div id="groupContent">
		<flex:flexgrid id="openingTendersGroupGrid" url="view/esdemo/data/projectmanager/opening_tenders_group_list.txt" queryColumns=""  
			 rp="10" height="120" onSubmit="bidOpeningList.before" onSuccess="bidOpeningList.success">
			<flex:flexCol name="name" display="开标人姓名" sortable="true" width="100" align="left"></flex:flexCol>
			<flex:flexCol name="account" display="开标人账号" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="phone" display="联系电话" sortable="true" width="100" align="left"></flex:flexCol>
			<flex:flexCol name="certificate" display="相关证书" sortable="true" width="200" align="left"></flex:flexCol>	
		</flex:flexgrid>
	</div>
	<div class="partContainers operationLog"><h5 id="tendersRecord"><span class="switch"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>记录</span></h5></div>
	<div id="tendersRecordContent">
		<flex:flexgrid id="tendersRecordGrid" url="view/esdemo/data/projectmanager/tender_record.txt" queryColumns=""  
			 rp="10" height="120" onSubmit="tendersRecord.before" onSuccess="tendersRecord.success">
			<flex:flexCol name="name" display="投标单位名称" sortable="true" width="100" align="left"></flex:flexCol>
			<flex:flexCol name="account" display="保证金" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="count" display="投标总价(元)" sortable="true" width="100" align="left"></flex:flexCol>
			<flex:flexCol name="endtime" display="投标结束时间" sortable="true" width="200" align="left"></flex:flexCol>	
		</flex:flexgrid>
	</div>	
</div>
