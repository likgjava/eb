<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="chapter" id="alipay-notice">
<ul class="sec-tab">
	<li class="selected"><a><span>商品库管理</span></a> <span class="arrow"></span>
	</li>
</ul>
<div class="alipay-manage">
<h4><span class="B EN"><b>${userName}</b>, 您好! <span
	class="login">上次登录:${lastLoginTime}</span> </span></h4>
<h4><a class="action h" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/brandmng/brand_list_audit_page.jsp');return false;" lzlinkno="11">待审核品牌数(<em>${task.GB}</em>)</a>
<a class="action h" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/goodsmng/goods_audit_list.jsp');return false;" lzlinkno="11">待审核商品数(<em>${task.GG}</em>)</a></h4>
<ul>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/brandmng/brand_list_manage.jsp');return false;"
		lzlinkno="13">品牌管理 </a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goodsclass/goodsclass_manage.jsp');return false;"
		lzlinkno="16">分类管理</a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/goodsmng/goods_audit_list.jsp');return false;"
		lzlinkno="14">商品管理</a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/assignmodifier/brand_list_assignmodifier.jsp');return false;"
		lzlinkno="14">维护商管理</a></li>
</ul>
</div>
</div>
