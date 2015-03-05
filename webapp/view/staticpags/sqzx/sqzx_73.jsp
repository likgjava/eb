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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec15e191f0b4d.jsp" class="toContentMain" title="江北年内投建30个安置房项目总投资过百亿">江北年内投建30个安置房项目总投资过百亿</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc9de98b086c.jsp" class="toContentMain" title="锦州工业重点推进锦州钛业钛白粉生产项目">锦州工业重点推进锦州钛业钛白粉生产项目</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc96f690086a.jsp" class="toContentMain" title="温州灯具出口首超1亿美元 节能灯大幅增长">温州灯具出口首超1亿美元 节能灯大幅增长</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc95454a0868.jsp" class="toContentMain" title="LED灯具照明产业的繁荣温暖了配件市场">LED灯具照明产业的繁荣温暖了配件市场</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc8b8d130866.jsp" class="toContentMain" title="日本大地震 或再次引爆家用消防器材市场">日本大地震 或再次引爆家用消防器材市场</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc6f20cd0862.jsp" class="toContentMain" title="建材工业步入转型期 新型涂料是未来主流">建材工业步入转型期 新型涂料是未来主流</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc67d2cc0860.jsp" class="toContentMain" title="贴片保险丝面世２０１１">贴片保险丝面世２０１１</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc5d8858085e.jsp" class="toContentMain" title="日本地震将促进国内安全应急救灾设备发展">日本地震将促进国内安全应急救灾设备发展</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc59b886085c.jsp" class="toContentMain" title="日本地震导致核电泄露 挖掘机械等需求扩大">日本地震导致核电泄露 挖掘机械等需求扩大</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc538022084e.jsp" class="toContentMain" title="4万亿资入“十二五”水利规划">4万亿资入“十二五”水利规划</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ebc3f3c9c084c.jsp" class="toContentMain" title="楼市调控促使地板价格受变相下调">楼市调控促使地板价格受变相下调</a></td>
          <td style="text-align:right">2011-03-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb8784c40078b.jsp" class="toContentMain" title="人大代表钟南山:药品招标“价低者得”很危险">人大代表钟南山:药品招标“价低者得”很危险</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb86e10f7076e.jsp" class="toContentMain" title="全球原木出口揭露 橱柜原料稳中有跌">全球原木出口揭露 橱柜原料稳中有跌</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb86d5474076c.jsp" class="toContentMain" title="《卫生陶瓷》国标出台 节水洁具缺认证">《卫生陶瓷》国标出台 节水洁具缺认证</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb86ad9980764.jsp" class="toContentMain" title="陶卫市场原材料集体涨价 到底谁是赢家">陶卫市场原材料集体涨价 到底谁是赢家</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb84a39630711.jsp" class="toContentMain" title="家具环保认证泛滥 消费者警防家居假低碳">家具环保认证泛滥 消费者警防家居假低碳</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb83fcdfb06f5.jsp" class="toContentMain" title="杭产家具开拓市场 品质化品牌化成重头戏">杭产家具开拓市场 品质化品牌化成重头戏</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb82cbbc706cb.jsp" class="toContentMain" title="三一集团创新给力 新品挖掘机即将亮相">三一集团创新给力 新品挖掘机即将亮相</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb8197ab206a5.jsp" class="toContentMain" title="我国工程机械行业：得挖掘机者得“天下”">我国工程机械行业：得挖掘机者得“天下”</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb815d7c606a2.jsp" class="toContentMain" title="受困日本地震 工程机械二手挖机进口将下降">受困日本地震 工程机械二手挖机进口将下降</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_72.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_74.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：73/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
