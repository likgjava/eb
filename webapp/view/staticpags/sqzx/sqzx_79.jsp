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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e6f0340012e6f2bfb750016.jsp" class="toContentMain" title="国外乳企争相掘金中国">国外乳企争相掘金中国</a></td>
          <td style="text-align:right">2011-03-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a3123aa005d.jsp" class="toContentMain" title="iPhone5触控屏面板曝光 4寸无边框设计">iPhone5触控屏面板曝光 4寸无边框设计</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a2f7a5b005b.jsp" class="toContentMain" title="2月10大畅销手机排行 HTC包揽三甲">2月10大畅销手机排行 HTC包揽三甲</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a2cb6940058.jsp" class="toContentMain" title="美菱电器国内首推家电制造业“先行赔付”">美菱电器国内首推家电制造业“先行赔付”</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a2b9d550056.jsp" class="toContentMain" title="百思买退场：五星电器可能沦为战略过渡品">百思买退场：五星电器可能沦为战略过渡品</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a2a5f740054.jsp" class="toContentMain" title="博西建全球最大冰箱厂 未来5年对华投资超40亿">博西建全球最大冰箱厂 未来5年对华投资超40亿</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a282003004b.jsp" class="toContentMain" title="智能家居让生活更舒适">智能家居让生活更舒适</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a257c850049.jsp" class="toContentMain" title="地板业新规成功引导建材市场发展新思路">地板业新规成功引导建材市场发展新思路</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a24050c0047.jsp" class="toContentMain" title="十二五”将迎来防水涂料黄金发展期">十二五”将迎来防水涂料黄金发展期</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a22682e0045.jsp" class="toContentMain" title="儿童家具陷入材料与涂装“污染门”">儿童家具陷入材料与涂装“污染门”</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a1fdbe80043.jsp" class="toContentMain" title="千元租金出租房走俏">千元租金出租房走俏</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a1db98f0040.jsp" class="toContentMain" title="2011年度中国家居产业振兴峰会即将开幕">2011年度中国家居产业振兴峰会即将开幕</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e69ef5a012e6a1a5b41003d.jsp" class="toContentMain" title="14省区成重金属污染治理重点 中央拟投百亿">14省区成重金属污染治理重点 中央拟投百亿</a></td>
          <td style="text-align:right">2011-02-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5adc731d018a.jsp" class="toContentMain" title="百思买否认退出中国 经营不善折戟中国市场">百思买否认退出中国 经营不善折戟中国市场</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5ad9fdfe0188.jsp" class="toContentMain" title="华为公开信邀美国当局调查收购3Leaf">华为公开信邀美国当局调查收购3Leaf</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5ac9b43c016b.jsp" class="toContentMain" title="物业公司按身高为保安定薪？">物业公司按身高为保安定薪？</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5ac148490169.jsp" class="toContentMain" title="和利时迎来2011年水处理行业开门红">和利时迎来2011年水处理行业开门红</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5abfb6e20167.jsp" class="toContentMain" title="污水处理重燃资本热情">污水处理重燃资本热情</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5abb6be10165.jsp" class="toContentMain" title="我国的PET塑料市场发展现状">我国的PET塑料市场发展现状</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5ab71b000163.jsp" class="toContentMain" title="井冈山市采用塑料光纤率先打造“全光城”">井冈山市采用塑料光纤率先打造“全光城”</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_78.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_80.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：79/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
