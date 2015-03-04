<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    pageContext.setAttribute("gpc", request.getContextPath());
%>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.js'>//核心</script>

<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/dataTables/media/js/jquery.dataTables.js'></script>

<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.form.js'>//异步提交</script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.ui.js'>//ui合集</script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/jquery.wresize.js" >//小补丁</script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/flexigrid/flexigrid.js">//列表</script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/dhtmlxTree/dhtmlxcommon.js">//树commom</script>  
<script type="text/javascript" src="${gpc}/view/resource/plug-in/dhtmlxTree/dhtmlxtree.js">//树</script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/select/jQuery.CascadingSelect.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/select/jQuery.FillOptions.js"></script>
<SCRIPT type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/autocomplete/jquery.autocomplete.js">//智能搜索</SCRIPT>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.autoHeight.js'>//自动高度</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/jquery/epsValidate/jquery.validate.js">//表单验证</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/otherUtils.js">//其他辅助方法</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/json/json-eps.js">//json数据与页面元素转换</script>

<script type="text/javascript" src="${gpc}/view/resource/scripts/util/obj2str.js">//json对象转字符串</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/select.js">//下拉框扩展方法</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/loadPage.js" >//异步加载页面</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/date.js" >//显示服务时间辅助</script>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
 a:link {color: #000; text-decoration:none;}     
 a:active{color: #000; text-decoration:none; }     
 a:visited {color: #000; text-decoration:none;}      
 a:hover {color: #000; text-decoration:none;}  
*{ margin:0}
body{ font-size:12px}
ol ul li{ list-style-type:none}
.content{width:99%; height:100%;}
#top,#main,#foot{ overflow:hidden}
.title{ width:650px; height:27px; background-image:url(<%=request.getContextPath()%>/view/es/ext/apply/images/apply_Topbg.png) }
.nav{ width:640px; height:29px; background-color:#f2f7ff; border-top:#c4c4c4 1px solid; border-bottom:#e4e4e4 1px solid; line-height:29px; padding-left:10px}
.red{color:#F00}
.button1{ padding-left:35px; margin-top:2px;}
#main{ width:100%; height:342px; background-image:url(<%=request.getContextPath()%>/view/es/ext/apply/images/apply_bg.gif)}
#login{ width:610px; height:42px; border:#b3b2b2 1px dashed;  padding-left:12px; margin-left:10px; background-color:#b1d9eb; margin-top:8px;}
#login ul { list-style-type:none; padding-top:10px; margin:0; margin-left:-18px; *margin-left:0px;}
#login li{ height:25px; float:left; width:50%}
.mainContent1{
	width:640px;
	height:154px;
	margin:12px 5px 8px 5px;
*width:620px; *margin-left:20px;}
.mainContent1 ul { list-style-type:none; margin-top:10px; overflow:hidden}
.mainContent1 li{ float:left;  width:280px; height:45px; border:1px solid #CCCCCC;border-collapse:collapse; line-height:45px;margin-left:-1px; padding-left:5px; margin-top:-1px }
.mainContent{ width:605px; margin-left:5px; overflow:hidden; margin-top:8px; padding-left:26px; *padding-left:0px}
.mainContent ul{ list-style-type:none; margin-top:10px; overflow:hidden; margin:0; padding:0; padding-left:15px;}
.mainContent li{ height:35px}
.button2{ width:81px; height:23px; background-image:url(<%=request.getContextPath()%>/view/es/ext/apply/images/apply_button2.png); float:left; margin-right:25px; text-align:center;  line-height:23px}
#zc a:link {color: #F00; text-decoration:none;}     
#zc a:active{color: #F00; text-decoration:none; }     
#zc a:visited {color: #F00; text-decoration:none;}      
#zc a:hover {color: #F00; text-decoration:none;}  
input{ height:13px;}



</style>
</head>

<body>
<div class="content">
	<!--main begin-->
	<div id="main">
		<input type="hidden" id="operTag" value="1"/>
		<input type="hidden" id="packCode" value=""/>
		<input type="hidden" id="pageTag" value="result"/>
		<input type="hidden" id="objId" name="objId" value=""/>
		<input type="hidden" id="project.objId" name="project.objId" value="${project.objId}"/>
		<input type="hidden" name="auditStatus" id="auditStatus" value="00"/>
		<input type="hidden" name="applyStatus" id="applyStatus" value="00"/>
		<input type="hidden" name="initPath" id="initPath"value="${gpc }"/>
		<input type="hidden" name="userName" id="userName"  value="${currentUser.usName }"/>
		<input type="hidden" value="${isNotPacked}" id="isPacked"/>
		<input type="hidden" value="${project.projCode}" id="projCode"/>
		<c:set var="signUprecordtemp"  value=""></c:set>
		<fieldset class="operationDiv" style="margin-top:30px">
			<c:set var="signUprecordtemp"  value=""></c:set>
			<legend><dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out></legend> 
			<div id="loginEXT" >
	     		<ul style="margin-left:30px;">
	            <c:if test="${isNotPacked!='isNotPacked'}">
	       		<li style="list-style-type:none;"><label  class="short" id="packStart">选择<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>：</label>
	        	<c:forEach items="${subProjs}" var="subProj" >
	           		<input type="checkbox" name="sub"  disabled="disabled" code="${subProj.projCode}"
	          		<c:forEach  items="${signUprecordList}" var="signUprecord" >
	               	<c:if test="${subProj.objId == signUprecord.project.objId}">checked="checked" </c:if>
	               	</c:forEach>   
	               	value="${subProj.objId}" id="sub" />${subProj.projName}
	            </c:forEach>
	            </li>
	            </c:if>
	  			<c:if test="${isNotPacked=='isNotPacked'}">
	          		<c:forEach  items="${signUprecordList}" var="signUprecord" >
	          		<c:set var="signUprecordtemp"  value="${signUprecord}"></c:set>
	          		</c:forEach>
	     		</c:if>
	     		<li style="padding-left:10px; margin-left:5px; margin-top:15px; width:560px; list-style-type:none; height:30px; line-height:30px; border:1px solid #CCCCCC;"><label>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label><span id="usNameView"></span></li>
	          	</ul>
			</div>
	  		<c:set var="count_no"  value="${0}"></c:set>
	    	<c:forEach  items="${signUprecordList}" var="signUprecordtemp" >
	   			<c:if test="${count_no == 0}">
	      		<div class="mainContent1" id="signupForm">
			  		<ul style="margin-left:15px;">  
			     		<li style="margin-top:0;margin-left:0;"><label>联&nbsp;&nbsp;系&nbsp;&nbsp;人：</label><span >${signUprecordtemp.linker }</span></li>
			        	<li style="margin-top:0;"><label>联系电话：</label><span>${signUprecordtemp.linkerTel }</span></li>
			         	<li style="margin-left:0;"><label>联系地址：</label><span>${signUprecordtemp.address }</span></li>
			          	<li><label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</label><span >${signUprecordtemp.zipCode }</span></li>
			          	<li style="margin-left:0;"><label >身份证号：</label><span>${signUprecordtemp.idCard }</span></li>
			          	<li><label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label><span >${signUprecordtemp.memo }</span></li>
			   		</ul> 
				</div>
			  	</c:if>
			  	<c:set var="count_no"  value="${count_no+1}"></c:set>
			</c:forEach>          
		</fieldset>
	</div> 
</div>
</body>
</html>
 <script language="javascript" type="text/javascript">    
        $(document).ready(function(){
        	$("#signupForm").hide();
          	$("#formButton").hide();
          	$("#formNote").hide();
          	
              if($("#userName").val()){
              	$("#usNameView").append($("#userName").val());
              	$("#signupForm").show();
              	$("#formButton").show();
              	$("#formNote").show();
            	if($("#isPacked").val()=='isNotPacked'){ //如果该项目未分包
    				$("#packCode").val($("#projCode").val());
                }else{ //将勾选的包组code放入隐藏域中
                	var packIds = '';
        			$("input[type=checkbox]:checked").each(function(){
        				packIds += $(this).attr('code') + ',' ;
        			})
        			$("#packCode").val(packIds);  
                }
              }else{
                  $("#signupForm").hide();
                  $("#formButton").hide();
                  $("#formNote").hide();
              }
        })
  </script>  