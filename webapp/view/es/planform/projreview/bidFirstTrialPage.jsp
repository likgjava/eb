<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>   

        <input type="hidden" id="type" name="type" value="${type}">
        <table class="tableList" id="SubProjectList">
		<caption>投标单位列表</caption>
      		<tr>
          		<th class="center" style="width: 20%;">投标单位名称</th>
          		<c:forEach items="${WorkgMemberList}" var="workgMember">
          		<th class="center">${workgMember.workgmName}</th>
          		</c:forEach>
          		
     		</tr>
		<c:set value="0" var="subProjectCount"/>
		<c:forEach items="${evaSellerRecordList}" var="evaSellerRecord" >
		<tr>
			<td id="supplierName_${subProjectCount}">${evaSellerRecord.supplierName}</td>
				<c:forEach items="${WorkgMemberList}" var="workgMember">
          		<td  class="center"><input type="checkbox" id="" ></td>
         		</c:forEach>
		</tr>
		<c:set value="${subProjectCount+1}" var="subProjectCount"/>
	</c:forEach>
    </table> 
 <script language="javascript">
   var evalBidRecordList = {};
   //查看评审结果

   $(document).ready(function(){
	   evalBidRecordList.beginEvalBidRecord =function(count,supplierId,subProjId,objId)
	   {
		   $.epsDialog({
		        title:'评审',
		        url:$('#initPath').val()+'/EvaSellerRecordController.do?method=getAllAuditMessage&supplierId='+supplierId+'&subProjId='+subProjId+'&objId='+objId+'&projectId='+$("#projectId").val(),
		        width: '600',
		        height: '170',
			    isReload:false,
			    onClose: function(){
				    if($("#projectTaskId") && $("#projectTaskId").val() != ""){
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				    }
		      	}
			});	
	   }



	   //录入评审结果
	   evalBidRecordList.keyinEvalBidRecord =function(count,supplierId,subProjId,objId,quoteSum)
	   {
		   $.epsDialog({
		        title:'评审',
		        url:$('#initPath').val()+'/EvaSellerRecordController.do?method=getKeyInEvalPage&supplierId='+supplierId+'&subProjId='+subProjId+'&objId='+objId+'&projectId='+$("#projectId").val()+'&quoteSum='+quoteSum,
		        width: '600',
		        height: '170',
			    isReload:true,
			    onClose: function(){
				    if($("#projectTaskId") && $("#projectTaskId").val() != ""){
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				    }
		      	}
			});	
	   }
		
	   })
   </script>  	