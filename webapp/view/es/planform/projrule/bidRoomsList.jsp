<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
	var bidRoomList={}
	bidRoomList.viewBidRoom =function(objId,projId){
		var isSelect = 'yes';
		if(projId==$("#projectId").val()){
			var isSelect = 'yes';
		}else{
			isSelect = 'no';
		}
		$("#epsDialogClose").click();
  		$.epsDialog({
	        title:'预订信息',
	        url:$('#initPath').val()+'/view/es/planform/projrule/reserveRoomDetail.jsp?isSelect='+isSelect+'&isSelectId='+objId,
	        width: '450',
	        height: '230',
	        onOpen: function(){ },
	        isReload:false,
	        afterLoad: function(){ },
	        onClose: function(){
				var workFlowTaskId = $('[id=auditTaskId]').val();
				if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
					
				}else{
					$("#myDesktop").click();
				}
	        }
		});	
	}
</script>
<div id="bidRoomListView" class="formLayout">
<table class="tablelist">
	<thead>
		<tr>
			<td>招标编号</td>
			<td>招标名称</td>
			<td>开始时间</td>
			<td>结束时间</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="bidRoom">
			<tr>
				<td>${bidRoom.project.projCode }</td>
				<td>${bidRoom.project.projName }</td>
				<td>${fn:substring(bidRoom.startDate, 0, 19)}</td>
				<td>${fn:substring(bidRoom.endDate, 0, 19)}</td>
				<td><a onclick="bidRoomList.viewBidRoom('${bidRoom.objId}','${bidRoom.project.objId }')">查看</a></td>
			</tr>
		</c:forEach>
	</tbody>

</table>

</div>
