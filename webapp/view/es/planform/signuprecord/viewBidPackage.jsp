<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="com.gpcsoft.epp.bid.domain.BidPackage,com.gpcsoft.core.context.FrameBeanFactory"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*,java.math.BigDecimal"%>
    <table class="tableList" id="SubProjectList">
	<caption style=" text-align: left">供应商投标情况</caption>
	<thead>
      		<tr class="center">
      			<th style="width:20%;">投标识别码</th>
          		<th style="width:20%;">投标单位</th> 
          		<th style="width:10%;">操作</th>
     		</tr>
	</thead>
	
	<tbody>
		<tr>
			<td>
			<c:choose>
			<c:when test="${bidNo!=null&&bidNo!=''}">${bidNo}</c:when>
			<c:otherwise><input type="text" name="bidNo" id="bidNo"/></c:otherwise>
			</c:choose>
			<span></span></td>
			<td>${orginfo.orgName}</td>
			<td>
				<c:choose>
			<c:when test="${bidNo!=null&&bidNo!=''}">您已签到</c:when>
			<c:otherwise><a href="#" onclick="signUP('${subPrjId}');">签到</a></c:otherwise>
			</c:choose>
			</td>
		</tr>
	</tbody>
	</table>
<script language="javascript">
	function signUP(id){
		$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=anonymousSignUp&bidNo='+$("#bidNo").val()+'&subProjectId='+id+'', {}, function(json){
				if(json.failure){alert(json.result);return;}
				alert("签到成功!");$("#epsDialog").click();$("li a.refreshData:first").click();
		});
	}
</script>
