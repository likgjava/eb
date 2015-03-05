<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>广东政府采购将优先购买自主创新产品- 【阳光易购】</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<!--JS-->
<script type="text/javascript" src='/view/srplatform/portal/include/common.js'></script>
</head>

<body>

<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub" class="index3pa">
			<%@ include file="/view/staticpags/load/left_cms.jsp" %>
		</div>
		<div id="contentMain" class="index3pa">
			<div id="conTitle">
				<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813176735601319239e00619f4.jsp" title="广东政府采购将优先购买自主创新产品" class="cmsHref_self">广东政府采购将优先购买自主创新产品</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>广东政府采购将优先购买自主创新产品</h1>
					<div class="source">
						<span>发布时间：2011-08-04</span>
						<span>发布人：-经济参考报  </span>
					</div>
					<p><P>《广东省自主创新促进条例(草案)》近日提交广东省十一届人大常委会第二十七次会议审议。该草案规定各级财政部门在预算审批过程中，在采购项目指出已确定的情况下，应当优先安排采购自主创新产品、服务的预算。同时提出部分大型科学仪器应当向社会开放，实现共享。</P>
<P>在鼓励自主创新成果转化与产业化方面，草案规定国有企事业单位建立健全职务发明分配制度。被授予专利权的单位与发明人、设计人可以约定职务发明的报酬方式与数额。利用财政性资金设立的高等学校好科技研究开发机构以技术转让方式，将职务创新成果提供给他人实施的，可从技术转让所得的净收入中提取不低于20%、不超过70%，用于一次性奖励创新成果完成人和为创新成果转化作出重要贡献的人员。</P>
<P>对广东省行政区域内公民、法人或其他组织自主创新的产品、服务或广东省需要重点扶持的产品、服务，在性能、技术等指标能够满足政府采购需求的条件下，政府采购应当购买;首次投放市场的，政府采购应当率先购买。采购人在编制年度采购预算时应当同时编报采购自主创新产品、服务的预算。各级财政部门在预算审批过程中，在采购项目指出已确定的情况下，应当优先安排采购自主创新产品、服务的预算。自主创新产品与服务政府采购的具体管理办法由省政府另行制定。</P>
<P>地级以上市人民政府可以依法发起设立或者参与设立创业投资引导基金，引导社会资金流向创业投资企业，引导创业投资企业向成长前期的科技型企业投资。县级以上人民政府应当通过无偿资助、贷款贴息、补助资金、保费补贴和创业风险投资等方式，加大对自主创新成果转化与产业化的支持。</P>
<P>在创新型人才建设与服务方面，草案规定地级以上市政府应当定期制定本地区创新型人才发展规划和紧缺人才开发目录，建设创新型人才队伍。县级以上人民政府应当优先保证对创新型人才建设的财政投入，保障人才发展重大项目的实施。同时草案还鼓励企事业单位建立和完善岗位工资、绩效工资、课题工资、协议工资、年薪制和奖励股权期权等分配方式。</P>
<P>根据草案规划，各级财政用于科学技术经费的增长幅度，应当高于本级财政经常性收入的增长幅度。到2015年及其后，广东省研究与开发经费应当占地区生产总值的2.3%以上。</P>
</p>
				</div>
			</div>
		</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
</div>
</body>

</html>

<script>
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
</script>