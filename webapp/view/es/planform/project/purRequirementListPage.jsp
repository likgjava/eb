<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
    var purRequirementListPage = {};
    purRequirementListPage.showDetail = function(objId){
		$.epsDialog({
	    	title:'申报书条目查询需求信息',
	    	url:$('#initPath').val()+'/TaskPlanController.do?method=toLookTaskPlanSubRequireInfoView&objId='+objId,
	    	width: '800',
	    	height: '420',
	    	onClose: function(){ 
	        	
	       }
		});
	}
   
   </script>
        <table class="tableList" id="purRequirementList">
		<caption>采购需求列表</caption>
  		<thead>
      		<tr >
          		<th class="center" >招标单位名称</th>
          		<th class="center" >采购需求名称</th>
          		<th class="center" >采购数量</th>
          		<th class="center" >单位</th>
          		<th class="center" >需求明细</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${projectMTaskPlanList}" var="projectMTaskPlan" >
		<tr>
			 <td class="center">${projectMTaskPlan.buyMainBody.orgName}</td>
			 <td class="center">${projectMTaskPlan.taskPlanSub.purchaseName}</td>
			 <td class="center">${projectMTaskPlan.taskPlanSub.quantity}</td>
			 <td class="center">${projectMTaskPlan.taskPlanSub.unit}</td>
			 <td class="center"><button class="enable" onclick="purRequirementListPage.showDetail('${projectMTaskPlan.taskPlanSub.objId}');" ><span>详情</span></button></td>
		</tr>
	</c:forEach>
	</tbody>
    </table>   	