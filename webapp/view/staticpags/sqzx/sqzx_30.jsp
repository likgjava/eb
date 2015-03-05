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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116d0f4f310e3.jsp" class="toContentMain" title="联创光电复牌 拟募资6亿拓LED产业">联创光电复牌 拟募资6亿拓LED产业</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116ccc7cd10dd.jsp" class="toContentMain" title="卫浴企业如何消化“涨价潮”带来的负面影响">卫浴企业如何消化“涨价潮”带来的负面影响</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116cb3cbe10d5.jsp" class="toContentMain" title="从企业并购看中国工程机械的国际化发展之路">从企业并购看中国工程机械的国际化发展之路</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116ca971c10d3.jsp" class="toContentMain" title="家具业竞争进入白热化 未来发展有五大趋势">家具业竞争进入白热化 未来发展有五大趋势</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116c0859110c5.jsp" class="toContentMain" title="仪器仪表行业十二五之行业关键技术">仪器仪表行业十二五之行业关键技术</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116bbd4bf10bd.jsp" class="toContentMain" title="家电企业瞄上太阳能商机">家电企业瞄上太阳能商机</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116ac215410a3.jsp" class="toContentMain" title="维C行业产能严重过剩 市场准入新门槛最快7月出台">维C行业产能严重过剩 市场准入新门槛最快7月出台</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116aad73e109f.jsp" class="toContentMain" title="六月份CPI同比涨6.4% 物价何时会回头？">六月份CPI同比涨6.4% 物价何时会回头？</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116aa04a2109d.jsp" class="toContentMain" title="中钢协官员：中国钢铁业将进入低增速时代">中钢协官员：中国钢铁业将进入低增速时代</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116a29bd31079.jsp" class="toContentMain" title="投资顾问评 2011年购车需求正开始释放">投资顾问评 2011年购车需求正开始释放</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013116a1e4601075.jsp" class="toContentMain" title="冷年新趋势：创造需求使空调细分市场火爆">冷年新趋势：创造需求使空调细分市场火爆</a></td>
          <td style="text-align:right">2011-07-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131076c6e480c22.jsp" class="toContentMain" title="汇源果汁正式进军日本市场 拟进一步开拓全球市场">汇源果汁正式进军日本市场 拟进一步开拓全球市场</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131076ad2e70c1c.jsp" class="toContentMain" title="3D电视市场崛起需过三道坎">3D电视市场崛起需过三道坎</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131076a00ae0c1a.jsp" class="toContentMain" title="华为将在英国推出首款自有品牌手机">华为将在英国推出首款自有品牌手机</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a701310768f04f0c14.jsp" class="toContentMain" title="啤酒超高端追赶洋酒 最贵720毫升售价398元">啤酒超高端追赶洋酒 最贵720毫升售价398元</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131076261870c0a.jsp" class="toContentMain" title="发改委抑制涨价 五金市场深陷价格困境">发改委抑制涨价 五金市场深陷价格困境</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a701310760f0ce0c04.jsp" class="toContentMain" title="LED可为植物生长“定制”光线">LED可为植物生长“定制”光线</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131075bbab90bfc.jsp" class="toContentMain" title="十二五中国钾肥企业海外大举找矿">十二五中国钾肥企业海外大举找矿</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131075af5bc0bf6.jsp" class="toContentMain" title="白糖抛储成交价走高 糖价有望继续上行">白糖抛储成交价走高 糖价有望继续上行</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131073b50760bba.jsp" class="toContentMain" title="需求旺盛市场繁荣 金银珠宝企业扩张忙">需求旺盛市场繁荣 金银珠宝企业扩张忙</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_29.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_31.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：30/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
