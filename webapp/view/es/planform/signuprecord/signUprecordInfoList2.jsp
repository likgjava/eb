<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="com.gpcsoft.epp.bid.domain.BidPackage,com.gpcsoft.core.context.FrameBeanFactory"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*,java.math.BigDecimal"%>

	<table class="tableList" id="SubProjectList">
	<c:if test="${passAudit eq  'YES' }">
	<caption style=" text-align: right">
		<a href="#"><span onclick="signUprecordInfoList.addSignUprecord();"	 title="补录投标单位"  class="sysicon siAdd">补录投标单位&nbsp;&nbsp;&nbsp;</span></a>
		<a href="#"><span onclick="signUprecordInfoList.batchAudit();"	 title="批量审核"  class="sysicon siAdd">批量审核&nbsp;&nbsp;&nbsp;</span></a>
		</caption>
	</c:if>
  		<thead>
      		<tr class="center">
      			<td style="width: 25px;text-align: center"><input type="checkbox" id="checkAll"></td>
      			<th>投标单位名称</th>
          		<th>状态</th>
          		<th>操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${signUprecordList}" var="signUprecord" varStatus="i">
		<tr>
			<td style="width: 25px;text-align: center"><input type="checkbox" name="orgCheck" value="${signUprecord.objId}"><input type="hidden" name="auditStatus" value="${signUprecord.auditStatus}"><input type="hidden" name="bailStatus" value="${signUprecord.bailStatus}"><input type="hidden" name="supplierName" value="${signUprecord.supplier.orgName}"></td>
			<td>${signUprecord.supplier.orgName}</td>
			<td class="center">${signUprecord.auditStatusCN}</td>
			<td class="center"><a href="#"><span class="sysicon siAccept" onclick="signUprecordInfoList.viewSignUprecord('${signUprecord.objId}','${signUprecord.bailStatus}','${signUprecord.supplier.orgName}');" title="		
			<c:choose>
			<c:when test="${signUprecord.auditStatus=='00'}">审核</c:when>
			<c:otherwise>查看详情</c:otherwise>
			</c:choose>">		
			<c:choose>
			<c:when test="${signUprecord.auditStatus=='00'}">审核</c:when>
			<c:otherwise>查看详情</c:otherwise>
			</c:choose></span></a></td>
			</tr>
	</c:forEach>
	</tbody>
    </table>
    
    <table class="tableList" id="SubProjectList">
	<caption style=" text-align: left">供应商投标情况</caption>
	<thead>
      		<tr class="center">
      			<th style="width:20%;">投标识别码</th>
          		<th style="width:20%;">投标单位</th> 
          		<th style="width:15%;">投标文件大小(k)</th>
          		<th style="width:15%;">投标状态</th>
          		<th style="width:10%;">操作</th>
     		</tr>
	</thead>
	
	<tbody>
	<c:forEach var="bidPackage" items="${bidPackageList}" varStatus="status">
		<tr>
			<td>${bidPackage.bid.bidNo}</td>
			<td>${bidPackage.bid.supplierName}</td>
			<td>
				<c:if test="${bidPackage.processStatus=='00'}">
					<fmt:formatNumber type="number" value="${bidPackage.bidFileTotalSize / 1024}" maxFractionDigits="2"/>
				</c:if>
				<c:if test="${bidPackage.processStatus=='01'}">
					<fmt:formatNumber type="number" value="" maxFractionDigits="2"/>
				</c:if>
			</td>
			<td>
				<c:choose>
					<c:when test="${bidPackage.processStatus=='00'}"><span style="color: green;">已投标</span></c:when>
					<c:when test="${bidPackage.processStatus=='02'}"><span style="color:red;">已撤标</span></c:when>
					<c:otherwise>未投标</c:otherwise>
				</c:choose>
			</td>
			<td><a href="#" onclick="signUprecordInfoList.viewSupplierBidDetail('${bidPackage.pack.objId}','${bidPackage.bid.supplier.objId}','${bidPackage.bid.supplierName}');">详情</a></td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
<script language="javascript">
$(document).ready(function(){
	
	$('#checkAll').click(function(){
		$("#checkAll").attr("checked",$("#checkAll").attr("checked"));
		if ($("#checkAll").attr("checked")){//全选
			$("input[name=orgCheck]").each(function(){
		        $(this).attr('checked',true);
		    });
		}else{//反选
			$("input[name=orgCheck]").each(function(){
		        $(this).attr('checked',false);
		    });
		}
	});
			
});
</script>