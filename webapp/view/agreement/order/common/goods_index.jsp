<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.chapter {
	width: auto;
	margin-bottom: 10px;
}
.sec-tab {
	background:
		url("view/resource/skin/pubservice/img/mytb_v4_bg.png")
		repeat-x scroll center -299px #FFFFFF;
	border-top: 1px solid #C4D5E0;
	height: 22px;
}
.sec-tab li a span {
color:#404040;
font-size:12px;
padding:0 5px;
font-weight: bold;
}
#alipay-notice .alipay-manage li,#alipay-notice .alipay-manage {
	background: url("view/resource/skin/pubservice/img/alipay_bg.png")
		no-repeat scroll 1000px 1000px transparent;
}
#alipay-notice .alipay-manage {
	background-position: -135px 5px;
	height: 100px;
	padding: 0 0 0 80px;
}

#alipay-notice .alipay-manage h4 {
	font-weight: normal;
	margin: 10px 5px;
}
.alipay-manage h4 b{
	font-size:15px;
}
.alipay-manage h4 a{
	padding-right:10px;
}
.alipay-manage h4 .login{
	font-size:11px;
	color:#999999;
}

#alipay-notice .alipay-manage ul {
	overflow: hidden;
}


#alipay-notice .alipay-manage li {
	background-position: 0 0;
	float: left;
	height: 30px;
	line-height: 30px;
	margin: 0 5px;
	text-align: center;
	width: 70px;
}


.sec {
	border-top: 0 none;
	margin: 0;
	padding: 2px 0 2px 70px;
}

.mytaobao-notice {
	overflow: hidden;
	position: relative;
}

.mytaobao-notice .sec {
	border-top: 1px dotted #CCCCCC;
	margin: -1px 0 0;
	padding: 5px 0 7px 80px;
	position: relative;
}

.mytaobao-notice .sec h4 {
	color: #404040;
	font-size: 12px;
	font-weight: normal;
	left: 0;
	position: absolute;
	text-align: right;
	width: 70px;
}

.mytaobao-notice .sec ul {
	margin: 0 0 2px;
}

.mytaobao-notice .sec li {
	float: left;
	line-height: 18px;
	margin: 0 0 3px;
	width: 135px;
}

.mytaobao-notice .sec li.long-text {
	width: 170px !important;
}

.mytaobao-notice .sec li em {
	color: #FF6600;
	font-weight: 700;
	padding: 0;
}

.mytaobao-notice .sec li em.none {
	color: #999999;
}
</style>
<div class="chapter" id="alipay-notice">
<ul class="sec-tab">
	<li class="selected"><a><span>我的商品库</span></a> <span class="arrow"></span>
	</li>
</ul>
<div class="alipay-manage">
<h4><span class="B EN"><b>${userName}</b>
<span class="login">上次登录:${lastLoginTime}</span>
</span></h4>
<h4><a
	class="action h"
	href=""
	lzlinkno="11">有效商品数(<em>5</em>)</a>
	<a
	class="action h"
	href=""
	lzlinkno="11">待审核商品数(<em>1</em>)</a>
	</h4>
<ul>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/brandmng/brand_list_manage.jsp');return false;"
		lzlinkno="13">品牌管理 </a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/goodsmng/goods_manage.jsp');return false;"
		lzlinkno="14">商品管理</a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/goods/goods/fittingmng/accessory_manage.jsp');return false;"
		lzlinkno="15">配件管理</a></li>
	<li><a href="javascript:void(0);" onclick="$('#conBody').loadPage('view/agreement/ebargaing/my_bargain_supplier_list.jsp');return false;"
		lzlinkno="16">协议管理</a></li>
</ul>
</div>
</div>

<div class="chapter chapter-ex">
<ul class="sec-tab">
	<li class="selected"><a><span>我的商品行情</span></a><span class="arrow"></span></li>
</ul>
<div class="mytaobao-notice">
<div class="sec">
<ul>
	<li>行情总数(<em class="none">5</em>)</li>
	<li><a>商品行情数(<em>5</em>)</a></li>
	<li><a>配件行情数(<em>0</em>)</a></li>
</ul>
</div>
</div>
</div>