<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/resultBulletinTabForDetail.js"></script>
		<div style="margin-bottom: 5px;" class="tabs">
		<div id="resultTabs" class="tabsHeader">
			<ul>	
				<c:forEach items="${projectList}" var="project" varStatus="i">
					    <li id="tab${i.count}" class=""><a href="#" onClick="mydesktop_supervise.getResultBulletin('${project.objId}','${i.count}');" style="line-height: 10px;"><span>${project.projName}</span></a></li>
				</c:forEach>
			</ul>
		</div>
			<div id="superviseTabDiv" style="height:auto;" class="tabsContent">
		</div>
		</div>
