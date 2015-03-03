<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>${news.title}- 阳光易购商圈新闻</title>
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
					<h1>${news.title}</h1>
					<div class="news_sth">
						<p class="news_source"><span class="date">${(news.date)!?string("yyyy年MM月dd")}</span><span class="news_source">来源：${news.origin!}</span></p>
						<div class="news_share">
							<!-- JiaThis Button BEGIN -->
							<div id="ckepop">
								<span class="jiathis_txt">分享到：</span>
								<a class="jiathis_button_qzone">QQ空间</a>
								<a class="jiathis_button_tsina">新浪微博</a>
								<a class="jiathis_button_renren">人人网</a>
								<a class="jiathis_button_kaixin001">开心网</a>
								<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
								<a class="jiathis_counter_style"></a>
							</div>
							<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
							<!-- JiaThis Button END -->
						</div>
						<div class="clearfloat"></div>
					</div>
					<div class="news_real">
						<p class="detailPic">
							<#if news.imgUrl??><img src="${base}/${news.imgUrl}" alt="${news.title}"/></#if>
						</p>
						<p>${content!}</p>
					</div>
					<div class="relate_news">
						<h4>相关报道</h4>
						<ul>
							<#list list as l>
								<#if l.objId!=news.objId>
								<li>·<a href="javascript:void(0);" id="${l.url!}" class="cmsHref_self">${l.title!}</a></li>
								</#if>
							</#list>
						</ul>
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
						<a href='http://p.yiqifa.com/s?sid=2dd514f49eba81d9&pid=91872&wid=413007&vid=627&cid=247&lid=56644&euid=&vwid=' target='_blank'><img border='0'  width='300'  height='250'  src='http://p.yiqifa.com/image?sid=2dd514f49eba81d9&pid=91872&wid=413007&vid=627&cid=247&lid=56644&euid=&vwid=' ></a>
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
