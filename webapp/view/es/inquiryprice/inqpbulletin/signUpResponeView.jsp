<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
	<h5><span>报名符合性要求设置</span></h5>
	<table border="0" class="table_list">
		<thead>
      		<tr>
          		<th width="20%">指标名称</th>
				<th width="20%">说明</th>
				<th width="20%">报名响应</th>
				<th width="20%">响应附件</th>
     		</tr>
		</thead>
		<tbody id="signupCongruous">
	 	<c:forEach items="${signUpResponeList}" var="signUpRespone">
	   		<tr>
	      		<td >
	                <input id='signupfacId' name="signupfacId" value="${signUpCondFactor.objId }" type="hidden"/>
	                <input id='factorId' name="factorId" value="${signUpCondFactor.factorId }" type="hidden"/>
	                <span id="factorName">${signUpCondFactor.factorName }</span>
				</td>
	    		<td ><textarea name='remark' id='remark' readonly="readonly">${signUpCondFactor.remark }</textarea></td>
	         	<td><textarea name='responseValue' id='responseValue' ></textarea></td>
	          	<td><div id="consContentsFile" class="uploadFile">${consign.consContentsAtt}</div></td>
   			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>	
<script>
var signUpCondFactorView ={}; 

</script>				