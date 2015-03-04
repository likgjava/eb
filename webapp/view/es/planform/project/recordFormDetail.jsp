<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<div class="formLayout form2Pa">
	<form id="recordFormDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="${recordForm.objId}"/>
     	<h4><span>备案书</span><a href="#" style="color: blue;float:right;margin-right:5px;text-decoration:underline" id="updateRecordForm" title="点击修改备案书">修改</a></h4>
			     	<ul>
						<li>
			 				<label class="short">招管备案号:</label>
							<span >${recordForm.recFormNo }</span>
						</li>
						<li>
			 				<label class="short">建设规模:</label>
							<span >${recordForm.recFormScale }</span>
						</li>
						<li>
			 				<label class="short">建设地点:</label>
							<span >${recordForm.recFormAddr }</span>
						</li>
							<li>
			 				<label class="short">结构类型:</label>
							<span >${recordForm.recFormStructureType }</span>
						</li>
							<li>
			 				<label class="short">工程名称:</label>
							<span >${recordForm.engineeringName }</span>
						</li>
							<li>
			 				<label class="short">建设工程规划许可证:</label>
							<span >${recordForm.planPermit }</span>
						</li>		
						<li >
			 				<label class="short">招标人证明:</label>
							<span ><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId1}" target="_blank" id="a1">${tendererProve }</a></span>
							
						</li>
						<li >
			 				<label class="short">立项证明:</label>
			 				<span ><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId2}" target="_blank" id="a2">${projApproval }</a></span>
						</li>
						<li >
			 				<label class="short">资金落实证明:</label>
			 				<span ><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId3}" target="_blank" id="a3">${fundsProof }</a></span>
						</li>
					</ul>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#updateRecordForm").click(function(){
		$('#recordFormDetail').loadPage($('#initPath').val()+"/RecordFormController.do?method=toUpdateRecordForm&projectId="+$("#projectId").val());
	})
	$("#a1").click(function(){
		window.open($("#a1").attr("href"));
	})
	$("#a2").click(function(){
		window.open($("#a2").attr("href"));
	})
	$("#a3").click(function(){
		window.open($("#a3").attr("href"));
	})
})
</script>
