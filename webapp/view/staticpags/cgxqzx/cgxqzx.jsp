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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813225280f013227992e660020.jsp" class="cmsHref_self" title="“十一五”中央单位政府采购改革成效显著">“十一五”中央单位政府采购改革成效显著</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813225280f01322798868f001f.jsp" class="cmsHref_self" title="中央单位政府采购改革要实现四个突破">中央单位政府采购改革要实现四个突破</a></td>
							<td style="text-align:right">2011-09-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081321f7bae0132227710d90051.jsp" class="cmsHref_self" title="政府采购存商机 机房空调带来新兴市场">政府采购存商机 机房空调带来新兴市场</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081321f7bae0132227679a70050.jsp" class="cmsHref_self" title="中国突然增加对铜的采购">中国突然增加对铜的采购</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081321f7bae0132227549db004d.jsp" class="cmsHref_self" title="政府采购探路“全国联动采购”">政府采购探路“全国联动采购”</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081321f7bae013222748ace004b.jsp" class="cmsHref_self" title="公务车采购解冻广汽量体造车">公务车采购解冻广汽量体造车</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813218571c01321d4cde0b0200.jsp" class="cmsHref_self" title="下半年采购旺季渐临近 各类轻纺原料小幅涨价">下半年采购旺季渐临近 各类轻纺原料小幅涨价</a></td>
							<td style="text-align:right">2011-08-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813204bef6013212fa1e42038a.jsp" class="cmsHref_self" title="北汽福田有意在印度向康明斯采购发动机">北汽福田有意在印度向康明斯采购发动机</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131ff33bb013203d54aa00198.jsp" class="cmsHref_self" title="陶瓷卫浴行业如何抢站市场">陶瓷卫浴行业如何抢站市场</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131ff33bb013203d2edac0178.jsp" class="cmsHref_self" title="造纸业政策投资加大自动化市场平稳增长">造纸业政策投资加大自动化市场平稳增长</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe6600eb013b.jsp" class="cmsHref_self" title="武汉光纤行业每年采购200万吨涂料">武汉光纤行业每年采购200万吨涂料</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe658f9a013a.jsp" class="cmsHref_self" title="河北上收基本药物采购议价权">河北上收基本药物采购议价权</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe644dc20139.jsp" class="cmsHref_self" title="苹果采购面板 国内彩电厂商集体推云概念电视">苹果采购面板 国内彩电厂商集体推云概念电视</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe637b920138.jsp" class="cmsHref_self" title="大力支持国产医疗器械十二五期间将获优先采购">大力支持国产医疗器械十二五期间将获优先采购</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe62a7930137.jsp" class="cmsHref_self" title="协议采购如何破除当前困境">协议采购如何破除当前困境</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe6205f30136.jsp" class="cmsHref_self" title="四川宜宾财政全面提升政府采购监督管理水平">四川宜宾财政全面提升政府采购监督管理水平</a></td>
							<td style="text-align:right">2011-08-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131f188870131f40f89920009.jsp" class="cmsHref_self" title="棉花价格表现平稳 纱厂适时采购">棉花价格表现平稳 纱厂适时采购</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131f188870131f40e56130008.jsp" class="cmsHref_self" title="支持自主品牌汽车发展 应由政府采购开始">支持自主品牌汽车发展 应由政府采购开始</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131db79dc0131df89487c023f.jsp" class="cmsHref_self" title="中国7月份采购了64.50万吨巴西糖">中国7月份采购了64.50万吨巴西糖</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131db79dc0131df882c17023e.jsp" class="cmsHref_self" title="西安市电子化政府采购工作全面启动">西安市电子化政府采购工作全面启动</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/18 每页20条 </span>
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