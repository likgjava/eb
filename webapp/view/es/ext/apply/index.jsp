<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    pageContext.setAttribute("gpc", request.getContextPath());
%>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.js'>//核心</script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.form.js'>//异步提交</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/jquery/epsValidate/jquery.validate.js">//表单验证</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/json/json-eps.js">//json数据与页面元素转换</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/loadPage.js" >//异步加载页面</script>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
.mainContent1{
	width:640px;
	height:154px;
	margin:0px 5px 8px 5px;
*width:620px; *margin-left:20px;}
.mainContent1 ul { list-style-type:none; margin-top:10px; overflow:hidden;}
.mainContent1 li{ float:left;  width:280px; height:45px;border:1px solid #CCCCCC;border-collapse:collapse; line-height:45px;margin-left:-1px; padding-left:5px; margin-top:-1px }

.mainContent{ width:605px; margin-left:5px; overflow:hidden; margin-top:8px; padding-left:26px; *padding-left:0px}
.mainContent ul{ list-style-type:none; margin-top:10px; overflow:hidden; margin:0; padding:0; padding-left:15px;}
.mainContent li{ height:35px}
.button2{ width:81px; height:23px; background-image:url(<%=request.getContextPath()%>/view/es/ext/apply/images/apply_button2.png); float:left; margin-right:25px; text-align:center;  line-height:23px}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

</head>

<body>   
<!--main begin-->
<div id="main" >
    <input type="hidden" id="pageTag" value="apply"/>
    <input type="hidden" id="packCode" value=""/>
    <input type="hidden" id="pack" value="${pack}"/>
	<input type="hidden" name="initPath" id="initPath" value="${gpc}"/>
	<input type="hidden" name="signUpFlag"  value="${signUpFlag}"/>
	<input type="hidden" id="projectId" name="projectId" value="<%=request.getParameter("projectId") %>"/>
	<input type="hidden" value="${isNotPacked}" id="isPacked"/>
		<div id="loginEXT"></div>
		<div id="packInfo">
	  		<ul style="margin-left:30px;">
	  			<li style="padding-left:15px; margin-left:5px; margin-top:15px; width:555px; list-style-type:none; height:30px; line-height:30px; border:1px solid #CCCCCC;"><label >用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label><span id="usNameView"></span></li>
    		</ul>
		</div>
	<form id="signUprecordForm1" method="post"  action="${gpc}/SignUprecordExtController.do?method=saveSignUprecord">
		<input type="hidden" id="project.objId" name="project.objId" value="${project.objId}"/>
		<input type="hidden" name="auditStatus" id="auditStatus" value="00"/>
		<input type="hidden" name="applyStatus" id="applyStatus" value="00"/>
		<input type="hidden" name="userName" id="userName"  value="${currentUser.usName }"/>
		<input type="hidden" value="" name="subPrjIds" id="subPrjIds"/>
        <div id="packInfo2" style="border-top: 1px solid rgb(204, 204, 204); border-left: 1px solid rgb(204, 204, 204); border-right: 1px solid rgb(204, 204, 204); padding-left: 15px; margin-left: 35px; margin-bottom: -10px; margin-top: 15px; width: 556px; list-style-type: none; height: 45px; line-height: 45px;">
         <c:if test="${isNotPacked=='isNotPacked'}">
         	<label  class="short">项目名称:${project.projName}</label>
         </c:if>
          <c:if test="${isNotPacked!='isNotPacked'}">
           <label  class="short" id="packStart">选择<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>：</label>
        		<c:forEach  items="${subProjs}" var="subProj">
          			<input type="checkbox" name="sub" value="${subProj.objId}" id="sub" code="${subProj.projCode}" />${subProj.projName}
        		</c:forEach>
           </c:if> 
        </div>
		<div class="mainContent1" id="signupForm">
		<ul style="margin-left:15px;"> 
			<li style="margin-top:0;margin-left:0;" ><label>联&nbsp;&nbsp;系&nbsp;&nbsp;人：</label><span ><input name="linker" id="linker"  type="text"/></span><span style="color: red;">&nbsp;*&nbsp;</span></li>
     		<li style="margin-top:0;" ><label>联系电话：</label><span><input name="linkerTel"  id="linkerTel" type="text" /></span><span style="color: red;">&nbsp;*&nbsp;</span></li>
     		<li style="margin-left:0;"><label>联系地址：</label><span><input name="address" id="address" type="text" /></span><span style="color: red;">&nbsp;*&nbsp;</span></li>
     		<li><label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</label><span ><input name="zipCode" id="zipCode" type="text" /></span><span style="color: red;">&nbsp;*&nbsp;</span></li>
     		<li style="margin-left:0;"><label >身份证号：</label><span><input name="idCard" id="idCard" type="text" /></span><span style="color: red;">&nbsp;*&nbsp;</span></li>
     		<li><label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label><span ><input name="memo" id="memo" type="text" /></span></li>
	  	</ul>
		</div>
  	</form>
  		 <div class="mainContent" id="formButton" style="margin-top:5px;*margin-top:10px;">
	     <div class="button2" id="submitBtn"  style="margin-left:250px"><a href="#" style="text-decoration:none">提&nbsp;&nbsp;交</a></div>
	     </div>
</div> 
</body>
</html>
 <script language="javascript" type="text/javascript">    
        $(document).ready(function(){
            $("#signupForm").hide();
        	$("#formButton").hide();
        	$("#formNote").hide();
        	$("#packInfo").hide();
        	$("#packInfo2").hide();
            if($("#userName").val()){
            	$("#usNameView").append($("#userName").val());
            	$("#signupForm").show();
            	$("#formButton").show();
            	$("#formNote").show();
            	$("#packInfo").show();
            	$("#packInfo2").show();

            	if($("#pack").val()!= null && $("#pack").val()!=""){
                	var pack = $("#pack").val();
                	$("input[type='checkbox']").attr("disabled","disabled");
					$("input[code='"+pack+"']").attr("checked","checked");
					$("#packCode").val(pack);
	                }
				
            	
            }else{
                $("#loginEXT").loadPage($('#initPath').val()+"/view/srplatform/login/loginEXT.jsp?projectId="+$("input[id=project.objId]").val());
                $("#signupForm").hide();
                $("#formButton").hide();
                $("#formNote").hide();
                $("#packInfo").hide();
                $("#packInfo2").hide();
            }
			$("#submitBtn").click(function(){
			    if($("#linker").val()==""){
					alert("请填写联系人！");
					return false;
				}else if($("#linkerTel").val()==""){
					alert("请填写联系电话！");
					return false;
				}else if($("#address").val()==""){
					alert("请填写联系地址！");
					return false;
				}else if($("#zipCode").val()==""){
					alert("请填写邮编！");
					return false;
				}else if($("#idCard").val()==""){
					alert("请填写身份证号！");
					return false;
				}

				if(confirm('确定要提交么？')){
					if($("input[name=signUpFlag]").val()=='isNotSignUp'){
						alert("现在不能报名！");
						return false;
					}
					
					var a="";
					$("input[id='sub']").each(function(i,n){
						if($(n).attr("checked")==true){
							a = a+$(n).val()+",";
						}
					})
					$("#subPrjIds").val(a.substring(0,a.length-1));
					if($("#isPacked").val()!='isNotPacked'){
						if($("#subPrjIds").val()==""||$("#subPrjIds").val()==null){
							alert("请选择包组！");
							return false;
						}
					}
					//提交时将选择的包组编码查出放入input中
					if($("#pack").val()== null || $("#pack").val()==""){
						var packIds = '';
						$("input[type=checkbox]:checked").each(function(){
							packIds += $(this).attr('code') + ',' ;
						})
						$("#packCode").val(packIds);
					}
					
					$("#signUprecordForm1").submit();
				}
			})
        })
  </script>  