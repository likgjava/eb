<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="fromType" value="${param.fromType}">
<div class="partContainers" id="epsTabs">
  <ul>
  <c:forEach items="${subPrjList }" var="subPrj">
  	<li>
      <a href="#taskPlanListInfo" id = "${subPrj.objId }" class="refreshData"><span>${subPrj.projName }</span></a>
    </li>
    <c:if test="${projectRule.ruleAnonymous!=null&&projectRule.ruleAnonymous=='1'}">
     <div  style="background-color: gray;width: 100px;margin-left: 1100px;height: 36px;font-size: 20px"><a style="cursor:pointer;"  id="a${subPrj.objId }" onclick="signPageTab.anonymousSignUp('${subPrj.objId }');"><u id="u${subPrj.objId }">签到</u></a></div>
     </c:if>
   </c:forEach>
  
   </ul>
   <div class="formLayout" id="taskPlanListInfo">
   </div>
   
</div>


<script language="javascript">
var signPageTab = {};
signPageTab.anonymousSignUp=function(id){
	$.epsDialog({
		title:'签到页面',
		url:$('#initPath').val()+'/SignUprecordController.do?method=viewBidPackage&subProjectId='+id,
		width: '480',
		height: '270',
		isReload: false
	});
}
$(document).ready(function(){
	$('#epsTabs').tabs();
	$("li a.refreshData").click(function(){
		signPageTab.currentTabID = $(this).attr("id");
		$("#taskPlanListInfo").loadPage($('#initPath').val()+"/SignUprecordController.do?method=toViewSignupPage&fromType="+$("#fromType").val()+"&prjId="+$(this).attr("id"));
	})
	$("li a.refreshData:first").click();
});
</script>