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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c7a0876e36.jsp" class="cmsHref_self" title="无锡并单采购破解价格垄断">无锡并单采购破解价格垄断</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c6a71e6e34.jsp" class="cmsHref_self" title="内蒙古禁止餐饮单位采购和使用4家企业8种问题产品">内蒙古禁止餐饮单位采购和使用4家企业8种问题产品</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c186db6e0e.jsp" class="cmsHref_self" title="台湾考虑禁止采购华为核心网设备">台湾考虑禁止采购华为核心网设备</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7bfc35f6e0a.jsp" class="cmsHref_self" title="执行国IV标准 政府汽车采购已领跑">执行国IV标准 政府汽车采购已领跑</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f28e6e0e0a44.jsp" class="cmsHref_self" title="6月采购经理指数创28个月新低">6月采购经理指数创28个月新低</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130e32796e105f5.jsp" class="cmsHref_self" title="执行国IV标准政府汽车采购应领跑">执行国IV标准政府汽车采购应领跑</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130e3269ac205f3.jsp" class="cmsHref_self" title="中国欧盟商会欢迎自主创新政府采购新规">中国欧盟商会欢迎自主创新政府采购新规</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130e325ddf105f1.jsp" class="cmsHref_self" title="合成胶价已经高于天胶期价 生产商加大天胶采购量">合成胶价已经高于天胶期价 生产商加大天胶采购量</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130e32545d805ef.jsp" class="cmsHref_self" title="志高中央空调入选政府采购">志高中央空调入选政府采购</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130e324412d05ed.jsp" class="cmsHref_self" title="财政部叫停政府优先采购自主创新产品规定">财政部叫停政府优先采购自主创新产品规定</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130c14db40130d3a59a4f0934.jsp" class="cmsHref_self" title="红十字总会超标采购420多万">红十字总会超标采购420多万</a></td>
							<td style="text-align:right">2011-06-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130c14db40130cea112b60156.jsp" class="cmsHref_self" title="大陆彩电企业赴台购3000万块液晶面板">大陆彩电企业赴台购3000万块液晶面板</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130c14db40130ce7daea10016.jsp" class="cmsHref_self" title="运营商用户端争夺渐烈：电信600亿采购手机">运营商用户端争夺渐烈：电信600亿采购手机</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130c14db40130ce7c69c80010.jsp" class="cmsHref_self" title="上牌难阻碍车企采购 奥迪涉水汽车金融租赁">上牌难阻碍车企采购 奥迪涉水汽车金融租赁</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130c14db40130ce7ba6b7000a.jsp" class="cmsHref_self" title="交大泰阳陷入硅片采购纠纷">交大泰阳陷入硅片采购纠纷</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130c14db40130ce7ad1220008.jsp" class="cmsHref_self" title="采购减少高价库存压港 铁矿石价格进入下降通道">采购减少高价库存压港 铁矿石价格进入下降通道</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130bf34b60130bf4d04000094.jsp" class="cmsHref_self" title="食用油限价令或再延期 油脂企业开工不足">食用油限价令或再延期 油脂企业开工不足</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130bf34b60130bf4c63d70092.jsp" class="cmsHref_self" title="日本模具企业采购两种重要战略分析">日本模具企业采购两种重要战略分析</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130bc5ca70130bf1e0c67002e.jsp" class="cmsHref_self" title="平板架构之争 采购平板电脑应用细说">平板架构之争 采购平板电脑应用细说</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130bc5ca70130bf1c708d002c.jsp" class="cmsHref_self" title="近500款车进山西政府采购 自主轿车较少">近500款车进山西政府采购 自主轿车较少</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_6.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_8.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：7/18 每页20条 </span>
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