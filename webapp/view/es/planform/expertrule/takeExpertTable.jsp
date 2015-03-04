<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/expertrule/takeExpertTable.js"></script>

<div id="superviseDoDiv" style="overflow: auto;" class="page">
	<div style="margin-bottom: 5px;" class="tabs">
		<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>	
			<c:forEach items="${subProjectList}" var="subProject" varStatus="i">
			    <li id="tab${i.count}"><a href="#" onClick="expertRule_table.getExpertRule('${subProject.objId}','${projectId}')"><span>${subProject.projName}</span></a></li>
			</c:forEach>
			</ul>
		</div>
		</div>
		<div id="subProjectTabDiv" style="height:auto;" class="tabsContent">
		</div>
	</div>
	</div>