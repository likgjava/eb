<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>创建项目-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css" media="screen"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
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
	<div class="page" style="min-height:300px!important;">
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
    			<div class="bd_t_name"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_project.jpg" /></div>
    		</div>
    		<!--内容-->
    		<div class="bd_main">
    			<div class="bd_m_top"></div>
    			<div class="bd_m_context">
    				<div class="bd_offer_success_cls"></div>
    				<div class="bd_offer_success">
    					<c:if test="${param.projectStatus=='00'}">
	    					<div class="bd_offer_s_status1">项目保存成功！
	    						<div id="submitDiv" class="bd_offer_s_btn">
		    						<a href="javascript:void()0;" id="submitProject" projectid="${param.projId}" class="bd_offer_s_goon">发布该项目</a>&nbsp;&nbsp;
		    					</div>
		    					<div id="submittingDiv" class="bd_offer_s_btn hidden">
		    						<img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/load.gif" />正在发布项目...
		    					</div>
	    					</div>
	    					<div class="bd_offer_s_others">您保存的尚未提交，请进入商务室发布该项目或点击上面的按钮进行发布</div>
    					</c:if>
   						<c:if test="${param.projectStatus=='01'}">
   							<div class="bd_offer_s_status1">项目发布成功！</div>
	    					<div class="bd_offer_s_others">您可以在首页查询到所发布的项目</div>
   						</c:if>
    					<div class="bd_offer_s_btn">
    						<a href="<%=request.getContextPath()%>/ModelIndexController.do?method=toDeskTopIndex" class="bd_offer_s_scan">进入您的商务室管理您发布的项目信息</a>
    					</div>
    				</div>
    				<div class="bd_offer_success_cls_bottom"></div>
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
<script type="text/javascript">
$(document).ready(function(){	
	//发布项目
	$('#submitProject').click(function(){
		if(window.confirm('确认发布该项目?')) {
			$('#submitDiv').addClass('hidden');
			$('#submittingDiv').removeClass('hidden');
			$.getJSON($('#initPath').val() + "/BargainProjectController.do?method=toSubmitProject&projectId="+$(this).attr('projectid'),function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				window.location.href=$('#initPath').val()+'/BargainProjectController.do?method=toProjectSubmitResultView&projectStatus=01';
			});
		}
	})
})
</script>
</body>
</html>
