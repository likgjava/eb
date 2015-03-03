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

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/pubProject.css" media="screen"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/create_bid_project_2.js'></script>
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
	<div class="page" style="min-height:800px!important;">
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>
			
			<!-- conBody开始 内容页面加载位置 -->
			<div class="grid16_16" id="conBody">
			
		    <div id="bd">
		    	<!--主体部分start-->
		    	<div class="bd_context">
		    		<!--导航 position-->
		    		<div class="bd_title">
						<div class="bd_t_name"><img  src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_project.jpg" /></div>
						<div class="bd_t_guide">
							<div class="bd_t_pos pos_1">填写项目和需求</div>
							<div class="bd_t_pos_spc"></div>
							<div class="bd_t_pos pos_2_on">设定规则轮次</div>
							<div class="bd_t_pos_spc"></div>
							<div class="bd_t_pos pos_3">填写其他信息并发布项目</div>
						</div>
					</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">	
		    			
		    			<form id="CreateProjectForm2">		
		    			<!--项目ID-->
		    			<input type="hidden" name="projId" id="projId" value="${projId}" />
		    				<!--分割线-->
		    				<div class="bd_post_space" id="bd_post_error_spc"></div>
		    				<!--标题-->
							<div class="bd_m_c_title2">
								<div class="bd_m_c_spc"></div>
								 <div class="bd_m_c_name"><span class="float-l">项目轮次信息</span></div>
							</div>
							<!--分割线2-->
							<div class="bd_post_space2"></div>
		    				<div class="bd_post_form">
		    					<input type="hidden" name="evalStartTime" id="evalStartTime" value=""/>
		    					<input type="hidden" name="evalEndTime" id="evalEndTime" value=""/>
		    					
		    					<div class="bd_post_form_line">
									<div class="bd_post_form_title">竞价时间：</div>
									<div class="bd_post_form_context">
										<div class="bd_post_form_note">竞价开始时间为第一轮的开始时间,结束时间为最后一轮的结束时间。</div>
									</div>												
								</div>
		    					
		    					<div class="bd_post_form_line">
									<div class="bd_post_form_title">设置轮次：</div>
									<div class="bd_post_form_context">
										<div class="bd_post_form_note">可点击”增加轮次“添加项目轮次，最多可添加5轮。
											<span class="sysicon siAdd"><a onclick="javasctipt:CreateProjectForm2.cloneTurn();" href="javascript:void(0);">添加轮次</a></span>
										</div>
									</div>												
								</div>
								
								<div id="turnDiv1" class="bd_post_form_line">
									<div class="bd_post_form_title">第<span id="turnNo1">1</span>轮：</div>
									<div class="bd_post_form_context">
										<div id="bd_post_properties" class="bd_post_properties" style="margin-top:0px;padding-top:0px;width:780px;">
											<div class="bd_post_p_line">
												<div class="bd_post_p_col_2">
													<label>开始时间：</label>
													<span>
														<input type="text" id="startTime1" name="startTime1" class="bd_post_p_input required" readonly="readonly" />
													</span>
												</div>
												<div class="bd_post_p_col_2">
													<label>结束时间：</label>
													<span>
														<input type="text" id="endTime1" name="endTime1" class="bd_post_p_input required" readonly="readonly" />
													</span>
												</div>
												<div class="bd_post_p_col_2" style="width:60%">
													<label>降幅：</label>
													<span>
												        <input name="totalCutType1" id="totalCutType1" type="radio" checked="checked" value="01" />
														<input name="totalCut1" id="totalCut1" type="text" maxlength="2" class="digits" style="width:40px" value="0"/>%
														<input name="totalCutType1" id="totalCutType1" type="radio" value="02"/>
														<input name="totalCut_1" id="totalCut_1" type="text" class="money" style="width:40px" value="0.0"/>元		
													</span>
												</div>
											</div>
										</div>
									</div>												
								</div>
							</div>
							
		<div class="bd_m_c_title2">
			<div class="bd_m_c_spc"></div>
			<div class="bd_m_c_name">项目规则信息</div>											
		</div>
		<!--分割线2-->
		<div class="bd_post_space2"></div>
				<div id="projRuleDiv">
				<ul class="safe-info">
					<c:forEach var="sysconfigItem" items="${sysconfigitemList}" varStatus="status">	
						<c:if test="${sysconfigItem.code=='maxPrice'}"><input type="hidden" id="itemid_maxPrice" name="itemid_maxPrice" value="${sysconfigItem.objId}"/></c:if>
						<c:if test="${sysconfigItem.code=='minPrice'}"><input type="hidden" id="itemid_minPrice" name="itemid_minPrice" value="${sysconfigItem.objId}"/></c:if>
						<c:if test="${sysconfigItem.code=='averagePrice'}"><input type="hidden" id="itemid_averagePrice" name="itemid_averagePrice" value="${sysconfigItem.objId}"/></c:if>
						<c:if test="${sysconfigItem.code=='selfRanking'}"><input type="hidden" id="itemid_selfRanking" name="itemid_selfRanking" value="${sysconfigItem.objId}"/></c:if>
						<c:if test="${sysconfigItem.code=='buyerBudget'}"><input type="hidden" id="itemid_buyerBudget" name="itemid_buyerBudget" value="${sysconfigItem.objId}"/></c:if>
						<c:if test="${sysconfigItem.code=='bargainNumber'}"><input type="hidden" id="itemid_bargainNumber" name="itemid_bargainNumber" value="${sysconfigItem.objId}"/></c:if>
						
						<c:if test="${sysconfigItem.code=='threeMust'}"><input type="hidden" id="itemid_threeMust" name="itemid_threeMust" value="${sysconfigItem.objId}"/></c:if>
					</c:forEach>
					
					<li class="item identity hightSearch" code="bargainNumber" title="<spring:message code="BargainProjectRule.bargainNumber"/>">
						<div class="title"><spring:message code="BargainProjectRule.bargainNumber"/></div>
						<div class="supply" style="width:450px;line-height:18px;"><spring:message code="BargainProjectRule.bargainNumber_desc"/></div>
						<div class="state"><span class="over" id="bargainNumber_span">多次报价</span></div>
						<div class="operate">
					    	<input name="bargainNumber" id="bargainNumber_2" type="radio" checked="checked" value="1" title="多次报价"/>多次报价
							<input name="bargainNumber" id="bargainNumber_1" type="radio" value="0" title="单次报价"/>单次报价
						</div>
					</li>
					<li class="item identity hightSearch" code="buyerBudget" title="<spring:message code="BargainProjectRule.buyerBudget"/>">
						<div class="title"><spring:message code="BargainProjectRule.buyerBudget"/></div>
						<div class="supply" style="width:450px;"><spring:message code="BargainProjectRule.buyerBudget_desc"/></div>
						<div class="state"><span class="over" id="buyerBudget_span">显示</span></div>
						<div class="operate">
					    	<input name="buyerBudget" id="buyerBudget_1" type="radio" value="0" title="不显示"/>不显示
							<input name="buyerBudget" id="buyerBudget_2" type="radio" checked="checked" value="1" title="显示"/>显示
						</div>
					</li>
					<li class="item identity hightSearch" code="maxPrice" title="<spring:message code="BargainProjectRule.maxPrice"/>">
						<div class="title"><spring:message code="BargainProjectRule.maxPrice"/></div>
						<div class="supply" style="width:450px;"><spring:message code="BargainProjectRule.maxPrice_desc"/></div>
						<div class="state"><span class="over" id="maxPrice_span">不显示</span></div>
						<div class="operate">
					    	<input name="maxPrice" id="maxPrice_1" type="radio" checked="checked" value="0" title="不显示"/>不显示
							<input name="maxPrice" id="maxPrice_2" type="radio" value="1" title="显示"/>显示
						</div>
					</li>
					<li class="item identity hightSearch" code="minPrice" title="<spring:message code="BargainProjectRule.minPrice"/>">
						<div class="title"><spring:message code="BargainProjectRule.minPrice"/></div>
						<div class="supply" style="width:450px;"><spring:message code="BargainProjectRule.minPrice_desc"/></div>
						<div class="state"><span class="over" id="minPrice_span">不显示</span></div>
						<div class="operate">
					    	<input name="minPrice" id="minPrice_1" type="radio" checked="checked" value="0" title="不显示"/>不显示
							<input name="minPrice" id="minPrice_2" type="radio" value="1" title="显示"/>显示
						</div>
					</li>
					<li class="item identity hightSearch" code="averagePrice" title="<spring:message code="BargainProjectRule.averagePrice"/>">
						<div class="title"><spring:message code="BargainProjectRule.averagePrice"/></div>
						<div class="supply" style="width:450px;"><spring:message code="BargainProjectRule.averagePrice_desc"/></div>
						<div class="state"><span class="over" id="averagePrice_span">不显示</span></div>
						<div class="operate">
					    	<input name="averagePrice" id="averagePrice_1" type="radio" checked="checked" value="0" title="不显示"/>不显示
							<input name="averagePrice" id="averagePrice_2" type="radio" value="1" title="显示"/>显示
						</div>
					</li>
					<li class="item identity hightSearch" code="selfRanking" title="<spring:message code="BargainProjectRule.selfRanking"/>">
						<div class="title"><spring:message code="BargainProjectRule.selfRanking"/></div>
						<div class="supply" style="width:450px;"><spring:message code="BargainProjectRule.selfRanking_desc"/></div>
						<div class="state"><span class="over" id="selfRanking_span">显示</span></div>
						<div class="operate">
					    	<input name="selfRanking" id="selfRanking_1" type="radio" value="0" title="不显示"/>不显示
							<input name="selfRanking" id="selfRanking_2" type="radio" checked="checked" value="1" title="显示"/>显示
						</div>
					</li>
					
					<li class="item identity hightSearch" code="threeMust" title="<spring:message code="BargainProjectRule.threeMust"/>">
						<div class="title"><spring:message code="BargainProjectRule.threeMust"/></div>
						<div class="supply" style="width:450px;"><spring:message code="BargainProjectRule.threeMust_desc"/></div>
						<div class="state"><span class="over" id="threeMust_span">不限定</span></div>
						<div class="operate">
					    	<input name="threeMust" id="threeMust_1" type="radio" checked="checked" value="1" title="至少三家"/>至少三家
							<input name="threeMust" id="threeMust_2" type="radio" value="0" title="不限定"/>不限定
						</div>
					</li>
					
				</ul>
			    </div>
			</form>
			
			
				<!--提交区域-->
				<div class="bd_post_subscribe">	
					<div id="submitDiv" class="bd_post_submit">
						<a href="javascript:void(0);" class="bd_post_submit_btn" id="bd_post_submit_btn" >保存，填写其他信息</a>
					</div>
					<div id="submittingDiv" class="bd_post_submit hidden">
						<img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/load.gif" /> 保存中......
					</div>
					<div class="bd_post_submit_cls"></div>	
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


<!-- 克隆轮次div -->
<div id="cloneDiv1" class="bd_post_form_line hidden">
	<div class="bd_post_form_title">第<span id="turnNo1">1</span>轮：</div>
	<div class="bd_post_form_context">
		<div id="bd_post_properties" class="bd_post_properties" style="margin-top:0px;padding-top:0px;width:780px;">
			<div class="bd_post_p_line">
				<div class="bd_post_p_col_2">
					<label>开始时间：</label>
					<span>
						<input type="text" id="startTime1" name="startTime1" class="bd_post_p_input required" readonly="readonly" />
					</span>
				</div>
				<div class="bd_post_p_col_2">
					<label>结束时间：</label>
					<span>
						<input type="text" id="endTime1" name="endTime1" class="bd_post_p_input required" readonly="readonly" />
					</span>
				</div>
				
				<div class="bd_post_p_col_2" style="width:60%">
					<label>降幅：</label>
					<span>
				        <input name="totalCutType" id="totalCutType" type="radio" checked="checked" value="01" />
						<input name="totalCut" id="totalCut" type="text" maxlength="2" class="digits" style="width:40px" value="0"/>%
						<input name="totalCutType" id="totalCutType" type="radio" value="02"/>
						<input name="totalCut_" id="totalCut_" type="text" class="money" style="width:40px" value="0.0"/>元		
					</span>
					<a id="delTurn1" href="javascript:void(0);">删除</a>
				</div>
			</div>
		</div>
	</div>												
</div>

</body>
</html>
