<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>威海高新区建立政府采购投标供应商准入机制- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fddece10214.jsp" title="威海高新区建立政府采购投标供应商准入机制" class="cmsHref_self">威海高新区建立政府采购投标供应商准入机制</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>威海高新区建立政府采购投标供应商准入机制</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-大众日报  </span>
					</div>
					<p><P>“自今年在威海地区率先建立政府采购投标供应商诚信档案制度以来,已有12家企业被取消了我区投标资格。”7月5日,高新区政府采购管理办公室主任刘厚纹说。</P>
<P>为进一步完善政府采购领域的廉洁准入机制,确保采购商品物美价廉,高新区将参加采购项目投标的供应商分门别类纳入供应商联系库。目前,该数据库涵盖市政工程、设备购置、服务购买等3大项32小类,涉及企业106家。</P>
<P>凡是参与高新区政府采购活动的企业,均由区检察院对企业及其法定代表人进行行贿犯罪档案查询,发现被查询对象在近三年有行贿、受贿档案记录的,一律取消其投标或中标资格,剔出联系库;对三年前有行贿、受贿犯罪档案记录的,视情节轻重给予取消投标中标资格或扣除一定信誉评价分数等相应处理。对投标供应商实行诚信等级评价,每次采购结束由采购办、纪委和代理机构三方共同对投标供应商诚信状况进行评价,按照评价得分高低建立供应商排行榜,失信企业将被淘汰。</P>
<P>今年以来,威海高新区共完成政府采购额1.4亿元,节减资金1129万元。</P>
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