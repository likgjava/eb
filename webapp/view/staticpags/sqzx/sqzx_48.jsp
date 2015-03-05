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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012fb38ca97a0520.jsp" class="toContentMain" title="我国成全球最大电工仪表生产和消费市场">我国成全球最大电工仪表生产和消费市场</a></td>
          <td style="text-align:right">2011-05-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012fb387faee0518.jsp" class="toContentMain" title="墨西哥修订电动机和热水器产品能效标准">墨西哥修订电动机和热水器产品能效标准</a></td>
          <td style="text-align:right">2011-05-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012f9fffaefd00f1.jsp" class="toContentMain" title="从注重形式到注重实质，从“文化建设”到“文化管理”">从注重形式到注重实质，从“文化建设”到“文化管理”</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012f9f6ab2cd005f.jsp" class="toContentMain" title="英国联合利华公司欲采购云南红茶">英国联合利华公司欲采购云南红茶</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012f9f667199003c.jsp" class="toContentMain" title="4月份中国制造业采购经理人指数51.8">4月份中国制造业采购经理人指数51.8</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012f9f65c2e0003a.jsp" class="toContentMain" title="国家能源局：今年将发布核电、风电标准171项">国家能源局：今年将发布核电、风电标准171项</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012f9f63f8e40038.jsp" class="toContentMain" title="南京市胸科医院医疗设备公开招标采购公告">南京市胸科医院医疗设备公开招标采购公告</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99c51d720188.jsp" class="toContentMain" title="北京地铁14号线工程组合式风阀及各类风阀一标招标采购公告">北京地铁14号线工程组合式风阀及各类风阀一标招标采购公告</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99be7088015c.jsp" class="toContentMain" title="北京市平谷区医院医疗设备采购项目招标公告">北京市平谷区医院医疗设备采购项目招标公告</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99b8095b0103.jsp" class="toContentMain" title="工商总局要求逐一登记食品添加剂商家">工商总局要求逐一登记食品添加剂商家</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99b66d1c00fc.jsp" class="toContentMain" title="我国局部省市煤炭库存告急">我国局部省市煤炭库存告急</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99b3e7b400f6.jsp" class="toContentMain" title="山东淄博：多家商超表示将集中采购蔬菜">山东淄博：多家商超表示将集中采购蔬菜</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99ad4c5500e9.jsp" class="toContentMain" title="中国宝钢集团预计铁矿石市场或提前转为供过于求">中国宝钢集团预计铁矿石市场或提前转为供过于求</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99a86e1e00e7.jsp" class="toContentMain" title="北京某企业投资120亿元开发铁矿采选工程">北京某企业投资120亿元开发铁矿采选工程</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99a60a1900e5.jsp" class="toContentMain" title="亚洲地区市场现货橡胶价格走低">亚洲地区市场现货橡胶价格走低</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f96faee012f99a22c9500e3.jsp" class="toContentMain" title="发改委价格司就保持市场煤炭价格稳定进行沟通煤炭企业">发改委价格司就保持市场煤炭价格稳定进行沟通煤炭企业</a></td>
          <td style="text-align:right">2011-04-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f9067aec00981.jsp" class="toContentMain" title="背投拼接墙未来发展将全面进入高清时代">背投拼接墙未来发展将全面进入高清时代</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f905aef440969.jsp" class="toContentMain" title="城市LED照明技术为建筑注入活力城市更人性化">城市LED照明技术为建筑注入活力城市更人性化</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f90591ea9095e.jsp" class="toContentMain" title="高端家具走俏市场 家具保养业将迎来春天">高端家具走俏市场 家具保养业将迎来春天</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f9057dd24095c.jsp" class="toContentMain" title="家装五大主材涨价实情调查：到底涨价没">家装五大主材涨价实情调查：到底涨价没</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_47.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_49.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：48/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
