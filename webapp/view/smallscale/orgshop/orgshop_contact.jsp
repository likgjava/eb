<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--联系方式-->
<div class="introduction">
	<div class="titleright">
		<h2 class="marginright">联系方式</h2>
	</div>
	<div class="conrig">
		<c:if test="${fn:length(contactList) == 0}"><div class="sorry">暂无联系方式！</div></c:if>
		<c:forEach var="contact" items="${contactList}">
			<div class="list">
				<ul>
					<li><label>联&nbsp;系&nbsp;人&nbsp;：</label><span>${contact.name}</span></li>
					<li><label>联系电话：</label><span>${contact.mobile}</span></li>
					<li><label>电子邮箱：</label><span>${contact.email}</span></li>
					<li><label>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</label><span>${contact.fax}</span></li>
				</ul>
			</div>
		</c:forEach>
		<div class="list">
			<ul>
				<li><label>移动电话：</label><span>${orgInfo.company.mobilePhone}</span></li>
				<li><label>企业地址：</label><span>${orgInfo.company.address}</span></li>
				<li><label>企业邮编：</label><span>${orgInfo.company.postCode}</span></li>
				<li><label>企业邮箱：</label><span>${orgInfo.company.email}</span></li>
				<li><label>企业网址：</label><span><a href="${orgInfo.webUrl}" target="_blank">${orgInfo.webUrl}</a></span></li>
			</ul>
		</div>
	</div>
</div>