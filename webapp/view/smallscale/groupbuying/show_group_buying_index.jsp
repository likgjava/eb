<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/>
<title>精品商城 - 阳光易购电子采购与招标平台</title>
<meta name="description" content="是阳光易购全力打造的B2C在线购物商城（Business to Customer）,提供时尚百货,数码,服装,家电,家纺,家居, 玩具,数字卡,图书，音像,箱包，皮具,农林特产,户外休闲等数万种特价商品，为您提供高质量的购物体验。" />
<meta name="keywords" content="礼品，工艺品，食品、饮料，数码，电脑，服装，服饰，家居用品，家用电器，通信产品，医药保健，运动休闲" />
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/smallscale/groupbuying/show_group_buying_index.js'></script>
</head>
<body>
<div id="container">
  <!-- 头部开始 -->
  <div class="header">
	<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/groupbuying.css"/>
  <!-- 头部结束-->
  <!--主要内容 开始-->
  <div id="sysContent" class="page">
  	<c:forEach var="groupBuyingList" items="${groupBuyingListList}" varStatus="status">
	<div class="jpjc jpjc_zi01">${groupBuyingClassList[status.index].name}</div>
	<div class="jpjc_cp">
		<c:forEach var="groupBuying" items="${groupBuyingList}">
			<div class="jc_aa">
				<div class="jc_chanpin">
					<div class='jc_jiage<c:if test="${groupBuying.maxNumber != null}">2</c:if>'>
						<span class="jc_shichang">市场价<br /><fmt:formatNumber value="${groupBuying.marketPrice}" pattern="#,##0.00#" /></span>
						<span class="jc_tuangou"><fmt:formatNumber value="${groupBuying.groupPrice}" pattern="#,##0.00#" /></span>
					</div>
					<div class="jc_img">
						<a title="${groupBuying.desc}" href="javascript:void(0);" onclick="ShowGroupBuyingIndex.toGroupBuyerFormView('${groupBuying.objId}');"><img style="width: 258px; height: 175px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${groupBuying.picture}&fileNameSuffix=_320*320" />" border="0" /></a>
					</div>
				</div>
				<div class="jc_wenzi">
					<a href="javascript:void(0);" title="${groupBuying.name}" onclick="ShowGroupBuyingIndex.toGroupBuyerFormView('${groupBuying.objId}');">
						<c:choose>
  							<c:when test="${fn:length(groupBuying.name) > 22}">${fn:substring(groupBuying.name,0,21)}…</c:when>
  							<c:otherwise>${groupBuying.name}</c:otherwise>
  						</c:choose>
					</a>
				</div>
				<div class="jc_sub"><input type="button" onclick="ShowGroupBuyingIndex.toGroupBuyerFormView('${groupBuying.objId}');" class='jc_subm<c:if test="${groupBuying.maxNumber != null}">2</c:if>' /></div>
			</div>
		</c:forEach>
		<div style="clear:both;"></div>
	</div>
	</c:forEach>
  </div>
  <!--主要内容 结束-->
<!-- 脚开始 -->
<div class="footer">
	<%@ include file="/view/srplatform/portal/include/foot.jsp" %>
</div>
<!-- 脚结束 -->
<!--在线客服开始-->
<%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
<!--在线客服结束-->
</div>
</body>
</html>