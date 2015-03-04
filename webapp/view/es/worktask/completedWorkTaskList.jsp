<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<table class="tableList" id="SubProjectList">
		<caption>已办任务列表</caption>
  		<thead>
      		<tr class="center">
      			<th>操作人</th>
      			<th>联系方式</th>
      			<th>所属单位</th>
          		<th>操作记录</th>
          		<th>审核意见</th>
          		<th>时间</th>
      			<!--<th>操作</th>
     		--></tr>
		</thead>
	<tbody>
		
	<c:forEach items="${completedWorkTaskList}" var="completedWorkTask" >
		<tr >
			<td align="left">${completedWorkTask.createUser.emp.name}</td>
			<td align="left">${completedWorkTask.createUser.emp.mobile}</td>
			<td align="left">${completedWorkTask.createUser.emp.company.name}</td>
			<td align="left">${completedWorkTask.worktaskName}</td>
			<td align="left">${completedWorkTask.opinion}</td>
			<td style="text-align:center;"> <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${completedWorkTask.createTime}"/></td>
		</tr>
	</c:forEach>
	</tbody>
    </table>
</div>
	
	<script type="text/javascript">
		function closeButton() {
			$('#epsDialogClose').click();
			}
		function viewOpinion(OpinionURL,bizId,parentId){
			var oURL = OpinionURL;
			var numS = oURL.lastIndexOf("&");
			var numE = oURL.lastIndexOf("=");
			var flagParameter = oURL.substring(numS+1,numE);
			if(flagParameter=='objId'){
				oURL=oURL+bizId;
			}else if(flagParameter=='parentId'){
				oURL=oURL.substring(0,numS);
				oURL=oURL+'&projectId='+parentId;
			}else if(oURL==''||oURL==null){
				alert('无保存操作,无数据！');
				return false;
			}
			$.epsDialog({
				id:"workTaskId",
		        title:"查看已办任务",
		        url:$("#initPath").val()+"/"+oURL+"&fromWT=yes",
		        width: '800',
		        height: '400',
		        isReload: false,
		        onClose: function(){
			}
			});
		
		}
	
	</script>