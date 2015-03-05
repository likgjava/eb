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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048a8c30d00ae.jsp" class="toContentMain" title="用电高峰助涨煤价 环渤海动力煤平均价已连涨11周">用电高峰助涨煤价 环渤海动力煤平均价已连涨11周</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048a80b0f00ac.jsp" class="toContentMain" title="每吨电解铝成本增加100元">每吨电解铝成本增加100元</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048a73c280065.jsp" class="toContentMain" title="商务部拟将钢铁流通企业分为5级">商务部拟将钢铁流通企业分为5级</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048a1f970001f.jsp" class="toContentMain" title="三大矿山报价微降 铁矿石价格下行信号渐强">三大矿山报价微降 铁矿石价格下行信号渐强</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048a13500001d.jsp" class="toContentMain" title="需求旺季已过 螺纹钢可尝试建立中长期空单">需求旺季已过 螺纹钢可尝试建立中长期空单</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813043aa55013043ac24980001.jsp" class="toContentMain" title="光伏组件库存创历史记录">光伏组件库存创历史记录</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb7013043a8e22203ce.jsp" class="toContentMain" title="纺织出口或出现“虚增” 纺企低增长高成本时代来临">纺织出口或出现“虚增” 纺企低增长高成本时代来临</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb7013043a62ffb03cc.jsp" class="toContentMain" title="日本5月制造业PMI为51.3，指标自2年低位回升">日本5月制造业PMI为51.3，指标自2年低位回升</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb70130439a54240398.jsp" class="toContentMain" title="供应趋紧 全球烧碱价格一路攀升">供应趋紧 全球烧碱价格一路攀升</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb7013043987567038f.jsp" class="toContentMain" title="中国铜需求让矿商始料未及">中国铜需求让矿商始料未及</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb701304380614d0377.jsp" class="toContentMain" title="淡水河谷预计第三季铁矿石价格保持稳定">淡水河谷预计第三季铁矿石价格保持稳定</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb70130437f92c30375.jsp" class="toContentMain" title="钢铁业：钢价维持胶着 矿价延续下探">钢铁业：钢价维持胶着 矿价延续下探</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb70130437cd5150371.jsp" class="toContentMain" title="汽贸企业高密度冲刺IPO 销售不旺给上市带来风险">汽贸企业高密度冲刺IPO 销售不旺给上市带来风险</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb70130437bb0e302f8.jsp" class="toContentMain" title="国内铁矿石价格短期内强势不再">国内铁矿石价格短期内强势不再</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb70130437a7c7502f6.jsp" class="toContentMain" title="南方旱灾不会对粮价产生明显影响">南方旱灾不会对粮价产生明显影响</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb701304379f4dd02f4.jsp" class="toContentMain" title="分级标准初定钢铁流通企业将分5级">分级标准初定钢铁流通企业将分5级</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303fbeb701304378d13602f2.jsp" class="toContentMain" title="罗冰生：钢材期货功能已“明显”显现">罗冰生：钢材期货功能已“明显”显现</a></td>
          <td style="text-align:right">2011-05-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081303e8a2e01303e8ab8690001.jsp" class="toContentMain" title="山东苹果泡沫破灭 价格出现反周期下降">山东苹果泡沫破灭 价格出现反周期下降</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e8978170135.jsp" class="toContentMain" title="5月初金银油价格集体跳水 机构预测近期再上涨">5月初金银油价格集体跳水 机构预测近期再上涨</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e7c759a0103.jsp" class="toContentMain" title="5月汇丰中国制造业PMI预览值为51.1%">5月汇丰中国制造业PMI预览值为51.1%</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_40.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_42.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：41/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
