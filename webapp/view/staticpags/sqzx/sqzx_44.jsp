<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript">
$(document).ready(function(){
$("#contentMain").addClass("sqindex3pa");
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
	$('#searchNewsContentMainBtn').click(function() {
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=sqzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/sqzx/sqzx.jsp" title="商圈资讯" class="cmsHref_self">商圈资讯</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商圈资讯展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda319fa70356" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff6c71d93032b.jsp" class="toContentMain" title="建材业掘金从“新”开始">建材业掘金从“新”开始</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff6bd047902ff.jsp" class="toContentMain" title="LED产业发展存瓶颈在家用市场亟待破局">LED产业发展存瓶颈在家用市场亟待破局</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff6baf50202fd.jsp" class="toContentMain" title="稀土市场囤货成风导致有价无市 专家建议推出稀土期货">稀土市场囤货成风导致有价无市 专家建议推出稀土期货</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff6b8823002fb.jsp" class="toContentMain" title="建筑钢走势疲软 铁矿石价格亦有“示弱”之意">建筑钢走势疲软 铁矿石价格亦有“示弱”之意</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff6b7627e02f9.jsp" class="toContentMain" title="欧盟对华铜版纸企业征收高额“双反”税">欧盟对华铜版纸企业征收高额“双反”税</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff675c025027d.jsp" class="toContentMain" title="2011年全球3D电视机出货量将增长5倍">2011年全球3D电视机出货量将增长5倍</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff66ea4a201f6.jsp" class="toContentMain" title="手机市场需求平平 手机芯片出货量平淡">手机市场需求平平 手机芯片出货量平淡</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff66cc80d01b4.jsp" class="toContentMain" title="“云计算”“云安全”与网络安全需求趋势">“云计算”“云安全”与网络安全需求趋势</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff64c546e00de.jsp" class="toContentMain" title="国家相关部门正积极预防大规模“油荒”">国家相关部门正积极预防大规模“油荒”</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff63e0da900c8.jsp" class="toContentMain" title="中国拟取消外资药品定价特权缩小与国产药价差">中国拟取消外资药品定价特权缩小与国产药价差</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff63b563d00c6.jsp" class="toContentMain" title="钢铁产业发展高层论坛探讨钢铁产业焦点议题">钢铁产业发展高层论坛探讨钢铁产业焦点议题</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c53a330127.jsp" class="toContentMain" title="每年13亿吨粮食被浪费占全球粮食产量的1/3">每年13亿吨粮食被浪费占全球粮食产量的1/3</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c4d10b0125.jsp" class="toContentMain" title="供大于求 铅价逼近前低">供大于求 铅价逼近前低</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c425740123.jsp" class="toContentMain" title="国家发改委拟推动开征环境税">国家发改委拟推动开征环境税</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c3780f0121.jsp" class="toContentMain" title="京沪高铁使用江西钢铁产品">京沪高铁使用江西钢铁产品</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c2aee9011f.jsp" class="toContentMain" title="全球采矿界巨头相约合肥">全球采矿界巨头相约合肥</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c212d2011d.jsp" class="toContentMain" title="电荒罪责不应归咎于煤炭业重组">电荒罪责不应归咎于煤炭业重组</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c18f4d011b.jsp" class="toContentMain" title="陕天然气今年计划投资总额49.7亿">陕天然气今年计划投资总额49.7亿</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe3e2c6012fe6c0d5e90119.jsp" class="toContentMain" title="下半年钢价回落可能性大 券商谨慎看待钢铁股后市">下半年钢价回落可能性大 券商谨慎看待钢铁股后市</a></td>
          <td style="text-align:right">2011-05-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fde6366012fe195a3b00011.jsp" class="toContentMain" title="2011年一季度重庆能源形势分析">2011年一季度重庆能源形势分析</a></td>
          <td style="text-align:right">2011-05-12</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_43.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_45.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：44/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
