<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysconfigItemUpdate.js"></script>
<div class="formLayout">
	<form id="sysconfigtypeUpdateForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<h5><span>修改系统配置条目信息</span></h5>
		<input type="hidden" name="itemPath" id="itemPath"  value=""/>
	    <ul>
     		<li>
	       		<label><spring:message code="sysConfigItemForm.name"/>:</label>
	       		<input type="text" name="name" id="name" class="required" />
	       		<span class="eleRequired">*</span>
	       </li>
	       <li>
	       		<label><spring:message code="sysConfigItemForm.code"/>:</label>
	       		<input type="text" name="code" id="code" class="required" />
	       		<span class="eleRequired">*</span>
	       </li>
	       <li>
	       		<label><spring:message code="sysConfigItemForm.dataType"/>:</label>
	       		<select id="dataType" name="dataType">
					<option value="String">文本类型</option>
					<option value="List">枚举类型</option>
					<option value="number">数字类型</option>
					<option value="Boolean">布尔类型</option>
					<option value="Date">日期类型</option>
				</select>
	       </li>
	       <li id="preValueLi" style="display:none">
	       		<label>新增<spring:message code="sysConfigItemForm.preValues"/>:</label>
       			<input type="text" name="preValueName" id="preValueName"   />
			    <input type="text" name="preValueCode" id="preValueCode"   />
			    <button id="preValueAdd" type="button" ><span>添加</span></button>      		
	       </li>
	       <li id="preValuesLi" style="display:none">
	       		<label><spring:message code="sysConfigItemForm.preValues"/>:</label>
	       		<table id="preValuesTable"  align="left" border="1" width="70%">
	       			<tr>
       					<td width="45%" align="center">预设名称
       					</td>
       					<td width="45%" align="center">预设值
       					</td>
       					<td width="10%" align="center">
       					</td>
       				</tr>
	       		</table>
	       </li>
	       <li>
	       		<label><spring:message code="sysConfigItemForm.notes"/>:</label>
	       		<input type="text" name="notes" id="notes" class="required" />
	       		<span class="eleRequired">*</span>
	       </li>
		</ul>
	</form>
</div>
<div class="conOperation">
	<button id="sysConfigItemUpdateUpdateSave" type="button" ><span><spring:message code="globe.save"/></span></button>
    <button id="sysConfigItemUpdateReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
</div>