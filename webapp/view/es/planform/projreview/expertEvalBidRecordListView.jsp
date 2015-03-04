<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
   
   
  
   <div id="superviseDoDiv" style="overflow: auto;width:900px;" class="page">
    <input type="hidden" id="myProjectId" name="myProjectId" value="${mySubProjectId}">
    <input type="hidden" id="projectId" value="${ProjectId}">
   <div style="margin-bottom: 5px;" class="tabs">
		<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>	
			   <c:forEach items="${subProjectList}" var="subProject1" >
				   <c:if test="${mySubProjectId == subProject1.objId}">
				  	 	<li id="tab${subProject1.objId}" class=""><a href="#" onClick="getEvalBidRecordListBySubProjId('${subProject1.objId}');"><span><c:out value="${subProject1.projName}"/></span></a></li>
				   </c:if>
	           </c:forEach>
			</ul>
		</div>
		</div>
		<div id="superviseTabDiv" style="height: 400px;" class="tabsContent">
		</div>
	

	</div>
   </div>
<script language="javascript">
   var evalBidRecordList = {};
   //获取该专家待评标的投标单位列表
	function getEvalBidRecordListBySubProjId(subProjId){
		$('#superviseDoDiv').find("li").removeClass();
		$("#tab"+subProjId).addClass('selected');
		$("#superviseTabDiv").empty();
        if(subProjId==$("#myProjectId").val())
        {
        $("#superviseTabDiv").loadPage($("#initPath").val()+"/EvaSellerRecordController.do?method=getEvaSellerRecordList&subProjId="+subProjId);
		}
        
		
		
	}
   
	//tab点击事件
	evalBidRecordList.tabClick = function(id){
		$('#superviseDoDiv').find("li").removeClass();
		$("#"+id).addClass('selected');
	}
   
   $(document).ready(function(){
		
		//遍历所有TAB，并点击第一个TAB
		$('#superviseDoDiv').find("li").each(function(i,n){
			if(this.id.indexOf('tab')>-1&&i == 0){
				$(this).find(">a").click();
			}
		});
		
	});
   

   
   </script>
   
   	