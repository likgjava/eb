<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<c:if test="${not empty param.isLastLoadPage}">
<% pageContext.setAttribute("gpc", request.getContextPath());%>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/common/plug_in.css"/>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/sysicon/sysicon.css"/>

<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.js'></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.ui.js'></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/jquery.wresize.js" ></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.autoHeight.js'></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.form.js'></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.jPrintArea.js'></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/FusionChart/JSClass/FusionCharts.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/FusionChart/JSClass/FusionChartsExportComponent.js"></script>

<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/flexigrid/flexigrid.js"></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/dataTables/media/js/jquery.dataTables.js'></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/dhtmlxTree/dhtmlxcommon.js"></script>  
<script type="text/javascript" src="${gpc}/view/resource/plug-in/dhtmlxTree/dhtmlxtree.js"></script>
<SCRIPT type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/autocomplete/jquery.autocomplete.js"></SCRIPT>
<script type="text/javascript" src="${gpc}/view/resource/scripts/jquery/epsValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/select/jQuery.CascadingSelect.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/select/jQuery.FillOptions.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/select/jquery.dxCombobox.js"></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/progressbar/jquery.progressbar.js'></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/jquery.bxGallery.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/ajaxpager/jquery.ajaxpager.js"></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/imgPlay/imgPlay.js'></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.scrollto.js'></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.qtip-1.0.0-rc3.js'></script>

<script type="text/javascript" src='${gpc}/view/resource/scripts/jquery/epsDatepicker/epsDatepicker.js'></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/jquery/epsDialog/epsDialog.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/jquery/epsContentMenu/epsContentMenu.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/json/json-eps.js"></script>

<script type="text/javascript" src="${gpc}/view/resource/scripts/util/obj2str.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/dateUtil.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/select.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/listTree.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/treeUtil.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/tabSet.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/otherUtils.js"></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/loadPage.js" ></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/sysTools.js" ></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/date.js" ></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/changeSkin.js" ></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/sysInfo.js" ></script>
<script type="text/javascript" src="${gpc}/view/resource/scripts/util/evaluate.js"></script>

</c:if>