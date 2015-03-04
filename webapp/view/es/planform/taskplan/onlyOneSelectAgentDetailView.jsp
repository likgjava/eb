<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">

<div id="selectAgentPage" class="formLayout">
     <ul>
     	<li id="taskAgent" class="functionBtnDiv">
     	<label class="short">选取的招标中心</label>
     	<input type="hidden" id="selectAgentId" value="${taskPlan.taskAgent.objId}"/>
     	<input type="text" id="selectedAgentName" value="${taskPlan.taskAgentName}" name="selectedAgentName" onclick="randomSelectAgentDetailView.showAgentList(0)">
     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     	<button name="saveAgent"><span>提交审核</span></button>
     	</li>
     </ul>
</div>

</div>

<script>
var randomSelectAgentDetailView = {};

$(document).ready(function(){
	randomSelectAgentDetailView.showAgentList=function(i){
     	 $.epsDialog({
  	        title:'招标中心列表',
  	        url:$('#initPath').val()+'/view/es/planform/taskplan/agentListGrid4.jsp?num='+i+'',
  	        width: '800',
  	        height: '420',
  	 	    isReload:true
  			});
      }

	//选择招标中心
 	$("button[name=saveAgent]").click(function(){
 	 	var selectAgentId = $("#selectAgentId").val();
 	 	var selectAgentName = $("input[name=selectedAgentName]").val();
 	 	if(''==selectAgentName || null==selectAgentName || selectAgentName == undefined){
 	 		alert("请先抽取招标中心！");
 	 		return;
 	 	}
 	 	var taskPlanId = $("#_taskPlanId").val();
 	 	var drawType = $("input[type=radio]:checked").val();
 	 
 	 	if(window.confirm('确定要提交审核吗？')){
 	 		$.getJSON($("#initPath").val()+"/TaskPlanController.do?method=saveSelectAgentForTaskplan",{taskplanId:taskPlanId,drawType:drawType,selectAgentId:selectAgentId},function(json){
 	 			 if(json.result)alert(json.result);if(json.failure)return;
 	 			 if($("#fromType").val()=='fromDesk'){//从待办任务进入页面
 					 $("#myDesktop").click();  
 				 }else{
 					 if($("#buyerLevel").val()=='twoLevel'){//二级单位提交
 			 				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanList.jsp'); 
 			 			 }else{//一级单位提交
 			 				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanListForSum.jsp');
 			 	 		}
 				 }
 	    	});
 	 	 	}
 	 	})
})







</script>
