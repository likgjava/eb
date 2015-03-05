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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=zzbjyctwh&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="诸子百家与传统文化" class="cmsHref_self">诸子百家与传统文化</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>诸子百家与传统文化展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812ed7f020012ee0c98e670995" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eed9c26710118.jsp" class="toContentMain" title="君子爱财，取之有道">君子爱财，取之有道</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eed7357b30116.jsp" class="toContentMain" title="孔子的中庸之道">孔子的中庸之道</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eed63a95400b8.jsp" class="toContentMain" title="己所不欲，勿施于人。">己所不欲，勿施于人。</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eebf33dea1f04.jsp" class="toContentMain" title="从徽商看中国传统商业的伦理、道德原则">从徽商看中国传统商业的伦理、道德原则</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eebf0d9011ef3.jsp" class="toContentMain" title="甬商与晋商、徽商地域文化传承比较研究">甬商与晋商、徽商地域文化传承比较研究</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eebea2e621ed7.jsp" class="toContentMain" title="“徽学是中国传统文化的重要名片”">“徽学是中国传统文化的重要名片”</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eebe5a2061ebe.jsp" class="toContentMain" title="传统徽商给我们带来的启示">传统徽商给我们带来的启示</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eebdbdfa61e7a.jsp" class="toContentMain" title="传统文化与徽商心理变迁">传统文化与徽商心理变迁</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eebce05ba1e0a.jsp" class="toContentMain" title="徽商与中国商帮文化">徽商与中国商帮文化</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeb1a10181afc.jsp" class="toContentMain" title="兵学鼻祖">兵学鼻祖</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeb0467a81aa4.jsp" class="toContentMain" title="法家思想集大成者">法家思想集大成者</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeaf757391a8e.jsp" class="toContentMain" title="墨家创始人">墨家创始人</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeaebce4e1a6c.jsp" class="toContentMain" title="道家代表人物">道家代表人物</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeadfb5441a68.jsp" class="toContentMain" title="儒家代表人物">儒家代表人物</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeaca6ca21a55.jsp" class="toContentMain" title="“亚圣”">“亚圣”</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eea7bbcaa1a2f.jsp" class="toContentMain" title="晋商商业道德的内涵">晋商商业道德的内涵</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee6a457d515c1.jsp" class="toContentMain" title="孔子、孟子和荀子的富民思想简论">孔子、孟子和荀子的富民思想简论</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee67ec1e314d6.jsp" class="toContentMain" title="晋商精神与传统文化">晋商精神与传统文化</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee6484d151352.jsp" class="toContentMain" title="荀子的商业经济思想">荀子的商业经济思想</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee64503e01333.jsp" class="toContentMain" title="道家文化中管理思想的探索">道家文化中管理思想的探索</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_8.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_10.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_10.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：9/10 每页20条 </span>
</div>		
      </div>
      
  </div>
	
