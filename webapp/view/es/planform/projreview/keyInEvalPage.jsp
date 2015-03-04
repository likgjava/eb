<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/keyInEvalPage.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input type="hidden" id="quoteSum" value="${quoteSum}">
<input type="hidden" id="projectId" value="${projectId}">
<input type="hidden" id="supplierId" value="${supplierId}">
<input type="hidden" id="subProjId" value="${subProjId}">		
<div id="keyInEvalPage" class="formLayout form2Pa">
	<table class="tableList">
		<tr>
	    	<th>专家名称</th>
	    	<th>专家打分</th>
	    	<th>是否推荐</th>
	    	<th style="width: auto;">状态</th>
	    	<th style="width: auto;">操作</th>
   		</tr>
   		<tbody id="addExpert">
   		<c:set var="evaSellerRecordCount" value="100"></c:set>
		<c:forEach items="${evaSellerRecordlist}" var="evaSellerRecord">
  					<tr >
  					<td class="hidden">
  						<form id="${evaSellerRecordCount}">
  							<input type="hidden" name="objId" value="${evaSellerRecord.objId}"></input>
  							<input type="hidden" name="supplierId" value="${supplierId}"></input>
  							<input type="hidden" name="supplierName" value="${evaSellerRecord.supplierName}"></input>
  							<input type="hidden" name="quoteSum" value="${quoteSum}"></input>
  							<input type="hidden" name="factorScore" value="${evaSellerRecord.factorScore}"></input>
  							<input type="hidden" name="recommend" value="${evaSellerRecord.recommend}"></input>
  							<input type="hidden" name="num" value="${evaSellerRecord.num}"></input>
  							<input type="hidden" name="subProjId" value="${subProjId}"></input>
  							<input type="hidden" name="projectId" value="${projectId}"></input>
  							<input type="hidden" name="evalLinkerName" value="${evaSellerRecord.evalLinkerName}"></input>
  						</form>
  						</td>
  						<!-- <td style="text-align: left">
		   					<select name="evalLinker" id="evalLinker" class="required">
		        			<c:forEach items="${expertList}" var="expert" >	
		        			<option value="${expert.objId}" <c:if test="${1==1}">selected="selected"</c:if>>${expert.workgmName}</option>
		        			</c:forEach>
		    				</select>
  						</td> -->
  						<td style="text-align: center" type="evalLinker">${evaSellerRecord.evalLinkerName}</td>
  						<td style="text-align: center" type="factorScore">${evaSellerRecord.factorScore}</td>
  						<td  type="recommend" class="center">
  	                    <c:choose>
  	                      <c:when test="${evaSellerRecord.recommend == '00'}">不推荐</c:when>
  	                      <c:when test="${evaSellerRecord.recommend == '01'}"> 推荐</c:when>
  	                      <c:otherwise>未录入</c:otherwise>
  	                    </c:choose>
  						</td>
  						<c:if test="${evaSellerRecord.objId == null}">
   						<td style="text-align: center">未录入</td>
   						<td style="text-align: center"><a class="abtn" openBidRecordId="${evaSellerRecordCount}" onclick="keyInEvalPage.addOrUpdate('${evaSellerRecordCount}')" identifier="keyinbid"><span>录入</span></a></td>
  						</c:if>
  						<c:if test="${evaSellerRecord.objId != null}">
   						<td style="text-align: center">已录入</td>
   						<td style="text-align: center"><a class="abtn" openBidRecordId="${evaSellerRecordCount}"  evaSellerRecordId="${evaSellerRecord.objId}"  onclick="keyInEvalPage.addOrUpdate('${evaSellerRecordCount}')" identifier="keyinbid"><span>修改</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="abtn"  onclick="keyInEvalPage.remove('${evaSellerRecord.objId}')" ><span>删除</span></a></td>
  						</c:if>
  					</tr>
  				<c:set var="evaSellerRecordCount" value="${evaSellerRecordCount+1}"></c:set>
		</c:forEach>
		<input type="hidden" name="expertCount" id="expertCount" value="${evaSellerRecordCount}">
	</tbody>	
</table>
	<div class="conOperation" style="text-align:center">
		<button type="button" onClick="keyInEvalPage.insertRow();" ><span>新增专家</span></button>
		<button type="button" id="returnBtn" ><span><spring:message code="globe.return"/></span></button>
	</div>
</div>

	
		
	