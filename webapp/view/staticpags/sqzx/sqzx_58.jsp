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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f666e15fc0446.jsp" class="toContentMain" title="人民币升值降低成本 内地采购商去年28今年3019">人民币升值降低成本 内地采购商去年28今年3019</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f666c06650433.jsp" class="toContentMain" title="华南采购经理人齐聚首 共商最优采购策略">华南采购经理人齐聚首 共商最优采购策略</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6669787e0428.jsp" class="toContentMain" title="采购商大减 佛山陶瓷价格优势不再">采购商大减 佛山陶瓷价格优势不再</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6659aad80409.jsp" class="toContentMain" title="赞比亚新采购法案加强对本国企业的保护">赞比亚新采购法案加强对本国企业的保护</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f665626000407.jsp" class="toContentMain" title="3LCD、DLP两大技术解析 三星家用投影采购宝典">3LCD、DLP两大技术解析 三星家用投影采购宝典</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6652c1db0401.jsp" class="toContentMain" title="榕赴上海宣传“5.18” 上百企业确定参加采购洽谈">榕赴上海宣传“5.18” 上百企业确定参加采购洽谈</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f664fb52203fc.jsp" class="toContentMain" title="邀请国际买家“来家”采购">邀请国际买家“来家”采购</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f664e32e603fa.jsp" class="toContentMain" title="广交会日本采购商达1239人 比上届增16.89%">广交会日本采购商达1239人 比上届增16.89%</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f664ce0d503f8.jsp" class="toContentMain" title="给无经验者的葡萄酒采购单">给无经验者的葡萄酒采购单</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f664a47bf03f4.jsp" class="toContentMain" title="日本东部再生铝合金生产商减少废铝采购">日本东部再生铝合金生产商减少废铝采购</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f66490ee303f0.jsp" class="toContentMain" title="地震减产日本面板 家电企业中国台湾采购">地震减产日本面板 家电企业中国台湾采购</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6647044c037b.jsp" class="toContentMain" title="采购商突破7万人刷新纪录 欧美采购商有增加">采购商突破7万人刷新纪录 欧美采购商有增加</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6645a0ee0354.jsp" class="toContentMain" title="天灾人祸冲击广交会 采购商或同比下降5%">天灾人祸冲击广交会 采购商或同比下降5%</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6643cab00350.jsp" class="toContentMain" title="河北冀州纺企采购疲软 地产棉价再下降">河北冀州纺企采购疲软 地产棉价再下降</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57f34c3c15a3.jsp" class="toContentMain" title="平板电脑强势来袭 传统PC首季销量下滑">平板电脑强势来袭 传统PC首季销量下滑</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57f237af15a1.jsp" class="toContentMain" title="线缆行业与国际接轨 增强市场竞争力是必然">线缆行业与国际接轨 增强市场竞争力是必然</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57ed7060159f.jsp" class="toContentMain" title="E0 级板材：有效防止居室头号杀手甲醛污染">E0 级板材：有效防止居室头号杀手甲醛污染</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57e9d4d0159d.jsp" class="toContentMain" title="越南增购中国电力">越南增购中国电力</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57e41942159b.jsp" class="toContentMain" title="我国机床出口大幅增长 产品结构有待进一步优化">我国机床出口大幅增长 产品结构有待进一步优化</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57e1730f1599.jsp" class="toContentMain" title="法国首次向中国订购18万吨级散货船">法国首次向中国订购18万吨级散货船</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_57.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_59.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：58/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
