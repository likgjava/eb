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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ee7925000d8.jsp" class="toContentMain" title="湖南省政协委员建议公开政府采购中标价格">湖南省政协委员建议公开政府采购中标价格</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ee5396800d6.jsp" class="toContentMain" title="国采中心与厂商达成一致 延长正版办公软件优惠期">国采中心与厂商达成一致 延长正版办公软件优惠期</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ee3b98f00d4.jsp" class="toContentMain" title="华电集团招标与采购网倾心打造“阳光采购”平台">华电集团招标与采购网倾心打造“阳光采购”平台</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7edc6a2100c4.jsp" class="toContentMain" title="德阳市政府集中采购步入快车道">德阳市政府集中采购步入快车道</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7eda618100c2.jsp" class="toContentMain" title="2011决战木门销售靠什么 三大体系缺一不可">2011决战木门销售靠什么 三大体系缺一不可</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ed7227400c0.jsp" class="toContentMain" title="木门企业生意有困难 纷纷表示压力颇大">木门企业生意有困难 纷纷表示压力颇大</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ed3cbd100b5.jsp" class="toContentMain" title="中国软床行业营销滞后模仿严重十之八九打">中国软床行业营销滞后模仿严重十之八九打</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ed09fda00b3.jsp" class="toContentMain" title="美大集成环保灶 获得中国品牌与传播大会两项大奖">美大集成环保灶 获得中国品牌与传播大会两项大奖</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ecc17a300b1.jsp" class="toContentMain" title="祺盛集成环保灶为什么被称为“性价比之王”？">祺盛集成环保灶为什么被称为“性价比之王”？</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e7a0600012e7ec9ec2e00af.jsp" class="toContentMain" title="优力得钢琴式集成环保灶与传统油烟机区别">优力得钢琴式集成环保灶与传统油烟机区别</a></td>
          <td style="text-align:right">2011-03-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e79c95eda0023.jsp" class="toContentMain" title="海口：梅河口市2010年政府采购节约资金2408万元">海口：梅河口市2010年政府采购节约资金2408万元</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e79bb71c40021.jsp" class="toContentMain" title="众买家将携采购清单参加广州消防产品交易会">众买家将携采购清单参加广州消防产品交易会</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e799f2e590011.jsp" class="toContentMain" title="数字时代印刷企业赢得订单的关键">数字时代印刷企业赢得订单的关键</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e799a5184000f.jsp" class="toContentMain" title="足浴器受经销商热宠 业内人士谨慎看好">足浴器受经销商热宠 业内人士谨慎看好</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e7997645e000d.jsp" class="toContentMain" title="辽宁胜华电缆有限公司的一款电缆质量不合格">辽宁胜华电缆有限公司的一款电缆质量不合格</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e7995415c000b.jsp" class="toContentMain" title="辽宁胜华电缆有限公司的一款电缆质量不合格">辽宁胜华电缆有限公司的一款电缆质量不合格</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e7992d4c00009.jsp" class="toContentMain" title="上上电缆集团喜获“2010年度北京电力物资采购优秀供应商”">上上电缆集团喜获“2010年度北京电力物资采购优秀供应商”</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e798e6b2f0007.jsp" class="toContentMain" title="基本药物网购将运行 医药电子商务将迎产业拐点">基本药物网购将运行 医药电子商务将迎产业拐点</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e798b551b0005.jsp" class="toContentMain" title="供应商可通过互联网参与长春市政府采购">供应商可通过互联网参与长春市政府采购</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e797ca1012e79890edd0003.jsp" class="toContentMain" title="河北省集采规模超过七成">河北省集采规模超过七成</a></td>
          <td style="text-align:right">2011-03-03</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_76.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_78.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：77/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
