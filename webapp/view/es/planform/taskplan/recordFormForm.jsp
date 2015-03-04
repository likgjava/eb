<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/recordFormForm.js"></script>
<div class="paper">     	
  	<div class="operationBtnDiv">
      <button id="printBtn" class="iconBtn" type="button"><span>打印</span></button>
   </div>
    <h1><span>建设工程招标投标登记表</span></h1>
 	<p class="headInfo">&nbsp;</p>
	<form id="recordFormForm" method="post">
		<div>
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			 <table class="tableListP">
			 <tr>
			 	<th colspan="4" width="100%" height="25" align="center" valign="middle">交易号：</th>
			 </tr>
			 <tr>
			 	<th width="10%" height="25" align="center" valign="middle">建设单位名称：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" >
					<input type="text" name="recFormOrgName" id="recFormOrgName" class="required maxInput"  maxlength="250" value="${recordForm.recFormOrgName}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">工程名称：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" >
					<input type="text" name="engineeringName" id="engineeringName" class="required maxInput"  maxlength="250" value="${recordForm.engineeringName}" />
		    	</td>
			 </tr>
			  <tr>
		    	<th width="10%" height="25" align="center" valign="middle">规划许可证：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" >
					<input type="text" name="planPermit" id="planPermit" class="required" maxlength="250" value="${recordForm.planPermit}"/>
		    	</td>
			 	<th width="10%" height="25" align="center" valign="middle">计划批文：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" >
					<input type="text" name="projApproval" id="projApproval" class="required maxInput"  maxlength="250" value="${recordForm.projApproval}"/>
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">投资金额（元）：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" >
					<input type="text" name="investent" id="investent" maxlength="250" class="required money" value="${recordForm.investent}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">资金来源：</th>
			 	<td height="25" >
					<input type="text" name="moneySource" id="moneySource" value="${recordForm.moneySource}"/>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">单个建筑面积(m2)：</th>
			 		<td height="25">
					<input type="text" name="singleArea" id="singleArea" value="${recordForm.singleArea}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">建设地点：</th>
			 	<td height="25" >
					<input type="text" name="recFormAddr" id="recFormAddr" value="${recordForm.recFormAddr}"/>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">建筑总面积(m2)：</th>
			 	<td height="25" >
					<input type="text" name="totalArea" id="totalArea" value="${recordForm.totalArea}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">工程个数：</th>
			 	<td height="25" >
					<input type="text" name="projNumber" id="projNumber" class="digits" value="${recordForm.projNumber}"/>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">结构形式：</th>
			 	<td height="25" >
					<input type="text" name="recFormStructureType" id="recFormStructureType" value="${recordForm.recFormStructureType}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">工程范围：</th>
			 	<td height="25" >
					<input type="text" name="recFormContent" id="recFormContent" value="${recordForm.recFormContent}"/>
		    	</td>
			 </tr>
			 <tr>
			 	<th width="10%" height="25" align="center" valign="middle">地上层数：</th>
			 	<td height="25" >
					<input type="text" name="layerOverRound" id="layerOverRound" class="digits" value="${recordForm.layerOverRound}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">地下层数：</th>
			 	<td height="25" >
					<input type="text" name="layerUnderRound" id="layerUnderRound" class="digits" value="${recordForm.layerUnderRound}"/>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">檐口高度(m)：</th>
			 	<td height="25" >
					<input type="text" name="cornice" id="cornice" class="floats" value="${recordForm.cornice}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">跨度(m)：</th>
			 	<td height="25" >
					<input type="text" name="span" id="span" class="floats" value="${recordForm.span}"/>
		    	</td>
			 </tr>
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">联系人：</th>
			 	<td height="25" >
					<input type="text" name="linkerName" id="linkerName"  value="${recordForm.linkerName}"/>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">联系电话：</th>
			 	<td height="25" >
					<input type="text" name="linkerTel" id="linkerTel" value="${recordForm.linkerTel}"/>
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">单位性质：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
					<html:select styleClass="required" id="unitProperty" name="unitProperty" code="unitProperty" selectedValue="${recordForm.unitProperty}">
					</html:select>
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">招标方式：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
 					<input type="radio" name="ebuyMethod" class="required" value="00" <c:if test="${recordForm.ebuyMethod=='00'}">checked="checked"</c:if>/>公开招标&nbsp;&nbsp;
					<input type="radio" name="ebuyMethod" class="required" value="01" <c:if test="${recordForm.ebuyMethod=='01'}">checked="checked"</c:if>/>邀请招标
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">项目属性：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
					<input type="radio" name="projProperty" class="required" value="00" <c:if test="${recordForm.projProperty=='00'}">checked="checked"</c:if>/>基础设施&nbsp;&nbsp;
					<input type="radio" name="projProperty" class="required" value="01" <c:if test="${recordForm.projProperty=='01'}">checked="checked"</c:if>/>公用事业
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">项目性质：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
					<input type="radio" name="projNature" class="required" value="00" <c:if test="${recordForm.projNature=='00'}">checked="checked"</c:if>/>新建&nbsp;&nbsp;
					<input type="radio" name="projNature" class="required" value="01" <c:if test="${recordForm.projNature=='01'}">checked="checked"</c:if>/>扩建&nbsp;&nbsp;
					<input type="radio" name="projNature" class="required" value="02" <c:if test="${recordForm.projNature=='02'}">checked="checked"</c:if>/>改建
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">招标组织形式：<span class="eleRequired">(*必填项)</span></th>
			 	<td height="25" colspan="3">
					<input type="radio" name="recFormBuyMethod" id="recFormBuyMethod" value="00" class="required" <c:if test="${recordForm.recFormBuyMethod=='00'}">checked="checked"</c:if>>自行组织招标&nbsp;&nbsp;
					<input type="radio" name="recFormBuyMethod" id="recFormBuyMethod" value="01" class="required" <c:if test="${recordForm.recFormBuyMethod=='01'}">checked="checked"</c:if>>委托组织招标
		    	</td>
			 </tr>
			</table>
		</div>
		<input type="hidden" name="useStatus" value="00">
	</form>		
		<div class="conOperation">
			<button type="button" id="recordFormSave"><span>保存</span></button>
			<button type="button" id="recordFormSubmit"><span>提交</span></button>
			<button type="button" id="recordFormReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		</div>
</div>		