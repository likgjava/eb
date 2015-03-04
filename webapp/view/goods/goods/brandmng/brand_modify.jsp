<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改品牌-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css" media="screen"/>

<!-- 变更 -->
<c:if test="${goodsBrand.useStatus=='01'}">
	<c:set  var ="updateType" value="change"></c:set>
</c:if>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/goods/goods/brandmng/brand_modify.js'></script>

<!-- 如果是变更则导入变更JS -->
<c:if test="${updateType=='change'}">
<script type="text/javascript" src='<%=request.getContextPath()%>/view/goods/goods/brandmng/brand_change.js'></script>
</c:if>

<!-- 取出变更的字段 -->
<c:if test="${goodsBrandChangeList!=null && !(empty goodsBrandChangeList)}">
	<c:forEach var="goodsBrandChange" items="${goodsBrandChangeList}">
		<!-- 变更的品牌名称 -->
		<c:if test="${brandNameUnable!=true && brandName == null && 'brandName'==goodsBrandChange.modifyType}">
			<c:set var="brandName" value="${goodsBrandChange.auditStatus!='02'? goodsBrandChange : null}"/>
			<c:set var="brandNameUnable" value="${goodsBrandChange.auditStatus=='02' ? true : false }"/>
		</c:if>
		<!-- 变更的英文名称 -->
		<c:if test="${englishNameUnable!=true && englishName == null && 'englishName'==goodsBrandChange.modifyType}">
			<c:set var="englishName" value="${goodsBrandChange.auditStatus!='02'? goodsBrandChange : null}"/>
			<c:set var="englishNameUnable" value="${goodsBrandChange.auditStatus=='02' ? true : false }"/>
		</c:if>
		<!-- 变更的分类名称 -->
		<c:if test="${changeClassUnable!=true && changeClass == null && 'goodsClass'==goodsBrandChange.modifyType}">
			<c:set var="changeClass" value="${goodsBrandChange.auditStatus!='02'? goodsBrandChange : null}"/>
			<c:set var="changeClassUnable" value="${goodsBrandChange.auditStatus=='02' ? true : false }"/>
		</c:if>
	</c:forEach>
</c:if>
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
		    	<div class="bd_top"></div>
		    	<!--主体部分start-->
		    	
		    	
		    	<div class="bd_context">

		    		<!--导航 position-->
		    		<div class="bd_title">
			    			<div class="bd_t_name"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_brand.jpg"/></div>
			    			<div class="bd_t_guide">
			    				<div class="bd_t_pos pos_1_on">填写品牌信息</div>
			    				<div class="bd_t_pos_spc"></div>
			    				<div class="bd_t_pos pos_2">提交或保存品牌信息</div>
			    			</div>
					</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">	
		    				<c:if test="${ goodsBrand.auditStatus=='03' && goodsBrand.opinion!=null}">
							<div class="formTips attention">
								抱歉！您的品牌不符合规范没有审核通过，管理员的意见是：【${goodsBrand.opinion}】，请修改后重新提交或者删除该记录！如有异议，请联系管理员。
							</div>
		    				</c:if>
		    			
		    				<input type="hidden" name="isManager" id="isManager" value= "${isManager}"/>
		    				<form id="brandForm" method="post">	
		    				<input type="hidden" id="mainLogo" name="mainLogo" value="${goodsBrand.mainLogo}" />
		    				<div class="bd_post_space" id="bd_post_error_spc"></div>
		    				
		    				<!--标题-->
							<div class="bd_m_c_title2">		    				
								<div class="bd_m_c_spc"></div>
								<div class="bd_m_c_name"><span class="float-l">填写基本信息：</span>
								 	<span class="font-s feedback">对品牌属性和名称有改进建议，<a href="javascript:void(0);" onclick="brand_modify.addSelfAdvise();" class="icon-feedback links">我要反馈</a> </span>
								</div>
							</div>
		    				<!--产品详细信息表单区-->
		    				<div class="bd_post_form">
		    				
		    					<div class="bd_post_form_line">
		    					
		    						<c:set var="goodsClassIds" value=""/>
		    						<c:forEach var="goodsClass"  items="${goodsBrand.goodsClasses}">
		    							<c:set var ="goodsClassIds" value="${goodsClassIds}${fn:length(goodsClassIds)>0?',':''}${goodsClass.objId}"/>
		    						</c:forEach>
		    						
		    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>商品分类：</div>
		    						<div class="bd_post_form_context">
		    							<div class="bd_post_form_editor">
		    								<input type="hidden" name="goodsClassIds" id="goodsClass.objId" value="${goodsClassIds}"/>
											<c:choose>
												<c:when test="${updateType=='change'}">
													<!-- 变更 -->
													<span id="goodsClassNames">${goodsBrand.goodsClassNames}</span>
													<div class="formTips attention">
														<c:choose>
															<c:when test="${changeClass!=null}">
																<!-- 待审核 -->
																<c:if test="${changeClass.auditStatus=='01'}">
																	分类变更为【<font color="green">${fn:split(changeClass.newValue,'##')[1]}</font>】审核中，暂不支持修改
																</c:if>
																<!-- 草稿 -->
																<c:if test="${changeClass.auditStatus=='00'}">
																	分类变更为【<font color="green">${fn:split(changeClass.newValue,'##')[1]}</font>】，如需再修改，请&nbsp;<a href="javascript:void(0);" onclick="brand_change.changeProperties('goodsClass','${goodsBrand.objId}',this);">修改变更</a>
																	<input type="hidden" id="changeClass.objId" name="changeClass.objId" value="${fn:split(changeClass.newValue,'##')[0]}" />
																	<input type="hidden" id="changeClass.name" name="changeClass.name"  value="${fn:split(changeClass.newValue,'##')[1]}" />
																</c:if>
																<!-- 未通过 -->
																<c:if test="${changeClass.auditStatus=='03'}">
																	上次变更为【<font color="red">${fn:split(changeClass.newValue,'##')[1]}</font>】未功过审核，如需修改，请&nbsp;<a href="javascript:void(0);" onclick="brand_change.changeProperties('goodsClass','${goodsBrand.objId}',this);">变更
																	<input type="hidden" id="changeClass.objId" name="changeClass.objId" value="${fn:split(changeClass.newValue,'##')[0]}" />
																	<input type="hidden" id="changeClass.name" name="changeClass.name"  value="${fn:split(changeClass.newValue,'##')[1]}" />
																	</a>
																</c:if>
															</c:when>
															<c:otherwise>
															此部分数据不能随意修改，如需修改，请&nbsp;<a href="javascript:void(0);" onclick="brand_change.changeProperties('goodsClass','${goodsBrand.objId}',this);">变更</a>
															</c:otherwise>
														</c:choose>
														
													</div>
												</c:when>
												<c:otherwise>
													<!-- 非变更 -->
													<textarea style="width:600px;height:100px;" name="goodsClassNames" id="goodsClass.name">${goodsBrand.goodsClassNames}</textarea>
													<a href="javascript:void(0);" onclick="brand_modify.chooseClassByCategory();"><span class="sysicon siSearch">&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
												</c:otherwise>
											</c:choose>
		    							</div>
		    						</div>												
		    					</div>
		    					
								<!--品牌-->
								<div class="bd_post_form_line">
									<input type="hidden" name="objId" id="objId" value= "${goodsBrand.objId}"/>
									<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>品牌名称： </div>
									<div class="bd_post_form_context" >
										<div id="brandNameDiv" class="bd_post_form_editor">
											<c:choose>
													<c:when test="${updateType=='change'}">
														<!-- 变更 -->
														<input type="hidden" name="changeName"/>
														<span id="brandNameSpan">${goodsBrand.brandName}</span>
														<div class="formTips attention">
															<c:choose>
																<c:when test="${brandName!=null}">
																	<!-- 待审核 -->
																	<c:if test="${brandName.auditStatus=='01'}">
																		商品名称变更为【<font color="green">${brandName.newValue}</font>】审核中，暂不支持修改
																	</c:if>
																	<!-- 草稿 -->
																	<c:if test="${brandName.auditStatus=='00'}">
																		商品名称变更为【<font color="green">${brandName.newValue}</font>】，如需再修改，请&nbsp;<a href="javascript:void(0);" onclick="brand_change.changeProperties('brandName','${goodsBrand.objId}',this);">修改变更</a>
																	</c:if>
																	<!-- 未通过 -->
																	<c:if test="${brandName.auditStatus=='03'}">
																		上次变更为【<font color="red">${brandName.newValue}</font>】未通过，如需修改，请&nbsp;<a id="brandNameHref" href="javascript:void(0);" onclick="brand_change.changeProperties('brandName','${goodsBrand.objId}',this);">变更</a>
																	</c:if>
																</c:when>
																<c:otherwise>
																此部分数据不能随意修改，如需修改，请&nbsp;<a id="brandNameHref" href="javascript:void(0);" onclick="brand_change.changeProperties('brandName','${goodsBrand.objId}',this);">变更</a>
																</c:otherwise>
															</c:choose>
														</div>
													</c:when>
													<c:otherwise>
														<input type="text" value="${goodsBrand.brandName}" name="brandName" id="brandName" maxlength="50" class="bd_post_input_long" onchange="brand_modify.brandNameCheck(this);" onfocus="brand_modify.brandNameFocus();"/>
													</c:otherwise>
											</c:choose>
										</div>
										<div class="bd_post_form_note">品牌名称应当是真实可信的，相关部门存档备案的真实数据;</div>
									</div>												
								</div>
		    					
		    					<!--产品属性-->
		    					<div class="bd_post_form_line" id="bd_post_form_properties">
		    						<div class="bd_post_form_title">品牌基本信息：</div>
		    						<div class="bd_post_form_context">
		    						
										<div class="bd_post_form_note">建议从以下方面描述您的品牌</div>
		    							<div class="bd_post_properties" id="bd_post_properties">
		    							
										<div class="bd_post_p_line">
											<div class="bd_post_p_name">
												<span class="bd_post_form_must">&nbsp;</span>英文名称：
											</div>
											<div id="englishNameDiv" class="bd_post_p_form">
												<c:choose>
													<c:when test="${updateType=='change'}">
														<!-- 变更 -->
														<input type="hidden" name="changeEnglish"/>
														<span id="englishNameSpan">${goodsBrand.englishName}</span>
														<div class="formTips attention">
															<c:choose>
																<c:when test="${englishName!=null}">
																	<!-- 待审核 -->
																	<c:if test="${englishName.auditStatus=='01'}">
																		英文名称变更为【<font color="green">${englishName.newValue}</font>】审核中，暂不支持修改
																	</c:if>
																	<!-- 草稿 -->
																	<c:if test="${englishName.auditStatus=='00'}">
																		英文名称变更为【<font color="green">${englishName.newValue}</font>】，如需再修改，请&nbsp;<a href="javascript:void(0);" onclick="brand_change.changeProperties('brandName','${goodsBrand.objId}',this);">修改变更</a>
																	</c:if>
																	<!-- 未通过 -->
																	<c:if test="${englishName.auditStatus=='03'}">
																		上次变更为【<font color="red">${englishName.newValue}</font>】未通过审核，如需修改，请&nbsp;<a id="englishNameHref" href="javascript:void(0);" onclick="brand_change.changeProperties('brandName','${goodsBrand.objId}',this);">变更</a>
																	</c:if>
																</c:when>
																<c:otherwise>
																此部分数据不能随意修改，如需修改，请&nbsp;<a id="englishNameHref" href="javascript:void(0);" onclick="brand_change.changeProperties('brandName','${goodsBrand.objId}',this);">变更</a>
																</c:otherwise>
															</c:choose>
														</div>
													</c:when>
													<c:otherwise>
														<input  type="text" value="${goodsBrand.englishName}" id="englishName" name="englishName" maxlength="50" class="bd_post_p_input" onchange="brand_modify.brandNameCheck(this);" onfocus="brand_modify.brandNameFocus();" />
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										
										<div class="bd_post_p_line">
											<div class="bd_post_p_name">外地品牌：</div>
											<div id="bd_post_p_form_feature2013_1" class="bd_post_p_form">
								        	<html:select id="ecdemic" name="ecdemic"  selectedIndex="${goodsBrand.ecdemic==true?1:0}" >
								        		<html:option value="false">否</html:option>
								        		<html:option value="true">是</html:option>
								        	</html:select>	
		        							</div>
										</div>
										
										<div class="bd_post_p_line">
											<div class="bd_post_p_name">国外品牌：</div>
											<div id="bd_post_p_form_feature2013_1" class="bd_post_p_form">
								        	<html:select id="foreigner" name="foreigner" selectedIndex="${goodsBrand.foreigner==true?1:0}">
								        		<html:option value="false">否</html:option>
								        		<html:option value="true">是</html:option>
								        	</html:select>										
		        							</div>
										</div>
										
										</div>
		    						</div>												
		    					</div>
								
		<div class="bd_post_form_line">
			<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>所属机构：</div>
			<div class="bd_post_form_context">
				<div class="bd_post_form_input_float">
							<input type="hidden" name="belongsId" value="${goodsBrand.belongsId }"/>
							<input type="hidden" name="belongsName" value="${goodsBrand.belongsName }"/>
							<span>${goodsBrand.belongsName}</span>
				</div>
			</div>												
		</div>
		
		<!--详细说明-->
		<a name="详细说明"></a>
		<div class="bd_post_form_line">
			<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>品牌描述： </div>
			<div class="bd_post_form_context" >
				<div class="bd_post_form_note">请详细填写您的品牌描述</div>
				<div id="bd_post_properties" class="bd_post_form_editor">
					<textarea id="brandDesc" class="required" name="brandDesc" style="width:600px;height:50px;" maxlength="250">${goodsBrand.brandDesc}</textarea>
				</div>
				<div class="bd_post_form_note">1、请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的品牌信息将无法通过审核;</div>
				<div class="bd_post_form_note">2、插入图片时可以添加外部链接。</div>
			</div>												
		</div>
		
		<div class="bd_post_form_line">
			<div class="bd_post_form_title">品牌图片： </div>
			<div class="bd_post_form_context" >
				<div class="k1" style="width:170px;">
					<div class="img_250_2" id="newPreview" style="border:1px solid #D5D5D5; padding: 4px;">
						<c:choose>
						<c:when test="${goodsBrand.mainLogo==null}">
							<img src="<c:url value="/view/resource/skin/goods/img/brand_add.gif"/>" width="160px" height="120px" />
						</c:when>
						<c:otherwise>
							<img src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsBrand.mainLogo}"/>" width="160px" height="120px" />
						</c:otherwise>
						</c:choose>
					</div>
					<div style="text-align: center;"><input type="button" id="toCropZoomImgBut" value="选择图片" /></div>
				</div>
			</div>												
		</div>
		
		</div>
		</form>
		<!--提交区域-->
		
		<div class="bd_post_subscribe">	
			<div class="bd_post_agreement ">点此阅读<a href="javascript:void(0);" onclick="$.epsDialog({id:'',title:'服务条款',url:$('#initPath').val()+'/cms/resbase/html/service_item.html' });" >阳光易购服务条款</a></div>																							
		  	<div class="conOperation">
				<c:choose>
					<c:when test="${updateType=='change'}">
						<!-- 变更 -->
						<button type="button" onclick="brand_change.createOrUpdateBrand('saveChange');"><span>保存</span></button>
					  	<button type="button" onclick="brand_change.createOrUpdateBrand('submitChange');"><span>提交</span></button>
					</c:when>
					<c:otherwise>
						<!-- 非变更 -->
				  		<button type="button" onclick="brand_modify.createOrUpdateBrand('save');"><span>保存</span></button>
				  		<button type="button" onclick="brand_modify.createOrUpdateBrand('submit');"><span>提交</span></button>
					</c:otherwise>
				</c:choose>
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
</body>
</html>
