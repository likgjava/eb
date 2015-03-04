<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
<div class="formLayout"> 
<input type="hidden" id="selectAgents">
<form id="taskAgentsForm" method="post">
<h5><span>请选择随机抽取的招标中心</span></h5> 
	<ul class="taskAgentUl"></ul>
</form>
</div>

<div id="selectAgentPage" class="formLayout form2Pa">
     <ul>
     	<li>
     	<label class="short">抽取的招标中心：</label>
     	<span id="selectedAgentName"></span>
     	<input type="hidden" id="selectAgentId">
     	</li>
     	<li class="functionBtnDiv">
     		<button name="randomSelect"><span>随机抽取</span></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     		<button name="saveAgent"><span class="add">保存</span></button>
     		<button name="returnBack"><span>返回</span></button>
     	</li>
     </ul>
</div>

</div>

<script>
var randomSelectAgentDetailView = {};

randomSelectAgentDetailView.checkeNotSame=function(){
	var taskAgentIds = '';
     $("input[name^=taskAgentId]").each(function(){
		var currentId = $(this).val(); 
		taskAgentIds += currentId+',';
     })
     var taskAgentIdArray = taskAgentIds.split(',');
     for(var i=0;i<taskAgentIdArray.length-1;i++){
			var curId = taskAgentIdArray[i];
			  for(var j=i+1;j<taskAgentIdArray.length-1;j++){
 					if(curId==taskAgentIdArray[j]){
							return false;
 	 					}		
				}

         }
     $("#selectAgents").val(taskAgentIds);
     return true;  
}

randomSelectAgentDetailView.randomSelectAgent=function(){
   randomSelectAgentDetailView.showImg();//显示抽取动画
   var selectAgents = $("#selectAgents").val();
   var selectNum = $("#selectNum").val();
   if(selectNum==0){
	   selectAgents = '';
	}
   $.getJSON($("#initPath").val()+"/TaskPlanController.do?method=selectAgent",{selectAgents:selectAgents},function(json){
		   if(json.result)alert(json.result);if(json.failure)return;
	    setTimeout(
		    	function(){
		    		$('#showImg').find(".epsDialogCloseNoReload").click();
					$("#selectedAgentName").text(json.agent.orgName);
					$("#selectAgentId").val(json.agent.objId);
					$("button[name=randomSelect]").attr("disabled","disabled");
					$("input[name=taskAgentName]").attr("disabled","disabled");
					$(":radio[name=selectType]").attr("disabled","disabled");
					$("button[name=saveAgent]").click();
		    	},
		    	parseInt(5*1000)
		    );
		
   });
}

randomSelectAgentDetailView.showImg=function(){
	$.epsDialog({
	    id :"showImg",
        title:"抽取招标中心",
        url:$("#initPath").val()+"/view/es/planform/taskplan/RandomAgent.jsp",
        width: 400,
        height: 150,
        isReload: true,
       	afterLoad:function() {
       		$('#showImg').find(".epsDialogClose").hide();
       	}
	});
}

$(document).ready(function(){
	$("button[name=saveAgent]").hide();
    randomSelectAgentDetailView.showAgentList=function(i){
      	 $.epsDialog({
   	        title:'招标中心列表',
   	        url:$('#initPath').val()+'/view/es/planform/taskplan/agentListGrid.jsp?num='+i+'',
   	        width: '800',
   	        height: '420',
   	 	    isReload:true
   			});
       }
    //根据配置文件获得抽取招标中心数量
	var selectNum = $("#selectNum").val();
	var ulObj =  $("ul.taskAgentUl");
    for(var i=0;i<selectNum;i++){
        var html = "<li class='taskAgent'>";
       		html+= "<label class='short'>"+(i+1)+"</label>";
       		html+= "<input type='hidden' value="+i+" id='id"+i+"' />";
       		html+= "<input type='hidden' name='taskAgentId["+i+"]' id='taskAgentId["+i+"]'/>";
       		html+= "<input type='text'  name='taskAgentName["+i+"]' id='taskAgentName["+i+"]' class='required' onclick='randomSelectAgentDetailView.showAgentList("+i+")'/>";
       		html+= "<span class='eleRequired'></span>";
       		html+= "</li>";
    	ulObj.append(html);
    }


	$("button[name=randomSelect]").click(function(){
		$("input[name=taskAgentName]").each(function(){
			if($(this).val()==null||$(this).val()==''||$(this).val()==undefined){
					alert("请先选择待随机抽取的招标中心！")
					return false;
				}
		})
		
		if(!$('#taskAgentsForm').valid()){
			return ;
		}
		if(!randomSelectAgentDetailView.checkeNotSame()){
			alert('不能选择重复的招标中心！');
			return ;
		}	
		randomSelectAgentDetailView.randomSelectAgent();
	})


 	$("button[name=saveAgent]").click(function(){//保存抽取的招标中心
 	 	var selectAgentId = $("#selectAgentId").val();
 	 	if(''==selectAgentId || null==selectAgentId || selectAgentId == undefined){
 	 		alert("请先抽取招标中心！");
 	 		return;
 	 	}
 	 	var taskPlanId = $("#_taskPlanId").val();
 	 	var drawType = $("input[type=radio]:checked").val();
 		$.getJSON($("#initPath").val()+"/TaskPlanController.do?method=saveSelectAgentForTaskplan",{taskplanId:taskPlanId,drawType:drawType,selectAgentId:selectAgentId},function(json){
 			 if(json.result)alert(json.result);if(json.failure)return;
    	});
 	 	})

	$("button[name=returnBack]").click(function(){//返回按钮
		 if($("#fromType").val()=='fromDesk'){//从待办任务进入页面
			 $("#myDesktop").click();  
		 }else{
			 if($("#buyerLevel").val()=='twoLevel'){//二级单位提交
	 				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanList.jsp'); 
	 			 }else{//一级单位提交
	 				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanListForSum.jsp');
	 	 		}
		 }
	})
})

</script>
