<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/itemValueAddForList.js"></script>
		
			<form method="post" id="itemValue">
			<input type="hidden" name="itemValueObjIds"></input>
			<input type="hidden" id="sysConfigItem_objId" name="sysConfigItemId" class="required" value="${itemId}"/>
				<div class="partContainers">
		       		<table id="preValuesTable" class="tableList" align="left" border="1" width="70%">
		       			<caption><h5><spring:message code="sysConfigItemForm.preValues"/></h5></caption>
		       			<thead>
			       			<tr class="center">
			       				<th >选择</th>
		       					<th >预设名称</th>
		       					<th >预设值</th>
		       					<th >配置值</th>
		       					<th >配置值名称</th>
		       					<th >备注</th>
		       				</tr>
	       				</thead>
	       				<tbody>
		       				<c:forEach items="${sysConfigItemPreValueList}" var="sysConfigItemPreValue">
		       				<c:set var="i" value="0"/>
		       					<c:forEach items="${sysConfigItemValueList}" var="sysConfigItemValue">
		       						<c:if test="${sysConfigItemPreValue.objId == sysConfigItemValue.sysConfitItemPreValue.objId}">
		       							<c:set var="i" value="1"/>
		       						</c:if>
		       					</c:forEach>
		       					<tr>
		       						<td class="center"><input type='checkbox' name='values' value='${sysConfigItemPreValue.objId}'  
		       							<c:if test="${i == 1}">checked=checked</c:if>
		       						></input></td>
		       						<td>${sysConfigItemPreValue.name}</td>
		       						<td>${sysConfigItemPreValue.code}</td>
		       						<c:if test="${i == 1}">
			       						<c:forEach items="${sysConfigItemValueList}" var="sysConfigItemValue">
			       							<c:if test="${sysConfigItemPreValue.objId == sysConfigItemValue.sysConfitItemPreValue.objId  }">
			       								<td><input name="value${sysConfigItemPreValue.objId}" value="${sysConfigItemValue.value}" class="required"></input><span class="eleRequired">*</span></td>
			       								<td><input name="preValueName${sysConfigItemPreValue.objId}" value="${sysConfigItemValue.preValueName}"  class="required"></input><span class="eleRequired">*</span></td>
			       								<td><input name="notes${sysConfigItemPreValue.objId}" value="${sysConfigItemValue.notes}"  class="required"></input><span class="eleRequired">*</span></td>
			       							</c:if>
			       						</c:forEach>
		       						</c:if>
		       						<c:if test="${i == 0}">
		       							<td><input name="value${sysConfigItemPreValue.objId}" value="" class="required"></input><span class="eleRequired">*</span></td>
		       							<td><input name="preValueName${sysConfigItemPreValue.objId}" value="${sysConfigItemPreValue.name}" class="required"></input><span class="eleRequired">*</span></td>
		       							<td><input name="notes${sysConfigItemPreValue.objId}" value=""  class="required"></input><span class="eleRequired">*</span></td>
		       						</c:if>
		       					</tr>
		       				</c:forEach>
		       			</tbody>
		       		</table>
		       	</div>
		</form>	
		
		<div class="conOperation">
			<button id="itemValueAddForListSave" type="button" ><span><spring:message code="globe.save"/></span></button>
	        <button id="itemValueAddForListReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
		</div>