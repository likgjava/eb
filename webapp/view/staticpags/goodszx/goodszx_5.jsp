<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供货资讯- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
		
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
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131767356013188141b8c0b1e.jsp" class="cmsHref_self" title="2011年上半年吹塑机出口增幅大于进口">2011年上半年吹塑机出口增幅大于进口</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081317673560131880ef3e80b1b.jsp" class="cmsHref_self" title="日本大地震导致用于汽车的专业涂料短缺">日本大地震导致用于汽车的专业涂料短缺</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081317673560131880d9e800b1a.jsp" class="cmsHref_self" title="6月全球芯片销售额247亿美元 环比降1.2%">6月全球芯片销售额247亿美元 环比降1.2%</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813176735601318808b9a10b16.jsp" class="cmsHref_self" title="发力中高端市场 纺织服装出口价格大涨">发力中高端市场 纺织服装出口价格大涨</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813176735601318800eaf00b12.jsp" class="cmsHref_self" title="工程机械十二五规划发布 销售和出口将双倍增">工程机械十二五规划发布 销售和出口将双倍增</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131767356013187f3af7a0b0b.jsp" class="cmsHref_self" title="中国液态乳制品需求将增四成">中国液态乳制品需求将增四成</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131767356013187f2ed4c0b0a.jsp" class="cmsHref_self" title="LED电视背光需求有望在9月回升">LED电视背光需求有望在9月回升</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01317366c30808bd.jsp" class="cmsHref_self" title="需求释放钢价仍有涨升空间">需求释放钢价仍有涨升空间</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01317362388708ba.jsp" class="cmsHref_self" title="数码相机市场需求趋缓">数码相机市场需求趋缓</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a013173613d3d08b8.jsp" class="cmsHref_self" title="钛白市场未来三年需求或再增100万吨">钛白市场未来三年需求或再增100万吨</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a0131735dad2108b5.jsp" class="cmsHref_self" title="供给大于需求 服装里料市场路在何方">供给大于需求 服装里料市场路在何方</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01316e464d6b06f8.jsp" class="cmsHref_self" title="中国钢铁生产仍然严重依赖进口铁矿石">中国钢铁生产仍然严重依赖进口铁矿石</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01316e4553c606f7.jsp" class="cmsHref_self" title="环保部将加快落实稀土保证金制度">环保部将加快落实稀土保证金制度</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01316e44a9f206f6.jsp" class="cmsHref_self" title="光伏风电行业贫富分化 中小企业面临洗牌">光伏风电行业贫富分化 中小企业面临洗牌</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01316e43e57906f4.jsp" class="cmsHref_self" title="棉花进口量连降3个月 下半年棉价料难大涨">棉花进口量连降3个月 下半年棉价料难大涨</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01316e38e59106eb.jsp" class="cmsHref_self" title="内忧外患下玩具出口企业被迫转战国内市场">内忧外患下玩具出口企业被迫转战国内市场</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01316e32aa1d06e7.jsp" class="cmsHref_self" title="现货供应紧张 沪铝近高不是终点">现货供应紧张 沪铝近高不是终点</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a0131690ed4bb04bd.jsp" class="cmsHref_self" title="我国皮革化学品需求增长迅速">我国皮革化学品需求增长迅速</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a0131640a011d02d9.jsp" class="cmsHref_self" title="我国汽车自主零部件供应体系亟待建立">我国汽车自主零部件供应体系亟待建立</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a013163eb564502c5.jsp" class="cmsHref_self" title="上半年彩电销售量额齐跌 政策透支农村市场">上半年彩电销售量额齐跌 政策透支农村市场</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_4.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_6.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：5/19 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=goodszx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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