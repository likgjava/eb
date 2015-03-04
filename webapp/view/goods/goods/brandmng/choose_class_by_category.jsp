<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

var chooseClassByCategory = {};

chooseClassByCategory.edit=function(objId){
	
	//只能选择叶子节点
	if($("#_childNodeOnly").val()=='true'&&chooseClassByCategory.tree.hasChildren(objId)!=0){
		alert("请选择叶子节点（白色节点）！");return;
	}
	
	if(objId==" ")
		return;
	if(objId=="-1")
		return;
	else{
		if($.trim($("#_isCheckBox").val())==""||$("#_isCheckBox").val()=="null"){
			var whileId = objId;
			var names = chooseClassByCategory.tree.getItemText(whileId);
			
			//选择所有父节点的id,name
			if( $("#_andAllParent").val()=='true' ){
				while( chooseClassByCategory.tree.getParentId(whileId)!='-1' ){
					whileId = chooseClassByCategory.tree.getParentId(whileId);
					names =  chooseClassByCategory.tree.getItemText( whileId ) + ',' + names ;
					objId = whileId + ',' + objId;
				}
			}
			returnValue(objId,names);
		}
		return;
	}
}

chooseClassByCategory.nodeOpen = function(id){
	
	if(id!=-1&&chooseClassByCategory.tree.hasChildren(id)!=0){
		chooseClassByCategory.tree.disableCheckbox(id,0);
	}

	if(chooseClassByCategory.tree.hasChildren(id)==0){
		chooseClassByCategory.tree.disableCheckbox(id,0);
	}
}

chooseClassByCategory.unableCheckIfNotLeaf = function(objId){
	$.each(chooseClassByCategory.tree.getAllSubItems(objId).split(","),function(index,obj){
		if(chooseClassByCategory.tree.hasChildren(obj)!=0){
			chooseClassByCategory.tree.disableCheckbox(obj,1);
		}
	})
}


//添加选中值
function addCheckedItem(id,name) {
	// 判断是否已经存在
	var isExis = false;
	$('#checkedTreeItems').find("li").each(function() {
		if($(this).find(":checkbox").val() == id){
			isExis = true;
		}
	});
	if($.trim($("#_isCheckBox").val())=="" || $("#_isCheckBox").val()=="null") {
		$('#checkedTreeItems').empty();
	}
	if(!isExis){
		$('#checkedTreeItems').append("<li><input type='checkbox' name='checkedTreeItemBox' value='" + id + "'/>&nbsp;<span>" + name + "</span></li>");
	}
}

function returnValue(id,name){
	//回填id
	if("" == $("#_ID").val())
		document.getElementById($("#_property").val()+".objId").value=id;
	else
		document.getElementById($("#_ID").val()).value=id;
	
	//回填name
	if("" == $("#_NAME").val()){
		document.getElementById($("#_property").val()+".name").value=name;
		$(document.getElementById($("#_property").val()+".name")).keyup();
	}
	else{
		document.getElementById($("#_NAME").val()).value=name;
		$(document.getElementById($("#_NAME").val())).keyup();
	}
	$("#_cancel").click();
}


$(document).ready(function(){

	var orgTypeParam = $("#_isManager").val()=='true'?'':'&orgType=supplier'

	//初始化树
	chooseClassByCategory.tree=new dhtmlXTreeObject('dialogTreeBox',"100%","100%",0); 
	chooseClassByCategory.tree.enableDragAndDrop(false);
	chooseClassByCategory.tree.setDragHandler(false);
	
	//只能选子节点 by yucy
	if($("#_childNodeOnly").val()=='true'&&$("#_isCheckBox").val()=='true'){
		chooseClassByCategory.tree.setOnOpenEndHandler(function(id){
			chooseClassByCategory.unableCheckIfNotLeaf(id)
		});
		
		//打开时可以由父节点级联选择
		chooseClassByCategory.tree.setOnOpenEndHandler(chooseClassByCategory.nodeOpen);
	}
	
	if($.trim($("#_isCheckBox").val())!=""&&$("#_isCheckBox").val()!="null"){
		chooseClassByCategory.tree.enableCheckBoxes(true)
		//初始化按钮不级联选中 0为表示不选中,1为选中状态
		if($("#_checkStatus").val() == "") {
			chooseClassByCategory.tree.enableThreeStateCheckboxes(0);
		}else {
			chooseClassByCategory.tree.enableThreeStateCheckboxes($("#_checkStatus").val());
		}

		$("#_cancel").before("<button id='_OK'><span>确定</span></button>")
		$("#_OK").click(function(){
			var ids = $('#checkedTreeItems').find("li").find(":checkbox").map(function(){return $(this).val();}).get().join(",") ;
			var names = $('#checkedTreeItems').find("li").find(":checkbox").map(function(){return $.trim($(this).parent().find("span").text());}).get().join(",") ;
			returnValue(ids+"",names);
		})
	}
	
	chooseClassByCategory.tree.setImagePath($("#initPath").val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	chooseClassByCategory.tree.setXMLAutoLoading($("#initPath").val()+"/GoodsClassController.do?method=getTreeByCategory"+orgTypeParam+"&action=listById&_isOpen=false" );
	var url=$("#initPath").val()+"/GoodsClassController.do?method=getTreeByCategory"+orgTypeParam+"&action=listTop&_isOpen=false";
	chooseClassByCategory.tree.loadXML(url,function(){

		if($.trim($("#_isCheckBox").val())!=""&&$("#_isCheckBox").val()!="null"){
			var objId = $("#_checkValues").val().split(",");
			if(objId != null && objId.length > 0){
				for(var i = 0 ; i < objId.length ; i ++){
					chooseClassByCategory.tree.setCheck(objId[i],1);
				}
			}
		}
		
		//只能选子节点 by yucy
		if($("#_childNodeOnly").val()=='true'&&$("#_isCheckBox").val()=='true'){
			chooseClassByCategory.unableCheckIfNotLeaf(-1);
			chooseClassByCategory.tree.disableCheckbox(-1,1);
		}
		
		chooseClassByCategory.tree.disableCheckbox(-1,1);//根节点不可选
		
	});


	$("#_clear").click(function(){
		$('#checkedTreeItems').empty();
	})
	$("#_cancel").click(function(){
		//关闭弹出层
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
	
	var _checkedIds = "";
	var _checkedNames = "";
	
	//获取已经选中的ID
	if("" == $("#_ID").val()){
		_checkedIds = document.getElementById($("#_property").val()+".objId").value;
	}else {
		_checkedIds = document.getElementById($("#_ID").val()).value;
	}
	//获取已经选中的name
	if("" == $("#_NAME").val()){
		_checkedNames = document.getElementById($("#_property").val()+".name").value;
	}else{
		_checkedNames = document.getElementById($("#_NAME").val()).value;
	}
	if($.trim(_checkedIds) != ""){
		for(var i = 0; i < $.trim(_checkedIds).split(",").length; i++){
			addCheckedItem($.trim(_checkedIds).split(",")[i],$.trim(_checkedNames).split(",")[i]);
		}
	}
	
	// 添加选中值
	$('#addSelectedTreeItems').click(function() {
		var ids=(chooseClassByCategory.tree.getAllChecked())
		var names="";
		if(ids == null || ids == "" || ids.split(",").length <= 0){
			alert("请选择相关数据!");
			return;
		}
		for(var i=0;i<ids.split(",").length;i++){
			if(chooseClassByCategory.tree.hasChildren(ids.split(",")[i])!=0||ids.split(",")[i]=="-1"){
				continue;
			}
			names+=(chooseClassByCategory.tree.getItemText(ids.split(",")[i]))+","
			addCheckedItem(ids.split(",")[i]+"",chooseClassByCategory.tree.getItemText(ids.split(",")[i]));
		}

	});
	
	// 移除选中值
	$('#removeSelectedTreeItems').click(function() {
		if($('#checkedTreeItems').find("li").find(":checkbox[checked]").map(function(){return $(this).val();}).get().join(",")==""){
			alert("请选择要移除的数据!");
		}
		$('#checkedTreeItems').find("li").find(":checkbox[checked]").each(function() {
			chooseClassByCategory.tree.setCheck($(this).val(),0);
			$(this).parent("li").remove();
		})
	});
	
	
})

</script>

<input type="hidden" id="_isManager"  value="<c:out value="${param.isManager}"/>"/>
<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_isCheckBox"  value="<c:out value="${param.isCheckBox}"/>"/>
<input type="hidden" id="_childNodeOnly"  value="<c:out value="${param.childNodeOnly}"/>"/>
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_isOpen"  value="<c:out value="${param.isOpen}"/>"/>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/>
<input type="hidden" id="_ID"  value="<c:out value="${param.IDS}"/>"/>
<input type="hidden" id="_NAME"  value="<c:out value="${param.NAMES}"/>"/>
<input type="hidden" id="txtURL"  value="<c:out value="${param.txtURL}"/>"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<input type="hidden" id="_checkStatus"  value="<c:out value="${param.checkStatus}"/>"/>

<input type="hidden" id="_andAllParent"  value="<c:out value="${param.andAllParent}"/>"/>

<div style="float: left; border: 1px solid #A0C6EA;<c:choose><c:when test="${param.isCheckBox!=null}">width: 40%;</c:when><c:otherwise>width: 98%;</c:otherwise></c:choose>height: 450px; overflow:auto; zoom:1;">
	<div id="dialogTreeBox" style="zoom:1;">
	</div>
</div>


<c:if test="${param.isCheckBox!=null}">
<div style="float: left; width: 15%; padding-top: 30%; text-align: center;" class="operationBtnDiv">
	<ul>
		<li>
			<button type="button" id="addSelectedTreeItems"><span>添加&nbsp;&gt;</span></button>
		</li>
		<li>
			<button type="button" id="removeSelectedTreeItems"><span>&lt;&nbsp;移除</span></button>
		</li>
		<li>
			<button id="_clear" type="button"><span>清&nbsp;空</span></button>
		</li>
	</ul>
</div>
<div style="border: 1px solid #A0C6EA;  height: 450px; width: 40%; text-indent: 10px;overflow:auto;">
	<h5 class="center">已选中值</h5>
	<ul id="checkedTreeItems">
	</ul>
</div>
</c:if>

<div class="conOperation">
	<button id="_cancel"><span>取消</span></button>
</div>