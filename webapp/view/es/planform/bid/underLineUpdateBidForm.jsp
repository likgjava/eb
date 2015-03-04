<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/underLineUpdateBidForm.js"></script>
<div class="partContainers">
	<input type="hidden" id="isShowFactor" value="${isShowFactor}"/>
	<div class="formLayout form2Pa">
	<form id="bidForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bidItem"/>
		<input type="hidden" name="PACK_IDS"/>
		<input type="hidden" name="objId" id="objId" value="${bid.objId}"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="fromDiv" id="fromDiv" value="${param.fromDiv}"/>
		<input type="hidden" name="tenderStartTime" id="tenderStartTime" value="${projectRule.submitStTime}"/>
		<input type="hidden" name="tenderEndTime" id="tenderEndTime" value="${projectRule.submitETime}"/>
		<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
		<input type="hidden" name="supplierName" value="${signUprecord.supplier.orgName}"></input>
		<input type="hidden" name="projName" value="${bid.projName}"></input>
		<input type="hidden" name="projCode" value="${bid.projCode}"></input>
		<input type="hidden" name="project.objId" value="${bid.project.objId}"></input>
		<input type="hidden" name="isDividePack" id="isDividePack" value="${projectRule.isDividePack}"/>
		<input type="hidden" name="openBidSTime" id="openBidSTime" value="${openBidSTime}"/>
		<input type="hidden" name="packIds" id="packIds" value="${packIds }"/>
			<h5><span id="bidmessage"><dm:out value="local__bidResult" tenderType="${project.tenderType}">录入开标结果</dm:out></span></h5>
			<div>
				<table>
		     		<tr>
						<th><label class="short">
						<c:if test="${projectRule.isDividePack==false}">
							所投项目：
						</c:if>
						<c:if test="${projectRule.isDividePack==true}">
							所投<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>：
						</c:if>
						</label></th>
						<td>
						   <c:choose>
						    <c:when test="${projectRule.isDividePack==false}">
						        ${project.projName}&nbsp;
						    </c:when>
						    <c:otherwise>
						    <c:forEach items="${project.subProject}" var="subProject">
								<c:set var="bidPackListNum" value="0"/>
								<c:forEach items="${bidPackList}" var="bidPack">
									<c:if test='${bidPack.objId == subProject.objId}'>
										<c:set var="bidPackListNum" value="${bidPackListNum+1}"/>
									</c:if>
								</c:forEach>
								<c:if test='${bidPackListNum > 0}'>
									<input value="${subProject.objId }" type="checkbox" <c:if test="${signUprecord.project.objId == subProject.objId }">checked="checked"</c:if>/> ${subProject.projName}&nbsp;
								</c:if>
								<c:if test='${bidPackListNum == 0}'>
									<input value="${subProject.objId }" type="checkbox"/> ${subProject.projName}&nbsp;
								</c:if>
							</c:forEach>
						    </c:otherwise>
						   </c:choose>
						</td>
						<th><label for="supplierName"  class="short">投标单位名称：</label></th>
						<td>
							<span id="supplierName"><c:out value="${signUprecord.supplier.orgName}"/></span> 
							<input type="hidden" name="supplier.objId" id="supplier.objId" value="${signUprecord.supplier.objId}">
						</td>
					</tr>	
						<tr>
							<th><label for="bidStatement"  class="short"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>声明：</label></th>
							<td colspan="3">
								<input type="text" name="bidStatement" id="bidStatement" class="required" value="${bid.bidStatement}"/>
								<span class="eleRequired">*</span>
							</td>
						</tr>
						  <c:if test="${project.tenderType == '04'}">
						<tr>
							<th><label for="projManagerName"  class="short">项目经理：</label></th>
							<td colspan="3">
								<input type="text" name="projManagerName" id="projManagerName" class="required" value="${bid.projManagerName}"/>
								<span class="eleRequired">*</span>
							</td>
						</tr>
						</c:if>
						<tr>
							<th><label for="bidQuoteSum"  class="short">报价总金额(元)：</label></th>
							<td colspan="3">
								<input type="text" name="bidQuoteSum" id="bidQuoteSum" class="required money checkBail" value="${bid.bidQuoteSum}"/>
								<span class="eleRequired">*</span>
							</td>
						</tr>
						
				</table>
				</div>
				<div id="bid_items_table" ></div>
				<div >
					<table >
						<tr>
							<th><label for="bidLinker"  class="short"><spring:message code="bidForm.bidLinker"/>：</label></th>
							<td colspan="3">
								<input type="text" name="bidLinker" id="bidLinker" class="required" value="${bid.bidLinker}" />
								<span class="eleRequired">*</span>
							</td>
						</tr>
						<tr>
							<th><label for="bidLinkerIdCard"  class="short"><spring:message code="bidForm.bidLinkerIdCard"/>：</label></th>
							<td colspan="3">
								<input type="text" name="bidLinkerIdCard" id="bidLinkerIdCard" class="required cnIdCard" value="${bid.bidLinkerIdCard}"/>
								<span class="eleRequired">*</span>
							</td>
						</tr>
						
					</table>
				</div>
			</form>
			<div >
			<table >
			<tr>
				<th ><label for="price"  class="short" ><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>文件<br><span class="eleRequired">(必填项*)</span>：</label></th>
				<td colspan="3"><div class="uploadFile" id="attachRelaId">${bid.attachRelaId}</div></td>
			</tr>
			</table>
			</div>
			<div >
				<div>
					<table >
						<tr>
							<th ><label for="bidRemark"  class="short" ><spring:message code="bidForm.bidRemark"/>：</label></th>
							<td colspan="3">
								<textarea name="bidRemark" id="bidRemark" class="maxInput" maxlength="200" style="width: 99%;height: 50px">${bid.bidRemark}</textarea>
							 	<span class="eleRequired"></span>
							</td>
						</tr>
					</table>
				</div>
				<div class="conOperation">
					<button id="bidSave" type="button" tabindex="18"><span>录入</span></button>
				</div>
		</div>
		</div>
</div>