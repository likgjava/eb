<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<table class="tableList" >
		<caption style=" text-align: left; font-weight:bold; ">投标单位：${supplierName}</caption>
		<thead>
	      		<tr class="center">
	          		<th style="width:30%;">投标分册</th>
	          		<th style="width:50%;">投标文件</th>
	          		<th style="width:20%;">投标文件大小(k)</th>
	     		</tr>
		</thead>
		
		<tbody>
			<c:forEach var="bidDetail" items="${supplierBidInfoList}" varStatus="status">
				<tr>
					<td>${bidDetail[0]}</td>
					<td>${bidDetail[1]}</td>
					<td align="right"><fmt:formatNumber type="number" value="${bidDetail[2] /1024}" maxFractionDigits="2"/></td>
				</tr>
			</c:forEach>
		</tbody>
</table>
