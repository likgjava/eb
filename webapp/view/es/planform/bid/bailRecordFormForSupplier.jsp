<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordFormForSupplier.js"></script>
<div class="formLayout form2Pa">
		<input type="hidden" value="${projectId}" id="projectIds" name="projectIds"/>
	<form id="bailRecordForm" method="post">
		<h5><span>录入保证金</span></h5>
		<ul>
			<li>
				<label class="short">保证金（元）：</label>
				<input type="text" value="${bailRecord.ballMoney}" id="ballMoney" name="ballMoney" class="required money"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">是否结转：</label>
				<input type="checkbox" id="carryForward" name="carryForward" <c:if test="${bailRecord.carryForward==true}">checked="checked"</c:if>/>
			</li>
			<li class="fullLine">
				<label class="short">支付方式：</label>
				<input type="radio" id="payType" name="payType" value="01" <c:if test="${bailRecord.payType==01}">checked="checked"</c:if>/>汇票&nbsp;
				<input type="radio" id="payType" name="payType" value="02" <c:if test="${bailRecord.payType==02}">checked="checked"</c:if>/>支票&nbsp;
				<input type="radio" id="payType" name="payType" value="03" <c:if test="${bailRecord.payType==03}">checked="checked"</c:if>/>电汇&nbsp;
				<input type="radio" id="payType" name="payType"  value="04" <c:if test="${bailRecord.payType==04}">checked="checked"</c:if>/>现金&nbsp;
				<input type="radio" id="payType" name="payType" value="05" <c:if test="${bailRecord.payType==05}">checked="checked"</c:if>/>其他
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">联系人：</label>
				<input type="text" value="${bailRecord.linker}" id="linker" name="linker" class="required"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">联系方式：</label>
				<input type="text" value="${bailRecord.linkerTel}" id="linkerTel" name="linkerTel" class="required cnPhone"/>
				<span class="eleRequired">*</span>
			</li>
			<c:choose>
				<c:when test="${bailRecord!=null&&bailRecord.objId!=null}">
					<li class="fullLine">
					<label class="short">缴纳证明：</label>
						<input type="hidden" name="attachRelaId1" id="attachRelaId1" value=""/>	      
						<input type="file" name="attachRelaIdBak" id="attachRelaIdBak"/>
					</li>
					<li class="fullLine">
						<label class="short">缴纳证明文件：</label>
						<input type="hidden" id="attachRelaId2" value="${bailRecord.renderAtt}"/>	      
						<div id="attachRelaId3" class="uploadFile"></div>
					</li>
				</c:when>
				<c:otherwise>
					<li class="fullLine">
						<label class="short">缴纳证明：</label>
						<input type="hidden" name="attachRelaId1" id="attachRelaId1" value=""/>	      
						<input type="file" name="attachRelaIdBak" id="attachRelaIdBak"/>
					</li>
				</c:otherwise>
			</c:choose>
			<li class="fullLine">
				<label class="short">备注：</label>
				<textarea rows="2" cols="2" id="remark" name="remark" style="height: 70px;width: 500px;text-align: left" maxlength="1000" class="maxInput">
				${bailRecord.remark}
				</textarea>
				<span class="eleRequired"></span>
			</li>
		</ul>
		<div class="conOperation">
			<button id="dosBuyRecordSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		</div>
	</form>
</div>