<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
   var evalBidRecordList = {};
   evalBidRecordList.beginevalBidRecord =function(subProjId)
   {
	  //确认成交投标单位
	  $.epsDialog({
	       	title:'确认成交投标单位',
	       	url:$('#initPath').val()+'/BuyResultController.do?method=toConfigBuyResultPageList&subProjId='+subProjId+'&projectId='+$("#projectId").val(),
	       	width: '650',
	       	height: '350',
	       	isReload:false,
			onClose: function(){
				if($("#projectTaskId") && $("#projectTaskId").val() != ""){
		        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			    }
	   		}
		});	
   }

    //查看评审报告
   evalBidRecordList.lookBulletin = function(subProjId){
	   $.epsDialog({
	       title:'评标报告',
	       url:$('#initPath').val()+'/EvaSellerRecordController.do?method=toShowBidEvaluationReport&subProjId='+subProjId,
	       width: '900',
	       height: '350',
		   isReload:false,
		   onClose: function(){
		  	planTemplateTask.refresh($("#projectTaskId").val()+"");
	      	}
		});	
   }
   </script>
        <table class="tableList" id="SubProjectList">
		<caption>评标报告列表</caption>
  		<thead>
      		<tr>
      			<th class="operation">评标报告</th>
          		<th class="operation"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</th>
          		<th class="operation" style="width: 20%;">确认成交投标单位</th>
     		</tr>
		</thead>
	<tbody>
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${bidEvaluationReportList}" var="bidEvaluationReport" >
		<tr>
			<td id="bullTitle" class="center"><a href="#" onclick="evalBidRecordList.lookBulletin('${bidEvaluationReport.project.objId}');">${bidEvaluationReport.bullTitle}</a></td>
			<td id="subProjName" class="center">${bidEvaluationReport.project.projName}</td>
			<td class="center">
				<a href="#"><span class="sysicon siEdit" onclick="evalBidRecordList.beginevalBidRecord('${bidEvaluationReport.project.objId}');" title="定标确认 ">定标确认&nbsp;&nbsp;&nbsp;</span></a>
			</td>
		</tr>
		<c:set value="${subProjectCount+1}" var="subProjectCount"/>
	</c:forEach>
	</tbody>
    </table>   	