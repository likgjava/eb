<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordFormForAgent.js"></script>
<div class="formLayout form2Pa">
		<input type="hidden" value="${param.projectId}" id="projectIds" name="projectIds"/>
	<form id="bailRecordForm" method="post">
		<h5><span>录入保证金</span></h5>
		<ul>
			<li>
				<label class="short">投标单位：</label>
				<input type="text" name="supplyerName" id="supplyerName" class="required" value=""/>
				<span class="eleRequired">*</span>
     			<input type="hidden" value="" name="supplyerId" id="supplyerId"/>
			</li>
			<li>
				<label class="short">保证金（元）：</label>
				<input type="text" value="" id="ballMoney" name="ballMoney" class="required money"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">是否结转：</label>
				<input type="checkbox" id="carryForward" name="carryForward" />
			</li>
			<li class="fullLine">
				<label class="short">支付方式：</label>
				<input type="radio" id="payType" name="payType" value="01" />汇票&nbsp;
				<input type="radio" id="payType" name="payType" value="02"/>支票&nbsp;
				<input type="radio" id="payType" name="payType" value="03" />电汇&nbsp;
				<input type="radio" id="payType" name="payType"  value="04"/>现金&nbsp;
				<input type="radio" id="payType" name="payType" value="05" />其他
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">联系人：</label>
				<input type="text" value="" id="linker" name="linker" class="required"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">联系方式：</label>
				<input type="text" value="" id="linkerTel" name="linkerTel" class="required cnPhone"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class="short">缴纳证明：</label>
				<input type="hidden" name="attachRelaId1" id="attachRelaId1" value=""/>	      
				<input type="file" name="attachRelaIdBak" id="attachRelaIdBak"/>
			</li>
			<li class="fullLine">
				<label class="short">备注：</label>
				<textarea rows="2" cols="2" id="remark" name="remark" style="height: 70px;width: 510px;text-align: left" maxlength="1000" class="maxInput">
				</textarea>
				<span class="eleRequired"></span>
			</li>
		</ul>
		<div class="conOperation">
			<button id="bailRecordSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="bailRecordClose" type="button" tabindex="18"><span>关闭</span></button>
		</div>
	</form>
</div>