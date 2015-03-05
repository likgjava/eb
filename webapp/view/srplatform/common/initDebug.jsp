<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<% pageContext.setAttribute("gpc", request.getContextPath()); %>

<!doctype html public "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%> 
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/common/plug_in.css"/>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/skin07/css/main.css"/>

<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/plug-in/jquery/dataTables/media/css/demo_page.css">
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/plug-in/jquery/dataTables/media/css/demo_table.css">
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/plug-in/jquery/dataTables/media/css/tableForm.css">
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/pubservice/css/goods_list.css"/>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/goods/css/goods_add.css"/>
<link rel="stylesheet" type="text/css" href="${gpc}/view/resource/skin/bizplatform/css/orginfo_add.css"/>

<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.js'></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.ui.js'></script>
<script type="text/javascript" src="${gpc}/view/resource/plug-in/jquery/jquery.wresize.js" ></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.autoHeight.js'></script>
<script type="text/javascript" src='${gpc}/view/resource/plug-in/jquery/jquery.form.js'></script>

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
<div style="display:none">
<%=request.getContextPath()%>
</div>

<div id="dialogDiv"></div>
<div id="floaterDiv"></div>
<div id="content"></div>

<input type="hidden" id="initPath" value="<%=request.getContextPath()%>"></input>
<input type="hidden" id="user" value="<%=AuthenticationHelper.getCurrentUser()%>"></input>

<script>	 
var PlatForm = {}
PlatForm.Secondmenus = [];
PlatForm.Threemenus = [];
PlatForm.topMenus = [];
PlatForm.user;	 
 
	//登陆动作	
	if($('#user').val()=='null'){   	
		var josnObj={ 'j_username':'admin', 'j_password':'123456' };//登陆账号和密码
	    //获取保存当前用户在JS
		$.ajax({
			url: $('#initPath').val()+"/login.do",	    
			type:"POST",
			dataType:'json',
			async:false,
			data:josnObj,
			cache:'false',
			success:function(json){		
				//if(json.failure){if(json.result)alert(json.result);return;}
				//PlatForm.user=json.user;		
			},error : function(msg) {
				//alert('登陆失败!(init.jsp)');
			}
		});
	}	
	
    //获取保存当前用户在JS
	$.ajax({
		url: $('#initPath').val()+"/UserMenuController.do?method=loadMenu",	    
		type:"POST",
		dataType:'json',
		async:false,
		success:function(json){		
			if(json.result)alert(json.result);
			PlatForm.user=json.user;		
		},error : function(msg) {
			alert('登陆失败!(init.jsp)');
		}
	});
</script>
</html>




 