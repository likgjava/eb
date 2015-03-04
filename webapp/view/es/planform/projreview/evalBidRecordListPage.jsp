<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   
   <script language="javascript">
   var evalBidRecordList = {};
   evalBidRecordList.beginevalBidRecord =function(count,supplierId,subProjId,objId)
   {
	   
	   $.epsDialog({
	        title:'评审',
	        url:$('#initPath').val()+'/EvaSellerRecordController.do?method=toEvaSellerRecordForm&isAJAX=false&count='+count+'&supplierId='+supplierId+'&subProjId='+subProjId+'&objId='+objId+'&projectId='+$("#projectId").val(),
	        width: '900',
	        height: '300',
		    isReload:false,
		    onClose: function(){
		   		$('#superviseTabDiv1').loadPage($('#initPath').val()+'/EvaSellerRecordController.do?method=getEvaSellerRecordList&subProjId='+subProjId+"&signinStatus="+$("#signinStatus").val());
	      	}
		});	
   }

   evalBidRecordList.showDetail =function(objId)
   {
	   $.epsDialog({
	        title:'投标详情',
	        url:$('#initPath').val()+'/BidController.do?method=toBidDetail&objId='+objId,
	        width: '900',
	        height: '350',
		    isReload:false,
		    onClose: function(){
		   	  //	checkProjectMenu('menu_evalbid');
		   	 	
		   		planTemplateTask.refresh($("#projectTaskId").val()+"");
	      	}
		});	
   }

   </script>
        <input type="hidden" id="projectId" name="projectId" value="${projectId}">
        <input type="hidden" id="signinStatus" name="signinStatus" value="${signinStatus}">
        <table class="tableList" id="SubProjectList">
		<caption>投标单位列表&nbsp;&nbsp;&nbsp;&nbsp;
		<!--<a href="#" onClick="returnBack();">[返回]</a>  -->
</caption>
  		<thead>
      		<tr>
          		<th class="center">投标单位名称
          		</th>
          		<th>报价总金额(元)</th>
          		<th>是否推荐</th>
          		<th class="operation">操作</th>
          		<th class="operation">查看<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>详细</th>
     		</tr>
		</thead>
	<tbody>
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${evaSellerRecordList}" var="evaSellerRecord" >
		<tr>
			<td id="supplierName_${subProjectCount}" class="center">${evaSellerRecord.supplierName} 
			</td>
			<td id="quoteSum_${subProjectCount}" class="center">${evaSellerRecord.quoteSum}
			</td>
			<td class="center">
			     <c:choose>
		      		<c:when test="${evaSellerRecord.recommend== '00'}">不推荐</c:when>
		      		<c:when test="${evaSellerRecord.recommend== '01'}">推荐</c:when>
		      		<c:otherwise>还未评审</c:otherwise>
		      	</c:choose>
			</td>
			<c:if test="${signinStatus=='01'}">
			<td class="center">
				<button class="sysicon siModify" onclick="evalBidRecordList.beginevalBidRecord('${subProjectCount }','${evaSellerRecord.supplier.objId}','${evaSellerRecord.subProjId}','${evaSellerRecord.objId}');" title="点击,进行专家评审" ><span>进行评审</span></button>
			</td>
			</c:if>
			<c:if test="${signinStatus=='00'||signinStatus=='undefined'}">
			<td class="center">
				<button class="sysicon siModify" onclick="alert('还未签到!');"><span>进行评审</span></button>
			</td>
			</c:if>
			<td class="center"><button class="enable" onclick="evalBidRecordList.showDetail('${evaSellerRecord.bidId}');" ><span>详情</span></button></td>
		</tr>
		<c:set value="${subProjectCount+1}" var="subProjectCount"/>
	</c:forEach>
	</tbody>
    </table>   	