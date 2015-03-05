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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee064a8f40893.jsp" class="toContentMain" title="衣柜行业营销策略之为买家提供专业的建议">衣柜行业营销策略之为买家提供专业的建议</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edc7b313a06f1.jsp" class="toContentMain" title="绿色低碳塑料已成为行业共识">绿色低碳塑料已成为行业共识</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edc59ad420699.jsp" class="toContentMain" title="王文京：最低中标价法不适用软件采购">王文京：最低中标价法不适用软件采购</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edc5685ff0693.jsp" class="toContentMain" title="印度尼西亚采购1.2万吨中国重钙">印度尼西亚采购1.2万吨中国重钙</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edc5427f60691.jsp" class="toContentMain" title="采购中央空调应关注专业性">采购中央空调应关注专业性</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edc3d6e0e05bb.jsp" class="toContentMain" title="商务部透露网上交易管理办法有望年底发布">商务部透露网上交易管理办法有望年底发布</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edc200f9e04ea.jsp" class="toContentMain" title="生产砂石骨料将成为水泥企业转型首选">生产砂石骨料将成为水泥企业转型首选</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edc1e613504d5.jsp" class="toContentMain" title="橱柜新材掀市场热潮 浓缩家居装修空间">橱柜新材掀市场热潮 浓缩家居装修空间</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edb2ea2a4008f.jsp" class="toContentMain" title="整体橱柜市场面临新一轮洗牌　市场空白有待填补">整体橱柜市场面临新一轮洗牌　市场空白有待填补</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edb140e76004d.jsp" class="toContentMain" title="“能效标识”亦可是购买小家电的参考标准">“能效标识”亦可是购买小家电的参考标准</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012edb0431110031.jsp" class="toContentMain" title="消费者谨慎对待无国家标准净水机">消费者谨慎对待无国家标准净水机</a></td>
          <td style="text-align:right">2011-03-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed73e766700fb.jsp" class="toContentMain" title="日本订单猛增 小企业接受"日单"考验">日本订单猛增 小企业接受"日单"考验</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed739baa700ea.jsp" class="toContentMain" title="主流运动品牌纷纷表露涨价意愿">主流运动品牌纷纷表露涨价意愿</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed73795f700dd.jsp" class="toContentMain" title="中国将解决部分奢侈品国内价高于国外价的问题">中国将解决部分奢侈品国内价高于国外价的问题</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed73643c500db.jsp" class="toContentMain" title="银行卡将迎来“芯”时代">银行卡将迎来“芯”时代</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed717729400c8.jsp" class="toContentMain" title="湖北省484种基本药物统一采购配送 5月底全覆盖">湖北省484种基本药物统一采购配送 5月底全覆盖</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed7132eaa00bc.jsp" class="toContentMain" title="中国采购改弦易辙　台湾可望独占市场鳌头">中国采购改弦易辙　台湾可望独占市场鳌头</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed70c69cc009d.jsp" class="toContentMain" title="特别提示：全球采购更要重视疫情风险">特别提示：全球采购更要重视疫情风险</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed709828c0094.jsp" class="toContentMain" title="爱普生80款产品再次入选第九期节能产品政府采购清单">爱普生80款产品再次入选第九期节能产品政府采购清单</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed7065cc1008d.jsp" class="toContentMain" title="７件失效的政府采购规章和规范性文件被废止">７件失效的政府采购规章和规范性文件被废止</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_70.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_72.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：71/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
