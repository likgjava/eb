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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813225280f013227a9515b002b.jsp" class="cmsHref_self" title="新材料规划7日发布 关键新材料自给率提高到70%">新材料规划7日发布 关键新材料自给率提高到70%</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081321f7bae01322280bd0f0054.jsp" class="cmsHref_self" title="需求增加原材料涨价 光盘片成本上升">需求增加原材料涨价 光盘片成本上升</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813204bef6013213394cc003e5.jsp" class="cmsHref_self" title="卫浴五金暴利时代终结市场遭遇三大拦路虎">卫浴五金暴利时代终结市场遭遇三大拦路虎</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813204bef601321338d54f03e4.jsp" class="cmsHref_self" title="建筑外墙外保温材料业明确行业准入条件">建筑外墙外保温材料业明确行业准入条件</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813204bef6013213377a6d03e3.jsp" class="cmsHref_self" title="屋顶光伏发电“十二五”拟增九倍">屋顶光伏发电“十二五”拟增九倍</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813204bef601321336ccae03e2.jsp" class="cmsHref_self" title="甲醇产能全国居首　专家看好河南煤化工业">甲醇产能全国居首　专家看好河南煤化工业</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813204bef6013213364ddc03e1.jsp" class="cmsHref_self" title="钛白粉价格高烧难退 下游企业直呼“伤不起”">钛白粉价格高烧难退 下游企业直呼“伤不起”</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813204bef601321322fb9a03d3.jsp" class="cmsHref_self" title="背光市场不如预期 高亮度LED出货量下修">背光市场不如预期 高亮度LED出货量下修</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ff33bb013203cefe35015b.jsp" class="cmsHref_self" title="家用中央空调逐渐成时代发展的主流">家用中央空调逐渐成时代发展的主流</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ff33bb0132038d61700132.jsp" class="cmsHref_self" title="需求高歌猛进 天胶蠢蠢欲动">需求高歌猛进 天胶蠢蠢欲动</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ff33bb0132038a4dc50131.jsp" class="cmsHref_self" title="童装需求持续增长 爆发式增长时代或来临">童装需求持续增长 爆发式增长时代或来临</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ff33bb0132038955ce0130.jsp" class="cmsHref_self" title="中国市场第三季电视机出货需求增19%">中国市场第三季电视机出货需求增19%</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131fa2f340131fe84ea4a0150.jsp" class="cmsHref_self" title="原料上涨 竞争激烈地板企业竞相争市场">原料上涨 竞争激烈地板企业竞相争市场</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131fa2f340131fe6db6a70140.jsp" class="cmsHref_self" title="水泥价格下行企业停产护价 需求上升后市乐观">水泥价格下行企业停产护价 需求上升后市乐观</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131fa2f340131fe6d32c7013f.jsp" class="cmsHref_self" title="日本需求大增 7月我国铝合金出口增21.38%">日本需求大增 7月我国铝合金出口增21.38%</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131fa2f340131fe6c70ee013e.jsp" class="cmsHref_self" title="食品和金银珠宝等需求持续旺盛">食品和金银珠宝等需求持续旺盛</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131f586b50131f94dec310044.jsp" class="cmsHref_self" title="宏观环境偏空 塑料仍有一定下跌空间">宏观环境偏空 塑料仍有一定下跌空间</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131f586b50131f94acdaf0043.jsp" class="cmsHref_self" title="冬虫夏草价涨三成创历史新高 今年产量小需求大">冬虫夏草价涨三成创历史新高 今年产量小需求大</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131f586b50131f949cdb10042.jsp" class="cmsHref_self" title="秋季服装原料需求放量 人造棉纱弱市上涨">秋季服装原料需求放量 人造棉纱弱市上涨</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131f188870131f4166552000b.jsp" class="cmsHref_self" title="惠普18个月内退出个人电脑市场">惠普18个月内退出个人电脑市场</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/19 每页20条 </span>
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