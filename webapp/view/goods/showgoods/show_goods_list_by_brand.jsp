<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${brand.brandName}-阳光易购电子采购与招标平台</title>
<meta name="description" content="阳光易购${brand.brandName}品牌展示区提供${brand.brandName}品牌各类型产品购买、报价、参数、描述等信息。" />
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/showgoods/show_goods_list_by_brand.js"></script>
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
		<input type="hidden" id="brandId" value="${param.brandId}" />
		<input type="hidden" id="style" value="list" />
		
		<div id="contentSub" class="index3pa">
			<div id="otherDiv">
				<%@ include file="/view/pubservice/common/customer_service_div.jsp" %>
			</div>
			<div class="firstCol">
				<%@ include file="/view/goods/showgoods/show_goods_list_by_class.jsp" %>
			</div>
		</div>
		<div id="contentMain" class="index3pa">
		 	<div id="conTitle">
		      	<div class="navCurrent">品牌展示</div>
		    </div>
		    <div id="conBody">
		    	<!-- 品牌介绍 -->
		    	<div class="item-list-s6">
		            <dl>
		                <dt><img alt="${brand.brandName}" src="<c:url value="AttachmentController.do?method=showImg&objId=${brand.mainLogo}&fileNameSuffix=_100*75" />" /></dt>
		                <dd> 
		                	<div><span style="font: 30px '黑体','宋体',serif; color: #ff0000;">${brand.brandName}</span></div>
		                    <div>
		                    	<span title="${brand.brandDesc}">
		                    	<c:choose>
		  							<c:when test="${fn:length(brand.brandDesc) > 100}">${fn:substring(brand.brandDesc,0,100)}…</c:when>
		  							<c:otherwise>${brand.brandDesc}</c:otherwise>
		  						</c:choose>
		                    	</span>
		                    </div>
		                </dd>
		            </dl>
		            <div class="list">
		            	<%@ include file="/view/goods/showgoods/show_recomment_goods_by_brand.jsp" %>
		            </div>
		        </div>
		    	<!-- end 品牌介绍 -->
		    	
		    	<!-- 搜索条件 开始 -->
				<div id="ListSubCategory">
					<!--详细搜索条件 开始 -->
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png" />筛选条件：</div>
						<form id="goodsFilter">
		    			<table style="width:100%">
							<tbody>
								<tr>
									<th>基本属性：</th>
									<td><input type="checkbox" name="isCustom" /><label> 自定义商品</label></td>
									<td><input type="checkbox" name="special" /><label> 特供商品</label></td>
									<td><input type="checkbox" name="soleToSell" /><label> 可单独出售</label></td>
									<td><input type="checkbox" name="isAccessory" /><label> 零配件</label></td>
								</tr>
								<tr>
									<th>扩展属性：</th>
									<td><input type="checkbox" name="creationCode" /><label> 自主创新产品</label></td>
									<td><input type="checkbox" name="energySavingProductNo" /><label> 节能产品</label></td>
									<td><input type="checkbox" name="environmentLabel" /><label> 环境标志产品</label></td>
									<td><input type="checkbox" name="cryptographyTechCode" /><label> 含有密码技术的信息产品</label></td>
								</tr>
								<tr>
									<th>关键字：</th>
									<td><input id="keyWord" value="${keyWord}" /></td>
									<th>价格区间（元）：</th>
									<td><input id="sprice" style="width:40px" /> 至 <input id="eprice" style="width:40px" /></td>
									<td class="operationBtnDiv l"><button type="button" id="sure"><span>确定</span></button></td>
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
		
		<div id="contentSupp" class="index3pa hidden"></div>
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