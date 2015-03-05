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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa8bfba900fd.jsp" class="cmsHref_self" title="当当与天梭表品牌授权之争升级 天梭发封杀声明">当当与天梭表品牌授权之争升级 天梭发封杀声明</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa7b130e0017.jsp" class="cmsHref_self" title="一季度西班牙最大服装企业INDITEX净盈利3.32亿欧元">一季度西班牙最大服装企业INDITEX净盈利3.32亿欧元</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309cd86d0130aa7a47d20015.jsp" class="cmsHref_self" title="青岛海尔探路整体上市 18.8亿购集团10公司股权">青岛海尔探路整体上市 18.8亿购集团10公司股权</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b6de0110609.jsp" class="cmsHref_self" title="国信招标集团首次应用电子化平台公开招标">国信招标集团首次应用电子化平台公开招标</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b6bf59a05ff.jsp" class="cmsHref_self" title="投标商应用电子化招标平台投标反馈良好">投标商应用电子化招标平台投标反馈良好</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b336d6d041a.jsp" class="cmsHref_self" title="大众发布新能源计划 混动将是研发重点">大众发布新能源计划 混动将是研发重点</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b323e120418.jsp" class="cmsHref_self" title="中粮以商标侵权起诉福宜康生产销售商">中粮以商标侵权起诉福宜康生产销售商</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b2956840399.jsp" class="cmsHref_self" title="部分地区放弃铅酸蓄电池产业 多家上市公司停产">部分地区放弃铅酸蓄电池产业 多家上市公司停产</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b2769e30397.jsp" class="cmsHref_self" title="中石化投资超千亿建国内最大炼化项目">中石化投资超千亿建国内最大炼化项目</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b229f270391.jsp" class="cmsHref_self" title="三大钢厂集体降价 分析称需求减弱是主因">三大钢厂集体降价 分析称需求减弱是主因</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b1fade2038d.jsp" class="cmsHref_self" title="德汽车空调制造商获大宇汽车供应协议">德汽车空调制造商获大宇汽车供应协议</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b11feb70371.jsp" class="cmsHref_self" title="并购小肥羊成行在望 百胜将成餐饮业"巨无霸"">并购小肥羊成行在望 百胜将成餐饮业"巨无霸"</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b111dd7036f.jsp" class="cmsHref_self" title="集团启动注资计划 青岛海尔18.8亿打包收购10资产">集团启动注资计划 青岛海尔18.8亿打包收购10资产</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b0e374e0369.jsp" class="cmsHref_self" title="三大钢企下调7月钢价 钢市再蒙阴云">三大钢企下调7月钢价 钢市再蒙阴云</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813090cbdd013091034775010c.jsp" class="cmsHref_self" title="必和必拓澳洲遭遇罢工 焦煤价格短期或上扬">必和必拓澳洲遭遇罢工 焦煤价格短期或上扬</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813090cbdd013090febe17010a.jsp" class="cmsHref_self" title="华润雪花3亿控股墨尼啤酒 欲一统辽宁市场">华润雪花3亿控股墨尼啤酒 欲一统辽宁市场</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813090cbdd013090fe29b20108.jsp" class="cmsHref_self" title="萨博濒临破产身价攀升 中国车企竞购或买单">萨博濒临破产身价攀升 中国车企竞购或买单</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813090cbdd013090fd665d0106.jsp" class="cmsHref_self" title="陶氏化学一季度大中华区销售额达10.4亿美元">陶氏化学一季度大中华区销售额达10.4亿美元</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bc00aba12fd.jsp" class="cmsHref_self" title="东芝大举并购 核电危机下的转型之举">东芝大举并购 核电危机下的转型之举</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bbf6a5912fb.jsp" class="cmsHref_self" title="三星本季度或超诺基亚成最大智能手机厂商">三星本季度或超诺基亚成最大智能手机厂商</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_21.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_23.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：22/29 每页20条 </span>
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