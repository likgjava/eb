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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4d2652204eb.jsp" class="cmsHref_self" title="品尚红酒获首轮2500万人民币融资 将全力提升用户体验">品尚红酒获首轮2500万人民币融资 将全力提升用户体验</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4d0538404e7.jsp" class="cmsHref_self" title="最差乳业标准或致寡头垄断 巴氏奶完败常温奶">最差乳业标准或致寡头垄断 巴氏奶完败常温奶</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4cefae804c7.jsp" class="cmsHref_self" title="中国电信助推国产CDMA手机厂商走向海外">中国电信助推国产CDMA手机厂商走向海外</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4cd263303fa.jsp" class="cmsHref_self" title="汽车业构建新秩序：大众汽车的全球企图">汽车业构建新秩序：大众汽车的全球企图</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afcd01960634.jsp" class="cmsHref_self" title="黑莓手机遭苹果谷歌蚕食 单日市值跌二成">黑莓手机遭苹果谷歌蚕食 单日市值跌二成</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afc96651062f.jsp" class="cmsHref_self" title="海尔空调关注度强势增长领跑">海尔空调关注度强势增长领跑</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afc76415061b.jsp" class="cmsHref_self" title="三星将为日本运营商KDDI提供LTE设备">三星将为日本运营商KDDI提供LTE设备</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afbe908705f2.jsp" class="cmsHref_self" title="2010年法国波尔多葡萄酒出口量增长20%">2010年法国波尔多葡萄酒出口量增长20%</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afbc9ff205ec.jsp" class="cmsHref_self" title="收购武陵酒业 联想“喝酒”意在整体上市">收购武陵酒业 联想“喝酒”意在整体上市</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afb4866405cc.jsp" class="cmsHref_self" title="丰田全年90万辆销售目标不变 经销商下半年承压">丰田全年90万辆销售目标不变 经销商下半年承压</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afb3d80f05c2.jsp" class="cmsHref_self" title="壳牌与中石油成立合资企业开发天然气">壳牌与中石油成立合资企业开发天然气</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afa42b200586.jsp" class="cmsHref_self" title="收购资产 海尔进入整体上市通道">收购资产 海尔进入整体上市通道</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afa2f36d0584.jsp" class="cmsHref_self" title="武钢与奇瑞将共同出资3.8亿元 建立汽车钢材公司">武钢与奇瑞将共同出资3.8亿元 建立汽车钢材公司</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afa26b890582.jsp" class="cmsHref_self" title="青岛海尔：收购家电上游资产提升盈利水平">青岛海尔：收购家电上游资产提升盈利水平</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130af9c9bbb0505.jsp" class="cmsHref_self" title="落子东南 鞍钢集团收购三钢获批">落子东南 鞍钢集团收购三钢获批</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa9dc7f9015e.jsp" class="cmsHref_self" title="宝马集团实路测试着眼新能源">宝马集团实路测试着眼新能源</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa939c3f013a.jsp" class="cmsHref_self" title="汽车销量连续两月负增长 行业向下趋势确认">汽车销量连续两月负增长 行业向下趋势确认</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa90f9b40128.jsp" class="cmsHref_self" title="乳品新国标再引争议 卫生部:可维护奶业稳定发展">乳品新国标再引争议 卫生部:可维护奶业稳定发展</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa8e9f46010d.jsp" class="cmsHref_self" title="华为预测今年业绩增幅不大 上半年工资又涨11%">华为预测今年业绩增幅不大 上半年工资又涨11%</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa8d5d6f0102.jsp" class="cmsHref_self" title="铁矿之后瞄准焦煤 力拓逼退中印对手拿下非洲大矿">铁矿之后瞄准焦煤 力拓逼退中印对手拿下非洲大矿</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_20.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_22.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：21/29 每页20条 </span>
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