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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081303fbeb7013043880af60381.jsp" class="cmsHref_self" title="供应商采购管理策略升级">供应商采购管理策略升级</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081303fbeb7013043874d30037f.jsp" class="cmsHref_self" title="国税系统如何防控政府采购风险">国税系统如何防控政府采购风险</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081303fbeb7013043868f63037d.jsp" class="cmsHref_self" title="政府采购管理改革要实现四个转变">政府采购管理改革要实现四个转变</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081303fbeb701304385c623037b.jsp" class="cmsHref_self" title="采购软件资产要依据年度资产配置计划">采购软件资产要依据年度资产配置计划</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130361e0401303e6783c70027.jsp" class="cmsHref_self" title="日本地震后开始批量采购大连鲍鱼">日本地震后开始批量采购大连鲍鱼</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130361e0401303e65f4d40023.jsp" class="cmsHref_self" title="济南政府采购将逐步强制采用节能产品">济南政府采购将逐步强制采用节能产品</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130361e0401303e65019f0021.jsp" class="cmsHref_self" title="公车采购新提法:18万内、1.8L排量双重限制">公车采购新提法:18万内、1.8L排量双重限制</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130361e0401303e63e84f001f.jsp" class="cmsHref_self" title="采购增加原油上涨 马盘棕榈油连续三周走高">采购增加原油上涨 马盘棕榈油连续三周走高</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029cc349e0025.jsp" class="cmsHref_self" title="欧盟反倾销终结：制鞋产业受惠明显 鞋企采购需求强劲">欧盟反倾销终结：制鞋产业受惠明显 鞋企采购需求强劲</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029c28c790017.jsp" class="cmsHref_self" title="政府如何采购民间应急救援服务">政府如何采购民间应急救援服务</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029c170120015.jsp" class="cmsHref_self" title="深采购订单指数优于省平均水平">深采购订单指数优于省平均水平</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029bf3d930013.jsp" class="cmsHref_self" title="公务车采购新标准出台 售价需在18万元以内">公务车采购新标准出台 售价需在18万元以内</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029be65bb0011.jsp" class="cmsHref_self" title="中储粮确认从美国采购100万吨黄玉米">中储粮确认从美国采购100万吨黄玉米</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029bd4040000f.jsp" class="cmsHref_self" title="青海通过政府采购机制引导农机购买方向">青海通过政府采购机制引导农机购买方向</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813021070101302499309a0088.jsp" class="cmsHref_self" title="内蒙古服装采购引入盲评">内蒙古服装采购引入盲评</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081302107010130249754640084.jsp" class="cmsHref_self" title="德国制造业采购经理人指数降至7个月低点">德国制造业采购经理人指数降至7个月低点</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081302107010130249650c00082.jsp" class="cmsHref_self" title="蜀中事件共识：基本药物招标采购制度必须改革">蜀中事件共识：基本药物招标采购制度必须改革</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130210701013024948c90007e.jsp" class="cmsHref_self" title="采购经理指数向好 黑龙江制造业经济呈增长扩张态势">采购经理指数向好 黑龙江制造业经济呈增长扩张态势</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813008ca4701301f9e961e0613.jsp" class="cmsHref_self" title="20家企业赴山东直接采购大蒜">20家企业赴山东直接采购大蒜</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813008ca4701301f9ba5d4060f.jsp" class="cmsHref_self" title="石家庄严禁医疗机构违法采购中药饮片">石家庄严禁医疗机构违法采购中药饮片</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_11.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_13.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：12/18 每页20条 </span>
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