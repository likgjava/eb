<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/congruousFactorTypeList.js"></script>
<input type="hidden" id="project_objId" value="${project.objId}"></input>
<div class="partContainers">
	<div class="formLayout form2Pa">
		<h5><span>项目信息</span></h5>
	     	<ul>
				<li>
					<label class="short"><spring:message code="projectForm.projCode"/>：</label>
					<span>${project.projCode}</span>
				</li>
				<li>
					<label class="short"><spring:message code="projectForm.projName"/>：</label>
					<span>${project.projName}</span>
				</li>
				<li>
					<label class="short"><spring:message code="projectForm.ebuyMethod"/>：</label>
					<span>${project.ebuyMethodCN}</span>
				</li>
				<li>
					<label class="short"><spring:message code="projectMTaskPlanForm.buyMainBody"/>：</label>
					<span>${project.buyersName}</span>
				</li>
				<li>
					<label class="short">评审方法：</label>
					<span></span>
				</li>
			</ul>
	</div>
</div>
<c:set var="num" value="0"></c:set>
<form:form id="congruousFactorTypeForm" method="post" modelAttribute="taskPlan">
	<div class="partContainers">
		<div class="formLayout form2Pa">
			<table class="tableList">
			<caption>请录入评审步骤</caption>
				<tr>
					<th colspan="2">评审步骤</th>
					<th><spring:message code="congruousFactorTypeForm.weightCoefficient"/></th>
					<th>操作</th>
				</tr>
				<c:forEach items="${congruousFactorTypeList}" var="congruousFactorType" >
					<tr>
						<c:if test='${congruousFactorType.typeCode != "competencyAudit"}'>
							<td style="text-align: center;"><input type="checkbox" value="${num+1}"/></td>
							<td>
								<input type="text" name="congruousFactorType[${num}].typeName" value="${congruousFactorType.typeName}" style="width: 160px;"/>
								<span class="eleRequired"></span>
							</td>
							<td>
								<input type="text" name="congruousFactorType[${num}].weightCoefficient" value="${congruousFactorType.weightCoefficient}" style="width: 60px;" class="digits" min="0" max="100"/>
								<span class="eleRequired"></span>
							</td>
						</c:if>
						<c:if test='${congruousFactorType.typeCode == "competencyAudit"}'>
							<td style="text-align: center;"><input type="checkbox" checked="checked" disabled="disabled" value="${num+1}"/></td>
							<td>${congruousFactorType.typeName}</td>
							<td><input type="hidden" name="congruousFactorType[${num}].typeName" value="${congruousFactorType.typeName}"/><input type="hidden" name="congruousFactorType[${num}].weightCoefficient" value="0"/></td>
						</c:if>
						<td>
							<input type="hidden" name="congruousFactorType[${num}].objId" value="${congruousFactorType.objId}"/>
							<input type="hidden" name="congruousFactorType[${num}].sort" value="${congruousFactorType.sort}"/>
							<input type="hidden" name="congruousFactorType[${num}].projId" value="${congruousFactorType.projId}"/>
							<input type="hidden" name="congruousFactorType[${num}].typeCode" value="${congruousFactorType.typeCode}"/>
							<c:if test='${congruousFactorType.typeCode != "competencyAudit"}'>
								<button onclick="congruousFactorTypeList.bottomMoveCongruousFactorType(this);" class="sysicon siLast" title="置底 指标分类" option="bottom"><span></span></button>
								<button onclick="congruousFactorTypeList.upMoveCongruousFactorType(this);" class="sysicon siUp" title="上移 指标分类" option="up"><span></span></button>
								<button onclick="congruousFactorTypeList.downMoveCongruousFactorType(this);" class="sysicon siDown" title="下移 指标分类" option="down"><span></span></button>
								<button onclick="congruousFactorTypeList.topMoveCongruousFactorType(this);" class="sysicon siFirst" title="置顶 指标分类" option="top"><span></span></button>
								<button onclick="congruousFactorTypeList.removceCongruousFactorType(this);" class="sysicon siDelete" title="删除 指标分类" option="delete"><span></span></button>
							</c:if>
						</td>
					</tr>
					<c:set var="num" value="${num+1}"></c:set>
				</c:forEach>
			</table>	
		</div>
	</div>
</form:form>
<input type="hidden" id="num" value="${num}" />
<div class="conOperation">
	<button id="next" type="button" tabindex="18"><span>保存</span></button>
</div>