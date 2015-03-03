<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>${chnl.name}- 阳光易购商圈新闻</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<link type="text/css" rel="stylesheet" href="${resbase}/news/news.css" />
<!--JS-->
<script type="text/javascript" src='/view/srplatform/portal/include/common.js'></script>
</head>

<body>

<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div class="container">
			<div class="news_container">
				<div class="news_content">
					<div class="top_bar">
						<div class="news_breadcrumb">
							<@f.position/>
						</div>
						<div class="clearfloat"></div>
					</div>
					<div class="news_list">
						<!-- 采购动态 -->
						<#if caigoudt?? && caigoudt?size!=0>
							<ul>
								<#list caigoudt as l>
									<li>
										<span class="news_sort"><a href="javascript:void(0);" id="${l.channel.url!}" class="cmsHref_self">[${l.channel.name!}]</a></span>
										<span class="news_title" ><a href="javascript:void(0);" id="${l.url!}" class="cmsHref_self">${l.title!}</a></span>
										<span class="news_date">${l.date?string('yyyy-MM-dd')}</span>	
									</li>
								</#list>
							</ul>
						</#if>
						<!-- 招投标动态 -->
						<#if zhaotoubiaodt?? && zhaotoubiaodt?size!=0>
							<ul>
								<#list zhaotoubiaodt as l>
									<li>
										<span class="news_sort"><a href="javascript:void(0);" id="${l.channel.url!}" class="cmsHref_self">[${l.channel.name!}]</a></span>
										<span class="news_title" ><a href="javascript:void(0);" id="${l.url!}" class="cmsHref_self">${l.title!}</a></span>
										<span class="news_date">${l.date?string('yyyy-MM-dd')}</span>	
									</li>
								</#list>
							</ul>
						</#if>
						<!-- 行业瞭望动态 -->
						<#if hangyeliaowang?? && hangyeliaowang?size!=0>
							<ul>
								<#list hangyeliaowang as l>
									<li>
										<span class="news_sort"><a href="javascript:void(0);" id="${l.channel.url!}" class="cmsHref_self">[${l.channel.name!}]</a></span>
										<span class="news_title" ><a href="javascript:void(0);" id="${l.url!}" class="cmsHref_self">${l.title!}</a></span>
										<span class="news_date">${l.date?string('yyyy-MM-dd')}</span>	
									</li>
								</#list>
							</ul>
						</#if>
						<!-- 经营动态 -->
						<#if jingyingdt?? && jingyingdt?size!=0>
							<ul>
								<#list jingyingdt as l>
									<li>
										<span class="news_sort"><a href="javascript:void(0);" id="${l.channel.url!}" class="cmsHref_self">[${l.channel.name!}]</a></span>
										<span class="news_title" ><a href="javascript:void(0);" id="${l.url!}" class="cmsHref_self"><#if l.title?length gt 18 >${l.title[0..18]}...<#else>${l.title!}</#if></a></span>
										<span class="news_date">${l.date?string('yyyy-MM-dd')}</span>	
									</li>
								</#list>
							</ul>
						</#if>
				  </div>
			  </div>
				<div class="news_sub">
					<div class="recommend_news">
						<h2>新闻推荐</h2>
						<ul>
							<#if xinwen?? && xinwen?size!=0>
								<#list xinwen as l>
									<#if l.str1?? && l.str1=="1">
										<li>·<a href="javascript:void(0);" id="${l.url!}" class="cmsHref_self">${l.title!}</a></li>
									</#if>
								</#list>
							</#if>
						</ul>
					</div>
					<div class="latest_news">
						<h2>最新新闻</h2>
						<ul>
							<#if xinwen?? && xinwen?size!=0>
								<#list xinwen as l>
									<#if !(l.str1??) || l.str1=="0">
										<li>·<a href="javascript:void(0);" id="${l.url!}" class="cmsHref_self">${l.title!}</a></li>
									</#if>
								</#list>
							</#if>
						</ul>
					</div>
					<div class="ad_sub">
						<embed src='http://g.yiqifa.com/gwk/flash/gwk_300_250.swf' flashvars='sid=2_222_0_1&cid=222&bid=0&tar=1&bgc=#ffffff&smc=#0000ff&smul=underline&smb=normal&pc=#ff0000&pul=none&pb=normal&gsid=417264&oid=96865&bc=#F0F0F0&sn=fantia.gouwuke.com' width='300' height='250' quality='high' wmode='transparent' bgcolor='#ffffff' align='middle' allowScriptAccess='sameDomain' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />
					</div>
				</div><!--.news_sub-->	
			</div>
		</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
</div>
</body>

</html>
