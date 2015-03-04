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
<form>
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<ul>
	<li><label><spring:message code="projectForm.projCode" />:</label>
	<input type="text" name="projCode1" id="projCode1" /></li>
	<li><label><spring:message code="projectForm.projName" />:</label>
	<input type="text" name="projName1" id="projName1" /></li>
	<li class="fullLine"><label for="ebuyMethod">项目类型:</label><html:select styleClass="required" id="tenderType1" name="tenderType1" code="taskplantype" selectedValue="${tenderType}">
						<html:option value="">—显示全部—</html:option> <html:option value="04"  >建筑工程</html:option></html:select></li>
	<li class="fullLine"><label for="ebuyMethod"><spring:message
		code="projectForm.ebuyMethod" />:</label> <html:select styleClass="required" id="ebuyMethod1" name="ebuyMethod1" code="ebuyMethod" selectedValue="${ebuyMethod}">
						 <html:option value="">—显示全部—</html:option>   </html:select></li>
	<li class="operationBtnDiv">
	<button type="button" id="pageSearchBut"><span><spring:message
		code="globe.search" /></span></button>
	</li>
</ul>
</div>
</form>
<div class="formLayout" id="consignListView">
<form id="pageSearchZone" name="pageSearchZone">
		<input type="hidden" id="_ebuyMethod" value="${ebuyMethod}"></input>
		<h5><span>我的项目</span></h5>
		<!-- 查询隐藏条件 -->
		<!-- 当前用户类型 -->
		<!--总数据 -->
		<input type="hidden" id="totalRowNum" name="totalRowNum" value="${PAGERESULT.totalRowNum }"/>
		<!--总页数 -->
		<input type="hidden" id="totalPageNum" name="totalPageNum" value="${PAGERESULT.totalPageNum }"/>
		<!--当前页 -->
		<input type="hidden" id="page" name="page" value="${PAGERESULT.pageNum }"/>
		<!--当前页 -->
		<input type="hidden" id="newPage" name="newPage" value="${PAGERESULT.pageNum }"/>
		<!--每页显示条数 -->
		<input type="hidden" id="rp" name="rp" value="${PAGERESULT.pageSize }"/>
		<input type="hidden" id="previousPage" name="previousPage" value="${PAGERESULT.previousPage }"/>
		<input type="hidden" id="nextPage" name="nextPage" value="${PAGERESULT.nextPage }"/>
		<!--查询访问的请求地址 -->
		<input type="hidden" id="pageSearchUrl" name="pageSearchUrl" value="${pageSearchUrl }"/>
		
		<input type="hidden" id="userType" name="userType" value="${userType }"/>
		<input type="hidden" id="searchMethod" name="searchMethod" value="${searchMethod }"/>
		<input type="hidden" name="projCode_op" value="like"/>
		<input type="hidden" name="projCode"  id="projCode" value="${ projCode}"/>
		<input type="hidden" name="projName"  id="projName" value="${ projName}"/>
		<input type="hidden" name="projName_op" value="like" />
		<input type="hidden" id="ebuyMethod" value="${ebuyMethod}" name="ebuyMethod"></input>
		<input type="hidden" value="${tenderType}" name="tenderType" id="tenderType">
		<input type="hidden" name="projProcessStatus" id="projProcessStatus" value="${projProcessStatus}"/>
</form>
		<table id="expertis" >
		<c:forEach items="${PAGERESULT.data}" var="project1" >
			<tr >
			<td  style="border-width: 0; ">	
			<label name="projs" for="projName">
			[<span id="${project1.ebuyMethod}" name="${project1.tenderType}">${project1.ebuyMethodCN}</span>]&nbsp;【${project1.projName}】(	${fn:substring(project1.createTime, 0, 10)})</label>
			<label for="projName">项目进度：</label><span class="spaceused" onclick='javascript:projectList.showPlan("${project1.objId}")'>${project1.processPers }</span>
			</td>
			<td align="right"  style="border-width: 0; ">
				<authz:authorize ifAnyGranted="ProjectController.do?method=toEnterProject">
	       			<a class="sysicon siDownBtn styleA" onclick='javascript:projectList.enter("${project1.objId}")' title="进入"><span>进入</span></a>	   
	       		</authz:authorize>
	       		<authz:authorize ifAnyGranted="ProjectController.do?method=toPutonProjectRecord">
	       			<a class="sysicon siDownBtn styleA" onclick='javascript:projectList.putonrecord("${project1.objId}")' title="进入"><span>归档</span></a>	   
	       		</authz:authorize>
	       		<a class="sysicon siDownBtn styleA" onclick='javascript:projectList.toProjectInfo("${project1.objId}")' title="进入项目"><span>进入项目</span></a>	   
				<!-- a class="sysicon siDownBtn styleA" onclick='javascript:projectList.toProjectInfoForTab("${project1.objId}")' title="进入项目(Tab)"><span>进入项目(Tab)</span></a-->	   
				<authz:authorize ifAnyGranted="ProjectController.do?method=toModifyProjectInfo&projectId=">
		       		<a class="sysicon siEdit styleA" onclick='javascript:projectList.toModifyProjectInfo("${project1.objId}")' title="修改项目"><span>修改项目</span></a>	   
				</authz:authorize>
				<authz:authorize ifAnyGranted="ProjectController.do?method=removeProject&projectId=">
		       		<a class="sysicon siDelete styleA" onclick='javascript:projectList.toRemoveProject("${project1.objId}")' title="删除项目"><span>删除项目</span></a>	   
				</authz:authorize>
	       	</td>			   		 
	       </tr>
	       <tr >
	       <td colspan="2" style="border-width: 0; ">
		   &nbsp;&nbsp;&nbsp;&nbsp;<c:choose><c:when test="${project1.projSummary== null}">该项目暂时没有项目简介</c:when>
		   						  			 <c:otherwise>${project1.projSummary}</c:otherwise>
		   						   </c:choose>
		   </td>
	
			</tr>
			<tr>
			 <td colspan="2" style= "border-bottom:   1px   dashed   #303030; "></td>
			</tr>	
			
		</c:forEach>
		</table>
		<%@ include file="/view/es/common/pageDirection2.jsp" %>
		</div>
		
<script language="javascript">
	var projectList={};
	$("#pageSearchBut").click(function(){
		$("#projCode").val($("#projCode1").val());
		$("#projName").val($("#projName1").val());
		$("#ebuyMethod").val($("#ebuyMethod1").val());
		$("#tenderType").val($("#tenderType1").val());
			//将需要跳转的页数据赋给page，以方便完成form封装
		$("#page").val($("#newPage").val());
		var url=$('#initPath').val()+'/'+$("#pageSearchUrl").val();
		if(url=='/es/undefined'){//用以判断去除不必要的加载
			return false;
		}
		var a = formToJsonObject("pageSearchZone");
		$('#conBody').loadPage(url,a);
	});
	//进入
	projectList.enter = function(projectId){
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectViewController.do?method=toProjectInfo&projectId='+projectId);
	}
	//备案
	projectList.putonrecord = function(projectId){
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectViewController.do?method=toPutOnProjectRecord&projectId='+projectId);
	}
	
	//进入项目
	projectList.toProjectInfo = function(projectId){
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+projectId);
	}

	//修改项目
	projectList.toModifyProjectInfo = function(projectId){
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toModifyProjectInfo&projectId='+projectId);
	}
	
	//删除项目
	projectList.toRemoveProject = function(projectId){
		if (window.confirm('确定删除?')) {
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=removeProject&projectId='+projectId,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForAgent');//刷新
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

	//更改项目显示颜色
	projectList.changeProjectColor = function(){
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
		if($("#tenderType").val()=='04'){
			$("#tenderType1").val('04');
		}
		projectList.changeProjectColor();
		$(".spaceused").progressBar();
		// 回填采购方式
		var ebuyMethod = $("#_ebuyMethod").val();
		if(null != ebuyMethod && '' != ebuyMethod){
			$("#ebuyMethod").val(ebuyMethod);
		}
	});
</script>