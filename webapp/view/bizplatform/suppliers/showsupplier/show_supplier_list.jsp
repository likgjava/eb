<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title><c:if test="${categoryName!=null}">【${categoryName}】-</c:if> 供应商库 - 阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/suppliers/showsupplier/show_supplier_list.js"></script></head>
<body>
<div id="container">
	<input type="hidden" id="districtLevel" value="${districtLevel}"/>
	<input type="hidden" id="districtId" value="${districtId}"/>
	<input type="hidden" id="categoryCode_menu" value="${categoryCode}"/>
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
		      	<div class="navCurrent">供应商展示</div>
		    </div>
		    <div id="conBody"><!--功能页内容-->
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
										<li><a href="#supplierLink" name="${category[0]}"
												<c:if test="${categoryCode == category[0] }"> class="strong" </c:if>
											>${category[2]}</a><i>(${category[3]})</i></li>
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
				</div>
				<!-- 搜索条件 结束 -->
				<!--详细搜索条件 开始 -->
				<div class="SubCategoryBox">
					<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>更多筛选条件：</div>
					<form id="goodsFilter">
		   			<table style="width:100%">
						<tbody>
							<tr>
								<td><label>所属行业：</label></td>
								<td>
									<select name="belongIndustry">
										<option value="">所有</option>
										<c:forEach var="industry" items="${industryList}">
											<option value="${industry.objId}">${industry.name}</option>
										</c:forEach>
									</select>
								</td>
								
								<td><label>企业性质：</label></td>
								<td>
								<html:select selectedValue="0" id="entPrpt" name="entPrpt" code="biz.orgInfo.entprpt">
									<html:option value="">所有</html:option>
								</html:select>
								</td>
								<td>是否厂商：
								<input type="checkbox" name="isManufacturerC" value="1"/> 厂商 &nbsp; <input type="checkbox" name="isManufacturerJ" value="0"/> 经销商</td>
								
							</tr>
							<tr>
								<td><label>企业规模：</label></td>
								<td>
									<html:select selectedValue="0" id="unitScape" name="unitScape" code="biz.orgInfo.unitScape">
										<html:option value="">所有</html:option>
									</html:select>
								</td>
								<td>关键字：</td>
								<td><input name="keyWord" value="${keyWord}" id="keyWord"/></td>
								<td class="operationBtnDiv l"><button type="button" onclick="show_list.makeSearchData()"><span>确定</span></button></td>
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
			        		<span class="supplierSort sysicon" id="evalSort" ><a name="supplierLink" href="javascript:void(0);">评价</a></span>
			        		<span class="supplierSort sysicon" id="validSort" ><a href="javascript:void(0);">入库时间</a></span></li>
			        <li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个供应商</li>
			      </ul>
			    </div>
				<!--排序方式 结束-->
				<!--产品信息 开始--> 
				<div id="showSuppListAndPic">
					<%@ include file="/view/bizplatform/suppliers/showsupplier/supplier_list_div_l.jsp" %>
				</div>
				<!--产品信息 结束-->
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<span id="otherDiv">
				<%@ include file="/view/pubservice/common/customer_service_div.jsp" %>
			</span>
			<span id="reconmendDiv">
				<jsp:include page="/SupplierShowController.do?method=getRecommendSupplierInfo&rp=5&page=1&keyWord=${keyWord}"></jsp:include>
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