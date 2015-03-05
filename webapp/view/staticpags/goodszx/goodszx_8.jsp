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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131183592013120fbd8830e64.jsp" class="cmsHref_self" title="GFMS：预计2011年矿产的供应量将会进一步增长">GFMS：预计2011年矿产的供应量将会进一步增长</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131183592013120fb0a6e0e62.jsp" class="cmsHref_self" title="生猪供应将增加 猪肉价格将趋稳">生猪供应将增加 猪肉价格将趋稳</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201311bea606a03a5.jsp" class="cmsHref_self" title="瓶装饮用水全新标准酝酿 必须标明水源">瓶装饮用水全新标准酝酿 必须标明水源</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201311bdd5b0b0389.jsp" class="cmsHref_self" title="全球需求走弱 面板价格涨势喊停">全球需求走弱 面板价格涨势喊停</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201311bd409f30371.jsp" class="cmsHref_self" title="河南济源散装水泥供应量再创历史新高">河南济源散装水泥供应量再创历史新高</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a7013116d267cd10e7.jsp" class="cmsHref_self" title="触摸屏全年需求涨三成 整体产能仍然供过于求">触摸屏全年需求涨三成 整体产能仍然供过于求</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a7013116bae8a010bb.jsp" class="cmsHref_self" title="中国医疗器械市场活跃 有望成全球第二大出口国">中国医疗器械市场活跃 有望成全球第二大出口国</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a701310759f4990bf4.jsp" class="cmsHref_self" title="上半年国产乘用车销量增5.3% 市场仍存探底悬念">上半年国产乘用车销量增5.3% 市场仍存探底悬念</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a701310757d8eb0bf0.jsp" class="cmsHref_self" title="粗钢日产量首次突破200万吨">粗钢日产量首次突破200万吨</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a701310745e7210bca.jsp" class="cmsHref_self" title="海南生猪满足岛内供应 9300万元扶持生猪养殖">海南生猪满足岛内供应 9300万元扶持生猪养殖</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70131074243830bc2.jsp" class="cmsHref_self" title="新措稳定生猪生产保供应">新措稳定生猪生产保供应</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a701310231dd0b0750.jsp" class="cmsHref_self" title="奢侈品关税未降 LV全线产品价格上涨">奢侈品关税未降 LV全线产品价格上涨</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a701310212de3d0714.jsp" class="cmsHref_self" title="小麦需求较为平淡 未来可能开始震荡走强">小麦需求较为平淡 未来可能开始震荡走强</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a7013102100956070e.jsp" class="cmsHref_self" title="中国需求或继续推涨坚果价格">中国需求或继续推涨坚果价格</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70131020f3e40070a.jsp" class="cmsHref_self" title="平板电视需求放缓 3D电视或掀新一轮热潮">平板电视需求放缓 3D电视或掀新一轮热潮</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70131020de1740708.jsp" class="cmsHref_self" title="中国铁矿石需求不会下降">中国铁矿石需求不会下降</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70131020894750702.jsp" class="cmsHref_self" title="供应量充足的申花板块 下半年最有可能有场恶战">供应量充足的申花板块 下半年最有可能有场恶战</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130fce537b501a6.jsp" class="cmsHref_self" title="西瓜供应量减了一多半 批发价涨了一成">西瓜供应量减了一多半 批发价涨了一成</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130fce4af9e01a4.jsp" class="cmsHref_self" title="海口：每日至少增投150头生猪供应限价肉">海口：每日至少增投150头生猪供应限价肉</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130fce39a3201a2.jsp" class="cmsHref_self" title="肉价缘何淡季冲高 供应趋紧是主因">肉价缘何淡季冲高 供应趋紧是主因</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_7.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_9.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：8/19 每页20条 </span>
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