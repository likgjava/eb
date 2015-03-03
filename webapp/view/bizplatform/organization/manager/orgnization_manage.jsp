<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/organization/manager/orgnization_manage.js"></script>

<input type="hidden" id="companyId" value="${company.objId}"/>
<input type="hidden" id="companyName" value="${company.orgName}"/>

<form  id="orgnizationTreeList" name="orgnizationTreeList">
<div class="treePage frameMainSub frameMs12 fullScreen">
	<!-- 组织机构树 -->
	<div class="treeOutside frameMain">
		<div id="orgnizationTreeGrid" style="overflow-x: auto;overflow-y: hidden"  class="treeContentDiv"></div>
	</div>
	
	<div class="treeRight frameSub" id="treeRight">
		<!-- 机构信息 -->
		<div id="companyDetail" class="formLayout form2Pa">
			<div class="treeEditNav">
				<ul>
					<li id="addDepartment" class="add"><a href="javascript:void(0);" onclick="OrgnizationManage.addDepartment();return false;"><span>新增部门</span></a> </li>
				</ul>
			</div>
			<h5><span>机构信息</span></h5>
			<ul>
				<li class="fullLine"><label>机构名称：</label><span>${company.orgName}</span></li>
				<li class="fullLine"><label>组织机构代码：</label><span>${company.code}</span></li>
				<li class="fullLine"><label>机构简称：</label><span>${company.shortName}</span></li>
				<li class="fullLine"><label>企业性质：</label><span>${company.entPrptCN}</span></li>
				<li class="fullLine"><label>企业已有角色：</label><p>
					<c:forEach var="companyRole" items="${companyRoleList}" varStatus="status">
						<c:if test="${status.index != 0}">, </c:if>
						<span>${companyRole.chName}</span>
					</c:forEach></p>
				</li>
			</ul>
		</div>
		
		<!-- 部门或岗位详情信息 -->
		<div id="orgnizationDetail" class="formLayout form2Pa hidden"></div>
		
		<!-- 部门或岗位表单 -->
		<div id="orgnizationForm" class="formLayout form2Pa hidden"></div>
	</div>
</div>

<c:if test="${isAdmin}">
	<div class="conOperation">
		<button id="orgnizationManageReturnBtn" type="button"><span>返回到机构列表页面</span></button>
	</div>
</c:if>
</form>