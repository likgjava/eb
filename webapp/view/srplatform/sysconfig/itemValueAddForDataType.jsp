<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/itemValueAddForDataType.js"></script>
<div class="formLayout">
	<input type="hidden" id="_value" value="${sysConfigItemValue.value}"></input>
	<form id="itemValueAddForStringForm" method="post">
		<h5><span>配置条目数据</span></h5>
		<input type="hidden" name="objId" value="${sysConfigItemValue.objId}"></input>
		<input type="hidden" name="sysConfigItem.objId" id="sysConfigType.objId" class="required" value="${itemId}"/>
		<input type="hidden" name="dataType" id="dataType" class="required" value="${dataType}"/>
	    <table>
	    	<tr>
	    		<th>数据类型</th>
	    		<td>
		    		<c:if test='${dataType == "String"}'>
		     			文本类型
		     		</c:if>
		     		<c:if test='${dataType == "number"}'>
	     				数字类型
	     			</c:if>
	     			<c:if test='${dataType == "Boolean"}'>
		     			布尔类型
	     			</c:if>
	     			<c:if test='${dataType == "Date"}'>
	     				日期类型
	     			</c:if>
	    		</td>
	    	</tr>
	    	<tr>
	     		<th><label><spring:message code="sysConfigItemValueForm.value"/>:</label></th>
	     		<td>
	     		<c:if test='${dataType == "Boolean"}'>
	     			是<input type="radio" name="value" value="1" checked="checked"></input>
	     			否<input type="radio" name="value" value="0"></input>
	     		</c:if>
	     		<c:if test='${dataType == "String"}'>
	     			<input type="text" name="value" id="value" class="required" value="${sysConfigItemValue.value}" style="width: 210px"/>
	     		</c:if>
	     		<c:if test='${dataType == "number"}'>
	     			<input type="text" name="value" id="value" class="required digits" value="${sysConfigItemValue.value}" />
	     		</c:if>
	     		<c:if test='${dataType == "Date"}'>
	     			<input type="text" name="value" id="value" class="required" readonly="readonly"" value="${sysConfigItemValue.value}" />
	     		</c:if>
	       		<span class="eleRequired">*</span>
	       		</td>
			</tr>
			<tr>
	     		<th><label><spring:message code="sysConfigItemValueForm.notes"/>:</label></th>
	       		<td><textarea name="notes" id="notes" class="required" style="width: 95%;height: 35px">${sysConfigItemValue.notes}</textarea>
	       		<span class="eleRequired">*</span></td>
			</tr>
		</table>			
	</form>
</div>
<div class="conOperation">
	<button id="itemValueAddForStringSave" type="button" ><span><spring:message code="globe.save"/></span></button>
    <button id="itemValueAddForStringReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
</div>
