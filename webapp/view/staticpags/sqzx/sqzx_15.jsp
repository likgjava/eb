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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac2cb909143a.jsp" class="toContentMain" title="达芬奇家具被查造假 仍标榜进口欲盖弥彰">达芬奇家具被查造假 仍标榜进口欲盖弥彰</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac2b049f1439.jsp" class="toContentMain" title="包装机械行业 未来将高唱绿色环保凯歌">包装机械行业 未来将高唱绿色环保凯歌</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac2863d81438.jsp" class="toContentMain" title="全国纯花生油价格同比涨幅近两成">全国纯花生油价格同比涨幅近两成</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac274b391437.jsp" class="toContentMain" title="TCL多媒体升全年 LCD电视销售目标960万台">TCL多媒体升全年 LCD电视销售目标960万台</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac26982f1436.jsp" class="toContentMain" title="家电市场较低迷 上半年销售额仅增一成">家电市场较低迷 上半年销售额仅增一成</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac20fffc1433.jsp" class="toContentMain" title="3D与智能 中国电视业跨越式发展的契机">3D与智能 中国电视业跨越式发展的契机</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac204dd71432.jsp" class="toContentMain" title="啤酒行业新一轮扩张到来 寡头竞争刚刚开始">啤酒行业新一轮扩张到来 寡头竞争刚刚开始</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac1ed4b91431.jsp" class="toContentMain" title="家电产业链压力上传导配件企业日子难过">家电产业链压力上传导配件企业日子难过</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac1da3f81430.jsp" class="toContentMain" title="热泵市场需要创新营销方式">热泵市场需要创新营销方式</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac1d019b142f.jsp" class="toContentMain" title="国内光伏行业的发展将呈现两个趋势">国内光伏行业的发展将呈现两个趋势</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac176bd7142e.jsp" class="toContentMain" title="水泥企业并购成本增加 扩张路径生变">水泥企业并购成本增加 扩张路径生变</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac15863c142d.jsp" class="toContentMain" title="白糖现货触摸8000元/吨 商务部投放储备糖调控">白糖现货触摸8000元/吨 商务部投放储备糖调控</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac150ae8142c.jsp" class="toContentMain" title="国内稀土疯狂涨势降温">国内稀土疯狂涨势降温</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac1362ba142a.jsp" class="toContentMain" title="中国稀土产业政策调整成效明显">中国稀土产业政策调整成效明显</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac12de261429.jsp" class="toContentMain" title="日系数码产品价格回到震前水平">日系数码产品价格回到震前水平</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac115baf1428.jsp" class="toContentMain" title="煤化工行业过度发展 转化率超七成产能过剩">煤化工行业过度发展 转化率超七成产能过剩</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a71491790bfb.jsp" class="toContentMain" title="解析中国涂料行业消费市场10大趋势">解析中国涂料行业消费市场10大趋势</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a712cc8e0bf9.jsp" class="toContentMain" title="竹纤维销量破百亿“独立新军”崛起纺织业">竹纤维销量破百亿“独立新军”崛起纺织业</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a71214e80bf8.jsp" class="toContentMain" title="家电市场上半年增速减缓 3G智能引领趋势">家电市场上半年增速减缓 3G智能引领趋势</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a70f3b380bf7.jsp" class="toContentMain" title="2011年上半年中国工程机械市场综述">2011年上半年中国工程机械市场综述</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_14.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_16.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：15/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
