<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
   <div id="superviseDoDiv1" style="overflow: auto;width: 99%" class="">
    <input type="hidden" id="projectId" value="${param.projectId}">
    <input type="hidden" id="type" name ="type" value="${type}">
   <div style="margin-bottom: 5px;" class="tabs">
		<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>	
			     <c:forEach items="${subProjectList}" var="subProject1" >
		          <li id="tab${subProject1.objId}" class=""><a href="#" onClick="getEvalBidRecordListBySubProjId('${subProject1.objId}');"><span><c:out value="${subProject1.projName}"/></span></a></li>
	             </c:forEach>
			</ul>
		</div>
		</div>
		<div id="superviseTabDiv1" style="height: 100%;" class="tabsContent">
		</div>
	</div>
   </div>
<script language="javascript">
   var evalBidRecordList = {}; 
   //根据包组ID展示评标Tab页
	function getEvalBidRecordListBySubProjId(subProjId){
		$('#superviseDoDiv1').find("li").removeClass();
		$("#tab"+subProjId).addClass('selected');
		$("#superviseTabDiv1").empty();
		$("#superviseTabDiv1").loadPage($("#initPath").val()+"/EvalBidRecordController.do?method=toSyndicEvalBidRecordListView&packId="+subProjId);
	}
   
	//tab点击事件
	evalBidRecordList.tabClick = function(id){
		$('#superviseDoDiv1').find("li").removeClass();
		$("#"+id).addClass('selected');
	}
   
   $(document).ready(function(){
		//遍历所有TAB，并点击第一个TAB
		$('#superviseDoDiv1').find("li").each(function(i,n){
			if(this.id.indexOf('tab')>-1&&i == 0){
				$(this).find(">a").click();
			}
		});
		
	});
   
   </script>
   
   	