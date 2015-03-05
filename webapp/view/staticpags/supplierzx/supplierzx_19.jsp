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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e3356ca40619.jsp" class="cmsHref_self" title="大众在华两家新工厂获批">大众在华两家新工厂获批</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e334d75b0617.jsp" class="cmsHref_self" title="戴姆勒20亿欧元注入北京奔驰">戴姆勒20亿欧元注入北京奔驰</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e32ce3de0603.jsp" class="cmsHref_self" title="印度Sonalika集团确认为通用供应发动机">印度Sonalika集团确认为通用供应发动机</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e32c4e590601.jsp" class="cmsHref_self" title="威伯科宣布为欧洲大型商用拖车商供应新一代制动器">威伯科宣布为欧洲大型商用拖车商供应新一代制动器</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e32b8d0605fd.jsp" class="cmsHref_self" title="索尼东芝日立或成最大小尺寸液晶面板供应商">索尼东芝日立或成最大小尺寸液晶面板供应商</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ceb8205f01e3.jsp" class="cmsHref_self" title="外媒评2012年将消失6大品牌：诺基亚索爱上榜">外媒评2012年将消失6大品牌：诺基亚索爱上榜</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130cea4a5f80160.jsp" class="cmsHref_self" title="青岛啤酒：60万千升新基地布局广东">青岛啤酒：60万千升新基地布局广东</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce9d68a40152.jsp" class="cmsHref_self" title="传AMD或将停止开发闪龙(Sempron)芯片">传AMD或将停止开发闪龙(Sempron)芯片</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce9cf5d80150.jsp" class="cmsHref_self" title="英特尔或2013年推Haswell芯片 超级本或诞生">英特尔或2013年推Haswell芯片 超级本或诞生</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce9a14aa014c.jsp" class="cmsHref_self" title="TCL携手台湾宏齐建设LED封装厂">TCL携手台湾宏齐建设LED封装厂</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce937eb10144.jsp" class="cmsHref_self" title="乳品新国标缩乳严重 被指迁就部分大型乳企">乳品新国标缩乳严重 被指迁就部分大型乳企</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce929f830142.jsp" class="cmsHref_self" title="锦湖轮胎质量门或引发中国轮胎业变局">锦湖轮胎质量门或引发中国轮胎业变局</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce8fdbf80136.jsp" class="cmsHref_self" title="东风日产切入产业链上游 控制零部件供应体系">东风日产切入产业链上游 控制零部件供应体系</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce8940490126.jsp" class="cmsHref_self" title="控制零部件供应体系东风日产切入产业链上游">控制零部件供应体系东风日产切入产业链上游</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf34b60130bf537ea000a2.jsp" class="cmsHref_self" title="酒鬼酒剥离房地产业务 深耕白酒主业">酒鬼酒剥离房地产业务 深耕白酒主业</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf34b60130bf516fd8009c.jsp" class="cmsHref_self" title="地震影响持续 丰田30种零部件供应短缺">地震影响持续 丰田30种零部件供应短缺</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf34b60130bf37350b0009.jsp" class="cmsHref_self" title="把控上游原料 霸王欲发力凉茶市场">把控上游原料 霸王欲发力凉茶市场</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf34b60130bf35d93b0003.jsp" class="cmsHref_self" title="国内生猪价格创新高 中粮联手日资百亿养猪">国内生猪价格创新高 中粮联手日资百亿养猪</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf287a0130bf3348e70011.jsp" class="cmsHref_self" title="华润雪花啤酒继续加速快跑">华润雪花啤酒继续加速快跑</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf287a0130bf32a01d000f.jsp" class="cmsHref_self" title="联想“喝下”江口醇迷雾重重">联想“喝下”江口醇迷雾重重</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_18.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_20.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：19/29 每页20条 </span>
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