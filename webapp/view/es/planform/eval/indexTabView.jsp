<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers"id="epsTabs">
  <ul>
  <c:forEach items="${subPrjList}" var="subPrj">
  	<li>
      <a href="#factorShow" id = "${subPrj.objId }" class="refreshData"><span>${subPrj.projName }</span></a>
    </li>
   </c:forEach>
   </ul>
<input type="hidden" id="subProjId" value="">
<div class="formLayout" id="factorShow">
</div>
</div>

<script language="javascript">
var signUprecordInfoList = {};
signUprecordInfoList.currentTabID = "";
$(document).ready(function(){
	$("#subProjId").val($("#projectId").val());    //第一次进入默认传入项目ID
	$('#epsTabs').tabs();

	$("li a.refreshData").click(function(){
		signUprecordInfoList.currentTabID = $(this).attr("id");
		$("#subProjId").val($(this).attr("id"));    //点击后传入包组ID
		$("#factorShow").loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toDetail&projectId="+$(this).attr("id"));
	})
    $("li a.refreshData:first").click();	
    
})
</script>