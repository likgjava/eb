<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="objId" name="objId" value="${param.objId}">
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import> 

<div id="bulletinContent"></div>

<script type="text/javascript">
$(document).ready(function(){
	//读取项目公告内容
	$.ajax({
		url: $("#initPath").val()+"/BulletinShowController.do?method=printBulletin&objId="+$("#objId").val(),
		dataType:'json',
		success:function(json){
			$('#bulletinContent').html(json.bulletinContents);
		},
		error:function(msg){
			alert('操作失败');
		}
	});
	
	//打印
	window.print();
})

</script>