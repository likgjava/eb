<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordForConfirm.js"></script>

<div class="formLayout form2Pa">        
	<h5><span>录入保证金</span></h5>
		<ul>
			<li class="fullLine">
				<label class="short">投标单位：</label>
				<span>${bailRecord.supplyerName}</span>
			</li>
			<li>
				<label class="short">保证金（元）：</label>
				<span>${bailRecord.ballMoney}</span>
			</li>
			<li>
				<label class="short">是否结转：</label>
				<c:choose>
					<c:when test="${bailRecord.carryForward==true}">
						<span>是</span>
					</c:when>
					<c:otherwise>
						<span>否</span>
					</c:otherwise>
				</c:choose>
				
			</li>
			<li>
				<label class="short">支付方式：</label>
				<span>${bailRecord.payTypeCN}</span>
			</li>
			<li>
				<label class="short">确认状态：</label>
				<span>${bailRecord.useStatusCN}</span>
			</li>
			<li>
				<label class="short">联系人：</label>
				<span>${bailRecord.linker}</span>
			</li>
			<li>
				<label class="short">联系方式：</label>
				<span>${bailRecord.linkerTel}</span>
			</li>
			<li class="fullLine">
				<label class="short">缴纳证明：</label>
				<div id="attachRelaId3" class="uploadFile"></div>
				<input type="hidden" id="attachRelaId2" value="${bailRecord.renderAtt}"/>
			</li>
			<li class="fullLine">
				<label class="short">备注：</label>
				<span>${bailRecord.remark}</span>
			</li>
		</ul>
</div>
<div class="formLayout" id="signUpRespone" style="padding-top:0px">
	<h5><span>审核意见</span></h5>
		<form id="dosBuyRecordConfirmForm" method="post">
		<ul>
			<li style="height:50px;">
				<input type="hidden" name="bailRecordId" id="bailRecordId" value="${bailRecord.objId}"/>
				<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
				<span class="eleRequired"></span>
			</li>
		</ul>
	</form>
</div>
<div class="conOperation">
     <button id="passButton" type="button" tabindex="18"><span>通过</span></button>
	<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
	<button id="bailRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>