<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<textarea class="hidden" name="invalidValue" id="invalidValue">${inValidListStr}</textarea>

<div class="formTips attention">
	<ul>
		<li>
			注意：区域节点中虚线框<img src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxTree/imgs/folderClosedV.gif">代表无效的，
			黄色文件夹<img src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxTree/imgs/folderClosed.gif">和
			白色节点<img src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxTree/imgs/leaf.gif">代表正常使用的
		</li>
	</ul>
</div>


<!-- 树形区 -->
<div id="agreementAreaTree" class="treeContentDiv"></div>

<div class="conOperation">
	<button type="button" id="selectAgreementArea" onclick="selectAreaTree.selectAndReturn();"><span>确定</span></button>
	<button type="button" id="closeAgreementArea" onclick="selectAreaTree.close();"><span><spring:message code="globe.close"/></span></button>
</div>

<script type="text/javascript">
var selectAreaTree = {};
selectAreaTree.isSort=0;
//全局树对象
selectAreaTree.tree;

//树形节点展开后
selectAreaTree.onOpenNodeAfter = function(id){
	selectAreaTree.setStyleOrSubStyle(id);
}

//设置节点样式
selectAreaTree.setStyleOrSubStyle = function( id ){
	var invalidValue = $("#invalidValue").val();
	if(invalidValue){
		if (invalidValue.indexOf(id)>=0 ) {
			selectAreaTree.tree.setItemImage(id,"folderOpenV.gif","folderClosedV.gif");
			selectAreaTree.tree.disableCheckbox(id,1)
		}
		if( selectAreaTree.tree.getSubItems(id) ){
			$.each(selectAreaTree.tree.getSubItems(id).split(","),function(index,obj){
				selectAreaTree.setStyleOrSubStyle(obj);
			})
		}
	}
}
	
//加载菜单树
selectAreaTree.menuTree = function(id){
	selectAreaTree.tree=new dhtmlXTreeObject("agreementAreaTree","100%","100%",0);
	selectAreaTree.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	selectAreaTree.tree.enableThreeStateCheckboxes(true);
	selectAreaTree.tree.enableCheckBoxes(1);
	selectAreaTree.tree.setOnOpenEndHandler(selectAreaTree.onOpenNodeAfter);//设置属性节点展开后回调方法
	selectAreaTree.tree.setXMLAutoLoading($('#initPath').val()+"/AreaController.do?method=getOwnerAreaTree&order=sort&action=listById&isOpen=0&isValid=2");
	selectAreaTree.tree.loadXML($("#initPath").val()+"/AreaController.do?method=getOwnerAreaTree&action=listTop&order=sort&isOpen=0&isValid=2",function(){
		selectAreaTree.setStyleOrSubStyle("-1");
	});
}

//选择和回填
selectAreaTree.selectAndReturn = function(){
	var returnName="";
	var returnId="";
	var allcheck = selectAreaTree.tree.getAllChecked();
	if(allcheck){
		$.each(allcheck.split(","),function(index,obj){
			if(obj!="-1"){
				returnName += ( returnName!=""?","+selectAreaTree.tree.getItemText(obj):selectAreaTree.tree.getItemText(obj) );
				returnId += ( returnId!=""?","+obj:obj);
			}
		})
		$("input[name=areaIds]").val(returnId);
		$("textarea[name=areaNames]").val(returnName);
		selectAreaTree.close();
	}else{
		alert("您没有选择数据！");
	}
}

//关闭
selectAreaTree.close = function(){
	$("#closeAgreementArea").closest(".epsDialog").find(".epsDialogClose").click();
}

$(document).ready(function(){
	//初始化菜单树	
	selectAreaTree.menuTree();
})
</script>
