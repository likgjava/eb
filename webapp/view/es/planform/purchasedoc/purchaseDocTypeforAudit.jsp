<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="epsTabs1">
	<ul>
		<li>
	      <a href="#purchaseDocAuditView" id = "tabs_purchaseDoc" class="refreshData"><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></a>
	    </li>
	    <li>
	      <a href="#InqpDocAuditView" id = "tabs_InqpDoc" class="refreshData"><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></a>
	    </li>
	    <li>
	      <a href="#ClarificationView" id = "tabs_ClarificationDoc" class="refreshData"><span><dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out></span></a>
	    </li>
    </ul>
    <div id="purchaseDocAuditView"></div>
    <div id="InqpDocAuditView"></div>
    <div id="ClarificationView"></div>
</div>
<script>
$(document).ready(function(){
	//加载tabs
	$('#epsTabs1').tabs();
    //加载采购文件审核列表
	$("#purchaseDocAuditView").load($('#initPath').val()+'/view/es/planform/purchasedoc/purchaseDocListForAudit.jsp');

})

	//询价文件
	$("#tabs_InqpDoc").click(function(){
		if($("#InqpDocAuditView").text().length == 0){
			$("#InqpDocAuditView").load($('#initPath').val()+'/view/es/inquiryprice/inqpdoc/inqpDocListForAudit.jsp');
		}
	})
	//澄清文件
	$("#tabs_ClarificationDoc").click(function(){
			$("#ClarificationView").load($('#initPath').val()+'/view/es/planform/clarificationDoc/ClarificationDocListForAudit.jsp');
	})
</script>