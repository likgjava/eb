<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/competitive/NegotiateResultForm.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
	<input type="hidden" id="isShowFactor" value="${isShowFactor}"/>
	<form id="bidForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="PACK_IDS"/>
		<input type="hidden" name="objId" id="objId" value="${bid.objId}"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="fromDiv" id="fromDiv" value="${param.fromDiv}"/>
		<input type="hidden" name="tenderStartTime" id="tenderStartTime" value="${projectRule.submitStTime}"/>
		<input type="hidden" name="tenderEndTime" id="tenderEndTime" value="${projectRule.submitETime}"/>
		<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
		<input type="hidden" name="supplierName" value="${user.orgInfo.orgName}"></input>
		<input type="hidden" name="projName" value="${bid.projName}"></input>
		<input type="hidden" name="projCode" value="${bid.projCode}"></input>
		<input type="hidden" name="project.objId" value="${bid.project.objId}"></input>
		<h5><span>录入谈判结果</span></h5>
		<table>
     		<tr>
				
				<th><label >所投<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>：</label></th>
				<td>
					<c:forEach items="${project.subProject}" var="subProject">
						<c:set var="bidPackListNum" value="0"/>
						<c:forEach items="${bidPackList}" var="bidPack">
							<c:if test='${bidPack.objId == subProject.objId}'>
								<c:set var="bidPackListNum" value="${bidPackListNum+1}"/>
							</c:if>
						</c:forEach>
						<c:if test='${bidPackListNum > 0}'>
							<input value="${subProject.objId }" type="checkbox" checked="checked"/> ${subProject.projName}&nbsp;
						</c:if>
						<c:if test='${bidPackListNum == 0}'>
							<input value="${subProject.objId }" type="checkbox"/> ${subProject.projName}&nbsp;
						</c:if>
					</c:forEach>
				</td>
				<th><label for="supplierName">投标单位名称：</label></th>
				<td>
					<span id="supplierName"><c:out value="${user.orgInfo.orgName}"/></span> 
						<input type="hidden" name="supplier.objId" id="supplier.objI" value="${user.orgInfo.objId}">
				</td>
			</tr>	
				<tr>
					<th><label for="bidBrand"><spring:message code="bidForm.bidBrand"/>：</label></th>
					<td><input type="text" name="bidBrand" id="bidBrand" class="required" value="${bid.bidBrand}"/>
						    <span class="eleRequired">*</span>
					</td>
					<th><label for="bidStatement"><spring:message code="bidForm.bidStatement"/>：</label></th>
					<td>
						<input type="text" name="bidStatement" id="bidStatement" class="required" value="${bid.bidStatement}"/>
						<span class="eleRequired">*</span>
					</td>
				</tr>
				<tr>
					<th><label for="bidQuoteSum"><spring:message code="bidForm.bidQuoteSum"/>：</label></th>
					<td>
						<input type="text" name="bidQuoteSum" id="bidQuoteSum" class="required money" maxlength="16" value="${bid.bidQuoteSum}"/>
						<span class="eleRequired">*</span>
					</td>
					<th><label for="bidLinker"><spring:message code="bidForm.bidLinker"/>：</label></th>
					<td>
						<input type="text" name="bidLinker" id="bidLinker" class="required" value="${user.emp.name }" />
						<span class="eleRequired">*</span>
					</td>
				</tr>
				<tr>
					<th><label for="bidLinkerIdCard"><spring:message code="bidForm.bidLinkerIdCard"/>：</label></th>
					<td colspan="3">
						<input type="text" name="bidLinkerIdCard" id="bidLinkerIdCard" class="required" value="${user.emp.idCard }"/>
						<span class="eleRequired">*</span>
					</td>
				</tr>
				<tr>
					<th><label for="price">递交谈判响应文件：</label></th>
					<td colspan="3"><div class="uploadFile" id="attachRelaId">${bid.attachRelaId}</div></td>
				</tr>
			</table>
			<table>
			<tr>
			<td>
			<div id="factorListInfo"></div>
			</td>
			<td></td>
			</tr>
			</table>
			<table>
				<tr>
					<th><label for="bidRemark"><spring:message code="bidForm.bidRemark"/>：</label></th>
					<td colspan="3">
						<textarea name="bidRemark" id="bidRemark" class="maxInput" maxlength="200" style="width: 95%;height: 50px">${bid.bidRemark}</textarea>
					 	<span class="eleRequired"></span>
					</td>
				</tr>
		</table>
		<div class="conOperation">
			<button id="bidSave" type="button" tabindex="18"><span>保存</span></button>
		</div>
	</form>
</div>
</div>