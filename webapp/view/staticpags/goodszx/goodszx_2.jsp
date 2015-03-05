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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131f188870131f411b86c000a.jsp" class="cmsHref_self" title="面板业竞争激烈市场需求疲软 或调整关税">面板业竞争激烈市场需求疲软 或调整关税</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef12a78f0040.jsp" class="cmsHref_self" title="化工市场陷入低迷 液氯、硝酸逆市上涨">化工市场陷入低迷 液氯、硝酸逆市上涨</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef0bd670003a.jsp" class="cmsHref_self" title="中国灯具出口企业转型在即 隐忧却接连不断">中国灯具出口企业转型在即 隐忧却接连不断</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef0b89670039.jsp" class="cmsHref_self" title="卫浴企业利润当先 如何实现财富最大化">卫浴企业利润当先 如何实现财富最大化</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef0a238b0038.jsp" class="cmsHref_self" title="白炽灯能否进入淘汰期存悬念">白炽灯能否进入淘汰期存悬念</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef0872d30037.jsp" class="cmsHref_self" title="LED国家标准为何千呼万唤始不出来？">LED国家标准为何千呼万唤始不出来？</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef07e6230036.jsp" class="cmsHref_self" title="卫浴五金利润缩水 座便器已开始走下坡路">卫浴五金利润缩水 座便器已开始走下坡路</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef077d670035.jsp" class="cmsHref_self" title="瓷片市场不景气 陶瓷业逆境中仍有希望">瓷片市场不景气 陶瓷业逆境中仍有希望</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131db79dc0131df97c835024c.jsp" class="cmsHref_self" title="楼市低迷 建筑建材商遭殃">楼市低迷 建筑建材商遭殃</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131db79dc0131df971730024b.jsp" class="cmsHref_self" title="我国电力机车电容器实现大批量出口美国">我国电力机车电容器实现大批量出口美国</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131db79dc0131df9014210243.jsp" class="cmsHref_self" title="传统日用玻璃制品满足多样需求">传统日用玻璃制品满足多样需求</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131db79dc0131df8c2aa40241.jsp" class="cmsHref_self" title="钛材需求或井喷">钛材需求或井喷</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131db79dc0131df8af1a10240.jsp" class="cmsHref_self" title="光伏行业需求放缓 晶澳太阳能净亏损3540万美元">光伏行业需求放缓 晶澳太阳能净亏损3540万美元</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131dabeec0131dafc8465002a.jsp" class="cmsHref_self" title="中央空调未来市场潜力大 增长趋势被看好">中央空调未来市场潜力大 增长趋势被看好</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131dabeec0131dafbb03a0029.jsp" class="cmsHref_self" title="电地暖面临阶梯电价 改进创新迫在眉睫">电地暖面临阶梯电价 改进创新迫在眉睫</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131dabeec0131daf7cc190026.jsp" class="cmsHref_self" title="煤质分析仪器行业 市场发展前景较好">煤质分析仪器行业 市场发展前景较好</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131dabeec0131daf16dca001b.jsp" class="cmsHref_self" title="太阳能光热产业：平板式逐渐赶超真空管">太阳能光热产业：平板式逐渐赶超真空管</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131dabeec0131daec14fc0019.jsp" class="cmsHref_self" title="空气能热泵或将迎来迅猛发展的朝阳期">空气能热泵或将迎来迅猛发展的朝阳期</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d7d3750131da76d16b001f.jsp" class="cmsHref_self" title="工程陆续开工 水泥需求旺季降至">工程陆续开工 水泥需求旺季降至</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d7d3750131da73ebde001e.jsp" class="cmsHref_self" title="水泥行业：八月上半月水泥需求仍在放缓">水泥行业：八月上半月水泥需求仍在放缓</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_3.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：2/19 每页20条 </span>
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