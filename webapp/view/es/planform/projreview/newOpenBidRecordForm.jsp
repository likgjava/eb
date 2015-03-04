<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/newOpenBidRecordForm.js"></script>

<div class="formLayout form2Pa">        
	<form id="openBidRecordForm" action="OpenBidRecordController.do?method=saveOpenBidRecord" method="post" enctype="multipart/form-data">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="supplierId" id="supplierId" value="${param.supplierId}"/>
		<input type="hidden" name="projectId" id="projectId"  value="${param.projectId}"/>
		<input type="hidden" name="subProjId" id="subProjId"  value="${param.subProjId}"/>
		<input type="hidden" name="bidId" id="bidId"  value="${param.bidId}"/>
		<input type="hidden" name="openbidGeneralviewObjId" id="openbidGeneralviewObjId"></input>
		<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
		<input type="hidden" name="quoteSum" id="quoteSum" value="${param.quoteSum}">
		<!-- 用于从前一页面接收数据的标识号 以便去获取对应的页面数据 -->
		<input type="hidden" name="count" id="count"  value="${param.count}"/>
     		<ul>

					<li>
						<label for="sellerName"><spring:message code="openBidRecordForm.sellerName"/></label>
						<!-- 隐藏 为了form表单提交 -->
						<input type="hidden" name="sellerName" id="sellerName" value=""  />
						<span id="sellerNameView"></span>
					</li>
                    
                 	<li>
						<label for="quoteSum"><spring:message code="openBidRecordForm.quoteSum"/></label>
						<input type="hidden" name="quoteSum" id="quoteSum" value=""/>
						<span id="quoteSumView"></span>
					</li>
		</ul>
	<!--  	<ul>
			<li class="fullLine">
				<label for="sellerName">开标一览表</label>
				<input type="file" size="61" id="attachRealFile" name="attachRealFile" onPropertyChange="evaSellerRecordForm.changeFile()"></input>
				<input type="hidden" name="attachRealFileId" id="attachRealFileId"></input>
				<span id="operate_div"></span><span id="download_div"></span>
			</li>
		</ul>
	-->	
		<div class="conOperation">
			<button id="openBidRecordSave" type="submit" tabindex="18"><span>录入</span></button>
			<button id="openBidRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>