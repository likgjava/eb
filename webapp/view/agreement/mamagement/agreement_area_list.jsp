<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<input type="hidden" name="areaId" id="areaId" value="">
<input type="hidden" name="isDelete" id = "isDelete" value="">
<input type="hidden" name="isModify" id = "isModify" value="">
<input type="hidden" name="isRoot" id = "isRoot" value="">




<textarea class="hidden" name="invalidValue" id="invalidValue">${inValidListStr}</textarea>
<textarea class="hidden" name="deleteValue" id="deleteValue">${delteListStr}</textarea>


<div class="formTips attention">
	<ul>
		<li>
			注意：区域节点中划<strike>横线</strike>的代表被删除的，虚线框
			<img src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxTree/imgs/folderClosedV.gif">代表无效的，
			黄色文件夹<img src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxTree/imgs/folderClosed.gif">和
			白色叶子节点<img src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxTree/imgs/leaf.gif">代表正常使用中的
		</li>
	</ul>
</div>


<div class="treePage frameMainSub frameMs12">
    <!-- 树形区 -->
	<div class="treeOutside frameMain">
	   <div class="treeBtn">
		      	<ul>
		        	<li>
		            	<a id="up" class="upMove" href="javascript:void(0);" onclick="return false;"><span></span>上移</a>
		            </li>
		            <li>
		            	<a id="down" class="downMove" href="javascript:void(0);" onclick="return false;"><span></span>下移</a>
		            </li>
		         </ul> 		
		</div>
	  <div id="agreementAreaTree" class="treeContentDiv"></div>
	  <div class="treeResize" ></div>
	</div>
 
	<div class="treeRight frameSub" id="treeRight">
		<!-- 详情 -->
		<div class="formLayout form1Pa"  id="agreementAreaDetail">
			<div class="operationBtnDiv r">
					<button id="newAgreementArea" type="button" onclick="AreaTree.toAddArea();"><span>新增区间</span></button>
					<button id="deleteAgreementArea" type="button" onclick="AreaTree.deleteArea();" ><span>删除区间</span></button>
			</div>
			<h4><span>协议区间信息</span></h4>
			<form id = "areaDetail">
			<ul>
				<li><label>协议区间名称：</label><span id="areaName"></span></li>
	            <li><label for="input02">行政区域编码：</label><span id="areaCodes"></span></li>
	            <li><label for="input02">行政区域名称：</label><span id="areaNames"></span></li>
	            <li><label for="input03">备注：</label><span id="memo"></span></li>
			</ul>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
var AreaTree = {};
AreaTree.isSort=0;
//全局树对象
AreaTree.tree;

//树形节点展开后
AreaTree.onOpenNodeAfter = function(id){
	AreaTree.setStyleOrSubStyle(id);
}

//设置节点样式
AreaTree.setStyleOrSubStyle = function( id ){
	var invalidValue = $("#invalidValue").val();
	var deleteValue = $("#deleteValue").val();
	
	if(invalidValue){
		if (invalidValue.indexOf(id)>=0 ) AreaTree.tree.setItemImage(id,"folderOpenV.gif","folderClosedV.gif");
		if( AreaTree.tree.getSubItems(id) ){
			$.each(AreaTree.tree.getSubItems(id).split(","),function(index,obj){
				AreaTree.setStyleOrSubStyle(obj);
			})
		}
	}
	if(deleteValue ){
		if (deleteValue.indexOf(id)>=0 ) AreaTree.tree.setItemText(id,"<strike>"+AreaTree.tree.getItemText(id)+"</strike>");
		if( AreaTree.tree.getSubItems(id) ){
			$.each(AreaTree.tree.getSubItems(id).split(","),function(index,obj){
				AreaTree.setStyleOrSubStyle(obj);
			})
		}
	}
}
	
//加载菜单树
AreaTree.menuTree = function(id){
	AreaTree.tree=new dhtmlXTreeObject("agreementAreaTree","100%","100%",0);
	AreaTree.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	AreaTree.tree.enableThreeStateCheckboxes(true);
	AreaTree.tree.setOnClickHandler(AreaTree.agreementAreaclick);
	AreaTree.tree.setOnOpenEndHandler(AreaTree.onOpenNodeAfter);//设置属性节点展开后回调方法
	AreaTree.tree.setXMLAutoLoading($('#initPath').val()+"/AreaController.do?method=getOwnerAreaTree&order=sort&action=listById&isOpen=0");
	AreaTree.tree.loadXML($("#initPath").val()+"/AreaController.do?method=getOwnerAreaTree&action=listTop&order=sort&isOpen=0",function(){
		AreaTree.setStyleOrSubStyle("-1");
	});
}

//菜单节点点击事件
AreaTree.agreementAreaclick = function(id){
	if(id != -1){//去除资源树顶级节点
		$.getJSON($('#initPath').val()+'/AreaController.do?method=getAreaInfo',{objId:id},function(json){
			json2Object("areaDetail",json.area);
			//是否可以删除
			"true"==json.isDelete?$("#isDelete").val(true):$("#isDelete").val(false);
			//是否可以修改
			"true"==json.isDelete?$("#isModify").val(true):$("#isModify").val(false);
			//objId
			$("input[name=areaId]").val(json.area.objId);
		});
	}
}

//删除区间
AreaTree.deleteArea = function(){
	if("false"==$("#isDelete").val()){
		alert("已被引用，不允许删除!");
		return;
	}
	if(null!=AreaTree.tree.getSelectedItemId()&&""!=AreaTree.tree.getSelectedItemId()){
		if(confirm("确定删除此区间？")){
			$.getJSON($('#initPath').val()+'/AreaController.do?method=delArea',{"areaId":AreaTree.tree.getSelectedItemId()},function(json){
				if(json.failure){
					alert("删除失败,所选区期间使用中!");
					return;
				}else{
					alert(json.result);
					//AreaTree.tree.deleteItem(AreaTree.tree.getSelectedItemId(),true);

					//var itemText = AreaTree.tree.getItemText(AreaTree.tree.getSelectedItemId()
							
					AreaTree.tree.setItemText(AreaTree.tree.getSelectedItemId(),"<strike>"+AreaTree.tree.getItemText(AreaTree.tree.getSelectedItemId())+"</strike>");
				}
			});
			
		}
	}
}

//跳转到新增区间
AreaTree.toAddArea = function(){
	$.epsDialog({
		id:"toAddAreaDialog",
		title:"新增区间",
		url:$("#initPath").val()+"/AreaController.do?method=toDistrictTree"
		,width:400,
		onClose:function(){
			$("#conBody").loadPage($("#initPath").val()+"/AreaController.do?method=toAreaList");
		}
	})
}

$(document).ready(function(){
	//初始化菜单树	
	AreaTree.menuTree();
	
	//节点上移
   	$("#up").click(function(){
   		if(AreaTree.isSort==0){
   			AreaTree.isSort=1;
   	   		if(treeUtil.sortUp(AreaTree.tree,"up","com.gpcsoft.agreement.management.domain.Area"))
   	   		AreaTree.isSort=0;
   		}
	})
	
	//节点下移
	$("#down").click(function(){
		if(AreaTree.isSort==0){
			AreaTree.isSort=1;
			if(treeUtil.sortDown(AreaTree.tree,"down","com.gpcsoft.agreement.management.domain.Area"))
				AreaTree.isSort=0;
   		}
	})

})
</script>
