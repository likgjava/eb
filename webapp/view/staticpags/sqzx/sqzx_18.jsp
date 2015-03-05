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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d3b7cdd1258.jsp" class="toContentMain" title="我国工程机械确定装备制造业重点产业">我国工程机械确定装备制造业重点产业</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d3936ea1256.jsp" class="toContentMain" title="制造业数据不及预期 金属价格展开调整">制造业数据不及预期 金属价格展开调整</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d3619f71255.jsp" class="toContentMain" title="液晶面板价格反弹落空 产能扩张消费疲软">液晶面板价格反弹落空 产能扩张消费疲软</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d2fa43f124d.jsp" class="toContentMain" title="食品添加剂应让消费者享有充分的知情权">食品添加剂应让消费者享有充分的知情权</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d2a4bf2124a.jsp" class="toContentMain" title="印染企业采用新工艺积极应对">印染企业采用新工艺积极应对</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d28e3dd1249.jsp" class="toContentMain" title="太阳能光伏制造的真实成本：压力重重">太阳能光伏制造的真实成本：压力重重</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d1ff40b1246.jsp" class="toContentMain" title="药材调控见效 7月党参环比降价11%">药材调控见效 7月党参环比降价11%</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d1f56e61245.jsp" class="toContentMain" title="电解铝行业利润大降 产能仍有大幅扩张冲动">电解铝行业利润大降 产能仍有大幅扩张冲动</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d1e2c401243.jsp" class="toContentMain" title="中国铁矿石价格指数8月试运行">中国铁矿石价格指数8月试运行</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131881fa86b0b26.jsp" class="toContentMain" title="我国涂料行业标准与国外新标准差距">我国涂料行业标准与国外新标准差距</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131881ed5280b25.jsp" class="toContentMain" title="变频涡旋压缩机成研究热点">变频涡旋压缩机成研究热点</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131881d7ed40b24.jsp" class="toContentMain" title="高附加值 实木橱柜强势再战中高端市场">高附加值 实木橱柜强势再战中高端市场</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318818efac0b22.jsp" class="toContentMain" title="我国机械保持持续平稳发展但仍需继续努力">我国机械保持持续平稳发展但仍需继续努力</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013188175f6d0b20.jsp" class="toContentMain" title="仪器仪表机械制造业质量竞争力分析">仪器仪表机械制造业质量竞争力分析</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131881332470b1d.jsp" class="toContentMain" title="日本不弃核能力推LED 产品重视安全性及性能">日本不弃核能力推LED 产品重视安全性及性能</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131880fd8ba0b1c.jsp" class="toContentMain" title="机床工业产值“十二五”冲击八千亿">机床工业产值“十二五”冲击八千亿</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131880c55110b19.jsp" class="toContentMain" title="硅胶印花产品受消费者追捧">硅胶印花产品受消费者追捧</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131880aced10b18.jsp" class="toContentMain" title="玩具业面临欧美“安全大考”">玩具业面临欧美“安全大考”</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131880707490b14.jsp" class="toContentMain" title="工程机械9000亿蛋糕待切 投资要点分析">工程机械9000亿蛋糕待切 投资要点分析</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013187fcb43a0b11.jsp" class="toContentMain" title="多晶硅价格短期有望小幅续涨">多晶硅价格短期有望小幅续涨</a></td>
          <td style="text-align:right">2011-08-02</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_17.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_19.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：18/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
