<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<style>
<!--
.styleA {
	cursor: hand;
}
-->
</style>
<input type="hidden" value="${serviceName}" id="serviceName">
<input type="hidden" value="${projProcessStatus}" id="projProcessStatus1">
<form id="pageSearchZone" name="pageSearchZone">
<input type="hidden" value="0" id="ruleAnonymous" name="ruleAnonymous"/>
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<ul>
	<li><label><spring:message code="projectForm.projCode" />:</label>
	<input type="text" name="projCode1" id="projCode1" /></li>
	<li><label><spring:message code="projectForm.projName" />:</label>
	<input type="text" name="projName1" id="projName1" /></li>
	<li class="fullLine"><label for="ebuyMethod"><spring:message
		code="projectForm.ebuyMethod" />:</label> <select styleClass="required short"
		id="ebuyMethod1" name="ebuyMethod1">
		<option value="">—显示全部—</option>
		<c:forEach var="em" items="${ebuyMethodList}">
			<option value="${em.code}">${em.message}</option>
		</c:forEach>
	</select></li>
	<li class="operationBtnDiv">
	<button type="button" id="pageSearchBut"><span><spring:message
		code="globe.search" /></span></button>
	</li>
</ul>
</div>
<div id="epsTabs">
<ul>
	<c:forEach items="${messageList}" var="mc">
		<li><a href="#consignListView" id="project${mc.code}"
			class="refreshData" tenderType="${mc.code}"> ${mc.message}</a></li>
	</c:forEach>
	<li><a href="#consignListView" id="projectNiming"
			class="refreshData" tenderType="niming"> 匿名投标项目</a></li>
</ul><!--这里的C：forEach只是拿出了列表上部四个选项卡-->

<div class="formLayout" id="consignListView"></div>
</div>
</form><!-- 这里放着一个空DIV，将根据所选选项卡，来填充list数据 -->


<script language="javascript">
	var projectList2={};
	$(document).ready(function(){
		projectList2.loadProjectList = function(tenderType){
			$("#consignListView").loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg2&ruleAnonymous='+$("#ruleAnonymous").val()+'&tenderType='+tenderType+'&serviceName='+$("#serviceName").val()+'&projProcessStatus='+$("#projProcessStatus1").val());
		} 
		$("#pageSearchBut").click(function(){
			$("#projCode").val($("#projCode1").val());
			$("#projName").val($("#projName1").val());
			$("#ebuyMethod").val($("#ebuyMethod1").val());
			$("#projProcessStatus").val($("#projProcessStatus1").val());
				//将需要跳转的页数据赋给page，以方便完成form封装
			$("#page").val($("#newPage").val());
			var url=$('#initPath').val()+'/'+$("#pageSearchUrl").val();
			if(url=='/es/undefined'){//用以判断去除不必要的加载
				return false;
			}
			$('#consignListView').loadPage(url,formToJsonObject("pageSearchZone"));
		});
		var ebuyMethod = $("#_ebuyMethod").val();
		// 回填采购方式
		if(null != ebuyMethod && '' != ebuyMethod){
			
			$("#ebuyMethod").val(ebuyMethod);
		}
		projectList2.loadProjectList('01');
		$('#epsTabs').tabs();
		 $("li.ui-state-disabled").each(function(){
				$(this).attr("class","ui-state-default ui-corner-top ui-state-active");
		 })
		projectList2.currentTabID = "project01";
		$("li a.refreshData").click(function(){
			projectList2.currentTabID = $(this).attr("id");
			if(projectList2.currentTabID == "project01"){//临时
				$("#ruleAnonymous").val("0");
				projectList2.loadProjectList('01');
				$("#tenderType").val("01");
				
			}else if(projectList2.currentTabID == "project02"){
				$("#ruleAnonymous").val("0");
				projectList2.loadProjectList('02');
				$("#tenderType").val("02");
			}else if(projectList2.currentTabID == "project03"){
				$("#ruleAnonymous").val("0");
				projectList2.loadProjectList('03');
				$("#tenderType").val("03");
			}else if(projectList2.currentTabID == "project04"){
				$("#ruleAnonymous").val("0");
				projectList2.loadProjectList('04');
				$("#tenderType").val("04");
			}else{
				$("#ruleAnonymous").val("1");
				$("#tenderType").val("");
				projectList2.loadProjectList("");
				
			}
		})
		$("li a.refreshData:first").click();
		$('body').bind("keypress", function(event){
			if (event.keyCode == 13) {								   
				$("#pageSearchBut").click();
				return false;
			}
		});
	});
</script>