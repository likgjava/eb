<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  

    <table class="tableList" id="SubProjectList">
		<caption>定标信息列表</caption>
  		<thead>
      		<tr>
      			<th class="operation">序号</th>
      			<th class="operation"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</th>
      			<th class="operation">评标报告</th>
          		<th class="operation">定标状态</th>
          		<th class="operation">中标供应商名称</th>
          		<th class="operation" style="width: 20%;">确认成交投标单位</th>
     		</tr>
		</thead>
	<tbody>
	<c:set value="1" var="subProjectCount"/>
	<c:forEach items="${subProjectList}" var="subProject" >
		<tr>
			<td id="No" class="center">${subProjectCount}</td>
			<td id="subProjName" class="center">${subProject.projName}</td>
			<td id="bullTitle" class="center">
			<c:choose>
			   <c:when test="${subProject.bidEvaluationReport!=null}">	<a href="#" onclick="evalBidRecordList.lookBulletin('${subProject.objId}');">${subProject.bidEvaluationReport.bullTitle}</a></c:when>
			   <c:otherwise>暂无数据</c:otherwise>
			</c:choose>
			</td>
			<td class="center">
			<c:choose>
			   <c:when test="${subProject.isConfigBuyResult== 'true' }"> 已定标</c:when>
			   <c:otherwise>未定标</c:otherwise>
			</c:choose>
			</td>
			<td class="center">
			<c:choose>
			   <c:when test="${subProject.winnerNames!=''}">${subProject.winnerNames}</c:when>
			   <c:otherwise>暂无数据</c:otherwise>
			</c:choose>
			</td>
			<td class="center">
				<c:choose>
			   <c:when test="${subProject.bidEvaluationReport!=null}"><a href="#"><span class="sysicon siEdit" onclick="evalBidRecordList.beginevalBidRecord('${subProject.objId}');" title="定标确认 ">定标确认&nbsp;&nbsp;&nbsp;</span></a></c:when>
			   <c:otherwise>还未起草评审报告</c:otherwise>
			   </c:choose>	
			</td>
		</tr>
		<c:set value="${subProjectCount+1}" var="subProjectCount"/>
	</c:forEach>
	</tbody>
    </table>
 <div class="conOperation">
   		<button id="finshSaveButton" type="button" tabindex="18"><span>完成</span></button>
   		<span><font color="red">(点击完成，方可进入下一步操作！)</font></span>
 	</div>

   <script language="javascript">
   var evalBidRecordList = {};
   evalBidRecordList.beginevalBidRecord =function(subProjId){
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

   $('#finshSaveButton').click(function(){
  	$.getJSON($('#initPath').val()+'/BuyResultController.do?method=finishBuyResult&projectId='+$('#projectId').val(),function(json){
  		if(json.result)alert(json.result);if(json.failure)return;
  		 planTemplateTask.clickMethod($("#projectTaskId").val()+"");
  		 planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		});
	  });
   </script>
   
   	