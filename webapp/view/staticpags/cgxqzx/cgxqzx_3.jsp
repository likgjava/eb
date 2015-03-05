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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131767356013182ca99ed00ea.jsp" class="cmsHref_self" title="中央空调市场需求平均增幅将超30%">中央空调市场需求平均增幅将超30%</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131767356013182c8b5c900e9.jsp" class="cmsHref_self" title="公务车采购新规出台　合资B级车“头痛”">公务车采购新规出台　合资B级车“头痛”</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131767356013182c69c2a00e8.jsp" class="cmsHref_self" title="山东实践基本药物集中采购 药品生产企业整合现端倪">山东实践基本药物集中采购 药品生产企业整合现端倪</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a0131735a2a2908b2.jsp" class="cmsHref_self" title="政府采购可吸纳可信计算产品">政府采购可吸纳可信计算产品</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01317359796e07d7.jsp" class="cmsHref_self" title="采购商160亿来华找买家">采购商160亿来华找买家</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01317358b09b07d6.jsp" class="cmsHref_self" title="药品招标采购难解行业困局">药品招标采购难解行业困局</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a013173572ed307d5.jsp" class="cmsHref_self" title="中央单位批量采购试点工作进入执行期">中央单位批量采购试点工作进入执行期</a></td>
							<td style="text-align:right">2011-07-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316e530c0c0703.jsp" class="cmsHref_self" title="我国太阳能发电成本不断下降">我国太阳能发电成本不断下降</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316e2f26c406e5.jsp" class="cmsHref_self" title="江西给酒类产品套"紧箍咒" 严禁采购销售假酒">江西给酒类产品套"紧箍咒" 严禁采购销售假酒</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316e2d41d706e4.jsp" class="cmsHref_self" title="棉花跌破收储价 市场采购低迷">棉花跌破收储价 市场采购低迷</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316e2b127706e2.jsp" class="cmsHref_self" title="山东滕州市上半年政府采购额实现1.36亿元">山东滕州市上半年政府采购额实现1.36亿元</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316909caee04b7.jsp" class="cmsHref_self" title="好想你：原料枣采购价大幅上升 增持">好想你：原料枣采购价大幅上升 增持</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316908545304b5.jsp" class="cmsHref_self" title="休闲食品采购需关注产品质量">休闲食品采购需关注产品质量</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a0131690774ac04b4.jsp" class="cmsHref_self" title="上海青浦区三项措施推进政府采购制度改革">上海青浦区三项措施推进政府采购制度改革</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316905c13a04b3.jsp" class="cmsHref_self" title="阳光易购---节约采购成本的开始">阳光易购---节约采购成本的开始</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a013163dfa1eb02b9.jsp" class="cmsHref_self" title="LED显示屏采购的常见误区解析">LED显示屏采购的常见误区解析</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a013163dece5c02b8.jsp" class="cmsHref_self" title="山东基本药物采购,遭遇"磨合期"的阵痛">山东基本药物采购,遭遇"磨合期"的阵痛</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a013163ddc9de02b7.jsp" class="cmsHref_self" title="发改委责令药商抛售党参 药企采购热情不一">发改委责令药商抛售党参 药企采购热情不一</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f07fc510011.jsp" class="cmsHref_self" title="达芬奇巧舌如簧 怕顾客混淆标注“全球采购”">达芬奇巧舌如簧 怕顾客混淆标注“全球采购”</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f07623b0010.jsp" class="cmsHref_self" title="7月25日豆粕市场预测及采购建议">7月25日豆粕市场预测及采购建议</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_2.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_4.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：3/18 每页20条 </span>
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