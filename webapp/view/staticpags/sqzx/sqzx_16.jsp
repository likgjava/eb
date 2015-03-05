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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a70983ca0bf5.jsp" class="toContentMain" title="我国陶瓷卫浴行业举步维艰的两大原因">我国陶瓷卫浴行业举步维艰的两大原因</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a708c3920bf4.jsp" class="toContentMain" title="五金家具越走越近 产业合成新新时代或来临">五金家具越走越近 产业合成新新时代或来临</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a705dba60bf1.jsp" class="toContentMain" title="机械行业面临多重困难 自主创新急需加强">机械行业面临多重困难 自主创新急需加强</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a7048e0b0bf0.jsp" class="toContentMain" title="聚乙烯市政工程成功应用于上海迪斯尼工程">聚乙烯市政工程成功应用于上海迪斯尼工程</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a702ad650bef.jsp" class="toContentMain" title="中国光伏发电能否“左右逢源”？">中国光伏发电能否“左右逢源”？</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a701f0af0bee.jsp" class="toContentMain" title="未来5年我国示波器市场规模将持续扩大">未来5年我国示波器市场规模将持续扩大</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a6ffbce90bec.jsp" class="toContentMain" title="市场进步：淘汰落后产能 建筑五金或看涨">市场进步：淘汰落后产能 建筑五金或看涨</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a6fdb65e0bea.jsp" class="toContentMain" title="2011上半年中国家电市场销售规模达6123亿">2011上半年中国家电市场销售规模达6123亿</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a6fb87f50be8.jsp" class="toContentMain" title="天然气大项目保障抚顺壁挂炉市场">天然气大项目保障抚顺壁挂炉市场</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a6fad2450be7.jsp" class="toContentMain" title="家电产业链压力上传导 配件企业日子难过">家电产业链压力上传导 配件企业日子难过</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a6de13bc0bd0.jsp" class="toContentMain" title="调查：近六成供应商计划短期内启动品牌策略">调查：近六成供应商计划短期内启动品牌策略</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131a6dc5da20bce.jsp" class="toContentMain" title="木门业市场需求发展空间大 是企业优势">木门业市场需求发展空间大 是企业优势</a></td>
          <td style="text-align:right">2011-08-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c01319792afe20346.jsp" class="toContentMain" title="红木家具融入现代风格 年轻化市场需求渐涨">红木家具融入现代风格 年轻化市场需求渐涨</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131978f3c0c0345.jsp" class="toContentMain" title="中国服装业商机无限 服企如何掘金？">中国服装业商机无限 服企如何掘金？</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131978db9ff0344.jsp" class="toContentMain" title="受益3D电视 上半年等离子需求量同比增45%">受益3D电视 上半年等离子需求量同比增45%</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131978c610e0343.jsp" class="toContentMain" title="中国纺织企业利润增长有持续回落表现">中国纺织企业利润增长有持续回落表现</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c01319788194b0342.jsp" class="toContentMain" title="全球照明市场预计将在2015年达到1150亿美金">全球照明市场预计将在2015年达到1150亿美金</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c01319786453e033e.jsp" class="toContentMain" title="欧盟玩具安全新指令 生产商如何应对“大考”">欧盟玩具安全新指令 生产商如何应对“大考”</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c013197855c7f033d.jsp" class="toContentMain" title="光伏上网电价出炉 价格倒逼传至上游">光伏上网电价出炉 价格倒逼传至上游</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c01319783f1f4033b.jsp" class="toContentMain" title="太阳能电池行业低迷带来整合机会">太阳能电池行业低迷带来整合机会</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_15.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_17.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：16/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
