<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/recordFormDetail.js"></script>
<div >     	
    <h5 align="center"><span>建设工程招标投标登记表</span></h5>
 	<p>&nbsp;</p>
	<form id="recordFormDetail" method="post">
		<div>
			 <table class="tableListP">
			 <tr>
			 	<th colspan="4" width="100%" height="25" align="center" valign="middle">交易号：</th>
			 </tr>
			 <tr>
			 	<th width="10%" height="25" align="center" valign="middle">建设单位名称：</th>
			 	<td height="25" >
			 		<span id="recFormOrgName">${recordForm.recFormOrgName}</span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">单个建筑面积(m2)：</th>
			 	<td height="25" colspan="3">
			 		<span id="singleArea">${recordForm.singleArea}</span>
		    	</td>
			 </tr>
			 <tr>
			 	<th width="10%" height="25" align="center" valign="middle">工程名称：</th>
			 	<td height="25" >
			 		<span id="engineeringName">${recordForm.engineeringName}</span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">建设地点：</th>
			 	<td height="25" >
					<span id="recFormAddr">${recordForm.recFormAddr}</span>
		    	</td>
			 </tr>
			 <tr>
			 	<th width="10%" height="25" align="center" valign="middle">计划批文：</th>
			 	<td height="25" >
			 		<span id="projApproval">${recordForm.projApproval}</span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">资金来源：</th>
			 	<td height="25" >
			 		<span id="moneySource">${recordForm.moneySource}</span>
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">投资金额（元）：</th>
			 	<td height="25" >
			 		<span id="investent"><fmt:formatNumber type="currency" value="${recordForm.investent}"/></span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">规划许可证：</th>
			 	<td height="25" >
			 		<span id="planPermit">${recordForm.planPermit}</span>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">建筑总面积(m2)：</th>
			 	<td height="25" >
			 		<span id="totalArea">${recordForm.totalArea}</span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">工程个数：</th>
			 	<td height="25" >
			 		<span id="projNumber"><fmt:formatNumber type="number" value="${recordForm.projNumber}"/></span>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">结构形式：</th>
			 	<td height="25" >
			 		<span id="recFormStructureType">${recordForm.recFormStructureType}</span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">工程范围：</th>
			 	<td height="25" >
					<span id="recFormContent">${recordForm.recFormContent}</span>
		    	</td>
			 </tr>
			 <tr>
			 	<th width="10%" height="25" align="center" valign="middle">地上层数：</th>
			 	<td height="25" >
			 		<span id="layerOverRound"><fmt:formatNumber type="number" value="${recordForm.layerOverRound}"/></span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">地下层数：</th>
			 	<td height="25" >
			 		<span id="layerUnderRound"><fmt:formatNumber type="number" value="${recordForm.layerUnderRound}"/></span>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">檐口高度(m)：</th>
			 	<td height="25" >
			 		<span id="cornice"><fmt:formatNumber type="number" value="${recordForm.cornice}"/></span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">跨度(m)：</th>
			 	<td height="25" >
			 		<span id="span"><fmt:formatNumber type="number" value="${recordForm.span}"/></span>
		    	</td>
			 </tr>
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">联系人：</th>
			 	<td height="25" >
			 		<span id="linkerName">${recordForm.linkerName}</span>
		    	</td>
		    	<th width="10%" height="25" align="center" valign="middle">联系电话：</th>
			 	<td height="25" >
			 		<span id="linkerTel">${recordForm.linkerTel}</span>
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">单位性质：</th>
			 	<td height="25" colspan="3">
			 		<span id="unitProperty">${recordForm.unitPropertyCN}</span>
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">招标方式：</th>
			 	<td height="25" colspan="3">
			 		<span id="ebuyMethod">${recordForm.ebuyMethodCn}</span>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">项目属性：</th>
			 	<td height="25" colspan="3">
			 		<span id="projProperty">
					<c:choose>
						<c:when test="${recordForm.projProperty==00}">基础设施</c:when>
						<c:otherwise>
						公用事业
						</c:otherwise>
					</c:choose>
					</span>
		    	</td>
			 </tr>
			  <tr>
			 	<th width="10%" height="25" align="center" valign="middle">项目性质：</th>
			 	<td height="25" colspan="3">
			 		<span id="projNature">
			 		<c:choose>
						<c:when test="${recordForm.projNature==00}">新建</c:when>
						<c:when test="${recordForm.projNature==01}">扩建</c:when>
						<c:otherwise>
						改建
						</c:otherwise>
					</c:choose>
			 		</span>
		    	</td>
			 </tr>
			   <tr>
			 	<th width="10%" height="25" align="center" valign="middle">招标组织形式：</th>
			 	<td height="25" colspan="3">
			 		<span id="recFormBuyMethod">
			 		${recordForm.recFormBuyMethodCn}
			 		</span>
		    	</td>
			 </tr>
			</table>
		</div>
	</form>		
</div>		