<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>${goods.productName}- 【供应 采购 报价】</title>
<meta name="description" content="${goods.productName}最新价格，及${goods.productName}图片、${goods.productName}参数、${goods.productName}评论、${goods.productName}描述等信息，采购询价，尽在阳光易购。" />
<meta name="keywords" content="${goods.goodsBrand.brandName},${goods.productCode },${goods.productName},${goods.productName}报价,${goods.productName}采购" />

<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<div id="container">
	<input type="hidden" id="tabId" value="${tabId}"/>
	<input type="hidden" id="goodsId" value="${goods.objId}" />
	<input type="hidden" id="isMember" value="${isMember}" />

	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub"></div>
		<div id="contentMain" class="index2paL">
			<div id="conTitle">
				<div class="navCurrent">
					<a href="javascript:void(0);" onclick="searchByTitle('${goods.goodsClass.goodsClassCode }','${goods.goodsClass.objId }');return false;" >${goods.goodsClass.goodsClassName}</a>
					<a href="javascript:void(0);" onclick="searchByTitle('${goods.goodsClass.goodsClassCode }','${goods.goodsClass.objId }','${goods.goodsBrand.objId }');return false;" >${goods.goodsBrand.brandName}</a>
					商品详情
				</div>
			</div> 
			<div id="idHandle" class="handle"></div>
			<div id="conBody"><!--功能页内容-->
				<div class="imgAndInfo">
					<div id="showImg">
						<ul id="goodsImg">
							<li><img width="280px" height="280px" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${goods.picture}" id="mainImg"/></li>
							<c:forEach var="image" items="${images}" varStatus="status">
								<li><img id="addImg${status.index }" width="280px" height="280px" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${image.objId}"/></li>
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
							
							<li class="detail-title" style="vertical-align:middle;">
								<span>购买数量：</span><input name="drectQty" value="1" style="width:25px;"/>&nbsp;
								<button type="button" onclick="addToCartDirect('${goods.referPrice}','${goods.measureUnit }')" class="addCart"></button>
							</li>
							<li class="detail-title">
								<button type="button" onclick="common.addFavorites('${goods.objId }','${goods.productName }','01')" class="favBtn" height="25px">加入收藏</button>
								<button type="button" onclick="show_list.addAttention('${goods.objId }')" class="favBtn" height="25px">加入关注</button>
								<font color="red">已有(${attentionNumber})人关注</font>
							</li>
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
						<div  style="width:470px;height:30px;padding-top:40px;*padding-top:15px;padding-left:9px;">
							<!-- JiaThis Button BEGIN -->
							<div id="ckepop">
								<span class="jiathis_txt">分享到：</span>
								<a class="jiathis_button_qzone">QQ空间</a>
								<a class="jiathis_button_tsina">新浪微博</a>
								<a class="jiathis_button_renren">人人网</a>
								<a class="jiathis_button_kaixin001">开心网</a>
								<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
								<a class="jiathis_counter_style"></a>
								<div class="clear"></div>
							</div>
							<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
							<!-- JiaThis Button END -->
						</div>
					</div>
					</div>
					<!---商品图 结束--> <!-- Tab页 -->
					<div id="epsTabs">
					<ul>
						<li><a href="#baseParam" id="baseParamTab"><span>商品参数</span></a></li>
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
					<!-- 商品行情tab页 -->
					<div id="goodsPriceList"></div>
					<!-- 评价信息tab页 -->
					<div id="evaluateList"></div>
					
					</div>
				<!-- 评价from -->
				<div id="evaluateDiv" style="border: 1px solid #C4D9EF;"><%@ include file="/view/goods/showgoods/goods_evaluate_form.jsp"%></div>
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<jsp:include page="/GoodsShowController.do?method=getRecommendGoods">
				<jsp:param value="5" name="rp"/>
				<jsp:param value="1" name="page"/>
				<jsp:param value="${goods.goodsClass.goodsClassCode}" name="goodsClassCode"/>
				<jsp:param value="${goods.goodsBrand.objId}" name="brandId"/>
			</jsp:include>
		</div>
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

<script>
var goodsDetail = {}
var show_list={};
goodsDetail.oTable;
goodsDetail.currentZoomImgId = ''; //标记当前被放大图片的标签id

//直接加入购物车
addToCartDirect = function(referPrice,goodsUnit){
	var drectQty = $("input[name=drectQty]").val();
	if(isNaN(drectQty)||Number(drectQty)<=0){alert("数量必须为正整数！");return ;}
	var shoppingCartItem={
			goods:{objId:$('#goodsId').val()},
			goodsUnit:goodsUnit,
			goodsQty:$("input[name=drectQty]").val(),
			goodsTotal:referPrice,
			goodsPrice:referPrice,
			agreePrice:referPrice,
			marketPrice:referPrice
	}; 
	shoppingCartItem.cartGoodsOptions=[];
	//加入
	addInShoppingCart(shoppingCartItem);
	
	shoppingCartItem = null;//闭包内存泄露问题 by yucy
}
//加入
function addInShoppingCart(shoppingCartItem){
	//判断登陆
	if(common.isLogin(true,"请先登录再添加商品到购物车！")){
		$.getJSON($('#initPath').val()+'/ShoppingCartItemController.do?method=saveAppendGoodsToShoppingCart',{shoppingCartItemStr:JSON.stringify(shoppingCartItem)},function(json){
			if(json.failure){alert(json.result);return;}
			alert('添加到购物车成功！');
			//刷新购物车div
			shoppingCartItem = null;//闭包内存泄露问题 by yucy
			getCartInfo();
		});
	}
}

//加入收藏
common.addFavorites = function(favoriteId,favoriteName,favoriteType){
	//判断是否登录
	if(common.isLogin(true)){
		$.epsDialog({
	        title:'加入收藏',
	        width:400,
	        height:150,
	        url:$('#initPath').val()+'/FavoritesController.do?method=toFavoritesForm&favoriteId='+favoriteId+'&favoriteName='+encodeURIComponent(favoriteName)+'&favoriteType='+favoriteType
		});
	}
}

//加入关注
show_list.addAttention = function(goodsId){
	//判断是否登录
	if(common.isLogin(true,true)){
		$.getJSON($("#initPath").val()+"/AttentionPriceController.do?method=saveAttention",{"goodsId":goodsId},function(json){
			if(json.result){
				alert("您已经关注过该商品！");
			} else if(json.success){
				alert("关注成功！");
			}
		})
	}
}

//点击品目标题
function searchByTitle (classCode,classId,brandId) {
	var param = "&goodsClassCode=" + classCode;
	
	if(brandId) {  //品牌
		param += "&brandId=" + brandId;
	}
	if(classId) {  //商品分类编码
		param += "&goodsClassId=" + classId;
	}
	window.open($('#initPath').val()+'/GoodsShowController.do?method=toGoodsList&rp=21&page=1&type=detail'+ param);
}

$(document).ready(function(){
	//选中顶部菜单
	changeTabsCss("goToGoods");
	
	//购买数量，控制不能输入非数字
	$("input[name=drectQty]").inputFillter({type:'int'});

	//图片“放大镜”
	$('#goodsImg li img').mouseover(function(){
		var id = $(this).attr('id');
		if(goodsDetail.currentZoomImgId != id){
			var imageZoom = new ImageZoom(id, "imgViewer", {mode: "handle", handle: "idHandle", scale: 20, delay: 0});
			goodsDetail.currentZoomImgId = id;
		}
	});
	
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
	
	$('ul.#goodsImg').bxGallery({});//图片显示控件
	
	$('#epsTabs').tabs();//加载tabs
});
</script>