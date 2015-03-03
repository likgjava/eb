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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/purchaserequire/requirement_form.js'></script>
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
						<div class="bd_t_name"><img  src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_buy.jpg" /></div><div class="bd_t_guide"><div class="bd_t_pos pos_1">选择类目</div>
						<div class="bd_t_pos_spc"></div><div class="bd_t_pos pos_2_on">填写信息详情</div><div class="bd_t_pos_spc"></div><div class="bd_t_pos pos_3">提交成功，等待审核</div></div>
					</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">	
		    				<form id="requirementForm">		
		    				<div class="bd_m_c_title2">
		    				<input id="auditStatus" name="auditStatus" value="01" type="hidden"/><!-- 默认待审核 -->
		    				<input id="pubStatus" name="pubStatus" value="00" type="hidden"/><!-- 默认未发布-->																	
		    					<div class="bd_m_c_spc"></div>
		    					<div class="bd_m_c_name">您选择的类目：
		    					<input id="categoryvalues" type="hidden" name="categoryvalues" value="${categoryvalues}" />
		    						<span class="bd_post_category">
		    							<span id="categoryNames">${categoryNames}</span>
		    							<a href="javascript:requirement_form.backTochooseCategory();" class="bd_recate_link" id="backTochooseCategoryBtn">返回重选类目</a>&nbsp;&nbsp;<span class="bd_font_h">返回重选类目会造成已填写的信息丢失，请慎重操作！</span>
		    						</span>
								</div>											
		    				</div>
		    				<!--分割线-->
		    				<div class="bd_post_space2"></div>										
							<!--错误提示区-->					
							
		    				<!--分割线-->
		    				<div class="bd_post_space" id="bd_post_error_spc"></div>
		    				<!--标题-->
							<a name="基本信息"></a>
							<div class="bd_m_c_title2">
								<div class="bd_m_c_spc"></div>
								 <div class="bd_m_c_name"><span class="float-l">填写基本信息</span>
								 <span class="font-s feedback">对本类目下属性和计量单位有改进建议，
								 <a href="javascript:requirement_form.addSelfAdvise();" class="icon-feedback links">我要反馈</a> </span>
								 </div>
							</div>
		    				<!--分割线2-->
		    				<!--产品详细信息表单区-->
		    				<div class="bd_post_form">
		    					<!--产品属性-->
								<a name="产品属性"></a>
		    					<div class="bd_post_form_line" id="bd_post_form_properties">
		    						<div class="bd_post_form_title">采购需求：</div>
		    						<div class="bd_post_form_context">
										<div class="bd_post_form_note">建议从以下方面描述您的求购需求</div>
		    							<div class="bd_post_properties" id="bd_post_properties">
		    							
		    							<div class="bd_post_p_line">
											<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>采购数量：</div>
												<div class="bd_post_p_form">
													<input id="purchaseQty" name="purchaseQty" maxlength="80" class="bd_post_p_input digits required" type="text" />
												</div>
										</div>
										
										<div class="bd_post_p_line">
											<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>单个预算（元）：</div>
												<div class="bd_post_p_form">
													<input id="purchaseBudget" name="purchaseBudget" class="bd_post_p_input money required" maxlength="80" type="text" />
											</div>
										</div>
										
										<div class="bd_post_p_line">
											<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>供货区域：</div>
											<div id="bd_post_p_form_feature2013_1" class="bd_post_p_form">
												<input type="hidden" name="districtId" id="district.objId" />
												<input type="text" name="districtNames" id="district.name" readonly="readonly" onclick="requirement_form.selectDistrict();return false;" class="bd_post_p_input sysicon siSearch required" maxlength="80" />
											</div>
										</div>
										
										</div>
		    						</div>												
		    					</div>
								<a name="信息标题"></a>
		    					<!--信息标题-->
		    					<div class="bd_post_form_line">
		    						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>信息标题：</div>
		    						<div class="bd_post_form_context">
		    							<div class="bd_post_form_input_float">
		    								<input class="bd_post_input_long required" id="title" maxlength="30" name="title" value="" type="text" />														
		    							</div>
		    							<div class="bd_post_form_note_float">最长30个汉字,建议在标题中包含产品名称和相应关键词</div>
		    						</div>												
		    					</div>
		<!--详细说明-->
		<a name="详细说明"></a>
		<div class="bd_post_form_line">
			<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>具体要求： </div>
			<div class="bd_post_form_context" >
				<div class="bd_post_form_note">请详细填写您的具体需求</div>
				<div id="htmlEditor"></div>
				<textarea id="discription" name="discription" class="hidden" maxlength="2000"></textarea>
				<div class="bd_post_form_note">1、请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的求购信息将无法通过审核;</div>
				<div class="bd_post_form_note">2、插入图片时可以添加外部链接。</div>
			</div>												
		</div>
		</div>
		<!--其他信息：信息有效期，联系方式，买卖速配-->
		<a name="其他信息"></a>
		<!--标题-->
		<div class="bd_m_c_title2">
			<div class="bd_m_c_spc"></div>
			<div class="bd_m_c_name">其他信息</div>											
		</div>
		<!--分割线2-->
		<div class="bd_post_space2"></div>
		<div class="bd_post_form">
			<!--联系方式-->
			<div class="bd_post_form_line">
				<div class="bd_post_form_line">
					<div class="bd_post_form_title">联系方式：</div>
					<div class="bd_post_form_context">
						<div class="bd_post_form_note">请仔细详细填写您的联系方式</div>
						<div class="bd_post_properties">
						
							<div class="bd_post_p_line">
								<div class="bd_post_p_name">联系人：</div>
								<div class="bd_post_p_form">
									<input id="linkMen" name="linkMen" type="text" value="${requirement.linkMen }" class="bd_post_p_input required" maxlength="80" />
								</div>
							</div>
							<div class="bd_post_p_line">
								<div class="bd_post_p_name">联系电话：</div>
								<div class="bd_post_p_form">
									<input id="linkTel" name="linkTel" type="text" value="${requirement.linkTel }" class="bd_post_p_input required number" maxlength="80" />
								</div>
							</div>
							<div class="bd_post_p_line">
								<div class="bd_post_p_name">电子邮件：</div>
								<div class="bd_post_p_form">
									<!-- 此input 加上email 就报错 -->
									<input id="email" name="email" type="text" value="${requirement.email }" class="bd_post_p_input required" maxlength="80" />
								</div>
							</div>
						</div>
					</div>												
				</div>
				<div class="bd_post_form_line">
					<div class="bd_post_form_title">公开联系方式：</div>
					<div class="bd_post_form_context">
						<div class="bd_post_form_note">选择否的话，则联系方式对外不公开</div>
						<div class="bd_post_form_radio"><input type="radio" name="isAnonymous" value="1" checked="checked"/></div>
						<div class="bd_post_form_radio_label_date"><label>&nbsp;是</label></div>
					
						<div class="bd_post_form_radio"><input type="radio" name="isAnonymous" value="0" /></div>
						<div class="bd_post_form_radio_label_date"><label>&nbsp;否</label></div>
					</div>
				</div>
				
						<div class="bd_post_form">
				<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>信息有效期：</div>
				<div class="bd_post_form_context">	
					<div class="bd_post_form_note">请谨慎确认您的信息有效期（一个月以30天计算）</div>
					
					<div class="bd_post_form_radio"><input type="radio" name="endTime" value="3" /></div>
					<div class="bd_post_form_radio_label_date"><label>&nbsp;3天</label></div>
					
					<div class="bd_post_form_radio"><input type="radio" name="endTime" value="7" /></div>
					<div class="bd_post_form_radio_label_date"><label>&nbsp;一周</label></div>
					
					<div class="bd_post_form_radio"><input type="radio" name="endTime" value="15" /></div>
					<div class="bd_post_form_radio_label_date"><label>&nbsp;半个月</label></div>
					
					<div class="bd_post_form_radio"><input type="radio" name="endTime" value="30" /></div>
					<div class="bd_post_form_radio_label_date"><label>&nbsp;一个月</label></div>
					
					<div class="bd_post_form_radio"><input type="radio" name="endTime" checked="checked" value="90" /></div>
					<div class="bd_post_form_radio_label_date"><label>&nbsp;三个月</label></div>
				</div>		
				</div>										
			</div>
			</div>
			</form>
						<div class="bd_post_form">											
					    			<!--产品图片-->
									<div class="bd_post_form_title">产品图片：</div>
									<div class="bd_post_form_context">
									<div class="bd_post_form_note">可上传相关产品图片，请勿在求购信息图片中添加带有公司或个人信息的水印</div>
										<div class="bd_post_form_uploader">
											<div id="ouploader">
									           <ul>
									       			<li id="oup-preview">
									       					<!-- 图片上传控件 -->
									       					<div class="" id="additionPicture"></div>
									                 </li>
									           </ul>                
											</div>
											<div class="cls"></div>
										</div>		
									</div>			
						</div>									
			
			<!--提交区域-->
				<div class="bd_post_subscribe">	
					<!-- 暂时屏蔽服务条款(因暂时没有条款内容)
					<div class="bd_post_agreement ">点此阅读<a href="javascript:void(0);" onclick="$.epsDialog({id:'',title:'服务条款',url:$('#initPath').val()+'/cms/resbase/html/service_item.html' });" >阳光易购服务条款</a></div>
					-->																							
				  	<div class="bd_post_submit">
						<a href="javascript:void(0);" onclick="requirement_form.agreedAndPublish();" class="bd_post_submit_btn" id="bd_post_submit_btn" >同意协议条款，我要发布</a>
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
