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

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />
<style type="text/css">
.selectCategory { background: none repeat scroll 0 0 #F2F2F2; border: 1px solid #CCCCCC; height: 25px; margin-bottom: 4px; overflow: hidden; position: relative; }
.selectCategory .choosed { float: left; line-height: 21px; margin-top: 2px; width: 610px; }
.selectCategory .choosed h3 { display: inline; float: left; font-size: 12px; }
.selectCategory .choosed a { background: none repeat scroll 0 0 #FFFFFF; border: 1px solid #E6E6E6; color: #404040; display: inline; float: left; line-height: 19px; margin-left: 5px; padding: 0 20px 0 5px; position: relative; white-space: nowrap; }
.selectCategory .choosed b { background: url("view/resource/skin/skin07/img/delete.gif") no-repeat scroll 0 0 transparent; }
.selectCategory .choosed b { background-position: 0 0px; height: 18px; position: absolute; right: 2px; top: 1px; width: 21px; }
</style>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/purchaserequire/showrequirement/show_requirement_list.js"></script>
</head>
<body>
<div id="container">
	<input type="hidden" id="districtLevel" value="${districtLevel}"/>
	<input type="hidden" id="districtId" value="${districtId}"/>
	<input type="hidden" id="goodsClassId" value="${goodsClassId}"/>
	<input type="hidden" id="categoryCode" value="${categoryCode}"/>

	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub" class="hidden"></div>
		<div id="contentMain" class="index2paL">
		 	<div id="conTitle">
		      	<div class="navCurrent">采购需求列表</div>
		    </div>
		    <!--功能页内容-->
		    <div id="conBody">
		    	<!--已选择采购品目 开始 -->
		    	<c:if test="${purCategoryName != null}">
		    	<div class="selectCategory">
		    		<div class="choosed">
						<h3>您已选择采购品目: </h3>
						<a title="${purCategoryName}" href="<%=request.getContextPath()%>/RequirementShowController.do?method=toRequirementList&rp=21&page=1&districtLevel=1">${purCategoryName}<b></b></a>
					</div>
				</div>
				</c:if>
				<!--已选择采购品目 结束-->
		    	<!-- 搜索条件 开始 -->
				<div id="ListSubCategory">
				
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>按商品类别选择：</div>
						<ul class="CategoryListTableLevel1 expand" id="moreProp1">
							<div id="propDisp1">
								<c:if test="${fn:length(categoryList) == 0}">
									<li>没有检索到相关商品类别数据！</li>
								</c:if>
								<c:if test="${fn:length(categoryList) > 0}">
									<li><a href="#supplierLink" name="-1" >全部商品类别</a></li>
									<c:forEach var="category" items="${categoryList}">
										<li><a title="${category[2]}" href="#supplierLink" name="${category[0]}"<c:if test="${categoryCode == category[0] }"> class="strong" </c:if>>
										<c:choose>
										<c:when test="${fn:length(category[2]) > 12}">${fn:substring(category[2],0,12)}... </c:when>
										<c:otherwise>${category[2]}</c:otherwise>
										</c:choose>
										</a><i>(${category[3]})</i></li>
									</c:forEach>
								</c:if>
							</div>
						</ul>
						<c:if test="${fn:length(categoryList) > 16}">
							<div class="expandOpen">
								<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp1','moreProp1','类别');return false;">
									<span class="sysicon siDownGray" id="expandProp1">显示全部类别</span>
								</a>
							</div>
						</c:if>
					</div>
					
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>按所在地选择：</div>
						<ul class="CategoryListTableLevel1 expand" id="moreProp2">
							<div id="propDisp2">
								<c:if test="${fn:length(districtList) == 0}">
									<li>没有检索到供应商所在地数据！</li>
								</c:if>
								<c:if test="${fn:length(districtList) > 0}">
									<li><a  name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict('-1');return false;">全部地区</a></li>
									<c:forEach var="district" items="${districtList}">
										<li><a name="${district[0]}" href="javascript:void(0);" onclick="show_list.changeDistrict('${district[0]}');return false;"
											<c:if test="${districtId == district[0] }"> class="strong" </c:if>
											>${district[1]}</a><i>(${district[2]})</i></li>
									</c:forEach>
								</c:if>
							</div>
						</ul>
						<c:if test="${fn:length(districtList) > 16}">
							<div class="expandOpen">
								<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp2','moreProp2','地区');return false;">
									<span class="sysicon siDownGray" id="expandProp2">显示全部地区</span>
								</a>
							</div>
						</c:if>
					</div>
				
					<div class="SubCategoryBox">
					<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>搜索条件：</div>
					<form id="bulletinFilter">
		   			<table style="width: 100%;">
						<tbody>					
							<tr>						
								<th>关键字：</th>
								<td><input type="text" name="keyWord" value="${keyWord}" id="keyWord"/></td>
								<th>采购预算（元）：</th>
								<td><input id="spurchaseBudget" style="width:40px" value=""/> 至 <input id="epurchaseBudget" style="width:40px" value=""/></td>
								<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData('firstPage')"><span>搜索</span></button></td>
								<td><a href="javascript:void(0);" id="publicRequ" style="background:url(view/resource/skin/sysicon/16/page_white_paint.png) no-repeat;margin-left:10px;padding-left:18px;height:18px;display:block;">我也要发布采购需求</a></td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
				</div>
				<!-- 搜索条件 结束 -->
				
		    	<!--排序方式 开始-->
			    <div class="conrank">
					<ul>
						<li class="displayBy"><em>排序方式：</em>
							<span class="supplierSort sysicon" id="createTimeSort" ><a href="javascript:void(0);">创建时间</a></span>
						</li>
						<li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个采购需求</li>
					</ul>
			    </div>
				<!--排序方式 结束-->
				<!--产品信息 开始--> 
				<div id="showSuppListAndPic">
					<%@ include file="/view/agreement/purchaserequire/showrequirement/requirement_list_div.jsp" %>
				</div>
				<!--产品信息 结束-->
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<span id="otherDiv">
				<%@ include file="/view/pubservice/common/customer_service_div.jsp" %>
			</span>
			<span id="reconmendDiv">			
				<jsp:include page="/ProjectShowController.do?method=toRecommendProjectSuppView&rp=5&page=1"></jsp:include>
			</span>
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