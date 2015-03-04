<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
   <script language="javascript">
 //返回
	$('#evaSellerRecordReturn').click(function(){
		$('#epsDialogCloseReload').click();
	});	  
   </script>
        <table class="tableList" id="SubProjectList">
		<caption>专家评分列表</caption>
  		<thead>
      		<tr>
          		<th class="center">专家姓名
          		</th>
          		<th>专家打分</th>
          		<th>是否推荐</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${evaSellerRecordlist}" var="evaSellerRecord" >
		<tr>
			<td id="expertName" style="width: 55px" class="center">${evaSellerRecord.expertName}</td>
			
			<td id="factorScore" style="width: 55px" class="center">${evaSellerRecord.factorScore}</td>
			
			<td style="width: 55px" class="center">
			     <c:choose>
		      		<c:when test="${evaSellerRecord.recommend== '00'}">不推荐</c:when>
		      		<c:when test="${evaSellerRecord.recommend== '01'}">推荐</c:when>
		      		<c:otherwise>还未评审</c:otherwise>
		      	</c:choose>
			</td>
		</tr>
	</c:forEach>
	</tbody>
    </table>   	
       <div class="conOperation">
			<button id="evaSellerRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
    