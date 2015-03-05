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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116a8da3a1095.jsp" class="cmsHref_self" title="徐福记：不会将公司全部卖给雀巢">徐福记：不会将公司全部卖给雀巢</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116a693ce1091.jsp" class="cmsHref_self" title="雀巢洽谈徐福记 外资渗透国人"一日三餐"令人忧">雀巢洽谈徐福记 外资渗透国人"一日三餐"令人忧</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116a5bb50108f.jsp" class="cmsHref_self" title="重庆轻纺集团收购德国汽配大佬 业务横跨欧亚美">重庆轻纺集团收购德国汽配大佬 业务横跨欧亚美</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131169de5fc1068.jsp" class="cmsHref_self" title="丰田零部件供应逐步恢复正常">丰田零部件供应逐步恢复正常</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013107678bd00c12.jsp" class="cmsHref_self" title="中国将成太阳能电池板主导生产国">中国将成太阳能电池板主导生产国</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131076655cf0c10.jsp" class="cmsHref_self" title="松下三菱3M等进口净水器被揭“不净”">松下三菱3M等进口净水器被揭“不净”</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131075f454b0c02.jsp" class="cmsHref_self" title="方正携手隽大机械亮相2011国际彩盒展">方正携手隽大机械亮相2011国际彩盒展</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131075583f30bee.jsp" class="cmsHref_self" title="中药材涨价药你命 7家药企联手进军上游">中药材涨价药你命 7家药企联手进军上游</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013107543c550be8.jsp" class="cmsHref_self" title="雀巢三月内两起并购 分析称对民族品牌威胁大">雀巢三月内两起并购 分析称对民族品牌威胁大</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131074f88600be4.jsp" class="cmsHref_self" title="塑化剂风波中葛兰素史克是否说谎">塑化剂风波中葛兰素史克是否说谎</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131074de1380bde.jsp" class="cmsHref_self" title="独揽政策收储特权 中储粮暂按兵不动">独揽政策收储特权 中储粮暂按兵不动</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131074bc3bf0bdc.jsp" class="cmsHref_self" title="比亚迪汽车金融合资被批见光死">比亚迪汽车金融合资被批见光死</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131074b0fed0bda.jsp" class="cmsHref_self" title="奥的斯电梯近年事故频发 强调产品特殊不召回">奥的斯电梯近年事故频发 强调产品特殊不召回</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131073632f30bb0.jsp" class="cmsHref_self" title="LG下调今年手机销售目标 普通功能手机需求减少">LG下调今年手机销售目标 普通功能手机需求减少</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a701310234dce4075a.jsp" class="cmsHref_self" title="雅培惠氏等洋奶粉涨价被指趁火打劫">雅培惠氏等洋奶粉涨价被指趁火打劫</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131022e152d074c.jsp" class="cmsHref_self" title="LG：制造出全球最先进冰箱压缩机">LG：制造出全球最先进冰箱压缩机</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131021959f3071e.jsp" class="cmsHref_self" title="中国铝业再曝海外投资失误 澳铝土开发项目告吹">中国铝业再曝海外投资失误 澳铝土开发项目告吹</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a701310218ec81071c.jsp" class="cmsHref_self" title="鞍钢澳铁矿项目成本再上调 较最初累计上浮55%">鞍钢澳铁矿项目成本再上调 较最初累计上浮55%</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a701310216e9330718.jsp" class="cmsHref_self" title="沃尔玛与宜家在中国开始大量买地">沃尔玛与宜家在中国开始大量买地</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131020b2d5a0706.jsp" class="cmsHref_self" title="海口6日开售限价猪肉 供应商随市场增加投放">海口6日开售限价猪肉 供应商随市场增加投放</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_15.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_17.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：16/29 每页20条 </span>
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