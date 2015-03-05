<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/itemValueAddForString.js"></script>
		<div class="formLayout">
		<form id="itemValueAddForStringForm" method="post">
			<h4><span>配置条目数据</span></h4>
			<!-- 隐藏不显示的数据 -->
			<!-- 系统配置类型ID -->
			<input type="hidden" name="sysConfigItem.objId" id="sysConfigType.objId" class="required" value="${itemId }"/>
			<input type="hidden" name="dataType" id="dataType" class="required" value="${dataType }"/>
		    <ul>
		    	<li>
		     		<label><spring:message code="sysConfigItemValueForm.value"/>:</label>
		       		<input type="text" name="value" id="value" class="required"  />
		       		<span class="eleRequired">*</span>
				</li>
				<li class="formtextarea">
		     		<label><spring:message code="sysConfigItemValueForm.notes"/>:</label>
		       		<textarea name="notes" id="notes" ></textarea>
				</li>
			</ul>			
			<div class="conOperation">
				<button id="itemValueAddForStringSave" type="button" ><span><spring:message code="globe.save"/></span></button>
		        <button id="itemValueAddForStringReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
		   	</div>
		</form>
		</div>
