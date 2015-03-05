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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/pubProject.css" />
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/smallscale/groupbuying/group_buying_form.js'></script>
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
				<div class="bd_t_name" style="font-size: 17px;">发布团购信息</div>
				<div class="bd_t_guide">
					<div class="bd_t_pos pos_1_on">发布团购信息</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_2">发布成功</div>
				</div>
			</div>
			<!--导航 结束-->
			<!--内容-->
			<div class="bd_main">
				<div class="bd_m_top"></div>
				<div class="bd_m_context">	
					<form id="createGroupBuyingForm" enctype="multipart/form-data">		
						<div class="bd_post_space" id="bd_post_error_spc"><!--分割线--></div>
						<!--标题-->
						<div class="bd_m_c_title2">
							<div class="bd_m_c_spc"></div>
							<div class="bd_m_c_name"><span class="float-l">填写团购信息</span></div>
						</div>
						<!--分割线2-->
						<div class="bd_post_space2"></div>
						<!-- 填写团购信息开始 -->
	    				<div class="bd_post_form">
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>团购名称：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="hidden" name="useStatus" id="useStatus" value="00" />
										<input type="hidden" name="objId" id="objId" value="${groupBuying.objId}" />
	    								<input class="bd_post_input_long required" type="text" name="name" id="name" maxlength="100" value="${groupBuying.name }"/>
	    							</div>
	    							<div class="bd_post_form_note_float">最长100个字符（50个汉字）</div>
								</div>
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>起始时间：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<input class="bd_post_p_input required" type="text" name="startTime" id="startTime" readonly="readonly" value="<fmt:formatDate value="${groupBuying.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	    							</div>
	    							<div class="bd_post_form_note_float">团购开始时间</div>
	    						</div>												
	    					</div>
	    					<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>结束时间：</div>
	    						<div class="bd_post_form_context">
	    							<div class="bd_post_form_input_float">
	    								<input class="bd_post_p_input required" type="text" name="endTime" id="endTime" readonly="readonly" value="<fmt:formatDate value="${groupBuying.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	    							</div>
	    							<div class="bd_post_form_note_float">团购结束时间</div>
	    						</div>												
	    					</div>
						</div>
						<!-- 填写团购信息结束 -->
						<div class="bd_m_c_title2">
							<div class="bd_m_c_spc"></div>
							<div class="bd_m_c_name">商品信息</div>											
						</div>
						<div class="bd_post_space2"></div>
						<!-- 填写商品信息开始 -->
						<div class="bd_post_form">
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>选择团购分类：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="text" id="groupBuyingClassName" class="bd_post_p_input sysicon siSearch required" readonly="readonly" value="${groupBuyingClassName}" />
	    							</div>
	    							<div class="bd_post_form_note_float">先选择团购分类，然后选择该分类下的商品</div>
								</div>
	    					</div>
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>选择商品：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
	    								<input type="hidden" id="goodsClassId" name="goodsClass.objId" value="${groupBuying.goodsClass.objId }" />
	    								<input type="hidden" id="goodsId" name="goods.objId" value="${groupBuying.goods.objId }" />
										<input type="text" id="productName" class="bd_post_p_input sysicon siSearch required" readonly="readonly" value="${groupBuying.goods.productName}"/>
	    							</div>
	    							<div class="bd_post_form_note_float">去商品库挑选一件商品</div>
								</div>
	    					</div>
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>市场价：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="text" id="marketPrice" name="marketPrice" referPrice="" class="bd_post_p_input money required" value="${groupBuying.marketPrice}" />
	    							</div>
	    							<div class="bd_post_form_note_float" style="color: red;">￥<span id="showMarketPrice"></span></div>
								</div>
	    					</div>
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>团购价：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="text" id="groupPrice" name="groupPrice" class="bd_post_p_input money required" value="${groupBuying.groupPrice}" />
	    							</div>
	    							<div class="bd_post_form_note_float" style="color: red;">￥<span id="showGroupPrice"></span></div>
								</div>
	    					</div>
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>折扣：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="text" id="discount" name="discount" class="bd_post_p_input required" value="${groupBuying.discount}" />
	    							</div>
								</div>
	    					</div>
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>最低团购数：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="text" id="minNumber" name="minNumber" class="bd_post_p_input digits required" value="${groupBuying.minNumber}" />
	    							</div>
								</div>
	    					</div>
							<div class="bd_post_form_line">
	    						<div class="bd_post_form_title">最大团购数：</div>
	    						<div class="bd_post_form_context">
									<div class="bd_post_form_input_float">
										<input type="text" id="maxNumber" name="maxNumber" class="bd_post_p_input digits" value="${groupBuying.maxNumber}" />
	    							</div>
	    							<div class="bd_post_form_note_float">如果最大团购数没有限制，此项可以不填</div>
								</div>
	    					</div>
	    					<div class="bd_post_form_line">
								<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>团购描述： </div>
								<div class="bd_post_form_context" >
									<div class="bd_post_form_note">请详细填写您的团购描述</div>
									<div id="bd_post_properties" class="bd_post_form_editor">
										<textarea name="desc" id="desc" class="required" maxLength="500" style="height:60px;width:687px;">${groupBuying.desc}</textarea>
									</div>
								</div>												
							</div>
						</div>
						<!-- 填写商品信息结束 -->
						<div class="bd_m_c_title2">
							<div class="bd_m_c_spc"></div>
							<div class="bd_m_c_name">上传团购图片</div>											
						</div>
						<div class="bd_post_space2"></div>
						<div class="bd_post_form">
							<div class="bd_post_form_line">
								<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>团购图片： </div>
								<div class="bd_post_form_context" >
									<div class="k1" style="width:310px;">
										<jsp:include page="/view/srplatform/upload/img_upload_load.jsp">
										<jsp:param name="maxWidth" value="258" />
										<jsp:param name="maxHeight" value="175" />
										<jsp:param name="propertieName" value="pictureFile" />
										<jsp:param name="nopicPath" value="AttachmentController.do?method=showImg&objId=${groupBuying.picture}" />
										</jsp:include>
									</div>
								</div>												
							</div>
	    				</div>
					</form>
									
					<!--提交区域 开始-->
					<div class="bd_post_subscribe">	
						<div id="submitDiv" class="bd_post_submit">
							<a href="javascript:void(0);" class="bd_post_submit_btn" id="saveGroupBuyingBtn">保存，等等再发布</a>
							<a href="javascript:void(0);" class="bd_post_submit_btn" id="submitGroupBuyingBtn">提交，并发布信息</a>
						</div>
						<div id="submittingDiv" class="bd_post_submit hidden">
							<img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/load.gif" />保存中......
						</div>
						<div class="bd_post_submit_cls"></div>
					</div>
					<!--提交区域 结束-->
				</div>
				<div class="bd_m_bottom"></div>
			</div>
		</div>
	<div class="bd_bottom"></div>
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
