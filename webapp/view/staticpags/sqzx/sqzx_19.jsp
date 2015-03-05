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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013187fbac700b10.jsp" class="toContentMain" title="风机制造商出海渐成趋势 有望成新的利润增长点">风机制造商出海渐成趋势 有望成新的利润增长点</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013187f6467e0b0d.jsp" class="toContentMain" title="燃气设备领域拓荒,构筑平台引发无限商机">燃气设备领域拓荒,构筑平台引发无限商机</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013187f52e950b0c.jsp" class="toContentMain" title="夏季葡萄酒市场商机无限">夏季葡萄酒市场商机无限</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013187f1c9ce0b09.jsp" class="toContentMain" title="微波炉高端化莫忘消费者需求">微波炉高端化莫忘消费者需求</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013187f058ae0b07.jsp" class="toContentMain" title="光伏上网电价出台 中国式需求大幕开启">光伏上网电价出台 中国式需求大幕开启</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182f27abd0110.jsp" class="toContentMain" title="日本太阳能联盟向中国企业发起挑战">日本太阳能联盟向中国企业发起挑战</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182eaae17010c.jsp" class="toContentMain" title="小功率LED照明点亮光伏应用大市场">小功率LED照明点亮光伏应用大市场</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182e7d770010a.jsp" class="toContentMain" title="安防IT化成趋势 网络视频监控得迅速发展">安防IT化成趋势 网络视频监控得迅速发展</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182e4492c0108.jsp" class="toContentMain" title="纺织机械行业自动化技术发展趋势">纺织机械行业自动化技术发展趋势</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182e349300107.jsp" class="toContentMain" title="酱香型白酒标准年内实施 不得添加食用酒精">酱香型白酒标准年内实施 不得添加食用酒精</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182df77800103.jsp" class="toContentMain" title="白酒高端价值已日趋理性">白酒高端价值已日趋理性</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182deae170101.jsp" class="toContentMain" title="千亿元市场吸引力 童装市场的求解之旅">千亿元市场吸引力 童装市场的求解之旅</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182dcaaff00ff.jsp" class="toContentMain" title="中国白酒为何总不能走向世界？">中国白酒为何总不能走向世界？</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182dbbe8a00fe.jsp" class="toContentMain" title="棉价跌秋装仍涨价 服装企业利润薄只敢接小单">棉价跌秋装仍涨价 服装企业利润薄只敢接小单</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182db523f00fc.jsp" class="toContentMain" title="2011上半年纺织企业应对艰难时局转变战略">2011上半年纺织企业应对艰难时局转变战略</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182da813c00fb.jsp" class="toContentMain" title="陶瓷花纸业规模效应仍存 国际环境刺激利好市场">陶瓷花纸业规模效应仍存 国际环境刺激利好市场</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182d599e000f6.jsp" class="toContentMain" title="上半年铁矿石涨4成 钢铁业多付三大矿山160亿美元">上半年铁矿石涨4成 钢铁业多付三大矿山160亿美元</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182d4f45500f5.jsp" class="toContentMain" title="新兴产业规划临近 生物质能或领衔新能源">新兴产业规划临近 生物质能或领衔新能源</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182d4794200f4.jsp" class="toContentMain" title="上半年石化工业产值月增幅超过30%">上半年石化工业产值月增幅超过30%</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182d3fbf900f3.jsp" class="toContentMain" title="太阳能发电装机容量五年内将增加十倍">太阳能发电装机容量五年内将增加十倍</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_18.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_20.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：19/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
