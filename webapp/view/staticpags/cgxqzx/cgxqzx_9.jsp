<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>采购焦点资讯- 【阳光易购】</title>
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
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b6f8005060f.jsp" class="cmsHref_self" title="国信招标集团首次应用电子化平台公开招标">国信招标集团首次应用电子化平台公开招标</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b6cf3d80603.jsp" class="cmsHref_self" title="投标商应用电子化招标平台投标反馈良好">投标商应用电子化招标平台投标反馈良好</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b378edc0420.jsp" class="cmsHref_self" title="前5月公务车采购量狂跌五成 车企加快脚步">前5月公务车采购量狂跌五成 车企加快脚步</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b1af2ca0387.jsp" class="cmsHref_self" title="河南省财政2.7亿元补贴公用发电企业电煤采购">河南省财政2.7亿元补贴公用发电企业电煤采购</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b18a0750383.jsp" class="cmsHref_self" title="冀东油田供应处集中采购降成本">冀东油田供应处集中采购降成本</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b1802de0381.jsp" class="cmsHref_self" title="浙江采购人参与政采有限时门槛">浙江采购人参与政采有限时门槛</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b168a170379.jsp" class="cmsHref_self" title="单一来源采购申请：专家意见忌含糊">单一来源采购申请：专家意见忌含糊</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b15fb490377.jsp" class="cmsHref_self" title="政府终端显示类采购5月观察 机顶盒项目采购一马当先">政府终端显示类采购5月观察 机顶盒项目采购一马当先</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b156e810375.jsp" class="cmsHref_self" title="同方电脑：批量集中采购可瓦解价格垄断">同方电脑：批量集中采购可瓦解价格垄断</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b13c3c30373.jsp" class="cmsHref_self" title="浙江引入成交控制价防高价采购">浙江引入成交控制价防高价采购</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090ecf7a800f2.jsp" class="cmsHref_self" title="甘肃严打采购使用“瘦肉精”违法犯罪行为">甘肃严打采购使用“瘦肉精”违法犯罪行为</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090ec633000f0.jsp" class="cmsHref_self" title="豪华二手车扎堆卖 外地车商为采购主力">豪华二手车扎堆卖 外地车商为采购主力</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090eb6cde00ee.jsp" class="cmsHref_self" title="公务车采购出新标准 自主品牌感觉良好">公务车采购出新标准 自主品牌感觉良好</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090ea72ec00ec.jsp" class="cmsHref_self" title="LCD采购量激增昭示3D电视褪去高端光环">LCD采购量激增昭示3D电视褪去高端光环</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090e97bf300ea.jsp" class="cmsHref_self" title="市政协召开政府采购工作民主监督座谈会">市政协召开政府采购工作民主监督座谈会</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090e8de3e00e8.jsp" class="cmsHref_self" title="上半年中国政府采购云计算项目7个 迎来政采盛宴">上半年中国政府采购云计算项目7个 迎来政采盛宴</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090e826e800e6.jsp" class="cmsHref_self" title="支持重大装备国产化 中石油采购全焊接球阀新产品">支持重大装备国产化 中石油采购全焊接球阀新产品</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090dad8ea000b.jsp" class="cmsHref_self" title="石油三巨头与民营油企利益交锋 博弈成品油定价新规">石油三巨头与民营油企利益交锋 博弈成品油定价新规</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090da66c60009.jsp" class="cmsHref_self" title="3d电视半年降价三成">3d电视半年降价三成</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b9db65011d4.jsp" class="cmsHref_self" title="国采中心电梯采购3年逾7000万元">国采中心电梯采购3年逾7000万元</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_8.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_10.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：9/18 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=cgxqzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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