<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="chapter" id="alipay-notice">
<ul class="sec-tab">
	<li class="selected"><a><span>基础数据</span></a> <span class="arrow"></span>
	</li>
</ul>
<div class="alipay-manage">
<h4><span class="B EN"><b>${userName}</b> 您好！ <span
	class="login">上次登录:${lastLoginTime}</span> </span></h4>
<ul>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/bizplatform/base/quota/quota_list.jsp');return false;"
		lzlinkno="13">评价指标 </a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('PurCategoryController.do');return false;"
		lzlinkno="16">采购品目</a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('QualificationController.do');return false;"
		lzlinkno="14">资质管理</a></li>
</ul>
</div>
</div>
