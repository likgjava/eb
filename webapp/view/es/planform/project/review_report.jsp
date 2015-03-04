<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/review_report.js"></script>
<div class="formLayout">
<form>

 <div class="partContainers operationLog"><h5 id="invalidSubmission"><span class="switch left11">无效<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>（报价）情况</span></h5></div>
 <div id="invalidSubmissionContent">
 <flex:flexgrid usepager="false" checkbox="false" id="invllReviewReportGrid" url="view/esdemo/data/projectmanager/review_report_ill.txt" queryColumns=""  
			searchZone="invllReviewReportSearchZone" rp="10" title="" height="120" onSubmit="reviewReportGrid.before" onKeyRight="reviewReportGrid.keyRight" onSuccess="reviewReportGrid.success">
		<flex:flexCol name="name" display="投标人名称" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="account" display="报价(元)" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="couse" display="不通过原因" sortable="true" width="100" align="left"></flex:flexCol>
	</flex:flexgrid>
</div>

 <div class="partContainers operationLog"><h5 id="attach"><span class="switch left11">关联附件</span></h5></div>
 <div id="attachContent">
  <ul style="padding-top:0px">
		<li>
			<label>上传文件</label>
			 <input type="file"><button type="button" id="upload"><span>上传</span></button>
		</li>
	</ul>
</div>

<div class="partContainers operationLog"><h5 id="reviewReport"><span class="switch left11">评审情况</span></h5></div>
 <div id="reviewReportContent">
 	<ul style="padding-top:0px">
		<li class="formTextarea">
			<label>评审情况</label>
			<textarea name="textarea1" rows="6" cols="80"></textarea>
		</li>
		<li class="formTextarea">
			<label>特殊情况说明</label>
			<textarea name="textarea1"  rows="6" cols="80"></textarea>
		</li>
	</ul>
</div>

 <div class="partContainers operationLog"><h5 id="supply"><span class="switch left11">投标单位名单</span></h5></div>
 <div id="supplyContent">
 <flex:flexgrid usepager="false" checkbox="false" id="supplierNameGrid" url="view/esdemo/data/projectmanager/review_report_suppliers.txt" queryColumns=""  
			searchZone="supplierNameSearchZone" rp="10" title="" height="120" onSubmit="reviewReportGrid.before" onKeyRight="reviewReportGrid.keyRight" onSuccess="reviewReportGrid.success">
		<flex:flexCol name="name" display="单位名称" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="isbuyer" display="已购买招标文件" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="couse" display="已投标（报价）" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="linephone" display="联系人" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="phone" display="联系电话" sortable="true" width="100" align="left"></flex:flexCol>
	</flex:flexgrid>
</div>

 <div class="partContainers operationLog"><h5 id="purchase"><span class="switch left11">采购代表人信息</span></h5></div>
 <div id="purchaseContent">
 <flex:flexgrid usepager="false" checkbox="false" id="buyerGrid" url="view/esdemo/data/projectmanager/review_report_buyer.txt" queryColumns=""  
			searchZone="buyerSearchZone" rp="10" title="" height="120" onSubmit="reviewReportGrid.before" onKeyRight="reviewReportGrid.keyRight" onSuccess="reviewReportGrid.success">
		<flex:flexCol name="name" display="姓名" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="phone" display="联系电话" sortable="true" width="150" align="left"></flex:flexCol>
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