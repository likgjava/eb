<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<input type="hidden" id="projectId" value="${param.projectId}">
<div id="superviseDoDiv">
   <div>
		<div>
			<div>
				<ul>	
				     <c:forEach items="${subProjectList}" var="subProject1" >
			          <li id="tab${subProject1.objId}" class=""><a href="#" url="/OpenBidRecordController.do?method=getOpenBidRecordList&subProjId=${subProject1.objId}" divId="#superviseTabDiv"><span><c:out value="${subProject1.projName}"/></span></a></li>
		             </c:forEach>
				</ul>
			</div>
		</div>
		<div id="superviseTabDiv" class="tabsContent"></div>
	</div>
</div>
<script language="javascript">
   $(document).ready(function(){
	   $('#superviseDoDiv').tabs1();
	});
</script>
   	