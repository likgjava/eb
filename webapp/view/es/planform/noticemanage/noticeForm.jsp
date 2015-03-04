<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/noticemanage/noticeForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="noticeForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="<c:out value="${param.projectId}"/>"/>
		<h4><span><dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></span></h4>
     		<ul>

					<li>
						<label for="noticeTitle"><spring:message code="noticeForm.noticeTitle"/></label>
						<input type="text" name="noticeTitle" id="noticeTitle" class="required"/>
						<span class="eleRequired">*</span>	  
					</li>
					
					<li>
						<label for="noticeType"><spring:message code="noticeForm.noticeType"/></label>
						<select name="noticeType" id="noticeType" class="required">
							<option></option>
							<option value="00">成交<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></option>
							<option value="01">结果<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></option>
						</select>
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="projName"><spring:message code="noticeForm.projName"/></label>
						<input type="text" name="projName" id="projName" class="required"/>
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="projCode"><spring:message code="noticeForm.projCode"/></label>
						<input type="text" name="projCode" id="projCode" class="required"/>
						<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label for="subProjName"><spring:message code="noticeForm.subProjName"/></label>
						<input type="text" name="subProjName" id="subProjName"/>
					</li>

					<li>
						<label for="subProjCode"><spring:message code="noticeForm.subProjCode"/></label>
						<input type="text" name="subProjCode" id="subProjCode"/>
					</li>

					<li>
						<label for="sellerName"><spring:message code="noticeForm.sellerName"/></label>
						<select name="sellerId" id="sellerId" class="required">
							<c:forEach items="${signList}" var="sign">
								<option value="${sign.supplierId}">gys</option>
							</c:forEach>
						</select>
						<span class="eleRequired">*</span>
					</li>
			
					<li style="width:100%;height:300px">
						<label for="noticeContent"><spring:message code="noticeForm.noticeContent"/></label>
						<textarea name="noticeContent" id="noticeContent" class="required,hidden" style="width:530px;height:280px"/>
						<span class="eleRequired">*</span>
					</li>

					<li style="width:100%;height:90px">
						<label for="noticeRemark"><spring:message code="noticeForm.noticeRemark"/></label>
						<textarea name="noticeRemark" id="noticeRemark" style="width:530px;height:80px"></textarea>
					</li>

		</ul>
		<div class="conOperation">
			<button id="noticeSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
<!--			<button id="noticeReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>-->
		</div>
	</form>
</div>