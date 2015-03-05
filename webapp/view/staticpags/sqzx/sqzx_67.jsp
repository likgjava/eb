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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0aa0efd511c6.jsp" class="toContentMain" title="阿里巴巴当“红娘”6大国际采购商“牵手”">阿里巴巴当“红娘”6大国际采购商“牵手”</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0a9f50ec11c4.jsp" class="toContentMain" title="“2011浦东国际采购洽谈会暨论坛”隆重举行">“2011浦东国际采购洽谈会暨论坛”隆重举行</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0a9d35bf11c2.jsp" class="toContentMain" title="土耳其招标采购尿素">土耳其招标采购尿素</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0a9b86a411c0.jsp" class="toContentMain" title="3月日本采购经理人指数加速下滑">3月日本采购经理人指数加速下滑</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0a99f56211be.jsp" class="toContentMain" title="华兹卜：菲柯特漆荣膺世界500强建材采购首选涂料品牌">华兹卜：菲柯特漆荣膺世界500强建材采购首选涂料品牌</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05f5d95b0960.jsp" class="toContentMain" title="厦门小众茶受青睐采购增10倍 "抗辐射概念"遭炒作">厦门小众茶受青睐采购增10倍 "抗辐射概念"遭炒作</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05f2f4e1095e.jsp" class="toContentMain" title="中国电信规模部署高压直流供电系统 预计年采购增幅30%">中国电信规模部署高压直流供电系统 预计年采购增幅30%</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05f0d91b095c.jsp" class="toContentMain" title="中华自动化开启国内工业品采购新时代">中华自动化开启国内工业品采购新时代</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05ee13ed08c2.jsp" class="toContentMain" title="国际采购商瞄上安徽造">国际采购商瞄上安徽造</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05eb3e0f087f.jsp" class="toContentMain" title="六大国际采购巨头与皖企“对接”">六大国际采购巨头与皖企“对接”</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05e9b103087d.jsp" class="toContentMain" title="红木采购商转悠半月不敢下单">红木采购商转悠半月不敢下单</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05e714a807ab.jsp" class="toContentMain" title="铝上行迎接采购旺季">铝上行迎接采购旺季</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05e2aa340794.jsp" class="toContentMain" title="22亿采购单 移动重金开拓OPhone平板市场">22亿采购单 移动重金开拓OPhone平板市场</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05e09e940792.jsp" class="toContentMain" title="世界500强“欧迪办公”采购杭货">世界500强“欧迪办公”采购杭货</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05ddb8aa0790.jsp" class="toContentMain" title="黑龙江省基层医疗机构药品采购集中招标">黑龙江省基层医疗机构药品采购集中招标</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f058a4352034c.jsp" class="toContentMain" title="2010年青海省省级政府采购规模突破30亿元大关">2010年青海省省级政府采购规模突破30亿元大关</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0587ec04034a.jsp" class="toContentMain" title="受日本强震影响国产彩电采购成本或提升">受日本强震影响国产彩电采购成本或提升</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05864c380348.jsp" class="toContentMain" title="黑河市政府采购中心民生工程采购项目现场抽检验收工作初见成效">黑河市政府采购中心民生工程采购项目现场抽检验收工作初见成效</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0584287e0346.jsp" class="toContentMain" title="中铁四局“推行阳光采购，降低生产成本”">中铁四局“推行阳光采购，降低生产成本”</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f057d1a400342.jsp" class="toContentMain" title="新中源陶瓷飞天给 利 免单 抽奖 北京大店采购游">新中源陶瓷飞天给 利 免单 抽奖 北京大店采购游</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_66.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_68.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：67/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
