<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/point/cash_form.js"></script>
<div class="formZone">         
	<form id="cashForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">兑现积分       <spring:message code="globe.input.required.prompt"/>  积分兑现规则：兑现积分不允许出现小数。</div>
     	<div class="formLayout form2Pa">
            <ul>
              <li class="formFieldset">
	     	     <label>兑换额度：</label>
			     <input type="text" name="cashNumber" id="cashNumber" class="required digits"  />
			     <span class="eleRequired">*</span>
                 <span style="color: red;">当前有效的积分为${nonce}，交易的积分不能超过有效积分。</span>
                 <input type="hidden" id="nonce" value="${nonce}">
	          </li>
              <li class="formFieldset">
	     	     <label>兑现金额：</label>
			     <input type="text" name="cashMoney" id="cashMoney" class="required money" disabled="disabled"/>
			     <span class="eleRequired">*</span>
	          </li>
			  <li class="formTextarea">
		          <label>备注：</label>
		          <textarea name="cashMemo" id="cashMemo" cols="" rows="5" style="width: 540px"></textarea>
              </li>
			  <li class="fullLine">
				 <label for="">附件：</label>
				 <div id="cashFileDiv" style="width: 600px;" class="uploadFile"></div>
              </li>
            </ul>
		</div>
	    <div class="conOperation">
	        <button class="btn primary" id="cashSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	        <button class="btn primary" id="cashReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	        <button class="btn" type="reset" id="cashReset" tabindex="20" ><span><spring:message code="globe.reset"/></span></button>
	    </div>
	</form>
</div>