
var floatEmployeeTree={};
floatEmployeeTree.tree;
floatEmployeeTree.currentId='';
floatEmployeeTree.isSort=0;

function treeTab(){
	treeHe();
	$(window).wresize(treeHe);
	var h =$('#contentThis').height();
	$('.treeOutside').jqResize('.treeResize',function(w){if(parseInt(w)<240){$('.treeOutside').width(240);}	$('.treeOutside').height(h)});
	function treeHe(){$('.treeOutside,.treeRight,.treeResize').height($('#contentThis').height());}
}

//点击节点事件
floatEmployeeTree.nodeClick=function(id){
	// alert($("#hiddenProperty").val());
	// alert($("#showProperty").val());
	// alert($("#orgId").val());
	floatEmployeeTree.currentId=id;
	if("employee"==floatEmployeeTree.tree.getItemTooltip(id)){
		$("input[id='"+$("#hiddenProperty").val()+"']").val(id);
		//$("#"+$("#hiddenProperty").val()).val(id);
		//$("#"+$("#showProperty").val()).val(floatEmployeeTree.tree.getItemText(id));
		$("input[id='"+$("#showProperty").val()+"']").val(floatEmployeeTree.tree.getItemText(id));
		$('#epsDialogClose').click();
	}
	
}

//初始化树
floatEmployeeTree.initEmployeeTree=function(){
	//dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
	floatEmployeeTree.tree=new dhtmlXTreeObject('floatEmployeeTree',"100%","100%",0);
	floatEmployeeTree.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	//floatEmployeeTree.tree.enableCheckBoxes(1);
	//floatEmployeeTree.tree.enableDragAndDrop(1);
	floatEmployeeTree.tree.enableThreeStateCheckboxes(false);
	floatEmployeeTree.tree.setOnClickHandler(floatEmployeeTree.nodeClick);
	floatEmployeeTree.tree.setXMLAutoLoading($('#initPath').val()+'/OrganizationController.do?method=getOrgEmployeeTree&action=listById&isOpen=0');//点 + 号触发展开事件
	floatEmployeeTree.tree.loadXML($('#initPath').val()+'/OrganizationController.do?method=getOrgEmployeeTree&action=listTop&isOpen=0&id=0',function(){
		//初始化树之后的回调函数
//		if($("#orgId").val()!="0" && $("#orgId").val()!=""){
//			floatEmployeeTree.tree.setItemText("-1",PlatForm.user.emp.org.name,"orgnization");
//			floatEmployeeTree.tree.changeItemId("-1",$("#orgId").val());
//		}
	});
}

$(document).ready(function(){	
	floatEmployeeTree.initEmployeeTree();//加载树
	
   	//展开合并所有节点
   	var isOpen = false;  //是否是展开状态
	var isFirst = true;  //是否是第一次展开
	$('#openAll').click(function(){
		if(!isOpen){
			if(isFirst){
				floatEmployeeTree.tree.deleteChildItems(0);//删掉根节点的所有子节点
				floatEmployeeTree.tree.loadXML($('#initPath').val()+'/EmployeeController.do?method=getTree&action=listTop&isOpen=1');
				isFirst = false;
			}
			else floatEmployeeTree.tree.openAllItems('0');
			$('#openAll').html('关闭');
			isOpen=true;
		}else{
			floatEmployeeTree.tree.closeAllItems('0');
			$('#openAll').html('展开');
			isOpen=false;
		}
	});
	
   	$("#clearEmp").click(function(){
   		$("input[id='"+$("#hiddenProperty").val()+"']").val('');
		$("input[id='"+$("#showProperty").val()+"']").val('');
		$('#epsDialogClose').click();
   	})
  
});
