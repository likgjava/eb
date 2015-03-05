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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f067e40000f.jsp" class="cmsHref_self" title="上半年政府采购规模83亿创新高 自主创新产品获政府首购">上半年政府采购规模83亿创新高 自主创新产品获政府首购</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f05d7c6000e.jsp" class="cmsHref_self" title="传苹果欲向LG采购55英寸OLED面板">传苹果欲向LG采购55英寸OLED面板</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f052cfc000d.jsp" class="cmsHref_self" title="PlayBook平板获美国政府采购产品认证">PlayBook平板获美国政府采购产品认证</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f0431cb000c.jsp" class="cmsHref_self" title="采购电感元件的常识">采购电感元件的常识</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314f6ece0b12b5.jsp" class="cmsHref_self" title="洋货神话不在 政府采购应扶持民族品牌">洋货神话不在 政府采购应扶持民族品牌</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314f6e166f12b4.jsp" class="cmsHref_self" title="东北药材市场最新动态">东北药材市场最新动态</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314f5740521218.jsp" class="cmsHref_self" title="日本旭洋造船欲向韩国中国采购设备">日本旭洋造船欲向韩国中国采购设备</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314a21da6f0ae9.jsp" class="cmsHref_self" title="基本准则要理顺 商务投影机采购的经验">基本准则要理顺 商务投影机采购的经验</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314a1dfe260ae5.jsp" class="cmsHref_self" title="雨润农产品全球采购中心开建">雨润农产品全球采购中心开建</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314a1d6c9c0ae4.jsp" class="cmsHref_self" title="国际核聚变实验堆将在中国采购电源">国际核聚变实验堆将在中国采购电源</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc470131450101930325.jsp" class="cmsHref_self" title="志高中央空调跃升为政府采购新选择">志高中央空调跃升为政府采购新选择</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc47013144ffb7d20322.jsp" class="cmsHref_self" title="瑞风和畅进军政府采购市场 建立MPV新基准">瑞风和畅进军政府采购市场 建立MPV新基准</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc47013144fef7c40320.jsp" class="cmsHref_self" title="华为与富士康签署20亿美元采购协议">华为与富士康签署20亿美元采购协议</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc47013144fde3e7031d.jsp" class="cmsHref_self" title="大陆企业在台达成3项采购合约价值24亿美元">大陆企业在台达成3项采购合约价值24亿美元</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fdc1e1d0212.jsp" class="cmsHref_self" title="运营商不愿只"吃"苹果 今年重点采购3G手机">运营商不愿只"吃"苹果 今年重点采购3G手机</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fdaf6730210.jsp" class="cmsHref_self" title="上半年厦门政府采购少花2.93亿">上半年厦门政府采购少花2.93亿</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fd9ea76020e.jsp" class="cmsHref_self" title="公车管理办法加速公务用车采购自主品牌">公车管理办法加速公务用车采购自主品牌</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fd8ea83020c.jsp" class="cmsHref_self" title="威海高新区建立政府采购投标供应商准入机制">威海高新区建立政府采购投标供应商准入机制</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fd7e0c7020b.jsp" class="cmsHref_self" title="夏普联手鸿海降低面板采购成本">夏普联手鸿海降低面板采购成本</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fd73c630208.jsp" class="cmsHref_self" title="医院院长把药械采购当“摇钱树”">医院院长把药械采购当“摇钱树”</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_3.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_5.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：4/18 每页20条 </span>
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