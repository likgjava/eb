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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d5665440221.jsp" class="cmsHref_self" title="电子代工企业遭遇污染通病">电子代工企业遭遇污染通病</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d557c540220.jsp" class="cmsHref_self" title="7月份化肥产业经济运行平稳">7月份化肥产业经济运行平稳</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d53c51c021f.jsp" class="cmsHref_self" title="中船重工跻身世界500强 位列全球船舶企业前三甲">中船重工跻身世界500强 位列全球船舶企业前三甲</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d52df89021e.jsp" class="cmsHref_self" title="惠普清库存遭遇尴尬抢购潮">惠普清库存遭遇尴尬抢购潮</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d50ea860202.jsp" class="cmsHref_self" title="五粮液上调出厂价 称遵循市场规律进行价格调整">五粮液上调出厂价 称遵循市场规律进行价格调整</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d4fe8db0201.jsp" class="cmsHref_self" title="比亚迪销售公司大幅裁员 员工微博爆料发泄">比亚迪销售公司大幅裁员 员工微博爆料发泄</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c0132187191b30039.jsp" class="cmsHref_self" title="阀门企业走专业化发展道路 市场趋于细分">阀门企业走专业化发展道路 市场趋于细分</a></td>
							<td style="text-align:right">2011-08-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321870e2420038.jsp" class="cmsHref_self" title="LED照明产业未来发展的康庄大道">LED照明产业未来发展的康庄大道</a></td>
							<td style="text-align:right">2011-08-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c0132186f85e10036.jsp" class="cmsHref_self" title="美的为新型环保制冷剂更替做足准备">美的为新型环保制冷剂更替做足准备</a></td>
							<td style="text-align:right">2011-08-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c0132185db0e50026.jsp" class="cmsHref_self" title="车用机油市场升温 长城润滑油全面出击">车用机油市场升温 长城润滑油全面出击</a></td>
							<td style="text-align:right">2011-08-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813204bef6013213342a8703db.jsp" class="cmsHref_self" title="三大动力助推工程机械产业发展提速">三大动力助推工程机械产业发展提速</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813204bef601321333544f03da.jsp" class="cmsHref_self" title="三星面板倒货 终端厂酝酿杀价抢市">三星面板倒货 终端厂酝酿杀价抢市</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813204bef60132130536fa03c4.jsp" class="cmsHref_self" title="三元或成太子奶操盘手 曾整合三鹿未见明显成效">三元或成太子奶操盘手 曾整合三鹿未见明显成效</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813204bef601321300ea9c03c3.jsp" class="cmsHref_self" title="东芝将向福特供应混合动力车用逆变器">东芝将向福特供应混合动力车用逆变器</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ff33bb013203afe4210149.jsp" class="cmsHref_self" title="卫浴五金暴利时代终结 市场遭遇三大拦路虎">卫浴五金暴利时代终结 市场遭遇三大拦路虎</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ff33bb013203ae46810148.jsp" class="cmsHref_self" title="沿海家具企业要西进 竞争中也蕴含着机遇">沿海家具企业要西进 竞争中也蕴含着机遇</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ff33bb013203acf9d80147.jsp" class="cmsHref_self" title="格力空调2011冷年创新高 全川销售突破40亿">格力空调2011冷年创新高 全川销售突破40亿</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ff33bb013203969c630133.jsp" class="cmsHref_self" title="谷歌收购摩托罗拉须过中国反垄断关 尚未提申请">谷歌收购摩托罗拉须过中国反垄断关 尚未提申请</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131fa2f340131fe88dd700151.jsp" class="cmsHref_self" title="空气能热水机市场兴起 美的执掌行业牛耳">空气能热水机市场兴起 美的执掌行业牛耳</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f96d4b610073.jsp" class="cmsHref_self" title="国内市场需求加大 多晶硅价格将回升">国内市场需求加大 多晶硅价格将回升</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_3.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：2/29 每页20条 </span>
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