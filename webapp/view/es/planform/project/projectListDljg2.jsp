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
	       		<a class="sysicon siDownBtn styleA" onclick='javascript:projectList.toViewProjectInfo("${project1.objId}")' title="查看项目"><span>查看项目</span></a>	   
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
		<%@ include file="/view/es/common/pageDirection.jsp" %>
<script language="javascript">
	var projectList={};

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

	//查看项目
	projectList.toViewProjectInfo = function(projectId){
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toViewProjectInfo&objId='+projectId);
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
				$('#consignListView').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForAgent');//刷新
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
		projectList.changeProjectColor();
		$(".spaceused").progressBar();
		// 回填采购方式
		var ebuyMethod = $("#_ebuyMethod").val();
		if(null != ebuyMethod && '' != ebuyMethod){
			$("#ebuyMethod").val(ebuyMethod);
		}
	});
</script>