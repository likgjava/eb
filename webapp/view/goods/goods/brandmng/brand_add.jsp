<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增品牌-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css" media="screen"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/goods/goods/brandmng/brand_add.js'></script>
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
						<div class="bd_t_name"><img  src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_brand.jpg" /></div>
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
		    			
		    				<input type="hidden" name="isManager" id="isManager" value= "${isManager}"/>
		    				<form id="brandForm" method="post">	
		    				<input type="hidden" name="objId" id="objId" value= ""/>
		    				<input type="hidden" id="mainLogo" name="mainLogo" />
		    				
		    				<div class="bd_post_space" id="bd_post_error_spc"></div>
		    				
		    				<!--标题-->
							<div class="bd_m_c_title2">
								<div class="bd_m_c_spc"></div>
								<div class="bd_m_c_name"><span class="float-l">填写基本信息：</span>
								 	<span class="font-s feedback">对品牌属性和名称有改进建议，<a href="javascript:void(0);" onclick="brand_add.addSelfAdvise();" class="icon-feedback links">我要反馈</a> </span>
								</div>
							</div>
		    				<!--产品详细信息表单区-->
		    				<div class="bd_post_form">
		    				
		    					<div class="bd_post_form_line">
		    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>商品分类：</div>
		    						<div class="bd_post_form_context">
		    							<div class="bd_post_form_input_float">
		    								<input type="hidden" name="goodsClassIds" id="goodsClass.objId" />
											<textarea style="width:600px;height:100px;" class="required" readonly="readonly" name="goodsClassNames" id="goodsClass.name"></textarea>
											<a href="javascript:void(0);" onclick="brand_add.chooseClassByCategory();"><span class="sysicon siSearch">&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
		    							</div>
		    						</div>												
		    					</div>
		    					
								<!--品牌-->
								<div class="bd_post_form_line">
									<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>品牌名称： </div>
									<div class="bd_post_form_context" >
										<div id="brandNameDiv" class="bd_post_form_editor">
										<input type="text" name="brandName" id="brandName" class="bd_post_input_long required" onchange="brand_add.brandNameCheck(this);" onfocus="brand_add.brandNameFocus();"/>
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
												<input  type="text" id="englishName" name="englishName"  class="bd_post_p_input" onchange="brand_add.brandNameCheck(this);" onfocus="brand_add.brandNameFocus();" />
											</div>
										</div>
										
										<div class="bd_post_p_line">
											<div class="bd_post_p_name">外地品牌：</div>
											<div id="bd_post_p_form_feature2013_1" class="bd_post_p_form">
											<select id="ecdemic" name="ecdemic">
									        	<option value="false">否</option>
									        	<option value="true">是</option>
								        	</select>											
		        							</div>
										</div>
										
										<div class="bd_post_p_line">
											<div class="bd_post_p_name">国外品牌：</div>
											<div id="bd_post_p_form_feature2013_1" class="bd_post_p_form">
											<select id="foreigner" name="foreigner">
									        	<option value="false">否</option>
									        	<option value="true">是</option>
								        	</select>											
		        							</div>
										</div>
										
										</div>
		    						</div>												
		    					</div>
								
		<div class="bd_post_form_line">
			<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>所属机构：</div>
			<div class="bd_post_form_context">
			
			
				<div class="bd_post_form_input_float">
					
					<c:choose>
						<c:when test="${isManager==true }">
							<input type="hidden" name="belongsId" id="belongsId" value="${blongOrg.objId}"/>
							<input type="text" name="belongsName" id="belongsName" readonly="readonly" class="bd_post_input_long sysicon siSearch" value="${blongOrg.orgName}" onclick="brand_add.chooseOrg();"/>
						</c:when>
						<c:otherwise>
							<input type="hidden" name="belongsId" value="${blongOrg.objId}"/>
							<input type="hidden" name="belongsName" value="${blongOrg.orgName}"/>
							<span>${blongOrg.orgName }</span>
						</c:otherwise>
					</c:choose>
					
				</div>
			</div>												
		</div>
		
		<!--详细说明-->
		<a name="详细说明"></a>
		<div class="bd_post_form_line">
			<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>品牌描述： </div>
			<div class="bd_post_form_context" >
				<div class="bd_post_form_note">请详细填写您的描述</div>
				<div id="bd_post_properties" class="bd_post_form_editor">
					<textarea id="brandDesc" class="required" name="brandDesc" style="width:600px;height:50px;" maxlength="250"></textarea>
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
						<img id="view" src="<c:url value="/view/resource/skin/goods/img/brand_add.gif"/>" width="160px" height="120px" />
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
		  		<button type="button" id="saveBrandBut" onclick="brand_add.createOrUpdateBrand('save');"><span>保存</span></button>
		  		<button type="button" id="submitBrandBut" onclick="brand_add.createOrUpdateBrand('submit');"><span>提交</span></button>
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
