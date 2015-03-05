<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应商资讯- 【阳光易购】</title>
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
		
				</div>
				<div class="formTips attention">
					<ul><li><em>网站公告展示：</em>发布网站公告，展示网站信息</li></ul>
				</div>	
				<div class="simpleSearch">
					<ul>
						<li><label> 关键字：</label>
							<input type="text" name="searchKeyContentMain" id="searchKeyContentMain" value="" style="width:120px"/>
						</li>
						<li><label> 发布时间：</label>
							<input type="text" name="startTime" id="startTime" readonly="readonly" style="width:80px"/>&nbsp;至
							<input type="text" name="endTime" id="endTime" readonly="readonly" style="width:80px"/>
						</li>
						<li><button type="button" id="searchNewsContentMainBtn"><span>搜索</span></button></li>
					</ul>
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182e6b9c00109.jsp" class="cmsHref_self" title="英特尔全球市场份额第二季度遭到AMD蚕食">英特尔全球市场份额第二季度遭到AMD蚕食</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182e038490104.jsp" class="cmsHref_self" title="电子书市场成鸡肋 传方正爱国者将退出">电子书市场成鸡肋 传方正爱国者将退出</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182d6e4df00f8.jsp" class="cmsHref_self" title="辉瑞料开价百亿美元售惠氏 雀巢达能等有意竞购">辉瑞料开价百亿美元售惠氏 雀巢达能等有意竞购</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182d1b8de00ef.jsp" class="cmsHref_self" title="松下将向海尔出售三洋部分家电部门">松下将向海尔出售三洋部分家电部门</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182cf4ee400ed.jsp" class="cmsHref_self" title="富士康未来三年增百万机器人取代人力">富士康未来三年增百万机器人取代人力</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182ce982800ec.jsp" class="cmsHref_self" title="苹果公司账面资产达463亿英镑 比美国政府有钱">苹果公司账面资产达463亿英镑 比美国政府有钱</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182ce281c00eb.jsp" class="cmsHref_self" title="苹果今年二季度以5.6%市场份额赚66.3%利润">苹果今年二季度以5.6%市场份额赚66.3%利润</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131738e6b2108d5.jsp" class="cmsHref_self" title="富士康与长虹结盟 将在绵阳建手机生产基地">富士康与长虹结盟 将在绵阳建手机生产基地</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01317387204308d1.jsp" class="cmsHref_self" title="东风日产5年投资36亿元扩大发动机产能">东风日产5年投资36亿元扩大发动机产能</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01317380dcd308ca.jsp" class="cmsHref_self" title="产品销售价格下降海普瑞净利减四成">产品销售价格下降海普瑞净利减四成</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131738039bc08c9.jsp" class="cmsHref_self" title="松下出价1.3亿美元 海尔收购三洋白电">松下出价1.3亿美元 海尔收购三洋白电</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131737e5c1208c8.jsp" class="cmsHref_self" title="海尔收购三洋东南亚冰洗资产 合肥三洋或受影响">海尔收购三洋东南亚冰洗资产 合肥三洋或受影响</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013173795e8608c7.jsp" class="cmsHref_self" title="动车事故信号设备商开发布会 称都是技术问题">动车事故信号设备商开发布会 称都是技术问题</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01317376482108c5.jsp" class="cmsHref_self" title="优质供应商可以进行反向选择">优质供应商可以进行反向选择</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013173736a7308c4.jsp" class="cmsHref_self" title="味千汤料原料供应商浮出水面 来自日方伙伴">味千汤料原料供应商浮出水面 来自日方伙伴</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01317370de5908c3.jsp" class="cmsHref_self" title="高铁软件供应商：产品特性导致“赖铁症”较重">高铁软件供应商：产品特性导致“赖铁症”较重</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131736e61b008c1.jsp" class="cmsHref_self" title="高铁硬件供应商：纷纷开辟“新战场”">高铁硬件供应商：纷纷开辟“新战场”</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316e4ed02f06fe.jsp" class="cmsHref_self" title="日本四大轴承制造商因涉嫌价格垄断被调查">日本四大轴承制造商因涉嫌价格垄断被调查</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316e40402506f1.jsp" class="cmsHref_self" title="强势植入变形金刚3的中国公司大热">强势植入变形金刚3的中国公司大热</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316e3e13c006ef.jsp" class="cmsHref_self" title="吉利反驳财务状况质疑 指资产负债率超七成合理">吉利反驳财务状况质疑 指资产负债率超七成合理</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_7.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_9.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：8/29 每页20条 </span>
</div>		
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
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#searchNewsContentMainBtn').click(function() {
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=supplierzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
     
    // 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
})
</script>