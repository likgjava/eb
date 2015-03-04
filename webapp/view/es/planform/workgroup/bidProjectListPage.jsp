<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
   var evalBidRecordList = {};
   evalBidRecordList.beginevalBidRecord =function(subProjId)
   {
	   $('#conBody').loadPage($('#initPath').val()+'/EvaSellerRecordController.do?method=getEvaSellerRecordList&subProjId='+subProjId);
   }
   </script>
        <table class="tableList" id="SubProjectList">
		<caption>项目列表</caption>
  		<thead>
      		<tr>
          		<th class="center"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</th>
          		<th class="center">签到状态</th>
          		<th class="operation">操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${workMemberList}" var="workMember" >
		<tr>
			<td id="${workMember.workGroup.project.objId}" class="center">${workMember.workGroup.project.projName}</td>
			<td class="center">${workMember.signinStatusCN}</td>
			
			<c:if test="${workMember.signinStatus=='01'}">
			<td class="center">
				<button class="sysicon siModify" onclick="evalBidRecordList.beginevalBidRecord('${workMember.workGroup.project.objId}');"><span>进入<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></span></button>
			</td>
			</c:if>
			
			<c:if test="${workMember.signinStatus=='00'}">
			<td class="center">
				<button class="sysicon siModify" onclick="alert('还未签到！');")><span>进入<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></span></button>
			</td>
			</c:if>
			
		</tr>
	</c:forEach>
	</tbody>
    </table>   	