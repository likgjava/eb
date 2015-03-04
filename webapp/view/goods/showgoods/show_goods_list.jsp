<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title><c:if test="${keyWord!=null&&keyWord!=''}">【${keyWord}】</c:if><c:if test="${goodsClass!=null}">【${goodsClass.goodsClassName}】</c:if>- 商品库 - 阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/showgoods/show_goods_list.js"></script>
</head>
<body>
<div id="container">
	<input type="hidden" id="goodsClassCode" value="${goodsClass.goodsClassCode}"/>
	<input type="hidden" id="style" value="list"/>

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
		      		<a href="javascript:void(0);" onclick="show_list.searchByCategory('${goodsClass.objId}','${goodsClass.goodsClassCode}');return false;"> ${goodsClass.goodsClassName} </a>
		      		<a href="<%=request.getContextPath()%>/IndexViewController.do?method=getGoodsClassForSortHasGoods">选择商品分类</a>
		      	</div>
		    </div>
		    <div id="conBody"><!--功能页内容-->
		    	<!-- 搜索条件 开始 -->
				<div id="ListSubCategory">
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="<%=request.getContextPath()%>/view/resource/skin/sysicon/16/bullet_go.png"/>按分类选择：</div>
						<ul class="CategoryListTableLevel1 expand" id="moreProp1">
							<div id="propDisp1">
								<c:if test="${fn:length(goodsClassList) == 0}">
									<li>没有检索到相关产品分类数据！</li>
								</c:if>
								<c:if test="${fn:length(goodsClassList) > 0}">
									<li><a href="javascript:void(0);" name="-1" >全部分类</a></li>
									<c:forEach var="goodsClass" items="${goodsClassList}" > 
										<li><a href="javascript:void(0);" name="${goodsClass[0]}" 
											<c:if test="${goodsClassId == goodsClass[0] }"> class="strong" </c:if>
										>
										${goodsClass[1]}</a><i>(${goodsClass[2]})</i></li>
									</c:forEach>
								</c:if>
							</div>
						</ul>
						<c:if test="${fn:length(goodsClassList) > 15}">
							<div class="expandOpen">
								<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp1','moreProp1','分类');return false;">
									<span class="sysicon siDownGray" id="expandProp1">显示全部分类</span>
								</a>
							</div>
						</c:if>
					</div>
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="<%=request.getContextPath()%>/view/resource/skin/sysicon/16/bullet_go.png"/>按品牌选择：</div>
						<ul class="CategoryListTableLevel1 expand" id="moreProp2">
							<div id="propDisp2">
								<c:if test="${fn:length(goodsBrandList) == 0}">
									<li>没有检索到相关品牌数据！</li>
								</c:if>
								<c:if test="${fn:length(goodsBrandList) > 0}">
									<li><a  name="-1" href="javascript:void(0);" onclick="show_list.changeBrand('-1');return false;">全部品牌</a></li>
									<c:forEach var="brand" items="${goodsBrandList}" > 
										<li><a name="${brand[0]}" href="javascript:void(0);" onclick="show_list.changeBrand('${brand[0]}');return false;"
											<c:if test="${brandId == brand[0] }"> class="strong" </c:if>
											>
										${brand[1]}</a><i>(${brand[2]})</i></li>
									</c:forEach>
								</c:if>
							</div>
						</ul>
						<c:if test="${fn:length(goodsBrandList) > 16}">
							<div class="expandOpen">
								<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp2','moreProp2','品牌');return false;">
									<span class="sysicon siDownGray" id="expandProp2">显示全部品牌</span>
								</a>
							</div>
						</c:if>
					</div>
					<!--详细搜索条件 开始 -->
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="<%=request.getContextPath()%>/view/resource/skin/sysicon/16/bullet_go.png"/>更多筛选条件：</div>
						<form id="goodsFilter">
		    			<table style="width:100%">
							<tbody>
								<tr>
									<th>基本属性：</th>
									<td><input type="checkbox" name="isCustom"/><label> 自定义商品</label></td>
									<td><input type="checkbox" name="special"/><label> 特供商品</label></td>
									<td><input type="checkbox" name="soleToSell"/><label> 可单独出售</label></td>
									<td><input type="checkbox" name="isAccessory"/><label> 零配件</label></td>
								</tr>
								<tr>
									<th>扩展属性：</th>
									<td><input type="checkbox" name="creationCode"/><label> 自主创新产品</label></td>
									<td><input type="checkbox" name="energySavingProductNo"/><label> 节能产品</label></td>
									<td><input type="checkbox" name="environmentLabel"/><label> 环境标志产品</label></td>
									<td><input type="checkbox" name="cryptographyTechCode"/><label> 含有密码技术的信息产品</label></td>
								</tr>
								<tr>
									<th>关键字：</th>
									<td><input id="keyWord" value="${keyWord}"/></td>
									<th>价格区间（元）：</th>
									<td><input id="sprice" style="width:40px"/> 至 <input id="eprice" style="width:40px"/></td>
									<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData('firstPage')"><span>确定</span></button></td>
								</tr>
							</tbody>
						</table>
						</form>
					</div>
					<!--详细搜索条件 结束 -->
				</div>
				<!-- 搜索条件 结束 -->
				
		    	<!--排序方式 开始-->
			    <div class="conrank">
			      <ul>
			        <li class="displayBy"><a id="showGoodsList" href="javascript:void(0);"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/view_mode_10a.gif" title="列表显示" />&nbsp;列表</a>
			        					<a id="showGoodsPic" href="javascript:void(0);"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/view_mode_10a1.gif" title="大图显示" />&nbsp;大图</a> </li>
			        <li class="shortBy"><em>排序：</em><span class="goodsSort sysicon" name="evalSum" id="evalSort" ><a href="javascript:void(0);">评价</a></span>
			        								<span class="goodsSort sysicon" name="referPrice" id="priceSort" ><a href="javascript:void(0);">价格</a></span>
			        								<span class="goodsSort sysicon" name="productDateIssued" id="timeSort" ><a href="javascript:void(0);">上架时间</a></span> </li>
			        <li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;件商品</li>
			      </ul>
			    </div>
				<!--排序方式 结束-->
				
				<!--产品信息 开始--> 
				<div id="showGoodsListAndPic">
					<%@ include file="/view/goods/showgoods/goods_list_div_l.jsp" %>
				</div>
				<!--产品信息 结束-->
				<!-- 商品比较 --> 
				<div id="goodsCompare" class="compareGoodsTips hidden">
				  	<h4><span>商品对比</span></h4>
				  	<ul></ul>
		  			<div class="conOperation center"><button id="startGoodsCompare"><span>开始比较</span></button><button onclick="show_list.removeCompare()"><span>取消对比</span></button></div>
				</div>
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<jsp:include page="/GoodsShowController.do?method=getRecommendGoods">
				<jsp:param value="5" name="rp"/>
				<jsp:param value="1" name="page"/>
				<jsp:param value="${param.goodsClassCode}" name="goodsClassCode"/>
				<jsp:param value="${param.keyWord}" name="keyWord"/>
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