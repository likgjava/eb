<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
   var evalBidRecordList = {};
   evalBidRecordList.intoProject =function(projectId)
   {
	   //$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=expertSignin',{workMemberId:workMemberId},function(json){
           
          //   $('#conBody').loadPage($('#initPath').val()+'/WorkgMemberController.do?method=toSigninProjectList');
		//});
          $('#conBody').loadPage($('#initPath').val()+'/WorkgMemberController.do?method=toSigninSubProjectList&projectId='+projectId);  
   }
   </script>
        <table class="tableList" id="SubProjectList">
		<caption>项目列表</caption>
  		<thead>
      		<tr>
          		<th class="center">招标名称</th>
          		<th class="operation">操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${projectList}" var="project" >
		<tr>
			<td id="${project.objId}" class="center">${project.projName}</td>
			<td class="center">
				<button class="sysicon siModify" onclick="evalBidRecordList.intoProject('${project.objId}');" title="进入项目"><span>进入项目</span></button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
    </table>   	