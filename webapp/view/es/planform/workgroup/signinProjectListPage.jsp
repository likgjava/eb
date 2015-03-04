<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
   var evalBidRecordList = {};
   evalBidRecordList.beginevalBidRecord =function(workMemberId)
   {
	   $.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=expertSignin',{workMemberId:workMemberId},function(json){
             alert("签到成功!");
             $("#superviseTabDiv1").loadPage($("#initPath").val()+"/WorkgMemberController.do?method=toExpertSignin&subProjectId="+$("#subProjectId").val());
            // $('#conBody').loadPage($('#initPath').val()+'/WorkgMemberController.do?method=toSigninProjectList');
		});
		  
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
	<input type="hidden" id="subProjectId" name="subProjectId" value="${subProjectId}">
	<c:forEach items="${workMemberList}" var="workMember" >
		<tr>
			<td id="${workMember.subProject.objId}" class="center">${workMember.subProject.projName}</td>
			<td class="center">${workMember.signinStatusCN}</td>
			<c:if test="${workMember.signinStatus=='01'}">
			<td class="center">
				<button class="sysicon siModify" onclick="alert('不能重复签到！');" title="已签到"><span>专家签到</span></button>
			</td>
			</c:if>
		    <c:if test="${workMember.signinStatus=='00'}">
			<td class="center">
				<button class="sysicon siModify" onclick="evalBidRecordList.beginevalBidRecord('${workMember.objId}');" title="点击,进行专家签到"><span>专家签到</span></button>
			</td>
			</c:if>
		</tr>
	</c:forEach>
	</tbody>
    </table>   	