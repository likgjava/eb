<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<% pageContext.setAttribute("gpc", request.getContextPath()); %>

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

<script type="text/javascript" src="${gpc}/view/resource/scripts/json/json-eps.js">//json数据与页面元素转换</script>

<script type="text/javascript" src="${gpc}/view/resource/scripts/util/obj2str.js">//json对象转字符串</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/select.js">//下拉框扩展方法</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/loadPage.js" >//异步加载页面</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/otherUtils.js">//其他辅助方法</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/date.js" >//显示服务时间辅助</script>
<script type="text/javascript" src='${gpc}/view/resource/scripts/common.js'>//公有js</script>
<script type="text/javascript" src='${gpc}/view/srplatform/login/sha1.js'></script>
<style type="text/css">
 a:link {color: #000; text-decoration:none;}     
 a:active{color: #000; text-decoration:none; }     
 a:visited {color: #000; text-decoration:none;}      
 a:hover {color: #000; text-decoration:none;}  
*{ margin:0}
body{ font-size:12px}
ol ul li{ list-style-type:none}
.content{width:650px; height:405px; border:#3399d3 1px solid}
#top,#main,#foot{ overflow:hidden}
.title{ width:650px; height:27px; background-image:url(images/apply_Topbg.png) }
.nav{ width:640px; height:29px; background-color:#f2f7ff; border-top:#c4c4c4 1px solid; border-bottom:#e4e4e4 1px solid; line-height:29px; padding-left:10px}
.red{color:#F00}
.button1{ padding-left:35px; margin-top:2px;}
#main{ width:650px; height:341px; background-image:url(images/apply_bg.gif)}
#login{ width:610px; height:42px; border:#b3b2b2 1px dashed;  padding-left:12px; margin-left:10px; background-color:#b1d9eb; margin-top:8px;}
#login ul { list-style-type:none; padding-top:10px; margin:0; margin-left:-18px; *margin-left:0px;}
#login li{ height:25px; float:left; width:50%}
.mainContent1{
	width:640px;
	height:154px;
	border-bottom:#b3b2b2 1px dashed;
	margin:12px 5px 8px 5px;
*width:620px; *margin-left:20px;}
.mainContent1 ul { list-style-type:none; margin-top:10px; overflow:hidden}
.mainContent1 li{ float:left;  width:50%; height:45px; }
.mainContent{ width:605px; margin-left:5px; overflow:hidden; margin-top:8px; padding-left:26px; *padding-left:0px}
.mainContent ul{ list-style-type:none; margin-top:10px; overflow:hidden; margin:0; padding:0; padding-left:15px;}
.mainContent li{ height:35px}
.button2{ width:81px; height:23px; background-image:url(images/apply_button2.png); float:left; margin-right:25px; text-align:center;  line-height:23px}
#zc a:link {color: #F00; text-decoration:none;}     
#zc a:active{color: #F00; text-decoration:none; }     
#zc a:visited {color: #F00; text-decoration:none;}      
#zc a:hover {color: #F00; text-decoration:none;}  
input{ height:13px;}
</style>
<input type="hidden" name="initPath" id="initPath"value="${gpc }"/>
<div>
	<ul>
   		<li><label >用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label><span>${currentUser.usName }</span></li>
	</ul>
</div>
