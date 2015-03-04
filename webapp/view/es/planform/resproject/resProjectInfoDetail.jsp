<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.areaClass{width:70%;margin-left:1px;height:60px;}
.showAgency{display: none;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/resproject/resProjectInfoDetail.js"></script>
<div class="paper"> 
<p class="headInfo">&nbsp;</p>
<input type="hidden" id="from" value="${from}"/>
<form id="resProjectInfoForm" method="post" >
	<div>
		<h5 align="center"><span>项目基本情况</span></h5>
		<table class="tableListP">
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">招标人性质：</th>
			 	<td height="25" >
			 		<span>${resProject.unitPropertyCN}</span>
			   	</td>
			   	<th width="10%" height="25" align="center" valign="middle">招标项目属性：</th>
			 	<td height="25" colspan="3">
			 		<c:choose>
						<c:when test="${resProject.projectType==00}">基础设施</c:when>
						<c:otherwise>
						公用事业
						</c:otherwise>
					</c:choose>
			   	</td>
			</tr>
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">计划批文：</th>
			 	<td height="25" >
			 		<span>${resProject.projApproval}</span>
			   	</td>
			   	<th width="10%" height="25" align="center" valign="middle">规划许可证号：</th>
			 	<td height="25" colspan="3">
			 		<span>${resProject.planPermit}</span>
			   	</td>
			</tr>
			<tr>
				<th width="10%" height="25" align="center" valign="middle">招标项目性质：</th>
			 		<td height="25" >
			 		<span><c:choose>
						<c:when test="${resProject.projNature==00}">新建</c:when>
						<c:when test="${resProject.projNature==01}">扩建</c:when>
						<c:otherwise>
						改建
						</c:otherwise>
					</c:choose></span>
			   	</td>
			 	<th width="10%" height="25" align="center" valign="middle">项目总投资额（元）：</th>
			 	<td height="25" >
			 		<span><fmt:formatNumber type="currency" value="${resProject.amt}"/></span>
			   	</td>
			</tr>
			<tr>
			   	<th width="10%" height="25" align="center" valign="middle">土地证号：</th>
			 	<td height="25" colspan="3">
			 		<span>${resProject.landNo}</span>
			   	</td>
			</tr>
			<tr>
			 	<th width="10%" height="25" align="center" valign="middle">建设工程分类：</th>
			 	<td height="25" colspan="3">
	 			<span><c:choose>
				<c:when test="${resProject.engProjType==00}">房屋建筑工程</c:when>
				<c:when test="${resProject.engProjType==01}">市政公用设施</c:when>
				</c:choose></span>
			   	</td>
			</tr>
			<tr >
			 	<th width="10%" height="25" align="center" valign="middle">是否比选代理机构：</th>
			 	<td height="25" colspan="3">
			 		<span><c:choose>
						<c:when test="${resProject.isVoting==00}">否</c:when>
						<c:when test="${resProject.isVoting==01}">是</c:when>
					</c:choose></span>
			   	</td>
			</tr>
		</table>
	</div>
</form>
<div class="conOperationBtnDiv">
	<button id="back" class="subBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
</div> 
<div class="paper" style="overflow-x:auto" >
<p class="headInfo">&nbsp;</p>
<h5 align="center"><span>项目招标事项申请</span></h5>
	<table width="400px" border="0" class="tableList">
		<thead>
			<tr>
                <th align="center" >招标内容</th>
				<th >合同估算价(万元)</th>
				<th >是否招标</th>
				<th >招标方式</th>
				<th >招标组织形式</th>
				<th >备注</th>
	  		</tr>
		</thead>
		<tbody id="resProjectInfoItem">
		<c:forEach items="${resProjectItemList}" var="resProjectItem">
	   			<tr>
                <td id='itemName' nowrap="nowrap" align="center">
                <input type="hidden" name='itemName'   value="${resProjectItem.itemName }" />
	                <span>${resProjectItem.itemName }</span>
	            </td>
                <td id='contractPrice' align="center">
                	 <span><c:if test="${resProjectItem.contractPrice==null}"></c:if><c:if test="${resProjectItem.contractPrice!=null}">${resProjectItem.contractPrice }</c:if></span>
                </td>
                <td id='isEbuy' nowrap="nowrap" align="center">
                   <input type="hidden" name='isEbuy'   value="${resProjectItem.isEbuy }" />
                   <span><c:choose><c:when test="${resProjectItem.isEbuy==00}">不招标</c:when><c:when test="${resProjectItem.isEbuy==01}">招标</c:when><c:otherwise></c:otherwise></c:choose></span>
                </td>
                <td id='ebuyMethod' nowrap="nowrap" align="center">
                	<input type="hidden" name='ebuyMethod'   value="${resProjectItem.ebuyMethod }" />
	                <span><c:choose><c:when test="${resProjectItem.ebuyMethod==00}">公开招标</c:when><c:when test="${resProjectItem.ebuyMethod==01}">邀请招标</c:when><c:otherwise></c:otherwise></c:choose></span>	
                </td>
                <td id='ebuyStyle' nowrap="nowrap" align="center">
                	<input type="hidden" name='ebuyStyle'   value="${resProjectItem.ebuyStyle }" />
	               	<span><c:choose><c:when test="${resProjectItem.ebuyStyle==00}">自行招标</c:when><c:when test="${resProjectItem.ebuyStyle==01}">委托招标</c:when><c:otherwise></c:otherwise></c:choose></span>
                </td>
                <td id='remark' nowrap="nowrap">
	                <span><c:if test="${resProjectItem.remark==null}"></c:if><c:if test="${resProjectItem.remark!=null}">${resProjectItem.remark }</c:if> </span>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
