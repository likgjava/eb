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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c6bdf220b0c.jsp" class="toContentMain" title="国家级LED项目落户天门 总投资8亿元">国家级LED项目落户天门 总投资8亿元</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c6aadb00b0a.jsp" class="toContentMain" title="中国LED专利申请世界第二 但仍缺乏核心技术">中国LED专利申请世界第二 但仍缺乏核心技术</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c6489150af7.jsp" class="toContentMain" title="湖南省计划5年投入32亿元养护高速公路">湖南省计划5年投入32亿元养护高速公路</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c5a33c90af3.jsp" class="toContentMain" title="我临沂经济开发区40个项目奠基投资达200亿">我临沂经济开发区40个项目奠基投资达200亿</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c5814a00af1.jsp" class="toContentMain" title="2011贵州将开建20个中型骨干水源工程项目">2011贵州将开建20个中型骨干水源工程项目</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c56b8d50aef.jsp" class="toContentMain" title="十二五长沙将投145.4亿用于新建改建公路">十二五长沙将投145.4亿用于新建改建公路</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c5337fd0aed.jsp" class="toContentMain" title="我国整体橱柜市场“空间”还有多少？">我国整体橱柜市场“空间”还有多少？</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c51baef0aeb.jsp" class="toContentMain" title="中海油计划在2013-2014年建设惠州乙烯裂解项目">中海油计划在2013-2014年建设惠州乙烯裂解项目</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c5088410ae9.jsp" class="toContentMain" title="大型水溶性聚氨酯项目投产">大型水溶性聚氨酯项目投产</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c4f0a6f0ae7.jsp" class="toContentMain" title="意大利企业在国内设30万吨高端钢管基地">意大利企业在国内设30万吨高端钢管基地</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6ba1435a0927.jsp" class="toContentMain" title="闸北区政府采购试行诚信证明制度">闸北区政府采购试行诚信证明制度</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b9f718e0925.jsp" class="toContentMain" title="借广交会平台 广东举行采购商与粤企交流会">借广交会平台 广东举行采购商与粤企交流会</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b9b6f78091b.jsp" class="toContentMain" title="天津开发区邀国际买家来采购 免费帮企业拓市场">天津开发区邀国际买家来采购 免费帮企业拓市场</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b98987d0912.jsp" class="toContentMain" title="4月18日豆粕市场预测及采购建议">4月18日豆粕市场预测及采购建议</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b96f7ea0910.jsp" class="toContentMain" title="2010年金蝶中间件采购量跃升第二位">2010年金蝶中间件采购量跃升第二位</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b90410a090c.jsp" class="toContentMain" title="RightIC.com上线 可实现元器件小批量现货采购">RightIC.com上线 可实现元器件小批量现货采购</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b8d4f24090a.jsp" class="toContentMain" title="淼鑫消防：交易会为消防采购提供便利">淼鑫消防：交易会为消防采购提供便利</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b8b42f50908.jsp" class="toContentMain" title="省广股份：采购方式转变有望提升盈利能力 增持评级">省广股份：采购方式转变有望提升盈利能力 增持评级</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b76352008ff.jsp" class="toContentMain" title="国内棉花价格继续下探 纺织厂采购谨慎">国内棉花价格继续下探 纺织厂采购谨慎</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b6e30f908ef.jsp" class="toContentMain" title="药品降价死现象普遍 业内建议廉价药品定点采购">药品降价死现象普遍 业内建议廉价药品定点采购</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_54.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_56.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：55/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
