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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e50818f0b0444.jsp" class="toContentMain" title="孩子是家中的宝贝 卧室装修忌讳要注意">孩子是家中的宝贝 卧室装修忌讳要注意</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e507910840442.jsp" class="toContentMain" title="应对国内市场收紧 家居业营销路在何方">应对国内市场收紧 家居业营销路在何方</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e50741d240440.jsp" class="toContentMain" title="环保部“绿剑”很给力 企业不减排禁上市">环保部“绿剑”很给力 企业不减排禁上市</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e50709f6f043e.jsp" class="toContentMain" title="火电行业需要更加更加绿色 节能减排已成共识">火电行业需要更加更加绿色 节能减排已成共识</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bde9bf50382.jsp" class="toContentMain" title="橱柜行业五大发展趋势主导兔年风向标">橱柜行业五大发展趋势主导兔年风向标</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bd79d910380.jsp" class="toContentMain" title="省钱秘笈 卫浴装修4个不得不看的建议">省钱秘笈 卫浴装修4个不得不看的建议</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bd42514037e.jsp" class="toContentMain" title="新能源行业并未过剩 发展仍有巨大潜力">新能源行业并未过剩 发展仍有巨大潜力</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bd10a1a037c.jsp" class="toContentMain" title="3D电视行业被标准扼住咽喉 上演合纵连横">3D电视行业被标准扼住咽喉 上演合纵连横</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bcdb917037a.jsp" class="toContentMain" title="电子商务向无线应用进军 手机客户端成必争地">电子商务向无线应用进军 手机客户端成必争地</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bcaf7fe0378.jsp" class="toContentMain" title="风电路线分野 双馈直驱谁是未来风电市场主宰">风电路线分野 双馈直驱谁是未来风电市场主宰</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bc292950376.jsp" class="toContentMain" title="团购网站比拼综合实力 风投不再大手大脚">团购网站比拼综合实力 风投不再大手大脚</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bbe40840374.jsp" class="toContentMain" title="汉王易博士对簿公堂 电子书产业面临标准之痛">汉王易博士对簿公堂 电子书产业面临标准之痛</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bbaa7350372.jsp" class="toContentMain" title="2014年环保业务将达400亿欧元">2014年环保业务将达400亿欧元</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4bb57bf80370.jsp" class="toContentMain" title="苏宁3C战略首发力与宏碁签订5亿美金采购大单">苏宁3C战略首发力与宏碁签订5亿美金采购大单</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4baa3425036b.jsp" class="toContentMain" title="珠海一五星酒店拟融资千万">珠海一五星酒店拟融资千万</a></td>
          <td style="text-align:right">2011-02-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e478956ae01e2.jsp" class="toContentMain" title="节能服务行业缺少流通 节能超市模式将被聚焦">节能服务行业缺少流通 节能超市模式将被聚焦</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e477a540d01da.jsp" class="toContentMain" title="国家酝酿“两会”后出台政策扶持大型风电企业">国家酝酿“两会”后出台政策扶持大型风电企业</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e476a1c4501d5.jsp" class="toContentMain" title="碳排放交易权争夺战打响 各地盲目建设存隐患">碳排放交易权争夺战打响 各地盲目建设存隐患</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4760f31301c9.jsp" class="toContentMain" title="“毒桶”曝光牵连饮水机 物理式净水器受宠">“毒桶”曝光牵连饮水机 物理式净水器受宠</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e4753aac501b7.jsp" class="toContentMain" title="彩电能效国标3月1日启动 高能耗彩电将被淘汰">彩电能效国标3月1日启动 高能耗彩电将被淘汰</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_80.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_82.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：81/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
