<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
	<form id="projectDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span>项目</span></h4>
			     	<ul>
			     		<li>
			 				<label for="projectForm.projCode"><spring:message code="projectForm.projCode"/>：</label>
							<span id="projCode">${project.projCode }</span>
						</li>
						<li>
			 				<label for="projectForm.projName"><spring:message code="projectForm.projName"/>：</label>
							<span id="projName">${project.projName }</span>
						</li>
						<li class="money">
			 				<label for="projectForm.purchAmout"><spring:message code="projectForm.purchAmout"/>：</label>
							<span id="purchAmout"><strong>￥<fmt:formatNumber value="${project.purchAmout }" pattern="#,##0.00#" /></strong> 万元</span>
						</li>
						<li>
			 				<label for="projectForm.agencies"><spring:message code="projectForm.agenciesName"/>：</label>
							<span id="agencies"><a href="javascript:void(0);" onclick="showOrgInfo('${project.agenciesId}');return false;"> ${project.agenciesName }</a></span>
						</li>
						<li>
			 				<label for="projectForm.suppliersName"><spring:message code="projectForm.suppliersName"/>：</label>
							<span id="suppliersName">
								<c:forEach items="${project.suppliers}" var="suppliers" >
									<a href="javascript:void(0);" onclick="showOrgInfo('${suppliers.supplierId}');return false;">${suppliers.supplierName}</a>
								</c:forEach>
							</span>
						</li>
						<li>
			 				<label for="projectForm.buyersName"><spring:message code="projectForm.buyersName"/>：</label>
							<span id="buyersName">${project.buyersName }</span>
						</li>
						<li>
			 				<label for="projectForm.ebuyMethod"><spring:message code="projectForm.ebuyMethod"/>：</label>
							<span id="ebuyMethod">${project.ebuyMethodCN }</span>
						</li>
						<li>
			 				<label for="projectForm.purCategoryNames"><spring:message code="projectForm.purCategoryNames"/>：</label>
							<span id="purCategoryNames">${project.purCategoryNames }</span>
						</li>
						<li>
			 				<label for="projectForm.manager"><spring:message code="projectForm.manager"/>：</label>
							<span id="manager">${project.manager.name }</span>
						</li>
						<li>
			 				<label for="projectForm.monitor"><spring:message code="projectForm.monitor"/>：</label>
							<span id="monitor">${project.monitor.name }</span>
						</li>
						<li>
			 				<label for="projectForm.projProcessStatus"><spring:message code="projectForm.projProcessStatus"/>：</label>
							<span id="projProcessStatus">${project.projProcessStatusCN }</span>
						</li>
						<li>
			 				<label for="projectForm.projImplStatus"><spring:message code="projectForm.projImplStatus"/>：</label>
							<span id="projImplStatus">${project.projImplStatusCN }</span>
						</li>
						<li>
			 				<label for="projectForm.auditStatus"><spring:message code="projectForm.auditStatus"/>：</label>
							<span id="auditStatus">${project.auditStatusCN }</span>
						</li>
						<li>
			 				<label for="projectForm.useStatus"><spring:message code="projectForm.useStatus"/>：</label>
							<span id="useStatus">${project.useStatusCN }</span>
						</li>
						<li>
			 				<label for="projectForm.processPers"><spring:message code="projectForm.processPers"/>：</label>
							<span id="processPers">${project.processPers }</span>
						</li>
						<li>
			 				<label for="projectForm.srcApp"><spring:message code="projectForm.srcApp"/>：</label>
							<span id="srcApp">${project.srcApp }</span>
						</li>
						<li>
			 				<label for="projectForm.signUpSTime"><spring:message code="projectForm.signUpSTime"/>：</label>
							<span id="signUpSTime"><fmt:formatDate value="${project.signUpSTime }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.signUpETime"><spring:message code="projectForm.signUpETime"/>：</label>
							<span id="signUpETime"><fmt:formatDate value="${project.signUpETime }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.tenderStartTime"><spring:message code="projectForm.tenderStartTime"/>：</label>
							<span id="tenderStartTime"><fmt:formatDate value="${project.tenderStartTime }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.tenderEndTime"><spring:message code="projectForm.tenderEndTime"/>：</label>
							<span id="tenderEndTime"><fmt:formatDate value="${project.tenderEndTime }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.planStartDate"><spring:message code="projectForm.planStartDate"/>：</label>
							<span id="planStartDate"><fmt:formatDate value="${project.planStartDate }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.planEndDate"><spring:message code="projectForm.planEndDate"/>：</label>
							<span id="planEndDate"><fmt:formatDate value="${project.planEndDate }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.startDate"><spring:message code="projectForm.startDate"/>：</label>
							<span id="startDate"><fmt:formatDate value="${project.startDate }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.endDate"><spring:message code="projectForm.endDate"/>：</label>
							<span id="endDate"><fmt:formatDate value="${project.endDate }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li>
			 				<label for="projectForm.meetingRoomId"><spring:message code="projectForm.meetingRoomId"/>：</label>
							<span id="meetingRoomId">${project.meetingRoomId }</span>
						</li>
						<li>
			 				<label for="projectForm.templateId"><spring:message code="projectForm.templateId"/>：</label>
							<span id="templateId">${project.templateId }</span>
						</li>
						
						<li>
			 				<label for="projectForm.createUser"><spring:message code="projectForm.createUser"/>：</label>
							<span id="createUser">${project.createUser.usName}</span>
						</li>
						<li>
			 				<label for="projectForm.createTime"><spring:message code="projectForm.createTime"/>：</label>
							<span id="createTime"><fmt:formatDate value="${project.createTime }" pattern= 'yyyy-MM-dd HH:mm:ss'/></span>
						</li>
						<li class="formTextarea">
			 				<label for="projectForm.projSummary"><spring:message code="projectForm.projSummary"/>：</label>
							<span id="projSummary">${project.projSummary }</span>
						</li>
					</ul>
		    <div class="conOperation">
		    	<button type="button" name="enterBackBtn" type="button" tabindex="20" onclick="common.open('${project.srcUrl}','${project.objId}');"><span>进入</span></button>
				<button type="button" name="historyBackBtn" type="button" tabindex="20""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>
<script>
function showOrgInfo(id){
	var url = $('#initPath').val()+'/OrgInfoController.do?method=getOrgAllInfo&orgInfoId='+id;
	$.epsDialog({
        title:'机构详情',
        url:url,
        width: '900',
        height: '500'
    }); 
}
</script>