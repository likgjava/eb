<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<div class="treePage frameMainSub frameMs12 ">
	<div class="treeOutside frameMain" style="height: 380px;width: 630px;">
		  <div id="orgnizationTree" style="overflow-x: auto;overflow-y: hidden;"  class="treeContentDiv"></div>
	</div>
	<div class="conOperation">
		<button name="return_to_node_form" type="button" tabindex="19"><span>清空</span></button>
	</div>
</div>
 
<script>
var organizationList = {};
//加载xml树
function orgnizationTree(){
	organizationList.tree=new dhtmlXTreeObject("orgnizationTree","100%","100%",0);
	organizationList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	organizationList.tree.setOnClickHandler(organizationList.edit);
	organizationList.tree.setXMLAutoLoading("WorkFlowCommonBizController.do?method=getOwnerOrgTree&order=sort&action=listById&isOpen=0");
	organizationList.tree.loadXML($("#initPath").val()+"/WorkFlowCommonBizController.do?method=getOwnerOrgTree&action=listTop&order=sort",function(){
		
	});
}
organizationList.edit = function(objId){
	if (objId == '-1') {
		return false;
	}
	$('[id=department.objId]').val(objId);
	$('[id=department.name]').val(organizationList.tree.getItemText(objId));
	$('#select_dept .epsDialogCloseReload').click();
}
orgnizationTree();
$('[name=return_to_node_form]').click(function(){
	$('[id=department.objId]').val('');
	$('[id=department.name]').val('');
	$('#select_dept .epsDialogCloseReload').click();
})
</script>
