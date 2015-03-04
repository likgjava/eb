<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractListForSupplier.js"></script>
<c:choose>
<c:when test="${notDivide == false}">
<table>
<c:forEach items="${subProjectList}" var="subProject1">
  <tr>
    <td><h4><span><font color="red">【<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>信息】</font>${subProject1.project.projName },${subProject1.project.projCode }</span>&nbsp;&nbsp;&nbsp;
    <c:choose>
    <c:when test="${contractMethod == '01'}">
    <a class="abtn" onclick="contractList.add('${subProject1.project.objId}','01')">按该<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>起草合同</a> </c:when>
    <c:otherwise>
      <a class="abtn" onclick="contractList.add('${subProject1.project.objId}','01')">按该<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>起草合同</a> &nbsp;&nbsp;&nbsp;
   </c:otherwise>
    </c:choose>
    </h4></td>
  </tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<c:choose>
<c:when test="${noConfirm!=true}">
<table>
<c:if test="${isCan=='Contract'}">
<c:if test="${contractMethod != '01'}">
<tr><td><h4><span> <font color="red">【项目信息】</font>${project.projName },${project.projCode }&nbsp;&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.add('${project.objId}','01')">按项目起草合同</a></span></h4></td></tr>
</c:if>
</c:if>
</table>
</c:when>
</c:choose>
</c:otherwise>
</c:choose>
<table>
<c:forEach items="${contractList}" var="subProject1" >
  <tr>
    <td><h4><span><font color="blue">【合同信息】</font>${subProject1.contractName },${subProject1.contractNo },</span>
    <c:choose>
    <c:when test="${subProject1.contractStatus == '00'}"><font color="green">待提交</font>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.update('${subProject1.objId}')">修改</a>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.remove('${subProject1.objId}')">删除</a></c:when>
    <c:when test="${subProject1.contractStatus == '01'}"><font color="green">待确认</font>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.detail('${subProject1.objId}')">查看详情</a></c:when>
    <c:when test="${subProject1.contractStatus == '02'}"><font color="green">被退回</font>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.update('${subProject1.objId}')">修改</a>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.remove('${subProject1.objId}')">删除</a></c:when>
     <c:otherwise><font color="gray">已确认</font>&nbsp;&nbsp;&nbsp;<a class="abtn" onclick="contractList.detail('${subProject1.objId}')">查看详情</a></c:otherwise>
    </c:choose>
    </h4></td>
  </tr>
</c:forEach>
</table>
