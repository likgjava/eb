<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout partContainers">
	<h5><span>报名符合性要求设置</span></h5>
	<table border="0" class="tableList">
		<thead>
      		<tr>
          		<th>指标名称</th>
				<th>说明</th>
     		</tr>
		</thead>
		<tbody id="signupCongruous">
	 	<c:forEach items="${signUpCondFactorList}" var="signUpCondFactor">
	              <tr>
	                <td class="center">
	                <input id='signupfacId' name="signupfacId" value="${signUpCondFactor.objId }" type="hidden"/>
	                <input id='factorId' name="factorId" value="${signUpCondFactor.factorId }" type="hidden"/>
	                <span id="factorName">${signUpCondFactor.factorName }</span></td>
	                <td class="center"><textarea  name='remark' id='remark' readonly="readonly" style='width:87%;height:40px;'>${signUpCondFactor.remark }</textarea></td>
	              </tr>
	              </c:forEach>
		</tbody>
	</table>
</div>	
<script>
var signUpCondFactorView ={}; 

</script>				