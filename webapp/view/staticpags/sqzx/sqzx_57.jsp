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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f679689100677.jsp" class="toContentMain" title="镇江环保型大口径PC管件热销">镇江环保型大口径PC管件热销</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6788d68805c8.jsp" class="toContentMain" title="世行向乌鲁木齐集中供热节能项目贷款1亿美元">世行向乌鲁木齐集中供热节能项目贷款1亿美元</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6760fa2305bb.jsp" class="toContentMain" title="台湾今年有望跃升全球最大LED产地">台湾今年有望跃升全球最大LED产地</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f675f0a5905b9.jsp" class="toContentMain" title="LED显示屏倡导良性循环发展越来越迫切">LED显示屏倡导良性循环发展越来越迫切</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f66a905e704cf.jsp" class="toContentMain" title="山东十二五电力规划投资1100亿元">山东十二五电力规划投资1100亿元</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f66a46fa104c1.jsp" class="toContentMain" title="茶业世界打造兰州最大中高端茶叶市场">茶业世界打造兰州最大中高端茶叶市场</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f66a2b6ae04bf.jsp" class="toContentMain" title="贵州遵义“湄潭翠芽”茶叶品牌价值达9亿">贵州遵义“湄潭翠芽”茶叶品牌价值达9亿</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f66a1445c04bd.jsp" class="toContentMain" title="兰州春茶大量上市，茶叶市场春茶多">兰州春茶大量上市，茶叶市场春茶多</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f669e3ca104bb.jsp" class="toContentMain" title="2013年LED背光应用需求量或达到高峰">2013年LED背光应用需求量或达到高峰</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f669c8f0004b9.jsp" class="toContentMain" title="Cree推出全集成型LED模组系列首款高流明产品">Cree推出全集成型LED模组系列首款高流明产品</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6698b25804b7.jsp" class="toContentMain" title="国民医疗消费向基层机构倾斜">国民医疗消费向基层机构倾斜</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f66943e2804b1.jsp" class="toContentMain" title="纵观建材业发展 亟需挺进“诚品”时代">纵观建材业发展 亟需挺进“诚品”时代</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f668e262e04af.jsp" class="toContentMain" title="少则1到2个月多则3到5个月 木门就会涨价？">少则1到2个月多则3到5个月 木门就会涨价？</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f66840ca504a3.jsp" class="toContentMain" title="千亿新能源汽车盛会 五金产业引爆市场契机">千亿新能源汽车盛会 五金产业引爆市场契机</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f667f9e0e04a1.jsp" class="toContentMain" title="GMC模式：网上预先匹配，广交会期间买家采购更快捷">GMC模式：网上预先匹配，广交会期间买家采购更快捷</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f667d21a8049c.jsp" class="toContentMain" title="集成吊顶不在家装渠道采购之列已成过去">集成吊顶不在家装渠道采购之列已成过去</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6679de2e048a.jsp" class="toContentMain" title="青岛泰能5亿扩建水清沟热电厂">青岛泰能5亿扩建水清沟热电厂</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6674e327046c.jsp" class="toContentMain" title="中东、北非提高小麦采购，法国出口量有望创新高">中东、北非提高小麦采购，法国出口量有望创新高</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f667314720465.jsp" class="toContentMain" title="四月的采购清单:辣女裤短时尚大赏">四月的采购清单:辣女裤短时尚大赏</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f667032cc0448.jsp" class="toContentMain" title="武汉荆州咸宁组织买手采购嘉鱼包菜">武汉荆州咸宁组织买手采购嘉鱼包菜</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_56.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_58.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：57/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
