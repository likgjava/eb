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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da5849fa000d.jsp" class="cmsHref_self" title="太子奶保命战打响 余留29亿元债务待解">太子奶保命战打响 余留29亿元债务待解</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da56f197000c.jsp" class="cmsHref_self" title="新华联接盘太子奶 面临为土地还是为品牌疑问">新华联接盘太子奶 面临为土地还是为品牌疑问</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da55f0d9000b.jsp" class="cmsHref_self" title="HTC起诉苹果产品侵犯三项技术专利">HTC起诉苹果产品侵犯三项技术专利</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d55c6c470077.jsp" class="cmsHref_self" title="门窗企业提高经济效益 加强内部控制很重要">门窗企业提高经济效益 加强内部控制很重要</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d55b90ac0076.jsp" class="cmsHref_self" title="原材料涨价 海尔海信空调相约集体提价">原材料涨价 海尔海信空调相约集体提价</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d55799750063.jsp" class="cmsHref_self" title="白酒也疯狂 两瓶汾酒可买辆兰博基尼">白酒也疯狂 两瓶汾酒可买辆兰博基尼</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d54cafa7005c.jsp" class="cmsHref_self" title="欧洲葡萄酒巨头卡斯特要单飞 欲自建品牌">欧洲葡萄酒巨头卡斯特要单飞 欲自建品牌</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d539dce2004c.jsp" class="cmsHref_self" title="诺基亚回应渠道崩盘说 称库存已正常">诺基亚回应渠道崩盘说 称库存已正常</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d5397b65004b.jsp" class="cmsHref_self" title="雅培投资2.3亿美元中国建厂">雅培投资2.3亿美元中国建厂</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d539212d004a.jsp" class="cmsHref_self" title="光明称洽购澳食品商已进尾声">光明称洽购澳食品商已进尾声</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d538b1790049.jsp" class="cmsHref_self" title="壳牌石油深海钻探安全再遭质疑">壳牌石油深海钻探安全再遭质疑</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d534634f0046.jsp" class="cmsHref_self" title="本田投资8亿美元于墨西哥建厂">本田投资8亿美元于墨西哥建厂</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ccdcaa0131d0149cfe007c.jsp" class="cmsHref_self" title="飞鹤否认对赌失败被迫售牧场还债">飞鹤否认对赌失败被迫售牧场还债</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ccdcaa0131d01399940023.jsp" class="cmsHref_self" title="光明食品证实洽购澳食品公司 或成其最大海外收购">光明食品证实洽购澳食品公司 或成其最大海外收购</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ccdcaa0131d012cc2d0008.jsp" class="cmsHref_self" title="诺基亚否认遭遇渠道商集体抵制">诺基亚否认遭遇渠道商集体抵制</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131c418af0131cae85eb00017.jsp" class="cmsHref_self" title="大批代理商拒绝进货 诺基亚在中国市场渠道崩盘">大批代理商拒绝进货 诺基亚在中国市场渠道崩盘</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b1356c9f1c99.jsp" class="cmsHref_self" title="债务危机一度让苹果超越美孚 成全球最大公司">债务危机一度让苹果超越美孚 成全球最大公司</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b134eef01c98.jsp" class="cmsHref_self" title="张裕提价半年净赚近9亿">张裕提价半年净赚近9亿</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b13462391c97.jsp" class="cmsHref_self" title="哈药股份环保门新进展 违规被罚123万">哈药股份环保门新进展 违规被罚123万</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b133fe721c96.jsp" class="cmsHref_self" title="中国北车动车67亿大单暂缓交付">中国北车动车67亿大单暂缓交付</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_3.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_5.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：4/29 每页20条 </span>
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