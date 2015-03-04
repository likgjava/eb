<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%> 
<%@page import="java.util.*"%>
<%@page import="com.gpcsoft.epp.signuprecord.domain.*"%>
<%@page import="com.gpcsoft.epp.bid.domain.BailRecord"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#epsTabs').tabs();
	$("li a.refreshData").click(function(){
		$("#bailR").loadPage($('#initPath').val()+'/BailRecordController.do?method=toapplyRecordForPack&projectId='+$(this).attr("id"));
	});
	$("li a.refreshData:first").click();
});
</script>
<div class="partContainers"id="epsTabs">
  <ul>
	  <c:forEach items="${projectAndSignUpRecordMap}" var="map">
	  	 <li>
	         <a href="#bailR"  class="refreshData" id="${map.key.objId}"><span>${map.key.projName}</span></a>
	     </li>
	  </c:forEach>
   </ul>
	   <div id="bailR" class="formLayout" >

	   </div>
</div>   
 
