<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.infocenter {

	border:1px solid #EEEEEE;
	padding: 10px 10px 20px 100px;
	position: relative;
}

.infocenter .avatar {
	left: 10px;
	position: absolute;
	top: 10px;
}

.infocenter .avatar img {
	height: 80px;
	width: 80px;
}

.infocenter .avatar .modify {
	background-color: #FFFFFF;
	bottom: 0;
	display: none;
	left: 0;
	opacity: 0.75;
	position: absolute;
	right: 0;
	text-align: center;
	width: 80px;
}

.infocenter .avatar-hover .modify,.infocenter .avatar:hover .modify {
	display: block;
}

.infocenter .info {
	line-height: 23px;
}

.infocenter .info .sstrong {
	font-family: "Microsoft Yahei", "Arial", sans-serif;
	font-size: 21px;
	line-height: 1em;
}

.infocenter .info .vip {
	padding-left:10px;
}

.infocenter .prompt {
	margin-top: 3px;
}

.infocenter .prompt li {
	float: left;
	margin-right: 8px;
	white-space: nowrap;
}

.infocenter .prompt li.logintips {
	color: #999999;
}

.infocenter .notice {
	border: 1px solid #C4D6DA;
	clear: both;
	margin: 15px 0 5px;
}

.infocenter .notice .wrapper {
	background:
		url("view/resource/skin/pubservice/img/mytb_v4_bg.png")
		repeat-x scroll center -750px #F0F6F9;
	border: 1px solid #FFFFFF;
	overflow: hidden;
}

.infocenter .notice .section {
	border-top: 1px solid #E1E2E7;
	line-height: 28px;
	margin: -1px 6px 0;
	padding-left: 70px;
}

.infocenter .notice strong,.infocenter .notice .section ul,.infocenter .notice .section ul li
	{
	float: left;
}

.infocenter .notice strong {
	clear: left;
	font-weight: bold;
	margin-left: -67px;
}

.infocenter .notice .section ul {
	width: 100%;
}

.infocenter .notice .section ul li {
	color: #999999;
	margin-right: 5px;
	min-width: 100px;
	white-space: nowrap;
}

.infocenter .notice .section ul li strong {
	clear: none;
	color: #FF6600;
	float: none;
	font-weight: normal;
	margin: 0;
}

.infocenter .alipay {
	margin-top: 15px;
}

.infocenter .alipay a {
	margin-right: 6px;
}

.infocenter .alipay a.account {
	border-bottom: 1px solid #404040;
	color: #404040;
	text-decoration: none;
}

.infocenter .alipay a.account:hover {
	border-bottom: 1px solid #FF6600;
	color: #FF6600;
}
</style>
<div class="infocenter">
<div class="avatar" id="J_Avatar"><a href=""><img width="80"
	height="80"
	src="view/resource/skin/skin07/img/expertRegist_title.gif"></a>
<a href="" class="modify">添加头像</a></div>
<div class="info"><span class="sstrong">${userName}</span><span class="vip">您好！您是${role}</span>
</div>

<ul class="prompt clearfix">
	<li class="logintips">上次登录:${lastLoginTime}(不是您登录的？<a
		target="_blank" href="">请点这里</a>)</li>
</ul>
<c:if test="${type == '1'}">
<div class="notice">
<div class="wrapper clearfix">
<div class="section clearfix"><strong>系统提醒：</strong>
<ul>
	<c:if test="${task.BP==0}"><li>待采购任务书(0)</li></c:if>
	<c:if test="${task.BP>0}"><li><a title="未完成的任务书" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/order/task/procurement_task_list.jsp');return false;">待采购任务书<strong>(${task.BP})</strong></a></li></c:if>
	<c:if test="${task.BO==0}"><li>待提交订单(0)</li></c:if>
	<c:if test="${task.BO>0}"><li><a title="暂存订单，等您提交给供应商" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/order/order/order_purchaser_list.jsp');return false;">待提交订单<strong>(${task.BO})</strong></a></li></c:if>
	<c:if test="${task.BC==0}"><li>待确认合同(0)</li></c:if>
	<c:if test="${task.BC>0}"><li><a title="已由供应商提交，等您确认" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/contract/contract_purchaser_list.jsp');return false;">待确认合同<strong>(${task.BC})</strong></a></li></c:if>
	<c:if test="${task.BB==0}"><li>进行中议价(0)</li></c:if>
	<c:if test="${task.BB>0}"><li><a title="正在进行中的议价" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/ebargaing/my_bargain_purchaser_list.jsp');return false;">进行中议价<strong>(${task.BB})</strong></a></li></c:if>
</ul>
</div>
</div>
</div>
<div class="alipay">我的购物车： 
 <a class="account" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/order/showGoods/my_shopping_car.jsp');return false;">${task.BG}</a>件商品 
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/order/task/procurement_task_list.jsp');return false;">[我的任务书]</a> 
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/order/order/order_purchaser_list.jsp');return false;">[订单管理]</a>
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/ebargaing/my_bargain_purchaser_list.jsp');return false;">[议价管理]</a> 
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/contract/contract_purchaser_list.jsp');return false;">[合同管理]</a></div>
</c:if>
<c:if test="${type == '2'}">
<div class="notice">
<div class="wrapper clearfix">
<div class="section clearfix"><strong>系统提醒：</strong>
<ul>
	<c:if test="${task.SO==0}"><li>待确认订单(0)</li></c:if>
	<c:if test="${task.SO>0}"><li><a title="采购人已提交，等您确认" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/order/order/order_supplier_list.jsp');return false;">待确认订单<strong>(${task.SO})</strong></a></li></c:if>
	<c:if test="${task.SC==0}"><li>待确认合同(0)</li></c:if>
	<c:if test="${task.SC>0}"><li><a title="已由采购人提交，等您确认" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/contract/contract_supplier_list.jsp');return false;">待确认合同<strong>(${task.SC})</strong></a></li></c:if>
	<c:if test="${task.SB==0}"><li>进行中议价(0)</li></c:if>
	<c:if test="${task.SB>0}"><li><a title="正在进行中的议价" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/ebargaing/my_bargain_supplier_list.jsp');return false;">进行中议价<strong>(${task.SB})</strong></a></li></c:if>
</ul>
</div>
</div>
</div>
<div class="alipay">我的协议：
 <a class="account" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/mamagement/purchase_agreement_list.jsp');return false;">${task.SA}</a>份协议
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/mamagement/agreement_goods_list.jsp');return false;">[协议商品管理]</a> 
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/order/order/order_supplier_list.jsp');return false;">[订单管理]</a>
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/ebargaing/my_bargain_supplier_list.jsp');return false;">[议价管理]</a> 
 <a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/contract/contract_supplier_list.jsp');return false;">[合同管理]</a></div>
</c:if>
</div>