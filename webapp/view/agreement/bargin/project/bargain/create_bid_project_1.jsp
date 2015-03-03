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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/pubProject.css" media="screen"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/create_bid_project_1.js'></script>

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
							<div class="bd_t_pos pos_1_on">填写项目需求</div>
							<div class="bd_t_pos_spc"></div>
							<div class="bd_t_pos pos_2">设定规则轮次</div>
							<div class="bd_t_pos_spc"></div>
							<div class="bd_t_pos pos_3">填写其他信息并发布项目</div>
						</div>
					</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">	
		    		<form id="CreateProjectForm1">		
		    		<!-- 从采购计划条目创建项目-任务书和任务书条目 -->
					<input type="hidden" name="taskId" id="taskId" value="${taskId}" />
					<input type="hidden" name="taskItemIds" id="taskItemIds" value="${taskItemIds}" />
					<!-- 从采购需求创建项目-采购需求条目ID -->
					<input type="hidden" name="requirementIds" id="requirementIds" value="${requirementIds}" />
					
					<!-- 项目总预算（如果有任务书或商品、需求等，则默认是他们的条目预算之和） -->
					<c:set var="priceTotal" value="0"/>
					
		    				<!--分割线-->
		    				<div class="bd_post_space" id="bd_post_error_spc"></div>
		    				<!--标题-->
							<div class="bd_m_c_title2">
								<div class="bd_m_c_spc"></div>
								 <div class="bd_m_c_name"><span class="float-l">填写项目信息</span></div>
							</div>
							<!--分割线2-->
							<div class="bd_post_space2"></div>
		    				<div class="bd_post_form">
		    					<div class="bd_post_form_line">
		    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>项目名称：</div>
		    						<div class="bd_post_form_context">
		    							<div class="bd_post_form_input_float">
		    								<input class="bd_post_input_long required" type="text" name="projName" id="projName" maxlength="50"/>
		    							</div>
		    							<div class="bd_post_form_note_float">最长50个汉字</div>
		    						</div>												
		    					</div>
		    					<div class="bd_post_form_line">
		    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>报名起始日期：</div>
		    						<div class="bd_post_form_context">
		    							<div class="bd_post_form_input_float">
		    								<input class="bd_post_p_input required" type="text" name="signUpSTime" id="signUpSTime" readonly="readonly"/>
		    							</div>
		    							<div class="bd_post_form_note_float">报名开始日期</div>
		    						</div>												
		    					</div>
		    					<div class="bd_post_form_line">
		    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>报名结束日期：</div>
		    						<div class="bd_post_form_context">
		    							<div class="bd_post_form_input_float">
		    								<input class="bd_post_p_input required" type="text" name="signUpETime" id="signUpETime" readonly="readonly"/>
		    							</div>
		    							<div class="bd_post_form_note_float">报名截止日期</div>
		    						</div>												
		    					</div>
		    					<div class="bd_post_form_line">
									<div class="bd_post_form_title">是否委托代理：</div>
									<div class="bd_post_form_context">
										<div class="bd_post_form_note">选择是的话，则可委托阳光易购进行代理</div>
										<div class="bd_post_form_radio"><input type="radio" name="isDelegate" value="1"/></div>
										<div class="bd_post_form_radio_label_date"><label>&nbsp;是</label></div>
									
										<div class="bd_post_form_radio"><input type="radio" name="isDelegate" value="0" checked="checked"/></div>
										<div class="bd_post_form_radio_label_date"><label>&nbsp;否</label></div>
									</div>
								</div>
							</div>
							
		<div class="bd_m_c_title2">
			<div class="bd_m_c_spc"></div>
			<div class="bd_m_c_name">需求信息
				<span class="bd_post_category">
					[总预算：￥<em id="total" style="color:red;"></em>&nbsp;元]
					<span class="bd_font_h">您可以对该项目添加多个需求(目前支持5个条目)</span>
					<span class="sysicon siAdd">
					<!-- 如果有商品，则克隆商品模板 -->
					<c:choose>
					<c:when test="${!empty shoppingCartItemList && fn:length(shoppingCartItemList) > 0}">
						<a id="addRequireItem" onclick="javasctipt:CreateProjectForm1.cloneRequireItem('goods');" href="javascript:void(0);">添加需求条目</a>
					</c:when>
					<c:otherwise>
						<a id="addRequireItem" onclick="javasctipt:CreateProjectForm1.cloneRequireItem('desc');" href="javascript:void(0);">添加需求条目</a>
					</c:otherwise>
					</c:choose>
					</span>
		    	</span>
			</div>											
		</div>
		
		<c:choose>
		<%-- 从采购计划-任务书条目创建竞价项目--%>
		<c:when test="${!empty taskItemList && fn:length(taskItemList) > 0}">
			<c:forEach var="taskItem" items="${taskItemList}" varStatus="status">
				<c:set var="priceTotal" value="${priceTotal+taskItem.goodsTotal}"/><!-- 循环计数 -->
				<div id="requireItemDiv${status.index+1}" tiid="${taskItem.objId}">
					<div class="bd_post_space2"></div>
					<div class="bd_post_form">
					<!--需求条目1-->
					<div class="bd_post_form_line">
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求条目信息：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_note">此需求条目预算：￥<span id="goodsTotalSpan_${status.index+1}" style="color:red;">${taskItem.goodsPrice*taskItem.goodsQty}</span>&nbsp;元&nbsp;<a id="delRequireItem_${status.index+1}" href="javascript:void(0);">删除此条目信息</a></div>
								<div id="bd_post_properties" class="bd_post_properties">
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>所属品目：</label>
											<input type="hidden" value="${taskItem.purCategory.objId}" id="purCategoryId${status.index+1}" name="purCategory.objId" />
											<input type="text" value="${taskItem.purCategory.categoryName}" id="purCategoryName${status.index+1}" name="purCategory.name" class="bd_post_p_input small sysicon siSearch required" readonly="readonly"/>
										</div>
										<div class="bd_post_p_col_2">
											<label>计量单位：</label>
											<span><input type="text" id="goodsUnit${status.index+1}" name="goodsUnit${status.index+1}" class="bd_post_p_input small required" maxlength="10"/></span>
										</div>
									</div>
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>单个预算(元)：</label>
											<span><input type="text" id="goodsPrice${status.index+1}" value="${taskItem.goodsPrice}" name="goodsPrice${status.index+1}" class="bd_post_p_input small money required"/></span>
										</div>
										<div class="bd_post_p_col_2">
											<label>采购数量：</label>
											<span><input type="text" value="${taskItem.goodsQty}" id="goodsQty${status.index+1}" name="goodsQty${status.index+1}" class="bd_post_p_input small digits required"/></span>
										</div>
										<input type="hidden" id="goodsTotal${status.index+1}" name="goodsTotal${status.index+1}" value="${taskItem.goodsTotal}"/>
									</div>
								</div>
							</div>												
						</div>
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求描述： </div>
							<div class="bd_post_form_context" >
								<div class="bd_post_form_note">请详细填写您的具体需求</div>
								<div id="bd_post_properties" class="bd_post_form_editor">
									<textarea name="descr${status.index+1}" id="descr${status.index+1}" class="required" maxLength="1000" style="height:60px;width:670px;">${taskItem.memo}</textarea>
								</div>
								<div class="bd_post_form_note">1、请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的求购信息将无法通过审核;</div>
							</div>												
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</c:when>
		<%-- 从采购需求创建竞价项目--%>
		<c:when test="${!empty requirementList && fn:length(requirementList) > 0}">
			<c:forEach var="requirement" items="${requirementList}" varStatus="status">
			<c:set var="priceTotal" value="${priceTotal+requirement.purchaseBudget}"/><!-- 循环计价 -->
				<div id="requireItemDiv${status.index+1}">
					<div class="bd_post_space2"></div>
					<div class="bd_post_form">
					<!--需求条目1-->
					<div class="bd_post_form_line">
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求条目信息：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_note">此需求条目预算：￥<span id="goodsTotalSpan_${status.index+1}" style="color:red;">${requirement.purchaseBudget * requirement.purchaseQty}</span>&nbsp;元&nbsp;<a id="delRequireItem_${status.index+1}" href="javascript:void(0);">删除此条目信息</a></div>
								<div id="bd_post_properties" class="bd_post_properties">
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>所属品目：</label>
											<input type="hidden" value="${requirement.category.objId}" id="purCategoryId${status.index+1}" name="purCategory.objId" />
											<input type="text" value="${requirement.category.categoryName}" id="purCategoryName${status.index+1}" name="purCategory.name" class="bd_post_p_input small sysicon siSearch required" readonly="readonly"/>
										</div>
										<div class="bd_post_p_col_2">
											<label>计量单位：</label>
											<span><input type="text" id="goodsUnit${status.index+1}" name="goodsUnit${status.index+1}" class="bd_post_p_input small required" maxlength="10"/></span>
										</div>
									</div>
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>单个预算(元)：</label>
											<span><input type="text" id="goodsPrice${status.index+1}" value="${requirement.purchaseBudget}" name="goodsPrice${status.index+1}" class="bd_post_p_input small money required"/></span>
										</div>
										<div class="bd_post_p_col_2">
											<label>采购数量：</label>
											<span><input type="text" value="${requirement.purchaseQty}" id="goodsQty${status.index+1}" name="goodsQty${status.index+1}" class="bd_post_p_input small digits required"/></span>
										</div>
										<input type="hidden" id="goodsTotal${status.index+1}" name="goodsTotal${status.index+1}" value="${requirement.purchaseBudget * requirement.purchaseQty}"/>
									</div>
								</div>
							</div>												
						</div>
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求描述： </div>
							<div class="bd_post_form_context" >
								<div class="bd_post_form_note">请详细填写您的具体需求</div>
								<div id="bd_post_properties" class="bd_post_form_editor">
									<textarea name="descr${status.index+1}" id="descr${status.index+1}" class="required" maxLength="1000" style="height:60px;width:670px;">${requirement.title}</textarea><br/>
								</div>
								<div class="bd_post_form_note">1、请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的求购信息将无法通过审核;</div>
							</div>												
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</c:when>
		<%-- 从购物车创建竞价项目--%>
		<c:when test="${!empty shoppingCartItemList && fn:length(shoppingCartItemList) > 0}">
			<c:forEach var = "shoppingCartItem" items="${shoppingCartItemList}" varStatus="status">
			<c:set var="priceTotal" value="${priceTotal+shoppingCartItem.goodsTotal}"/><!-- 循环计价 -->
				<div id="cartItemDiv${status.index+1}" cartitemid="${shoppingCartItem.objId}">
					<div class="bd_post_space2"></div>
					<div class="bd_post_form">
					<!--需求条目1-->
					<div class="bd_post_form_line">
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求条目信息：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_note">此需求条目预算：￥<span id="goodsTotalSpan_${status.index+1}" style="color:red;"><fmt:formatNumber value="${shoppingCartItem.goodsTotal}" pattern="#,##0.00#" /></span>&nbsp;元&nbsp;<a type="cartitem" id="delRequireItem_${status.index+1}" href="javascript:void(0);">删除此条目信息</a></div>
								<div id="bd_post_properties" class="bd_post_properties">
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>商品名称：</label><span id="productName${status.index+1}">${shoppingCartItem.goods.productName}</span>
											<input type="hidden" value="${shoppingCartItem.goods.objId }" id="goodsId${status.index+1}" name="goods.objId"/>
											<input type="hidden" value="${shoppingCartItem.goods.purCategory.objId}" id="purCategoryId${status.index+1}" name="purCategory.objId" />
											<input type="hidden" value="${shoppingCartItem.goods.purCategory.categoryName}" id="purCategoryName${status.index+1}" name="purCategory.name"/>
											<input type="hidden" value="${shoppingCartItem.goods.measureUnit}" id="goodsUnit${status.index+1}" name="goodsUnit${status.index+1}"/>
										</div>
									</div>
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>市场价(元)：</label>
											<span id="marketPrice${status.index+1}"><fmt:formatNumber value="${shoppingCartItem.marketPrice }" pattern="#,##0.00#" /></span>
											<input type="hidden" id="marketPrice${status.index+1}" name="marketPrice${status.index+1}" value="${shoppingCartItem.marketPrice }" />
										</div>
										<div class="bd_post_p_col_2">
											<label>协议价(元)：</label>
											<span id="agreePrice${status.index+1}"><fmt:formatNumber value="${shoppingCartItem.agreePrice }" pattern="#,##0.00#" /></span>
											<input type="hidden" id="agreePrice${status.index+1}" name="agreePrice${status.index+1}" value="${shoppingCartItem.agreePrice }" />
										</div>
									</div>
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>单个预算(元)：</label>
											<span><input type="text" id="goodsPrice${status.index+1}" value="${shoppingCartItem.agreePrice}" name="goodsPrice${status.index+1}" class="bd_post_p_input small money required"/></span>
										</div>
										<div class="bd_post_p_col_2">
											<label>采购数量：</label>
											<span><input type="text" value="${shoppingCartItem.goodsQty}" id="goodsQty${status.index+1}" name="goodsQty${status.index+1}" class="bd_post_p_input small digits required"/></span>
										</div>
										<input type="hidden" id="goodsTotal${status.index+1}" name="goodsTotal${status.index+1}" value="${shoppingCartItem.goodsTotal}"/>
									</div>
								</div>
							</div>												
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div id="requireItemDiv1">
				<div class="bd_post_space2"></div>
				<div class="bd_post_form">
				<!--需求条目1-->
				<div class="bd_post_form_line">
					<div class="bd_post_form_line">
						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求条目信息：</div>
						<div class="bd_post_form_context">
							<div class="bd_post_form_note">此需求条目预算：￥<span id="goodsTotalSpan_1" style="color:red;"></span>&nbsp;元&nbsp;<a id="delRequireItem_1" href="javascript:void(0);">删除此条目信息</a></div>
							<div id="bd_post_properties" class="bd_post_properties">
								<div class="bd_post_p_line">
									<div class="bd_post_p_col_2">
										<label>所属品目：</label>
										<input type="hidden" id="purCategoryId1" name="purCategory.objId" />
										<input type="text" id="purCategoryName1" name="purCategory.name" class="bd_post_p_input small sysicon siSearch required" readonly="readonly"/>
									</div>
									<div class="bd_post_p_col_2">
										<label>计量单位：</label>
										<span><input type="text" id="goodsUnit1" name="goodsUnit1" class="bd_post_p_input small required" maxlength="10"/></span>
									</div>
								</div>
								<div class="bd_post_p_line">
									<div class="bd_post_p_col_2">
										<label>单个预算(元)：</label>
										<input type="text" id="goodsPrice1" name="goodsPrice1" class="bd_post_p_input small money required"/>
									</div>
									<div class="bd_post_p_col_2">
										<label>采购数量：</label>
										<span><input type="text" id="goodsQty1" name="goodsQty1" class="bd_post_p_input small digits required"/></span>
									</div>
									<input type="hidden" id="goodsTotal1" name="goodsTotal1" />
								</div>
							</div>
						</div>												
					</div>
					<div class="bd_post_form_line">
						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求描述： </div>
						<div class="bd_post_form_context" >
							<div class="bd_post_form_note">请详细填写您的具体需求</div>
							<div id="bd_post_properties" class="bd_post_form_editor">
								<textarea name="descr1" id="descr1" class="required" maxLength="1000" style="height:60px;width:670px;"></textarea>
							</div>
							<div class="bd_post_form_note">1、请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的求购信息将无法通过审核;</div>
						</div>												
					</div>
				</div>
			</div>
			</div>
		</c:otherwise>
		</c:choose>
		
		<!-- 项目总预算（如果有任务书或商品、需求等，则默认是他们的条目预算之和） -->
		<input type="hidden" name="budgetTotalMoney" id="budgetTotalMoney" value="${priceTotal}"/>
</form>
									
			
			<!--提交区域-->
				<div class="bd_post_subscribe">	
				  	<div id="submitDiv" class="bd_post_submit">
						<a href="javascript:void(0);" class="bd_post_submit_btn" id="bd_post_submit_btn" >保存，设置项目轮次和规则</a>
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

<!-- 待克隆的需求条目 -无商品-->
<div class="hidden" id="cloneDescDiv1">
<div class="bd_post_space2"></div>
<div class="bd_post_form">
				<!--需求条目1-->
				<div class="bd_post_form_line">
					<div class="bd_post_form_line">
						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求条目信息：</div>
						<div class="bd_post_form_context">
							<div class="bd_post_form_note">此需求条目预算：￥<span id="goodsTotalSpan_1" style="color:red;"></span>&nbsp;元&nbsp;<a id="delRequireItem_1" href="javascript:void(0);">删除此条目信息</a></div>
							<div id="bd_post_properties" class="bd_post_properties">
								<div class="bd_post_p_line">
									<div class="bd_post_p_col_2">
										<label>所属品目：</label>
										<input type="hidden" id="purCategoryId1" name="purCategory.objId" />
										<input type="text" id="purCategoryName1" name="purCategory.name" class="bd_post_p_input small sysicon siSearch required" readonly="readonly"/>
									</div>
									<div class="bd_post_p_col_2">
										<label>计量单位：</label>
										<span><input type="text" id="goodsUnit1" name="goodsUnit1" class="bd_post_p_input small required" maxlength="10"/></span>
									</div>
								</div>
								<div class="bd_post_p_line">
									<div class="bd_post_p_col_2">
										<label>单个预算(元)：</label>
										<input type="text" id="goodsPrice1" name="goodsPrice1" class="bd_post_p_input small money required"/>
									</div>
									<div class="bd_post_p_col_2">
										<label>采购数量：</label>
										<span><input type="text" id="goodsQty1" name="goodsQty1" class="bd_post_p_input small digits required"/></span>
									</div>
									<input type="hidden" id="goodsTotal1" name="goodsTotal1" />
								</div>
							</div>
						</div>												
					</div>
					<div class="bd_post_form_line">
						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求描述： </div>
						<div class="bd_post_form_context" >
							<div class="bd_post_form_note">请详细填写您的具体需求</div>
							<div id="bd_post_properties" class="bd_post_form_editor">
								<textarea name="descr1" id="descr1" class="required" maxLength="1000" style="height:60px;width:670px;"></textarea>
							</div>
							<div class="bd_post_form_note">1、请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的求购信息将无法通过审核;</div>
						</div>												
					</div>
				</div>
</div>
</div>
<!-- 待克隆的需求条目 -无商品 end-->

<!-- 待克隆的需求条目 -有商品-->
<div class="hidden" id="cloneGoodsDiv1">
<div class="bd_post_space2"></div>
<div class="bd_post_form">
				<!--需求条目1-->
				<div class="bd_post_form_line">
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>需求条目信息：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_note">此需求条目预算：￥<span id="goodsTotalSpan_1" style="color:red;"></span>&nbsp;元&nbsp;<a type="cartitem" id="delRequireItem_1" href="javascript:void(0);">删除此条目信息</a></div>
								<div id="bd_post_properties" class="bd_post_properties">
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>商品名称：</label>
											<input type="hidden" id="goodsId1" name="goods.objId"/>
											<input type="hidden" id="purCategoryId1" name="purCategory.objId" />
											<input type="hidden" id="purCategoryName1" name="purCategory.name"/>
											<input type="hidden" id="goodsUnit1" name="goodsUnit1"/>
											<input type="text" id="productName1" name="goods.name" class="bd_post_p_input sysicon siSearch required" readonly="readonly"/>
										</div>
									</div>
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>市场价(元)：</label>
											<span id="marketPrice1"></span>
											<input type="hidden" id="marketPrice1" name="marketPrice1"/>
										</div>
										<div class="bd_post_p_col_2">
											<label>协议价(元)：</label>
											<span id="agreePrice1"></span>
											<input type="hidden" id="agreePrice1" name="agreePrice1"/>
										</div>
									</div>
									<div class="bd_post_p_line">
										<div class="bd_post_p_col_2">
											<label>单个预算(元)：</label>
											<span><input type="text" id="goodsPrice1" name="goodsPrice1" class="bd_post_p_input small money required"/></span>
										</div>
										<div class="bd_post_p_col_2">
											<label>采购数量：</label>
											<span><input type="text" id="goodsQty1" name="goodsQty1" class="bd_post_p_input small digits required"/></span>
										</div>
										<input type="hidden" id="goodsTotal1" name="goodsTotal1"/>
									</div>
								</div>
							</div>												
						</div>
					</div>
</div>
</div>
<!-- 待克隆的需求条目 -有商品 end-->


</body>
</html>
