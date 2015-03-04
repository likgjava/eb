<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<input type="hidden" id="projectId" value="${projectId}">
<div id="superviseDoDiv" style="overflow: auto;" class="page">
<div style="margin-bottom: 5px;" class="tabs">
<div class="tabsHeader">
<div class="tabsHeaderContent">
<ul>	
     <c:forEach items="${subProjectList}" var="subProject1" >
         <li id="tab${subProject1.objId}" ><a href="#" onClick="keyIntoSupplierList.getSupplierList('${subProject1.objId}')"><span><c:out value="${subProject1.projName}"/></span></a></li>
     </c:forEach>
</ul>
</div>
</div>
	<div id="superviseTabDiv" style="height:auto;" class="tabsContent"></div>
</div>
</div>



<script language="javascript">
var keyIntoSupplierList = {};
keyIntoSupplierList.getSupplierList=function(subProjectId){
	$('#superviseTabDiv').empty().loadPage($('#initPath').val()+'/SignUprecordController.do?method=toSupplierSubList&subProjId='+subProjectId);
}

   $(document).ready(function(){
		$('#superviseDoDiv a').click(function(){
			$('#superviseDoDiv a').parent().removeClass();
			$(this).parent().addClass('selected');
		});
		$('#superviseDoDiv a:first').click();
	});
</script>