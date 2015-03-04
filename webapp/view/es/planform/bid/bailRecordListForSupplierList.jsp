<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers" id="epsTabs">
  <ul>
  <c:forEach items="${projectList}" var="subPrj">
  	<li>
      <a href="#bailRecordInfo" id = "${subPrj.objId}" class="refreshData"><span>${subPrj.projName }</span></a>
    </li>
   </c:forEach>
   </ul>
   <div class="formLayout" id="bailRecordInfo">
   </div>
</div>


<script language="javascript">
var signPageTab = {};
$(document).ready(function(){
	$('#epsTabs').tabs();
	$("li a.refreshData").click(function(){
		signPageTab.currentTabID = $(this).attr("id");
		if (!signPageTab.currentTabID) {
			signPageTab.currentTabID = this.id;
		}
		$("#bailRecordInfo").loadPage($('#initPath').val()+"/BailRecordController.do?method=toBailRecordForSupplier&isComplete=true&projectId="+signPageTab.currentTabID);
	})
	$("li a.refreshData:first").click();
});
</script>