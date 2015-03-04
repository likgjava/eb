<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/evaSellerRecordForm.js"></script>

<div class="formLayout form2Pa">        
	<form id="evaSellerRecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="supplierId" id="supplierId" value="${supplierId}"/>
		<input type="hidden" name="subProjId" id="subProjId"  value="${subProjId}"/>
		<input type="hidden" name="projectId" id="projectId"  value="${projectId}"/>
		<!-- 用于从前一页面接收数据的标识号 以便去获取对应的页面数据 -->
		<input type="hidden" name="count" id="count"  value="${count}"/>
     		<ul>

					<li>
						<label for="supplierName"><spring:message code="evaSellerRecordForm.supplierName"/></label>
						<!-- 隐藏 为了form表单提交 -->
						<input type="hidden" name="supplierName" id="supplierName" value=""  />
						<span id="supplierNameView"></span>
					</li>
                    
                 	<li>
						<label for="quoteSum"><spring:message code="evaSellerRecordForm.quoteSum"/></label>
						<input type="hidden" name="quoteSum" id="quoteSum" value=""/>
						<span id="quoteSumView"></span>
					</li>
					<li>
						<label for="factorScore"><spring:message code="evaSellerRecordForm.factorScore"/></label>
						<input type="text" name="factorScore" id="factorScore" class="required" max="100.00" min="0.00"/>
							<span class="eleRequired">*</span>
					</li>
					
                    <li>
						<label for="recommend"><spring:message code="evaSellerRecordForm.recommend"/></label>
	        			<input type="radio" name="recommend" id="recommend_00" value="00" checked="checked" />不推荐&nbsp;&nbsp;
	        			<input type="radio" name="recommend" id="recommend_01" value="01" />推荐&nbsp;&nbsp;
					</li>
				
                    <li class="formTextarea">
		      	    <label for="factorOpinion"><spring:message code="evaSellerRecordForm.factorOpinion"/>：</label>
		            <textarea name="factorOpinion" id="factorOpinion" cols="" rows="5"  class="required maxInput" maxlength="500">${subProject.remark } </textarea>
		            <span class="eleRequired">*</span>
		 	        </li>
		</ul>
		<div class="conOperation">
			<button id="evaSellerRecordSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="evaSellerRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>