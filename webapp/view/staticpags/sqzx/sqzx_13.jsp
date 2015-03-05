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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d13c9933067d.jsp" class="toContentMain" title="塑胶地板在建筑行业攀升市场需求日益扩大">塑胶地板在建筑行业攀升市场需求日益扩大</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d13c0407067c.jsp" class="toContentMain" title="地板市场竞争加剧 企业施行“通杀”战略">地板市场竞争加剧 企业施行“通杀”战略</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d13b4a25067b.jsp" class="toContentMain" title="家纺行业业绩好得益市场竞争环境缓和">家纺行业业绩好得益市场竞争环境缓和</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d13ac295067a.jsp" class="toContentMain" title="剪不断理还乱 化纤是否永远挣脱不了棉花结">剪不断理还乱 化纤是否永远挣脱不了棉花结</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d13928020679.jsp" class="toContentMain" title="家居建材进入保障房市场 抛出性价比新主张">家居建材进入保障房市场 抛出性价比新主张</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d134c4b00667.jsp" class="toContentMain" title="七大液晶面板巨头均被韩国FTC调查">七大液晶面板巨头均被韩国FTC调查</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d017b39c0081.jsp" class="toContentMain" title="白酒金三角变成地域品牌 川酒内部新矛盾显现">白酒金三角变成地域品牌 川酒内部新矛盾显现</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d016e9490080.jsp" class="toContentMain" title="中国面板业恐陷投产即亏损困局">中国面板业恐陷投产即亏损困局</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb0dbc240035.jsp" class="toContentMain" title="锂元素决定电动汽车未来">锂元素决定电动汽车未来</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb0ca2240034.jsp" class="toContentMain" title="地板企业：开展网络营销 争夺80后市场">地板企业：开展网络营销 争夺80后市场</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb0c0e1e0033.jsp" class="toContentMain" title="棉花价格一路狂泻 难撼棉纺服装涨价">棉花价格一路狂泻 难撼棉纺服装涨价</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb08c9f80032.jsp" class="toContentMain" title="棉花价格一路狂泻 难撼棉纺服装涨价">棉花价格一路狂泻 难撼棉纺服装涨价</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb085c670031.jsp" class="toContentMain" title="2011我医疗器械进出口额将超250亿">2011我医疗器械进出口额将超250亿</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb05a8b0002f.jsp" class="toContentMain" title="玻璃钢门窗市场处于起步阶段 面临考验">玻璃钢门窗市场处于起步阶段 面临考验</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb04e4ce002e.jsp" class="toContentMain" title="盘点下半年卫浴市场关键词 环保家具智能化">盘点下半年卫浴市场关键词 环保家具智能化</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb03fcea002d.jsp" class="toContentMain" title="金属门窗市场2011会呈现良好增长态势">金属门窗市场2011会呈现良好增长态势</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb030c9d002c.jsp" class="toContentMain" title="中国食品加工包装制造业合作前景看好">中国食品加工包装制造业合作前景看好</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cb000425002a.jsp" class="toContentMain" title="山东文登机电工具产业发展迅猛">山东文登机电工具产业发展迅猛</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cafe32e20028.jsp" class="toContentMain" title="能源业风电新标准出台 噩耗还是福音">能源业风电新标准出台 噩耗还是福音</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cafd267c0027.jsp" class="toContentMain" title="高铁降速求安全 五金业谨记稳步发展是前提">高铁降速求安全 五金业谨记稳步发展是前提</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_12.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_14.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：13/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
