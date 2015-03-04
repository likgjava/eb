<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
   <div class="partContainers" id="superviseDoDiv1">
    <input type="hidden" id="projectId" value="${param.projectId}">
    <input type="hidden" id="type" name ="type" value="${type}">
	<ul>	
	     <c:forEach items="${subProjectList}" var="subProject1" >
          <li id="tab${subProject1.objId}" class=""><a href="#" onClick="getEvalBidRecordListBySubProjId('${subProject1.objId}');"><span><c:out value="${subProject1.projName}"/></span></a></li>
            </c:forEach>
	</ul>
	<div id="superviseTabDiv1"   class="tabsContent"></div>
   </div>
<script language="javascript">
   var evalBidRecordList = {}; 
   //获取所有的审核过的招标文件
	function getEvalBidRecordListBySubProjId(subProjId){
		$('#superviseDoDiv1').find("li").removeClass();
		$("#tab"+subProjId).addClass('selected');
		$("#superviseTabDiv1").empty();
		$("#superviseTabDiv1").loadPage($("#initPath").val()+"/CongruousFactorController.do?method=setGenviewDefine&type="+$("#type").val()+"&subProjId="+subProjId);
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
   
   	