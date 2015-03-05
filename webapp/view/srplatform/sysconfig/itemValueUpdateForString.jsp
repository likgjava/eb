<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/itemValueUpdateForString.js"></script>
		<div class="formLayout">
		<form id="itemValueUpdateForStringForm" method="post">
			<h4><span>配置条目数据</span></h4>
			<!-- 隐藏不显示的数据 -->
			<!-- 系统配置类型ID -->
			<input type="hidden" name="dataType" id="dataType" value=""/>
			<input type="hidden" name="objId" id="objId" value="${objId }"/>
		    <ul>
		    	<li>
		     		<label><spring:message code="sysConfigItemValueForm.value"/>:</label>
		       		<input type="text" name="value" id="value" class="required"  />
		       		<span class="eleRequired">*</span>
				</li>
				<li class="formTextarea">
		     		<label><spring:message code="sysConfigItemValueForm.notes"/>:</label>
		       		<textarea name="notes" id="notes" ></textarea>
				</li>
			</ul>			
			<div class="conOperation">
				<button id="itemValueUpdateForStringSave" type="button" ><span><spring:message code="globe.save"/></span></button>
		        <button id="itemValueUpdateForStringDelete" type="button" ><span><spring:message code="globe.delete"/></span></button>
		        <button id="itemValueUpdateForStringReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
		   	</div>
		</form>
		</div>
