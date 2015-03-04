<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@page import="java.util.*,com.gpcsoft.epp.buyresult.domain.*" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/buyresult/buy_result_list_view.js"></script>
<div  class="partContainers" id="superviseDoDiv1" >
			<ul id="resultTabUl">
			 	 <c:set var="count" value="0"></c:set>
				 <c:forEach items="${results}" var="result" >
				 <li id="tab${result.objId}" class=""><a href="#" onClick="buy_result_list.viewWinner('${count}','${result.objId }','${result.subProjId}');"><span><c:out value="${result.subProjName}"/></span></a></li>
				 <c:set var ="count" value = "${count+1}"></c:set>
				 </c:forEach>
			</ul>
	<div id="resultTabDiv" class="tabsContent">	</div>

</div>