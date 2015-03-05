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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312626357b1907.jsp" class="toContentMain" title="国电16亿购加拿大风电项目 开启海外投资新领域">国电16亿购加拿大风电项目 开启海外投资新领域</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131183592013126193682171e.jsp" class="toContentMain" title="未来需求预翻番 铝价中期强势显现">未来需求预翻番 铝价中期强势显现</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131183592013126184f5e171c.jsp" class="toContentMain" title="原油上涨及需求上升 美豆油跳涨2.1%">原油上涨及需求上升 美豆油跳涨2.1%</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131261721e4171a.jsp" class="toContentMain" title="中国需求放缓 国内外棉花期货连续大跌">中国需求放缓 国内外棉花期货连续大跌</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312615c4341718.jsp" class="toContentMain" title="中国电子商会称智能电视市场需求超40%">中国电子商会称智能电视市场需求超40%</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131183592013126149a781716.jsp" class="toContentMain" title="国际能源署：明年全球石油需求将有所增加">国际能源署：明年全球石油需求将有所增加</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131183592013121336ff90eea.jsp" class="toContentMain" title="工程机械销量稳定 水利建设起拉动作用">工程机械销量稳定 水利建设起拉动作用</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131212e511b0edf.jsp" class="toContentMain" title="新能源车产业推进 有望再获鼓励政策">新能源车产业推进 有望再获鼓励政策</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131212da00d0edd.jsp" class="toContentMain" title="鞋服企业家陷入微博控 实现零成本营销">鞋服企业家陷入微博控 实现零成本营销</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131212a01150edb.jsp" class="toContentMain" title="医药行业价格管制之差别定价">医药行业价格管制之差别定价</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131183592013121264cea0ed9.jsp" class="toContentMain" title="错过未来2年 将错失LED数千亿美元市场">错过未来2年 将错失LED数千亿美元市场</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312122a7180ece.jsp" class="toContentMain" title="中国液态乳制品需求将增四成">中国液态乳制品需求将增四成</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312121aa120eca.jsp" class="toContentMain" title="服装企业均涨价销售增长受考验">服装企业均涨价销售增长受考验</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312120fa540ec6.jsp" class="toContentMain" title="光伏设备订单明年骤降 晶硅设备商受冲击大">光伏设备订单明年骤降 晶硅设备商受冲击大</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312120888f0ec4.jsp" class="toContentMain" title="动力煤价格三季度末料有所下滑">动力煤价格三季度末料有所下滑</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131210f10a90ea6.jsp" class="toContentMain" title="终端需求不振 中国LED芯片厂商大幅修正扩产计划">终端需求不振 中国LED芯片厂商大幅修正扩产计划</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131210e79060ea4.jsp" class="toContentMain" title="需求不如预期 LED产业压力大">需求不如预期 LED产业压力大</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131210c7ac20ea2.jsp" class="toContentMain" title="全球黄金需求强劲 中国独领风骚">全球黄金需求强劲 中国独领风骚</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131210b4f710ea0.jsp" class="toContentMain" title="需求将迎小高峰 三季度钢价反弹有支撑">需求将迎小高峰 三季度钢价反弹有支撑</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131210942480e96.jsp" class="toContentMain" title="上半年化工水泥需求强劲 相关公司预告高增长">上半年化工水泥需求强劲 相关公司预告高增长</a></td>
          <td style="text-align:right">2011-07-13</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_27.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_29.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：28/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
