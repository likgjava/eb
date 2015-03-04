<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/expertrule/obtain_Expert_List.js"></script>

<div class="partContainers">
	<table class="tableList" id="">
	
		<caption> <div class="functionBtnDiv">评标专家信息<button id="obtain" <c:if test="${obtain=='no'}">disabled="disabled"</c:if> ><span class="add">获取</span></button></div></caption>
  		<thead>
      		<tr class="center">
      			<th>名称</th>
          		<th>职称</th>
          		<th>专业</th>
          		<th>登录帐号</th>
          		<th>登录密码</th>
          		<th>联系电话</th>
          		<th>组长</th>
          		<%--<th>正选</th>
          		<th>备选</th>--%>
          		<th>操作</th>
     		</tr>
		</thead>
		<thead id="obtainExpertTable">
		<c:forEach items="${listWorkgMember}" var="workgMember" varStatus="i">
		<tr id="tName${i.count}">
			<td style="text-align:center;">${workgMember.workgmName}</td>
			<td style="text-align:center;">${workgMember.workgmDuty}</td>
			<td style="text-align:center;">${workgMember.workgmSpeciality}</td>
			<td style="text-align:center;">${workgMember.workgmAccount}</td>
			<td style="text-align:center;">${workgMember.workgmPassWord}</td>
			<td style="text-align:center;">${workgMember.linkerPhone}</td>
			<td style="text-align:center;"><input type="radio" name="radioLeader" <c:if test="${workgMember.workgmIsLeader=='01'}">checked</c:if> onClick="obtainExpertList.isLeader('${workgMember.objId}')"/></td>
			<%--<td style="text-align:center;"><input type="checkbox" name="radioAmoun${i}" <c:if test="${workgMember.isAmount=='00'}">checked</c:if> onClick="obtainExpertList.isAmoun('${workgMember.objId}','00')"/></td>
			<td style="text-align:center;"><input type="checkbox" name="radioAmoun${i}" <c:if test="${workgMember.isAmount=='01'}">checked</c:if> onClick="obtainExpertList.isAmoun('${workgMember.objId}','01')"/></td>--%>
		<td style="text-align:center;"><button id="${i.count}" objId="${workgMember.objId}" onClick="obtainExpertList.delMember('${workgMember.objId}','${i.count}')">删除</button></td>
		</tr>
		</c:forEach>
		</thead>
    </table>
</div>
	<input type="hidden" id="exper_subProjectId" value="${subProjectId}"/>
	<input type="hidden" id="workId" value="${project.workGroup.objId}"/>