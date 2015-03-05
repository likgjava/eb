<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/point/deal_form.js"></script>
<div class="formZone">         
	<form id="dealForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">交易积分      <spring:message code="globe.input.required.prompt"/></div>
     	<div class="formLayout form2Pa">
           <ul>
              <li class="formFieldset">
	     	     <label>赠送给：</label>
			     <input type="text" name="userIdes"  id="toUser" class="required"  />
			     <span class="eleRequired">*</span>
	          </li>
              <li class="formFieldset">
	     	     <label>交易额度：</label>
			     <input type="text" name="dealNumber" id="dealNumber" class="required digits"  />
			     <span class="eleRequired">*</span>
                 <input type="hidden" id="nonce" value="${nonce}">
                 <span style="color: red;">当前有效的积分为${nonce}，交易的积分不能超过有效积分。</span>
	          </li>
			  <li class="formTextarea">
		          <label>备注：</label>
		          <textarea name="dealMemo" id="dealMemo" cols="" rows="5" style="width: 540px"></textarea>
              </li>
           </ul>
		</div>
	    <div class="conOperation">
	        <button class="btn primary" id="dealSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	        <button class="btn primary" id="dealReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	        <button class="btn" type="reset" id="dealReset" tabindex="20" ><span><spring:message code="globe.reset"/></span></button>
	    </div>
	</form>
</div>