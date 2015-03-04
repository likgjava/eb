<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/projectListForAudit.js"></script>
	<form id="projectSearchZone" >
		<!-- 查询隐藏条件 -->
		<!-- 当前用户类型 -->
		<input type="hidden" id="userType" name="userType" value="${userType }"/>
		<input type="hidden" id="searchMethod" name="searchMethod" value="${searchMethod }"/>
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li>
					<label><spring:message code="projectForm.projCode"/>:</label>
					<input type="hidden" name="projCode_op" value="like"/>
					<input type="text" name="projCode"  />
				</li>
				<li>
					<label><spring:message code="projectForm.projName"/>:</label>
					<input type="text" name="projName"  />
					<input type="hidden" name="projName_op" value="like"/>
				</li>
				<li class="operationBtnDiv">
				      <button type="submit"><span><spring:message code="globe.search"/></span></button>
				</li>
			</ul>
		</div>
	</form>
	<flex:flexgrid checkbox="true"
		id="projectGrid" url="ProjectController.do?method=getProjectListByTaskPlanForAudit" queryColumns=""  
			searchZone="projectSearchZone" rp="10" title="待审核大宗项目"  >
					<flex:flexCol name="projCode" display="projectForm.projCode" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="projName" display="projectForm.projName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN" display="projectForm.ebuyMethod" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="createTime" display="projectForm.createTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexBtn name="审核" bclass="audit" onpress="projectListForAudit.audit"></flex:flexBtn>				
	</flex:flexgrid>
