<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.gpcsoft.epp.signuprecord.domain.*"%>
<%@page import="com.gpcsoft.epp.bid.domain.BailRecord"%>
<script type="text/javascript">
var signUprecordList ={};
signUprecordList.add = function(signUprecordId){
	 $.epsDialog({
	        title:'保证金录入界面',
	        url:$('#initPath').val()+'/BailRecordController.do?method=toAddorUpdateBailRecord&projectId='+$("#project_Id").val()+'&signUprecordId='+signUprecordId,
	        width: '750',
	        height: '200',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        isReload:false,
	        onClose: function(){ 
		    }
	    }); 
}
</script>


<table class="tableList">
	<thead>
		<tr>
			<th class="center">投标单位名称</th>
			<th class="center"><dm:out value="local__BAILRECORD"
				tenderType="${map.key.tenderType}">保证金</dm:out>缴纳状态</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${signUprecordList}" var="signUprecord">
		<input type="hidden" value="${signUprecord.project.objId}" id="project_Id">
			<tr>
				<td>${signUprecord.supplier.orgName}</td>
				<td style="text-align: center">${signUprecord.bailStatusCN }</td>
				<td style="text-align: center"><a href="#"
					onclick="signUprecordList.add('${signUprecord.objId }')">录入<dm:out
					value="local__BAILRECORD" tenderType="${map.key.tenderType}">保证金</dm:out>缴纳情况</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

