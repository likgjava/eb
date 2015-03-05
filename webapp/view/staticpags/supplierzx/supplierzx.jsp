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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227b339620033.jsp" class="cmsHref_self" title="惠普连续四个季度跌出中国PC市场三甲">惠普连续四个季度跌出中国PC市场三甲</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227b1b9a30031.jsp" class="cmsHref_self" title="三星显示器部门高层裁员10％ 结构改组">三星显示器部门高层裁员10％ 结构改组</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227afddee002f.jsp" class="cmsHref_self" title="中小企业资金吃紧 水泥龙头或加快并购">中小企业资金吃紧 水泥龙头或加快并购</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227accd39002d.jsp" class="cmsHref_self" title="太子奶重整平均清偿率9.57% 高科退出舞台">太子奶重整平均清偿率9.57% 高科退出舞台</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227a286810026.jsp" class="cmsHref_self" title="太子奶破产重整计划出炉 20亿债权清偿率仅9.57%">太子奶破产重整计划出炉 20亿债权清偿率仅9.57%</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227a173060025.jsp" class="cmsHref_self" title="诺基亚中国单方面裁员170人被指违反劳动法">诺基亚中国单方面裁员170人被指违反劳动法</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227a0e6f30024.jsp" class="cmsHref_self" title="路线或涉禁区 华润雪花勇闯可可西里遭质疑">路线或涉禁区 华润雪花勇闯可可西里遭质疑</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f0132279e1e890021.jsp" class="cmsHref_self" title="五粮液与茅台争头把交椅限货提价">五粮液与茅台争头把交椅限货提价</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081321f7bae013222854a010057.jsp" class="cmsHref_self" title="五粮液大幅提价或引发涨价潮">五粮液大幅提价或引发涨价潮</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081321f7bae0132227a4d110052.jsp" class="cmsHref_self" title="苹果在华供应商集体再陷“污染门”">苹果在华供应商集体再陷“污染门”</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d631fee0233.jsp" class="cmsHref_self" title="水性耐热高温涂料再刮世界化工风暴">水性耐热高温涂料再刮世界化工风暴</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d61b8d00232.jsp" class="cmsHref_self" title="“十二五”可再生能源发展目标明确">“十二五”可再生能源发展目标明确</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d600d580231.jsp" class="cmsHref_self" title="发改委：2011年7月份轮胎行业增速加快">发改委：2011年7月份轮胎行业增速加快</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5f8b950230.jsp" class="cmsHref_self" title="原材料涨势不停 2011地板价格走势几何">原材料涨势不停 2011地板价格走势几何</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5ed4c5022f.jsp" class="cmsHref_self" title="2011年我国太阳能产业在政策护航下前行">2011年我国太阳能产业在政策护航下前行</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5e6785022e.jsp" class="cmsHref_self" title="国内棉花价格下跌趋势难止">国内棉花价格下跌趋势难止</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5dd67a022d.jsp" class="cmsHref_self" title="药品遭遇降价死 降价药鲜有药店销售">药品遭遇降价死 降价药鲜有药店销售</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5d0605022c.jsp" class="cmsHref_self" title="空调2012年销售量增长速度或放缓至10%">空调2012年销售量增长速度或放缓至10%</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5c41f0022b.jsp" class="cmsHref_self" title="是什么掐住了中小卫浴企业发展的脉搏">是什么掐住了中小卫浴企业发展的脉搏</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5b1bde022a.jsp" class="cmsHref_self" title="伊利上半年乳企业绩第一 光明乳业倒数">伊利上半年乳企业绩第一 光明乳业倒数</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/29 每页20条 </span>
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