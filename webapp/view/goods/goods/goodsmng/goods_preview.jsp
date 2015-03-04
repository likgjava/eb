<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<div id="container">
  <!--头部容器 开始-->
  <div class="header">
  	<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--头部容器 结束-->
	<!--主要内容 开始-->
  	<div id="sysContent" class="page">
		<input type="hidden" id="goodsId" value="${goods.objId}" />
		<input type="hidden" id="isMember" value="${isMember}" />
		
		<div id="conTitle">
			<div class="navCurrent">
				<a href="javascript:void(0);" onclick="searchByTitle('${goods.goodsClass.goodsClassCode }','${goods.goodsClass.objId }');return false;" >${goods.goodsClass.goodsClassName }</a>
				<a href="javascript:void(0);" onclick="searchByTitle('${goods.goodsClass.goodsClassCode }','${goods.goodsClass.objId }','${goods.goodsBrand.objId }');return false;" >${goods.goodsBrand.brandName }</a>
				商品详情
			</div>
		</div> 
		<div id="idHandle" class="handle"></div>
		<div id="conBody"><!--功能页内容-->
			<div class="imgAndInfo">
				<div id="showImg">
					<ul id="goodsImg">
						<li><img src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />" id="mainImg" /></li>
						<c:forEach var="image" items="${images}">
							<li><img src="<c:url value="AttachmentController.do?method=showImg&objId=${image.objId}" />" /></li>
						</c:forEach>				
					</ul>
				</div>
				<div id="imgViewer" class="izViewer"></div>
				<div id="showInfo" class="morePic">
					<ul class="meta">	
						<li class="detail-title"><span>商品编号：</span>${goods.goodsCode }</li>
						<li class="detail-title"><span>商品名称：</span><strong>${goods.productName }</strong></li>
						<li class="detail-title"><span>规格型号：</span>${goods.productCode }</li>
						<li class="detail-price"><span>参考价&nbsp;&nbsp;&nbsp;：</span><strong>￥<fmt:formatNumber value="${goods.referPrice}" pattern="#,##0.00#" /></strong>元 </li>
						
						<c:if test="${minPrtcPrice!=null&&maxPrtcPrice!=null}">
						<li class="detail-price hidden" id="maxtPrtcPriceLi">
							<span>协议价&nbsp;&nbsp;&nbsp;：</span>
							<strong><span id="maxtPrtcPrice">
							￥<fmt:formatNumber value="${minPrtcPrice}" pattern="#,##0.00#" />~<fmt:formatNumber value="${maxPrtcPrice}" pattern="#,##0.00#" />
							</span></strong>元
						</li>
						</c:if>
					</ul>
				    <div class="key">
					    <dl>
				   			<dt><em>评价总分：</em></dt>
				   			<dd class="totalScore">
				   						<ul class="rating-level">
				   								<li><a class="aa<fmt:formatNumber type="number" value="${goods.evalSum }" maxFractionDigits="0"/>-stars current-rating"  href="#"></a></li>
				   						</ul>
				   						<span id="stars2-tips" class="result">
				   							<fmt:formatNumber type="number" value="${goods.evalSum }" pattern="#0.0"/>分
				   						</span>
				   			</dd>
				   		</dl>
				    </div>
					<ul class="other">
						<li><span>计量单位：</span><span id="measureUnit">${goods.measureUnit }</span></li>
						<li><span>发布时间：</span><fmt:formatDate value="${goods.productDateIssued }" pattern="yyyy年MM月dd"/></li>										
						<li><span>商品产地：</span>${goods.madeIn }</li>
						<li><span>生产厂家：</span>${goods.factory }</li>
					</ul>
				</div>
				</div>
				<!-- Tab页 -->
				<div id="epsTabs">
				<ul>
					<li><a href="#baseParam" id="baseParamTab"><span>商品参数</span></a></li>
					<li><a href="#optionList" id="optionListTab"><span>选配信息</span></a></li>
					<li class="hidden"><a href="#goodsPriceList" id="goodsPriceListTab"><span>商品行情</span></a></li>
					<li><a href="#evaluateList" id="evaluateListTab"><span>评价信息</span></a></li>
				</ul>
				<div id="baseParam" class="formLayout">
					<div class="attributes" id="attributes"> 
						<dl class="attributes-list">
				   			<dt><em>商品属性：</em></dt>
				   			<dd><c:choose>
					   			<c:when test="${goods.special }"><span class="award_star_gold_3">特供商品</span></c:when>
					   			<c:otherwise><span class="award_star_delete">非特供商品</span></c:otherwise>
				   			</c:choose></dd>
				   			<dd><c:choose>
					   			<c:when test="${goods.isCustom }"><span class="ruby">自定义商品</span></c:when>
					   			<c:otherwise><span class="ruby_delete">非自定义商品</span></c:otherwise>
				   			</c:choose></dd>
				   			<dd><c:choose>
					   			<c:when test="${goods.isAccessory }"><span class="cog">零配件</span></c:when>
					   			<c:otherwise><span class="cog_delete">非零配件</span></c:otherwise>
				   			</c:choose></dd>
				   			<dd><c:choose>
					   			<c:when test="${goods.soleToSell }"><span class="coins">可单独出售</span></c:when>
					   			<c:otherwise><span class="coins_delete">不可单独出售</span></c:otherwise>
				   			</c:choose></dd>
				   		</dl>
						<ul class="attributes-list">
							<li> 自主创新认定编号：${goods.creationCode}<c:if test="${goods.creationCode == null}">无</c:if></li>
							<li> 环境标志产品编号：${goods.environmentLabel}<c:if test="${goods.environmentLabel == null}">无</c:if></li>
							<li> 节能产品编号编号：${goods.energySavingProductNo}<c:if test="${goods.energySavingProductNo == null}">无</c:if></li>
							<li> 含有密码技术的信息产品编号：${goods.cryptographyTechCode}<c:if test="${goods.cryptographyTechCode == null}">无</c:if></li>
						</ul>
		    		</div>
			    	<div>
			    		<h4><span>基本参数</span></h4>
			    		<table class="frontTableList">
			    			<c:choose>
			    				<c:when test="${goods.goodsParamSet != null && fn:length(goods.goodsParamSet) > 0}">
				    				<c:forEach var="goodsParam" items="${goods.goodsParamSet}">
										<c:if test="${goodsParam.goodsClassParam.isLeaf == false}">
											<tr><td colspan="2"><em>${goodsParam.goodsClassParam.paramName}</em></td>
												<c:forEach var="paramLeaf" items="${goods.goodsParamSet}">
													<c:if test="${paramLeaf.goodsClassParam.isLeaf == true && paramLeaf.goodsClassParam.parent.objId==goodsParam.goodsClassParam.objId && fn:length(paramLeaf.paramValue) > 0}">
														<tr>
															<td><label>${paramLeaf.paramName}<c:if test="${!empty paramLeaf.goodsClassParam.paramUnit}">(${paramLeaf.goodsClassParam.paramUnit})</c:if></label></td>
															<td >${paramLeaf.paramValue}</td>
														</tr>
													</c:if>
												</c:forEach>
											</tr>
										</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr><td>${goods.spec }</td></tr>
								</c:otherwise>
							</c:choose>
			    		</table>
			    	</div>
		    		<div>
		    			<h4><span>商品功能描述</span></h4>
		    			${goods.functionIntro }
		    		</div>
		    		<c:if test="${goods.opinion!=null}">
				    		<div>
				    			<h4><span>审核意见</span></h4>
				    			${goods.opinion}
				    		</div>
			    	</c:if>
				</div>
				<div id="optionList" class="formLayout">
					<table class="frontTableList" id="optionListTable">
						<thead>
							<tr>
								<th>商品参数</th>
								<th>选配内容</th>
								<th>规格型号描述</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="goodsParam" items="${goods.goodsParamSet}">
							<c:choose>
								<c:when test="${fn:length(goodsParam.goodsOptionalFittingSet) > 0}">
								<tr><td rowspan="${fn:length(goodsParam.goodsOptionalFittingSet) + 1}">${goodsParam.paramName}</td></tr>
								<c:forEach var="optinalFitting" items="${goodsParam.goodsOptionalFittingSet}">
									<c:if test="${optinalFitting.isUse != '02'}">
										<tr>
										<td>${optinalFitting.optionContent}</td>
										<td>${optinalFitting.specification}</td>
										</tr>
									</c:if>
								</c:forEach>
								</c:when>
							</c:choose>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 商品行情tab页 -->
				<div id="goodsPriceList"></div>
				<!-- 评价信息tab页 -->
				<div id="evaluateList"></div>
				
				</div>
		</div>
    </div>
    <!--主要内容 结束-->
		
	<!--底部容器 开始-->
	<div class="footer">
		<%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!--底部容器 结束-->
	<!--在线客服开始-->
	<%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
	<!--在线客服结束-->
</div>
<!--页面容器 结束-->
<div id="extraDiv">
  <!--扩展用容器，用于与内容无关的装饰性扩展-->
  <div id="extraDiv1"></div>
  <div id="extraDiv2"></div>
  <div id="extraDiv3"></div>
  <div id="extraDiv4"></div>
  <div id="extraDiv5"></div>
  <div id="extraDiv6"></div>
</div>
</body>
</html>

<script>
//点击品目标题
function searchByTitle(classCode,classId,brandId) {
	var param = "&goodsClassCode=" + classCode;
	
	if(brandId) { //品牌
		param += "&brandId=" + brandId;
	}
	if(classId) { //商品分类编码
		param += "&goodsClassId=" + classId;
	}
	window.open($('#initPath').val()+'/GoodsShowController.do?method=toGoodsList&rp=21&page=1&type=detail'+ param);
}

$(document).ready(function(){
	//选中顶部菜单
	changeTabsCss("goToGoods");

	//加载评价列表
	$("#evaluateListTab").click(function(){
		$("#evaluateList").loadPage($("#initPath").val()+"/view/goods/showgoods/goods_evaluate_list.jsp");
	})
	
	//加载行情列表
	if(common.isLogin(false)){
		//点击才触发 (为了解决浏览器内存溢出问题 )
		$("#goodsPriceListTab").click(function(){
			if($("#goodsPriceList").html()==""){//加载一次不再反复加载
				$("#goodsPriceList").loadPage($("#initPath").val()+"/GoodsPriceShowController.do?method=toGoodsPriceShowList&goodsId="+$("#goodsId").val()+"&isMember="+$("#isMember").val());
			}
		})
		$('#maxtPrtcPriceLi').removeClass("hidden");
		$('#goodsPriceListTab').parent().removeClass("hidden");
	}
	
	var imageZoom;
	if(imageZoom==null){
		imageZoom = new ImageZoom( "mainImg", "imgViewer", {
			mode: "handle", handle: "idHandle", scale: 20, delay: 0
		});
	}

	$('ul.#goodsImg').bxGallery({});//图片显示控件
	
	$('#epsTabs').tabs();//加载tabs
})
</script>