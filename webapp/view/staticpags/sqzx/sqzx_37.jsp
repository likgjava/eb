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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b24a9a70395.jsp" class="toContentMain" title="香港葡萄酒进口飞速上涨 大陆市场需求高">香港葡萄酒进口飞速上涨 大陆市场需求高</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b2435280393.jsp" class="toContentMain" title="解析五金工具需求大增 企业如何抓住机遇？">解析五金工具需求大增 企业如何抓住机遇？</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b101b44036d.jsp" class="toContentMain" title="中钢协：钢市将呈波动运行态势">中钢协：钢市将呈波动运行态势</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b0f14ef036b.jsp" class="toContentMain" title="焦炭短期料将继续寻底">焦炭短期料将继续寻底</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd01309107c1210114.jsp" class="toContentMain" title="国内新能源汽车基本是政策热市场冷">国内新能源汽车基本是政策热市场冷</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd01309106df480112.jsp" class="toContentMain" title="环保政策推动钢企转型">环保政策推动钢企转型</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd0130910527950110.jsp" class="toContentMain" title="原料药价格近期涨幅惊人 生物素三周翻一倍">原料药价格近期涨幅惊人 生物素三周翻一倍</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd013090fb955c0104.jsp" class="toContentMain" title="机构称中国大宗商品需求正在放缓">机构称中国大宗商品需求正在放缓</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd013090fa30d10100.jsp" class="toContentMain" title="经济回暖涂料装修市场需求高">经济回暖涂料装修市场需求高</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd013090f7f81000fe.jsp" class="toContentMain" title="中国2015年天胶需求量将超480万吨">中国2015年天胶需求量将超480万吨</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd013090f6ca4d00fc.jsp" class="toContentMain" title="限量指标天量需求让稀土炒作成“疯”">限量指标天量需求让稀土炒作成“疯”</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd013090dc321d000d.jsp" class="toContentMain" title="西班牙烟草价格变动影响帝国烟草利润">西班牙烟草价格变动影响帝国烟草利润</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813090cbdd013090d899b60007.jsp" class="toContentMain" title="食品价格"贡献"达六成 成本推动加流动性过剩">食品价格"贡献"达六成 成本推动加流动性过剩</a></td>
          <td style="text-align:right">2011-06-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bc5bd151310.jsp" class="toContentMain" title="疯狂棉价或平稳 纺织业下半年仍负重前行">疯狂棉价或平稳 纺织业下半年仍负重前行</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bc489a5130e.jsp" class="toContentMain" title="浙江“十二五”造纸业从大省向强省转变">浙江“十二五”造纸业从大省向强省转变</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bb9700d12db.jsp" class="toContentMain" title="工程机械销量连续两月下滑 行业迎来景气拐点">工程机械销量连续两月下滑 行业迎来景气拐点</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bb86ac812d9.jsp" class="toContentMain" title="疯狂棉价或趋平稳 纺织业下半年仍负重前行">疯狂棉价或趋平稳 纺织业下半年仍负重前行</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bb7a31e12d7.jsp" class="toContentMain" title="我国对位芳纶成功量产打破国外约40年垄断">我国对位芳纶成功量产打破国外约40年垄断</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bb7007c12d5.jsp" class="toContentMain" title="中国品牌力指数首次发布 涵盖105个行业">中国品牌力指数首次发布 涵盖105个行业</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bb68a2e12d3.jsp" class="toContentMain" title="报告称中国消费者市场急胀 中外企业未做好应对">报告称中国消费者市场急胀 中外企业未做好应对</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_36.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_38.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：37/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
