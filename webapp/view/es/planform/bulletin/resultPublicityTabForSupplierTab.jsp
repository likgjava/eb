<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/resultPublicityTabForSupplierTab.js"></script>
	<div style="margin-bottom: 5px;" class="tabs">
		<div class="tabsHeader">
			<div id="resultTabs" class="tabsHeaderContent">
			<ul id="resultTabUl">	
				<c:forEach items="${projectList}" var="project" varStatus="i">
					    <li id="tab${i.count}" class=""><a href="#" onClick="mydesktop_supervise.getResultBulletin('${project.objId}','${i.count}');"><span>${project.projName}</span></a></li>
				</c:forEach>
			</ul>
			</div>
		</div>
		<div id="superviseTabDiv"  class="tabsContent">
		</div>
	</div>
