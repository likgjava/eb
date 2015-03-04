<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/expertrule/expert_condition.js"></script>
<style>
<!--
.styleA{
cursor: hand;
text-decoration: underline;
color: blue;
}
-->
</style>
<table class="tableList" id="moreCondition">
	<caption>抽取条件</caption>
	<thead>
      		<tr>
      			<th style="text-align:center;">序号</th>
      			<th style="text-align:center;">抽取条件</th>
      			<th style="text-align:center;">操作</th>
      		</tr>
    </thead>
	<tbody id="bodyCondition">
	<c:if test="${itemFlag=='no'}">
		<tr>
			<td colspan="2" style="text-align:center;color: red;">当前还没有创建抽取条件,请使用下面的创建工具建立抽取条件！</td>
		</tr>
	</c:if>
	<c:if test="${itemFlag=='yes'}">
		<c:forEach items="${listTakeExpertRuleItem}" var="takeExpertRuleItem" varStatus="i"> 
			<tr>
				<td style="text-align:center;width: 40px;">${i.count}</td>
				<td>${takeExpertRuleItem.contents}</td>
				<td style="text-align:center;width: 90px;">
				<div class="conOperation">
				<a href="#" title="修改" class="styleA" id="del_${i.count}" ${param.disabled} onClick="expertCondition.modifyCondition('${takeExpertRuleItem.objId}')"><span>修改</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="删除" class="styleA" id="del_${i.count}" ${param.disabled} onClick="expertCondition.delCondition('${takeExpertRuleItem.objId}')"><span>删除</span></a>
				</div>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>