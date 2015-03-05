<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="com.gpcsoft.srplatform.auth.domain.User,com.gpcsoft.plugin.acegi.AuthenticationHelper,com.gpcsoft.core.utils.DateUtil,java.util.Map"%>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/main.css" />
<!--[if IE 6]>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/skin/thems/default/iepngfix/iepngfix_tilebg.js'></script>
<link href="<%=request.getContextPath()%>/view/resource/skin/thems/default/ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->

<%
	User user = AuthenticationHelper.getCurrentUser();
	int year = DateUtil.getYear();
	int month = DateUtil.getMonth() - 1;
	int date = DateUtil.getDate();
	int hour = DateUtil.getHour();
	int minute = DateUtil.getMinute();
	int second = DateUtil.getSecond();
%>

<!-- 页面参数 -->
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
<input type="hidden" id="returnUrl" value="" />
<input type="hidden" id="hrefUrl" value=""/>
<input type="hidden" id="roleType" value="<%=request.getSession().getAttribute("roleStr")%>" />
<input type="hidden" id="globleOrg" value="<%=user==null?null:user.getOrgInfo()%>" />
<input type="hidden" id="currentTab" value="0"/>

<!--网站导航 开始-->
<div class="siteNav">
	<!--用户信息 开始-->
	<p class="userInfo">
<%
		if(user!=null){
%>
			您好，<span class="currentUser" id="loginUser"><a href="javascript:void(0);" id="<%=user.getObjId()%>"><%=user.getUsName()%></a></span>&nbsp;<span id="logout"><a href="javascript:common.unlogin()">[退出]</a></span>
			<span id="security" class="hidden"><span class="modifier">为了你的密码安全，请填写[<a href="javascript:common.addSecurityQuestions()">密保资料</a>]</span></span>
<%
		} else {
%>
			您好，欢迎来阳光易购！<a href="javascript:void(0);" name="toLogin">登录</a> <a title="免费注册成为会员享受更多服务" href="javascript:void(0);" name="toRegistration">免费注册</a>
<%
		}
%>
	</p>
	<!--用户信息 结束-->
	<!--快捷菜单 开始-->
	<ul class="quickMenu" id="quickMenu">
		<li class="shoppingBox"><a href="javascript:common.goinToShopCart();" title="我的购物车" class="shoppingCart"><span>购物车</span><em id="shoppingCartGoodsTotal">0</em> 件</a></li>
	
		<authz:authorize ifAnyGranted="xejy"><li id="numberAreaXY" class="hidden"><a href="javascript:void(0);" class="dzcghome">电子采购室</a></li></authz:authorize>
		<authz:authorize ifAnyGranted="ztb"><li id="numberAreaZT" class="hidden"><a href="javascript:void(0);" class="ztbhome">电子招标室</a></li></authz:authorize>
		
		<%@ include file="/view/srplatform/portal/include/header_tool.jsp" %>
	</ul>
	<!--快捷菜单 结束-->
</div>
<!--网站导航 结束--> 
<!--网站基础信息 开始-->
<div class="branding">
	<a target="_self" href="<%=request.getContextPath()%>/"><h1>阳光易购</h1></a>
	<p class="hotline "><label>咨询热线</label><em>010-88356236</em></p>
</div>
<!--网站基础信息 结束--> 
<!--主导航菜单 开始-->
	<ul class="navMain">
		<li class="selected"><a id="goToIndex" href="javascript:void(0);" title="首页">首页</a></li>
		<li><a id="goToBidding" href="javascript:void(0);" title="电子招标">电子招标</a></li>
		<li><a id="goToBargain" href="javascript:void(0);" title="电子采购">电子采购</a></li>
		<li><a id="goToGoods" href="javascript:void(0);" title="商品库">商品库</a></li>
		<li><a id="goToSupplier" href="javascript:void(0);" title="供应商库">供应商库</a></li>
		<li><a id="goToGoodsMall" href="javascript:void(0);" title="精品商城">精品商城</a></li>
	</ul>
<!--主导航菜单 结束-->
<!--统计数据 开始-->
	<ul class="statData">
		<li><label for="purUnits">认证采购单位：</label><em id="purUnits">${orgCountMap["buyerCount"]}</em> 家</li>
		<li><label for="suppliers">认证供应商：</label><em id="suppliers">${orgCountMap["supplierCount"]}</em> 家</li>
		<%@ include file="/view/staticpags/load/index_count.jsp" %>
	</ul>
<!--统计数据 结束-->
<!--网站搜索 开始-->
<div class="search">
	<input type="hidden" id="searchType" value="1" <c:if test="${param.searchType!=null}">name="${param.searchType}"</c:if> />
	<ul class="searchTabs">
		<li class="selected"><a href="javascript:void(0);" onclick="keyWordTypeChange(this,'1');">找商品</a></li>
		<li><a href="javascript:void(0);" onclick="keyWordTypeChange(this,'2');">找公告</a></li>
		<li><a href="javascript:void(0);" onclick="keyWordTypeChange(this,'3');">找供应商</a></li>
		<li><a href="javascript:void(0);" onclick="keyWordTypeChange(this,'4');">找专家</a></li>
	</ul>
	<div class="searchPanel">
		<input type="text" id="keyWords" />
		<button onclick="keyWordSearch();return false;">搜索</button>
	</div>
	<!-- 热门标签 开始 -->
	<input type="hidden" id="hotTagsMaxResult" value="${hotTagsMaxResults}" />
	<input type="hidden" id="hotTagsType" value="${hotTagsType}" />
	<p class="searchHot" id="hotTagsListP">
		<label>热门搜索：</label>
	</p>
	<!-- 热门标签 结束 -->
</div>
<!--网站搜索 结束-->

<script>
var gpcsoftDate = new Date("<%=year%>","<%=month%>","<%=date%>","<%=hour%>","<%=minute%>","<%=second%>");//设定 当前 服 器时间 

$(document).ready(function(){
	//判断是否登录
	if(common.isLogin(false)){
		$("#numberAreaXY").show();
		$("#numberAreaZT").show();
	}

	//获取热门标签
	var maxResults = ($('#hotTagsMaxResult').val()=='' ? 5 : $('#hotTagsMaxResult').val());
	var tagsType = ($('#hotTagsType').val()=='' ? '01' : $('#hotTagsType').val());
	$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getHotTagsList",{'maxResults':maxResults,'tagsType':tagsType},function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		$.each(json.hotTagsList, function(index,hotTag){
			var tagsDscr = (hotTag.tagsDscr==null ? hotTag.tagsName : hotTag.tagsDscr);
			var oper = ' <a href="javascript:void(0);" name="gTag" title="'+tagsDscr+'" onclick="keyWordSearch(\''+hotTag.tagsName+'\');return false;">'+hotTag.tagsName+'</a> ';
			$('#hotTagsListP').append(oper);
		});
	});

	//获取购物车信息
	getCartInfo();

	//监听搜索框的回车事件
	$('#keyWords').keypress(function(event){
		if(event.keyCode == 13){
			keyWordSearch();
			return false;
		}
	});
});
</script>