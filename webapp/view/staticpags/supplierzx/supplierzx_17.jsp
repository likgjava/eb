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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131020955ca0704.jsp" class="cmsHref_self" title="塑胶供应商苏威拟在华建特种聚合物工厂">塑胶供应商苏威拟在华建特种聚合物工厂</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a701310206feac06fa.jsp" class="cmsHref_self" title="商业智能创新市场引来供应商激烈竞争">商业智能创新市场引来供应商激烈竞争</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013102065b3906f8.jsp" class="cmsHref_self" title="前苹果供应商认罪 曾泄露iPhone机密">前苹果供应商认罪 曾泄露iPhone机密</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fd0438e002c0.jsp" class="cmsHref_self" title="iPhone美国市场份额超黑莓 占8.7%份额">iPhone美国市场份额超黑莓 占8.7%份额</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcf2c0470298.jsp" class="cmsHref_self" title="中铝中色对掐争夺江苏两稀土公司">中铝中色对掐争夺江苏两稀土公司</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcf1e5320296.jsp" class="cmsHref_self" title="美国邦吉退出并购 中粮确认控股塔利糖业">美国邦吉退出并购 中粮确认控股塔利糖业</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcf131e4028b.jsp" class="cmsHref_self" title="比亚迪终止设立汽车金融有限公司">比亚迪终止设立汽车金融有限公司</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcf08cd00289.jsp" class="cmsHref_self" title="京港地铁：停用4号线奥的斯电梯 进行全面检查">京港地铁：停用4号线奥的斯电梯 进行全面检查</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fceedf3401b8.jsp" class="cmsHref_self" title="中国最大蛋企要求CNN向全球皮蛋消费者道歉">中国最大蛋企要求CNN向全球皮蛋消费者道歉</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fced741601b4.jsp" class="cmsHref_self" title="家电巨头扎堆淘金地产 苏宁苏泊尔争相拿地">家电巨头扎堆淘金地产 苏宁苏泊尔争相拿地</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcec5d7d01b2.jsp" class="cmsHref_self" title="雀巢徐福记承认进行收购谈判">雀巢徐福记承认进行收购谈判</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fce7387301a8.jsp" class="cmsHref_self" title="iPhone5已经开始生产首批供货1500万台">iPhone5已经开始生产首批供货1500万台</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcdfde89019e.jsp" class="cmsHref_self" title="飞力达：长三角较领先的IT供应链服务商">飞力达：长三角较领先的IT供应链服务商</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcdee458019c.jsp" class="cmsHref_self" title="太阳能电力供应商中盛光电获5000万美元私募融资">太阳能电力供应商中盛光电获5000万美元私募融资</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7f723376e7c.jsp" class="cmsHref_self" title="青啤以“四度”空间打造中国啤酒第一品牌">青啤以“四度”空间打造中国啤酒第一品牌</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7f129c36e76.jsp" class="cmsHref_self" title="三星液晶面板并入半导体部门">三星液晶面板并入半导体部门</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7e456446e70.jsp" class="cmsHref_self" title="英特尔联手比亚迪共推中国新能源及智能汽车">英特尔联手比亚迪共推中国新能源及智能汽车</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7e30a6d6e6e.jsp" class="cmsHref_self" title="雅诗兰黛旗下多品牌提价近一成">雅诗兰黛旗下多品牌提价近一成</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7e23f236e6c.jsp" class="cmsHref_self" title="发改委约谈俩月后洋奶粉全线涨价 雅培惠氏领衔">发改委约谈俩月后洋奶粉全线涨价 雅培惠氏领衔</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7e1a5e76e6a.jsp" class="cmsHref_self" title="宝马今年第二次提价 豪车逆势热销领跑车市">宝马今年第二次提价 豪车逆势热销领跑车市</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_16.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_18.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：17/29 每页20条 </span>
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