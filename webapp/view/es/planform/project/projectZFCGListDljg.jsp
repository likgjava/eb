<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<style>
<!--
.styleA{
cursor: hand;
}
-->
</style>
		<input type="hidden" value="${serviceName}" id="serviceName">
<form id="pageSearchZone" name="pageSearchZone" >
		<div class="conSearch" >
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li>
					<label><spring:message code="projectForm.projCode"/>:</label>
					<input type="text" name="projCode1"  id="projCode1" />
				</li>
				<li>
					<label><spring:message code="projectForm.projName"/>:</label>
					<input type="text" name="projName1"  id="projName1" />
				</li>
				<li class="fullLine">
					<label for="ebuyMethod"><spring:message code="projectForm.ebuyMethod"/>:</label>
					<html:select styleClass="required short" id="ebuyMethod1" name="ebuyMethod1" code="ebuyMethod" selectedValue="${ebuyMethod}" >
					<html:option value="">—请选择—</html:option>
				</html:select>
				</li>
				<li class="operationBtnDiv">
				      <button type="button" id="pageSearchBut"><span><spring:message code="globe.search"/></span></button>
				</li>
			</ul>
		</div>
<div id="epsTabs">
			<ul>
			<c:forEach items="${messageList}" var="mc">
			  <li>
			  <a  href="#consignListView" id="project${mc.code}" class="refreshData"> ${mc.message}</a>
			 </li>
			 </c:forEach>
			</ul>
	<div class="formLayout"  id="consignListView">			
	  </div>
</div>
</form>
<script language="javascript">
	var projectList={};
	
	projectList.toProjectInfo = function(projectId){//进入项目
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+projectId);
	}

	projectList.toProjectInfoForTab = function(projectId){//进入项目forTab
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForTab&objId='+projectId);
	}
	
	projectList.toModifyProjectInfo = function(projectId){//修改项目
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toModifyProjectInfo&projectId='+projectId);
	}
	projectList.toRemoveProject = function(projectId){//删除项目
		if (window.confirm('确定删除?')) {
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=removeProject&projectId='+projectId,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForAgent');//刷新
			});
		}
	}
	//删除
	projectList.remove=function(name,grid){
		if(!projectList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}

	//显示项目进度
	projectList.showPlan=function(projectId){
		$.epsDialog({
	        title:"项目进度",
	        width:"900",
	        url:$('#initPath').val()+'/view/es/planform/projectplan/projectPlanDetailOuter.jsp?projectId='+projectId
		});
	}

	function changeProjectColor() {
		$("label[name=projs]").each(function(i,n){
			var ebuyMethod = $(n).find('span').eq(0).attr('id');
			if(ebuyMethod == '00'){ //如果为公开招标
				$(n).find('span').eq(0).attr("style","color: red;");//公开招标项目
			}else{
				$(n).find('span').eq(0).attr("style","color: #ffa500;");//非公开招标项目
			}
		})
	}
	
	$(document).ready(function(){
		$("#consignListView").loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg2&tenderType=01&serviceName='+$("#serviceName").val());
		changeProjectColor();
		$(".spaceused").progressBar();
		
		$("#pageSearchBut").click(function(){
			$("#projCode").val($("#projCode1").val());
			$("#projName").val($("#projName1").val());
			$("#ebuyMethod").val($("#ebuyMethod1").val());
			pageDirection.jump();
		});
		// 回填采购方式
		var ebuyMethod = $("#_ebuyMethod").val();
		if(null != ebuyMethod && '' != ebuyMethod){
			$("#ebuyMethod").val(ebuyMethod);
		}
		$('#epsTabs').tabs();
		projectList.currentTabID = "project03";
		$("li a.refreshData").click(function(){
			projectList.currentTabID = $(this).attr("id");
			if(projectList.currentTabID == "project01"){//临时
				$("#tenderType").val("01");
			}else if(projectList.currentTabID == "project02"){
				$("#tenderType").val("02");
			}else if(projectList.currentTabID == "project03"){
				$("#tenderType").val("03");
			}else{
				$("#tenderType").val("04");
			}
			$("#pageSearchBut").click();
		})
		
	});
</script>