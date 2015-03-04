<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractListForBuyer.js"></script>
<table>
<c:forEach items="${contractList}" var="subProject1">
  <tr>
    <td><h4><span><font color="blue">【合同信息】</font>${subProject1.contractName },${subProject1.contractNo },</span>
     <c:choose>
       	<c:when test="${param.fromType == 'fromHistory'}"><a class="abtn" onclick="contractList.detail('${subProject1.objId}','${param.fromType}')">查看详情</a></c:when>
       		<c:otherwise>
       		 	<c:choose>
    				<c:when test="${subProject1.contractStatus == '01'}"><font color="red">待确认</font>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.confirm('${subProject1.objId}')">确认</a></c:when>
     				<c:otherwise><font color="gray">已确认</font>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.detail('${subProject1.objId}')">查看详情</a></c:otherwise>
    			</c:choose>
       		</c:otherwise>
     </c:choose>
    </h4></td>
  </tr>
</c:forEach>
</table>
