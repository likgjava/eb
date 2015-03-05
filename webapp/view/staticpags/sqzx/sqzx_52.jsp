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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f767cc1690233.jsp" class="toContentMain" title="慈溪今年新建续建7条市政道路">慈溪今年新建续建7条市政道路</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f767b81df0231.jsp" class="toContentMain" title="海口市政维修向城郊外延伸">海口市政维修向城郊外延伸</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f767a5293022f.jsp" class="toContentMain" title="贵州贵阳第一季度市政重点工程完成投资逾53亿">贵州贵阳第一季度市政重点工程完成投资逾53亿</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7678edce022d.jsp" class="toContentMain" title="2010年山西农村居民购买家电支出同比增长10.1%">2010年山西农村居民购买家电支出同比增长10.1%</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7677b840022a.jsp" class="toContentMain" title="机床行业“十二五”目标明确 高档机床成发展重点">机床行业“十二五”目标明确 高档机床成发展重点</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7675e75c0227.jsp" class="toContentMain" title="低碳时代来临 IT助力政府绿色采购">低碳时代来临 IT助力政府绿色采购</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7605384401fa.jsp" class="toContentMain" title="小心有人冒充军人采购物品 骗取店主预付货款">小心有人冒充军人采购物品 骗取店主预付货款</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f76031b8e01f8.jsp" class="toContentMain" title="韩国采购大颗粒尿素">韩国采购大颗粒尿素</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f760223da01f6.jsp" class="toContentMain" title="广交会吸客逾10万 采购商到会增一成">广交会吸客逾10万 采购商到会增一成</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7600304201f4.jsp" class="toContentMain" title="钢厂采购态度谨慎 新华铁矿石价格指数下降">钢厂采购态度谨慎 新华铁矿石价格指数下降</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f75fcf26e01ee.jsp" class="toContentMain" title="中药材含硫量作为采购必检项目">中药材含硫量作为采购必检项目</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f75f642b101dd.jsp" class="toContentMain" title="欧盟：中国公共采购达7万亿 监管缺乏透明度">欧盟：中国公共采购达7万亿 监管缺乏透明度</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f71f1354f0024.jsp" class="toContentMain" title="赤峰市克旗环保局强化环境监测能力建设">赤峰市克旗环保局强化环境监测能力建设</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f71eba3370020.jsp" class="toContentMain" title="冷水江市全面提升环境监测能力">冷水江市全面提升环境监测能力</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f71e8dda1001c.jsp" class="toContentMain" title="聚光科技：环境监测物联网先锋 两化融合产业升级推手">聚光科技：环境监测物联网先锋 两化融合产业升级推手</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f71e440600019.jsp" class="toContentMain" title="橱柜涉足衣柜 家装产业寻求多元化发展">橱柜涉足衣柜 家装产业寻求多元化发展</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f71e0a4340017.jsp" class="toContentMain" title="家具材质“穿”上新名词 消费者你伤不起">家具材质“穿”上新名词 消费者你伤不起</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f71dcd351000e.jsp" class="toContentMain" title="塑料行业投资机会逐渐呈现">塑料行业投资机会逐渐呈现</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f71d98464000c.jsp" class="toContentMain" title="一季度中国乙烯产量同比增长32%">一季度中国乙烯产量同比增长32%</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70ef1ff002b9.jsp" class="toContentMain" title="中国创造下的变革 照明巨头带头打响品牌战">中国创造下的变革 照明巨头带头打响品牌战</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_51.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_53.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：52/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
