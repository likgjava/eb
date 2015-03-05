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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f9053ec620956.jsp" class="toContentMain" title="陶瓷产品流行新趋势 薄板成行业新军">陶瓷产品流行新趋势 薄板成行业新军</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f90523cf0094a.jsp" class="toContentMain" title="关于家具产品现状的思考：产品生命短之惑">关于家具产品现状的思考：产品生命短之惑</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f90471c0e093a.jsp" class="toContentMain" title="家电设计迅猛发展 5年内家电进入物联时代">家电设计迅猛发展 5年内家电进入物联时代</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f9044000f0935.jsp" class="toContentMain" title="技术标准竞争时代 中国家电业亟待标准导航">技术标准竞争时代 中国家电业亟待标准导航</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f903e4f3e0931.jsp" class="toContentMain" title="重庆一季度家电下乡产品销售89.4万台">重庆一季度家电下乡产品销售89.4万台</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f8fe4ae370895.jsp" class="toContentMain" title="“碳访行动”启动仪式新闻发布会在京召开">“碳访行动”启动仪式新闻发布会在京召开</a></td>
          <td style="text-align:right">2011-04-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f8b23f6f404eb.jsp" class="toContentMain" title="原材料涨价10% 衣柜企业被逼到墙角?">原材料涨价10% 衣柜企业被逼到墙角?</a></td>
          <td style="text-align:right">2011-04-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f8ab47db9025e.jsp" class="toContentMain" title="婚房装修火爆 低碳橱柜衣柜受青睐">婚房装修火爆 低碳橱柜衣柜受青睐</a></td>
          <td style="text-align:right">2011-04-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f8aac2319025c.jsp" class="toContentMain" title="智能建筑成趋势 智能家居倡导节能减排">智能建筑成趋势 智能家居倡导节能减排</a></td>
          <td style="text-align:right">2011-04-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f8a9cdd4c025a.jsp" class="toContentMain" title="国内大功率LED封装胶不逊于国际品牌">国内大功率LED封装胶不逊于国际品牌</a></td>
          <td style="text-align:right">2011-04-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7c8d65012f8a9b0bb60258.jsp" class="toContentMain" title="“五一”家居促销潮消费者小心价格陷阱">“五一”家居促销潮消费者小心价格陷阱</a></td>
          <td style="text-align:right">2011-04-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b38628b0107.jsp" class="toContentMain" title="为节能 地下车库将试点推广LED灯">为节能 地下车库将试点推广LED灯</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b336b6600fc.jsp" class="toContentMain" title="集中采购将成TD终端合作主要模式 拟提升性价比">集中采购将成TD终端合作主要模式 拟提升性价比</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b2d16a100ee.jsp" class="toContentMain" title="金马凯旋家居CBD春季批发与采购展销会盛装开幕">金马凯旋家居CBD春季批发与采购展销会盛装开幕</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b24587a00d5.jsp" class="toContentMain" title="第14届渝洽会采购清单超52亿美元">第14届渝洽会采购清单超52亿美元</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b1fa52f00a8.jsp" class="toContentMain" title="民营医院发展前景看好">民营医院发展前景看好</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b1bbac0009a.jsp" class="toContentMain" title="民营医院举步维艰寻出路">民营医院举步维艰寻出路</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b183a410095.jsp" class="toContentMain" title="湖南鼓励社会资本办医疗机构 满足群众多种需求">湖南鼓励社会资本办医疗机构 满足群众多种需求</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b16cad50090.jsp" class="toContentMain" title="民营医院线上推广的新思路">民营医院线上推广的新思路</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b14bada008e.jsp" class="toContentMain" title="5年购11架直升机组建空中120 危急病人免费接送">5年购11架直升机组建空中120 危急病人免费接送</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_48.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_50.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：49/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
