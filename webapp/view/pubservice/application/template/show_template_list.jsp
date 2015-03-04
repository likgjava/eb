<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>范本库-阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/template/show_template_list.js"></script>
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
		<div id="contentSub"></div>
		<div id="contentMain" class="index2paL">
		 	<div id="conTitle">
		      	<div class="navCurrent">范本展示</div>
		    </div>
		    <div id="conBody">
		    	<!-- 搜索条件 开始 -->
				<div id="ListSubCategory">
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>按品目选择：</div>
						<ul class="CategoryListTableLevel1 expand" id="moreProp1">
							<div id="propDisp1">
								<c:if test="${fn:length(categoryList) == 0}">
									<li>没有检索到相关品目数据！</li>
								</c:if>
								<c:if test="${fn:length(categoryList) > 0}">
									<li><a href="javascript:void(0);" name="-1" >全部品目</a></li>
									<c:forEach var="category" items="${categoryList}" > 
										<li>
											<a href="javascript:void(0);" name="${category[0]}" <c:if test="${categoryCode == category[0] }"> class="strong" </c:if>>${category[1]}</a><i>(${category[2]})</i>
										</li>
									</c:forEach>
								</c:if>
							</div>
						</ul>
						<c:if test="${fn:length(categoryList) > 15}">
							<div class="expandOpen">
								<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp1','moreProp1','分类');return false;">
									<span class="sysicon siDownGray" id="expandProp1">显示全部分类</span>
								</a>
							</div>
						</c:if>
					</div>
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>按区域选择：</div>
						<ul class="CategoryListTableLevel1 expand" id="moreProp2">
							<div id="propDisp2">
								<c:if test="${fn:length(districtList) == 0}">
									<li>没有检索到相关区域数据！</li>
								</c:if>
								<c:if test="${fn:length(districtList) > 0}">
									<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict('-1');return false;">全部区域</a></li>
									<c:forEach var="district" items="${districtList}" > 
										<li>
											<a name="${district[0]}" href="javascript:void(0);" onclick="show_list.changeDistrict('${district[0]}');return false;" <c:if test="${districtId == district[0] }"> class="strong" </c:if>>${district[1]}</a><i>(${district[2]})</i>
										</li>
									</c:forEach>
								</c:if>
							</div>
						</ul>
						<c:if test="${fn:length(districtList) > 16}">
							<div class="expandOpen">
								<a href="javascript:void(0);" onclick="common.showOrHiddenMore('expandProp2','moreProp2','区域');return false;">
									<span class="sysicon siDownGray" id="expandProp2">显示全部区域</span>
								</a>
							</div>
						</c:if>
					</div>
					<!--详细搜索条件 开始 -->
					<div class="SubCategoryBox">
						<div class="FindByHint"><img src="view/resource/skin/sysicon/16/bullet_go.png"/>更多筛选条件：</div>
						<form id="templateFilter">
		    			<table style="width:100%">
							<tbody>
								<tr>
									<td>范本类型：<html:select id="templateType" name="templateType" code="pubservice.application.template.templateType"><html:option value="">全部</html:option></html:select></td>
									<td><input type="checkbox" id="isShare" name="isShare" value="1"/><label for="isShare"> 共享范本</label></td>
									<td>关键字：<input id="keyWord" value="${keyWord}"/></td>
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
			        <li class="displayBy">
			        	<em>排序：</em>
			        	<span class="templateSort sysicon" id="createTimeSort"><a href="javascript:void(0);">创建时间</a></span>
			        	<span class="templateSort sysicon" id="favoriteNumSort"><a href="javascript:void(0);">收藏次数</a></span>
			        	<span class="templateSort sysicon" id="downNumSort"><a href="javascript:void(0);">下载次数</a></span>
			        </li>
			        <li class="proAmount">共&nbsp;<strong><span id="totalAmount">${PAGERESULT.totalRowNum }</span></strong>&nbsp;个范本</li>
			      </ul>
			    </div>
				<!--排序方式 结束-->
				
				<!--范本列表开始--> 
				<div id="showTemplateList">
					<%@ include file="/view/pubservice/application/template/template_list_div_l.jsp" %>
				</div>
				<!--范本列表结束-->
			</div>
		</div>
		<div id="contentSupp" class="index2paR">
			<jsp:include page="/DotTemplateShowController.do?method=getRecommendTemplateListView">
				<jsp:param value="10" name="rp"/>
				<jsp:param value="1" name="page"/>
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