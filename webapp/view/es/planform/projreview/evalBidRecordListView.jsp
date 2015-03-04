<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
   <div id="superviseDoDiv" style="overflow: auto;width:99%;" class="page">
    <input type="hidden" id="projectId" value="${param.projectId}">
   <div style="margin-bottom: 5px;" class="tabs">
		<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>	
	              <li id="tab3" class=""><a href="#" onClick="toEvalBidRecordResultList();"><span>评标结果</span></a></li>
	              <li id="tab4" class=""><a href="#" onClick="toBidEvaluationReport();"><span>评标报告</span></a></li>
			</ul>
		</div>
		</div>
		<div id="superviseTabDiv" class="tabsContent"></div>
	</div>
   </div>
<script language="javascript">
   var evalBidRecordList = {};
   //跳转到评审结果页面
   function toEvalBidRecordResultList()
   {
	   $('#superviseDoDiv').find("li").removeClass();
	   $("#tab3").addClass('selected');
	   $("#superviseTabDiv").empty();
	   $("#superviseTabDiv").loadPage($("#initPath").val()+"/EvaSellerRecordController.do?method=toEvaSellerRecordResult&projectId="+$("#projectId").val());
   }

    //跳转到评标报告页面
     function toBidEvaluationReport()
   {
	  $('#superviseDoDiv').find("li").removeClass();
	  $("#tab4").addClass('selected');
	  $("#superviseTabDiv").empty();
	  $("#superviseTabDiv").loadPage($("#initPath").val()+"/EvaSellerRecordController.do?method=toBidEvaluationReport&projectId="+$("#projectId").val());
   }
   //获取所有的待审核的招标文件
	function getEvalBidRecordListBySubProjId(subProjId){
		$('#superviseDoDiv').find("li").removeClass();
		$("#tab"+subProjId).addClass('selected');
		$("#superviseTabDiv").empty();
		$("#superviseTabDiv").loadPage($("#initPath").val()+"/EvaSellerRecordController.do?method=getEvaSellerRecordList&subProjId="+subProjId);
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
   
   	