<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers" id="epsTabs">
  <ul>
  <c:forEach items="${projectList}" var="subPrj">
  	<li>
      <a href="#dosBuyRecordInfo" id = "${subPrj.objId}" class="refreshData"><span>${subPrj.projName }</span></a>
    </li>
   </c:forEach>
   </ul>
   <div class="formLayout" id="dosBuyRecordInfo">
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
		$("#dosBuyRecordInfo").loadPage($('#initPath').val()+"/DosBuyRecordController.do?method=toDosBuyRecordListForSupplier&projectId="+signPageTab.currentTabID);
	})
	$("li a.refreshData:first").click();
});
</script>