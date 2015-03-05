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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bbd23c112e7.jsp" class="cmsHref_self" title="三星LED在韩反诉西门子侵犯LED专利">三星LED在韩反诉西门子侵犯LED专利</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bb4a103125a.jsp" class="cmsHref_self" title="青海盐湖与中川矿业合作开发加拿大钾矿">青海盐湖与中川矿业合作开发加拿大钾矿</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bb352411258.jsp" class="cmsHref_self" title="飞利浦将斥23亿-25亿元人民币收购奔腾电器">飞利浦将斥23亿-25亿元人民币收购奔腾电器</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bb2418d1250.jsp" class="cmsHref_self" title="奢侈品牌普拉达今起在港招股 拟融资26亿美元">奢侈品牌普拉达今起在港招股 拟融资26亿美元</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bb195f7124e.jsp" class="cmsHref_self" title="涉塑化剂企业杭州溢香源已停止生产销售">涉塑化剂企业杭州溢香源已停止生产销售</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bac981f1235.jsp" class="cmsHref_self" title="国家能源局：大面积煤炭供应紧张不会出现">国家能源局：大面积煤炭供应紧张不会出现</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bab17451229.jsp" class="cmsHref_self" title="日本芯片巨头将提早恢复震前供应">日本芯片巨头将提早恢复震前供应</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308693c6ca042f.jsp" class="cmsHref_self" title="AMD计划年底淘汰六核羿龙CPU 让位FX系列">AMD计划年底淘汰六核羿龙CPU 让位FX系列</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a9560130869381ed042d.jsp" class="cmsHref_self" title="IBM研发石墨烯高速电路板：取代CMOS晶体管？">IBM研发石墨烯高速电路板：取代CMOS晶体管？</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a9560130869245fe041c.jsp" class="cmsHref_self" title="宝钢率先下调 7月钢价每吨最高降500元">宝钢率先下调 7月钢价每吨最高降500元</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a9560130868fec8f03f0.jsp" class="cmsHref_self" title="苹果微软英特尔中兴等与Google争夺北电专利">苹果微软英特尔中兴等与Google争夺北电专利</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a9560130868f08d703ed.jsp" class="cmsHref_self" title="上海梅林接连卖地套现 或成光明食品整体上市平台">上海梅林接连卖地套现 或成光明食品整体上市平台</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308680deb90382.jsp" class="cmsHref_self" title="锦湖轮胎召回风波未消 长春工厂劳资纠纷再陷停工">锦湖轮胎召回风波未消 长春工厂劳资纠纷再陷停工</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308672ad280359.jsp" class="cmsHref_self" title="杭州溢香源停止生产销售 所含塑化剂系原料采购">杭州溢香源停止生产销售 所含塑化剂系原料采购</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307728fab04312.jsp" class="cmsHref_self" title="日本311大地震促使三菱电机半导体供应链走向多样化">日本311大地震促使三菱电机半导体供应链走向多样化</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307723060542ed.jsp" class="cmsHref_self" title="40大矿业巨头去年利润过千亿">40大矿业巨头去年利润过千亿</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130771e5b5942c3.jsp" class="cmsHref_self" title="“邯钢造”打造世界级液压支架">“邯钢造”打造世界级液压支架</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130771d7ce342bc.jsp" class="cmsHref_self" title="TCL两月内三度澄清重大投资">TCL两月内三度澄清重大投资</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130771c223442ba.jsp" class="cmsHref_self" title="华润雪花掷3亿巩固辽宁大本营">华润雪花掷3亿巩固辽宁大本营</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130771baa9442b3.jsp" class="cmsHref_self" title="国际四大粮商争购澳企 中粮海外布局遇双重困局">国际四大粮商争购澳企 中粮海外布局遇双重困局</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_22.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_24.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：23/29 每页20条 </span>
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