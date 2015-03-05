<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>${voteTemplate.templateName }</title>
		<c:import url="/view/srplatform/common/init.jsp">
			<c:param name="isLastLoadPage">
				true
			</c:param>
		</c:import>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/smallscale/vote/vino/style.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/vote/vino/vino_index.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/portal/include/common.js"></script>
		<script type="text/javascript">
			var template_unfurl = {};
			//显示采购人或供应商详情
			template_unfurl.showOrgDetail = function(orgInfoId, buyerId, supplierId) {
				if(orgInfoId==null || orgInfoId==""){return ;}
				if(buyerId!=null && buyerId!="" && buyerId!="undefined"){
					common.geToBuyerDetail(buyerId);
				}else{
					common.goToOrgShop(orgInfoId);
				}
			}
		</script>
	</head>
	<body>
		<div class="header hidden">
			<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
		</div>
		<input type="hidden" id="userId" value="${user.objId}"/>
		<input type="hidden" id="userName" value="${user.usName}"/>
		<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
		<input type="hidden" id="templateObjCode" value="${voteTemplate.templateCode}"/>
		<input type="hidden" id="templateId" value="${voteTemplate.objId}"/>
		<input type="hidden" id="expertVoteFactor" value="${param.expertVoteFactor}"/>

		<div id="banner"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/banner.jpg" /></div>
		<div id="nav">
			<div class="nav_111"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_01.jpg" /></div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoIndex()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_02.jpg" border="0" /></a>
			</div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoHdjs()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_03.jpg" border="0" /></a>
			</div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoHdlc()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_04.jpg" border="0" /></a>
			</div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoJxsz()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_05.jpg" border="0" /></a>
			</div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoPszj()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_06.jpg" border="0" /></a>
			</div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoGktp()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_07.jpg" border="0" /></a>
			</div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoMtlm()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_08.jpg" border="0" /></a>
			</div>
			<div class="nav_111">
				<a href="javascript:void(0);" onclick="vinoIndex.toVinoBmfs()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_09.jpg" border="0" /></a>
			</div>
			<div class="nav_111"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_10.jpg" /></div>
		</div>
	
		<div id="conBody">
			<%@ include file="/view/smallscale/vote/vino/vino_default.jsp" %>
		</div>

		<div id="bottom">
			关于我们 | 网站导航 | 会员服务 | 广告服务 | 网上调查 联系我们 | 版权所有 <br/>
			Copyright&copy;2007中国名企排行网 Rrights Reserved京ICP证070104号 北京市公安局海淀分局备案编号11010818900 <br/>
			本站网络实名/通用网址：“中国名企排行网”京ICP备09089782号<br/>
			<script src='http://hm.baidu.com/h.js?443c561ffd3d09ee58ae95993ef75fc3' type='text/javascript'></script>
		</div>
	</body>
</html>