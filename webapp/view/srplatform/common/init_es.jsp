<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<c:if test="${not empty param.isLastLoadPage}">
<%
    pageContext.setAttribute("gpc", request.getContextPath());
%>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/common/plug_in.css"/>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/sysicon/sysicon_es.css"/>
<link rel="alternate stylesheet" type="text/css" href="${gpc}/view/resource/skin/skin05/css/main.css" title="skin05"/>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/bizplatform/css/orginfo_add.css"/>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/pubservice/css/my_desk.css"/>
<link href="${gpc}/view/resource/skin/skin05/base/css/myDesktop.css" type="text/css" rel="stylesheet">
<link href="${gpc}/view/resource/skin/skin05/base/css/tips.css" type="text/css" rel="stylesheet">
<link href="${gpc}/view/resource/skin/base_es/css/table2.css" type="text/css" rel="stylesheet">

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
<script type="text/javascript" src='${gpc}/view/resource/scripts/jquery/epsDatepicker/epsDatepicker.js'>//日期控件</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/jquery/epsDialog/epsDialog.js">//弹出层</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/jquery/epsContentMenu/epsContentMenu.js">//右键菜单</script>

<script type="text/javascript" src="${gpc}/view/resource/scripts/util/obj2str.js">//json对象转字符串</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/dateUtil.js">//时间处理辅助</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/select.js">//下拉框扩展方法</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/listTree.js">//树形下拉框</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/treeUtil.js">//树排序辅助</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/otherUtils.js">//其他辅助方法</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/loadPage.js" >//异步加载页面</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/sysTools.js" >//热键辅助</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/date.js" >//显示服务时间辅助</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/changeSkin.js" >//切换皮肤</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/menu_es.js" >//动态菜单</script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/sysInfo.js" >//系统信息</script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/progressbar/jquery.progressbar.js'>//显示项目进度插件</script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/FCKeditor/fckeditor.js">//fckeditor插件</script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/FusionChart/JSClass/FusionCharts.js">//图表插件</script> 
</c:if>