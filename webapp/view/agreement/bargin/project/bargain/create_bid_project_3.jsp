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
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/create_bid_project_3.js'></script>

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
							<div class="bd_t_pos pos_2">设定规则轮次</div>
							<div class="bd_t_pos_spc"></div>
							<div class="bd_t_pos pos_3_on">填写其他信息并发布项目</div>
						</div>
					</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">	
		    			<form id="CreateProjectForm3" method="post">		
		    			<!--项目ID-->
		    			<input type="hidden" name="projId" id="projId" value="${projId}" />
		    			<input type="hidden" name="companyId" id="companyId" value="${companyId}" />
		    			<input type="hidden" name="project.objId" id="project.objId" value="${projId}" />
		    			
		    				<!--分割线-->
		    				<div class="bd_post_space" id="bd_post_error_spc"></div>
		    				<!--标题-->
							<div class="bd_m_c_title2">
								<div class="bd_m_c_spc"></div>
								 <div class="bd_m_c_name"><span class="float-l">付款方式信息</span></div>
							</div>
							<!--分割线2-->
							<div class="bd_post_space2"></div>
		    				<div class="bd_post_form">
		    					<div class="bd_post_form_line">
									<div class="bd_post_form_title">付款方式：</div>
									<div class="bd_post_form_context">
										<div class="bd_post_form_note">请详细填写付款方式</div>
										<div class="bd_post_properties">
											<div class="bd_post_p_line">
												<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>交货日期：</div>
												<div class="bd_post_p_form">
													<input class="bd_post_input_long required" type="text" name="deliveryDate" id="deliveryDate" maxlength="100"/>
												</div>
											</div>
											<div class="bd_post_p_line">
												<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>交货地点：</div>
												<div class="bd_post_p_form">
													<input class="bd_post_input_long required" type="text" name="deliveryAddress" id="deliveryAddress"/>
												</div>
											</div>
											<div class="bd_post_p_line">
												<div class="bd_post_p_name">交货方式：</div>
												<div class="bd_post_p_form">
													<html:select selectedValue="0" styleClass="required" id="deliveryType" name="deliveryType" code="biz.project.deliveryType"></html:select>
												</div>
											</div>
											<div class="bd_post_p_line">
												<div class="bd_post_p_name">付款方式：</div>
												<div class="bd_post_p_form">
													<html:select selectedValue="0" styleClass="bd_post_p_input required" id="payType" name="payType" code="biz.project.payType"></html:select>
												</div>
											</div>
											<div class="bd_post_p_line">
												<div class="bd_post_p_name">其他补充说明：</div>
												<div class="bd_post_p_form">
													<input class="bd_post_input_long" type="text" name="supplement" id="supplement" maxlength="500"/>
												</div>
											</div>
										</div>
									</div>												
								</div>
							</div>
							
		<div class="bd_m_c_title2">
			<div class="bd_m_c_spc"></div>
			<div class="bd_m_c_name">联系方式信息</div>											
		</div>
		<!--分割线2-->
		<div class="bd_post_space2"></div>
		<div class="bd_post_form">
				<div class="bd_post_form_line">
					<div class="bd_post_form_title">联系方式：</div>
					<div class="bd_post_form_context">
						<div class="bd_post_form_note">请详细填写您的联系方式</div>
						<div class="bd_post_properties">
							<div class="bd_post_p_line">
								<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>联系人：</div>
								<div class="bd_post_p_form">
									<input class="bd_post_input_long required" value="${contactName}" type="text" name="linker" id="linker" maxlength="100"/>
		    						<a href="javascript:void(0);" onclick="CreateProjectForm3.chooseLinker();return false;">>>点击选择联系人</a>
								</div>
							</div>
							<div class="bd_post_p_line">
								<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>移动电话：</div>
								<div class="bd_post_p_form">
									<input class="bd_post_input_long required cnMobile" type="text" value="${contact.mobilePhone}" name="mobilePhone" id="mobilePhone"/>
								</div>
							</div>
							<div class="bd_post_p_line">
								<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>固定电话：</div>
								<div class="bd_post_p_form">
									<input class="bd_post_input_long required cnPhone" type="text" value="${contact.tel}" name="fixedTelephone" id="fixedTelephone"/>
								</div>
							</div>
							<div class="bd_post_p_line">
								<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>传真：</div>
								<div class="bd_post_p_form">
									<input class="bd_post_input_long required cnPhone" value="${contact.fax}" type="text" name="fax" id="fax"/>
								</div>
							</div>
							<div class="bd_post_p_line">
								<div class="bd_post_p_name"><span class="bd_post_form_must">&nbsp;</span>联系地址：</div>
								<div class="bd_post_p_form">
									<input class="bd_post_input_long required" value="${contact.address}" type="text" name="address" id="address"/>
								</div>
							</div>
							<div class="bd_post_p_line">
								<div class="bd_post_p_name">邮编：</div>
								<div class="bd_post_p_form">
									<input class="bd_post_input_long cnZipCode" type="text" value="${contact.postCode}" name="postCode" id="postCode" maxlength="500"/>
								</div>
							</div>
						</div>
					</div>												
				</div>
			</div>
			
		<div class="bd_m_c_title2">
			<div class="bd_m_c_spc"></div>
			<div class="bd_m_c_name">供应商资质信息</div>											
		</div>
			<!--分割线2-->
			<div class="bd_post_space2"></div>
			<div class="bd_post_form">
				<div class="bd_post_form_line">
					<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>供应商资质描述： </div>
					<div class="bd_post_form_context" >
						<div class="bd_post_form_note">请详细填写供应商应具备的资质</div>
						<div id="bd_post_properties" class="bd_post_form_editor">
							<textarea name="companyQualification" class="required" id="companyQualification" maxLength="500" style="height:60px;width:687px;"></textarea>
						</div>
						<div class="bd_post_form_note">请勿在详细说明中出现您的公司名、电话、电子邮箱、传真等联系信息，出现上述内容的求购信息将无法通过审核;</div>
					</div>												
				</div>
			</div>
			
			<div class="bd_m_c_title2">
				<div class="bd_m_c_spc"></div>
				<div class="bd_m_c_name">评审规则文件</div>											
			</div>
			<!--分割线2-->
			<div class="bd_post_space2"></div>
			<div class="bd_post_form">
				<div class="bd_post_form_line">
					<div class="bd_post_form_title">评审规则文件： </div>
					<div class="bd_post_form_context" >
						
						<div id="bd_post_properties" class="bd_post_form_editor">
							<input name="chooseFile" value="choose" onclick="CreateProjectForm3.chooseFileChange();" id="chooseFromSys" type="radio"  checked="checked"/>
							<span id="assessmentFile_name"></span>
							<input type="hidden" id="assessmentFile.objId" name="assessmentFile.objId"/>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="CreateProjectForm3.chooseRuleFile();">>>点击从已有的文件中选择</a>
						</div>
						
						<div id="bd_post_properties" class="bd_post_form_editor">
							<input name="chooseFile" value="upload" onclick="CreateProjectForm3.chooseFileChange();" id="chooseUpload" type="radio"/>上传一个评审规则<input type="file" id="assessment_File" name="assessment_File" disabled="disabled"/>
						</div>
						
						<div class="bd_post_form_note">1、若您选择已有文件，请选择比较合适您的评审规则文件。2、若选择上传规则文件，请仔细确认您的评审规则文件无误后上传提交;</div>
					</div>												
				</div>
			</div>
			
			</form>
			
			<!--提交区域-->
			<div class="bd_post_subscribe">	
			  	<div id="submitDiv" class="bd_post_submit">
					<a href="javascript:void(0);" class="bd_post_submit_btn" id="bd_post_save_btn" >保存，等等再发布</a>
					<a href="javascript:void(0);" class="bd_post_submit_btn" id="bd_post_submit_btn" >提交并发布项目</a>
				</div>
				<div id="submittingDiv" class="bd_post_submit hidden">
					<img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/load.gif"/> 保存中......
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
