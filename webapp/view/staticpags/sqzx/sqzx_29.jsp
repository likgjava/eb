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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311c00254d04bb.jsp" class="toContentMain" title="全球光伏企业停止大规模扩张 设备采购支出明年料减47%">全球光伏企业停止大规模扩张 设备采购支出明年料减47%</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bfe4b6804a9.jsp" class="toContentMain" title="近一成产能年内淘汰 水泥价格或上涨相迎">近一成产能年内淘汰 水泥价格或上涨相迎</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bfda63e04a4.jsp" class="toContentMain" title="电子元件十二五规划出炉 年均投资将达千亿">电子元件十二五规划出炉 年均投资将达千亿</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bfcd45704a1.jsp" class="toContentMain" title="大起大落一年间 棉价寻底进行时">大起大落一年间 棉价寻底进行时</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bfaf365049f.jsp" class="toContentMain" title="乳业国标之争乃利益之争">乳业国标之争乃利益之争</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bf4e80b0411.jsp" class="toContentMain" title="建材内需市场低迷陶企加速海外布局">建材内需市场低迷陶企加速海外布局</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bf34b3c03da.jsp" class="toContentMain" title="国内碳酸锂业复苏拐点隐现">国内碳酸锂业复苏拐点隐现</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bf0e08b03cc.jsp" class="toContentMain" title="十二五规划构架成型 物联网产业向民企敞开大门">十二五规划构架成型 物联网产业向民企敞开大门</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bee9c5903b5.jsp" class="toContentMain" title="豪车领涨上半年中国车市 国产化成竞争焦点">豪车领涨上半年中国车市 国产化成竞争焦点</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bdb69b90381.jsp" class="toContentMain" title="市场不复低迷需求升级 车企看好大型客车">市场不复低迷需求升级 车企看好大型客车</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bda727f037f.jsp" class="toContentMain" title="需求释放库存下降钢材市场迎短期行情">需求释放库存下降钢材市场迎短期行情</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bd8c69d0377.jsp" class="toContentMain" title="电解铝产能过剩之谜：终端需求旺盛不可思议">电解铝产能过剩之谜：终端需求旺盛不可思议</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bd77efc0375.jsp" class="toContentMain" title="需求低迷 三季度原油难现趋势行情">需求低迷 三季度原油难现趋势行情</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201311bd6bc870373.jsp" class="toContentMain" title="河北石家庄纺企需求疲软 棉花市场继续恶化">河北石家庄纺企需求疲软 棉花市场继续恶化</a></td>
          <td style="text-align:right">2011-07-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116db4e0e10f5.jsp" class="toContentMain" title="太阳能电池全自动丝网印刷机研制成功">太阳能电池全自动丝网印刷机研制成功</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116d9bf3e10f3.jsp" class="toContentMain" title="2015年生物质发电规划目标达1300万千瓦">2015年生物质发电规划目标达1300万千瓦</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116d8cb8b10f1.jsp" class="toContentMain" title="中国PK印度:谁是太阳能市场新人王?">中国PK印度:谁是太阳能市场新人王?</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116d7c1a910ef.jsp" class="toContentMain" title="涂料市场格局转变为船舶涂料带来发展契机">涂料市场格局转变为船舶涂料带来发展契机</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116d3412a10e9.jsp" class="toContentMain" title="英国最新研究表示塑料太阳能电池或实现商业化">英国最新研究表示塑料太阳能电池或实现商业化</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116d16fce10e5.jsp" class="toContentMain" title="灯协炮轰政府缺乏对LED产业的全方位扶持">灯协炮轰政府缺乏对LED产业的全方位扶持</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_28.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_30.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：29/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
