<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<div class="partContainers" id="superviseDoDiv1">
	 <input type="hidden" id="projectId" value="${param.projectId}">
	 <ul>	
     	<c:forEach items="${subProjectList}" var="subProject1" >
         	<li id="tab${subProject1.objId}" class=""><a onClick="getBidEvaluationReportBySubProjId('${subProject1.objId}');"><span><c:out value="${subProject1.projName}"/></span></a></li>
        </c:forEach>
	</ul>
	<!--<a onClick="getBidEvalua....>中本有href="#",现在去掉了  -->
	<div id="superviseTabDiv1" class="tabsContent"></div>
</div>
	



<script language="javascript">
   var evalBidRecordList = {}; 
   //获取包组录入评标报告页面
	function getBidEvaluationReportBySubProjId(subProjId){
		$('#superviseDoDiv1').find("li").removeClass();
		$("#tab"+subProjId).addClass('selected ui-tabs-selected ui-state-active');
		$("#superviseTabDiv1").empty();
		$("#superviseTabDiv1").loadPage($("#initPath").val()+"/EvaSellerRecordController.do?method=toShowBidEvaluationReport&subProjId="+subProjId);
		}
   
	//tab点击事件
	evalBidRecordList.tabClick = function(id){
		$('#superviseDoDiv1').find("li").removeClass();
		$("#"+id).addClass('selected');
	}
   
   $(document).ready(function(){
	   $("#superviseDoDiv1").tabs();
		//遍历所有TAB，并点击第一个TAB
		$('#superviseDoDiv1').find("li").each(function(i,n){
			if(this.id.indexOf('tab')>-1&&i == 0){
				$(this).find(">a").click();
			}
		});
		
	});
   
   </script>
   
   	