<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/>
<title>阳光易购-中国权威的电子采购与招标第三方公共服务平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/pubProject.css" media="screen"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/smallscale/groupbuying/group_buyer_form.js'></script>
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
	<div id="bd">
		<!--主体部分start-->
		<div class="bd_context">
			<!--导航 开始-->
			<div class="bd_title">
				<div class="bd_t_name" style="font-size: 17px;">我要团购</div>
				<div class="bd_t_guide">
					<div class="bd_t_pos pos_1_on">填写订单信息</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_2">网银支付</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_3">团购成功</div>
				</div>
			</div>
			<!--导航 结束-->
			<!--内容-->
			<div class="bd_main">
				<div class="bd_m_top"></div>
				<div class="bd_m_context">	
					<form:form id="groupBuyerForm" modelAttribute="groupBuyer">
						<input type="hidden" id="objId" name="objId" value="${groupBuyer.objId}"/>
						<input type="hidden" id="objId" name="groupBuying.objId" value="${groupBuying.objId}"/>
						<input type="hidden" id="objId" name="orgInfo.objId" value="${groupBuyer.orgInfo.objId}"/>
						<input type="hidden" id="provinceId" name="province.objId" value="${groupBuyer.province.objId}"/>
						<div class="bd_post_space" id="bd_post_error_spc"><!--分割线--></div>
						<!--标题-->
						<div class="bd_m_c_title2">
							<div class="bd_m_c_spc"></div>
							<div class="bd_m_c_name"><span class="float-l">填写收货人信息</span></div>
						</div>
						<!--分割线2-->
						<div class="bd_post_space2"></div>
						<!-- 填写团购信息开始 -->
	    				<div class="bd_post_form">
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>收货人：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="text" name="receiveName" value="${groupBuyer.receiveName}" class="required"/>
	    							</div>
								</div>
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>省份：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<form:select path="" id="province" cssClass="required">
											<c:if test="${expertInfo.district == null}">
							            		<form:option value="">请选择</form:option>
							            	</c:if>
							            	<form:options items="${provinceList}" itemValue="code" itemLabel="name" /> 
							            </form:select>
	    							</div>
	    						</div>												
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>手机号码：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<input type="text" name="mobilePhone" value="${groupBuyer.mobilePhone}" class="cnMobile required" />
	    							</div>
	    						</div>		
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title">固定电话：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<input type="text" name="fixedPhone" value="${groupBuyer.fixedPhone}" class="cnPhone" />
	    							</div>
	    						</div>		
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>电子邮件：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<input type="text" name="email" value="${groupBuyer.email}" class="email required" />
	    							</div>
	    						</div>		
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title">邮政编码：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<input type="text" name="postCode" value="${groupBuyer.postCode}" class="cnZipCode" />
	    							</div>
	    						</div>		
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>详细地址：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<input type="text" name="address" size="40" value="${groupBuyer.address}" class="bd_post_input_long required" />
	    							</div>
	    						</div>												
	    					</div>
						</div>
						<!-- 填写团购信息结束 -->
						<div class="bd_m_c_title2">
							<div class="bd_m_c_spc"></div>
							<div class="bd_m_c_name">选择配送方式</div>											
						</div>
						<div class="bd_post_space2"></div>
						<!-- 填写商品信息开始 -->
						<div class="bd_post_form">
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>配送方式：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
	    								<input type="radio" name="shippingMethod" value="00" checked="checked" />快递  
										<input type="radio" name="shippingMethod" value="01" />平邮  
										<input type="radio" name="shippingMethod" value="02" />EMS
										<input type="hidden" name="paymentMethod" value="01" />
	    							</div>
								</div>
	    					</div>
						</div>
						<!-- 填写商品信息结束 -->
						<div class="bd_m_c_title2">
							<div class="bd_m_c_spc"></div>
							<div class="bd_m_c_name">商品清单</div>
						</div>
						<div class="bd_post_space2"></div>
						<div class="bd_post_form">
							<div class="bd_post_form_line">
								<table class="frontTableList" id="goodsAndOption">
								<thead>
									<tr valign="middle">
										<td align="center" valign="middle" bgcolor="#FFF4D7" >商品编号</td>
										<td align="center" valign="middle" bgcolor="#FFF4D7">商品名称</td>
										<td align="center" valign="middle" bgcolor="#FFF4D7">团购价</td>
										<td align="center" valign="middle" bgcolor="#FFF4D7">商品数量</td>
										<td align="center" bgcolor="#FFF4D7" style="width: 100px;">总价</td>
									</tr>
								</thead>
								<tbody>
									<tr valign="middle">
										<td align="center" bgcolor="#FFFDDD">${groupBuying.goods.goodsCode}</td>
										<td align="left" bgcolor="#FFFDDD" style="padding-left:5px;">${groupBuying.goods.productName}</td>
										<td align="center" bgcolor="#FFFDDD">￥<fmt:formatNumber value="${groupBuying.groupPrice}" pattern="#,##0.00#" /></td>
										<td align="center" bgcolor="#FFFDDD">
											<a href="javascript:void(0);" onclick="GroupBuyerForm.updateAmount('-');">-</a>
											<input type="hidden" id="groupPrice" value="${groupBuying.groupPrice}" />
											<input type="hidden" id="maxNumber" value="${groupBuying.maxNumber}" />
											<input type="hidden" id="currentNumber" value="${groupBuying.currentNumber}" />
											<input type="text" id="amount" name="amount" value="${groupBuyer.amount}" size="3" maxlength="4" style="width: 35px;" onkeyup="GroupBuyerForm.updateAmount();"/>
											<a href="javascript:void(0);" onclick="GroupBuyerForm.updateAmount('+');">+</a>
										</td>
										<td align="center" valign="middle" bgcolor="#FFFDDD">￥<span id="totalPrice">${groupBuying.groupPrice}</span></td>
									</tr>
								</tbody>
								</table>
							</div>
	    				</div>
					</form:form>
									
					<!--提交区域 开始-->
					<div class="bd_post_subscribe">
						<div id="submitDiv" class="bd_post_submit">
							<c:choose>
								<c:when test="${groupBuying.maxNumber==null || (groupBuying.maxNumber - groupBuying.currentNumber > 0)}">
									<a href="javascript:void(0);" class="bd_post_submit_btn" id="saveGroupBuyerBut">提交订单</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0);" class="bd_post_submit_btn">对不起，该商品已卖完</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="bd_post_submit_cls"></div>
					</div>
					<!--提交区域 结束-->
				</div>
			</div>
		</div>
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