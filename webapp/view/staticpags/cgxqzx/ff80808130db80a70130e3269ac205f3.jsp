<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国欧盟商会欢迎自主创新政府采购新规- 【阳光易购】</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
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
		<div id="contentSub" class="index3pa">
			<%@ include file="/view/staticpags/load/left_cms.jsp" %>
		</div>
		<div id="contentMain" class="index3pa">
			<div id="conTitle">
				<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130e3269ac205f3.jsp" title="中国欧盟商会欢迎自主创新政府采购新规" class="cmsHref_self">中国欧盟商会欢迎自主创新政府采购新规</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中国欧盟商会欢迎自主创新政府采购新规</h1>
					<div class="source">
						<span>发布时间：2011-07-01</span>
						<span>发布人：-《财经网》  </span>
					</div>
					<p><P>对7月1日起生效的财政部印发的《关于停止执行&lt;自主创新产品政府采购预算管理办法&gt;等三个文件的通知》 (下简称《通知》)，中国欧盟商会表示了欢迎。</P>
<P>《通知》中涉及的《财政部关于印发〈自主创新产品政府采购预算管理办法〉的通知》、《财政部关于印发〈自主创新产品政府采购评审办法〉的通知》和《财政部关于印发〈自主创新产品政府采购合同管理办法〉的通知》，是自主创新和政府采购挂钩的三个主要文件。</P>
<P>《通知》生效以后，意味着在华投资的外国公司，其外国母公司授权生产的产品也可以参与政府采购的竞争，而不会因为不是自主知识产权而区别对待。</P>
<P>“我们认为《通知》是中国在政府采购领域提供公平竞争环境的一大进步，”中国欧盟商会主席大卫先生评论道，“欧盟商会一直致力于促成自主创新政策与政府采购的脱钩。”</P>
<P>中国欧盟商会认为，上述三个文件阻碍了欧洲企业在某些领域中进入政府采购市场。为了保护欧洲公司在华利益，该组织曾多次对自主创新产品的政府采购问题表示反对。</P>
<P>中国欧盟商会进而表示，取消这三个文件，将促进中国发展技术创新能力，并让欧洲企业通过提供高科技、绿色产品为中国宏观发展目标进一步做出贡献。</P>
<P>中国欧盟商会知识产权工作组主席康保罗认为，“《通知》巩固了温家宝总理去年关于政府采购计划不会根据是否为自主知识产权而区别对待的声明。《通知》的对象是各级政府部门，其中包括省级和市级政府，这让《通知》具有重要意义。”</P>
</p>
				</div>
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

<script>
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
</script>