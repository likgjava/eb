<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_create_jzgc.js"></script>

<div class="partContainers">
	<input type="hidden" name="parentId" id="parentId" value="${parentId}" />
	<input type="hidden" name="tenderType" id="tenderType" value="${project.tenderType}" />
	<!-- 包组列表 -->
	<table class="tableList" id="SubProjectList">
		<caption>
			<div class="functionBtnDiv">				 
			 <dm:out value="local__package" tenderType="${project.tenderType}">标段</dm:out>列表
			<c:choose>
				<c:when test="${role!='03' && isStart!='yes'}">
					<button id="subProjectSave" >
						<span class="add"><c:if test="${isStart!='yes'}">新增</c:if></span>
					</button>
				</c:when>
				<c:when test="${isStart=='yes'}">
					<span class="red">[已到投标单位报名时间，不能修改标段]</span>
				</c:when>
			</c:choose>
			</div>
		</caption>
  		<thead>
  		  <c:forEach items="${subProjectList}" var="sub" begin="0" end="0">
      		<tr>
          		<th class="center" width="10%">标段编号</th>
          		<th width="15%">标段名称</th>
          		<th width="10%">投资金额（元）</th>
          		<th>招标范围</th>
          		<th class="operation" width="20%">操作</th>
     		</tr>
     	 </c:forEach>
		</thead>
	<tbody>
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${subProjects}" var="subProject1" >
		<tr id="${subProject1.objId}">
			<td align="center" >${subProject1.projCode }
			</td>
			<td align="center" >${subProject1.projName }
			</td>
			<td align="right">${subProject1.budgetTotalMoney }
			</td>
			<td >${subProject1.zhaobiaoArea }
			</td>
			<td class="center" width="20%">
			<!-- 判断为招标中心 -->
			<c:if test="${role!='03'}">
			<c:if test="${isStart!='yes'}">
			<a href="#" style="text-decoration: underline;"><span class="sysicon siEdit" onclick="updateSubProject('${ subProject1.objId}','${subProject1.parentId }');" title="修改">修改</span></a>
			<a href="#" style="text-decoration: underline;"><span class="sysicon siExit" onclick="removeSubProject('${ subProject1.objId}');" title="删除">删除</span></a>	
			</c:if>
			<c:if test="${isStart=='yes'}">
				<a href="#" style="text-decoration: underline;"><span class="sysicon siAccept" onclick="viewSubProject('${subProject1.objId}');" title="查看">查看</span></a>
			</c:if>
			</c:if>
			<!-- 判断为监管机构 -->
			<c:if test="${role=='03'}">
				<a href="#" style="text-decoration: underline;"><span class="sysicon siAccept" onclick="viewSubProject('${subProject1.objId}');" title="查看">查看</span></a>
			</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
    </table>
</div>