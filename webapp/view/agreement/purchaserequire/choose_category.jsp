<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/purchaserequire/choose_category.js'></script>
</head>
<body>

<!--页面容器 开始-->
<div id="container"> 
  <!--头部容器 开始-->
  <div class="header"> 
  		<input type="hidden" id="isOutCss" value="${param.isOutCss}"/>
  		<c:choose>
	  		<c:when test="${!isOutCss}"><jsp:include page="/view/srplatform/portal/include/backgroundmenu.jsp"></jsp:include></c:when>
  			<c:otherwise><%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%></c:otherwise>
  		</c:choose>
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:600px!important;">
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>
			
			<!-- conBody开始 内容页面加载位置 -->
			<div class="grid16_16" id="conBody">
			
				<div id="bd">
				<div class="bd_top"></div>
				<!--主体部分start-->
				<div class="bd_context">
				<div class="bd_title">
				<div class="bd_t_name"><img  src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_buy.jpg" /></div>
				<div class="bd_t_guide">
					<div class="bd_t_pos pos_1_on">选择类目</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_2">填写信息详情</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_3">提交成功，等待审核</div>
				</div>			
				</div>
				<div class="bd_main">
					<div class="bd_m_top"></div>
					<div class="bd_m_context">
						<div class="bd_m_c_title">
						<div class="bd_m_c_spc"></div>
							<div class="bd_m_c_name">
								<span class="float-l">选择类目</span>
							</div>
						</div>
						<div class="bd_c_c_main">
							<div class="bd_c_c_m_top"></div>
							<div class="bd_c_c_m_context">
								<div class="bd_c_c_form">
									<div class="bd_c_c_key">请输入产品名称以查找类目</div>
									<div class="bd_c_c_keyinput"><input id="searchText" value="请输入产品名称、类目名称等关键词" type="text" onblur="choose_category.blurSearchText(this);return false;" onclick="choose_category.clickSearchText(this);return false;" /></div>
									<div class="bd_c_c_keybtn"><input onclick="choose_category.clickSearchBtn();return false;" value="查&nbsp;&nbsp;找" id="bd_c_c_keybtn_search" type="button" /></div>
									<div class="cls"></div>
								</div>
								<!-- change style 20100414 begin -->
								<div id="bd_c_c_btn" class="bd_c_c_btn">
									<div style="color: rgb(0, 0, 0);" class="bd_c_c_category_btn" id="bd_m_c_select">											
										<div id="closecategory" class="bd_c_c_tab_h" onclick="choose_category.closeOrOpen(this);return false;">通过浏览自选类目</div>
									</div>
								</div>
								
								<!-- 存值域 -->
								<input id="categoryvalues" name="categoryvalues" value="${categoryvalues}" type="hidden" />
								
								<div style="display: block;" class="bd_c_c_select_new" id="selectCategoryDiv">	
									<div class="bd_c_c_select_b" id="level1">
										<c:forEach var="purCategory" items="${purCategoryList}">
											<div class="bd_c_c_select_li">
												<a href="javascript:void(0);" onclick="choose_category.getSecondCategorys('${purCategory.objId}',this);return false;" class="<c:if test="${!purCategory.isLeaf}">bd_c_c_select_li_p</c:if><c:if test="${purCategory.isLeaf}">bd_c_c_select_li</c:if>" id="${purCategory.objId}" >${purCategory.categoryName}</a>
											</div>
										</c:forEach>
									</div>
									<div class="bd_c_c_select_b" id="level2"></div>
									<div class="bd_c_c_select_b" id="level3"></div>
									<div class="bd_c_c_select_cls"></div>
								</div>
								
								<!-- 查找结果集 -->
								<div id="searchresultDiv" style="display: none;" class="bd_c_c_select_new">
									<div class="bd_c_c_list">
										<div id="searchListtips" class="bd_c_c_text" style="display: none;">根据您输入的关键词，我们为您查找到以下类目：</div>
										<div id="searchListDiv" class="bd_c_c_result" style="display: block;"></div>
										<div id="resultnoresult" class="bd_c_c_noresult" style="display: none;">
											<div class="bd_c_c_noresult_text">抱歉，没有找到与关键字相关的类目。<br />请更换关键词<span onclick="choose_category.backtoChooseSefl();" class="bd_c_font_ul">或通过浏览来自选类目</span></div>
										</div>
										<div id="resultothers" class="bd_c_c_others" style="display: block;">不是您想要的类目？<span onclick="choose_category.backtoChooseSefl();" class="bd_c_font_ul">您可以通过浏览来自选类目</span></div>
									</div>
									<div class="bd_c_c_select_cls"></div>													
								</div>
								
								<div class="cls"></div>
							</div>
							<div class="bd_c_c_m_bottom"></div>
						</div>
						<div class="bd_c_p ">
							<span id="bd_c_p_title" class="bd_c_p_title">您当前选择的是：<span class="bd_c_p_list" id="categoryNames">${categoryNames}</span></span>
						</div>
						<div class="bd_c_submit" id="bd_c_submit">											
							<a href="javascript:choose_category.nextStepBtn();" id="nextStepBtn" name="nextStepBtn">下一步，填写信息详情</a>
							<div class="bd_c_submit_cls"></div>
						</div>
					</div>
					<div class="bd_m_bottom"></div>									
					</div>								
				</div>
				<!--主体部分end-->
				<div class="bd_bottom"></div>
				</div>
			
			</div>
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束--> 
</div>
<!--页面容器 结束--> 
<!--扩展用容器，用于与内容无关的装饰性扩展-->
<div id="extraDiv">
  <div id="extraDiv1"></div>
  <div id="extraDiv2"></div>
  <div id="extraDiv3"></div>
  <div id="extraDiv4"></div>
  <div id="extraDiv5"></div>
  <div id="extraDiv6"></div>
</div>
</body>
</html>
