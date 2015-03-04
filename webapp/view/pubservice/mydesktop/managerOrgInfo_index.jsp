<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="chapter" id="alipay-notice">
<ul class="sec-tab">
	<li class="selected"><a><span>机构管理</span></a> <span class="arrow"></span>
	</li>
</ul>
<div class="alipay-manage">
<h4><span class="B EN"><b>${userName}</b> 您好!您是${role} <span
	class="login">上次登录:${lastLoginTime}</span> </span></h4>
<h4><a class="action h" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/bizplatform/organization/manager/organization_audit_list.jsp');return false;" lzlinkno="11">待审核机构数(<strong>${task.MC}</strong>)</a>
<a class="action h" href="javascript:void(0);" onclick="$('#conBody').loadPage('OrgQualityController.do?method=toOrgQualityAuditView');return false;" lzlinkno="11">待审核资质数(<strong>${task.ZC}</strong>)</a>
<a class="action h" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/bizplatform/organization/successcase/successcase_audit_list.jsp');return false;" lzlinkno="11">待审核案例数(<strong>${task.AC}</strong>)</a></h4>
<ul>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/bizplatform/organization/manager/organization_manage_list.jsp?appType=XEJY');return false;"
		lzlinkno="13">机构管理 </a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('OrgQualityController.do?method=toOrgQualityAuditView');return false;"
		lzlinkno="16">资质管理</a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/bizplatform/organization/successcase/successcase_audit_list.jsp');return false;"
		lzlinkno="16">案例管理</a></li>
</ul>
</div>
</div>
