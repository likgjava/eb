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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131073a64cf0bb8.jsp" class="toContentMain" title="建筑节能提升聚氨酯板需求 红宝丽有望直接受益">建筑节能提升聚氨酯板需求 红宝丽有望直接受益</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131073958790bb4.jsp" class="toContentMain" title="保障房需求拉动 粗钢日产量首次突破200万吨">保障房需求拉动 粗钢日产量首次突破200万吨</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131073858580bb2.jsp" class="toContentMain" title="多晶硅传统需求旺季将至 价格暂回暖">多晶硅传统需求旺季将至 价格暂回暖</a></td>
          <td style="text-align:right">2011-07-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a701310241e838076c.jsp" class="toContentMain" title="我国海上风力发电起步平稳潜力巨大">我国海上风力发电起步平稳潜力巨大</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131023c4fb90766.jsp" class="toContentMain" title="发展云计算产业已经成为时代变革之需">发展云计算产业已经成为时代变革之需</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131023a7cce0760.jsp" class="toContentMain" title="2012年LED供过于求的比率将会扩大至21%">2012年LED供过于求的比率将会扩大至21%</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013102241c39073e.jsp" class="toContentMain" title="车市冷电荒猛 重庆汽车业再度受伤">车市冷电荒猛 重庆汽车业再度受伤</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a7013102225f480738.jsp" class="toContentMain" title="大蒜价格下跌幅度超过70% 农商博弈入库大限">大蒜价格下跌幅度超过70% 农商博弈入库大限</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131022198130734.jsp" class="toContentMain" title="中国葡萄酒行业保持两位数年增长">中国葡萄酒行业保持两位数年增长</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a701310220cae20732.jsp" class="toContentMain" title="业内人士：原木稀缺推高黄花梨木质家具价格">业内人士：原木稀缺推高黄花梨木质家具价格</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131021f6116072e.jsp" class="toContentMain" title="中国签订钾肥进口大单 下半年涨价17.5%">中国签订钾肥进口大单 下半年涨价17.5%</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70131021de1b30728.jsp" class="toContentMain" title="市场需求不旺促使国内甲醛价格走低">市场需求不旺促使国内甲醛价格走低</a></td>
          <td style="text-align:right">2011-07-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fd0b812402d3.jsp" class="toContentMain" title="家纺俏走礼品市场 行业销售额速度递增">家纺俏走礼品市场 行业销售额速度递增</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fd09f25202d1.jsp" class="toContentMain" title="搭十二五顺风车 建筑涂料市场一片大好">搭十二五顺风车 建筑涂料市场一片大好</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fd08adcb02cf.jsp" class="toContentMain" title="猪价连涨11周 地方政府急抛储备冻肉">猪价连涨11周 地方政府急抛储备冻肉</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fd06228702c2.jsp" class="toContentMain" title="国内商品市场走势分化 化工品逆势上扬">国内商品市场走势分化 化工品逆势上扬</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fd0237d202ba.jsp" class="toContentMain" title="2011年全年电池芯价格至少有10%的成长空间">2011年全年电池芯价格至少有10%的成长空间</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fd0176f002b8.jsp" class="toContentMain" title="电子书终端割价近半 盛大希望低价撬动市场">电子书终端割价近半 盛大希望低价撬动市场</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fcf99d3602aa.jsp" class="toContentMain" title="建材十二五着眼发展新材料 力挺特种玻璃">建材十二五着眼发展新材料 力挺特种玻璃</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fcf8c34f02a8.jsp" class="toContentMain" title="上半年农副产品价格大幅上涨 下半年或集体趋稳">上半年农副产品价格大幅上涨 下半年或集体趋稳</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_30.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_32.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：31/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
