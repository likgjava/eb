<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>


<textarea class="hidden" id="validAreaValue" name="validAreaValue">${validListStr}</textarea>
<textarea class="hidden" id="invalidAreaValue" name="invalidAreaValue">${inValidListStr}</textarea>

<div class="conOperation">
	<button type="button" id="saveAgreementArea" onclick="selectDistrictToAreaPage.saveArea();"><span><spring:message code="globe.save"/></span></button>
	<button type="button" id="closeAgreementArea" onclick="selectDistrictToAreaPage.close();"><span><spring:message code="globe.close"/></span></button>
</div>
<div id="selectDistrictToArea"></div>

<script type="text/javascript">

var selectDistrictToAreaPage = {};
selectDistrictToAreaPage.tree;

selectDistrictToAreaPage.validAreaValue = $("#validAreaValue").val();
selectDistrictToAreaPage.invalidAreaValue = $("#invalidAreaValue").val();


//树节点check方法
selectDistrictToAreaPage.agreementAreaCheck = function(id){
	var tempId = selectDistrictToAreaPage.tree.getParentId(id);
	while( tempId != "-1" ){
		if( selectDistrictToAreaPage.tree.isItemChecked(tempId)=="0" && selectDistrictToAreaPage.tree.isItemChecked(id)=="1" ){
			selectDistrictToAreaPage.tree.setCheck(tempId,"unsure");
		}
		//当前节点check状态为不选中时
		if(selectDistrictToAreaPage.tree.isItemChecked(id)!="1" ){
			//处理父节点
			var childCheck = selectDistrictToAreaPage.tree._getAllChecked( selectDistrictToAreaPage.tree._globalIdStorageFind(tempId) ,"",1);
			if(childCheck==null||childCheck==""&& selectDistrictToAreaPage.tree.isItemChecked(tempId)!="1" ){
				selectDistrictToAreaPage.tree.setCheck(tempId,"0");
			}
		}
		tempId = selectDistrictToAreaPage.tree.getParentId(tempId);
	}
	//处理本节点(如果有子节点被选中就不可变为空)
	var subCount = selectDistrictToAreaPage.tree._globalIdStorageFind(id,0,1).childsCount;
	var currentSubUncheck = selectDistrictToAreaPage.tree._getAllChecked( selectDistrictToAreaPage.tree._globalIdStorageFind(id,0,1) ,"",1);
	if( selectDistrictToAreaPage.tree.isItemChecked(id)=="0" && currentSubUncheck.split(",").length < (subCount+1) ){
		selectDistrictToAreaPage.tree.setCheck(id,"unsure");
	}
}

//展开节点的操作
selectDistrictToAreaPage.onOpenNodeAfter = function(id){
	//设置展开节点下子节点的check状态
	selectDistrictToAreaPage.checkStatusByServiceValue(id);
}

//根据服务器保存的值设置id下所有子节点check状态,用于数据回填
selectDistrictToAreaPage.checkStatusByServiceValue = function(id){
	$.each(selectDistrictToAreaPage.tree.getAllSubItems(id).split(","),function(idx,node){
		if( selectDistrictToAreaPage.validAreaValue.indexOf( node )>=0 ){
			selectDistrictToAreaPage.tree.setCheck(node,"1");
			selectDistrictToAreaPage.tree.disableCheckbox(node,1);//还要将回填的数据锁定
		}
		if( selectDistrictToAreaPage.invalidAreaValue.indexOf( node )>=0 ){
			selectDistrictToAreaPage.tree.setCheck(node,"unsure");
		}
	})
}

//初始化树
selectDistrictToAreaPage.initSelectTree = function(){
	selectDistrictToAreaPage.tree=new dhtmlXTreeObject('selectDistrictToArea',"100%","100%",0);
	selectDistrictToAreaPage.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	selectDistrictToAreaPage.tree.enableDragAndDrop(false);
	selectDistrictToAreaPage.tree.setOnCheckHandler(selectDistrictToAreaPage.agreementAreaCheck);
	selectDistrictToAreaPage.tree.setOnClickHandler(selectDistrictToAreaPage.agreementAreaClick);
	selectDistrictToAreaPage.tree.setOnOpenEndHandler(selectDistrictToAreaPage.onOpenNodeAfter);
	selectDistrictToAreaPage.tree.enableCheckBoxes(1);
	selectDistrictToAreaPage.tree.setXMLAutoLoading($('#initPath').val()+'/DistrictController.do?method=getTree&action=listById&order=sort');//点 + 号触发展开事件
	selectDistrictToAreaPage.tree.loadXML($('#initPath').val()+'/DistrictController.do?method=getTree&action=listTop&isOpen=0&order=sort',function(){
		//第一次初始化完毕之后
		selectDistrictToAreaPage.checkStatusByServiceValue("-1");
	})
}

//保存区间
selectDistrictToAreaPage.saveArea = function(){
	var validArea = selectDistrictToAreaPage.tree.getAllChecked();
	var invalidArea = selectDistrictToAreaPage.tree.getAllPartiallyChecked();
	if(!validArea && !invalidArea){alert("没有选中任何数据！");return ;}
	$.getJSON($("#initPath").val()+"/AreaController.do?method=saveAreaBatch",{"validArea":validArea,"invalidArea":invalidArea},function(json){
		if(json.success) {
			alert("保存成功！");
			selectDistrictToAreaPage.close();
		}
	})
}

//关闭
selectDistrictToAreaPage.close = function(){
	$("#closeAgreementArea").closest(".epsDialog").find(".epsDialogClose").click();
}

$(document).ready(function(){

	//初始化树
	selectDistrictToAreaPage.initSelectTree();
});
</script>