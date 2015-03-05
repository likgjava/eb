
var floatOrgTree={};
floatOrgTree.tree;
floatOrgTree.isSort=0;

function treeTab(){
	treeHe();
	$(window).wresize(treeHe);
	var h =$('#contentThis').height();
	$('.treeOutside').jqResize('.treeResize',function(w){if(parseInt(w)<240){$('.treeOutside').width(240);}	$('.treeOutside').height(h)});
	function treeHe(){$('.treeOutside,.treeRight,.treeResize').height($('#contentThis').height());}
}

//点击节点事件
floatOrgTree.nodeClick=function(id){
	if(id!=-1){
		if($('#oldPid').val()!=id){
			$.getJSON($('#initPath').val()+'/OrganizationController.do?method=changeParent', {'orgId':$('#orgId').val(),'oldPid':$('#oldPid').val(),'newPid':id}, function(json){
				if(json.result)alert(json.result);;
				if(json.failure)return;
				$("#organizationGrid").reload();
				$('#epsDialogClose').click();
			});
		}
		else{
			$('#epsDialogClose').click();
		}
	}
	
}

//初始化树
floatOrgTree.initOrgTree=function(){
	//dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
	floatOrgTree.tree=new dhtmlXTreeObject('floatOrgTree',"100%","100%",0);
	floatOrgTree.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	floatOrgTree.tree.enableThreeStateCheckboxes(false);
	floatOrgTree.tree.setOnClickHandler(floatOrgTree.nodeClick);
	//alert($('#type').val());
	floatOrgTree.tree.setXMLAutoLoading($('#initPath').val()+'/OrganizationController.do?method=getOwnerOrgTree&action=listById&isOpen=0'+"&type="+$('#type').val());//点 + 号触发展开事件
	floatOrgTree.tree.loadXML($('#initPath').val()+"/OrganizationController.do?method=getOwnerOrgTree&action=listTop&isOpen=0",function(){
		//初始化树之后的回调函数
		if($("#roleOrgId").val()!="0" && $("#roleOrgId").val()!=""){
			floatOrgTree.tree.changeItemId("-1",$("#roleOrgId").val());
		}
	});
}

$(document).ready(function(){	
	floatOrgTree.initOrgTree();//加载树
	$('#_clear').click(function(){
		if($('#oldPid').val()!=''){
			$.getJSON($('#initPath').val()+'/OrganizationController.do?method=changeParent', {'orgId':$('#orgId').val(),'oldPid':$('#oldPid').val(),'newPid':''}, function(json){
				$("#organizationGrid").reload();
			});
		}
		$('#epsDialogClose').click();
	})
	  	
   	
});
