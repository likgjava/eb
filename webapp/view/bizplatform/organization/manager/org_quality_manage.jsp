<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/organization/manager/org_quality_manager.js"/>'></script>
<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num4">
        <li class="done"><span class="first">1. 填写基本信息</span></li> 
        <li class="done"><span>2. 填写财务信息</span></li>
        <li class="done current-prev"><span>3. 填写法务信息</span></li>
        <li class="last current"><span>4. 填写企业资质</span></li>
    </ol>
</div>
<!-- end 导航显示 -->

<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>待审核的资质不能修改,新增资质请点击
			<a href="javascript:void(0);" class="sysicon siAdd" id="addQuality"><span><strong>新增资质</strong></span></a>
		</li>
	</ul>
</div>

<!-- statusIndex 计数的变量 -->
<c:set var="statusIndex" value="0"></c:set>
<c:forEach var="quality" items="${orgQuality}" varStatus="status">

	<!-- 显示的 -->
	<c:if test="${quality.qualificationClass.isDisplay==true}"><c:set var="statusIndex" value="${statusIndex+1}"></c:set>
	
	<div class="importantNote myTask <c:if test="${(statusIndex) % 2 == 1}">myTaskL</c:if><c:if test="${(statusIndex) % 2 == 0}">myTaskR</c:if>">
		<h4><span>${quality.qualificationDefine.qualityName }<c:if test="${quality.auditStatus=='03'&&quality.useStatus=='00'}"><font color="red">（被退回！）</font></c:if></span></h4>
			<span class="more">
				<c:if test="${quality.auditStatus!='02'&&quality.auditStatus!='01'}">
					<a href="javascript:void(0);" onclick="orgQulityManager.update('${quality.objId}');return false;" ><strong>[修改]</strong></a>
				</c:if>
				<c:if test="${quality.auditStatus!='02'&&quality.auditStatus!='01'&&quality.useStatus!='02'}">
					<a href="javascript:void(0);" onclick="orgQulityManager.del('${quality.objId}');return false;" ><strong>[删除]</strong></a>
				</c:if>
				<c:if test="${quality.auditStatus=='02'&&quality.useStatus=='01'}">
					<strong><font color="green">[已通过]</font></strong>
				</c:if>
				<c:if test="${quality.auditStatus=='01'}">
					<strong><font color="blue">[正在审核中]</font></strong>
				</c:if>
			</span>
		<ul>
			<c:forEach var="detail" items="${quality.qualificationDetailSet}" >
				<c:if test="${detail.paramValue != null}">
					<li>${detail.qualityParam.qualityName }：<c:if test="${detail.qualityParam.paramType!='5'}">${detail.paramValue}</c:if>
					<c:if test="${detail.qualityParam.paramType=='5'}">
						<c:forEach var = "level" items = "${fn:split(detail.qualityParam.level,',')}">
							<c:if test="${detail.paramValue ==(fn:split(level,'#'))[1] }">${(fn:split(level,'#'))[0]}</c:if>
						</c:forEach>
					</c:if>
					 </li>
				</c:if>
			</c:forEach>
			<c:if test="${quality.qualificationFile != null}">
				<li><a href="javascript:void(0);" name="qualificationFile" id="${quality.qualificationFile }">文件下载</a></li>
			</c:if>
	    </ul>
	</div>
	
	</c:if>
</c:forEach>

