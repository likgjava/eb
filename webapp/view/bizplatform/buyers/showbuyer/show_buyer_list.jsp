<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title> 采购人库 - 阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/buyers/showbuyer/show_buyer_list.js"></script>
</head>
<body>
<div id="container">
	<input type="hidden" id="districtLevel" value="${districtLevel}"/>
	<input type="hidden" id="districtId" value="${districtId}"/>

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
		      	<div class="navCurrent">采购人展示</div>
		    </div>
		    <div id="conBody"><!--功能页内容-->
		    	<!-- 搜索条件 开始 -->
				<div id="ListSubCategory">
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png" />按企业性质选择：</div>
						<ul class="CategoryListTableLevel1">
							<div id="propDisp1">
								<c:if test="${fn:length(unitTypeList) == 0}">
									<li>没有检索到采购人企业性质数据！</li>
								</c:if>
								<c:if test="${fn:length(unitTypeList) > 0}">
									<li><a href="javascript:void(0);" name="-1" >全部企业性质</a></li>
									<c:forEach var="type" items="${unitTypeList}">
										<li><a href="javascript:void(0);" name="${type[0]}" 
												<c:if test="${unitType == type[0] }">class="strong" </c:if>
											>${type[1]}</a><i>(${type[2]})</i>
										</li>
									</c:forEach>
								</c:if>
							</div>
						</ul>
					</div>
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png" />按所在地选择：</div>
						<ul class="CategoryListTableLevel1 expand" id="moreProp2">
							<div id="propDisp2">
								<c:if test="${fn:length(districtList) == 0}">
									<li>没有检索到采购人所在地数据！</li>
								</c:if>
								<c:if test="${fn:length(districtList) > 0}">
									<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict('-1');return false;">全部地区</a></li>
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
				</div>
				<!-- 搜索条件 结束 -->
				
				<!--详细搜索条件 开始 -->
				<div class="SubCategoryBox">
					<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png" />更多筛选条件：</div>
					<form id="goodsFilter">
		   			<table style="width:100%">
						<tbody>
							<tr>
								<td><label>已采购金额：</label></td>
								<td colspan="2"><input name="moneyTotleLeft" id="moneyTotleLeft" style="width:80px" />&nbsp;至&nbsp;<input name="moneyTotleRight" id="moneyTotleRight" style="width:80px" /> （元）</td>
								<td><label>所属行业：</label></td>
								<td colspan="2">
									<select name="belongIndustry">
										<option value="">所有</option>
										<c:forEach var="industry" items="${industryList}">
											<option value="${industry.objId}">${industry.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>	
								<td><label>最近交易时间：</label></td>
								<td colspan="2">
									<input type="checkbox" name="dealTime" value="7" /><label> 一周内</label>&nbsp;
									<input type="checkbox" name="dealTime" value="30" /><label> 一个月内</label>&nbsp;
									<input type="checkbox" name="dealTime" value="90" /><label> 三个月内</label>&nbsp;
									<input type="checkbox" name="dealTime" value="180" /><label> 六个月内</label>&nbsp;
								</td>
								<td><label>关键字：</label></td>
								<td><input name="keyWord" value="${keyWord}" id="keyWord" /></td>
								<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData('firstPage')"><span>确定</span></button></td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
				<!--详细搜索条件 结束 -->
				
		    	<!--排序方式 开始-->
			    <div class="conrank">
			      <ul>
			        <li class="displayBy"><em>排序方式：</em>
			        		<span class="buyerSort sysicon" id="evalSort" ><a href="javascript:void(0);">评价</a></span>
			        		<span class="buyerSort sysicon" id="dealMoneySort" ><a href="javascript:void(0);">交易金额</a></span>
			        		<span class="buyerSort sysicon" id="validSort" ><a href="javascript:void(0);">入库时间</a></span>
			        </li>
			        <li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个采购人</li>
			      </ul>
			    </div>
				<!--排序方式 结束-->
				<!--采购人信息 开始--> 
				<div id="showBuyerListAndPic">
					<%@ include file="/view/bizplatform/buyers/showbuyer/buyer_list_div_l.jsp" %>
				</div>
				<!--采购人信息 结束-->
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<span id="otherDiv">
				<%@ include file="/view/pubservice/common/customer_service_div.jsp" %>
			</span>
			<span id="reconmendDiv">
				<jsp:include page="/BuyerShowController.do?method=getRecommendBuyer&rp=10&page=1&keyWord=${keyWord}"></jsp:include>
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