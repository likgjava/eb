<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="partContainers" id="epsTabs">
  <ul>
  	<c:forEach items="${subPrjList }" var="subPrj">
  	<li>
      <a href="#supplierBidInfo" id = "${subPrj.objId}" class="refreshData"><span>${subPrj.projName }</span></a>
    </li>
   </c:forEach>
  </ul>
  
  <div class="formLayout" id="supplierBidInfo">
  </div>
</div>

<script language="javascript">
var signPageTab = {};
$(document).ready(function(){
	$('#epsTabs').tabs();
	$("li a.refreshData").click(function(){
		signPageTab.currentTabID = $(this).attr("id");
		$("#supplierBidInfo").loadPage($('#initPath').val()+"/BidController.do?method=toSupplierBidDetail&projectId="+$('#projectId').val()+"&packId="+$(this).attr("id"));
	})
	$("li a.refreshData:first").click();
});
</script>