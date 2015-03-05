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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e7478629a0162.jsp" class="toContentMain" title="五金业务员要对九种陷阱订单说“不”">五金业务员要对九种陷阱订单说“不”</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e7475f7a40160.jsp" class="toContentMain" title="我国不锈钢橱柜行业发展有待规范">我国不锈钢橱柜行业发展有待规范</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e7473da6e015e.jsp" class="toContentMain" title="钢材资金紧张 谁偷吃了钢企的“油水”">钢材资金紧张 谁偷吃了钢企的“油水”</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e747016ea015c.jsp" class="toContentMain" title="2011年香港成衣展研讨会专注探讨棉市">2011年香港成衣展研讨会专注探讨棉市</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e746e1421015a.jsp" class="toContentMain" title="上流家族亲子装 新消费潮流已经掀起">上流家族亲子装 新消费潮流已经掀起</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e746b92cd0158.jsp" class="toContentMain" title="国内光伏巨头进军美国 需寻找合作伙伴">国内光伏巨头进军美国 需寻找合作伙伴</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e7469bb640156.jsp" class="toContentMain" title="苹果iPad 2不会降价的5大理由">苹果iPad 2不会降价的5大理由</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e7467934a0154.jsp" class="toContentMain" title="半导体设备业创新面临三大瓶颈">半导体设备业创新面临三大瓶颈</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e746571740152.jsp" class="toContentMain" title="长信科技投1.91亿元生产“触摸大屏”">长信科技投1.91亿元生产“触摸大屏”</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e7462b2dc0150.jsp" class="toContentMain" title="保障性住房建设提速 山河智能全力备战">保障性住房建设提速 山河智能全力备战</a></td>
          <td style="text-align:right">2011-03-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f45a84b0050.jsp" class="toContentMain" title="罗兰整体衣柜：环保衣柜的奥秘">罗兰整体衣柜：环保衣柜的奥秘</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f430baa004e.jsp" class="toContentMain" title="椒江：照明用上太阳能">椒江：照明用上太阳能</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f4158c4004c.jsp" class="toContentMain" title="英飞特：挟“驱动器”以令LED">英飞特：挟“驱动器”以令LED</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f3d393d0048.jsp" class="toContentMain" title="上海太平洋厨房设备有限公司上黑榜被通报">上海太平洋厨房设备有限公司上黑榜被通报</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f3b608d0041.jsp" class="toContentMain" title="明星代言 地板品牌形象提升的唯一途径?">明星代言 地板品牌形象提升的唯一途径?</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f391e470038.jsp" class="toContentMain" title="三部委：中国企业低碳技术研发能力差">三部委：中国企业低碳技术研发能力差</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f37b36e0036.jsp" class="toContentMain" title="超四成火电企业亏损 电价或上半年上调">超四成火电企业亏损 电价或上半年上调</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f3560ae0034.jsp" class="toContentMain" title="电子商务服务业由幕后走至台前 成又一增长点">电子商务服务业由幕后走至台前 成又一增长点</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f3209f70030.jsp" class="toContentMain" title="英特尔宣布完成76.8亿美元收购McAfee">英特尔宣布完成76.8亿美元收购McAfee</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f302f70002a.jsp" class="toContentMain" title="BPA塑料奶瓶或致性早熟被欧盟封杀 北京仍有售">BPA塑料奶瓶或致性早熟被欧盟封杀 北京仍有售</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_77.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_79.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：78/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
