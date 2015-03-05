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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203bd241f014d.jsp" class="toContentMain" title="我国LED产业是最具发展潜力的新兴行业">我国LED产业是最具发展潜力的新兴行业</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203b73a18014c.jsp" class="toContentMain" title="家电业十二五规划：产值目标上调近四成">家电业十二五规划：产值目标上调近四成</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203b50a65014a.jsp" class="toContentMain" title="我国三大塑料建材危机中的发展情况">我国三大塑料建材危机中的发展情况</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203aa69c90146.jsp" class="toContentMain" title="风力发电引领全球可再生能源发展">风力发电引领全球可再生能源发展</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203a9410c0145.jsp" class="toContentMain" title="十二五我国洗衣机产业将重点发展高端产品">十二五我国洗衣机产业将重点发展高端产品</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203a890ea0144.jsp" class="toContentMain" title="化“危”为“机”：推动玩具行业转型升级">化“危”为“机”：推动玩具行业转型升级</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203a3e2a80143.jsp" class="toContentMain" title="太阳能光热产业变局与转型方向">太阳能光热产业变局与转型方向</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203a2c2310142.jsp" class="toContentMain" title="地板行业自主创新 提高产品科技含量">地板行业自主创新 提高产品科技含量</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203a118f5013d.jsp" class="toContentMain" title="儿童玩具等有望列入监督抽查重点产品">儿童玩具等有望列入监督抽查重点产品</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203a083e00138.jsp" class="toContentMain" title="太阳能热水器强制标准将出台 行业或迎洗牌">太阳能热水器强制标准将出台 行业或迎洗牌</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb0132039e992e0136.jsp" class="toContentMain" title="钢市内忧外患 钢贸商如何应对">钢市内忧外患 钢贸商如何应对</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb0132039d8d700135.jsp" class="toContentMain" title="光伏产业大跃进 突显行业隐疾">光伏产业大跃进 突显行业隐疾</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb01320397f0210134.jsp" class="toContentMain" title="太平洋百货年底前退出北京市场">太平洋百货年底前退出北京市场</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fe9b1c0131fec978290017.jsp" class="toContentMain" title="中国门窗幕墙行业 离发达国家还有多远？">中国门窗幕墙行业 离发达国家还有多远？</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fe9b1c0131fec773fe0016.jsp" class="toContentMain" title="LED照明在医疗领域的特点与优势">LED照明在医疗领域的特点与优势</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fe9b1c0131fec5f8760015.jsp" class="toContentMain" title="粘胶短丝价格探底回升 相关公司赶忙复产">粘胶短丝价格探底回升 相关公司赶忙复产</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fe9b1c0131fec4abf20013.jsp" class="toContentMain" title="家居建材成本上涨 家具行业或再掀涨价潮">家居建材成本上涨 家具行业或再掀涨价潮</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fe9b1c0131fec3c0c70012.jsp" class="toContentMain" title="地板行业转变原料进口渠道 缓解涨价压力">地板行业转变原料进口渠道 缓解涨价压力</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fe9b1c0131febefb04000e.jsp" class="toContentMain" title="五金企业在高成本边缘挣扎 处境窘迫">五金企业在高成本边缘挣扎 处境窘迫</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe99aaa7015c.jsp" class="toContentMain" title="皮毛价格忽高忽低 皮革业升级势在必行">皮毛价格忽高忽低 皮革业升级势在必行</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_5.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_7.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：6/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
