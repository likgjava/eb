<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/confirmBailRecordPage.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input type="hidden" id="_project_id" value="${projId}"></input>
<input type="hidden" id="currentTabId" value=""></input>
<input type="hidden" id="isNotFirst" value="${isNotFirst}">
<div id="openbidGeneralList">
	<ul>
		<c:forEach items="${packList}" var="pack">
			<li><a href="#${pack.objId}"  class="refreshData" ids="${pack.objId}"><span>${pack.projName}</span></a></li>
		</c:forEach>
	</ul>

	<c:forEach items="${packList}" var="pack">
		<div id="${pack.objId}">
			<table class="tableList">
				<tr>
			    	<th>投标单位名称</th>
			    	<th>报名情况</th>
			    	<th>保证金（元）</th>
			    	<th>联系人</th>
			    	<th>联系电话</th>
			    	<th>是否参与投标</th>
		   		</tr>
		   		   <c:forEach items="${signUprecordList}" var="signUprecord">
		   		   		<c:if test="${signUprecord.project.objId==pack.objId}">
		   		   		<tr>
			   		   		<td title="${signUprecord.supplier.orgName}">${signUprecord.supplierName}</td>
			   		        <td>${signUprecord.auditStatusCN}</td>    
				   		   		<c:forEach items="${bailRecordList}" var="bailRecord">
			   		   				<c:choose>
										<c:when test="${bailRecord.signUprecord.objId==signUprecord.objId}">
											<td>${bailRecord.ballMoney}</td>
										</c:when>
									</c:choose>
				   		   		</c:forEach>
			   		   		<td>${signUprecord.linker}</td>    
			   		   		<td>${signUprecord.linkerTel}</td> 
			   		   		<td align="center">
			   		   			<c:if test="${signUprecord.auditStatus==01}">
				   		   			<input type="checkbox" name="confirmBailRecord" id="${signUprecord.supplier.objId}|${projId}|${pack.objId}|${signUprecord.linker}|" value="" <c:if test="${signUprecord.isBid =='isBid'}">checked='checked' disabled='disabled'</c:if> />
				   		   		</c:if>
				   		   		<c:if test="${signUprecord.auditStatus==02}">
				   		   			<input type="checkbox" name="falseBailRecord" id="${signUprecord.supplier.objId}|${projId}|${pack.objId}|${signUprecord.linker}|" value="" <c:if test="${signUprecord.isBid =='isBid'}">checked='checked' disabled='disabled'</c:if> />
				   		   		</c:if>
				   		   		<c:if test="${signUprecord.auditStatus==00}">
				   		   			<input type="checkbox" name="falseBailRecord" id="${signUprecord.supplier.objId}|${projId}|${pack.objId}|${signUprecord.linker}|" value="" <c:if test="${signUprecord.isBid =='isBid'}">checked='checked' disabled='disabled'</c:if> />
				   		   		</c:if>
			   		   		</td>     
		   		   		</tr>
		   		   		</c:if>
		   		   </c:forEach>
			</table>
		</div>
	</c:forEach>
	
	<div class="conOperation">
		<button tabindex="18" type="button" id="confirmBailRecordSave"><span>保存</span></button>
	</div>	
</div>