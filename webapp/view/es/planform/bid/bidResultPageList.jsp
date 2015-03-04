<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
   var bidResultPageList = {};
   bidResultPageList.showDetail =function(objId)
   {
	   $.epsDialog({
	        title:'投标详情',
	        url:$('#initPath').val()+'/BidController.do?method=toBidDetail&objId='+objId,
	        width: '900',
	        height: '350',
		    isReload:false,
		    onClose: function(){
		   	  //	checkProjectMenu('menu_evalbid');
		   	//projectagent.treeLeftClick($("#projectTaskId").val()+"");
		   	planTemplateTask.refresh($("#projectTaskId").val()+"");
	      	}
		});	
   }
   </script>
        <table class="tableList" id="SubProjectList" style="width: 99%">
		<caption>已投标单位列表</caption>
  		<thead>
      		<tr >
          		<th class="center" >投标单位名称</th>
          		<th class="center" ><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>详情</th>
     		</tr>
		</thead>
	<tbody>
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${bidList}" var="bid" >
		<tr>
			<td id="supplierName_${subProjectCount}">${bid.supplierName}</td>
			<td class="center"><button class="enable" onclick="bidResultPageList.showDetail('${bid.objId}');" ><span>详情</span></button></td>
		</tr>
		<c:set value="${subProjectCount+1}" var="subProjectCount"/>
	</c:forEach>
	</tbody>
    </table>   	